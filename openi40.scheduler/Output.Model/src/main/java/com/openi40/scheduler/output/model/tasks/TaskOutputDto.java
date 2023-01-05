package com.openi40.scheduler.output.model.tasks;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.openi40.scheduler.output.model.OutputDto;

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
public class TaskOutputDto extends OutputDto {
	protected String workOrderCode = null;
	protected boolean successfullyScheduled = false;
	protected String workCenterCode = null;
	protected String cycleCode = null;
	protected String operationCode = null;
	protected String sequenceCode = null;
	protected String predefinedMachineCode = null;
	protected String forcedMachineCode = null;
	protected String scheduledMachineCode = null;
	protected boolean workOrderRootTask = false;
	protected String equipmentSpecCode=null;
	protected Timestamp askedDeliveryDateTime=null;
	protected String salesOrderLineCode = null;
	protected double qtyTotal = 0.0;
	protected double qtyProduced = 0.0;
	protected Integer customPriority = 0;
	protected double setupTime=0.0;
	protected double workTime=0.0;
	protected String setupGroupCode=null;
	protected Timestamp minProductionDateConstraint=null;
	protected Timestamp maxProductionDateConstraint=null;
	protected String status = null;
	protected Date acquiredStartSetup = null;
	protected Date acquiredEndSetup = null;
	protected Date acquiredStartWork = null;
	protected Date acquiredEndWork = null;
	protected Date acquiredProductionUpdate = null;
	protected String acquiredMachineCode = null;
	
	protected List<UsedSecondaryResourcesInfoOutputDto> acquiredSetupUsedResources = new ArrayList<UsedSecondaryResourcesInfoOutputDto>();
	
	protected List<UsedSecondaryResourcesInfoOutputDto> acquiredWorkUsedResources = new ArrayList<UsedSecondaryResourcesInfoOutputDto>();
	@Data
	public static class SecondaryReservation {
		public SecondaryReservation() {

		}

		String resourceCode = null;
		String secondaryResourceGroupCode = null;
		String slotType = null;
		Date start = null;
		Date end = null;
	}

	public static enum LinkType {
		STOCK, PRODUCTION, PURCHASE
	}

	@Data
	public static class TaskMaterialTransfer {
		String productCode=null;
		String warehouseCode = null;
		String taskCode = null;
		String machineCode = null;
		String workCenterCode = null;
		String supplyTaskCode = null;
		String supplyWorkOrderCode = null;
		String purchaseOrderCode = null;
		String purchaseOrderLineCode = null;
		Date startTransfer = null;
		Date endTransfer = null;
		String transferType = null;
		double batchQty = 0;
		double nrTransfers = 0;
		double qtyReserved = 0;
		LinkType linkType = null;
	}
	@Data
	public static class UsedSecondaryResourcesInfoOutputDto extends OutputDto {
		private String resourceGroup = null;
		private List<String> usedResourcesCodes = new ArrayList<String>();
	}
	protected List<SecondaryReservation> secondaryReservations = new ArrayList<>();
	protected List<TaskMaterialTransfer> materialTransfer = new ArrayList<>();
	protected Date startPreparation = null, endPreparation = null, startExecution = null, endExecution = null;

}
