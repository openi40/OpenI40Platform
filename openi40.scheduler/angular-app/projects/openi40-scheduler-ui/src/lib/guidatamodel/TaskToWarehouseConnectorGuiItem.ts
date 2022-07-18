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
import { EdgeConnectorGuiItem } from './EdgeConnectorGuiItem';
import { PointCoords } from './PointCoords';
import { TaskGuiItem } from './TaskGuiItem';
import { WarehouseTimesheetGuiItem } from './WarehouseTimesheetGuiItem';


export class TaskToWarehouseConnectorGuiItem extends EdgeConnectorGuiItem<SupplyReservationDto[], TaskGuiItem, WarehouseTimesheetGuiItem> {
  public calculateConnectorPoints(): PointCoords[] {
    let polys:PointCoords[]=[];
    if (this.from.data.work){
    let startPoint=this.from.getMiddleTimePoint(this.from.data.work.utcStartDateTime);
    let endPoint=this.to.getMiddleTimePoint(this.from.data.work.utcStartDateTime);
    if (startPoint && endPoint){
      polys.push(startPoint);
      polys.push(endPoint);
    }

  }
    return polys;
  }

}
