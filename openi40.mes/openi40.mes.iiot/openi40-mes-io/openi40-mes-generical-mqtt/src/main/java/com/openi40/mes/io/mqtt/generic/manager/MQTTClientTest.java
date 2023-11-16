package com.openi40.mes.io.mqtt.generic.manager;

import org.eclipse.paho.mqttv5.client.IMqttToken;
import org.eclipse.paho.mqttv5.client.MqttCallback;
import org.eclipse.paho.mqttv5.client.MqttClient;
import org.eclipse.paho.mqttv5.client.MqttDisconnectResponse;
import org.eclipse.paho.mqttv5.common.MqttException;
import org.eclipse.paho.mqttv5.common.MqttMessage;
import org.eclipse.paho.mqttv5.common.packet.MqttProperties;

public class MQTTClientTest {

	public static void main(String[] args) throws MqttException {
		MqttClient client = new MqttClient("tcp://127.0.0.1:1883", "test-client");
		client.connect();

		
		client.setCallback(new MqttCallback() {

			@Override
			public void mqttErrorOccurred(MqttException exception) {
				// TODO Auto-generated method stub

			}

			@Override
			public void messageArrived(String topic, MqttMessage message) throws Exception {
				// TODO Auto-generated method stub

			}

			@Override
			public void disconnected(MqttDisconnectResponse disconnectResponse) {
				// TODO Auto-generated method stub

			}

			@Override
			public void deliveryComplete(IMqttToken token) {
				// TODO Auto-generated method stub

			}

			@Override
			public void connectComplete(boolean reconnect, String serverURI) {
				// TODO Auto-generated method stub

			}

			@Override
			public void authPacketArrived(int reasonCode, MqttProperties properties) {
				// TODO Auto-generated method stub

			}
		});
		for (int index = 0; index < 1000; index++) {
			MqttMessage message = new MqttMessage(("{temperature:" + index + "}").getBytes());
			client.publish("zlansub", message);
		}
		client.close();
		System.exit(0);

	}

}
