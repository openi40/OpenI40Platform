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
import { SupplyReservationDto } from 'projects/openi40-scheduler-api/src/lib';
import { TaskGuiItem } from '../guidatamodel/TaskGuiItem';
import { WarehouseTimesheetGuiItem } from '../guidatamodel/WarehouseTimesheetGuiItem';
import { EdgeConnectorRenderer } from './EdgeConnectorRenderer';
import { WarehouseToTaskConnectorGuiItem } from '../guidatamodel/WarehouseToTaskConnectorGuiItem';

export class WarehouseToTaskConnectorRenderer extends EdgeConnectorRenderer<SupplyReservationDto[], WarehouseTimesheetGuiItem, TaskGuiItem, WarehouseToTaskConnectorGuiItem> {
  public get startMarker():string {
    return "warehouse-movement";
  }
  public get startYOffset():number{
    return -this.boundGuiItem.graphicConfig.dimensions.cellHeight;
  }
  public get endYOffset():number{
    return 0;
  }
}
