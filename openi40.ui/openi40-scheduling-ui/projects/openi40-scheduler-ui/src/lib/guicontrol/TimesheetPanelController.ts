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
import { TimesheetPanelRenderer } from '../guirenderers/TimesheetPanelRenderer';
import { TimesheetPanelGuiItem } from '../guidatamodel/TimesheetPanelGuiItem';
import { Renderer2 } from '@angular/core';
import { TaskController } from './TaskController';
import { ISvgPatternsProvider } from '../guirenderers/ISvgPatternsProvider';
export class TimesheetPanelController extends GuiController<TimesheetPanelGuiItem, TimesheetPanelRenderer> {

    public constructor(_data: TimesheetPanelGuiItem, renderer2: Renderer2,svgPatternsProvider:ISvgPatternsProvider) {
        super(_data,renderer2,(_d: TimesheetPanelGuiItem, _r: Renderer2) => {return new TimesheetPanelRenderer(_r,_d,svgPatternsProvider)});

    }

}
