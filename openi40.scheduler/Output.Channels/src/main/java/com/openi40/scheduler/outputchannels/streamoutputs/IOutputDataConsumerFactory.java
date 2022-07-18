package com.openi40.scheduler.outputchannels.streamoutputs;

import java.util.function.Consumer;

import com.openi40.scheduler.output.model.OutputDto;
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
public interface IOutputDataConsumerFactory {
	String getDataSourceName();

	String getDataSetName();

	String getDataSetVariant();

	<DtoEntityType extends OutputDto> IExtendedConsumer<DtoEntityType> getConsumer(Class<DtoEntityType> requiredType)
			throws OutputDataStreamException;
	
	String getDataSourceDescription();
}
