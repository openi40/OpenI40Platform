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
import { DepartmentDto, PlantDto } from 'projects/openi40-scheduler-api/src/lib/';
import { DepartmentGuiItem } from '../guidatamodel/DepartmentGuiItem';
import { DepartmentRenderer } from '../guirenderers/DepartmentRenderer';
import { PlantGuiItem } from '../guidatamodel/PlantGuiItem';
import { PlantRenderer } from '../guirenderers/PlantRenderer';
import { TreeNodeController } from './TreeNodeController';
import { DepartmentController } from './DepartmentController';
import { Renderer2 } from '@angular/core';
import { IGuiController } from './IGuiController';
import { WarehouseController } from './WarehouseController';
export class PlantController extends TreeNodeController<PlantDto, PlantGuiItem, PlantRenderer, DepartmentDto, DepartmentGuiItem, DepartmentRenderer, DepartmentController> {
    public warehouses:WarehouseController[]=[];
    private fullChilds:IGuiController[]=[];
    public constructor(_data:PlantGuiItem,renderer2:Renderer2){
        super(_data,
              renderer2,
              (_d: PlantGuiItem, _r: Renderer2) => {return new PlantRenderer(_r,_d)},
              (d: DepartmentGuiItem[], r: Renderer2)=> {
                  let outVector:DepartmentController[]=[];
                  if (d)   d.forEach((v)=>{outVector.push(new DepartmentController(v,r))});
                  return outVector;
              });
        this._mainChilds.forEach((ch)=>{
            this.fullChilds.push(ch);
        })
        this.data.warehouses.forEach((wh)=>{
            this.warehouses.push(new WarehouseController(wh,renderer2));
        })
        this.warehouses.forEach((wh)=>{
            this.fullChilds.push(wh);
        })
    }
    public get fullChildsList():IGuiController[]{
        return this.fullChilds;
    }
}
