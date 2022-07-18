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
import { MachineGuiItem } from '../guidatamodel/MachineGuiItem';
import { MachineRenderer } from '../guirenderers/MachineRenderer';
import { MachineDto, WorkCenterDto } from 'projects/openi40-scheduler-api/src/lib/';
import { WorkCenterRenderer } from '../guirenderers/WorkCenterRenderer';
import { WorkCenterGuiItem } from '../guidatamodel/WorkCenterGuiItem';
import { TreeNodeController } from './TreeNodeController';
import { MachineController } from './MachineController';
import { Renderer2 } from '@angular/core';
export class WorkCenterController extends TreeNodeController<WorkCenterDto, WorkCenterGuiItem, WorkCenterRenderer, MachineDto, MachineGuiItem, MachineRenderer, MachineController> {

    public constructor(data: WorkCenterGuiItem, renderer2: Renderer2) {
        super(data, 
              renderer2,  
              (_d: WorkCenterGuiItem, _r: Renderer2) => {return new WorkCenterRenderer(_r,_d)}, 
              (d: MachineGuiItem[], r: Renderer2) =>{
                  let  outVector:MachineController[]=[];
                  if (d) d.forEach((v)=>{
                      outVector.push(new MachineController(v,r));
                  })
                  return outVector;
              })
    }
}
