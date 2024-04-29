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
package com.openi40.platform.persistence.output.channel.impl;

import java.io.IOException;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ConnectionCallback;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.openi40.platform.persistence.output.channel.AbstractPersistentExtendedConsumer;
import com.openi40.platform.persistence.output.channel.AbstractPersistentExtendedConsumerFactory;
import com.openi40.platform.persistence.output.channel.JDBCOutputTransactionWrapper;
import com.openi40.scheduler.output.model.ApsSchedulingSetOutputDto;
import com.openi40.scheduler.outputchannels.streamoutputs.IExtendedConsumer;

public class ApsSchedulingSetOutputPersistentExtendedConsumer
		extends AbstractPersistentExtendedConsumer<ApsSchedulingSetOutputDto> {
	@Service
	public static class ApsSchedulingSetOutputPersistentExtendedConsumerFactory
			extends AbstractPersistentExtendedConsumerFactory<ApsSchedulingSetOutputDto> {

		public ApsSchedulingSetOutputPersistentExtendedConsumerFactory(ObjectMapper mapper) {
			super(mapper, ApsSchedulingSetOutputDto.class);

		}

		@Override
		public AbstractPersistentExtendedConsumer<ApsSchedulingSetOutputDto> create(
				JDBCOutputTransactionWrapper wrapper) {

			return new ApsSchedulingSetOutputPersistentExtendedConsumer(mapper, wrapper);
		}

	}

	public ApsSchedulingSetOutputPersistentExtendedConsumer(ObjectMapper mapper, JDBCOutputTransactionWrapper wrapper) {
		super(ApsSchedulingSetOutputDto.class, mapper, wrapper);

	}

	@Override
	protected void initialize() throws SQLException {
		// here i delete every apsschedulingset and their related entities

		Statement statement = jdbcTransactionWrapper.getConnection().createStatement();
		statement.executeUpdate("delete from scheduled_wo");
		statement.executeUpdate("delete from scheduling_set");

	}

	int setCount = 0;
	int woCount = 0;

	@Override
	protected void save(List<ApsSchedulingSetOutputDto> list) throws SQLException {
		Timestamp ts = new Timestamp(System.currentTimeMillis());

		PreparedStatement insertAS = jdbcTransactionWrapper.getConnection().prepareStatement(
				"insert into scheduling_set (code,description,position,modified_ts,options,algo_dir,algo_type) values (?,?,?,?,?,?,?)");
		PreparedStatement insertASWO = jdbcTransactionWrapper.getConnection().prepareStatement(
				"insert into scheduled_wo (code,description,position,modified_ts,sched_set_code,work_order_code) values (?,?,?,?,?,?)");
		for (ApsSchedulingSetOutputDto apss : list) {
			setCount++;
			String setCode = "00000" + setCount;
			insertAS.clearParameters();
			insertAS.setString(1, setCode);
			insertAS.setString(2, "Scheduling set nr. " + setCount);
			insertAS.setInt(3, setCount);
			insertAS.setTimestamp(4, ts);
			insertAS.setString(5, apss.getOptions());
			insertAS.setString(6, apss.getAlgorithmDirection());
			insertAS.setString(7, apss.getAlgorithmType());
			insertAS.executeUpdate();
			for (String woCode : apss.getWorkOrderCodes()) {
				woCount++;
				String entryCode = setCode + "-" + woCount;
				insertASWO.clearParameters();
				insertASWO.setString(1, entryCode);
				insertASWO.setString(2, "work order " + woCode);
				insertASWO.setInt(3, woCount);
				insertASWO.setTimestamp(4, ts);
				insertASWO.setString(5, setCode);
				insertASWO.setString(6, woCode);
				insertASWO.executeUpdate();
			}
		}
		insertAS.close();
		insertASWO.close();

	}

	@Override
	protected void disposeResources() {

	}

}
