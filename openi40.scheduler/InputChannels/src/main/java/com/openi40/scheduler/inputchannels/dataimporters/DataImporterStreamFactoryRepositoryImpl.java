package com.openi40.scheduler.inputchannels.dataimporters;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openi40.scheduler.inputchannels.streaminputs.IInputDataStreamFactory;
import com.openi40.scheduler.inputchannels.streaminputs.IInputDataStreamFactoryRepository;
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
@Service
public class DataImporterStreamFactoryRepositoryImpl implements IInputDataStreamFactoryRepository {
	@Autowired(required = false)
	List<IInputDataStreamFactory> streamFactories;

	public DataImporterStreamFactoryRepositoryImpl() {

	}

	@Override
	public List<IInputDataStreamFactory> getDataImporterStreamFactories() {
		if (streamFactories == null)
			streamFactories = new ArrayList<IInputDataStreamFactory>();
		return streamFactories;
	}

}
