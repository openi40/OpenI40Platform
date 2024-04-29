package com.openi40.scheduler.engine.contextualplugarch;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.openi40.scheduler.common.aps.IApsObject;
import com.openi40.scheduler.common.aps.IEnvironment;

/**
 * 
 * This code is part of the OpenI40 open source advanced production scheduler
 * platform suite, have look to its licencing options.
 * Web site: http://openi40.org/  
 * Github: https://github.com/openi40/OpenI40Platform
 * We hope you enjoy implementing new amazing projects with it.
 * @author Paolo Zavalloni  architectures@openi40.org
 *
 *  Abstract factory repository, its instances will rappresent the collection
 * point for customizations and default implementation of all algorithms
 * @param <BusinessComponentType>
 * @param <BusinessFactoryType>
 * @param <BusinessDataType>
 */
public class AbstractBusinessLogicImplementationChooser<BusinessComponentType extends IBusinessLogic<BusinessDataType>, BusinessFactoryType extends IBusinessLogicFactory<BusinessComponentType, BusinessDataType>, BusinessDataType extends IApsObject>
		implements IBusinessLogicImplementationChooser<BusinessComponentType, BusinessFactoryType, BusinessDataType> {
	protected static Logger LOGGER = LoggerFactory.getLogger(AbstractBusinessLogicImplementationChooser.class);
	@Autowired(required = false)
	protected List<IBusinessLogicFactory<BusinessComponentType, BusinessDataType>> factories;

	protected Class<BusinessComponentType> businessComponentType = null;

	protected IBusinessLogicFactory<BusinessComponentType, BusinessDataType> defaultFactory;

	public AbstractBusinessLogicImplementationChooser(Class<BusinessComponentType> businessComponentType) {
		this.businessComponentType = businessComponentType;
	}

	public AbstractBusinessLogicImplementationChooser(Class<BusinessComponentType> businessComponentType,
			IBusinessLogicFactory<BusinessComponentType, BusinessDataType> defaultFactory) {
		this.defaultFactory = defaultFactory;
		this.businessComponentType = businessComponentType;

	}

	@PostConstruct
	protected void Initialize() {
		LOGGER.info("FactoryRepository " + getClass().getName() + " for "
				+ (businessComponentType != null ? businessComponentType.getName() : "NULL"));
	}

	/**
	 * Adds a custom factory
	 * 
	 * @param factory
	 */
	public void addFactory(BusinessFactoryType factory) {
		if (LOGGER.isDebugEnabled()) LOGGER.debug(this.getClass().toString() + ".AddFactory(factory)", "factory=" + factory.toString());
		if (!factories.contains(factory)) {
			factories.add(factory);
		}
	}

	/**
	 * Removes a custom factory
	 * 
	 * @param factory
	 */
	public void removeFactory(BusinessFactoryType factory) {
		if (LOGGER.isDebugEnabled()) LOGGER.debug(this.getClass().toString() + ".RemoveFactory(factory)", "factory=" + factory.toString());
		factories.remove(factory);
	}

	/**
	 * Default factory property
	 */
	public IBusinessLogicFactory<BusinessComponentType, BusinessDataType> getDefaultFactory() {
		return defaultFactory;
	}

	public void setDefaultFactory(IBusinessLogicFactory<BusinessComponentType, BusinessDataType> value) {
		defaultFactory = value;
	}

	/**
	 * Creates a new Business component type interrogating if each available factory
	 * is able to manage the entityObject and environment, if no custom factory is
	 * able to handle the arguments, fallback to default factory will be performed.
	 * 
	 * @param entityObject
	 * @param environment
	 * @return
	 */
	public BusinessComponentType create(BusinessDataType entityObject, IEnvironment environment) {
		if (LOGGER.isDebugEnabled()) LOGGER.debug(this.getClass() + ".Create(entityObject,environment)",
				" entityObject=" + entityObject + ", environment=" + environment);

		if (factories != null) {
			for (IBusinessLogicFactory<BusinessComponentType, BusinessDataType> factory : factories) {
				if (factory.isCanManage(entityObject, environment)) {
					if (LOGGER.isDebugEnabled()) LOGGER.debug("factory.IsCanManage(entityObject, environment)==true",
							"Return new instance using factory " + factory);
					return factory.create(entityObject, environment);
				}
			}
		}
		if (LOGGER.isDebugEnabled()) LOGGER.debug(this.getClass().toString(), "Return new instance using default factory " + defaultFactory);
		return defaultFactory.create(entityObject, environment);
	}

	/**
	 * Create new business component with specific selected factory choosed using
	 * its unique enum value.
	 * 
	 * @param enum
	 * @param entityObject
	 * @param environment
	 * @return
	 */
	public BusinessComponentType create(String implementationKey, BusinessDataType entityObject,
			IEnvironment environment) {
		if (LOGGER.isDebugEnabled()) LOGGER.debug(this.getClass() + ".Create(enumValue,entityObject,environment)",
				" enumValue=" + implementationKey + ", entityObject=" + entityObject + ", environment=" + environment);
		for (IBusinessLogicFactory<BusinessComponentType, BusinessDataType> factory : factories) {
			if (factory.getImplementationKey().equals(implementationKey)) {
				if (LOGGER.isDebugEnabled()) LOGGER.debug("Find matching enum value for factory", factory.toString());
				return factory.create(entityObject, environment);
			}
		}

		if (defaultFactory.getImplementationKey().equals(implementationKey)) {
			if (LOGGER.isDebugEnabled()) LOGGER.debug("Find matching enum value for factory", defaultFactory.toString());
			return defaultFactory.create(entityObject, environment);
		}
		throw new InvalidParameterException("Enum " + implementationKey + " is unknown");
	}

	/**
	 * Gets list of all enums rappresenting factories
	 * 
	 * @return
	 */
	public ArrayList<String> getImplementationKeys() {
		ArrayList<String> enums = new ArrayList<>();
		for (IBusinessLogicFactory<BusinessComponentType, BusinessDataType> factory : factories) {
			enums.add(factory.getImplementationKey());
		}
		enums.add(defaultFactory.getImplementationKey());
		return enums;
	}

	/**
	 * @param entityObject
	 * @param environment
	 * @return
	 */

	public ArrayList<String> getImplementationKeys(BusinessDataType entityObject, IEnvironment environment) {
		ArrayList<String> enums = new ArrayList<String>();
		for (IBusinessLogicFactory<BusinessComponentType, BusinessDataType> factory : factories) {
			if (factory.isCanManage(entityObject, environment)) {
				enums.add(factory.getImplementationKey());
			}
		}
		return enums;
	}

	/**
	 * Returns business component returned interface type
	 * 
	 * @return
	 */
	public java.lang.Class getBusinessLogicType() {

		return defaultFactory.getBusinessLogicType();
	}

}