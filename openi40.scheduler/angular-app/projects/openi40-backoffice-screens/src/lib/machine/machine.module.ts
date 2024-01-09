import { CommonModule } from "@angular/common";
import { Injectable, NgModule } from "@angular/core";
import { AbstractUICreateNewService, AbstractUIDeleteService, AbstractUIFindByCodeService, AbstractUISaveService, OpenI40BackofficeMetaUISectionRoutingModule, OperationResult, OperationStatus } from "@openi40/backoffice-ui";
import { Observable, map, of } from "rxjs";
import { AbstractBasePagedSearch } from "../reusable/abstract-paged-search-service";
import { OI40DBMachine, Oi40DbMachineRepositoryService, Pageable, Sort } from "@openi40/backoffice-api";
@Injectable()
class MachinePagedSearchService extends AbstractBasePagedSearch<OI40DBMachine> {

    constructor(private service: Oi40DbMachineRepositoryService) {
        super()
    }
    protected override invokeRemote(searchArgument: { qbe: any; page: { page: number; pageSize: number; }; }): Observable<{ content?: OI40DBMachine[] | undefined; empty?: boolean | undefined; first?: boolean | undefined; last?: boolean | undefined; number?: number | undefined; numberOfElements?: number | undefined; pageable?: Pageable | undefined; size?: number | undefined; sort?: Sort | undefined; totalElements?: number | undefined; totalPages?: number | undefined; }> {
        return this.service.findByQbePagedPageOI40DBMachine(searchArgument);
    }

}
@Injectable()
class MachineFindByCodeService extends AbstractUIFindByCodeService<any> {
    constructor(private service: Oi40DbMachineRepositoryService) {
        super()
    }
    public override findByCode(code: string): Observable<OperationResult<any>> {
        return this.service.findByCodeOI40DBMachine(code).pipe(map(r => {
            return {
                status: OperationStatus.SUCCESS,
                data: r,
                msgs: []
            };

        }));
    }

}
@Injectable()
class MachineCreateNewService extends AbstractUICreateNewService<any> {

    constructor(private service: Oi40DbMachineRepositoryService) {
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
class MachineSaveService extends AbstractUISaveService<any> {

    constructor(private service: Oi40DbMachineRepositoryService) {
        super()
    }
    public override save(data: any): Observable<OperationResult<any>> {
        return this.service.updateSingleOI40DBMachine(data).pipe(map(r => {
            return {
                status: OperationStatus.SUCCESS,
                data: r,
                msgs: []
            };

        }));
    }
}
@Injectable()
class MachineDeleteService extends AbstractUIDeleteService<any> {

    constructor(private service: Oi40DbMachineRepositoryService) {
        super()
    }
    public override delete(data: any): Observable<OperationResult<any>> {
        return this.service.deleteByCodeVoid9(data.code).pipe(map(r => {
            return {
                status: OperationStatus.SUCCESS,
                data: r,
                msgs: []
            };
        }));
    }
}
const configuredModule = OpenI40BackofficeMetaUISectionRoutingModule.getConfiguredBackendSection("machines", "machines", [], [], MachinePagedSearchService, "edit machine", [], MachineFindByCodeService, MachineCreateNewService, MachineSaveService, MachineDeleteService);
@NgModule({
    imports: [CommonModule, configuredModule]
})
export class MachineModule { }