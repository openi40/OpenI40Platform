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
import {TimeSegment} from './TimeSegment'
export class TimesheetSearchStructureItem extends TimeSegment{
  public array?: Array<TimesheetSearchStructureItem> = null;
  public searchMap?: Map<number, TimesheetSearchStructureItem> = new Map();
  public startSlot?: TimesheetSearchStructureItem;//{ utcStartDateTime?: number; utcEndDateTime?: number; };
  public endSlot?: TimesheetSearchStructureItem;// { utcStartDateTime?: number; utcEndDateTime?: number; };
  public startDate?:string=null;
  public endDate?:string=null;
  public startTime?:string=null;
  public endTime?:string=null;
  public xLeft?:number=0;
  public xWidth?: number=0;
  public code?:string;
  public description?:string;
  public id?:string;
}
