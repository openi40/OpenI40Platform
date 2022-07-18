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
import { OperationModelDto } from 'projects/openi40-scheduler-api/src/lib';

@Component({
  selector: 'openi40-operation-model',
  templateUrl: './openi40-operation-model.component.html',
  styleUrls: ['./openi40-operation-model.component.css']
})
export class Openi40OperationModelComponent implements OnInit,OnChanges {
  @Input("operationModelCode") operationModelCode:string=null;
  @Input("operationModel") operationModel:OperationModelDto=null;
  constructor() { }
  ngOnChanges(changes: SimpleChanges): void {

  }

  ngOnInit(): void {
  }

}
