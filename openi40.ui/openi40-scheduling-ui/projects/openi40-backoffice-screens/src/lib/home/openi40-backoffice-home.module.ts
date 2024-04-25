import { NgModule } from "@angular/core";
import {CommonModule} from "@angular/common";
import { RouterModule, Routes } from '@angular/router';
import { OpenI40BackofficeHomeComponent } from "./openi40-backoffice-home.component";
import { Openi40BackofficeMetaUIModule } from "@openi40/backoffice-ui";
export const routes: Routes = []=[{
  path:"openi40-backoffice-home",
  component: OpenI40BackofficeHomeComponent
}];
@NgModule({
  imports:[CommonModule,Openi40BackofficeMetaUIModule, RouterModule.forRoot(routes)],
  declarations:[OpenI40BackofficeHomeComponent]
})
export class OpenI40BackofficeHomeModule {}
