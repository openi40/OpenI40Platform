package com.openi40.dbmodel.repositories;

import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.openi40.dbmodel.entities.OI40DBScheduledWorkOrder;
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
@Repository
@RestController
@RequestMapping(path = "/integration/OI40DBScheduledWorkOrder")
public interface OI40DBScheduledWorkOrderRepository
		extends OI40BaseRepository<OI40DBScheduledWorkOrder> {
}