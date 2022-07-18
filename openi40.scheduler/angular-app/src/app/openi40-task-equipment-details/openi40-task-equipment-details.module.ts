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
import { TableModule } from 'primeng/table';
import { Openi40TaskEquipmentDetailsComponent } from "./openi40-task-equipment-details.component";

@NgModule(
  {
    declarations:[Openi40TaskEquipmentDetailsComponent],
    exports:[Openi40TaskEquipmentDetailsComponent],
    imports:[CommonModule,ReactiveFormsModule,CdkScrollableModule, TabViewModule,PanelModule,BlockUIModule,ButtonModule,DialogModule,TableModule,PanelModule]
  }
)
export class Openi40TaskEquipmentDetailsModule {

}
