package com.openi40.scheduler.inputchannels.streaminputs;

import java.util.Date;
import java.util.stream.Stream;

import com.openi40.scheduler.input.model.InputDto;

/**
 * 
 * This code is part of the OpenI40 open source advanced production scheduler
 * platform suite, have look to its licencing options. Web site:
 * http://openi40.org/ Github: https://github.com/openi40/OpenI40Platform We
 * hope you enjoy implementing new amazing projects with it.
 * 
 * @author architectures@openi40.org
 *
 */
public interface IInputDataStreamFactory {
	String getDataSourceName();

	String getDataSetName();

	String getDataSetVariant();
	boolean isCanBeCached();

	<DtoEntityType extends InputDto> Stream<DtoEntityType> getStream(Class<DtoEntityType> requiredType)
			throws InputDataStreamException;

	<DtoEntityType extends InputDto> Stream<DtoEntityType> getStream(Class<DtoEntityType> requiredType,
			Date modifiedAfter) throws InputDataStreamException;

	String getDataSourceDescription();

	boolean isRealtime();

	boolean isProductionControlEnabled();

	void setRealtime(boolean rt);

	void setProductionControlEnabled(boolean pce);
}
