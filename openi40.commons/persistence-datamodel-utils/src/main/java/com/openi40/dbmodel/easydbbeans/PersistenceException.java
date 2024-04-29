package com.openi40.dbmodel.easydbbeans;

/**
 * Base Exception for the EasyPersistance part of the framework
 * Creation date: (16/09/2001 17.06.59)
 * @author Paolo Zavalloni : architectures@openi40.org
 */
public class PersistenceException extends Exception {
	Throwable nestedException=null;
/**
 * PersistenceException constructor comment.
 */
public PersistenceException() {
	super();
}
/**
 * PersistenceException constructor comment.
 * @param s java.lang.String
 */
public PersistenceException(String s) {
	super(s);
}
/**
 * Insert the method's description here.
 * Creation date: (23/09/2002 18.06.43)
 * @param nested java.lang.Throwable
 */
public PersistenceException(Throwable nested) {
    super(nested != null ? "Nested message is -->" + nested.getMessage() : "");
    nestedException = nested;
}
public PersistenceException(String string, Throwable th1) {
	super(string,th1);
}
/**
 * Insert the method's description here.
 * Creation date: (23/09/2002 18.07.34)
 */
public void printStackTrace() {
    this.printStackTrace(System.err);
}
/**
 * Insert the method's description here.
 * Creation date: (23/09/2002 18.05.51)
 * @param pw java.io.PrintWriter
 */
public void printStackTrace(java.io.PrintStream pw) {
    if (nestedException != null) {
        pw.println("" + getClass().getName() + " but nested exception is ");
        nestedException.printStackTrace(pw);

    } else
        super.printStackTrace(pw);
}
/**
 * Insert the method's description here.
 * Creation date: (23/09/2002 18.05.51)
 * @param pw java.io.PrintWriter
 */
public void printStackTrace(java.io.PrintWriter pw) {
    if (nestedException != null) {
        pw.println("" + getClass().getName() + " but nested exception is ");
        nestedException.printStackTrace(pw);

    } else
        super.printStackTrace(pw);
}
}
