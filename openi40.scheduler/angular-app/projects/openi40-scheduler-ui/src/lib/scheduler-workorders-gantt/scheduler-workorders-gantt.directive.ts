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
import { AfterViewInit, Directive, ElementRef, EventEmitter, Input, OnChanges, OnInit, Renderer2, SimpleChanges } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ApsCommandResourceService, ApsDataDto } from 'projects/openi40-scheduler-api/src/lib';
import { ControllerAwareCallbackSupport } from '../guicontrol/ControllerAwareCallbackSupport';
import { IGuiController } from '../guicontrol/IGuiController';
import { TaskController } from '../guicontrol/TaskController';
import { WorkOrderProjectController } from '../guicontrol/WorkOrderProjectController';
import { WorkOrdersGanttController } from '../guicontrol/WorkOrdersGanttController';
import { WorkOrderTreePlantController } from '../guicontrol/WorkOrderTreePlantController';
import { WorkOrderTreeProductiveCompanyController } from '../guicontrol/WorkOrderTreeProductiveCompanyController';
import { WorkOrderTreeTaskController } from '../guicontrol/WorkOrderTreeTaskController';
import { WorkOrderTreeWorkOrderController } from '../guicontrol/WorkOrderTreeWorkOrderController';
import { TaskGuiItem } from '../guidatamodel/TaskGuiItem';
import { WorkOrderProjectGuiItem } from '../guidatamodel/WorkOrderProjectGuiItem';
import { WorkOrdersTreeApsDataGuiItem } from '../guidatamodel/WorkOrdersTreeApsDataGuiItem';
import { WorkOrderTreePlantGuiItem } from '../guidatamodel/WorkOrderTreePlantGuiItem';
import { WorkOrderTreeProductiveCompanyGuiItem } from '../guidatamodel/WorkOrderTreeProductiveCompanyGuiItem';
import { WorkOrderTreeTaskGuiItem } from '../guidatamodel/WorkOrderTreeTaskGuiItem';
import { WorkOrderTreeWorkOrderGuiItem } from '../guidatamodel/WorkOrderTreeWorkOrderGuiItem';
import { SvgPatternsProvider } from '../guirenderers/SvgPatternsProvider';
import { TaskRenderer } from '../guirenderers/TaskRenderer';
import { WorkOrderProjectRenderer } from '../guirenderers/WorkOrderProjectRenderer';
import { WorkOrderTreePlantRenderer } from '../guirenderers/WorkOrderTreePlantRenderer';
import { WorkOrderTreeProductiveCompanyRenderer } from '../guirenderers/WorkOrderTreeProductiveCompanyRenderer';
import { WorkOrderTreeTaskRenderer } from '../guirenderers/WorkOrderTreeTaskRenderer';
import { WorkOrderTreeWorkOrderRenderer } from '../guirenderers/WorkOrderTreeWorkOrderRenderer';
import { Openi40GraphicConfiguration } from '../screenconstants';

@Directive({
  selector: '[openi40-workorders-gantt]',

})

export class SchedulerWorkOrdersGanttDirective implements OnInit,AfterViewInit,OnChanges   {

  private dataSetName: string;
  private dataSetVariant: string;
  public apsDataGuiItem:WorkOrdersTreeApsDataGuiItem=null;
  @Input("apsData")  apsData:ApsDataDto=null;
 private _graphicConfiguration:Openi40GraphicConfiguration=null;
 @Input("graphicConfiguration") set graphicConfiguration(gc:Openi40GraphicConfiguration){
    this._graphicConfiguration=gc;
    if (this.apsDataGuiItem) {
      this.showApsData();
    }
  }
  @Input("startRefresh") startRefresh:EventEmitter<Object>=new EventEmitter();
  @Input("endRefresh") endRefresh:EventEmitter<Object>=new EventEmitter();


