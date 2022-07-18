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
import { ElementRef, Renderer2 } from '@angular/core';
import { ApsDataGuiItem } from "../guidatamodel/ApsDataGuiItem";
import { ResourcesRootGuiItem } from "../guidatamodel/ResourcesRootGuiItem";
import { AbstractSvgComponentRenderer } from './AbstractSvgComponentRenderer';
import { ResourcesPanelRenderer } from './ResourcesPanelRenderer';
import { GanttTimesheetRenderer } from './GanttTimesheetRenderer';
import { NodesCounter } from '../guidatamodel/NodesCounter';
import { WorkOrdersTreeApsDataGuiItem } from '../guidatamodel/WorkOrdersTreeApsDataGuiItem';
export class WorkOrdersGanttRenderer extends AbstractSvgComponentRenderer<WorkOrdersTreeApsDataGuiItem> {

  public constructor( renderer: Renderer2, boundGuiItem: WorkOrdersTreeApsDataGuiItem) {
    super( renderer, boundGuiItem);

  }
  public create(): Element {
    var _containerDiv = this.createHtmlElement("div");
    this.setAttribute(_containerDiv, "class", "p-d-flex p-flex-row row openi40-workorders-gantt");

    return _containerDiv;
  }
  public update(): void {

  }
}
