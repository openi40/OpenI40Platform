package com.openi40.scheduler.model.resourcesdeps;

import com.openi40.scheduler.common.aps.IMetaInfo;
import com.openi40.scheduler.model.AbstractApsObject;

public class ResourceDepsItemMetaInfo {
	String resourceType = null;
	String resourceUniqueCode = null;
	boolean leafNode = false;
	boolean metaInfoNode = false;
	boolean resourceGroup = false;
	boolean resource = false;
	public static ResourceDepsItemMetaInfo of(AbstractApsObject o) {
		return new ResourceDepsItemMetaInfo(o);
	}
	public ResourceDepsItemMetaInfo() {

	}
	
	public ResourceDepsItemMetaInfo(String resourceType, String resourceUniqueCode, boolean leafNode) {
		this.resourceType = resourceType;
		this.resourceUniqueCode = resourceUniqueCode;
		this.leafNode = leafNode;
	}
	
	public ResourceDepsItemMetaInfo(AbstractApsObject object) {
		this.resourceType = object.getClass().getSimpleName();
		this.resourceUniqueCode =this.resourceType+":"+object.getCode();
		this.metaInfoNode= object instanceof IMetaInfo;

	}

	public String getResourceType() {
		return resourceType;
	}

	public void setResourceType(String resourceType) {
		this.resourceType = resourceType;
	}

	public String getResourceUniqueCode() {
		return resourceUniqueCode;
	}

	public void setResourceUniqueCode(String resourceUniqueCode) {
		this.resourceUniqueCode = resourceUniqueCode;
	}

	public boolean isLeafNode() {
		return leafNode;
	}

	public void setLeafNode(boolean leafNode) {
		this.leafNode = leafNode;
	}

	public boolean isResourceGroup() {
		return resourceGroup;
	}

	public void setResourceGroup(boolean resourceGroup) {
		this.resourceGroup = resourceGroup;
	}

	public boolean isResource() {
		return resource;
	}

	public void setResource(boolean resource) {
		this.resource = resource;
	}

	public boolean isMetaInfoNode() {
		return metaInfoNode;
	}

	public void setMetaInfoNode(boolean metaInfoNode) {
		this.metaInfoNode = metaInfoNode;
	}

}
