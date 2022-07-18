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
package com.openi40.scheduler.tests;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.openi40.scheduler.common.aps.IApsData;
import com.openi40.scheduler.common.aps.IApsObject;
import com.openi40.scheduler.common.aps.ICloneable;
import com.openi40.scheduler.common.aps.IMetaInfo;
import com.openi40.scheduler.common.aps.IReferencingMetaInfo;
import com.openi40.scheduler.engine.contextualplugarch.BusinessModelFactory;
import com.openi40.scheduler.engine.contextualplugarch.BusinessModelFactoryException;
import com.openi40.scheduler.engine.contextualplugarch.ITypeHierarchyBusinessModelFactory;
import com.openi40.scheduler.model.aps.ApsData;
import com.openi40.scheduler.model.equipment.Machine;
@Service
public class MyMachineSubclassFactory extends BusinessModelFactory
		implements ITypeHierarchyBusinessModelFactory<Machine> {

	public MyMachineSubclassFactory() {
		super(new ArrayList<>());
	}

	@Override
	public <ApsType extends IApsObject> ApsType create(Class<ApsType> type, IApsData context)
			throws BusinessModelFactoryException {

		return type.equals(Machine.class) ? (ApsType) new MyMachineSubclass((ApsData) context) : super.create(type, context);
	}

	@Override
	public Class<Machine> getManagedTypesAncestor() {

		return Machine.class;
	}

}
