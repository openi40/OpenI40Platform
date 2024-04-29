package com.openi40.scheduler.mapper.inputmodel2apsmodel;

import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.config.AutowireCapableBeanFactory;

import com.openi40.scheduler.common.aps.IApsObject;
import com.openi40.scheduler.engine.contextualplugarch.BusinessModelFactoryException;
import com.openi40.scheduler.engine.contextualplugarch.IBusinessModelFactory;
import com.openi40.scheduler.input.model.InputDto;
import com.openi40.scheduler.mapper.AbstractReflectorMapper;
import com.openi40.scheduler.mapper.IEntitiesFactory;
import com.openi40.scheduler.mapper.IMapper;
import com.openi40.scheduler.mapper.MapperException;
import com.openi40.scheduler.model.aps.ApsData;
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
public class AbstractInputModel2ApsModelService<InputType extends InputDto, ApsType extends IApsObject>
		extends AbstractReflectorMapper<InputType, ApsType> implements IMapper<InputType, ApsType> {
	IBusinessModelFactory businessModelFactory = null;

	public AbstractInputModel2ApsModelService(Class<InputType> inputType, Class<ApsType> apsType,
			AutowireCapableBeanFactory beanFactory, IBusinessModelFactory businessModelFactory) {
		super(beanFactory, inputType, apsType, InputModel2ApsModelConfiguration.typeMap);
		this.businessModelFactory = businessModelFactory;
	}

	@Override
	public ApsType mapInstance(InputType object, Class<ApsType> targetType, IEntitiesFactory entityFactory,
			Map environment, boolean recursive) throws MapperException {
		if (!environment.containsKey("context"))
			throw new MapperException(
					"The actual apsData must be put in environment map as \"context\" entry to let this mapper work");
		ApsData context = (ApsData) environment.get("context");
		ApsType apsObject = createObject(context, targetType);
		this.copyValue(object, apsObject, entityFactory, environment, recursive);
		apsObject.setModifiedTimestamp(object.getModifiedTimestamp());
		return apsObject;
	}

	protected ApsType createObject(ApsData context, Class<ApsType> targetType) throws MapperException {
		try {

			return businessModelFactory.create(targetType, context);
		} catch (BusinessModelFactoryException e) {
			throw new MapperException(
					"Cannot create a object of type " + targetType.getName() + " cannot reach the constructor", e);
		}

	}

	@Override
	public void copyValue(InputType originalObject, ApsType targetObject, IEntitiesFactory entityFactory,
			Map environment, boolean recursive) throws MapperException {
		super.copyValue(originalObject, targetObject, entityFactory, environment, recursive);
		Map<String, Object> originalMap = originalObject.getAttributesMap();
		for (Entry<String, Object> entry : originalMap.entrySet()) {
			targetObject.setAttribute(entry.getKey(), entry.getValue());
		}
	}

}