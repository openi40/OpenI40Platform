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
import { ChangeDetectorRef, Component, Input, OnChanges, OnInit, SimpleChanges } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ApsCommandResourceService, ApsDataDto, ApsDataListService, DataSetEntry, TaskDto, WSScheduleTaskResponseItem } from '../../../../openi40-scheduler-api/src/lib';
import { TaskController } from '../../../../openi40-scheduler-ui/src/lib/guicontrol/TaskController';
import { DIMENSION_CHOICES, Openi40GraphicConfiguration } from '../../../../openi40-scheduler-ui/src/lib/screenconstants';
import { WorkOrderProjectController } from '../../../../openi40-scheduler-ui/src/lib/guicontrol/WorkOrderProjectController';
import { MachineController } from '../../../../openi40-scheduler-ui/src/lib/guicontrol/MachineController';
import { WebsocketService } from './websocket-service';
import { Subject } from 'rxjs';
import { SelectItem } from 'primeng/api';
import { faBolt } from '@fortawesome/free-solid-svg-icons';
@Component({
  selector: 'openi40-dataset-homepage',
  templateUrl: './openi40-dataset-homepage.component.html',
  styleUrls: ['./openi40-dataset-homepage.component.css']
})

export class Openi40DatasetHomepageComponent implements OnInit, OnChanges {
  public currentTabIndex: number = 0;
  private dataSourceName: string;
  private dataSetName: string;
  private dataSetVariant: string;
  public faBolt = faBolt;
  public apsListItem: DataSetEntry = {};
  public get schedulingAdvancedMode(): boolean {
    const length: number = this.data?.schedulingSets?.length ? this.data.schedulingSets.length : 0;
    return length && length > 1 ? true : false;
  }
  public schedulingModes: SelectItem[] = [{ value: false, label: "Standard" }, { value: true, label: "Advanced" }];
  @Input("blockedDocument") public blockedDocument: boolean = false;
  @Input("data") public data: ApsDataDto = {};
  public graphicConfigurations: Openi40GraphicConfiguration[] = DIMENSION_CHOICES[0].configurations;
  @Input("currentGraphicConfigIndex") private currentGraphicConfigIndex: number = 1;
  public currentGraphicConfiguration: Openi40GraphicConfiguration = this.graphicConfigurations[this.currentGraphicConfigIndex];
  stompClient: any;
  constructor(
    protected apsCommandResourceService: ApsCommandResourceService,
    private route: ActivatedRoute,
    private changeDetector: ChangeDetectorRef,
    protected websocketService: WebsocketService,
    private apsListService: ApsDataListService) { }
  ngOnChanges(changes: SimpleChanges): void {

  }
  public changeTab(n: number): void {
    this.currentTabIndex = n;
    this.changeDetector.markForCheck();
  }
  public isCurrentTab(n: number): boolean {
    return this.currentTabIndex === n ? true : false;
  }
  ngOnInit(): void {
    try {
      this.blockedDocument = true;
      this.dataSourceName = this.route.snapshot.queryParamMap.get("dataSourceName");
      this.dataSetName = this.route.snapshot.queryParamMap.get("dataSetName");
      this.route.queryParamMap.subscribe(queryParams => {
        this.dataSetVariant = queryParams.get("dataSetVariant")
      });
      this.dataSetVariant = this.route.snapshot.queryParamMap.get("dataSetVariant")
      this.route.queryParamMap.subscribe(queryParams => {
        this.dataSetVariant = queryParams.get("dataSetVariant")
      });
      if (this.dataSetName && this.dataSetVariant) {
        this.apsCommandResourceService.refreshApsDataDto(this.dataSetName, this.dataSourceName, this.dataSetVariant).subscribe((apsData) => {
          this.data = apsData;
          this.blockedDocument = false;
          this.websocketConnect(this.dataSetName, this.dataSetVariant);
        });
      }
      this.apsListService.getListDataSetEntry().subscribe(results => {
        this.apsListItem = results ? results.find(entry => entry.dataSourceName === this.dataSourceName && entry.dataSetName === this.dataSetName && entry.dataSetVariant === this.dataSetVariant) : null;
      });
    } catch (e) {
      console.log(e);
      this.blockedDocument = false;
    }
  }

