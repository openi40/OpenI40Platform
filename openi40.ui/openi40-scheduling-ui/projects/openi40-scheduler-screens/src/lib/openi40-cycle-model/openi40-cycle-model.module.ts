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
import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { Openi40CycleModelComponent } from './openi40-cycle-model.component';
import {Openi40OperationModelModule} from '../openi40-operation-model/openi40-operation-model.module'
import { AccordionModule } from 'primeng/accordion';
import { PanelModule } from 'primeng/panel';
@NgModule(
  {
    imports:[CommonModule,Openi40OperationModelModule,AccordionModule,PanelModule],
    declarations:[Openi40CycleModelComponent],
    exports:[Openi40CycleModelComponent]
  }
)
export class Openi40CycleModelModule{

}
