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
	public double getAverageMinPurchaseQty() {
		return averageMinPurchaseQty;
	}
	public void setAverageMinPurchaseQty(double averageMinPurchaseQty) {
		this.averageMinPurchaseQty = averageMinPurchaseQty;
	}
	public boolean isCanBeProducedByScheduler() {
		return canBeProducedByScheduler;
	}
	public void setCanBeProducedByScheduler(boolean canBeProducedByScheduler) {
		this.canBeProducedByScheduler = canBeProducedByScheduler;
	}
	public boolean isCanBePurchasedByScheduler() {
		return canBePurchasedByScheduler;
	}
	public void setCanBePurchasedByScheduler(boolean canBePurchasedByScheduler) {
		this.canBePurchasedByScheduler = canBePurchasedByScheduler;
	}
	public int getLeadTimeDays() {
		return leadTimeDays;
	}
	public void setLeadTimeDays(int leadTimeDays) {
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
