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
import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { BlockUIModule } from 'primeng/blockui';
import { ButtonModule } from 'primeng/button';
import { DialogModule } from 'primeng/dialog';
import { PanelModule } from 'primeng/panel';
import { TabViewModule } from 'primeng/tabview';
import { SelectButtonModule } from 'primeng/selectbutton';
import { SchedulerResourcesGanttModule, SchedulerWorkOrdersGanttModule } from 'projects/openi40-scheduler-ui/src/lib';
import { Openi40TasksDatatableModule } from '../openi40-tasks-datatable/openi40-tasks-datatable.module';
import { Openi40DatasetHomepageComponent } from './openi40-dataset-homepage.component';
import { Openi40TaskDetailsModule } from '../openi40-task-details/openi40-task-details.module'
import { Openi40SchedulingSettingsModule } from '../openi40-scheduling-settings/openi40-scheduling-settings.module'
import { Openi40ResourcesUseGraphModule } from '../openi40-resources-use-graph/openi40-resources-use-graph.module'
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { Openi40SchedulingWarningsModule } from '../openi40-scheduling-warnings/openi40-scheduling-warnings.module';
import { Openi40DependencyGraphModule } from '../openi40-dependency-graph/openi40-dependency-graph.module'
import { Openi40SchedulingsSettingsWizardModule } from '../openi40-scheduling-settings-wizard/openi40-scheduling-settings-wizard.module'
import {Openi40MaterialPlanningModule} from '../openi40-material-planning/openi40-material-planning.module'
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
export const lazyLoadingRoutes: Routes =
  [
    {
      path: '',
      component: Openi40DatasetHomepageComponent,
      data: {
        navbarLabel: 'Homepage dataset'
      }
    }
  ]
@NgModule({
  imports: [CommonModule, FormsModule, ReactiveFormsModule, SelectButtonModule, SchedulerResourcesGanttModule, SchedulerWorkOrdersGanttModule, TabViewModule, PanelModule, BlockUIModule, ButtonModule, DialogModule, Openi40TasksDatatableModule, Openi40TaskDetailsModule, Openi40SchedulingSettingsModule, Openi40ResourcesUseGraphModule, Openi40SchedulingWarningsModule, Openi40DependencyGraphModule, Openi40SchedulingsSettingsWizardModule,Openi40MaterialPlanningModule,FontAwesomeModule, RouterModule.forChild(lazyLoadingRoutes)],
  declarations: [Openi40DatasetHomepageComponent],
  exports: [Openi40DatasetHomepageComponent],
  providers: []
})


export class Openi40DatasetHomepageModule {

}
