package com.openi40.mes.metamessaging.model.jsonutil;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.openi40.mes.metamessaging.model.AbstractOI40IOTApplicationMessage;
import com.openi40.mes.metamessaging.model.AbstractOI40MetaMessage;
import com.openi40.mes.metamessaging.model.SpooledRetryEnvelopeMessage;

public class MetaMsgJsonUtil {
	static ObjectMapper mapper = MessagesPolimorphicMapperFactory.createMessagesPolimorphicMapper();
	

	static final String META_CLASS_FIELD = "____class";

	public static <T extends AbstractOI40MetaMessage> T materialize(InputStream is, Class<T> type) throws JsonParseException, JsonMappingException, IOException {

		return mapper.readValue(is, type);
	}
	public static <T extends AbstractOI40MetaMessage> void serialize(T t,OutputStream os) throws JsonGenerationException, JsonMappingException, IOException {
		mapper.writeValue(os, t);
	}
	public static <T extends AbstractOI40MetaMessage> InputStream serialize(T t) throws JsonGenerationException, JsonMappingException, IOException {
		ByteArrayOutputStream bos=new ByteArrayOutputStream();
		serialize(t, bos);
		return new ByteArrayInputStream(bos.toByteArray());
	}
	public static  <T extends AbstractOI40MetaMessage> String serializeToString(T t) throws JsonGenerationException, JsonMappingException, IOException {
		ByteArrayOutputStream bos=new ByteArrayOutputStream();
		serialize(t, bos);
		return new String(bos.toByteArray());
	}

	public static void main(String[] args) throws IOException {
		MetaEnrichedSerializer meta = new MetaEnrichedSerializer();
		SpooledRetryEnvelopeMessage spooled = new SpooledRetryEnvelopeMessage(new AbstractOI40IOTApplicationMessage() {
		}, null, 100000);

		
		String json = mapper.writeValueAsString(spooled);
		AbstractOI40MetaMessage read = mapper.readValue(json.getBytes(), AbstractOI40MetaMessage.class);
		System.out.println(json);
		AbstractOI40MetaMessage msg[] = new AbstractOI40MetaMessage[] { spooled };
		json = mapper.writeValueAsString(msg);
		System.out.println(json);

	}
}
