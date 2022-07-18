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
package com.openi40.generical.dbintegration.utils;

class TypesMapItem {
	public Class classe = null;
	public int sqlType = -1;

	public TypesMapItem(Class classe, int sqlType) {
		this.classe = classe;
		this.sqlType = sqlType;
	}
}
