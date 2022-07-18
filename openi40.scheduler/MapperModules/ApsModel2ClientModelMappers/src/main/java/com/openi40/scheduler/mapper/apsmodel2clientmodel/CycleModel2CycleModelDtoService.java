package com.openi40.scheduler.mapper.apsmodel2clientmodel;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.stereotype.Service;

import com.openi40.scheduler.client.model.cycles.CycleModelDto;
import com.openi40.scheduler.client.model.cycles.OperationModelDto;
import com.openi40.scheduler.mapper.AbstractReflectorMapper;
import com.openi40.scheduler.mapper.IEntitiesFactory;
import com.openi40.scheduler.mapper.IMapper;
import com.openi40.scheduler.mapper.MapperException;
import com.openi40.scheduler.model.cycle.CycleModel;
import com.openi40.scheduler.model.cycle.OperationModel;
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
public class CycleModel2CycleModelDtoService extends AbstractReflectorMapper<CycleModel, CycleModelDto>
		implements IMapper<CycleModel, CycleModelDto> {
	public CycleModel2CycleModelDtoService(@Autowired AutowireCapableBeanFactory beanFactory) {
		super(beanFactory, CycleModel.class, CycleModelDto.class);
	}
	@Override
	public void copyValue(CycleModel originalObject, CycleModelDto targetObject, IEntitiesFactory entityFactory,
			Map environment, boolean recursive) throws MapperException {
		super.copyValue(originalObject, targetObject, entityFactory, environment, recursive);
		targetObject.getOperations().clear();
		List<OperationModel> operations = originalObject.getOperations();
		IMapper<OperationModel, OperationModelDto> mapper = this.getMapperFactory().createMapper(OperationModel.class, OperationModelDto.class);
		for(int i=operations.size()-1;i>=0;i--) {
			OperationModel operationModel = operations.get(i);
			OperationModelDto operationModelDto = mapper.mapInstance(operationModel, OperationModelDto.class, entityFactory, environment, recursive);
			targetObject.getOperations().add(operationModelDto);
		}
	}
}
