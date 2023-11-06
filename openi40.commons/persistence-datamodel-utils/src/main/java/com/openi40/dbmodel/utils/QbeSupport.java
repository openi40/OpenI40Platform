package com.openi40.dbmodel.utils;

public class QbeSupport<OI40Type> {
	private OI40Type qbe=null;
	private PageInfo page=null;
	public QbeSupport() {
		
	}
	public OI40Type getQbe() {
		return qbe;
	}
	public void setQbe(OI40Type qbe) {
		this.qbe = qbe;
	}
	public PageInfo getPage() {
		return page;
	}
	public void setPage(PageInfo page) {
		this.page = page;
	}

}
