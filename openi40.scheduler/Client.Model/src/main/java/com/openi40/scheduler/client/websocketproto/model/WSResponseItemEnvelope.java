/**
 * 
 * This code is part of the OpenI40 open source advanced production scheduler
 * platform suite, have look to its licencing options.
 * Web site: http://openi40.org/  
 * Github: https://github.com/openi40/OpenI40Platform
 * We hope you enjoy implementing new amazing projects with it.
 * @author Paolo Zavalloni  architectures@openi40.org
 *
 */
package com.openi40.scheduler.client.websocketproto.model;

import java.util.UUID;


public class WSResponseItemEnvelope<DataType> extends BaseWSDataItem {
	protected DataType data = null;
	protected String requestID = null;
	protected String responseID = UUID.randomUUID().toString();
	protected int responseItemNr = 0;
	protected boolean lastItem = true;
	public DataType getData() {
		return data;
	}
	public void setData(DataType data) {
		this.data = data;
	}
	public String getRequestID() {
		return requestID;
	}
	public void setRequestID(String requestID) {
		this.requestID = requestID;
	}
	public String getResponseID() {
		return responseID;
	}
	public void setResponseID(String responseID) {
		this.responseID = responseID;
	}
	public int getResponseItemNr() {
		return responseItemNr;
	}
	public void setResponseItemNr(int responseItemNr) {
		this.responseItemNr = responseItemNr;
	}
	public boolean isLastItem() {
		return lastItem;
	}
	public void setLastItem(boolean lastItem) {
		this.lastItem = lastItem;
	}

}
