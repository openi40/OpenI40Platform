package com.openi40.scheduler.mapper.apsmodel2clientmodel;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.stereotype.Service;

import com.openi40.scheduler.client.model.material.MaterialConsumptionDto;
import com.openi40.scheduler.client.model.material.ProductDto;
import com.openi40.scheduler.client.model.material.SupplyReservationDto;
import com.openi40.scheduler.mapper.AbstractReflectorMapper;
import com.openi40.scheduler.mapper.IEntitiesFactory;
import com.openi40.scheduler.mapper.IMapper;
import com.openi40.scheduler.mapper.MapperException;
import com.openi40.scheduler.model.material.Product;
import com.openi40.scheduler.model.material.ItemConsumed;
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
public class ApsModelTask2ClientModelMaterialConsumptionDtoService
		extends AbstractReflectorMapper<ItemConsumed, MaterialConsumptionDto>
		implements IMapper<ItemConsumed, MaterialConsumptionDto> {

	public ApsModelTask2ClientModelMaterialConsumptionDtoService(@Autowired AutowireCapableBeanFactory beanFactory) {
		super(beanFactory, ItemConsumed.class, MaterialConsumptionDto.class, ApsModel2ClientModelConfiguration.typeMap);
	}

	@Override
	public void copyValue(ItemConsumed originalObject, MaterialConsumptionDto targetObject,
			IEntitiesFactory entityFactory, Map environment, boolean recursive) throws MapperException {
		super.copyValue(originalObject, targetObject, entityFactory, environment, recursive);
		IMapper<Product, ProductDto> productMapper = this.getMapperFactory().createMapper(Product.class,
				ProductDto.class);
		if (originalObject.getProduct() != null) {
			ProductDto product = productMapper.mapInstance(originalObject.getProduct(), ProductDto.class, entityFactory,
					environment, recursive);
			targetObject.setProduct(product);
		}
		List<SupplyReservation> reservations = originalObject.getOwnedReservations();
		if (reservations != null) {
			IMapper<SupplyReservation, SupplyReservationDto> mapper = getMapperFactory()
					.createMapper(SupplyReservation.class, SupplyReservationDto.class);
			for (SupplyReservation res : reservations) {
				SupplyReservationDto resCopy = mapper.mapInstance(res, SupplyReservationDto.class, entityFactory,
						environment, recursive);
				targetObject.getOwnedReservations().add(resCopy);
			}

		}
	}
}