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
import { ApsDataDto } from 'projects/openi40-scheduler-api/src/lib/';
import { ProductiveCompanyGuiItem } from "../guidatamodel/ProductiveCompanyGuiItem";
import { ResourcesRootGuiItem } from "../guidatamodel/ResourcesRootGuiItem";
import { AbstractSvgComponentRenderer } from './AbstractSvgComponentRenderer';
import { TreeNodeRenderer } from './TreeNodeRenderer';
import {ResourcesRootRenderer} from './ResourcesRootRenderer'
import { WorkOrdersTreeApsDataGuiItem } from '../guidatamodel/WorkOrdersTreeApsDataGuiItem';
import { AbstractSvgDocumentRenderer } from './AbstractSvgDocumentRenderer';
export class WorkOrdersPanelRenderer extends AbstractSvgDocumentRenderer<WorkOrdersTreeApsDataGuiItem> {


  public create(): Element {
    let svgItem = this.createSvgDocument(this.boundGuiItem);

    return svgItem;
  }
  public update(): void {

  }
}
;
