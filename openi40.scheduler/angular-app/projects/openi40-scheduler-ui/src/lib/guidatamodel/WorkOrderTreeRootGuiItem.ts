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
import { ApsDataDto } from 'projects/openi40-scheduler-api/src/lib';
import { TreeNodeGuiItem } from './TreeNodeGuiItem';
import { WorkOrderTreeProductiveCompanyGuiItem } from './WorkOrderTreeProductiveCompanyGuiItem';


export class WorkOrderTreeRootGuiItem extends TreeNodeGuiItem<ApsDataDto, WorkOrderTreeProductiveCompanyGuiItem> {
  protected getMainChildGuiItems(): WorkOrderTreeProductiveCompanyGuiItem[] {
    let outTree: WorkOrderTreeProductiveCompanyGuiItem[] = [];
    if (this.dataItem.productiveCompanies) {
      this.dataItem.productiveCompanies.forEach((comp) => {
        outTree.push(new WorkOrderTreeProductiveCompanyGuiItem(comp,this.graphicConfig, this.depth + 1, this._nodesCounter));
      });
    }
    return outTree;
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
}
