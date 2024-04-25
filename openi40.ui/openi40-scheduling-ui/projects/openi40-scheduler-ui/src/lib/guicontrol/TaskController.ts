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
import { TaskGuiItem } from '../guidatamodel/TaskGuiItem';
import { TaskRenderer } from '../guirenderers/TaskRenderer';
import { GuiController } from './GuiController';


export class TaskController extends GuiController<TaskGuiItem, TaskRenderer> {
  public constructor(boundGuiItem: TaskGuiItem, renderer2: Renderer2) {
    super(boundGuiItem, renderer2, (_d: TaskGuiItem, _r: Renderer2) => { return new TaskRenderer(_r, _d); });
  }
  protected initializeGuiEventListeners():void {
    this._renderer.anchor.addEventListener("click",this);
  }
}
