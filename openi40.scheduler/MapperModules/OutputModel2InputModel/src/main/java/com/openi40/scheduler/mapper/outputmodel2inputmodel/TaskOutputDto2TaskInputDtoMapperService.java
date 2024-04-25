package com.openi40.scheduler.mapper.outputmodel2inputmodel;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.stereotype.Service;

import com.openi40.scheduler.input.model.InputDto;
import com.openi40.scheduler.input.model.tasks.ApsMessageInputDto;
import com.openi40.scheduler.input.model.tasks.TaskInputDto;
import com.openi40.scheduler.input.model.tasks.TaskResourceReservationInputDto;
import com.openi40.scheduler.input.model.tasks.UsedSecondaryResourcesInfoInputDto;
import com.openi40.scheduler.mapper.DefaultEntitiesFactory;
import com.openi40.scheduler.mapper.IEntitiesFactory;
import com.openi40.scheduler.mapper.IMapper;
import com.openi40.scheduler.mapper.MapperException;
import com.openi40.scheduler.output.model.OutputDto;
import com.openi40.scheduler.output.model.tasks.TaskOutputDto;
import com.openi40.scheduler.output.model.tasks.TaskOutputDto.ApsMessageOutputDto;
import com.openi40.scheduler.output.model.tasks.TaskOutputDto.SecondaryReservation;
import com.openi40.scheduler.output.model.tasks.TaskOutputDto.TaskMaterialTransfer;
import com.openi40.scheduler.output.model.tasks.TaskOutputDto.UsedSecondaryResourcesInfoOutputDto;

@Service
public class TaskOutputDto2TaskInputDtoMapperService
		extends AbstractOutputDto2InputDtoMapper<TaskOutputDto, TaskInputDto> {
	public TaskOutputDto2TaskInputDtoMapperService(@Autowired AutowireCapableBeanFactory autowireCapableFactory) {
		super(TaskOutputDto.class, TaskInputDto.class, autowireCapableFactory);
	}

	private HashMap dummyArg = new HashMap<>();
	private IEntitiesFactory ifactory = DefaultEntitiesFactory.Instance;

	@Override
	public void copyValue(TaskOutputDto originalObject, TaskInputDto targetObject, IEntitiesFactory entityFactory,
			Map environment, boolean recursive) throws MapperException {
		super.copyValue(originalObject, targetObject, entityFactory, environment, false);
		IMapper<UsedSecondaryResourcesInfoOutputDto, UsedSecondaryResourcesInfoInputDto> mapper = this
				.getMapperFactory()
				.createMapper(UsedSecondaryResourcesInfoOutputDto.class, UsedSecondaryResourcesInfoInputDto.class);

		if (originalObject.getAcquiredSetupUsedResources() != null) {
			for (UsedSecondaryResourcesInfoOutputDto sUsed : originalObject.getAcquiredSetupUsedResources()) {
				UsedSecondaryResourcesInfoInputDto entry = mapper.mapInstance(sUsed,
						UsedSecondaryResourcesInfoInputDto.class, entityFactory, environment, recursive);
				targetObject.getAcquiredSetupUsedResources().add(entry);
			}
		}
		if (originalObject.getAcquiredWorkUsedResources() != null) {
			for (UsedSecondaryResourcesInfoOutputDto sUsed : originalObject.getAcquiredWorkUsedResources()) {
				UsedSecondaryResourcesInfoInputDto entry = mapper.mapInstance(sUsed,
						UsedSecondaryResourcesInfoInputDto.class, entityFactory, environment, recursive);
				targetObject.getAcquiredWorkUsedResources().add(entry);
			}
		}
		if (originalObject.getMessages() != null && originalObject.getMessages().size() > 0) {
			IMapper<ApsMessageOutputDto, ApsMessageInputDto> msgMapper = this.getMapperFactory()
					.createMapper(ApsMessageOutputDto.class, ApsMessageInputDto.class);
			for (ApsMessageOutputDto msg : originalObject.getMessages()) {
				ApsMessageInputDto outMsg = msgMapper.mapInstance(msg, ApsMessageInputDto.class, entityFactory,
						environment, recursive);
				targetObject.getMessages().add(outMsg);
			}
		}
		if (originalObject.getSecondaryReservations() != null && originalObject.getSecondaryReservations().size() > 0) {
			IMapper<SecondaryReservation, TaskResourceReservationInputDto> secondaryMapper = this.getMapperFactory()
					.createMapper(SecondaryReservation.class, TaskResourceReservationInputDto.class);
			for (SecondaryReservation seco : originalObject.getSecondaryReservations()) {
				TaskResourceReservationInputDto entry = secondaryMapper.mapInstance(seco,
						TaskResourceReservationInputDto.class, entityFactory, environment, true);
				targetObject.getResourcesReservations().add(entry);
			}
		}
	}
}