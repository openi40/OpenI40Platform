package com.openi40.mes.metamessaging.model;

public class UnmanageableMessageEnvelope extends AbstractOI40IOTMetaMessage {
	private AbstractOI40MetaMessage content=null;
	public UnmanageableMessageEnvelope(AbstractOI40MetaMessage c) {
		this.content=c;
		this.setMsgId(c.getMsgId()+"::unmanageable");
		this.setFrom(c.getFrom());
		this.setTo(c.getTo());
	}
	public AbstractOI40MetaMessage getContent() {
		return content;
	}
	public void setContent(AbstractOI40MetaMessage content) {
		this.content = content;
	}

}
