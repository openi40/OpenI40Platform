package com.openi40.scheduler.tests;

import java.sql.Timestamp;
import java.util.Iterator;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.openi40.scheduler.apsdatacache.ApsDataCacheException;
import com.openi40.scheduler.engine.aps.ApsLogics;
import com.openi40.scheduler.engine.aps.IApsLogicComposer;
import com.openi40.scheduler.engine.aps.IApsMessagesBroker;
import com.openi40.scheduler.engine.aps.IApsMessagesBroker.ApsMessageResponse;
import com.openi40.scheduler.engine.contextualplugarch.IContextualBusinessLogicFactory;
import com.openi40.scheduler.engine.messages.handling.MessageHandlingErrorMessage;
import com.openi40.scheduler.engine.standalone.Main;
import com.openi40.scheduler.model.ReservableObjectAvailability;
import com.openi40.scheduler.model.aps.ApsData;
import com.openi40.scheduler.model.aps.ApsLogicOptions;
import com.openi40.scheduler.model.aps.ApsSchedulingSet;
import com.openi40.scheduler.model.dao.DataModelDaoException;
import com.openi40.scheduler.model.dao.IMachineDao;
import com.openi40.scheduler.model.dao.ITaskDao;
import com.openi40.scheduler.model.dao.IWorkOrderDao;
import com.openi40.scheduler.model.equipment.Machine;
import com.openi40.scheduler.model.messages.EndSetupMessage;
import com.openi40.scheduler.model.messages.EndWorkMessage;
import com.openi40.scheduler.model.messages.StartSetupMessage;
import com.openi40.scheduler.model.messages.StartWorkMessage;
import com.openi40.scheduler.model.messages.TaskProductionUpdateMessage;
import com.openi40.scheduler.model.orders.WorkOrder;
import com.openi40.scheduler.model.tasks.Task;
import com.openi40.scheduler.model.tasks.TaskStatus;

import junit.framework.Assert;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = { Main.class })
@ComponentScan("com.openi40.scheduler")
public class IOMessagesTests {
	static Logger LOGGER = LoggerFactory.getLogger(IOMessagesTests.class);
	@Autowired
	IContextualBusinessLogicFactory ComponentFactory;
	@Autowired
	AutowireCapableBeanFactory beanFactory;
	@Autowired
	TestsApsDataSourceUncachedFactory uncachedAccessor;
	@Autowired
	IWorkOrderDao workOrderDao;
	@Autowired
	ITaskDao taskDao;
	@Autowired
	IMachineDao machineDao;
	static final String dataSourceName = "SS-COMPANY-DEMO";
	static final String dataSetName = "STAINLESS-STEEL-COMPANY";
	static final String dataSetVariant = "DEFAULT";

	public void setScheduleAllWorkOrders(ApsData apsData, String algorithm) {
		ApsSchedulingSet action = new ApsSchedulingSet(apsData);
		List<WorkOrder> workorders = null;
		try {
			workorders = workOrderDao.findAll(apsData);
		} catch (DataModelDaoException ex) {
			throw new IllegalStateException("Work order dao is not working", ex);
		}
		for (WorkOrder wo : workorders) {
			action.addWorkOrder(wo);
		}
		action.setOptions(new ApsLogicOptions());
		action.setAlgorithmType(algorithm);
		apsData.getSchedulingSets().add(action);
	}

	private void checkApsMessageResponse(ApsMessageResponse response) {
		List<MessageHandlingErrorMessage> errs = response.getErrors();
		for (MessageHandlingErrorMessage m : errs) {
			LOGGER.error("Error code:" + m.getErrorCode() + " description:" + m.getErrorMsg());

		}
		Assert.assertTrue("Message correctly handled=>false", response.isMessageHandled());
	}

	private void checkMachineState(String machineCode, ApsData data, ReservableObjectAvailability state)
			throws DataModelDaoException {
		Machine machine = machineDao.findByCode(machineCode, data);
		Assert.assertEquals(
				"Required machine state=>" + state.name() + " but is => "
						+ (machine.getAvailability() != null ? machine.getAvailability().name() : "null"),
				machine.getAvailability(), state);
	}

	private void checkTaskState(String taskCode, ApsData data, TaskStatus state) throws DataModelDaoException {
		Task task = taskDao.findByCode(taskCode, data);
		Assert.assertEquals("Required task state=>" + state.name() + " but is => "
				+ (task.getStatus() != null ? task.getStatus().name() : "null"), task.getStatus(), state);
	}

