package com.openi40.platform.iomessages.spooler.services;

import java.util.function.Function;

import com.openi40.scheduler.iomessages.AbstractBaseIOMessage;

import lombok.Data;



public interface IMSGSpoolingService {
	public void add(String dataSourceName,String dataSetName,String dataSetVariant,AbstractBaseIOMessage message) throws MSGSpoolerException;	
	@Data
	public static class MsgIngestionResult {
		boolean successfull=false;
		String errorCode=null;
		String errorMessage=null;
	}
	public void evaluateMessages(String dataSourceName,String dataSetName,String dataSetVariant,Function<AbstractBaseIOMessage, MsgIngestionResult> function);
}
