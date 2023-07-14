import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { MegaMenuModule } from 'primeng/megamenu';
import { Openi40BackofficeMetaUIModule,UIConfigurationRepositoryService, UI_CONFIG } from 'projects/openi40-backoffice-ui/src/public-api';
//import { UIConfigurationRepositoryService, UI_CONFIG } from 'projects/openi40-backoffice-ui/src/lib/services/ui-configurations-repository.service';
@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule,
    CommonModule,
    RouterModule,
    MegaMenuModule,
    Openi40BackofficeMetaUIModule
  ],
  providers: [UIConfigurationRepositoryService,{provide:UI_CONFIG,
              useValue:{uniqueUiKey:"products-search",
              title:"Products search",
              formGroup:{},
              accessRights:[]
            },multi:true}],
  bootstrap: [AppComponent]
})
export class AppModule { }
