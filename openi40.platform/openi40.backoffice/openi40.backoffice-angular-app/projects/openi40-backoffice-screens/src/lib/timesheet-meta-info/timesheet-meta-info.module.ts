import { CommonModule } from "@angular/common";
import { Injectable, NgModule } from "@angular/core";
import { AbstractUICreateNewService, AbstractUIDeleteService, AbstractUIFindByCodeService, AbstractUISaveService, OpenI40BackofficeMetaUISectionRoutingModule, OperationResult, OperationStatus, UIControl, UIResultColumn } from "@openi40/backoffice-ui";
import { Observable, map, of } from "rxjs";
import { AbstractBasePagedSearch } from "../reusable/abstract-paged-search-service";
import { OI40DBTimesheetMetaInfo, Oi40DbTimesheetMetaInfoRepositoryService, Pageable, Sort } from "@openi40/backoffice-api";
import { OI40ProductiveCompanyAutocompleteSearchService, OI40TimesheetMetaInfoAutocompleteSearchService, Openi40BackofficeServicesModule } from "@openi40/backoffice-services";
@Injectable()
class TimesheetMetaInfoPagedSearchService extends AbstractBasePagedSearch<OI40DBTimesheetMetaInfo> {

    constructor(private service: Oi40DbTimesheetMetaInfoRepositoryService) {
        super()
    }
    protected override invokeRemote(searchArgument: { qbe: any; page: { page: number; pageSize: number; }; }): Observable<{ content?: OI40DBTimesheetMetaInfo[] | undefined; empty?: boolean | undefined; first?: boolean | undefined; last?: boolean | undefined; number?: number | undefined; numberOfElements?: number | undefined; pageable?: Pageable | undefined; size?: number | undefined; sort?: Sort | undefined; totalElements?: number | undefined; totalPages?: number | undefined; }> {
        return this.service.findByQbePagedPageOI40DBTimesheetMetaInfo(searchArgument);
    }

}
@Injectable()
class TimesheetMetaInfoFindByCodeService extends AbstractUIFindByCodeService<any> {
    constructor(private service: Oi40DbTimesheetMetaInfoRepositoryService) {
        super()
    }
    public override findByCode(code: string): Observable<OperationResult<any>> {
        return this.service.findByCodeOI40DBTimesheetMetaInfo(code).pipe(map(r => {
            return {
                status: OperationStatus.SUCCESS,
                data: r,
                msgs: []
            };

        }));
    }

}
@Injectable()
class TimesheetMetaInfoCreateNewService extends AbstractUICreateNewService<any> {

    constructor(private service: Oi40DbTimesheetMetaInfoRepositoryService) {
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
class TimesheetMetaInfoSaveService extends AbstractUISaveService<any> {

    constructor(private service: Oi40DbTimesheetMetaInfoRepositoryService) {
        super()
    }
    public override save(data: any): Observable<OperationResult<any>> {
        return this.service.updateSingleOI40DBTimesheetMetaInfo(data).pipe(map(r => {
            return {
                status: OperationStatus.SUCCESS,
                data: r,
                msgs: []
            };

        }));
    }
}
@Injectable()
class TimesheetMetaInfoDeleteService extends AbstractUIDeleteService<any> {

    constructor(private service: Oi40DbTimesheetMetaInfoRepositoryService) {
        super()
    }
    public override delete(data: any): Observable<OperationResult<any>> {
        return this.service.deleteByCodeVoid35(data.code).pipe(map(r => {
            return {
                status: OperationStatus.SUCCESS,
                data: r,
                msgs: []
            };
        }));
    }
}

const ADDITIONAL_SEARCH_MODULE:UIControl[]=[];
const ADDITIONAL_EDIT_MODULE:UIControl[]=[];
const ADDITIONAL_RESULT_TABLE_MODULE:UIResultColumn[]=[];
const configuredModule = OpenI40BackofficeMetaUISectionRoutingModule.getConfiguredBackendSection("timesheet-meta-infos", "calendars", ADDITIONAL_SEARCH_MODULE, ADDITIONAL_RESULT_TABLE_MODULE, TimesheetMetaInfoPagedSearchService, "edit calendar", ADDITIONAL_EDIT_MODULE, TimesheetMetaInfoFindByCodeService, TimesheetMetaInfoCreateNewService, TimesheetMetaInfoSaveService, TimesheetMetaInfoDeleteService);
@NgModule({
    imports: [CommonModule,Openi40BackofficeServicesModule, configuredModule]
})
export class TimesheetMetaInfoModule { }