  constructor(protected apsCommandResourceService:ApsCommandResourceService,
              private route: ActivatedRoute,
              protected renderer:Renderer2,
              private guiElement: ElementRef,
              private svgPatternsProvider:SvgPatternsProvider
              ) {

  }
  ngAfterViewInit(): void {

  }
  private workOrdersGanttController:WorkOrdersGanttController=null;
  private actualGui:Element=null;
  private showApsData():void {
    this.startRefresh.emit(this);
    console.log("Begin setApsData(...)");
    this.apsDataGuiItem= new WorkOrdersTreeApsDataGuiItem(this.apsData,this._graphicConfiguration);
    //TODO: MANAGE REPLACEMENT OF CONTENT AS REAL SWAP
    this.workOrdersGanttController=new WorkOrdersGanttController(this.apsDataGuiItem,this.renderer,this.svgPatternsProvider);
    this.rebindGuiEvents();
    let newGui=this.workOrdersGanttController.show(this.guiElement.nativeElement);
    this.swapGui(newGui);
    console.log("End setApsData(...)");
    this.endRefresh.emit(this);
  }
  ngOnInit(): void {
    //this.showApsData();
  }
  ngOnChanges(changes: SimpleChanges): void {
    if (changes.apsData ) {
      this.showApsData();
    }
  }
  @Input("taskClick")   taskClick=(controller: TaskController, _event: Event) =>{

  }
  @Input("taskTreeItemClick")   taskTreeItemClick=(controller: WorkOrderTreeTaskController, _event: Event) =>{

  }
  @Input("workOrderTreeItemClick") workOrderTreeItemClick=(controller: WorkOrderTreeWorkOrderController, _event: Event) =>{

  }
  @Input("workOrderProjectClick")  workOrderProjectClick=(controller: WorkOrderProjectController, _event: Event) =>{

  }
  @Input("plantClick")  plantClick=(controller: WorkOrderTreePlantController, _event: Event) =>{

  }
  @Input("productiveCompanyClick")  productiveCompanyClick=(controller: WorkOrderTreeProductiveCompanyController, _event: Event) =>{

  }
  public redraw=(controller:IGuiController,_event:Event)=>{
    this.workOrdersGanttController.update();
  }
  private rebindGuiEvents() {

      this.workOrdersGanttController.addControllerCallback(new ControllerAwareCallbackSupport<TaskGuiItem,TaskRenderer,TaskController>(
        ["click"],["TaskController"],this.taskClick
      ));


      this.workOrdersGanttController.addControllerCallback(new ControllerAwareCallbackSupport<WorkOrderTreeTaskGuiItem,WorkOrderTreeTaskRenderer,WorkOrderTreeTaskController>(
        ["click"],["WorkOrderTreeTaskController"],this.taskTreeItemClick
      ));
      this.workOrdersGanttController.addControllerCallback(new ControllerAwareCallbackSupport<WorkOrderTreeTaskGuiItem,WorkOrderTreeTaskRenderer,WorkOrderTreeTaskController>(
        ["redraw"],["WorkOrderTreeTaskController"],this.redraw
      ));


      this.workOrdersGanttController.addControllerCallback(new ControllerAwareCallbackSupport<WorkOrderTreeWorkOrderGuiItem,WorkOrderTreeWorkOrderRenderer,WorkOrderTreeWorkOrderController>(
        ["click"],["WorkOrderTreeWorkOrderController"],this.workOrderTreeItemClick
      ));
      this.workOrdersGanttController.addControllerCallback(new ControllerAwareCallbackSupport<WorkOrderTreeWorkOrderGuiItem,WorkOrderTreeWorkOrderRenderer,WorkOrderTreeWorkOrderController>(
        ["redraw"],["WorkOrderTreeWorkOrderController"],this.redraw
      ));

      this.workOrdersGanttController.addControllerCallback(new ControllerAwareCallbackSupport<WorkOrderProjectGuiItem,WorkOrderProjectRenderer,WorkOrderProjectController>(
        ["click"],["WorkOrderProjectController"],this.workOrderProjectClick
      ));


      this.workOrdersGanttController.addControllerCallback(new ControllerAwareCallbackSupport<WorkOrderTreeProductiveCompanyGuiItem,WorkOrderTreeProductiveCompanyRenderer,WorkOrderTreeProductiveCompanyController>(
        ["click"],["WorkOrderTreeProductiveCompanyController"],this.productiveCompanyClick
      ));
      this.workOrdersGanttController.addControllerCallback(new ControllerAwareCallbackSupport<WorkOrderTreeProductiveCompanyGuiItem,WorkOrderTreeProductiveCompanyRenderer,WorkOrderTreeProductiveCompanyController>(
        ["redraw"],["WorkOrderTreeProductiveCompanyController"],this.redraw
      ));
      this.workOrdersGanttController.addControllerCallback(new ControllerAwareCallbackSupport<WorkOrderTreePlantGuiItem,WorkOrderTreePlantRenderer,WorkOrderTreePlantController>(
        ["click"],["WorkOrderTreePlantController"],this.plantClick
      ));
      this.workOrdersGanttController.addControllerCallback(new ControllerAwareCallbackSupport<WorkOrderTreePlantGuiItem,WorkOrderTreePlantRenderer,WorkOrderTreePlantController>(
        ["redraw"],["WorkOrderTreePlantController"],this.redraw
      ));

  }

  private swapGui(newGui:Element):Element{
    const oldGui=this.actualGui;
    if (this.actualGui) {
      this.renderer.removeChild(this.guiElement.nativeElement,this.actualGui);
    }
    this.actualGui=newGui;
    this.renderer.appendChild(this.guiElement.nativeElement,this.actualGui);
    return oldGui;
  }
}

