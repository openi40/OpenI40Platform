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
package com.openi40.scheduler.client.model.cycles;

import java.util.UUID;

import com.openi40.scheduler.client.model.ClientDto;

public class SecondaryResourceUseSpecification extends ClientDto{
	private SecondaryResourceUseCalculationType useType = null;
	private String secondaryResourceGroupCode = null;
	private int qty = 0;
	private int minQty=0;
	private int maxQty=0;
	private SecondaryResourceUseTime usedTime = null;
	private int AfterStartMinutes = 0, BeforeStopMinutes = 0;
	public SecondaryResourceUseSpecification() {
		this.code=UUID.randomUUID().toString();
	}
	public SecondaryResourceUseCalculationType getUseType() {
		return useType;
	}

	public void setUseType(SecondaryResourceUseCalculationType useType) {
		this.useType = useType;
	}

	
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

	public SecondaryResourceUseTime getUsedTime() {
		return usedTime;
	}

	public void setUsedTime(SecondaryResourceUseTime usedTime) {
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
