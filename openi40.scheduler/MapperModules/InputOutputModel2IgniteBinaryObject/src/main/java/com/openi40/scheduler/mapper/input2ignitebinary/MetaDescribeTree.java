package com.openi40.scheduler.mapper.input2ignitebinary;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;


public class MetaDescribeTree<T, HierarchyBaseType> {
	Class<T> describedType = null;
	Class<HierarchyBaseType> baseType = null;


	public static class MetaPropertyDescriptor {
		MetaPropertyDescriptor(PropertyDescriptor pd) {
			this.type = pd.getPropertyType();
			this.name = pd.getName();
			this.readMethod = pd.getReadMethod();
			this.writeMethod = pd.getWriteMethod();
		}

		Class type;
		String name = null;
		Method writeMethod = null, readMethod = null;
		MetaDescribeTree metaDescribeTree = null;
		boolean isNestedDescribeableObject = false;
		public Class getType() {
			return type;
		}
		public String getName() {
			return name;
		}
		public Method getWriteMethod() {
			return writeMethod;
		}
		public Method getReadMethod() {
			return readMethod;
		}
		public MetaDescribeTree getMetaDescribeTree() {
			return metaDescribeTree;
		}
		public boolean isNestedDescribeableObject() {
			return isNestedDescribeableObject;
		}
	};

	public static class CollectionAttributeDescriptor extends MetaPropertyDescriptor {
		CollectionAttributeDescriptor(PropertyDescriptor pd) {
			super(pd);
		}
		Class elementType=null;
		public Class getElementType() {
			return elementType;
		}
	}

	List<MetaPropertyDescriptor> properties = new ArrayList<>();
	List<CollectionAttributeDescriptor> collections = new ArrayList<>();

	private MetaDescribeTree() {

	}

	private static HashMap<Class, MetaDescribeTree> descriptionsCache = new HashMap<>();

	static <T, HierarchyBaseType> MetaDescribeTree<T, HierarchyBaseType> describe(Class<T> type,
			Class<HierarchyBaseType> baseType) throws IntrospectionException {
		if (descriptionsCache.containsKey(type)) {
			return descriptionsCache.get(type);
		}
		java.beans.BeanInfo info = java.beans.Introspector.getBeanInfo(type);
		java.beans.PropertyDescriptor[] _properties = info.getPropertyDescriptors();
		MetaDescribeTree<T, HierarchyBaseType> desc = new MetaDescribeTree<T, HierarchyBaseType>();
		TreeMap<String, MetaPropertyDescriptor> properties = new TreeMap<>();
		TreeMap<String, CollectionAttributeDescriptor> collections = new TreeMap<>();
		for (PropertyDescriptor propertyDescriptor : _properties) {
			if (baseType.isAssignableFrom(propertyDescriptor.getPropertyType())) {
				MetaPropertyDescriptor md = new MetaPropertyDescriptor(propertyDescriptor);
				properties.put(md.name, md);
				md.isNestedDescribeableObject = true;
				md.metaDescribeTree = describe(md.type, baseType);
			} else if (Collection.class.isAssignableFrom(propertyDescriptor.getPropertyType())) {
				CollectionAttributeDescriptor md = new CollectionAttributeDescriptor(propertyDescriptor);
				collections.put(md.name, md);
				Type rtype = propertyDescriptor.getReadMethod().getGenericReturnType();
				if (rtype instanceof ParameterizedType) {
					ParameterizedType pType = (ParameterizedType) rtype;
					Type[] typeArgs = pType.getActualTypeArguments();
					Class argType = typeArgs != null && typeArgs.length > 0 && typeArgs[0] instanceof Class
							? (Class) typeArgs[0]
							: null;
					md.elementType=argType;
					md.isNestedDescribeableObject = argType != null && baseType.isAssignableFrom(baseType);
					if (md.isNestedDescribeableObject) {
						md.metaDescribeTree = describe(argType, baseType);
					}
				}

			} else {
				MetaPropertyDescriptor md = new MetaPropertyDescriptor(propertyDescriptor);
				properties.put(md.name, md);
			}
		}
		desc.properties=new ArrayList<>(properties.values());
		desc.collections=new ArrayList<>(collections.values());
		synchronized (descriptionsCache) {
			descriptionsCache.put(type, desc);
		}
		return desc;
	}

	public Class<T> getDescribedType() {
		return describedType;
	}

	public Class<HierarchyBaseType> getBaseType() {
		return baseType;
	}

	public List<MetaPropertyDescriptor> getProperties() {
		return properties;
	}

	public List<CollectionAttributeDescriptor> getCollections() {
		return collections;
	}

}
