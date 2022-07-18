package com.openi40.scheduler.mapper;

import java.util.Map;
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
public interface IMapper<OriginalType, TargetType> {
	public TargetType mapInstance(OriginalType object,Class<TargetType> targetType, IEntitiesFactory entityFactory, Map environment, boolean recursive) throws MapperException;

	public void copyValue(OriginalType originalObject, TargetType targetObject, IEntitiesFactory entityFactory, Map environment, boolean recursive)
			throws MapperException;
	
	public boolean isAbleToMap(Class<OriginalType> originalType, Class<TargetType> targetType);
	
	public Class<OriginalType> getOriginalType();

	public Class<TargetType> getTargetType();

	
}
