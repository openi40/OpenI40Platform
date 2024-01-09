import { CommonModule } from "@angular/common";
import { NgModule } from "@angular/core";
import { RouterModule, Routes } from "@angular/router";
import { BASE_PATH } from "@openi40/scheduler-api";
export function getBaseUrl() {
  let host=document.location.hostname;
  let port=document.location.port;
  let protocol=document.location.protocol;
  if (port==="4200") {
    port="8080";
  }
  let openi40Base=protocol+"//"+host+":"+port+"/openi40";
  console.log("Setting basePath: "+openi40Base);
  return openi40Base;
}
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
  imports:[CommonModule,RouterModule.forRoot(routes)],
  exports:[],
  providers:[{ provide: BASE_PATH, useFactory: getBaseUrl }]
})
export class OpenI40SchedulerScreensModule {}