	private void checkChoosedMachinCoherency(String taskCode, String machineCode, ApsData data)
			throws DataModelDaoException {
		Task task = taskDao.findByCode(taskCode, data);
		Assert.assertNotNull("Scheduled task machine assignment data structures not allocated", task.getEquipment());
		Assert.assertNotNull("Scheduled task machine assignment data structures not allocated",
				task.getEquipment().getExecution());
		Assert.assertNotNull("Scheduled task machine assignment data structures not allocated",
				task.getEquipment().getExecution().getResource());
		Assert.assertNotNull("Scheduled task machine assignment data structures not allocated",
				task.getEquipment().getExecution().getResource().getChoosenEquipment());
		Assert.assertNotNull("Scheduled task machine assignment data structures not allocated", task.getEquipment());
		Assert.assertNotNull("Scheduled task machine assignment data structures not allocated",
				task.getEquipment().getPreparation());
		Assert.assertNotNull("Scheduled task machine assignment data structures not allocated",
				task.getEquipment().getPreparation().getResource());
		Assert.assertNotNull("Scheduled task machine assignment data structures not allocated",
				task.getEquipment().getPreparation().getResource().getChoosenEquipment());

		Assert.assertEquals(
				"Expected setup machine=>" + machineCode + " but found=> "
						+ task.getEquipment().getPreparation().getResource().getChoosenEquipment().getCode(),
				task.getEquipment().getPreparation().getResource().getChoosenEquipment().getCode(), machineCode);
		Assert.assertEquals(
				"Expected working machine=>" + machineCode + " but found=> "
						+ task.getEquipment().getExecution().getResource().getChoosenEquipment().getCode(),
				task.getEquipment().getExecution().getResource().getChoosenEquipment().getCode(), machineCode);

	}

	@Test
	public void correctMessagesFlowTest() throws ApsDataCacheException, DataModelDaoException {

		ApsData apsData = uncachedAccessor.loadData(dataSourceName, dataSetName, dataSetVariant);
		apsData.setProductionControlEnabled(true);
		;
		setScheduleAllWorkOrders(apsData, ApsLogics.FORWARD_APS);
		IApsLogicComposer composer = ComponentFactory.create(IApsLogicComposer.class, apsData, apsData);
		composer.schedule(apsData, null);
		long timestamp = System.currentTimeMillis();
		String machineCode = "SS-LASERCUTMACHINE-02";
		String taskCode = "SS/ORD003-2020/001-AISI316SQUARE50x50x3mm-1-001";
		// Sending startSetupMessage and verifiyng status coherences
		IApsMessagesBroker broker = ComponentFactory.create(IApsMessagesBroker.class, apsData, apsData);
		StartSetupMessage startSetupMessage = new StartSetupMessage(apsData);
		startSetupMessage.setMessageTimestamp(Timestamp.valueOf("2021-01-09 08:59:59"));
		startSetupMessage.setTaskCode(taskCode);
		startSetupMessage.setMachineCode(machineCode);
		ApsMessageResponse response = broker.handleMessage(startSetupMessage, apsData, null);
		checkApsMessageResponse(response);
		checkMachineState(machineCode, apsData, ReservableObjectAvailability.EXECUTING_SETUP);
		checkTaskState(taskCode, apsData, TaskStatus.EXECUTING_SETUP);
		checkChoosedMachinCoherency(taskCode, machineCode, apsData);

		EndSetupMessage endSetupMessage = new EndSetupMessage(apsData);
		endSetupMessage.setMessageTimestamp(Timestamp.valueOf("2021-01-09 09:05:59"));
		endSetupMessage.setTaskCode(taskCode);
		endSetupMessage.setMachineCode(machineCode);
		response = broker.handleMessage(endSetupMessage, apsData, null);
		checkApsMessageResponse(response);
		checkMachineState(machineCode, apsData, ReservableObjectAvailability.SETUP_DONE);
		checkTaskState(taskCode, apsData, TaskStatus.SETUP_DONE);
		checkChoosedMachinCoherency(taskCode, machineCode, apsData);

		StartWorkMessage startWorkMessage = new StartWorkMessage(apsData);
		startWorkMessage.setMessageTimestamp(Timestamp.valueOf("2021-01-09 09:06:59"));
		startWorkMessage.setTaskCode(taskCode);
		startWorkMessage.setMachineCode(machineCode);
		response = broker.handleMessage(startWorkMessage, apsData, null);
		checkApsMessageResponse(response);
		checkMachineState(machineCode, apsData, ReservableObjectAvailability.EXECUTING_WORK);
		checkTaskState(taskCode, apsData, TaskStatus.EXECUTING_WORK);
		checkChoosedMachinCoherency(taskCode, machineCode, apsData);
		for (int i = 1; i <= 9; i++) {
			TaskProductionUpdateMessage produpdate = new TaskProductionUpdateMessage(apsData);
			produpdate.setMessageTimestamp(Timestamp.valueOf("2021-01-09 09:1" + i + ":59"));
			produpdate.setTaskCode(taskCode);
			produpdate.setMachineCode(machineCode);
			response = broker.handleMessage(produpdate, apsData, null);
			checkApsMessageResponse(response);
			checkMachineState(machineCode, apsData, ReservableObjectAvailability.EXECUTING_WORK);
			checkTaskState(taskCode, apsData, TaskStatus.EXECUTING_WORK);
			checkChoosedMachinCoherency(taskCode, machineCode, apsData);
		}
		EndWorkMessage endWorkMessage = new EndWorkMessage(apsData);
		endWorkMessage.setMessageTimestamp(Timestamp.valueOf("2021-01-09 09:10:59"));
		endWorkMessage.setTaskCode(taskCode);
		endWorkMessage.setMachineCode(machineCode);
		response = broker.handleMessage(endWorkMessage, apsData, null);
		checkApsMessageResponse(response);
		checkMachineState(machineCode, apsData, ReservableObjectAvailability.AVAILABLE);
		checkTaskState(taskCode, apsData, TaskStatus.EXECUTED);
		// checkChoosedMachinCoherency(taskCode, machineCode,apsData);

		LOGGER.info("Executed in " + (System.currentTimeMillis() - timestamp) + " ms");

	}
}
