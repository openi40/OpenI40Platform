package com.openi40.scheduler.mapper.apsmodel2clientmodel;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.stereotype.Service;

import com.openi40.scheduler.client.model.ClientDto;
import com.openi40.scheduler.client.model.material.AbstractSupplyDto;
import com.openi40.scheduler.client.model.material.SupplyReservationDto;
import com.openi40.scheduler.common.aps.IApsObject;
import com.openi40.scheduler.mapper.AbstractReflectorMapper;
import com.openi40.scheduler.mapper.IEntitiesFactory;
import com.openi40.scheduler.mapper.IMapper;
import com.openi40.scheduler.mapper.MapperException;
import com.openi40.scheduler.model.material.AbstractSupply;
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
public class ApsModelAbstractSupply2AbstractSupplyDtoService<AbstractSupplyType extends AbstractSupply, AbstractSupplyDtoType extends AbstractSupplyDto>
		extends AbstractReflectorMapper<AbstractSupplyType, AbstractSupplyDtoType>
		implements IMapper<AbstractSupplyType, AbstractSupplyDtoType> {

	protected ApsModelAbstractSupply2AbstractSupplyDtoService(AutowireCapableBeanFactory beanFactory,
			Class<AbstractSupplyType> originalType, Class<AbstractSupplyDtoType> targetType) {
		super(beanFactory, originalType, targetType);

	}

	@Override
	public void copyValue(AbstractSupplyType originalObject, AbstractSupplyDtoType targetObject,
			IEntitiesFactory entityFactory, Map environment, boolean recursive) throws MapperException {
		super.copyValue(originalObject, targetObject, entityFactory, environment, recursive);
		IMapper<SupplyReservation, SupplyReservationDto> reservationMapper = this.getMapperFactory()
				.createMapper(SupplyReservation.class, SupplyReservationDto.class);
		if (targetObject.getReservations().isEmpty()) {
			List<SupplyReservation> originalReservations = originalObject.getReservations();
			for (SupplyReservation supplyReservation : originalReservations) {
				SupplyReservationDto reservationDto = reservationMapper.mapInstance(supplyReservation,
						SupplyReservationDto.class, entityFactory, environment, recursive);
				targetObject.getReservations().add(reservationDto);
			}
		}
	}

}
