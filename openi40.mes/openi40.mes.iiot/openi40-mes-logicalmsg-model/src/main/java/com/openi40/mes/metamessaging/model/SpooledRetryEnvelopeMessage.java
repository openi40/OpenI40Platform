package com.openi40.mes.metamessaging.model;

import java.sql.Timestamp;
import java.util.Date;
import java.util.GregorianCalendar;


public class SpooledRetryEnvelopeMessage extends AbstractOI40IOTMetaMessage {
	private AbstractOI40IOTMetaMessage content = null;
	private Timestamp retryThreshold = null;
	public SpooledRetryEnvelopeMessage() {
		
	}
	public SpooledRetryEnvelopeMessage(AbstractOI40IOTMetaMessage content, Date from, int millisecondRetry) {
		this.content = content;
		Date now = new Date();
		if (from == null || from.before(now)) {
			from = now;
		}
		GregorianCalendar gregorian = new GregorianCalendar();
		gregorian.setTime(from);
		if (millisecondRetry > 0) {
			gregorian.add(GregorianCalendar.MILLISECOND, millisecondRetry);
		}
		this.setFrom(content.getFrom());
		this.setTo(content.getTo());	
		this.setMsgId(content.getMsgId()+"::spool-retry");
		this.retryThreshold = new Timestamp(gregorian.getTimeInMillis());
	}
	public AbstractOI40IOTMetaMessage getContent() {
		return content;
	}
	public void setContent(AbstractOI40IOTMetaMessage content) {
		this.content = content;
	}
	public Timestamp getRetryThreshold() {
		return retryThreshold;
	}
	public void setRetryThreshold(Timestamp retryThreshold) {
		this.retryThreshold = retryThreshold;
	}

}
