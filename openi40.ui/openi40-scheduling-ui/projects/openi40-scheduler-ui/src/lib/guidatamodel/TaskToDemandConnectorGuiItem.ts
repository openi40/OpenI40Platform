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
import { DemandGuiItem } from './DemandGuiItem';
import { DemandTimesheetGuiItem } from './DemandTimesheetGuiItem';
import { EdgeConnectorGuiItem } from './EdgeConnectorGuiItem';
import { PointCoords } from './PointCoords';
import { TaskGuiItem } from './TaskGuiItem';


export class TaskToDemandConnectorGuiItem extends EdgeConnectorGuiItem<SupplyReservationDto[], TaskGuiItem, DemandTimesheetGuiItem> {
  public calculateConnectorPoints(): PointCoords[] {
    let polys:PointCoords[]=[];
    if (this.from.data.work && this.from.data.askedDeliveryDateTime){
    let startPoint=this.from.getMiddleTimePoint(this.from.data.work.utcEndDateTime);
    let _askedTime=this.from.data.askedDeliveryDateTime  ;
    let _askedTimeUtc=new Date(_askedTime).getTime();
    let endPoint=this.to.getMiddleTimePoint(_askedTimeUtc);
    //  console.log("Demand connector for:" + this.from.data.code+" startPoint="+startPoint.ToString()+" endPoint="+endPoint.ToString());
    if (startPoint && endPoint){
      polys.push(startPoint);
      polys.push({x:startPoint.x,y:(startPoint.y+endPoint.y)/2});
      polys.push({x:endPoint.x,y:(startPoint.y+endPoint.y)/2});
      polys.push(endPoint);
    }

  }
    return polys;
  }
  public get additionalCSSClass():string {
    let _css=null;
    if (this.from.data.work && this.from.data.askedDeliveryDateTime){
      let _askedTime=this.from.data.askedDeliveryDateTime  ;
      let _askedTimeUtc=new Date(_askedTime).getTime();
      _css=_askedTimeUtc>=this.from.data.work.utcEndDateTime?"inTime":"inLate";
    }
    return _css;
  }
}
