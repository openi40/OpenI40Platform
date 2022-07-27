package com.openi40.scheduler.engine.realtime;

import java.util.Date;
import java.util.GregorianCalendar;

import com.openi40.scheduler.engine.contextualplugarch.BusinessLogic;
import com.openi40.scheduler.engine.contextualplugarch.DefaultImplementation;
import com.openi40.scheduler.model.aps.ApsData;
import com.openi40.scheduler.model.aps.ApsWindow;

/**
 * 
 * This code is part of the OpenI40 open source advanced production scheduler
 * platform suite, have look to its licencing options. Web site:
 * http://openi40.org/ Github: https://github.com/openi40/OpenI40Platform We
 * hope you enjoy implementing new amazing projects with it.
 * 
 * @author architectures@openi40.org
 *
 */
@DefaultImplementation(implemented = IRealTimeDataManager.class, entityClass = ApsData.class)
public class RealtimDataManagerImpl extends BusinessLogic<ApsData> implements IRealTimeDataManager {
	public final Integer REALTIME_PLANNING_DAYS = 60;

	public RealtimDataManagerImpl() {

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
			GregorianCalendar calendar = new GregorianCalendar();
			data.getSchedulingWindow().setStartDateTime(calendar.getTime());
			calendar.add(GregorianCalendar.DAY_OF_YEAR, data.getSchedulingWindow().getRealtimePlanningDays());
			data.getSchedulingWindow().setEndDateTime(calendar.getTime());
		}

	}

}
