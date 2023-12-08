/**
 * 
 * This code is part of the OpenI40 open source advanced production scheduler
 * platform suite, have look to its licencing options.
 * Web site: http://openi40.org/  
 * Github: https://github.com/openi40/OpenI40Platform
 * We hope you enjoy implementing new amazing projects with it.
 * @author architectures@openi40.org
 *
 */
package com.openi40.scheduler.client.websocketproto.model;

import java.util.UUID;


public class WSRequestEnvelope<AbstractWSRequestType extends AbstractWSRequest> extends BaseWSDataItem {
	protected AbstractWSRequestType request=null; 
	protected String requestID=UUID.randomUUID().toString();
	public AbstractWSRequestType getRequest() {
		return request;
	}
	public void setRequest(AbstractWSRequestType request) {
		this.request = request;
	}
	public String getRequestID() {
		return requestID;
	}
	public void setRequestID(String requestID) {
		this.requestID = requestID;
	}

}
