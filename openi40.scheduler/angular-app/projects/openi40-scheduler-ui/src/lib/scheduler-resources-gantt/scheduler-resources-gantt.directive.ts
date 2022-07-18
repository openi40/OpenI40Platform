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
import { OnInit, AfterViewInit, ElementRef, Renderer2, Directive, Input, Output, EventEmitter, OnChanges, SimpleChanges } from '@angular/core';
import { ApsCommandResourceService, ApsDataDto, ApsDataListService, TaskDependency, TaskDto} from 'projects/openi40-scheduler-api/src/lib';
import {ApsDataGuiItem} from '../guidatamodel/ApsDataGuiItem'
import {ResourcesGanttRenderer} from '../guirenderers/ResourcesGanttRenderer'
import {ResourcesGanttController} from '../guicontrol/ResourcesGanttController'
import { ActivatedRoute } from '@angular/router';
import { IControllerCallback } from '../guicontrol/IControllerCallback';
import { TaskController } from '../guicontrol/TaskController';
import { MachineController } from '../guicontrol/MachineController';
import { WorkCenterController } from '../guicontrol/WorkCenterController';
import { PlantController } from '../guicontrol/PlantController';
import { ProductiveCompanyController } from '../guicontrol/ProductiveCompanyController';
import { DepartmentController } from '../guicontrol/DepartmentController';
import {ControllerAwareCallbackSupport} from '../guicontrol/ControllerAwareCallbackSupport';
import { TaskRenderer } from '../guirenderers/TaskRenderer';
import { TaskGuiItem } from '../guidatamodel/TaskGuiItem';
import { MachineGuiItem } from '../guidatamodel/MachineGuiItem';
import { MachineRenderer } from '../guirenderers/MachineRenderer';
import { WorkCenterGuiItem } from '../guidatamodel/WorkCenterGuiItem';
import { WorkCenterRenderer } from '../guirenderers/WorkCenterRenderer';
import { PlantGuiItem } from '../guidatamodel/PlantGuiItem';
import { PlantRenderer } from '../guirenderers/PlantRenderer';
import { ProductiveCompanyRenderer } from '../guirenderers/ProductiveCompanyRenderer';
import { ProductiveCompanyGuiItem } from '../guidatamodel/ProductiveCompanyGuiItem';
import { DepartmentRenderer } from '../guirenderers/DepartmentRenderer';
import { DepartmentGuiItem } from '../guidatamodel/DepartmentGuiItem';
import { Openi40GraphicConfiguration } from '../screenconstants';
import { SvgPatternsProvider } from '../guirenderers/SvgPatternsProvider';
import { IGuiController } from '../guicontrol/IGuiController';
@Directive({
  selector: '[openi40-resources-gantt]',

})

export class SchedulerResourcesGanttDirective implements OnInit,AfterViewInit,OnChanges  {

  private dataSetName: string;
  private dataSetVariant: string;
  public apsDataGuiItem:ApsDataGuiItem

  @Input("apsData")  apsData:ApsDataDto=null;
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
  ngOnChanges(changes: SimpleChanges): void {
    this.showApsData();
  }
  ngAfterViewInit(): void {

  }
  private _graphicConfiguration:Openi40GraphicConfiguration=null;
  private resourceGanttController:ResourcesGanttController=null;
  private actualGui:Element=null;
  private showApsData():void {
    this.startRefresh.emit(this);
    console.log("Begin showApsData(...)");
    this.apsDataGuiItem= new ApsDataGuiItem(this.apsData,this._graphicConfiguration);
    //TODO: MANAGE REPLACEMENT OF CONTENT AS REAL SWAP
    this.resourceGanttController=new ResourcesGanttController(this.apsDataGuiItem,this.renderer,this.svgPatternsProvider);
    this.rebindGuiEvents();
    let newGui=this.resourceGanttController.show(this.guiElement.nativeElement);

    this.swapGui(newGui);
    console.log("End showApsData(...)");
    this.endRefresh.emit(this);
  }
  ngOnInit(): void {
    this.showApsData();
  }
  @Input("taskClick") taskClick=(controller: TaskController, _event: Event)=> {

  }
  @Input("machineClick")  machineClick=(controller: MachineController, _event: Event) =>{

  }
  @Input("workCenterClick")  workCenterClick=(controller: WorkCenterController, _event: Event) =>{

  }
  @Input("plantClick")   plantClick=(controller: PlantController, _event: Event)=>{

  }
  @Input("productiveCompanyClick")    productiveCompanyClick=(controller: ProductiveCompanyController, _event: Event)=> {

  }
  @Input("departmentCompanyClick")    departmentCompanyClick=(controller: DepartmentController, _event: Event) =>{

  }
  public redraw=(controller:IGuiController,_event:Event)=>{
    this.resourceGanttController.update();
  }
  private rebindGuiEvents() {

      this.resourceGanttController.addControllerCallback(new ControllerAwareCallbackSupport<TaskGuiItem,TaskRenderer,TaskController>(
        ["click"],["TaskController"],this.taskClick
      ));
      this.resourceGanttController.addControllerCallback(new ControllerAwareCallbackSupport<MachineGuiItem,MachineRenderer,MachineController>(
        ["click"],["MachineController"],this.machineClick
      ));
      this.resourceGanttController.addControllerCallback(new ControllerAwareCallbackSupport<WorkCenterGuiItem,WorkCenterRenderer,WorkCenterController>(
        ["click"],["WorkCenterController"],this.workCenterClick
      ));
      this.resourceGanttController.addControllerCallback(new ControllerAwareCallbackSupport<WorkCenterGuiItem,WorkCenterRenderer,WorkCenterController>(
        ["redraw"],["WorkCenterController"],this.redraw
      ));
      this.resourceGanttController.addControllerCallback(new ControllerAwareCallbackSupport<DepartmentGuiItem,DepartmentRenderer,DepartmentController>(
        ["click"],["DepartmentController"],this.departmentCompanyClick
      ));
      this.resourceGanttController.addControllerCallback(new ControllerAwareCallbackSupport<DepartmentGuiItem,DepartmentRenderer,DepartmentController>(
        ["redraw"],["DepartmentController"],this.redraw
      ));

      this.resourceGanttController.addControllerCallback(new ControllerAwareCallbackSupport<PlantGuiItem,PlantRenderer,PlantController>(
        ["click"],["PlantController"],this.plantClick
      ));
      this.resourceGanttController.addControllerCallback(new ControllerAwareCallbackSupport<PlantGuiItem,PlantRenderer,PlantController>(
        ["redraw"],["PlantController"],this.redraw
      ));

      this.resourceGanttController.addControllerCallback(new ControllerAwareCallbackSupport<ProductiveCompanyGuiItem,ProductiveCompanyRenderer,ProductiveCompanyController>(
        ["click"],["ProductiveCompanyController"],this.productiveCompanyClick
      ));
      this.resourceGanttController.addControllerCallback(new ControllerAwareCallbackSupport<ProductiveCompanyGuiItem,ProductiveCompanyRenderer,ProductiveCompanyController>(
        ["redraw"],["ProductiveCompanyController"],this.redraw
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

