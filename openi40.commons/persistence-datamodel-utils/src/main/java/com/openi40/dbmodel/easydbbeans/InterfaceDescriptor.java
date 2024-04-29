package com.openi40.dbmodel.easydbbeans;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;

/**
 * Insert the type's description here. Creation date: (30/06/2001 17.28.50)
 * 
 * @author Paolo Zavalloni : architectures@openi40.org
 */
public class InterfaceDescriptor implements java.io.Serializable {
	Class managedClass = null;
	java.beans.PropertyDescriptor managedDataProperties[] = null;
	int managedPropertiesSqlTypes[] = null;
	java.util.HashMap one2manyAggregation = new java.util.HashMap();
	java.util.HashMap one2oneAggregation = new java.util.HashMap();
	java.util.List aggregations = new ArrayList();
	static java.util.HashMap descriptionCache = new HashMap();
	java.util.HashMap propertyTable = new java.util.HashMap();

	/**
	 * ObjectDescriptor constructor comment.
	 */
	InterfaceDescriptor(Class clss) throws java.beans.IntrospectionException {
		super();
		java.beans.BeanInfo info = java.beans.Introspector.getBeanInfo(clss);
		setManagedClass(clss);
		descriptionCache.put(clss, this);
		java.util.Vector vector = new java.util.Vector();
		for (int index = 0; index < info.getPropertyDescriptors().length; index++) {
			java.beans.PropertyDescriptor descriptor = info.getPropertyDescriptors()[index];
			if (TypesManager.isManaged(descriptor.getPropertyType()) && descriptor.getWriteMethod() != null
					&& descriptor.getReadMethod() != null) {
				vector.addElement(descriptor);
			}
		}
		managedDataProperties = new java.beans.PropertyDescriptor[vector.size()];
		managedPropertiesSqlTypes = new int[vector.size()];
		vector.copyInto(managedDataProperties);
		for (int index = 0; index < vector.size(); index++) {
			managedPropertiesSqlTypes[index] = TypesManager
					.getSQLMappedType(managedDataProperties[index].getPropertyType());
			propertyTable.put(managedDataProperties[index].getName(), managedDataProperties[index]);
		}
		{
			ONE2MANYAggregationDescriptor descrs[] = ONE2MANYAggregationDescriptor.aggregationDescriptors(this);
			for (int index = 0; index < descrs.length; index++) {
				aggregations.add(descrs[index]);
				one2manyAggregation.put(descrs[index].getAggregation_name(), descrs[index]);
			}
		}
		{
			ONE2ONEAggregationDescriptor descrs[] = ONE2ONEAggregationDescriptor.aggregationDescriptors(this);
			for (int index = 0; index < descrs.length; index++) {
				aggregations.add(descrs[index]);
				one2oneAggregation.put(descrs[index].getAggregation_name(), descrs[index]);
			}
		}
	}

	/**
	 * Insert the method's description here. Creation date: (30/06/2001 17.48.03)
	 * 
	 * @return com.zconsultancies.threelayers.persistence.objectmodel.InterfaceDescriptor
	 * @param clss java.lang.Class
	 */
	public static InterfaceDescriptor descript(Class clss) throws AggregatedObjectModelException {
		if (!AggregationObjectModel.class.isAssignableFrom(clss)) {
			throw new IllegalArgumentException("La classe " + clss.getName() + " passata non implementa "
					+ AggregationObjectModel.class.getName() + " ");
		}
		try {
			InterfaceDescriptor idesc = (InterfaceDescriptor) descriptionCache.get(clss);
			if (idesc == null) {
				idesc = new InterfaceDescriptor(clss);
				descriptionCache.put(clss, idesc);
				java.util.Enumeration enum1 = idesc.getAggregations();
				while (enum1.hasMoreElements()) {
					AggregationDescriptor desc_object = (AggregationDescriptor) enum1.nextElement();
					desc_object.setAggregatedInterface(descript(desc_object.getAggregatedType()));
				}
			}
			return idesc;
		} catch (java.beans.IntrospectionException exc) {
			throw new AggregatedObjectModelException(
					"La classe " + clss.getName() + " non e' analizzabile con Introspector");
		}
	}

	/**
	 * Insert the method's description here. Creation date: (30/06/2001 18.45.16)
	 * 
	 * @return java.util.Enumeration
	 */
	public java.util.Enumeration getAggregations() {
		return new Vector(aggregations).elements();
	}

