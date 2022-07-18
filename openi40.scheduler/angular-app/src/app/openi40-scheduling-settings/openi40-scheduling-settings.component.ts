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
import { stringify } from '@angular/compiler/src/util';
import { ChangeDetectorRef, Component, EventEmitter, Input, OnChanges, OnInit, Output, SimpleChanges } from '@angular/core';
import { FormBuilder, FormControl, FormGroup } from '@angular/forms';
import { SelectItem } from 'primeng/api';
import { ApsSchedulingSetDto, ApsDataDto, ClientDto, WorkOrderDto } from 'projects/openi40-scheduler-api/src/lib';

@Component({
  selector: 'openi40-scheduling-settings',
  templateUrl: './openi40-scheduling-settings.component.html',
  styleUrls: ['./openi40-scheduling-settings.component.css']
})
export class Openi40SchedulingSettingsComponent implements OnInit,OnChanges {
  schedulingAlgorithms:SelectItem[]=[{value:"APS.BACKWARD",label:"Backward"},
                                                          {value:"APS.FORWARD",label:"Forward"}
                                                        ];
  @Output("apsDataChange") apsDataChange:EventEmitter<ApsDataDto>=new EventEmitter<ApsDataDto>();
  @Input() schedulingAdvancedMode:boolean=false;
  constructor(private changeDetector: ChangeDetectorRef) {

  }

  ngOnChanges(changes: SimpleChanges): void {


  }
  ngOnInit(): void {

  }

  @Input("apsAction") public  apsAction:ApsSchedulingSetDto=null;
  @Input("apsData") apsData:ApsDataDto=null;
  public workOrderFreeList:ClientDto[]=[];
  public editSchedulingSet:boolean=false;

  public openSchedulingSetEditing():void {
    this.editSchedulingSet=true;
  }
  public closeSchedulingSetEditing():void {
    this.editSchedulingSet=false;
  }
  public forwardApsDataChange(event:ApsDataDto) : void {
    this.apsData=event;
    this.apsDataChange.emit(event);
  }
}
