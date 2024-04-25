import { CommonModule } from "@angular/common";
import { Injectable, NgModule } from "@angular/core";
import { AbstractUICreateNewService, AbstractUIDeleteService, AbstractUIFindByCodeService, AbstractUIPagedSearchService, AbstractUISaveService, OpenI40BackofficeMetaUISectionRoutingModule, OperationResult, OperationStatus, Page, PageMeta } from "@openi40/backoffice-ui";
import { Observable, map, of } from "rxjs";
import { AbstractBasePagedSearch } from "../reusable/abstract-paged-search-service";
import { OI40DBProductiveCompany, Oi40DbProductiveCompanyRepositoryService, Pageable, Sort } from "@openi40/backoffice-api";
@Injectable()
class ProductiveCompaniesPagedSearchService extends AbstractBasePagedSearch<OI40DBProductiveCompany> {

    constructor(private service: Oi40DbProductiveCompanyRepositoryService) {
        super()
    }
    protected override invokeRemote(searchArgument: { qbe: any; page: { page: number; pageSize: number; }; }): Observable<{ content?: OI40DBProductiveCompany[] | undefined; empty?: boolean | undefined; first?: boolean | undefined; last?: boolean | undefined; number?: number | undefined; numberOfElements?: number | undefined; pageable?: Pageable | undefined; size?: number | undefined; sort?: Sort | undefined; totalElements?: number | undefined; totalPages?: number | undefined; }> {
        return this.service.findByQbePagedPageOI40DBProductiveCompany(searchArgument);
    }

}
@Injectable()
class ProductiveCompanyFindByCodeService extends AbstractUIFindByCodeService<any> {
    constructor(private service: Oi40DbProductiveCompanyRepositoryService) {
        super()
    }
    public override findByCode(code: string): Observable<OperationResult<any>> {
        return this.service.findByCodeOI40DBProductiveCompany(code).pipe(map(r => {
            return {
                status: OperationStatus.SUCCESS,
                data: r,
                msgs: []
            };

        }));
    }

}
@Injectable()
class ProductiveCompanyCreateNewService extends AbstractUICreateNewService<any> {

    constructor(private service: Oi40DbProductiveCompanyRepositoryService) {
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
class ProductiveCompanySaveService extends AbstractUISaveService<any> {

    constructor(private service: Oi40DbProductiveCompanyRepositoryService) {
        super()
    }
    public override save(data: any): Observable<OperationResult<any>> {
        return this.service.updateSingleOI40DBProductiveCompany(data).pipe(map(r => {
            return {
                status: OperationStatus.SUCCESS,
                data: r,
                msgs: []
            };

        }));
    }
}
@Injectable()
class ProductiveCompanyDeleteService extends AbstractUIDeleteService<any> {

    constructor(private service: Oi40DbProductiveCompanyRepositoryService) {
        super()
    }
    public override delete(data: any): Observable<OperationResult<any>> {
        return this.service.deleteByCodeVoid18(data.code).pipe(map(r => {
            return {
                status: OperationStatus.SUCCESS,
                data: r,
                msgs: []
            };
        }));
    }
}
const configuredModule = OpenI40BackofficeMetaUISectionRoutingModule.getConfiguredBackendSection("productive-companies", "companies", [], [], ProductiveCompaniesPagedSearchService, "edit company", [], ProductiveCompanyFindByCodeService, ProductiveCompanyCreateNewService, ProductiveCompanySaveService, ProductiveCompanyDeleteService);
@NgModule({
    imports: [CommonModule, configuredModule]
})
export class ProductiveCompanyModule { }