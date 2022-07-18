package com.openi40.scheduler.common.datamodel;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Predicate;

import org.springframework.stereotype.Service;
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
public class DataInputValidatorImpl implements IDataInputValidator {
	class ReferenceInfo {
		Method accessor = null;
		String field = null;
		Class referencedType = null;
		boolean nullable = false;
	}

	class ReferencesMetaInfos {
		List<ReferenceInfo> referencesList = new ArrayList<ReferenceInfo>();
		Class _class = null;
		Method accessor = null;
		List<ReferencesMetaInfos> childEntities = new ArrayList<DataInputValidatorImpl.ReferencesMetaInfos>();
	}

	public DataInputValidatorImpl() {

	}

	@Override
	public <DataComplexType, DataBaseType> List<ValidationMessage> validate(DataComplexType data,
			Class<DataComplexType> dataComplexType, Class<DataBaseType> dataBaseType,
			Function<DataBaseType, String> idExtractor) {
		List<ValidationMessage> validationMessages = new ArrayList<ValidationMessage>();
		// First index all data types
		Map<Class<? extends DataBaseType>, Map<String, DataBaseType>> indexedMap = indexData(validationMessages, data,
				dataComplexType, dataBaseType, idExtractor);
		indexedMap.forEach((Class<? extends DataBaseType> _type, Map<String, DataBaseType> map) -> {
			ReferencesMetaInfos inspectedStructure = inspectConstraints(_type, dataBaseType);
			map.values().forEach((DataBaseType entry) -> {
				validationMessages.addAll(validateEntry(inspectedStructure, entry, indexedMap));
			});
		});
		return validationMessages;
	}

	private <DataBaseType> List<ValidationMessage> validateEntry(ReferencesMetaInfos inspectedStructure,
			DataBaseType entry, Map<Class<? extends DataBaseType>, Map<String, DataBaseType>> indexedMap) {
		List<ValidationMessage> validationMessages = new ArrayList<ValidationMessage>();
		for (ReferenceInfo ref : inspectedStructure.referencesList) {
			Object _refId;
			try {
				_refId = ref.accessor.invoke(entry);

				if (_refId == null && !ref.nullable) {
					ValidationMessage nullProperty = new ValidationMessage("Null reference", ref.field,
							ref.referencedType, entry);
					validationMessages.add(nullProperty);
				} else if (_refId == null && ref.nullable) {
				} else {
					if (!(indexedMap.containsKey(ref.referencedType)
							&& indexedMap.get(ref.referencedType).containsKey(_refId))) {
						ValidationMessage noReferenceProperty = new ValidationMessage("Invalid reference", ref.field,
								ref.referencedType, _refId, entry);
						validationMessages.add(noReferenceProperty);
					}
				}
			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
				throw new RuntimeException("problems accessing " + ref.accessor.getName() + " of "
						+ ref.accessor.getDeclaringClass().getName(), e);
			}
		}
		return validationMessages;
	}

	private <DataBaseType> ReferencesMetaInfos inspectConstraints(Class<? extends DataBaseType> _type,
			Class<DataBaseType> dataBaseType) {
		ReferencesMetaInfos meta = new ReferencesMetaInfos();
		meta._class = _type;
		Method[] methods = _type.getMethods();
		for (Method method : methods) {
			if (method.getName().startsWith("get") && method.getParameterCount() == 0) {
				ObjectReferenceConstraint annotation = method.getAnnotation(ObjectReferenceConstraint.class);
				ObjectReferenceConstraint[] annotations = method.getAnnotationsByType(ObjectReferenceConstraint.class);
				if (annotation != null) {
					Method accessor = method;
					if (accessor != null) {
						ReferenceInfo reference = new ReferenceInfo();
						reference.accessor = accessor;
						reference.referencedType = annotation.referencedType();
						reference.nullable = annotation.nullable();
						reference.field = method.getName().substring(3);
						meta.referencesList.add(reference);
					}
				} else if (dataBaseType.isAssignableFrom(method.getReturnType())) {
					ReferencesMetaInfos refMeta = inspectConstraints(
							(Class<? extends DataBaseType>) method.getReturnType(), dataBaseType);
					if (!refMeta.childEntities.isEmpty() || !refMeta.referencesList.isEmpty()) {
						Method accessor = method;
						if (accessor != null) {
							refMeta.accessor = accessor;
							meta.childEntities.add(refMeta);
						}
					}
				} else if (Collection.class.isAssignableFrom(method.getReturnType())) {
					Type type = method.getGenericReturnType();
					if (type instanceof ParameterizedType) {
						ParameterizedType parType = (ParameterizedType) type;
						Type[] args = parType.getActualTypeArguments();
						if (args != null && args.length == 1) {
							if (args[0] instanceof Class) {
								Method accessor = method;
								if (accessor != null) {
									Class _toBeInspected = (Class) args[0];
									if (dataBaseType.isAssignableFrom(_toBeInspected)) {
										ReferencesMetaInfos refMeta = inspectConstraints(
												(Class<? extends DataBaseType>) _toBeInspected, dataBaseType);
										if (!refMeta.childEntities.isEmpty() || !refMeta.referencesList.isEmpty()) {
											refMeta.accessor = accessor;
											meta.childEntities.add(refMeta);

										}
									}
								}
							}
						}
					}
				}
			}
		}
		return meta;
	}

