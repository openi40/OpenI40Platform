package com.openi40.scheduler.output.model.equipment;

import java.util.Date;

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
public class MachineReservationDto extends OutputDto {
	protected String machineCode = null, taskCode = null;
	protected Date startReservation = null;
	protected Date endReservation = null;
	protected String reservationType = null;

	public MachineReservationDto() {

	}

}
