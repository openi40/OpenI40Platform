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
package com.openi40.scheduler.client.model.aps;

import java.util.ArrayList;
import java.util.List;

import com.openi40.scheduler.client.model.ClientDto;


public class ApsSchedulingSetDto extends ClientDto {
	String algorithmType = null;
	List<ClientDto> workOrders = new ArrayList<ClientDto>();
	ApsLogicOptionsDto options = null;
	public String getAlgorithmType() {
		return algorithmType;
	}
	public void setAlgorithmType(String algorithmType) {
		this.algorithmType = algorithmType;
	}
	public List<ClientDto> getWorkOrders() {
		return workOrders;
	}
	public void setWorkOrders(List<ClientDto> workOrders) {
		this.workOrders = workOrders;
	}
	public ApsLogicOptionsDto getOptions() {
		return options;
	}
	public void setOptions(ApsLogicOptionsDto options) {
		this.options = options;
	}
}
