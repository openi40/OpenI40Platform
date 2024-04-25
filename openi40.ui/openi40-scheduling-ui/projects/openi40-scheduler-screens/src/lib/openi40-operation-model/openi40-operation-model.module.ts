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
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { TableModule } from 'primeng/table';
import {PanelModule} from 'primeng/panel';
import { Openi40OperationModelComponent } from './openi40-operation-model.component';
@NgModule(
  {
    imports:[CommonModule,FormsModule,ReactiveFormsModule,TableModule,PanelModule],
    declarations:[Openi40OperationModelComponent],
    exports:[Openi40OperationModelComponent]
  }
)
export class Openi40OperationModelModule{

}
