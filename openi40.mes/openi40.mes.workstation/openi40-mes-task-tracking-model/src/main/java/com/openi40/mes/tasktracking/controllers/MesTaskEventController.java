package com.openi40.mes.tasktracking.controllers;

import java.util.List;

import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.openi40.dbmodel.utils.PageInfo;
import com.openi40.mes.assetworkstation.model.AssetWorkstationRequestWrapper;
import com.openi40.mes.tasktracking.model.OI40DBMesTaskEvent;
import com.openi40.mes.tasktracking.model.OI40DBMesTaskEventEquip;
import com.openi40.mes.tasktracking.model.OI40DBMesTaskEventMaterial;
import com.openi40.mes.tasktracking.model.dto.MesTaskEventDto;
import com.openi40.mes.tasktracking.repositories.OI40DBMesTaskEventEquipRepository;
import com.openi40.mes.tasktracking.repositories.OI40DBMesTaskEventMaterialRepository;
import com.openi40.mes.tasktracking.repositories.OI40DBMesTaskEventRepository;
import com.openi40.mes.tasktracking.service.ITaskTrackingService;

import io.swagger.annotations.Api;

@Api
@RestController
@RequestMapping(value = "tasktracking/MesTaskEventController")
public class MesTaskEventController {
	@Autowired
	OI40DBMesTaskEventRepository taskEventRepo;
	@Autowired
	OI40DBMesTaskEventEquipRepository taskEventEquipRepo;
	@Autowired
	OI40DBMesTaskEventMaterialRepository taskEventMaterialRepo;
	
	public MesTaskEventController() {

	}

	@PostMapping(path = "findByMesTaskCode/{code}", produces = MediaType.APPLICATION_JSON_VALUE)
	@Transactional
	public Page<MesTaskEventDto> findByMesTaskCode(@NotNull @PathVariable("code") String mesTaskCode,
			@RequestBody PageInfo pageInfo) {
		Page<OI40DBMesTaskEvent> events = this.taskEventRepo.findByMesTaskCode(mesTaskCode, PageInfo.from(pageInfo));
		return events.map((entry) -> {
			MesTaskEventDto event = new MesTaskEventDto();
			event.setEvent(entry);
			event.setEquipments(taskEventEquipRepo.findByEventId(entry.getId()));
			event.setMaterial(taskEventMaterialRepo.findByEventId(entry.getId()));
			return event;
		});

	}

	@PostMapping(path = "update", produces = MediaType.APPLICATION_JSON_VALUE)
	@Transactional(value = TxType.REQUIRES_NEW)
	public MesTaskEventDto update(@NotNull @RequestBody AssetWorkstationRequestWrapper<MesTaskEventDto> request) {
		MesTaskEventDto data = request.getContent();
		MesTaskEventDto out = new MesTaskEventDto();
		OI40DBMesTaskEvent entry = taskEventRepo.saveAndFlush(data.getEvent());
		out.setEvent(entry);
		if (data.getEquipments() != null) {
			for (OI40DBMesTaskEventEquip equip : data.getEquipments()) {
				equip.setEventId(entry.getId());
				taskEventEquipRepo.saveAndFlush(equip);
				out.getEquipments().add(equip);
			}

		}
		if (data.getMaterial() != null) {
			for (OI40DBMesTaskEventMaterial mat : data.getMaterial()) {
				mat.setEventId(entry.getId());
				taskEventMaterialRepo.saveAndFlush(mat);
				out.getMaterial().add(mat);
			}

		}
		return data;
	}

	@PostMapping(path = "removeEvents")
	@Transactional
	public void removeEvents(@RequestBody List<Long> ids) {
		for (Long id : ids) {
			this.taskEventRepo.deleteById(id);
			this.taskEventMaterialRepo.deleteByEventId(id);
			this.taskEventEquipRepo.deleteByEventId(id);
		}
	}
}
