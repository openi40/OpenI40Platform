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

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.openi40.scheduler.output.model.OutputDto;
import com.openi40.scheduler.outputchannels.streamoutputs.IExtendedConsumer;

public abstract class AbstractPersistentExtendedConsumer<T extends OutputDto> implements IExtendedConsumer<T> {
	Logger LOGGER = LoggerFactory.getLogger(this.getClass());
	Class<T> managedType = null;
	protected int saveClusterSize = 1000;
	protected boolean initialized = false;
	protected ObjectMapper mapper;
	protected JdbcTemplate jdbcTemplate=null;
	public AbstractPersistentExtendedConsumer(Class<T> managedType,ObjectMapper mapper,JdbcTemplate jdbcTemplate) {
		this.managedType = managedType;
		this.mapper=mapper;
		this.jdbcTemplate=jdbcTemplate;
	}

	List<T> buffer = new ArrayList<>();

	@Override
	public synchronized void accept(T t) {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Begin accept(code='" + t.getCode() + "')");
			try {
				LOGGER.debug("Received:" + (mapper != null && t != null ? mapper.writeValueAsString(t) : " Null "));
			} catch (JsonProcessingException e) {
				LOGGER.warn("Error serializing for log", e);
			}
		}
		buffer.add(t);
		if ((buffer.size() % saveClusterSize) == 0) {
			if (!initialized) {
				initialize();
				initialized = true;
			}
			this.save(buffer);
			buffer = new ArrayList<>();
		}
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("End accept(code='" + t.getCode() + "')");
		}
	}

	protected abstract void initialize();

	protected abstract void save(List<T> list);

	protected abstract void disposeResources();

	@Override
	public synchronized void endReached() {
		if (!initialized) {
			initialize();
			initialized = true;
		}
		this.save(buffer);
		this.disposeResources();
	}

	public Class<T> getManagedType() {
		return managedType;
	}

}
