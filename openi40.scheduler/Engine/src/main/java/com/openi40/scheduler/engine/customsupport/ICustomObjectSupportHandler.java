package com.openi40.scheduler.engine.customsupport;

import java.util.List;

import com.openi40.scheduler.common.aps.IApsObject;
import com.openi40.scheduler.engine.contextualplugarch.BusinessInterface;
import com.openi40.scheduler.engine.contextualplugarch.IBusinessLogic;
@BusinessInterface(entityClass = IApsObject.class)
public interface ICustomObjectSupportHandler extends IBusinessLogic<IApsObject> {
	public boolean isEntityWithCustomObjectSupport(Class<? extends IApsObject> type);
	public boolean isBatchEntryInitializationEntity(Class<? extends IApsObject> entityType);
	public int getBatchEntriesSize(Class<? extends IApsObject> entityType);
	public <T extends IApsObject> void initializeEntry(Class<T> entityType,T entityEntry);
	public <T extends IApsObject> void initializeEntries(Class<T> entityType,List<T> entityEntry);
}
