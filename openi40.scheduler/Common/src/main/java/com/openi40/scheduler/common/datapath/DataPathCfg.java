package com.openi40.scheduler.common.datapath;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
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
public class DataPathCfg {
	protected Class searchedType = null;

	public Class getSearchedType() {
		return searchedType;
	}

	public DataPathCfg(Class searchedType) {
		this.searchedType = searchedType;
	}

	Map<Class, List<String>> paths = new HashMap<Class, List<String>>();

	public List<String> getPathsFrom(Class fromClass) {
		List<String> outList = new ArrayList<String>();
		if (paths.containsKey(fromClass))
			return paths.get(fromClass);
		Class[] interfaces = fromClass.getInterfaces();
		if (interfaces != null) {
			for (Class iface : interfaces) {
				if (paths.containsKey(iface))
					return paths.get(iface);
			}
		}
		Set<Entry<Class, List<String>>> set = paths.entrySet();
		for (Entry<Class, List<String>> entry : set) {
			if (entry.getKey().isAssignableFrom(fromClass)) {
				outList.addAll(entry.getValue());
			}
		}
		return outList;
	}

	public Map<Class, List<String>> getEntries() {
		return new HashMap(paths);
	}

	public DataPathCfg withPath(Class fromCLass, String path) {
		if (!paths.containsKey(fromCLass)) {
			paths.put(fromCLass, new ArrayList<String>());
		}
		paths.get(fromCLass).add(path);

		return this;
	}
}
