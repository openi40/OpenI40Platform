package com.openi40.scheduler.client.restapi;

import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.openi40.scheduler.apsdatacache.ApsDataCacheException;
import com.openi40.scheduler.apsdatacache.IApsDataCache;
import com.openi40.scheduler.apsdatacache.IApsDataCacheAggregator;
import com.openi40.scheduler.client.material.model.DailyWarehouseGraph;
import com.openi40.scheduler.client.material.model.MaterialPlanningDto;
import com.openi40.scheduler.client.material.model.MaterialPlanningDto.MaterialWarehousePlanningDto;
import com.openi40.scheduler.client.model.material.ProductionSupplyDto;
import com.openi40.scheduler.client.model.material.PurchaseSupplyDto;
import com.openi40.scheduler.client.model.material.StockSupplyDto;
import com.openi40.scheduler.client.model.material.SupplyReservationDto;
import com.openi40.scheduler.client.model.orders.SalesOrderLineDto;
import com.openi40.scheduler.client.restapi.genericmodel.AbstractBaseResource;
import com.openi40.scheduler.common.utils.DateUtil;
import com.openi40.scheduler.common.utils.DateUtil.Week;
import com.openi40.scheduler.engine.contextualplugarch.ILazyContextualBusinessLogicFactoryLoader;
import com.openi40.scheduler.engine.material.IMaterialTimeLineManager;
import com.openi40.scheduler.mapper.DefaultEntitiesFactory;
import com.openi40.scheduler.mapper.IMapper;
import com.openi40.scheduler.mapper.IMapperFactory;
import com.openi40.scheduler.mapper.MapperException;
import com.openi40.scheduler.model.aps.ApsData;
import com.openi40.scheduler.model.aps.ApsWindow;
import com.openi40.scheduler.model.companystructure.Warehouse;
import com.openi40.scheduler.model.dao.DataModelDaoException;
import com.openi40.scheduler.model.dao.IProductDao;
import com.openi40.scheduler.model.dao.IProductionSupplyDao;
import com.openi40.scheduler.model.dao.IPurchaseSupplyDao;
import com.openi40.scheduler.model.dao.ISalesOrderLineDao;
import com.openi40.scheduler.model.dao.IStockSupplyDao;
import com.openi40.scheduler.model.dao.IWarehouseDao;
import com.openi40.scheduler.model.material.AbstractSupply;
import com.openi40.scheduler.model.material.Product;
import com.openi40.scheduler.model.material.ProductionSupply;
import com.openi40.scheduler.model.material.PurchaseSupply;
import com.openi40.scheduler.model.material.StockSupply;
import com.openi40.scheduler.model.material.SupplyReservation;
import com.openi40.scheduler.model.material.timeline.AbstractMaterialTimeLine;
import com.openi40.scheduler.model.material.timeline.TimeLineException;
import com.openi40.scheduler.model.material.timeline.WarehouseProductMaterialTimeLine;
import com.openi40.scheduler.model.orders.SalesOrderLine;

import io.swagger.annotations.Api;

/**
 * 
 * This code is part of the OpenI40 open source advanced production scheduler
 * platform suite, have look to its licencing options.
 * Web site: http://openi40.org/  
 * Github: https://github.com/openi40/OpenI40Platform
 * We hope you enjoy implementing new amazing projects with it.
 * @author architectures@openi40.org
 *
 */
@RestController
@RequestMapping("schedulerClient/ApsMaterialAnalisys")
@Api
public class ApsMaterialAnalisysResource extends AbstractBaseResource {

	public ApsMaterialAnalisysResource(IApsDataCacheAggregator apsDataCache, IMapperFactory mapperFactory,
			ILazyContextualBusinessLogicFactoryLoader lazyAutowire) {
		super(apsDataCache, mapperFactory, lazyAutowire);
	}

	@Autowired
	IProductDao productDao;
	@Autowired
	IProductionSupplyDao productionSupplyDao;
	@Autowired
	IPurchaseSupplyDao purchaseSupplyDao;
	@Autowired
	IStockSupplyDao stockSupplyDao;
	@Autowired
	ISalesOrderLineDao salesOrderLineDao;

	@Autowired
	IWarehouseDao warehouseDao;