  public schedule(): void {
    try {
      this.blockedDocument = true;
      this.apsCommandResourceService.scheduleApsDataDto(this.dataSetName, this.dataSourceName, this.dataSetVariant).subscribe((apsData) => {
        this.data = apsData;
        this.blockedDocument = false;
      });
    } catch (e) {
      console.log(e);
      this.blockedDocument = false;
    }
  }
  public doReschedule(evt) {
    this.schedule();
  }
  public scheduleAll(algorithmType: string): void {
    try {
      this.blockedDocument = true;
      this.apsCommandResourceService.scheduleAllApsDataDto(encodeURIComponent(algorithmType), this.dataSetName, this.dataSourceName, this.dataSetVariant).subscribe((apsData) => {
        this.data = apsData;
        this.blockedDocument = false;
      });
    } catch (e) {
      console.log(e);
      this.blockedDocument = false;
    }
  }
  public showWaitScreen(event) {
    this.blockedDocument = true;
  }
  public hideWaitScreen(event) {
    this.blockedDocument = false;
  }
  public moreTimeDetails(event): void {
    if (this.currentGraphicConfigIndex < this.graphicConfigurations.length - 1) {
      this.currentGraphicConfigIndex++;
      this.currentGraphicConfiguration = this.graphicConfigurations[this.currentGraphicConfigIndex];
    }
  }
  public lessTimeDetails(event): void {
    if (this.currentGraphicConfigIndex > 0) {
      this.currentGraphicConfigIndex--;
      this.currentGraphicConfiguration = this.graphicConfigurations[this.currentGraphicConfigIndex];
    }
  }
  public scheduleAllForward(event): void {
    this.scheduleAll('APS.FORWARD');
  }
  public scheduleAllBackward(event): void {
    this.scheduleAll('APS.BACKWARD');
  }
  public scheduleAllParallelForward(event): void {
    this.scheduleAll('APS.FORWARD.PARALLEL');
  }
  public scheduleAllParallelBackward(event): void {
    this.scheduleAll('APS.BACKWARD.PARALLEL');
  }
  public reload(): void {
    try {
      this.blockedDocument = true;
      //dataSetId: string, dataSourceName: string, variantId: string
      this.apsCommandResourceService.reloadApsDataDto(this.dataSetName, this.dataSourceName, this.dataSetVariant).subscribe((apsData) => {
        this.data = apsData;
        this.blockedDocument = false;
      });
    } catch (e) {
      console.log(e);
      this.blockedDocument = false;
    }
  }
  public save(): void {
    try {
      this.blockedDocument = true;
      //dataSetId: string, dataSourceName: string, variantId: string
      this.apsCommandResourceService.saveApsSaveResult(this.dataSetName, this.dataSourceName, this.dataSetVariant).subscribe(result => {
        this.blockedDocument = false;
      });
    } catch (e) {
      console.log(e);
      this.blockedDocument = false;
    }
  }
  @Input("viewTaskWindow") public viewTaskWindow: boolean = false;
  @Input("selectedTask") public selectedTask: TaskController = null;
  public taskClicked = (taskController) => {
    this.viewTaskWindow = true;
    this.selectedTask = taskController;
    //console.log("Selected: "+JSON.stringify(this.selectedTask.data.data));
    this.changeDetector.detectChanges();
  }
  @Input("viewWorkOrder") public viewWorkOrder: boolean = false;
  public selectedWorkOrder: WorkOrderProjectController = null;
  public workOrderClicked = (woController) => {
    this.viewWorkOrder = true;
    this.selectedWorkOrder = woController;
    //console.log("Selected: "+JSON.stringify(this.selectedTask.data.data));
    this.changeDetector.detectChanges();
  }

  @Input("viewMachine") public viewMachine: boolean = false;
  public selectedMachine: MachineController = null;
  public machineClicked = (v) => {
    this.viewMachine = true;
    this.selectedMachine = v;
    //console.log("Selected: "+JSON.stringify(this.selectedTask.data.data));
    this.changeDetector.detectChanges();
  }

  public lessTimeDetailsDisabled(): boolean {
    return this.currentGraphicConfigIndex === 0;
  }
  public moreTimeDetailsDisabled(): boolean {
    return this.currentGraphicConfigIndex === (this.graphicConfigurations.length - 1);
  }
  protected websocketTasks: Subject<WSScheduleTaskResponseItem>;
  private websocketConnect(dataSetId, dataSetVariant) {
    try {
      /*this.websocketService.connect("ws://localhost:8080/openi40/openi40.scheduler.topic/"+dataSetId+"/"+dataSetVariant).subscribe
      (
      (response: MessageEvent): WSScheduleTaskResponseItem => {
        let data = JSON.parse(response.data);
        return data as WSScheduleTaskResponseItem;
      }
    );;*/
    } catch (e) {
      console.error(e);
    }
  }
  @Input("viewSchedulingSettings") viewSchedulingSettings: boolean = false;
  showSchedulingSettings(evt) {
    this.viewSchedulingSettings = true;
    this.changeDetector.markForCheck();
  }
  closeSchedulingSettings(evt) {
    this.viewSchedulingSettings = false;
    this.blockedDocument = false;
    this.changeDetector.markForCheck();
  }
  onStartBlockUi(evt) {
    this.blockedDocument = true;
    this.changeDetector.markForCheck();
  }
  onStopBlockUi(evt) {
    this.blockedDocument = false;
    this.changeDetector.markForCheck();
  }
  updateApsData(evt) {
    this.data = evt;
    this.changeDetector.markForCheck();
    //this.blockedDocument=true;
    /*this.apsCommandResourceService.refreshApsDataDto(this.data.dataSetName,this.data.dataSourceName,this.data.dataSetVariant).subscribe((apsData)=>{
      this.data=apsData;
      this.blockedDocument=false;
      this.changeDetector.detectChanges();
    });*/
  }
  public browsedTasks:TaskDto[]=[];
  public addviewedTask(t:TaskDto) {
    this.browsedTasks.push(t);
  }
  public removeViewedTask(t:TaskDto) {
    this.browsedTasks=this.browsedTasks.filter((entry=>t!==entry));
  }

  private resolutionIndex: number = 0;
  toggleResolution(evt) {
    this.resolutionIndex++;
    if (this.resolutionIndex > DIMENSION_CHOICES.length) {
      this.resolutionIndex = 0;
    }
    this.graphicConfigurations = DIMENSION_CHOICES[this.resolutionIndex].configurations;
    this.currentGraphicConfiguration = DIMENSION_CHOICES[this.resolutionIndex].configurations[this.currentGraphicConfigIndex];
  }
  public get resolutionLabel() {
    return DIMENSION_CHOICES[this.resolutionIndex].label;
  }
  public get resolutionDescription() {
    return DIMENSION_CHOICES[this.resolutionIndex].description;
  }

}

