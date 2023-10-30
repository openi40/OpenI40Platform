package com.openi40.mes.logical.kernel.core;

import java.util.ArrayList;
import java.util.stream.Stream;

import org.springframework.stereotype.Service;

import com.openi40.mes.metamessaging.model.AbstractOI40IOTMetaMessage;

@Service
public class MessagesStreamConcentrator {

	public MessagesStreamConcentrator() {
		
	}
	public Stream<AbstractOI40IOTMetaMessage> stream(){
		return new ArrayList<AbstractOI40IOTMetaMessage>().stream();
	}

}
