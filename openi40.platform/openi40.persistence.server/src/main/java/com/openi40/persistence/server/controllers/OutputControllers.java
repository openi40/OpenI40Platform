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
package com.openi40.persistence.server.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.openi40.persistence.server.basecontrollers.BaseOutputController;
import com.openi40.scheduler.output.model.ApsSchedulingSetOutputDto;
import com.openi40.scheduler.output.model.orders.PeggingOutputDto;
import com.openi40.scheduler.output.model.orders.WorkOrderOutputDto;
import com.openi40.scheduler.output.model.tasks.TaskOutputDto;
import com.openi40.scheduler.output.model.tasks.TaskRelationOutputDto;
import com.openi40.scheduler.outputchannels.streamoutputs.IOutputDataConsumerFactory;

public class OutputControllers {

	@RestController
	@RequestMapping(value = "/output/ApsSchedulingSet")
	static public class ApsSchedulingSetOutputController extends BaseOutputController<ApsSchedulingSetOutputDto> {
		@Autowired
		public ApsSchedulingSetOutputController(
				@Qualifier("persistenceOutputDataConsumerFactory") List<IOutputDataConsumerFactory> outputDataConsumerFactories) {
			super(ApsSchedulingSetOutputDto.class, outputDataConsumerFactories);
		}
	}

	@RestController
	@RequestMapping(value = "/output/Task")
	static public class TaskOutputController extends BaseOutputController<TaskOutputDto> {
		@Autowired
		public TaskOutputController(
				@Qualifier("persistenceOutputDataConsumerFactory") List<IOutputDataConsumerFactory> outputDataConsumerFactories) {
			super(TaskOutputDto.class, outputDataConsumerFactories);
		}
	}

	@RestController
	@RequestMapping(value = "/output/TaskRelation")
	static public class TaskRelationOutputController extends BaseOutputController<TaskRelationOutputDto> {
		@Autowired
		public TaskRelationOutputController(
				@Qualifier("persistenceOutputDataConsumerFactory") List<IOutputDataConsumerFactory> outputDataConsumerFactories) {
			super(TaskRelationOutputDto.class, outputDataConsumerFactories);
		}
	}

	@RestController
	@RequestMapping(value = "/output/Pegging")
	static public class PeggingOutputController extends BaseOutputController<PeggingOutputDto> {
		@Autowired
		public PeggingOutputController(
				@Qualifier("persistenceOutputDataConsumerFactory") List<IOutputDataConsumerFactory> outputDataConsumerFactories) {
			super(PeggingOutputDto.class, outputDataConsumerFactories);
		}
	}

	@RestController
	@RequestMapping(value = "/output/WorkOrder")
	static public class WorkOrderOutputController extends BaseOutputController<WorkOrderOutputDto> {
		@Autowired
		public WorkOrderOutputController(
				@Qualifier("persistenceOutputDataConsumerFactory") List<IOutputDataConsumerFactory> outputDataConsumerFactories) {
			super(WorkOrderOutputDto.class, outputDataConsumerFactories);
		}
	}

}
