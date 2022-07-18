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
import { TimesheetSearchStructureItem } from "../timeutils/TimesheetSearchStructureItem";
import { DataBoundGuiItem } from "./DataBoundGuiItem";

export class TimeCellGuiItem extends DataBoundGuiItem<TimesheetSearchStructureItem> {
  set x(v: number) { this._x = v; }
  set y(v: number) { this._y = v; }
  set width(v: number) { this._width = v; }
  set height(v: number) { this._height = v; }
}
