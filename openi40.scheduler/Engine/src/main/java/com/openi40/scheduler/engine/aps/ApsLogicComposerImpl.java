package com.openi40.scheduler.engine.aps;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.openi40.scheduler.engine.apsdata.IApsDataManager;
import com.openi40.scheduler.engine.contextualplugarch.BusinessLogic;
import com.openi40.scheduler.engine.contextualplugarch.DefaultImplementation;
import com.openi40.scheduler.engine.realtime.IRealTimeDataManager;
import com.openi40.scheduler.model.aps.ApsData;
import com.openi40.scheduler.model.aps.ApsSchedulingSet;
import com.openi40.scheduler.model.companystructure.Plant;
import com.openi40.scheduler.model.companystructure.ProductiveCompany;
import com.openi40.scheduler.model.orders.WorkOrder;

/**
 * 
 * This code is part of the OpenI40 open source advanced production scheduler
 * platform suite, have look to its licencing options. Web site:
 * http://openi40.org/ Github: https://github.com/openi40/OpenI40Platform We
 * hope you enjoy implementing new amazing projects with it.
 * 
 * @author architectures@openi40.org
 *
 *         Scheduling engine implementation, re-runs all algorithms in tasks
 *         groups with planned options
 */
@DefaultImplementation(implemented = IApsLogicComposer.class, entityClass = ApsData.class)
public class ApsLogicComposerImpl extends BusinessLogic<ApsData> implements IApsLogicComposer {
	static Logger LOGGER = LoggerFactory.getLogger(ApsLogicComposerImpl.class);

	public void schedule(ApsData apsData, ApsLogicNotifiedObjects observer) {
		long ts = System.currentTimeMillis();
		LOGGER.info("Begin ApsLogicComposerImpl.schedule(...)");

		long ts1 = System.currentTimeMillis();
		// Resets all data model prior to scheduling releasing all non-locked
		// Resources, materials ecc..
		apsData.resetSchedulingData();
		boolean scheduled = true;
		IApsDataManager dataManager = this.componentsFactory.create(IApsDataManager.class, apsData, apsData);
		// Initialize data structures if not yet done
		if (!apsData.isInitialized()) {
			dataManager.initialize(apsData);
		}
		dataManager.beforeScheduling(apsData);
		if (apsData.isRealtime()) {
			IRealTimeDataManager realTimeDataManager = this.componentsFactory.create(IRealTimeDataManager.class,
					apsData, apsData);
			realTimeDataManager.actualize(apsData);
		}
		if (apsData.isProductionControlEnabled()) {
			IApsProductionControlSchedulerController productionSchedulingController = componentsFactory
					.create(IApsProductionControlSchedulerController.class, apsData, apsData);
			productionSchedulingController.prepareSchedulingData(apsData);

		}
		LOGGER.info("Cleared scheduling data in " + ((System.currentTimeMillis() - ts1) / 1000) + " sec");
		for (ApsSchedulingSet apsAction : apsData.getSchedulingSets()) {
			IApsLogic apsLogic = componentsFactory.create(IApsLogic.class, apsAction.getAlgorithmType(), apsAction,
					apsData);
			apsAction.setAlgorithmDirection(apsLogic.getDirection());
			LOGGER.info("Scheduling => " + apsAction.getAlgorithmType());
			apsLogic.schedule(apsAction, observer);
			scheduled = scheduled && apsAction.isScheduled();
		}
		LOGGER.info("End ApsLogicComposerImpl.schedule(...) sec=" + ((System.currentTimeMillis() - ts) / 1000));
		apsData.debugLogging();
	}

	@Override
	public List<ApsSchedulingSet> autoSetTasks(ApsData apsData, List<ApsSchedulingSet> schedulingSets) {
		List<ApsSchedulingSet> outSets = new ArrayList<>();
		Map<String, WorkOrder> unscheduledWorkOrder = new HashMap<>();
		if (apsData.getProductiveCompanies() != null) {
			for (ProductiveCompany c : apsData.getProductiveCompanies()) {
				if (c.getPlants() != null) {
					for (Plant p : c.getPlants()) {
						if (p.getWorkOrders() != null) {
							for (WorkOrder wo : p.getWorkOrders()) {
								unscheduledWorkOrder.put(wo.getCode(), wo);
							}
						}
					}
				}
			}
		}
		for (ApsSchedulingSet apsSet : schedulingSets) {
			if (apsSet.getWorkOrders() != null) {
				for (WorkOrder w : apsSet.getWorkOrders()) {
					unscheduledWorkOrder.remove(w.getCode());
				}
			}
		}
		List<WorkOrder> unscheduled = new ArrayList<>(unscheduledWorkOrder.values());
		for (ApsSchedulingSet apsSet : schedulingSets) {
			IApsLogic scheduler = this.componentsFactory.create(IApsLogic.class, apsSet, apsData);
			ApsSchedulingSet fixedApsSet = scheduler.autoSetTasks(apsSet, unscheduled, apsData);
			outSets.add(fixedApsSet);
		}
		return outSets;
	}

}