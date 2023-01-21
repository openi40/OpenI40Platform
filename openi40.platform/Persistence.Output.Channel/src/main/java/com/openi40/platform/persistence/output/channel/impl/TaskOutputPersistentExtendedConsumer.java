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
import java.sql.Types;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ConnectionCallback;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.openi40.platform.persistence.output.channel.AbstractPersistentExtendedConsumer;
import com.openi40.platform.persistence.output.channel.AbstractPersistentExtendedConsumerFactory;
import com.openi40.scheduler.output.model.tasks.TaskOutputDto;
import com.openi40.scheduler.output.model.tasks.TaskOutputDto.SecondaryReservation;
import com.openi40.scheduler.output.model.tasks.TaskOutputDto.TaskMaterialTransfer;
import com.openi40.scheduler.output.model.tasks.TaskOutputDto.UsedSecondaryResourcesInfoOutputDto;
import com.openi40.scheduler.outputchannels.streamoutputs.IExtendedConsumer;

public class TaskOutputPersistentExtendedConsumer extends AbstractPersistentExtendedConsumer<TaskOutputDto> {
	@Service
	public static class TaskOutputPersistentExtendedConsumerFactory
			extends AbstractPersistentExtendedConsumerFactory<TaskOutputDto> {

		public TaskOutputPersistentExtendedConsumerFactory(ObjectMapper mapper, JdbcTemplate jdbcTemplate) {
			super(mapper, TaskOutputDto.class, jdbcTemplate);

		}

		@Override
		public IExtendedConsumer<TaskOutputDto> create() {

			return new TaskOutputPersistentExtendedConsumer(mapper, jdbcTemplate);
		}

	};

	public TaskOutputPersistentExtendedConsumer(ObjectMapper mapper, JdbcTemplate jdbcTemplate) {
		super(TaskOutputDto.class, mapper, jdbcTemplate);

	}

	static String insertSQL = "insert into task(code,description,cycle_code,op_code,work_order_code,work_center_code,modified_ts,pred_mac_code,scheduled_mac_code, forced_mac_code,start_preparation,end_preparation,start_execution,end_execution,successfully_scheduled,success_scheduled,work_order_root,sequence_code,equip_spec_code,asked_del_time,sales_line_code,qty_total,qty_produced,custom_priority,setup_time,work_time,setup_group_code,min_prd_date,max_prd_date,status,acq_prep_start,acq_prep_end,acq_prd_start,acq_prd_end,acq_prd_upd,acq_machine_code) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	static String updateSQL = "update task set description=?,cycle_code=?,op_code=?,work_order_code=?,work_center_code=?,modified_ts=?,pred_mac_code=?,scheduled_mac_code=?, forced_mac_code=?,start_preparation=?,end_preparation=?,start_execution=?,end_execution=?,successfully_scheduled=?,success_scheduled=?,equip_spec_code=?,asked_del_time=?,sales_line_code=?,qty_total=?,qty_produced=?,custom_priority=?,setup_time=?,work_time=?,setup_group_code=?,min_prd_date=?,max_prd_date=?,status=?,acq_prep_start=?,acq_prep_end=?,acq_prd_start=?,acq_prd_end=?,acq_prd_upd=?,acq_machine_code=? where code=?";

	@Override
	protected void initialize() {

	}

	void assignTS(PreparedStatement ps, int index, Date date) throws SQLException {
		if (date != null) {
			ps.setTimestamp(index, new Timestamp(date.getTime()));
		} else {
			ps.setNull(index, Types.TIMESTAMP);
		}
	}

