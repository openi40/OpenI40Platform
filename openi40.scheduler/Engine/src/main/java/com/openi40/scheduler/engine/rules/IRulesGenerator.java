package com.openi40.scheduler.engine.rules;

import java.util.List;

import com.openi40.scheduler.engine.contextualplugarch.IBusinessLogic;
import com.openi40.scheduler.model.aps.ApsLogicOptions;
import com.openi40.scheduler.model.rules.Rule;
import com.openi40.scheduler.model.tasks.Task;
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
public interface IRulesGenerator<RuleType extends Rule> extends IBusinessLogic<Task>{
	public List<RuleType> rebuildRules(List<RuleType> actualRules,Task task,ApsLogicOptions options);
}
