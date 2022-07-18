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
import { BaseGuiItem } from './BaseGuiItem';
import { TreeNodeGuiItem } from './TreeNodeGuiItem';
import { WarehouseGuiItem } from './WarehouseGuiItem';
import { WorkOrderTreeWorkOrderGuiItem } from './WorkOrderTreeWorkOrderGuiItem';


export class WorkOrderTreePlantGuiItem extends TreeNodeGuiItem<PlantDto, WorkOrderTreeWorkOrderGuiItem> {u
  private fullChilds:BaseGuiItem[]=null;
  private _warehouses:WarehouseGuiItem[]=null;
  protected getMainChildGuiItems(): WorkOrderTreeWorkOrderGuiItem[] {
    let outTree: WorkOrderTreeWorkOrderGuiItem[] = [];
    if (this.dataItem.workOrders) {
      this.dataItem.workOrders.forEach((wo) => {
        outTree.push(new WorkOrderTreeWorkOrderGuiItem(wo,this.graphicConfig, this.depth + 1, this._nodesCounter));
      });
    }
    return outTree;
  }
  public get warehouses():WarehouseGuiItem[]{
    if (!this._warehouses) {
      this._warehouses=[];
      this.data.warehouses.forEach((wh)=>{
        this._warehouses.push(new WarehouseGuiItem(wh,this.graphicConfig,this.depth+1,this._nodesCounter));
      });
    }
    return this._warehouses;
  }
  public get fullChildsList():BaseGuiItem[]{
    if (!this.fullChilds){
      this.fullChilds=[];
      this.warehouses.forEach((wh)=>{
        this.fullChilds.push(wh)
      })
      this.mainChilds.forEach((m)=>{
        this.fullChilds.push(m);
      })
    }
    return this.fullChilds;
  }
  get objectType():string {
    return "WorkOrderTreePlantGuiItem";
  }
  get iconPath():string {
    return "plant";
  }
}
