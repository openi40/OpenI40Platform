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
import { ITimeBar } from './ITimeBar';
import { PointCoords } from './PointCoords';
import { TimesheetSearchStructure } from '../timeutils/TimesheetSearchStructure';
import { DataBoundGuiItem } from './DataBoundGuiItem';
import { Openi40GraphicConfiguration } from '../screenconstants';

export class DataBoundTimeBar<BoundData> extends DataBoundGuiItem<BoundData> implements ITimeBar {
  public constructor(protected dataItem: BoundData,graphicConfig:Openi40GraphicConfiguration, protected timeSearchStructure: TimesheetSearchStructure) {
    super(dataItem,graphicConfig);
  }
  public getTopTimePoint(utfTime: number): PointCoords {
    let out: PointCoords = this.leftTop;
    let x = this.timeSearchStructure.getXTimeCoord(utfTime);
    if (x >= 0 && this.x <= x && this.width + this.x >= x) {
      out.x = x;
    } else {
      out = null;
    }
    return out;
  }
  public getBottomTimePoint(utfTime: number): PointCoords {
    let out: PointCoords = this.leftBottom;
    let x = this.timeSearchStructure.getXTimeCoord(utfTime);
    if (x >= 0 && this.x <= x && this.width + this.x >= x) {
      out.x = x;
    } else {
      out = null;
    }
    return out;
  }
  public getMiddleTimePoint(utfTime: number): PointCoords {
    let out: PointCoords = this.leftBottom;
    out.y -= Math.round(this.height / 2);
    let x = this.timeSearchStructure.getXTimeCoord(utfTime);
    if (x >= 0 && this.x <= x && this.width + this.x >= x) {
      out.x = x;
    } else {
      out = null;
    }
    return out;
  }

}
