package com.openi40.mes.io.logical.apsbroker.model;

import java.io.Serializable;

import com.openi40.mes.metamessaging.model.AbstractOI40IOTApplicationMessage;
import com.openi40.mes.metamessaging.model.ConfigurableMessageProperty;
import com.openi40.mes.metamessaging.model.ConfigurableMessageType;
import com.openi40.scheduler.iomessages.AbortTaskIOMessage;
import com.openi40.scheduler.iomessages.AbstractBaseIOMessage;
import com.openi40.scheduler.iomessages.EndSetupIOMessage;
import com.openi40.scheduler.iomessages.EndWorkIOMessage;
import com.openi40.scheduler.iomessages.OutOfOrderMachineIOMessage;
import com.openi40.scheduler.iomessages.PauseTaskIOMessage;
import com.openi40.scheduler.iomessages.StartSetupIOMessage;
import com.openi40.scheduler.iomessages.StartWorkIOMessage;
import com.openi40.scheduler.iomessages.TaskProductionUpdateIOMessage;
import com.openi40.scheduler.iomessages.UnderMantainmentMachineIOMessage;
import com.openi40.scheduler.iomessages.UsedSecondaryResourcesInfo;

@ConfigurableMessageType(typeDescription = "Outgoing messages for the OpenI40 APS System")

public class Openi40ApsOutgoingMessage extends AbstractOI40IOTApplicationMessage implements Serializable {
	private static final Class[] APS_IO_MESSAGE_TYPES = { AbortTaskIOMessage.class, EndSetupIOMessage.class,
			EndWorkIOMessage.class, OutOfOrderMachineIOMessage.class, PauseTaskIOMessage.class,
			StartSetupIOMessage.class, StartWorkIOMessage.class, TaskProductionUpdateIOMessage.class,
			UnderMantainmentMachineIOMessage.class, UsedSecondaryResourcesInfo.class };

	private AbstractBaseIOMessage apsContent = null;
	/**
	 * 
	 */
	private static final long serialVersionUID = 2488579198526066531L;

	public Openi40ApsOutgoingMessage() {

	}

	@ConfigurableMessageProperty(propertyDescription = "Aps message content", possibleTypes = {
			AbortTaskIOMessage.class, EndSetupIOMessage.class, EndWorkIOMessage.class, OutOfOrderMachineIOMessage.class,
			PauseTaskIOMessage.class, StartSetupIOMessage.class, StartWorkIOMessage.class,
			TaskProductionUpdateIOMessage.class, UnderMantainmentMachineIOMessage.class,
			UsedSecondaryResourcesInfo.class }) 
	public AbstractBaseIOMessage getApsContent() {
		return apsContent;
	}

	public void setApsContent(AbstractBaseIOMessage apsContent) {
		this.apsContent = apsContent;
	}

}
