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
import { WarehouseDto } from 'projects/openi40-scheduler-api/src/lib';
import { WarehouseGuiItem } from '../guidatamodel/WarehouseGuiItem';
import { WarehouseTimesheetGuiItem } from '../guidatamodel/WarehouseTimesheetGuiItem';
import { WarehouseTimesheetRenderer } from '../guirenderers/WarehouseTimesheetRenderer';
import { CalendarWideResourceController } from './CalendarWideResourceController';

export class WarehouseTimesheetController extends   CalendarWideResourceController<WarehouseDto, WarehouseGuiItem, WarehouseTimesheetGuiItem, WarehouseTimesheetRenderer> {
}
