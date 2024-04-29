package com.openi40.scheduler.common.utils;

import java.util.ArrayList;
import java.util.List;
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
public class CollectionUtil {
	private CollectionUtil() {
	}

	private static CollectionUtil Instance = new CollectionUtil();

	public static CollectionUtil getInstance() {
		return Instance;
	}

	public final <BaseType, DerivedType extends BaseType> List<DerivedType> filterByType(List<BaseType> list, Class<DerivedType> derivedTypeClass) {
		List<DerivedType> rv = new ArrayList<DerivedType>();
		for (BaseType v : list) {
			if (derivedTypeClass.isAssignableFrom(v.getClass())) {
				DerivedType dt = (DerivedType) v;
				rv.add(dt);
			}
		}
		return rv;
	}

	public final <CType> void AddCollection(List<CType> outRanges, List<CType> list) {
		for (CType item : list) {
			outRanges.add(item);
		}
	}
}