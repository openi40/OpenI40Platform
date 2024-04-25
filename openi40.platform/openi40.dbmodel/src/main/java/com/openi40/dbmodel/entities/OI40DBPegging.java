package com.openi40.dbmodel.entities;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
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
@Entity
@Table(name = "pegging")
@AttributeOverrides(value = {
@AttributeOverride(name = "code", column = @Column(name = "code")),
@AttributeOverride(name = "description", column = @Column(name = "description")),
@AttributeOverride(name = "consumerWorkOrderCode", column = @Column(name = "cons_worder_code")),
@AttributeOverride(name = "consumerTaskCode", column = @Column(name = "cons_task_code")),
@AttributeOverride(name = "removed", column = @Column(name = "removed")),
@AttributeOverride(name = "modifiedTimestamp", column = @Column(name = "modified_ts")),
@AttributeOverride(name = "peggingQty", column = @Column(name = "pegging_qty")),
@AttributeOverride(name = "producerWorkOrderCode", column = @Column(name = "prdcr_worder_code")),
@AttributeOverride(name = "producerTaskCode", column = @Column(name = "prdcr_task_code")),
@AttributeOverride(name = "integrationTs", column = @Column(name = "integration_ts"))
})

public class OI40DBPegging extends OI40DBBaseEntity{
	private String consumerWorkOrderCode = null;
	private String producerWorkOrderCode = null;
	private String consumerTaskCode = null;
	private String producerTaskCode = null;
	Double peggingQty=null;
	public String getConsumerWorkOrderCode() {
		return consumerWorkOrderCode;
	}
	public void setConsumerWorkOrderCode(String consumerWorkOrderCode) {
		this.consumerWorkOrderCode = consumerWorkOrderCode;
	}
	public String getProducerWorkOrderCode() {
		return producerWorkOrderCode;
	}
	public void setProducerWorkOrderCode(String producerWorkOrderCode) {
		this.producerWorkOrderCode = producerWorkOrderCode;
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
	public Double getPeggingQty() {
		return peggingQty;
	}
	public void setPeggingQty(Double peggingQty) {
		this.peggingQty = peggingQty;
	}
}
