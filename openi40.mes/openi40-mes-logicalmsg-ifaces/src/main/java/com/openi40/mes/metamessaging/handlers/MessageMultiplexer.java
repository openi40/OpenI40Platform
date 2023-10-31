package com.openi40.mes.metamessaging.handlers;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.openi40.mes.metamessaging.model.AbstractOI40MetaMessage;
import com.openi40.mes.metamessaging.model.ManagedMessageType;

public class MessageMultiplexer<MsgType extends AbstractOI40MetaMessage> implements MessageReceiver<MsgType> {
	protected List<MessageReceiver<MsgType>> multiplexed = new ArrayList<MessageReceiver<MsgType>>();

	public MessageMultiplexer(List<MessageReceiver<MsgType>> multiplexed) {
		this.multiplexed = multiplexed;
	}

	@Override
	public void onMessage(MsgType msg, MessagingEnvironment environment) {
		for (MessageReceiver<MsgType> messageReceiver : multiplexed) {
			if (messageReceiver.isCanManage(msg)) {
				messageReceiver.onMessage(msg, environment);
			}
		}
	}

	@Override
	public ManagedMessageType[] getHandledTypes() {
		ArrayList<ManagedMessageType> msgTypes = new ArrayList<ManagedMessageType>();
		for (MessageReceiver<MsgType> messageReceiver : multiplexed) {
			ManagedMessageType[] t = messageReceiver.getHandledTypes();
			if (t != null) {
				for (ManagedMessageType managedMessageType : msgTypes) {
					if (managedMessageType != null)
						msgTypes.add(managedMessageType);
				}
			}
		}
		return msgTypes.toArray(new ManagedMessageType[msgTypes.size()]);
	}

	@Override
	public boolean isCanManage(MsgType msg) {
		boolean canManage = false;
		for (MessageReceiver<MsgType> messageReceiver : multiplexed) {
			canManage = canManage || messageReceiver.isCanManage(msg);
		}
		return canManage;
	}

}
