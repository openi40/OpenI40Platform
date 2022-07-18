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
import { MachineGuiItem } from '../guidatamodel/MachineGuiItem';
import { TreeNodeRenderer } from './TreeNodeRenderer';
import { WorkCenterGuiItem } from '../guidatamodel/WorkCenterGuiItem';
import { WorkCenterDto, MachineDto } from 'projects/openi40-scheduler-api/src/lib';
import { MachineRenderer } from './MachineRenderer';
export class WorkCenterRenderer extends TreeNodeRenderer<WorkCenterDto, WorkCenterGuiItem, MachineDto, MachineGuiItem, MachineRenderer> {
}
