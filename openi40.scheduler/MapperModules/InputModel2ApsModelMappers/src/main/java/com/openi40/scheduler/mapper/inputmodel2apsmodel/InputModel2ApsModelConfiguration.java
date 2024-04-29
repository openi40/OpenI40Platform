package com.openi40.scheduler.mapper.inputmodel2apsmodel;

import org.springframework.context.annotation.Configuration;

import com.openi40.scheduler.input.model.ApsInputData;
import com.openi40.scheduler.input.model.companystructure.ProductiveCompanyInputDto;
import com.openi40.scheduler.input.model.orders.SalesOrderInputDto;
import com.openi40.scheduler.input.model.orders.SalesOrderLineInputDto;
import com.openi40.scheduler.input.model.time.TimesheetMetaInfoExceptionRuleInputDto;
import com.openi40.scheduler.input.model.time.TimesheetMetaInfoInputDto;
import com.openi40.scheduler.input.model.time.TimesheetMetaInfoWorkingTimeRuleInputDto;
import com.openi40.scheduler.mapper.MapperException;
import com.openi40.scheduler.mapper.TypeMap;
import com.openi40.scheduler.model.aps.ApsData;
import com.openi40.scheduler.model.companystructure.ProductiveCompany;
import com.openi40.scheduler.model.orders.SalesOrder;
import com.openi40.scheduler.model.orders.SalesOrderLine;
import com.openi40.scheduler.model.time.TimesheetMetaInfo;
import com.openi40.scheduler.model.time.TimesheetMetaInfoExceptionRule;
import com.openi40.scheduler.model.time.TimesheetMetaInfoWorkingTimeRule;
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
@Configuration
public class InputModel2ApsModelConfiguration {
	static final TypeMap typeMap = new TypeMap();
	static {
		try {
			typeMap.add(ApsInputData.class, ApsData.class);
			typeMap.add(SalesOrderInputDto.class, SalesOrder.class);
			typeMap.add(SalesOrderInputDto.class, SalesOrder.class,"OrderLines",SalesOrderLineInputDto.class,SalesOrderLine.class);
			typeMap.add(ProductiveCompanyInputDto.class, ProductiveCompany.class);
			typeMap.add(TimesheetMetaInfoInputDto.class, TimesheetMetaInfo.class);
			typeMap.add(TimesheetMetaInfoInputDto.class, TimesheetMetaInfo.class, "workingTimeRules",
					TimesheetMetaInfoWorkingTimeRuleInputDto.class, TimesheetMetaInfoWorkingTimeRule.class);
			typeMap.add(TimesheetMetaInfoInputDto.class, TimesheetMetaInfo.class, "exceptionRules",
					TimesheetMetaInfoExceptionRuleInputDto.class, TimesheetMetaInfoExceptionRule.class);
		} catch (MapperException exception) {
			exception.printStackTrace();
		}
	}

}
