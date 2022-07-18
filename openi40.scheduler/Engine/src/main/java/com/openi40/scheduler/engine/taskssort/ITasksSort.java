package com.openi40.scheduler.engine.taskssort;

import java.util.List;

import com.openi40.scheduler.engine.contextualplugarch.BusinessInterface;
import com.openi40.scheduler.engine.contextualplugarch.IBusinessLogic;
import com.openi40.scheduler.model.aps.ApsSchedulingSet;
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
 * This interface declares task ordering component interface, it is used to
 * order tasks in order to be scheduled coherently with a scheduling algorithm
 * architectures@openi40.org 
 */
@BusinessInterface(entityClass = ApsSchedulingSet.class)
public interface ITasksSort extends IBusinessLogic<ApsSchedulingSet> {

	/**
	 * @param EntityObject
	 * @return
	 */
	List<Task> sort(ApsSchedulingSet EntityObject);

}