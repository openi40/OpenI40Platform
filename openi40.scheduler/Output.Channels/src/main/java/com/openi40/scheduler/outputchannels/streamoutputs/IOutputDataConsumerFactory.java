package com.openi40.scheduler.outputchannels.streamoutputs;

import java.util.function.Consumer;
import java.util.stream.Stream;

import com.openi40.scheduler.output.model.OutputDto;
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
public interface IOutputDataConsumerFactory {
	String getDataSourceName();

	String getDataSetName();

	String getDataSetVariant();
	IOutputTransactionWrapper createOutputTransaction();
	<DtoEntityType extends OutputDto> void consume(Stream<DtoEntityType> stream,Class<DtoEntityType> requiredType, IOutputTransactionWrapper outputTransactionWrapper ) throws OutputDataStreamException;;
	String getDataSourceDescription();
}
