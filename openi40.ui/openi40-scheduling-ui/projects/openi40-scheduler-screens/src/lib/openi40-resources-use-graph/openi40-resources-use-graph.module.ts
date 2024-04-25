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
import { NgModule } from '@angular/core';
import { Openi40ResourcesUseGraphService } from './openi40-resources-use-graph-service';
import {Openi40ResourcesUseGraphComponent}  from './openi40-resources-use-graph.component'
import {TreeTableModule} from 'primeng/treetable';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import {ChartModule} from 'primeng/chart';
@NgModule({
  imports: [
    CommonModule,FormsModule,ReactiveFormsModule,TreeTableModule,ChartModule
  ],
  declarations:[Openi40ResourcesUseGraphComponent],
  exports:[Openi40ResourcesUseGraphComponent],
  providers:[Openi40ResourcesUseGraphService]
})
export class Openi40ResourcesUseGraphModule {

}
