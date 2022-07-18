package com.openi40.scheduler.model.cycle;

import java.util.ArrayList;
import java.util.List;

import com.openi40.scheduler.model.AbstractApsObject;
import com.openi40.scheduler.model.aps.ApsData;

import lombok.Data;
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
@Data
public class CycleModel extends AbstractApsObject {
	protected String plantCode = null;
	protected String productCode = null;
	protected boolean defaultProductCycle = false;
	protected List<OperationModel> operations = new ArrayList<>();
	protected OperationModel rootOperation = null;

	public CycleModel(ApsData context) {
		super(context);
	}

	public void restructureOperations() {
		for (int i = 0; i < operations.size(); i++) {
			OperationModel operation = operations.get(i);
			if (i == 0) {
				rootOperation = operation;
			}
			if (i < operations.size() - 1) {
				if (operation.getChildTasks().isEmpty()) {
					//TODO RECREATE EDGES EVEN IF NO ACTUAL RUNNING CODE WILL USE IT
				}
			}
		}
	}

}
