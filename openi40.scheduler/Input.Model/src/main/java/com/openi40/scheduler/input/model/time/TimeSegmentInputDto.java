package com.openi40.scheduler.input.model.time;

import java.util.Date;

import javax.persistence.MappedSuperclass;

import com.openi40.scheduler.input.model.InputDto;

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
@MappedSuperclass
public class TimeSegmentInputDto extends InputDto {
	Date startDateTime=null,endDateTime=null;

}
