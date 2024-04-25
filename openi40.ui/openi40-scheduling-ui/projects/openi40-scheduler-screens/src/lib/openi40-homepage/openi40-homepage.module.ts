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
import {Openi40HomepageComponent} from './openi40-homepage.component'
import { BrowserModule } from '@angular/platform-browser';

import { CommonModule } from '@angular/common';
import { RouterModule, Routes } from '@angular/router';
export const lazyLoadingRoutes: Routes =
[
  {
    path: '',
    component: Openi40HomepageComponent,
    data: {
      navbarLabel: 'Homepage'
    }
  }
]
@NgModule({
    declarations:[Openi40HomepageComponent],
    exports:[Openi40HomepageComponent],
    imports:[RouterModule.forChild(lazyLoadingRoutes),CommonModule]
})
export class Openi40HomepageModule {

}
