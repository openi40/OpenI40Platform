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
import { Renderer2 } from '@angular/core';
import { ProductiveCompanyDto } from 'projects/openi40-scheduler-api/src/lib';
import { DemandGuiItem } from '../guidatamodel/DemandGuiItem';
import { DemandRenderer } from '../guirenderers/DemandGuiRenderer';
import { TreeItemController } from './TreeItemController';

export class DemandController extends TreeItemController<ProductiveCompanyDto,DemandGuiItem,DemandRenderer>{
    public constructor (boundGuiItem: DemandGuiItem, renderer2: Renderer2) {
        super(boundGuiItem,renderer2,(_d: DemandGuiItem, _r: Renderer2) =>{return new DemandRenderer(_r,_d)} );
    }
}