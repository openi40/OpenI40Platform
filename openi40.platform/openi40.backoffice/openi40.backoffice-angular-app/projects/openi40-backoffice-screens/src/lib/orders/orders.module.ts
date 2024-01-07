import { CommonModule } from "@angular/common";
import { NgModule } from "@angular/core";
import { FindPurchaseOrderService, FindSalesOrderService, PurchaseOrdersSearch, SalesOrdersSearch, SavePurchaseOrderService, SaveSalesOrderService } from "./orders-search.service";
import { DefaultGoToDetailService, OpenI40BackofficeMetaUISectionRoutingModule, Openi40BackofficeMetaUIModule, UIDetailForm, UIFormGroup, UIResultColumn, UISearchForm } from "@openi40/backoffice-ui";
import { ApiModule } from "@openi40/backoffice-api";
import { RouterModule, Routes } from "@angular/router";
import { OI40DepartmentAutocompleteSearchService, OI40PartnerAutocompleteSearchService, OI40PlantAutocompleteSearchService, OI40ProductAutocompleteSearchService, OI40WarehouseAutocompleteSearchService, Openi40BackofficeServicesModule } from "@openi40/backoffice-services";
import { GenericOrder } from "./generic-order";
import { BlockUIModule } from "primeng/blockui";
import { ButtonModule } from "primeng/button";
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
    color?: string;
    completedQty?: number;
    customPriority?: number;
    departmentCode?: string;
    description?: string;
    integrationTs?: Date;
    lineStatus?: string;
    maxProductionDateConstraint?: Date;
    minProductionDateConstraint?: Date;
    modifiedTimestamp?: Date;
    orderCode?: string;
    orderType?: string;
    plannedDeliveryDate?: Date;
    plantCode?: string;
    productCode?: string;
    removed?: boolean;
    residualQty?: number;
    totalQty?: number;
    warehouseCode?: string;*/
const GENERIC_ORDER_ROW_FORMGROUP_CONFIG: UIFormGroup = {
    name: "row",
    controls: [
        {
            controlName: "code",
            label: "code",
            type: "text"
        }, {
            controlName: "description",
            label: "description",
            type: "text"
        },
        OI40ProductAutocompleteSearchService.getControlConfig(),
        {
            controlName: "totalQty",
            label: "total qty",
            type: "number"
        },
        OI40PlantAutocompleteSearchService.getControlConfig(),
        OI40DepartmentAutocompleteSearchService.getControlConfig(), 
        OI40WarehouseAutocompleteSearchService.getControlConfig(),
        {
            controlName: "askedDeliveryDate",
            label: "asked delivery date",
            type: "date"
        }, {
            controlName: "plannedDeliveryDate",
            label: "planned delivery date",
            type: "date"
        }, {
            controlName: "orderCode",
            label: "orderCode",
            type: "hidden"
        }
    ]
}
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
const GENERIC_ORDER_FORMGROUP_CONFIG: UIFormGroup = {
    name: "modelForm",
    controls: [{
        controlName: "code",
        label: "code",
        type: "text",
        containerCssClasses: "col-4"
    }, {
        controlName: "description",
        label: "description",
        type: "text",
        containerCssClasses: "col-8"
    }, {
        controlName: "orderStatus",
        label: "Order status",
        type: "dropdown",
        values: [{ code: "SUSPENDED", description: "Suspended" }, { code: "ACTIVE", description: "Active" }, { code: "DELIVERED", description: "Delivered" }, { code: "DISABLED", description: "Disabled" }],
        mappings: {
            label: "description",
            identifier: "code"
        },
        containerCssClasses: "col-4"
    }, OI40PlantAutocompleteSearchService.getControlConfig(),
    OI40DepartmentAutocompleteSearchService.getControlConfig(),
    OI40PartnerAutocompleteSearchService.getControlConfig(),

    {
        controlName: "askedDeliveryDate",
        label: "asked delivery date",
        type: "date",
        containerCssClasses: "col-4"
    }, {
        controlName: "plannedDeliveryDate",
        label: "planned delivery date",
        type: "date",
        containerCssClasses: "col-4"
    }],
    formArrays: [{ ...GENERIC_ORDER_ROW_FORMGROUP_CONFIG, length: 1, name: "rows" }]
};

const SALES_ORDER_FORM_CONFIG: UIDetailForm<GenericOrder> = {
    title: "Sales order editing",
    formGroup: GENERIC_ORDER_FORMGROUP_CONFIG,
    findByCodeService: FindSalesOrderService,
    uniqueUiKey: "sales-order-editing",
    saveService: SaveSalesOrderService
};
const PURCHASE_ORDER_FORM_CONFIG: UIDetailForm<GenericOrder> = {
    title: "Purchase order editing",
    formGroup: GENERIC_ORDER_FORMGROUP_CONFIG,
    findByCodeService: FindPurchaseOrderService,
    uniqueUiKey: "sales-order-editing",
    saveService: SavePurchaseOrderService
}

const salesOrdersRoutes: Routes = OpenI40BackofficeMetaUISectionRoutingModule.getConfiguredRoutes("sales-orders", SALES_ORDER_SEARCH_CONFIGURATION, SALES_ORDER_FORM_CONFIG);
const purchaseOrdersRoutes: Routes = OpenI40BackofficeMetaUISectionRoutingModule.getConfiguredRoutes("purchase-orders", PURCHASE_ORDER_SEARCH_CONFIGURATION, PURCHASE_ORDER_FORM_CONFIG);
@NgModule({
    imports: [
        CommonModule,
        ApiModule,
        Openi40BackofficeServicesModule,
        Openi40BackofficeMetaUIModule,
        BlockUIModule,
        ButtonModule,
        RouterModule.forRoot(salesOrdersRoutes), RouterModule.forRoot(purchaseOrdersRoutes)],
    providers: [SalesOrdersSearch, PurchaseOrdersSearch, FindSalesOrderService, SaveSalesOrderService, FindPurchaseOrderService, SavePurchaseOrderService],
    declarations: [],
    exports: []
})
export class OrdersModule { }