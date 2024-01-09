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
import { ApsDataGuiItem } from "../guidatamodel/ApsDataGuiItem";
import { AbstractSvgComponentRenderer } from './AbstractSvgComponentRenderer';
import { ElementRef, Renderer2 } from '@angular/core';
import { TimesheetPanelRenderer } from './TimesheetPanelRenderer';
import { TimesheetPanelGuiItem } from '../guidatamodel/TimesheetPanelGuiItem';
import { SvgPatternsProvider } from "./SvgPatternsProvider";
import { ISvgPatternsProvider } from "./ISvgPatternsProvider";
import { BaseGuiItem } from "../guidatamodel/BaseGuiItem";

export class GanttTimesheetRenderer extends AbstractSvgComponentRenderer<ApsDataGuiItem> {

  private timesheetRenderer:TimesheetPanelRenderer=null;
  private timesheetGuiItem:TimesheetPanelGuiItem=null;
  public constructor(  renderer: Renderer2,  boundGuiItem: ApsDataGuiItem,svgPatternsProvider:ISvgPatternsProvider,private treeItemNodes:BaseGuiItem[]){
    super(renderer,boundGuiItem);
    this.timesheetGuiItem=new TimesheetPanelGuiItem(boundGuiItem.dataItem.timesheet,this.boundGuiItem.graphicConfig,treeItemNodes.length, treeItemNodes);
    this.timesheetRenderer=new TimesheetPanelRenderer(renderer,this.timesheetGuiItem,svgPatternsProvider);
  }
  public create(): Element {
    //FIRST CREATE TIMESHEET PANEL AND STRUCTURES
    let svgItem = this.timesheetRenderer.create();

    return svgItem;
  }
  public update(): void {

  }
}
