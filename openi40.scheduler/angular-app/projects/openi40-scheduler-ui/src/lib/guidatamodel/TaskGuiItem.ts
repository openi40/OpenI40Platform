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
import { TaskDto } from 'projects/openi40-scheduler-api/src/lib/';
import { DataBoundGuiItem } from './DataBoundGuiItem';
import {ITaskYCoordinatesCalculator} from './ITaskYCoordinatesCalculator'
import { TimesheetSearchStructureItem } from '../timeutils/TimesheetSearchStructureItem';
import { TimesheetSearchStructure } from '../timeutils/TimesheetSearchStructure';
import { TimesheetPanelGuiItem } from './TimesheetPanelGuiItem';
import { Openi40GraphicConfiguration } from '../screenconstants';
import { YBlocksRetrieverMap } from './YBlocksRetrieverMap';
import { DataBoundTimeBar } from './DataBoundTimeBar';

export class TaskGuiItem extends DataBoundTimeBar<TaskDto> {



  private _xStartSetupLeft:number=0;
  private _xStopSetupRight:number=0;
  private _xStartWorkLeft:number=0;
  private _xStopWorkRight:number=0;
  public recalculateTaskCoordinates(_timesheetPanel:TimesheetPanelGuiItem,_taskYCoordinatesCalculator:ITaskYCoordinatesCalculator,_retrieverMap:YBlocksRetrieverMap):void {
      let _block=_taskYCoordinatesCalculator.getReferenceBlock(this,_retrieverMap);
      //rendo l'attuale task invisibile se e' tale l'elemento di schermo di riferimento
      if (_block) {
        this.visible=_block.visible;
      }
			this._y = _taskYCoordinatesCalculator.getY(this,_retrieverMap);
			this._height = _taskYCoordinatesCalculator.getHeight(this,_retrieverMap);
			let setup = this.dataItem.setup;
			let work = this.dataItem.work;
			let startSetupHour = _timesheetPanel.currentSearchStructure.getStartSegment(setup.utcStartDateTime);
			let endSetupHour = _timesheetPanel.currentSearchStructure.getEndSegment(setup.utcEndDateTime);
			let startWorkHour = _timesheetPanel.currentSearchStructure.getStartSegment(work.utcStartDateTime);
			let endWorkHour = _timesheetPanel.currentSearchStructure.getEndSegment(work.utcEndDateTime);
			this._xStartSetupLeft = 0;
			this._xStopSetupRight = 0;
			this._xStartWorkLeft = 0;
			this._xStopWorkRight = 0;
			if (startSetupHour && endSetupHour && startWorkHour && endWorkHour) {
				this._xStartSetupLeft = _timesheetPanel.currentSearchStructure.calculateXCoordinata(startSetupHour,setup.utcStartDateTime);
				this._xStopSetupRight = _timesheetPanel.currentSearchStructure.calculateXCoordinata(endSetupHour,setup.utcEndDateTime);
				this._xStartWorkLeft = _timesheetPanel.currentSearchStructure.calculateXCoordinata(startWorkHour,work.utcStartDateTime);
        this._xStopWorkRight = _timesheetPanel.currentSearchStructure.calculateXCoordinata(endWorkHour,	work.utcEndDateTime);
        this._x=this._xStartSetupLeft;
        this._width=this._xStopWorkRight-this._xStartSetupLeft;
        this._height=this.graphicConfig.dimensions.cellHeight-2;
				let phaseCodes = this.dataItem.workOrderCode + "/" + this.dataItem.sequenceCode;
      }else {
        console.log("Unaval calendar refs");
      }
  }
  public get xStartSetupLeft(){
    return this._xStartSetupLeft;
  }
  public get xStopSetupRight(){
    return this._xStopSetupRight;
  }
  public get xStartWorkLeft(){
    return this._xStartWorkLeft;
  }
  public get xStopWorkRight(){
    return this._xStopWorkRight;
  }

  public get x() {
    return this.xStartSetupLeft;
  }
  public get width() {
    return this.xStopWorkRight-this.xStartSetupLeft;
  }
  public get workColor():string {
    return "#FF0000";
  }
  public get setupColor():string {
    return "#FFFF00";
  }
  public get visibleLabel():string {
    return this.dataItem.workOrderCode+"/"+this.dataItem.sequenceCode;
  }
  public get longLabel():string {
    return this.dataItem.workOrderCode+"/"+this.dataItem.sequenceCode+" "+this.dataItem.description;
  }
}
