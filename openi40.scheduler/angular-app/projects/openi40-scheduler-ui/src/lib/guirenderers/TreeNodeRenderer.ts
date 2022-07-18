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
import { TreeNodeGuiItem } from "../guidatamodel/TreeNodeGuiItem";
import { BaseGuiItem } from "../guidatamodel/BaseGuiItem";

import { ProductiveCompanyDto } from 'projects/openi40-scheduler-api/src/lib';
import { TreeItemRenderer } from './TreeItemRenderer';
import { TreeItemGuiItem } from '../guidatamodel/TreeItemGuiItem';
export class TreeNodeRenderer<BoundData,BoundDataGuiItemType extends TreeNodeGuiItem<BoundData,BoundChildGuiItemType>,ChildDataType,  BoundChildGuiItemType extends TreeItemGuiItem<ChildDataType>, ItemRendererType extends TreeItemRenderer< BoundChildGuiItemType>> extends TreeItemRenderer<BoundDataGuiItemType> {


}
