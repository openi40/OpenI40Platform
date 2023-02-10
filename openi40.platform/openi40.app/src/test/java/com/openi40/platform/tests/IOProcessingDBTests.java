package com.openi40.platform.tests;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.openi40.platform.iomessages.spooler.services.IMSGSpoolingProcessorService;
import com.openi40.platform.iomessages.spooler.services.MSGSpoolerException;
import com.openi40.scheduler.apsdatacache.ApsDataCacheException;
import com.openi40.scheduler.apsdatacache.IApsDataCache;
import com.openi40.scheduler.common.aps.IApsObject;
import com.openi40.scheduler.engine.aps.ApsLogicNotifiedObjects;
import com.openi40.scheduler.engine.aps.ApsLogics;
import com.openi40.scheduler.engine.aps.IApsLogic;
import com.openi40.scheduler.engine.aps.IApsLogicComposer;
import com.openi40.scheduler.engine.aps.IApsLogicObserver;
import com.openi40.scheduler.engine.aps.IApsMessagesBroker;
import com.openi40.scheduler.engine.aps.IApsMessagesBroker.ApsMessageResponse;
import com.openi40.scheduler.engine.apsdata.IApsDataManager;
import com.openi40.scheduler.engine.rules.IRuleSolutionListener;
import com.openi40.scheduler.engine.rules.IRuleStateListener;
import com.openi40.scheduler.engine.rules.RulePlanningEvent;
import com.openi40.scheduler.engine.rules.RulePlanningEvent.RuleEventType;
import com.openi40.scheduler.iomessages.EndSetupIOMessage;
import com.openi40.scheduler.iomessages.EndWorkIOMessage;
import com.openi40.scheduler.iomessages.StartSetupIOMessage;
import com.openi40.scheduler.iomessages.StartWorkIOMessage;
import com.openi40.scheduler.iomessages.TaskProductionUpdateIOMessage;
import com.openi40.scheduler.mapper.MapperException;
import com.openi40.scheduler.model.ReservableObjectAvailability;
import com.openi40.scheduler.model.aps.ApsData;
import com.openi40.scheduler.model.aps.ApsLogicDirection;
import com.openi40.scheduler.model.aps.ApsSchedulingSet;
import com.openi40.scheduler.model.dao.DataModelDaoException;
import com.openi40.scheduler.model.messages.EndSetupMessage;
import com.openi40.scheduler.model.messages.EndWorkMessage;
import com.openi40.scheduler.model.messages.StartSetupMessage;
import com.openi40.scheduler.model.messages.StartWorkMessage;
import com.openi40.scheduler.model.messages.TaskProductionUpdateMessage;
import com.openi40.scheduler.model.rules.Rule;
import com.openi40.scheduler.model.tasks.Task;
import com.openi40.scheduler.model.tasks.TaskStatus;
import com.openi40.scheduler.tests.util.IOProcessingTestUtilService;

public class IOProcessingDBTests extends AbstractStainlessSteelCompanyDBTests {
	@Autowired
	IOProcessingTestUtilService ioTestsService;
	public IOProcessingDBTests() {

	}

