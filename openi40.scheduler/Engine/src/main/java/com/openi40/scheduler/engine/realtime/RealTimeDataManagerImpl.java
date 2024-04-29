package com.openi40.scheduler.engine.realtime;

import java.util.GregorianCalendar;

import org.springframework.beans.factory.annotation.Autowired;

import com.openi40.scheduler.common.utils.DateUtil;
import com.openi40.scheduler.engine.contextualplugarch.BusinessLogic;
import com.openi40.scheduler.engine.contextualplugarch.DefaultImplementation;
import com.openi40.scheduler.engine.timesheet.TimeSheetsInitializer;
import com.openi40.scheduler.model.aps.ApsData;
import com.openi40.scheduler.model.aps.ApsWindow;

/**
 * 
 * This code is part of the OpenI40 open source advanced production scheduler
 * platform suite, have look to its licencing options. Web site:
 * http://openi40.org/ Github: https://github.com/openi40/OpenI40Platform We
 * hope you enjoy implementing new amazing projects with it.
 * 
 * @author Paolo Zavalloni  architectures@openi40.org
 *
 */
@DefaultImplementation(implemented = IRealTimeDataManager.class, entityClass = ApsData.class)
public class RealTimeDataManagerImpl extends BusinessLogic<ApsData> implements IRealTimeDataManager {
	public final Integer REALTIME_PLANNING_DAYS = 60;
	public final Integer REALTIME_PLANNING_PAST_DAYS = 10;
	@Autowired
	TimeSheetsInitializer timeSheetsInitializer;
	public RealTimeDataManagerImpl() {

	}

	/**
	 * Moves forward the scheduling window
	 */
	@Override
	public void actualize(ApsData data) {
		if (data.isRealtime()) {
			if (data.getSchedulingWindow() == null) {
				data.setSchedulingWindow(new ApsWindow(data));
			}
			if (data.getSchedulingWindow().getRealtimePlanningDays() == null) {
				data.getSchedulingWindow().setRealtimePlanningDays(REALTIME_PLANNING_DAYS);
			}
			if (data.getSchedulingWindow().getRealtimePlanningPastDays() == null) {
				data.getSchedulingWindow().setRealtimePlanningPastDays(REALTIME_PLANNING_PAST_DAYS);
			}
			GregorianCalendar calendar = new GregorianCalendar();
			data.setActualDateTime(DateUtil.discretize(calendar.getTime()));
			calendar.set(GregorianCalendar.HOUR_OF_DAY, 0);
			calendar.set(GregorianCalendar.MINUTE, 0);
			calendar.set(GregorianCalendar.SECOND, 0);
			calendar.set(GregorianCalendar.MILLISECOND, 0);
			calendar.add(GregorianCalendar.DAY_OF_YEAR, -data.getSchedulingWindow().getRealtimePlanningPastDays());
			data.getSchedulingWindow().setStartDateTime(DateUtil.discretize(calendar.getTime()));
			calendar.add(GregorianCalendar.DAY_OF_YEAR, data.getSchedulingWindow().getRealtimePlanningDays()
					+ data.getSchedulingWindow().getRealtimePlanningPastDays());
			data.getSchedulingWindow().setEndDateTime(DateUtil.discretize(calendar.getTime()));
			timeSheetsInitializer.initializeCalendars(data);
		}

	}

}
