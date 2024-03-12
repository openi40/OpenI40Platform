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
package com.openi40.scheduler.tests;

import static org.junit.Assert.assertTrue;

import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.openi40.scheduler.apsdatacache.ApsDataCacheException;
import com.openi40.scheduler.client.model.aps.ApsDataDto;
import com.openi40.scheduler.common.aps.IApsObject;
import com.openi40.scheduler.engine.aps.ApsLogicNotifiedObjects;
import com.openi40.scheduler.engine.aps.ApsLogics;
import com.openi40.scheduler.engine.aps.IApsLogicComposer;
import com.openi40.scheduler.engine.aps.IApsLogicObserver;
import com.openi40.scheduler.engine.apsdata.IApsDataManager;
import com.openi40.scheduler.engine.contextualplugarch.IContextualBusinessLogicFactory;
import com.openi40.scheduler.engine.rules.IRuleSolutionListener;
import com.openi40.scheduler.engine.rules.IRuleStateListener;
import com.openi40.scheduler.engine.rules.RulePlanningEvent;
import com.openi40.scheduler.engine.rules.RulePlanningEvent.RuleEventType;
import com.openi40.scheduler.engine.standalone.Main;
import com.openi40.scheduler.input.model.companystructure.ProductiveCompanyInputDto;
import com.openi40.scheduler.input.model.orders.SalesOrderInputDto;
import com.openi40.scheduler.mapper.DefaultEntitiesFactory;
import com.openi40.scheduler.mapper.IMapper;
import com.openi40.scheduler.mapper.IMapperFactory;
import com.openi40.scheduler.mapper.MapperException;
import com.openi40.scheduler.model.aps.ApsData;
import com.openi40.scheduler.model.aps.ApsLogicOptions;
import com.openi40.scheduler.model.aps.ApsMessage;
import com.openi40.scheduler.model.aps.ApsMessageLevel;
import com.openi40.scheduler.model.aps.ApsSchedulingSet;
import com.openi40.scheduler.model.companystructure.Plant;
import com.openi40.scheduler.model.companystructure.ProductiveCompany;
import com.openi40.scheduler.model.cycle.BomItemModel;
import com.openi40.scheduler.model.dao.DataModelDaoException;
import com.openi40.scheduler.model.dao.IBomItemModelDao;
import com.openi40.scheduler.model.dao.IProductDao;
import com.openi40.scheduler.model.dao.ISalesOrderDao;
import com.openi40.scheduler.model.dao.IStockSupplyDao;
import com.openi40.scheduler.model.dao.IWorkOrderDao;
import com.openi40.scheduler.model.material.Product;
import com.openi40.scheduler.model.material.StockSupply;
import com.openi40.scheduler.model.orders.SalesOrder;
import com.openi40.scheduler.model.orders.SalesOrderLine;
import com.openi40.scheduler.model.orders.WorkOrder;
import com.openi40.scheduler.model.rules.Rule;
import com.openi40.scheduler.model.tasks.Task;
import com.openi40.scheduler.model.tasks.TaskEdge;
import com.openi40.scheduler.model.tasks.TaskVisitUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = { Main.class })
@ComponentScan("com.openi40.scheduler")
public class SchedulingAlgorithmStructureTest {
	@Autowired
	IContextualBusinessLogicFactory ComponentFactory;
	@Autowired
	AutowireCapableBeanFactory beanFactory;
	static Logger LOGGER = LoggerFactory.getLogger(SchedulingAlgorithmStructureTest.class);
	@Autowired
	TestsApsDataSourceUncachedFactory uncachedAccessor;

	@BeforeClass
	public static void initialize() {

	}

	public static void Cleanup() {

	}

	@Test
	public void testSpearfishingHoldingLoadedFromDiskForward() throws ApsDataCacheException {
		String dataSourceName = "SPEARFISHING-HOLDING";
		String dataSetName = "SPEARFISHING-HOLDING";
		String dataSetVariant = "DEFAULT";
		ApsData apsData = uncachedAccessor.loadData(dataSourceName, dataSetName, dataSetVariant);
		TestScheduling(ApsLogics.FORWARD_APS, apsData);
	}

