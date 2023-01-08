package com.openi40.platform.iomessages.spooler.model;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "msg_spooler_entry")
public class MSGSpoolerEntry implements Serializable {
	public static final String MSG_STATUS_RECEIVED = "RECEIVED";
	public static final String MSG_STATUS_PROCESSED = "PROCESSED";
	public static final String MSG_STATUS_ERROR = "ERROR";
	@Id
	@Column(name = "msg_entry_id")
	private long msgEntryId = 0l;
	@Column(name = "ts_memorized")
	private Timestamp timestampMemorized = null;
	@Column(name = "ts_message")
	private Timestamp messageTimestamp = null;
	@Column(name = "msg_class_name")
	private String msgClassName = null;
	@Column(name = "json_dump")
	private String jsonDump = null;
	@Column(name = "processed_status")
	private String processedStatus = MSG_STATUS_RECEIVED;
	@Column(name = "processed_ts")
	private Timestamp processedTimestamp = null;
	@Column(name = "data_src_name")
	private String dataSourceName = null;
	@Column(name = "data_set_name")
	private String dataSetName = null;
	@Column(name = "data_set_variant")
	private String dataSetVariant = null;
}
