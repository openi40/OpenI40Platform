package com.openi40.mes.iiot.tests;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Properties;
import java.util.UUID;
import java.util.Vector;
import java.util.function.Consumer;
import java.util.stream.Stream;

import javax.sql.DataSource;

import org.eclipse.paho.mqttv5.client.MqttClient;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.testcontainers.containers.wait.strategy.LogMessageWaitStrategy;
import org.testcontainers.hivemq.HiveMQContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import com.openi40.dbmodel.easydbbeans.BaseDBBeanDao;
import com.openi40.mes.datamodel.persister.MesLogicalMsg;
import com.openi40.mes.io.mqtt.generic.config.GenericMQTTChannelConfig;
import com.openi40.mes.io.mqtt.generic.config.IntegratedChannelsConfig;
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

	@Autowired
	GenericMQTTChannelConfig mqttConfig;

	@BeforeAll
	public void initialize() throws Exception {
		
	}
	@AfterAll
	public void stop() throws Exception {
		container.stop();
	}
	@Test
	public void testMQTTMessages() throws Exception {
		prepareDB(dataSource);
		super.runScript(dataSource, "populate-localtests.sql", false, true);
		final LogMessageWaitStrategy waitStrategy = new LogMessageWaitStrategy();
		waitStrategy.withRegEx(".*Started HiveMQ in.*");
		container.start();
		container.waitingFor(waitStrategy);
		String ip = container.getContainerIpAddress();

		int mappedPort = container.getMqttPort();
		LOGGER.info("HiveMQ Started!! on port:" + ip + ":" + mappedPort);
		for (IntegratedChannelsConfig cfg : mqttConfig.getIntegrations()) {
			cfg.getBrokerConfig().setBrokerUrl("tcp://" + ip + ":" + mappedPort);
		}
		MqttGenericManager mqttManager = factory.getBean(MqttGenericManager.class);
		mqttManager.configureConnections(mqttConfig);
		assertTrue(mqttManager.isAllChannelsStarted(),
				"expected=> mqttManager.isAllChannelsStarted() == true but channels have not been started");
		assertTrue(mqttManager.getNMqttClientsStarted() > 0,
				"expected=> mqttManager.getNMqttClientsStarted() > 0  but no mqtt client have been successfully started");
		MqttClient mqttClient = new MqttClient("tcp://" + ip + ":" + mappedPort,
				"testClient-" + UUID.randomUUID().toString());
		mqttClient.connect();
		List<String> sent = new ArrayList<String>();
		for (int i = 0; i < 100; i++) {
			String msg = "{id=" + i + ",msgId='" + UUID.randomUUID().toString() + "'}";
			mqttClient.publish("mqtt_test_receive_topic", msg.getBytes(), 1, false);
			sent.add(msg);
		}
		try

		{
			Thread.sleep(20000);
			mqttClient.close(true);
		} catch (Throwable th) {
		}
		Connection connection = dataSource.getConnection();
		final ArrayList<MesLogicalMsg> collector = new ArrayList<MesLogicalMsg>();
		Thread.sleep(40000);
		BaseDBBeanDao<MesLogicalMsg> dao = BaseDBBeanDao.create(MesLogicalMsg.class);
		Stream<MesLogicalMsg> stream = dao.streamByWhere(connection,
				"message_type='" + MqttToSaveableMessageRelayReceiver.RelayMQTTContentMessage.class.getName() + "'");
		final ArrayList<MesLogicalMsg> tmpCollector = new ArrayList<MesLogicalMsg>();
		Consumer<MesLogicalMsg> consumer = new Consumer<MesLogicalMsg>() {
			public int counter = 0;

			@Override
			public void accept(MesLogicalMsg t) {
				collector.add(t);
				tmpCollector.add(t);
			}
		};
		stream.forEach(consumer);

		LOGGER.info("Read globally=>" + collector.size() + " but sent " + sent.size() + " relayed mqtt messages");
		connection.close();
		assertTrue(collector.size() == sent.size(), "expected 100 records relayed from mqtt to saveable message type");

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
