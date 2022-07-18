package com.openi40.scheduler.mapper.inputmodel2apsmodel;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.stereotype.Service;

import com.openi40.scheduler.engine.contextualplugarch.IBusinessModelFactory;
import com.openi40.scheduler.input.model.orders.PurchaseOrderInputDto;
import com.openi40.scheduler.input.model.orders.PurchaseOrderLineInputDto;
import com.openi40.scheduler.input.model.orders.SalesOrderInputDto;
import com.openi40.scheduler.input.model.orders.SalesOrderLineInputDto;
import com.openi40.scheduler.mapper.IEntitiesFactory;
import com.openi40.scheduler.mapper.IMapper;
import com.openi40.scheduler.mapper.MapperException;
import com.openi40.scheduler.model.orders.PurchaseOrder;
import com.openi40.scheduler.model.orders.PurchaseOrderLine;
import com.openi40.scheduler.model.orders.SalesOrder;
import com.openi40.scheduler.model.orders.SalesOrderLine;
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
public class PurchaseOrderInputModel2ApsModelService
		extends AbstractInputModel2ApsModelService<PurchaseOrderInputDto, PurchaseOrder> {

	public PurchaseOrderInputModel2ApsModelService(AutowireCapableBeanFactory beanFactory, IBusinessModelFactory businessModelFactory) {
		super(PurchaseOrderInputDto.class, PurchaseOrder.class, beanFactory,businessModelFactory);

	}

	@Override
	public void copyValue(PurchaseOrderInputDto originalObject, PurchaseOrder targetObject,
			IEntitiesFactory entityFactory, Map environment, boolean recursive) throws MapperException {
		super.copyValue(originalObject, targetObject, entityFactory, environment, recursive);
		IMapper<PurchaseOrderLineInputDto, PurchaseOrderLine> orderLineMapper = this.getMapperFactory()
				.createMapper(PurchaseOrderLineInputDto.class, PurchaseOrderLine.class);
		List<PurchaseOrderLineInputDto> olines = originalObject.getOrderLines();
		List<PurchaseOrderLine> targetLines = targetObject.getOrderLines();
		for (PurchaseOrderLineInputDto salesOrderLineInputDto : olines) {
			List<PurchaseOrderLine> matching = new ArrayList<PurchaseOrderLine>();
			targetLines.forEach((oLine) -> {
				if (oLine.getCode().equals(salesOrderLineInputDto.getCode())) {
					matching.add(oLine);
				}
			});
			if (matching.isEmpty()) {
				targetLines.add(orderLineMapper.mapInstance(salesOrderLineInputDto, PurchaseOrderLine.class,
						entityFactory, environment, recursive));
			} else {
				orderLineMapper.copyValue(salesOrderLineInputDto, matching.get(0), entityFactory, environment,
						recursive);
			}
		}

	}

}