	@Test
	public void testSpearfishingHoldingLoadedFromDiskBackward() throws ApsDataCacheException {
		String dataSourceName = "SPEARFISHING-HOLDING";
		String dataSetName = "SPEARFISHING-HOLDING";
		String dataSetVariant = "DEFAULT";
		ApsData apsData = uncachedAccessor.loadData(dataSourceName, dataSetName, dataSetVariant);
		TestScheduling(ApsLogics.BACKWARD_APS, apsData);
	}

	@Test
	public void testArbaleteLoadedFromDiskForward() throws ApsDataCacheException {
		String dataSourceName = "ARBALETE-DEMO";
		String dataSetName = "ARBALETE-COMPANY";
		String dataSetVariant = "DEFAULT";

		ApsData apsData = uncachedAccessor.loadData(dataSourceName, dataSetName, dataSetVariant);
		TestScheduling(ApsLogics.FORWARD_APS, apsData);
	}

	@Test
	public void testArbaleteLoadedFromDiskBackward() throws ApsDataCacheException {
		String dataSourceName = "ARBALETE-DEMO";
		String dataSetName = "ARBALETE-COMPANY";
		String dataSetVariant = "DEFAULT";
		ApsData apsData = uncachedAccessor.loadData(dataSourceName, dataSetName, dataSetVariant);
		TestScheduling(ApsLogics.BACKWARD_APS, apsData);
	}

	@Test
	public void testStainlessSteelCompanyLoadedFromDiskBackward() throws ApsDataCacheException {

		String dataSourceName = "SS-COMPANY-DEMO";
		String dataSetName = "STAINLESS-STEEL-COMPANY";
		String dataSetVariant = "DEFAULT";
		ApsData apsData = uncachedAccessor.loadData(dataSourceName, dataSetName, dataSetVariant);
		TestScheduling(ApsLogics.BACKWARD_APS, apsData);
	}

	@Test
	public void testStainlessSteelCompanyLoadedFromDiskForward() throws ApsDataCacheException {

		String dataSourceName = "SS-COMPANY-DEMO";
		String dataSetName = "STAINLESS-STEEL-COMPANY";
		String dataSetVariant = "DEFAULT";
		ApsData apsData = uncachedAccessor.loadData(dataSourceName, dataSetName, dataSetVariant);
		TestScheduling(ApsLogics.FORWARD_APS, apsData);
	}

	@Test
	public void testStainlessSteelCompanyPurchaseSimulationTestLoadedFromDiskForward() throws ApsDataCacheException {

		String dataSourceName = "SS-COMPANY-DEMO-PURCHASE-TEST";
		String dataSetName = "STAINLESS-STEEL-COMPANY";
		String dataSetVariant = "PURCHASE-TEST-DS";
		ApsData apsData = uncachedAccessor.loadData(dataSourceName, dataSetName, dataSetVariant);
		TestScheduling(ApsLogics.FORWARD_APS, apsData);
	}

	@Ignore
	@Test
	public void testStainlessSteelCompanyLoadedFromDiskBackwardUntillUnschedulable() throws ApsDataCacheException {

		String dataSourceName = "SS-COMPANY-DEMO";
		String dataSetName = "STAINLESS-STEEL-COMPANY";
		String dataSetVariant = "DEFAULT";
		ApsData apsData = uncachedAccessor.loadData(dataSourceName, dataSetName, dataSetVariant);

		TestScheduling(ApsLogics.BACKWARD_APS, apsData);
	}

