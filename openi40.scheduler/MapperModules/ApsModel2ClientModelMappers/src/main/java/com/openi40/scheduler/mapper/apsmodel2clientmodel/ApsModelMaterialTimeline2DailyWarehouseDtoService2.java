package com.openi40.scheduler.mapper.apsmodel2clientmodel;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.stereotype.Service;

import com.openi40.scheduler.client.material.model.DailyWarehouseGraph;
import com.openi40.scheduler.client.material.model.InventoryDay;
import com.openi40.scheduler.client.model.material.StockSupplyDto;
import com.openi40.scheduler.mapper.AbstractReflectorMapper;
import com.openi40.scheduler.mapper.IEntitiesFactory;
import com.openi40.scheduler.mapper.IMapper;
import com.openi40.scheduler.mapper.MapperException;
import com.openi40.scheduler.model.material.StockSupply;
import com.openi40.scheduler.model.material.timeline.AbstractMaterialTimeLine;
import com.openi40.scheduler.model.material.timeline.InventoryTimeNode;
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
@Service
public class ApsModelMaterialTimeline2DailyWarehouseDtoService2
		extends AbstractReflectorMapper<AbstractMaterialTimeLine, DailyWarehouseGraph>
		implements IMapper<AbstractMaterialTimeLine, DailyWarehouseGraph> {
	@Autowired
	public ApsModelMaterialTimeline2DailyWarehouseDtoService2(AutowireCapableBeanFactory beanFactory) {
		super(beanFactory, AbstractMaterialTimeLine.class, DailyWarehouseGraph.class);

	}

	@Override
	public void copyValue(AbstractMaterialTimeLine originalObject, DailyWarehouseGraph targetObject,
			IEntitiesFactory entityFactory, Map environment, boolean recursive) throws MapperException {
		super.copyValue(originalObject, targetObject, entityFactory, environment, recursive);
		InventoryTimeNode actualNode = originalObject.getInitialInventoryNode();
		while (actualNode != null) {
			if (actualNode.getStartingSegment() != null) {
				InventoryDay inventoryDay = new InventoryDay();
				inventoryDay.setInventoryQty(actualNode.getStartingSegment().getInventoryQty());
				inventoryDay.setTime(actualNode.getEventsTime());
				targetObject.getInventoryDays().add(inventoryDay);
			}
			InventoryTimeNode nextNode = null;
			if (actualNode.getStartingSegment() != null) {
				nextNode = actualNode.getStartingSegment().getEndNode();
			}
			actualNode = nextNode;
		}
	}
}
