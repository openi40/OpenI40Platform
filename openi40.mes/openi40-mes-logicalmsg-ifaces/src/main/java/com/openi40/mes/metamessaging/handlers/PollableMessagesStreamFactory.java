package com.openi40.mes.metamessaging.handlers;

import java.util.stream.Stream;

import com.openi40.mes.metamessaging.model.AbstractOI40IOTMetaMessage;

public interface PollableMessagesStreamFactory {
	public Stream<AbstractOI40IOTMetaMessage> stream();
}
