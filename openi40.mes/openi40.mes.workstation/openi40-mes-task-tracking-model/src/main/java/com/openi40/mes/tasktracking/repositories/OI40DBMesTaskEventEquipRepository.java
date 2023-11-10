package com.openi40.mes.tasktracking.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.openi40.mes.tasktracking.model.OI40DBMesTaskEventEquip;

@Repository
public interface OI40DBMesTaskEventEquipRepository extends JpaRepository<OI40DBMesTaskEventEquip, Long> {
	List<OI40DBMesTaskEventEquip> findByEventId(Long eventId);

	void deleteByEventId(Long id);
}
