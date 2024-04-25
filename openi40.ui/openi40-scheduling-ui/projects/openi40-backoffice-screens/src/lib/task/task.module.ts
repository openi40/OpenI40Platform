import { CommonModule } from "@angular/common";
import { Injectable, NgModule } from "@angular/core";
import { AbstractUICreateNewService, AbstractUIDeleteService, AbstractUIFindByCodeService, AbstractUISaveService, OpenI40BackofficeMetaUISectionRoutingModule, OperationResult, OperationStatus } from "@openi40/backoffice-ui";
import { Observable, map, of } from "rxjs";
import { AbstractBasePagedSearch } from "../reusable/abstract-paged-search-service";
import { OI40DBTask, Oi40DbTaskRepositoryService, Pageable, Sort } from "@openi40/backoffice-api";
@Injectable()
class TaskPagedSearchService extends AbstractBasePagedSearch<OI40DBTask> {

    constructor(private service: Oi40DbTaskRepositoryService) {
        super()
    }
    protected override invokeRemote(searchArgument: { qbe: any; page: { page: number; pageSize: number; }; }): Observable<{ content?: OI40DBTask[] | undefined; empty?: boolean | undefined; first?: boolean | undefined; last?: boolean | undefined; number?: number | undefined; numberOfElements?: number | undefined; pageable?: Pageable | undefined; size?: number | undefined; sort?: Sort | undefined; totalElements?: number | undefined; totalPages?: number | undefined; }> {
        return this.service.findByQbePagedPageOI40DBTask(searchArgument);
    }

}
@Injectable()
class TaskFindByCodeService extends AbstractUIFindByCodeService<any> {
    constructor(private service: Oi40DbTaskRepositoryService) {
        super()
    }
    public override findByCode(code: string): Observable<OperationResult<any>> {
        return this.service.findByCodeOI40DBTask(code).pipe(map(r => {
            return {
                status: OperationStatus.SUCCESS,
                data: r,
                msgs: []
            };

        }));
    }

}
@Injectable()
class TaskCreateNewService extends AbstractUICreateNewService<any> {

    constructor(private service: Oi40DbTaskRepositoryService) {
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
class TaskSaveService extends AbstractUISaveService<any> {

    constructor(private service: Oi40DbTaskRepositoryService) {
        super()
    }
    public override save(data: any): Observable<OperationResult<any>> {
        return this.service.updateSingleOI40DBTask(data).pipe(map(r => {
            return {
                status: OperationStatus.SUCCESS,
                data: r,
                msgs: []
            };

        }));
    }
}
@Injectable()
class TaskDeleteService extends AbstractUIDeleteService<any> {

    constructor(private service: Oi40DbTaskRepositoryService) {
        super()
    }
    public override delete(data: any): Observable<OperationResult<any>> {
        return this.service.deleteByCodeVoid31(data.code).pipe(map(r => {
            return {
                status: OperationStatus.SUCCESS,
                data: r,
                msgs: []
            };
        }));
    }
}
const configuredModule = OpenI40BackofficeMetaUISectionRoutingModule.getConfiguredBackendSection("tasks", "productive tasks", [], [], TaskPagedSearchService, "edit task", [], TaskFindByCodeService, TaskCreateNewService, TaskSaveService, TaskDeleteService);
@NgModule({
    imports: [CommonModule, configuredModule]
})
export class TaskModule { }