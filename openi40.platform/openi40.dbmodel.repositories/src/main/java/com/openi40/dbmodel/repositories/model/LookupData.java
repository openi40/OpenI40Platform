package com.openi40.dbmodel.repositories.model;

public class LookupData {
	private String code=null;
	private String description=null;
	private PageInfo page=null;
	public LookupData() {
		
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public PageInfo getPage() {
		return page;
	}
	public void setPage(PageInfo page) {
		this.page = page;
	}

}
