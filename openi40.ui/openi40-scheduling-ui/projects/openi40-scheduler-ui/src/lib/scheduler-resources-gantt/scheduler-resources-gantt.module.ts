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
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';
import {SchedulerResourcesGanttDirective} from './scheduler-resources-gantt.directive'

import { BrowserModule } from '@angular/platform-browser';
import { SvgPatternsProvider } from '../guirenderers/SvgPatternsProvider';

@NgModule({
  declarations: [
    SchedulerResourcesGanttDirective
  ],
  imports: [
  HttpClientModule
  ],
  exports:[SchedulerResourcesGanttDirective],
  providers: [SvgPatternsProvider],
  bootstrap: []
})
export class SchedulerResourcesGanttModule {

}
