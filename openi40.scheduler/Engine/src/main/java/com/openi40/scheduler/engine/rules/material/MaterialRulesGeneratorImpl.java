package com.openi40.scheduler.engine.rules.material;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.openi40.scheduler.engine.contextualplugarch.BusinessLogic;
import com.openi40.scheduler.engine.contextualplugarch.DefaultImplementation;
import com.openi40.scheduler.model.aps.ApsLogicOptions;
import com.openi40.scheduler.model.aps.ApsMessage;
import com.openi40.scheduler.model.aps.ApsMessageConstrants;
import com.openi40.scheduler.model.material.ItemConsumed;
import com.openi40.scheduler.model.material.ProductionSupply;
import com.openi40.scheduler.model.rules.MaterialRule;
import com.openi40.scheduler.model.rules.Rule;
import com.openi40.scheduler.model.tasks.Task;
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
@DefaultImplementation(entityClass = Task.class, implemented = IMaterialRulesGenerator.class)
public class MaterialRulesGeneratorImpl extends BusinessLogic<Task> implements IMaterialRulesGenerator {
	static Logger LOGGER = LoggerFactory.getLogger(MaterialRulesGeneratorImpl.class);

	@Override
	public List<MaterialRule> rebuildRules(List<MaterialRule> actualRules, Task task,
			ApsLogicOptions schedulingOptions) {
		Map<String, Object> environment = new HashMap<String, Object>();
		environment.put("task", task);
		List<MaterialRule> rules = new ArrayList<MaterialRule>();
		if (LOGGER.isDebugEnabled())
			LOGGER.debug("Begin MaterialRulesGeneratorImpl.rebuildRules(...)", "");
		List<ItemConsumed> cList = new ArrayList<ItemConsumed>();

		for (ItemConsumed ct : task.getMaterialConsumptions()) {
			cList.add(ct);
		}
		for (ItemConsumed consumption : cList) {
			MaterialRule materialConstraint = null;
			// For each task providing material try to add constraint rule starting after
			// production (according to lot/continues is becoming available)
			if (consumption.getTaskProductionLink() != null) {
				ProductionSupply plink = consumption.getTaskProductionLink();
				materialConstraint = new MaterialRule(task, Rule.ConstraintOrigin.SCHEDULING,
						Rule.ConstraintPriority.MANDATORY, consumption, plink,
						schedulingOptions.getDefaultCoveringType());
			} else {
				materialConstraint = new MaterialRule(task, Rule.ConstraintOrigin.SCHEDULING,
						Rule.ConstraintPriority.MANDATORY, consumption, null,
						schedulingOptions.getDefaultCoveringType());
			}
			if (consumption.getProduct() != null)
				environment.put("product", consumption.getProduct());
			ApsMessage unavailableMessage = new ApsMessage(this,task, ApsMessageConstrants.MATERIAL_SUPPLY_UNAVAILABLE,
					environment,task.getContext());
			materialConstraint.setUnmetConstraintMessage(unavailableMessage);
			materialConstraint.setResolutionStrategies(schedulingOptions.getSupplyResolutionStrategies());

			if (materialConstraint.getConsumer() != null
					&& materialConstraint.getConsumer().getProduct() != null) {
				String logValue = "Added MaterialConstraintRule for task #" + task.getId() + " for product code "
						+ materialConstraint.getConsumer().getProduct().getCode() + " using "
						+ (materialConstraint.getMaterialSupplies().size())
						+ " linked supply (other tasks), with resolution strategies: "
						+ materialConstraint.getResolutionStrategies();
				if (LOGGER.isDebugEnabled())
					LOGGER.debug("Added MaterialRule ", logValue);
			}
			rules.add(materialConstraint);
		}
		if (LOGGER.isDebugEnabled())
			LOGGER.debug("End MaterialRulesGeneratorImpl.rebuildRules(...)", "");
		return rules;
	}

}
