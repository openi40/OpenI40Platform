package com.openi40.scheduler.model.aps;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.openi40.scheduler.model.rules.MaterialRule;
import com.openi40.scheduler.model.rules.Rule;
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

public class ApsLogicOptions implements Serializable {
	boolean dependencySatisfiedTasksFirst=false;
	public enum OnUnallocableChildTaskAction {
		UNDO_ALL_TREE, LEAVE_ALLOCABLE_TASKS, LEAVE_AS_IS;

	}

	public enum SchedulingPriorities {
		MINIMIZE_SETUP("minize setup","chooses equipment options with minimal setup time"), // chooses between equipment those being with minimal setup time
		MINIMIZE_WORK_ELAPSED_TIME("minimum work time","chooses equipment with minimum work time to produce goods"), // Chooses equipment with minimum work time to produce goods
		MINIMIZE_WORK_END_DATETIME("finished soon","chooses equipment with sooner end of work time"); // Chooses equipment with latest end of work datetime
		
		private SchedulingPriorities(String desc,String longDesc) {
			this.description=desc;
			this.longDescription=longDesc;
		}
		String longDescription=null;
		public String getLongDescription() {
			return longDescription;
		}
		public String getDescription() {
			return description;
		}
		String description=null;
		public String getCode() {return this.toString();}
	}
	public static enum SortDirection {
		ASC, DESC
	}

	public static class SortOption {
		

		public SortOption() {
		}

		public SortOption(String property,String description,String longDescription, SortDirection dir) {
			this.propertyName = property;
			this.sortDirection = dir;
			this.description=description;
			this.longDescription=longDescription;
		}
		protected String description=null;
		protected String propertyName = null;
		protected SortDirection sortDirection = SortDirection.ASC;
		protected String longDescription=null;
		public String getDescription() {
			return description;
		}

		public String getPropertyName() {
			return propertyName;
		}

		public SortDirection getSortDirection() {
			return sortDirection;
		}

		public String getLongDescription() {
			return longDescription;
		}

	}
	
	private Rule.ConstraintPriority DefaultAskedDeliveryDateConstraintPriority = Rule.ConstraintPriority.MANDATORY;
	public static final List<MaterialRule.SupplyResolutionStrategy> SUPPLY_RESOLUTION_STRATEGY_LIST=Arrays.asList(new MaterialRule.SupplyResolutionStrategy[] { MaterialRule.SupplyResolutionStrategy.FOLLOW_PRODUCTION_LINK, MaterialRule.SupplyResolutionStrategy.USE_STOCK_IF_AVAILABLE,
			MaterialRule.SupplyResolutionStrategy.USE_PURCHASE_ORDER_IF_REQUIRED, MaterialRule.SupplyResolutionStrategy.CREATE_ODL_IF_REQUIRED, MaterialRule.SupplyResolutionStrategy.CREATE_PURCHASE_ORDER_IF_REQUIRED });
	private List<MaterialRule.SupplyResolutionStrategy> SupplyResolutionStrategies = new ArrayList<MaterialRule.SupplyResolutionStrategy>(SUPPLY_RESOLUTION_STRATEGY_LIST);
	private MaterialRule.CoveringConstraintType DefaultCoveringType = MaterialRule.CoveringConstraintType.TOTAL_COVERING_MANDATORY;
	private List<SchedulingPriorities> SchedulingPrioritiesOptions = new ArrayList<SchedulingPriorities>(Arrays.asList(new SchedulingPriorities[] { SchedulingPriorities.MINIMIZE_WORK_ELAPSED_TIME,SchedulingPriorities.MINIMIZE_WORK_END_DATETIME, SchedulingPriorities.MINIMIZE_SETUP }));
	private OnUnallocableChildTaskAction UnallocableChildTaskAction = OnUnallocableChildTaskAction.LEAVE_ALLOCABLE_TASKS;
	private List<SortOption> sortOptions=new ArrayList<SortOption>();
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("ApsLogicOptions{}");
		return sb.toString();

	}
	public boolean isDependencySatisfiedTasksFirst() {
		return dependencySatisfiedTasksFirst;
	}
	public void setDependencySatisfiedTasksFirst(boolean dependencySatisfiedTasksFirst) {
		this.dependencySatisfiedTasksFirst = dependencySatisfiedTasksFirst;
	}
	public Rule.ConstraintPriority getDefaultAskedDeliveryDateConstraintPriority() {
		return DefaultAskedDeliveryDateConstraintPriority;
	}
	public void setDefaultAskedDeliveryDateConstraintPriority(
			Rule.ConstraintPriority defaultAskedDeliveryDateConstraintPriority) {
		DefaultAskedDeliveryDateConstraintPriority = defaultAskedDeliveryDateConstraintPriority;
	}
	public List<MaterialRule.SupplyResolutionStrategy> getSupplyResolutionStrategies() {
		return SupplyResolutionStrategies;
	}
	public void setSupplyResolutionStrategies(List<MaterialRule.SupplyResolutionStrategy> supplyResolutionStrategies) {
		SupplyResolutionStrategies = supplyResolutionStrategies;
	}
	public MaterialRule.CoveringConstraintType getDefaultCoveringType() {
		return DefaultCoveringType;
	}
	public void setDefaultCoveringType(MaterialRule.CoveringConstraintType defaultCoveringType) {
		DefaultCoveringType = defaultCoveringType;
	}
	public List<SchedulingPriorities> getSchedulingPrioritiesOptions() {
		return SchedulingPrioritiesOptions;
	}
	public void setSchedulingPrioritiesOptions(List<SchedulingPriorities> schedulingPrioritiesOptions) {
		SchedulingPrioritiesOptions = schedulingPrioritiesOptions;
	}
	public OnUnallocableChildTaskAction getUnallocableChildTaskAction() {
		return UnallocableChildTaskAction;
	}
	public void setUnallocableChildTaskAction(OnUnallocableChildTaskAction unallocableChildTaskAction) {
		UnallocableChildTaskAction = unallocableChildTaskAction;
	}
	public List<SortOption> getSortOptions() {
		return sortOptions;
	}
	public void setSortOptions(List<SortOption> sortOptions) {
		this.sortOptions = sortOptions;
	}
}