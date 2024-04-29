package com.openi40.scheduler.outputchannels.dataexporters;

import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openi40.scheduler.mapper.MapperException;
import com.openi40.scheduler.model.aps.ApsData;
import com.openi40.scheduler.model.dao.DataModelDaoException;
import com.openi40.scheduler.output.model.OutputDto;
import com.openi40.scheduler.outputchannels.streamoutputs.IExtendedConsumer;
import com.openi40.scheduler.outputchannels.streamoutputs.IOutputDataConsumerFactory;
import com.openi40.scheduler.outputchannels.streamoutputs.IOutputTransactionWrapper;
import com.openi40.scheduler.outputchannels.streamoutputs.OutputDataStreamException;
import com.openi40.scheduler.outputchannels.streamoutputs.OutputTransactionException;

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
@Service
public class DataExporterAgentImpl implements IDataExporterAgent {
	IExportedClassListProvider classListProvider = null;

	public DataExporterAgentImpl(@Autowired IExportedClassListProvider classListProvider) {
		this.classListProvider = classListProvider;
	}

	@Override
	public void doSync(ApsData context, IOutputDataConsumerFactory consumersFactory,
			IDataExporterFactoryRepository diFactoryRepository)
			throws DataModelDaoException, MapperException, OutputDataStreamException {
		IOutputTransactionWrapper transaction = null;
		try {
			transaction = consumersFactory.createOutputTransaction();
			transaction.begin();
			List<Class<? extends OutputDto>> classesList = classListProvider.getClassesList();
			for (Class<? extends OutputDto> exportedType : classesList) {

				IDataExporterFactory exporterFactory = diFactoryRepository.getExporterFactory(exportedType);
				Stream exporter = exporterFactory.create(context);
				consumersFactory.consume(exporter, exportedType, transaction);

			}
			transaction.commit();
		} catch (OutputTransactionException e) {
			throw new OutputDataStreamException("Exception managing transaction", e);
		} finally {
			try {
				transaction.close();
			} catch (Throwable th) {
			}
		}
	}

}
