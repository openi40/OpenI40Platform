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
package com.openi40.scheduler.client.model.orders;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.openi40.scheduler.client.model.ClientDto;
import com.openi40.scheduler.client.model.material.ProductDto;
import com.openi40.scheduler.client.model.tasks.TaskDto;
import com.openi40.scheduler.client.model.time.TimeSegmentDto;


public class WorkOrderDto extends ClientDto {
	private List<TaskDto> tasks = new ArrayList<>();
	private Date deliveryDate = null;
	private Date askedDeliveryDateTime = null;
	private int Idx = 0;
	private String salesOrderLineCode=null;
	private boolean rootSalesOrderWorkOrder=false;
	private TimeSegmentDto execution = new TimeSegmentDto();
	private double TotalQty = 0.0;
	protected String color = null;
	
	public static class PeggingDto extends ClientDto{
		private ClientDto consumerWorkOrder = null;
		private ClientDto producerWorkOrder = null;
		public ClientDto getConsumerWorkOrder() {
			return consumerWorkOrder;
		}
		public void setConsumerWorkOrder(ClientDto consumerWorkOrder) {
			this.consumerWorkOrder = consumerWorkOrder;
		}
		public ClientDto getProducerWorkOrder() {
			return producerWorkOrder;
		}
		public void setProducerWorkOrder(ClientDto producerWorkOrder) {
			this.producerWorkOrder = producerWorkOrder;
		}
	}
	
	public static class ProducedPartDto extends ClientDto{
		ProductDto suppliedItem=null;
		double qty=0.0;
		public ProductDto getSuppliedItem() {
			return suppliedItem;
		}
		public void setSuppliedItem(ProductDto suppliedItem) {
			this.suppliedItem = suppliedItem;
		}
		public double getQty() {
			return qty;
		}
		public void setQty(double qty) {
			this.qty = qty;
		}
	}
	private List<PeggingDto> peggings=new ArrayList<PeggingDto>();
	private ProducedPartDto producedPart=null;
	public List<TaskDto> getTasks() {
		return tasks;
	}
	public void setTasks(List<TaskDto> tasks) {
		this.tasks = tasks;
	}
	public Date getDeliveryDate() {
		return deliveryDate;
	}
	public void setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
	}
	public Date getAskedDeliveryDateTime() {
		return askedDeliveryDateTime;
	}
	public void setAskedDeliveryDateTime(Date askedDeliveryDateTime) {
		this.askedDeliveryDateTime = askedDeliveryDateTime;
	}
	public int getIdx() {
		return Idx;
	}
	public void setIdx(int idx) {
		Idx = idx;
	}
	public String getSalesOrderLineCode() {
		return salesOrderLineCode;
	}
	public void setSalesOrderLineCode(String salesOrderLineCode) {
		this.salesOrderLineCode = salesOrderLineCode;
	}
	public boolean isRootSalesOrderWorkOrder() {
		return rootSalesOrderWorkOrder;
	}
	public void setRootSalesOrderWorkOrder(boolean rootSalesOrderWorkOrder) {
		this.rootSalesOrderWorkOrder = rootSalesOrderWorkOrder;
	}
	public TimeSegmentDto getExecution() {
		return execution;
	}
	public void setExecution(TimeSegmentDto execution) {
		this.execution = execution;
	}
	public double getTotalQty() {
		return TotalQty;
	}
	public void setTotalQty(double totalQty) {
		TotalQty = totalQty;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public List<PeggingDto> getPeggings() {
		return peggings;
	}
	public void setPeggings(List<PeggingDto> peggings) {
		this.peggings = peggings;
	}
	public ProducedPartDto getProducedPart() {
		return producedPart;
	}
	public void setProducedPart(ProducedPartDto producedPart) {
		this.producedPart = producedPart;
	}
}
