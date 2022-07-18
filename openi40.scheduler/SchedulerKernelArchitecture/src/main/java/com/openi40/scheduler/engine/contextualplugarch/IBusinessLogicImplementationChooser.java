package com.openi40.scheduler.engine.contextualplugarch;

import java.util.ArrayList;

import com.openi40.scheduler.common.aps.IApsObject;
import com.openi40.scheduler.common.aps.IEnvironment;
/**
 * 
 * This code is part of the OpenI40 open source advanced production scheduler
 * platform suite, have look to its licencing options.
 * Web site: http://openi40.org/  
 * Github: https://github.com/openi40/OpenI40Platform
 * We hope you enjoy implementing new amazing projects with it.
 * @author architectures@openi40.org
 *
 * @param <BusinessComponentType>
 * @param <BusinessFactoryType>
 * @param <BusinessDataType>
 */
public interface IBusinessLogicImplementationChooser<BusinessComponentType extends IBusinessLogic<BusinessDataType>, BusinessFactoryType extends IBusinessLogicFactory<BusinessComponentType, BusinessDataType>, BusinessDataType extends IApsObject> {
	/**
	 * Creates a new Business component type interrogating if each available factory
	 * is able to manage the entityObject and environment, if no custom factory is
	 * able to handle the arguments, fallback to default factory will be performed.
	 * 
	 * @param entityObject
	 * @param environment
	 * @return
	 */
	public BusinessComponentType create(BusinessDataType entityObject, IEnvironment environment);

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
			IEnvironment environment);
	/**
	 * Returns business component returned interface type
	 * 
	 * @return
	 */
	public java.lang.Class getBusinessLogicType();
	/**
	 * Default factory property
	 */
	public IBusinessLogicFactory<BusinessComponentType, BusinessDataType> getDefaultFactory() ;

	public void setDefaultFactory(IBusinessLogicFactory<BusinessComponentType, BusinessDataType> value) ;
	/**
	 * Gets list of all enums rappresenting factories
	 * 
	 * @return
	 */
	public ArrayList<String> getImplementationKeys();
	/**
	 * @param entityObject
	 * @param environment
	 * @return
	 */

	public ArrayList<String> getImplementationKeys(BusinessDataType entityObject, IEnvironment environment);
	/**
	 * Adds a custom factory
	 * 
	 * @param factory
	 */
	public void addFactory(BusinessFactoryType factory);
	/**
	 * Removes a custom factory
	 * 
	 * @param factory
	 */
	public void removeFactory(BusinessFactoryType factory);
}
