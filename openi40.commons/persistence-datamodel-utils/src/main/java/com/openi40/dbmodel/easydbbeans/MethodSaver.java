package com.openi40.dbmodel.easydbbeans;

/**
 * Insert the type's description here.
 * Creation date: (04/09/2002 15.18.24)
 * @author Paolo Zavalloni : architectures@openi40.org
 */
public class MethodSaver implements java.io.Serializable {
	String methodName;
	String returnedClass;
	String classe;
	String parametersClass[];
/**
 * MethodSaver constructor comment.
 */
public MethodSaver() {
	super();
}
/**
 * Insert the method's description here.
 * Creation date: (04/09/2002 15.31.11)
 * @param metodo java.lang.reflect.Method
 * @param classe java.lang.Class
 */
public MethodSaver(java.lang.reflect.Method metodo, Class classe) {
    this.classe = classe.getName();
    this.methodName = metodo.getName();
    this.returnedClass = metodo.getReturnType().getName();
    Class cls[] = metodo.getParameterTypes();
    if (cls != null) {
        this.parametersClass = new String[cls.length];
        for (int i = 0; i < cls.length; i++) {
            this.parametersClass[i] = cls[i].getName();
        }
    }
}
/**
 * Insert the method's description here.
 * Creation date: (04/09/2002 15.30.35)
 * @return java.lang.String
 */
public java.lang.String getClasse() {
	return classe;
}
/**
 * Insert the method's description here.
 * Creation date: (04/09/2002 15.35.42)
 * @return java.lang.reflect.Method
 */
public java.lang.reflect.Method getMethod() throws ClassNotFoundException, NoSuchMethodException {
    Class cl = Class.forName(this.classe);
    Class parTypes[] = null;
    if (parametersClass != null) {
        parTypes = new Class[parametersClass.length];
        for (int i = 0; i < parTypes.length; i++) {
            parTypes[i] = Class.forName(parametersClass[i]);
        }
    }
    return cl.getMethod(methodName, parTypes);
}
/**
 * Insert the method's description here.
 * Creation date: (04/09/2002 15.30.36)
 * @return java.lang.String
 */
public java.lang.String getMethodName() {
	return methodName;
}
/**
 * Insert the method's description here.
 * Creation date: (04/09/2002 15.30.36)
 * @return java.lang.String[]
 */
public java.lang.String[] getParametersClass() {
	return parametersClass;
}
/**
 * Insert the method's description here.
 * Creation date: (04/09/2002 15.30.36)
 * @return java.lang.String
 */
public java.lang.String getReturnedClass() {
	return returnedClass;
}
/**
 * Insert the method's description here.
 * Creation date: (04/09/2002 15.30.35)
 * @param newClasse java.lang.String
 */
public void setClasse(java.lang.String newClasse) {
	classe = newClasse;
}
/**
 * Insert the method's description here.
 * Creation date: (04/09/2002 15.30.36)
 * @param newMethodName java.lang.String
 */
public void setMethodName(java.lang.String newMethodName) {
	methodName = newMethodName;
}
/**
 * Insert the method's description here.
 * Creation date: (04/09/2002 15.30.36)
 * @param newParametersClass java.lang.String[]
 */
public void setParametersClass(java.lang.String[] newParametersClass) {
	parametersClass = newParametersClass;
}
/**
 * Insert the method's description here.
 * Creation date: (04/09/2002 15.30.36)
 * @param newReturnedClass java.lang.String
 */
public void setReturnedClass(java.lang.String newReturnedClass) {
	returnedClass = newReturnedClass;
}
}
