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
import { WorkOrderDto } from 'projects/openi40-scheduler-api/src/lib/';
import { TreeNodeGuiItem } from "./TreeNodeGuiItem";
import { TaskGuiItem } from './TaskGuiItem';
import {WorkOrderTreeTaskGuiItem} from './WorkOrderTreeTaskGuiItem'
export class WorkOrderTreeWorkOrderGuiItem extends TreeNodeGuiItem<WorkOrderDto, WorkOrderTreeTaskGuiItem> {
  protected getMainChildGuiItems(): WorkOrderTreeTaskGuiItem[] {
    let outVector: WorkOrderTreeTaskGuiItem[] = [];
    if (this.dataItem.tasks) {
      this.dataItem.tasks.forEach((entry) => {
        outVector.push(new WorkOrderTreeTaskGuiItem(entry,this.graphicConfig,this.depth+1,this._nodesCounter));
      });
    }
    return outVector;
  }
  get objectType():string {
    return "WorkOrderTreeWorkOrderGuiItem";
  }
  get iconPath():string {
    return "workorder";
  }
}
