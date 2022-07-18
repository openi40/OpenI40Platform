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
import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { ApsDataDto, TaskDto } from 'projects/openi40-scheduler-api/src/lib';
@Component({
  selector:"openi40-task-equipment-details",
  templateUrl:"openi40-task-equipment-details.component.html",
  styleUrls:["openi40-task-equipment-details.component.css"]
})
export class Openi40TaskEquipmentDetailsComponent implements OnInit {
  ngOnInit(): void {

  }
  @Input("task") task:TaskDto=null;
}
