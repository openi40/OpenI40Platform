package com.openi40.scheduler.mapper.apsmodel2clientmodel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.stereotype.Service;

import com.openi40.scheduler.client.model.material.ProductionSupplyDto;
import com.openi40.scheduler.client.model.material.StockSupplyDto;
import com.openi40.scheduler.model.material.ProductionSupply;
import com.openi40.scheduler.model.material.StockSupply;
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
public class ApsModelProductionSupply2ProductionSupplyDtoService
		extends ApsModelAbstractSupply2AbstractSupplyDtoService<ProductionSupply, ProductionSupplyDto> {
	@Autowired
	public ApsModelProductionSupply2ProductionSupplyDtoService(AutowireCapableBeanFactory beanFactory) {
		super(beanFactory, ProductionSupply.class, ProductionSupplyDto.class);

	}

}