	/**
	 * Insert the method's description here. Creation date: (30/06/2001 17.46.22)
	 * 
	 * @return java.lang.Class
	 */
	public java.lang.Class getManagedClass() {
		return managedClass;
	}

	/**
	 * Insert the method's description here. Creation date: (30/06/2001 17.30.57)
	 * 
	 * @return java.beans.PropertyDescriptor[]
	 */
	public java.beans.PropertyDescriptor[] getManagedDataProperties() {
		return managedDataProperties;
	}

	/**
	 * Insert the method's description here. Creation date: (08/07/2001 23.54.53)
	 * 
	 * @return int[]
	 */
	public int[] getManagedPropertiesSqlTypes() {
		return managedPropertiesSqlTypes;
	}

	/**
	 * Insert the method's description here. Creation date: (30/06/2001 18.46.37)
	 * 
	 * @return com.zconsultancies.threelayers.persistence.objectmodel.ONE2ONEAggregationDescriptor
	 * @param name java.lang.String
	 */
	public ONE2MANYAggregationDescriptor getONE2MANYAggregation(String name) {
		return (ONE2MANYAggregationDescriptor) one2manyAggregation.get(name);
	}

	/**
	 * Insert the method's description here. Creation date: (30/06/2001 18.45.16)
	 * 
	 * @return java.util.Enumeration
	 */
	public java.util.Enumeration getONE2MANYAggregationNames() {
		return new Vector(one2manyAggregation.values()).elements();
	}

	/**
	 * Insert the method's description here. Creation date: (30/06/2001 18.45.16)
	 * 
	 * @return java.util.Enumeration
	 */
	public java.util.Enumeration getONE2MANYAggregations() {
		return new Vector(one2manyAggregation.values()).elements();
	}

	/**
	 * Insert the method's description here. Creation date: (30/06/2001 18.46.37)
	 * 
	 * @return com.zconsultancies.threelayers.persistence.objectmodel.ONE2ONEAggregationDescriptor
	 * @param name java.lang.String
	 */
	public ONE2ONEAggregationDescriptor getONE2ONEAggregation(String name) {
		return (ONE2ONEAggregationDescriptor) one2oneAggregation.get(name);
	}

	/**
	 * Insert the method's description here. Creation date: (30/06/2001 18.45.16)
	 * 
	 * @return java.util.Enumeration
	 */
	public java.util.Enumeration getONE2ONEAggregationNames() {
		return new Vector(one2oneAggregation.keySet()).elements();
	}

	/**
	 * Insert the method's description here. Creation date: (30/06/2001 18.45.16)
	 * 
	 * @return java.util.Enumeration
	 */
	public java.util.Enumeration getONE2ONEAggregations() {
		return new Vector(one2oneAggregation.keySet()).elements();
	}

	/**
	 * Insert the method's description here. Creation date: (11/07/2001 11.33.25)
	 * 
	 * @return java.beans.PropertyDescriptor
	 * @param propertyName java.lang.String
	 */
	public java.beans.PropertyDescriptor getPropertyDescriptor(String propertyName) {
		java.beans.PropertyDescriptor descriptor = (java.beans.PropertyDescriptor) this.propertyTable.get(propertyName);
		if (descriptor == null)
			throw new IllegalArgumentException(
					"Property " + propertyName + " unknown for class " + managedClass.getName());
		return descriptor;
	}

	public java.beans.PropertyDescriptor getPropertyDescriptorOrNull(String propertyName) {
		java.beans.PropertyDescriptor descriptor = (java.beans.PropertyDescriptor) this.propertyTable.get(propertyName);
		return descriptor;
	}

	/**
	 * Insert the method's description here. Creation date: (30/06/2001 17.46.22)
	 * 
	 * @param newManagedClass java.lang.Class
	 */
	void setManagedClass(java.lang.Class newManagedClass) {
		managedClass = newManagedClass;
	}

	/**
	 * Insert the method's description here. Creation date: (30/06/2001 17.30.57)
	 * 
	 * @param newManagedDataProperties java.beans.PropertyDescriptor[]
	 */
	void setManagedDataProperties(java.beans.PropertyDescriptor[] newManagedDataProperties) {
		managedDataProperties = newManagedDataProperties;
	}
}
