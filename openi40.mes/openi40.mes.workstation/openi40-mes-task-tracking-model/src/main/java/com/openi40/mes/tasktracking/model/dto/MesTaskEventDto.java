package com.openi40.mes.tasktracking.model.dto;

import java.util.ArrayList;
import java.util.List;

import com.openi40.mes.tasktracking.model.OI40DBMesTaskEvent;
import com.openi40.mes.tasktracking.model.OI40DBMesTaskEventEquip;
import com.openi40.mes.tasktracking.model.OI40DBMesTaskEventMaterial;

import lombok.Data;
@Data
public class MesTaskEventDto {
	OI40DBMesTaskEvent event = null;
	List<OI40DBMesTaskEventEquip> equipments = new ArrayList<OI40DBMesTaskEventEquip>();
	List<OI40DBMesTaskEventMaterial> material = new ArrayList<OI40DBMesTaskEventMaterial>();

	public MesTaskEventDto() {

	}

	

}
