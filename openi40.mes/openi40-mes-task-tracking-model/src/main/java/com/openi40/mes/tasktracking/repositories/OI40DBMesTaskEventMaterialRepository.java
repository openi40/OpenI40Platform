package com.openi40.mes.tasktracking.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.openi40.mes.tasktracking.model.OI40DBMesTaskEventMaterial;
@Repository
public interface OI40DBMesTaskEventMaterialRepository extends JpaRepository<OI40DBMesTaskEventMaterial, Long>{
	List<OI40DBMesTaskEventMaterial> findByEventId(Long eventId);

	void deleteByEventId(Long id);
}
