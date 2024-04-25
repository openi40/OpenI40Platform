package com.openi40.scheduler.client.stomp;

import org.springframework.stereotype.Service;

import com.openi40.scheduler.client.stomp.model.EndSchedulingStatusDto;
import com.openi40.scheduler.client.stomp.model.OpenI40EndSchedulingEnvelope;
import com.openi40.scheduler.client.stomp.model.OpenI40MessageEnvelope;
import com.openi40.scheduler.client.stomp.model.OpenI40ResetDataSchedulingEnvelope;
import com.openi40.scheduler.client.stomp.model.OpenI40StartSchedulingEnvelope;
import com.openi40.scheduler.client.stomp.model.ResetDataStatusDto;
import com.openi40.scheduler.client.stomp.model.StartSchedulingStatusDto;
import com.openi40.scheduler.engine.aps.IApsComposerListener;
import com.openi40.scheduler.model.aps.ApsData;

@Service
public class StompApsComposerListener implements IApsComposerListener {
	ClientSendObjectService sendService = null;

	public StompApsComposerListener(ClientSendObjectService sendService) {
		this.sendService = sendService;
	}

	private void sendObject(ApsData data, OpenI40MessageEnvelope envelope) {
		this.sendService.notifyClients(data.getDataSourceName(), data.getDataSetName(), data.getDataSetVariant(),
				envelope);
	}

	@Override
	public void onStartAlgorithms(ApsData data) {
		sendObject(data, new OpenI40StartSchedulingEnvelope(new StartSchedulingStatusDto()));

	}

	@Override
	public void onDataClean(ApsData data) {
		sendObject(data, new OpenI40ResetDataSchedulingEnvelope(new ResetDataStatusDto()));
	}

	@Override
	public void onEndAlgorithms(ApsData data) {
		sendObject(data, new OpenI40EndSchedulingEnvelope(new EndSchedulingStatusDto()));

	}

}
