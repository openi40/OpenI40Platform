package com.openi40.ignite.datastreamfactories;

import java.util.Date;
import java.util.Iterator;
import java.util.stream.Stream;

import javax.cache.Cache.Entry;

import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteCache;
import org.apache.ignite.cache.CachePeekMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.openi40.ignite.config.HACachedApsDataSetConfig;
import com.openi40.scheduler.input.model.InputDto;
import com.openi40.scheduler.inputchannels.streaminputs.IInputDataStreamFactory;
import com.openi40.scheduler.inputchannels.streaminputs.InputDataStreamException;

public class IgniteInputDataStreamFactory implements IInputDataStreamFactory {
	HACachedApsDataSetConfig dataSetConfig = null;
	Ignite ignite = null;
	static Logger LOGGER = LoggerFactory.getLogger(IgniteInputDataStreamFactory.class);

	public IgniteInputDataStreamFactory(HACachedApsDataSetConfig dataSetConfig, Ignite ignite) {
		this.dataSetConfig = dataSetConfig;
		this.ignite = ignite;
	}

	@Override
	public String getDataSourceName() {

		return dataSetConfig.getDataSourceName();
	}

	@Override
	public String getDataSetName() {

		return dataSetConfig.getDataSetName();
	}

	@Override
	public String getDataSetVariant() {
		return dataSetConfig.getDataSetVariant();
	}

	@Override
	public <DtoEntityType extends InputDto> Stream<DtoEntityType> getStream(Class<DtoEntityType> requiredType)
			throws InputDataStreamException {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Begin getStream("+requiredType.getName()+")");
		}
		IgniteCache<String, DtoEntityType> cache = ignite.getOrCreateCache(requiredType.getName());
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Cache "+requiredType.getSimpleName()+" size="+cache.sizeLong(CachePeekMode.ALL));
		}
		Iterator<Entry<String, DtoEntityType>> iterator = cache.iterator();
		Stream<DtoEntityType> stream = null;
		try {
			stream = Stream.iterate(requiredType.newInstance(), (t) -> {
				return iterator.hasNext();
			}, (t) -> {
				return iterator.next().getValue();
			});
		} catch (InstantiationException | IllegalAccessException e) {
			throw new InputDataStreamException(
					"Problema streaming ignite data from " + requiredType.getSimpleName() + " cache", e);
		}
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("End getStream("+requiredType.getName()+")");
		}
		return stream;
	}

	@Override
	public <DtoEntityType extends InputDto> Stream<DtoEntityType> getStream(Class<DtoEntityType> requiredType,
			Date modifiedAfter) throws InputDataStreamException {

		return null;
	}

	@Override
	public String getDataSourceDescription() {

		return dataSetConfig.getDataSourceDescription();
	}

	@Override
	public boolean isRealtime() {

		return dataSetConfig.isRealtime();
	}

	@Override
	public boolean isProductionControlEnabled() {

		return dataSetConfig.isProductionControlEnabled();
	}

	@Override
	public void setRealtime(boolean rt) {
		dataSetConfig.setRealtime(rt);

	}

	@Override
	public void setProductionControlEnabled(boolean pce) {
		dataSetConfig.setProductionControlEnabled(pce);

	}

}
