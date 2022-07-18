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
package com.openi40.persistence.server.basecontrollers;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.openi40.scheduler.engine.OpenI40Exception;
import com.openi40.scheduler.input.model.InputDto;
import com.openi40.scheduler.inputchannels.streaminputs.IInputDataStreamFactory;
import com.openi40.scheduler.inputchannels.streaminputs.InputDataStreamException;

import reactor.core.publisher.Flux;

public abstract class BaseInputController<InputType extends InputDto> {

	Class<InputType> inputType = null;
	List<IInputDataStreamFactory> persistenceInputDataStreamFactories = null;

	public BaseInputController(Class<InputType> inputType,
			@Qualifier("persistenceInputDataStreamFactories") List<IInputDataStreamFactory> persistenceInputDataStreamFactories) {
		this.persistenceInputDataStreamFactories = persistenceInputDataStreamFactories;
		this.inputType = inputType;
	}

	private IInputDataStreamFactory getFactory(String dataSourceName, String dataSetName, String dataSetVariant) {
		IInputDataStreamFactory factory = null;
		for (IInputDataStreamFactory f : this.persistenceInputDataStreamFactories) {
			if (f.getDataSetName().equals(dataSetName) && f.getDataSourceName().equals(dataSourceName)
					&& f.getDataSetVariant().equals(dataSetVariant)) {
				factory = f;
			}

		}
		if (factory == null)
			throw new OpenI40Exception(
					"Not found data source=>" + dataSourceName + "/" + dataSetName + "/" + dataSetVariant);
		return factory;
	}

	@GetMapping(path = "stream/{dataSourceName}/{dataSetName}/{dataSetVariant}/", produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
	public Flux<InputType> getFlux(@NotNull @PathVariable("dataSourceName") String dataSourceName,
			@NotNull @PathVariable("dataSetName") String dataSetName,
			@NotNull @PathVariable("dataSetVariant") String dataSetVariant) {
		Stream<InputType> stream;
		try {
			stream = getFactory(dataSourceName, dataSetName, dataSetVariant).getStream(this.inputType);
			return Flux.fromStream(stream);
		} catch (InputDataStreamException e) {
			throw new OpenI40Exception("Cannot stream object of type " + inputType.getName(), e);
		}

	}

	@GetMapping(path = "stream/{dataSourceName}/{dataSetName}/{dataSetVariant}/{timestamp}/", produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
	public Flux<InputType> getFluxByTimestamp(@NotNull @PathVariable("dataSourceName") String dataSourceName,
			@NotNull @PathVariable("dataSetName") String dataSetName,
			@NotNull @PathVariable("dataSetVariant") String dataSetVariant,
			@NotNull @PathVariable("timestamp") Timestamp timestamp) {
		Stream<InputType> stream;
		try {
			stream = getFactory(dataSourceName, dataSetName, dataSetVariant).getStream(this.inputType, timestamp);
			return Flux.fromStream(stream);
		} catch (InputDataStreamException e) {
			throw new OpenI40Exception("Cannot stream object of type " + inputType.getName(), e);
		}

	}

	@GetMapping(path = "dataVector/{dataSourceName}/{dataSetName}/{dataSetVariant}/", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<InputType> dataVector(@NotNull @PathVariable("dataSourceName") String dataSourceName,
			@NotNull @PathVariable("dataSetName") String dataSetName,
			@NotNull @PathVariable("dataSetVariant") String dataSetVariant) {
		Stream<InputType> stream;
		try {
			stream = getFactory(dataSourceName, dataSetName, dataSetVariant).getStream(this.inputType);
			final List<InputType> outvector = new ArrayList<>();
			stream.forEach((inputObject) -> {
				outvector.add(inputObject);
			});
			return outvector;
		} catch (InputDataStreamException e) {
			throw new OpenI40Exception("Cannot stream object of type " + inputType.getName(), e);
		}
	}

	@GetMapping(path = "dataVector/{dataSourceName}/{dataSetName}/{dataSetVariant}/{timestamp}/", produces = MediaType.APPLICATION_JSON_VALUE)	
	public List<InputType> dataVectorByTimestamp(@NotNull @PathVariable("dataSourceName") String dataSourceName,
			@NotNull @PathVariable("dataSetName") String dataSetName,
			@NotNull @PathVariable("dataSetVariant") String dataSetVariant,
			@NotNull @PathVariable("timestamp") Timestamp timestamp) {
		Stream<InputType> stream;
		try {
			stream = getFactory(dataSourceName, dataSetName, dataSetVariant).getStream(this.inputType, timestamp);
			final List<InputType> outvector = new ArrayList<>();
			stream.forEach((inputObject) -> {
				outvector.add(inputObject);
			});
			return outvector;
		} catch (InputDataStreamException e) {
			throw new OpenI40Exception("Cannot stream object of type " + inputType.getName(), e);
		}
	}
	
}
