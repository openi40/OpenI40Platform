import { Injectable, NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { MegaMenuModule } from 'primeng/megamenu';
import { AbstractUIPagedSearchService, DefaultGoToDetailService, Openi40BackofficeMetaUIModule, Page, PageMeta, UIConfigurationRepositoryService, UISearchForm, UI_CONFIG } from 'projects/openi40-backoffice-ui/src/public-api';
import { AppRoutingModule } from './app-routing.module';
import { Observable, of } from 'rxjs';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
//import { UIConfigurationRepositoryService, UI_CONFIG } from 'projects/openi40-backoffice-ui/src/lib/services/ui-configurations-repository.service';
@Injectable()
export class ProductsSearch extends AbstractUIPagedSearchService {
  public override searchPaged(search: any, page: PageMeta): Observable<Page<any>> {
    const npage: Page<any> = new Page<any>();
    npage.data = [{ id: 1, code: "00001", description: "Trigger 001" }, { id: 2, code: "00002", description: "Trigger 002" }]
    return of(npage);
  }

}
export function defaultGotoDetailCallback(actualValue: any, configuration: UISearchForm<any, any>, runtimeComponent: any){

}
const PRODUCTS_SEARCH_CONFIGURATION: UISearchForm<any, any> = {
  uniqueUiKey: "products-search",
  title: "Products search",
  formGroup: {
    name: "searchForm", controls: [
      {
        controlName: "code",
        label: "code",
        type: "text",
        required: true,
        containerCssClasses: "col-3"
      },
      {
        controlName: "description",
        label: "description",
        type: "text",
        required: false,
        containerCssClasses: "col-3"
      },
      {
        controlName: "typeOfProduct",
        label: "type of product",
        type: "dropdown",
        required: false,
        values: [{ id: 1, code: "PRODUCT", description: "Product" }, { id: 2, code: "RAWMATERIAL", description: "Raw material" }],
        mappings: { label: "description", identifier: "id" },
        containerCssClasses: "col-3"
      }
    ]
  },
  accessRights: [],
  searchService: ProductsSearch,
  resultColumns: [{ field: "id", header: "id" }, { field: "code", header: "code" }, { field: "description", header: "description" }],
  gotoDetailService:DefaultGoToDetailService
};
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
    AppRoutingModule,
    Openi40BackofficeMetaUIModule
  ],
  providers: [UIConfigurationRepositoryService, {
    provide: UI_CONFIG,
    useValue: PRODUCTS_SEARCH_CONFIGURATION, multi: true
  }, ProductsSearch],
  bootstrap: [AppComponent]
})
export class AppModule { }
