package com.openi40.dbmodel.easydbbeans;

import java.beans.PropertyDescriptor;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.StringBufferInputStream;
import java.io.StringWriter;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.security.Key;
import java.security.KeyStore;
import java.security.Provider;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.Date;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import java.util.Vector;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.CDATASection;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;


/**
 * XMLObject base class for XML --> <-- OO trasformations complaint to the
 * AggregationObjectModel
 */
public abstract class AutoDescribingObject extends java.beans.Beans implements AggregationObjectModel, java.io.Serializable {
	public final static String UNUPDATABLE_FIELDS[] = new String[] { "luid", "c_time", "m_time", "m_uid", "c_uid" };

	

	public static final int PROPERTY_AS_ATTRIBUTE = 0;

	public static final int PROPERTY_AS_ELEMENT = 1;

	// protected InterfaceDescriptor interfaceDescriptor = null;
	protected Long objectID = null;

	/**
	 * default constructor for XMLObject
	 * 
	 */
	public AutoDescribingObject() {
		// interfaceDescriptor =
		// objectmodel.InterfaceDescriptor.descript(getClass());
		objectID = new Long(hashCode());
	}

	protected static javax.xml.parsers.DocumentBuilder documentBuilder() {
		try {
			javax.xml.parsers.DocumentBuilderFactory factory = javax.xml.parsers.DocumentBuilderFactory.newInstance();
			return factory.newDocumentBuilder();
		} catch (Throwable th) {
			throw new IllegalStateException("Impossibile inizializzare il DocumentBuilder da adoperare");
		}
	}

	

	/**
	 * Crates an aggregated Object of type classType, can be overridden from
	 * subclasses to let the aggregation run for various new rappresentation
	 * Creation date: (17/11/2001 11.44.25)
	 * 
	 * @return XMLObject
	 * @param classType
	 *            java.lang.Class
	 */
	protected AutoDescribingObject thlCreateAggregatedObject(Class classType) {
		try {
			return (AutoDescribingObject) classType.newInstance();
		} catch (InstantiationException exc) {
			throw new IllegalStateException("Cannot instantiate an object of class " + classType.getName());
		} catch (IllegalAccessException exc) {
			throw new IllegalStateException("Cannot instantiate an object of class " + classType.getName() + " constructor inaccessible");
		}
	}

	/**
	 * Returns the interfaceDescriptor for this XMLObject Creation date:
	 * (30/06/2001 18.04.00)
	 * 
	 * @return objectmodel.
	 *         InterfaceDescriptor
	 * @exception java.beans.IntrospectionException
	 *                The exception description.
	 */
	public final InterfaceDescriptor thlDescribeInterface() {
		// return interfaceDescriptor;
		return InterfaceDescriptor.descript(getClass());
	}

	/**
	 * Insert the method's description here. Creation date: (09/04/2003
	 * 22.11.58)
	 * 
	 * @return xmlmapping.XMLObject
	 * @param id
	 *            java.lang.Long
	 */
	public AutoDescribingObject thlFindByObjectID(Long id) {
		return thlFindByObjectID(id, new java.util.Hashtable());
	}

	/**
	 * Insert the method's description here. Creation date: (09/04/2003
	 * 22.11.58)
	 * 
	 * @return xmlmapping.XMLObject
	 * @param id
	 *            java.lang.Long
	 */
	private AutoDescribingObject thlFindByObjectID(Long id, java.util.Hashtable table) {
		if (objectID.equals(id))
			return this;
		table.put(this, this);
		java.util.Vector object_to_search = new java.util.Vector();
		InterfaceDescriptor descriptor = thlDescribeInterface();
		java.util.Enumeration one2many = descriptor.getONE2MANYAggregations();
		while (one2many.hasMoreElements()) {
			ONE2MANYAggregationDescriptor one2manyd = (ONE2MANYAggregationDescriptor) one2many.nextElement();
			for (int index = 0; index < one2manyd.getCountAttribute(this); index++) {
				Object object = one2manyd.getObjectAt(index, this);
				if (object != null && object instanceof AutoDescribingObject) {
					AutoDescribingObject _xml_object = (AutoDescribingObject) object;
					if (_xml_object.thlGetObjectID().equals(id))
						return _xml_object;
					object_to_search.addElement(_xml_object);
				}
			}
		}
		java.util.Enumeration one2one = descriptor.getONE2ONEAggregations();
		while (one2one.hasMoreElements()) {
			ONE2ONEAggregationDescriptor one2oned = (ONE2ONEAggregationDescriptor) one2one.nextElement();
			Object object = one2oned.getAssociated(this);
			if (object != null && object instanceof AutoDescribingObject) {
				AutoDescribingObject _xml_object = (AutoDescribingObject) object;
				if (_xml_object.thlGetObjectID().equals(id))
					return _xml_object;
				object_to_search.addElement(_xml_object);
			}
		}
		AutoDescribingObject object = null;
		for (int index = 0; object == null && index < object_to_search.size(); index++) {
			object = (AutoDescribingObject) object_to_search.elementAt(index);
			object = object.thlFindByObjectID(id, table);
		}
		return object;
	}

