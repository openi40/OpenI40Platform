package com.openi40.scheduler.engine.resallocator;

import java.util.List;

import com.openi40.scheduler.model.planning.equipment.EquipmentEvaluatedChoice;
import com.openi40.scheduler.model.planning.material.MaterialEvaluatedChoice;
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
public class ResourcesCombination {
	public ResourcesCombination(EquipmentEvaluatedChoice equipmentAllocation,
			List<MaterialEvaluatedChoice> materialSatisfactionOptions) {
		EquipmentAllocationOption = equipmentAllocation;
		MaterialAllocationOptions = materialSatisfactionOptions;
	}

	private EquipmentEvaluatedChoice EquipmentAllocationOption;

	public final EquipmentEvaluatedChoice getEquipmentAllocationOption() {
		return EquipmentAllocationOption;
	}

	private List<MaterialEvaluatedChoice> MaterialAllocationOptions;

	public final List<MaterialEvaluatedChoice> getMaterialAllocationOptions() {
		return MaterialAllocationOptions;
	}

}
