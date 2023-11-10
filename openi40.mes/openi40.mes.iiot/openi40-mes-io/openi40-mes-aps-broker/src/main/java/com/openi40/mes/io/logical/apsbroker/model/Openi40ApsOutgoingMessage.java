package com.openi40.mes.io.logical.apsbroker.model;

import java.io.Serializable;

import com.openi40.mes.metamessaging.model.AbstractOI40IOTApplicationMessage;
import com.openi40.scheduler.iomessages.AbstractBaseIOMessage;

import lombok.Data;
@Data
public class Openi40ApsOutgoingMessage extends AbstractOI40IOTApplicationMessage implements Serializable{
	private AbstractBaseIOMessage apsContent=null;
	/**
	 * 
	 */
	private static final long serialVersionUID = 2488579198526066531L;

	public Openi40ApsOutgoingMessage() {
		
	}

}
