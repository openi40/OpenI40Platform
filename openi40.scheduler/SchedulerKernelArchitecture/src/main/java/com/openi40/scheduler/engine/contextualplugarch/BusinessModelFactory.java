package com.openi40.scheduler.engine.contextualplugarch;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openi40.scheduler.common.aps.IApsData;
import com.openi40.scheduler.common.aps.IApsObject;
import com.openi40.scheduler.common.aps.ICloneable;
import com.openi40.scheduler.common.aps.IMetaInfo;
import com.openi40.scheduler.common.aps.IReferencingMetaInfo;
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
public class BusinessModelFactory implements IBusinessModelFactory {
	List<ITypeHierarchyBusinessModelFactory<?>> typesSpecialized = new ArrayList<>();
	protected Map<Class, List<Constructor<?>>> constructorsCache = new HashMap<>();

	public BusinessModelFactory(
			@Autowired(required = false) List<ITypeHierarchyBusinessModelFactory<?>> typesSpecialized) {
		this.typesSpecialized = typesSpecialized;
		if (this.typesSpecialized == null) {
			this.typesSpecialized = new ArrayList<>();
		}
	}

	protected List<Constructor<?>> getConstructors(Class type) {
		if (!constructorsCache.containsKey(type)) {
			Constructor<?>[] constructors = type.getConstructors();
			List<Constructor<?>> list = new ArrayList<>();
			if (constructors != null) {
				for (Constructor<?> constructor : constructors) {
					if (Modifier.isPublic(constructor.getModifiers())) {
						list.add(constructor);
					}
				}
			}
			constructorsCache.put(type, list);
		}
		return constructorsCache.get(type);
	}

	protected <ApsType> ApsType newInstance(Class<ApsType> type, Object... params)
			throws BusinessModelFactoryException {
		List<Constructor<?>> constructors = getConstructors(type);
		for (Constructor<?> constructor : constructors) {
			boolean matchingParams = true;
			Parameter[] paramTypes = constructor.getParameters();
			matchingParams = ((params == null || params.length == 0) && (paramTypes == null || paramTypes.length == 0))
					|| (params != null && paramTypes != null && params.length == paramTypes.length);
			if (params != null && matchingParams) {
				for (int i = 0; matchingParams && i < params.length; i++) {
					matchingParams = matchingParams
							&& (params[i] == null || paramTypes[i].getType().isAssignableFrom(params[i].getClass()));
				}
			}
			if (matchingParams)
				try {
					return (ApsType) constructor.newInstance(params);
				} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
						| InvocationTargetException e) {
					throw new BusinessModelFactoryException(
							"Cannot create " + type.getName() + " with params " + params, e);
				}
		}
		throw new BusinessModelFactoryException(
				"Cannot create " + type.getName() + " no matching constructor with params: " + params);
	}

	protected int rateHierarchyDistance(ITypeHierarchyBusinessModelFactory<?> specialized,
			Class<? extends IApsObject> type) {
		if (specialized.getManagedTypesAncestor().equals(type))
			return 0;
		else if (specialized.getManagedTypesAncestor().isAssignableFrom(type)
				&& IApsObject.class.isAssignableFrom(type.getSuperclass()))
			return 1 + rateHierarchyDistance(specialized, (Class<? extends IApsObject>) type.getSuperclass());
		return Integer.MAX_VALUE;

	}

	protected ITypeHierarchyBusinessModelFactory<?> getNearestFactory(Class type) {
		if (!typesSpecialized.isEmpty()) {
			for (ITypeHierarchyBusinessModelFactory<?> specialized : typesSpecialized) {
				if (specialized.getManagedTypesAncestor().equals(type)) {
					return specialized;
				}
			}
			List<ITypeHierarchyBusinessModelFactory<?>> matchingAncestors = new ArrayList<>();
			for (ITypeHierarchyBusinessModelFactory<?> specialized : typesSpecialized) {
				if (specialized.getManagedTypesAncestor().isAssignableFrom(type)) {
					matchingAncestors.add(specialized);
				}
			}
			if (matchingAncestors.size() == 1)
				return matchingAncestors.get(0);
			TreeMap<Integer, List<ITypeHierarchyBusinessModelFactory<?>>> ratings = new TreeMap<>();
			for (ITypeHierarchyBusinessModelFactory<?> matching : matchingAncestors) {
				int rate = rateHierarchyDistance(matching, type);
				if (!ratings.containsKey(rate)) {
					ratings.put(rate, new ArrayList<>());
				}
				ratings.get(rate).add(matching);
			}
			if (!ratings.isEmpty()) {
				return ratings.firstEntry().getValue().get(0);
			}
		}
		return null;
	}

	@Override
	public <ApsType extends IApsObject> ApsType create(Class<ApsType> type, IApsData context)
			throws BusinessModelFactoryException {
		ITypeHierarchyBusinessModelFactory<? extends IApsObject> factory = getNearestFactory(type);
		if (factory != null)
			return factory.create(type, context);
		return newInstance(type, context);
	}

	@Override
	public <ApsType extends IApsObject> ApsType create(Class<ApsType> type, ApsType masterCopy)
			throws BusinessModelFactoryException {
		ITypeHierarchyBusinessModelFactory<? extends IApsObject> factory = getNearestFactory(type);
		if (factory != null)
			return factory.create(type, masterCopy);
		return create(type, masterCopy, masterCopy.getContext());
	}

	@Override
	public <ApsType extends IApsObject> ApsType create(Class<ApsType> type, ApsType masterCopy, IApsData context)
			throws BusinessModelFactoryException {
		ITypeHierarchyBusinessModelFactory<? extends IApsObject> factory = getNearestFactory(type);
		if (factory != null)
			return factory.create(type, masterCopy, context);
		return newInstance(type, new Object[] { context, masterCopy });
	}

	@Override
	public <ApsType extends ICloneable> ApsType clone(ApsType instance) throws BusinessModelFactoryException {

		try {
			return (ApsType) instance.cleanClone();
		} catch (CloneNotSupportedException e) {
			throw new BusinessModelFactoryException("Clone not supported!", e);
		}
	}

	@Override
	public <ApsMetaInfoType extends IMetaInfo, ApsType extends IReferencingMetaInfo<ApsMetaInfoType>> ApsType create(
			Class<ApsType> type, ApsMetaInfoType metaInfo, IApsData context) throws BusinessModelFactoryException {
		ITypeHierarchyBusinessModelFactory<? extends IApsObject> factory = getNearestFactory(type);
		if (factory != null)
			return factory.create(type, metaInfo, context);
		return newInstance(type, new Object[] { context, metaInfo });
	}

}
