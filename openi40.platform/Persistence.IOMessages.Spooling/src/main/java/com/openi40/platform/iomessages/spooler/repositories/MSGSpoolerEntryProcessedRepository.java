package com.openi40.platform.iomessages.spooler.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.openi40.platform.iomessages.spooler.model.MsgSpoolerEntry;
import com.openi40.platform.iomessages.spooler.model.MsgSpoolerEntryProcessed;

@Repository
public interface MSGSpoolerEntryProcessedRepository extends JpaRepository<MsgSpoolerEntryProcessed, Long> {
	@Query("select T from MsgSpoolerEntryProcessed T where T.msgEntryId=:msgEntryId order by processedTimestamp")
	public List<MsgSpoolerEntryProcessed> findByMsgEntryId(@Param("msgEntryId") Long id);
}
