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

import com.openi40.scheduler.client.model.ClientDto;

import lombok.Data;
@Data
public class ProductDto extends ClientDto {

	public ProductDto() {
		
	}
	protected double averageMinPurchaseQty = 0.0;
	protected boolean canBeProducedByScheduler = false;
	protected boolean canBePurchasedByScheduler = false;
	protected int leadTimeDays = 0;
	protected String class1fam1=null;
	protected String class1fam2=null;
	protected String class1fam3=null;
	protected String class2fam1=null;
	protected String class2fam2=null;
	protected String class2fam3=null;
	protected String movUnity=null;
	protected String purchUnity=null;
	protected Double mov2purchCoeff=null;
	protected Double reorderQty=null;
	protected Double netWeight=null;
}