	/**
	 * gets the value of a property of this object by its name
	 * 
	 * @return Object
	 */
	public Object thlGet(String propName) {
		try {
			return thlDescribeInterface().getPropertyDescriptor(propName).getReadMethod().invoke(this, null);
		} catch (IllegalAccessException exc) {
			throw new IllegalArgumentException("IllegalAccessException in thlGet(\"" + propName + "\") name of the property:" + propName + " "
					+ exc.toString());
		} catch (java.lang.reflect.InvocationTargetException exc) {
			throw new IllegalArgumentException("InvocationTargetException in thlGet(\"" + propName + "\") name of the property:" + propName + " "
					+ exc.toString());
		}
	}

	/**
	 * Returns the unique ID of this object for internal framework use
	 * 
	 * @return Long
	 */
	public final Long thlGetObjectID() {
		return objectID;
	}

	/**
	 * Returns an object collection by path between objects Creation date:
	 * (30/09/2002 12.38.54)
	 * 
	 * @return java.util.Collection
	 * @param path
	 *            java.lang.String
	 * @exception PersistenceException
	 *                The exception description.
	 * @exception java.sql.SQLException
	 *                The exception description.
	 */
	public java.util.Collection thlGetObjectsByPath(String path) throws PersistenceException {
		return thlGetObjectsByPath(path, new java.util.Vector(), null);
	}

	public java.util.Collection thlGetObjectsByPath(String path, PersistenceTransaction transaction)
			throws PersistenceException {
		return thlGetObjectsByPath(path, new Vector(), transaction);
	}

	/**
	 * Insert the method's description here. Creation date: (30/09/2002
	 * 12.38.54)
	 * 
	 * @return java.util.Collection
	 * @param path
	 *            java.lang.String
	 * @exception PersistenceException
	 *                The exception description.
	 * @exception java.sql.SQLException
	 *                The exception description.
	 */
	protected java.util.Collection thlGetObjectsByPath(String path, java.util.Vector data, PersistenceTransaction transaction)
			throws PersistenceException {
		if (path == null)
			return data;
		if (path.equals("/") || path.trim().length() == 0) {
			data.addElement(this);
		} else {
			if (path.startsWith("/")) {
				path = path.substring(1);
			}
			java.util.StringTokenizer pathTokenizer = new java.util.StringTokenizer(path, "/");
			java.util.Vector nextNodes = new java.util.Vector();
			if (pathTokenizer.hasMoreTokens()) {
				String node = pathTokenizer.nextToken();
				ONE2MANYAggregationDescriptor one2many = thlDescribeInterface().getONE2MANYAggregation(node);
				if (one2many == null)
					one2many = thlDescribeInterface().getONE2MANYAggregation(node.toLowerCase());
				if (one2many != null) {
					int count = 0;
					if (transaction == null) {
						count = one2many.getCountAttribute(this);
					} else {
						count = one2many.getCountAttribute(this, transaction);
					}
					for (int i = 0; i < count; i++) {
						if (transaction == null) {
							nextNodes.addElement(one2many.getObjectAt(i, this));
						} else {
							nextNodes.addElement(one2many.getObjectAt(i, this, transaction));
						}
					}
				} else {
					ONE2ONEAggregationDescriptor one2one = thlDescribeInterface().getONE2ONEAggregation(node);
					if (one2one == null)
						one2one = thlDescribeInterface().getONE2ONEAggregation(node.toLowerCase());
					if (one2one == null)
						throw new IllegalArgumentException("Unknown node " + node + " in path " + path);
					Object related = null;
					if (transaction == null) {
						related = one2one.getAssociated(this);
					} else {
						related = one2one.getAssociated(this, transaction);
					}
					if (related != null) {
						nextNodes.addElement(related);
					}
				}
				path = path.substring(node.length());
				for (int index = 0; index < nextNodes.size(); index++) {
					AutoDescribingObject object = (AutoDescribingObject) nextNodes.elementAt(index);
					object.thlGetObjectsByPath(path, data, transaction);
				}
			}
		}
		return data;
	}

