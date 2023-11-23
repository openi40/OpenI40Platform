package com.openi40.ignite.datastreamfactories;

import java.util.Date;
import java.util.Iterator;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import javax.cache.Cache.Entry;

import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteCache;
import org.apache.ignite.cache.CachePeekMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanFactory;

import com.openi40.ignite.config.HACachedApsDataSetConfig;
import com.openi40.scheduler.input.model.InputDto;
import com.openi40.scheduler.inputchannels.streaminputs.IInputDataStreamFactory;
import com.openi40.scheduler.inputchannels.streaminputs.InputDataStreamException;

public class IgniteInputDataStreamFactory implements IInputDataStreamFactory {
	HACachedApsDataSetConfig dataSetConfig = null;

	static Logger LOGGER = LoggerFactory.getLogger(IgniteInputDataStreamFactory.class);
	BeanFactory beanFactory;

	public IgniteInputDataStreamFactory(HACachedApsDataSetConfig dataSetConfig, BeanFactory beanFactory) {
		this.dataSetConfig = dataSetConfig;

		this.beanFactory = beanFactory;
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
		Stream<DtoEntityType> stream = null;
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Begin getStreamInternal(" + requiredType.getName() + ")");
		}
		Ignite ignite = beanFactory.getBean(Ignite.class);
		if (ignite == null)
			throw new InputDataStreamException("Ignite engine is not yet injectable");
		IgniteCache<String, DtoEntityType> cache = ignite
				.<String, DtoEntityType>getOrCreateCache(requiredType.getName());
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Cache " + requiredType.getSimpleName() + " size=" + cache.sizeLong(CachePeekMode.ALL));
		}

		final Iterator<Entry<String, DtoEntityType>> iterator = cache.<String, DtoEntityType>iterator();
		Iterator<DtoEntityType> elementsIterator = new Iterator<DtoEntityType>() {
			@Override
			public boolean hasNext() {

				return iterator.hasNext();
			}

			@Override
			public DtoEntityType next() {

				return iterator.next().getValue();
			}
		};
		Spliterator<DtoEntityType> spliterator = Spliterators.spliteratorUnknownSize(elementsIterator, 0);
		
		/*
		 * if (iterator.hasNext()) {
		 * 
		 * DtoEntityType firstValue = iterator.next().getValue();
		 * 
		 * stream = Stream.iterate(firstValue, (t) -> { boolean hasNext =
		 * iterator.hasNext(); return hasNext; }, (t) -> { DtoEntityType entry =
		 * iterator.next().getValue(); return entry; }).sequential(); } else return
		 * Stream.empty();
		 */
		stream = StreamSupport.stream(spliterator, false);

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("End getStreamInternal(" + requiredType.getName() + ")");
		}
		return stream;
	}
	/*
	 * @Override public <DtoEntityType extends InputDto> Stream<DtoEntityType>
	 * getStream(Class<DtoEntityType> requiredType) throws InputDataStreamException
	 * { if (LOGGER.isDebugEnabled()) { LOGGER.debug("Begin getStream(" +
	 * requiredType.getName() + ")"); } IgniteCache<String, BinaryObject> cache =
	 * ignite.<String, BinaryObject>getOrCreateCache(requiredType.getName())
	 * .withKeepBinary(); if (LOGGER.isDebugEnabled()) { LOGGER.debug("Cache " +
	 * requiredType.getSimpleName() + " size=" + cache.sizeLong(CachePeekMode.ALL));
	 * } Iterator<Entry<String, BinaryObject>> iterator = cache.iterator();
	 * Stream<DtoEntityType> stream = null; IMapper<BinaryObjectBuilder,
	 * DtoEntityType> mapper; try { mapper =
	 * mapperFactory.createMapper(BinaryObjectBuilder.class, requiredType); try {
	 * stream = Stream.iterate(requiredType.newInstance(), (t) -> { return
	 * iterator.hasNext(); }, (t) -> { BinaryObject object =
	 * iterator.next().getValue(); BinaryObjectBuilder builder = object.toBuilder();
	 * try { return mapper.mapInstance(builder, requiredType,
	 * DefaultEntitiesFactory.Instance, new HashMap<>(), true); } catch
	 * (MapperException e) { throw new RuntimeException("", e); } }); } catch
	 * (InstantiationException | IllegalAccessException e) { throw new
	 * InputDataStreamException( "Problema streaming ignite data from " +
	 * requiredType.getSimpleName() + " cache", e); } } catch (MapperException e) {
	 * throw new InputDataStreamException( "Problema streaming ignite data from " +
	 * requiredType.getSimpleName() + " cache", e); }
	 * 
	 * if (LOGGER.isDebugEnabled()) { LOGGER.debug("End getStream(" +
	 * requiredType.getName() + ")"); } return stream; }
	 */

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
