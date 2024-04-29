package com.openi40.scheduler.mapper.apsmodel2clientmodel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.stereotype.Service;

import com.openi40.scheduler.client.model.material.StockSupplyDto;
import com.openi40.scheduler.model.material.StockSupply;
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
public class ApsModelStockSupply2StockSupplyDtoService
		extends ApsModelAbstractSupply2AbstractSupplyDtoService<StockSupply, StockSupplyDto> {
	@Autowired
	public ApsModelStockSupply2StockSupplyDtoService(AutowireCapableBeanFactory beanFactory) {
		super(beanFactory, StockSupply.class, StockSupplyDto.class);
		
	}

}