	/**
	 * Returns an array of property descriptor for valid properties of this
	 * class (read the com.zconsultancies object model )
	 * 
	 * @return java.beans.PropertyDescriptor[]
	 */
	public java.beans.PropertyDescriptor[] thlGetPropertyList() {
		return thlDescribeInterface().getManagedDataProperties();
	}

	/**
	 * Returns the TAG name of the Element rappresenting an object of this class
	 * encoded as a string Creation date: (22/09/2001 16.33.03)
	 * 
	 * @return String TAG name
	 */
	public String thlGetXMLElementTag() {
		return getClass().getName().replace('.', '_');
	}

	/**
	 * Sets a property with the name propName to a value passed as "value"
	 * argument
	 * 
	 * @param propName
	 *            String
	 * @param value
	 *            Object
	 */
	public void thlSet(String propName, Object value) {

		try {
			// AUTOMATIZZO UN CAMBIO DI TIPO DI DATI NEL LIMITE DEL POSSIBILE
			PropertyDescriptor descriptor = thlDescribeInterface().getPropertyDescriptor(propName);
			if (value != null && !descriptor.getPropertyType().isAssignableFrom(value.getClass())) {
				if (value instanceof String && Number.class.isAssignableFrom(descriptor.getPropertyType())) {
					value = Double.parseDouble(value.toString());
				}
				if (value instanceof Number && Number.class.isAssignableFrom(descriptor.getPropertyType())) {
					if (descriptor.getPropertyType().equals(Long.class)) {
						value = new Long(((Number) value).longValue());
					} else if (descriptor.getPropertyType().equals(Integer.class)) {
						value = new Integer(((Number) value).intValue());
					} else if (descriptor.getPropertyType().equals(Short.class)) {
						value = new Short(((Number) value).shortValue());
					} else if (descriptor.getPropertyType().equals(Double.class)) {
						value = new Double(((Number) value).doubleValue());
					} else if (descriptor.getPropertyType().equals(Float.class)) {
						value = new Float(((Number) value).floatValue());
					} else if (descriptor.getPropertyType().equals(BigDecimal.class)) {
						value = new BigDecimal(((Number) value).doubleValue());
					}
				}
			}
			descriptor.getWriteMethod().invoke(this, new Object[] { value });
		} catch (IllegalAccessException exc) {
			exc.printStackTrace();
			throw new IllegalArgumentException("IllegalAccessException in thlGet(\"" + propName + "\") name of the property:" + propName + " "
					+ exc.toString() + " value = " + value);
		} catch (java.lang.reflect.InvocationTargetException exc) {
			exc.printStackTrace();
			throw new IllegalArgumentException("InvocationTargetException in thlGet(\"" + propName + "\") name of the property:" + propName + " "
					+ exc.toString() + " value = " + value);
		} catch (Throwable th) {
			th.printStackTrace();
			throw new IllegalArgumentException("InvocationTargetException in thlGet(\"" + propName + "\") name of the property:" + propName + " "
					+ th.toString() + " value = " + value);
		}
	}

