package com.openi40.scheduler.engine.contextualplugarch;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;

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
 */
public abstract class BusinessLogicFactory<BusinessComponentType extends IBusinessLogic<BusinessDataType>, BusinessDataType extends IApsObject>
		implements IBusinessLogicFactory<BusinessComponentType, BusinessDataType> {
	static Logger LOGGER = LoggerFactory.getLogger(BusinessLogicFactory.class);
	protected BusinessComponentType businessComponent = null;
	protected Class<BusinessComponentType> businessComponentType = null;
	protected Class implementationType = null;
	protected @Autowired AutowireCapableBeanFactory beanFactory;
	protected boolean mantainReference = true;
	protected String implementationKey = DefaultImplementationConstant.DEFAULT_IMPLEMENTATION;
	protected String switchingImplementationProperty = null;

	protected BusinessLogicFactory(Class<BusinessComponentType> businessComponentType, Class implementationType) {
		this(businessComponentType, implementationType, true, DefaultImplementationConstant.DEFAULT_IMPLEMENTATION);

	}

	protected BusinessLogicFactory(Class<BusinessComponentType> businessComponentType, Class implementationType,
			String implementationKey) {
		this(businessComponentType, implementationType, true, implementationKey);

	}

	protected BusinessLogicFactory(Class<BusinessComponentType> businessComponentType, Class implementationType,
			boolean mantainReference, String implementationKey) {
		this.businessComponentType = businessComponentType;
		this.implementationType = implementationType;
		this.mantainReference = mantainReference;
		if (!this.businessComponentType.isInterface()) {
			throw new IllegalStateException("The businessComponentType parameter must be an interface");
		}
		if (!IBusinessLogic.class.isAssignableFrom(this.businessComponentType)) {
			throw new IllegalStateException(
					"The businessComponentType parameter must be an interface extending IBusinessLogic");
		}
		if (!this.businessComponentType.isAssignableFrom(this.implementationType)) {
			throw new IllegalStateException("The type " + this.implementationType.getName() + " cannot be assigned to "
					+ this.businessComponentType.getName()
					+ " the implementationType is not a valid class implementing interface "
					+ this.businessComponentType.getName());
		}
		this.implementationKey = implementationKey;
	}

	@PostConstruct
	protected void initialized() {
		LOGGER.info("Initializing BusinessLogicFactory=>" + this.getClass().getSimpleName() + " for "
				+ businessComponentType.getName() + " key=\"" + implementationKey + "\" with implementation=>"
				+ this.implementationType.getName());

	}

	Method switchingMethodReference = null;

	@SuppressWarnings("unchecked")
	public BusinessComponentType create(BusinessDataType entityObject, IEnvironment environment) {
		BusinessComponentType _t = businessComponent;
		if (mantainReference && _t == null || !mantainReference) {
			int autowireMode = AutowireCapableBeanFactory.AUTOWIRE_BY_NAME | AutowireCapableBeanFactory.AUTOWIRE_BY_TYPE
					| AutowireCapableBeanFactory.AUTOWIRE_CONSTRUCTOR;
			boolean dependencyCheck = true;
			_t = (BusinessComponentType) beanFactory.createBean(implementationType, autowireMode, dependencyCheck);
			_t.setImplementationKey(getImplementationKey());

		}
		if (mantainReference) {
			businessComponent = _t;
		}
		return _t;
	}

	public java.lang.Class getBusinessLogicType() {
		return businessComponentType;
	}

	public String getImplementationKey() {
		return this.implementationKey;
	}

	public boolean isCanManage(BusinessDataType entityObject, IEnvironment environment) {
		if (this.switchingMethodReference == null && this.switchingImplementationProperty != null
				&& this.switchingImplementationProperty.trim().length() > 0) {
			try {
				this.switchingMethodReference=entityObject.getClass().getMethod("get"+this.switchingImplementationProperty);
			} catch (NoSuchMethodException | SecurityException e) {
				char _buffer[]=this.switchingImplementationProperty.toCharArray();
				_buffer[0]=Character.toUpperCase(_buffer[0]);
				String _switchProperty=new String(_buffer);
				try {
					this.switchingMethodReference=entityObject.getClass().getMethod("get"+_switchProperty);
				} catch (NoSuchMethodException | SecurityException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
		if (this.switchingMethodReference != null && this.implementationKey != null) {
			try {
				Object value = this.switchingMethodReference.invoke(entityObject);
				return (value != null && value.toString().trim().equals(this.implementationKey));
			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		return false;
	}
}