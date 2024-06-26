package com.openi40.platform.iomessages.spooler.model;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


@Entity
@Table(name = "msg_spooler_entry")

@SequenceGenerator(name = "msg_spooler_entry_seq-gen", sequenceName = "msg_spooler_entry_seq", initialValue = 1, allocationSize = 1)
public class MsgSpoolerEntry implements Serializable {
	public static final String MSG_STATUS_RECEIVED = "RECEIVED";
	public static final String MSG_STATUS_PROCESSED = "PROCESSED";
	public static final String MSG_STATUS_ERROR = "ERROR";
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "msg_spooler_entry_seq-gen")
	@Column(name = "msg_entry_id")
	private long msgEntryId = 0l;
	@Column(name = "code")
	private String code = null;
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
	public long getMsgEntryId() {
		return msgEntryId;
	}
	public void setMsgEntryId(long msgEntryId) {
		this.msgEntryId = msgEntryId;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public Timestamp getTimestampMemorized() {
		return timestampMemorized;
	}
	public void setTimestampMemorized(Timestamp timestampMemorized) {
		this.timestampMemorized = timestampMemorized;
	}
	public Timestamp getMessageTimestamp() {
		return messageTimestamp;
	}
	public void setMessageTimestamp(Timestamp messageTimestamp) {
		this.messageTimestamp = messageTimestamp;
	}
	public String getMsgClassName() {
		return msgClassName;
	}
	public void setMsgClassName(String msgClassName) {
		this.msgClassName = msgClassName;
	}
	public String getJsonDump() {
		return jsonDump;
	}
	public void setJsonDump(String jsonDump) {
		this.jsonDump = jsonDump;
	}
	public String getProcessedStatus() {
		return processedStatus;
	}
	public void setProcessedStatus(String processedStatus) {
		this.processedStatus = processedStatus;
	}
	public Timestamp getProcessedTimestamp() {
		return processedTimestamp;
	}
	public void setProcessedTimestamp(Timestamp processedTimestamp) {
		this.processedTimestamp = processedTimestamp;
	}
	public String getDataSourceName() {
		return dataSourceName;
	}
	public void setDataSourceName(String dataSourceName) {
		this.dataSourceName = dataSourceName;
	}
	public String getDataSetName() {
		return dataSetName;
	}
	public void setDataSetName(String dataSetName) {
		this.dataSetName = dataSetName;
	}
	public String getDataSetVariant() {
		return dataSetVariant;
	}
	public void setDataSetVariant(String dataSetVariant) {
		this.dataSetVariant = dataSetVariant;
	}
}
