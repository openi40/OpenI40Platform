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
package com.openi40.scheduler.client.model.aps;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.openi40.scheduler.client.model.ApsWindowDto;
import com.openi40.scheduler.client.model.ClientDto;
import com.openi40.scheduler.client.model.companystructure.ProductiveCompanyDto;
import com.openi40.scheduler.client.model.material.ProductDto;
import com.openi40.scheduler.client.model.time.TimesheetDto;

import lombok.Data;

@Data
public class ApsDataDto extends ClientDto implements Serializable {

	protected List<ProductiveCompanyDto> productiveCompanies = new ArrayList<ProductiveCompanyDto>();
	protected List<ProductDto> products = new ArrayList<ProductDto>();
	protected List<ApsSchedulingSetDto> schedulingSets = new ArrayList<ApsSchedulingSetDto>();
	protected Date actualDateTime = null;
	protected ApsWindowDto schedulingWindow = null;
	private String dataSourceName = null;
	protected String dataSetName = null;
	protected TimesheetDto timesheet = null;
	protected String calendarKey = null;
	protected String dataSetVariant = null;
	protected boolean scheduled = false;
	protected boolean fullyPlanned = false;
	protected boolean realtime = false;
	protected boolean productionControlEnabled = false;
}
