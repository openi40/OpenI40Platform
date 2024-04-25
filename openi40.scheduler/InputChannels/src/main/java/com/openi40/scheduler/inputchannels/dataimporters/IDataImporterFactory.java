package com.openi40.scheduler.inputchannels.dataimporters;

import java.util.function.Consumer;

import com.openi40.scheduler.common.aps.IApsObject;
import com.openi40.scheduler.input.model.InputDto;
import com.openi40.scheduler.mapper.MapperException;
import com.openi40.scheduler.model.aps.ApsData;
import com.openi40.scheduler.model.dao.DataModelDaoException;
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
public interface IDataImporterFactory<ImportedData extends InputDto,MatchingData extends IApsObject> {
	public IDataImporterConsumer<ImportedData> create(ApsData data) throws DataModelDaoException, MapperException;
	public Class<ImportedData> getManagedType();
	public Class<MatchingData> getMappedType();
	
}
