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
import { HttpClientModule } from '@angular/common/http';
import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { IconModule } from '@ant-design/icons-angular';
import { ApiModule as Openi40ClientRestModule, BASE_PATH } from 'projects/openi40-scheduler-api/src/lib/';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { WebsocketService } from '../../projects/openi40-scheduler-screens/src/lib/openi40-dataset-homepage/websocket-service';
import { FaIconLibrary, FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { fas } from '@fortawesome/free-solid-svg-icons';
import { far } from '@fortawesome/free-regular-svg-icons';


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
  CommonModule,
  AppRoutingModule,
  Openi40ClientRestModule,
  HttpClientModule,
  BrowserAnimationsModule,
  IconModule,
  FontAwesomeModule
  ],
  providers: [WebsocketService,{ provide: BASE_PATH, useFactory: getBaseUrl }],
  bootstrap: [AppComponent]
})
export class AppModule {constructor(library: FaIconLibrary) {
  library.addIconPacks(fas, far);
} }
