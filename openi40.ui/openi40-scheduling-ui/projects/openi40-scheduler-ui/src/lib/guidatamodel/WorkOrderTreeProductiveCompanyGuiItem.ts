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
import { ProductiveCompanyDto } from 'projects/openi40-scheduler-api/src/lib';
import { BaseGuiItem } from './BaseGuiItem';
import { DemandGuiItem } from './DemandGuiItem';
import { TreeNodeGuiItem } from './TreeNodeGuiItem';
import { WorkOrderTreePlantGuiItem } from './WorkOrderTreePlantGuiItem';


export class WorkOrderTreeProductiveCompanyGuiItem extends TreeNodeGuiItem<ProductiveCompanyDto, WorkOrderTreePlantGuiItem> {
  private _demand:DemandGuiItem=null;
  private _fullChilds:BaseGuiItem[]=[];
  protected getMainChildGuiItems(): WorkOrderTreePlantGuiItem[] {
    let outTree: WorkOrderTreePlantGuiItem[] = [];
    if (this.dataItem.plants) {
      this.dataItem.plants.forEach((plant) => {
        outTree.push(new WorkOrderTreePlantGuiItem(plant,this.graphicConfig, this.depth + 1, this._nodesCounter));
      });
    }
    return outTree;
  }
  get objectType():string {
    return "WorkOrderTreeProductiveCompanyGuiItem";
  }
  get iconPath():string {
    return "company";
  }
  public get demand():DemandGuiItem{
    if (!this._demand)
      this._demand=new DemandGuiItem(this.data,this.graphicConfig,this.depth+1,this._nodesCounter);
    return this._demand;
  }
  public get fullChildsList():BaseGuiItem[]{
    if (this._fullChilds.length==0){
      this.mainChilds.forEach((ch)=>{
        this._fullChilds.push(ch);
      })
      this._fullChilds.push(this.demand);
    }
    return this._fullChilds;
  }
}
