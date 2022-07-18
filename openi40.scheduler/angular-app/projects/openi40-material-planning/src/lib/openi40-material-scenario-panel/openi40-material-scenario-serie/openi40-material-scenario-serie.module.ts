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
import { NgModule } from "@angular/core";
import { Openi40MaterialSimulatorService } from "../openi40-material-simulator.service";
import { Openi40MaterialScenarioSerieComponent } from "./openi40-material-scenario-serie.component";
import {TreeTableModule} from 'primeng/treetable';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import {ChartModule} from 'primeng/chart';
import { TableModule } from "primeng/table";

@NgModule({
  imports: [
    CommonModule,FormsModule,ReactiveFormsModule,TreeTableModule,ChartModule,TableModule
  ],
  declarations:[Openi40MaterialScenarioSerieComponent],
  exports:[Openi40MaterialScenarioSerieComponent]
})
export class Openi40MaterialScenarioSerieModule{

}
