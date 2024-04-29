package com.openi40.scheduler.input.model.tasks;

import javax.persistence.MappedSuperclass;

import com.openi40.scheduler.common.datamodel.ObjectReferenceConstraint;
import com.openi40.scheduler.input.model.ApsInputData;
import com.openi40.scheduler.input.model.InputDto;
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
@MappedSuperclass
public class TaskRelationInputDto extends InputDto {

	String supplierTaskCode = null, consumerTaskCode = null, alignmentType = null, timeRangeType = null,
			peggingCode = null, bomItemCode = null, consumptionTransferType = null;
	Boolean peggingEdge = false;
	long offsetMillisecs = 0L;
	double consumptionBatchQty = 0.0;

	@ObjectReferenceConstraint(containerType = ApsInputData.class, referencedType = TaskInputDto.class, nullable = false)
	public String getSupplierTaskCode() {
		return supplierTaskCode;
	}

	public void setSupplierTaskCode(String supplierTaskCode) {
		this.supplierTaskCode = supplierTaskCode;
	}

	@ObjectReferenceConstraint(containerType = ApsInputData.class, referencedType = TaskInputDto.class, nullable = false)
	public String getConsumerTaskCode() {
		return consumerTaskCode;
	}

	public void setConsumerTaskCode(String consumerTaskCode) {
		this.consumerTaskCode = consumerTaskCode;
	}

	public String getAlignmentType() {
		return alignmentType;
	}

	public void setAlignmentType(String alignmentType) {
		this.alignmentType = alignmentType;
	}

	public String getTimeRangeType() {
		return timeRangeType;
	}

	public void setTimeRangeType(String timeRangeType) {
		this.timeRangeType = timeRangeType;
	}

	public String getPeggingCode() {
		return peggingCode;
	}

	public void setPeggingCode(String peggingCode) {
		this.peggingCode = peggingCode;
	}

	public String getBomItemCode() {
		return bomItemCode;
	}

	public void setBomItemCode(String bomItemCode) {
		this.bomItemCode = bomItemCode;
	}

	public String getConsumptionTransferType() {
		return consumptionTransferType;
	}

	public void setConsumptionTransferType(String consumptionTransferType) {
		this.consumptionTransferType = consumptionTransferType;
	}

	public Boolean getPeggingEdge() {
		return peggingEdge;
	}

	public void setPeggingEdge(Boolean peggingEdge) {
		this.peggingEdge = peggingEdge;
	}

	public long getOffsetMillisecs() {
		return offsetMillisecs;
	}

	public void setOffsetMillisecs(long offsetMillisecs) {
		this.offsetMillisecs = offsetMillisecs;
	}

	public double getConsumptionBatchQty() {
		return consumptionBatchQty;
	}

	public void setConsumptionBatchQty(double consumptionBatchQty) {
		this.consumptionBatchQty = consumptionBatchQty;
	}

}
