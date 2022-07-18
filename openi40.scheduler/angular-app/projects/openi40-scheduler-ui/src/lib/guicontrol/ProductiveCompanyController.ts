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
import { PlantDto, ProductiveCompanyDto } from 'projects/openi40-scheduler-api/src/lib/';
import { PlantGuiItem } from '../guidatamodel/PlantGuiItem';
import { PlantRenderer } from '../guirenderers/PlantRenderer';
import { ProductiveCompanyGuiItem } from '../guidatamodel/ProductiveCompanyGuiItem';
import { ProductiveCompanyRenderer } from '../guirenderers/ProductiveCompanyRenderer';
import { TreeNodeController } from './TreeNodeController';
import { PlantController } from "./PlantController";
import { Renderer2 } from '@angular/core';
import { DemandController } from './DemandController';
import { IGuiController } from './IGuiController';
export class ProductiveCompanyController extends TreeNodeController<ProductiveCompanyDto, ProductiveCompanyGuiItem, ProductiveCompanyRenderer, PlantDto, PlantGuiItem, PlantRenderer, PlantController> {
    public demand:DemandController=null;
    public constructor(_data:ProductiveCompanyGuiItem,renderer2:Renderer2 ){
        super(_data,renderer2,
            (d:ProductiveCompanyGuiItem,r:Renderer2)=>{return new ProductiveCompanyRenderer(r,d)},
            (d:PlantGuiItem[],r:Renderer2)=>{
                let outVector:PlantController[]=[];
                if (d) d.forEach((v)=>{outVector.push(new PlantController(v,r))})
                return outVector;
            }
            );
            this.demand=new DemandController(this.boundGuiItem.demand,renderer2);

    }
    private _fullChilds:IGuiController[]=[];
    public get fullChildsList():IGuiController[]{
        if (this._fullChilds.length==0){
            this.mainChilds.forEach((ch)=>{
                this._fullChilds.push(ch);
            });
            this._fullChilds.push(this.demand);
        }
        return this._fullChilds;
    }
}
