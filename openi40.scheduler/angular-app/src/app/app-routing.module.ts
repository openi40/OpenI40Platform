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
import { Routes, RouterModule } from '@angular/router';

const routes: Routes = [
  { path: '',
    redirectTo: '/openi40-homepage',
    pathMatch: 'full'
  },
  {
    path:'openi40-homepage',
    loadChildren: () => import('./openi40-homepage/openi40-homepage.module').then(m => m.Openi40HomepageModule)
  },
  {

    path:'openi40-dataset-homepage',
    loadChildren: () => import('./openi40-dataset-homepage/openi40-dataset-homepage.module').then(m => m.Openi40DatasetHomepageModule)

  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes,
    { enableTracing: true })],
  exports: [RouterModule]
})
export class AppRoutingModule { }
