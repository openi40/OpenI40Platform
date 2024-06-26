package com.openi40.mes.metamessaging.kernel;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.openi40.mes.metamessaging.handlers.MessagingEnvironment;
import com.openi40.mes.metamessaging.handlers.OI40IOTMessageReceiver;
import com.openi40.mes.metamessaging.model.AbstractOI40MetaMessage;
import com.openi40.mes.metamessaging.model.ManagedMessageType;

class AvoidCycleMessageReceiverWrapper implements OI40IOTMessageReceiver {
	private static Logger LOGGER = LoggerFactory.getLogger(AvoidCycleMessageReceiverWrapper.class);
	protected OI40IOTMessageReceiver wrapped = null;

	AvoidCycleMessageReceiverWrapper(OI40IOTMessageReceiver w) {
		this.wrapped = w;
	}

	static OI40IOTMessageReceiver of(OI40IOTMessageReceiver wrapped) {
		if (wrapped == null) {
			return new OI40IOTMessageReceiver() {
				@Override
				public ManagedMessageType[] getHandledTypes() {

					return new ManagedMessageType[0];
				}

				@Override
				public boolean isCanManage(AbstractOI40MetaMessage msg) {

					return false;
				}

				@Override
				public void onMessage(AbstractOI40MetaMessage msg, MessagingEnvironment environment) {

				}

			};
		}
		return new AvoidCycleMessageReceiverWrapper(wrapped);
	}

	static List<OI40IOTMessageReceiver> of(List<OI40IOTMessageReceiver> wrapped) {
		List<OI40IOTMessageReceiver> out = new ArrayList<OI40IOTMessageReceiver>();
		if (wrapped != null) {
			for (OI40IOTMessageReceiver messageReceiver : wrapped) {
				out.add(of(messageReceiver));
			}
		}
		return out;
	}

	@Override
	public ManagedMessageType[] getHandledTypes() {

		return wrapped.getHandledTypes();
	}

	@Override
	public String getHandlerId() {

		return wrapped.getHandlerId();
	}

	@Override
	public String getLayerId() {

		return this.wrapped.getLayerId();
	}

	@Override
	public void onMessage(AbstractOI40MetaMessage msg, MessagingEnvironment environment) {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Begin [" + getHandlerId() + "$avoid-cycles].onMessage(...)");
		}
		if (isCanManage(msg) && wrapped.isCanManage(msg)) {
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("Begin [" + wrapped.getHandlerId() + "].onMessage(...)");
			}
			msg.addHandlerId(wrapped.getHandlerId());
			wrapped.onMessage(msg, environment);
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("End [" + wrapped.getHandlerId() + "$avoid-cycles].onMessage(...)");
			}

		} else {
			LOGGER.warn("Message cycle avoidence actived for message=>" + msg.getMsgId() + " and handler=>"
					+ wrapped.getHandlerId());
		}
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("End  [" + getHandlerId() + "].onMessage(...)");
		}

	}

	@Override
	public boolean isCanManage(AbstractOI40MetaMessage msg) {
		boolean cycleNotActivated = !msg.isAlreadyHandledFrom(wrapped.getHandlerId());
		if (!cycleNotActivated) {
			LOGGER.warn("Message cycle avoidence checked as TRUE for message=>" + msg.getMsgId() + " and handler=>"
					+ wrapped.getHandlerId());
		}
		return cycleNotActivated;
	}

}
