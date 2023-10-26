package com.openi40.dbmodel.repositories.model;

public class AutoCompleteData {
	private PageInfo page=null;
	private String searchString=null;
	public AutoCompleteData() {
		
	}
	
	public String getSearchString() {
		return searchString;
	}
	public void setSearchString(String searchString) {
		this.searchString = searchString;
	}

	public PageInfo getPage() {
		return page;
	}

	public void setPage(PageInfo page) {
		this.page = page;
	}

}
