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
package com.openi40.platform.config;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import com.openi40.platform.persistence.input.channel.AbstractPersistenceInputDataStream;
import com.openi40.platform.persistence.input.channel.PersistenceInputDataStreamFactory;
import com.openi40.platform.persistence.output.channel.AbstractPersistentExtendedConsumerFactory;
import com.openi40.platform.persistence.output.channel.PersistenceOutputDataConsumerFactory;
import com.openi40.scheduler.inputchannels.streaminputs.IInputDataStreamFactory;
import com.openi40.scheduler.outputchannels.streamoutputs.IOutputDataConsumerFactory;

@Service
public class PersistenceChannelsInitializer {
	PersistenceChannelsConfig config = null;
	List<AbstractPersistenceInputDataStream> persistenceStreams = null;
	List<AbstractPersistentExtendedConsumerFactory> extendedConsumers = null;
	DataSource dataSource=null;
	@Autowired
	public PersistenceChannelsInitializer(PersistenceChannelsConfig config,
			List<AbstractPersistenceInputDataStream> persistenceStreams,
			List<AbstractPersistentExtendedConsumerFactory> extendedConsumers,DataSource dataSource) {
		this.config = config;
		this.persistenceStreams = persistenceStreams;
		this.extendedConsumers = extendedConsumers;
		this.dataSource=dataSource;
	}

	@Bean
	@Qualifier("persistenceInputDataStreamFactories")
	public List<IInputDataStreamFactory> getPersistenceInputDataStreamFactories() {
		List<IInputDataStreamFactory> dsfactory = new ArrayList<>();
		if (this.config != null && !this.config.getConfigs().isEmpty()) {
			this.config.getConfigs().forEach(entry -> {
				PersistenceInputDataStreamFactory psf = new PersistenceInputDataStreamFactory();
				psf.setDataSourceName(entry.getDataSourceName());
				psf.setDataSetName(entry.getDataSetName());
				psf.setDataSetVariant(entry.getDataSetVariant());
				psf.setDataSourceDescription(entry.getDataSourceDescription());
				psf.setUseJpaStreaming(entry.useJpaStreaming);
				psf.setBatchingSize(entry.getBatchingSize());
				psf.setPersistenceStreams(this.persistenceStreams);
				psf.setRealtime(entry.realtime);
				psf.setProductionControlEnabled(entry.productionControlEnabled);
				dsfactory.add(psf);
			});
		}
		// return this.config != null ? new ArrayList<>(this.config.getConfigs()) : new
		// ArrayList<>();
		return dsfactory;
	}

	@Bean
	@Qualifier("persistenceOutputDataConsumerFactory")
	public List<IOutputDataConsumerFactory> getOutputDataConsumerFactories() {
		List<IOutputDataConsumerFactory> dsfactory = new ArrayList<>();
		if (this.config != null && !this.config.getConfigs().isEmpty()) {
			this.config.getConfigs().forEach(entry -> {

				PersistenceOutputDataConsumerFactory podcf = new PersistenceOutputDataConsumerFactory(dataSource); 
				podcf.setDataSetName(entry.getDataSetName());
				podcf.setDataSourceName(entry.getDataSourceName());
				podcf.setDataSetName(entry.getDataSetName());
				podcf.setDataSetVariant(entry.getDataSetVariant());
				podcf.setDataSourceDescription(entry.getDataSourceDescription());
				podcf.setExtendedConsumers(this.extendedConsumers);
				dsfactory.add(podcf);
			});
		}

		return dsfactory;
	}
}
