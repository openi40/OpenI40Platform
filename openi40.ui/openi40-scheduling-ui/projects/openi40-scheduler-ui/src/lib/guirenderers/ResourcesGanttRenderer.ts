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
import { Renderer2 } from '@angular/core';
import { ApsDataGuiItem } from "../guidatamodel/ApsDataGuiItem";
import { AbstractSvgComponentRenderer } from './AbstractSvgComponentRenderer';
export class ResourcesGanttRenderer extends AbstractSvgComponentRenderer<ApsDataGuiItem> {

  public constructor( renderer: Renderer2, boundGuiItem: ApsDataGuiItem) {
    super( renderer, boundGuiItem);

  }
  public create(): Element {
    var _containerDiv = this.createHtmlElement("div");
    this.setAttribute(_containerDiv, "class", "p-d-flex p-flex-row row openi40-resource-gantt");

    return _containerDiv;
  }
  public update(): void {

  }
}
