package com.openi40.scheduler.engine.contextualplugarch;

import com.openi40.scheduler.common.aps.IApsObject;
import com.openi40.scheduler.common.aps.IEnvironment;
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
public class TypeSwitchedBusinessLogicFactory<BusinessComponentType extends IBusinessLogic<BusinessDataType>, BusinessDataType extends IApsObject, SwitchingBusinessDataSubType extends BusinessDataType>
		extends BusinessLogicFactory<BusinessComponentType, BusinessDataType> {
	private String enumConstant;
	protected Class<SwitchingBusinessDataSubType> switchingDataClass = null;

	public TypeSwitchedBusinessLogicFactory(String enumConstant, Class<BusinessComponentType> businessComponentType,
			Class<SwitchingBusinessDataSubType> switchingDataClass, Class defaultImplementation) {
		super(businessComponentType, defaultImplementation);
		this.enumConstant = enumConstant;
		this.switchingDataClass = switchingDataClass;
	}

	@Override
	public String getImplementationKey() {
		return enumConstant;
	}

	@Override
	public boolean isCanManage(BusinessDataType entityObject, IEnvironment environment) {
		return switchingDataClass.isAssignableFrom(entityObject.getClass());
	}
}