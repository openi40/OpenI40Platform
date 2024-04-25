package com.openi40.scheduler.engine.contextualplugarch;

import com.openi40.scheduler.common.aps.IApsObject;
import com.openi40.scheduler.common.aps.IEnvironment;

public interface IScriptedBusinessLogic<BusinessDataType extends IApsObject> extends IBusinessLogic<BusinessDataType> {
	boolean isCanManage(BusinessDataType entityObject, IEnvironment environment);
}
