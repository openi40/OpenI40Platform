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
import { TreeItemGuiItem } from '../guidatamodel/TreeItemGuiItem';
import { TreeItemRenderer } from '../guirenderers/TreeItemRenderer';
import { TreeNodeGuiItem } from '../guidatamodel/TreeNodeGuiItem';
import { TreeNodeRenderer } from '../guirenderers/TreeNodeRenderer';
import { TreeItemController } from './TreeItemController';
import { Renderer2 } from '@angular/core';
import { IControllerCallback } from './IControllerCallback';
import { IGuiController } from './IGuiController';
export class TreeNodeController<
  BoundData,
  BoundDataGuiItemType extends TreeNodeGuiItem<BoundData, BoundChildGuiItemType>,
  BoundDataGuiRendererType extends TreeNodeRenderer<BoundData, BoundDataGuiItemType, ChildDataType, BoundChildGuiItemType, ItemRendererType>,
  ChildDataType,
  BoundChildGuiItemType extends TreeItemGuiItem<ChildDataType>,
  ItemRendererType extends TreeItemRenderer<BoundChildGuiItemType>,
  ItemControllerType extends TreeItemController<ChildDataType, BoundChildGuiItemType, ItemRendererType>
  >
  extends TreeItemController<BoundData, BoundDataGuiItemType, BoundDataGuiRendererType> {
  protected _mainChilds: ItemControllerType[] = null;
  public constructor(_data: BoundDataGuiItemType,
                      protected renderer2: Renderer2,
                      rendererFactory:(_d:BoundDataGuiItemType,_r:Renderer2)=>BoundDataGuiRendererType,
                      childFactory: (d:BoundChildGuiItemType[],r:Renderer2) => ItemControllerType[]) {
    super(_data, renderer2,rendererFactory);
    this._mainChilds = childFactory(_data.mainChilds,renderer2);
  }
  public get mainChilds(): ItemControllerType[] {
    return this._mainChilds;
  }
  public get fullChildsList():IGuiController[]{
    return this._mainChilds;
  }
  public show(rootElement:Element):Element {
    let generatedRoot=super.show(rootElement);
    if (this.fullChildsList) {
      this.fullChildsList.forEach((child)=>{
        child.show(generatedRoot);
      })
    }
    return generatedRoot;
  }
  protected initializeGuiEventListeners(){
    super.initializeGuiEventListeners();
    if (this._renderer.treeItemIcon) {
      this._renderer.treeItemIcon.addEventListener("click",(evt: Event)=>{
        this.boundGuiItem.toggleOpened();

        this.fireEvent("redraw",evt);
      });
    }
    if (this._renderer.treeItemIconAnchor) {
      this._renderer.treeItemIconAnchor.addEventListener("click",(evt: Event)=>{
        this.boundGuiItem.toggleOpened();

        this.fireEvent("redraw",evt);
      });
    }
  }
  public addControllerCallback(callback:IControllerCallback):void {
    super.addControllerCallback(callback);
    if (this.fullChildsList) {
      this.fullChildsList.forEach((ch)=>{ch.addControllerCallback(callback);})
    }
  }
}
