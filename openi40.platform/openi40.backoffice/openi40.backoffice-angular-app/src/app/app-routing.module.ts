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
import { Routes, RouterModule } from '@angular/router';
import { SearchUIFormComponent } from 'projects/openi40-backoffice-ui/src/lib/ui-configurable/search-ui-form/search-ui-form.component';
import { Openi40BackofficeMetaUIModule } from 'projects/openi40-backoffice-ui/src/public-api';

const routes: Routes = [
  {
    path:'products-search',
    component: SearchUIFormComponent,
    data:{
      guiConfigurationKey:"products-search"
    }
  }
];

@NgModule({
  imports: [CommonModule,Openi40BackofficeMetaUIModule, RouterModule.forRoot(routes,{ enableTracing: true })],
  exports: [RouterModule]
})
export class AppRoutingModule { }
