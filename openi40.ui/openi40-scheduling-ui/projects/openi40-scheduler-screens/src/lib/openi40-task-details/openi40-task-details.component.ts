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
  selector: 'openi40-task-details',
  templateUrl: './openi40-task-details.component.html',
  styleUrls: ['./openi40-task-details.component.css']
})
export class Openi40TaskDetailsComponent implements OnInit {
  @Input("task") task:TaskDto=null;
  @Input("apsData") apsData:ApsDataDto=null;
  @Output() viewTaskDetails:EventEmitter<TaskDto>=new EventEmitter();
  constructor() { }
  visible:boolean=true;
  @Output("close") close:EventEmitter<boolean>=new EventEmitter<boolean>();
  okClick(evt){
    this.close.emit(true);
  }
  hideClick(evt) {
    this.close.emit(true);
  }
  ngOnInit(): void {
  }
  public viewDetailsClicked(task:TaskDto):void {
    this.viewTaskDetails.emit(task);
  }

}
