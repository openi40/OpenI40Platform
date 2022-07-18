package com.openi40.scheduler.engine.contextualplugarch;

import com.openi40.scheduler.common.aps.IApsData;
import com.openi40.scheduler.common.aps.IApsObject;
import com.openi40.scheduler.common.aps.ICloneable;
import com.openi40.scheduler.common.aps.IMetaInfo;
import com.openi40.scheduler.common.aps.IReferencingMetaInfo;
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
public interface IBusinessModelFactory {
	public <ApsType extends IApsObject> ApsType create(Class<ApsType> type, IApsData context)
			throws BusinessModelFactoryException;

	public <ApsType extends IApsObject> ApsType create(Class<ApsType> type, ApsType masterCopy)
			throws BusinessModelFactoryException;

	public <ApsType extends IApsObject> ApsType create(Class<ApsType> type, ApsType masterCopy, IApsData context)
			throws BusinessModelFactoryException;

	public <ApsType extends ICloneable> ApsType clone(ApsType instance) throws BusinessModelFactoryException;

	public <ApsMetaInfoType extends IMetaInfo, ApsType extends IReferencingMetaInfo<ApsMetaInfoType>> ApsType create(
			Class<ApsType> type, ApsMetaInfoType metaInfo, IApsData context) throws BusinessModelFactoryException;
}
