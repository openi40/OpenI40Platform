package com.openi40.scheduler.input.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;
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
public class ApsSchedulingSetInputDto extends InputDto {
	int position = 0;
	private String options = null, algorithmDirection = null, algorithmType = null;
	List<ScheduledWorkOrderInputDto> scheduledWorkOrders = new ArrayList<>();

	public ApsSchedulingSetInputDto() {

	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}
	@Transient
	public List<ScheduledWorkOrderInputDto> getScheduledWorkOrders() {
		return scheduledWorkOrders;
	}

	public void setScheduledWorkOrders(List<ScheduledWorkOrderInputDto> scheduledWorkOrders) {
		this.scheduledWorkOrders = scheduledWorkOrders;
	}

	public String getOptions() {
		return options;
	}

	public void setOptions(String options) {
		this.options = options;
	}

	public String getAlgorithmDirection() {
		return algorithmDirection;
	}

	public void setAlgorithmDirection(String algorithmDirection) {
		this.algorithmDirection = algorithmDirection;
	}

	public String getAlgorithmType() {
		return algorithmType;
	}

	public void setAlgorithmType(String algorithmType) {
		this.algorithmType = algorithmType;
	}

}
