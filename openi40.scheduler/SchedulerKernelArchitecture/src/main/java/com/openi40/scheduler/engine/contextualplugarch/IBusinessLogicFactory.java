package com.openi40.scheduler.engine.contextualplugarch;

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
 *Business component factory interface, this is the entry point for component architecture and component customizations.
 *The architecture of this factory is thought to be reverse controlled interrogating the factory with the method IsCanManage
 *if it is the right factory for an entity object.
 *If it returns true, this factory is chosen to create the new business component providing special business logic.
 *This mechanism uses a context rappresentation expressed in ISchedulingEnvironmentNode to enable this factory
 *to return true with IsCanManage method on special data instances and configuration.
 *This is the main feature around wich the software can be customized enhancing its behavior operating only in single context of data instantiation
 *or doing override of the standard behavior.
 * 
 * <typeparam name="BusinessComponentType"></typeparam>
 * <typeparam name="BusinessDataType"></typeparam>
*/
public interface IBusinessLogicFactory<BusinessComponentType extends IBusinessLogic<BusinessDataType>, BusinessDataType extends IApsObject>
{

	/** 
	 This method creates a new IBusinessComponent with handling responsability on passed entityObject for the specific environment context
	 @param entityObject 
	 @param environment 
	 @return
	*/
	 BusinessComponentType create(BusinessDataType entityObject, IEnvironment environment);

	/** 
	 This method returns true when this factory is the right one to handle specific entityObject instance state
	 and specific environment operating context.
	 Default implementation factories must return true. 
	 @param entityObject 
	 @param environment 
	 @return
	*/
	 boolean isCanManage(BusinessDataType entityObject, IEnvironment environment);

	/** 
	 Enumerator value with unique identification of the couple {IBusinssComponent interface type,enum value}
	 @return
	*/
	 String getImplementationKey();

	/** 
	 Returns the type of IBusinessComponent that this factory will return (or its parent class if returning more than one subclasses).
	 @return
	*/
	 java.lang.Class getBusinessLogicType();

}