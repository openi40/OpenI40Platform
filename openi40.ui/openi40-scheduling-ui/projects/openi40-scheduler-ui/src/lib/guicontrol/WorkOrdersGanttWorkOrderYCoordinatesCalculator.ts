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
import { YBlocksRetrieverMap } from '../guidatamodel/YBlocksRetrieverMap';
import { BaseGuiItem } from '../guidatamodel/BaseGuiItem';

import { WorkOrderProjectGuiItem } from '../guidatamodel/WorkOrderProjectGuiItem';
import { IWorkOrderYCoordinatesCalculator } from '../guidatamodel/IWorkOrderYCoordinatesCalculator';

export class WorkOrdersGanttWorkOrderYCoordinatesCalculator implements IWorkOrderYCoordinatesCalculator {
  getY(_task: WorkOrderProjectGuiItem, retrieverMap: YBlocksRetrieverMap): number {
    let block: BaseGuiItem = this.getReferenceBlock(_task, retrieverMap);
    if (block) {
      return block.y-block.graphicConfig.dimensions.cellHeight;
    }
    else
      return -1;
  }
  getHeight(_task: WorkOrderProjectGuiItem, retrieverMap: YBlocksRetrieverMap): number {
    let block: BaseGuiItem = this.getReferenceBlock(_task, retrieverMap);
    if (block) {
      return _task.graphicConfig.dimensions.cellHeight;
    }
    else
      return -1;
  }
  getReferenceBlock(_task: WorkOrderProjectGuiItem, retrieverMap: YBlocksRetrieverMap): BaseGuiItem {
    let resource = _task.data.code;
    if (resource) {
      return retrieverMap.get("WorkOrderTreeWorkOrderGuiItem", resource);
    }
    else
      return null;
  }
}
