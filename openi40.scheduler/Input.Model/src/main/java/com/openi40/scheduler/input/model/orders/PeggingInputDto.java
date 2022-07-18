package com.openi40.scheduler.input.model.orders;

import javax.persistence.MappedSuperclass;

import com.openi40.scheduler.common.datamodel.ObjectReferenceConstraint;
import com.openi40.scheduler.input.model.ApsInputData;
import com.openi40.scheduler.input.model.InputDto;
/**
 * 
 * This code is part of the OpenI40 open source advanced production scheduler
 * platform suite, have look to its licencing options.
 * Web site: http://openi40.org/  
 * Github: https://github.com/openi40/OpenI40Platform
 * We hope you enjoy implementing new amazing projects with it.
 * @author architectures@openi40.org
 *
 */
@MappedSuperclass
public class PeggingInputDto extends InputDto {
	private String consumerWorkOrderCode = null;
	private String producerWorkOrderCode = null;
	private String consumerTaskCode = null;
	private String producerTaskCode = null;
	double peggingQty=0;
	@ObjectReferenceConstraint(containerType = ApsInputData.class, referencedType = WorkOrderInputDto.class, nullable = false)
	public String getConsumerWorkOrderCode() {
		return consumerWorkOrderCode;
	}
	public void setConsumerWorkOrderCode(String consumerWorkOrderCode) {
		this.consumerWorkOrderCode = consumerWorkOrderCode;
	}
	@ObjectReferenceConstraint(containerType = ApsInputData.class, referencedType = WorkOrderInputDto.class, nullable = false)
	public String getProducerWorkOrderCode() {
		return producerWorkOrderCode;
	}
	public void setProducerWorkOrderCode(String producerWorkOrderCode) {
		this.producerWorkOrderCode = producerWorkOrderCode;
	}
	public double getPeggingQty() {
		return peggingQty;
	}
	public void setPeggingQty(double peggingQty) {
		this.peggingQty = peggingQty;
	}
	public String getConsumerTaskCode() {
		return consumerTaskCode;
	}
	public void setConsumerTaskCode(String consumerTaskCode) {
		this.consumerTaskCode = consumerTaskCode;
	}
	public String getProducerTaskCode() {
		return producerTaskCode;
	}
	public void setProducerTaskCode(String producerTaskCode) {
		this.producerTaskCode = producerTaskCode;
	}
}