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
import { ProductiveCompanyDto } from 'projects/openi40-scheduler-api/src/lib/';
import { TreeNodeGuiItem } from "./TreeNodeGuiItem";
import { PlantGuiItem } from './PlantGuiItem';
import { DemandGuiItem } from './DemandGuiItem';
import { BaseGuiItem } from './BaseGuiItem';

export class ProductiveCompanyGuiItem extends TreeNodeGuiItem<ProductiveCompanyDto, PlantGuiItem> {
  private _demand:DemandGuiItem=null;
  private _fullChilds:BaseGuiItem[]=[];
  protected getMainChildGuiItems(): PlantGuiItem[] {
    let outVector: PlantGuiItem[] = [];
    if (this.dataItem.plants) {
      this.dataItem.plants.forEach((entry) => {
        outVector.push(new PlantGuiItem(entry,this.graphicConfig,this.depth+1,this._nodesCounter));
      });
    }
    //this.demand;
    return outVector;
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
  get iconPath():string {
    return "company";
  }
  get objectType():string {
    return "ProductiveCompanyGuiItem";
  }
}
