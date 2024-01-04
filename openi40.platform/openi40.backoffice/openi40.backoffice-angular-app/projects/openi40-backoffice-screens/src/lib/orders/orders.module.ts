import { CommonModule } from "@angular/common";
import { Injectable, NgModule } from "@angular/core";
import { FindPurchaseOrderService, FindSalesOrderService, PurchaseOrdersSearch, SalesOrdersSearch, SavePurchaseOrderService, SaveSalesOrderService } from "./orders-search.service";
import { AbstractUIFindByCodeService, AbstractUISaveService, DefaultGoToDetailService, Openi40BackofficeMetaUIModule, OperationResult, OperationStatus, UIDetailForm, UIFormGroup, UIResultColumn, UISearchForm, UI_DETAIL_CONFIG, UI_SEARCH_CONFIG } from "@openi40/backoffice-ui";
import { ApiModule, OI40DBSalesOrder, OI40DBSalesOrderLine, Oi40DbSalesOrderLineRepositoryService, Oi40DbSalesOrderRepositoryService } from "@openi40/backoffice-api";
import { RouterModule, Routes } from "@angular/router";
import { DetailUIFormComponent } from "projects/openi40-backoffice-ui/src/lib/ui-configurable/detail-ui-form/detail-ui-form.component";
import { SearchUIFormComponent } from "@openi40/backoffice-ui";
import { OI40DepartmentAutocompleteSearchService, OI40PlantAutocompleteSearchService, Openi40BackofficeServicesModule } from "@openi40/backoffice-services";
import { OrderComponent } from "./editing/order.component";
import { GenericOrder } from "./editing/generic-order";
import { Observable, forkJoin, map, of } from "rxjs";
import { BlockUIModule } from "primeng/blockui";
const DOCUMENT_SEARCH_COMMON_FORMGROUP: UIFormGroup = {
    name: "documentSearch",
    controls: [{
        controlName: "code",
        label: "code",
        type: "text",
        required: false,
        containerCssClasses: "col-2"
    },
    {
        controlName: "description",
        label: "description",
        type: "text",
        required: false,
        containerCssClasses: "col-4"
    }, {
        controlName: "orderStatus",
        label: "Order status",
        type: "dropdown",
        values: [{ code: "SUSPENDED", description: "Suspended" }, { code: "ACTIVE", description: "Active" }, { code: "DELIVERED", description: "Delivered" }, { code: "DISABLED", description: "Disabled" }],
        mappings: {
            label: "description",
            identifier: "code"
        }
    }, OI40PlantAutocompleteSearchService.getControlConfig(), 
       OI40DepartmentAutocompleteSearchService.getControlConfig()
      /*askedDeliveryDate?: Date;
      attributesMap?: any;
      code?: string;
      customPriority?: number;
      departmentCode?: string;
      description?: string;
      integrationTs?: Date;
      modifiedTimestamp?: Date;
      orderStatus?: string;
      orderType?: string;
      partner?: string;
      plannedDeliveryDate?: Date;
      plantCode?: string;
    removed?: boolean;*/]
};
const FIELDS_LIST: UIResultColumn[] = [{ field: "code", header: "code" },
{ field: "description", header: "description" },
{ field: "departmentCode", header: "department code" },
{ field: "partner", header: "partner code" },
{ field: "plantCode", header: "plant code" },
{ field: "askedDeliveryDate", header: "asked delivery date" },
{ field: "plannedDeliveryDate", header: "planned delivery date" },
{ field: "orderStatus", header: "order status code" },
{ field: "orderType", header: "order type code" }];
const SALES_ORDER_SEARCH_CONFIGURATION: UISearchForm<any, any> = {
    uniqueUiKey: "sales-order-search",
    title: "Sales order search",
    formGroup: DOCUMENT_SEARCH_COMMON_FORMGROUP,
    accessRights: [],
    searchService: SalesOrdersSearch,
    resultColumns: FIELDS_LIST,
    gotoDetailService: DefaultGoToDetailService
};
const PURCHASE_ORDER_SEARCH_CONFIGURATION: UISearchForm<any, any> = {
    uniqueUiKey: "purchase-order-search",
    title: "Purchase order search",
    formGroup: DOCUMENT_SEARCH_COMMON_FORMGROUP,
    accessRights: [],
    searchService: PurchaseOrdersSearch,
    resultColumns: FIELDS_LIST,
    gotoDetailService: DefaultGoToDetailService
};
/* askedDeliveryDate?: Date;
    attributesMap?: any;
    code?: string;
    customPriority?: number;
    departmentCode?: string;
    description?: string;
    integrationTs?: Date;
    modifiedTimestamp?: Date;
    orderStatus?: string;
    orderType?: string;
    partner?: string;
    plannedDeliveryDate?: Date;
    plantCode?: string;
    removed?: boolean;*/
