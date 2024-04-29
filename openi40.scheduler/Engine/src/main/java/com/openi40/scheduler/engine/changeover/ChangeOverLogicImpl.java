package com.openi40.scheduler.engine.changeover;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.openi40.scheduler.engine.OpenI40Exception;
import com.openi40.scheduler.engine.contextualplugarch.BusinessLogic;
import com.openi40.scheduler.engine.contextualplugarch.DefaultImplementation;
import com.openi40.scheduler.model.companystructure.WorkCenter;
import com.openi40.scheduler.model.cycle.ChangeOverMatrixItem;
import com.openi40.scheduler.model.dao.DataModelDaoException;
import com.openi40.scheduler.model.dao.IChangeOverMatrixItemDao;
import com.openi40.scheduler.model.equipment.MachinesGroup;
import com.openi40.scheduler.model.equipment.TaskEquipmentChangeOverInfo;
import com.openi40.scheduler.model.tasks.Task;
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
@DefaultImplementation(implemented = IChangeOverLogic.class, entityClass = TaskEquipmentChangeOverInfo.class)
public class ChangeOverLogicImpl extends BusinessLogic<TaskEquipmentChangeOverInfo> implements IChangeOverLogic {
	static Logger LOGGER = LoggerFactory.getLogger(ChangeOverLogicImpl.class);
	@Autowired
	IChangeOverMatrixItemDao changeOverMatrixItemDao;

	public double calculateChangeOverTime(Task task, TaskEquipmentChangeOverInfo changeOverInfo) {
		String _setupGroupFrom = changeOverInfo.getBeforeEquipment() != null
				? changeOverInfo.getBeforeEquipment().getSetupGroupCode()
				: null;
		String _setupGroupTo = changeOverInfo.getAfterEquipment() != null
				? changeOverInfo.getAfterEquipment().getSetupGroupCode()
				: null;
		String _machineCode = changeOverInfo.getAfterEquipment() != null
				&& changeOverInfo.getAfterEquipment().getPreparation() != null
				&& changeOverInfo.getAfterEquipment().getPreparation().getResource() != null
				&& changeOverInfo.getAfterEquipment().getPreparation().getResource().getChoosenEquipment() != null
						? changeOverInfo.getAfterEquipment().getPreparation().getResource().getChoosenEquipment()
								.getCode()
						: null;
		MachinesGroup machineGroup = changeOverInfo.getAfterEquipment() != null
				&& changeOverInfo.getAfterEquipment().getPreparation() != null
				&& changeOverInfo.getAfterEquipment().getPreparation().getResource() != null
				&& changeOverInfo.getAfterEquipment().getPreparation().getResource().getMetaInfo() != null
						? changeOverInfo.getAfterEquipment().getPreparation().getResource().getMetaInfo().getGroup()
						: null;
		WorkCenter workCenter = machineGroup != null && machineGroup instanceof WorkCenter ? (WorkCenter) machineGroup
				: null;
		if (workCenter != null && _machineCode != null && _setupGroupFrom != null && _setupGroupTo != null) {
			try {
				ChangeOverMatrixItem item = changeOverMatrixItemDao.findBySetupCodes(_setupGroupFrom, _setupGroupTo,
						_machineCode, workCenter);
				return item.getSetupTime();
			} catch (DataModelDaoException e) {
				throw new OpenI40Exception("Problems searching for change over informations", e);
			}
		}
		return 0.0;
	}

	@Override
	public boolean isChangeOverManaged(Task task, TaskEquipmentChangeOverInfo changeOverInfo) {
		String _setupGroupFrom = changeOverInfo.getBeforeEquipment() != null
				? changeOverInfo.getBeforeEquipment().getSetupGroupCode()
				: null;
		String _setupGroupTo = changeOverInfo.getAfterEquipment() != null
				? changeOverInfo.getAfterEquipment().getSetupGroupCode()
				: null;
		String _machineCode = changeOverInfo.getAfterEquipment() != null
				&& changeOverInfo.getAfterEquipment().getPreparation() != null
				&& changeOverInfo.getAfterEquipment().getPreparation().getResource() != null
				&& changeOverInfo.getAfterEquipment().getPreparation().getResource().getChoosenEquipment() != null
						? changeOverInfo.getAfterEquipment().getPreparation().getResource().getChoosenEquipment()
								.getCode()
						: null;
		MachinesGroup machineGroup = changeOverInfo.getAfterEquipment() != null
				&& changeOverInfo.getAfterEquipment().getPreparation() != null
				&& changeOverInfo.getAfterEquipment().getPreparation().getResource() != null
				&& changeOverInfo.getAfterEquipment().getPreparation().getResource().getMetaInfo() != null
						? changeOverInfo.getAfterEquipment().getPreparation().getResource().getMetaInfo().getGroup()
						: null;
		WorkCenter workCenter = machineGroup != null && machineGroup instanceof WorkCenter ? (WorkCenter) machineGroup
				: null;
		try {
			return _setupGroupFrom != null && _setupGroupTo != null && _machineCode != null && workCenter != null
					&& changeOverMatrixItemDao.findBySetupCodes(_setupGroupFrom, _setupGroupTo, _machineCode,
							workCenter) != null;
		} catch (DataModelDaoException e) {
			throw new OpenI40Exception("Problems searching for change over informations", e);
		}
	}
}