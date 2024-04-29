package com.openi40.scheduler.engine.material;

import java.util.List;

import com.openi40.scheduler.engine.contextualplugarch.BusinessInterface;
import com.openi40.scheduler.engine.contextualplugarch.IBusinessLogic;
import com.openi40.scheduler.model.aps.ApsData;
import com.openi40.scheduler.model.rules.MaterialRule;
import com.openi40.scheduler.model.time.TimeSegmentRequirement;
/**
 * 
 * This code is part of the OpenI40 open source advanced production scheduler
 * platform suite, have look to its licencing options.
 * Web site: http://openi40.org/  
 * Github: https://github.com/openi40/OpenI40Platform
 * We hope you enjoy implementing new amazing projects with it.
 * @author Paolo Zavalloni  architectures@openi40.org
 *
 *
 * This component creates plans for material management constraints
 */
@BusinessInterface(entityClass = MaterialRule.class)
public interface IMaterialManager extends IBusinessLogic<MaterialRule> {
	/**
	 * Allocate material in all modes available to satisfy a material constraint
	 * 
	 * @param materialConstraint
	 * @param context
	 * @return
	 */
	List<MaterialManagementSettings> solveMaterialConstraint(MaterialRule materialConstraint, TimeSegmentRequirement SetupTimeRange, TimeSegmentRequirement WorkTimeRange, ApsData context);

}