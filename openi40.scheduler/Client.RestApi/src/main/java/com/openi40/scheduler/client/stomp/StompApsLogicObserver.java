package com.openi40.scheduler.client.stomp;

import java.util.HashMap;

import org.springframework.stereotype.Service;

import com.openi40.scheduler.client.model.tasks.TaskDto;
import com.openi40.scheduler.client.stomp.model.OpenI40TaskEnvelope;
import com.openi40.scheduler.common.aps.IApsObject;
import com.openi40.scheduler.engine.aps.IApsLogicObserver;
import com.openi40.scheduler.mapper.DefaultEntitiesFactory;
import com.openi40.scheduler.mapper.IMapper;
import com.openi40.scheduler.mapper.IMapperFactory;
import com.openi40.scheduler.mapper.MapperException;
import com.openi40.scheduler.model.aps.ApsData;
import com.openi40.scheduler.model.aps.ApsSchedulingSet;
import com.openi40.scheduler.model.tasks.Task;

@Service
public class StompApsLogicObserver implements IApsLogicObserver {
	ClientSendObjectService sendService = null;
	IMapperFactory mapperFactory = null;
	IMapper<Task, TaskDto> taskMapper = null;

	public StompApsLogicObserver(ClientSendObjectService sendService, IMapperFactory mapperFactory)
			throws MapperException {
		this.sendService = sendService;
		this.mapperFactory = mapperFactory;
		taskMapper = this.mapperFactory.createMapper(Task.class, TaskDto.class);
	}

	@Override
	public void startProcessingElement(IApsObject object) {

	}

	static HashMap environment = new HashMap();

	@Override
	public void processedElement(IApsObject object) {
		if (object instanceof Task) {
			try {
				String dataSourceName = null;
				String dataSetId = null;
				String dataSetVariant = null;
				if (object.getContext() != null && object.getContext() instanceof ApsSchedulingSet
						&& object.getContext().getContext() != null
						&& object.getContext().getContext() instanceof ApsData) {

					ApsData apsData = (ApsData) object.getContext().getContext();
					dataSourceName = apsData.getDataSourceName();
					dataSetId = apsData.getDataSetName();
					dataSetVariant = apsData.getDataSetVariant();
					TaskDto taskDto = taskMapper.mapInstance((Task) object, TaskDto.class,
							DefaultEntitiesFactory.Instance, environment, true);
					OpenI40TaskEnvelope envelope = new OpenI40TaskEnvelope(taskDto);
					sendService.notifyClients(dataSourceName, dataSetId, dataSetVariant, envelope);
				}
			} catch (MapperException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

}
