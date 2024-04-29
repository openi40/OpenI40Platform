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
import com.openi40.scheduler.output.model.orders.WorkOrderOutputDto;
import com.openi40.scheduler.output.model.tasks.TaskOutputDto;
import com.openi40.scheduler.output.model.tasks.TaskRelationOutputDto;
import com.openi40.scheduler.outputchannels.streamoutputs.IExtendedConsumer;

public class WorkOrderOutputPersistentExtendedConsumer extends AbstractPersistentExtendedConsumer<WorkOrderOutputDto> {

	@Service
	public static class WorkOrderOutputPersistentExtendedConsumerFactory
			extends AbstractPersistentExtendedConsumerFactory<WorkOrderOutputDto> {

		public WorkOrderOutputPersistentExtendedConsumerFactory(ObjectMapper mapper) {
			super(mapper, WorkOrderOutputDto.class);

		}

		@Override
		public AbstractPersistentExtendedConsumer<WorkOrderOutputDto> create(JDBCOutputTransactionWrapper w) {

			return new WorkOrderOutputPersistentExtendedConsumer(mapper, w);
		}

	}

	public WorkOrderOutputPersistentExtendedConsumer(ObjectMapper mapper, JDBCOutputTransactionWrapper w) {
		super(WorkOrderOutputDto.class, mapper, w);

	}

	@Override
	protected void initialize() {

	}

	static String updateSQL = "update work_order set color=?,del_date=?,start_execution_date=?,end_execution_date=?,modified_ts=?,total_qty=?,cycle_code=?,min_prd_date=?,max_prd_date=? where code=?";
	static String insertSQL = "insert into work_order (code,description,plant_code,prd_code,color,del_date,start_execution_date,end_execution_date,sales_order_line_code,modified_ts,total_qty,cycle_code,root_task,min_prd_date,max_prd_date) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

	@Override
	protected void save(List<WorkOrderOutputDto> list) throws SQLException {
		Timestamp ts = new Timestamp(System.currentTimeMillis());
		Connection con = jdbcTransactionWrapper.getConnection();
		PreparedStatement update = con.prepareStatement(updateSQL);
		PreparedStatement insert = con.prepareStatement(insertSQL);
		for (WorkOrderOutputDto dto : list) {
			update.clearParameters();

			update.setString(1, dto.getColor());
			if (dto.getDeliveryDate() != null)
				update.setDate(2, new java.sql.Date(dto.getDeliveryDate().getTime()));
			else
				update.setNull(2, java.sql.Types.DATE);
			if (dto.getStartExecutionDate() != null)
				update.setTimestamp(3, new java.sql.Timestamp(dto.getStartExecutionDate().getTime()));
			else
				update.setNull(3, java.sql.Types.TIMESTAMP);
			if (dto.getEndExecutionDate() != null)
				update.setTimestamp(4, new java.sql.Timestamp(dto.getEndExecutionDate().getTime()));
			else
				update.setNull(4, java.sql.Types.TIMESTAMP);
			update.setTimestamp(5, ts);
			update.setDouble(6, dto.getTotalQty());
			update.setString(7, dto.getCycleCode());
			if (dto.getMinProductionDateConstraint() != null) {
				update.setTimestamp(8, new Timestamp(dto.getMinProductionDateConstraint().getTime()));
			} else {
				update.setNull(8, java.sql.Types.TIMESTAMP);
			}
			if (dto.getMaxProductionDateConstraint() != null) {

				update.setTimestamp(9, new Timestamp(dto.getMaxProductionDateConstraint().getTime()));
			} else {
				update.setNull(9, java.sql.Types.TIMESTAMP);
			}
			update.setString(10, dto.getCode());
			int howMany = update.executeUpdate();
			if (howMany == 0) {
				insert.clearParameters();
				insert.setString(1, dto.getCode());
				insert.setString(2, dto.getDescription());
				insert.setString(3, dto.getPlantCode());
				insert.setString(4, dto.getProductCode());
				insert.setString(5, dto.getColor());
				if (dto.getDeliveryDate() != null)
					insert.setDate(6, new java.sql.Date(dto.getDeliveryDate().getTime()));
				else
					insert.setNull(6, java.sql.Types.DATE);
				if (dto.getStartExecutionDate() != null)
					insert.setTimestamp(7, new java.sql.Timestamp(dto.getStartExecutionDate().getTime()));
				else
					insert.setNull(7, java.sql.Types.TIMESTAMP);
				if (dto.getEndExecutionDate() != null)
					insert.setTimestamp(8, new java.sql.Timestamp(dto.getEndExecutionDate().getTime()));
				else
					insert.setNull(8, java.sql.Types.TIMESTAMP);
				insert.setString(9, dto.getSalesOrderLineCode());
				insert.setTimestamp(10, ts);
				insert.setDouble(11, dto.getTotalQty());
				insert.setString(12, dto.getCycleCode());
				insert.setBoolean(13, dto.isRootSalesOrderWorkOrder());
				if (dto.getMinProductionDateConstraint() != null) {
					insert.setTimestamp(14, new Timestamp(dto.getMinProductionDateConstraint().getTime()));
				} else {
					insert.setNull(14, java.sql.Types.TIMESTAMP);
				}
				if (dto.getMaxProductionDateConstraint() != null) {

					insert.setTimestamp(15, new Timestamp(dto.getMaxProductionDateConstraint().getTime()));
				} else {
					insert.setNull(15, java.sql.Types.TIMESTAMP);
				}
				insert.execute();
			}
		}
		insert.close();
	}

	@Override
	protected void disposeResources() {

	}

}
