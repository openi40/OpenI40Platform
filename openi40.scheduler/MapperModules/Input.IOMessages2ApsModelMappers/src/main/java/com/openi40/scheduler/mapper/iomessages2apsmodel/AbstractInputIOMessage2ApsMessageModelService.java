package com.openi40.scheduler.mapper.iomessages2apsmodel;

import java.util.Map;

import org.springframework.beans.factory.config.AutowireCapableBeanFactory;

import com.openi40.scheduler.engine.contextualplugarch.BusinessModelFactoryException;
import com.openi40.scheduler.engine.contextualplugarch.IBusinessModelFactory;
import com.openi40.scheduler.iomessages.AbstractBaseIOMessage;
import com.openi40.scheduler.mapper.IEntitiesFactory;
import com.openi40.scheduler.mapper.MapperException;
import com.openi40.scheduler.mapper.TypeMap;
import com.openi40.scheduler.model.aps.ApsData;
import com.openi40.scheduler.model.messages.AbstractBaseMessage;

public abstract class AbstractInputIOMessage2ApsMessageModelService<IOMsgType extends AbstractBaseIOMessage, ApsMessageType extends AbstractBaseMessage>
		extends com.openi40.scheduler.mapper.AbstractReflectorMapper<IOMsgType, ApsMessageType> {
	IBusinessModelFactory businessModelFactory = null;

	public AbstractInputIOMessage2ApsMessageModelService(AutowireCapableBeanFactory beanFactory,
			IBusinessModelFactory businessModelFactory, Class<IOMsgType> originalType,
			Class<ApsMessageType> targetType, TypeMap typesMap) {
		super(beanFactory, originalType, targetType,typesMap);
		this.businessModelFactory = businessModelFactory;
	}

	@Override
	public ApsMessageType mapInstance(IOMsgType object, Class<ApsMessageType> targetType,
			IEntitiesFactory entityFactory, Map environment, boolean recursive) throws MapperException {

		if (!environment.containsKey("context"))
			throw new MapperException(
					"The actual apsData must be put in environment map as \"context\" entry to let this mapper work");
		ApsData context = (ApsData) environment.get("context");
		ApsMessageType apsObject = createObject(context, targetType);
		this.copyValue(object, apsObject, entityFactory, environment, recursive);
		apsObject.setMessageTimestamp(object.getMessageTimestamp());
		apsObject.setModifiedTimestamp(object.getMessageTimestamp());
		return apsObject;
	}

	protected ApsMessageType createObject(ApsData context, Class<ApsMessageType> targetType) throws MapperException {
		try {

			return businessModelFactory.create(targetType, context);
		} catch (BusinessModelFactoryException e) {
			throw new MapperException(
					"Cannot create a object of type " + targetType.getName() + " cannot reach the constructor", e);
		}

	}

}
