import { CommonModule } from "@angular/common";
import { Injectable, NgModule } from "@angular/core";
import { AbstractUICreateNewService, AbstractUIDeleteService, AbstractUIFindByCodeService, AbstractUISaveService, OpenI40BackofficeMetaUISectionRoutingModule, OperationResult, OperationStatus } from "@openi40/backoffice-ui";
import { Observable, map, of } from "rxjs";
import { AbstractBasePagedSearch } from "../reusable/abstract-paged-search-service";
import { OI40DBWarehouse, Oi40DbWarehouseRepositoryService, Pageable, Sort } from "@openi40/backoffice-api";
@Injectable()
class WarehousePagedSearchService extends AbstractBasePagedSearch<OI40DBWarehouse> {

    constructor(private service: Oi40DbWarehouseRepositoryService) {
        super()
    }
    protected override invokeRemote(searchArgument: { qbe: any; page: { page: number; pageSize: number; }; }): Observable<{ content?: OI40DBWarehouse[] | undefined; empty?: boolean | undefined; first?: boolean | undefined; last?: boolean | undefined; number?: number | undefined; numberOfElements?: number | undefined; pageable?: Pageable | undefined; size?: number | undefined; sort?: Sort | undefined; totalElements?: number | undefined; totalPages?: number | undefined; }> {
        return this.service.findByQbePagedPageOI40DBWarehouse(searchArgument);
    }

}
@Injectable()
class WarehouseFindByCodeService extends AbstractUIFindByCodeService<any> {
    constructor(private service: Oi40DbWarehouseRepositoryService) {
        super()
    }
    public override findByCode(code: string): Observable<OperationResult<any>> {
        return this.service.findByCodeOI40DBWarehouse(code).pipe(map(r => {
            return {
                status: OperationStatus.SUCCESS,
                data: r,
                msgs: []
            };

        }));
    }

}
@Injectable()
class WarehouseCreateNewService extends AbstractUICreateNewService<any> {

    constructor(private service: Oi40DbWarehouseRepositoryService) {
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
class WarehouseSaveService extends AbstractUISaveService<any> {

    constructor(private service: Oi40DbWarehouseRepositoryService) {
        super()
    }
    public override save(data: any): Observable<OperationResult<any>> {
        return this.service.updateSingleOI40DBWarehouse(data).pipe(map(r => {
            return {
                status: OperationStatus.SUCCESS,
                data: r,
                msgs: []
            };

        }));
    }
}
@Injectable()
class WarehouseDeleteService extends AbstractUIDeleteService<any> {

    constructor(private service: Oi40DbWarehouseRepositoryService) {
        super()
    }
    public override delete(data: any): Observable<OperationResult<any>> {
        return this.service.deleteByCodeVoid38(data.code).pipe(map(r => {
            return {
                status: OperationStatus.SUCCESS,
                data: r,
                msgs: []
            };
        }));
    }
}
const configuredModule = OpenI40BackofficeMetaUISectionRoutingModule.getConfiguredBackendSection("warehouses", "warehouses", [], [], WarehousePagedSearchService, "edit warehouse", [], WarehouseFindByCodeService, WarehouseCreateNewService, WarehouseSaveService, WarehouseDeleteService);
@NgModule({
    imports: [CommonModule, configuredModule]
})
export class WarehouseModule { }