package com.openi40.platform.iomessages.spooler.services;

import java.util.function.Function;

import com.openi40.platform.iomessages.spooler.services.IMSGSpoolingService.MsgIngestionResult;
import com.openi40.scheduler.iomessages.AbstractBaseIOMessage;

public interface IMSGSpoolingProcessorService extends Function<AbstractBaseIOMessage, MsgIngestionResult> {

}
