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
package com.openi40.generical.dbintegration.services;

import java.sql.Connection;

import org.springframework.stereotype.Service;

import com.openi40.generical.dbintegration.configuration.IntegrationConfiguration;
@Service
public class ExportDataService {

	public ExportDataService() {
		
	}

	public void exportData(Connection openi40Connection, Connection otherDBConnection,
			IntegrationConfiguration configuration) {

	}

}
