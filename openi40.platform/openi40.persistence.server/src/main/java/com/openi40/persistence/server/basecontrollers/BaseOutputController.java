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

import java.util.List;

import javax.validation.constraints.NotNull;

import org.apache.commons.logging.LogFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.openi40.scheduler.engine.OpenI40Exception;
import com.openi40.scheduler.inputchannels.streaminputs.IInputDataStreamFactory;
import com.openi40.scheduler.output.model.OutputDto;
import com.openi40.scheduler.outputchannels.streamoutputs.IExtendedConsumer;
import com.openi40.scheduler.outputchannels.streamoutputs.IOutputDataConsumerFactory;
import com.openi40.scheduler.outputchannels.streamoutputs.OutputDataStreamException;

public abstract class BaseOutputController<OutputType extends OutputDto> {
	Class<OutputType> outputType = null;
	List<IOutputDataConsumerFactory> outputDataConsumerFactory = null;
	Logger LOGGER = LoggerFactory.getLogger(this.getClass());

	public BaseOutputController(Class<OutputType> outputType,
			@Qualifier("persistenceOutputDataConsumerFactory") List<IOutputDataConsumerFactory> outputDataConsumerFactories) {
		this.outputType = outputType;
		this.outputDataConsumerFactory = outputDataConsumerFactories;

	}

	private IOutputDataConsumerFactory getFactory(String dataSourceName, String dataSetName, String dataSetVariant) {
		IOutputDataConsumerFactory factory = null;
		for (IOutputDataConsumerFactory f : this.outputDataConsumerFactory) {
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

	@PostMapping("vectorialSave/{dataSourceName}/{dataSetName}/{dataSetVariant}/")
	public void vectorialSave(@NotNull @PathVariable("dataSourceName") String dataSourceName,
			@NotNull @PathVariable("dataSetName") String dataSetName,
			@NotNull @PathVariable("dataSetVariant") String dataSetVariant, @RequestBody List<OutputType> data)
			throws OutputDataStreamException {
		IOutputDataConsumerFactory factory = getFactory(dataSourceName, dataSetName, dataSetVariant);
		factory.<OutputType>consume(data.stream(), outputType);

	}

}
