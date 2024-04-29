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
package com.openi40.platform.persistence.input.channel.model;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.openi40.scheduler.input.model.material.ProductInputDto;
@Entity
@Table(name = "prd")
@AttributeOverrides(value = {
@AttributeOverride(name = "code", column = @Column(name = "code")),
@AttributeOverride(name = "description", column = @Column(name = "description")),
@AttributeOverride(name = "averageMinPurchaseQty", column = @Column(name = "average_min_purch_qty")),
@AttributeOverride(name = "canBeProducedByScheduler", column = @Column(name = "can_be_prdcd_by_scheduler")),
@AttributeOverride(name = "canBePurchasedByScheduler", column = @Column(name = "can_be_purchd_by_scheduler")),
@AttributeOverride(name = "removed", column = @Column(name = "removed")),
@AttributeOverride(name = "leadTimeDays", column = @Column(name = "lead_time_days")),
@AttributeOverride(name = "modifiedTimestamp", column = @Column(name = "modified_ts")),
@AttributeOverride(name = "class1fam1", column = @Column(name = "class1fam1")),
@AttributeOverride(name = "class1fam2", column = @Column(name = "class1fam2")),
@AttributeOverride(name = "class1fam3", column = @Column(name = "class1fam3")),
@AttributeOverride(name = "class2fam1", column = @Column(name = "class2fam1")),
@AttributeOverride(name = "class2fam2", column = @Column(name = "class2fam2")),
@AttributeOverride(name = "class2fam3", column = @Column(name = "class2fam3")),
@AttributeOverride(name = "movUnity", column = @Column(name = "mov_unity")),
@AttributeOverride(name = "purchUnity", column = @Column(name = "purch_unity")),
@AttributeOverride(name = "mov2purchCoeff", column = @Column(name = "mov2purch_coeff")),
@AttributeOverride(name = "reorderQty", column = @Column(name = "reorder_qty")),
@AttributeOverride(name = "netWeight", column = @Column(name = "net_weight"))
})
public class Product extends ProductInputDto{
@Id
	@Override
	public String getCode() {
		
		return super.getCode();
	}
	@Override
	public void setCode(String code) {
		
		super.setCode(code);
	}
}
