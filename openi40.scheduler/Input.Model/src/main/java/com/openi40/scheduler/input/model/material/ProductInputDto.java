package com.openi40.scheduler.input.model.material;

import javax.persistence.MappedSuperclass;

import com.openi40.scheduler.input.model.InputDto;

/**
 * 
 * This code is part of the OpenI40 open source advanced production scheduler
 * platform suite, have look to its licencing options. Web site:
 * http://openi40.org/ Github: https://github.com/openi40/OpenI40Platform We
 * hope you enjoy implementing new amazing projects with it.
 * 
 * @author architectures@openi40.org
 *
 */

@MappedSuperclass
public class ProductInputDto extends InputDto {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6990194557295949848L;
	protected Double averageMinPurchaseQty = 0.0;
	protected Boolean canBeProducedByScheduler = false;
	protected Boolean canBePurchasedByScheduler = false;
	protected Integer leadTimeDays = 0;
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

	public Double getAverageMinPurchaseQty() {
		return averageMinPurchaseQty!=null?averageMinPurchaseQty:0;
	}

	public void setAverageMinPurchaseQty(Double averageMinPurchaseQty) {
		this.averageMinPurchaseQty = averageMinPurchaseQty;
	}

	public Boolean getCanBeProducedByScheduler() {
		return canBeProducedByScheduler != null ? canBeProducedByScheduler : false;
	}

	public void setCanBeProducedByScheduler(Boolean canBeProducedByScheduler) {
		this.canBeProducedByScheduler = canBeProducedByScheduler;
	}

	public Boolean getCanBePurchasedByScheduler() {
		return canBePurchasedByScheduler != null ? canBePurchasedByScheduler : false;
	}

	public void setCanBePurchasedByScheduler(Boolean canBePurchasedByScheduler) {
		this.canBePurchasedByScheduler = canBePurchasedByScheduler;
	}

	public Integer getLeadTimeDays() {
		return leadTimeDays != null ? leadTimeDays : 0;
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
		return mov2purchCoeff != null ? mov2purchCoeff : 0.0;
	}

	public void setMov2purchCoeff(Double mov2purchCoeff) {
		this.mov2purchCoeff = mov2purchCoeff;
	}

	public Double getReorderQty() {
		return reorderQty != null ? reorderQty : 0;
	}

	public void setReorderQty(Double reorderQty) {
		this.reorderQty = reorderQty;
	}

	public Double getNetWeight() {
		return netWeight != null ? netWeight : 0;
	}

	public void setNetWeight(Double netWeight) {
		this.netWeight = netWeight;
	}

}
