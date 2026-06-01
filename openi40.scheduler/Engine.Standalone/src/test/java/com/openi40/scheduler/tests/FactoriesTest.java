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

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


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


@SpringBootTest(classes = Main.class)
public class FactoriesTest {
	@Autowired
	IContextualBusinessLogicFactory ComponentFactory;

	@BeforeAll
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
		assertNotNull(equipmentAllocator, "IEquipmentAllocator is not allocated ");
		// Test CapacityEvaluator creation
		IWorkTimeLogic capacityEvaluationFactoryRepository = ComponentFactory.create(IWorkTimeLogic.class,
				plannedEquipment, context);
		assertNotNull(capacityEvaluationFactoryRepository, "ICapacityEvaluator is not allocated ");
		TaskEquipmentInfo info = new TaskEquipmentInfo(context);
		// Test ISetupEvaluator creation
		ISetupTimeLogic setupTimeLogic = ComponentFactory.create(ISetupTimeLogic.class, info, context);
		assertNotNull(setupTimeLogic, "ISetupEvaluator is not allocated ");
		// Test ISetupExpiredTimeEvaluator creation
		TaskEquipmentChangeOverInfo taskEquipmentChangeOverInfo = new TaskEquipmentChangeOverInfo(context);
		// Test IChangeOverEvaluator creation
		IChangeOverLogic changeOverLogic = ComponentFactory.create(IChangeOverLogic.class, taskEquipmentChangeOverInfo,
				context);
		assertNotNull(changeOverLogic, "IChangeOverEvaluator is not allocated ");

		// Test ICalendarManager creation
		Machine machine = new Machine(context);
		ITimesheetLogic timesheetLogic = (ComponentFactory.create(ITimesheetLogic.class, machine, context));
		assertNotNull(timesheetLogic, "ICalendarManager is not allocated ");
		// Test ISchedulerEngine creation
		IApsLogicComposer apsLogicComposer = ComponentFactory.create(IApsLogicComposer.class, context, context);
		assertNotNull(apsLogicComposer, "ISchedulingEngine is not allocated ");
		ApsSchedulingSet apsAction = new ApsSchedulingSet(context);
		context.getSchedulingSets().add(apsAction);
		IApsLogic apsLogic = ComponentFactory.create(IApsLogic.class, apsAction, context);
		assertNotNull(apsLogic, "ISchedulingAlgorithm is not allocated ");
		apsAction.setAlgorithmType(apsLogic.getImplementationKey());
		apsAction.setOptions(apsLogic.createDefaultOptions(apsAction));
		IApsLogicComposer engine = ComponentFactory.create(IApsLogicComposer.class, context, context);
		assertNotNull(engine, "ISchedulingEngine is not allocated ");
		engine.schedule(context, null);

	}
}