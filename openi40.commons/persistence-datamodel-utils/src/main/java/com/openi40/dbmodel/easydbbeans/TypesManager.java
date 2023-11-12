package com.openi40.dbmodel.easydbbeans;

import java.sql.Blob;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLXML;
import java.sql.Time;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.HashMap;
import java.util.Iterator;
import java.util.StringTokenizer;
import java.util.Vector;

/**
 * Utility class for the management of Base class types convertions Creation
 * date: (30/06/2001 16.55.45)
 * 
 * @author: ZaVA
 */
public class TypesManager {
	public static String TRUE = "1";

	public static String FALSE = "0";

	private static java.util.HashMap formats = new java.util.HashMap();

	public static Class showTypes[] = { String.class, Long.class, Integer.class, Short.class, Float.class, Double.class, java.sql.Date.class,
			java.sql.Time.class, java.sql.Timestamp.class, Boolean.class, Double.class, java.math.BigDecimal.class, java.sql.SQLXML.class, Blob.class };

	private static Class JavaTypes[] = { String.class, String.class, String.class, String.class, Long.class, Integer.class, Short.class, Float.class,
			Float.class, Double.class, Boolean.class, java.sql.Date.class, java.sql.Time.class, java.sql.Timestamp.class, Boolean.class,
			Boolean.class, Double.class, java.math.BigDecimal.class, java.sql.SQLXML.class, Blob.class };

	private static int sqlTypes[] = { java.sql.Types.CHAR, java.sql.Types.VARCHAR, java.sql.Types.CLOB, java.sql.Types.LONGVARCHAR,
			java.sql.Types.BIGINT, java.sql.Types.INTEGER, java.sql.Types.SMALLINT, java.sql.Types.FLOAT, java.sql.Types.REAL, java.sql.Types.DOUBLE,
			java.sql.Types.CHAR, java.sql.Types.DATE, java.sql.Types.TIME, java.sql.Types.TIMESTAMP, java.sql.Types.CHAR, java.sql.Types.BIT,
			java.sql.Types.NUMERIC, java.sql.Types.DECIMAL, java.sql.Types.SQLXML, java.sql.Types.BLOB };

	private static java.util.ArrayList sql2java = new java.util.ArrayList();

	private static java.util.ArrayList typesVector = new java.util.ArrayList();
	private static HashMap sql2javaMap = new HashMap();
	private static HashMap java2sqlMap = new HashMap();
	static {
		for (int index = 0; index < JavaTypes.length; index++) {
			typesVector.add(JavaTypes[index]);
			Integer sqlType = new Integer(sqlTypes[index]);
			Class javaType = JavaTypes[index];
			if (!sql2javaMap.containsKey(sqlType)) {
				sql2javaMap.put(sqlType, javaType);
			}
			if (!java2sqlMap.containsKey(javaType)) {
				java2sqlMap.put(javaType, sqlType);
			}
		}
		sql2java.add(new TypesMapItem(String.class, java.sql.Types.CHAR));
		sql2java.add(new TypesMapItem(String.class, java.sql.Types.NCHAR));
		sql2java.add(new TypesMapItem(String.class, java.sql.Types.NVARCHAR));
		sql2java.add(new TypesMapItem(String.class, java.sql.Types.VARCHAR));
		sql2java.add(new TypesMapItem(String.class, java.sql.Types.CLOB));
		sql2java.add(new TypesMapItem(String.class, java.sql.Types.LONGVARCHAR));
		sql2java.add(new TypesMapItem(Long.class, java.sql.Types.BIGINT));
		sql2java.add(new TypesMapItem(Integer.class, java.sql.Types.INTEGER));
		sql2java.add(new TypesMapItem(Short.class, java.sql.Types.SMALLINT));
		sql2java.add(new TypesMapItem(Float.class, java.sql.Types.FLOAT));
		sql2java.add(new TypesMapItem(Float.class, java.sql.Types.REAL));
		sql2java.add(new TypesMapItem(Double.class, java.sql.Types.DOUBLE));
		sql2java.add(new TypesMapItem(java.sql.Date.class, java.sql.Types.DATE));
		
		sql2java.add(new TypesMapItem(java.sql.Time.class, java.sql.Types.TIME));
		sql2java.add(new TypesMapItem(java.sql.Timestamp.class, java.sql.Types.TIMESTAMP));
		sql2java.add(new TypesMapItem(java.math.BigDecimal.class, java.sql.Types.NUMERIC));
		sql2java.add(new TypesMapItem(java.math.BigDecimal.class, java.sql.Types.DECIMAL));
		sql2java.add(new TypesMapItem(Boolean.class, java.sql.Types.BIT));
		sql2java.add(new TypesMapItem(SQLXML.class, java.sql.Types.SQLXML));
		for (Iterator iterator = sql2java.iterator(); iterator.hasNext();) {
			TypesMapItem typeMap = (TypesMapItem) iterator.next();
			java2sqlMap.put(new Integer(typeMap.sqlType), typeMap.classe);
		}
	}

