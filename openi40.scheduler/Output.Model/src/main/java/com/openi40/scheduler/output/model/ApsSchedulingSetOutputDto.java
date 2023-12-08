package com.openi40.scheduler.output.model;

import java.util.ArrayList;
import java.util.List;
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

public class ApsSchedulingSetOutputDto extends OutputDto {
	private String options = null, algorithmDirection = null, algorithmType = null;
	private List<String> workOrderCodes = new ArrayList<>();

	public ApsSchedulingSetOutputDto() {

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

	public List<String> getWorkOrderCodes() {
		return workOrderCodes;
	}

	public void setWorkOrderCodes(List<String> workOrderCodes) {
		this.workOrderCodes = workOrderCodes;
	}

}
