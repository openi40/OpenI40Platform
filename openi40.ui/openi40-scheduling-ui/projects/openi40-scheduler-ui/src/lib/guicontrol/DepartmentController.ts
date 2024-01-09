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
import { WorkCenterDto, DepartmentDto } from 'projects/openi40-scheduler-api/src/lib/';
import { WorkCenterRenderer } from '../guirenderers/WorkCenterRenderer';
import { WorkCenterGuiItem } from '../guidatamodel/WorkCenterGuiItem';
import { DepartmentGuiItem } from '../guidatamodel/DepartmentGuiItem';
import { DepartmentRenderer } from '../guirenderers/DepartmentRenderer';
import { TreeNodeController } from './TreeNodeController';
import { WorkCenterController } from './WorkCenterController';
import { Renderer2 } from '@angular/core';
export class DepartmentController extends TreeNodeController<DepartmentDto, DepartmentGuiItem, DepartmentRenderer, WorkCenterDto, WorkCenterGuiItem, WorkCenterRenderer, WorkCenterController> {
    constructor (_data: DepartmentGuiItem, renderer2: Renderer2) {
        super(
            _data,
            renderer2,
            (_d: DepartmentGuiItem, _r: Renderer2) => {return new DepartmentRenderer(_r,_d)},
        (d: WorkCenterGuiItem[], r: Renderer2) => {
            let outVector:WorkCenterController[]=[];
            if (d) d.forEach((v)=>{outVector.push(new WorkCenterController(v,renderer2))})
            return outVector;
        });
    }
}
