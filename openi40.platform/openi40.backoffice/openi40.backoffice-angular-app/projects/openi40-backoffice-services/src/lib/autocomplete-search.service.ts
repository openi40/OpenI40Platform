import { Injectable } from "@angular/core";
import { AutoCompleteData, OI40DBCoProductItem, OI40DBProduct, Oi40DbCoProductItemRepositoryService, Oi40DbDepartmentRepositoryService, Oi40DbPartnerRepositoryService, Oi40DbPlantRepositoryService, Oi40DbProductRepositoryService, Oi40DbProductiveCompanyRepositoryService, Oi40DbTimesheetMetaInfoRepositoryService, Oi40DbWarehouseRepositoryService, PageOI40DBCoProductItem, PageOI40DBDepartment, PageOI40DBPartner, PageOI40DBPlant, PageOI40DBProduct, PageOI40DBProductiveCompany, PageOI40DBTimesheetMetaInfo, PageOI40DBWarehouse, Pageable, Sort } from "@openi40/backoffice-api";
import { AbstractUIPagedSearchService, Page, PageMeta, UIControl } from "@openi40/backoffice-ui";
import { Observable, map } from "rxjs";


 abstract class AbstractAutocompletePagedSearchService<ResultPageType extends {content?: Array<any>;empty?: boolean;   first?: boolean;        last?: boolean;        number?: number;        numberOfElements?: number;        pageable?: Pageable;        size?: number;        sort?: Sort;        totalElements?: number;        totalPages?: number;}> extends AbstractUIPagedSearchService {
    protected abstract  doRemoteCall(autoCompleteData:AutoCompleteData):Observable<ResultPageType>;
    
    public override searchPaged(search: any, page: PageMeta): Observable<Page<any>> {
        const autoCompleteData:AutoCompleteData={
            searchString:search,
            page:page?{
                page:page?.page,
                pageSize:page?.size,
                totalElements:page?.totalElements
            }:undefined
        }
        return this.doRemoteCall(autoCompleteData).pipe(map(returned=>{
            const page:Page<any>=new Page<any>();
            page.data=returned?.content?returned.content:[];
            page.page=returned?.pageable?.pageNumber?returned.pageable.pageNumber:0;
            page.totalElements=returned?.totalElements?returned.totalElements:0;
            return page;
        }));
    }
}
@Injectable({providedIn:"root"})
export class OI40ProductAutocompleteSearchService extends AbstractAutocompletePagedSearchService<PageOI40DBProduct>{
    constructor(private remoteService:Oi40DbProductRepositoryService) {
        super()
    }
    protected override doRemoteCall(autoCompleteData: AutoCompleteData): Observable<PageOI40DBProduct> {
        return this.remoteService.doAutocompletePageOI40DBProduct(autoCompleteData);
    }
    public static getControlConfig():UIControl {
        return {
            controlName:"productCode",
            label:"Product",
            type:"dropdown",
            placeholder:"Select product",
            containerCssClasses:"col-4",
            populationService: OI40ProductAutocompleteSearchService
        };
    }
}
@Injectable({providedIn:"root"})
export class OI40PartnerAutocompleteSearchService extends AbstractAutocompletePagedSearchService<PageOI40DBPartner>{
    constructor(private remoteService:Oi40DbPartnerRepositoryService) {
        super()
    }
    protected override doRemoteCall(autoCompleteData: AutoCompleteData): Observable<PageOI40DBPartner> {
        return this.remoteService.doAutocompletePageOI40DBPartner(autoCompleteData);
    }
    public static getControlConfig():UIControl {
        return {
            controlName:"partner",
            label:"Partner",
            type:"dropdown",
            placeholder:"Select partner",
            containerCssClasses:"col-4",
            populationService: OI40PartnerAutocompleteSearchService
        };
    }
}
@Injectable({providedIn:"root"})
export class OI40CoProductItemAutocompleteSearchService extends AbstractAutocompletePagedSearchService<PageOI40DBCoProductItem>{
    constructor(private remoteService:Oi40DbCoProductItemRepositoryService) {
        super()
    }
    protected override doRemoteCall(autoCompleteData: AutoCompleteData): Observable<PageOI40DBCoProductItem> {
        return this.remoteService.doAutocompletePageOI40DBCoProductItem(autoCompleteData);
    }
    public static getControlConfig():UIControl {
        return {
            controlName:"coProduct",
            label:"Co-product",
            type:"dropdown",
            placeholder:"Select co-product",
            containerCssClasses:"col-4",
            populationService: OI40CoProductItemAutocompleteSearchService
        };
    }
}
@Injectable({providedIn:"root"})
export class OI40ProductiveCompanyAutocompleteSearchService extends AbstractAutocompletePagedSearchService<PageOI40DBProductiveCompany>{
    constructor(private remoteService:Oi40DbProductiveCompanyRepositoryService) {
        super()
    }
    protected override doRemoteCall(autoCompleteData: AutoCompleteData): Observable<PageOI40DBProductiveCompany> {
        return this.remoteService.doAutocompletePageOI40DBProductiveCompany(autoCompleteData);
    }
    public static getControlConfig():UIControl {
        return {
            controlName:"productiveCompanyCode",
            label:"Company",
            type:"dropdown",
            placeholder:"Select company",
            containerCssClasses:"col-4",
            populationService: OI40ProductiveCompanyAutocompleteSearchService
        };
    }
}
@Injectable({providedIn:"root"})
export class OI40PlantAutocompleteSearchService extends AbstractAutocompletePagedSearchService<PageOI40DBPlant>{
    constructor(private remoteService:Oi40DbPlantRepositoryService) {
        super()
    }
    protected override doRemoteCall(autoCompleteData: AutoCompleteData): Observable<PageOI40DBPlant> {
        return this.remoteService.doAutocompletePageOI40DBPlant(autoCompleteData);
    }
    public static getControlConfig():UIControl {
        return {
            controlName:"plantCode",
            label:"Plant",
            type:"dropdown",
            placeholder:"Select plant",
            containerCssClasses:"col-4",
            populationService: OI40PlantAutocompleteSearchService
        };
    }
}
@Injectable({providedIn:"root"})
export class OI40DepartmentAutocompleteSearchService extends AbstractAutocompletePagedSearchService<PageOI40DBDepartment>{
    constructor(private remoteService:Oi40DbDepartmentRepositoryService) {
        super()
    }
    protected override doRemoteCall(autoCompleteData: AutoCompleteData): Observable<PageOI40DBDepartment> {
        return this.remoteService.doAutocompletePageOI40DBDepartment(autoCompleteData);
    }
    public static getControlConfig():UIControl {
        return {
            controlName:"departmentCode",
            label:"Department",
            type:"dropdown",
            placeholder:"Select department",
            containerCssClasses:"col-4",
            populationService: OI40DepartmentAutocompleteSearchService
        };
    }
}
@Injectable({providedIn:"root"})
export class OI40WarehouseAutocompleteSearchService extends AbstractAutocompletePagedSearchService<PageOI40DBWarehouse>{
    constructor(private remoteService:Oi40DbWarehouseRepositoryService) {
        super()
    }
    protected override doRemoteCall(autoCompleteData: AutoCompleteData): Observable<PageOI40DBWarehouse> {
        return this.remoteService.doAutocompletePageOI40DBWarehouse(autoCompleteData);
    }
    public static getControlConfig():UIControl {
        return {
            controlName:"warehouseCode",
            label:"Warehouse",
            type:"dropdown",
            placeholder:"Select warehouse",
            containerCssClasses:"col-4",
            populationService: OI40WarehouseAutocompleteSearchService
        };
    }
}
@Injectable({providedIn:"root"})
export class OI40TimesheetMetaInfoAutocompleteSearchService extends AbstractAutocompletePagedSearchService<PageOI40DBTimesheetMetaInfo>{
    constructor(private remoteService:Oi40DbTimesheetMetaInfoRepositoryService) {
        super()
    }
    protected override doRemoteCall(autoCompleteData: AutoCompleteData): Observable<PageOI40DBTimesheetMetaInfo> {
        return this.remoteService.doAutocompletePageOI40DBTimesheetMetaInfo(autoCompleteData);
    }
    public static getControlConfig():UIControl {
        return {
            controlName:"timesheetMetaInfoCode",
            label:"calendar",
            type:"dropdown",
            placeholder:"Select calendar",
            containerCssClasses:"col-4",
            populationService: OI40TimesheetMetaInfoAutocompleteSearchService
        };
    }
}
