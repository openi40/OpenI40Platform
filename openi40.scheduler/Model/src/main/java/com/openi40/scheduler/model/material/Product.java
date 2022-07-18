package com.openi40.scheduler.model.material;

import com.openi40.scheduler.model.AbstractApsObject;
import com.openi40.scheduler.model.aps.ApsData;

import lombok.Data;
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
@Data
public class Product extends AbstractApsObject {
	protected String class1fam1 = null;
	protected String class1fam2 = null;
	protected String class1fam3 = null;
	protected String class2fam1 = null;
	protected String class2fam2 = null;
	protected String class2fam3 = null;
	protected String movUnity = null;
	protected String purchUnity = null;
	protected Double mov2purchCoeff = null;
	protected Double reorderQty = null;
	protected Double netWeight = null;
	private boolean canBePurchasedByScheduler = true;
	private boolean canBeProducedByScheduler = true;
	private double averageMinPurchaseQty = 0;
	private int leadTimeDays = 0;

	public Product(ApsData context) {
		super(context);
	}

	@Override
	public boolean equals(Object o) {
		boolean tempVar = o instanceof Product;
		Product i = tempVar ? (Product) o : null;
		if (tempVar) {
			return equals(i);
		} else {
			return super.equals(o);
		}
	}

	public boolean equals(Product product) {
		return this.getCode() != null && product != null && product.getCode() != null
				&& this.getCode().equals(product.getCode());
	}

}