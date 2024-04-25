package com.openi40.mes.metamessaging.model.jsonutil;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.openi40.mes.metamessaging.model.AbstractOI40MetaMessage;

public class MetaEnrichedDeserializer extends StdDeserializer<AbstractOI40MetaMessage> {
	public MetaEnrichedDeserializer() {
		this(null);
	}

	public MetaEnrichedDeserializer(Class type) {
		super(type);
	}

	@Override
	public AbstractOI40MetaMessage deserialize(JsonParser p, DeserializationContext ctxt)
			throws IOException, JsonProcessingException {

		return this.deserializeEnriched(p, ctxt);

	}

	private <T> T deserializeEnriched(TreeNode node, DeserializationContext ctxt)
			throws IOException, JsonProcessingException {
		T out = null;
		TreeNode metaInfo = node.get(MetaMsgJsonUtil.META_CLASS_FIELD);
		String __class = metaInfo.toString().replace("\"", "");
		if (__class != null) {
			try {
				Class<T> type = (Class<T>) Class.forName(__class);
				out = type.newInstance();
				Method[] methods = type.getMethods();
				if (methods != null)
					for (Method method : methods) {
						if (method.getName().startsWith("set") && method.getParameterCount() == 1
								&& Modifier.isPublic(method.getModifiers())
								&& !Modifier.isStatic(method.getModifiers())) {
							String attributeName = method.getName().substring(3);
							TreeNode attributeNode = node.get(attributeName);
							if (attributeNode != null && (attributeNode.isObject() || attributeNode.isValueNode())) {

								Class<?> setterType = method.getParameters()[0].getType();
								if (setterType.isPrimitive() || setterType.getName().startsWith("java.lang")
										|| setterType.getName().startsWith("java.util")
										|| setterType.getName().startsWith("java.sql")) {
									//TODO: FINIRE IL READING	
								} else {
									Object attribute = deserializeEnriched(attributeNode, ctxt);
									if (attribute != null)
										method.invoke(out, attribute);
								}
							} else if (attributeNode != null && attributeNode.isArray()) {
								//TODO: FINIRE IL READING
							}

						}
					}
			} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | IllegalArgumentException
					| InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return out;
	}

	private <T> T deserializeEnriched(JsonParser p, DeserializationContext ctxt)
			throws IOException, JsonProcessingException {

		TreeNode node = p.getCodec().readTree(p);
		return deserializeEnriched(node, ctxt);
	}

}
