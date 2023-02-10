package com.openi40.platform.tests;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;

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
import com.openi40.scheduler.inputchannels.streaminputs.IInputDataStreamFactory;
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

//@TestPropertySource(locations = { "classpath:application-realtime.yml" })

public class RealtimeIOProcessingDBTests extends AbstractStainlessSteelCompanyDBTests {
	@Autowired
	IOProcessingTestUtilService ioTestsService;
	@Autowired
	@Qualifier("persistenceInputDataStreamFactories")
	List<IInputDataStreamFactory> streamFactories;

	public RealtimeIOProcessingDBTests() {

	}

	protected Date getCurrentTime() {
		return new Timestamp(System.currentTimeMillis());
	}

	@Test
	public void correctRealtimeIOProcessingFlowDBTest() throws IOException, SQLException, ApsDataCacheException,
			DataModelDaoException, MapperException, MSGSpoolerException {
		try {
			if (streamFactories != null) {
				for (IInputDataStreamFactory sf : streamFactories) {
					sf.setRealtime(true);
				}
			}
			prepareDB();
			IApsDataCache dataCache = this.apsDataCacheAggregator.getDataCache("DB-LINK");
			ApsData apsData = dataCache.getCachedData("DB-DataSet", "DEFAULT");
			initializeScheduling(apsData, ApsLogicDirection.FORWARD, ApsLogics.FORWARD_APS);
			IApsLogicComposer composer = contextualBusinessLogicFactory.create(IApsLogicComposer.class, apsData,
					apsData);
			composer.schedule(apsData, null);
			dataCache.save("DB-DataSet", "DEFAULT");
			dumpScenario(apsData, "correctRealtimeIOProcessingFlowDBTest-0");
			verifyScheduled(apsData);
			long timestamp = System.currentTimeMillis();
			String machineCode = "SS-LASERCUTMACHINE-02";
			String taskCode = "SS/ORD003-2020/001-AISI316SQUARE50x50x3mm-1-001";
			// Sending startSetupMessage and verifiyng status coherences
			StartSetupIOMessage startSetupMessage = new StartSetupIOMessage();
			startSetupMessage.setMessageTimestamp(getCurrentTime());
			startSetupMessage.setTaskCode(taskCode);
			startSetupMessage.setMachineCode(machineCode);
			spoolMessage(startSetupMessage);
			waitEmptySpool();
			dumpScenario(apsData, "correctRealtimeIOProcessingFlowDBTest-START-SETUP");
			verifyScheduled(apsData);
			// ApsMessageResponse response = broker.handleMessage(startSetupMessage,
			// apsData, null);
			// ioTestsService.checkApsMessageResponse(response);
			ioTestsService.checkMachineState(machineCode, apsData, ReservableObjectAvailability.EXECUTING_SETUP);
			ioTestsService.checkTaskState(taskCode, apsData, TaskStatus.EXECUTING_SETUP);
			ioTestsService.checkChoosedMachinCoherency(taskCode, machineCode, apsData);

			EndSetupIOMessage endSetupMessage = new EndSetupIOMessage();
			endSetupMessage.setMessageTimestamp(getCurrentTime());
			apsData.setActualDateTime(endSetupMessage.getMessageTimestamp());
			endSetupMessage.setTaskCode(taskCode);
			endSetupMessage.setMachineCode(machineCode);
			spoolMessage(endSetupMessage);
			waitEmptySpool();
			dumpScenario(apsData, "correctRealtimeIOProcessingFlowDBTest-END-SETUP");
			verifyScheduled(apsData);
			// response = broker.handleMessage(endSetupMessage, apsData, null);
			// ioTestsService.checkApsMessageResponse(response);
			ioTestsService.checkMachineState(machineCode, apsData, ReservableObjectAvailability.SETUP_DONE);
			ioTestsService.checkTaskState(taskCode, apsData, TaskStatus.SETUP_DONE);
			ioTestsService.checkChoosedMachinCoherency(taskCode, machineCode, apsData);

			StartWorkIOMessage startWorkMessage = new StartWorkIOMessage();
			startWorkMessage.setMessageTimestamp(getCurrentTime());
			startWorkMessage.setTaskCode(taskCode);
			startWorkMessage.setMachineCode(machineCode);

			spoolMessage(startWorkMessage);
			waitEmptySpool();
			dumpScenario(apsData, "correctRealtimeIOProcessingFlowDBTest-START-WORKING");
			verifyScheduled(apsData);
			// response = broker.handleMessage(startWorkMessage, apsData, null);
			// ioTestsService.checkApsMessageResponse(response);
			ioTestsService.checkMachineState(machineCode, apsData, ReservableObjectAvailability.EXECUTING_WORK);
			ioTestsService.checkTaskState(taskCode, apsData, TaskStatus.EXECUTING_WORK);
			ioTestsService.checkChoosedMachinCoherency(taskCode, machineCode, apsData);
			for (int i = 1; i <= 9; i++) {
				TaskProductionUpdateIOMessage produpdate = new TaskProductionUpdateIOMessage();
				produpdate.setMessageTimestamp(getCurrentTime());
				produpdate.setTaskCode(taskCode);
				produpdate.setMachineCode(machineCode);
				produpdate.setProduced(i + 1);
				apsData.setActualDateTime(produpdate.getMessageTimestamp());
				spoolMessage(produpdate);
				waitEmptySpool();
				dumpScenario(apsData, "correctRealtimeIOProcessingFlowDBTest-PRODUCTION-UPDATE-" + i);
				verifyScheduled(apsData);
				// response = broker.handleMessage(produpdate, apsData, null);
				// ioTestsService.checkApsMessageResponse(response);
				ioTestsService.checkMachineState(machineCode, apsData, ReservableObjectAvailability.EXECUTING_WORK);
				ioTestsService.checkTaskState(taskCode, apsData, TaskStatus.EXECUTING_WORK);
				ioTestsService.checkChoosedMachinCoherency(taskCode, machineCode, apsData);
			}
			EndWorkIOMessage endWorkMessage = new EndWorkIOMessage();
			endWorkMessage.setMessageTimestamp(getCurrentTime());
			endWorkMessage.setTaskCode(taskCode);
			endWorkMessage.setMachineCode(machineCode);

			spoolMessage(endWorkMessage);
			waitEmptySpool();
			dumpScenario(apsData, "correctRealtimeIOProcessingFlowDBTest-END-WORK");
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
