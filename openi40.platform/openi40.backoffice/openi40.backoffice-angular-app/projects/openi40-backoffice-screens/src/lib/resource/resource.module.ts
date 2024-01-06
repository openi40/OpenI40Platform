import { CommonModule } from "@angular/common";
import { Injectable, NgModule } from "@angular/core";
import { AbstractUICreateNewService, AbstractUIDeleteService, AbstractUIFindByCodeService, AbstractUISaveService, OpenI40BackofficeMetaUISectionRoutingModule, OperationResult, OperationStatus } from "@openi40/backoffice-ui";
import { Observable, map, of } from "rxjs";
import { AbstractBasePagedSearch } from "../reusable/abstract-paged-search-service";
import { OI40DBSecondaryResource, Oi40DbSecondaryResourceRepositoryService, Pageable, Sort } from "@openi40/backoffice-api";
@Injectable()
class SecondaryResourcePagedSearchService extends AbstractBasePagedSearch<OI40DBSecondaryResource> {

    constructor(private service: Oi40DbSecondaryResourceRepositoryService) {
        super()
    }
    protected override invokeRemote(searchArgument: { qbe: any; page: { page: number; pageSize: number; }; }): Observable<{ content?: OI40DBSecondaryResource[] | undefined; empty?: boolean | undefined; first?: boolean | undefined; last?: boolean | undefined; number?: number | undefined; numberOfElements?: number | undefined; pageable?: Pageable | undefined; size?: number | undefined; sort?: Sort | undefined; totalElements?: number | undefined; totalPages?: number | undefined; }> {
        return this.service.findByQbePagedPageOI40DBSecondaryResource(searchArgument);
    }

}
@Injectable()
class SecondaryResourceFindByCodeService extends AbstractUIFindByCodeService<any> {
    constructor(private service: Oi40DbSecondaryResourceRepositoryService) {
        super()
    }
    public override findByCode(code: string): Observable<OperationResult<any>> {
        return this.service.findByCodeOI40DBSecondaryResource(code).pipe(map(r => {
            return {
                status: OperationStatus.SUCCESS,
                data: r,
                msgs: []
            };

        }));
    }

}
@Injectable()
class SecondaryResourceCreateNewService extends AbstractUICreateNewService<any> {

    constructor(private service: Oi40DbSecondaryResourceRepositoryService) {
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
class SecondaryResourceSaveService extends AbstractUISaveService<any> {

    constructor(private service: Oi40DbSecondaryResourceRepositoryService) {
        super()
    }
    public override save(data: any): Observable<OperationResult<any>> {
        return this.service.updateSingleOI40DBSecondaryResource(data).pipe(map(r => {
            return {
                status: OperationStatus.SUCCESS,
                data: r,
                msgs: []
            };

        }));
    }
}
@Injectable()
class SecondaryResourceDeleteService extends AbstractUIDeleteService<any> {

    constructor(private service: Oi40DbSecondaryResourceRepositoryService) {
        super()
    }
    public override delete(data: any): Observable<OperationResult<any>> {
        return this.service.deleteByCodeVoid25(data.code).pipe(map(r => {
            return {
                status: OperationStatus.SUCCESS,
                data: r,
                msgs: []
            };
        }));
    }
}
const configuredModule = OpenI40BackofficeMetaUISectionRoutingModule.getConfiguredBackendSection("resources", "resources", [], [], SecondaryResourcePagedSearchService, "edit resource", [], SecondaryResourceFindByCodeService, SecondaryResourceCreateNewService, SecondaryResourceSaveService, SecondaryResourceDeleteService);
@NgModule({
    imports: [CommonModule, configuredModule]
})
export class ResourceModule { }