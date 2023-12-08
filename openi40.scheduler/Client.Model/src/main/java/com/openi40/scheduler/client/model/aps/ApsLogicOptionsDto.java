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


public class ApsLogicOptionsDto extends ClientDto {
	private String DefaultAskedDeliveryDateConstraintPriority = null;
	private List<SupplyResolutionStrategyHolder> SupplyResolutionStrategies = new ArrayList<SupplyResolutionStrategyHolder>();
	private String DefaultCoveringType = null;
	private List<SchedulingPriorityOptionDto> SchedulingPrioritiesOptions = new ArrayList<SchedulingPriorityOptionDto>();
	private String UnallocableChildTaskAction = null;
	private List<SortOptionDto> sortOptions = new ArrayList<SortOptionDto>();
	public static class SupplyResolutionStrategyDto extends ClientDto{
		String longDescription=null;boolean userCanDisable=false;
			public SupplyResolutionStrategyDto() {
				
			}
		public SupplyResolutionStrategyDto(String code,String description,String longDescription, boolean userCanDisable) {
			this.code=code;
			this.description=description;
			this.longDescription=longDescription;this.userCanDisable=userCanDisable;
		}
		public String getLongDescription() {
			return longDescription;
		}
		public void setLongDescription(String longDescription) {
			this.longDescription = longDescription;
		}
		public boolean isUserCanDisable() {
			return userCanDisable;
		}
		public void setUserCanDisable(boolean userCanDisable) {
			this.userCanDisable = userCanDisable;
		}
	}
	
	public static class SupplyResolutionStrategyHolder {
		public SupplyResolutionStrategyHolder() {
			
		}
		public SupplyResolutionStrategyHolder(SupplyResolutionStrategyDto s,boolean enabled) {
			this.strategy=s;
			this.enabled=enabled;
		}
		SupplyResolutionStrategyDto strategy=null;
		boolean enabled=true;
		public SupplyResolutionStrategyDto getStrategy() {
			return strategy;
		}
		public void setStrategy(SupplyResolutionStrategyDto strategy) {
			this.strategy = strategy;
		}
		public boolean isEnabled() {
			return enabled;
		}
		public void setEnabled(boolean enabled) {
			this.enabled = enabled;
		}
	}
	
	public static class SchedulingPriorityOptionDto extends ClientDto{
		String longDescription=null;
		public SchedulingPriorityOptionDto() {
			
		}
		public SchedulingPriorityOptionDto(String code,String description,String longDescription) {
			this.code=code;
			this.description=description;
			this.longDescription=longDescription;
		}
		public String getLongDescription() {
			return longDescription;
		}
		public void setLongDescription(String longDescription) {
			this.longDescription = longDescription;
		}
	}
	
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
		public String getPropertyName() {
			return propertyName;
		}

		public void setPropertyName(String propertyName) {
			this.propertyName = propertyName;
		}

		public String getSortDirection() {
			return sortDirection;
		}

		public void setSortDirection(String sortDirection) {
			this.sortDirection = sortDirection;
		}

		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
		}

	}

	public String getDefaultAskedDeliveryDateConstraintPriority() {
		return DefaultAskedDeliveryDateConstraintPriority;
	}

	public void setDefaultAskedDeliveryDateConstraintPriority(String defaultAskedDeliveryDateConstraintPriority) {
		DefaultAskedDeliveryDateConstraintPriority = defaultAskedDeliveryDateConstraintPriority;
	}

	public List<SupplyResolutionStrategyHolder> getSupplyResolutionStrategies() {
		return SupplyResolutionStrategies;
	}

	public void setSupplyResolutionStrategies(List<SupplyResolutionStrategyHolder> supplyResolutionStrategies) {
		SupplyResolutionStrategies = supplyResolutionStrategies;
	}

	public String getDefaultCoveringType() {
		return DefaultCoveringType;
	}

	public void setDefaultCoveringType(String defaultCoveringType) {
		DefaultCoveringType = defaultCoveringType;
	}

	public List<SchedulingPriorityOptionDto> getSchedulingPrioritiesOptions() {
		return SchedulingPrioritiesOptions;
	}

	public void setSchedulingPrioritiesOptions(List<SchedulingPriorityOptionDto> schedulingPrioritiesOptions) {
		SchedulingPrioritiesOptions = schedulingPrioritiesOptions;
	}

	public String getUnallocableChildTaskAction() {
		return UnallocableChildTaskAction;
	}

	public void setUnallocableChildTaskAction(String unallocableChildTaskAction) {
		UnallocableChildTaskAction = unallocableChildTaskAction;
	}

	public List<SortOptionDto> getSortOptions() {
		return sortOptions;
	}

	public void setSortOptions(List<SortOptionDto> sortOptions) {
		this.sortOptions = sortOptions;
	}

	
}
