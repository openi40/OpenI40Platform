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
import { WorkOrderDto } from 'projects/openi40-scheduler-api/src/lib';

import { DataBoundGuiItem } from './DataBoundGuiItem';
import { DataBoundTimeBar } from './DataBoundTimeBar';
import { ITaskYCoordinatesCalculator } from './ITaskYCoordinatesCalculator';
import { IWorkOrderYCoordinatesCalculator } from './IWorkOrderYCoordinatesCalculator';
import { TimesheetPanelGuiItem } from './TimesheetPanelGuiItem';
import { TimesheetSearchStructureItem } from '../timeutils/TimesheetSearchStructureItem';
import { YBlocksRetrieverMap } from './YBlocksRetrieverMap';

export class WorkOrderProjectGuiItem extends DataBoundTimeBar<WorkOrderDto>{
  public recalculateWorkOrderCoordinates(_timesheetPanel:TimesheetPanelGuiItem,_workOrderYCoordinatesCalculator:IWorkOrderYCoordinatesCalculator,_retrieverMap:YBlocksRetrieverMap):void {
    let block=_workOrderYCoordinatesCalculator.getReferenceBlock(this,_retrieverMap);
    if (block) {
      this.visible=block.visible;
    }
    this._y = _workOrderYCoordinatesCalculator.getY(this,_retrieverMap)+this.graphicConfig.dimensions.cellHeight/2;
    this._height = _workOrderYCoordinatesCalculator.getHeight(this,_retrieverMap);
    this._x= 0;
    this._width = 0;
    let execution = this.dataItem.execution;
    if (execution) {
    let startHour = _timesheetPanel.currentSearchStructure.getEntry(execution.utcStartDateTime);
    let endHour = _timesheetPanel.currentSearchStructure.getEntry(execution.utcEndDateTime);

    if (startHour && endHour) {
      this._x = _timesheetPanel.currentSearchStructure.calculateXCoordinata(startHour,execution.utcStartDateTime);
      let _xStopSetupRight = _timesheetPanel.currentSearchStructure.calculateXCoordinata(endHour,execution.utcEndDateTime);
      this._width=_xStopSetupRight-this._x;
      this._height=this.graphicConfig.dimensions.cellHeight/2;
    }
  }
}

public get visibleLabel():string {
  return this.code;
}
get objectType():string {
  return "WorkOrderProjectGuiItem";
}
}
