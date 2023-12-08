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

import com.openi40.scheduler.client.model.ClientDto;

public class GraphicContextItemDto<GraphicContentDataType> extends ClientDto {
	GraphicContentDataType graphicContent = null;

	public GraphicContentDataType getGraphicContent() {
		return graphicContent;
	}

	public void setGraphicContent(GraphicContentDataType graphicContent) {
		this.graphicContent = graphicContent;
	}
}
