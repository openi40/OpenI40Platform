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
import { Renderer2 } from '@angular/core';
import { WorkOrdersPanelRenderer } from '../guirenderers/WorkOrdersPanelRenderer';
import {WorkOrderTreeRootController} from './WorkOrderTreeRootController'
import { WorkOrdersTreeApsDataGuiItem } from '../guidatamodel/WorkOrdersTreeApsDataGuiItem';
import { IControllerCallback } from './IControllerCallback';
import { ISvgPatternsProvider } from '../guirenderers/ISvgPatternsProvider';
export class WorkOrdersPanelController extends GuiController<WorkOrdersTreeApsDataGuiItem,WorkOrdersPanelRenderer>{
    private _workOrdersRootController:WorkOrderTreeRootController =null;
    public constructor(apsData:WorkOrdersTreeApsDataGuiItem,renderer2:Renderer2,svgPatternsProvider:ISvgPatternsProvider ){
        super(apsData,renderer2,(d:WorkOrdersTreeApsDataGuiItem,r:Renderer2)=>{
          return new WorkOrdersPanelRenderer(r,d,svgPatternsProvider);})
        this._workOrdersRootController=new WorkOrderTreeRootController(apsData.workOrderTreeRootGuiItem,renderer2);
    }
    public get workOrdersRootController():WorkOrderTreeRootController {
        return this._workOrdersRootController;
    }
    public get verticalSlices():number {
        return this.boundGuiItem.workOrderTreeRootGuiItem.countNodes();
    }
    public show(rootItem:Element):Element{
        let rootGenerated:Element=super.show(rootItem);
        this._workOrdersRootController.show(rootGenerated);
        return rootGenerated;
    }
    public addControllerCallback(callback:IControllerCallback):void {
      super.addControllerCallback(callback);
      this._workOrdersRootController.addControllerCallback(callback);

    }
}