	static final String _jsonOrder = "{\r\n" + "			\"code\": \"SS/${orderCode}\",\r\n"
			+ "			\"plantCode\": \"STB002\",\r\n" + "			\"description\": \"\",\r\n"
			+ "			\"orderType\": \"SALES\",\r\n" + "			\"orderLines\": [\r\n" + "				{\r\n"
			+ "					\"code\": \"SS/${orderCode}/001\",\r\n"
			+ "					\"orderCode\": \"SS/ORD005-2020\",\r\n"
			+ "					\"plantCode\": \"STB002\",\r\n" + "					\"warehouseCode\": \"WH002\",\r\n"
			+ "					\"askedDeliveryDate\": \"${deliveryDate}\",\r\n"
			+ "					\"productCode\": \"TRIGGER001\",\r\n" + "					\"residualQty\": 10,\r\n"
			+ "					\"completedQty\": 0,\r\n" + "					\"totalQty\": 10,\r\n"
			+ "					\"explodeWorkOrders\": true,\r\n" + "					\"color\": \"#9900b3\"\r\n"
			+ "				},\r\n" + "				{\r\n" + "					\"code\": \"SS/${orderCode}/002\",\r\n"
			+ "					\"orderCode\": \"SS/${orderCode}\",\r\n"
			+ "					\"plantCode\": \"STB002\",\r\n" + "					\"warehouseCode\": \"WH002\",\r\n"
			+ "					\"askedDeliveryDate\": \"${deliveryDate}\",\r\n"
			+ "					\"productCode\": \"SHAFT130CM\",\r\n" + "					\"residualQty\": 10,\r\n"
			+ "					\"completedQty\": 0,\r\n" + "					\"totalQty\": 10,\r\n"
			+ "					\"explodeWorkOrders\": true,\r\n" + "					\"color\": \"#C0b399\"\r\n"
			+ "				}\r\n" + "			]\r\n" + "		}";
	@Autowired
	ObjectMapper objectMapper;
	@Autowired
	ISalesOrderDao salesOrderDao;

	@Ignore
	@Test
	public void testStainlessSteelCompanyLoadedFromDiskForwardUntillUnschedulable() throws ApsDataCacheException,
			JsonParseException, JsonMappingException, IOException, MapperException, DataModelDaoException {

		String dataSourceName = "SS-COMPANY-DEMO";
		String dataSetName = "STAINLESS-STEEL-COMPANY";
		String dataSetVariant = "DEFAULT";
		ApsData apsData = uncachedAccessor.loadData(dataSourceName, dataSetName, dataSetVariant);
		List<SalesOrderInputDto> additionalSalesOrders = new ArrayList<>();
		IMapper<SalesOrderInputDto, SalesOrder> orderMapper = mapperFactory.createMapper(SalesOrderInputDto.class,
				SalesOrder.class);
		int nOrderNumberer = 6;

		java.sql.Date actualDeliveryDate = new java.sql.Date(
				apsData.getSchedulingWindow().getStartDateTime().getTime());
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(actualDeliveryDate);
		gc.add(GregorianCalendar.DAY_OF_YEAR, 12);
		HashMap environment = new HashMap<>();
		environment.put("context", apsData);
		actualDeliveryDate = gc.getTime().after(apsData.getSchedulingWindow().getEndDateTime()) ? actualDeliveryDate
				: new java.sql.Date(gc.getTimeInMillis());
		for (int i = 0;; i++) {
			TestScheduling(ApsLogics.FORWARD_APS, apsData);

			objectMapper.writeValue(new FileOutputStream("AdditionalOrders.json"), additionalSalesOrders);
			String _thisOrder = _jsonOrder.replace("${orderCode}", "SORD" + nOrderNumberer).replace("${deliveryDate}",
					actualDeliveryDate.toString());
			SalesOrderInputDto salesOrder = objectMapper.readValue(_thisOrder.getBytes(), SalesOrderInputDto.class);
			additionalSalesOrders.add(salesOrder);
			SalesOrder newOrder = orderMapper.mapInstance(salesOrder, SalesOrder.class, DefaultEntitiesFactory.Instance,
					environment, true);
			salesOrderDao.insert(newOrder, apsData);
			nOrderNumberer++;
			System.out.println("Additional orders:" + i);
			gc = new GregorianCalendar();
			gc.setTime(actualDeliveryDate);
			gc.add(GregorianCalendar.DAY_OF_YEAR, i % 2 == 0 ? 2 : 1);
			actualDeliveryDate = gc.getTime().after(apsData.getSchedulingWindow().getEndDateTime()) ? actualDeliveryDate
					: new java.sql.Date(gc.getTimeInMillis());

		}

	}

//	@Test
//	public final void TestMultipleDependingTaskWithPrimaryResourceProcessMultipleOrdersBackwardSchedulerHeavy() {
//		TestScheduling(ApsLogics.BACKWARD_APS,
//				testSchedulingContextGenerator.GenerateTaskHierarchySchedulingContext(3, 2, 2, 10, 1, getOperators()));
//	}

