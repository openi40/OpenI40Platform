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
import { TreeItemController } from './TreeItemController';
import { WorkOrderTreeTaskGuiItem } from '../guidatamodel/WorkOrderTreeTaskGuiItem';
import { WorkOrderTreeTaskRenderer } from '../guirenderers/WorkOrderTreeTaskRenderer';
import { TaskDto } from 'projects/openi40-scheduler-api/src/lib';

export class WorkOrderTreeTaskController extends TreeItemController<TaskDto, WorkOrderTreeTaskGuiItem, WorkOrderTreeTaskRenderer> {
  public constructor(boundGuiItem: WorkOrderTreeTaskGuiItem, renderer2: Renderer2) {
    super(boundGuiItem, renderer2, (_d: WorkOrderTreeTaskGuiItem, _r: Renderer2) => { return new WorkOrderTreeTaskRenderer(_r, _d); });
  }
}
