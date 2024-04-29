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
package com.openi40.scheduler.client.model.material;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.openi40.scheduler.client.model.ClientDto;
import com.openi40.scheduler.common.utils.DateUtil;
import com.openi40.scheduler.common.utils.DateUtil.Week;


public class AbstractSupplyDto extends ClientDto {

	public AbstractSupplyDto() {

	}

	double qtyTotal = 0, qtyAvailable = 0, qtyReserved = 0;
	Date availabilityDateTime = null;
	String warehouseCode = null;
	String productCode = null;
	List<SupplyReservationDto> reservations = new ArrayList<>();

	public Integer getAvailabilityWeek() {
		Week week = null;
		if (availabilityDateTime != null) {
			week = DateUtil.getWeek(availabilityDateTime);
		}

		return week == null ? null : week.getPeriod();
	}

	public double getQtyTotal() {
		return qtyTotal;
	}

	public void setQtyTotal(double qtyTotal) {
		this.qtyTotal = qtyTotal;
	}

	public double getQtyAvailable() {
		return qtyAvailable;
	}

	public void setQtyAvailable(double qtyAvailable) {
		this.qtyAvailable = qtyAvailable;
	}

	public double getQtyReserved() {
		return qtyReserved;
	}

	public void setQtyReserved(double qtyReserved) {
		this.qtyReserved = qtyReserved;
	}

	public Date getAvailabilityDateTime() {
		return availabilityDateTime;
	}

	public void setAvailabilityDateTime(Date availabilityDateTime) {
		this.availabilityDateTime = availabilityDateTime;
	}

	public String getWarehouseCode() {
		return warehouseCode;
	}

	public void setWarehouseCode(String warehouseCode) {
		this.warehouseCode = warehouseCode;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public List<SupplyReservationDto> getReservations() {
		return reservations;
	}

	public void setReservations(List<SupplyReservationDto> reservations) {
		this.reservations = reservations;
	}

}
