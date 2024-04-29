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
package com.openi40.generical.dbintegration.utils;

import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.openi40.generical.dbintegration.meta.configuration.EntityMetaData;
import com.openi40.generical.dbintegration.meta.configuration.IntegrationMetaDataConfiguration;

public class MetaDataUtils {
	static Logger LOGGER = LoggerFactory.getLogger(MetaDataUtils.class);

	public static String getTableName(String entityName, IntegrationMetaDataConfiguration metaData) {
		if (metaData != null && metaData.getImportedEntities() != null) {
			List<EntityMetaData> entities = metaData.getImportedEntities();
			for (Iterator iterator = entities.iterator(); iterator.hasNext();) {
				EntityMetaData entityMetaData = (EntityMetaData) iterator.next();
				if (entityMetaData.getEntityName() != null
						&& entityMetaData.getEntityName().trim().equals(entityName.trim()))
					return entityMetaData.getTableName();
			}
		}
		String status = "Referred entity with name:" + entityName
				+ " in entities transfer configuration does not have an entry in dbmetadata configuration";
		LOGGER.error(status);
		throw new IllegalStateException(status);
	}

}
