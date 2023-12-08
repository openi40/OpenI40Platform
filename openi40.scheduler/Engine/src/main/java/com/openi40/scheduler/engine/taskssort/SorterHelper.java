package com.openi40.scheduler.engine.taskssort;

import java.util.Date;

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

public abstract class SorterHelper {
	String propertyName;
	String defaultDirection;
	String description;
	String longDescription;
	Class dataType;
	private SorterHelper(String pName,String direction,String description,String longDescription,Class dataType) {
		this.propertyName=pName;
		this.defaultDirection=direction;
		this.dataType=dataType;
		this.description=description;
		this.longDescription=longDescription;
	}
	
	public abstract Object extractData(Task task);
	public final static SorterHelper ASKED_DELIVERY_DATE=new SorterHelper("ASKED_DELIVERY_DATE","ASC","sales order/production order asked delivery date","sales order/production order asked delivery date",Date.class) {

		@Override
		public Object extractData(Task task) {
			
			return task.getAskedDeliveryDateTime();
		}
		
	};
	public final static SorterHelper CUSTOM_PRIORITY=new SorterHelper("CUSTOM_PRIORITY","ASC","sales order/production order priority","custom sales order/production order priority",Integer.class) {

		@Override
		public Object extractData(Task task) {
			
			return task.getCustomPriority();
		}
		
	};
	public final static SorterHelper PROPERTIES_LIST[]= {CUSTOM_PRIORITY,ASKED_DELIVERY_DATE};
	public String getPropertyName() {
		return propertyName;
	}

	public String getDefaultDirection() {
		return defaultDirection;
	}

	public String getDescription() {
		return description;
	}

	public String getLongDescription() {
		return longDescription;
	}

	public Class getDataType() {
		return dataType;
	}
}
