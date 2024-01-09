import { CommonModule } from "@angular/common";
import { Injectable, NgModule } from "@angular/core";
import { AbstractUICreateNewService, AbstractUIDeleteService, AbstractUIFindByCodeService, AbstractUISaveService, OpenI40BackofficeMetaUISectionRoutingModule, OperationResult, OperationStatus } from "@openi40/backoffice-ui";
import { Observable, map, of } from "rxjs";
import { AbstractBasePagedSearch } from "../reusable/abstract-paged-search-service";
import { OI40DBDepartment, Oi40DbDepartmentRepositoryService, Pageable, Sort } from "@openi40/backoffice-api";
@Injectable()
class DepartmentPagedSearchService extends AbstractBasePagedSearch<OI40DBDepartment> {

    constructor(private service: Oi40DbDepartmentRepositoryService) {
        super()
    }
    protected override invokeRemote(searchArgument: { qbe: any; page: { page: number; pageSize: number; }; }): Observable<{ content?: OI40DBDepartment[] | undefined; empty?: boolean | undefined; first?: boolean | undefined; last?: boolean | undefined; number?: number | undefined; numberOfElements?: number | undefined; pageable?: Pageable | undefined; size?: number | undefined; sort?: Sort | undefined; totalElements?: number | undefined; totalPages?: number | undefined; }> {
        return this.service.findByQbePagedPageOI40DBDepartment(searchArgument);
    }

}
@Injectable()
class DepartmentFindByCodeService extends AbstractUIFindByCodeService<any> {
    constructor(private service: Oi40DbDepartmentRepositoryService) {
        super()
    }
    public override findByCode(code: string): Observable<OperationResult<any>> {
        return this.service.findByCodeOI40DBDepartment(code).pipe(map(r => {
            return {
                status: OperationStatus.SUCCESS,
                data: r,
                msgs: []
            };

        }));
    }

}
@Injectable()
class DepartmentCreateNewService extends AbstractUICreateNewService<any> {

    constructor(private service: Oi40DbDepartmentRepositoryService) {
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
class DepartmentSaveService extends AbstractUISaveService<any> {

    constructor(private service: Oi40DbDepartmentRepositoryService) {
        super()
    }
    public override save(data: any): Observable<OperationResult<any>> {
        return this.service.updateSingleOI40DBDepartment(data).pipe(map(r => {
            return {
                status: OperationStatus.SUCCESS,
                data: r,
                msgs: []
            };

        }));
    }
}
@Injectable()
class DepartmentDeleteService extends AbstractUIDeleteService<any> {

    constructor(private service: Oi40DbDepartmentRepositoryService) {
        super()
    }
    public override delete(data: any): Observable<OperationResult<any>> {
        return this.service.deleteByCodeVoid6(data.code).pipe(map(r => {
            return {
                status: OperationStatus.SUCCESS,
                data: r,
                msgs: []
            };
        }));
    }
}
const configuredModule = OpenI40BackofficeMetaUISectionRoutingModule.getConfiguredBackendSection("departments", "departments", [], [], DepartmentPagedSearchService, "edit department", [], DepartmentFindByCodeService, DepartmentCreateNewService, DepartmentSaveService, DepartmentDeleteService);
@NgModule({
    imports: [CommonModule, configuredModule]
})
export class DepartmentModule { }