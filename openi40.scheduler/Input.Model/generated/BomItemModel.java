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
import javax.persistence.UniqueConstraint;
import com.openi40.platform.persistence.input.channel.StreamLoadRelated;
import com.openi40.platform.persistence.input.channel.StreamLoadRelated.RelationType;
import com.openi40.scheduler.input.model.cycles.BomItemModelInputDto;
@Entity
@Table(name = "bom_item_model")
@AttributeOverrides(value = {
@AttributeOverride(name = "code", column = @Column(name = "code")),
@AttributeOverride(name = "description", column = @Column(name = "description")),
@AttributeOverride(name = "deleted", column = @Column(name = "deleted")),
@AttributeOverride(name = "modifiedTimestamp", column = @Column(name = "modified_ts")),
@AttributeOverride(name = "operationCode", column = @Column(name = "op_code")),
@AttributeOverride(name = "requiredProductCode", column = @Column(name = "required_prd_code")),
@AttributeOverride(name = "useCoefficient", column = @Column(name = "use_coefficient")),
@AttributeOverride(name = "warehouseCode", column = @Column(name = "whouse_code"))
})
public class BomItemModel extends BomItemModelInputDto{
@Id
	@Override
	public String getCode() {
		
		return super.getCode();
	}
	@Override
	public void setCode(String code) {
		
		super.setCode(code);
	}
@StreamLoadRelated(overriddenType=com.openi40.scheduler.input.model.cycles.BomItemModelInputDto$BomItemBatchInfoInputDto.class, loadType=BomItemBatchInfo.class,relationType=RelationType.ONE2ONE,joinProperty="bomItemCode")
@Override
public void setConsumingBatchInfo(com.openi40.scheduler.input.model.cycles.BomItemModelInputDto$BomItemBatchInfoInputDto p){
super.setConsumingBatchInfo(p);
}
}
