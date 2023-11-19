package com.openi40.mes.iiot.tests;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Hashtable;
import java.util.List;
import java.util.Properties;
import java.util.Vector;

import javax.sql.DataSource;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.testcontainers.hivemq.HiveMQContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import com.openi40.mes.datamodel.persister.MesLogicalMsg;
import com.openi40.mes.io.mqtt.generic.manager.MqttGenericManager;
import com.openi40.mes.metamessaging.handlers.IMicroKernel;
import com.openi40.mes.metamessaging.model.AbstractOI40IOTApplicationMessage;

import lombok.Data;

@Testcontainers
public class MessagesBrokerTests extends AbstractMesDBSupportTest {
	@Autowired
	BeanFactory factory;
	@Autowired
	DataSource dataSource;

	@Container
	HiveMQContainer container = new HiveMQContainer(new DockerImageName("hivemq/hivemq-ce:latest"));

	public MessagesBrokerTests() {

	}

	protected <Type> Type doInject(Class<Type> typeclass) {
		return factory.getBean(typeclass);
	}
	
	@Test
	public void testMQMessages() throws Exception {

		prepareDB(dataSource);
		container.setExposedPorts(List.of(1388));
		//container.setExtraHosts(List.of("127.0.0.1:1388"));
		container.start();
		int mappedPort = container.getMqttPort();
		LOGGER.info("HiveMQ Started!! on port:"+mappedPort);
		MqttGenericManager mqttManager = factory.getBean(MqttGenericManager.class);
		mqttManager.configureConnections();
		assertTrue(mqttManager.isAllChannelsStarted(), "expected=> mqttManager.isAllChannelsStarted() == true but channels have not been started") ;
		assertTrue(mqttManager.getNMqttClientsStarted()>0, "expected=> mqttManager.getNMqttClientsStarted() > 0  but no mqtt client have been successfully started") ;
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
