package com.openi40.scheduler.engine.rules.equipment;

import com.openi40.scheduler.engine.contextualplugarch.BusinessInterface;
import com.openi40.scheduler.engine.rules.IRulesGenerator;
import com.openi40.scheduler.model.rules.EquipmentRule;
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
 */
@BusinessInterface(entityClass = Task.class)
public interface IEquipmentRulesGenerator extends IRulesGenerator<EquipmentRule> {

}
