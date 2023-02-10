package com.openi40.scheduler.engine.messages.handling;

import com.openi40.scheduler.model.aps.ApsData;
import com.openi40.scheduler.model.messages.AbstractBaseMessage;
import com.openi40.scheduler.model.messages.handling.ApsMessageManagementResponse;
/******
 * 
 * This code is part of the OpenI40 open source advanced production scheduler
 * platform suite, have look to its licencing options.
 * Web site: http://openi40.org/  
 * Github: https://github.com/openi40/OpenI40Platform
 * We hope you enjoy implementing new amazing projects with it.
 * @author architectures@openi40.org
 *
 * @param <MsgType>
 */
public interface IBaseMessagesHandler<MsgType extends AbstractBaseMessage> {
	/**
	 * This method is responsable to handle incoming messages throwing exception for messages not compatible with actual state or invalid.
	 * It changes the state of the ApsData and returns ApsMessageManagementResponse with reschedule==true if Aps kernel has to reschedule
	 * data after data modification.
	 * @param msg
	 * @param context
	 * @return
	 * @throws ApsMessageValidationException
	 * @throws ApsMessageManagementException
	 */
	public ApsMessageManagementResponse handleMessage(MsgType msg, ApsData context)
			throws ApsMessageValidationException, ApsMessageManagementException;
	public Class<MsgType> getHandledType();
}
