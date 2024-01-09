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
import { DataBoundGuiItem } from './DataBoundGuiItem';
import { TimesheetPanelGuiItem } from './TimesheetPanelGuiItem';
import { TreeItemGuiItem } from './TreeItemGuiItem';
import { YBlocksRetrieverMap } from './YBlocksRetrieverMap';
import {PointCoords} from './PointCoords'
import { EdgeConnectorGuiItem } from './EdgeConnectorGuiItem';
import { BaseGuiItem } from './BaseGuiItem';
import { TimesheetSearchStructure } from '../timeutils/TimesheetSearchStructure';
import { ITimeBar } from './ITimeBar';
import { DataBoundTimeBar } from './DataBoundTimeBar';
import { Openi40GraphicConfiguration } from '../screenconstants';
export class CalendarWideGuiItem<DataType,RelatedTreeItem extends TreeItemGuiItem<DataType>> extends DataBoundTimeBar<DataType> implements ITimeBar{
  public constructor(dataItem:DataType,graphicConfig:Openi40GraphicConfiguration,public timesheetPanel:TimesheetPanelGuiItem,protected relatedTreeItem:RelatedTreeItem,protected yBlockRetrieveMap:YBlocksRetrieverMap,public color:String="#FFFF05"){
    super(dataItem,graphicConfig,timesheetPanel.currentSearchStructure);
  }

  recalculateCoordinates() {
    this.x=0;
      if (this.timesheetPanel) {
      this._width=this.timesheetPanel.width;
    }
    let treeItem=this.yBlockRetrieveMap.get(this.relatedTreeItem.constructor.name,this.relatedTreeItem.code);

    if (treeItem) {
      this.visible=treeItem.visible;
      this._y=treeItem.y-treeItem.height;
      this._height=treeItem.height;
    }
  }

}


