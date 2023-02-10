package com.openi40.platform.iomessages.spooler.services;

import javax.inject.Singleton;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.openi40.platform.iomessages.spooler.config.MSGElaborationConfig;

@Singleton
@Service
public class SpoolerProcessingTickWrapperService {
	static Logger LOGGER = LoggerFactory.getLogger(SpoolerProcessingTickWrapperService.class);
	@Autowired(required = false)
	IMSGSpoolingProcessorService spoolingProcessor;
	@Autowired
	IMSGSpoolingService spoolingService;
	@Autowired
	MSGElaborationConfig elaborationConfig;

	public SpoolerProcessingTickWrapperService() {

	}

	public synchronized void runSpoolingConsumer(String dataSourceName, String dataSourceSet,
			String dataSourceVariant) {
		if (elaborationConfig.isEnableMessagesElaboration() && this.spoolingProcessor != null) {

			LOGGER.info("Begin Running spoolingConsumer processing infos");
			this.spoolingService.evaluateMessages(dataSourceName, dataSourceSet, dataSourceVariant, spoolingProcessor);

			LOGGER.info("End Running spoolingConsumer processing infos");

		}
	}

	@Scheduled(initialDelay = 20000, fixedRate = 10000)
	public void schedulingTick() {
		if (elaborationConfig.isEnableMessagesElaboration()) {
			this.runSpoolingConsumer(elaborationConfig.getDataSourceName(), elaborationConfig.getDataSetName(),
					elaborationConfig.getDataSetVariant());
		}
	}

}
