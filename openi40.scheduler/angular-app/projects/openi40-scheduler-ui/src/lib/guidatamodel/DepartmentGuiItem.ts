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
import { DepartmentDto } from 'projects/openi40-scheduler-api/src/lib/';
import { TreeNodeGuiItem } from "./TreeNodeGuiItem";
import { WorkCenterGuiItem } from './WorkCenterGuiItem';
import { ResourceGroupGuiItem } from './ResourceGroupGuiItem';
import { NodesCounter } from './NodesCounter';
import { Openi40GraphicConfiguration } from '../screenconstants';

export class DepartmentGuiItem extends TreeNodeGuiItem<DepartmentDto, WorkCenterGuiItem> {
  public secondaryResources: ResourceGroupGuiItem[] = [];
  public constructor(dataItem: DepartmentDto,graphicConfig:Openi40GraphicConfiguration,_depth:number,_nodesCounter:NodesCounter) {
    super(dataItem,graphicConfig,_depth,_nodesCounter);
    if (dataItem.secondaryResourceGroups) {
      dataItem.secondaryResourceGroups.forEach((sr) => { this.secondaryResources.push(new ResourceGroupGuiItem(sr,this.graphicConfig)); });
    }
  }
  protected getMainChildGuiItems(): WorkCenterGuiItem[] {
    let outVector: WorkCenterGuiItem[] = [];
    if (this.dataItem.workCenters) {
      this.dataItem.workCenters.forEach((entry) => {
        outVector.push(new WorkCenterGuiItem(entry,this.graphicConfig,this.depth+1,this._nodesCounter));
      });
    }
    return outVector;
  }
  get iconPath():string {
    return "department";
  }
  get objectType():string {
    return "DepartmentGuiItem";
  }
}
