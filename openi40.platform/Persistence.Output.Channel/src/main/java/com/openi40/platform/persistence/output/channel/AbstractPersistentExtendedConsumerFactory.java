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

import org.springframework.jdbc.core.JdbcTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.openi40.scheduler.output.model.OutputDto;
import com.openi40.scheduler.outputchannels.streamoutputs.IExtendedConsumer;
import com.openi40.scheduler.outputchannels.streamoutputs.IExtendedConsumerFactory;

public abstract class AbstractPersistentExtendedConsumerFactory<OutType extends OutputDto>
		implements IExtendedConsumerFactory<OutType> {
	protected Class<OutType> managedType = null;
	protected JdbcTemplate jdbcTemplate = null;
	protected ObjectMapper mapper = null;

	public AbstractPersistentExtendedConsumerFactory(ObjectMapper mapper, Class<OutType> type,
			JdbcTemplate jdbcTemplate) {
		this.managedType = type;
		this.jdbcTemplate = jdbcTemplate;
		this.mapper = mapper;
	}

	@Override
	public Class<OutType> getManagedType() {

		return managedType;
	}

}
