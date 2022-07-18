package com.openi40.scheduler.outputchannels.dataexporters;

import com.openi40.scheduler.mapper.MapperException;
import com.openi40.scheduler.model.aps.ApsData;
import com.openi40.scheduler.model.dao.DataModelDaoException;
import com.openi40.scheduler.outputchannels.streamoutputs.IOutputDataConsumerFactory;
import com.openi40.scheduler.outputchannels.streamoutputs.OutputDataStreamException;
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
public interface IDataExporterAgent {
	public void doSync(ApsData context, IOutputDataConsumerFactory streamFactory,
			IDataExporterFactoryRepository diFactoryRepository)
			throws DataModelDaoException, MapperException, OutputDataStreamException;
}
