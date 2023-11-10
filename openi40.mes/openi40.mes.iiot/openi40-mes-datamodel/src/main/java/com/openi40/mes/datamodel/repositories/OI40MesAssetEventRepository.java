package com.openi40.mes.datamodel.repositories;

import java.util.stream.Stream;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.openi40.mes.datamodel.OI40DBMesAssetEvent;
@Repository
public interface OI40MesAssetEventRepository extends JpaRepository<OI40DBMesAssetEvent, Long>{
		@Query("select e from OI40DBMesAssetEvent e where (e.processedStatus is null or e.processedStatus ='UNPROCESSED') order by e.eventTime")
		Stream<OI40DBMesAssetEvent> getUnprocessedStream();
}
