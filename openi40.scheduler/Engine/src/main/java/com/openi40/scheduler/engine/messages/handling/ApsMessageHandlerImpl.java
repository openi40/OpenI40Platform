package com.openi40.scheduler.engine.messages.handling;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.openi40.scheduler.engine.OpenI40Exception;
import com.openi40.scheduler.engine.contextualplugarch.BusinessLogic;
import com.openi40.scheduler.engine.contextualplugarch.DefaultImplementation;
import com.openi40.scheduler.engine.messages.handling.impl.AbstractSpecializedMessageHandler;
import com.openi40.scheduler.model.aps.ApsData;
import com.openi40.scheduler.model.messages.AbstractBaseMessage;

/**
 * 
 * This code is part of the OpenI40 open source advanced production scheduler
 * platform suite, have look to its licencing options. Web site:
 * http://openi40.org/ Github: https://github.com/openi40/OpenI40Platform We
 * hope you enjoy implementing new amazing projects with it.
 * 
 * @author architectures@openi40.org
 *
 */
@DefaultImplementation(implemented = IApsMessagesHandler.class, entityClass = AbstractBaseMessage.class)
public class ApsMessageHandlerImpl extends BusinessLogic<AbstractBaseMessage> implements IApsMessagesHandler {
	static Logger LOGGER = LoggerFactory.getLogger(ApsMessageHandlerImpl.class);
	Map<Class<? extends AbstractBaseMessage>, AbstractSpecializedMessageHandler> handlersMap = new HashMap<Class<? extends AbstractBaseMessage>, AbstractSpecializedMessageHandler>();

	public ApsMessageHandlerImpl(
			@Autowired List<AbstractSpecializedMessageHandler> handlers) {
		if (handlers != null) {
			for (AbstractSpecializedMessageHandler abstractSpecializedMessageHandler : handlers) {
				this.handlersMap.put(abstractSpecializedMessageHandler.getHandledType(),
						abstractSpecializedMessageHandler);
			}
		}
	}

	@Override
	public ApsMessageManagementResponse handleMessage(AbstractBaseMessage msg, ApsData context)
			throws ApsMessageValidationException, ApsMessageManagementException {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Begin handleMessage(" + msg + ",context)");
		}
		IBaseMessagesHandler handler = this.handlersMap.get(msg.getClass());
		if (handler == null) {
			String textmsg = "The AbstractSpecializedMessageHandler for " + msg.getClass().getName()
					+ " is not implemented";
			throw new OpenI40Exception(textmsg);
		}
		ApsMessageManagementResponse response = handler.handleMessage(msg, context);
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("End handleMessage(" + msg + ",context) returning " + response);
		}
		return response;
	}

	@Override
	public Class<AbstractBaseMessage> getHandledType() {

		return AbstractBaseMessage.class;
	}

}