	public void thlSetData(Hashtable properties, Vector avoidProps, boolean only_if_present) {
		PropertyDescriptor pds[] = thlGetPropertyList();
		for (int index = 0; pds != null && index < pds.length; index++) {
			String name = pds[index].getName();
			if (avoidProps.contains(name))
				continue;
			Object value = properties.get(name);
			if (value != null || !only_if_present) {
				if (!pds[index].getPropertyType().isAssignableFrom(value.getClass()) && value instanceof String) {
					try {
						value = TypesManager.convertFromString(pds[index].getPropertyType(), value.toString());
					} catch (Throwable e) {
						e.printStackTrace();
						throw new IllegalStateException(e);
					}
				}
				if (pds[index].getPropertyType().equals(Integer.class)) {
					if (!(value instanceof Integer)) {
						value = new Integer(((Number) value).intValue());
					}
				} else if (pds[index].getPropertyType().equals(Long.class)) {
					if (!(value instanceof Long)) {
						value = new Long(((Number) value).longValue());
					}
				} else if (pds[index].getPropertyType().equals(Short.class)) {
					if (!(value instanceof Short)) {
						value = new Short(((Number) value).shortValue());
					}
				} else if (pds[index].getPropertyType().equals(Double.class)) {
					if (!(value instanceof Double)) {
						value = new Double(((Number) value).doubleValue());
					}
				} else if (pds[index].getPropertyType().equals(java.lang.Float.class)) {
					if (!(value instanceof java.lang.Float)) {
						value = new java.lang.Float(((Number) value).floatValue());
					}
				} else if (pds[index].getPropertyType().equals(BigDecimal.class)) {
					if (!(value instanceof BigDecimal)) {
						value = new BigDecimal(((Number) value).doubleValue());
					}
				}
				thlSet(name, value);
			}
		}
	}

	/**
	 * Sets a property value passing the value object encoded as a string
	 * Creation date: (22/09/2001 16.33.03)
	 * 
	 * @param name
	 *            java.lang.String
	 * @param value
	 *            java.lang.String
	 */
	public void thlSetAsString(String name, String value) {
		if (value == null)
			thlSet(name, null);
		PropertyDescriptor descriptor = thlDescribeInterface().getPropertyDescriptor(name);
		try {
			Object object = TypesManager.convertFromString(descriptor.getPropertyType(), value);
			thlSet(name, object);
		} catch (ParseException exc) {
			throw new IllegalStateException("Impossibile creare un'oggetto di tipo " + descriptor.getPropertyType().getName() + " da '" + value + "'");
		}
	}

	/**
	 * Sets a property value passing the value object encoded as a string
	 * Creation date: (22/09/2001 16.33.03)
	 * 
	 * @param name
	 *            java.lang.String
	 * @param value
	 *            java.lang.String
	 */
	public void thlSetAsString(String name, String value, java.util.Locale locale) throws java.text.ParseException {
		if (value == null)
			thlSet(name, null);
		PropertyDescriptor descriptor = thlDescribeInterface().getPropertyDescriptor(name);
		Object object = TypesManager.convertFromString(descriptor.getPropertyType(), value, locale);
		thlSet(name, object);
	}

	/**
	 * sets the value of the unique object ID property that is for internal
	 * framework use
	 * 
	 */
	public final void thlSetObjectID(Long id) {
		objectID = id;
	}

	/**
	 * Insert the method's description here. Creation date: (08/08/2002
	 * 15.55.48)
	 * 
	 * @param element
	 *            org.w3c.dom.Element
	 * @exception java.text.ParseException
	 *                The exception description.
	 */
	public void thlSetProperties(Element element) throws java.text.ParseException {
		String attributeName = null;
		String attribute = null;
		PropertyDescriptor[] pds = thlGetPropertyList();
		String objID = element.getAttribute("thlObjectID");
		if (objID != null) {
			try {
				objectID = new Long(objID);
			} catch (Throwable th) {
			}
		}
		for (int index = 0; index < pds.length; index++) {
			attributeName = pds[index].getName();
			attribute = element.getAttribute(attributeName);
			if (attribute != null)
				attribute = attribute.trim();
			Object value = TypesManager.convertFromString(pds[index].getPropertyType(), attribute);
			if (value instanceof String && value != null && value.toString().trim().equals("")) {
				value = null;
			}
			thlSet(attributeName, value);
		}
	}

