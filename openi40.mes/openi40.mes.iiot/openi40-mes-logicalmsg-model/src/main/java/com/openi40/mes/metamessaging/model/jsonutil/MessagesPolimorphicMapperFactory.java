package com.openi40.mes.metamessaging.model.jsonutil;

import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.openi40.mes.metamessaging.model.AbstractOI40MetaMessage;

public class MessagesPolimorphicMapperFactory {

	public final static ObjectMapper createMessagesPolimorphicMapper() {
		ObjectMapper mapper = new ObjectMapper();
		SimpleModule simpleModule = new SimpleModule("MetaMsgJsonUtilModule", new Version(1, 0, 0, null));
		simpleModule.addSerializer(new MetaEnrichedSerializer());
		simpleModule.addDeserializer(AbstractOI40MetaMessage.class, new MetaEnrichedDeserializer());
		mapper.registerModule(simpleModule);
		return mapper;
	}

}
