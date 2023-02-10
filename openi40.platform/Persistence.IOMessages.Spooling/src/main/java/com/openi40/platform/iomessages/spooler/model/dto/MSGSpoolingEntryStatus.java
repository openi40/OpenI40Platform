package com.openi40.platform.iomessages.spooler.model.dto;

import java.util.ArrayList;
import java.util.List;

import com.openi40.platform.iomessages.spooler.model.MsgSpoolerEntry;
import com.openi40.platform.iomessages.spooler.model.MsgSpoolerEntryProcessed;

import lombok.Data;

@Data
public class MSGSpoolingEntryStatus {
	MsgSpoolerEntry spoolerEntry = null;
	List<MsgSpoolerEntryProcessed> processedStatus = new ArrayList<>();

	public MSGSpoolingEntryStatus() {

	}

}
