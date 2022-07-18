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
import { Renderer2 } from '@angular/core';
import { WorkOrderTreeTaskGuiItem } from '../guidatamodel/WorkOrderTreeTaskGuiItem';
import { WorkOrderTreeTaskRenderer } from '../guirenderers/WorkOrderTreeTaskRenderer';
import { TaskDto, WorkOrderDto } from 'projects/openi40-scheduler-api/src/lib';
import { TreeNodeController } from './TreeNodeController';
import { WorkOrderTreeWorkOrderGuiItem } from '../guidatamodel/WorkOrderTreeWorkOrderGuiItem';
import { WorkOrderTreeWorkOrderRenderer } from '../guirenderers/WorkOrderTreeWorkOrderRenderer';
import { WorkOrderTreeTaskController } from "./WorkOrderTreeTaskController";

export class WorkOrderTreeWorkOrderController extends TreeNodeController<WorkOrderDto, WorkOrderTreeWorkOrderGuiItem, WorkOrderTreeWorkOrderRenderer, TaskDto, WorkOrderTreeTaskGuiItem, WorkOrderTreeTaskRenderer, WorkOrderTreeTaskController> {
  public constructor(boundGuiItem: WorkOrderTreeWorkOrderGuiItem, renderer2: Renderer2) {
    super(boundGuiItem, renderer2,
      (_d: WorkOrderTreeWorkOrderGuiItem, _r: Renderer2) => { return new WorkOrderTreeWorkOrderRenderer(_r, _d); },
      (d: WorkOrderTreeTaskGuiItem[], r: Renderer2) => {
        let outArray: WorkOrderTreeTaskController[] = [];
        if (d)
          d.forEach((item) => {
            outArray.push(new WorkOrderTreeTaskController(item, renderer2));
          });
        return outArray;
      }
    );
  }
}
