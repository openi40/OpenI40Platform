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
package com.openi40.generical.dbintegration.handlers;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

public interface IEntityIntegrationFactory {
	public List<Map<String, Object>> getEntities(List<String> params);

	public List<Map<String, Object>> getEntities(List<String> params, Timestamp ts);
}
