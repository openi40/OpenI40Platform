package com.openi40.scheduler.inputchannels.streaminputs.handlers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Stream;

import com.openi40.scheduler.input.model.InputDto;
import com.openi40.scheduler.inputchannels.streaminputs.IInputDataStreamFactory;
import com.openi40.scheduler.inputchannels.streaminputs.InputDataStreamException;

import lombok.Data;
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
@Data
public class ApsInputMixerDataStreamFactory implements IInputDataStreamFactory {
	String dataSourceName = null, dataSetName = null, dataSetVariant = null, dataSourceDescription = null;
	List<IInputDataStreamFactory> mixedFactories = new ArrayList<>();

	public ApsInputMixerDataStreamFactory(List<IInputDataStreamFactory> mixedFactories) {
		this.mixedFactories = mixedFactories;
	}

	@Override
	public <DtoEntityType extends InputDto> Stream<DtoEntityType> getStream(Class<DtoEntityType> requiredType)
			throws InputDataStreamException {
		Stream<DtoEntityType> stream = new ArrayList<DtoEntityType>().stream();
		for (IInputDataStreamFactory iInputDataStreamFactory : mixedFactories) {
			Stream<DtoEntityType> _joinedStream = iInputDataStreamFactory.getStream(requiredType);
			if (_joinedStream != null) {
				stream = Stream.concat(stream, _joinedStream);
			}
		}
		return stream;
	}

	@Override
	public <DtoEntityType extends InputDto> Stream<DtoEntityType> getStream(Class<DtoEntityType> requiredType,
			Date modifiedAfter) throws InputDataStreamException {

		Stream<DtoEntityType> stream = new ArrayList<DtoEntityType>().stream();
		for (IInputDataStreamFactory iInputDataStreamFactory : mixedFactories) {
			Stream<DtoEntityType> _joinedStream = iInputDataStreamFactory.getStream(requiredType, modifiedAfter);
			if (_joinedStream != null) {
				stream = Stream.concat(stream, _joinedStream);
			}
		}
		return stream;
	}

}
