package com.openi40.platform.iomessages.spooler.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.openi40.platform.iomessages.spooler.model.MSGSpoolerEntry;
import com.openi40.platform.iomessages.spooler.model.MSGSpoolerEntryProcessed;
@Repository
public interface MSGSpoolerEntryProcessedRepository extends JpaRepository<MSGSpoolerEntryProcessed, Long> {

}
