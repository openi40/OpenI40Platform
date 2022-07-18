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
import { PlantDto, WorkOrderDto } from 'projects/openi40-scheduler-api/src/lib';
import { TreeNodeController } from './TreeNodeController';
import { WorkOrderTreeWorkOrderGuiItem } from '../guidatamodel/WorkOrderTreeWorkOrderGuiItem';
import { WorkOrderTreeWorkOrderRenderer } from '../guirenderers/WorkOrderTreeWorkOrderRenderer';
import { WorkOrderTreePlantGuiItem } from '../guidatamodel/WorkOrderTreePlantGuiItem';
import { WorkOrderTreePlantRenderer } from '../guirenderers/WorkOrderTreePlantRenderer';
import { WorkOrderTreeWorkOrderController } from "./WorkOrderTreeWorkOrderController";
import { IGuiController } from './IGuiController';
import {WarehouseController} from './WarehouseController'
export class WorkOrderTreePlantController extends TreeNodeController<PlantDto, WorkOrderTreePlantGuiItem, WorkOrderTreePlantRenderer, WorkOrderDto, WorkOrderTreeWorkOrderGuiItem, WorkOrderTreeWorkOrderRenderer, WorkOrderTreeWorkOrderController> {
  private fullChilds:IGuiController[]=[];
  public warehouses:WarehouseController[]=[];
  public constructor(boundGuiItem: WorkOrderTreePlantGuiItem, renderer2: Renderer2) {
    super(boundGuiItem, renderer2,
      (_d: WorkOrderTreePlantGuiItem, _r: Renderer2) => { return new WorkOrderTreePlantRenderer(_r, _d); },
      (d: WorkOrderTreeWorkOrderGuiItem[], r: Renderer2) => {
        let outArray: WorkOrderTreeWorkOrderController[] = [];
        if (d)
          d.forEach((item) => {
            outArray.push(new WorkOrderTreeWorkOrderController(item, renderer2));
          });
        return outArray;
      }
    );
      this.boundGuiItem.warehouses.forEach((wh)=>{
        this.warehouses.push(new WarehouseController(wh,renderer2));
      })
      this.warehouses.forEach((wh)=>{
        this.fullChilds.push(wh);
      })
      this.mainChilds.forEach((m)=>{
        this.fullChilds.push(m);
      })
  }
  public get fullChildsList():IGuiController[]{
    return this.fullChilds;
  }
}