	@Autowired
	IWorkOrderDao workOrderDao;

	public void TestScheduling(ApsData context) {

		IApsLogicComposer engine = ComponentFactory.create(IApsLogicComposer.class, context, context);
		// System.out.println("task-id;work-order;machine;machine-id;start-setup;end-setup;start-work;end-work");
		IApsLogicObserver observer = new IApsLogicObserver() {
			@Override
			public void processedElement(IApsObject object) {
				if (object != null && object instanceof Task) {
					Task task = (Task) object;
					if (task.getCode()
							.equals("ORD001-2020/001-CAMOSPEARGUNBARREL95-1-BARRELL95-2-WAFER130x6x5-1-001")) {
						System.out.println(
								"ORD001-2020/001-CAMOSPEARGUNBARREL95-1-BARRELL95-2-WAFER130x6x5-1-001 reached!");
					}
				}

			}

			@Override
			public void startProcessingElement(IApsObject object) {

				if (object != null && object instanceof Task) {
					Task task = (Task) object;
					if (task.getCode()
							.equals("ORD001-2020/001-CAMOSPEARGUNBARREL95-1-BARRELL95-2-WAFER130x6x5-1-001")) {
						System.out.println(
								"ORD001-2020/001-CAMOSPEARGUNBARREL95-1-BARRELL95-2-WAFER130x6x5-1-001 reached!");
					}
				}

			}
		};
		ApsLogicNotifiedObjects listeners = new ApsLogicNotifiedObjects();
		listeners.setObserver(observer);
		listeners.setConstraintRuleListener(new IRuleStateListener() {

			@Override
			public void verifiedState(Rule rule, Task task, boolean constraintMeet) {
				if (!constraintMeet) {
					// LOGGER.warn("Cannot solve " + rule + " regarding: " +
					// task.getWorkOrderCode()+"/"+task.getSequenceCode());
					// throw new EngineTestingError("Cannot solve " + rule + " regarding: " +
					// task.getWorkOrderCode()+"/"+task.getSequenceCode());
					/*
					 * if (rule instanceof MaterialRule) { MaterialRule mcr = (MaterialRule) rule;
					 * LOGGER.error("Constraint rule for supply of meterial:" +
					 * mcr.getConsumer().getRequiredItem().getCode() + " and task=>" + task.getId()
					 * + " seqCode:" + task.getSequenceCode() + " workOrder:" +
					 * task.getWorkOrderCode()); } else LOGGER.error("Constraint not met=>" + rule +
					 * " on task=>" + task);
					 */
				}
				// System.exit(-1);
			}
		});
		IRuleSolutionListener solutionListener = new IRuleSolutionListener() {

			@Override
			public void onConstraintSolutionEvent(RulePlanningEvent event) {
				if (event.getEventType() == RuleEventType.CONSTRAINT_UNSOLVABLE) {
					// throw new EngineTestingError("Cannot solve " + event.getRule() + " regarding:
					// " + event.getContextData());
					// LOGGER.warn("Cannot solve " + event.getRule() + " regarding: " +
					// event.getContextData());
				}

			}
		};
		listeners.setConstraintSolutionListener(solutionListener);
		engine.schedule(context, listeners);
		List<Task> allTasks = TaskVisitUtil.Instance.getProcessedTasksList(context);
		int countTasks = allTasks.size();
		Map<String, Boolean> duplicatesGuard = new HashMap<>();
		for (ApsSchedulingSet action : context.getSchedulingSets()) {
			for (WorkOrder wo : action.getWorkOrders()) {
				Task task = wo.getRootTask();
				List<ApsMessage> messages = task.getMessages();
				for (ApsMessage message : messages) {
					// LOGGER.info(message.getMessageDescription());
				}
				DoAssertionsOnScheduledTaskAndChilds(task, 0, "ROOT", duplicatesGuard);
			}
		}
		if (LOGGER.isDebugEnabled()) {

			try {
				IMapper<ApsData, ApsDataDto> mapper = mapperFactory.createMapper(ApsData.class, ApsDataDto.class);
				ApsDataDto apsDataDto;
				apsDataDto = mapper.mapInstance(context, ApsDataDto.class, DefaultEntitiesFactory.Instance,
						new HashMap(), true);

				ObjectMapper jsonMapper = new ObjectMapper();
				if (LOGGER.isDebugEnabled())
					LOGGER.debug(jsonMapper.writerWithDefaultPrettyPrinter().writeValueAsString(apsDataDto));
			} catch (MapperException | JsonProcessingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		context = null;

	}

	public void TestScheduling(String schedulingType, ApsData context) {

		// I create a producedQty pz order with nOrders orders

		ApsSchedulingSet action = new ApsSchedulingSet(context);
		List<WorkOrder> workorders = null;
		try {
			workorders = workOrderDao.findAll(context);
		} catch (DataModelDaoException ex) {
			throw new IllegalStateException("Work order dao is not working", ex);
		}
		for (WorkOrder wo : workorders) {
			action.addWorkOrder(wo);
		}
		action.setOptions(new ApsLogicOptions());
		int countTasks = 0;
		int nOrders = workorders.size();

		if (LOGGER.isDebugEnabled())
			LOGGER.debug("[XXXX]=>RUNNUNG TEST WITH nTasks=>" + countTasks + " nOrders=" + nOrders + " ", "");
		// Schedule with loader
		if (schedulingType != null) {
			action.setAlgorithmType(schedulingType);
		} else {
			action.setAlgorithmType(ApsLogics.FORWARD_APS);
		}
		context.getSchedulingSets().add(action);
		TestScheduling(context);
		// context.resetSchedulingData();
		context = null;
	}

	@Autowired
	IMapperFactory mapperFactory;

	private void DoAssertionsOnScheduledTaskAndChilds(Task task, int level, String logPreamble,
			Map<String, Boolean> avoidDuplicates) {
		if (avoidDuplicates.containsKey(task.getId()))
			return;
		for (ApsMessage msg : task.getMessages()) {

			LOGGER.warn(msg.getMessageDescription());

		}
		double duration = task.getWorkPhaseExecution().getDurationMinutes();
		double setup = task.getSetupPhaseExecution().getDurationMinutes();
		double overallDuration = task.getMainTimeRange().getDurationMinutes();
		if (!task.isSuccessfullyScheduled()) {
			LOGGER.info("Not scheduled");
		}
		assertTrue(task.getCode() + " is scheduled", task.isSuccessfullyScheduled());
		assertTrue(task.getCode() + " has scheduled subtree", task.isSubtreeSuccessfullyScheduled());
		assert task.getStartDateTime() != null;
		assert task.getStartDateTime() != null;
		assert task.getEndDateTime() != null;
		assert task.getEndDateTime() != null;
		// TODO: ADD LENGTH DURATION CHECK
		// Assert.assertEquals("Work duration must be " + task.getQtyTotal() * 10,
		// duration, task.getQtyTotal() * 10, 0.0);
		// Assert.assertEquals("Setup duration must be 10 minutes", setup, 10.0, 0);

		/*
		 * Debug.WriteLine("Task: " + task.getId() + " scheduled=>" +
		 * task.getIsSuccessfullyScheduled() + " start=" + task.getStartDateTime() +
		 * " end=" + task.getEndDateTime() + " duration=" + duration + " setup=" + setup
		 * + " overallDuration=" + overallDuration);
		 */
		/*
		 * System.out.println("LEVEL[" + level + "] MACHINE=> " +
		 * task.getEquipment().getSetupEquipment().getResource().getChoosenEquipment().
		 * getCode() + "=ID=>" +
		 * task.getEquipment().getSetupEquipment().getResource().getId() + "=> Task:" +
		 * task.getId() + " scheduled=>" + task.isSuccessfullyScheduled() +
		 * " SetupPeriod=> " + task.getSetupPhaseExecution() + " WorkPeriod=> " +
		 * task.getWorkPhaseExecution());
		 */
		avoidDuplicates.put(task.getId(), true);
		for (TaskEdge childTask : task.getChildTasks()) {
			DoAssertionsOnScheduledTaskAndChilds(childTask.getProducerTask(), level + 1,
					logPreamble + "=>" + task.getId(), avoidDuplicates);
			Date startTask = task.getWorkPhaseExecution().getStartDateTime();
			Date endProducerTask = childTask.getProducerTask().getWorkPhaseExecution().getEndDateTime();
			switch (childTask.getAlignmentType()) {
			case START_AFTER_END: {
				assert startTask.compareTo(endProducerTask) >= 0;

			}
				break;
			case START_AFTER_START: {

				assert task.getWorkPhaseExecution().getStartDateTime()
						.compareTo(childTask.getProducerTask().getWorkPhaseExecution().getStartDateTime()) >= 0;
			}
				break;
			}
		}

	}

	@Autowired
	IBomItemModelDao bomItemDao;
	@Autowired
	IStockSupplyDao stockSupplyDao;
	@Autowired
	IProductDao productDao;

	@Test
	public void testStainlessSteelCompanyInfiniteCapacityStock() throws ApsDataCacheException, DataModelDaoException {
		String dataSourceName = "SS-COMPANY-DEMO";
		String dataSetName = "STAINLESS-STEEL-COMPANY";
		String dataSetVariant = "DEFAULT";
		ApsData apsData = uncachedAccessor.loadData(dataSourceName, dataSetName, dataSetVariant);
		String finalOrderProductCode = "AISI316SQUARE50x50x3mm";
		Product product = productDao.findByCode(finalOrderProductCode, apsData); // Reset all orders
		for (ProductiveCompany pc : apsData.getProductiveCompanies()) {
			for (Plant plant : pc.getPlants()) {
				// Resetting all orders
				plant.getSalesOrders().clear();
				plant.getPurchaseOrders().clear();
				plant.getWorkOrders().clear();
				SalesOrder salesOrder = new SalesOrder(apsData);
				salesOrder.setCode("ORDER0001");
				salesOrder.setDescription("Infinite capacity test order");
				salesOrder.setCompanyCode(pc.getCode());
				salesOrder.setPlantCode(plant.getCode());
				salesOrder.setDepartmentCode(plant.getDepartments().get(0).getCode());
				// Adding a huge qty order
				SalesOrderLine sline = new SalesOrderLine(apsData);
				sline.setCode("LINE0001");
				sline.setCompanyCode(pc.getCode());
				sline.setPlantCode(plant.getCode());
				sline.setWarehouseCode("WH002");
				sline.setPlannedDeliveryDate(Timestamp.valueOf("2021-06-01 23:59:59"));
				sline.setProduct(product);
				sline.setTotalQty(100.0);
				sline.setExplodeWorkOrders(true);
				salesOrder.getOrderLines().add(sline);
				plant.getSalesOrders().add(salesOrder);
			}
		}
		// I Modify the required qty for this simple cycle
		String bomCode = "OP-CYCLE-AISI316SQUARE50x50x3mm-001-AISI316SHEET600x600x3mm";
		BomItemModel bomItem = bomItemDao.findByCode(bomCode, apsData);
		bomItem.setQty(1000.0);
		// Set to 0 quantity a the inventory and to infiniteCapacity on a required
		// material
		String stockSupplyId = "SSINVENTORY001";
		StockSupply stockSupply = stockSupplyDao.findByCode(stockSupplyId, apsData);
		stockSupply.setQtyTotal(0);
		stockSupply.setInfiniteCapacity(true);
		// Reinitialize data and recreate work orders ecc...
		IApsDataManager dataManager = this.ComponentFactory.create(IApsDataManager.class, apsData, apsData);
		dataManager.beforeScheduling(apsData);
		TestScheduling(ApsLogics.FORWARD_APS, apsData);
	}

	@Test
	public void testStainlessSteelCompanyWithMinMaxProductionBounds()
			throws ApsDataCacheException, DataModelDaoException {
		String dataSourceName = "SS-COMPANY-DEMO";
		String dataSetName = "STAINLESS-STEEL-COMPANY";
		String dataSetVariant = "DEFAULT";
		ApsData apsData = uncachedAccessor.loadData(dataSourceName, dataSetName, dataSetVariant);
		String finalOrderProductCode = "AISI316SQUARE50x50x3mm";
		Product product = productDao.findByCode(finalOrderProductCode, apsData); // Reset all orders
		String complexProductCode = "TRIGGER001";
		Product complexProduct = productDao.findByCode(complexProductCode, apsData);
		for (ProductiveCompany pc : apsData.getProductiveCompanies()) {
			for (Plant plant : pc.getPlants()) {
				// Resetting all orders
				plant.getSalesOrders().clear();
				plant.getPurchaseOrders().clear();
				plant.getWorkOrders().clear();
				SalesOrder salesOrder = new SalesOrder(apsData);
				salesOrder.setCode("ORDER0001");
				salesOrder.setDescription("Infinite capacity test order");
				salesOrder.setCompanyCode(pc.getCode());
				salesOrder.setPlantCode(plant.getCode());
				salesOrder.setDepartmentCode(plant.getDepartments().get(0).getCode());
				// Adding an order line with minimum and maximum assigned production bounds
				// dates
				SalesOrderLine sline = new SalesOrderLine(apsData);
				sline.setCode("LINE0001");
				sline.setCompanyCode(pc.getCode());
				sline.setPlantCode(plant.getCode());
				sline.setWarehouseCode("WH002");
				sline.setPlannedDeliveryDate(Timestamp.valueOf("2021-06-01 23:59:59"));
				sline.setMinProductionDateConstraint(Timestamp.valueOf("2021-01-12 08:59:59"));
				sline.setMaxProductionDateConstraint(Timestamp.valueOf("2021-02-08 23:59:59"));
				sline.setProduct(product);
				sline.setTotalQty(1.0);
				sline.setExplodeWorkOrders(true);
				salesOrder.getOrderLines().add(sline);
				sline = new SalesOrderLine(apsData);
				sline.setCode("LINE0002");
				sline.setCompanyCode(pc.getCode());
				sline.setPlantCode(plant.getCode());
				sline.setWarehouseCode("WH002");
				sline.setPlannedDeliveryDate(Timestamp.valueOf("2021-06-01 23:59:59"));
				sline.setMinProductionDateConstraint(Timestamp.valueOf("2021-04-09 08:59:59"));
				sline.setMaxProductionDateConstraint(Timestamp.valueOf("2021-04-18 23:59:59"));
				sline.setProduct(product);
				sline.setTotalQty(1.0);
				sline.setExplodeWorkOrders(true);
				salesOrder.getOrderLines().add(sline);
				sline = new SalesOrderLine(apsData);
				sline.setCode("LINE0003");
				sline.setCompanyCode(pc.getCode());
				sline.setPlantCode(plant.getCode());
				sline.setWarehouseCode("WH002");
				sline.setPlannedDeliveryDate(Timestamp.valueOf("2021-06-01 23:59:59"));
				sline.setMinProductionDateConstraint(Timestamp.valueOf("2021-04-09 08:59:59"));
				sline.setMaxProductionDateConstraint(Timestamp.valueOf("2021-04-30 23:59:59"));
				sline.setProduct(complexProduct);
				sline.setTotalQty(1.0);
				sline.setExplodeWorkOrders(true);
				salesOrder.getOrderLines().add(sline);
				plant.getSalesOrders().add(salesOrder);
			}
		}
		// I Modify the required qty for this simple cycle
		String bomCode = "OP-CYCLE-AISI316SQUARE50x50x3mm-001-AISI316SHEET600x600x3mm";
		BomItemModel bomItem = bomItemDao.findByCode(bomCode, apsData);
		bomItem.setQty(1000.0);
		// Set to 0 quantity a the inventory and to infiniteCapacity on a required
		// material
		String stockSupplyId = "SSINVENTORY001";
		StockSupply stockSupply = stockSupplyDao.findByCode(stockSupplyId, apsData);
		stockSupply.setQtyTotal(10000);
		// Reinitialize data and recreate work orders ecc...
		IApsDataManager dataManager = this.ComponentFactory.create(IApsDataManager.class, apsData, apsData);
		dataManager.beforeScheduling(apsData);
		TestScheduling(ApsLogics.FORWARD_APS, apsData);
	}
}