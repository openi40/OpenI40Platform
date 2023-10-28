import { CommonModule } from "@angular/common";
import { NgModule } from "@angular/core";
import { RouterModule, Routes } from "@angular/router";
import { DefaultGoToDetailService, OpenI40BackofficeMetaUIRoutingModule, UIDetailForm, UISearchForm, UI_DETAIL_CONFIG, UI_SEARCH_CONFIG } from "projects/openi40-backoffice-ui/src/public-api";
import { ProductsSearch } from "./products-search.service";
import { ApiModule } from "@openi40/backoffice-api";
import { ProductFindByCodeService, ProductSaveService } from "./product-detail.service";
import { Openi40BackofficeServicesModule } from "projects/openi40-backoffice-services/src/public-api";
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
        containerCssClasses:"col-3"
      },
      {
        controlName: "description",
        label: "description",
        type: "text",
        required: false,
        containerCssClasses: "col-3"
      }/*,
      {
        controlName: "typeOfProduct",
        label: "type of product",
        type: "dropdown",
        required: false,
        values: [{ id: 1, code: "PRODUCT", description: "Product" }, { id: 2, code: "RAWMATERIAL", description: "Raw material" }],
        mappings: { label: "description", identifier: "id" },
        containerCssClasses: "col-3"
      }*/
    ]
  },
  accessRights: [],
  searchService: ProductsSearch,
  resultColumns: [{ field: "id", header: "id" }, { field: "code", header: "code" }, { field: "description", header: "description" }],
  gotoDetailService:DefaultGoToDetailService
};
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
      controlName: "attributesMap",
      label: "attributesMap",
      type: "hidden"
    },
    {
      controlName: "integrationTs",
      label: "integrationTs",
      type: "hidden"
    },
    {
      controlName: "modifiedTimestamp",
      label: "modifiedTimestamp",
      type: "hidden"
    },
    {
      controlName: "removed",
      label: "removed",
      type: "hidden"
    }]
  },
  findByCodeService: ProductFindByCodeService,
  saveService: ProductSaveService,
  uniqueUiKey: "PRODUCT_DETAIL"
};
@NgModule({
    imports:[CommonModule,OpenI40BackofficeMetaUIRoutingModule,RouterModule.forRoot(routes),ApiModule,Openi40BackofficeServicesModule],
    providers: [{
        provide: UI_SEARCH_CONFIG,
        useValue: PRODUCTS_SEARCH_CONFIGURATION, multi: false
      },{provide: UI_DETAIL_CONFIG, 
         useValue:PRODUCT_DETAIL_CONFIGURATION,
         multi:false}, ProductsSearch,ProductFindByCodeService,ProductSaveService]
})
export class ProductsModule {

}