	/**
	 * 
	 * Assigns a Format object to convert to and from strings for the class
	 * javaType Creation date: (29/08/2002 17.49.29)
	 * 
	 * @param javaType
	 *            java.lang.Class
	 * @param format
	 *            java.text.Format
	 */
	public static void assignFormat(Class javaType, java.text.Format format) {
		formats.put(javaType, format);
	}

	/**
	 * Converts from a String to an Object of the given class without
	 * Nationalized format management Creation date: (30/06/2001 17.11.52)
	 * 
	 * @return java.lang.Object
	 * @param clss
	 *            java.lang.Class The class of the object to be returned
	 * @param value
	 *            java.lang.String
	 */
	public static Object convertFromString(Class clss, String value) throws java.text.ParseException {
		try {
			if (value == null)
				return null;
			if (value.trim().equals("")) {
				if (Number.class.isAssignableFrom(clss) || java.sql.Date.class.isAssignableFrom(clss)) {
					return null;
				}
			}
			value = value.trim();
			if (clss.equals(Class.class)) {
				return Class.forName(value);
			}
			if (java.sql.Date.class.isAssignableFrom(clss)) {
				if (value.trim().length() == 0)
					return null;
				try {
					return java.sql.Date.valueOf(value);
				} catch (Exception exc) {
					// WORKAROUND SE IL FORMATO DI DATE NON E' CORRETTO
					StringTokenizer tokenizer = new StringTokenizer(value.trim(), "-");
					String anno = null, mese = null, giorno = null;
					if (tokenizer.hasMoreTokens()) {
						anno = tokenizer.nextToken();
					}
					if (tokenizer.hasMoreTokens()) {
						mese = tokenizer.nextToken();
					}
					if (tokenizer.hasMoreTokens()) {
						giorno = tokenizer.nextToken();
					}
					if (anno != null && mese != null && giorno != null) {
						int _anno = Integer.parseInt(anno);
						int _mese = Integer.parseInt(mese);
						int _giorno = Integer.parseInt(giorno);
						Date date = new Date(_anno, _mese - 1, _giorno);
						return date;
					} else {
						throw new IllegalArgumentException("La stringa " + value + " non   un formato date standard");
					}
				}
			} else if (java.sql.Timestamp.class.isAssignableFrom(clss)) {
				if (value.trim().length() == 0)
					return null;
				return java.sql.Timestamp.valueOf(value);
			} else if (java.sql.Time.class.isAssignableFrom(clss)) {
				if (value.trim().length() == 0)
					return null;
				return java.sql.Time.valueOf(value);
			} else
				try {
					// GUARDO SE C'E UN COSTRUTTORE CHE HA UN PARAMETRO STRINGA
					return convertFromStringUsingConstructor(clss, value);
				} catch (Throwable th) {
				}
			return convertFromStringUsingConstructor(clss, value);
		} catch (Exception exc) {
			throw new IllegalStateException("Errore : impossibile creare oggetto di tipo " + clss.getName()
					+ " con costruttore con parametro una stringa di valore \"" + value + "\"");
		}
	}

