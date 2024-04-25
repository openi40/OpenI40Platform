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
import { WarehouseDto } from 'projects/openi40-scheduler-api/src/lib';
import { WarehouseGuiItem } from '../guidatamodel/WarehouseGuiItem';
import { WarehouseRenderer } from '../guirenderers/WarehouseRenderer';
import { TreeItemController } from './TreeItemController';

export class WarehouseController extends TreeItemController<WarehouseDto,WarehouseGuiItem,WarehouseRenderer>{
    public constructor(boundGuiItem:WarehouseGuiItem,renderer2:Renderer2){
        super(boundGuiItem,renderer2, (_d: WarehouseGuiItem, _r: Renderer2) => {return new WarehouseRenderer(_r,_d);})
    }
    protected initializeGuiEventListeners():void {
      this._renderer.anchor.addEventListener("click",this);
    }
}
