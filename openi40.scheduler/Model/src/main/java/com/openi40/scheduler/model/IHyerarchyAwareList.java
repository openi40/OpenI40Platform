package com.openi40.scheduler.model;

import java.util.List;

import com.openi40.scheduler.common.aps.IApsObject;
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
public interface IHyerarchyAwareList<ParentType extends IHyerarchyAwareNode,ChildType extends IApsObject>  extends List<ChildType>{
	public Class<ParentType> getParentType();
	public Class<ChildType> getChildType();
	public ParentType getParent();
	public String getRelationName();
	public ChildType findById(String id);
	public ChildType findByCode(String code);
	public boolean hasChildWithId(String id);
	public boolean hasChildWithCode(String code);
}