	@GetMapping(value = "materialPlanning/{dataSourceName}/{dataSetId}/{variantId}/{productCode}/")
	public MaterialPlanningDto getMaterialPlanning(@PathVariable("dataSourceName") String dataSourceName,
			@PathVariable("dataSetId") String dataSetId, @PathVariable("variantId") String variantId,
			@PathVariable("productCode") String productCode)
			throws ApsDataCacheException, DataModelDaoException, MapperException, TimeLineException {
		IApsDataCache datacache = this.apsDataCacheAggregator.getDataCache(dataSourceName);
		
		ApsData apsdata = datacache.getCachedData(dataSetId, variantId);
		MaterialPlanningDto materialPlanningDto = new MaterialPlanningDto();
		materialPlanningDto.setProductCode(productCode);
		Product product = productDao.findByCode(productCode, apsdata);
		List<Warehouse> warehouses = warehouseDao.findAll(apsdata);
		IMapper<AbstractMaterialTimeLine, DailyWarehouseGraph> materialTimeLineMapper = this.mapperFactory.createMapper(AbstractMaterialTimeLine.class, DailyWarehouseGraph.class);
		IMaterialTimeLineManager materialTimeLineManager=this.getComponentFactory().create(IMaterialTimeLineManager.class,apsdata,apsdata);
		
		HashMap environment=new HashMap<>();
		Map<String, MaterialWarehousePlanningDto> mpMap = new HashMap<>();
		for (Warehouse warehouse : warehouses) {
			MaterialWarehousePlanningDto w = new MaterialWarehousePlanningDto();
			w.setWarehouseCode(warehouse.getCode());
			materialPlanningDto.getWarehousesAnalisys().add(w);
			mpMap.put(warehouse.getCode(), w);
			WarehouseProductMaterialTimeLine materialTimeLine = materialTimeLineManager.getMaterialTimeLine(warehouse, product, apsdata);
			w.setDailyGraph(materialTimeLineMapper.mapInstance(materialTimeLine, DailyWarehouseGraph.class, DefaultEntitiesFactory.Instance, environment, true));
		}
		List<AbstractSupply> allSupplies = new ArrayList<>();
		List<ProductionSupply> productionSupplies = productionSupplyDao.findByItem(product, apsdata);
		IMapper<ProductionSupply, ProductionSupplyDto> productionSupplyMapper = this.mapperFactory
				.createMapper(ProductionSupply.class, ProductionSupplyDto.class);
		for (ProductionSupply productionSupply : productionSupplies) {
			ProductionSupplyDto materialProduction = productionSupplyMapper.mapInstance(productionSupply,
					ProductionSupplyDto.class, DefaultEntitiesFactory.Instance, environment, true);
			allSupplies.add(productionSupply);
			if (materialProduction.getWarehouseCode() != null
					&& materialProduction.getWarehouseCode().trim().length() > 0) {
				if (mpMap.containsKey(materialProduction.getWarehouseCode())) {
					mpMap.get(materialProduction.getWarehouseCode()).getProductionSupplies().add(materialProduction);
				}
			} else {
				if (mpMap.size() == 1)
					mpMap.forEach((key, value) -> {
						value.getProductionSupplies().add(materialProduction);
					});
			}
		}
		List<PurchaseSupply> purchaseSupplies = purchaseSupplyDao.findByItem(product, apsdata);
		IMapper<PurchaseSupply, PurchaseSupplyDto> polMapper = mapperFactory.createMapper(PurchaseSupply.class,
				PurchaseSupplyDto.class);
		for (PurchaseSupply purchaseOrderLine : purchaseSupplies) {
			PurchaseSupplyDto purchaseOrderLineDto = polMapper.mapInstance(purchaseOrderLine, PurchaseSupplyDto.class,
					DefaultEntitiesFactory.Instance, environment, true);
			allSupplies.add(purchaseOrderLine);
			if (purchaseOrderLine.getWarehouseCode() != null
					&& purchaseOrderLine.getWarehouseCode().trim().length() > 0) {
				if (mpMap.containsKey(purchaseOrderLine.getWarehouseCode())) {
					mpMap.get(purchaseOrderLine.getWarehouseCode()).getPurchaseSupplies().add(purchaseOrderLineDto);
				}
			} else {
				if (mpMap.size() == 1)
					mpMap.forEach((key, value) -> {
						value.getPurchaseSupplies().add(purchaseOrderLineDto);
					});
			}
		}
		IMapper<StockSupply, StockSupplyDto> sMapper = mapperFactory.createMapper(StockSupply.class,
				StockSupplyDto.class);
		List<StockSupply> stockSupplies = stockSupplyDao.findByItem(product, apsdata);
		for (StockSupply stockSupply : stockSupplies) {
			StockSupplyDto stockSupplyDto = sMapper.mapInstance(stockSupply, StockSupplyDto.class,
					DefaultEntitiesFactory.Instance, environment, true);
			allSupplies.add(stockSupply);
			if (stockSupplyDto.getWarehouseCode() != null && stockSupplyDto.getWarehouseCode().trim().length() > 0) {
				if (mpMap.containsKey(stockSupplyDto.getWarehouseCode())) {
					mpMap.get(stockSupplyDto.getWarehouseCode()).setStockSupply(stockSupplyDto);
				}
			} else {
				if (mpMap.size() == 1)
					mpMap.forEach((key, value) -> {
						value.setStockSupply(stockSupplyDto);
					});
			}
		}
		IMapper<SupplyReservation, SupplyReservationDto> reservationMapper = mapperFactory
				.createMapper(SupplyReservation.class, SupplyReservationDto.class);
		for (AbstractSupply supply : allSupplies) {
			if (supply.getReservations() != null) {
				for (SupplyReservation reservation : supply.getReservations()) {
					SupplyReservationDto rDto = reservationMapper.mapInstance(reservation, SupplyReservationDto.class, DefaultEntitiesFactory.Instance, environment, true);
					if (rDto.getWarehouseCode() != null && rDto.getWarehouseCode().trim().length() > 0) {
						if (mpMap.containsKey(rDto.getWarehouseCode())) {
							mpMap.get(rDto.getWarehouseCode()).getProductionConsumptions().add(rDto);
						}
					} else {
						if (mpMap.size() == 1)
							mpMap.forEach((key, value) -> {
								value.getProductionConsumptions().add(rDto);
							});
					}
				}
			}
		}
		IMapper<SalesOrderLine, SalesOrderLineDto> solMapper = mapperFactory.createMapper(SalesOrderLine.class,
				SalesOrderLineDto.class);
		List<SalesOrderLine> salesOrderLines = salesOrderLineDao.findByItem(product, apsdata);
		for (SalesOrderLine salesOrderLine : salesOrderLines) {
			SalesOrderLineDto salesOrderLineDto = solMapper.mapInstance(salesOrderLine, SalesOrderLineDto.class,
					DefaultEntitiesFactory.Instance, new HashMap<>(), true);
			if (salesOrderLineDto.getWarehouseCode() != null
					&& salesOrderLineDto.getWarehouseCode().trim().length() > 0) {
				if (mpMap.containsKey(salesOrderLineDto.getWarehouseCode())) {
					mpMap.get(salesOrderLineDto.getWarehouseCode()).getSalesOrderLines().add(salesOrderLineDto);
				}
			} else {
				if (mpMap.size() == 1)
					mpMap.forEach((key, value) -> {
						value.getSalesOrderLines().add(salesOrderLineDto);
					});
			}
		}

		materialPlanningDto.analizedWeeks = generateAnalyzedWeeks(apsdata.getSchedulingWindow());
		
		return materialPlanningDto;
	}

	private List<Week> generateAnalyzedWeeks(ApsWindow schedulingWindow) {
		List<Week> outWeeks = new ArrayList<>();
		Date actualDate = schedulingWindow.getStartDateTime();

		while (actualDate.before(schedulingWindow.getEndDateTime())) {
			Week week = DateUtil.getWeek(actualDate);
			outWeeks.add(week);
			GregorianCalendar gc = new GregorianCalendar();
			gc.setTime(actualDate);
			gc.add(GregorianCalendar.WEEK_OF_YEAR, 1);
			actualDate = gc.getTime();
		}

		return outWeeks;
	}

}
