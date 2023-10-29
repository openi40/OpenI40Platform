package com.openi40.mes.metamessaging.handlers;

import com.openi40.mes.metamessaging.model.AbstractOI40IOTMetaMessage;

public interface OI40IOTMessageReceiver<MsgType extends AbstractOI40IOTMetaMessage>
		extends MessageTypeConditionalMessageReceiver<MsgType> {
	
}
