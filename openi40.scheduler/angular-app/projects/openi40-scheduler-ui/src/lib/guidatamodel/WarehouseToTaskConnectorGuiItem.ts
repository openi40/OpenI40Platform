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


export class WarehouseToTaskConnectorGuiItem extends EdgeConnectorGuiItem<SupplyReservationDto[], WarehouseTimesheetGuiItem, TaskGuiItem> {
  public calculateConnectorPoints(): PointCoords[] {
    let out:PointCoords[]=[];
    let ts:number=this.to.data.work.utcStartDateTime;

    let point=this.from.getBottomTimePoint(ts);
    if (point) {
      out.push(point);
      //out.push({x:this.to.xStartWorkLeft,y:this.to.y});
      out.push(this.to.getTopTimePoint(ts));
    }
    return out;
  }

}
