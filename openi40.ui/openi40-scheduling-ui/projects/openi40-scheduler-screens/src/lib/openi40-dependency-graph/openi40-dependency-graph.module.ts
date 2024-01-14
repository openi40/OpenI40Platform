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
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import {OrganizationChartModule} from 'primeng/organizationchart';
import { TooltipModule } from 'primeng/tooltip';
import { Openi40DependencyGraphComponent } from './openi40-dependency-graph.component';

@NgModule({
  imports:[CommonModule,FormsModule,ReactiveFormsModule,OrganizationChartModule,TooltipModule],
  declarations:[Openi40DependencyGraphComponent],
  exports:[Openi40DependencyGraphComponent]
})
export class Openi40DependencyGraphModule {}
