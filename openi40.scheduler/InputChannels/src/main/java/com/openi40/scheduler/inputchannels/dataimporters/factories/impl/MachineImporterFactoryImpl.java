package com.openi40.scheduler.inputchannels.dataimporters.factories.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openi40.scheduler.input.model.equipment.MachineInputDto;
import com.openi40.scheduler.inputchannels.dataimporters.AbstractDataImporterFactory;
import com.openi40.scheduler.inputchannels.dataimporters.factories.IMachineImporterFactory;
import com.openi40.scheduler.mapper.IMapperFactory;
import com.openi40.scheduler.model.dao.IMachineDao;
import com.openi40.scheduler.model.equipment.Machine;
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
public class MachineImporterFactoryImpl extends AbstractDataImporterFactory<MachineInputDto, Machine> implements IMachineImporterFactory{
	@Autowired
	public MachineImporterFactoryImpl(IMapperFactory mapperFactory, IMachineDao apsDataModelDao) {
		super(MachineInputDto.class, Machine.class, mapperFactory, apsDataModelDao);
	}

}