const GENERIC_ORDER_FORMGROUP_CONFIG:UIFormGroup={
    name:"modelForm",
    controls:[{
        controlName:"code",
        label:"code",
        type:"text",
        containerCssClasses:"col-4"
    },{
        controlName:"description",
        label:"description",
        type:"text",
        containerCssClasses:"col-8"
    }, {
        controlName: "orderStatus",
        label: "Order status",
        type: "dropdown",
        values: [{ code: "SUSPENDED", description: "Suspended" }, { code: "ACTIVE", description: "Active" }, { code: "DELIVERED", description: "Delivered" }, { code: "DISABLED", description: "Disabled" }],
        mappings: {
            label: "description",
            identifier: "code"
        }
    }, OI40PlantAutocompleteSearchService.getControlConfig(), 
       OI40DepartmentAutocompleteSearchService.getControlConfig(),{
        controlName:"askedDeliveryDate",
        label:"asked delivery date",
        type:"date"
       },{
        controlName:"plannedDeliveryDate",
        label:"planned delivery date",
        type:"date"
       }]
};

const SALES_ORDER_FORM_CONFIG:UIDetailForm<GenericOrder>={
    title:"Sales order editing"    ,
    formGroup:GENERIC_ORDER_FORMGROUP_CONFIG,
    findByCodeService:FindSalesOrderService,
    uniqueUiKey:"sales-order-editing",
    saveService:SaveSalesOrderService
};
const PURCHASE_ORDER_FORM_CONFIG:UIDetailForm<GenericOrder>={
    title:"Purchase order editing",
    formGroup:GENERIC_ORDER_FORMGROUP_CONFIG,
    findByCodeService:FindPurchaseOrderService,
    uniqueUiKey:"sales-order-editing",
    saveService:SavePurchaseOrderService
}
const routes: Routes = [
    {
        pathMatch: "full",
        path: 'sales-orders',
        component: SearchUIFormComponent,
        providers: [{
            provide: UI_SEARCH_CONFIG,
            useValue: SALES_ORDER_SEARCH_CONFIGURATION, multi: false
        }]
    },
    {
        path: 'sales-orders/:code/edit',
        component: OrderComponent,
        providers: [{
            provide: UI_DETAIL_CONFIG,
            useValue: SALES_ORDER_FORM_CONFIG, multi: false
        }]
    }, {
        path: 'sales-orders/new',
        component: OrderComponent,
        providers: [{
            provide: UI_DETAIL_CONFIG,
            useValue: SALES_ORDER_FORM_CONFIG, multi: false
        }]
    },
    {
        pathMatch: "full",
        path: 'purchase-orders',
        component: SearchUIFormComponent,
        providers: [{
            provide: UI_SEARCH_CONFIG,
            useValue: PURCHASE_ORDER_SEARCH_CONFIGURATION, multi: false
        }]
    },
    {
        path: 'purchase-orders/:code/edit',
        component: OrderComponent,
        providers: [{
            provide: UI_DETAIL_CONFIG,
            useValue: PURCHASE_ORDER_FORM_CONFIG, multi: false
        }]
    }, {
        path: 'purchase-orders/new',
        component: OrderComponent,
        providers: [{
            provide: UI_DETAIL_CONFIG,
            useValue: PURCHASE_ORDER_FORM_CONFIG, multi: false
        }]
    }
];
@NgModule({ 
    imports: [
        CommonModule,
        ApiModule, 
        Openi40BackofficeServicesModule,
        Openi40BackofficeMetaUIModule,
        BlockUIModule,
        RouterModule.forRoot(routes)], 
    providers: [SalesOrdersSearch, PurchaseOrdersSearch,FindSalesOrderService,SaveSalesOrderService,FindPurchaseOrderService,SavePurchaseOrderService],
    declarations:[OrderComponent],
    exports:[OrderComponent] })
export class OrdersModule { }