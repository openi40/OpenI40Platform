package com.openi40.mes.tasktracking.model.dto;

import com.openi40.mes.tasktracking.model.OI40DBApsTask;
import com.openi40.mes.tasktracking.model.OI40DBMesTask;


public class SelectableTaskOptionDto {
	OI40DBMesTask mesTask = null;
	OI40DBApsTask apsTask = null;
	public OI40DBMesTask getMesTask() {
		return mesTask;
	}
	public void setMesTask(OI40DBMesTask mesTask) {
		this.mesTask = mesTask;
	}
	public OI40DBApsTask getApsTask() {
		return apsTask;
	}
	public void setApsTask(OI40DBApsTask apsTask) {
		this.apsTask = apsTask;
	}
}