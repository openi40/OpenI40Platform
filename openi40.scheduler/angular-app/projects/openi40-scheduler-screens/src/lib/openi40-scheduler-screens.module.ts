import { CommonModule } from "@angular/common";
import { NgModule } from "@angular/core";
import { RouterModule, Routes } from "@angular/router";
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
  imports:[CommonModule,RouterModule.forRoot(routes)]
})
export class OpenI40SchedulerScreensModule {}
