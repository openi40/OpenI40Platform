package com.openi40.scheduler.outputchannels.streamoutputs.handlers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import com.openi40.scheduler.output.model.OutputDto;
import com.openi40.scheduler.outputchannels.streamoutputs.IExtendedConsumer;
import com.openi40.scheduler.outputchannels.streamoutputs.IOutputDataConsumerFactory;
import com.openi40.scheduler.outputchannels.streamoutputs.IOutputTransactionWrapper;
import com.openi40.scheduler.outputchannels.streamoutputs.OutputDataStreamException;
import com.openi40.scheduler.outputchannels.streamoutputs.config.AbstractOutputDataConsumerFactoryConfig;
import com.openi40.scheduler.outputchannels.streamoutputs.config.EntityOutputSetting;

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

public abstract class AbstractConfigurableOutputDataConsumerFactory<OutputEntryType extends EntityOutputSetting, CType extends AbstractOutputDataConsumerFactoryConfig<OutputEntryType>>
		implements IOutputDataConsumerFactory {
	protected String dataSourceName = null, dataSetName = null, dataSetVariant = null, dataSourceDescription = null;
	

	protected CType config = null;

	public AbstractConfigurableOutputDataConsumerFactory(CType cfg) {
		this.config = cfg;
		this.dataSourceName = config.getDataSourceName();
		this.dataSetName = config.getDataSetName();
		this.dataSetVariant = config.getDataSetVariant();
		this.dataSourceDescription = config.getDescription();
	}

	@Override
	public String getDataSourceName() {

		return dataSourceName;
	}

	@Override
	public String getDataSetName() {

		return dataSetName;
	}

	@Override
	public String getDataSetVariant() {

		return dataSetVariant;
	}

	protected class ExtendedConsumer<DtoEntityType extends OutputDto> implements IExtendedConsumer<DtoEntityType> {
		List<DtoEntityType> group = new ArrayList<>();
		OutputEntryType entryCfg = null;

		ExtendedConsumer(OutputEntryType cfg) {
			entryCfg = cfg;
		}

		@Override
		public void accept(DtoEntityType t) {
			int nEntriesGrouping = entryCfg.getNEntriesGrouping() != 0 ? entryCfg.getNEntriesGrouping()
					: config.getNEntriesGrouping();
			if (nEntriesGrouping == 1) {
				send(config, entryCfg, t);
			} else {
				group.add(t);
				if (nEntriesGrouping > 0 && group.size() >= nEntriesGrouping) {
					flush();
				}
			}

		}

		@Override
		public void endReached() {
			flush();
		}

		private void flush() {
			send(config, entryCfg, group);
			group.clear();
		}
	};

	private <DtoEntityType extends OutputDto> IExtendedConsumer<DtoEntityType> getConsumer(
			Class<DtoEntityType> requiredType) throws OutputDataStreamException {
		OutputEntryType configEntry = getOrProduceConfigurationEntry(requiredType);
		return new ExtendedConsumer<DtoEntityType>(configEntry);
	}

	@Override
	public <DtoEntityType extends OutputDto> void consume(Stream<DtoEntityType> stream,
			Class<DtoEntityType> requiredType, IOutputTransactionWrapper outputTransactionWrapper) throws OutputDataStreamException {
		IExtendedConsumer<DtoEntityType> consumer = getConsumer(requiredType);
		stream.forEach(consumer);
		consumer.endReached();

	}

	public abstract <DtoEntityType extends OutputDto> void send(CType config, OutputEntryType cfgItem,
			List<DtoEntityType> list);

	public abstract <DtoEntityType extends OutputDto> void send(CType config, OutputEntryType cfgItem,
			DtoEntityType entry);

	@Override
	public String getDataSourceDescription() {

		return dataSourceDescription;
	}

	protected OutputEntryType getOrProduceConfigurationEntry(Class<? extends OutputDto> requiredType)
			throws OutputDataStreamException {
		List<OutputEntryType> found = new ArrayList<OutputEntryType>();
		config.getEntitiesSetting().forEach((x) -> {
			if (x.getEntityName() != null && x.getEntityName().equalsIgnoreCase(requiredType.getSimpleName())) {
				found.add(x);
			}
		});
		if (!found.isEmpty()) {
			return found.get(0);
		}
		if (config.isCreateURIByEntityName()) {
			String _template = config.getRelativePathTemplate();
			String _relativeString = _template != null ? _template.replace("${entity}", requiredType.getSimpleName())
					: requiredType.getSimpleName();

			return createTemplatedEntry(requiredType, _relativeString);
		}
		throw new OutputDataStreamException("no configuration for class: " + requiredType.getSimpleName());
	}

	protected abstract OutputEntryType createTemplatedEntry(Class<? extends OutputDto> requiredType,
			String relativeString) throws OutputDataStreamException;
	@Override
	public IOutputTransactionWrapper createOutputTransaction() {
		
		return new IOutputTransactionWrapper() {
			
			@Override
			public void rollback() {
				
				
			}
			
			@Override
			public void commit() {
				
				
			}
			
			@Override
			public void close() {
				
				
			}
			
			@Override
			public void begin() {
				
				
			}
		};
	}

	public CType getConfig() {
		return config;
	}

	public void setConfig(CType config) {
		this.config = config;
	}

	public void setDataSourceName(String dataSourceName) {
		this.dataSourceName = dataSourceName;
	}

	public void setDataSetName(String dataSetName) {
		this.dataSetName = dataSetName;
	}

	public void setDataSetVariant(String dataSetVariant) {
		this.dataSetVariant = dataSetVariant;
	}

	public void setDataSourceDescription(String dataSourceDescription) {
		this.dataSourceDescription = dataSourceDescription;
	}

}
