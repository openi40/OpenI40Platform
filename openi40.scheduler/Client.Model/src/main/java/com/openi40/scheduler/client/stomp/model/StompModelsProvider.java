package com.openi40.scheduler.client.stomp.model;

import java.util.List;

import org.springframework.stereotype.Service;

import com.openi40.commons.webconfigs.SwaggerAdditionalModelsProvider;

@Service
public class StompModelsProvider implements SwaggerAdditionalModelsProvider {

	public StompModelsProvider() {

	}

	@Override
	public List<Class> get() {

		return List.of(OpenI40StartSchedulingEnvelope.class, OpenI40ResetDataSchedulingEnvelope.class,
				OpenI40EndSchedulingEnvelope.class, OpenI40TaskEnvelope.class);
	}

}
