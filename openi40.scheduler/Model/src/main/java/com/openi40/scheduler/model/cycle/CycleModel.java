package com.openi40.scheduler.model.cycle;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.openi40.scheduler.model.AbstractApsObject;
import com.openi40.scheduler.model.aps.ApsData;
import com.openi40.scheduler.model.resourcesdeps.IApsResourcesDependencyTreeObject;
import com.openi40.scheduler.model.resourcesdeps.ResourceDepsItemMetaInfo;
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

public class CycleModel extends AbstractApsObject implements IApsResourcesDependencyTreeObject {
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

	public String getPlantCode() {
		return plantCode;
	}

	public void setPlantCode(String plantCode) {
		this.plantCode = plantCode;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public boolean isDefaultProductCycle() {
		return defaultProductCycle;
	}

	public void setDefaultProductCycle(boolean defaultProductCycle) {
		this.defaultProductCycle = defaultProductCycle;
	}

	public List<OperationModel> getOperations() {
		return operations;
	}

	public void setOperations(List<OperationModel> operations) {
		this.operations = operations;
	}

	public OperationModel getRootOperation() {
		return rootOperation;
	}

	public void setRootOperation(OperationModel rootOperation) {
		this.rootOperation = rootOperation;
	}

	@Override
	public ResourceDepsItemMetaInfo getResourceItemInfo() {
		
		return ResourceDepsItemMetaInfo.of(this);
	}

	@Override
	public Collection<IApsResourcesDependencyTreeObject> getResourceDependencyChilds() {
		
		return new ArrayList<IApsResourcesDependencyTreeObject>(operations);
	}

}
