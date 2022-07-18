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
import { BaseGuiItem } from '../guidatamodel/BaseGuiItem';
import { AbstractSvgComponentRenderer } from '../guirenderers/AbstractSvgComponentRenderer';
import { GuiController } from './GuiController';
import { IControllerCallback } from './IControllerCallback';

export class ControllerAwareCallbackSupport<guiItemType extends BaseGuiItem, rendererType extends AbstractSvgComponentRenderer<guiItemType>, controllerType extends GuiController<guiItemType, rendererType>> implements IControllerCallback {
  public constructor(protected eventTypes: string[], protected objecTypes: string[], protected controllerAwareCallback: (controller: controllerType, _event: Event) => void) {
  }
  getTargetObjectTypes(): string[] {
    return this.objecTypes;
  }
  getTargetEvents(): string[] {
    return this.eventTypes;
  }
  onEvent(item: any, _event: Event): void {
    this.controllerAwareCallback(item, _event);
  }
}
