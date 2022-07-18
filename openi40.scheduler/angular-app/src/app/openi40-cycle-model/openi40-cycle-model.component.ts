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
import { CycleModelDto,ApsCycleModelResourceService } from 'projects/openi40-scheduler-api/src/lib';
@Component({
  selector: 'openi40-cycle-model',
  templateUrl: './openi40-cycle-model.component.html',
  styleUrls: ['./openi40-cycle-model.component.css']
})
export class Openi40CycleModelComponent implements OnInit,OnChanges {
  constructor(
    private cycleModelResourceService:ApsCycleModelResourceService
  ) { }

  @Input("cycleCode") cycleCode:string=null;
  @Input("highlightOperationModelCode") highlightOperationModelCode:string=null;
  @Input("cycleModel") cycleModel:CycleModelDto=null;
  @Input("dataSourceName") dataSourceName:string;
  @Input("dataSetName") dataSetName: string;
  @Input("dataSetVariant") dataSetVariant: string;
  ngOnInit(): void {
    if (this.cycleModel) {

    }else {
        this.cycleModelResourceService.getCycleCycleModelDto(this.cycleCode,this.dataSetName,this.dataSourceName,this.dataSetVariant).subscribe((cycle)=>{
          this.cycleModel=cycle;
        });
    }
  }
  ngOnChanges(changes: SimpleChanges): void {

  }

}
