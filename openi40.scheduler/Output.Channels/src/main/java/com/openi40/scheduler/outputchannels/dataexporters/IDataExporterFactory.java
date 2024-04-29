package com.openi40.scheduler.outputchannels.dataexporters;

import java.util.function.Consumer;
import java.util.stream.Stream;

import com.openi40.scheduler.common.aps.IApsObject;
import com.openi40.scheduler.mapper.MapperException;
import com.openi40.scheduler.model.aps.ApsData;
import com.openi40.scheduler.model.dao.DataModelDaoException;
import com.openi40.scheduler.output.model.OutputDto;
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
public interface IDataExporterFactory<ApsDataType extends IApsObject, ExportedDataType extends OutputDto> {
	public Stream<ExportedDataType> create(ApsData data) throws DataModelDaoException, MapperException;

	public Class<ApsDataType> getApsDataType();

	public Class<ExportedDataType> getExportedType();
}
