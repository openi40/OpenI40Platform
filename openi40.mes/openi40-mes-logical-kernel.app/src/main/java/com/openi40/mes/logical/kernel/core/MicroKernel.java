package com.openi40.mes.logical.kernel.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openi40.mes.metamessaging.handlers.MessageReceiver;
import com.openi40.mes.metamessaging.handlers.MessagingEnvironment;
import com.openi40.mes.metamessaging.kernel.MetaMessagingKernel;
import com.openi40.mes.metamessaging.model.AbstractOI40IOTMetaMessage;
import com.openi40.mes.metamessaging.model.ManagedMessageType;

@Service
public class MicroKernel implements MessageReceiver<AbstractOI40IOTMetaMessage> {
	MetaMessagingKernel metaMessagingKernel = null;

	static Logger LOGGER = LoggerFactory.getLogger(MicroKernel.class);

	public MicroKernel(@Autowired MetaMessagingKernel metaMessagingKernel) {
		this.metaMessagingKernel = metaMessagingKernel;

	}

	@Override
	public void onMessage(AbstractOI40IOTMetaMessage msg, MessagingEnvironment environment) {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Begin [" + getHandlerId() + "].onMessage(...)");
		}
		metaMessagingKernel.onMessage(msg, metaMessagingKernel);
		if (LOGGER.isDebugEnabled()) { 
			LOGGER.debug("End [" + getHandlerId() + "].onMessage(...)");
		}
	}
 
	@Override
	public boolean isCanManage(AbstractOI40IOTMetaMessage msg) {

		return true;
	}

	@Override
	public ManagedMessageType[] getHandledTypes() {

		return new ManagedMessageType[0];
	}

	@Override
	public String getLayerId() {

		return "micro-kernel";
	}

}
