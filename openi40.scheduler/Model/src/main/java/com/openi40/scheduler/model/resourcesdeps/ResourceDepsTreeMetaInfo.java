package com.openi40.scheduler.model.resourcesdeps;

import java.util.ArrayList;
import java.util.List;

public class ResourceDepsTreeMetaInfo {
	 ResourceDepsItemMetaInfo item=null;
	 List<ResourceDepsTreeMetaInfo> childs=new ArrayList<ResourceDepsTreeMetaInfo>();
	public ResourceDepsItemMetaInfo getItem() {
		return item;
	}
	public void setItem(ResourceDepsItemMetaInfo item) {
		this.item = item;
	}
	public List<ResourceDepsTreeMetaInfo> getChilds() {
		return childs;
	}
	public void setChilds(List<ResourceDepsTreeMetaInfo> childs) {
		this.childs = childs;
	}
}
