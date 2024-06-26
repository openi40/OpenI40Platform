package com.openi40.scheduler.engine.aps;

import java.util.ArrayList;
import java.util.List;

import com.openi40.scheduler.engine.contextualplugarch.BusinessInterface;
import com.openi40.scheduler.engine.contextualplugarch.IBusinessLogic;
import com.openi40.scheduler.model.aps.ApsData;
import com.openi40.scheduler.model.messages.AbstractBaseMessage;
import com.openi40.scheduler.model.messages.handling.MessageHandlingErrorMessage;

@BusinessInterface(entityClass = ApsData.class)
public interface IApsMessagesBroker extends IBusinessLogic<ApsData>{
	
	public static class ApsMessageResponse {
		public ApsMessageResponse(List<MessageHandlingErrorMessage> errors,boolean messageHandled) {
			this.errors=errors;
			this.messageHandled=messageHandled;
		}
		List<MessageHandlingErrorMessage> errors=new ArrayList<MessageHandlingErrorMessage>();
		boolean messageHandled=false;
		public List<MessageHandlingErrorMessage> getErrors() {
			return errors;
		}
		public boolean isMessageHandled() {
			return messageHandled;
		}
	}
	ApsMessageResponse handleMessage(AbstractBaseMessage message, ApsData context, ApsLogicNotifiedObjects observer);
}