	/**
	 * Insert the method's description here. Creation date: (04/01/2003 2.00.29)
	 * 
	 * @return java.lang.String
	 */
	public String toString() {
		StringBuffer buffer = new StringBuffer(getClass().getName() + " object state :");
		PropertyDescriptor descriptors[] = thlGetPropertyList();
		for (int index = 0; index < descriptors.length; index++) {
			buffer.append(descriptors[index].getName());
			buffer.append("-->");
			// Object object = thlGet(descriptors[index].getName());
			Object object;
			try {
				object = descriptors[index].getReadMethod() != null ? descriptors[index].getReadMethod().invoke(this, new Object[0]) : null;
				if (object == null) {
					buffer.append("null");
				} else {
					buffer.append(object);
				}
				buffer.append("\n");
			} catch (IllegalArgumentException e) {

				e.printStackTrace();
			} catch (IllegalAccessException e) {

				e.printStackTrace();
			} catch (InvocationTargetException e) {

				e.printStackTrace();
			}

		}
		return buffer.toString();
	}

	public void thlSetDOMProperty(String property, Document document) throws TransformerException {
		Transformer transformer = TransformerFactory.newInstance().newTransformer();
		StringWriter writer = new StringWriter();
		transformer.setOutputProperty(OutputKeys.ENCODING, "ISO-8859-1");
		transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");
		transformer.transform(new DOMSource(document), new StreamResult(writer));
		thlSetAsString(property, writer.toString());
	}

	

	public Document thlGetDOMProperty(String property) throws SAXException, IOException {
		Object value = thlGet(property);
		if (value != null && value.toString().trim().length() > 0) {
			StringBufferInputStream sbif = new StringBufferInputStream(value.toString());
			Document document = documentBuilder().parse(sbif);
			return document;
		} else
			return null;
	}

	public void thlSetProperties(Attributes attributes) throws Exception {
		PropertyDescriptor descs[] = thlDescribeInterface().getManagedDataProperties();
		for (int i = 0; descs != null && i < descs.length; i++) {
			String _property = descs[i].getName();

			String _value = attributes.getValue(_property);
			if (_value != null) {
				Object value = TypesManager.convertFromString(descs[i].getPropertyType(), _value);
				thlSet(_property, value);
			}

		}
	}

	protected void after_dom_read() {
	}

	public Map thlGetPropertyValues() {
		Hashtable _values = new Hashtable();
		PropertyDescriptor props[] = thlGetPropertyList();
		for (int i = 0; props != null && i < props.length; i++) {
			Object _value = thlGet(props[i].getName());
			if (_value != null) {
				_values.put(props[i].getName(), _value);
			}
		}
		return _values;
	}

	public long thlRegenerateObjectID() {
		double _objectID = Math.random() * 100000000.0;
		this.objectID = new Long((long) _objectID);
		return this.objectID.longValue();
	}

	public Properties toProperties(String propertiesPrefix) {
		Properties props = new Properties();
		PropertyDescriptor pds[] = thlDescribeInterface().getManagedDataProperties();
		for (int i = 0; i < pds.length; i++) {
			String name = pds[i].getName();
			Object value = thlGet(name);
			if (value != null) {
				String propName = (propertiesPrefix != null ? propertiesPrefix : "") + name;
				props.setProperty(propName, value.toString().trim());
			}
		}
		return props;
	}

	public Properties toProperties() {
		return this.toProperties(null);
	}

	public void fromProperties(Properties props, String propertiesPrefix) throws ParseException {
		PropertyDescriptor pds[] = thlDescribeInterface().getManagedDataProperties();
		for (int i = 0; i < pds.length; i++) {
			String name = pds[i].getName();
			String propName = (propertiesPrefix != null ? propertiesPrefix : "") + name;
			String value = props.getProperty(propName);
			if (value != null) {
				Object valueObject = TypesManager.convertFromString(pds[i].getPropertyType(), value);
				thlSet(name, valueObject);
			} else {
				thlSet(name, null);
			}
		}
	}

	public void fromProperties(Properties props) throws ParseException {
		fromProperties(props, null);
	}
	/*************************************************************************
	 * RItorna la chiave (primaria) che identifica univocamente l'oggetto
	 * @return
	 */
	public String thlGetEntityKey() {
		return toString();
	};
}