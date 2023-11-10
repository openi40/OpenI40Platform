package com.openi40.mes.tasktracking.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.openi40.mes.assetworkstation.model.AssetWorkstationIdentifier;
import com.openi40.mes.tasktracking.model.dto.SelectableTaskOptionDto;
import com.openi40.mes.tasktracking.model.dto.TaskEventTypeDto;
import com.openi40.mes.tasktracking.service.ITaskTrackingService;

import io.swagger.annotations.Api;

@Api
@RestController
@RequestMapping(value = "tasktracking/MesTaskController")
public class MesTaskController {
	@Autowired
	ITaskTrackingService taskTrackingService;

	public MesTaskController() {

	}

	@PostMapping(path = "getCurrentTask")
	public SelectableTaskOptionDto getCurrentTask(HttpServletRequest request, AssetWorkstationIdentifier workstation) {
		SelectableTaskOptionDto actualTask = null;
		return actualTask;
	}

	@PostMapping(path = "getSelectableTasks")
	public List<SelectableTaskOptionDto> getSelectableTasks(HttpServletRequest request,
			AssetWorkstationIdentifier workstation) {
		List<SelectableTaskOptionDto> selectables = new ArrayList<SelectableTaskOptionDto>();

		return selectables;
	}

	@PostMapping(path = "getCurrentTaskManageableEvents/{mesTaskCode}")
	public List<TaskEventTypeDto> getCurrentTaskManageableEvents(HttpServletRequest request,
			@PathVariable("mesTaskCode") String mesTaskCode, AssetWorkstationIdentifier workstation) {
		return taskTrackingService.getCurrentTaskManageableEvents(workstation, mesTaskCode);
	}

}
