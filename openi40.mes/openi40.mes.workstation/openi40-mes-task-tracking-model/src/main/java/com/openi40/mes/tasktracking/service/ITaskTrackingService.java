package com.openi40.mes.tasktracking.service;

import java.util.List;

import com.openi40.mes.assetworkstation.model.AssetWorkstationIdentifier;
import com.openi40.mes.tasktracking.model.dto.TaskEventTypeDto;

public interface ITaskTrackingService {
	public List<TaskEventTypeDto> getCurrentTaskManageableEvents(AssetWorkstationIdentifier identifier,
			String currentMesTaskCode);
}
