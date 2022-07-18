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

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.openi40.scheduler.apsdatacache.ApsDataCacheException;
import com.openi40.scheduler.apsdatacache.IApsDataCache;
import com.openi40.scheduler.apsdatacache.IApsDataCacheAggregator;
import com.openi40.scheduler.engine.contextualplugarch.ILazyContextualBusinessLogicFactoryLoader;
import com.openi40.scheduler.engine.standalone.Main;
import com.openi40.scheduler.engine.timesheet.IAvailableTimeRangeGenerator;
import com.openi40.scheduler.model.aps.ApsData;
import com.openi40.scheduler.model.dao.DataModelDaoException;
import com.openi40.scheduler.model.dao.IMachineDao;
import com.openi40.scheduler.model.equipment.Machine;
import com.openi40.scheduler.model.time.TimesheetAvailableTimeRange;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = { Main.class })
@ComponentScan("com.openi40")
public class InputChannelsTest {
	static Logger LOGGER=org.slf4j.LoggerFactory.getLogger(InputChannelsTest.class);
	static final String dataSetName = "ARBALETE-COMPANY";
	static final String dataSetVariant = "DEFAULT";
	static final String dataSourceName = "ARBALETE-DEMO";
	static final String sawMachine01="SAWMACHINE-01";
	static final String heatingChamber="HEATING-CHAMBER";
	@Autowired
	IApsDataCacheAggregator aggregator;
	@Autowired IMachineDao machineDao;
	@Autowired ILazyContextualBusinessLogicFactoryLoader factoryLoader;
	@Test
	public void testDemoDataLoading() throws ApsDataCacheException, DataModelDaoException {
		IApsDataCache dataCache = aggregator.getDataCache(dataSourceName);
		assertNotNull("dataCache has not been loaded",dataCache);
		ApsData apsData;
		apsData = dataCache.getCachedData(dataSetName, dataSetVariant);
		apsData.getSchedulingWindow().setStartDateTime(new Date(120, 11, 5, 0, 0, 0));
		apsData.getSchedulingWindow().setEndDateTime(new Date(121, 05, 5, 24, 0, 0));
		assertNotNull("cannot load cached data from JSON!!",apsData);
		Machine machine=machineDao.findByCode(sawMachine01, apsData);
		assertTrue("custom model objects factory is not working properly",machine instanceof MyMachineSubclass);
		IAvailableTimeRangeGenerator timeRangesGenerator = factoryLoader.getComponentFactory().create(IAvailableTimeRangeGenerator.class, machine, apsData);
		List<TimesheetAvailableTimeRange> ranges = timeRangesGenerator.generateAvailableTimeRanges(machine, apsData.getSchedulingWindow());
		System.out.println(sawMachine01);
		ranges.forEach((x)->{
			System.out.println(x.toString());
		});
		machine=machineDao.findByCode(heatingChamber, apsData);
		assertTrue("custom model objects factory is not working properly",machine instanceof MyMachineSubclass);
		timeRangesGenerator = factoryLoader.getComponentFactory().create(IAvailableTimeRangeGenerator.class, machine, apsData);
		ranges = timeRangesGenerator.generateAvailableTimeRanges(machine, apsData.getSchedulingWindow());
		System.out.println(heatingChamber);
		ranges.forEach((x)->{
			System.out.println(x.toString());
		});

	}

}
