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
import { CommonModule } from "@angular/common";
import { NgModule } from "@angular/core";
import { Openi40TasksDatatableComponent } from "./openi40-tasks-datatable.component";
import {TableModule} from 'primeng/table'
import { CdkScrollableModule } from '@angular/cdk/scrolling';
import { Openi40TaskDetailsModule } from '../openi40-task-details/openi40-task-details.module';
@NgModule({
  imports:[CommonModule,TableModule,CdkScrollableModule,Openi40TaskDetailsModule],
  declarations: [Openi40TasksDatatableComponent],
  exports:[Openi40TasksDatatableComponent],
  providers:[]
})
export class Openi40TasksDatatableModule {

}
