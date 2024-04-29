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
package com.openi40.generical.dbintegration.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.openi40.generical.dbintegration.services.DBIntegrationService;

@RestController
@RequestMapping(path = "dbintegration/IntegrationRestController")
public class IntegrationRestController {
	private DBIntegrationService integrationService = null;

	@Autowired
	public IntegrationRestController(DBIntegrationService integrationService) {
		this.integrationService = integrationService;

	}

	@GetMapping("importFullData")
	public void importFullData() {
		integrationService.importData(false);
	}

	@GetMapping("importIncrementalData")
	public void importIncrementalData() {
		integrationService.importData(true);
	}

	@GetMapping("exportData")
	public void exportData() {
		integrationService.exportData();
	}

}
