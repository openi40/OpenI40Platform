package com.openi40.scheduler.client.stomp.model;

import com.openi40.scheduler.client.model.tasks.TaskDto;

public class OpenI40TaskEnvelope extends OpenI40MessageEnvelope<TaskDto> {

	public OpenI40TaskEnvelope() {
		// TODO Auto-generated constructor stub
	}

	public OpenI40TaskEnvelope(TaskDto p) {
		super(p);
		// TODO Auto-generated constructor stub
	}

}
