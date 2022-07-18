package com.openi40.scheduler.engine.material;

import java.util.List;

import com.openi40.scheduler.engine.contextualplugarch.BusinessInterface;
import com.openi40.scheduler.engine.contextualplugarch.IBusinessLogic;
import com.openi40.scheduler.model.aps.ApsData;
import com.openi40.scheduler.model.companystructure.Warehouse;
import com.openi40.scheduler.model.material.ISupply;
import com.openi40.scheduler.model.material.ISupplyConsumer;
import com.openi40.scheduler.model.material.Product;
import com.openi40.scheduler.model.material.timeline.TimeLineException;
import com.openi40.scheduler.model.material.timeline.WarehouseProductMaterialTimeLine;
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
@BusinessInterface(entityClass = ApsData.class)
public interface IMaterialTimeLineManager extends IBusinessLogic<ApsData> {
	public WarehouseProductMaterialTimeLine getMaterialTimeLine(Warehouse warehouse, Product product, ApsData context)
			throws TimeLineException;

	public void addSupply(ApsData context, ISupply supply)
			throws TimeLineException;

	public void addSupplyConsumer(ApsData context, ISupplyConsumer consumer)
			throws TimeLineException;

	public void addSupply(ApsData context, List<ISupply> supplies)
			throws TimeLineException;

	public void add(ApsData context, List<ISupplyConsumer> consumers, List<ISupply> supplies) throws TimeLineException;

	public void addSupplyConsumer(ApsData context, List<ISupplyConsumer> consumers) throws TimeLineException;

	public void addScheduledTask(Task task, ApsData context) throws TimeLineException;
}
