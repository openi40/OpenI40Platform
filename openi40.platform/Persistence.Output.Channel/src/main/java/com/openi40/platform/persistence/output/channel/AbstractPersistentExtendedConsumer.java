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
	protected Logger LOGGER = LoggerFactory.getLogger(this.getClass());
	protected Class<T> managedType = null;
	protected int saveClusterSize = 1000;
	protected boolean initialized = false;
	protected ObjectMapper mapper;
	protected JDBCOutputTransactionWrapper jdbcTransactionWrapper = null;

	public AbstractPersistentExtendedConsumer(Class<T> managedType, ObjectMapper mapper,
			JDBCOutputTransactionWrapper jdbcTW) {
		this.managedType = managedType;
		this.mapper = mapper;
		this.jdbcTransactionWrapper = jdbcTW;
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
		try {
			if ((buffer.size() % saveClusterSize) == 0) {
				if (!initialized) {
					initialize();
					initialized = true;
				}
				this.save(buffer);
				buffer = new ArrayList<>();
			}
		} catch (SQLException exc) {
			LOGGER.error("Exception in accept(..)", exc);
			throw new RuntimeException("Exception in accept(..)", exc);
		}
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("End accept(code='" + t.getCode() + "')");
		}
	}

	protected abstract void initialize() throws SQLException;

	protected abstract void save(List<T> list) throws SQLException;

	protected abstract void disposeResources() throws SQLException;

	public synchronized void endReached() {
		try {
			if (!initialized) {
				initialize();
				initialized = true;
			}
			this.save(buffer);
			this.disposeResources();
		} catch (SQLException exc) {
			LOGGER.error("Exception in endReached(..)", exc);
		}
	}

	public Class<T> getManagedType() {
		return managedType;
	}

}
