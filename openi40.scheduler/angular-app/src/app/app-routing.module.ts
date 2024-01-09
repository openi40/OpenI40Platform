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
import { RouterModule } from '@angular/router';
import { OpenI40SchedulerScreensModule } from '@openi40/scheduler-screens';
import { OpenI40BackofficeScreensModule } from '@openi40/backoffice-screens';



@NgModule({
  imports: [OpenI40SchedulerScreensModule ,OpenI40BackofficeScreensModule],
  exports: [RouterModule]
})
export class AppRoutingModule { }
