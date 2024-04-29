/**
 * 
 * This code is part of the OpenI40 open source advanced production scheduler
 * platform suite, have look to its licencing options.
 * Web site: http://openi40.org/  
 * Github: https://github.com/openi40/OpenI40Platform
 * We hope you enjoy implementing new amazing projects with it.
 * @author Paolo Zavalloni  architectures@openi40.org
 *
 */
package com.openi40.scheduler.client.model.aps;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.openi40.scheduler.client.model.ApsWindowDto;
import com.openi40.scheduler.client.model.ClientDto;
import com.openi40.scheduler.client.model.companystructure.ProductiveCompanyDto;
import com.openi40.scheduler.client.model.material.ProductDto;
import com.openi40.scheduler.client.model.time.TimesheetDto;


public class ApsDataDto extends ClientDto implements Serializable {

	protected List<ProductiveCompanyDto> productiveCompanies = new ArrayList<ProductiveCompanyDto>();
	protected List<ProductDto> products = new ArrayList<ProductDto>();
	protected List<ApsSchedulingSetDto> schedulingSets = new ArrayList<ApsSchedulingSetDto>();
	protected Date actualDateTime = null;
	protected ApsWindowDto schedulingWindow = null;
	private String dataSourceName = null;
	protected String dataSetName = null;
	protected TimesheetDto timesheet = null;
	protected String calendarKey = null;
	protected String dataSetVariant = null;
	protected boolean scheduled = false;
	protected boolean fullyPlanned = false;
	protected boolean realtime = false;
	protected boolean productionControlEnabled = false;
	public List<ProductiveCompanyDto> getProductiveCompanies() {
		return productiveCompanies;
	}
	public void setProductiveCompanies(List<ProductiveCompanyDto> productiveCompanies) {
		this.productiveCompanies = productiveCompanies;
	}
	public List<ProductDto> getProducts() {
		return products;
	}
	public void setProducts(List<ProductDto> products) {
		this.products = products;
	}
	public List<ApsSchedulingSetDto> getSchedulingSets() {
		return schedulingSets;
	}
	public void setSchedulingSets(List<ApsSchedulingSetDto> schedulingSets) {
		this.schedulingSets = schedulingSets;
	}
	public Date getActualDateTime() {
		return actualDateTime;
	}
	public void setActualDateTime(Date actualDateTime) {
		this.actualDateTime = actualDateTime;
	}
	public ApsWindowDto getSchedulingWindow() {
		return schedulingWindow;
	}
	public void setSchedulingWindow(ApsWindowDto schedulingWindow) {
		this.schedulingWindow = schedulingWindow;
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
	public TimesheetDto getTimesheet() {
		return timesheet;
	}
	public void setTimesheet(TimesheetDto timesheet) {
		this.timesheet = timesheet;
	}
	public String getCalendarKey() {
		return calendarKey;
	}
	public void setCalendarKey(String calendarKey) {
		this.calendarKey = calendarKey;
	}
	public String getDataSetVariant() {
		return dataSetVariant;
	}
	public void setDataSetVariant(String dataSetVariant) {
		this.dataSetVariant = dataSetVariant;
	}
	public boolean isScheduled() {
		return scheduled;
	}
	public void setScheduled(boolean scheduled) {
		this.scheduled = scheduled;
	}
	public boolean isFullyPlanned() {
		return fullyPlanned;
	}
	public void setFullyPlanned(boolean fullyPlanned) {
		this.fullyPlanned = fullyPlanned;
	}
	public boolean isRealtime() {
		return realtime;
	}
	public void setRealtime(boolean realtime) {
		this.realtime = realtime;
	}
	public boolean isProductionControlEnabled() {
		return productionControlEnabled;
	}
	public void setProductionControlEnabled(boolean productionControlEnabled) {
		this.productionControlEnabled = productionControlEnabled;
	}
}
