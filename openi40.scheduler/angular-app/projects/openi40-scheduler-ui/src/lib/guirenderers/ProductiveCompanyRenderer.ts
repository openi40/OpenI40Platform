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
import { PlantDto, ProductiveCompanyDto } from 'projects/openi40-scheduler-api/src/lib';
import { PlantGuiItem } from '../guidatamodel/PlantGuiItem';
import { ProductiveCompanyGuiItem } from '../guidatamodel/ProductiveCompanyGuiItem';
import { PlantRenderer } from './PlantRenderer';
export class ProductiveCompanyRenderer extends TreeNodeRenderer<ProductiveCompanyDto, ProductiveCompanyGuiItem, PlantDto, PlantGuiItem, PlantRenderer> {
}
