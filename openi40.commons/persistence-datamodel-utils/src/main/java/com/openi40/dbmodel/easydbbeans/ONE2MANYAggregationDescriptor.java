package com.openi40.dbmodel.easydbbeans;

import java.util.Hashtable;



/**
 * Insert the type's description here. Creation date: (30/06/2001 17.43.38)
 * 
 * @author Paolo Zavalloni : architectures@openi40.org
 */
public class ONE2MANYAggregationDescriptor extends AggregationDescriptor implements java.io.Serializable {
	private java.lang.reflect.Method _addMethod = null;

	private java.lang.reflect.Method _getMethod = null;

	private java.lang.reflect.Method _createMethod = null;

	private java.lang.reflect.Method _removeMethod = null;

	private java.lang.reflect.Method _countMethod = null;

	private java.lang.reflect.Method _addMethod_Transact = null;

	private java.lang.reflect.Method _getMethod_Transact = null;

	private java.lang.reflect.Method _createMethod_Transact = null;

	private java.lang.reflect.Method _removeMethod_Transact = null;

	private java.lang.reflect.Method _countMethod_Transact = null;

	/**
	 * ONE2MANYAggregationDescriptor constructor comment.
	 */
	protected ONE2MANYAggregationDescriptor() {
		super();
		setAggregation_type(ONE2MANY);
	}

	/**
	 * Insert the method's description here. Creation date: (30/06/2001
	 * 18.34.48)
	 * 
	 * @param object
	 *            com.zconsultancies.threelayers.persistence.objectmodel.
	 *            AggregationObjectModel
	 * @param container
	 *            com.zconsultancies.threelayers.persistence.objectmodel.
	 *            AggregationObjectModel
	 */
	public void addObject(AggregationObjectModel object, AggregationObjectModel container) {
		try {

			_addMethod.invoke(container, new Object[] { object });

		} catch (java.lang.reflect.InvocationTargetException exc) {
			exc.printStackTrace();
			throw new AggregatedObjectModelException("Errore in chiamata a " + _addMethod.getDeclaringClass().getName() + "."
					+ _addMethod.getName() + "\n" + exc.toString());
		} catch (IllegalAccessException exc) {
			throw new AggregatedObjectModelException("Errore in chiamata a " + _addMethod.getDeclaringClass().getName() + "."
					+ _addMethod.getName() + "\n" + exc.toString());
		}
	}

	public void addObject(AggregationObjectModel object, AggregationObjectModel container, PersistenceTransaction transaction) {
		if (!hasTransactionAwareInterface)
			throw new IllegalStateException("La classe " + container.getClass().getName()
					+ " non ha una transaction aware interface");
		try {
			_addMethod_Transact.invoke(container, new Object[] { object, transaction });
		} catch (java.lang.reflect.InvocationTargetException exc) {
			throw new AggregatedObjectModelException("Errore in chiamata a " + _addMethod.getDeclaringClass().getName() + "."
					+ _addMethod.getName() + "\n" + exc.toString());
		} catch (IllegalAccessException exc) {
			throw new AggregatedObjectModelException("Errore in chiamata a " + _addMethod.getDeclaringClass().getName() + "."
					+ _addMethod.getName() + "\n" + exc.toString());
		}
	}

