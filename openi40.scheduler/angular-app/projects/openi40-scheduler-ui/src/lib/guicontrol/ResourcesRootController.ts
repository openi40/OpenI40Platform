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
import { ProductiveCompanyDto, ApsDataDto } from 'projects/openi40-scheduler-api/src/lib/';
import { ProductiveCompanyGuiItem } from '../guidatamodel/ProductiveCompanyGuiItem';
import { ProductiveCompanyRenderer } from '../guirenderers/ProductiveCompanyRenderer';
import { ResourcesRootGuiItem } from '../guidatamodel/ResourcesRootGuiItem';
import { ResourcesRootRenderer } from '../guirenderers/ResourcesRootRenderer';
import { TreeNodeController } from './TreeNodeController';
import { ProductiveCompanyController } from './ProductiveCompanyController';
import { Renderer2 } from '@angular/core';
import { NodesCounter } from '../guidatamodel/NodesCounter';

export class ResourcesRootController extends TreeNodeController<ApsDataDto, ResourcesRootGuiItem, ResourcesRootRenderer, ProductiveCompanyDto, ProductiveCompanyGuiItem, ProductiveCompanyRenderer, ProductiveCompanyController> {
    public constructor(_data:ResourcesRootGuiItem,renderer2:Renderer2 ){
        super(_data,
              renderer2,
              (d:ResourcesRootGuiItem,r:Renderer2)=>{return new ResourcesRootRenderer(r,d);},
              (d:ProductiveCompanyGuiItem[],r:Renderer2)=>{
                let outValue:ProductiveCompanyController[]=[];
                if (d)   {
                    d.forEach((v)=>{outValue.push(new ProductiveCompanyController(v,r))})
                }
                return outValue;})
        //_data.y=cellHeight;
    }
}
