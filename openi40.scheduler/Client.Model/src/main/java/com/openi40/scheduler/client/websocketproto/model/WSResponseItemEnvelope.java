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

import lombok.Data;

@Data
public class WSResponseItemEnvelope<DataType> extends BaseWSDataItem {
	protected DataType data = null;
	protected String requestID = null;
	protected String responseID = UUID.randomUUID().toString();
	protected int responseItemNr = 0;
	protected boolean lastItem = true;

}