	/**
	 * Insert the method's description here. Creation date: (30/06/2001
	 * 17.11.52)
	 * 
	 * @return java.lang.Object
	 * @param clss
	 *            java.lang.Class
	 * @param value
	 *            java.lang.String
	 * @param nation
	 *            java.lang.String
	 */
	public static Object convertFromString(Class clss, String value, String nation) {
		try {
			if (value == null)
				return null;
			if (value.trim().equals("")) {
				if (Number.class.isAssignableFrom(clss)) {
					return null;
				}
			}
			if (clss.equals(Class.class)) {
				return Class.forName(value);
			}
			if (java.sql.Date.class.isAssignableFrom(clss)) {
				return java.sql.Date.valueOf(value);
			} else {
				Class types[] = { String.class };
				Object params[] = { value };
				java.lang.reflect.Constructor constructor = clss.getConstructor(types);
				return constructor.newInstance(params);
			}
		} catch (NoSuchMethodException exc) {
			throw new IllegalStateException("Errore : impossibile creare oggetto di tipo " + clss.getName()
					+ " con costruttore con parametro una stringa");
		} catch (IllegalAccessException exc) {
			throw new IllegalStateException("Errore : impossibile creare oggetto di tipo " + clss.getName()
					+ " con costruttore con parametro una stringa");
		} catch (InstantiationException exc) {
			throw new IllegalStateException("Errore : impossibile creare oggetto di tipo " + clss.getName()
					+ " con costruttore con parametro una stringa");
		} catch (java.lang.reflect.InvocationTargetException exc) {
			throw new IllegalStateException("Errore : impossibile creare oggetto di tipo " + clss.getName()
					+ " con costruttore con parametro una stringa");
		} catch (ClassNotFoundException exc) {
			throw new IllegalStateException("Errore : impossibile creare oggetto di tipo " + clss.getName()
					+ " con costruttore con parametro una stringa");
		}
	}

