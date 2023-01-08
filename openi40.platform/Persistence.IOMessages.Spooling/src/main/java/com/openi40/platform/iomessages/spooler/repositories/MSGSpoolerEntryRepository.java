package com.openi40.platform.iomessages.spooler.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.openi40.platform.iomessages.spooler.model.MSGSpoolerEntry;

@Repository
public interface MSGSpoolerEntryRepository extends JpaRepository<MSGSpoolerEntry, Long> {
	@Query("select T from MsgSpoolerEntry T where T.dataSourceName=:dataSourceName and T.dataSetName=:dataSetName and T.dataSetVariant=:dataSetVariant and T.processedStatus=:processedStatus order by messageTimestamp")
	List<MSGSpoolerEntry> findByProcessedStatus(@Param("dataSourceName") String dataSourceName,@Param("dataSetName") String dataSetName,@Param("dataSetVariant") String dataSetVariant,@Param("processedStatus") String  processedStatus);
}
