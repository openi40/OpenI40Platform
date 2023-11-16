package com.openi40.mes.metamessaging.model.jsonutil;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Collection;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.openi40.mes.metamessaging.model.AbstractOI40IOTMetaMessage;
import com.openi40.mes.metamessaging.model.AbstractOI40MetaMessage;

public class MetaEnrichedSerializer extends StdSerializer<AbstractOI40MetaMessage> {
	public MetaEnrichedSerializer() {
		this(null);
	}

	public MetaEnrichedSerializer(Class t) {
		super(t);
	}

	@Override
	public Class<AbstractOI40MetaMessage> handledType() {

		return AbstractOI40MetaMessage.class;
	}

	private void serializeObjectWithMeta(Object value, String fieldName, JsonGenerator gen, SerializerProvider provider)
			throws IOException {
		if (value != null) {
			Class type = value.getClass();
			boolean isArray = type.isArray() || Collection.class.isAssignableFrom(type);
			if (!isArray) {
				if (fieldName == null)
					gen.writeStartObject();
				else
					gen.writeObjectFieldStart(fieldName);
				gen.writeStringField(MetaMsgJsonUtil.META_CLASS_FIELD, value.getClass().getName());

			} else {
				if (fieldName == null)
					gen.writeStartArray();
				else
					gen.writeArrayFieldStart(fieldName);
			}
			serializeAttributes(value, type, gen, provider);
			if (isArray) {

				if (type.isArray()) {
					Class elementType = type.getComponentType();
					if (elementType.isPrimitive()) {
						if (elementType.equals(byte.class)) {
							byte buffer[] = (byte[]) value;
							
						} else if (elementType.equals(int.class)) {

						} else if (elementType.equals(long.class)) {

						} else if (elementType.equals(boolean.class)) {

						}
					} else {
						Object array[] = (Object[]) value;
						for (Object object : array) {
							serializeObjectWithMeta(object, null, gen, provider);
						}
					}
				} else {
					Collection collection = (Collection) value;
					for (Object object : collection) {
						serializeObjectWithMeta(object, null, gen, provider);
					}
				}
			}
			if (!isArray) {

				gen.writeEndObject();
			} else {
				gen.writeEndArray();
			}
		}
	}

	private void serializeAttributes(Object value, Class type, JsonGenerator gen, SerializerProvider provider) {
		Method[] methods = type.getMethods();
		if (methods != null) {
			for (Method method : methods) {
				if (method.getName().startsWith("get") && !method.getName().equals("getClass")
						&& method.getParameterCount() == 0 && Modifier.isPublic(method.getModifiers())
						&& !Modifier.isStatic(method.getModifiers())) {
					try {
						String attributeName = method.getName().substring(3);
						Object attribute = method.invoke(value);
						if (attribute != null) {
							Class attributeType = attribute.getClass();
							if (attributeType.isPrimitive() || attributeType.getName().startsWith("java.lang")
									|| attributeType.getName().startsWith("java.util")
									|| attributeType.getName().startsWith("java.sql")) {
								gen.writeObjectField(attributeName, attribute);
							} else {
								serializeObjectWithMeta(attribute, attributeName, gen, provider);
							}
						}
					} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException
							| IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
	}

	@Override
	public void serialize(AbstractOI40MetaMessage value, JsonGenerator gen, SerializerProvider provider)
			throws IOException {
		this.serializeObjectWithMeta(value, null, gen, provider);
	}
}