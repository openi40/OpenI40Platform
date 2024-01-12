import { CommonModule } from "@angular/common";
import { NgModule } from "@angular/core";
import { RouterModule, Routes } from "@angular/router";
import { BASE_PATH } from "@openi40/scheduler-api";
import { getBaseUrl } from "@openi40/common-ui";
const routes: Routes = [

  {
    path:'openi40-scheduler-home',
    loadChildren: () => import('./openi40-homepage/openi40-homepage.module').then(m => m.Openi40HomepageModule)
  },
  {

    path:'openi40-dataset-homepage',
    loadChildren: () => import('./openi40-dataset-homepage/openi40-dataset-homepage.module').then(m => m.Openi40DatasetHomepageModule)

  }
];
@NgModule({
  imports:[CommonModule,RouterModule.forRoot(routes)],
  exports:[],
  providers:[{ provide: BASE_PATH, useFactory: getBaseUrl }]
})
export class OpenI40SchedulerScreensModule {}
