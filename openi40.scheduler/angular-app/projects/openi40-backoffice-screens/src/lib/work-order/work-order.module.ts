import { CommonModule } from "@angular/common";
import { Injectable, NgModule } from "@angular/core";
import { AbstractUICreateNewService, AbstractUIDeleteService, AbstractUIFindByCodeService, AbstractUISaveService, OpenI40BackofficeMetaUISectionRoutingModule, OperationResult, OperationStatus } from "@openi40/backoffice-ui";
import { Observable, map, of } from "rxjs";
import { AbstractBasePagedSearch } from "../reusable/abstract-paged-search-service";
import { OI40DBWorkOrder, Oi40DbWorkOrderRepositoryService, Pageable, Sort } from "@openi40/backoffice-api";
@Injectable()
class WorkOrderPagedSearchService extends AbstractBasePagedSearch<OI40DBWorkOrder> {

    constructor(private service: Oi40DbWorkOrderRepositoryService) {
        super()
    }
    protected override invokeRemote(searchArgument: { qbe: any; page: { page: number; pageSize: number; }; }): Observable<{ content?: OI40DBWorkOrder[] | undefined; empty?: boolean | undefined; first?: boolean | undefined; last?: boolean | undefined; number?: number | undefined; numberOfElements?: number | undefined; pageable?: Pageable | undefined; size?: number | undefined; sort?: Sort | undefined; totalElements?: number | undefined; totalPages?: number | undefined; }> {
        return this.service.findByQbePagedPageOI40DBWorkOrder(searchArgument);
    }

}
@Injectable()
class WorkOrderFindByCodeService extends AbstractUIFindByCodeService<any> {
    constructor(private service: Oi40DbWorkOrderRepositoryService) {
        super()
    }
    public override findByCode(code: string): Observable<OperationResult<any>> {
        return this.service.findByCodeOI40DBWorkOrder(code).pipe(map(r => {
            return {
                status: OperationStatus.SUCCESS,
                data: r,
                msgs: []
            };

        }));
    }

}
@Injectable()
class WorkOrderCreateNewService extends AbstractUICreateNewService<any> {

    constructor(private service: Oi40DbWorkOrderRepositoryService) {
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
class WorkOrderSaveService extends AbstractUISaveService<any> {

    constructor(private service: Oi40DbWorkOrderRepositoryService) {
        super()
    }
    public override save(data: any): Observable<OperationResult<any>> {
        return this.service.updateSingleOI40DBWorkOrder(data).pipe(map(r => {
            return {
                status: OperationStatus.SUCCESS,
                data: r,
                msgs: []
            };

        }));
    }
}
@Injectable()
class WorkOrderDeleteService extends AbstractUIDeleteService<any> {

    constructor(private service: Oi40DbWorkOrderRepositoryService) {
        super()
    }
    public override delete(data: any): Observable<OperationResult<any>> {
        return this.service.deleteByCodeVoid40(data.code).pipe(map(r => {
            return {
                status: OperationStatus.SUCCESS,
                data: r,
                msgs: []
            };
        }));
    }
}
const configuredModule = OpenI40BackofficeMetaUISectionRoutingModule.getConfiguredBackendSection("workorders", "work orders", [], [], WorkOrderPagedSearchService, "edit work order", [], WorkOrderFindByCodeService, WorkOrderCreateNewService, WorkOrderSaveService, WorkOrderDeleteService);
@NgModule({
    imports: [CommonModule, configuredModule]
})
export class WorkOrderModule { }