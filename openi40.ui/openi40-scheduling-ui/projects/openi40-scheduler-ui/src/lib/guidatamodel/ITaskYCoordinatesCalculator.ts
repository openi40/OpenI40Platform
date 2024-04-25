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
import { BaseGuiItem } from './BaseGuiItem';
import { TaskGuiItem } from './TaskGuiItem';
import {YBlocksRetrieverMap} from './YBlocksRetrieverMap'
export interface ITaskYCoordinatesCalculator{
   getY(_task:TaskGuiItem,retrieverMap:YBlocksRetrieverMap) : number;
   getHeight(_task:TaskGuiItem,retrieverMap:YBlocksRetrieverMap) :number;
   getReferenceBlock(_task:TaskGuiItem,retrieverMap:YBlocksRetrieverMap):BaseGuiItem;
}

