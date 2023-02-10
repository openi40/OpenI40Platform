package com.openi40.platform.iomessages.spooler.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "msg_spooler_proc")
@Data
@SequenceGenerator(name = "msg_spooler_proc_seq-gen", sequenceName = "msg_spooler_proc_seq", initialValue = 1, allocationSize = 1)
public class MsgSpoolerEntryProcessed {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "msg_spooler_proc_seq-gen")
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
	@Column(name = "processed_ts")
	private Timestamp processedTimestamp = null;
}
