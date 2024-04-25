package com.openi40.scheduler.client.stomp;

import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class ClientSendObjectService {
	private SimpMessagingTemplate sendTemplate = null;

	public ClientSendObjectService(SimpMessagingTemplate sendTemplate) {
		this.sendTemplate = sendTemplate;
	}

	@MessageMapping("/schedule/{dataSourceName}/{dataSetId}/{dataSetVariant}")
	public void notifyClients(@DestinationVariable(value = "dataSourceName") String dataSourceName,
			@DestinationVariable(value = "dataSetId") String dataSetId,
			@DestinationVariable(value = "dataSetVariant") String dataSetVariant, final Object message) {
		sendTemplate.convertAndSend("/topic/schedule/" + dataSourceName + "/" + dataSetId + "/" + dataSetVariant,
				message);
	}
}
