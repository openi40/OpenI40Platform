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
package com.openi40.scheduler.client.graphs.model;

import java.util.ArrayList;
import java.util.List;


public class GraphicContextNodeDto<GraphicContentDataType> extends GraphicContextItemDto<GraphicContentDataType> {
	List<GraphicContextNodeDto<GraphicContentDataType>> nodes = new ArrayList<GraphicContextNodeDto<GraphicContentDataType>>();
	List<GraphicContextItemDto<GraphicContentDataType>> leafs = new ArrayList<GraphicContextItemDto<GraphicContentDataType>>();
	public List<GraphicContextNodeDto<GraphicContentDataType>> getNodes() {
		return nodes;
	}
	public void setNodes(List<GraphicContextNodeDto<GraphicContentDataType>> nodes) {
		this.nodes = nodes;
	}
	public List<GraphicContextItemDto<GraphicContentDataType>> getLeafs() {
		return leafs;
	}
	public void setLeafs(List<GraphicContextItemDto<GraphicContentDataType>> leafs) {
		this.leafs = leafs;
	}
}
