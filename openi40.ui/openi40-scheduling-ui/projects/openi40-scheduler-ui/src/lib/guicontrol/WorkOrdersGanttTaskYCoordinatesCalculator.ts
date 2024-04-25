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
import { TaskGuiItem } from '../guidatamodel/TaskGuiItem';
import { YBlocksRetrieverMap } from '../guidatamodel/YBlocksRetrieverMap';
import { ITaskYCoordinatesCalculator } from '../guidatamodel/ITaskYCoordinatesCalculator';
import { BaseGuiItem } from '../guidatamodel/BaseGuiItem';


export class WorkOrdersGanttTaskYCoordinatesCalculator implements ITaskYCoordinatesCalculator {
  getY(_task: TaskGuiItem, retrieverMap: YBlocksRetrieverMap): number {
    let block: BaseGuiItem = this.getReferenceBlock(_task, retrieverMap);
    if (block) {
      return block.y-block.height;
    }
    else
      return -1;
  }
  getHeight(_task: TaskGuiItem, retrieverMap: YBlocksRetrieverMap): number {
    let block: BaseGuiItem = this.getReferenceBlock(_task, retrieverMap);
    if (block) {
      return _task.graphicConfig.dimensions.cellHeight;
    }
    else
      return -1;
  }
  getReferenceBlock(_task: TaskGuiItem, retrieverMap: YBlocksRetrieverMap): BaseGuiItem {
    let resource = _task.data.code;
    if (resource) {
      return retrieverMap.get("WorkOrderTreeTaskGuiItem", resource);
    }
    else
      return null;
  }

}
