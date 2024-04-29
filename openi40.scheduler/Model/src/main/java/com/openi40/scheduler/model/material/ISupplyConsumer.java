package com.openi40.scheduler.model.material;

import java.util.Date;
import java.util.List;

import com.openi40.scheduler.common.aps.IApsObject;
import com.openi40.scheduler.model.rules.MaterialRule;
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
public interface ISupplyConsumer extends IApsObject {

	List<SupplyReservation> getOwnedReservations();

	void addMaterialReservation(SupplyReservation reservation);

	void removeMaterialReservation(SupplyReservation reservation);

	MaterialRule.ConsumptionMode getConsumptionMode();

	void setConsumptionMode(MaterialRule.ConsumptionMode value);

	Product getProduct();

	void setProduct(Product value);

	double getRequiredQty();

	void setRequiredQty(double value);

	double getSatisfiedQty();

	double getUnsatisfiedQty();

	double getLotQty();

	Date getRequiredDateTime();

	void setRequiredDateTime(Date value);

	Date getSatisfiedDateTime();

	void setSatisfiedDateTime(Date value);

	String getWarehouseCode();

	void setWarehouseCode(String c);

}