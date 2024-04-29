package com.openi40.scheduler.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.openi40.scheduler.common.aps.IApsObject;
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
public class HyerarchyAwareList<ParentType extends IHyerarchyAwareNode, ChildType extends IApsObject>
		extends ArrayList<ChildType> implements IHyerarchyAwareList<ParentType, ChildType>, Serializable {
	Map<String, ChildType> childsIdAccessor = new HashMap<String, ChildType>();
	Map<String, ChildType> childsCodeAccessor = new HashMap<String, ChildType>();
	/**
	 * 
	 */
	private static final long serialVersionUID = -1460227605338935862L;
	ParentType parent = null;
	String relationName = null;
	Class<ParentType> pType = null;
	Class<ChildType> cType = null;

	public HyerarchyAwareList(Class<ParentType> pType, Class<ChildType> cType, ParentType parent, String relationName) {
		this.parent = parent;
		this.relationName = relationName;
		this.pType = pType;
		this.cType = cType;
	}
	
	@Override
	public ParentType getParent() {
		return this.parent;
	}

	@Override
	public String getRelationName() {
		return this.relationName;
	}

	@Override
	public boolean add(ChildType e) {
		childsIdAccessor.put(e.getId(), e);
		childsCodeAccessor.put(e.getCode(), e);
		return super.add(e);
	}

	@Override
	public void add(int index, ChildType e) {
		childsIdAccessor.put(e.getId(), e);
		childsCodeAccessor.put(e.getCode(), e);
		super.add(index, e);
	}

	@Override
	public boolean addAll(Collection<? extends ChildType> c) {
		for (ChildType e : c) {
			childsIdAccessor.put(e.getId(), e);
			childsCodeAccessor.put(e.getCode(), e);
		}
		return super.addAll(c);
	}

	@Override
	public boolean addAll(int index, Collection<? extends ChildType> c) {
		for (ChildType e : c) {
			childsIdAccessor.put(e.getId(), e);
			childsCodeAccessor.put(e.getCode(), e);
		}
		return super.addAll(index, c);
	}

	@Override
	public void clear() {
		childsIdAccessor.clear();
		childsCodeAccessor.clear();
		super.clear();
	}

	@Override
	public ChildType remove(int index) {
		ChildType removed = super.remove(index);
		if (removed != null) {
			childsIdAccessor.remove(removed.getId());
			childsCodeAccessor.remove(removed.getCode());
		}
		return removed;
	}

	@Override
	public boolean remove(Object o) {
		boolean rmvd = super.remove(o);
		if (rmvd && o != null && o instanceof IApsObject) {
			IApsObject removed = (IApsObject) o;
			childsIdAccessor.remove(removed.getId());
			childsCodeAccessor.remove(removed.getCode());
		}
		return rmvd;
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		boolean oneIsRemoved = false;
		if (c != null) {
			for (Object object : c) {
				this.remove(object);
			}
		}
		return oneIsRemoved;
	}

	@Override
	public Class<ParentType> getParentType() {

		return null;
	}

	@Override
	public Class<ChildType> getChildType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ChildType findById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ChildType findByCode(String code) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean hasChildWithId(String id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean hasChildWithCode(String code) {
		// TODO Auto-generated method stub
		return false;
	}

}
