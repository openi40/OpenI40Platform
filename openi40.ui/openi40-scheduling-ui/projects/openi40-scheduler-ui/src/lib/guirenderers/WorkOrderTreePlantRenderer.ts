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
import { PlantDto, WorkOrderDto } from 'projects/openi40-scheduler-api/src/lib';
import { WorkOrderTreePlantGuiItem } from '../guidatamodel/WorkOrderTreePlantGuiItem';
import { WorkOrderTreeWorkOrderGuiItem } from '../guidatamodel/WorkOrderTreeWorkOrderGuiItem';
import { TreeNodeRenderer } from './TreeNodeRenderer';
import { WorkOrderTreeWorkOrderRenderer } from './WorkOrderTreeWorkOrderRenderer';


export class WorkOrderTreePlantRenderer extends TreeNodeRenderer<PlantDto, WorkOrderTreePlantGuiItem, WorkOrderDto, WorkOrderTreeWorkOrderGuiItem, WorkOrderTreeWorkOrderRenderer> {
}
