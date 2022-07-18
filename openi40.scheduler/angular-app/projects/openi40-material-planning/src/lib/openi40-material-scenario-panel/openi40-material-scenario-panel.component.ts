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
import { Component, Input, OnChanges, OnInit, SimpleChanges } from '@angular/core';
import { MaterialPlanningDto } from '../../../../openi40-scheduler-api/src/lib';
import { Openi40MaterialSimulatorService,SimulatedScenario } from './openi40-material-simulator.service';

@Component({
  selector: 'openi40-material-scenario-panel',
  templateUrl: './openi40-material-scenario-panel.component.html',
  styleUrls: ['./openi40-material-scenario-panel.component.css']
})
export class Openi40MaterialScenarioPanelComponent implements OnInit,OnChanges {
  @Input("materialPlanningData") materialPlanningData:MaterialPlanningDto=null;
  scenarios: SimulatedScenario[]=[];
  constructor(
    private materialSimulatorService:Openi40MaterialSimulatorService
  ) { }
  ngOnChanges(changes: SimpleChanges): void {
    this.scenarios=[];
    if (this.materialPlanningData){
      this.scenarios=this.materialSimulatorService.loadSimulatedScenarios(this.materialPlanningData);
    }
  }

  ngOnInit(): void {
    this.scenarios=[];
    if (this.materialPlanningData){
      this.scenarios=this.materialSimulatorService.loadSimulatedScenarios(this.materialPlanningData);
    }
  }

}
