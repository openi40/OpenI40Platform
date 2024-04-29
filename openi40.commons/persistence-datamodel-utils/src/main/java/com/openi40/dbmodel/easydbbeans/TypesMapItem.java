package com.openi40.dbmodel.easydbbeans;

/**
 * Insert the type's description here.
 * Creation date: (25/09/2002 14.15.09)
 * @author Paolo Zavalloni : architectures@openi40.org
 */
class TypesMapItem {
    public Class classe = null;
    public int sqlType = -1;
/**
 * Insert the method's description here.
 * Creation date: (25/09/2002 14.17.25)
 * @param classe java.lang.Class
 * @param sqlType int
 */
public TypesMapItem(Class classe, int sqlType) {
    this.classe = classe;
    this.sqlType = sqlType;
}
}
