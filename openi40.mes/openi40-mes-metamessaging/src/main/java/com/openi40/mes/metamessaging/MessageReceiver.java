package com.openi40.mes.metamessaging;

import java.lang.reflect.Type;
import java.util.function.Consumer;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public interface MessageReceiver<MsgType extends AbstractOI40MetaMessage> {

	public void onMessage(MsgType msg, MessagingEnvironment environment);

	public boolean isCanManage(MsgType msg);

	public default Consumer<MsgType> consumer() {
		return new Consumer<MsgType>() {
			@Override
			public void accept(MsgType t) {
				if (isCanManage(t)) {
					onMessage(t, null);
				}
			}
		};
	}

	public default void consume(Stream<MsgType> stream) {
		Logger LOGGER = LoggerFactory.getLogger(MessageReceiver.class);
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Begin consume(...)");
		}
		stream.forEach((msg) -> {
			if (isCanManage(msg)) {
				this.onMessage(msg, null);
			}
		});
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("End consume(...)");
		}
	}

	public ManagedMessageType[] getHandledTypes();

	public default String getLayerId() {
		return "unknown";
	}

	public default String getHandlerId() {
		return "openi40::handlers::" + getLayerId() + "::" + this.getClass().getName();
	}
}
