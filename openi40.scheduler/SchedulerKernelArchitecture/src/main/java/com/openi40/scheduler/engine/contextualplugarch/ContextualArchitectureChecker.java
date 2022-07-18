package com.openi40.scheduler.engine.contextualplugarch;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.reflections.Reflections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.openi40.scheduler.common.aps.IApsObject;
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
public class ContextualArchitectureChecker {
	final static String GENERATED_CLASSES_PACKAGE = "com.openi40.scheduler.engine.runtimesys";
	private static final Logger LOGGER = LoggerFactory.getLogger(ContextualArchitectureChecker.class);

	static class AlternativeInfo {
		Class<? extends BusinessLogic<?>> alternativeClass = null;
		AlternativeImplementation alternativeAnnotation = null;
	}

	static class BusinessInterfaceInformations {
		Class businessInterface = null;
		BusinessInterface biAnnotation = null;
		Class<? extends IApsObject> entityClass = null;
		DefaultImplementation defaultImplementation = null;
		Class<? extends BusinessLogic<?>> defaultImplementationClass = null;
		List<AlternativeInfo> alternativeImplementations = new ArrayList<AlternativeInfo>();
		Class<? extends AbstractBusinessLogicImplementationChooser<?, ?, ?>> generatedContextualFactory = null;
	}

	public ContextualArchitectureChecker() {
	}

	@PostConstruct
	public void postConstruct() {
		checkArchitecture(System.getProperties(), "com.openi40.scheduler.engine");

	}

	public void checkArchitecture(Properties initializationParameters, String... packages) {
		LOGGER.info("Begin ContextualPluginArchitectureInitializer.Initialize(...)");

		Reflections reflections = new Reflections(packages);
		Map<Class, BusinessInterfaceInformations> interfacesMap = new HashMap<>();
		Set<Class<?>> _businessInterfacetypes = reflections.getTypesAnnotatedWith(BusinessInterface.class, true);
		for (Class<?> businessIface : _businessInterfacetypes) {
			if (!businessIface.isInterface()) {
				String msg = "The annotation @BusinessInterface must be put only on interfaces but found on "
						+ businessIface.getName();
				LOGGER.error(msg);
				throw new ArchitectureException(msg);
			}
			LOGGER.info("Found BusinessInterface => " + businessIface.getName());
			BusinessInterface biAnnotation = businessIface.getAnnotation(BusinessInterface.class);
			BusinessInterfaceInformations info = new BusinessInterfaceInformations();
			info.businessInterface = businessIface;
			info.biAnnotation = biAnnotation;
			info.entityClass = biAnnotation.entityClass();
			interfacesMap.put(businessIface, info);
		}
		Set<Class<?>> _defaultImplementations = reflections.getTypesAnnotatedWith(DefaultImplementation.class);
		for (Class<?> defaultImplementation : _defaultImplementations) {
			DefaultImplementation defaultImplementationAnnotation = defaultImplementation
					.getAnnotation(DefaultImplementation.class);
			LOGGER.info("Found default implementation " + defaultImplementation.getName() + " for "
					+ defaultImplementationAnnotation.implemented().getName() + " with key=>"
					+ defaultImplementationAnnotation.key());
			if (!BusinessLogic.class.isAssignableFrom(defaultImplementation)) {
				String msg = "The default implementation  " + defaultImplementation.getName()
						+ " must extend BusinessLogic<?> class";
				LOGGER.error(msg);
				throw new ArchitectureException(msg);
			}
			if (!defaultImplementationAnnotation.implemented().isAssignableFrom(defaultImplementation)) {
				String msg = "The default implementation  " + defaultImplementation.getName()
						+ " must implement interface " + defaultImplementationAnnotation.implemented().getName();
				LOGGER.error(msg);
				throw new ArchitectureException(msg);
			}
			if (!interfacesMap.containsKey(defaultImplementationAnnotation.implemented())) {
				String msg = "The interface  " + defaultImplementationAnnotation.implemented().getName()
						+ " is not in the list of interfaces with @BusinessInterface annotations";
				LOGGER.error(msg);
				throw new ArchitectureException(msg);
			}
			BusinessInterfaceInformations biInfos = interfacesMap.get(defaultImplementationAnnotation.implemented());
			biInfos.defaultImplementation = defaultImplementationAnnotation;
			biInfos.defaultImplementationClass = (Class<? extends BusinessLogic<?>>) defaultImplementation;
		}

		Set<Class<?>> _alternativeImplementations = reflections.getTypesAnnotatedWith(AlternativeImplementation.class);
		for (Class<?> alternativeImplementation : _alternativeImplementations) {
			AlternativeImplementation alternImpl = alternativeImplementation
					.getAnnotation(AlternativeImplementation.class);
			BusinessInterfaceInformations biInfos = interfacesMap.get(alternImpl.implemented());
			LOGGER.info("Found alternative implementation " + alternativeImplementation.getName() + " for "
					+ alternImpl.implemented().getName() + " with key=>" + alternImpl.key());
			if (!BusinessLogic.class.isAssignableFrom(alternativeImplementation)) {
				String msg = "The alternative implementation  " + alternativeImplementation.getName()
						+ " must extend BusinessLogic<?> class";
				LOGGER.error(msg);
				throw new ArchitectureException(msg);
			}
			if (!alternImpl.implemented().isAssignableFrom(alternativeImplementation)) {
				String msg = "The alternative implementation  " + alternativeImplementation.getName()
						+ " must implement interface " + alternImpl.implemented().getName();
				LOGGER.error(msg);
				throw new ArchitectureException(msg);
			}
			if (!interfacesMap.containsKey(alternImpl.implemented())) {
				String msg = "The interface  " + alternImpl.implemented().getName()
						+ " is not in the list of interfaces with @BusinessInterface annotations";
				LOGGER.error(msg);
				throw new ArchitectureException(msg);
			}
			BusinessInterfaceInformations infos = interfacesMap.get(alternImpl.implemented());
			AlternativeInfo ainfos = new AlternativeInfo();
			ainfos.alternativeClass = (Class<? extends BusinessLogic<?>>) alternativeImplementation;
			ainfos.alternativeAnnotation = alternImpl;
			infos.alternativeImplementations.add(ainfos);
		}

		LOGGER.info("End ContextualPluginArchitectureInitializer.Initialize(...)");

	}
}
