package com.openi40.scheduler.mapper;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.TypeVariable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.util.TypeUtils;

import com.openi40.scheduler.mapper.TypeMap.TypesCouple;
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
public abstract class AbstractReflectorMapper<OriginalType, TargetType> implements IMapper<OriginalType, TargetType> {
	static Logger LOGGER = LoggerFactory.getLogger(AbstractReflectorMapper.class);
	AutowireCapableBeanFactory beanFactory = null;
	Class<OriginalType> originalType = null;
	Class<TargetType> targetType = null;
	TypeMap typeMap = new TypeMap();

	protected AbstractReflectorMapper(AutowireCapableBeanFactory beanFactory, Class<OriginalType> originalType, Class<TargetType> targetType) {
		this.beanFactory = beanFactory;
		this.originalType = originalType;
		this.targetType = targetType;
	}

	protected AbstractReflectorMapper(AutowireCapableBeanFactory beanFactory, Class<OriginalType> originalType, Class<TargetType> targetType, TypeMap typesMap) {
		this.beanFactory = beanFactory;
		this.originalType = originalType;
		this.targetType = targetType;
		this.typeMap = typesMap;

	}

	protected class LazyMapClient {
		@Autowired
		public IMapperFactory mapperFactory;
	}

	@Override
	public TargetType mapInstance(OriginalType object, Class<TargetType> targetType, IEntitiesFactory entityFactory, Map environment, boolean recursive) throws MapperException {
		TargetType instance = null;
		if (entityFactory == null)
			entityFactory = DefaultEntitiesFactory.Instance;
		instance = entityFactory.create(targetType, environment);
		copyValue(object, instance, entityFactory, environment, recursive);
		return instance;
	}

	interface DataTreater {
		Object treat(Object object, Class objectTargetType, IEntitiesFactory entityFactory, Map environment) throws MapperException;
	}

	class SetterGetterCouple {
		Method getter = null;
		Method setter = null;
		boolean container = false;
		boolean enumToString = false;
		boolean stringToEnum = false;
		DataTreater treater = new DataTreater() {

			@Override
			public Object treat(Object object, Class objectTargetType, IEntitiesFactory entityFactory, Map environment) {
				if (enumToString) {
					return object != null ? object.toString() : null;
				} else if (stringToEnum) {
					Object _constants[] = objectTargetType.getEnumConstants();
					if (_constants != null) {
						for (Object object2 : _constants) {
							if (object2.toString().equals(object))
								return object2;
						}
					}
					return null;
				} else
					return object;
			}
		};
	}

	protected HashMap<Class, HashMap<Class, List<SetterGetterCouple>>> reflectionCache = new HashMap<>();
	private LazyMapClient lazyMapClient = null;

	private LazyMapClient getLazyMapClient() {
		if (lazyMapClient == null) {
			LazyMapClient lmc = new LazyMapClient();
			beanFactory.autowireBean(lmc);
			lazyMapClient = lmc;
		}
		return lazyMapClient;
	}

	protected class MapperDataTreater implements DataTreater {
		Class targetType = null;
		IMapper mapper = null;
		boolean recursive = false;

		MapperDataTreater(Class targetType, IMapper mapper, boolean recursive) {
			this.targetType = targetType;
			this.mapper = mapper;
			this.recursive = recursive;
		}

		@Override
		public Object treat(Object object, Class objectTargetType, IEntitiesFactory entityFactory, Map environment) throws MapperException {
			if (object != null)
				return mapper.mapInstance(object, objectTargetType, entityFactory, environment, recursive);
			return null;
		}
	};

	protected class CollectionDataTreater implements DataTreater {
		TypesCouple collectionCouple = null;

		CollectionDataTreater(TypesCouple collectionCouple) {
			this.collectionCouple = collectionCouple;
		}

