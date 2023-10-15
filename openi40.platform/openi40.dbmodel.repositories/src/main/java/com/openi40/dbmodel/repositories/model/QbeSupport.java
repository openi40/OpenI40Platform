package com.openi40.dbmodel.repositories.model;

import com.openi40.dbmodel.entities.OI40DBBaseEntity;

public class QbeSupport<OI40Type extends OI40DBBaseEntity> {
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
