import { NgModule } from "@angular/core";
import { CommonModule } from "@angular/common";
import { Openi40CommonUiComponent } from "./openi40-common-ui.component";
import { Openi40CommonUiService } from "./openi40-common-ui.service";
import { RouterModule, Routes } from "@angular/router";
import { HttpClientModule } from "@angular/common/http";
import { BlockUIModule } from "primeng/blockui";
const routes:Routes=[{
  pathMatch:"full",
  path:"",
  redirectTo:"openi40-common-ui"
},{
  path:"openi40-common-ui",
  component: Openi40CommonUiComponent
}];
@NgModule({
  imports:[CommonModule,HttpClientModule,BlockUIModule, RouterModule.forRoot(routes)],
  declarations:[Openi40CommonUiComponent],
  exports:[Openi40CommonUiComponent],
  providers:[Openi40CommonUiService]
})
export class Openi40CommonUiModule {}
