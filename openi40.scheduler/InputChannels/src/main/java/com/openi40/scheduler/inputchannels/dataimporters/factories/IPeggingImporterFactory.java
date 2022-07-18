package com.openi40.scheduler.inputchannels.dataimporters.factories;

import com.openi40.scheduler.input.model.companystructure.PlantInputDto;
import com.openi40.scheduler.input.model.orders.PeggingInputDto;
import com.openi40.scheduler.inputchannels.dataimporters.IDataImporterFactory;
import com.openi40.scheduler.model.companystructure.Plant;
import com.openi40.scheduler.model.orders.Pegging;
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
public interface IPeggingImporterFactory extends IDataImporterFactory<PeggingInputDto, Pegging> {

}
