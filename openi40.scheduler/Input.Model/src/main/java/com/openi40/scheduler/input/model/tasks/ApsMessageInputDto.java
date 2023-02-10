package com.openi40.scheduler.input.model.tasks;

import javax.persistence.MappedSuperclass;

import com.openi40.scheduler.input.model.InputDto;

import lombok.Data;

@Data
@MappedSuperclass
public class ApsMessageInputDto extends InputDto {
	private Integer position=0;
	private String taskCode = null;
	private String messageCategory = null;
	private String messageCode = null;
	private String messageDescription = null;
	private String msgLevel = null;
	private String sourceModule = null;
	private String sourceObjectClass = null;	
	private Integer globalPosition=0;
	public ApsMessageInputDto() {

	}

}
