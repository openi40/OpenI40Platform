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
package com.openi40.generical.dbintegration.services;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openi40.generical.dbintegration.configuration.EntityIntegrationConfig;

@Service
public class EntityImporterFactory {
	List<IEntityImporter> importers = null;

	public EntityImporterFactory(@Autowired(required = true) List<IEntityImporter> importers) {
		this.importers = importers;
	}

	public <T extends EntityIntegrationConfig> IEntityImporter<T> getImplementationFor(T configType) {
		for (Iterator iterator = importers.iterator(); iterator.hasNext();) {
			IEntityImporter iEntityImporter = (IEntityImporter) iterator.next();
			if (iEntityImporter.getManagedConfigurationType().isAssignableFrom(configType.getClass())
					&& iEntityImporter.isSupportsConfiguration(configType))
				return iEntityImporter;
		}
		throw new RuntimeException("Cannot found importer for configuration type:" + configType.getClass().getName());
	}

}
