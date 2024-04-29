package com.openi40.dbmodel.entities;

import java.io.Serializable;

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
 * @author Paolo Zavalloni  architectures@openi40.org
 *
 */
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
@AttributeOverride(name = "netWeight", column = @Column(name = "net_weight")),
@AttributeOverride(name = "integrationTs", column = @Column(name = "integration_ts"))
})

public class OI40DBProduct extends OI40DBBaseEntity implements Serializable{
	protected Double averageMinPurchaseQty = null;
	protected Boolean canBeProducedByScheduler = null;
	protected Boolean canBePurchasedByScheduler = null;
	protected Integer leadTimeDays = null;
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
	public Double getAverageMinPurchaseQty() {
		return averageMinPurchaseQty;
	}
	public void setAverageMinPurchaseQty(Double averageMinPurchaseQty) {
		this.averageMinPurchaseQty = averageMinPurchaseQty;
	}
	public Boolean getCanBeProducedByScheduler() {
		return canBeProducedByScheduler;
	}
	public void setCanBeProducedByScheduler(Boolean canBeProducedByScheduler) {
		this.canBeProducedByScheduler = canBeProducedByScheduler;
	}
	public Boolean getCanBePurchasedByScheduler() {
		return canBePurchasedByScheduler;
	}
	public void setCanBePurchasedByScheduler(Boolean canBePurchasedByScheduler) {
		this.canBePurchasedByScheduler = canBePurchasedByScheduler;
	}
	public Integer getLeadTimeDays() {
		return leadTimeDays;
	}
	public void setLeadTimeDays(Integer leadTimeDays) {
		this.leadTimeDays = leadTimeDays;
	}
	public String getClass1fam1() {
		return class1fam1;
	}
	public void setClass1fam1(String class1fam1) {
		this.class1fam1 = class1fam1;
	}
	public String getClass1fam2() {
		return class1fam2;
	}
	public void setClass1fam2(String class1fam2) {
		this.class1fam2 = class1fam2;
	}
	public String getClass1fam3() {
		return class1fam3;
	}
	public void setClass1fam3(String class1fam3) {
		this.class1fam3 = class1fam3;
	}
	public String getClass2fam1() {
		return class2fam1;
	}
	public void setClass2fam1(String class2fam1) {
		this.class2fam1 = class2fam1;
	}
	public String getClass2fam2() {
		return class2fam2;
	}
	public void setClass2fam2(String class2fam2) {
		this.class2fam2 = class2fam2;
	}
	public String getClass2fam3() {
		return class2fam3;
	}
	public void setClass2fam3(String class2fam3) {
		this.class2fam3 = class2fam3;
	}
	public String getMovUnity() {
		return movUnity;
	}
	public void setMovUnity(String movUnity) {
		this.movUnity = movUnity;
	}
	public String getPurchUnity() {
		return purchUnity;
	}
	public void setPurchUnity(String purchUnity) {
		this.purchUnity = purchUnity;
	}
	public Double getMov2purchCoeff() {
		return mov2purchCoeff;
	}
	public void setMov2purchCoeff(Double mov2purchCoeff) {
		this.mov2purchCoeff = mov2purchCoeff;
	}
	public Double getReorderQty() {
		return reorderQty;
	}
	public void setReorderQty(Double reorderQty) {
		this.reorderQty = reorderQty;
	}
	public Double getNetWeight() {
		return netWeight;
	}
	public void setNetWeight(Double netWeight) {
		this.netWeight = netWeight;
	}
}
