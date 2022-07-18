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
import { WorkCenterDto } from 'projects/openi40-scheduler-api/src/lib/';
import { TreeNodeGuiItem } from "./TreeNodeGuiItem";
import { MachineGuiItem } from './MachineGuiItem';

export class WorkCenterGuiItem extends TreeNodeGuiItem<WorkCenterDto, MachineGuiItem> {
  protected getMainChildGuiItems(): MachineGuiItem[] {
    let outVector: MachineGuiItem[] = [];
    if (this.dataItem.resources) {
      this.dataItem.resources.forEach((entry) => {
        outVector.push(new MachineGuiItem(entry,this.graphicConfig,this.depth+1,this._nodesCounter));
      });
    }
    return outVector;
  }
  get objectType():string {
    return "WorkCenterGuiItem";
  }
  get iconPath():string {
    return "workcenter";
  }
}
