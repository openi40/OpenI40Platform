package com.openi40.scheduler.outputchannels.dataexporters;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
@Service
public class DataExporterFactoryRepositoryImpl implements IDataExporterFactoryRepository {
	List<IDataExporterFactory<?, ? extends OutputDto>> factories = null;
	Map<Class<? extends OutputDto>, IDataExporterFactory<?, ? extends OutputDto>> factoriesMap = new HashMap<>();

	public DataExporterFactoryRepositoryImpl(@Autowired(required = false) List<IDataExporterFactory<?, ?>> factories) {
		this.factories = factories;
		if (this.factories != null) {
			this.factories.forEach((entry) -> {
				factoriesMap.put(entry.getExportedType(), entry);
			});
		}
	}

	@Override
	public <T extends OutputDto> IDataExporterFactory<?, T> getExporterFactory(Class<T> outputType) {
		if (!factoriesMap.containsKey(outputType))
			throw new IllegalStateException("Unknown dataExporter for dataType=" + outputType);
		return (IDataExporterFactory<?, T>) factoriesMap.get(outputType);
	}

}
