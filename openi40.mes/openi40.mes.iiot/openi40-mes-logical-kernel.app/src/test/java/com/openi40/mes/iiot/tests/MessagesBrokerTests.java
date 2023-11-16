package com.openi40.mes.iiot.tests;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Hashtable;
import java.util.Properties;
import java.util.Vector;

import javax.sql.DataSource;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.hivemq.HiveMQServer;
import com.hivemq.embedded.EmbeddedHiveMQ;
import com.openi40.mes.datamodel.persister.MesLogicalMsg;
import com.openi40.mes.io.mqtt.generic.manager.MqttGenericManager;
import com.openi40.mes.metamessaging.handlers.IMicroKernel;
import com.openi40.mes.metamessaging.model.AbstractOI40IOTApplicationMessage;

import lombok.Data;

public class MessagesBrokerTests extends AbstractMesDBSupportTest {
	@Autowired
	BeanFactory factory;
	@Autowired DataSource dataSource;


	public MessagesBrokerTests() {

	}

	protected <Type> Type doInject(Class<Type> typeclass) {
		return factory.getBean(typeclass);
	}
	
	@Test
	public void testMQMessages() throws Exception {
		
			
			prepareDB(dataSource);
			HiveMQServer mqServer = new HiveMQServer();
			mqServer.start();
			
			LOGGER.info("HiveMQ Started!!");
			
			MqttGenericManager mqttManager=factory.getBean(MqttGenericManager.class);
			mqttManager.configureConnections();
	}

	@Data
	public static class ToBeSavedApplicationMessage extends AbstractOI40IOTApplicationMessage {
		private String contentField = null;
	}

	@Test
	public void dumpSaveMessageTest() throws IOException, SQLException {
		prepareDB(dataSource);
		IMicroKernel microKernel = doInject(IMicroKernel.class);
		for (int i = 0; i < 20; i++) {
			ToBeSavedApplicationMessage message = new ToBeSavedApplicationMessage();
			message.setAssetFrom("TEST");
			message.setAssetTo("TEST");
			message.setContentField("Hi! i'm testing the microkernel msg " + i);
			microKernel.onMessage(message, null);
		}

		MesLogicalMsg mesLogicalMsg = new MesLogicalMsg();
		Connection connection = dataSource.getConnection();
		Hashtable key = new Hashtable();
		key.put("message_type", ToBeSavedApplicationMessage.class.getName());
		Vector inserted = mesLogicalMsg.findBy(connection, key);
		assert inserted.size() == 20;

	}

}
