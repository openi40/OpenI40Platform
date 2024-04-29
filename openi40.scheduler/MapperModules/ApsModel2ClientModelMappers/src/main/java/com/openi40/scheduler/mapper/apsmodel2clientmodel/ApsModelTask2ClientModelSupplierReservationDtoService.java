package com.openi40.scheduler.mapper.apsmodel2clientmodel;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.stereotype.Service;

import com.openi40.scheduler.client.model.material.SupplyReservationDto;
import com.openi40.scheduler.mapper.AbstractReflectorMapper;
import com.openi40.scheduler.mapper.IEntitiesFactory;
import com.openi40.scheduler.mapper.IMapper;
import com.openi40.scheduler.mapper.MapperException;
import com.openi40.scheduler.model.material.ISupply;
import com.openi40.scheduler.model.material.ProductionSupply;
import com.openi40.scheduler.model.material.PurchaseSupply;
import com.openi40.scheduler.model.material.StockSupply;
import com.openi40.scheduler.model.material.SupplyReservation;
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
public class ApsModelTask2ClientModelSupplierReservationDtoService
		extends AbstractReflectorMapper<SupplyReservation, SupplyReservationDto>
		implements IMapper<SupplyReservation, SupplyReservationDto> {

	public ApsModelTask2ClientModelSupplierReservationDtoService(
			@Autowired AutowireCapableBeanFactory beanFactory) {
		super(beanFactory, SupplyReservation.class, SupplyReservationDto.class, ApsModel2ClientModelConfiguration.typeMap);
	}

	@Override
	public void copyValue(SupplyReservation originalObject, SupplyReservationDto targetObject,
			IEntitiesFactory entityFactory, Map environment, boolean recursive) throws MapperException {
		super.copyValue(originalObject, targetObject, entityFactory, environment, recursive);
		targetObject.setSupplyType(
				originalObject.getSupply() != null ? originalObject.getSupply().getClass().getSimpleName() : null);
		ISupply supply = originalObject.getSupply();
		if (supply instanceof PurchaseSupply) {
			PurchaseSupply _s = (PurchaseSupply) supply;
			targetObject.setWarehouseCode(_s.getWarehouseCode());
			targetObject.setDocumentCode(_s.getOrderCode());
		} else if (supply instanceof StockSupply) {
			StockSupply _s = (StockSupply) supply;
			targetObject.setWarehouseCode(_s.getWarehouseCode());

		} else if (supply instanceof ProductionSupply) {
			ProductionSupply _s = (ProductionSupply) supply;
			targetObject.setWarehouseCode(_s.getWarehouseCode());
			targetObject.setWorkOrderCode(_s.getWorkOrderCode());
		}

	}
}