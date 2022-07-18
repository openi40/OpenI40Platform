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
package com.openi40.platform.persistence.output.channel;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.openi40.scheduler.output.model.OutputDto;
import com.openi40.scheduler.outputchannels.streamoutputs.IExtendedConsumer;
import com.openi40.scheduler.outputchannels.streamoutputs.IExtendedConsumerFactory;
import com.openi40.scheduler.outputchannels.streamoutputs.IOutputDataConsumerFactory;
import com.openi40.scheduler.outputchannels.streamoutputs.OutputDataStreamException;

public class PersistenceOutputDataConsumerFactory implements IOutputDataConsumerFactory {
	String dataSourceName = null, dataSetName = null, dataSetVariant = null, dataSourceDescription = null;
	Map<Class<? extends OutputDto>, IExtendedConsumerFactory> consumers = new HashMap<Class<? extends OutputDto>, IExtendedConsumerFactory>();

	@Override
	public <DtoEntityType extends OutputDto> IExtendedConsumer<DtoEntityType> getConsumer(
			Class<DtoEntityType> requiredType) throws OutputDataStreamException {
		IExtendedConsumerFactory outConsumer = null;
		if (consumers.containsKey(requiredType)) {
			outConsumer = consumers.get(requiredType);
		} else {
			Set<Entry<Class<? extends OutputDto>, IExtendedConsumerFactory>> set = consumers.entrySet();
			for (Entry<Class<? extends OutputDto>, IExtendedConsumerFactory> entry : set) {
				if (requiredType.isAssignableFrom(entry.getKey())) {
					outConsumer = entry.getValue();
				}
			}
		}
		if (outConsumer == null) {
			throw new OutputDataStreamException("Cannot find IExtendedConsumer for " + requiredType.getName());
		}
		return outConsumer.create();
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

	public void setExtendedConsumers(List<AbstractPersistentExtendedConsumerFactory> extendedConsumers) {
		for (AbstractPersistentExtendedConsumerFactory ec : extendedConsumers) {
			this.consumers.put(ec.getManagedType(), ec);
		}
	}

}
