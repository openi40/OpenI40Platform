package com.openi40.mes.io.mqtt.generic.input;

import org.eclipse.paho.mqttv5.common.MqttMessage;

public interface IMqttArrivedSuccessfully {
	public void messageArrived(String topic, MqttMessage message) throws Exception;
}
