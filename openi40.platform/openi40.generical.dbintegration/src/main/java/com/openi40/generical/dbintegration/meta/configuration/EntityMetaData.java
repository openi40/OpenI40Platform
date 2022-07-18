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
package com.openi40.generical.dbintegration.meta.configuration;

import lombok.Data;

@Data
public class EntityMetaData {
	private String entityName=null;
	private String tableName=null;
	public EntityMetaData() {
		
	}
	

}
