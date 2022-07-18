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
package com.openi40.scheduler.client.model.aps;

import java.util.ArrayList;
import java.util.List;

import com.openi40.scheduler.client.model.ClientDto;

import lombok.Data;

@Data
public class ApsLogicOptionsDto extends ClientDto {
	private String DefaultAskedDeliveryDateConstraintPriority = null;
	private List<SupplyResolutionStrategyHolder> SupplyResolutionStrategies = new ArrayList<SupplyResolutionStrategyHolder>();
	private String DefaultCoveringType = null;
	private List<SchedulingPriorityOptionDto> SchedulingPrioritiesOptions = new ArrayList<SchedulingPriorityOptionDto>();
	private String UnallocableChildTaskAction = null;
	@Data 
	public static class SupplyResolutionStrategyDto extends ClientDto{
		String longDescription=null;boolean userCanDisable=false;
			public SupplyResolutionStrategyDto() {
				
			}
		public SupplyResolutionStrategyDto(String code,String description,String longDescription, boolean userCanDisable) {
			this.code=code;
			this.description=description;
			this.longDescription=longDescription;this.userCanDisable=userCanDisable;
		}
	}
	@Data
	public static class SupplyResolutionStrategyHolder {
		public SupplyResolutionStrategyHolder() {
			
		}
		public SupplyResolutionStrategyHolder(SupplyResolutionStrategyDto s,boolean enabled) {
			this.strategy=s;
			this.enabled=enabled;
		}
		SupplyResolutionStrategyDto strategy=null;
		boolean enabled=true;
	}
	@Data 
	public static class SchedulingPriorityOptionDto extends ClientDto{
		String longDescription=null;
		public SchedulingPriorityOptionDto() {
			
		}
		public SchedulingPriorityOptionDto(String code,String description,String longDescription) {
			this.code=code;
			this.description=description;
			this.longDescription=longDescription;
		}
	}
	@Data
	public static class SortOptionDto extends ClientDto {

		public SortOptionDto() {
		}

		public SortOptionDto(String propertyName2,String description, String direction) {
			this.propertyName=propertyName2;
			this.code=propertyName2;
			this.sortDirection=direction;
			this.description=description;
		}

		protected String propertyName = null;
		protected String sortDirection = "ASC";
		protected String description=null;

	}

	private List<SortOptionDto> sortOptions = new ArrayList<SortOptionDto>();
}
