package com.openi40.scheduler.model.rules;

import java.util.ArrayList;
import java.util.List;

import com.openi40.scheduler.model.aps.ApsData;
import com.openi40.scheduler.model.material.ISupply;
import com.openi40.scheduler.model.material.ISupplyConsumer;
import com.openi40.scheduler.model.material.SupplyReservation;
import com.openi40.scheduler.model.tasks.Task;
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

public class MaterialRule extends Rule {
	public enum ConsumptionMode {
		CONTINUOUS, LOT_CONSUMPTION;

	}

	public enum CoveringConstraintType {
		PARTIAL_COVERING_MANDATORY, TOTAL_COVERING_MANDATORY;


	}

	public enum MaterialContraintType {
		PRODUCTION_LINK, SUPPLY_REQUIREMENT;


	}

	public static enum SupplyResolutionStrategy {
		CREATE_ODL_IF_REQUIRED("create work orders on missing goods","create work order for parts that can be produced on same plant and company",true), 
		CREATE_PURCHASE_ORDER_IF_REQUIRED("create purchase orders on missing goods","create purchase orders for purchaseable goods",true), 
		FOLLOW_PRODUCTION_LINK("use work order peggings and relation between task","use work order peggings and relation between tasks to supply semi-finished goods",false), 
		USE_PURCHASE_ORDER_IF_REQUIRED("use existing purchase orders to supply raw material/semi-finished goods","use existing purchase orders to supply raw material/semi-finished goods",true), 
		USE_STOCK_IF_AVAILABLE("use actual phisically stocked goods as production raw materials/semi-finished parts","use actual phisically stocked goods as production raw materials/semi-finished parts",true);
		SupplyResolutionStrategy(String desc,String longDesc,boolean userCanDisable){
			this.description=desc;
			this.longDescription=longDesc;
			this.userCanDisable=userCanDisable;
		}
		boolean userCanDisable=true;
		public boolean isUserCanDisable() {
			return userCanDisable;
		}
		String description=null;
		String longDescription=null;
		public String getCode() {
			return this.toString();
		}
		public String getDescription() {
			return description;
		}
		public String getLongDescription() {
			return longDescription;
		}
	}
	
	private List<SupplyReservation> constraintGeneratedReservations = new ArrayList<SupplyReservation>();

	private ISupplyConsumer consumer;

	private ConsumptionMode consumerMode = ConsumptionMode.values()[0];
	private CoveringConstraintType coveringType = CoveringConstraintType.TOTAL_COVERING_MANDATORY;
	private MaterialContraintType materialContraint = MaterialContraintType.values()[0];
	private List<ISupply> materialSupplies = new ArrayList<ISupply>();
	private List<SupplyResolutionStrategy> resolutionStrategies = new ArrayList<SupplyResolutionStrategy>();

	public MaterialRule(ApsData context) {
		super(context);
	}

	public MaterialRule(Task target, ConstraintOrigin origin, ConstraintPriority priority, ISupplyConsumer consumer, ISupply linkedSupply, CoveringConstraintType covering)

	{
		super(target, origin, priority);
		if (linkedSupply != null) {
			getMaterialSupplies().add(linkedSupply);
		}

		setConsumer(consumer);
		setConsumerMode(getConsumer().getConsumptionMode());
		setMaterialContraint(linkedSupply == null ? MaterialContraintType.SUPPLY_REQUIREMENT : MaterialContraintType.PRODUCTION_LINK);
		setCoveringType(covering);
	}

	public List<SupplyReservation> getConstraintGeneratedReservations() {
		return constraintGeneratedReservations;
	}

	public void setConstraintGeneratedReservations(List<SupplyReservation> constraintGeneratedReservations) {
		constraintGeneratedReservations = constraintGeneratedReservations;
	}

	public ISupplyConsumer getConsumer() {
		return consumer;
	}

	public void setConsumer(ISupplyConsumer consumer) {
		consumer = consumer;
	}

	public ConsumptionMode getConsumerMode() {
		return consumerMode;
	}

	public void setConsumerMode(ConsumptionMode consumerMode) {
		consumerMode = consumerMode;
	}

	public CoveringConstraintType getCoveringType() {
		return coveringType;
	}

	public void setCoveringType(CoveringConstraintType coveringType) {
		coveringType = coveringType;
	}

	public MaterialContraintType getMaterialContraint() {
		return materialContraint;
	}

	public void setMaterialContraint(MaterialContraintType materialContraint) {
		materialContraint = materialContraint;
	}

	public List<ISupply> getMaterialSupplies() {
		return materialSupplies;
	}

	public void setMaterialSupplies(List<ISupply> materialSupplies) {
		materialSupplies = materialSupplies;
	}

	public List<SupplyResolutionStrategy> getResolutionStrategies() {
		return resolutionStrategies;
	}

	public void setResolutionStrategies(List<SupplyResolutionStrategy> resolutionStrategies) {
		resolutionStrategies = resolutionStrategies;
	}

}