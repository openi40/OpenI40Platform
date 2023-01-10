package com.openi40.platform.iomessages.spooler.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.openi40.platform.iomessages.spooler.model.MsgSpoolerEntry;

@Repository
public interface MSGSpoolerEntryRepository extends JpaRepository<MsgSpoolerEntry, Long> {
	@Query("select T from MsgSpoolerEntry T where T.dataSourceName=:dataSourceName and T.dataSetName=:dataSetName and T.dataSetVariant=:dataSetVariant and T.processedStatus=:processedStatus order by messageTimestamp")
	List<MsgSpoolerEntry> findByProcessedStatus(@Param("dataSourceName") String dataSourceName,
			@Param("dataSetName") String dataSetName, @Param("dataSetVariant") String dataSetVariant,
			@Param("processedStatus") String processedStatus);

	@Query("select T from MsgSpoolerEntry T where T.dataSourceName=:dataSourceName and T.dataSetName=:dataSetName and T.dataSetVariant=:dataSetVariant and T.code=:code order by messageTimestamp")
	List<MsgSpoolerEntry> findByCode(@Param("dataSourceName") String dataSourceName,
			@Param("dataSetName") String dataSetName, @Param("dataSetVariant") String dataSetVariant,
			@Param("code") String code);

}
