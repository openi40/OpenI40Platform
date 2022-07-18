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
import { ChangeDetectorRef, Component, EventEmitter, Input, OnChanges, OnInit, Output, SimpleChanges } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { ApsSchedulingSetDto, ApsCommandResourceService, ApsDataDto } from 'projects/openi40-scheduler-api/src/lib';
@Component({
  selector: 'openi40-scheduling-settings-page',
  templateUrl: './openi40-scheduling-settings-page.component.html',
  styleUrls: ['./openi40-scheduling-settings-page.component.css']
})
export class Openi40SchedulingSettingsPageComponent implements OnInit,OnChanges{
  @Input("apsData") apsData:ApsDataDto=null;
  @Output("closeEvent") closeEvent:EventEmitter<boolean>=new EventEmitter<boolean>();
  @Output("startBlockingUi") startBlockingUi:EventEmitter<boolean>=new EventEmitter();
  @Output("endBlockingUi") endBlockingUi:EventEmitter<boolean>=new EventEmitter();
  @Output("apsDataChange") apsDataChange:EventEmitter<ApsDataDto>=new EventEmitter<ApsDataDto>();
  @Input() schedulingAdvancedMode:boolean=false;
  public visible=true;
  public canCreateSchedulingSet:boolean=false;
  public tabIndex:number=0;
  public constructor(protected formBuilder:FormBuilder,protected apsCommandResourceService:ApsCommandResourceService,private changeDetector: ChangeDetectorRef) {

  }
  public close(evt){
    this.closeEvent.emit(true);
  }

  public editingVisible:boolean=false;
  public editedSchedulingSet:ApsSchedulingSetDto=null;
  public doNewSchedulingSetEditing(evt):void{
    if (this.apsData) {

      this.apsCommandResourceService.getCleanApsActionApsSchedulingSetDto(this.apsData.dataSetName,this.apsData.dataSourceName,this.apsData.dataSetVariant).subscribe((cleanAction)=>{
        this.editedSchedulingSet=cleanAction;
        this.editingVisible=true;
        this.changeDetector.markForCheck();
      })
    }
  }
  public closeSchedulingSetEditing(evt):void {
    this.editingVisible=false;
    this.editedSchedulingSet=null;
    this.checkCanCreateNew();
  }
  ngOnChanges(changes: SimpleChanges): void {
    this.checkCanCreateNew();
  }

  ngOnInit(): void {
    this.checkCanCreateNew();
  }
  private checkCanCreateNew():void {
    this.canCreateSchedulingSet=false;
    if (this.apsData ) {
      let map:Map<string,boolean>=new Map();
      this.apsData.schedulingSets.forEach((sa)=>{
        sa.workOrders.forEach((wo)=>{
          map.set(wo.code,true);
        })
      })
      this.apsData.productiveCompanies.forEach((pc)=>{
        pc.plants.forEach((plant)=>{
          plant.workOrders.forEach((workOrder)=>{
            if (!map.has(workOrder.code)) {
              this.canCreateSchedulingSet=true;
              return;
            }
          })
        })
      })
    }
  }
  public closable:boolean=true;
  public handleClose(evt){
    if (this.apsData && this.apsData.schedulingSets &&  evt.index>=0 && evt.index < this.apsData.schedulingSets.length){

      const toDrop=this.apsData.schedulingSets[evt.index];
      const dataSetId=this.apsData.dataSetName;
      const  dataSourceName=this.apsData.dataSourceName;
      const schedulingSetId=toDrop.id;
      const  variantId= this.apsData.dataSetVariant;
      this.apsCommandResourceService.dropSchedulingSetApsDataDto(dataSetId,dataSourceName,schedulingSetId,variantId).subscribe(data=>{
        this.apsDataChangeEvent(data);
        this.checkCanCreateNew();
      });

    }
  }
  public changedTab(evt) {
    if (evt.index && evt.index < this.apsData.schedulingSets.length){
      this.tabIndex=evt.index;
    }
  }
  public apsDataChangeEvent(evt:ApsDataDto) {
    this.apsData=evt;
    this.apsDataChange.emit(evt);
  }

}
