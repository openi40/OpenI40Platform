package com.openi40.scheduler.client.restapi.genericmodel;

import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.openi40.scheduler.apsdatacache.ApsDataCacheException;
import com.openi40.scheduler.apsdatacache.IApsDataCache;
import com.openi40.scheduler.apsdatacache.IApsDataCacheAggregator;
import com.openi40.scheduler.engine.contextualplugarch.ILazyContextualBusinessLogicFactoryLoader;
import com.openi40.scheduler.mapper.DefaultEntitiesFactory;
import com.openi40.scheduler.mapper.IMapper;
import com.openi40.scheduler.mapper.IMapperFactory;
import com.openi40.scheduler.model.ITimesheetAllocableObject;
import com.openi40.scheduler.model.aps.ApsData;
import com.openi40.scheduler.model.dao.DataModelDaoException;
import com.openi40.scheduler.model.dao.IApsReservableObjectDao;
import com.openi40.scheduler.model.time.Timesheet;
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
public class AbstractApsCalendarResource<CalendarRappresentationType> extends AbstractBaseResource {
	static Logger LOGGER = LoggerFactory.getLogger(AbstractApsCalendarResource.class);
	protected Class<CalendarRappresentationType> calendarType = null;
	protected IApsReservableObjectDao apsReservableObjectDao = null;

	protected AbstractApsCalendarResource(Class<CalendarRappresentationType> calendarType,
			IApsDataCacheAggregator apsDataCacheAggregator, IMapperFactory mapperFactory,
			ILazyContextualBusinessLogicFactoryLoader lazyAutowire, IApsReservableObjectDao apsReservableObjectDao) {
		super(apsDataCacheAggregator, mapperFactory, lazyAutowire);
		this.calendarType = calendarType;
		this.apsReservableObjectDao = apsReservableObjectDao;
	}

	@GetMapping(value = "calendar/{dataSourceName}/{dataSetId}/{variantId}/{objectId}/")
	public ResponseEntity<CalendarRappresentationType> getCalendar(
			@PathVariable("dataSourceName") String dataSourceName, @PathVariable("dataSetId") String dataSetId,
			@PathVariable("variantId") String variantId, @PathVariable("objectId") String objectId) throws Exception {
		try {
			IApsDataCache datacache = apsDataCacheAggregator.getDataCache(dataSourceName);
			ApsData data = datacache.getCachedData(dataSetId, variantId);
			Timesheet timesheet = null;
			ITimesheetAllocableObject retValue = null;
			if (objectId != null && data.getId().equals(objectId) || objectId == null
					|| objectId.trim().length() == 0) {
				retValue = data;
			} else if (objectId != null) {
				retValue = apsReservableObjectDao.findById(objectId, data);
			}
			if (retValue != null) {
				timesheet = retValue.getTimesheet();
				if (timesheet == null) {
					LOGGER.error("Calendar not initialized for objectId=" + objectId + " in dataset/variant="
							+ dataSetId + "/" + variantId);
				}
			} else {
				LOGGER.error(
						"Object not found objectId=" + objectId + " in dataset/variant=" + dataSetId + "/" + variantId);
			}
			if (timesheet != null) {
				IMapper<ApsData, CalendarRappresentationType> mapper = this.mapperFactory.createMapper(ApsData.class,
						this.calendarType);
				CalendarRappresentationType mapped = mapper.mapInstance(data, this.calendarType,
						DefaultEntitiesFactory.Instance, new HashMap<>(), true);
				return ResponseEntity.ok().body(mapped);
			} else {
				return ResponseEntity.notFound().build();
			}
		} catch (ApsDataCacheException | DataModelDaoException e) {
			LOGGER.error("Unable to found cached data objectId=" + objectId + " in dataset/variant=" + dataSetId + "/"
					+ variantId, e);
			throw e;
		}

	}
}
