package com.openi40.scheduler.input.model.cycles;

import java.util.UUID;

import javax.persistence.MappedSuperclass;

import com.openi40.scheduler.common.datamodel.ObjectReferenceConstraint;
import com.openi40.scheduler.input.model.ApsInputData;
import com.openi40.scheduler.input.model.InputDto;
import com.openi40.scheduler.input.model.companystructure.ResourceGroupInputDto;
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
public class SecondaryResourceUseSpecificationInputDto extends InputDto {
	private String useType = null;
	private String secondaryResourceGroupCode = null;
	private int qty = 0;
	private int minQty = 0;
	private int maxQty = 0;
	private String usedTime = null;
	private int AfterStartMinutes = 0, BeforeStopMinutes = 0;
	String operationEquipmentSpecCode=null;
	public String getOperationEquipmentSpecCode() {
		return operationEquipmentSpecCode;
	}
	public void setOperationEquipmentSpecCode(String operationEquipmentSpecCode) {
		this.operationEquipmentSpecCode = operationEquipmentSpecCode;
	}
	public SecondaryResourceUseSpecificationInputDto() {
		this.code = UUID.randomUUID().toString();
	}

	public String getUseType() {
		return useType;
	}

	public void setUseType(String useType) {
		this.useType = useType;
	}

	@ObjectReferenceConstraint(containerType = ApsInputData.class, referencedType = ResourceGroupInputDto.class, nullable = false)
	public String getSecondaryResourceGroupCode() {
		return secondaryResourceGroupCode;
	}

	public void setSecondaryResourceGroupCode(String secondaryResourceGroupCode) {
		this.secondaryResourceGroupCode = secondaryResourceGroupCode;
	}

	public int getQty() {
		return qty;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}

	public String getUsedTime() {
		return usedTime;
	}

	public void setUsedTime(String usedTime) {
		this.usedTime = usedTime;
	}

	public int getAfterStartMinutes() {
		return AfterStartMinutes;
	}

	public void setAfterStartMinutes(int afterStartMinutes) {
		AfterStartMinutes = afterStartMinutes;
	}

	public int getBeforeStopMinutes() {
		return BeforeStopMinutes;
	}

	public void setBeforeStopMinutes(int beforeStopMinutes) {
		BeforeStopMinutes = beforeStopMinutes;
	}

	public int getMinQty() {
		return minQty;
	}

	public void setMinQty(int minQty) {
		this.minQty = minQty;
	}

	public int getMaxQty() {
		return maxQty;
	}

	public void setMaxQty(int maxQty) {
		this.maxQty = maxQty;
	}
}
