package com.openi40.scheduler.model;

import com.openi40.scheduler.common.aps.ICloneable;
import com.openi40.scheduler.common.aps.IMetaInfo;
import com.openi40.scheduler.common.aps.IReferencingMetaInfo;
import com.openi40.scheduler.model.aps.ApsData;

import lombok.Data;
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
@Data
public class AbstractApsObjectReferencingMetaInfo<ModelType extends IMetaInfo> extends AbstractApsObject implements ICloneable, IReferencingMetaInfo<ModelType> {
	protected ModelType metaInfo;

	public AbstractApsObjectReferencingMetaInfo(ApsData context) {
		super(context);
	}


	public ICloneable cleanClone() throws CloneNotSupportedException {
		return (ICloneable) clone();
	}

	protected void finalize() throws Throwable {
		setMetaInfo(null);
	}
}