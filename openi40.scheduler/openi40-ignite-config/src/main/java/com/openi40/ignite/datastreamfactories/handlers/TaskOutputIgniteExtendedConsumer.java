package com.openi40.ignite.datastreamfactories.handlers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import javax.cache.Cache.Entry;

import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteCache;
import org.apache.ignite.IgniteDataStreamer;
import org.apache.ignite.cache.query.Query;
import org.apache.ignite.cache.query.QueryCursor;
import org.apache.ignite.cache.query.ScanQuery;
import org.apache.ignite.internal.processors.cache.query.SqlFieldsQueryEx;
import org.apache.ignite.lang.IgniteBiPredicate;

import com.openi40.scheduler.input.model.material.StockSupplyInputDto;
import com.openi40.scheduler.input.model.orders.WorkOrderInputDto;
import com.openi40.scheduler.input.model.tasks.AbstractTaskMaterialReservationInputDto;
import com.openi40.scheduler.input.model.tasks.TaskInputDto;
import com.openi40.scheduler.input.model.tasks.TaskProductionMaterialReservationInputDto;
import com.openi40.scheduler.input.model.tasks.TaskPurchaseMaterialReservationInputDto;
import com.openi40.scheduler.input.model.tasks.TaskStockMaterialReservationInputDto;
import com.openi40.scheduler.mapper.DefaultEntitiesFactory;
import com.openi40.scheduler.mapper.IMapper;
import com.openi40.scheduler.mapper.IMapperFactory;
import com.openi40.scheduler.mapper.MapperException;
import com.openi40.scheduler.output.model.tasks.TaskOutputDto;
import com.openi40.scheduler.output.model.tasks.TaskOutputDto.LinkType;
import com.openi40.scheduler.output.model.tasks.TaskOutputDto.TaskMaterialTransfer;

/*WorkOrderOutputDto.class, TaskOutputDto.class, TaskRelationOutputDto.class,
			PeggingOutputDto.class,ApsSchedulingSetOutputDto.class */
public class TaskOutputIgniteExtendedConsumer extends AbstractIgniteExtendedConsumer<TaskOutputDto> {
	private IMapper<TaskOutputDto, TaskInputDto> taskMapper = null;
	private Map dummyParams = new HashMap();
	private IgniteDataStreamer<String, TaskInputDto> taskInputStreamer = null;
	private IMapper<TaskMaterialTransfer, TaskStockMaterialReservationInputDto> stockMapper;
	private IMapper<TaskMaterialTransfer, TaskProductionMaterialReservationInputDto> productionMapper;
	private IMapper<TaskMaterialTransfer, TaskPurchaseMaterialReservationInputDto> purchaseMapper;
	private IgniteCache<String, TaskStockMaterialReservationInputDto> stockResCache = null;
	private IgniteCache<String, TaskProductionMaterialReservationInputDto> productionResCache = null;
	private IgniteCache<String, TaskPurchaseMaterialReservationInputDto> purchaseResCache = null;
	private ArrayList<GroupedMods> resultsList;

	public TaskOutputIgniteExtendedConsumer(Ignite ignite, IMapperFactory mapperFactory) {
		super(TaskOutputDto.class, ignite, mapperFactory);

	}

	@Override
	protected void initializeInputLayerModificationSupport() throws IgniteExtenderConsumerException {
		this.resultsList = new ArrayList<GroupedMods>();
		this.taskInputStreamer = this.ignite.dataStreamer(TaskInputDto.class.getName());
		this.taskInputStreamer.allowOverwrite(true);
		this.stockResCache = ignite.cache(TaskStockMaterialReservationInputDto.class.getName());
		this.productionResCache = ignite.cache(TaskProductionMaterialReservationInputDto.class.getName());
		this.purchaseResCache = ignite.cache(TaskPurchaseMaterialReservationInputDto.class.getName());
		try {
			this.taskMapper = this.mapperFactory.createMapper(TaskOutputDto.class, TaskInputDto.class);
			this.stockMapper = this.mapperFactory.createMapper(TaskMaterialTransfer.class,
					TaskStockMaterialReservationInputDto.class);
			this.productionMapper = this.mapperFactory.createMapper(TaskMaterialTransfer.class,
					TaskProductionMaterialReservationInputDto.class);
			this.purchaseMapper = this.mapperFactory.createMapper(TaskMaterialTransfer.class,
					TaskPurchaseMaterialReservationInputDto.class);
		} catch (MapperException e) {
			throw new IgniteExtenderConsumerException("Exception in initializeInputLayerModificationSupport()", e);
		}

	}

