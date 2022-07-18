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
import { TaskDependency } from 'projects/openi40-scheduler-api/src/lib';
import { EdgeConnectorGuiItem } from './EdgeConnectorGuiItem';
import { PointCoords } from './PointCoords';
import { TaskGuiItem } from './TaskGuiItem';

export class TaskToTaskEdgeConnectorGuiItem extends EdgeConnectorGuiItem<TaskDependency, TaskGuiItem,TaskGuiItem>{
  public calculateConnectorPoints(): PointCoords[] {
    let polys:PointCoords[]=[];
    let startX=Math.min(this.from.x+this.from.width,this.to.x);
    let startY=this.from.y+this.from.height/2;
    let endPoint=this.to.getMiddleTimePoint(this.to.data.work.utcStartDateTime);
    if (endPoint){
      let endX=endPoint.x;
      let endY=this.to.y+this.to.height/2;
      polys.push({x:startX,y:startY});
      if (startX<endX-6) {
        polys.push({x:(startX+endX)/2,y:startY});
        polys.push({x:(startX+endX)/2,y:endY});
      }
      polys.push({x:endX,y:endY});
    }
    return polys;
  }

}
