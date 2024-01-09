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
import { PointCoords } from './PointCoords';

export interface ITimeBar {
  getTopTimePoint(utfTime: number): PointCoords;
  getBottomTimePoint(utfTime: number): PointCoords;
  getMiddleTimePoint(utfTime: number): PointCoords;
}
