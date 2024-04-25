package com.openi40.platform.iomessages.spooler.model.dto;

import java.util.ArrayList;
import java.util.List;

import com.openi40.platform.iomessages.spooler.model.MsgSpoolerEntry;
import com.openi40.platform.iomessages.spooler.model.MsgSpoolerEntryProcessed;


public class MSGSpoolingEntryStatus {
	MsgSpoolerEntry spoolerEntry = null;
	List<MsgSpoolerEntryProcessed> processedStatus = new ArrayList<>();

	public MSGSpoolingEntryStatus() {

	}

	public MsgSpoolerEntry getSpoolerEntry() {
		return spoolerEntry;
	}

	public void setSpoolerEntry(MsgSpoolerEntry spoolerEntry) {
		this.spoolerEntry = spoolerEntry;
	}

	public List<MsgSpoolerEntryProcessed> getProcessedStatus() {
		return processedStatus;
	}

	public void setProcessedStatus(List<MsgSpoolerEntryProcessed> processedStatus) {
		this.processedStatus = processedStatus;
	}

}
