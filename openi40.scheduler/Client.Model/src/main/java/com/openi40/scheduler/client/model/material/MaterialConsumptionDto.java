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
package com.openi40.scheduler.client.model.material;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.openi40.scheduler.client.model.ClientDto;

import lombok.Data;

@Data
public class MaterialConsumptionDto extends ClientDto {
	private double lotQty = 0;
	private double unsatisfiedQty=0.0;
	private Date satisfiedDateTime = null;
	private Date requiredDateTime = null;
	private ProductDto product=null;
	private double requiredQty = 0.0;
	private String warehouseCode=null;
	private List<SupplyReservationDto> ownedReservations = new ArrayList<SupplyReservationDto>();
	public long getRequiredDateTimeUTC() {
		return this.requiredDateTime!=null?this.requiredDateTime.getTime():0l;
	}
}
