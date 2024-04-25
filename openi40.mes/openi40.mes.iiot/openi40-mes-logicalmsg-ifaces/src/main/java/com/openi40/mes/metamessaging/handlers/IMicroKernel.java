package com.openi40.mes.metamessaging.handlers;

import com.openi40.mes.metamessaging.model.AbstractOI40IOTMetaMessage;

public interface IMicroKernel extends MessageReceiver<AbstractOI40IOTMetaMessage> {
	public static final String MICROKERNEL_ID = "openi40::mes::microkernel";
}
