package com.openi40.scheduler.model.resourcesdeps;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public interface IApsResourcesDependencyTreeObject {
	public ResourceDepsItemMetaInfo getResourceItemInfo();

	public Collection<IApsResourcesDependencyTreeObject> getResourceDependencyChilds();
	
	public default ResourceDepsTreeMetaInfo getResourceTreeMetaInfo() {
		ResourceDepsTreeMetaInfo thisNode = new ResourceDepsTreeMetaInfo();
		thisNode.setItem(getResourceItemInfo());
		Collection<IApsResourcesDependencyTreeObject> childs = getResourceDependencyChilds();
		for (IApsResourcesDependencyTreeObject currentChild : childs) {
			thisNode.getChilds().add(currentChild.getResourceTreeMetaInfo());
		}
		return thisNode;
	}
	 default Collection<IApsResourcesDependencyTreeObject> aggregateChilds(Collection< IApsResourcesDependencyTreeObject> ...elements) {
		List<IApsResourcesDependencyTreeObject> out=new ArrayList<IApsResourcesDependencyTreeObject>();
		if (elements!=null) {
			for (int i = 0; i < elements.length; i++) {
				out.addAll(elements[i]);
			}
		}
		return out;
	} 
}