	@Test
	public void correctIOProcessingFlowDBTest() throws IOException, SQLException, ApsDataCacheException,
			DataModelDaoException, MapperException, MSGSpoolerException {
		try {
			prepareDB();

			IApsDataCache dataCache = this.apsDataCacheAggregator.getDataCache("DB-LINK");
			ApsData apsData = dataCache.getCachedData("DB-DataSet", "DEFAULT");
			initializeScheduling(apsData, ApsLogicDirection.FORWARD, ApsLogics.FORWARD_APS);
			IApsLogicComposer composer = contextualBusinessLogicFactory.create(IApsLogicComposer.class, apsData,
					apsData);
			composer.schedule(apsData, null);
			dataCache.save("DB-DataSet", "DEFAULT");
			dumpScenario(apsData, "correctIOProcessingFlowDBTest-0");
			verifyScheduled(apsData);
			long timestamp = System.currentTimeMillis();
			String machineCode = "SS-LASERCUTMACHINE-02";
			String taskCode = "SS/ORD003-2020/001-AISI316SQUARE50x50x3mm-1-001";
			// Sending startSetupMessage and verifiyng status coherences
			StartSetupIOMessage startSetupMessage = new StartSetupIOMessage();
			startSetupMessage.setMessageTimestamp(Timestamp.valueOf("2021-01-11 08:59:59"));
			apsData.setActualDateTime(Timestamp.valueOf("2021-01-11 08:59:59"));
			startSetupMessage.setTaskCode(taskCode);
			startSetupMessage.setMachineCode(machineCode);
			spoolMessage(startSetupMessage);
			waitEmptySpool();
			dumpScenario(apsData, "correctIOProcessingFlowDBTest-START-SETUP");
			verifyScheduled(apsData);
			// ApsMessageResponse response = broker.handleMessage(startSetupMessage,
			// apsData, null);
			// ioTestsService.checkApsMessageResponse(response);
			ioTestsService.checkMachineState(machineCode, apsData, ReservableObjectAvailability.EXECUTING_SETUP);
			ioTestsService.checkTaskState(taskCode, apsData, TaskStatus.EXECUTING_SETUP);
			ioTestsService.checkChoosedMachinCoherency(taskCode, machineCode, apsData);

			EndSetupIOMessage endSetupMessage = new EndSetupIOMessage();
			endSetupMessage.setMessageTimestamp(Timestamp.valueOf("2021-01-11 09:05:59"));
			apsData.setActualDateTime(endSetupMessage.getMessageTimestamp());
			endSetupMessage.setTaskCode(taskCode);
			endSetupMessage.setMachineCode(machineCode);
			spoolMessage(endSetupMessage);
			waitEmptySpool();
			dumpScenario(apsData, "correctIOProcessingFlowDBTest-END-SETUP");
			verifyScheduled(apsData);
			// response = broker.handleMessage(endSetupMessage, apsData, null);
			// ioTestsService.checkApsMessageResponse(response);
			ioTestsService.checkMachineState(machineCode, apsData, ReservableObjectAvailability.SETUP_DONE);
			ioTestsService.checkTaskState(taskCode, apsData, TaskStatus.SETUP_DONE);
			ioTestsService.checkChoosedMachinCoherency(taskCode, machineCode, apsData);

			StartWorkIOMessage startWorkMessage = new StartWorkIOMessage();
			startWorkMessage.setMessageTimestamp(Timestamp.valueOf("2021-01-11 09:06:59"));
			startWorkMessage.setTaskCode(taskCode);
			startWorkMessage.setMachineCode(machineCode);
			apsData.setActualDateTime(startWorkMessage.getMessageTimestamp());
			spoolMessage(startWorkMessage);
			waitEmptySpool();
			dumpScenario(apsData, "correctIOProcessingFlowDBTest-START-WORKING");
			verifyScheduled(apsData);
			// response = broker.handleMessage(startWorkMessage, apsData, null);
			// ioTestsService.checkApsMessageResponse(response);
			ioTestsService.checkMachineState(machineCode, apsData, ReservableObjectAvailability.EXECUTING_WORK);
			ioTestsService.checkTaskState(taskCode, apsData, TaskStatus.EXECUTING_WORK);
			ioTestsService.checkChoosedMachinCoherency(taskCode, machineCode, apsData);
			for (int i = 1; i <= 9; i++) {
				TaskProductionUpdateIOMessage produpdate = new TaskProductionUpdateIOMessage();
				produpdate.setMessageTimestamp(Timestamp.valueOf("2021-01-11 09:1" + i + ":59"));
				produpdate.setTaskCode(taskCode);
				produpdate.setMachineCode(machineCode);
				produpdate.setProduced(i+1);
				apsData.setActualDateTime(produpdate.getMessageTimestamp());
				spoolMessage(produpdate);
				waitEmptySpool();
				dumpScenario(apsData, "correctIOProcessingFlowDBTest-PRODUCTION-UPDATE-" + i);
				verifyScheduled(apsData);
				// response = broker.handleMessage(produpdate, apsData, null);
				// ioTestsService.checkApsMessageResponse(response);
				ioTestsService.checkMachineState(machineCode, apsData, ReservableObjectAvailability.EXECUTING_WORK);
				ioTestsService.checkTaskState(taskCode, apsData, TaskStatus.EXECUTING_WORK);
				ioTestsService.checkChoosedMachinCoherency(taskCode, machineCode, apsData);
			}
			EndWorkIOMessage endWorkMessage = new EndWorkIOMessage();
			endWorkMessage.setMessageTimestamp(Timestamp.valueOf("2021-01-11 09:10:59"));
			endWorkMessage.setTaskCode(taskCode);
			endWorkMessage.setMachineCode(machineCode);
			apsData.setActualDateTime(endWorkMessage.getMessageTimestamp());
			spoolMessage(endWorkMessage);
			waitEmptySpool();
			dumpScenario(apsData, "correctIOProcessingFlowDBTest-END-WORK");
			verifyScheduled(apsData);
			// response = broker.handleMessage(endWorkMessage, apsData, null);
			// ioTestsService.checkApsMessageResponse(response);
			ioTestsService.checkMachineState(machineCode, apsData, ReservableObjectAvailability.AVAILABLE);
			ioTestsService.checkTaskState(taskCode, apsData, TaskStatus.EXECUTED);
			ioTestsService.checkChoosedMachinCoherency(taskCode, machineCode, apsData);

			LOGGER.info("Executed in " + (System.currentTimeMillis() - timestamp) + " ms");

		} finally {
			dropDB();
		}
	}

	private static final int SAVING_CHECK_ITERATIONS = 5;

}