	/**
	 * Converts from a String to an Object of the given class with Nationalized
	 * format management Creation date: (30/06/2001 17.11.52)
	 * 
	 * @return java.lang.Object
	 * @param clss
	 *            java.lang.Class The class of the object to be returned
	 * @param value
	 *            java.lang.String
	 * @param locale
	 *            java.util.Locale The locale to provide
	 */
	public static Object convertFromString(Class clss, String value, java.util.Locale locale) throws java.text.ParseException {
		if (clss.equals(String.class)) {
			return value;
		}
		if (value == null) {
			if (clss.equals(Boolean.class)) {
				return new Boolean(false);
			}
			return null;
		}
		// PROVO CON UN FORMATO CUSTOMIZZATO
		java.text.Format format = getFormat(clss);
		if (format != null) {
			Object object = format.parseObject(value.trim());
			if (object != null) {
				if (object instanceof java.util.Date) {
					java.util.Date data = (java.util.Date) object;
					if (clss.equals(java.sql.Date.class)) {
						return new java.sql.Date(data.getTime());
					} else if (clss.equals(java.util.Date.class)) {
						return object;
					} else if (clss.equals(Time.class)) {
						return new Time(data.getTime());
					} else if (clss.equals(Timestamp.class)) {
						return new Timestamp(data.getTime());
					}
				} else if (object instanceof Number) {
					Number number = (Number) object;
					if (clss.equals(Integer.class)) {
						return new Integer(number.intValue());
					} else if (clss.equals(Long.class)) {
						return new Long(number.longValue());
					} else if (clss.equals(Short.class)) {
						return new Short(number.shortValue());
					} else if (clss.equals(Double.class)) {
						return new Double(number.doubleValue());
					} else if (clss.equals(Float.class)) {
						return new Float(number.floatValue());
					} else if (clss.equals(java.math.BigDecimal.class)) {
						return new java.math.BigDecimal(number.doubleValue());
					}
				}
			}
		}
		try {
			if (value.trim().equals("")) {
				if (Number.class.isAssignableFrom(clss)) {
					return null;
				}
			}
			if (clss.equals(Class.class)) {
				return Class.forName(value);
			}
			if (java.sql.Time.class.isAssignableFrom(clss)) { // TENTO DI
				// PRENDERE I
				// DATI CON UN
				// FORMATO DATA
				// REGOLARE
				java.text.DateFormat df = java.text.SimpleDateFormat.getTimeInstance(java.text.DateFormat.DEFAULT, locale);
				java.util.Date dt = df.parse(value);
				return new java.sql.Time(dt.getTime());
			} else if (java.sql.Timestamp.class.isAssignableFrom(clss)) { // TENTO
				// DI
				// PRENDERE
				// I
				// DATI
				// CON
				// UN
				// FORMATO
				// DATA
				// REGOLARE
				java.text.DateFormat df = java.text.SimpleDateFormat.getDateTimeInstance(java.text.DateFormat.DATE_FIELD,
						java.text.DateFormat.DEFAULT, locale);
				java.util.Date dt = df.parse(value);
				return new java.sql.Timestamp(dt.getTime());
			} else if (java.sql.Date.class.isAssignableFrom(clss)) { // TENTO
				// DI
				// PRENDERE
				// I
				// DATI
				// CON
				// UN
				// FORMATO
				// DATA
				// REGOLARE
				java.text.DateFormat df = java.text.DateFormat.getDateInstance(java.text.DateFormat.DATE_FIELD, locale);
				java.util.Date dt = df.parse(value);
				return new java.sql.Date(dt.getTime());
			} else if (Number.class.isAssignableFrom(clss)) {
				try { // TENTO COL FORMATO CLASSICO INTERNO
					return convertFromStringUsingConstructor(clss, value);
				} catch (Throwable th) {
				} // TENTO DI GESTIRE I NUMERI DI UN FORMATO NAZIONALIZZATO
				java.text.NumberFormat nf = java.text.NumberFormat.getInstance(locale);
				try {
					Number n = nf.parse(value);
					if (clss.equals(Integer.class)) {
						return new Integer(n.intValue());
					} else if (clss.equals(Long.class)) {
						return new Long(n.longValue());
					} else if (clss.equals(Short.class)) {
						return new Short(n.shortValue());
					} else if (clss.equals(Float.class)) {
						return new Float(n.floatValue());
					} else if (clss.equals(Double.class)) {
						return new Double(n.doubleValue());
					}
				} catch (NumberFormatException exc) { // COL FORMATO
					// NAZIONALIZZATO SIAMO
					// ANDATI MALE PROVO
					// TROVANDO UN APPOSITO
					// COSTRUTTORE
					try {
						return convertFromStringUsingConstructor(clss, value);
					} catch (Throwable th) {
						throw new IllegalStateException("Tentato di creare " + clss.getName() + " da stringa \"" + value
								+ "\" con nazionalizzazione " + locale + " e con costruttore con parametro stringa,entrambi falliti");
					}
				} catch (java.text.ParseException exc) { // COL FORMATO
					// NAZIONALIZZATO
					// SIAMO ANDATI MALE
					// PROVO TROVANDO UN
					// APPOSITO
					// COSTRUTTORE
					try {
						return convertFromStringUsingConstructor(clss, value);
					} catch (Throwable th) {
						throw new IllegalStateException("Tentato di creare " + clss.getName() + " da stringa \"" + value
								+ "\" con nazionalizzazione " + locale + " e con costruttore con parametro stringa,entrambi falliti");
					}
				}
			}
			return convertFromStringUsingConstructor(clss, value);
		} catch (Exception exc) {
			throw new IllegalStateException("Errore : impossibile creare oggetto di tipo " + clss.getName()
					+ " con costruttore con parametro una stringa di valore \"" + value + "\"");
		}
	}

