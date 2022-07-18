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
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { BlockUIModule } from 'primeng/blockui';
import { ButtonModule } from 'primeng/button';
import { DialogModule } from 'primeng/dialog';
import { PanelModule } from 'primeng/panel';
import { SelectButtonModule } from 'primeng/selectbutton';
import { TableModule } from 'primeng/table';
import { TabViewModule } from 'primeng/tabview';
import { Openi40SchedulingWarningsComponent } from './openi40-scheduling-warnings.component';

@NgModule(
  {
  imports:[CommonModule,FormsModule, ReactiveFormsModule,TableModule,ButtonModule,DialogModule,PanelModule,SelectButtonModule,TabViewModule,CdkScrollableModule],
  declarations:[Openi40SchedulingWarningsComponent],
  exports:[Openi40SchedulingWarningsComponent]
}
)
export class Openi40SchedulingWarningsModule {

}
