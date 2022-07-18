package com.openi40.scheduler.output.model;

import java.util.ArrayList;
import java.util.List;

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
public class ApsSchedulingSetOutputDto extends OutputDto {
	private String options = null, algorithmDirection = null, algorithmType = null;
	private List<String> workOrderCodes = new ArrayList<>();

	public ApsSchedulingSetOutputDto() {

	}

}
