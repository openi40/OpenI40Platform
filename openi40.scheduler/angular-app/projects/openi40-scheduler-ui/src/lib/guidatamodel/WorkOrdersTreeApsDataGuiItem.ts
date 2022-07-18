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
import { WorkOrderTreeRootGuiItem } from './WorkOrderTreeRootGuiItem';

export class WorkOrdersTreeApsDataGuiItem extends DataBoundGuiItem<ApsDataDto> {
  protected _workOrderRootGuiItem: WorkOrderTreeRootGuiItem = null;
  public constructor(public dataItem: ApsDataDto,graphicConfig:Openi40GraphicConfiguration) {
    super(dataItem,graphicConfig);
    this._workOrderRootGuiItem = new WorkOrderTreeRootGuiItem(dataItem,graphicConfig, 0, new NodesCounter(0));
  }

  public get workOrderTreeRootGuiItem(): WorkOrderTreeRootGuiItem {
    return this._workOrderRootGuiItem;
  }
  public recalculateCoordinates(): void {
    this._workOrderRootGuiItem.recalculateCoordinates();
    this._x = this._workOrderRootGuiItem.x;
    this._y = this._workOrderRootGuiItem.y;
    this._width = this._workOrderRootGuiItem.width;
    this._height = this._workOrderRootGuiItem.height;
  }
}
