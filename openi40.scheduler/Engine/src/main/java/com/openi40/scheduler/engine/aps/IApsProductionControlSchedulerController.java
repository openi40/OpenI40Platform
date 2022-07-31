package com.openi40.scheduler.engine.aps;

import com.openi40.scheduler.engine.contextualplugarch.BusinessInterface;
import com.openi40.scheduler.engine.contextualplugarch.IBusinessLogic;
import com.openi40.scheduler.model.aps.ApsData;
@BusinessInterface(entityClass = ApsData.class)
public interface IApsProductionControlSchedulerController extends IBusinessLogic<ApsData> {
	public void prepareSchedulingData(ApsData context);
}
