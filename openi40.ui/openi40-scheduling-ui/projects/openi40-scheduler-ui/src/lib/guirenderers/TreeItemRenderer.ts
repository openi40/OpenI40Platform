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
import { BaseGuiItem } from "../guidatamodel/BaseGuiItem";

import { AbstractSvgComponentRenderer } from './AbstractSvgComponentRenderer';
export class TreeItemRenderer<BoundType extends BaseGuiItem> extends AbstractSvgComponentRenderer<BoundType> {
  public label: Element = null;
  public icon: Element = null;
  public anchor: Element = null;
  public title: Element = null;
  public g: Element = null;
  public horizontalRect: Element = null;
  public treeItemIcon: Element = null;
  public treeItemRect: Element = null;
  public treeItemIconAnchor: Element = null;
  public create(): Element {
    this.g = this.createBoundElement("g", this.boundGuiItem);
    if (((this.boundGuiItem.nodeNumber + 1) % 2 === 0) && this.boundGuiItem.nodeNumber >= 2) {
      //this.boundGuiItem.graphicConfig.horizontalRectColor
      let rect = this.createElement("rect");
      this.horizontalRect = rect;
      this.setAttribute(this.horizontalRect, "width", "100%");
      this.setAttribute(this.horizontalRect, "fill", this.boundGuiItem.graphicConfig.horizontalRectColor);
      this.setAttribute(this.horizontalRect, "class", "horizontalRect");
      this.setAttribute(this.horizontalRect, "stroke-width", "1px");
      this.renderer2.appendChild(this.g, this.horizontalRect);
    }

    this.anchor = this.createElement("a");
    this.title = this.createElement("title");
    this.title.innerHTML = this.boundGuiItem.visibleLabel;
    let _label = this.createElement("text");
    this.label = _label;

    this.setAttribute(_label, "id", "lb_" + this.boundGuiItem.nodeNumber);
    let _objectName = this.boundGuiItem.objectType;
    this.setAttribute(_label, "class", "rc_label " + _objectName + " " + (this.boundGuiItem.additionalCSSClass ? " " + this.boundGuiItem.additionalCSSClass : ""));
    this.setAttribute(_label, "fill", "#101010");
    let visibleText = this.boundGuiItem.visibleLabel;
    _label.innerHTML = visibleText;
    this.setAttribute(this.anchor, "title", this.boundGuiItem.code + " " + this.boundGuiItem.description);
    this.setAttribute(this.anchor, "href", "javascript:void(0)");
    if (this.boundGuiItem.clickListener)
      this.anchor.addEventListener("click", this.boundGuiItem.clickListener);

    this.renderer2.appendChild(this.g, this.anchor);
    this.renderer2.appendChild(this.g, this.title);
    this.renderer2.appendChild(this.anchor, this.label);
    if (this.boundGuiItem["mainChilds"]) {
      this.treeItemRect=this.createElement("rect");
      this.treeItemIcon = this.createElement("path");
      this.updateTreeItemIcon();
      this.treeItemIconAnchor = this.createElement("a");
      //this.setAttribute(this.treeItemIcon, "class", "treeItemCtrl ");
      this.setAttribute(this.treeItemIconAnchor, "href", "javascript:void(0);");
      this.renderer2.appendChild(this.treeItemIconAnchor, this.treeItemIcon);
      this.renderer2.appendChild(this.treeItemIconAnchor, this.treeItemRect);
      this.renderer2.appendChild(this.g, this.treeItemIconAnchor);
    }
    let iconPath = this.boundGuiItem["iconPath"];
    if (iconPath) {
      this.icon = this.createElement("path");
      this.setAttribute(this.icon, "marker-mid", "url(#" + iconPath + ")");
      this.setAttribute(this.icon, "fill", "none");
      this.updateIconPath();
      this.renderer2.appendChild(this.g, this.icon);
    }
    this.update();
    return this.g;
  }
  public update(): void {
    if (this.horizontalRect) {
      this.setAttribute(this.horizontalRect, "x", "0");
      this.setAttribute(this.horizontalRect, "y", this.boundGuiItem.y.toString());
      this.setAttribute(this.horizontalRect, "height", this.boundGuiItem.graphicConfig.dimensions.cellHeight);
    }
    this.adaptElement(this.g, this.boundGuiItem);
    this.setAttribute(this.label, "x", (this.boundGuiItem.x + this.boundGuiItem.graphicConfig.dimensions.cellWidth * 2 + 5).toString());
    this.setAttribute(this.label, "y", "" + (this.boundGuiItem.y + this.boundGuiItem.graphicConfig.dimensions.labelsVerticalOffset).toString());
    this.setAttribute(this.label, "height", "" + this.boundGuiItem.graphicConfig.dimensions.cellHeight);
    this.setAttribute(this.label, "width", "" + this.boundGuiItem.graphicConfig.dimensions.cellWidth);
    if (this.icon) {
     this.updateIconPath();
    }
    if (this.boundGuiItem["mainChilds"] && this.treeItemIcon) {
      this.updateTreeItemIcon();
    }
  }
  private updateIconPath():void {
    let _d = "M" + this.boundGuiItem.x.toString() + " " + (this.boundGuiItem.y - this.boundGuiItem.graphicConfig.dimensions.cellHeight).toString() + " L" + (this.boundGuiItem.x + 1 + this.boundGuiItem.graphicConfig.dimensions.cellWidth).toString() + " " + (this.boundGuiItem.y - this.boundGuiItem.graphicConfig.dimensions.cellHeight).toString() + " Z";
      this.setAttribute(this.icon, "d", _d);
  }
  private updateTreeItemIcon(): void {
    this.setAttribute(this.treeItemRect,"x",this.boundGuiItem.x);
    this.setAttribute(this.treeItemRect,"y",this.boundGuiItem.y-this.boundGuiItem.graphicConfig.dimensions.cellHeight);
    this.setAttribute(this.treeItemRect,"height",this.boundGuiItem.graphicConfig.dimensions.cellHeight);
    this.setAttribute(this.treeItemRect,"width",this.boundGuiItem.graphicConfig.dimensions.cellWidth);
    this.setAttribute(this.treeItemRect,"fill","transparent");
    let _d = "M" + this.boundGuiItem.x.toString() + " " + (this.boundGuiItem.y - this.boundGuiItem.graphicConfig.dimensions.cellHeight/2-3.5).toString() + " L" + (this.boundGuiItem.x ).toString() + " " + (this.boundGuiItem.y - this.boundGuiItem.graphicConfig.dimensions.cellHeight/2-3.5).toString() + " Z";
    this.setAttribute(this.treeItemIcon, "d", _d);
    const folderIcon = this.boundGuiItem.opened === true ? "treeItemMarkerOpened" : "treeItemMarkerClosed";
    this.setAttribute(this.treeItemIcon, "marker-mid", "url(#" + folderIcon + ")");
    this.setAttribute(this.treeItemIcon, "fill", "none");
  }
  public drop(rootElement: Element): void {
    this.renderer2.removeChild(rootElement, this.g);
    this.label = null;
    this.anchor = null;
    this.g = null;
  }

}
