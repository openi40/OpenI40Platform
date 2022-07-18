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
import { TaskDependency } from 'projects/openi40-scheduler-api/src/lib';
import { TaskGuiItem } from '../guidatamodel/TaskGuiItem';
import { TaskToTaskEdgeConnectorGuiItem } from '../guidatamodel/TaskToTaskEdgeConnectorGuiItem';
import { TaskToTaskEdgeConnectorRenderer } from '../guirenderers/TaskToTaskEdgeConnectorRenderer';
import { EdgeConnectorController } from './EdgeConnectorController';
export class TaskToTaskEdgeConnectorController extends EdgeConnectorController<TaskDependency,TaskGuiItem,TaskGuiItem,TaskToTaskEdgeConnectorGuiItem,TaskToTaskEdgeConnectorRenderer>{
  public constructor (taskDependency:TaskDependency,renderer2:Renderer2,from:TaskGuiItem,to:TaskGuiItem ) {
    super(new TaskToTaskEdgeConnectorGuiItem(taskDependency,from.graphicConfig,from,to),renderer2,
    (_d: TaskToTaskEdgeConnectorGuiItem, _r: Renderer2)=>{return new TaskToTaskEdgeConnectorRenderer(_r,_d);});
  }
}