	/**
	 * Insert the method's description here. Creation date: (30/06/2001
	 * 18.36.45)
	 * 
	 * @return com.zconsultancies.threelayers.persistence.objectmodel.
	 *         ONE2MANYAggregationDescriptor[]
	 * @param descr
	 *            com.zconsultancies.threelayers.persistence.objectmodel.
	 *            InterfaceDescriptor
	 * @exception java.lang.Throwable
	 *                The exception description.
	 */
	public static ONE2MANYAggregationDescriptor[] aggregationDescriptors(InterfaceDescriptor descr)
			throws java.beans.IntrospectionException {
		java.util.Vector descriptors = new java.util.Vector();
		java.lang.reflect.Method methods[] = descr.getManagedClass().getMethods();
		java.lang.reflect.Method addMethod = null;
		java.lang.reflect.Method getMethod = null;
		java.lang.reflect.Method createMethod = null;
		java.lang.reflect.Method removeMethod = null;
		java.lang.reflect.Method countMethod = null;
		java.lang.reflect.Method addMethod_Transact = null;
		java.lang.reflect.Method getMethod_Transact = null;
		java.lang.reflect.Method createMethod_Transact = null;
		java.lang.reflect.Method removeMethod_Transact = null;
		java.lang.reflect.Method countMethod_Transact = null;
		Hashtable aggregationCache = new Hashtable();
		for (int i = 0; i < methods.length; i++) {
			String name = methods[i].getName();
			String property = null;
			// MODIFICA DEL 8-10-2003 verifica pi  stretta dei metodi per
			// togliere ambiguit 
			if (name.startsWith("get") && name.endsWith("Count") && methods[i].getReturnType() != null
					&& methods[i].getReturnType().equals(Integer.class)
					&& (methods[i].getParameterTypes() == null || methods[i].getParameterTypes().length == 0)) {
				property = name.substring(0, name.length() - 5);
				property = property.substring(3);
				String _createMethodName = "create" + property;
				String _addMethodName = "add" + property;
				String _removeMethodName = "remove" + property;
				String _getElementMethodName = "get" + property;

				countMethod = methods[i];
				for (int d = 0; d < methods.length; d++) {
					name = methods[d].getName();
					// if (name.startsWith("create") &&
					// name.substring(6).equals(property) &&
					// (methods[d].getParameterTypes() == null ||
					// methods[d].getParameterTypes().length == 0)) {
					if (name.equals(_createMethodName)
							&& (methods[d].getParameterTypes() == null || methods[d].getParameterTypes().length == 0)) {
						createMethod = methods[d];
						for (int f = 0; f < methods.length; f++) {
							name = methods[f].getName();
							// if (name.startsWith("get")
							// && name.substring(3).equals(property)
							// && (methods[f].getParameterTypes() != null &&
							// methods[f].getParameterTypes().length == 1 &&
							// methods[f]
							// .getParameterTypes()[0].equals(Integer.class))
							// && (methods[f].getReturnType() != null &&
							// methods[f].getReturnType().equals(
							// createMethod.getReturnType()))) {
							if (name.equals(_getElementMethodName)
									&& (methods[f].getParameterTypes() != null && methods[f].getParameterTypes().length == 1 && methods[f]
											.getParameterTypes()[0].equals(Integer.class))
									&& (methods[f].getReturnType() != null && methods[f].getReturnType().equals(
											createMethod.getReturnType()))) {
								getMethod = methods[f];
								for (int g = 0; g < methods.length; g++) {
									name = methods[g].getName();
									// if (name.startsWith("remove")
									// && name.substring(6).equals(property)
									// && (methods[g].getParameterTypes() !=
									// null
									// && methods[g].getParameterTypes().length
									// == 1 && methods[g]
									// .getParameterTypes()[0].equals(createMethod.getReturnType())))
									// {
									if (name.equals(_removeMethodName)
											&& (methods[g].getParameterTypes() != null
													&& methods[g].getParameterTypes().length == 1 && methods[g]
													.getParameterTypes()[0].equals(createMethod.getReturnType()))) {
										removeMethod = methods[g];
										for (int a = 0; a < methods.length; a++) {
											name = methods[a].getName();
											// if (name.startsWith("add") &&
											// name.endsWith(property)
											// && methods[a].getParameterTypes()
											// != null
											// &&
											// methods[a].getParameterTypes().length
											// == 1
											// &&
											// methods[a].getParameterTypes()[0].equals(getMethod.getReturnType()))
											// {
											if (name.equals(_addMethodName) && methods[a].getParameterTypes() != null
													&& methods[a].getParameterTypes().length == 1
													&& methods[a].getParameterTypes()[0].equals(getMethod.getReturnType())) {
												addMethod = methods[a];
												ONE2MANYAggregationDescriptor one2manydescr = new ONE2MANYAggregationDescriptor();
												one2manydescr.setAggregation_name(property);
												one2manydescr.setAggregation_type(ONE2MANY);
												one2manydescr._addMethod = addMethod;
												one2manydescr._countMethod = countMethod;
												one2manydescr._createMethod = createMethod;
												one2manydescr._getMethod = getMethod;
												one2manydescr._removeMethod = removeMethod;
												one2manydescr.setAggregatedType(getMethod.getReturnType());
												descriptors.addElement(one2manydescr);
												aggregationCache.put(property, one2manydescr);
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}
		// ADDED TRANSACTION AWARE ACCESSORS MANAGEMENT
		for (int i = 0; i < methods.length; i++) {
			String name = methods[i].getName();
			String property = null;
			if (name.startsWith("get")
					&& name.endsWith("Count")
					&& methods[i].getReturnType() != null
					&& methods[i].getReturnType().equals(Integer.class)
					&& (methods[i].getParameterTypes() != null && methods[i].getParameterTypes().length == 1 && methods[i]
							.getParameterTypes()[0].equals(PersistenceTransaction.class))) {
				property = name.substring(0, name.length() - 5);
				property = property.substring(3);
				countMethod_Transact = methods[i];
				for (int d = 0; d < methods.length; d++) {
					name = methods[d].getName();
					if (name.startsWith("create")
							&& name.substring(6).equals(property)
							&& (methods[d].getParameterTypes() != null && methods[d].getParameterTypes().length == 1 && methods[d]
									.getParameterTypes()[0].equals(PersistenceTransaction.class))) {
						createMethod_Transact = methods[d];
						for (int f = 0; f < methods.length; f++) {
							name = methods[f].getName();
							if (name.startsWith("get")
									&& name.substring(3).equals(property)
									&& (methods[f].getParameterTypes() != null && methods[f].getParameterTypes().length == 2
											&& methods[f].getParameterTypes()[0].equals(Integer.class) && methods[f]
											.getParameterTypes()[1].equals(PersistenceTransaction.class))
									&& (methods[f].getReturnType() != null && methods[f].getReturnType().equals(
											createMethod_Transact.getReturnType()))) {
								getMethod_Transact = methods[f];
								for (int g = 0; g < methods.length; g++) {
									name = methods[g].getName();
									if (name.startsWith("remove")
											&& name.substring(6).equals(property)
											&& (methods[g].getParameterTypes() != null
													&& methods[g].getParameterTypes().length == 2
													&& methods[g].getParameterTypes()[0].equals(createMethod_Transact
															.getReturnType()) && methods[g].getParameterTypes()[1]
													.equals(PersistenceTransaction.class))) {
										removeMethod_Transact = methods[g];
										for (int a = 0; a < methods.length; a++) {
											name = methods[a].getName();
											if (name.startsWith("add")
													&& name.endsWith(property)
													&& methods[a].getParameterTypes() != null
													&& methods[a].getParameterTypes().length == 2
													&& methods[a].getParameterTypes()[0].equals(getMethod_Transact
															.getReturnType())
													&& methods[a].getParameterTypes()[1].equals(PersistenceTransaction.class)) {
												addMethod_Transact = methods[a];
												ONE2MANYAggregationDescriptor one2manydescr = (ONE2MANYAggregationDescriptor) aggregationCache
														.get(property);
												if (one2manydescr != null) {
													one2manydescr._addMethod_Transact = addMethod_Transact;
													one2manydescr._countMethod_Transact = countMethod_Transact;
													one2manydescr._createMethod_Transact = createMethod_Transact;
													one2manydescr._getMethod_Transact = getMethod_Transact;
													one2manydescr._removeMethod_Transact = removeMethod_Transact;
													one2manydescr.hasTransactionAwareInterface = true;
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}
		ONE2MANYAggregationDescriptor descs[] = new ONE2MANYAggregationDescriptor[descriptors.size()];
		descriptors.copyInto(descs);
		return descs;
	}

	/**
	 * Insert the method's description here. Creation date: (30/06/2001
	 * 18.33.36)
	 * 
	 * @return com.zconsultancies.threelayers.persistence.objectmodel.
	 *         AggregationObjectModel
	 * @param object
	 *            com.zconsultancies.threelayers.persistence.objectmodel.
	 *            AggregationObjectModel
	 */
	public AggregationObjectModel createObject(AggregationObjectModel object) {
		try {
			Object obj = _createMethod.invoke(object, new Object[] {});
			return (AggregationObjectModel) obj;
		} catch (java.lang.reflect.InvocationTargetException exc) {
			throw new AggregatedObjectModelException("Errore in chiamata a " + _createMethod.getDeclaringClass().getName() + "."
					+ _createMethod.getName() + "\n" + exc.toString());
		} catch (IllegalAccessException exc) {
			throw new AggregatedObjectModelException("Errore in chiamata a " + _createMethod.getDeclaringClass().getName() + "."
					+ _createMethod.getName() + "\n" + exc.toString());
		}
	}

	public AggregationObjectModel createObject(AggregationObjectModel object, PersistenceTransaction transaction) {
		if (!hasTransactionAwareInterface)
			throw new IllegalStateException("La classe " + object.getClass().getName()
					+ " non ha una transaction aware interface");
		try {
			Object obj = _createMethod_Transact.invoke(object, new Object[] { transaction });
			return (AggregationObjectModel) obj;
		} catch (java.lang.reflect.InvocationTargetException exc) {
			throw new AggregatedObjectModelException("Errore in chiamata a " + _createMethod.getDeclaringClass().getName() + "."
					+ _createMethod.getName() + "\n" + exc.toString());
		} catch (IllegalAccessException exc) {
			throw new AggregatedObjectModelException("Errore in chiamata a " + _createMethod.getDeclaringClass().getName() + "."
					+ _createMethod.getName() + "\n" + exc.toString());
		}
	}

	/**
	 * Insert the method's description here. Creation date: (30/06/2001
	 * 17.45.08)
	 * 
	 * @return int
	 * @param object
	 *            java.lang.Object
	 */
	public int getCountAttribute(AggregationObjectModel object) {
		try {
			Object obj = _countMethod.invoke(object, new Object[] {});
			return ((Integer) obj).intValue();
		} catch (java.lang.reflect.InvocationTargetException exc) {
			throw new AggregatedObjectModelException("Errore in chiamata a " + _countMethod.getDeclaringClass().getName() + "."
					+ _countMethod.getName() + "\n" + exc.toString());
		} catch (IllegalAccessException exc) {
			throw new AggregatedObjectModelException("Errore in chiamata a " + _countMethod.getDeclaringClass().getName() + "."
					+ _countMethod.getName() + "\n" + exc.toString());
		}
	}

	public int getCountAttribute(AggregationObjectModel object, PersistenceTransaction transaction) {
		if (!hasTransactionAwareInterface)
			throw new IllegalStateException("La classe " + object.getClass().getName()
					+ " non ha una transaction aware interface");
		try {
			Object obj = _countMethod_Transact.invoke(object, new Object[] { transaction });
			return ((Integer) obj).intValue();
		} catch (java.lang.reflect.InvocationTargetException exc) {
			exc.printStackTrace();
			throw new AggregatedObjectModelException("Errore in chiamata a " + _countMethod.getDeclaringClass().getName() + "."
					+ _countMethod.getName() + "\n" + exc.toString());
		} catch (IllegalAccessException exc) {
			throw new AggregatedObjectModelException("Errore in chiamata a " + _countMethod.getDeclaringClass().getName() + "."
					+ _countMethod.getName() + "\n" + exc.toString());
		}
	}

	/**
	 * Insert the method's description here. Creation date: (30/06/2001
	 * 18.32.39)
	 * 
	 * @return com.zconsultancies.threelayers.persistence.objectmodel.
	 *         AggregationObjectModel
	 * @param index
	 *            int
	 * @param object
	 *            java.lang.Object
	 */
	public AggregationObjectModel getObjectAt(int index, AggregationObjectModel object) {
		try {
			Object obj = _getMethod.invoke(object, new Object[] { new Integer(index) });
			return (AggregationObjectModel) obj;
		} catch (java.lang.reflect.InvocationTargetException exc) {
			throw new AggregatedObjectModelException("Errore in chiamata a " + _getMethod.getDeclaringClass().getName() + "."
					+ _getMethod.getName() + "\n" + exc.toString());
		} catch (IllegalAccessException exc) {
			throw new AggregatedObjectModelException("Errore in chiamata a " + _getMethod.getDeclaringClass().getName() + "."
					+ _getMethod.getName() + "\n" + exc.toString());
		}
	}

	public AggregationObjectModel getObjectAt(int index, AggregationObjectModel object, PersistenceTransaction transaction) {
		if (!hasTransactionAwareInterface)
			throw new IllegalStateException("La classe " + object.getClass().getName()
					+ " non ha una transaction aware interface");
		try {
			Object obj = _getMethod_Transact.invoke(object, new Object[] { new Integer(index), transaction });
			return (AggregationObjectModel) obj;
		} catch (java.lang.reflect.InvocationTargetException exc) {
			throw new AggregatedObjectModelException("Errore in chiamata a " + _getMethod.getDeclaringClass().getName() + "."
					+ _getMethod.getName() + "\n" + exc.toString());
		} catch (IllegalAccessException exc) {
			throw new AggregatedObjectModelException("Errore in chiamata a " + _getMethod.getDeclaringClass().getName() + "."
					+ _getMethod.getName() + "\n" + exc.toString());
		}
	}

	/**
	 * Insert the method's description here. Creation date: (30/06/2001
	 * 19.19.07)
	 * 
	 * @param args
	 *            java.lang.String[]
	 */
	public static void main(String[] args) {
		try {
			// InterfaceDescriptor.descript(com.zconsultancies.threelayers.persistence.dbmappingdescription.DataContext.class);
		} catch (Throwable th) {
			th.printStackTrace();
		}
	}

	/**
	 * Insert the method's description here. Creation date: (30/06/2001
	 * 18.34.48)
	 * 
	 * @param object
	 *            com.zconsultancies.threelayers.persistence.objectmodel.
	 *            AggregationObjectModel
	 * @param container
	 *            com.zconsultancies.threelayers.persistence.objectmodel.
	 *            AggregationObjectModel
	 */
	public void removeObject(AggregationObjectModel object, AggregationObjectModel container) {
		try {
			_removeMethod.invoke(container, new Object[] { object });
		} catch (java.lang.reflect.InvocationTargetException exc) {
			throw new AggregatedObjectModelException("Errore in chiamata a " + _removeMethod.getDeclaringClass().getName() + "."
					+ _removeMethod.getName() + "\n" + exc.toString());
		} catch (IllegalAccessException exc) {
			throw new AggregatedObjectModelException("Errore in chiamata a " + _removeMethod.getDeclaringClass().getName() + "."
					+ _removeMethod.getName() + "\n" + exc.toString());
		}
	}

	public void removeObject(AggregationObjectModel object, AggregationObjectModel container, PersistenceTransaction transaction) {
		if (!hasTransactionAwareInterface)
			throw new IllegalStateException("La classe " + object.getClass().getName()
					+ " non ha una transaction aware interface");
		try {
			_removeMethod_Transact.invoke(container, new Object[] { object, transaction });
		} catch (java.lang.reflect.InvocationTargetException exc) {
			throw new AggregatedObjectModelException("Errore in chiamata a " + _removeMethod.getDeclaringClass().getName() + "."
					+ _removeMethod.getName() + "\n" + exc.toString());
		} catch (IllegalAccessException exc) {
			throw new AggregatedObjectModelException("Errore in chiamata a " + _removeMethod.getDeclaringClass().getName() + "."
					+ _removeMethod.getName() + "\n" + exc.toString());
		}
	}
}
