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
import { ApsDataDto } from 'projects/openi40-scheduler-api/src/lib/';
import { Openi40GraphicConfiguration } from '../screenconstants';
import { DataBoundGuiItem } from './DataBoundGuiItem';
import { NodesCounter } from './NodesCounter';
import { ProductiveCompanyGuiItem } from "./ProductiveCompanyGuiItem";
import { ResourcesRootGuiItem } from './ResourcesRootGuiItem';
import {WorkOrderTreeRootGuiItem} from './WorkOrderTreeRootGuiItem'
//TODO:SPLITTARE IL TIPO, UNO PER RESOURCES GANTT,L'ALTRO PER ORDERS GANTT
export class ApsDataGuiItem extends DataBoundGuiItem<ApsDataDto> {
  protected _resourcesRootGuiItem:ResourcesRootGuiItem=null;
  protected _workOrderRootGuiItem:WorkOrderTreeRootGuiItem=null;
  public constructor(public dataItem: ApsDataDto,graphicConfig:Openi40GraphicConfiguration) {
    super(dataItem,graphicConfig);
    this._resourcesRootGuiItem=new ResourcesRootGuiItem(dataItem,graphicConfig,0,new NodesCounter(0));
    this._resourcesRootGuiItem.y=0;
    this._workOrderRootGuiItem=new WorkOrderTreeRootGuiItem(dataItem,graphicConfig,0,new NodesCounter(0));
    this._workOrderRootGuiItem.y=0;
  }
  public get resourcesRootGuiItem():ResourcesRootGuiItem{
    return this._resourcesRootGuiItem;
  }
  public get workOrderTreeRootGuiItem():WorkOrderTreeRootGuiItem{
    return this._workOrderRootGuiItem;
  }
  public recalculateCoordinates():void {
    this._resourcesRootGuiItem.recalculateCoordinates();
    this._x=this._resourcesRootGuiItem.x;
    this._y=this._resourcesRootGuiItem.y;
    this._width=this._resourcesRootGuiItem.width;
    this._height=this._resourcesRootGuiItem.height;
  }
}

