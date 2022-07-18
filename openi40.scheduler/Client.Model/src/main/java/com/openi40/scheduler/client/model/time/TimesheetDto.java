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

import lombok.Data;
@Data
public class TimesheetDto extends TimeSegmentDto implements Serializable {
	public TimesheetDto() {
		
	}
	
	@Data
	public static class YearDto extends TimeSegmentDto{
		protected long year=0;
		protected List<YearQuarterDto> quarters=new ArrayList<>();
	}
	@Data
	public static class YearQuarterDto  extends TimeSegmentDto{
		protected 	long quarter=0;
		protected 	List<MonthDto> months=new ArrayList<>();
	}
	@Data
	public static class MonthDto  extends TimeSegmentDto{
		protected long month=0;
		protected 	List<DayDto> days=new ArrayList<>();
	}
	@Data
	public static class DayDto  extends TimeSegmentDto{
		protected   long week=0;
		protected   long date=0;
		protected 	List<TurnDto> turns=new ArrayList<TurnDto>();
	}
	@Data
	public static class TurnDto  extends TimeSegmentDto{
		protected   long week=0;
		protected   long date=0;
		protected 	List<HourDto> hours=new ArrayList<>();
		protected String startShortDateTime=null,endShortDateTime=null;
		protected String startShortDate=null;
		protected String startShortTime=null;
		protected String endShortDate=null;
		protected String endShortTime=null;
	}
	@Data
	public static class HourDto  extends TimeSegmentDto{
		protected long hour=0l;
		protected String startShortDateTime=null,endShortDateTime=null;
		protected String startShortDate=null;
		protected String startShortTime=null;
		protected String endShortDate=null;
		protected String endShortTime=null;
	}
	protected List<YearDto> years=new ArrayList<>();
}