	@Override
	protected void save(List<TaskOutputDto> list) {
		ConnectionCallback<Integer> callback = new ConnectionCallback<Integer>() {
			@Override
			public Integer doInConnection(Connection con) throws SQLException, DataAccessException {
				Timestamp ts = new Timestamp(System.currentTimeMillis());
				PreparedStatement update = con.prepareStatement(updateSQL);
				PreparedStatement insert = con.prepareStatement(insertSQL);

				for (TaskOutputDto task : list) {
					update.clearParameters();
					// code,description,cycle_code,op_code,work_order_code,work_center_code,modified_ts,pred_mac_code,scheduled_mac_code,
					// forced_mac_code,start_preparation,end_preparation,start_execution,end_execution,successfully_scheduled,success_scheduled
					update.setString(1, task.getDescription());
					update.setString(2, task.getCycleCode());
					update.setString(3, task.getOperationCode());
					update.setString(4, task.getWorkOrderCode());
					update.setString(5, task.getWorkCenterCode());
					update.setTimestamp(6, ts);
					update.setString(7, task.getPredefinedMachineCode());
					update.setString(8, task.getScheduledMachineCode());
					update.setString(9, task.getForcedMachineCode());
					assignTS(update, 10, task.getStartPreparation());
					assignTS(update, 11, task.getEndPreparation());
					assignTS(update, 12, task.getStartExecution());
					assignTS(update, 13, task.getEndExecution());
					update.setBoolean(14, task.isSuccessfullyScheduled());
					update.setBoolean(15, task.isSuccessfullyScheduled());
					update.setString(16, task.getEquipmentSpecCode());
					// asked_del_time,sales_line_code,qty_total,qty_produced,custom_priority,setup_time,work_time,setup_group_code
					assignTS(update, 17, task.getAskedDeliveryDateTime());
					update.setString(18, task.getSalesOrderLineCode());
					update.setDouble(19, task.getQtyTotal());
					update.setDouble(20, task.getQtyProduced());
					update.setInt(21, task.getCustomPriority() != null ? task.getCustomPriority() : 0);
					update.setDouble(22, task.getSetupTime());
					update.setDouble(23, task.getWorkTime());
					update.setString(24, task.getSetupGroupCode());
					assignTS(update, 25, task.getMinProductionDateConstraint());
					assignTS(update, 26, task.getMaxProductionDateConstraint());
					// status=?,acq_prep_start=?,acq_prep_end=?,acq_prd_start=?,acq_prd_end=?,acq_prd_upd=?,acq_machine_code=?
					update.setString(27, task.getStatus());
					assignTS(update, 28, task.getAcquiredStartSetup());
					assignTS(update, 29, task.getAcquiredEndSetup());
					assignTS(update, 30, task.getAcquiredStartWork());
					assignTS(update, 31, task.getAcquiredEndWork());
					assignTS(update, 32, task.getAcquiredProductionUpdate());
					update.setString(33, task.getAcquiredMachineCode());
					update.setString(34, task.getCode());
					int howMany = update.executeUpdate();
					if (howMany == 0) {
						insert.clearParameters();
						// code,description,cycle_code,op_code,work_order_code,work_center_code,modified_ts,pred_mac_code,scheduled_mac_code,
						// forced_mac_code,start_preparation,end_preparation,start_execution,end_execution,successfully_scheduled,success_scheduled
						insert.setString(1, task.getCode());
						insert.setString(2, task.getDescription());
						insert.setString(3, task.getCycleCode());
						insert.setString(4, task.getOperationCode());
						insert.setString(5, task.getWorkOrderCode());
						insert.setString(6, task.getWorkCenterCode());
						insert.setTimestamp(7, ts);
						insert.setString(8, task.getPredefinedMachineCode());
						insert.setString(9, task.getScheduledMachineCode());
						insert.setString(10, task.getForcedMachineCode());
						assignTS(insert, 11, task.getStartPreparation());
						assignTS(insert, 12, task.getEndPreparation());
						assignTS(insert, 13, task.getStartExecution());
						assignTS(insert, 14, task.getEndExecution());
						insert.setBoolean(15, task.isSuccessfullyScheduled());
						insert.setBoolean(16, task.isSuccessfullyScheduled());
						insert.setBoolean(17, task.isWorkOrderRootTask());
						insert.setString(18, task.getSequenceCode());
						insert.setString(19, task.getEquipmentSpecCode());
						assignTS(insert, 20, task.getAskedDeliveryDateTime());
						insert.setString(21, task.getSalesOrderLineCode());
						insert.setDouble(22, task.getQtyTotal());
						insert.setDouble(23, task.getQtyProduced());
						insert.setInt(24, task.getCustomPriority() != null ? task.getCustomPriority() : 0);
						insert.setDouble(25, task.getSetupTime());
						insert.setDouble(26, task.getWorkTime());
						insert.setString(27, task.getSetupGroupCode());
						assignTS(insert, 28, task.getMinProductionDateConstraint());
						assignTS(insert, 29, task.getMaxProductionDateConstraint());
						insert.setString(30, task.getStatus());
						assignTS(insert, 31, task.getAcquiredStartSetup());
						assignTS(insert, 32, task.getAcquiredEndSetup());
						assignTS(insert, 33, task.getAcquiredStartWork());
						assignTS(insert, 34, task.getAcquiredEndWork());
						assignTS(insert, 35, task.getAcquiredProductionUpdate());
						insert.setString(36, task.getAcquiredMachineCode());

						insert.executeUpdate();
					}

				}
				insert.close();
				update.close();
				String deleteSecondary = "delete from rc_group_reserv where task_code in (";
				String deleteWhousePick = "delete from whouse_picklist where task_code in (";
				String deleteTaskPick = "delete from task_picklist where task_code in (";
				String deletePurchasePick = "delete from purchase_picklist where task_code in (";
				String deleteTRC = "delete from task_relation where consmr_task_code in (";
				String deleteTRP = "delete from task_relation where suplr_task_code in (";
				String deleteAcqSetupRC = "delete from acq_setup_resources where task_code in (";
				String deleteAcqWorkRC = "delete from acq_work_resources where task_code in (";
				String vectorialDeletes[] = { deleteSecondary, deleteWhousePick, deleteTaskPick, deletePurchasePick,
						deleteTRC, deleteTRP, deleteAcqSetupRC, deleteAcqWorkRC };
				for (int i = 0; i < list.size(); i++) {
					for (int j = 0; j < vectorialDeletes.length; j++) {
						vectorialDeletes[j] += "?";
						if (i < list.size() - 1)
							vectorialDeletes[j] += ",";
						else
							vectorialDeletes[j] += ")";
					}
				}
				TaskOutputDto task = null;
				for (String deleteClause : vectorialDeletes) {
					PreparedStatement psDelete = con.prepareStatement(deleteClause);
					psDelete.clearParameters();
					for (int i = 0; i < list.size(); i++) {
						task = list.get(i);
						psDelete.setString(i + 1, task.getCode());
					}
					psDelete.executeUpdate();
					psDelete.close();
				}

				String GROUP_RESERVINSERT = "insert into rc_group_reserv(code,description,task_code,rc_code,rc_group_code,start_reserv,end_reserv,use_type,modified_ts) values (?,?,?,?,?,?,?,?,?)";

				String WHPICKLISTINSERT = "insert into whouse_picklist (code,description,modified_ts,whouse_code,task_code,mac_code,work_center_code,prd_code,start_transfer,end_transfer,transfer_type,batch_qty,nr_transfers,qty_reserved) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
				String TASKPICKLISTINSERT = "insert into task_picklist(code,description,modified_ts,whouse_code,task_code,mac_code,work_center_code,prd_code,start_transfer,end_transfer,transfer_type,batch_qty,nr_transfers,qty_reserved,supply_task,supply_work_order) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
				String PURCHPICLISTINSERT = "insert into purchase_picklist (code,description  ,modified_ts,whouse_code,task_code,mac_code,work_center_code,prd_code,start_transfer,end_transfer,transfer_type,batch_qty,nr_transfers,qty_reserved,purch_order,purch_order_line) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
				String ACQSETUPINSERT = "INSERT INTO acq_setup_resources (code,description,removed,modified_ts,task_code,rc_codes,resource_group) values (?,?,?,?,?,?,?)";
				String ACQWORKINSERT = "INSERT INTO acq_work_resources (code,description,removed,modified_ts,task_code,rc_codes,resource_group) values (?,?,?,?,?,?,?)";
				PreparedStatement psInsertRC = con.prepareStatement(GROUP_RESERVINSERT);
				PreparedStatement pswhpick = con.prepareStatement(WHPICKLISTINSERT);
				PreparedStatement taskpick = con.prepareStatement(TASKPICKLISTINSERT);
				PreparedStatement purchpick = con.prepareStatement(PURCHPICLISTINSERT);
				PreparedStatement acqsetup = con.prepareStatement(ACQSETUPINSERT);
				PreparedStatement acqwork = con.prepareStatement(ACQWORKINSERT);
				HashMap<String, Integer> countMap = new HashMap<>();
				int matIndex = 1;
				for (int i = 0; i < list.size(); i++) {
					task = list.get(i);
					if (!task.isSuccessfullyScheduled())
						continue;
					for (SecondaryReservation rc : task.getSecondaryReservations()) {
						// code,description,task_code,rc_code,rc_group_code,start_reserv,end_reserv,use_type
						String code = task.getCode() + "-" + rc.getResourceCode();
						if (!countMap.containsKey(code)) {
							countMap.put(code, 0);
						}
						Integer idx = countMap.get(code);
						int index = idx.intValue() + 1;
						countMap.put(code, index);
						code = code + "-" + index;
						psInsertRC.clearParameters();
						psInsertRC.setString(1, code);
						psInsertRC.setString(2, "Reservation for task " + task.getCode() + " and resource "
								+ rc.getResourceCode() + " as " + rc.getSlotType());
						psInsertRC.setString(3, task.getCode());
						psInsertRC.setString(4, rc.getResourceCode());
						psInsertRC.setString(5, rc.getSecondaryResourceGroupCode());
						psInsertRC.setTimestamp(6, new Timestamp(rc.getStart().getTime()));
						psInsertRC.setTimestamp(7, new Timestamp(rc.getEnd().getTime()));
						psInsertRC.setString(8, rc.getSlotType());
						psInsertRC.setTimestamp(9, ts);
						psInsertRC.executeUpdate();
					}

					for (TaskMaterialTransfer mat : task.getMaterialTransfer()) {
						PreparedStatement ps = null;
						switch (mat.getLinkType()) {
						case STOCK: {
							ps = pswhpick;
						}
							break;
						case PRODUCTION: {
							ps = taskpick;
						}
							break;
						case PURCHASE: {
							ps = purchpick;
						}
							break;
						}
						ps.clearParameters();
						// code,description,modified_ts,whouse_code,task_code,mac_code,work_center_code,prd_code,start_transfer,end_transfer,transfer_type,batch_qty,nr_transfers,qty_reserved
						ps.setString(1, task.getCode() + "-" + mat.getProductCode() + "-" + matIndex);
						ps.setString(2, "Transfers for task " + task.getCode() + " product " + mat.getProductCode()
								+ " warehouse " + mat.getWarehouseCode());
						ps.setTimestamp(3, ts);
						ps.setString(4, mat.getWarehouseCode());
						ps.setString(5, mat.getTaskCode());
						ps.setString(6, mat.getMachineCode());
						ps.setString(7, mat.getWorkCenterCode());
						ps.setString(8, mat.getProductCode());
						assignTS(ps, 9, mat.getStartTransfer());
						assignTS(ps, 10, mat.getEndTransfer());
						ps.setString(11, "");
						ps.setDouble(12, 0);
						ps.setDouble(13, 0);
						ps.setDouble(14, mat.getQtyReserved());
						switch (mat.getLinkType()) {
						case STOCK: {

						}
							break;
						case PRODUCTION: {
							// supply_task,supply_work_order
							ps.setString(15, mat.getSupplyTaskCode());
							ps.setString(16, mat.getSupplyWorkOrderCode());
						}
							break;
						case PURCHASE: {
							// purch_order,purch_order_line
							ps.setString(15, mat.getPurchaseOrderCode());
							ps.setString(16, mat.getPurchaseOrderLineCode());
						}
							break;
						}
						ps.executeUpdate();
						matIndex++;
					}
					for (UsedSecondaryResourcesInfoOutputDto used : task.getAcquiredSetupUsedResources()) {
						acqsetup.clearParameters();
						// code,description,removed,modified_ts,task_code,rc_codes
						acqsetup.setString(1, used.getCode());
						acqsetup.setString(2, used.getDescription());
						acqsetup.setBoolean(3, false);
						assignTS(acqsetup, 4, ts);
						acqsetup.setString(5, task.getCode());
						acqsetup.setString(6, generateParsableString(used.getUsedResourcesCodes()));
						acqsetup.setString(7, used.getResourceGroup());
						acqsetup.executeUpdate();
					}
					for (UsedSecondaryResourcesInfoOutputDto used : task.getAcquiredWorkUsedResources()) {
						acqwork.clearParameters();
						// code,description,removed,modified_ts,task_code,rc_codes
						acqwork.setString(1, used.getCode());
						acqwork.setString(2, used.getDescription());
						acqwork.setBoolean(3, false);
						assignTS(acqwork, 4, ts);
						acqwork.setString(5, task.getCode());
						acqwork.setString(6, generateParsableString(used.getUsedResourcesCodes()));
						acqwork.setString(7, used.getResourceGroup());
						acqwork.executeUpdate();
					}
				}
				psInsertRC.close();
				pswhpick.close();
				taskpick.close();
				purchpick.close();
				acqsetup.close();
				acqwork.close();
				return list.size();
			}

			private String generateParsableString(List<String> usedResourcesCodes) {
				StringBuffer sb = new StringBuffer();
				if (usedResourcesCodes != null) {
					Iterator<String> iterable = usedResourcesCodes.iterator();
					while (iterable.hasNext()) {
						sb.append(iterable.next());
						if (iterable.hasNext())
							sb.append(";");
					}
				}
				return sb.toString();
			}
		};
		this.jdbcTemplate.execute(callback);
	}

	@Override
	protected void disposeResources() {

	}

}