	/**
	 * Tries to convert a String into an Object using constructor Creation date:
	 * (30/06/2001 17.11.52)
	 * 
	 * @return java.lang.Object
	 * @param clss
	 *            java.lang.Class
	 * @param value
	 *            java.lang.String
	 * @param nation
	 *            java.lang.String
	 */
	private static Object convertFromStringUsingConstructor(Class clss, String value) throws Exception {
		if (value == null)
			return null;
		Class types[] = { String.class };
		Object params[] = { value };
		java.lang.reflect.Constructor constructor = clss.getConstructor(types);
		return constructor.newInstance(params);
	}

	/**
	 * Insert the method's description here. Creation date: (30/06/2001
	 * 17.11.04)
	 * 
	 * @return java.lang.String
	 * @param object
	 *            java.lang.Object
	 */
	public static String convertToString(Object object) {
		if (object instanceof Class) {
			return ((Class) object).getName();
		}
		return object.toString();
	}

	/**
	 * Insert the method's description here. Creation date: (30/06/2001
	 * 17.11.04)
	 * 
	 * @return java.lang.String
	 * @param object
	 *            java.lang.Object
	 */
	public static String convertToString(Object object, java.util.Locale locale) {
		if (object == null)
			return null;
		// PROVO CON UN FORMATO CUSTOMIZZATO
		java.text.Format format = getFormat(object.getClass());
		if (format != null) {
			return format.format(object);
		}
		if (object instanceof Class) {
			return ((Class) object).getName();
		}
		// AGGIUNTI IF INNESCATI PER BUG BONFIGLIOLI
		if (object instanceof Number) {
			if (object instanceof Long) {
				return object.toString();
			} else if (object instanceof Integer) {
				return object.toString();
			} else if (object instanceof Short) {
				return object.toString();
			} else {
				java.text.NumberFormat nf = java.text.NumberFormat.getInstance(locale);
				return nf.format(object);
			}
		} else if (object instanceof java.sql.Date) {
			java.text.DateFormat df = java.text.DateFormat.getDateInstance(java.text.DateFormat.DATE_FIELD, locale);
			java.util.Date dt = (java.util.Date) object;
			return df.format(dt);
		} else if (object instanceof java.sql.Timestamp) {
			java.text.DateFormat df = java.text.SimpleDateFormat.getDateTimeInstance(java.text.DateFormat.DATE_FIELD, java.text.DateFormat.DEFAULT,
					locale);
			// java.text.SimpleDateFormat.getDateTimeInstance()
			java.sql.Timestamp dt = (java.sql.Timestamp) object;
			return df.format(dt);
		} else if (object instanceof java.sql.Time) {
			java.text.DateFormat df = java.text.DateFormat.getTimeInstance(java.text.DateFormat.DEFAULT, locale);
			java.sql.Time dt = (java.sql.Time) object;
			return df.format(dt);
		}
		return object.toString();
	}

	public static void fillPreparedStatementParameter(java.sql.PreparedStatement ps, boolean nullManagement, Vector params) throws SQLException {
		for (int i = 0; i < params.size(); i++) {
			Object value = params.elementAt(i);

			fillPreparedStatementParameter(ps, nullManagement, value, value.getClass(), i + 1);
		}
	}

