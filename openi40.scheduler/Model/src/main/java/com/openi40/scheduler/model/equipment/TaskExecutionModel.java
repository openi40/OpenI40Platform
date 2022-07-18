package com.openi40.scheduler.model.equipment;

import java.util.ArrayList;
import java.util.List;

import com.openi40.scheduler.common.aps.IMetaInfo;
import com.openi40.scheduler.model.AbstractApsObject;
import com.openi40.scheduler.model.aps.ApsData;
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
public class TaskExecutionModel extends AbstractApsObject implements IMetaInfo {
	public static class ResourceModelInfo extends TaskExecutionUseModel<Machine, MachinesGroup> {
		public ResourceModelInfo() {
			super(Machine.class, MachinesGroup.class);
		}
	}

	public static class SecondaryModelInfo extends TaskExecutionUseModel<Resource, ResourceGroup> {
		public SecondaryModelInfo() {
			super(Resource.class, ResourceGroup.class);
		}
	}

	public TaskExecutionModel() {
		super(null);
	}

	public TaskExecutionModel(ApsData context) {
		super(context);
	}

	private ResourceModelInfo Resource = new ResourceModelInfo();

	public final ResourceModelInfo getResource() {
		return Resource;
	}

	public final void setResource(ResourceModelInfo value) {
		Resource = value;
	}

	private List<SecondaryModelInfo> SecondaryResources = new ArrayList<SecondaryModelInfo>();

	public final List<SecondaryModelInfo> getSecondaryResources() {
		return SecondaryResources;
	}

	public final void setSecondaryResources(List<SecondaryModelInfo> value) {
		SecondaryResources = value;
	}

	
}