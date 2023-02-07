package com.openi40.platform.tests;

import java.io.IOException;
import java.sql.SQLException;

import org.junit.Test;

import com.openi40.scheduler.apsdatacache.ApsDataCacheException;
import com.openi40.scheduler.apsdatacache.IApsDataCache;
import com.openi40.scheduler.common.aps.IApsObject;
import com.openi40.scheduler.engine.aps.ApsLogicNotifiedObjects;
import com.openi40.scheduler.engine.aps.ApsLogics;
import com.openi40.scheduler.engine.aps.IApsLogic;
import com.openi40.scheduler.engine.aps.IApsLogicComposer;
import com.openi40.scheduler.engine.aps.IApsLogicObserver;
import com.openi40.scheduler.engine.apsdata.IApsDataManager;
import com.openi40.scheduler.engine.rules.IRuleSolutionListener;
import com.openi40.scheduler.engine.rules.IRuleStateListener;
import com.openi40.scheduler.engine.rules.RulePlanningEvent;
import com.openi40.scheduler.engine.rules.RulePlanningEvent.RuleEventType;
import com.openi40.scheduler.mapper.MapperException;
import com.openi40.scheduler.model.aps.ApsData;
import com.openi40.scheduler.model.aps.ApsLogicDirection;
import com.openi40.scheduler.model.aps.ApsSchedulingSet;
import com.openi40.scheduler.model.dao.DataModelDaoException;
import com.openi40.scheduler.model.rules.Rule;
import com.openi40.scheduler.model.tasks.Task;

public class DBPlatformTests extends AbstractStainlessSteelCompanyDBTests {
	
	public DBPlatformTests() {

	}

	@Test
	public void loadScheduleForwardSaveTest()
			throws IOException, SQLException, ApsDataCacheException, DataModelDaoException, MapperException {
		try {
			prepareDB();

			IApsDataCache dataCache = this.apsDataCacheAggregator.getDataCache("DB-LINK");
			ApsData apsData = dataCache.getCachedData("DB-DataSet", "DEFAULT");
			initializeScheduling(apsData, ApsLogicDirection.FORWARD, ApsLogics.FORWARD_APS);
			IApsLogicComposer composer = contextualBusinessLogicFactory.create(IApsLogicComposer.class, apsData,
					apsData);
			composer.schedule(apsData, null);
			dumpScenario(apsData, "loadScheduleForwardSaveTest");
			verifyScheduled(apsData);
			dataCache.save("DB-DataSet", "DEFAULT");
		} finally {
			dropDB();
		}
	}

	private static final int SAVING_CHECK_ITERATIONS = 5;

	@Test
	public void loadScheduleForwardSaveTestMultipleIterations()
			throws IOException, SQLException, ApsDataCacheException, DataModelDaoException, MapperException {
		try {
			prepareDB();
			ApsLogicNotifiedObjects observer=new ApsLogicNotifiedObjects();
			observer.setConstraintRuleListener(new IRuleStateListener() {

				@Override
				public void verifiedState(Rule rule, Task task, boolean constraintMeet) {
					
					
				}
				
			});
			observer.setConstraintSolutionListener(new IRuleSolutionListener() {
				
				@Override
				public void onConstraintSolutionEvent(RulePlanningEvent event) {
					if (event.getEventType() ==RuleEventType.CONSTRAINT_UNSOLVABLE) {
						LOGGER.error(event.toString());
					}
					
				}
			});
			observer.setObserver(new IApsLogicObserver() {
				
				@Override
				public void startProcessingElement(IApsObject object) {
					
					
				}
				
				@Override
				public void processedElement(IApsObject object) {
					
					
				}
			}); 
			IApsDataCache dataCache = this.apsDataCacheAggregator.getDataCache("DB-LINK");
			ApsData apsData = dataCache.getCachedData("DB-DataSet", "DEFAULT");
			initializeScheduling(apsData, ApsLogicDirection.FORWARD, ApsLogics.FORWARD_APS);
			IApsLogicComposer composer = contextualBusinessLogicFactory.create(IApsLogicComposer.class, apsData,
					apsData);
			for (int i = 0; i < SAVING_CHECK_ITERATIONS; i++) {
				composer.schedule(apsData, observer);
				dumpScenario(apsData, "loadScheduleForwardSaveTestMultipleIterations-"+i);
				verifyScheduled(apsData);
				dataCache.save("DB-DataSet", "DEFAULT");
				apsData = dataCache.reload("DB-DataSet", "DEFAULT");
				LOGGER.info("Saving iteration = " + i);
			}
		} finally {
			dropDB();
		}
	}
	@Test
	public void loadScheduleBackwardSaveTestMultipleIterations()
			throws IOException, SQLException, ApsDataCacheException, DataModelDaoException, MapperException {
		try {
			prepareDB();
			ApsLogicNotifiedObjects observer=new ApsLogicNotifiedObjects();
			observer.setConstraintRuleListener(new IRuleStateListener() {

				@Override
				public void verifiedState(Rule rule, Task task, boolean constraintMeet) {
					
					
				}
				
			});
			observer.setConstraintSolutionListener(new IRuleSolutionListener() {
				
				@Override
				public void onConstraintSolutionEvent(RulePlanningEvent event) {
					if (event.getEventType() ==RuleEventType.CONSTRAINT_UNSOLVABLE) {
						LOGGER.error(event.toString());
					}
					
				}
			});
			observer.setObserver(new IApsLogicObserver() {
				
				@Override
				public void startProcessingElement(IApsObject object) {
					
					
				}
				
				@Override
				public void processedElement(IApsObject object) {
					
					
				}
			}); 
			IApsDataCache dataCache = this.apsDataCacheAggregator.getDataCache("DB-LINK");
			ApsData apsData = dataCache.getCachedData("DB-DataSet", "DEFAULT");
			initializeScheduling(apsData, ApsLogicDirection.BACKWARD, ApsLogics.BACKWARD_APS);
			IApsLogicComposer composer = contextualBusinessLogicFactory.create(IApsLogicComposer.class, apsData,
					apsData);
			for (int i = 0; i < SAVING_CHECK_ITERATIONS; i++) {
				composer.schedule(apsData, observer);
				dumpScenario(apsData, "loadScheduleBackwardSaveTestMultipleIterations-"+i);
				verifyScheduled(apsData);
				dataCache.save("DB-DataSet", "DEFAULT");
				apsData = dataCache.reload("DB-DataSet", "DEFAULT");
				LOGGER.info("Saving iteration = " + i);
			}
		} finally {
			dropDB();
		}
	}
	@Test
	public void loadScheduleBackwardSaveTest()
			throws IOException, SQLException, ApsDataCacheException, DataModelDaoException, MapperException {
		try {
			prepareDB();

			IApsDataCache dataCache = this.apsDataCacheAggregator.getDataCache("DB-LINK");
			ApsData apsData = dataCache.getCachedData("DB-DataSet", "DEFAULT");
			initializeScheduling(apsData, ApsLogicDirection.BACKWARD, ApsLogics.BACKWARD_APS);
			IApsLogicComposer composer = contextualBusinessLogicFactory.create(IApsLogicComposer.class, apsData,
					apsData);

			composer.schedule(apsData, null);
			dumpScenario(apsData, "loadScheduleBackwardSaveTest");
			verifyScheduled(apsData);
			dataCache.save("DB-DataSet", "DEFAULT");
		} finally {
			dropDB();
		}
	}

}
