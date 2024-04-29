package com.openi40.scheduler.outputchannels.dataexporters.factories.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openi40.scheduler.mapper.IMapperFactory;
import com.openi40.scheduler.model.aps.ApsSchedulingSet;
import com.openi40.scheduler.model.dao.IApsSchedulingSetDao;
import com.openi40.scheduler.model.dao.IPeggingDao;
import com.openi40.scheduler.model.dao.ITaskDao;
import com.openi40.scheduler.model.dao.IWorkOrderDao;
import com.openi40.scheduler.model.orders.Pegging;
import com.openi40.scheduler.model.orders.WorkOrder;
import com.openi40.scheduler.model.tasks.Task;
import com.openi40.scheduler.output.model.ApsSchedulingSetOutputDto;
import com.openi40.scheduler.output.model.orders.PeggingOutputDto;
import com.openi40.scheduler.output.model.orders.WorkOrderOutputDto;
import com.openi40.scheduler.output.model.tasks.TaskOutputDto;
import com.openi40.scheduler.outputchannels.dataexporters.AbstractDataExporterFactory;
import com.openi40.scheduler.outputchannels.dataexporters.factories.IApsSchedulingSetOutputDataExporterFactory;
import com.openi40.scheduler.outputchannels.dataexporters.factories.IPeggingOutputDataExporterFactory;
import com.openi40.scheduler.outputchannels.dataexporters.factories.ITaskOutputDataExporterFactory;
import com.openi40.scheduler.outputchannels.dataexporters.factories.IWorkOrderOutputDataExporterFactory;
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
@Service
public class ApsSchedulingSetOutputDataExporterFactoryImpl
		extends AbstractDataExporterFactory<ApsSchedulingSet, ApsSchedulingSetOutputDto, IApsSchedulingSetDao>
		implements IApsSchedulingSetOutputDataExporterFactory {
	@Autowired
	public ApsSchedulingSetOutputDataExporterFactoryImpl(IApsSchedulingSetDao dao, IMapperFactory mapperFactory) {
		super(ApsSchedulingSet.class, ApsSchedulingSetOutputDto.class, dao, mapperFactory);

	}

}
