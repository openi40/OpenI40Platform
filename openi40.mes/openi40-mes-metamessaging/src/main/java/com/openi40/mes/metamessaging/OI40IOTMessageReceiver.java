package com.openi40.mes.metamessaging;

public interface OI40IOTMessageReceiver<MsgType extends AbstractOI40IOTMetaMessage>
		extends MessageTypeConditionalMessageReceiver<MsgType> {
	
}
