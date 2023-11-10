package com.openi40.mes.datamodel;

import java.io.InputStream;
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
@Table(name = "MES_SPOOL_MSG")
@SequenceGenerator(name = "OI40DBMesSpoolMsgGen", sequenceName = "mes_spool_msg_seq", initialValue = 1000, allocationSize = 1)
@Data
public class OI40DBMesSpoolMsg {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "OI40DBMesSpoolMsgGen")
	@Column(name = "id")
	Long id = null;
	@Column(name = "message_time")
	Timestamp messageTime = null;
	@Column(name = "message_type")
	String messageType = null;
	@Column(name = "message_uuid")
	String messageUuid = null;
	@Column(name = "spool_type")
	String spoolType = null;
	@Column(name = "resend_trheshold")
	Timestamp resendThreshold = null;
	@Column(name = "originalMsgId")
	String originalMsgId = null;
	@Column(name = "correlationId")
	String correlationId = null;
	@Column(name = "from_ref")
	String fromRef = null;
	@Column(name = "to_ref")
	String toRef = null;
	@Column(name = "mes_asset_code")
	String mesAssetCode = null;
	@Column(name = "mac_code")
	String macCode = null;
	@Column(name = "ip_address")
	String ipAddress = null;
	@Column(name = "sent")
	Boolean sent = null;
	@Column(name = "payload")
	String payload;

	public OI40DBMesSpoolMsg() {

	}

}
