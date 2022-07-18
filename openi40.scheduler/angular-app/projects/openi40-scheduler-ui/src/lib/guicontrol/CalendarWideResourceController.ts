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
import { CalendarWideGuiItem } from '../guidatamodel/CalendarWideGuiItem';
import { TreeItemGuiItem } from '../guidatamodel/TreeItemGuiItem';
import { CalendarWideGuiRenderer } from '../guirenderers/CalendarWideGuiRenderer';
import { GuiController } from './GuiController';

export class CalendarWideResourceController<DataType,RelatedTreeItem extends TreeItemGuiItem<DataType>,CalendarWideGuiType extends CalendarWideGuiItem<DataType,RelatedTreeItem>,CalendarWideRendererType extends CalendarWideGuiRenderer<DataType,RelatedTreeItem,CalendarWideGuiType>> extends GuiController<CalendarWideGuiType,CalendarWideRendererType>{

}

