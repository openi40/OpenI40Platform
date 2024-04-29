package com.openi40.scheduler.inputchannels.dataimporters;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openi40.scheduler.input.model.InputDto;
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
public class DataImporterFactoryRepositoryImpl implements IDataImporterFactoryRepository {
	List<IDataImporterFactory> factories = null;

	public DataImporterFactoryRepositoryImpl(@Autowired List<IDataImporterFactory> factories) {
		this.factories = factories;
	}

	@Override
	public <T extends InputDto> IDataImporterFactory<T, ?> getImporterFactory(Class<T> inputType) {
		IDataImporterFactory<T, ?> outFactory = null;
		if (factories != null) {
			for (IDataImporterFactory iDataImporterFactory : factories) {
				if (iDataImporterFactory.getManagedType().isAssignableFrom(inputType)) {
					outFactory = iDataImporterFactory;
				}
			}
		}
		return outFactory;
	}

}
