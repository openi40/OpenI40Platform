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
import { ApsDataDto } from 'projects/openi40-scheduler-api/src/lib/';
import { TreeNodeGuiItem } from "./TreeNodeGuiItem";
import { ProductiveCompanyGuiItem } from "./ProductiveCompanyGuiItem";
import { Openi40GraphicConfiguration } from '../screenconstants';
import { NodesCounter } from './NodesCounter';
export class ResourcesRootGuiItem extends TreeNodeGuiItem<ApsDataDto, ProductiveCompanyGuiItem> {
  constructor(dataItem: ApsDataDto, graphicConfig: Openi40GraphicConfiguration, _depth: any, _nodesCounter: NodesCounter, minWidth?: number, minHeight?: number) {
    super(dataItem,graphicConfig,_depth,_nodesCounter,minWidth,minHeight);
    this._y=graphicConfig.dimensions.timesheetHeaderHeight;
  }
  protected getMainChildGuiItems(): ProductiveCompanyGuiItem[] {
    let outVector: ProductiveCompanyGuiItem[] = [];
    if (this.dataItem.productiveCompanies) {
      this.dataItem.productiveCompanies.forEach((entry) => {
        outVector.push(new ProductiveCompanyGuiItem(entry,this.graphicConfig,this.depth+1,this._nodesCounter));
      });
    }
    return outVector;
  }
  public get yParentOffset() {
    return this.graphicConfig.dimensions.timesheetHeaderHeight;
  }
  public recalculateCoordinates():void{
    super.recalculateCoordinates();
    let yOffset=this.y+ this.graphicConfig.dimensions.timesheetHeaderHeight;
    if (this.fullChildsList) this.fullChildsList.forEach((n)=>{
      n.x=this.x+n.xParentOffset;
      n.y=yOffset+n.yParentOffset;
      yOffset+=n.height;
      n.recalculateCoordinates();
    }
    );
  }
  public get code():string {return "";}
  public get description():string {return "";}
}
