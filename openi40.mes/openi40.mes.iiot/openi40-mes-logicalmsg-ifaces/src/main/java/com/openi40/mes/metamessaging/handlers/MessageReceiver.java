package com.openi40.mes.metamessaging.handlers;

import java.lang.reflect.Type;
import java.util.function.Consumer;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.openi40.mes.metamessaging.model.AbstractOI40MetaMessage;
import com.openi40.mes.metamessaging.model.ManagedMessageType;

public interface MessageReceiver<MsgType extends AbstractOI40MetaMessage> {

	public final static String IOT_APPLICATION_RECEIVER = "openi40::iot::application-receiver";
	public final static String IOT_KERNEL_RECEIVER = "openi40::iot::kernel-receiver";
	public final static String IOT_SYSTEM_RECEIVER = "openi40::iot::system-receiver";

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
