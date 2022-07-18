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
import { DemandGuiItem } from '../guidatamodel/DemandGuiItem';
import { DemandTimesheetGuiItem } from '../guidatamodel/DemandTimesheetGuiItem';
import { TaskGuiItem } from '../guidatamodel/TaskGuiItem';
import { TaskToDemandConnectorGuiItem } from '../guidatamodel/TaskToDemandConnectorGuiItem';
import { WarehouseGuiItem } from '../guidatamodel/WarehouseGuiItem';
import { EdgeConnectorRenderer } from './EdgeConnectorRenderer';
 export class TaskToDemandConnectorRenderer extends EdgeConnectorRenderer<SupplyReservationDto[], TaskGuiItem, DemandTimesheetGuiItem,TaskToDemandConnectorGuiItem>{
  public get endMarker():string {
    return "demand";
  }
  public get startYOffset():number{
    return 0;
  }
  public get endYOffset():number{
    return -this.boundGuiItem.graphicConfig.dimensions.cellHeight/2;
  }
 }

