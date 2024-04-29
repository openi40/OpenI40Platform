package com.openi40.scheduler.model.companystructure;

import com.openi40.scheduler.model.AbstractApsObject;
import com.openi40.scheduler.model.ITimesheetAllocableObject;
import com.openi40.scheduler.model.aps.ApsData;
import com.openi40.scheduler.model.time.Timesheet;
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

public class Company extends AbstractApsObject implements ITimesheetAllocableObject {
	private boolean infiniteCapacity=false;	private String address = null;
	private String city = null;
	private String companyName = null;
	private String country = null;
	private String provincia = null;
	private String zipCode = null;
	protected String timesheetMetaInfoCode=null;
	protected Timesheet timesheet=null;
	public Company(ApsData context) {
		super(context);
	}
	public boolean isInfiniteCapacity() {
		return infiniteCapacity;
	}
	public void setInfiniteCapacity(boolean infiniteCapacity) {
		this.infiniteCapacity = infiniteCapacity;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getProvincia() {
		return provincia;
	}
	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}
	public String getZipCode() {
		return zipCode;
	}
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	public String getTimesheetMetaInfoCode() {
		return timesheetMetaInfoCode;
	}
	public void setTimesheetMetaInfoCode(String timesheetMetaInfoCode) {
		this.timesheetMetaInfoCode = timesheetMetaInfoCode;
	}
	public Timesheet getTimesheet() {
		return timesheet;
	}
	public void setTimesheet(Timesheet timesheet) {
		this.timesheet = timesheet;
	}


}