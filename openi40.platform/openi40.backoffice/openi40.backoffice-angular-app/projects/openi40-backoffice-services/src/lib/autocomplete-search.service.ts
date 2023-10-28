import { Injectable } from "@angular/core";
import { AutoCompleteData, OI40DBCoProductItem, OI40DBProduct, Oi40DbCoProductItemRepositoryService, Oi40DbPlantRepositoryService, Oi40DbProductRepositoryService, Oi40DbWarehouseRepositoryService, PageOI40DBCoProductItem, PageOI40DBPlant, PageOI40DBProduct, PageOI40DBProductiveCompany, PageOI40DBWarehouse, Pageable, Sort } from "@openi40/backoffice-api";
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
            controlName:"product",
            label:"Product",
            type:"dropdown",
            placeholder:"Select product",
            populationService: OI40ProductAutocompleteSearchService
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
            populationService: OI40CoProductItemAutocompleteSearchService
        };
    }
}
@Injectable({providedIn:"root"})
export class OI40ProductiveCompanyAutocompleteSearchService extends AbstractAutocompletePagedSearchService<PageOI40DBProductiveCompany>{
    constructor(private remoteService:Oi40DbProductRepositoryService) {
        super()
    }
    protected override doRemoteCall(autoCompleteData: AutoCompleteData): Observable<PageOI40DBProductiveCompany> {
        return this.remoteService.doAutocompletePageOI40DBProduct(autoCompleteData);
    }
    public static getControlConfig():UIControl {
        return {
            controlName:"productiveCompany",
            label:"Company",
            type:"dropdown",
            placeholder:"Select company",
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
            controlName:"plant",
            label:"Plant",
            type:"dropdown",
            placeholder:"Select plant",
            populationService: OI40PlantAutocompleteSearchService
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
            controlName:"warehouse",
            label:"Warehouse",
            type:"dropdown",
            placeholder:"Select warehouse",
            populationService: OI40WarehouseAutocompleteSearchService
        };
    }
}