package com.openi40.scheduler.engine.equipment.allocation;

import java.util.ArrayList;
import java.util.List;

import com.openi40.scheduler.model.equipment.TaskEquipmentInfo;
import com.openi40.scheduler.model.equipment.TaskExecutionPlanned.WorkSecondaryResourceInfos;
import com.openi40.scheduler.model.equipment.TaskPreparationPlanned.SetupSecondaryResourceInfos;
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
class SecondaryResourceRationalizer {
	static final SecondaryResourceRationalizer Instance = new SecondaryResourceRationalizer();

	private SecondaryResourceRationalizer() {

	}

	List<MatchingSecondaryResourcePhases> pairMatchingGroups(TaskEquipmentInfo equipmentInfo) {
		ArrayList<SetupSecondaryResourceInfos> prepSecondaries = new ArrayList(
				equipmentInfo.getPreparation().getSecondaryResources());
		ArrayList<WorkSecondaryResourceInfos> workSecondaries = new ArrayList(
				equipmentInfo.getExecution().getSecondaryResources());
		List<MatchingSecondaryResourcePhases> _sr = new ArrayList<>();
		for (SetupSecondaryResourceInfos taskPreparationUseModel : prepSecondaries) {
			MatchingSecondaryResourcePhases couple = null;
			for (WorkSecondaryResourceInfos taskExecutionUseModel : workSecondaries) {
				if (taskExecutionUseModel.getMetaInfo().getGroup() == taskPreparationUseModel.getMetaInfo()
						.getGroup()) {
					couple = new MatchingSecondaryResourcePhases();
					couple.preparation = taskPreparationUseModel;
					couple.execution = taskExecutionUseModel;
					_sr.add(couple);
				}
			}
		}
		for (MatchingSecondaryResourcePhases secondaryResourceWS : _sr) {
			prepSecondaries.removeIf((tUse) -> {
				return secondaryResourceWS.preparation == tUse;
			});
			workSecondaries.removeIf((tUse) -> {
				return secondaryResourceWS.execution == tUse;
			});
		}
		for (SetupSecondaryResourceInfos taskPreparationUseModel : prepSecondaries) {
			MatchingSecondaryResourcePhases couple = null;
			couple = new MatchingSecondaryResourcePhases();
			couple.preparation = taskPreparationUseModel;
			_sr.add(couple);
		}
		for (WorkSecondaryResourceInfos taskExecutionUseModel : workSecondaries) {
			MatchingSecondaryResourcePhases couple = null;
			couple = new MatchingSecondaryResourcePhases();
			couple.execution = taskExecutionUseModel;
			_sr.add(couple);
		}
		return _sr;
	}

}
