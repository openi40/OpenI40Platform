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
package com.openi40.scheduler.client.model.companystructure;

import java.util.ArrayList;
import java.util.List;


public class ProductiveCompanyDto extends AbstractCompany {

	List<PlantDto> plants = new ArrayList<PlantDto>();

	public List<PlantDto> getPlants() {
		return plants;
	}

	public void setPlants(List<PlantDto> plants) {
		this.plants = plants;
	}

}
