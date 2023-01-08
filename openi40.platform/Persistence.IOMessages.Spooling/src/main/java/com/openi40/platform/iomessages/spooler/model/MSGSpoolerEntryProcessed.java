package com.openi40.platform.iomessages.spooler.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Id;

import lombok.Data;

@Data
public class MSGSpoolerEntryProcessed {
	@Id
	@Column(name = "id")
	private long msgEntryProcessedId = 0l;
	@Column(name = "msg_entry_id")
	private long msgEntryId = 0l;
	@Column(name = "process_ok")
	private boolean processSuccessfully = false;
	@Column(name = "error_code")
	private String errorCode = null;
	@Column(name = "error_msg")
	private String errorMessage = null;
	@Column(name="processed_ts")
	private Timestamp processedTimestamp=null;
}
