package com.openi40.dbmodel.easydbbeans;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Vector;

/**
 * Insert the type's description here. Creation date: (20/09/2002 13.01.47)
 * 
 * @author: architectures@openi40.org
 */
public class WhereCondition {
	public String toString() {
		return this.getWhereCondition();
	}

	Vector parameters = new Vector();
	java.util.Vector list = new java.util.Vector();

	Vector subConditions = new Vector();

	Vector subConditionsToggle = new Vector();

	String booleanOperator = "AND";

	/**
	 * WhereCondition constructor comment.
	 */
	public WhereCondition() {
		super();
	}

	public void addCondition(WhereCondition whereCondition) {
		this.subConditions.addElement(whereCondition);
		this.subConditionsToggle.addElement(new Boolean(false));
	}

	public void addNotCondition(WhereCondition whereCondition) {
		this.subConditions.addElement(whereCondition);
		this.subConditionsToggle.addElement(new Boolean(true));
	}

	public void addInCondition(String _field, Vector values) {
		Object vals[] = new Object[values.size()];
		for (int i = 0; i < vals.length; i++) {
			vals[i] = values.elementAt(i);
		}
		addInCondition(_field, vals);
	}

	public void addNotInCondition(String _field, Vector values) {
		addNotInCondition(_field, values.toArray());
	}

	public void addInCondition(String _field, Object[] values) {
		this.addInCondition(_field, true, values);
	}

	public void addNotInCondition(String _field, Object[] values) {
		this.addInCondition(_field, false, values);
	}

	public void addInCondition(String _field, boolean in_or_not_in, Object[] values) {
		boolean ok = false;

		if (values != null && values.length == 1 && values[0] != null) {
			WhereCondition whereCondition = new WhereCondition();
			whereCondition.addEqualCondition(_field, values[0]);
			if (in_or_not_in) {
				addCondition(whereCondition);
			} else {
				addNotCondition(whereCondition);
			}
		} else {
			String _inClause = _field + (in_or_not_in ? " IN " : " NOT IN ") + " (";
			for (int i = 0; values != null && i < values.length; i++) {
				_inClause += values[i] instanceof String ? "'" + values[i].toString().trim() + "'"
						: values[i].toString().trim();
				if (i < values.length - 1) {
					_inClause += ",";
				}
				ok = true;
			}
			_inClause += ")";
			if (ok) {
				addCondition(_inClause);
			}
		}

	}

	public void addCondition(Hashtable key_tables) {
		addCondition(null, key_tables);
	}

	public void addNotCondition(Hashtable keys) {
		WhereCondition whereCondition = new WhereCondition();
		whereCondition.addCondition(keys);
		addCondition(" not (" + whereCondition.toString() + ")");
	}

	public void addCondition(Hashtable key_tables, Vector params) {
		addCondition(null, key_tables, params);
	}

	public void addCondition(String alias, Hashtable key_tables) {
		addCondition(alias, key_tables, null);
	}

	public void addCondition(String alias, Hashtable key_tables, Vector params) {
		Enumeration enum1 = key_tables.keys();
		while (enum1.hasMoreElements()) {
			String key = (String) enum1.nextElement();
			Object value = key_tables.get(key);
			if (params == null) {
				addEqualCondition((alias != null && alias.trim().length() > 0 ? alias.trim() + "." : "") + key, value);
			} else {
				addCondition((alias != null && alias.trim().length() > 0 ? alias.trim() + "." : "") + key + " = ? ");
				params.addElement(value);
			}
		}
	}

	/**
	 * Insert the method's description here. Creation date: (20/09/2002 13.02.10)
	 * 
	 * @param condition java.lang.String
	 */
	public void addCondition(String condition) {
		list.addElement(condition);
	}

	/**
	 * Insert the method's description here. Creation date: (21/09/2002 13.11.19)
	 * 
	 * @param condition java.lang.String
	 * @param value     java.lang.Object
	 */
	public void addEqualCondition(String condition, Object value) {
		list.addElement(condition + "=" + ((value instanceof String || value instanceof Date) ? "'" : "") + value
				+ ((value instanceof String || value instanceof Date) ? "'" : ""));
	}

	/**
	 * Insert the method's description here. Creation date: (21/09/2002 13.11.19)
	 * 
	 * @param condition java.lang.String
	 * @param value     java.lang.Object
	 */
	public void addEqualCondition(String condition, Object value, Vector params) {
		if (params != null) {
			list.addElement(condition + " = ? ");
			params.addElement(value);
		} else {
			list.addElement(condition + " = " + ((value instanceof String || value instanceof Date) ? "'" : "") + value
					+ ((value instanceof String || value instanceof Date) ? "'" : ""));
		}
	}

	/**
	 * Insert the method's description here. Creation date: (05/02/2003 17.18.52)
	 * 
	 * @param keys java.util.Hashtable
	 */
	public void addFindKeysCondition(java.util.Hashtable keys) {
		java.util.Enumeration enum1 = keys.keys();
		while (enum1.hasMoreElements()) {
			String key = (String) enum1.nextElement();
			Object value = keys.get(key);
			addEqualCondition(key, value);
		}
	}

	/**
	 * Insert the method's description here. Creation date: (14/04/2003 20.21.07)
	 * 
	 * @return java.lang.String
	 */
	public java.lang.String getBooleanOperator() {
		return booleanOperator;
	}

	/**
	 * Insert the method's description here. Creation date: (20/09/2002 13.02.40)
	 * 
	 * @return java.lang.String
	 */
	public String getWhereCondition() {

		ArrayList<String> conditions = new ArrayList<String>();
		for (int i = 0; i < list.size(); i++) {
			String condition = list.elementAt(i).toString();
			conditions.add(condition);

		}
		if (this.subConditions.size() > 0) {
			for (int i = 0; i < this.subConditions.size(); i++) {
				WhereCondition _whereCondition = (WhereCondition) this.subConditions.elementAt(i);
				if (!_whereCondition.isEmpty()) {
					Boolean _toggle = (Boolean) this.subConditionsToggle.elementAt(i);
					String condition = (_toggle.booleanValue() ? " NOT " : "") + "("
							+ _whereCondition.getWhereCondition() + ")";
					conditions.add(condition);
				}
			}
		}
		String _conditions = null;
		for (Iterator iterator = conditions.iterator(); iterator.hasNext();) {
			String condition = (String) iterator.next();
			if (_conditions == null)
				_conditions = condition;
			else
				_conditions += condition;
			if (iterator.hasNext()) {
				_conditions += " " + booleanOperator + " ";
			}
		}
		return _conditions;
	}

	public String getNegatedWhereCondition() {
		return " NOT (" + getWhereCondition() + ")";
	}

	/**
	 * Insert the method's description here. Creation date: (20/09/2002 13.04.30)
	 * 
	 * @return boolean
	 */
	public boolean isEmpty() {
		return list.isEmpty() && subConditions.isEmpty();
	}

	/**
	 * Insert the method's description here. Creation date: (14/04/2003 20.21.07)
	 * 
	 * @param newBooleanOperator java.lang.String
	 */
	public void setBooleanOperator(java.lang.String newBooleanOperator) {
		booleanOperator = newBooleanOperator;
	}

	public Vector getParameters() {
		return parameters;
	}

	public void setParameters(Vector parameters) {
		this.parameters = parameters;
	}

	public void addInCondition(String _field, Collection collection) {
		Vector v = new Vector(collection);
		addInCondition(_field, v);

	}
}