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

import java.util.List;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.openi40.scheduler.engine.contextualplugarch.IContextualBusinessLogicFactory;
import com.openi40.scheduler.engine.equipment.configuration.IEquipmentConfigurator;
import com.openi40.scheduler.engine.standalone.Main;
import com.openi40.scheduler.model.aps.ApsSchedulingSet;
import com.openi40.scheduler.model.aps.ApsData;
import com.openi40.scheduler.model.aps.ApsLogicOptions;
import com.openi40.scheduler.model.equipment.Machine;
import com.openi40.scheduler.model.equipment.Resource;
import com.openi40.scheduler.model.equipment.ResourceGroup;
import com.openi40.scheduler.model.equipment.TaskEquipmentInfo;
import com.openi40.scheduler.model.equipment.TaskEquipmentModelInfo;
import com.openi40.scheduler.model.equipment.TaskEquipmentModelOptions;
import com.openi40.scheduler.model.equipment.TaskExecutionModel;
import com.openi40.scheduler.model.equipment.TaskPreparationUseModel;
import com.openi40.scheduler.model.equipment.TaskProcessMetaInfo;
import com.openi40.scheduler.model.tasks.Task;

/** 
 Summary description for DefaultEquipmentConfiguratorTest
*/
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Main.class)
public class DefaultEquipmentConfiguratorTest
{
	@Autowired IContextualBusinessLogicFactory ComponentFactory;
	public DefaultEquipmentConfiguratorTest()
	{

	}
	@BeforeClass
	public static void Initialize()
	{
		TestsRuntimeStartup.Initialize();
	}

	public static com.openi40.scheduler.model.equipment.TaskEquipmentModelOptions CreateCantiereEquipment(ApsData c)
	{
		com.openi40.scheduler.model.equipment.TaskEquipmentModelOptions toConfigure = new TaskEquipmentModelOptions(c);
		TaskEquipmentModelInfo equipmentModel = new TaskEquipmentModelInfo(c);
		Machine machine = new Machine(c);
		machine.setCode("001");
		machine.setId("001");
		machine.setDescription("Cantiere");
		equipmentModel.setTaskMetaInfo(new TaskProcessMetaInfo(c));
		equipmentModel.getTaskMetaInfo().setSetupTime(10d);
		equipmentModel.getPreparationModel().getResource().getGroup().getResources().add(machine);
		equipmentModel.getPreparationModel().getResource().getGroup().setId("Cantieri001");
		equipmentModel.getPreparationModel().getResource().getGroup().setCode("Cantieri001");
		equipmentModel.getPreparationModel().getResource().getGroup().setDescription("Cantieri work center");
		equipmentModel.getPreparationModel().getResource().setQty(1);
		equipmentModel.getExecutionModel().getResource().setGroup(equipmentModel.getPreparationModel().getResource().getGroup());
		equipmentModel.getExecutionModel().getResource().setQty(1);
		Resource carpentiere = new Resource(c);
		carpentiere.setId("carpentiere");
		carpentiere.setCode("carpentiere");
		carpentiere.setDescription("carpentiere");
		Resource muratore = new Resource(c);
		muratore.setId("muratore");
		muratore.setCode("muratore");
		muratore.setDescription("muratore");
		TaskPreparationUseModel<Resource, ResourceGroup> secondaryResourceCarpentieri = new TaskPreparationUseModel<Resource, ResourceGroup>(Resource.class, ResourceGroup.class);
		secondaryResourceCarpentieri.setId("carpentieri");
		secondaryResourceCarpentieri.setCode("carpentieri");
		secondaryResourceCarpentieri.setDescription("carpentieri");
		secondaryResourceCarpentieri.getGroup().getResources().add(carpentiere);
		secondaryResourceCarpentieri.getGroup().setId("carpentieri WC");
		secondaryResourceCarpentieri.getGroup().setCode("carpentieri WC");
		secondaryResourceCarpentieri.getGroup().setDescription("carpentieri work center");
		secondaryResourceCarpentieri.setQty(1);
		equipmentModel.getPreparationModel().getSecondaryResources().add(secondaryResourceCarpentieri);
		TaskPreparationUseModel<Resource, ResourceGroup> secondaryResourceMuratore = new TaskPreparationUseModel<Resource, ResourceGroup>(Resource.class, ResourceGroup.class);
		secondaryResourceMuratore.setId("muratori");
		secondaryResourceMuratore.setCode("muratori");
		secondaryResourceMuratore.setDescription("muratori");
		secondaryResourceMuratore.getGroup().getResources().add(muratore);
		secondaryResourceMuratore.getGroup().setId("muratori wc");
		secondaryResourceMuratore.getGroup().setCode("muratori wc");
		secondaryResourceMuratore.getGroup().setDescription("muratori work center");
		secondaryResourceMuratore.setQty(1);
		equipmentModel.getPreparationModel().getSecondaryResources().add(secondaryResourceMuratore);

		TaskExecutionModel.SecondaryModelInfo workSecondaryResourceCarpentiere = new TaskExecutionModel.SecondaryModelInfo();
		workSecondaryResourceCarpentiere.setMinQty(1);
		workSecondaryResourceCarpentiere.setMaxQty(3);
		// workSecondaryResourceCarpentiere.Qty = 1;
		workSecondaryResourceCarpentiere.getGroup().getResources().add(carpentiere);

		equipmentModel.getExecutionModel().getSecondaryResources().add(workSecondaryResourceCarpentiere);
		TaskExecutionModel.SecondaryModelInfo workSecondaryResourceMuratore = new TaskExecutionModel.SecondaryModelInfo();
		workSecondaryResourceMuratore.getGroup().getResources().add(muratore);
		workSecondaryResourceMuratore.setMinQty(1);
		workSecondaryResourceMuratore.setMaxQty(3);
		//workSecondaryResourceMuratore.Qty = 1;
		equipmentModel.getExecutionModel().getSecondaryResources().add(workSecondaryResourceMuratore);
		Resource capomastro = new Resource(c);
		capomastro.setId("capomastro");
		capomastro.setCode("capomastro");
		TaskExecutionModel.SecondaryModelInfo workSecondaryResourceCapomastro = new TaskExecutionModel.SecondaryModelInfo();
		workSecondaryResourceCapomastro.getGroup().getResources().add(capomastro);
		workSecondaryResourceCapomastro.setMinQty(1);
		workSecondaryResourceCapomastro.setMaxQty(3);
		//workSecondaryResourceCapomastro.Qty = 1;
		equipmentModel.getExecutionModel().getSecondaryResources().add(workSecondaryResourceCapomastro);
		toConfigure.getEquipmentModels().add(equipmentModel);
		return toConfigure;
	}


	@Test
	public final void TestDefaultEquipmentConfigurator()
	{
		ApsData context = new ApsData();
		com.openi40.scheduler.model.equipment.TaskEquipmentModelOptions toConfigure = CreateCantiereEquipment(context);

		ApsLogicOptions apsLogicOptions = new ApsLogicOptions();

		com.openi40.scheduler.model.tasks.Task scheduledActivity = new Task(context);
		scheduledActivity.setParentSchedulingSet(new ApsSchedulingSet(context));
		IEquipmentConfigurator equipmentConfigurator = ComponentFactory.create(IEquipmentConfigurator.class,toConfigure, context);
		List<TaskEquipmentInfo> list = equipmentConfigurator.calculateEquipmentConfigurations(toConfigure, apsLogicOptions, scheduledActivity, context);
		Assert.assertEquals("The resulting planned resources must be 27!!!! But are " + list.size(),list.size(),27);
	}
}