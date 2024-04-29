package com.openi40.scheduler.engine.contextualplugarch;

import org.springframework.beans.factory.annotation.Autowired;

import com.openi40.scheduler.common.aps.IApsObject;

/**
 * 
 * This code is part of the OpenI40 open source advanced production scheduler
 * platform suite, have look to its licencing options.
 * Web site: http://openi40.org/  
 * Github: https://github.com/openi40/OpenI40Platform
 * We hope you enjoy implementing new amazing projects with it.
 * @author Paolo Zavalloni  architectures@openi40.org
 *
 * <typeparam name="EntityObjectType"></typeparam>
 */
public abstract class BusinessLogic<EntityObjectType extends IApsObject> implements IBusinessLogic<EntityObjectType> {
	@Autowired
	protected IContextualBusinessLogicFactory componentsFactory;
	@Autowired
	protected IBusinessModelFactory businessModelFactory;
	private String implementationKey = null;

	public final String getImplementationKey() {
		return implementationKey;
	}

	public final void setImplementationKey(String value) {
		this.implementationKey = value;
	}

	private String IdBusinessComponent;

	public final String getIdBusinessComponent() {
		return IdBusinessComponent;
	}

	public BusinessLogic() {
		IdBusinessComponent = this.getClass().toString();
	}

}