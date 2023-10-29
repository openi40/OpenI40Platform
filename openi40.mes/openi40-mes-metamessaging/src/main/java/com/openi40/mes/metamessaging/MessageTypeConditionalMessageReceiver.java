package com.openi40.mes.metamessaging;

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
