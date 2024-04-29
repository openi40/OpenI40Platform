package com.openi40.scheduler.mapper.apsmodel2clientmodel;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.stereotype.Service;

import com.openi40.scheduler.client.model.time.TimesheetDto;
import com.openi40.scheduler.client.model.time.TimesheetDto.DayDto;
import com.openi40.scheduler.client.model.time.TimesheetDto.HourDto;
import com.openi40.scheduler.client.model.time.TimesheetDto.MonthDto;
import com.openi40.scheduler.client.model.time.TimesheetDto.TurnDto;
import com.openi40.scheduler.client.model.time.TimesheetDto.YearDto;
import com.openi40.scheduler.client.model.time.TimesheetDto.YearQuarterDto;
import com.openi40.scheduler.common.utils.DateUtil;
import com.openi40.scheduler.mapper.AbstractReflectorMapper;
import com.openi40.scheduler.mapper.IEntitiesFactory;
import com.openi40.scheduler.mapper.IMapper;
import com.openi40.scheduler.mapper.MapperException;
import com.openi40.scheduler.model.time.Timesheet;
import com.openi40.scheduler.model.time.TimeSegment;
import com.openi40.scheduler.model.time.TimeSegmentsGroup;
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
@Service
public class ApsModelCalendar2ClientModelCalendarDtoService extends AbstractReflectorMapper<Timesheet, TimesheetDto>
		implements IMapper<Timesheet, TimesheetDto> {

	public ApsModelCalendar2ClientModelCalendarDtoService(@Autowired AutowireCapableBeanFactory beanFactory) {
		super(beanFactory, Timesheet.class, TimesheetDto.class, ApsModel2ClientModelConfiguration.typeMap);
	}

	static DateFormat simpleDateTimeFormat = SimpleDateFormat.getDateTimeInstance(SimpleDateFormat.SHORT,
			SimpleDateFormat.SHORT, Locale.ITALIAN);
	static DateFormat simpleDateFormat = SimpleDateFormat.getDateInstance(SimpleDateFormat.SHORT, Locale.ITALIAN);
	static DateFormat simpleTimeFormat = SimpleDateFormat.getTimeInstance(SimpleDateFormat.SHORT, Locale.ITALIAN);

	@Override
	public void copyValue(Timesheet originalObject, TimesheetDto targetObject, IEntitiesFactory entityFactory,
			Map environment, boolean recursive) throws MapperException {

		super.copyValue(originalObject, targetObject, entityFactory, environment, recursive);
		TimeSegmentsGroup mainRange = originalObject.getMainRange();
		int startDate = 0, endDate = 0;
		if (mainRange.getStartDateTime() != null) {
			startDate = (int) DateUtil.dateLongRappresentation(mainRange.getStartDateTime());
		}
		if (mainRange.getEndDateTime() != null) {
			endDate = (int) DateUtil.dateLongRappresentation(mainRange.getEndDateTime());
		}
		Map<Long, YearDto> years = new TreeMap();
		Map<Long, YearQuarterDto> quarters = new TreeMap<>();
		Map<Long, MonthDto> months = new TreeMap<>();
		Map<Long, DayDto> days = new TreeMap<>();
		Map<Long, HourDto> hours = new TreeMap<>();
		Map<Long, TurnDto> turns = new TreeMap<Long, TurnDto>();
		if (startDate > 0 && endDate > 0 && startDate < endDate) {
			List<TimeSegment> calendarEntries = mainRange.getCalendarEvents();
			for (int i = 0; i < calendarEntries.size(); i++) {
				TimeSegment segment = calendarEntries.get(i);
				Date start = segment.getStartDateTime();
				Date end = segment.getEndDateTime();
				TurnDto turnDto = new TurnDto();
				turnDto.setCode("TRN" + i);
				turnDto.setId(turnDto.getCode());
				turnDto.setStartDateTime(start);
				turnDto.setStartShortDateTime(simpleDateTimeFormat.format(start));
				turnDto.setStartShortDate(simpleDateFormat.format(start));
				turnDto.setStartShortTime(simpleTimeFormat.format(start));
				turnDto.setEndDateTime(end);
				turnDto.setEndShortDateTime(simpleDateTimeFormat.format(end));
				turnDto.setEndShortDate(simpleDateFormat.format(end));
				turnDto.setEndShortTime(simpleTimeFormat.format(end));
				turnDto.setDate(DateUtil.dateLongRappresentation(start));
				String _description = turnDto.getStartShortDateTime() + "<-->" + turnDto.getEndShortDateTime();
				turnDto.setDescription(_description);
				GregorianCalendar gc = new GregorianCalendar();
				gc.setTime(start);
				while (gc.getTime().before(end)) {
					Date _start = gc.getTime();
					gc.add(GregorianCalendar.HOUR, 1);
					Date _end = gc.getTime();
					HourDto hourDto = new HourDto();
					hourDto.setStartDateTime(_start);
					hourDto.setStartShortDateTime(simpleDateTimeFormat.format(_start));
					if (_end.before(end)) {
						hourDto.setEndDateTime(_end);
					} else
						hourDto.setEndDateTime(end);
					hourDto.setEndShortDateTime(simpleDateTimeFormat.format(hourDto.getEndDateTime()));
					hourDto.setHour(DateUtil.dateTimeLongDiscreteRappresentation(_start) / 10000);
					hourDto.setStartShortDate(simpleDateFormat.format(_start));
					hourDto.setStartShortTime(simpleTimeFormat.format(_start));
					hourDto.setEndShortDate(simpleDateFormat.format(_end));
					hourDto.setEndShortTime(simpleTimeFormat.format(_end));
					turnDto.getHours().add(hourDto);
					String description = hourDto.getStartShortDateTime() + "<-->" + hourDto.getEndShortDateTime();
					hourDto.setDescription(description);
					hourDto.setId("H" + hourDto.getHour());
					long day = DateUtil.dateLongRappresentation(_start);
					if (!days.containsKey(day)) {
						DayDto _day = new DayDto();
						_day.setDate(day);
						GregorianCalendar _gc = new GregorianCalendar();
						_gc.setTime(hourDto.getStartDateTime());
						_gc.set(GregorianCalendar.HOUR_OF_DAY, 0);
						_gc.set(GregorianCalendar.MINUTE, 0);
						_gc.set(GregorianCalendar.SECOND, 0);
						_gc.set(GregorianCalendar.MILLISECOND, 0);
						_day.setStartDateTime(_gc.getTime());
						_gc.set(GregorianCalendar.HOUR_OF_DAY, 24);
						_day.setEndDateTime(_gc.getTime());
						_day.setId("day" + day);
						_day.setDescription(SimpleDateFormat.getDateInstance(SimpleDateFormat.SHORT, Locale.ITALIAN)
								.format(hourDto.getStartDateTime()));
						days.put(day, _day);
						long month = day / 100;
						if (!months.containsKey(month)) {
							MonthDto _month = new MonthDto();
							_month.setMonth(month);
							_month.setId("m" + month);
							months.put(month, _month);
							long year = month / 100;
							long quarter = (((month - (year * 100)) - 1) / 3) + 1;
							long quarterPart = quarter;
							quarter = quarter + (year * 100);
							if (!quarters.containsKey(quarter)) {
								YearQuarterDto yqdto = new YearQuarterDto();
								yqdto.setQuarter(quarter);
								yqdto.setId("q" + quarter);
								yqdto.setDescription("Quarter " + quarterPart + " year " + year);
								quarters.put(quarter, yqdto);
								if (!years.containsKey(year)) {
									YearDto y = new YearDto();
									y.setId("y" + year);
									y.setYear(year);
									y.setDescription("Year " + year);
									years.put(year, y);
									targetObject.getYears().add(y);
								}
								years.get(year).getQuarters().add(yqdto);
							}
							quarters.get(quarter).getMonths().add(_month);
						}
						months.get(month).getDays().add(_day);
					}
					DayDto _day = days.get(day);
					if (!_day.getTurns().contains(turnDto)) {
						_day.getTurns().add(turnDto);
					}
				}
			}
		}
	}
}