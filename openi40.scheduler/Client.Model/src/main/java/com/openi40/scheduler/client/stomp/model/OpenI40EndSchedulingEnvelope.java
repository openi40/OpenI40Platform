package com.openi40.scheduler.client.stomp.model;

public class OpenI40EndSchedulingEnvelope extends OpenI40MessageEnvelope<EndSchedulingStatusDto> {

	public OpenI40EndSchedulingEnvelope() {
		
	}

	public OpenI40EndSchedulingEnvelope(EndSchedulingStatusDto p) {
		super(p);
		
	}

}
