package com.openi40.scheduler.inputchannels.streaminputs.handlers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Stream;

import com.openi40.scheduler.input.model.InputDto;
import com.openi40.scheduler.inputchannels.streaminputs.IInputDataStreamFactory;
import com.openi40.scheduler.inputchannels.streaminputs.InputDataStreamException;

/**
 * 
 * This code is part of the OpenI40 open source advanced production scheduler
 * platform suite, have look to its licencing options. Web site:
 * http://openi40.org/ Github: https://github.com/openi40/OpenI40Platform We
 * hope you enjoy implementing new amazing projects with it.
 * 
 * @author Paolo Zavalloni  architectures@openi40.org
 *
 */

public class ApsInputMixerDataStreamFactory implements IInputDataStreamFactory {
	protected String dataSourceName = null, dataSetName = null, dataSetVariant = null, dataSourceDescription = null;
	protected boolean canBeCached = true;
	protected List<IInputDataStreamFactory> mixedFactories = new ArrayList<>();
	protected boolean realtime = false;
	protected boolean productionControlEnabled = false;

	public ApsInputMixerDataStreamFactory(List<IInputDataStreamFactory> mixedFactories) {
		this.mixedFactories = mixedFactories;
	}

	@Override
	public <DtoEntityType extends InputDto> Stream<DtoEntityType> getStream(Class<DtoEntityType> requiredType)
			throws InputDataStreamException {
		Stream<DtoEntityType> stream[] = new Stream[mixedFactories.size()];
		int idx = 0;
		for (IInputDataStreamFactory iInputDataStreamFactory : mixedFactories) {
			Stream<DtoEntityType> _joinedStream = iInputDataStreamFactory.getStream(requiredType);
			stream[idx] = _joinedStream;
			idx++;
		}
		return joinStreams(stream);
	}

	private <DtoEntityType extends InputDto> Stream<DtoEntityType> joinStreams(Stream<DtoEntityType>... streams) {
		if (streams == null)
			return Stream.of();
		if (streams.length == 1) {
			return streams[0];
		}
		if (streams.length == 2) {
			return Stream.concat(streams[0], streams[1]);
		} else {
			Stream<DtoEntityType>[] nextLevelArray = new Stream[streams.length - 1];
			for (int i = 1; i < streams.length; i++) {
				nextLevelArray[i - 1] = streams[i];
			}
			return Stream.concat(streams[0], joinStreams(nextLevelArray));
		}
	}

	@Override
	public <DtoEntityType extends InputDto> Stream<DtoEntityType> getStream(Class<DtoEntityType> requiredType,
			Date modifiedAfter) throws InputDataStreamException {
		Stream<DtoEntityType> stream[] = new Stream[mixedFactories.size()];
		int idx = 0;
		for (IInputDataStreamFactory iInputDataStreamFactory : mixedFactories) {
			Stream<DtoEntityType> _joinedStream = iInputDataStreamFactory.getStream(requiredType, modifiedAfter);
			stream[idx] = _joinedStream;
			idx++;
		}
		return joinStreams(stream);

	}

	public String getDataSourceName() {
		return dataSourceName;
	}

	public void setDataSourceName(String dataSourceName) {
		this.dataSourceName = dataSourceName;
	}

	public String getDataSetName() {
		return dataSetName;
	}

	public void setDataSetName(String dataSetName) {
		this.dataSetName = dataSetName;
	}

	public String getDataSetVariant() {
		return dataSetVariant;
	}

	public void setDataSetVariant(String dataSetVariant) {
		this.dataSetVariant = dataSetVariant;
	}

	public String getDataSourceDescription() {
		return dataSourceDescription;
	}

	public void setDataSourceDescription(String dataSourceDescription) {
		this.dataSourceDescription = dataSourceDescription;
	}

	public boolean isCanBeCached() {
		return canBeCached;
	}

	public void setCanBeCached(boolean canBeCached) {
		this.canBeCached = canBeCached;
	}

	public List<IInputDataStreamFactory> getMixedFactories() {
		return mixedFactories;
	}

	public void setMixedFactories(List<IInputDataStreamFactory> mixedFactories) {
		this.mixedFactories = mixedFactories;
	}

	public boolean isRealtime() {
		return realtime;
	}

	public void setRealtime(boolean realtime) {
		this.realtime = realtime;
	}

	public boolean isProductionControlEnabled() {
		return productionControlEnabled;
	}

	public void setProductionControlEnabled(boolean productionControlEnabled) {
		this.productionControlEnabled = productionControlEnabled;
	}

}
