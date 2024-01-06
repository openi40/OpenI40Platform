import { CommonModule } from "@angular/common";
import { Injectable, NgModule } from "@angular/core";
import { AbstractUICreateNewService, AbstractUIDeleteService, AbstractUIFindByCodeService, AbstractUISaveService, OpenI40BackofficeMetaUISectionRoutingModule, OperationResult, OperationStatus } from "@openi40/backoffice-ui";
import { Observable, map, of } from "rxjs";
import { AbstractBasePagedSearch } from "../reusable/abstract-paged-search-service";
import { OI40DBPlant, Oi40DbPlantRepositoryService, Pageable, Sort } from "@openi40/backoffice-api";
@Injectable()
class PlantPagedSearchService extends AbstractBasePagedSearch<OI40DBPlant> {

    constructor(private service: Oi40DbPlantRepositoryService) {
        super()
    }
    protected override invokeRemote(searchArgument: { qbe: any; page: { page: number; pageSize: number; }; }): Observable<{ content?: OI40DBPlant[] | undefined; empty?: boolean | undefined; first?: boolean | undefined; last?: boolean | undefined; number?: number | undefined; numberOfElements?: number | undefined; pageable?: Pageable | undefined; size?: number | undefined; sort?: Sort | undefined; totalElements?: number | undefined; totalPages?: number | undefined; }> {
        return this.service.findByQbePagedPageOI40DBPlant(searchArgument);
    }

}
@Injectable()
class PlantFindByCodeService extends AbstractUIFindByCodeService<any> {
    constructor(private service: Oi40DbPlantRepositoryService) {
        super()
    }
    public override findByCode(code: string): Observable<OperationResult<any>> {
        return this.service.findByCodeOI40DBPlant(code).pipe(map(r => {
            return {
                status: OperationStatus.SUCCESS,
                data: r,
                msgs: []
            };

        }));
    }

}
@Injectable()
class PlantCreateNewService extends AbstractUICreateNewService<any> {

    constructor(private service: Oi40DbPlantRepositoryService) {
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
class PlantSaveService extends AbstractUISaveService<any> {

    constructor(private service: Oi40DbPlantRepositoryService) {
        super()
    }
    public override save(data: any): Observable<OperationResult<any>> {
        return this.service.updateSingleOI40DBPlant(data).pipe(map(r => {
            return {
                status: OperationStatus.SUCCESS,
                data: r,
                msgs: []
            };

        }));
    }
}
@Injectable()
class PlantDeleteService extends AbstractUIDeleteService<any> {

    constructor(private service: Oi40DbPlantRepositoryService) {
        super()
    }
    public override delete(data: any): Observable<OperationResult<any>> {
        return this.service.deleteByCodeVoid15(data.code).pipe(map(r => {
            return {
                status: OperationStatus.SUCCESS,
                data: r,
                msgs: []
            };
        }));
    }
}
const configuredModule = OpenI40BackofficeMetaUISectionRoutingModule.getConfiguredBackendSection("plants", "plants", [], [], PlantPagedSearchService, "edit plant", [], PlantFindByCodeService, PlantCreateNewService, PlantSaveService, PlantDeleteService);
@NgModule({
    imports: [CommonModule, configuredModule]
})
export class PlantModule { }