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
package com.openi40.scheduler.client.graphs.model;

import java.util.ArrayList;
import java.util.List;

import com.openi40.scheduler.client.model.ClientDto;
import com.openi40.scheduler.client.model.companystructure.ResourceGroupDto;
import com.openi40.scheduler.client.model.companystructure.WorkCenterDto;
import com.openi40.scheduler.client.model.time.TimesheetDto;
import com.openi40.scheduler.client.model.time.UsageTimeSegmentDto;

public class ResourcesUsageGraphDto extends ClientDto {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4998358706998813913L;

	public static class ResourceUsageDto extends ClientDto {
		List<UsageTimeSegmentDto> usages=new ArrayList<UsageTimeSegmentDto>();

		public List<UsageTimeSegmentDto> getUsages() {
			return usages;
		}

		public void setUsages(List<UsageTimeSegmentDto> usages) {
			this.usages = usages;
		}
	}
	
	public static class BaseGroupUsageDto<ResourceGroupType extends ClientDto> extends ClientDto {
		List<ResourceUsageDto> resources=new ArrayList<ResourceUsageDto>();

		public List<ResourceUsageDto> getResources() {
			return resources;
		}

		public void setResources(List<ResourceUsageDto> resources) {
			this.resources = resources;
		}
	}
	public static class WorkcenterUsageDto extends BaseGroupUsageDto<WorkCenterDto>{
		
	}
	public static class ResourcesGroupUsageDto extends BaseGroupUsageDto<ResourceGroupDto>{
		
	}
	
	public static class UsageDataItemDto extends ClientDto{
		List<WorkcenterUsageDto> workcenters=new ArrayList<ResourcesUsageGraphDto.WorkcenterUsageDto>();
		List<ResourcesGroupUsageDto> secondaryResourceGroups=new ArrayList<ResourcesUsageGraphDto.ResourcesGroupUsageDto>();
		public List<WorkcenterUsageDto> getWorkcenters() {
			return workcenters;
		}
		public void setWorkcenters(List<WorkcenterUsageDto> workcenters) {
			this.workcenters = workcenters;
		}
		public List<ResourcesGroupUsageDto> getSecondaryResourceGroups() {
			return secondaryResourceGroups;
		}
		public void setSecondaryResourceGroups(List<ResourcesGroupUsageDto> secondaryResourceGroups) {
			this.secondaryResourceGroups = secondaryResourceGroups;
		}		
	}
	TimesheetDto timesheet=null;
	List<GraphicContextNodeDto<UsageDataItemDto>> roots=new ArrayList<GraphicContextNodeDto<UsageDataItemDto>>();
	public TimesheetDto getTimesheet() {
		return timesheet;
	}
	public void setTimesheet(TimesheetDto timesheet) {
		this.timesheet = timesheet;
	}
	public List<GraphicContextNodeDto<UsageDataItemDto>> getRoots() {
		return roots;
	}
	public void setRoots(List<GraphicContextNodeDto<UsageDataItemDto>> roots) {
		this.roots = roots;
	}
	
}
