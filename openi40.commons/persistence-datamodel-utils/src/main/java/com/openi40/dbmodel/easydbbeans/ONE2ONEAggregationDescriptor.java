package com.openi40.dbmodel.easydbbeans;

import java.lang.reflect.Method;
import java.util.Hashtable;



/**
 * Insert the type's description here. Creation date: (30/06/2001 17.43.38)
 * 
 * @author: architectures@openi40.org
 */
public class ONE2ONEAggregationDescriptor extends AggregationDescriptor {
	java.beans.PropertyDescriptor propertyDescriptor = null;
	Method get_Transaction = null;
	Method set_Transaction = null;

	/**
	 * ONE2MANYAggregationDescriptor constructor comment.
	 */
	protected ONE2ONEAggregationDescriptor() {
		super();
		setAggregation_type(ONE2ONE);
	}

	/**
	 * Insert the method's description here. Creation date: (30/06/2001 18.37.40)
	 * 
	 * @return com.zconsultancies.threelayers.persistence.objectmodel.ONE2ONEAggregationDescriptor
	 * @param descr com.zconsultancies.threelayers.persistence.objectmodel.InterfaceDescriptor
	 * @exception java.beans.IntrospectionException The exception description.
	 */
	public static ONE2ONEAggregationDescriptor[] aggregationDescriptors(InterfaceDescriptor descr)
			throws java.beans.IntrospectionException {
		java.beans.PropertyDescriptor descrs[] = java.beans.Introspector.getBeanInfo(descr.getManagedClass())
				.getPropertyDescriptors();
		Class managed = descr.getManagedClass();
		java.util.Vector vector = new java.util.Vector();
		Hashtable aggregationCache = new Hashtable();
		for (int index = 0; index < descrs.length; index++) {
			if (descrs[index].getPropertyType() != null
					&& AggregationObjectModel.class.isAssignableFrom(descrs[index].getPropertyType())
					&& descrs[index].getReadMethod() != null
					&& descrs[index].getReadMethod().getParameterTypes().length == 0) {
				ONE2ONEAggregationDescriptor o2odescr = new ONE2ONEAggregationDescriptor();
				vector.addElement(o2odescr);
				o2odescr.propertyDescriptor = descrs[index];
				o2odescr.setAggregatedType(descrs[index].getPropertyType());
				o2odescr.setAggregation_name(descrs[index].getName());
				aggregationCache.put(descrs[index].getName(), o2odescr);
				// com.zconsultancies.threelayers.util.LogUtil.out.println(descrs[index].getName()
				// + " type " + descrs[index].getPropertyType());
			}
			// else
			// com.zconsultancies.threelayers.util.LogUtil.out.println(descrs[index].getName()
			// + " type " + descrs[index].getPropertyType() + " Not recognized");
		}
		for (int index = 0; index < vector.size(); index++) {
			ONE2ONEAggregationDescriptor o2odescr = (ONE2ONEAggregationDescriptor) vector.elementAt(index);
			if (o2odescr.getPropertyDescriptor().getReadMethod() != null
					&& o2odescr.getPropertyDescriptor().getWriteMethod() != null) {
				String get_name = o2odescr.getPropertyDescriptor().getReadMethod().getName();
				String set_name = o2odescr.getPropertyDescriptor().getWriteMethod().getName();
				try {
					Method get_m = managed.getMethod(get_name, new Class[] { PersistenceTransaction.class });
					Method set_m = managed.getMethod(set_name,
							new Class[] { get_m.getReturnType(), PersistenceTransaction.class });
					if (get_m.getReturnType().equals(o2odescr.getPropertyDescriptor().getPropertyType())) {
						o2odescr.hasTransactionAwareInterface = true;
						o2odescr.set_Transaction = set_m;
						o2odescr.get_Transaction = get_m;
					}
				} catch (Throwable th1) {
				}
			}
		}
		ONE2ONEAggregationDescriptor[] v = new ONE2ONEAggregationDescriptor[vector.size()];
		vector.copyInto(v);
		return v;
	}

	/**
	 * Insert the method's description here. Creation date: (01/07/2001 15.05.15)
	 * 
	 * @return com.zconsultancies.threelayers.persistence.objectmodel.AggregationObjectModel
	 * @param container com.zconsultancies.threelayers.persistence.objectmodel.AggregationObjectModel
	 */
	public AggregationObjectModel getAssociated(AggregationObjectModel container) {
		try {
			return (AggregationObjectModel) propertyDescriptor.getReadMethod().invoke(container, null);
		} catch (IllegalAccessException exc) {
			exc.printStackTrace();
			throw new AggregatedObjectModelException(exc.getMessage());
		} catch (java.lang.reflect.InvocationTargetException exc) {
			exc.printStackTrace();
			throw new AggregatedObjectModelException(exc.getMessage());
		}
	}

	public AggregationObjectModel getAssociated(AggregationObjectModel container, PersistenceTransaction transaction) {
		if (!hasTransactionAwareInterface)
			throw new IllegalStateException(
					"The class " + container.getClass().getName() + " has not a transaction aware interface");
		try {
			return (AggregationObjectModel) this.get_Transaction.invoke(container, new Object[] { transaction });
		} catch (IllegalAccessException exc) {
			exc.printStackTrace();
			throw new AggregatedObjectModelException(exc.getMessage());
		} catch (java.lang.reflect.InvocationTargetException exc) {
			exc.printStackTrace();
			throw new AggregatedObjectModelException(exc.getMessage());
		}
	}

	/**
	 * Insert the method's description here. Creation date: (30/06/2001 18.07.25)
	 * 
	 * @return java.beans.PropertyDescriptor
	 */
	protected java.beans.PropertyDescriptor getPropertyDescriptor() {
		return propertyDescriptor;
	}

	/**
	 * Insert the method's description here. Creation date: (01/07/2001 15.05.15)
	 * 
	 * @return com.zconsultancies.threelayers.persistence.objectmodel.AggregationObjectModel
	 * @param container com.zconsultancies.threelayers.persistence.objectmodel.AggregationObjectModel
	 */
	public void setAssociated(AggregationObjectModel object, AggregationObjectModel container) {
		try {
			propertyDescriptor.getWriteMethod().invoke(container, new Object[] { object });
		} catch (IllegalAccessException exc) {
			exc.printStackTrace();
			throw new AggregatedObjectModelException();
		} catch (java.lang.reflect.InvocationTargetException exc) {
			exc.printStackTrace();
			throw new AggregatedObjectModelException();
		}
	}

	public void setAssociated(AggregationObjectModel object, AggregationObjectModel container,
			PersistenceTransaction transaction) {
		if (!hasTransactionAwareInterface)
			throw new IllegalStateException(
					"The class " + container.getClass().getName() + " has not a transaction aware interface");
		try {
			this.set_Transaction.invoke(container, new Object[] { object, transaction });
		} catch (IllegalAccessException exc) {
			throw new AggregatedObjectModelException();
		} catch (java.lang.reflect.InvocationTargetException exc) {
			throw new AggregatedObjectModelException();
		}
	}

	/**
	 * Insert the method's description here. Creation date: (30/06/2001 18.07.25)
	 * 
	 * @param newPropertyDescriptor java.beans.PropertyDescriptor
	 */
	protected void setPropertyDescriptor(java.beans.PropertyDescriptor newPropertyDescriptor) {
		propertyDescriptor = newPropertyDescriptor;
	}
}
