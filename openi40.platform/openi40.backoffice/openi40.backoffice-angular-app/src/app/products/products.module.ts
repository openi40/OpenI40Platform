import { CommonModule } from "@angular/common";
import { Injectable, NgModule } from "@angular/core";
import { RouterModule, Routes } from "@angular/router";
import { AbstractUIFindByCodeService, AbstractUISaveService, DefaultGoToDetailService, OpenI40BackofficeMetaUIRoutingModule, OperationResult, OperationStatus, UIDetailForm, UISearchForm, UI_DETAIL_CONFIG, UI_SEARCH_CONFIG } from "projects/openi40-backoffice-ui/src/public-api";
import { Observable, of } from "rxjs";
import { ProductsSearch } from "./products-search.service";
import { ApiModule } from "@openi40/backoffice-api"; 
const routes: Routes = [
    {
      path:'products-search',
      loadChildren:()=>import("projects/openi40-backoffice-ui/src/lib/openi40-backoffice-meta-ui-routing.module").then(m=>m.OpenI40BackofficeMetaUIRoutingModule)    
    }
  ];
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
@Injectable({providedIn:"root"})
class ProductFindByCodeService extends AbstractUIFindByCodeService<any>{
  public override findByCode(_code: string): Observable<OperationResult<any>> {
    const returnObject={code:_code,description:"Product detail",typeOfProduct:{ id: 1, code: "PRODUCT", description: "Product" }};
    const ors:OperationResult<any>={
      status:OperationStatus.SUCCESS,
      data:returnObject
    };
    return of(ors);
  }

}
@Injectable({providedIn:"root"})
class ProductSaveService extends AbstractUISaveService<any> {
  public override save(data: any): Observable<OperationResult<any>>{
    const ors:OperationResult<any>={
      status:OperationStatus.SUCCESS,
      data:data
    };
    return of(ors);
  }
  
  
}
const PRODUCT_DETAIL_CONFIGURATION:UIDetailForm<any>={
  title: "Product",
  formGroup: {
    name: "edit",
    controls: [{
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
    }]
  },
  findByCodeService: ProductFindByCodeService,
  saveService: ProductSaveService,
  uniqueUiKey: "PRODUCT_DETAIL"
};
@NgModule({
    imports:[CommonModule,OpenI40BackofficeMetaUIRoutingModule,RouterModule.forRoot(routes),ApiModule],
    providers: [{
        provide: UI_SEARCH_CONFIG,
        useValue: PRODUCTS_SEARCH_CONFIGURATION, multi: false
      },{provide: UI_DETAIL_CONFIG, 
         useValue:PRODUCT_DETAIL_CONFIGURATION,
         multi:false}, ProductsSearch,ProductFindByCodeService,ProductSaveService]
})
export class ProductsModule {

}