package com.openi40.dbmodel.repositories.model;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.support.PageableExecutionUtils;

import lombok.Data;


public class PageInfo {
	private Integer page=null;
	private Integer pageSize=null;
	private Integer totalElements=null;
	public PageInfo() {
		
	}
	public static Pageable from(PageInfo page) {
		Pageable pageable=Pageable.unpaged();
		if (page!=null && page.getPage()!=null && page.getPageSize()!=null) {
			pageable=PageRequest.of(page.getPage(), page.getPageSize());
		}
		return pageable;
	}
	public Integer getPage() {
		return page;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public Integer getTotalElements() {
		return totalElements;
	}
	public void setTotalElements(Integer totalElements) {
		this.totalElements = totalElements;
	}
}
