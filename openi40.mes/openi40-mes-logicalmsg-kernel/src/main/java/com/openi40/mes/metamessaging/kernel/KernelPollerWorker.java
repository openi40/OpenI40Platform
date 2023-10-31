package com.openi40.mes.metamessaging.kernel;

import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;

import com.openi40.mes.metamessaging.handlers.PollableMessagesStreamFactory;
import com.openi40.mes.metamessaging.model.AbstractOI40IOTMetaMessage;

public class KernelPollerWorker {
	MetaMessagingKernel kernel=null;
	public KernelPollerWorker(@Autowired MetaMessagingKernel kernel) {
		this.kernel=kernel;
	}
	@Async
	public void consumeStream(PollableMessagesStreamFactory factory) {
		Stream<AbstractOI40IOTMetaMessage> stream = factory.stream();
		kernel.consume(stream);
	}

}
