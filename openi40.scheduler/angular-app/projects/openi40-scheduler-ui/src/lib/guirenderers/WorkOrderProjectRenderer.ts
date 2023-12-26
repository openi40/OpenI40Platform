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
import { WorkOrderDto } from 'projects/openi40-scheduler-api/src/lib';
import { GuiController } from '../guicontrol/GuiController';
import { WorkOrderProjectGuiItem } from '../guidatamodel/WorkOrderProjectGuiItem';
import { AbstractSvgComponentRenderer } from './AbstractSvgComponentRenderer';

export class WorkOrderProjectRenderer extends AbstractSvgComponentRenderer<WorkOrderProjectGuiItem>{
  public anchor: Element=null;
  public executionElement: Element=null;
  public g: Element=null;
  public label: Element=null;
  public title: Element=null;
  public create(): Element {
    this.g=this.createBoundElement("g",this.boundGuiItem);
    this.boundGuiItem.svgElement=this.g;
    this.anchor=this.createElement("a");
    this.executionElement=this.createElement("rect");
    this.setAttribute(this.executionElement,"id","WorkOrder"+this.boundGuiItem.id);
    var phaseCodes = this.boundGuiItem.data.code;
    //this.setAttribute(this.anchor,"title","Work order "+phaseCodes);
    this.setAttribute(this.anchor,"href","javascript:void(0);");
    this.title=this.createElement("title");
    this.title.innerHTML= this.boundGuiItem.visibleLabel;
    if (this.boundGuiItem.clickListener)
      this.anchor.addEventListener("click",this.boundGuiItem.clickListener);
    this.renderer2.appendChild(this.anchor,this.executionElement);
    this.renderer2.appendChild(this.g,this.anchor);
    this.renderer2.appendChild(this.g,this.title);
    if (this.isCanRenderLabel) {
      this.label=this.createLabel();
      this.renderer2.appendChild(this.anchor,this.label);
    }
    this.update();
    return this.g;
}
public update(): void {
  var phaseCodes = this.boundGuiItem.data.code;
  this.adaptElement(this.g,this.boundGuiItem);
   this.adaptElement(this.executionElement,this.boundGuiItem);
    this.setAttribute(this.executionElement,"class", "workStyle"+(this.boundGuiItem.additionalCSSClass?" "+this.boundGuiItem.additionalCSSClass:""));
    //this.setAttribute(this.executionElement,"title", "WorkOrder " + phaseCodes);
    if (this.boundGuiItem?.data?.color) {
      this.setAttribute(this.executionElement,"fill",this.boundGuiItem?.data?.color);
    }else {
      this.renderer2.removeAttribute(this.executionElement,"fill");

    }
}

}
