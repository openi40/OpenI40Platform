package com.openi40.scheduler.engine.equipment.configuration;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.openi40.scheduler.engine.OpenI40Exception;
import com.openi40.scheduler.engine.contextualplugarch.BusinessLogic;
import com.openi40.scheduler.model.ITimesheetAllocableObject;
import com.openi40.scheduler.model.aps.ApsSchedulingSet;
import com.openi40.scheduler.model.equipment.Group;
import com.openi40.scheduler.model.equipment.TaskExecutionUseModel;
import com.openi40.scheduler.model.tasks.Task;
/**
 * 
 * This code is part of the OpenI40 open source advanced production scheduler
 * platform suite, have look to its licencing options.
 * Web site: http://openi40.org/  
 * Github: https://github.com/openi40/OpenI40Platform
 * We hope you enjoy implementing new amazing projects with it.
 * @author architectures@openi40.org
 *
 *
 * Base implementation for Work resource and secondary resource working
 * configurations exploder
 * 
 * <typeparam name="RCType"></typeparam>
 */

public abstract class AbstractWorkResourceConfigurator<RCType extends ITimesheetAllocableObject, RCTypeGroup extends Group<RCType>, WEquipmentModelType extends TaskExecutionUseModel<RCType, RCTypeGroup>> extends BusinessLogic<WEquipmentModelType> {
	static Logger LOGGER = LoggerFactory.getLogger(AbstractWorkResourceConfigurator.class);
	protected Class<WEquipmentModelType> equipmentClass = null;

	public AbstractWorkResourceConfigurator(Class<WEquipmentModelType> equipmentClass) {
		this.equipmentClass = equipmentClass;
	}

	public List<WEquipmentModelType> explodeConfigurations(WEquipmentModelType config, Task task, ApsSchedulingSet action) {
		return Permutate(config);
	}

	private List<WEquipmentModelType> Permutate(WEquipmentModelType v) {
		try {
			List<WEquipmentModelType> outValues = new ArrayList<WEquipmentModelType>();
			if (v.getQty() != 0) {
				WEquipmentModelType um = equipmentClass.newInstance();
				um.setGroup(v.getGroup());
				um.setQty(v.getQty());
				outValues.add(um);
			} else {
				if (v.getMinQty() < v.getMaxQty() && v.getMinQty() >= 0 && v.getMaxQty() >= 0) {
					for (int i = v.getMinQty(); i <= v.getMaxQty(); i++) {
						WEquipmentModelType um = equipmentClass.newInstance();
						um.setGroup(v.getGroup());
						um.setQty(v.getQty());
						outValues.add(um);
					}
				} else if (v.getDiscreteRange().size() > 0) {
					for (int dValue : v.getDiscreteRange()) {
						WEquipmentModelType um = equipmentClass.newInstance();
						um.setGroup(v.getGroup());
						um.setQty(v.getQty());
						outValues.add(um);
					}
				} else {
					throw new OpenI40Exception("Error, incomprensible WorkEquipmentUseModel configuration " + v.toString());
				}
			}
			return outValues;
		} catch (InstantiationException e) {
			throw new OpenI40Exception("Error, nested exception ", e);
		} catch (IllegalAccessException e) {
			throw new OpenI40Exception("Error, nested exception ", e);
		} finally {
		}

	}
}