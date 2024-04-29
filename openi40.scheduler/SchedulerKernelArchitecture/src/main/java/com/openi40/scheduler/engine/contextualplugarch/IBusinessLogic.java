package com.openi40.scheduler.engine.contextualplugarch;

import com.openi40.scheduler.common.aps.IApsObject;

/**
 * 
 * This code is part of the OpenI40 open source advanced production scheduler
 * platform suite, have look to its licencing options. Web site:
 * http://openi40.org/ Github: https://github.com/openi40/OpenI40Platform We
 * hope you enjoy implementing new amazing projects with it.
 * 
 * @author Paolo Zavalloni  architectures@openi40.org
 *
 *
 *         Main business logic algorithm component interface. Every business
 *         component has a reference to the domain object type wich is thought
 *         to interact with. The creation of IBusinessComponent('s) is handled
 *         due to a IBusinessLogicFactory wich is the entry point for component
 *         architecture and component customization.
 * 
 *         <typeparam name="BusinessDataType"></typeparam>
 */
public interface IBusinessLogic<BusinessDataType extends IApsObject> extends IBaseBusinessLogic {

	/**
	 * Enum rappresenting the factory creator for this component
	 * 
	 * @return
	 */
	String getImplementationKey();

	void setImplementationKey(String value);

	String getIdBusinessComponent();

}