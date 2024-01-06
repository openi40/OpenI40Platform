import { CommonModule } from "@angular/common";
import { Injectable, NgModule } from "@angular/core";
import { AbstractUICreateNewService, AbstractUIDeleteService, AbstractUIFindByCodeService, AbstractUISaveService, OpenI40BackofficeMetaUISectionRoutingModule, OperationResult, OperationStatus, UIControl } from "@openi40/backoffice-ui";
import { Observable, map, of } from "rxjs";
import { AbstractBasePagedSearch } from "../reusable/abstract-paged-search-service";
import { OI40DBProduct, Oi40DbProductRepositoryService, Pageable, Sort } from "@openi40/backoffice-api";
@Injectable()
class ProductPagedSearchService extends AbstractBasePagedSearch<OI40DBProduct> {

    constructor(private service: Oi40DbProductRepositoryService) {
        super()
    }
    protected override invokeRemote(searchArgument: { qbe: any; page: { page: number; pageSize: number; }; }): Observable<{ content?: OI40DBProduct[] | undefined; empty?: boolean | undefined; first?: boolean | undefined; last?: boolean | undefined; number?: number | undefined; numberOfElements?: number | undefined; pageable?: Pageable | undefined; size?: number | undefined; sort?: Sort | undefined; totalElements?: number | undefined; totalPages?: number | undefined; }> {
        return this.service.findByQbePagedPageOI40DBProduct(searchArgument);
    }

}
@Injectable()
class ProductFindByCodeService extends AbstractUIFindByCodeService<any> {
    constructor(private service: Oi40DbProductRepositoryService) {
        super()
    }
    public override findByCode(code: string): Observable<OperationResult<any>> {
        return this.service.findByCodeOI40DBProduct(code).pipe(map(r => {
            return {
                status: OperationStatus.SUCCESS,
                data: r,
                msgs: []
            };

        }));
    }

}
@Injectable()
class ProductCreateNewService extends AbstractUICreateNewService<any> {

    constructor(private service: Oi40DbProductRepositoryService) {
        super()
    }
    public override createNew(modelObject: any): Observable<OperationResult<any>> {
        return of({
            status: OperationStatus.SUCCESS,
            data: {},
            msgs: []
        });
    }
}
@Injectable()
class ProductSaveService extends AbstractUISaveService<any> {

    constructor(private service: Oi40DbProductRepositoryService) {
        super()
    }
    public override save(data: any): Observable<OperationResult<any>> {
        return this.service.updateSingleOI40DBProduct(data).pipe(map(r => {
            return {
                status: OperationStatus.SUCCESS,
                data: r,
                msgs: []
            };

        }));
    }
}
@Injectable()
class ProductDeleteService extends AbstractUIDeleteService<any> {

    constructor(private service: Oi40DbProductRepositoryService) {
        super()
    }
    public override delete(data: any): Observable<OperationResult<any>> {
        return this.service.deleteByCodeVoid16(data.code).pipe(map(r => {
            return {
                status: OperationStatus.SUCCESS,
                data: r,
                msgs: []
            };
        }));
    }
}
const UNITIES: string[] = ["PZ", "KG", "MT", "M2"];
const CUSTOM_DETAIL_EDITABLE_FIELDS:UIControl[]=[{
    controlName: "averageMinPurchaseQty",
    type: "number",
    label: "Min purchase lot",
    containerCssClasses: "col-3",
    readOnlyOnModify:true
  }, {
    controlName: "leadTimeDays", type: "number", label: "Lead time (days)",
    containerCssClasses: "col-3"
  },

  {
    controlName: "reorderQty", type: "number", label: "Manual reorder qty (purchase un.)",
    containerCssClasses: "col-3"
  },
  {
    controlName: "netWeight", type: "number", label: "Net weight (Kg)",
    containerCssClasses: "col-3"
  },
  {
    controlName: "movUnity", type: "dropdown", label: "Internal movimentation unity", values: UNITIES,
    required: true,
    containerCssClasses: "col-3"
  },
  {
    controlName: "purchUnity", type: "dropdown", label: "Purchase unity", values: UNITIES,
    required: true,
    containerCssClasses: "col-3"
  },
  {controlName:"canBeProducedByScheduler", type:"boolean",label:"Can be produced"},
  {controlName:"canBePurchasedByScheduler", type:"boolean",label:"Can be purchased"},
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
  }];
const configuredModule = OpenI40BackofficeMetaUISectionRoutingModule.getConfiguredBackendSection("products", "products", [], [], ProductPagedSearchService, "edit product", CUSTOM_DETAIL_EDITABLE_FIELDS, ProductFindByCodeService, ProductCreateNewService, ProductSaveService, ProductDeleteService);
@NgModule({
    imports: [CommonModule, configuredModule]
})
export class ProductModule { }