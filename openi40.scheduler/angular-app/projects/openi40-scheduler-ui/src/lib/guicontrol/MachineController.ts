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
import { MachineDto } from 'projects/openi40-scheduler-api/src/lib/';
import { TreeItemController } from './TreeItemController';
import { Renderer2 } from '@angular/core';
export class MachineController extends TreeItemController<MachineDto, MachineGuiItem, MachineRenderer> {
    public constructor(_data: MachineGuiItem, renderer2: Renderer2) {
        super(_data,renderer2,(_d: MachineGuiItem, _r: Renderer2) => {return new MachineRenderer(_r,_d)});
    }
}
