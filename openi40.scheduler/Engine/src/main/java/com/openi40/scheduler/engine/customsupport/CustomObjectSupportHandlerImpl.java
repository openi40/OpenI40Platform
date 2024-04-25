package com.openi40.scheduler.engine.customsupport;

import java.util.List;

import com.openi40.scheduler.common.aps.IApsObject;
import com.openi40.scheduler.engine.contextualplugarch.BusinessLogic;
import com.openi40.scheduler.engine.contextualplugarch.DefaultImplementation;
import com.openi40.scheduler.engine.cyclesrouting.IProductionCycleRoutingStrategy;
import com.openi40.scheduler.model.orders.WorkOrder;
@DefaultImplementation(implemented = ICustomObjectSupportHandler.class, entityClass = IApsObject.class)
public class CustomObjectSupportHandlerImpl extends BusinessLogic<IApsObject> implements ICustomObjectSupportHandler {

	@Override
	public boolean isEntityWithCustomObjectSupport(Class<? extends IApsObject> type) {
		
		return false;
	}

	@Override
	public boolean isBatchEntryInitializationEntity(Class<? extends IApsObject> entityType) {
		
		return false;
	}

	@Override
	public int getBatchEntriesSize(Class<? extends IApsObject> entityType) {
		
		return 0;
	}

	@Override
	public <T extends IApsObject> void initializeEntry(Class<T> entityType, T entityEntry) {
		

	}

	@Override
	public <T extends IApsObject> void initializeEntries(Class<T> entityType, List<T> entityEntry) {
		

	}

}
