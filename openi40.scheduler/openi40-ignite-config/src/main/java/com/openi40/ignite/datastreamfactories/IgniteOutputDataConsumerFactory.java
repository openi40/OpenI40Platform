package com.openi40.ignite.datastreamfactories;

import java.util.stream.Stream;

import org.apache.ignite.Ignite;
import org.apache.ignite.transactions.Transaction;
import org.apache.ignite.transactions.TransactionConcurrency;
import org.apache.ignite.transactions.TransactionIsolation;

import com.openi40.ignite.config.HACachedApsDataSetConfig;
import com.openi40.ignite.datastreamfactories.handlers.AbstractIgniteExtendedConsumer;
import com.openi40.ignite.datastreamfactories.handlers.IgniteExtendedConsumerHandlerFactory;
import com.openi40.ignite.datastreamfactories.handlers.IgniteExtenderConsumerException;
import com.openi40.scheduler.output.model.OutputDto;
import com.openi40.scheduler.outputchannels.streamoutputs.IOutputDataConsumerFactory;
import com.openi40.scheduler.outputchannels.streamoutputs.IOutputTransactionWrapper;
import com.openi40.scheduler.outputchannels.streamoutputs.OutputDataStreamException;

public class IgniteOutputDataConsumerFactory implements IOutputDataConsumerFactory {
	HACachedApsDataSetConfig config = null;
	IgniteExtendedConsumerHandlerFactory consumerFactory = null;
	Ignite ignite = null;

	class IgniteTransactionWrapper implements IOutputTransactionWrapper {
		Transaction transaction = null;

		@Override
		public void begin() {
			this.transaction = ignite.transactions().txStart(TransactionConcurrency.PESSIMISTIC,
					TransactionIsolation.SERIALIZABLE);

		}

		@Override
		public void commit() {
			this.transaction.commit();

		}

		@Override
		public void rollback() {
			this.transaction.rollback();

		}

		@Override
		public void close() {
			this.transaction.close();

		}

	}

	public IgniteOutputDataConsumerFactory(HACachedApsDataSetConfig config,
			IgniteExtendedConsumerHandlerFactory consumerFactory, Ignite ignite) {
		this.config = config;
		this.consumerFactory = consumerFactory;
		this.ignite = ignite;
	}

	@Override
	public String getDataSourceName() {

		return config.getDataSourceName();
	}

	@Override
	public String getDataSetName() {
		return config.getDataSetName();
	}

	@Override
	public String getDataSetVariant() {
		return config.getDataSetVariant();
	}

	@Override
	public <DtoEntityType extends OutputDto> void consume(Stream<DtoEntityType> stream,
			Class<DtoEntityType> requiredType, IOutputTransactionWrapper outputTransactionWrapper)
			throws OutputDataStreamException {
		try {
			AbstractIgniteExtendedConsumer<DtoEntityType> extendedConsumer = this.consumerFactory.create(requiredType,
					ignite);
			extendedConsumer.initialize();
			extendedConsumer.consume(stream);
			extendedConsumer.endReached();
		} catch (IgniteExtenderConsumerException e) {
			throw new OutputDataStreamException("Exception in getConsumer(..)", e);
		}

	}

	@Override
	public String getDataSourceDescription() {
		return config.getDataSourceDescription();
	}

	@Override
	public IOutputTransactionWrapper createOutputTransaction() {

		return new IgniteTransactionWrapper();
	}

}