	static class GroupedMods {
		public TaskInputDto taskInput = null;
		public List<TaskStockMaterialReservationInputDto> stockSupply = new ArrayList<>();
		public List<TaskProductionMaterialReservationInputDto> productionSupply = new ArrayList<>();
		public List<TaskPurchaseMaterialReservationInputDto> purchaseSupply = new ArrayList<>();

	}

	@Override
	protected void modifyInputLayer(TaskOutputDto t) throws IgniteExtenderConsumerException {
		try {
			GroupedMods mods = new GroupedMods();
			TaskInputDto inputTask = this.taskMapper.mapInstance(t, TaskInputDto.class, DefaultEntitiesFactory.Instance,
					this.dummyParams, true);
			mods.taskInput = inputTask;

			if (t.getMaterialTransfer() != null) {
				for (TaskMaterialTransfer transfer : t.getMaterialTransfer()) {
					if (transfer.getLinkType() == LinkType.STOCK) {
						TaskStockMaterialReservationInputDto stock = stockMapper.mapInstance(transfer,
								TaskStockMaterialReservationInputDto.class, DefaultEntitiesFactory.Instance,
								dummyParams, true);
						mods.stockSupply.add(stock);
					} else if (transfer.getLinkType() == LinkType.PRODUCTION) {
						TaskProductionMaterialReservationInputDto production = productionMapper.mapInstance(transfer,
								TaskProductionMaterialReservationInputDto.class, DefaultEntitiesFactory.Instance,
								dummyParams, true);
						mods.productionSupply.add(production);
					} else if (transfer.getLinkType() == LinkType.PURCHASE) {
						TaskPurchaseMaterialReservationInputDto purchase = purchaseMapper.mapInstance(transfer,
								TaskPurchaseMaterialReservationInputDto.class, DefaultEntitiesFactory.Instance,
								dummyParams, true);
						mods.purchaseSupply.add(purchase);
					} else
						LOGGER.error("Transfer=> " + transfer + " of unkown type");
					;
				}
			}
			this.resultsList.add(mods);
		} catch (MapperException e) {
			throw new IgniteExtenderConsumerException("Exception in initializeInputLayerModificationSupport()", e);
		}
	}

	private <T extends AbstractTaskMaterialReservationInputDto> void removeTaskRelatedAndSave(
			IgniteCache<String, T> cache, List<T> toSave, Map<String, Boolean> taskCodes) {
		IgniteBiPredicate<String, T> predicate = (key, item) -> item.getTaskCode() != null
				&& taskCodes.containsKey(item.getTaskCode());
		QueryCursor<Entry<String, T>> cursor = cache.query(new ScanQuery<>(predicate));
		List<Entry<String, T>> list = cursor.getAll();
		for (Entry<String, T> entry : list) {
			cache.remove(entry.getKey());
		}
		cursor.close();
		for(T item:toSave) {
			cache.put(item.getCode(), item);
		}
	}

	@Override
	public void endReached() {
		List<TaskStockMaterialReservationInputDto> stockSupply = new ArrayList<>();
		List<TaskProductionMaterialReservationInputDto> productionSupply = new ArrayList<>();
		List<TaskPurchaseMaterialReservationInputDto> purchaseSupply = new ArrayList<>();
		Map<String, Boolean> tasks = new HashMap<>();
		for (GroupedMods entry : resultsList) {
			this.taskInputStreamer.addData(entry.taskInput.getCode(), entry.taskInput);
			stockSupply.addAll(entry.stockSupply);
			productionSupply.addAll(entry.productionSupply);
			purchaseSupply.addAll(entry.purchaseSupply);
			tasks.put(entry.taskInput.getCode(), true);
		}
		removeTaskRelatedAndSave(this.stockResCache ,stockSupply, tasks);
		removeTaskRelatedAndSave(this.purchaseResCache ,purchaseSupply, tasks);
		removeTaskRelatedAndSave(this.productionResCache ,productionSupply, tasks);
		super.endReached();
		try {
			this.taskInputStreamer.flush();
			this.taskInputStreamer.close();
		} catch (Throwable th) {
		}
	}

}
