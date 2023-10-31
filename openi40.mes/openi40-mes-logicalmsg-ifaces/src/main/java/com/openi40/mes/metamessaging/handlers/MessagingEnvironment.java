package com.openi40.mes.metamessaging.handlers;

import java.util.Date;

public interface MessagingEnvironment {
	public MessageReceiver getLoopbackSender();
	public MessageReceiver getSpooledRetrySender(Date startDate,int delayMilliseconds);
	public MessageReceiver getUnmanageableMessageSender();
}
