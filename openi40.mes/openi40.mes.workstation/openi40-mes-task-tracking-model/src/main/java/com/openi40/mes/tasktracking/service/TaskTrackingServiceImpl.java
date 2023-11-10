package com.openi40.mes.tasktracking.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.openi40.mes.assetworkstation.model.AssetWorkstationIdentifier;
import com.openi40.mes.tasktracking.model.dto.TaskEventTypeDto;
@Service
public class TaskTrackingServiceImpl implements ITaskTrackingService {

	public TaskTrackingServiceImpl() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<TaskEventTypeDto> getCurrentTaskManageableEvents(AssetWorkstationIdentifier identifier,
			String currentMesTaskCode) {
		// TODO Auto-generated method stub
		return null;
	}

}
