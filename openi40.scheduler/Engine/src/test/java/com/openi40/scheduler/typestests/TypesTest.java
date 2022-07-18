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
package com.openi40.scheduler.typestests;

import com.openi40.scheduler.engine.aps.IApsLogic;
import com.openi40.scheduler.engine.aps.IApsLogicComposer;
import com.openi40.scheduler.engine.contextualplugarch.AbstractBusinessLogicImplementationChooser;
import com.openi40.scheduler.engine.contextualplugarch.IBusinessLogicFactory;
import com.openi40.scheduler.model.aps.ApsData;
import com.openi40.scheduler.model.aps.ApsSchedulingSet;

public class TypesTest {
	public static void main(String s[]) {
		AbstractBusinessLogicImplementationChooser<IApsLogic, IBusinessLogicFactory<IApsLogic, ApsSchedulingSet>, ApsSchedulingSet> repository = new AbstractBusinessLogicImplementationChooser<IApsLogic, IBusinessLogicFactory<IApsLogic, ApsSchedulingSet>, ApsSchedulingSet>(
				IApsLogic.class);
		AbstractBusinessLogicImplementationChooser<IApsLogicComposer, IBusinessLogicFactory<IApsLogicComposer, ApsData>, ApsData> repository1 = new AbstractBusinessLogicImplementationChooser<IApsLogicComposer, IBusinessLogicFactory<IApsLogicComposer, ApsData>, ApsData>(
				IApsLogicComposer.class);

	}
}
