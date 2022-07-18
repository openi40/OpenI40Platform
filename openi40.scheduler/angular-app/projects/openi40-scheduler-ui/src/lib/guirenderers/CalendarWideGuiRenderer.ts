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
import { PlantDto } from 'projects/openi40-scheduler-api/src/lib';
import { CalendarWideGuiItem } from '../guidatamodel/CalendarWideGuiItem';
import { PlantGuiItem } from '../guidatamodel/PlantGuiItem';
import { TreeItemGuiItem } from '../guidatamodel/TreeItemGuiItem';
import { AbstractSvgComponentRenderer } from './AbstractSvgComponentRenderer';

export class CalendarWideGuiRenderer<DataType,RelatedTreeItem extends TreeItemGuiItem<DataType>,CalendarWideGuiType extends CalendarWideGuiItem<DataType,RelatedTreeItem>> extends AbstractSvgComponentRenderer<CalendarWideGuiType>{
  public g:Element=null;
  public anchor:Element=null;
  public rect:Element=null;
  public create(): Element {
    this.g=this.createElement("g");
    this.anchor=this.createElement("a");
    this.rect=this.createElement("rect");
    this.setAttribute(this.g,"id","WIDE"+this.boundGuiItem.id);
    this.renderer2.appendChild(this.g,this.anchor);
    this.renderer2.appendChild(this.anchor,this.rect);
    this.setAttribute(this.anchor,"href","javascript:void(0);");
    this.setAttribute(this.rect,"class",this.boundGuiItem.constructor.name+(this.boundGuiItem.additionalCSSClass?" "+this.boundGuiItem.additionalCSSClass:""));
    this.update();
    return this.g;
  }
  public update(): void {
    this.adaptElement(this.g,this.boundGuiItem);
    this.adaptElement(this.rect,this.boundGuiItem);
  }
  public drop(root:Element):void {
    this.renderer2.removeChild(root,this.g);
    this.g=null;
    this.anchor=null;
    this.rect=null;
  }
}