		@Override
		public Object treat(Object object, Class objectTargetType, IEntitiesFactory entityFactory, Map environment) throws MapperException {
			Object outValue = null;
			if (object != null) {
				IMapper mapper = getLazyMapClient().mapperFactory.createMapper(collectionCouple.fromType, collectionCouple.toType);
				outValue = mapper.mapInstance(object, collectionCouple.toType, entityFactory, environment, true);
			}
			return outValue;
		}

	}

	protected TypeVariable getContainedType(Class type) {
		TypeVariable[] tp = type.getTypeParameters();
		if (tp != null && tp.length > 0)
			return tp[0];
		return getContainedType(type.getSuperclass());
	}

	protected IMapperFactory getMapperFactory() {
		return getLazyMapClient().mapperFactory;
	}

	protected List<String> getOriginSkippedGetMethods() {
		return new ArrayList<>();
	}

	protected List<SetterGetterCouple> inspectAssignations(Class originalType, Class targetType) throws MapperException {
		List<SetterGetterCouple> outAssignations = new ArrayList<SetterGetterCouple>();
		Method originalMethods[] = originalType.getMethods();
		Method targetMethods[] = targetType.getMethods();
		HashMap<String, SetterGetterCouple> propertiesMappings = new HashMap<>();
		List<String> skippedGetMethods = getOriginSkippedGetMethods();
		for (Method method : originalMethods) {
			int parameterCount = method.getParameterCount();
			String methodName = method.getName();

			boolean isGetter = Modifier.isPublic(method.getModifiers()) && parameterCount == 0 && (methodName.startsWith("get") || (methodName.startsWith("is") && method.getReturnType().isPrimitive() && method.getReturnType().equals(boolean.class)));
			if (isGetter) {
				if (skippedGetMethods.contains(methodName))
					continue;
				String propertyName = method.getName().startsWith("get") ? method.getName().substring("get".length()) : method.getName().substring("is".length());
				Class type = method.getReturnType();

				List<Method> matchingTargetMethods = new ArrayList<Method>();
				for (Method targetMethod : targetMethods) {
					if (Modifier.isPublic(targetMethod.getModifiers()) && targetMethod.getName().equals("set" + propertyName) && targetMethod.getParameterCount() == 1) {
						matchingTargetMethods.add(targetMethod);
					}
				}

				if (type.getName().startsWith("java.lang") || type.getName().startsWith("java.time") || Date.class.isAssignableFrom(type) || (type.getName().indexOf(".") < 0) || Enum.class.isAssignableFrom(type)) {
					Method selectedTargetMethod = null;
					for (Method targetMethod : matchingTargetMethods) {
						if (targetMethod.getParameters()[0].getType().isAssignableFrom(type) || (Enum.class.isAssignableFrom(type) && targetMethod.getParameters()[0].getType().equals(String.class))
								|| (Enum.class.isAssignableFrom(targetMethod.getParameters()[0].getType()) && type.equals(String.class)))
							selectedTargetMethod = targetMethod;
					}
					if (selectedTargetMethod != null) {
						SetterGetterCouple couple = new SetterGetterCouple();
						couple.getter = method;
						couple.setter = selectedTargetMethod;
						couple.enumToString = (Enum.class.isAssignableFrom(type) && selectedTargetMethod.getParameters()[0].getType().equals(String.class));
						couple.stringToEnum = (Enum.class.isAssignableFrom(selectedTargetMethod.getParameters()[0].getType()) && type.equals(String.class));
						outAssignations.add(couple);
					}
				} else if (Collection.class.isAssignableFrom(type)) {

					Method selectedMethod = null;
					for (Method actualMethod : matchingTargetMethods) {
						if (Collection.class.isAssignableFrom(actualMethod.getParameterTypes()[0])) {
							selectedMethod = actualMethod;
						}
					}
					if (selectedMethod != null) {
						TypesCouple collectionCouple = typeMap.get(originalType, targetType, propertyName);
						if (collectionCouple != null) {
							SetterGetterCouple setterGetter = new SetterGetterCouple();
							setterGetter.getter = method;
							setterGetter.setter = selectedMethod;
							setterGetter.container = true;
							setterGetter.treater = new CollectionDataTreater(collectionCouple);
							outAssignations.add(setterGetter);
						}
					}
				} else {
					if (!type.isInterface() && !Iterable.class.isAssignableFrom(type)) {
						LazyMapClient lazyMapClnt = getLazyMapClient();
						for (Method potentialMatch : matchingTargetMethods) {
							try {
								IMapper mapper = lazyMapClnt.mapperFactory.createMapper(type, potentialMatch.getParameters()[0].getType());
								SetterGetterCouple couple = new SetterGetterCouple();
								couple.getter = method;
								couple.setter = potentialMatch;
								outAssignations.add(couple);
								couple.treater = new MapperDataTreater(potentialMatch.getParameters()[0].getType(), mapper, true);
								break;
							} catch (MapperException exception) {
							}
						}
					}
				}
			}
		}
		return outAssignations;
	}

