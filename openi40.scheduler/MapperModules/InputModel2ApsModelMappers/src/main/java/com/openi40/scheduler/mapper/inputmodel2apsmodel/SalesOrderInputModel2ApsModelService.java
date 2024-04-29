package com.openi40.scheduler.mapper.inputmodel2apsmodel;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.stereotype.Service;

import com.openi40.scheduler.engine.contextualplugarch.IBusinessModelFactory;
import com.openi40.scheduler.input.model.orders.SalesOrderInputDto;
import com.openi40.scheduler.input.model.orders.SalesOrderLineInputDto;
import com.openi40.scheduler.mapper.IEntitiesFactory;
import com.openi40.scheduler.mapper.IMapper;
import com.openi40.scheduler.mapper.MapperException;
import com.openi40.scheduler.model.orders.SalesOrder;
import com.openi40.scheduler.model.orders.SalesOrderLine;
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
@Service
public class SalesOrderInputModel2ApsModelService
		extends AbstractInputModel2ApsModelService<SalesOrderInputDto, SalesOrder> {

	public SalesOrderInputModel2ApsModelService(AutowireCapableBeanFactory beanFactory, IBusinessModelFactory businessModelFactory) {
		super(SalesOrderInputDto.class, SalesOrder.class, beanFactory,businessModelFactory);

	}

	@Override
	public void copyValue(SalesOrderInputDto originalObject, SalesOrder targetObject, IEntitiesFactory entityFactory,
			Map environment, boolean recursive) throws MapperException {
		super.copyValue(originalObject, targetObject, entityFactory, environment, recursive);
		IMapper<SalesOrderLineInputDto, SalesOrderLine> orderLineMapper = this.getMapperFactory()
				.createMapper(SalesOrderLineInputDto.class, SalesOrderLine.class);
		List<SalesOrderLineInputDto> olines = originalObject.getOrderLines();
		List<SalesOrderLine> targetLines = targetObject.getOrderLines();		
		for (SalesOrderLineInputDto salesOrderLineInputDto : olines) {
			List<SalesOrderLine> matching = new ArrayList<SalesOrderLine>();
			targetLines.forEach((oLine) -> {
				if (oLine.getCode().equals(salesOrderLineInputDto.getCode())) {
					matching.add(oLine);
				}
			});
			if (matching.isEmpty()) {
				targetLines.add(orderLineMapper.mapInstance(salesOrderLineInputDto, SalesOrderLine.class, entityFactory,
						environment, recursive));
			} else {
				orderLineMapper.copyValue(salesOrderLineInputDto, matching.get(0), entityFactory, environment,
						recursive);
			}
		}

	}

}