	/**
	 * Insert the method's description here. Creation date: (23/09/2001
	 * 14.25.14)
	 * 
	 * @param ps
	 *            java.sql.PreparedStatement
	 * @param fields
	 *            com.zconsultancies.threelayers.persistence.PersistenceField[]
	 * @exception java.sql.SQLException
	 *                The exception description.
	 */
	public static void fillPreparedStatementParameter(java.sql.PreparedStatement ps, boolean nullManagement, Object object, Class objClass, int index)
			throws java.sql.SQLException {

		int sqlType = TypesManager.getSQLMappedType(objClass);
		if (objClass.equals(String.class)) {
			if (object == null && nullManagement) {
				ps.setNull(index, java.sql.Types.VARCHAR);
			} else {
				if (object == null)
					object = "";
				ps.setString(index, object.toString());
			}
		} else if (objClass.equals(Integer.class)) {
			if (object == null && nullManagement) {
				ps.setNull(index, java.sql.Types.INTEGER);
			} else {
				if (object == null)
					object = new Integer(0);
				ps.setInt(index, ((Number) object).intValue());
			}
		} else if (objClass.equals(Long.class)) {
			if (object == null && nullManagement) {
				ps.setNull(index, java.sql.Types.BIGINT);
			} else {
				if (object == null)
					object = new Integer(0);
				ps.setLong(index, ((Number) object).longValue());
			}
		} else if (objClass.equals(Short.class)) {
			if (object == null && nullManagement) {
				ps.setNull(index, java.sql.Types.SMALLINT);
			} else {
				if (object == null)
					object = new Integer(0);
				ps.setShort(index, ((Number) object).shortValue());
			}
		} else if (objClass.equals(Double.class)) {
			if (object == null && nullManagement) {
				ps.setNull(index, java.sql.Types.DOUBLE);
			} else {
				if (object == null)
					object = new Integer(0);
				ps.setDouble(index, ((Number) object).doubleValue());
			}
		} else if (objClass.equals(Float.class)) {
			if (object == null && nullManagement) {
				ps.setNull(index, java.sql.Types.FLOAT);
			} else {
				if (object == null)
					object = new Integer(0);
				ps.setFloat(index, ((Number) object).floatValue());
			}
		} else if (objClass.equals(java.math.BigDecimal.class)) {
			// AGGIUNTO BIGDECIMALS 18-sett-2002
			// WORKARAUND PER DB2 6.1
			boolean isDB2 = ps.getClass().getName().indexOf(".db2.") != -1;
			if (object == null && nullManagement) {
				ps.setNull(index, java.sql.Types.DECIMAL);
			} else {
				if (!isDB2) {
					ps.setBigDecimal(index, (java.math.BigDecimal) object);

				} else {
					if (object == null) {
						ps.setDouble(index, 0.0);
					} else {
						ps.setDouble(index, ((java.math.BigDecimal) object).doubleValue());
					}
				}
			}
		} else if (objClass.equals(java.sql.Time.class)) {
			if (object == null && nullManagement) {
				ps.setNull(index, java.sql.Types.TIME);
			} else {
				ps.setTime(index, (java.sql.Time) object);
			}
		} else if (objClass.equals(java.sql.Timestamp.class) || java.sql.Timestamp.class.isAssignableFrom(objClass)) {
			if (object == null && nullManagement) {
				ps.setNull(index, java.sql.Types.TIMESTAMP);
			} else {
				ps.setTimestamp(index, (java.sql.Timestamp) object);
			}
		} else if (objClass.equals(java.sql.Date.class)) {
			if (object == null && nullManagement) {
				ps.setNull(index, java.sql.Types.DATE);
			} else {
				ps.setDate(index, (java.sql.Date) object);
			}
		} else if (objClass.equals(Boolean.class)) {
			if (object == null || object.toString().equals("false")) {
				ps.setString(index, FALSE);
			} else {
				ps.setString(index, TRUE);
			}
		} else if (objClass.equals(Blob.class)) {
			if (object == null) {
				ps.setNull(index, sqlType);
			} else {
				ps.setBlob(index, (Blob) object);
			}
		} else
			throw new IllegalStateException("Tipo " + objClass.getName() + " non gestito");
	}

	/**
	 * Insert the method's description here. Creation date: (29/08/2002
	 * 17.51.21)
	 * 
	 * @return java.text.Format
	 * @param javaType
	 *            java.lang.Class
	 */
	public static java.text.Format getFormat(Class javaType) {
		return (java.text.Format) formats.get(javaType);
	}

