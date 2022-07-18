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
import { ApsDataDto, TimesheetDto } from 'projects/openi40-scheduler-api/src/lib';
import { ApsDataGuiItem } from '../guidatamodel/ApsDataGuiItem';
import { ResourcesPanelRenderer } from '../guirenderers/ResourcesPanelRenderer';
import { Renderer2 } from '@angular/core';
import { ResourcesRootRenderer } from '../guirenderers/ResourcesRootRenderer';
import { ResourcesRootController } from './ResourcesRootController';
import { IControllerCallback } from './IControllerCallback';
import { ISvgPatternsProvider } from '../guirenderers/ISvgPatternsProvider';

export class ResourcesPanelController extends GuiController<ApsDataGuiItem,ResourcesPanelRenderer>{
    private _resourcesRootController:ResourcesRootController=null;
    public constructor(apsData:ApsDataGuiItem,renderer2:Renderer2 ,svgPatternsProvider:ISvgPatternsProvider){
        super(apsData,renderer2,(d:ApsDataGuiItem,r:Renderer2)=>{return new ResourcesPanelRenderer(r,d,svgPatternsProvider);})
        this._resourcesRootController=new ResourcesRootController(apsData.resourcesRootGuiItem,renderer2);
    }
    public get resourcesRootController():ResourcesRootController {
        return this._resourcesRootController;
    }
    public get verticalSlices():number {
        return this.boundGuiItem.resourcesRootGuiItem.countNodes();
    }
    public show(rootItem:Element):Element{
        let rootGenerated:Element=super.show(rootItem);
        this.resourcesRootController.show(rootGenerated);
        return rootGenerated;
    }
    public addControllerCallback(callback:IControllerCallback):void {
      super.addControllerCallback(callback);
      this._resourcesRootController.addControllerCallback(callback);

    }
}
