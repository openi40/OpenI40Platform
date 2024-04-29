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

import com.openi40.platform.persistence.input.channel.StreamLoadRelated;
import com.openi40.platform.persistence.input.channel.StreamLoadRelated.RelationType;
import com.openi40.scheduler.input.model.cycles.BomItemModelInputDto;
import com.openi40.scheduler.input.model.cycles.OperationModelInputDto;

@Entity
@Table(name = "op_model")
@AttributeOverrides(value = { @AttributeOverride(name = "code", column = @Column(name = "code")),
		@AttributeOverride(name = "description", column = @Column(name = "description")),
		@AttributeOverride(name = "cycleCode", column = @Column(name = "cycle_code")),
		@AttributeOverride(name = "removed", column = @Column(name = "removed")),
		@AttributeOverride(name = "modifiedTimestamp", column = @Column(name = "modified_ts")),
		@AttributeOverride(name = "plantCode", column = @Column(name = "plant_code")),
		@AttributeOverride(name = "sequenceCode", column = @Column(name = "sequence_code")),
		@AttributeOverride(name = "consumingBatchQty", column = @Column(name = "cons_batch_qty")),
		@AttributeOverride(name = "producingBatchQty", column = @Column(name = "prd_batch_qty"))

		, @AttributeOverride(name = "consumingBatchTransferType", column = @Column(name = "cons_transfer_type")),

		@AttributeOverride(name = "producingBatchTransferType", column = @Column(name = "prd_transfer_type"))
		

})
public class OperationModel extends OperationModelInputDto {

	@Id
	@Override
	public String getCode() {

		return super.getCode();
	}

	@Override
	public void setCode(String code) {

		super.setCode(code);
	}

	@StreamLoadRelated(overriddenType = BomItemModelInputDto.class, loadType = BomItemModel.class, relationType = RelationType.ONE2MANY, joinProperty = "operationCode")
	@Override
	public void setBomItems(java.util.List<com.openi40.scheduler.input.model.cycles.BomItemModelInputDto> p) {
		super.setBomItems(p);
	}

	@StreamLoadRelated(overriddenType = com.openi40.scheduler.input.model.cycles.CoProductItemInputDto.class, loadType = CoProductItem.class, relationType = RelationType.ONE2MANY, joinProperty = "operationCode")
	@Override
	public void setCoProducts(java.util.List<com.openi40.scheduler.input.model.cycles.CoProductItemInputDto> p) {
		super.setCoProducts(p);
	}

	@StreamLoadRelated(overriddenType = com.openi40.scheduler.input.model.cycles.OperationEquipmentSpecificationInputDto.class, loadType = OperationEquipmentSpecification.class, relationType = RelationType.ONE2MANY, joinProperty = "operationCode")
	@Override
	public void setEquipmentSpecifications(
			java.util.List<com.openi40.scheduler.input.model.cycles.OperationEquipmentSpecificationInputDto> p) {
		super.setEquipmentSpecifications(p);
	}

	@StreamLoadRelated(overriddenType = com.openi40.scheduler.input.model.cycles.MachineEquipmentSpecificationInputDto.class, loadType = MachineEquipmentSpecification.class, relationType = RelationType.ONE2MANY, joinProperty = "operationCode")
	@Override
	public void setMachinesEquipmentSpecifications(
			java.util.List<com.openi40.scheduler.input.model.cycles.MachineEquipmentSpecificationInputDto> p) {
		super.setMachinesEquipmentSpecifications(p);
	}

}