	/**
	 * Insert the method's description here. Creation date: (23/09/2001
	 * 17.33.17)
	 * 
	 * @return java.lang.Class
	 * @param sqlType
	 *            int
	 */
	public static Class getJavaMappedType(int sqlType) {
		if (sqlType == Types.TINYINT) {
			return Short.class;
		}
		if (sqlType == 1111)
			return String.class;
		// Class type = (Class) sql2javaMap.get(new Integer(sqlType));
		// if (type != null)
		// return type;
		for (int index = 0; index < sql2java.size(); index++) {
			TypesMapItem item = (TypesMapItem) sql2java.get(index);
			if (item.sqlType == sqlType)
				return item.classe;
		}
		for (int index = 0; index < sqlTypes.length; index++) {
			if (sqlTypes[index] == sqlType) {
				return JavaTypes[index];
			}
		}

		if (sqlType == -16) {
			return String.class;
		}
		if (sqlType == -15) {
			return String.class;
		}
		throw new IllegalArgumentException("Tipo SQL = " + sqlType + " non gestito");
	}

	/**
	 * Insert the method's description here. Creation date: (18/09/2002
	 * 16.53.45)
	 * 
	 * @return java.lang.Object
	 * @param rs
	 *            java.sql.ResultSet
	 * @param type
	 *            java.lang.Class
	 * @exception java.sql.SQLException
	 *                The exception description.
	 */
	public static Object getObject(ResultSet rs, int tableField, Class type) throws java.sql.SQLException {
		try {
			if (type.equals(String.class)) {
				String value = rs.getString(tableField);
				return ((value == null ? null : value.trim()));
			} else if (type.equals(Long.class)) {
				return (new Long(rs.getLong(tableField)));
			} else if (type.equals(Integer.class)) {
				return (new Integer(rs.getInt(tableField)));
			} else if (type.equals(Short.class)) {
				return (new Short(rs.getShort(tableField)));
			} else if (type.equals(Boolean.class)) {
				String value = rs.getString(tableField);
				if (value == null) {
					return (null);
				} else {
					return (new Boolean(value.equals("1") || value.equalsIgnoreCase("true")));
				}
			} else if (type.equals(Double.class)) {
				return (new Double(rs.getDouble(tableField)));
			} else if (type.equals(Float.class)) {
				return (new Float(rs.getFloat(tableField)));
			} else if (type.equals(java.math.BigDecimal.class)) {
				// WORKARAUND PER PROBLEMI DRIVER DB2
				Object value = null;
				if (rs.getClass().getName().toLowerCase().indexOf("ibm") == -1
						|| (rs.getClass().getName().toLowerCase().indexOf("com.ibm.as400.access") != -1)) {
					value = rs.getObject(tableField);
				} else {
					value = (rs.getBigDecimal(tableField));
				}
				if (value != null && value instanceof Number) {
					return new java.math.BigDecimal(((Number) value).doubleValue());
				} else {
					if (value != null)
						System.err.println("Errore invece che un Number si   letto un " + value.getClass().getName() + "=" + value);
					else
						return new java.math.BigDecimal((double) 0);
				}
			} else if (type.equals(java.sql.Date.class)) {
				return (rs.getDate(tableField));
			} else if (type.equals(java.sql.Time.class)) {
				return (rs.getTime(tableField));
			} else if (type.equals(java.sql.Timestamp.class)) {
				return (rs.getTimestamp(tableField));
			}
			System.err.println("TYPE " + type.getName() + " not managed");
		} catch (Throwable exc) {
			System.err.println("Problems with the field " + tableField + " type needed whas --> " + type.getName());
			exc.printStackTrace();
			throw new SQLException(exc.getMessage());
		}
		return null;
	}