	private Method getAccessor(Class _type, Field field) {
		String _fieldName = field.getName();
		char _name[] = _fieldName.toCharArray();
		_name[0] = Character.toUpperCase(_name[0]);
		_fieldName = new String(_name);
		String getterName = "get" + _fieldName;
		try {
			Method method = _type.getMethod(getterName);
			if (method.getReturnType().equals(field.getType()) && Modifier.isPublic(method.getModifiers())) {
				return method;
			}
		} catch (NoSuchMethodException | SecurityException e) {

		}
		return null;
	}

	private <DataComplexType, DataBaseType> Map<Class<? extends DataBaseType>, Map<String, DataBaseType>> indexData(
			List<ValidationMessage> validationMessages, DataComplexType data, Class<DataComplexType> dataComplexType,
			Class<DataBaseType> dataBaseType, Function<DataBaseType, String> idExtractor) {
		Map<Class<? extends DataBaseType>, Map<String, DataBaseType>> outData = new HashMap<Class<? extends DataBaseType>, Map<String, DataBaseType>>();
		Method[] methods = dataComplexType.getMethods();
		for (Method method : methods) {
			boolean dataAccessorOk = false;
			Class _accessedType = null;
			if (method.getName().startsWith("get") && method.getReturnType() != null && method.getParameterCount() == 0
					&& Collection.class.isAssignableFrom(method.getReturnType())
					&& Modifier.isPublic(method.getModifiers())) {
				Type _type = method.getGenericReturnType();
				if (_type instanceof ParameterizedType) {
					ParameterizedType parType = (ParameterizedType) _type;
					Type[] args = parType.getActualTypeArguments();
					if (args != null && args.length == 1 && args[0] instanceof Class) {

						Class _parameterClass = (Class) args[0];
						if (dataBaseType.isAssignableFrom(_parameterClass)) {
							dataAccessorOk = true;
							_accessedType = _parameterClass;

						}
					}
				}
			}
			if (dataAccessorOk) {
				try {
					Collection<? extends DataBaseType> _entries = (Collection<? extends DataBaseType>) method
							.invoke(data);
					if (_entries != null) {
						if (!outData.containsKey(_accessedType))
							outData.put(_accessedType, new HashMap<String, DataBaseType>());
						Map<String, DataBaseType> map2fill = outData.get(_accessedType);
						_entries.forEach((DataBaseType entry) -> {
							String id = idExtractor.apply(entry);
							if (id == null || id.trim().length() == 0) {
								ValidationMessage nullIdMessage = new ValidationMessage("main identification code null",
										entry);
								validationMessages.add(nullIdMessage);
							} else {
								if (map2fill.containsKey(id.trim())) {
									ValidationMessage duplicatedMessage = new ValidationMessage(
											"already inserted entry with same code", entry);
									validationMessages.add(duplicatedMessage);
								} else {
									map2fill.put(id.trim(), entry);
								}
							}
						});
					}
				} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
					throw new RuntimeException("Problem in inspecting data with accessor " + method.getName()
							+ " on structure=>" + dataComplexType.getName(), e);
				}
			}
		}
		return outData;
	}
}
