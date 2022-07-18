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
import { TreeNodeRenderer } from './TreeNodeRenderer';
import { DepartmentDto, PlantDto } from 'projects/openi40-scheduler-api/src/lib';
import { DepartmentGuiItem } from '../guidatamodel/DepartmentGuiItem';
import { PlantGuiItem } from '../guidatamodel/PlantGuiItem';
import { DepartmentRenderer } from './DepartmentRenderer';
export class PlantRenderer extends TreeNodeRenderer<PlantDto, PlantGuiItem, DepartmentDto, DepartmentGuiItem, DepartmentRenderer> {

}
