package com.openi40.platform.iomessages.processing;

import java.util.List;

import com.openi40.scheduler.model.messages.AbstractBaseMessage;
import com.openi40.scheduler.model.messages.handling.MessageHandlingErrorMessage;

public interface IIOMessageHandlingListener {
	void processedCorrectly(String dataSourceName, String dataSetName, String dataSetVariant,
			AbstractBaseMessage message);

	void processedUncorrectly(String dataSourceName, String dataSetName, String dataSetVariant,
			AbstractBaseMessage message, List<MessageHandlingErrorMessage> errors);
}