	@Override
	public void copyValue(OriginalType originalObject, TargetType targetObject, IEntitiesFactory entityFactory, Map environment, boolean recursive) throws MapperException {
		
		Class originalType = originalObject.getClass(), targetType = targetObject.getClass();
		if (!reflectionCache.containsKey(originalType)) {
			reflectionCache.put(originalType, new HashMap<>());
		}
		if (!reflectionCache.get(originalType).containsKey(targetType)) {
			reflectionCache.get(originalType).put(targetType, inspectAssignations(originalType, targetType));
		}
		if (entityFactory==null) {
			entityFactory=DefaultEntitiesFactory.Instance;
		}
		List<SetterGetterCouple> assignations = reflectionCache.get(originalType).get(targetType);
		for (SetterGetterCouple sgc : assignations) {
			Object value = null;
			try {
				value = sgc.getter.invoke(originalObject);
				if (value != null) {
					if (!sgc.container) {
						value = sgc.treater.treat(value, sgc.setter.getParameterTypes()[0], entityFactory, environment);
						sgc.setter.invoke(targetObject, value);
					} else if (value instanceof Collection && sgc.container) {
						Collection collection = (Collection) value;
						Class _targetType = sgc.setter.getParameterTypes()[0];
						Collection outCollection = null;
						if (_targetType.isInterface()) {
							outCollection = new ArrayList();
						}
						for (Object sValue : collection) {
							Object _v = sgc.treater.treat(sValue, sgc.setter.getParameterTypes()[0], entityFactory, environment);
							outCollection.add(_v);

						}
						sgc.setter.invoke(targetObject, outCollection);
					}
				}

			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
				String msg = "Error assigning from " + sgc.getter + " value=" + value + " to " + sgc.setter;
				LOGGER.error(msg, e);
				throw new MapperException(msg, e);
			}
		}
	}

	protected <InputType, OutputType> void mapCollection(Collection<InputType> inputCollection, Class<InputType> inputType, Collection<OutputType> outputCollection, Class<OutputType> outputType, IMapperFactory mapperFactory, IEntitiesFactory entityFactory, Map environment, boolean recursive)
			throws MapperException {
		IMapper<InputType, OutputType> mapper = mapperFactory.createMapper(inputType, outputType);
		for (InputType input : inputCollection) {
			OutputType output = mapper.mapInstance(input, outputType, entityFactory, environment, recursive);
			outputCollection.add(output);
		}
	};

	@Override
	public boolean isAbleToMap(Class<OriginalType> originalType, Class<TargetType> targetType) {

		return this.originalType.isAssignableFrom(originalType) && this.targetType.isAssignableFrom(targetType);
	}

	@Override
	public Class<OriginalType> getOriginalType() {

		return originalType;
	}

	@Override
	public Class<TargetType> getTargetType() {
		return targetType;
	}

}
