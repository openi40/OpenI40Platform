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
import { ProductiveCompanyDto, ApsDataDto } from 'projects/openi40-scheduler-api/src/lib';
import { ProductiveCompanyGuiItem } from '../guidatamodel/ProductiveCompanyGuiItem';
import { ResourcesRootGuiItem } from '../guidatamodel/ResourcesRootGuiItem';
import { ProductiveCompanyRenderer } from './ProductiveCompanyRenderer';
export class ResourcesRootRenderer extends TreeNodeRenderer<ApsDataDto, ResourcesRootGuiItem, ProductiveCompanyDto, ProductiveCompanyGuiItem, ProductiveCompanyRenderer> {
}
