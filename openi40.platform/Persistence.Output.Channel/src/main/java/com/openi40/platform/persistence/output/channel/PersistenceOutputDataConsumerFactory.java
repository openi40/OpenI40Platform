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
package com.openi40.platform.persistence.output.channel;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Stream;

import javax.sql.DataSource;

import com.openi40.scheduler.output.model.OutputDto;
import com.openi40.scheduler.outputchannels.streamoutputs.IExtendedConsumer;
import com.openi40.scheduler.outputchannels.streamoutputs.IOutputDataConsumerFactory;
import com.openi40.scheduler.outputchannels.streamoutputs.IOutputTransactionWrapper;
import com.openi40.scheduler.outputchannels.streamoutputs.OutputDataStreamException;

public class PersistenceOutputDataConsumerFactory implements IOutputDataConsumerFactory {
	String dataSourceName = null, dataSetName = null, dataSetVariant = null, dataSourceDescription = null;
	Map<Class<? extends OutputDto>, AbstractPersistentExtendedConsumerFactory> consumers = new HashMap<Class<? extends OutputDto>, AbstractPersistentExtendedConsumerFactory>();
	DataSource dataSource = null;

	public PersistenceOutputDataConsumerFactory(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	private <DtoEntityType extends OutputDto> AbstractPersistentExtendedConsumer<DtoEntityType> getConsumer(
			Class<DtoEntityType> requiredType, JDBCOutputTransactionWrapper transactionWrapper)
			throws OutputDataStreamException {
		AbstractPersistentExtendedConsumerFactory outConsumer = null;
		if (consumers.containsKey(requiredType)) {
			outConsumer = consumers.get(requiredType);
		} else {
			Set<Entry<Class<? extends OutputDto>, AbstractPersistentExtendedConsumerFactory>> set = consumers
					.entrySet();
			for (Entry<Class<? extends OutputDto>, AbstractPersistentExtendedConsumerFactory> entry : set) {
				if (requiredType.isAssignableFrom(entry.getKey())) {
					outConsumer = entry.getValue();
				}
			}
		}
		if (outConsumer == null) {
			throw new OutputDataStreamException("Cannot find IExtendedConsumer for " + requiredType.getName());
		}
		return outConsumer.create(transactionWrapper);
	}

	@Override
	public <DtoEntityType extends OutputDto> void consume(Stream<DtoEntityType> stream,
			Class<DtoEntityType> requiredType, IOutputTransactionWrapper outputTransactionWrapper)

			throws OutputDataStreamException {
		JDBCOutputTransactionWrapper tWrapper = (JDBCOutputTransactionWrapper) outputTransactionWrapper;
		IExtendedConsumer<DtoEntityType> consumer = getConsumer(requiredType, tWrapper);
		stream.forEach(consumer);
		consumer.endReached();

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

	@Override
	public IOutputTransactionWrapper createOutputTransaction() {
		try {
			JDBCOutputTransactionWrapper transactionWrapper = new JDBCOutputTransactionWrapper(
					dataSource.getConnection());
			return transactionWrapper;
		} catch (SQLException exc) {
			throw new RuntimeException("Exception in createOutputTransaction()", exc);
		}
	}

}
