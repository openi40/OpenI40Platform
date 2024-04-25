package com.openi40.mes.tasktracking.model.dto;

import java.util.ArrayList;
import java.util.List;

import com.openi40.mes.tasktracking.model.OI40DBMesTaskEvent;
import com.openi40.mes.tasktracking.model.OI40DBMesTaskEventEquip;
import com.openi40.mes.tasktracking.model.OI40DBMesTaskEventMaterial;

public class MesTaskEventDto {
	OI40DBMesTaskEvent event = null;
	List<OI40DBMesTaskEventEquip> equipments = new ArrayList<OI40DBMesTaskEventEquip>();
	List<OI40DBMesTaskEventMaterial> material = new ArrayList<OI40DBMesTaskEventMaterial>();

	public MesTaskEventDto() {

	}

	public OI40DBMesTaskEvent getEvent() {
		return event;
	}

	public void setEvent(OI40DBMesTaskEvent event) {
		this.event = event;
	}

	public List<OI40DBMesTaskEventEquip> getEquipments() {
		return equipments;
	}

	public void setEquipments(List<OI40DBMesTaskEventEquip> equipments) {
		this.equipments = equipments;
	}

	public List<OI40DBMesTaskEventMaterial> getMaterial() {
		return material;
	}

	public void setMaterial(List<OI40DBMesTaskEventMaterial> material) {
		this.material = material;
	}

	

}
