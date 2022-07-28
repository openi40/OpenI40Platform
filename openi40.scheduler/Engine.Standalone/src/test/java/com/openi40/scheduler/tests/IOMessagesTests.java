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
import com.openi40.scheduler.model.aps.ApsData;
import com.openi40.scheduler.model.aps.ApsLogicOptions;
import com.openi40.scheduler.model.aps.ApsSchedulingSet;
import com.openi40.scheduler.model.dao.DataModelDaoException;
import com.openi40.scheduler.model.dao.IWorkOrderDao;
import com.openi40.scheduler.model.messages.StartSetupMessage;
import com.openi40.scheduler.model.orders.WorkOrder;

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

	@Test
	public void correctMessagesFlowTest() throws ApsDataCacheException {
		ApsData apsData = uncachedAccessor.loadData(dataSourceName, dataSetName, dataSetVariant);
		setScheduleAllWorkOrders(apsData, ApsLogics.FORWARD_APS);
		IApsLogicComposer composer = ComponentFactory.create(IApsLogicComposer.class, apsData, apsData);
		composer.schedule(apsData, null);
		IApsMessagesBroker broker = ComponentFactory.create(IApsMessagesBroker.class, apsData, apsData);
		StartSetupMessage startSetupMessage = new StartSetupMessage(apsData);
		startSetupMessage.setMessageTimestamp(Timestamp.valueOf("2021-04-09 08:59:59"));
		startSetupMessage.setTaskCode("SS/ORD003-2020/001-AISI316SQUARE50x50x3mm-1-001");
		startSetupMessage.setMachineCode("SS-LASERCUTMACHINE-01");
		ApsMessageResponse response = broker.handleMessage(startSetupMessage, apsData, null);
		checkApsMessageResponse(response);
	}
}
