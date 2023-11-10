package com.openi40.mes.tasktracking.model.dto;

import com.openi40.mes.tasktracking.model.OI40DBApsTask;
import com.openi40.mes.tasktracking.model.OI40DBMesTask;

import lombok.Data;

@Data
public class SelectableTaskOptionDto {
	OI40DBMesTask mesTask = null;
	OI40DBApsTask apsTask = null;
}