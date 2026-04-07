package com.openi40.scheduler.tests;

import java.sql.Timestamp;
import java.util.Iterator;
import java.util.List;

import org.junit.jupiter.api.Test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;


import com.openi40.scheduler.apsdatacache.ApsDataCacheException;
import com.openi40.scheduler.engine.aps.ApsLogics;
import com.openi40.scheduler.engine.aps.IApsLogicComposer;
import com.openi40.scheduler.engine.aps.IApsMessagesBroker;
import com.openi40.scheduler.engine.aps.IApsMessagesBroker.ApsMessageResponse;
import com.openi40.scheduler.engine.contextualplugarch.IContextualBusinessLogicFactory;
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
import com.openi40.scheduler.model.messages.handling.MessageHandlingErrorMessage;
import com.openi40.scheduler.model.orders.WorkOrder;
import com.openi40.scheduler.model.tasks.Task;
import com.openi40.scheduler.model.tasks.TaskStatus;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

//
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
		assertTrue(response.isMessageHandled(), "Message correctly handled=>false");
	}

	private void checkMachineState(String machineCode, ApsData data, ReservableObjectAvailability state)
			throws DataModelDaoException {
		Machine machine = machineDao.findByCode(machineCode, data);
		assertEquals(state, machine.getAvailability(),
				"Required machine state=>" + state.name() + " but is => "
						+ (machine.getAvailability() != null ? machine.getAvailability().name() : "null"));
	}

	private void checkTaskState(String taskCode, ApsData data, TaskStatus state) throws DataModelDaoException {
		Task task = taskDao.findByCode(taskCode, data);
		assertEquals(state, task.getStatus(), "Required task state=>" + state.name() + " but is => "
				+ (task.getStatus() != null ? task.getStatus().name() : "null"));
	}

	private void checkChoosedMachinCoherency(String taskCode, String machineCode, ApsData data)
			throws DataModelDaoException {
		Task task = taskDao.findByCode(taskCode, data);
		assertNotNull(task.getEquipment(), "Scheduled task machine assignment data structures not allocated");
		assertNotNull(task.getEquipment().getExecution(), "Scheduled task machine assignment data structures not allocated");
		assertNotNull(task.getEquipment().getExecution().getResource(), "Scheduled task machine assignment data structures not allocated");
		assertNotNull(task.getEquipment().getExecution().getResource().getChoosenEquipment(), "Scheduled task machine assignment data structures not allocated");
		assertNotNull(task.getEquipment(), "Scheduled task machine assignment data structures not allocated");
		assertNotNull(task.getEquipment().getPreparation(), "Scheduled task machine assignment data structures not allocated");
		assertNotNull(task.getEquipment().getPreparation().getResource(), "Scheduled task machine assignment data structures not allocated");
		assertNotNull(task.getEquipment().getPreparation().getResource().getChoosenEquipment(), "Scheduled task machine assignment data structures not allocated");

		assertEquals(machineCode, task.getEquipment().getPreparation().getResource().getChoosenEquipment().getCode(),
				"Expected setup machine=>" + machineCode + " but found=> "
						+ task.getEquipment().getPreparation().getResource().getChoosenEquipment().getCode());
		assertEquals(machineCode, task.getEquipment().getExecution().getResource().getChoosenEquipment().getCode(),
				"Expected working machine=>" + machineCode + " but found=> "
						+ task.getEquipment().getExecution().getResource().getChoosenEquipment().getCode());

	}

	@Test
	public void correctMessagesFlowTest() throws ApsDataCacheException, DataModelDaoException {

		ApsData apsData = uncachedAccessor.loadData(dataSourceName, dataSetName, dataSetVariant);
		apsData.setProductionControlEnabled(true);		
		setScheduleAllWorkOrders(apsData, ApsLogics.FORWARD_APS);
		IApsLogicComposer composer = ComponentFactory.create(IApsLogicComposer.class, apsData, apsData);
		composer.schedule(apsData, null);
		long timestamp = System.currentTimeMillis();
		String machineCode = "SS-LASERCUTMACHINE-02";
		String taskCode = "SS/ORD003-2020/001-AISI316SQUARE50x50x3mm-1-001";
		// Sending startSetupMessage and verifiyng status coherences
		IApsMessagesBroker broker = ComponentFactory.create(IApsMessagesBroker.class, apsData, apsData);
		StartSetupMessage startSetupMessage = new StartSetupMessage(apsData);
		startSetupMessage.setMessageTimestamp(Timestamp.valueOf("2021-01-11 08:59:59"));
		apsData.setActualDateTime(Timestamp.valueOf("2021-01-11 08:59:59"));
		startSetupMessage.setTaskCode(taskCode);
		startSetupMessage.setMachineCode(machineCode);
		ApsMessageResponse response = broker.handleMessage(startSetupMessage, apsData, null);
		checkApsMessageResponse(response);
		checkMachineState(machineCode, apsData, ReservableObjectAvailability.EXECUTING_SETUP);
		checkTaskState(taskCode, apsData, TaskStatus.EXECUTING_SETUP);
		checkChoosedMachinCoherency(taskCode, machineCode, apsData);

		EndSetupMessage endSetupMessage = new EndSetupMessage(apsData);
		endSetupMessage.setMessageTimestamp(Timestamp.valueOf("2021-01-11 09:05:59"));
		apsData.setActualDateTime(endSetupMessage.getMessageTimestamp());
		endSetupMessage.setTaskCode(taskCode);
		endSetupMessage.setMachineCode(machineCode);
		response = broker.handleMessage(endSetupMessage, apsData, null);
		checkApsMessageResponse(response);
		checkMachineState(machineCode, apsData, ReservableObjectAvailability.SETUP_DONE);
		checkTaskState(taskCode, apsData, TaskStatus.SETUP_DONE);
		checkChoosedMachinCoherency(taskCode, machineCode, apsData);

		StartWorkMessage startWorkMessage = new StartWorkMessage(apsData);
		startWorkMessage.setMessageTimestamp(Timestamp.valueOf("2021-01-11 09:06:59"));
		startWorkMessage.setTaskCode(taskCode);
		startWorkMessage.setMachineCode(machineCode);
		apsData.setActualDateTime(startWorkMessage.getMessageTimestamp());
		response = broker.handleMessage(startWorkMessage, apsData, null);
		checkApsMessageResponse(response);
		checkMachineState(machineCode, apsData, ReservableObjectAvailability.EXECUTING_WORK);
		checkTaskState(taskCode, apsData, TaskStatus.EXECUTING_WORK);
		checkChoosedMachinCoherency(taskCode, machineCode, apsData);
		for (int i = 1; i <= 9; i++) {
			TaskProductionUpdateMessage produpdate = new TaskProductionUpdateMessage(apsData);
			produpdate.setMessageTimestamp(Timestamp.valueOf("2021-01-11 09:1" + i + ":59"));
			produpdate.setTaskCode(taskCode);
			produpdate.setMachineCode(machineCode);
			apsData.setActualDateTime(produpdate.getMessageTimestamp());
			response = broker.handleMessage(produpdate, apsData, null);
			checkApsMessageResponse(response);
			checkMachineState(machineCode, apsData, ReservableObjectAvailability.EXECUTING_WORK);
			checkTaskState(taskCode, apsData, TaskStatus.EXECUTING_WORK);
			checkChoosedMachinCoherency(taskCode, machineCode, apsData);
		}
		EndWorkMessage endWorkMessage = new EndWorkMessage(apsData);
		endWorkMessage.setMessageTimestamp(Timestamp.valueOf("2021-01-11 09:10:59"));
		endWorkMessage.setTaskCode(taskCode);
		endWorkMessage.setMachineCode(machineCode);
		apsData.setActualDateTime(endWorkMessage.getMessageTimestamp());
		response = broker.handleMessage(endWorkMessage, apsData, null);
		checkApsMessageResponse(response);
		checkMachineState(machineCode, apsData, ReservableObjectAvailability.AVAILABLE);
		checkTaskState(taskCode, apsData, TaskStatus.EXECUTED);
		// checkChoosedMachinCoherency(taskCode, machineCode,apsData);

		LOGGER.info("Executed in " + (System.currentTimeMillis() - timestamp) + " ms");

	}
}
