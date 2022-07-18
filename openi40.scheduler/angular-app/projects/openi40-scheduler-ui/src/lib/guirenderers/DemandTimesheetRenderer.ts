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
import { ProductiveCompanyDto } from 'projects/openi40-scheduler-api/src/lib';
import { DemandGuiItem } from '../guidatamodel/DemandGuiItem';
import { DemandTimesheetGuiItem } from '../guidatamodel/DemandTimesheetGuiItem';
import { CalendarWideGuiRenderer } from './CalendarWideGuiRenderer';

export class DemandTimesheetRenderer extends CalendarWideGuiRenderer<ProductiveCompanyDto, DemandGuiItem, DemandTimesheetGuiItem> {
}
