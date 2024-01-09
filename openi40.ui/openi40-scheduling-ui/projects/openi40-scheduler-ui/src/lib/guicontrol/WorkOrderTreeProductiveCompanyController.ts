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
import { PlantDto, ProductiveCompanyDto } from 'projects/openi40-scheduler-api/src/lib';
import { TreeNodeController } from './TreeNodeController';
import { WorkOrderTreePlantGuiItem } from '../guidatamodel/WorkOrderTreePlantGuiItem';
import { WorkOrderTreePlantRenderer } from '../guirenderers/WorkOrderTreePlantRenderer';
import { WorkOrderTreeProductiveCompanyGuiItem } from '../guidatamodel/WorkOrderTreeProductiveCompanyGuiItem';
import { WorkOrderTreeProductiveCompanyRenderer } from '../guirenderers/WorkOrderTreeProductiveCompanyRenderer';
import { WorkOrderTreePlantController } from "./WorkOrderTreePlantController";
import {DemandController}from './DemandController'
import { IGuiController } from './IGuiController';
export class WorkOrderTreeProductiveCompanyController extends TreeNodeController<ProductiveCompanyDto, WorkOrderTreeProductiveCompanyGuiItem, WorkOrderTreeProductiveCompanyRenderer, PlantDto, WorkOrderTreePlantGuiItem, WorkOrderTreePlantRenderer, WorkOrderTreePlantController> {
  public demand:DemandController=null;
  public constructor(boundGuiItem: WorkOrderTreeProductiveCompanyGuiItem, renderer2: Renderer2) {
    super(boundGuiItem, renderer2,
      (_d: WorkOrderTreeProductiveCompanyGuiItem, _r: Renderer2) => { return new WorkOrderTreeProductiveCompanyRenderer(_r, _d); },
      (d: WorkOrderTreePlantGuiItem[], r: Renderer2) => {
        let outArray: WorkOrderTreePlantController[] = [];
        if (d)
          d.forEach((item) => {
            outArray.push(new WorkOrderTreePlantController(item, renderer2));
          });
        return outArray;
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
