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
import { TimesheetDto } from 'projects/openi40-scheduler-api/src/lib';
import {    Openi40GraphicConfiguration} from '../screenconstants';
import { DataBoundGuiItem } from './DataBoundGuiItem';
import { TimeCellGuiItem } from './TimeCellGuiItem';
import { TimesheetSearchStructure } from '../timeutils/TimesheetSearchStructure';
import { BaseGuiItem } from './BaseGuiItem';

export class TimesheetPanelGuiItem extends DataBoundGuiItem<TimesheetDto> {

  currentSearchStructure: TimesheetSearchStructure = null;



  public constructor(dataItem: TimesheetDto,graphicConfig:Openi40GraphicConfiguration,public verticalSlices:number=100,public treeItemsNodes:BaseGuiItem[]) {
    super(dataItem,graphicConfig);
    this._height=verticalSlices*graphicConfig.dimensions.cellHeight+graphicConfig.dimensions.timesheetHeaderHeight;
    this._width=500;
    this._x=0;
    this._y=0;
    this._xParentOffset=0;
    if (dataItem) {
      this.currentSearchStructure=TimesheetSearchStructure.build(dataItem,graphicConfig);
    }
  }
  public get width() {
    var length =this.currentSearchStructure?this.currentSearchStructure.timeSlotsArray?this.currentSearchStructure.timeSlotsArray.length:0:0;
    return this.graphicConfig.dimensions.timeCellWidth * (length + 1);
  }
  public get height () {
    return this._height;
  }
  public createTimeCells():TimeCellGuiItem[] {
    let _childs:TimeCellGuiItem[]=[];
    if (this.currentSearchStructure && this.currentSearchStructure.timeSlotsArray){
    for (var i = 0; i < this.currentSearchStructure.timeSlotsArray.length; i++) {
      var timeSlot = this.currentSearchStructure.timeSlotsArray[i];
      let tc = new TimeCellGuiItem(timeSlot,this.graphicConfig);
      var height = this.graphicConfig.dimensions.cellHeight;
      var offset = this.graphicConfig.dimensions.timeCellWidth * i;
      tc.x = offset;
      tc.width = this.graphicConfig.dimensions.timeCellWidth;
      tc.y = 0;
      tc.height = this.height;
      _childs.push(tc);
    }
  }
    return _childs;
  }
}
