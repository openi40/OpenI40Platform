package com.openi40.scheduler.engine.aps;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.openi40.scheduler.engine.contextualplugarch.BusinessLogic;
import com.openi40.scheduler.engine.contextualplugarch.DefaultImplementation;
import com.openi40.scheduler.engine.messages.handling.ApsMessageManagementException;
import com.openi40.scheduler.engine.messages.handling.ApsMessageManagementResponse;
import com.openi40.scheduler.engine.messages.handling.ApsMessageValidationException;
import com.openi40.scheduler.engine.messages.handling.IApsMessagesHandler;
import com.openi40.scheduler.model.aps.ApsData;
import com.openi40.scheduler.model.messages.AbstractBaseMessage;

@DefaultImplementation(implemented = IApsMessagesBroker.class, entityClass = ApsData.class)
public class ApsMessagesBrokerImpl extends BusinessLogic<ApsData> implements IApsMessagesBroker {
	static Logger LOGGER = LoggerFactory.getLogger(ApsMessagesBrokerImpl.class);

	public ApsMessagesBrokerImpl() {

	}

	@Override
	public ApsMessageResponse handleMessage(AbstractBaseMessage message, ApsData context,
			ApsLogicNotifiedObjects observer) {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Begin handleMessage(...)");
		}
		ApsMessageResponse retValue = null;
		IApsMessagesHandler handler = this.componentsFactory.create(IApsMessagesHandler.class, message, context);
		ApsMessageManagementResponse response;
		try {
			synchronized (context) {
				response = handler.handleMessage(message, context);
				IApsLogicComposer composer = this.componentsFactory.create(IApsLogicComposer.class, context, context);
				if (response.isReschedule()) {
					composer.schedule(context, observer);
				}
			}
			retValue = new ApsMessageResponse(new ArrayList(), true);
		} catch (ApsMessageValidationException | ApsMessageManagementException e) {
			retValue = new ApsMessageResponse(e.getMessages(), false);
		}

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("End handleMessage(...)");
		}
		return retValue;
	}

}
