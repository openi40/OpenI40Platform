import { Injectable, NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { MegaMenuModule } from 'primeng/megamenu';
import { AppRoutingModule } from './app-routing.module';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { BASE_PATH } from 'projects/openi40-backoffice-api/src/lib';
//import { UIConfigurationRepositoryService, UI_CONFIG } from 'projects/openi40-backoffice-ui/src/lib/services/ui-configurations-repository.service';

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
@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    CommonModule,
    RouterModule,
    MegaMenuModule,
    AppRoutingModule
  ],
  providers:[{ provide: BASE_PATH, useFactory: getBaseUrl }],
  
  bootstrap: [AppComponent]
})
export class AppModule { }
