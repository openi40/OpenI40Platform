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
import { ApsDataDto, ProductiveCompanyDto } from 'projects/openi40-scheduler-api/src/lib';
import { WorkOrderTreeProductiveCompanyGuiItem } from '../guidatamodel/WorkOrderTreeProductiveCompanyGuiItem';
import { WorkOrderTreeRootGuiItem } from '../guidatamodel/WorkOrderTreeRootGuiItem';
import { TreeNodeRenderer } from './TreeNodeRenderer';
import { WorkOrderTreeProductiveCompanyRenderer } from './WorkOrderTreeProductiveCompanyRenderer';


export class WorkOrderTreeRootRenderer extends TreeNodeRenderer<ApsDataDto, WorkOrderTreeRootGuiItem, ProductiveCompanyDto, WorkOrderTreeProductiveCompanyGuiItem, WorkOrderTreeProductiveCompanyRenderer> {
}
