package com.openi40.mes.metamessaging.handlers;

import com.openi40.mes.metamessaging.model.AbstractOI40MetaMessage;
import com.openi40.mes.metamessaging.model.ManagedMessageType;

public interface MessageTypeConditionalMessageReceiver<MsgType extends AbstractOI40MetaMessage>
		extends MessageReceiver<MsgType> {
	@Override
	public default boolean isCanManage(MsgType msg) {
		boolean canManage = false;
		Class thisClass = getClass();
		ManagedMessageType[] types = getHandledTypes();
		if (types != null) {
			for (ManagedMessageType managedMessageType : types) {
				canManage = canManage
						|| (managedMessageType.getType().equals(thisClass) || (managedMessageType.isManageExtensions()
								&& managedMessageType.getType().isAssignableFrom(thisClass)));
			}
		}
		return canManage;
	}
}
