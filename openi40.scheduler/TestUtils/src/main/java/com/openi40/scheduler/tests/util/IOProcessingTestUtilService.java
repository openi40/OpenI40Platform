package com.openi40.scheduler.tests.util;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.openi40.scheduler.engine.aps.IApsMessagesBroker.ApsMessageResponse;
import com.openi40.scheduler.model.ReservableObjectAvailability;
import com.openi40.scheduler.model.aps.ApsData;
import com.openi40.scheduler.model.dao.DataModelDaoException;
import com.openi40.scheduler.model.dao.IMachineDao;
import com.openi40.scheduler.model.dao.ITaskDao;
import com.openi40.scheduler.model.dao.IWorkOrderDao;
import com.openi40.scheduler.model.equipment.Machine;
import com.openi40.scheduler.model.messages.handling.MessageHandlingErrorMessage;
import com.openi40.scheduler.model.tasks.Task;
import com.openi40.scheduler.model.tasks.TaskStatus;

import junit.framework.Assert;

import org.junit.jupiter.api.*; 
@Service
public class IOProcessingTestUtilService {
	static Logger LOGGER=LoggerFactory.getLogger(IOProcessingTestUtilService.class);
	@Autowired
	IWorkOrderDao workOrderDao;
	@Autowired
	ITaskDao taskDao;
	@Autowired
	IMachineDao machineDao;
	public IOProcessingTestUtilService() {
		
	}

	public void checkApsMessageResponse(ApsMessageResponse response) {
		List<MessageHandlingErrorMessage> errs = response.getErrors();
		for (MessageHandlingErrorMessage m : errs) {
			LOGGER.error("Error code:" + m.getErrorCode() + " description:" + m.getErrorMsg());
	
		}
		Assert.assertTrue("Message correctly handled=>false", response.isMessageHandled());
	}

	public void checkChoosedMachinCoherency(String taskCode, String machineCode, ApsData data)
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

	public void checkMachineState(String machineCode, ApsData data, ReservableObjectAvailability state)
			throws DataModelDaoException {
		Machine machine = machineDao.findByCode(machineCode, data);
		Assert.assertEquals(
				"Required machine state=>" + state.name() + " but is => "
						+ (machine.getAvailability() != null ? machine.getAvailability().name() : "null"),
				machine.getAvailability(), state);
	}

	public void checkTaskState(String taskCode, ApsData data, TaskStatus state) throws DataModelDaoException {
		Task task = taskDao.findByCode(taskCode, data);
		Assert.assertEquals("Required task state=>" + state.name() + " but is => "
				+ (task.getStatus() != null ? task.getStatus().name() : "null"), task.getStatus(), state);
	}

}
