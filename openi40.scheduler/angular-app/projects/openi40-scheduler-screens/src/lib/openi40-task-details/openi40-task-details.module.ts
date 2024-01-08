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
import { CdkScrollableModule } from '@angular/cdk/scrolling';
import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { ReactiveFormsModule } from '@angular/forms';
import { BlockUIModule } from 'primeng/blockui';
import { ButtonModule } from 'primeng/button';
import { DialogModule } from 'primeng/dialog';
import { PanelModule } from 'primeng/panel';
import { TabViewModule } from 'primeng/tabview';
import { Openi40TaskDetailsComponent } from './openi40-task-details.component';
import {Openi40OperationModelModule} from '../openi40-operation-model/openi40-operation-model.module'
import {Openi40TaskEquipmentDetailsModule} from '../openi40-task-equipment-details/openi40-task-equipment-details.module';
import {Openi40CycleModelModule} from '../openi40-cycle-model/openi40-cycle-model.module'
import { TableModule } from 'primeng/table';
import { Openi40DependencyGraphModule } from '../openi40-dependency-graph/openi40-dependency-graph.module';
@NgModule({
  imports:[CommonModule,ReactiveFormsModule,CdkScrollableModule, TabViewModule,PanelModule,BlockUIModule,ButtonModule,DialogModule,Openi40OperationModelModule,Openi40CycleModelModule,TableModule,PanelModule,Openi40DependencyGraphModule,Openi40TaskEquipmentDetailsModule],
  declarations: [Openi40TaskDetailsComponent],
  exports:[Openi40TaskDetailsComponent],
  providers:[]
})
export class Openi40TaskDetailsModule{

}
