/**
 * 
 * This code is part of the OpenI40 open source advanced production scheduler
 * platform suite, have look to its licencing options.
 * Web site: http://openi40.org/  
 * Github: https://github.com/openi40/OpenI40Platform
 * We hope you enjoy implementing new amazing projects with it.
 * @author architectures@openi40.org
 *
 */
package com.openi40.scheduler.client.model.time;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class TimesheetDto extends TimeSegmentDto implements Serializable {
	protected List<YearDto> years=new ArrayList<>();
	public TimesheetDto() {
		
	}
	
	
	public static class YearDto extends TimeSegmentDto{
		protected long year=0;
		protected List<YearQuarterDto> quarters=new ArrayList<>();
		public long getYear() {
			return year;
		}
		public void setYear(long year) {
			this.year = year;
		}
		public List<YearQuarterDto> getQuarters() {
			return quarters;
		}
		public void setQuarters(List<YearQuarterDto> quarters) {
			this.quarters = quarters;
		}
	}
	
	public static class YearQuarterDto  extends TimeSegmentDto{
		protected 	long quarter=0;
		protected 	List<MonthDto> months=new ArrayList<>();
		public long getQuarter() {
			return quarter;
		}
		public void setQuarter(long quarter) {
			this.quarter = quarter;
		}
		public List<MonthDto> getMonths() {
			return months;
		}
		public void setMonths(List<MonthDto> months) {
			this.months = months;
		}
	}
	
	public static class MonthDto  extends TimeSegmentDto{
		protected long month=0;
		protected 	List<DayDto> days=new ArrayList<>();
		public long getMonth() {
			return month;
		}
		public void setMonth(long month) {
			this.month = month;
		}
		public List<DayDto> getDays() {
			return days;
		}
		public void setDays(List<DayDto> days) {
			this.days = days;
		}
	}
	
	public static class DayDto  extends TimeSegmentDto{
		protected   long week=0;
		protected   long date=0;
		protected 	List<TurnDto> turns=new ArrayList<TurnDto>();
		public long getWeek() {
			return week;
		}
		public void setWeek(long week) {
			this.week = week;
		}
		public long getDate() {
			return date;
		}
		public void setDate(long date) {
			this.date = date;
		}
		public List<TurnDto> getTurns() {
			return turns;
		}
		public void setTurns(List<TurnDto> turns) {
			this.turns = turns;
		}
	}
	
	public static class TurnDto  extends TimeSegmentDto{
		protected   long week=0;
		protected   long date=0;
		protected 	List<HourDto> hours=new ArrayList<>();
		protected String startShortDateTime=null,endShortDateTime=null;
		protected String startShortDate=null;
		protected String startShortTime=null;
		protected String endShortDate=null;
		protected String endShortTime=null;
		public long getWeek() {
			return week;
		}
		public void setWeek(long week) {
			this.week = week;
		}
		public long getDate() {
			return date;
		}
		public void setDate(long date) {
			this.date = date;
		}
		public List<HourDto> getHours() {
			return hours;
		}
		public void setHours(List<HourDto> hours) {
			this.hours = hours;
		}
		public String getStartShortDateTime() {
			return startShortDateTime;
		}
		public void setStartShortDateTime(String startShortDateTime) {
			this.startShortDateTime = startShortDateTime;
		}
		public String getEndShortDateTime() {
			return endShortDateTime;
		}
		public void setEndShortDateTime(String endShortDateTime) {
			this.endShortDateTime = endShortDateTime;
		}
		public String getStartShortDate() {
			return startShortDate;
		}
		public void setStartShortDate(String startShortDate) {
			this.startShortDate = startShortDate;
		}
		public String getStartShortTime() {
			return startShortTime;
		}
		public void setStartShortTime(String startShortTime) {
			this.startShortTime = startShortTime;
		}
		public String getEndShortDate() {
			return endShortDate;
		}
		public void setEndShortDate(String endShortDate) {
			this.endShortDate = endShortDate;
		}
		public String getEndShortTime() {
			return endShortTime;
		}
		public void setEndShortTime(String endShortTime) {
			this.endShortTime = endShortTime;
		}
	}
	
	public static class HourDto  extends TimeSegmentDto{
		protected long hour=0l;
		protected String startShortDateTime=null,endShortDateTime=null;
		protected String startShortDate=null;
		protected String startShortTime=null;
		protected String endShortDate=null;
		protected String endShortTime=null;
		public long getHour() {
			return hour;
		}
		public void setHour(long hour) {
			this.hour = hour;
		}
		public String getStartShortDateTime() {
			return startShortDateTime;
		}
		public void setStartShortDateTime(String startShortDateTime) {
			this.startShortDateTime = startShortDateTime;
		}
		public String getEndShortDateTime() {
			return endShortDateTime;
		}
		public void setEndShortDateTime(String endShortDateTime) {
			this.endShortDateTime = endShortDateTime;
		}
		public String getStartShortDate() {
			return startShortDate;
		}
		public void setStartShortDate(String startShortDate) {
			this.startShortDate = startShortDate;
		}
		public String getStartShortTime() {
			return startShortTime;
		}
		public void setStartShortTime(String startShortTime) {
			this.startShortTime = startShortTime;
		}
		public String getEndShortDate() {
			return endShortDate;
		}
		public void setEndShortDate(String endShortDate) {
			this.endShortDate = endShortDate;
		}
		public String getEndShortTime() {
			return endShortTime;
		}
		public void setEndShortTime(String endShortTime) {
			this.endShortTime = endShortTime;
		}
	}

	public List<YearDto> getYears() {
		return years;
	}

	public void setYears(List<YearDto> years) {
		this.years = years;
	}
	
}
