package com.openi40.scheduler.engine.contextualplugarch;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.openi40.scheduler.common.aps.IApsObject;
import com.openi40.scheduler.common.aps.IEnvironment;
import com.openi40.scheduler.common.aps.IOperation;
import com.openi40.scheduler.common.aps.IOperationActuator;
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
@Service
@Lazy
public class ContextualBusinessLogicFactoryImpl implements IContextualBusinessLogicFactory {
	static Logger LOGGER = LoggerFactory.getLogger(ContextualBusinessLogicFactoryImpl.class);
	
	AutowireCapableBeanFactory beanFactory;

	class LazyLoadingComponents {
		@Autowired
		List<IBusinessLogicImplementationChooser<?, ?, ?>> factoryRepositories;

		void Initialize() {
			if (factoryRepositories != null) {
				for (Iterator iterator = factoryRepositories.iterator(); iterator.hasNext();) {
					IBusinessLogicImplementationChooser<?, ?, ?> iBusinessLogicFactoryRepository = (IBusinessLogicImplementationChooser<?, ?, ?>) iterator
							.next();
					if (LOGGER.isDebugEnabled()) {
						LOGGER.debug("Associating to " + iBusinessLogicFactoryRepository.getBusinessLogicType()
								+ " factory repository " + iBusinessLogicFactoryRepository.getClass().getName());
					}
					repositoriesMap.put(iBusinessLogicFactoryRepository.getBusinessLogicType(),
							iBusinessLogicFactoryRepository);
				}
			}
		}

		Map<Class, IBusinessLogicImplementationChooser<?, ?, ?>> repositoriesMap = new HashMap<Class, IBusinessLogicImplementationChooser<?, ?, ?>>();
	}

	public ContextualBusinessLogicFactoryImpl(
			@Autowired List<IOperationActuator<?>> reversibleOperationActuators,
			@Autowired AutowireCapableBeanFactory beanFactory) {
		this.reversibleOperationActuators=reversibleOperationActuators;
		this.beanFactory=beanFactory;

	}

	LazyLoadingComponents lazy = null;

	@Override
	public <BusinessComponentType extends IBusinessLogic<BusinessDataType>, BusinessFactoryType extends IBusinessLogicFactory<BusinessComponentType, BusinessDataType>, BusinessDataType extends IApsObject> IBusinessLogicImplementationChooser<BusinessComponentType, BusinessFactoryType, BusinessDataType> getFactoryRepository(
			Class<BusinessComponentType> businessInterfaceType) {

		if (lazy == null) {
			lazy = new LazyLoadingComponents();
			beanFactory.autowireBean(lazy);
			lazy.Initialize();
		}
		IBusinessLogicImplementationChooser<BusinessComponentType, ?, BusinessDataType> iBusinessLogicFactoryRepository = (IBusinessLogicImplementationChooser<BusinessComponentType, ?, BusinessDataType>) lazy.repositoriesMap
				.get(businessInterfaceType);
		IBusinessLogicImplementationChooser<BusinessComponentType, BusinessFactoryType, BusinessDataType> retValue = (IBusinessLogicImplementationChooser<BusinessComponentType, BusinessFactoryType, BusinessDataType>) iBusinessLogicFactoryRepository;

		if (retValue == null) {
			throw new IllegalStateException("Not found repository of factories for " + businessInterfaceType);
		}
		return retValue;
	}

	@Override
	public <BusinessComponentType extends IBusinessLogic<BusinessDataType>, BusinessDataType extends IApsObject> BusinessComponentType create(
			Class<BusinessComponentType> businessInterfaceType, BusinessDataType mainEntityParameter,
			IEnvironment environment) {

		return getFactoryRepository(businessInterfaceType).create(mainEntityParameter, environment);
	}

	@Override
	public <BusinessComponentType extends IBusinessLogic<BusinessDataType>, BusinessDataType extends IApsObject> BusinessComponentType create(
			Class<BusinessComponentType> businessInterfaceType, String implementationKey,
			BusinessDataType mainEntityParameter, IEnvironment environment) {

		return getFactoryRepository(businessInterfaceType).create(implementationKey, mainEntityParameter, environment);
	}

	protected List<IOperationActuator<?>> reversibleOperationActuators;

	@Override
	public <IReversibleOperationType extends IOperation> IOperationActuator<IReversibleOperationType> create(
			Class<IReversibleOperationType> operation) {
		if (reversibleOperationActuators != null) {
			for (IOperationActuator<?> iReversibleOperationActuator : reversibleOperationActuators) {
				if (iReversibleOperationActuator.getRootOperationType().isAssignableFrom(operation)) {
					return (IOperationActuator<IReversibleOperationType>) iReversibleOperationActuator;
				}
			}
		}
		throw new ArchitectureException("Not found IReversibleOperationActuator for type " + operation.getName());
	}

}
