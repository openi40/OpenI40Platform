package com.openi40.mes.tasktracking.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.openi40.mes.tasktracking.model.OI40DBMesTask;
@Repository
public interface OI40DBMesTaskRepository extends JpaRepository< OI40DBMesTask, String>{

}
