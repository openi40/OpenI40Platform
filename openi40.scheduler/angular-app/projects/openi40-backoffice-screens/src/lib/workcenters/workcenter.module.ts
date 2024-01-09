import { CommonModule } from "@angular/common";
import { Injectable, NgModule } from "@angular/core";
import { AbstractUICreateNewService, AbstractUIDeleteService, AbstractUIFindByCodeService, AbstractUISaveService, OpenI40BackofficeMetaUISectionRoutingModule, OperationResult, OperationStatus } from "@openi40/backoffice-ui";
import { Observable, map, of } from "rxjs";
import { AbstractBasePagedSearch } from "../reusable/abstract-paged-search-service";
import { OI40DBWorkCenter, Oi40DbWorkCenterRepositoryService, Pageable, Sort } from "@openi40/backoffice-api";
@Injectable()
class WorkCenterPagedSearchService extends AbstractBasePagedSearch<OI40DBWorkCenter> {

    constructor(private service: Oi40DbWorkCenterRepositoryService) {
        super()
    }
    protected override invokeRemote(searchArgument: { qbe: any; page: { page: number; pageSize: number; }; }): Observable<{ content?: OI40DBWorkCenter[] | undefined; empty?: boolean | undefined; first?: boolean | undefined; last?: boolean | undefined; number?: number | undefined; numberOfElements?: number | undefined; pageable?: Pageable | undefined; size?: number | undefined; sort?: Sort | undefined; totalElements?: number | undefined; totalPages?: number | undefined; }> {
        return this.service.findByQbePagedPageOI40DBWorkCenter(searchArgument);
    }

}
@Injectable()
class WorkCenterFindByCodeService extends AbstractUIFindByCodeService<any> {
    constructor(private service: Oi40DbWorkCenterRepositoryService) {
        super()
    }
    public override findByCode(code: string): Observable<OperationResult<any>> {
        return this.service.findByCodeOI40DBWorkCenter(code).pipe(map(r => {
            return {
                status: OperationStatus.SUCCESS,
                data: r,
                msgs: []
            };

        }));
    }

}
@Injectable()
class WorkCenterCreateNewService extends AbstractUICreateNewService<any> {

    constructor(private service: Oi40DbWorkCenterRepositoryService) {
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
class WorkCenterSaveService extends AbstractUISaveService<any> {

    constructor(private service: Oi40DbWorkCenterRepositoryService) {
        super()
    }
    public override save(data: any): Observable<OperationResult<any>> {
        return this.service.updateSingleOI40DBWorkCenter(data).pipe(map(r => {
            return {
                status: OperationStatus.SUCCESS,
                data: r,
                msgs: []
            };

        }));
    }
}
@Injectable()
class WorkCenterDeleteService extends AbstractUIDeleteService<any> {

    constructor(private service: Oi40DbWorkCenterRepositoryService) {
        super()
    }
    public override delete(data: any): Observable<OperationResult<any>> {
        return this.service.deleteByCodeVoid39(data.code).pipe(map(r => {
            return {
                status: OperationStatus.SUCCESS,
                data: r,
                msgs: []
            };
        }));
    }
}
const configuredModule = OpenI40BackofficeMetaUISectionRoutingModule.getConfiguredBackendSection("workcenters", "workcenters", [], [], WorkCenterPagedSearchService, "edit workcenter", [], WorkCenterFindByCodeService, WorkCenterCreateNewService, WorkCenterSaveService, WorkCenterDeleteService);
@NgModule({
    imports: [CommonModule, configuredModule]
})
export class WorkCenterModule { }