package com.openi40.scheduler.outputchannels.dataexporters;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.openi40.scheduler.output.model.ApsSchedulingSetOutputDto;
import com.openi40.scheduler.output.model.OutputDto;
import com.openi40.scheduler.output.model.orders.PeggingOutputDto;
import com.openi40.scheduler.output.model.orders.WorkOrderOutputDto;
import com.openi40.scheduler.output.model.tasks.TaskOutputDto;
import com.openi40.scheduler.output.model.tasks.TaskRelationOutputDto;
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
@Service
public class ExportedClassListProviderImpl implements IExportedClassListProvider {
	static final Class defaultClasses[] = { WorkOrderOutputDto.class, TaskOutputDto.class, TaskRelationOutputDto.class,
			PeggingOutputDto.class,ApsSchedulingSetOutputDto.class };

	public ExportedClassListProviderImpl() {

	}

	@Override
	public List<Class<? extends OutputDto>> getClassesList() {
		List<Class<? extends OutputDto>> outList = new ArrayList<>();
		for (Class class1 : defaultClasses) {
			outList.add(class1);
		}
		return outList;
	}

}
