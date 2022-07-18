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
package com.openi40.scheduler.client.graphs.model;

import java.util.ArrayList;
import java.util.List;

import com.openi40.scheduler.client.model.ClientDto;
import com.openi40.scheduler.client.model.companystructure.ResourceGroupDto;
import com.openi40.scheduler.client.model.companystructure.WorkCenterDto;
import com.openi40.scheduler.client.model.time.TimesheetDto;
import com.openi40.scheduler.client.model.time.UsageTimeSegmentDto;

import lombok.Data;
@Data
public class ResourcesUsageGraphDto extends ClientDto {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4998358706998813913L;
	@Data
	public static class ResourceUsageDto extends ClientDto {
		List<UsageTimeSegmentDto> usages=new ArrayList<UsageTimeSegmentDto>();
	}
	@Data
	public static class BaseGroupUsageDto<ResourceGroupType extends ClientDto> extends ClientDto {
		List<ResourceUsageDto> resources=new ArrayList<ResourceUsageDto>();
	}
	public static class WorkcenterUsageDto extends BaseGroupUsageDto<WorkCenterDto>{
		
	}
	public static class ResourcesGroupUsageDto extends BaseGroupUsageDto<ResourceGroupDto>{
		
	}
	@Data
	public static class UsageDataItemDto extends ClientDto{
		List<WorkcenterUsageDto> workcenters=new ArrayList<ResourcesUsageGraphDto.WorkcenterUsageDto>();
		List<ResourcesGroupUsageDto> secondaryResourceGroups=new ArrayList<ResourcesUsageGraphDto.ResourcesGroupUsageDto>();		
	}
	TimesheetDto timesheet=null;
	List<GraphicContextNodeDto<UsageDataItemDto>> roots=new ArrayList<GraphicContextNodeDto<UsageDataItemDto>>();
}
