package com.openi40.scheduler.mapper;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
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
public class TypeMap {
	public class TypesCouple {
		public TypesCouple() {

		}

		public TypesCouple(Class fType, Class tType) {
			this.fromType = fType;
			this.toType = tType;
		}

		public Class fromType = null;
		public Class toType = null;
	}

	public class TypeMetaInfos {
		public Class toType = null;
		public Map<String, TypesCouple> collectionsMapping = new HashMap<>();
	}

	Map<Class, TypeMetaInfos> map = new HashMap<Class, TypeMetaInfos>();

	public TypeMap() {

	}

	public void add(Class typeFrom, Class typeTo) throws MapperException {
		if (map.containsKey(typeFrom))
			throw new MapperException(
					"Mapping type " + typeFrom + " again, but it whas mapped already to " + map.get(typeFrom));
		map.put(typeFrom, new TypeMetaInfos());
		map.get(typeFrom).toType = typeTo;
	}

	public TypeMetaInfos get(Class typeFrom) {
		TypeMetaInfos typeTo = map.get(typeFrom);
		if (typeTo == null) {
			Set<Map.Entry<Class, TypeMetaInfos>> _set = map.entrySet();
			for (Entry<Class, TypeMetaInfos> entry : _set) {
				if (entry.getKey().isAssignableFrom(typeFrom)) {
					typeTo = entry.getValue();
					break;
				}
			}
		}
		return typeTo;
	}

	public void add(Class fromType, Class toType, String collectionPropertyName, Class fromAggregated,
			Class toAggregated) throws MapperException {
		if (!map.containsKey(fromType))
			throw new MapperException("Mapping type " + fromType + " unknown ");
		TypeMetaInfos ref = get(fromType);
		if (!ref.toType.equals(toType))
			throw new MapperException(
					"Mapping type " + fromType + " is on type " + ref.toType + " and does not match type " + toType);
		ref.collectionsMapping.put(collectionPropertyName, new TypesCouple(fromAggregated, toAggregated));
	}

	public TypesCouple get(Class fromType, Class toType, String collectionPropertyName) {
		TypeMetaInfos meta = get(fromType);
		return meta != null ? meta.collectionsMapping.get(collectionPropertyName) : null;
	}
}
