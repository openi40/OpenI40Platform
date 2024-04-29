/**
 * 
 * This code is part of the OpenI40 open source advanced production scheduler
 * platform suite, have look to its licencing options.
 * Web site: http://openi40.org/  
 * Github: https://github.com/openi40/OpenI40Platform
 * We hope you enjoy implementing new amazing projects with it.
 * @author Paolo Zavalloni  architectures@openi40.org
 *
 */
package com.openi40.scheduler.tests;

import java.util.Date;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.openi40.scheduler.engine.aps.IApsLogic;
import com.openi40.scheduler.engine.aps.IApsLogicComposer;
import com.openi40.scheduler.engine.apsdata.IApsDataManager;
import com.openi40.scheduler.engine.changeover.IChangeOverLogic;
import com.openi40.scheduler.engine.contextualplugarch.IContextualBusinessLogicFactory;
import com.openi40.scheduler.engine.equipment.allocation.IEquipmentAllocator;
import com.openi40.scheduler.engine.setuptime.ISetupTimeLogic;
import com.openi40.scheduler.engine.standalone.Main;
import com.openi40.scheduler.engine.timesheet.ITimesheetLogic;
import com.openi40.scheduler.engine.worktime.IWorkTimeLogic;
import com.openi40.scheduler.model.aps.ApsSchedulingSet;
import com.openi40.scheduler.model.aps.ApsData;
import com.openi40.scheduler.model.equipment.Machine;
import com.openi40.scheduler.model.equipment.TaskEquipmentChangeOverInfo;
import com.openi40.scheduler.model.equipment.TaskEquipmentInfo;
import com.openi40.scheduler.model.rules.EquipmentRule;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Main.class)
public class FactoriesTest {
	@Autowired
	IContextualBusinessLogicFactory ComponentFactory;

	@BeforeClass
	public static void Initialize() {
		TestsRuntimeStartup.Initialize();
		
	}

	@Test
	public final void testFactories() {

		ApsData context = new ApsData();
		context.getSchedulingWindow().setStartDateTime(new Date());
		context.getSchedulingWindow().setEndDateTime(new Date());
		IApsDataManager apsDataManager = ComponentFactory.create(IApsDataManager.class, context, context);
		apsDataManager.initialize(context);
		EquipmentRule equipmentAllocationConstraint = new EquipmentRule(context);
		TaskEquipmentInfo plannedEquipment = new TaskEquipmentInfo(context);
		// Test EquipmentAllocator creation
		IEquipmentAllocator equipmentAllocator = ComponentFactory.create(IEquipmentAllocator.class,
				equipmentAllocationConstraint, context);
		Assert.assertNotNull("IEquipmentAllocator is not allocated ", equipmentAllocator);
		// Test CapacityEvaluator creation
		IWorkTimeLogic capacityEvaluationFactoryRepository = ComponentFactory.create(IWorkTimeLogic.class,
				plannedEquipment, context);
		Assert.assertNotNull("ICapacityEvaluator is not allocated ", capacityEvaluationFactoryRepository);
		TaskEquipmentInfo info = new TaskEquipmentInfo(context);
		// Test ISetupEvaluator creation
		ISetupTimeLogic setupTimeLogic = ComponentFactory.create(ISetupTimeLogic.class, info, context);
		Assert.assertNotNull("ISetupEvaluator is not allocated ", setupTimeLogic);
		// Test ISetupExpiredTimeEvaluator creation
		TaskEquipmentChangeOverInfo taskEquipmentChangeOverInfo = new TaskEquipmentChangeOverInfo(context);
		// Test IChangeOverEvaluator creation
		IChangeOverLogic changeOverLogic = ComponentFactory.create(IChangeOverLogic.class, taskEquipmentChangeOverInfo,
				context);
		Assert.assertNotNull("IChangeOverEvaluator is not allocated ", changeOverLogic);

		// Test ICalendarManager creation
		Machine machine = new Machine(context);
		ITimesheetLogic timesheetLogic = (ComponentFactory.create(ITimesheetLogic.class, machine, context));
		Assert.assertNotNull("ICalendarManager is not allocated ", timesheetLogic);
		// Test ISchedulerEngine creation
		IApsLogicComposer apsLogicComposer = ComponentFactory.create(IApsLogicComposer.class, context, context);
		Assert.assertNotNull("ISchedulingEngine is not allocated ", apsLogicComposer);
		ApsSchedulingSet apsAction = new ApsSchedulingSet(context);
		context.getSchedulingSets().add(apsAction);
		IApsLogic apsLogic = ComponentFactory.create(IApsLogic.class, apsAction, context);
		Assert.assertNotNull("ISchedulingAlgorithm is not allocated ", apsLogic);
		apsAction.setAlgorithmType(apsLogic.getImplementationKey());
		apsAction.setOptions(apsLogic.createDefaultOptions(apsAction));
		IApsLogicComposer engine = ComponentFactory.create(IApsLogicComposer.class, context, context);
		Assert.assertNotNull("ISchedulingEngine is not allocated ", engine);
		engine.schedule(context, null);

	}
}