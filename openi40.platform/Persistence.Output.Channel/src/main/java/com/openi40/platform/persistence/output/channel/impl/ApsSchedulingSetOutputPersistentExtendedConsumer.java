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
import com.openi40.scheduler.output.model.ApsSchedulingSetOutputDto;
import com.openi40.scheduler.outputchannels.streamoutputs.IExtendedConsumer;

public class ApsSchedulingSetOutputPersistentExtendedConsumer
		extends AbstractPersistentExtendedConsumer<ApsSchedulingSetOutputDto> {
	@Service
	public static class ApsSchedulingSetOutputPersistentExtendedConsumerFactory
			extends AbstractPersistentExtendedConsumerFactory<ApsSchedulingSetOutputDto> {

		public ApsSchedulingSetOutputPersistentExtendedConsumerFactory(ObjectMapper mapper, JdbcTemplate jdbcTemplate) {
			super(mapper, ApsSchedulingSetOutputDto.class, jdbcTemplate);

		}

		@Override
		public IExtendedConsumer<ApsSchedulingSetOutputDto> create() {

			return new ApsSchedulingSetOutputPersistentExtendedConsumer(mapper, jdbcTemplate);
		}

	}

	public ApsSchedulingSetOutputPersistentExtendedConsumer(ObjectMapper mapper, JdbcTemplate jdbcTemplate) {
		super(ApsSchedulingSetOutputDto.class, mapper, jdbcTemplate);

	}

	@Override
	protected void initialize() {
		// here i delete every apsschedulingset and their related entities
		ConnectionCallback<Integer> callback = new ConnectionCallback<Integer>() {
			@Override
			public Integer doInConnection(Connection con) throws SQLException, DataAccessException {
				Statement statement = con.createStatement();
				statement.executeUpdate("delete from scheduled_wo");
				statement.executeUpdate("delete from scheduling_set");
				return 0;
			}
		};
		jdbcTemplate.execute(callback);
	}

	int setCount = 0;
	int woCount = 0;

	@Override
	protected void save(List<ApsSchedulingSetOutputDto> list) {
		Timestamp ts = new Timestamp(System.currentTimeMillis());
		ConnectionCallback<Integer> callback = new ConnectionCallback<Integer>() {
			@Override
			public Integer doInConnection(Connection con) throws SQLException, DataAccessException {
				PreparedStatement insertAS = con.prepareStatement(
						"insert into scheduling_set (code,description,position,modified_ts,options,algo_dir,algo_type) values (?,?,?,?,?,?,?)");
				PreparedStatement insertASWO = con.prepareStatement(
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
				return 0;
			}
		};
		jdbcTemplate.execute(callback);
	}

	@Override
	protected void disposeResources() {

	}

}
