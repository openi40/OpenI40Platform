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
import { GuiController } from './GuiController';
import { ApsDataGuiItem } from '../guidatamodel/ApsDataGuiItem';
import { ResourcesGanttRenderer } from '../guirenderers/ResourcesGanttRenderer';
import { Renderer2 } from '@angular/core';
import { ResourcesPanelController } from './ResourcesPanelController';
import { TimesheetPanelController } from './TimesheetPanelController';
import { TimesheetPanelGuiItem } from '../guidatamodel/TimesheetPanelGuiItem';
import { TaskController } from './TaskController';
import { TaskGuiItem } from '../guidatamodel/TaskGuiItem';
import { YBlocksRetrieverMap } from '../guidatamodel/YBlocksRetrieverMap';
import { ITaskYCoordinatesCalculator } from '../guidatamodel/ITaskYCoordinatesCalculator';
import { MachineGuiItem } from '../guidatamodel/MachineGuiItem';
import { ResourceGanttTaskYCoordinatesCalculator } from './ResourceGanttTaskYCoordinatesCalculator';
import { IControllerCallback } from './IControllerCallback';
import { TaskToTaskEdgeConnectorController } from './TaskToTaskEdgeConnectorController'
import { IGuiController } from './IGuiController';
import { SupplyReservationDto, WarehouseDto } from 'projects/openi40-scheduler-api/src/lib';
import { WarehouseTimesheetController } from './WarehouseTimesheetController'
import { DemandTimesheetController } from './DemandTimesheetController'
import { WarehouseTimesheetGuiItem } from '../guidatamodel/WarehouseTimesheetGuiItem';
import { WarehouseTimesheetRenderer } from '../guirenderers/WarehouseTimesheetRenderer';
import { DemandTimesheetGuiItem } from '../guidatamodel/DemandTimesheetGuiItem';
import { DemandTimesheetRenderer } from '../guirenderers/DemandTimesheetRenderer';
import { WarehouseToTaskConnectorController } from './WarehouseToTaskConnectorController';
import { WarehouseToTaskConnectorGuiItem } from '../guidatamodel/WarehouseToTaskConnectorGuiItem';
import { WarehouseToTaskConnectorRenderer } from '../guirenderers/WarehouseToTaskConnectorRenderer';
import { TaskToDemandConnectorController } from './TaskToDemandConnectorController';
import { TaskToDemandConnectorGuiItem } from '../guidatamodel/TaskToDemandConnectorGuiItem';
import { TaskToDemandConnectorRenderer } from '../guirenderers/TaskToDemandConnectorRenderer';
import { ISvgPatternsProvider } from '../guirenderers/ISvgPatternsProvider';
export class ResourcesGanttController extends GuiController<ApsDataGuiItem, ResourcesGanttRenderer>{
  protected resourcesPanelController: ResourcesPanelController = null;
  protected timesheetPanelController: TimesheetPanelController = null;
  private leftDiv: Element = null;
  private rightDiv: Element = null;
  private resourcesMap: YBlocksRetrieverMap = null;
  protected _taskControllers: TaskController[] = [];
  protected _taskToTaskConnections: TaskToTaskEdgeConnectorController[] = [];
  protected _warehousesTimesheetControllers: WarehouseTimesheetController[] = [];
  protected _demandTimesheetControllers: DemandTimesheetController[] = [];
  protected _warehouseToTaskConnectorController:WarehouseToTaskConnectorController[]=[];
  protected _taskToDemandConnections:TaskToDemandConnectorController[]=[];
  public constructor(_data: ApsDataGuiItem, renderer2: Renderer2,svgPatternsProvider:ISvgPatternsProvider) {
    super(_data, renderer2, (_d: ApsDataGuiItem, _r: Renderer2) => { return new ResourcesGanttRenderer(_r, _d) });
    let tasksMap: Map<string, TaskController> = new Map();
    this.resourcesPanelController = new ResourcesPanelController(_data, renderer2,svgPatternsProvider);
    this.timesheetPanelController = new TimesheetPanelController(new TimesheetPanelGuiItem(_data.dataItem.timesheet,this.boundGuiItem.graphicConfig, this.resourcesPanelController.verticalSlices,this.resourcesPanelController.resourcesRootController.data.listNodes), renderer2,svgPatternsProvider);
    this.leftDiv = this.createHtmlElement("div");
    this.setAttribute(this.leftDiv, "class", "col-3");
    this.rightDiv = this.createHtmlElement("div");
    this.setAttribute(this.rightDiv, "class", "col-9");
    //style="overflow-y:hidden; overflow-x:hidden"
    this.setAttribute(this.rightDiv, "style", "overflow-x: auto;");
    this.resourcesMap = this.resourcesPanelController.resourcesRootController.data.mappedControls;
    let demandConnectedTasks:Map<string,TaskController[]>=new Map();
    if (this.boundGuiItem.data.productiveCompanies) {
      this.boundGuiItem.data.productiveCompanies.forEach((pc) => {
        let thisCompanyDemandConnectedTasks:TaskController[]=[];
        demandConnectedTasks.set(pc.code,thisCompanyDemandConnectedTasks);
        if (pc.plants) {
          pc.plants.forEach((pl) => {
            if (pl.workOrders) {
              pl.workOrders.forEach((wo) => {
                if (wo.tasks) {
                  wo.tasks.forEach((task) => {
                    let tc: TaskController = new TaskController(new TaskGuiItem(task,this.boundGuiItem.graphicConfig, this.timesheetPanelController.data.currentSearchStructure), renderer2);
                    tasksMap.set(tc.data.id, tc);
                    this._taskControllers.push(tc);
                    if (wo.rootSalesOrderWorkOrder==true && task.workOrderRootTask==true) {
                      //In this case this must be connected to delivery
                      thisCompanyDemandConnectedTasks.push(tc);
                    }
                  });
                }
              });
            }
          });
        }
      });
    }
    let whareHousesCache = new Map<string, WarehouseTimesheetController>();
    this.resourcesPanelController.resourcesRootController.mainChilds.forEach((pc) => {
      let thisCompanyDemandConnectedTasks= demandConnectedTasks.get(pc.data.data.code);
      let thisCompanyDemand=new DemandTimesheetController(
        new DemandTimesheetGuiItem(pc.data.data,this.boundGuiItem.graphicConfig, this.timesheetPanelController.data, pc.demand.data, this.resourcesMap)
        , renderer2, (_d: DemandTimesheetGuiItem, _r: Renderer2) => { return new DemandTimesheetRenderer(_r, _d) }
      );
      if (thisCompanyDemandConnectedTasks) {
        thisCompanyDemandConnectedTasks.forEach((taskController)=>{
          let _taskDemandConnectionController:TaskToDemandConnectorController=null;
          let _taskDemandGuiItem:TaskToDemandConnectorGuiItem=new TaskToDemandConnectorGuiItem([], this.boundGuiItem.graphicConfig, taskController.data,thisCompanyDemand.data);
          let _taskDemandController:TaskToDemandConnectorController=new TaskToDemandConnectorController(_taskDemandGuiItem,renderer2,(_d: TaskToDemandConnectorGuiItem, _r: Renderer2) => {return new TaskToDemandConnectorRenderer(renderer2,_d);});
          this._taskToDemandConnections.push(_taskDemandController);
        });
      }
      this._demandTimesheetControllers.push(thisCompanyDemand);
      pc.mainChilds.forEach((plnt) => {
        plnt.warehouses.forEach((wh) => {
          let wt = new WarehouseTimesheetController(
            new WarehouseTimesheetGuiItem(wh.data.data,this.boundGuiItem.graphicConfig, this.timesheetPanelController.data, wh.data, this.resourcesMap), renderer2, (_d: WarehouseTimesheetGuiItem, _r: Renderer2) => { return new WarehouseTimesheetRenderer(_r, _d) });
          this._warehousesTimesheetControllers.push(wt);
          whareHousesCache.set(wh.data.code, wt);
        })
      })
    })
    if (this._taskControllers) {
      this._taskControllers.forEach((_tc) => {

        if (_tc.data.data.dependencies) {
          _tc.data.data.dependencies.forEach((dep) => {
            if (tasksMap.has(dep.supplyTaskId)) {
              const dependentTask: TaskGuiItem = _tc.data;
              const erogatingTask: TaskGuiItem = tasksMap.get(dep.supplyTaskId).data;
              let edge: TaskToTaskEdgeConnectorController = new TaskToTaskEdgeConnectorController(dep, renderer2, erogatingTask, dependentTask);
              this._taskToTaskConnections.push(edge);
            }
          })
        }
        if (_tc.data.data.materialConsumptions) {
          let timeMap = new Map<string, Map<number, SupplyReservationDto[]>>();
          _tc.data.data.materialConsumptions.forEach((mc) => {
            if (mc.ownedReservations) {
              mc.ownedReservations.forEach((res) => {
                if (res.supplyType === "StockSupply" && res.warehouseCode) {
                  if (!timeMap.has(res.warehouseCode)) {
                    timeMap.set(res.warehouseCode, new Map());
                  }
                  if (!timeMap.get(res.warehouseCode).has(mc.requiredDateTimeUTC)) {
                    let arr: SupplyReservationDto[] = [];
                    timeMap.get(res.warehouseCode).set(mc.requiredDateTimeUTC, arr);
                  }
                  timeMap.get(res.warehouseCode).get(mc.requiredDateTimeUTC).push(res);
                }
              });
            }
          })
          let whCodes = Array.from(timeMap.keys());
          whCodes.forEach((whareHouseCode) => {
            let wh = whareHousesCache.get(whareHouseCode);
            if (wh) {
              let map = timeMap.get(whareHouseCode);
              let arrayOfArray = Array.from(map.values());
              arrayOfArray.forEach((resArray) => {
                let edgeModel: WarehouseToTaskConnectorGuiItem = new WarehouseToTaskConnectorGuiItem(resArray,this.boundGuiItem.graphicConfig, wh.data, _tc.data);
                let edgeController: WarehouseToTaskConnectorController = new WarehouseToTaskConnectorController(edgeModel, renderer2, (_d: WarehouseToTaskConnectorGuiItem, _r: Renderer2) => { return new WarehouseToTaskConnectorRenderer(_r, _d) });
                this._warehouseToTaskConnectorController.push(edgeController);
              })

            }
          })
        }
      });
    }
  }
  public addControllerCallback(callback: IControllerCallback): void {
    super.addControllerCallback(callback);
    this.resourcesPanelController.addControllerCallback(callback);
    this.timesheetPanelController.addControllerCallback(callback);
    if (this._taskControllers) {
      this._taskControllers.forEach((ch) => { ch.addControllerCallback(callback); })
    }
    if (this._taskToTaskConnections) {
      this._taskToTaskConnections.forEach((ch) => { ch.addControllerCallback(callback); })
    }
    if (this._warehousesTimesheetControllers) {
      this._warehousesTimesheetControllers.forEach((ch) => { ch.addControllerCallback(callback); })
    }
    if (this._demandTimesheetControllers) {
      this._demandTimesheetControllers.forEach((ch) => { ch.addControllerCallback(callback); })
    }
    if (this._warehouseToTaskConnectorController) {
      this._warehouseToTaskConnectorController.forEach((ch) => { ch.addControllerCallback(callback); })
    }
  }
  private taskCoordinatesCalculator: ITaskYCoordinatesCalculator = new ResourceGanttTaskYCoordinatesCalculator();
  public show(rootElement: Element): Element {

    let rootGenerated: Element = super.show(rootElement);
    this.setAttribute(rootGenerated, "class", "grid");
    if (rootGenerated.childNodes.length == 0) {
      this.renderer2.appendChild(rootGenerated, this.leftDiv);
      this.renderer2.appendChild(rootGenerated, this.rightDiv);
    }
    this.resourcesPanelController.show(this.leftDiv);
    if (this.resourcesPanelController?.data?.width) {
      this.renderer2.setAttribute(this.leftDiv,"style","width:"+this.resourcesPanelController.data.width+"px;")  ;
    }
    let timesheetPanel: Element = this.timesheetPanelController.show(this.rightDiv);
    if (this._taskControllers) {
      this._taskControllers.forEach((taskController) => {
        taskController.data.recalculateTaskCoordinates(this.timesheetPanelController.data, this.taskCoordinatesCalculator, this.resourcesMap);
        taskController.show(timesheetPanel);
        //console.log(taskController.data.toString())
      });
    }
    if (this._taskToTaskConnections) {
      this._taskToTaskConnections.forEach((ch) => {
        ch.show(timesheetPanel);
      })
    }
    if (this._warehousesTimesheetControllers) {
      this._warehousesTimesheetControllers.forEach((ch) => { ch.show(timesheetPanel); })
    }
    if (this._demandTimesheetControllers) {
      this._demandTimesheetControllers.forEach((ch) => { ch.show(timesheetPanel); })
    }
    if (this._warehouseToTaskConnectorController) {
      this._warehouseToTaskConnectorController.forEach((ch) => { ch.show(timesheetPanel); })
    }
    if (this._taskToDemandConnections) {
      this._taskToDemandConnections.forEach((t2d)=>{
        t2d.show(timesheetPanel);
        console.log(t2d.data.from.data.code);
      });
    }
    return rootGenerated;
  }
  public get taskControllers(): TaskController[] { return this._taskControllers; }
}
