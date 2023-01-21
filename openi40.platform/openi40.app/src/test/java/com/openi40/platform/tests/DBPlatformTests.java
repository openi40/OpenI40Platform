package com.openi40.platform.tests;

import java.io.IOException;
import java.sql.SQLException;

import org.junit.Test;

import com.openi40.scheduler.apsdatacache.ApsDataCacheException;
import com.openi40.scheduler.apsdatacache.IApsDataCache;
import com.openi40.scheduler.engine.aps.ApsLogics;
import com.openi40.scheduler.engine.aps.IApsLogic;
import com.openi40.scheduler.engine.aps.IApsLogicComposer;
import com.openi40.scheduler.model.aps.ApsData;
import com.openi40.scheduler.model.aps.ApsLogicDirection;
import com.openi40.scheduler.model.aps.ApsSchedulingSet;
import com.openi40.scheduler.model.dao.DataModelDaoException;

public class DBPlatformTests extends AbstractStainlessSteelCompanyDBTests {

	public DBPlatformTests() {

	}

	@Test
	public void loadScheduleForwardSaveTest()
			throws IOException, SQLException, ApsDataCacheException, DataModelDaoException {
		try {
			prepareDB();

			IApsDataCache dataCache = this.apsDataCacheAggregator.getDataCache("DB-LINK");
			ApsData apsData = dataCache.getCachedData("DB-DataSet", "DEFAULT");
			IApsLogicComposer composer = contextualBusinessLogicFactory.create(IApsLogicComposer.class, apsData,
					apsData);
			composer.schedule(apsData, null);
			ApsSchedulingSet schedulingSet = new ApsSchedulingSet(apsData);
			schedulingSet.setAlgorithmDirection(ApsLogicDirection.FORWARD);
			schedulingSet.setAlgorithmType(ApsLogics.FORWARD_APS);
			IApsLogic apsLogic = contextualBusinessLogicFactory.create(IApsLogic.class, schedulingSet, apsData);
			schedulingSet.setOptions(apsLogic.createDefaultOptions(schedulingSet));
			apsData.getSchedulingSets().add(schedulingSet);

			composer.autoSetTasks(apsData, apsData.getSchedulingSets());
			composer.schedule(apsData, null);
			verifyScheduled(apsData);
			dataCache.save("DB-DataSet", "DEFAULT");
		} finally {
			dropDB();
		}
	}

	private static final int SAVING_CHECK_ITERATIONS = 5;

	@Test
	public void loadScheduleForwardSaveTestMultipleIterations()
			throws IOException, SQLException, ApsDataCacheException, DataModelDaoException {
		try {
			prepareDB();

			IApsDataCache dataCache = this.apsDataCacheAggregator.getDataCache("DB-LINK");
			ApsData apsData = dataCache.getCachedData("DB-DataSet", "DEFAULT");
			IApsLogicComposer composer = contextualBusinessLogicFactory.create(IApsLogicComposer.class, apsData,
					apsData);
			composer.schedule(apsData, null);
			ApsSchedulingSet schedulingSet = new ApsSchedulingSet(apsData);
			schedulingSet.setAlgorithmDirection(ApsLogicDirection.FORWARD);
			schedulingSet.setAlgorithmType(ApsLogics.FORWARD_APS);
			IApsLogic apsLogic = contextualBusinessLogicFactory.create(IApsLogic.class, schedulingSet, apsData);
			schedulingSet.setOptions(apsLogic.createDefaultOptions(schedulingSet));
			apsData.getSchedulingSets().add(schedulingSet);
			composer.autoSetTasks(apsData, apsData.getSchedulingSets());
			for (int i = 0; i < SAVING_CHECK_ITERATIONS; i++) {
				composer.schedule(apsData, null);
				verifyScheduled(apsData);
				dataCache.save("DB-DataSet", "DEFAULT");
				apsData=dataCache.reload("DB-DataSet", "DEFAULT");
				LOGGER.info("Saving iteration = "+i);
			}
		} finally {
			dropDB();
		}
	}

	@Test
	public void loadScheduleBackwardSaveTest()
			throws IOException, SQLException, ApsDataCacheException, DataModelDaoException {
		try {
			prepareDB();

			IApsDataCache dataCache = this.apsDataCacheAggregator.getDataCache("DB-LINK");
			ApsData apsData = dataCache.getCachedData("DB-DataSet", "DEFAULT");
			IApsLogicComposer composer = contextualBusinessLogicFactory.create(IApsLogicComposer.class, apsData,
					apsData);
			composer.schedule(apsData, null);
			ApsSchedulingSet schedulingSet = new ApsSchedulingSet(apsData);
			schedulingSet.setAlgorithmDirection(ApsLogicDirection.BACKWARD);
			schedulingSet.setAlgorithmType(ApsLogics.BACKWARD_APS);
			IApsLogic apsLogic = contextualBusinessLogicFactory.create(IApsLogic.class, schedulingSet, apsData);
			schedulingSet.setOptions(apsLogic.createDefaultOptions(schedulingSet));
			apsData.getSchedulingSets().add(schedulingSet);

			composer.autoSetTasks(apsData, apsData.getSchedulingSets());
			composer.schedule(apsData, null);
			verifyScheduled(apsData);
			dataCache.save("DB-DataSet", "DEFAULT");
		} finally {
			dropDB();
		}
	}

}
