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

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ConnectionCallback;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.openi40.platform.persistence.output.channel.AbstractPersistentExtendedConsumer;
import com.openi40.platform.persistence.output.channel.AbstractPersistentExtendedConsumerFactory;
import com.openi40.scheduler.output.model.tasks.TaskRelationOutputDto;
import com.openi40.scheduler.outputchannels.streamoutputs.IExtendedConsumer;

public class TaskRelationOutputPersistentExtendedConsumer
		extends AbstractPersistentExtendedConsumer<TaskRelationOutputDto> {
	@Service
	public static class TaskRelationOutputPersistentExtendedConsumerFactory
			extends AbstractPersistentExtendedConsumerFactory<TaskRelationOutputDto> {

		public TaskRelationOutputPersistentExtendedConsumerFactory(ObjectMapper mapper, JdbcTemplate jdbcTemplate) {
			super(mapper, TaskRelationOutputDto.class, jdbcTemplate);

		}

		@Override
		public IExtendedConsumer<TaskRelationOutputDto> create() {

			return new TaskRelationOutputPersistentExtendedConsumer(mapper, jdbcTemplate);
		}

	}

	public TaskRelationOutputPersistentExtendedConsumer(ObjectMapper mapper, JdbcTemplate jdbcTemplate) {
		super(TaskRelationOutputDto.class, mapper, jdbcTemplate);

	}

	@Override
	protected void initialize() {

	}

	static final String insertSQL = "insert into task_relation(code,description,consmr_task_code,suplr_task_code,consmr_worder_code,suplr_worder_code,modified_ts,alignment_type,"
			+ "time_range_type,\r\n"
			+ "pegging_edge,\r\n"
			+ "pegging_code,\r\n"
			+ "offset_millisecs,\r\n"
			+ "bom_item_code,\r\n"
			+ "cons_transfer_type,\r\n"
			+ "cons_batch_qty) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

	@Override
	protected void save(List<TaskRelationOutputDto> list) {
		Timestamp ts = new Timestamp(System.currentTimeMillis());
		ConnectionCallback<Integer> callback = new ConnectionCallback<Integer>() {
			@Override
			public Integer doInConnection(Connection con) throws SQLException, DataAccessException {
				PreparedStatement insert = con.prepareStatement(insertSQL);
				for (TaskRelationOutputDto tr : list) {
					// code,description,consmr_task_code,suplr_task_code,modified_ts
					insert.clearParameters();
					insert.setString(1, tr.getCode());
					insert.setString(2, tr.getDescription());
					insert.setString(3, tr.getConsumerTaskCode());
					insert.setString(4, tr.getSupplierTaskCode());
					insert.setString(5, tr.getConsumerWorkOrder());
					insert.setString(6, tr.getSupplierWorkOrder());
					insert.setTimestamp(7, ts);
					insert.setString(8, tr.getAlignmentType());
					insert.setString(9, tr.getTimeRangeType());
					insert.setBoolean(10, tr.getPeggingEdge());
					insert.setString(11, tr.getPeggingCode());
					insert.setLong(12, tr.getOffsetMillisecs());
					insert.setString(13, tr.getBomItemCode());
					insert.setString(14, tr.getConsumptionTransferType());
					insert.setDouble(15, tr.getConsumptionBatchQty());
					insert.executeUpdate();
				}
				insert.close();
				return list.size();
			}

		};
		this.jdbcTemplate.execute(callback);
	}

	@Override
	protected void disposeResources() {

	}

}
