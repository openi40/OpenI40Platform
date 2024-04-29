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
package com.openi40.scheduler.client.model.tasks;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.openi40.scheduler.client.model.time.TimeSegmentDto;


public class EquipmentInfoDto implements Serializable {
	String setupGroupCode = null;
	List<RCUse> machine = new ArrayList<>();
	List<RCUse> secondaries = new ArrayList<>();
	
	public static class RCUse extends TimeSegmentDto {

	}

	public EquipmentInfoDto() {
	}

	public String getSetupGroupCode() {
		return setupGroupCode;
	}

	public void setSetupGroupCode(String setupGroupCode) {
		this.setupGroupCode = setupGroupCode;
	}

	public List<RCUse> getMachine() {
		return machine;
	}

	public void setMachine(List<RCUse> machine) {
		this.machine = machine;
	}

	public List<RCUse> getSecondaries() {
		return secondaries;
	}

	public void setSecondaries(List<RCUse> secondaries) {
		this.secondaries = secondaries;
	}

	
}
