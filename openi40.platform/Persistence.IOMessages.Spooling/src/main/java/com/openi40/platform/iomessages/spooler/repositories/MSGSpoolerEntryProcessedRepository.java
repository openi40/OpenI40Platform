package com.openi40.platform.iomessages.spooler.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.openi40.platform.iomessages.spooler.model.MsgSpoolerEntry;
import com.openi40.platform.iomessages.spooler.model.MsgSpoolerEntryProcessed;
@Repository
public interface MSGSpoolerEntryProcessedRepository extends JpaRepository<MsgSpoolerEntryProcessed, Long> {

}
