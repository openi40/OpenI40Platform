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
package com.openi40.scheduler.client.model.tasks;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.openi40.scheduler.client.model.ClientDto;
import com.openi40.scheduler.client.model.equipment.MachineDto;
import com.openi40.scheduler.client.model.equipment.SecondaryResourceDto;
import com.openi40.scheduler.client.model.time.TimeSegmentDto;

import lombok.Data;

@Data
public class EquipmentInfoDto implements Serializable {
	String setupGroupCode = null;

	@Data
	public static class RCUse extends TimeSegmentDto {

	}

	public EquipmentInfoDto() {
	}

	List<RCUse> machine = new ArrayList<>();
	List<RCUse> secondaries = new ArrayList<>();
}
