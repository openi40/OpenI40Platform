package com.openi40.scheduler.outputchannels.dataexporters.factories;

import com.openi40.scheduler.model.aps.ApsSchedulingSet;
import com.openi40.scheduler.model.orders.Pegging;
import com.openi40.scheduler.model.orders.WorkOrder;
import com.openi40.scheduler.output.model.ApsSchedulingSetOutputDto;
import com.openi40.scheduler.output.model.orders.PeggingOutputDto;
import com.openi40.scheduler.output.model.orders.WorkOrderOutputDto;
import com.openi40.scheduler.outputchannels.dataexporters.IDataExporterFactory;
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
public interface IApsSchedulingSetOutputDataExporterFactory extends IDataExporterFactory<ApsSchedulingSet, ApsSchedulingSetOutputDto> {

}
