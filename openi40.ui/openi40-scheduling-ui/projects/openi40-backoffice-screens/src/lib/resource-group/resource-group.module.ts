import { CommonModule } from "@angular/common";
import { Injectable, NgModule } from "@angular/core";
import { AbstractUICreateNewService, AbstractUIDeleteService, AbstractUIFindByCodeService, AbstractUISaveService, OpenI40BackofficeMetaUISectionRoutingModule, OperationResult, OperationStatus } from "@openi40/backoffice-ui";
import { Observable, map, of } from "rxjs";
import { AbstractBasePagedSearch } from "../reusable/abstract-paged-search-service";
import { OI40DBResourceGroup, Oi40DbResourceGroupRepositoryService, Pageable, Sort } from "@openi40/backoffice-api";
@Injectable()
class ResourceGroupPagedSearchService extends AbstractBasePagedSearch<OI40DBResourceGroup> {

    constructor(private service: Oi40DbResourceGroupRepositoryService) {
        super()
    }
    protected override invokeRemote(searchArgument: { qbe: any; page: { page: number; pageSize: number; }; }): Observable<{ content?: OI40DBResourceGroup[] | undefined; empty?: boolean | undefined; first?: boolean | undefined; last?: boolean | undefined; number?: number | undefined; numberOfElements?: number | undefined; pageable?: Pageable | undefined; size?: number | undefined; sort?: Sort | undefined; totalElements?: number | undefined; totalPages?: number | undefined; }> {
        return this.service.findByQbePagedPageOI40DBResourceGroup(searchArgument);
    }

}
@Injectable()
class ResourceGroupFindByCodeService extends AbstractUIFindByCodeService<any> {
    constructor(private service: Oi40DbResourceGroupRepositoryService) {
        super()
    }
    public override findByCode(code: string): Observable<OperationResult<any>> {
        return this.service.findByCodeOI40DBResourceGroup(code).pipe(map(r => {
            return {
                status: OperationStatus.SUCCESS,
                data: r,
                msgs: []
            };

        }));
    }

}
@Injectable()
class ResourceGroupCreateNewService extends AbstractUICreateNewService<any> {

    constructor(private service: Oi40DbResourceGroupRepositoryService) {
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
class ResourceGroupSaveService extends AbstractUISaveService<any> {

    constructor(private service: Oi40DbResourceGroupRepositoryService) {
        super()
    }
    public override save(data: any): Observable<OperationResult<any>> {
        return this.service.updateSingleOI40DBResourceGroup(data).pipe(map(r => {
            return {
                status: OperationStatus.SUCCESS,
                data: r,
                msgs: []
            };

        }));
    }
}
@Injectable()
class ResourceGroupDeleteService extends AbstractUIDeleteService<any> {

    constructor(private service: Oi40DbResourceGroupRepositoryService) {
        super()
    }
    public override delete(data: any): Observable<OperationResult<any>> {
        return this.service.deleteByCodeVoid21(data.code).pipe(map(r => {
            return {
                status: OperationStatus.SUCCESS,
                data: r,
                msgs: []
            };
        }));
    }
}
const configuredModule = OpenI40BackofficeMetaUISectionRoutingModule.getConfiguredBackendSection("resource-groups", "resource groups", [], [], ResourceGroupPagedSearchService, "edit resource group", [], ResourceGroupFindByCodeService, ResourceGroupCreateNewService, ResourceGroupSaveService, ResourceGroupDeleteService);
@NgModule({
    imports: [CommonModule, configuredModule]
})
export class ResourceGroupModule { }