	public static Object getObject(ResultSet rs, String tableField, Class type) throws java.sql.SQLException {
		try {
			if (type.equals(String.class)) {
				String value = rs.getString(tableField);
				return ((value == null ? null : value.trim()));
			} else if (type.equals(Long.class)) {
				return (new Long(rs.getLong(tableField)));
			} else if (type.equals(Integer.class)) {
				return (new Integer(rs.getInt(tableField)));
			} else if (type.equals(Short.class)) {
				return (new Short(rs.getShort(tableField)));
			} else if (type.equals(Boolean.class)) {
				String value = rs.getString(tableField);
				if (value == null) {
					return (null);
				} else {
					return (new Boolean(value.equals("1") || value.equalsIgnoreCase("true")));
				}
			} else if (type.equals(Double.class)) {
				return (new Double(rs.getDouble(tableField)));
			} else if (type.equals(Float.class)) {
				return (new Float(rs.getFloat(tableField)));
			} else if (type.equals(java.math.BigDecimal.class)) {
				// WORKARAUND PER PROBLEMI DRIVER DB2
				Object value = null;
				if (rs.getClass().getName().toLowerCase().indexOf("ibm") == -1) {
					value = rs.getObject(tableField);
				} else {
					value = new Double(rs.getDouble(tableField));
				}
				if (value != null && value instanceof Number) {
					return new java.math.BigDecimal(((Number) value).doubleValue());
				} else {
					if (value != null)
						System.err.println("Errore invece che un Number si   letto un " + value.getClass().getName() + "=" + value);
					else
						return new java.math.BigDecimal((double) 0);
				}
			} else if (type.equals(java.sql.Date.class)) {
				return (rs.getDate(tableField));
			} else if (type.equals(java.sql.Time.class)) {
				return (rs.getTime(tableField));
			} else if (type.equals(java.sql.Timestamp.class)) {
				return (rs.getTimestamp(tableField));
			}
			System.err.println("TYPE " + type.getName() + " not managed");
		} catch (SQLException exc) {
			System.err.println("Problems with the field " + tableField + " type needed whas --> " + type.getName());
			SQLException exc1 = exc;
			int nested = 0;
			while (exc1 != null) {
				
				exc1.printStackTrace();
				nested++;
				exc1 = exc1.getNextException();
			}
			throw new IllegalStateException(exc.getMessage());
		} catch (Throwable exc) {
			System.err.println("Problems with the field " + tableField + " type needed whas --> " + type.getName());
			exc.printStackTrace();
			throw new IllegalStateException(exc.getMessage());
		}
		return null;
	}

	/**
	 * Insert the method's description here. Creation date: (08/07/2001
	 * 23.59.03)
	 * 
	 * @return int
	 * @param clss
	 *            java.lang.Class
	 */
	public static int getSQLMappedType(Class clss) {
		Integer _sqltype = (Integer) java2sqlMap.get(clss);
		if (_sqltype != null) {
			return _sqltype.intValue();
		}
		int index = typesVector.indexOf(clss);
		if (index < 0) {
			int j = 0;
			for (Iterator iterator = typesVector.iterator(); iterator.hasNext();) {
				Class type = (Class) iterator.next();
				if (type.isAssignableFrom(clss)) {
					index = j;
				}
				j++;
			}
		}
		if (index < 0)
			throw new IllegalArgumentException("Class " + clss.getName() + " it is not managed from Object <--> RDBMS Mapping");
		return sqlTypes[index];
	}

	/**
	 * Insert the method's description here. Creation date: (30/06/2001
	 * 17.09.23)
	 * 
	 * @return boolean
	 * @param object
	 *            java.lang.Object
	 */
	public static boolean isManaged(Class objectClass) {
		return typesVector.contains(objectClass);
	}

	/**
	 * Insert the method's description here. Creation date: (30/06/2001
	 * 17.09.23)
	 * 
	 * @return boolean
	 * @param object
	 *            java.lang.Object
	 */
	public static boolean isManaged(Object object) {
		return typesVector.contains(object.getClass());
	}

	
}
