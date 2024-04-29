package com.openi40.scheduler.mapper;

import java.util.Map;
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
public class DefaultEntitiesFactory implements IEntitiesFactory {

	private DefaultEntitiesFactory() {

	}

	public static final DefaultEntitiesFactory Instance = new DefaultEntitiesFactory();

	@Override
	public <EntityType> EntityType create(Class<EntityType> type, Map environment) throws MapperException {

		try {
			return type.newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			throw new MapperException("Unable to create " + type.getName(), e);
		}
	}

}
