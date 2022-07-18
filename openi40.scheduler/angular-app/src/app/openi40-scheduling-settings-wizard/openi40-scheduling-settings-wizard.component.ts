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
import { ApsSchedulingSetDto, ApsCommandResourceService, ApsDataDto, ClientDto, WorkOrderDto } from 'projects/openi40-scheduler-api/src/lib';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
@Component({
  selector: 'openi40-scheduling-settings-wizard',
  templateUrl: './openi40-scheduling-settings-wizard.component.html',
  styleUrls: ['./openi40-scheduling-settings-wizard.component.css']
})
export class Openi40SchedulingSettingsWizardComponent implements OnInit, OnChanges {
  schedulingAlgorithms: SelectItem[] = [{ value: "APS.BACKWARD", label: "Backward" },
  { value: "APS.FORWARD", label: "Forward" }
  ];
  @Output("closeEvent") closeEvent: EventEmitter<boolean> = new EventEmitter<boolean>();
  @Output("startBlockingUi") startBlockingUi: EventEmitter<boolean> = new EventEmitter();
  @Output("endBlockingUi") endBlockingUi: EventEmitter<boolean> = new EventEmitter();
  @Output("apsDataChange") apsDataChange: EventEmitter<ApsDataDto> = new EventEmitter<ApsDataDto>();
  @Output("closeWizard") closeWizard: EventEmitter<boolean> = new EventEmitter();
  @Input() schedulingAdvancedMode: boolean = false;
  public addedWorkOrders: WorkOrderDto[] = [];
  public firstPage: boolean = true;
  public secondPage: boolean = false;
  public loading: boolean = false;
  constructor(protected formBuilder: FormBuilder, protected apsCommandResourceService: ApsCommandResourceService, private changeDetector: ChangeDetectorRef) {

  }

  ngOnChanges(changes: SimpleChanges): void {
    this.reloadWorkOrderFreeList();
    this.changeDetector.markForCheck();

  }
  ngOnInit(): void {

    this.reloadWorkOrderFreeList();
  }
  private computeAddedWorkOrders(before: ApsSchedulingSetDto, after: ApsSchedulingSetDto): WorkOrderDto[] {
    const results: WorkOrderDto[] = [];
    if (after.workOrders && after.workOrders.length>0) {
      after.workOrders.forEach(wo=>{
        if (!before.workOrders ||  (before.workOrders.some((entry=>entry.code===wo.code))!==true)) {
          results.push(wo);
        }
      });
    }
    return results;
  }

  public autoCompleteWorkOrders(): Observable<ApsSchedulingSetDto[]> {
    const sets: ApsSchedulingSetDto[] = [];
    let _createNew: boolean = true;
    let _foundIndex: number = -1;
    if (this.apsData.schedulingSets) {
      this.apsData.schedulingSets.forEach((sa, index) => {
        if (sa.id === this.apsAction.id) {
          _foundIndex = index;
          _createNew = false;
          sets.push(sa);
        }
      });
    }
    if (_createNew) {
      sets.push(this.apsAction);
    }
    this.loading = true;
    return this.apsCommandResourceService.autoSetTasksListApsSchedulingSetDto(sets, this.apsData.dataSetName, this.apsData.dataSourceName, this.apsData.dataSetVariant).pipe(map(schedulingSets => {
      if (schedulingSets) {
        const foundMatching = schedulingSets.find((entry) => entry.id === this.apsAction.id);
        if (foundMatching) {
          this.addedWorkOrders = this.computeAddedWorkOrders(this.apsAction, foundMatching);
          this.apsAction.workOrders = foundMatching.workOrders;
        }
        this.loading = false;
      }
      return schedulingSets;
    }));

  }
  private endWizard(): void {
    this.addedWorkOrders=[];

    this.apsDataChange.emit(this.apsData);
    this.closeWizard.emit(true);
    this.closeEvent.emit(true);
    this.changeDetector.markForCheck();
  }
  public saveAndApply(evt) {
    this.startBlockingUi.emit(true);

    this.autoCompleteWorkOrders().subscribe((schedulingSets: ApsSchedulingSetDto[]) => {
      this.apsData.schedulingSets = schedulingSets;
      this.apsCommandResourceService.updateActionsApsDataDto(this.apsData.schedulingSets, this.apsData.dataSetName, this.apsData.dataSourceName, this.apsData.dataSetVariant).subscribe((data) => {
        this.apsData = data;
        this.endBlockingUi.emit(true);
        this.secondPage=false;
        if (!this.addedWorkOrders || this.addedWorkOrders.length===0){
          this.endWizard();
        }
      })
    });

  }


  private reloadWorkOrderFreeList(): void {
    this.workOrderFreeList = [];
    let inActionWOS: Map<string, ClientDto> = new Map<string, ClientDto>();
    if (this.apsData.schedulingSets) {
      this.apsData.schedulingSets.forEach((action) => {
        if (action.workOrders) {
          action.workOrders.forEach((wo) => {
            inActionWOS.set(wo.id, wo);
          });
        }
      });
    }
    this.apsData.productiveCompanies.forEach((pc) => {
      pc.plants.forEach((plant) => {
        plant.workOrders.forEach(workOrder => {
          if (!inActionWOS.has(workOrder.id)) {
            let object: ClientDto = { id: workOrder.id, code: workOrder.code, description: workOrder.description };
            this.workOrderFreeList.push(object);
          }
        })
      })
    })
  }
  private _apsAction: ApsSchedulingSetDto = null;
  @Input("apsAction") public set apsAction(a: ApsSchedulingSetDto) {
    const objCloneByJsonStringfy = JSON.parse(JSON.stringify(a));
    this._apsAction = objCloneByJsonStringfy;
  }
  public get apsAction(): ApsSchedulingSetDto {
    return this._apsAction;
  }
  @Input("apsData") apsData: ApsDataDto = null;
  public workOrderFreeList: ClientDto[] = [];
  public close(): void {
    this.closeWizard.emit(true);
    this.closeEvent.emit(true);
  }
  public goToAdvancedSettings(): void {
    this.firstPage = false;
    this.secondPage = true;
  }
  public goFirstPage(): void {
    this.firstPage = true;
    this.secondPage = false;
  }

}
