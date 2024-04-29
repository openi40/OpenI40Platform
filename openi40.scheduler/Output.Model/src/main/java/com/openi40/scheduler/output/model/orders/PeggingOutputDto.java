package com.openi40.scheduler.output.model.orders;

import com.openi40.scheduler.output.model.OutputDto;
/**
 * 
 * This code is part of the OpenI40 open source advanced production scheduler
 * platform suite, have look to its licencing options.
 * Web site: http://openi40.org/  
 * Github: https://github.com/openi40/OpenI40Platform
 * We hope you enjoy implementing new amazing projects with it.
 * @author Paolo Zavalloni  architectures@openi40.org
 *
 */

public class PeggingOutputDto extends OutputDto {
	private String consumerWorkOrderCode = null;
	private String consumerTaskCode = null;
	private String producerWorkOrderCode = null;
	private String producerTaskCode = null;
	double peggingQty=0;
	public String getConsumerWorkOrderCode() {
		return consumerWorkOrderCode;
	}
	public void setConsumerWorkOrderCode(String consumerWorkOrderCode) {
		this.consumerWorkOrderCode = consumerWorkOrderCode;
	}
	public String getConsumerTaskCode() {
		return consumerTaskCode;
	}
	public void setConsumerTaskCode(String consumerTaskCode) {
		this.consumerTaskCode = consumerTaskCode;
	}
	public String getProducerWorkOrderCode() {
		return producerWorkOrderCode;
	}
	public void setProducerWorkOrderCode(String producerWorkOrderCode) {
		this.producerWorkOrderCode = producerWorkOrderCode;
	}
	public String getProducerTaskCode() {
		return producerTaskCode;
	}
	public void setProducerTaskCode(String producerTaskCode) {
		this.producerTaskCode = producerTaskCode;
	}
	public double getPeggingQty() {
		return peggingQty;
	}
	public void setPeggingQty(double peggingQty) {
		this.peggingQty = peggingQty;
	}
	
}