package com.openi40.mes.metamessaging.model;

import java.sql.Timestamp;
import java.util.Date;
import java.util.GregorianCalendar;

import lombok.Data;

@Data
public class SpooledRetryEnvelopeMessage extends AbstractOI40IOTMetaMessage {
	private AbstractOI40IOTMetaMessage content = null;
	private Timestamp retryThreshold = null;

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
		this.setMsgId(content.getMsgId());
		this.retryThreshold = new Timestamp(gregorian.getTimeInMillis());
	}

}
