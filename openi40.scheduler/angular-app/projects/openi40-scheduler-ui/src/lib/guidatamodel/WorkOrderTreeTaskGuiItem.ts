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
import { TaskDto } from 'projects/openi40-scheduler-api/src/lib';
import { TreeItemGuiItem } from './TreeItemGuiItem';



export class WorkOrderTreeTaskGuiItem extends TreeItemGuiItem<TaskDto> {
  public get visibleLabel():string {
    return this.data.workOrderCode+"/"+this.data.sequenceCode;
  }
  get objectType():string {
    return "WorkOrderTreeTaskGuiItem";
  }
  get iconPath():string {
    return "task";
  }
}
