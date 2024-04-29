package com.openi40.scheduler.common.utils;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.GregorianCalendar;
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
public class DateUtil {
	private DateUtil() {
	}

	private static DateUtil Instance = new DateUtil();

	public static DateUtil getInstance() {
		return Instance;
	}

	public static class Period implements Serializable {
		/**
		 * 
		 */
		private static final long serialVersionUID = -6450141597245129589L;
		public Period() {
			
		}
		protected int period = 0;
		protected String description = null;

		public int getPeriod() {
			return period;
		}

		public String getDescription() {
			return description;
		}
	}

	public static class Week extends Period implements Serializable{
		/**
		 * 
		 */
		private static final long serialVersionUID = 790786347693879137L;

		public Week() {
		}

		public Week(int y, int w) {
			this.year = y;
			this.week = w;
			this.description = y + "/" + w;
			this.period = y * 100 + week;
		}

		protected int year = 0, week = 0;

		public int getYear() {
			return year;
		}

		public int getWeek() {
			return week;
		}

	}

	public static Week getWeek(Date date) {
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(date);
		int y = gc.get(GregorianCalendar.YEAR);
		int w = gc.get(GregorianCalendar.WEEK_OF_YEAR);
		return new Week(y, w);

	}

	public static Date addMinutes(Date date, int minutes) {
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(date);
		gc.add(GregorianCalendar.MINUTE, minutes);
		return gc.getTime();
	}

	public static Date addDays(Date date, int days) {
		return add(date, GregorianCalendar.DAY_OF_YEAR, days);
	}

	public static Date addHours(Date date, int hours) {
		return add(date, GregorianCalendar.HOUR, hours);
	}

	public static Date add(Date date, int gregoriancalendarConstant, int minutes) {
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(date);
		gc.add(gregoriancalendarConstant, minutes);
		return gc.getTime();
	}

	public final Date Min(Date d1, Date d2) {
		if (d1 != null && d2 != null && d1 != null && d2 != null) {
			return d1.compareTo(d2) < 0 ? d1 : d2;
		} else {
			return d1 != null ? d1 : d2;
		}
	}

	public final Date Max(Date d1, Date d2) {
		if (d1 != null && d2 != null && d1 != null && d2 != null) {
			return d1.compareTo(d2) > 0 ? d1 : d2;
		} else {
			return d1 != null ? d1 : d2;
		}
	}

	public final LocalDateTime Min(LocalDateTime d1, LocalDateTime d2) {
		if (d1 != null && d2 != null && d1 != null && d2 != null) {
			return d1.compareTo(d2) < 0 ? d1 : d2;
		} else {
			return d1 != null ? d1 : d2;
		}
	}

	public final LocalDateTime Max(LocalDateTime d1, LocalDateTime d2) {
		if (d1 != null && d2 != null && d1 != null && d2 != null) {
			return d1.compareTo(d2) > 0 ? d1 : d2;
		} else {
			return d1 != null ? d1 : d2;
		}
	}

	public static final DateFormat defaultInternalDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	public static String toString(Date d) {
		return d != null ? defaultInternalDateFormat.format(d) : null;
	}

	public static Date fromString(String _s) throws ParseException {
		if (_s != null && _s.trim().length() > 0) {
			return defaultInternalDateFormat.parse(_s.trim());
		}
		return null;
	}

	public static final long TIME_DISCRETE_STEP_SECONDS = 60l;

	public static long timeLongRappresentation(Date d) {
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(d);
		return gc.get(GregorianCalendar.HOUR_OF_DAY) * 10000l + gc.get(GregorianCalendar.MINUTE) * 100l
				+ gc.get(GregorianCalendar.SECOND);
	}

	public static long dateLongRappresentation(Date d) {
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(d);
		return gc.get(GregorianCalendar.YEAR) * 10000l + (gc.get(GregorianCalendar.MONTH) + 1l) * 100l
				+ gc.get(GregorianCalendar.DAY_OF_MONTH);
	}

	public static long dateTimeLongRappresentation(Date d) {
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(d);
		long time = gc.get(GregorianCalendar.HOUR_OF_DAY) * 10000l + gc.get(GregorianCalendar.MINUTE) * 100l
				+ gc.get(GregorianCalendar.SECOND);
		long date = gc.get(GregorianCalendar.YEAR) * 10000l + (gc.get(GregorianCalendar.MONTH) + 1l) * 100l
				+ gc.get(GregorianCalendar.DAY_OF_MONTH);
		return date * 1000000l + time;
	}

	public static Date discretize(Date d) {
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(d);
		gc.set(GregorianCalendar.SECOND, 0);
		gc.set(GregorianCalendar.MILLISECOND, 0);
		return gc.getTime();
	}

	public static long dateTimeLongDiscreteRappresentation(Date d) {
		return dateTimeLongRappresentation(discretize(d));
	}

	public static int calculateDiscreteStepsNr(Date d1, Date d2) {
		int n = 0;
		long start = d1.getTime();
		long end = d2.getTime();
		long difference = end - start;
		long steps = difference / (TIME_DISCRETE_STEP_SECONDS * 1000l);
		return (int) steps;
	}
}