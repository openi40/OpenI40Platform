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

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.SQLType;
import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ConnectionCallback;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.openi40.platform.persistence.output.channel.AbstractPersistentExtendedConsumer;
import com.openi40.platform.persistence.output.channel.AbstractPersistentExtendedConsumerFactory;
import com.openi40.platform.persistence.output.channel.JDBCOutputTransactionWrapper;
import com.openi40.scheduler.output.model.orders.PeggingOutputDto;
import com.openi40.scheduler.output.model.orders.WorkOrderOutputDto;
import com.openi40.scheduler.output.model.tasks.TaskOutputDto;
import com.openi40.scheduler.output.model.tasks.TaskRelationOutputDto;
import com.openi40.scheduler.outputchannels.streamoutputs.IExtendedConsumer;

public class PeggingOutputPersistentExtendedConsumer extends AbstractPersistentExtendedConsumer<PeggingOutputDto> {

	@Service
	public static class PeggingOutputPersistentExtendedConsumerFactory
			extends AbstractPersistentExtendedConsumerFactory<PeggingOutputDto> {

		public PeggingOutputPersistentExtendedConsumerFactory(ObjectMapper mapper) {
			super(mapper, PeggingOutputDto.class);

		}

		@Override
		public AbstractPersistentExtendedConsumer<PeggingOutputDto> create(JDBCOutputTransactionWrapper wrapper) {

			return new PeggingOutputPersistentExtendedConsumer(mapper, wrapper);
		}

	}

	public PeggingOutputPersistentExtendedConsumer(ObjectMapper mapper, JDBCOutputTransactionWrapper wrapper) {
		super(PeggingOutputDto.class, mapper, wrapper);

	}

	private PreparedStatement update = null;
	private PreparedStatement insert = null;
	static String updateSQL = "update pegging set description=?,modified_ts=?,pegging_qty=? where cons_worder_code=? and cons_task_code=? and prdcr_worder_code=? and prdcr_task_code=?";
	static String insertSQL = "insert into pegging (description,modified_ts,pegging_qty,cons_worder_code,cons_task_code,prdcr_worder_code,prdcr_task_code,code) values (?,?,?,?,?,?,?,?)";

	@Override
	protected void initialize() throws SQLException {
		update = jdbcTransactionWrapper.getConnection().prepareStatement(updateSQL);
		insert = jdbcTransactionWrapper.getConnection().prepareStatement(insertSQL);

	}

	

	@Override
	protected void save(List<PeggingOutputDto> list) throws SQLException {
		Timestamp ts = new Timestamp(System.currentTimeMillis());

		for (PeggingOutputDto dto : list) {
			String description = "Work Order " + dto.getProducerWorkOrderCode() + " to " + dto.getConsumerTaskCode();
			update.clearParameters();
			update.setString(1, description);
			update.setTimestamp(2, ts);
			update.setDouble(3, dto.getPeggingQty());
			update.setString(4, dto.getConsumerWorkOrderCode());
			update.setString(5, dto.getConsumerTaskCode());
			update.setString(6, dto.getProducerWorkOrderCode());
			update.setString(7, dto.getProducerTaskCode());
			int howMany = update.executeUpdate();
			if (howMany == 0) {
				insert.clearParameters();
				insert.setString(1, description);
				insert.setTimestamp(2, ts);
				insert.setDouble(3, dto.getPeggingQty());
				insert.setString(4, dto.getConsumerWorkOrderCode());
				insert.setString(5, dto.getConsumerTaskCode());
				insert.setString(6, dto.getProducerWorkOrderCode());
				insert.setString(7, dto.getProducerTaskCode());
				insert.setString(8, UUID.randomUUID().toString());
				insert.executeUpdate();
			}
		}

	}

	@Override
	protected void disposeResources() throws SQLException {
		insert.close();
		update.close();

	}

}
