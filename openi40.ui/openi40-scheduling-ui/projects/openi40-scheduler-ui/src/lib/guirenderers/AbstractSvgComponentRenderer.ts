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
import { BaseGuiItem } from "../guidatamodel/BaseGuiItem";
export const SVG_NAMESPACE="http://www.w3.org/2000/svg";
export const  FONT_WIDTH:number=8;
export abstract class AbstractSvgComponentRenderer<GuiItemType extends BaseGuiItem> {
  constructor( protected renderer2: Renderer2, protected boundGuiItem: GuiItemType) {
  }
  protected setAttribute(elem: Element, attribName: string, value: any) {
    this.renderer2.setAttribute(elem, attribName, value);
  }
  protected createHtmlElement(elemType: string): Element {
    return this.renderer2.createElement(elemType);
  }
  protected createElement(elemType: string): Element {
    let _element=this.renderer2.createElement(elemType, SVG_NAMESPACE);
    this.renderedElements.push(_element);
    return _element;
  }
  protected adaptElement(item: Element, guiItem: BaseGuiItem): void {
    this.renderer2.setAttribute(item, "x", guiItem.x.toString());
    this.renderer2.setAttribute(item, "y", guiItem.y.toString());
    this.renderer2.setAttribute(item, "width", guiItem.width.toString());
    this.renderer2.setAttribute(item, "height", guiItem.height.toString());
  }
  protected createBoundElement(elemType: string = "rect", guiItem: BaseGuiItem): Element {
    let item: Element = this.createElement(elemType);
    this.adaptElement(item, guiItem);
    guiItem.svgElement = item;
    return item;
  }
  protected renderedElements:Element[]=[];
  public setVisibility(visibility:boolean):void {
    if (this.renderedElements) {
      this.renderedElements.forEach((element)=>{
          this.setAttribute(element,"visibility", visibility===true?"visible":"hidden" );
      });
    }
  }
  protected get isCanRenderLabel():boolean{
    return this.boundGuiItem.visibleLabel!=null && this.labelSize<=this.boundGuiItem.width;
  }
  protected get labelSize():number{
    return this.boundGuiItem.graphicConfig.dimensions.characterWidth*this.boundGuiItem.visibleLabel.length;
  }
  protected createLabel():Element{
    let label:Element=this.createBoundElement("text",this.boundGuiItem);
    this.setAttribute(label,"class",this.boundGuiItem.constructor.name+"-label"+(this.boundGuiItem.additionalCSSClass?" "+this.boundGuiItem.additionalCSSClass:""));
    label.innerHTML=this.boundGuiItem.visibleLabel;
    this.renderer2.setAttribute(label, "x", this.boundGuiItem.x.toString());
    this.renderer2.setAttribute(label, "y", (this.boundGuiItem.y+this.boundGuiItem.height+this.boundGuiItem.graphicConfig.dimensions.labelsVerticalOffset).toString());
    this.renderer2.setAttribute(label, "width", this.labelSize.toString());
    this.renderer2.setAttribute(label, "height", this.boundGuiItem.height.toString());
    return label;
  }
  protected updateLabel(label:Element):void {
    let _labelSize:number=this.labelSize;
    this.renderer2.setAttribute(label, "x", (this.boundGuiItem.x+(this.boundGuiItem.width-this.labelSize)/2).toString());
    this.renderer2.setAttribute(label, "y", (this.boundGuiItem.y+this.boundGuiItem.height+this.boundGuiItem.graphicConfig.dimensions.labelsVerticalOffset).toString());
    this.renderer2.setAttribute(label, "width", this.labelSize.toString());
    this.renderer2.setAttribute(label, "height", this.boundGuiItem.height.toString());
  }



  public abstract create(): Element;
  public abstract update():void;
  public drop(rootElement:Element):void {

  }
}
