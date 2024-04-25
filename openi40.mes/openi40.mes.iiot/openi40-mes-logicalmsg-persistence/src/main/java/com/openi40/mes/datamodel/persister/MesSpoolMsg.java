package com.openi40.mes.datamodel.persister;

import java.sql.Timestamp;

public  class MesSpoolMsg extends MesLogicalMsg {
	Timestamp resend_trheshold=null;
	String spool_type=null;
	public MesSpoolMsg() {
		this.table="MES_SPOOL_MSG";
		this.autoIncrements= new String[]{"id"};
	}
	public Timestamp getResend_trheshold() {
		return resend_trheshold;
	}
	public void setResend_trheshold(Timestamp resend_trheshold) {
		this.resend_trheshold = resend_trheshold;
	}
	public String getSpool_type() {
		return spool_type;
	}
	public void setSpool_type(String spool_type) {
		this.spool_type = spool_type;
	}

}
