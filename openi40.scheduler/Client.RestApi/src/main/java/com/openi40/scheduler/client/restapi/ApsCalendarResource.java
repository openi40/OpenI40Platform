package com.openi40.scheduler.client.restapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.openi40.scheduler.apsdatacache.IApsDataCacheAggregator;
import com.openi40.scheduler.client.model.time.TimesheetDto;
import com.openi40.scheduler.client.restapi.genericmodel.AbstractApsCalendarResource;
import com.openi40.scheduler.engine.contextualplugarch.ILazyContextualBusinessLogicFactoryLoader;
import com.openi40.scheduler.mapper.IMapperFactory;
import com.openi40.scheduler.model.dao.IApsReservableObjectDao;

import io.swagger.annotations.Api;
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
@RestController
@RequestMapping("schedulerClient/ApsCalendar")
@Api
public class ApsCalendarResource extends AbstractApsCalendarResource<TimesheetDto> {
	
	public ApsCalendarResource(@Autowired IApsDataCacheAggregator apsDataCache,@Autowired IMapperFactory mapperFactory,@Autowired ILazyContextualBusinessLogicFactoryLoader lazyAutowire,@Autowired IApsReservableObjectDao apsReservableObjectDao) {
		super(TimesheetDto.class, apsDataCache, mapperFactory, lazyAutowire, apsReservableObjectDao);
		
	}

}
