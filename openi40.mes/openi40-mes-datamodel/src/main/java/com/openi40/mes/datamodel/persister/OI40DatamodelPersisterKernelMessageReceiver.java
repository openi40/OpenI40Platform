package com.openi40.mes.datamodel.persister;

import java.sql.Connection;
import java.sql.SQLException;

import javax.inject.Singleton;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.openi40.mes.datamodel.OI40DBMesAssetEvent;
import com.openi40.mes.datamodel.repositories.OI40MesAssetEventRepository;
import com.openi40.mes.metamessaging.handlers.MessageReceiver;
import com.openi40.mes.metamessaging.handlers.MessagingEnvironment;
import com.openi40.mes.metamessaging.model.AbstractOI40MetaMessage;
import com.openi40.mes.metamessaging.model.ManagedMessageType;

@Service
@Qualifier(value = MessageReceiver.IOT_KERNEL_RECEIVER)
public class OI40DatamodelPersisterKernelMessageReceiver implements MessageReceiver {
	static Logger LOGGER = LoggerFactory.getLogger(OI40DatamodelPersisterKernelMessageReceiver.class);

	public OI40DatamodelPersisterKernelMessageReceiver() {

	}

	@Override
	public void onMessage(AbstractOI40MetaMessage msg, MessagingEnvironment environment) {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Begin onMessage([" + msg.getMsgId() + "])"); 
		}
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("End onMessage([" + msg.getMsgId() + "])");
		}
	}

	@Override
	public boolean isCanManage(AbstractOI40MetaMessage msg) {

		return true;
	}

	@Override
	public ManagedMessageType[] getHandledTypes() {

		return new ManagedMessageType[0];
	}

}
