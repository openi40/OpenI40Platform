package com.openi40.mes.tasktracking.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.openi40.mes.tasktracking.model.OI40DBMesTaskEvent;

@Repository
public interface OI40DBMesTaskEventRepository extends JpaRepository<OI40DBMesTaskEvent, Long> {
	public Page<OI40DBMesTaskEvent> findByMesTaskCode(String mesTaskCode, Pageable pageable);
}
