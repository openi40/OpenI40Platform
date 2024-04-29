package com.openi40.scheduler.engine.contextualplugarch;

import com.openi40.scheduler.common.aps.IApsObject;
import com.openi40.scheduler.common.aps.IEnvironment;
import com.openi40.scheduler.common.aps.IOperationActuatorFactory;

/**
 * 
 * This code is part of the OpenI40 open source advanced production scheduler
 * platform suite, have look to its licencing options. Web site:
 * http://openi40.org/ Github: https://github.com/openi40/OpenI40Platform We
 * hope you enjoy implementing new amazing projects with it.
 * 
 * @author Paolo Zavalloni  architectures@openi40.org 
 * Contextual general factory for all  IBusinessComponent business logic implementations
 * 
 *
 *
 */

public interface IContextualBusinessLogicFactory extends IOperationActuatorFactory {

	public <BusinessComponentType extends IBusinessLogic<BusinessDataType>, BusinessFactoryType extends IBusinessLogicFactory<BusinessComponentType, BusinessDataType>, BusinessDataType extends IApsObject> IBusinessLogicImplementationChooser<BusinessComponentType, BusinessFactoryType, BusinessDataType> getFactoryRepository(
			Class<BusinessComponentType> businessInterfaceType);

	public <BusinessComponentType extends IBusinessLogic<BusinessDataType>, BusinessDataType extends IApsObject> BusinessComponentType create(
			Class<BusinessComponentType> businessInterfaceType, BusinessDataType mainEntityParameter,
			IEnvironment environment);

	public <BusinessComponentType extends IBusinessLogic<BusinessDataType>, BusinessDataType extends IApsObject> BusinessComponentType create(
			Class<BusinessComponentType> businessInterfaceType, String implementationKey,
			BusinessDataType mainEntityParameter, IEnvironment environment);

}
