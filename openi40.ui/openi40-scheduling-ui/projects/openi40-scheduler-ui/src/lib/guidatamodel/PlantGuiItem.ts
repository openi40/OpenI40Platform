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
import { PlantDto } from 'projects/openi40-scheduler-api/src/lib/';
import { TreeNodeGuiItem } from "./TreeNodeGuiItem";
import { WorkOrderTreeWorkOrderGuiItem } from './WorkOrderTreeWorkOrderGuiItem';
import { DepartmentGuiItem } from './DepartmentGuiItem';
import { WarehouseGuiItem } from './WarehouseGuiItem';
import { NodesCounter } from './NodesCounter';
import { BaseGuiItem } from './BaseGuiItem';
import { Openi40GraphicConfiguration } from '../screenconstants';

export class PlantGuiItem extends TreeNodeGuiItem<PlantDto, DepartmentGuiItem> {
  public warehouses: WarehouseGuiItem[] = [];
  private fullChilds:BaseGuiItem[]=[];

  public constructor(dataItem: PlantDto,graphicConfig:Openi40GraphicConfiguration,protected _depth:number,protected _nodesCounter:NodesCounter) {
    super(dataItem,graphicConfig,_depth,_nodesCounter);
    if (this.dataItem.warehouses) {
      this.dataItem.warehouses.forEach((wh) => { this.warehouses.push(new WarehouseGuiItem(wh,this.graphicConfig,this.depth+1,_nodesCounter)); });
    }
    this.warehouses.forEach((wh)=>{
      this.fullChilds.push(wh);
    })
    this.mainChilds.forEach((ch)=>{
      this.fullChilds.push(ch);
    })
  }
  protected getMainChildGuiItems(): DepartmentGuiItem[] {
    let outVector: DepartmentGuiItem[] = [];
    if (this.dataItem.departments) {
      this.dataItem.departments.forEach((entry) => {
        outVector.push(new DepartmentGuiItem(entry,this.graphicConfig,this.depth+1,this._nodesCounter));
      });
    }
    return outVector;
  }
  public get fullChildsList():BaseGuiItem[]{
    return this.fullChilds;
  }
  get objectType():string {
    return "PlantGuiItem";
  }
  get iconPath():string {
    return "plant";
  }
}
