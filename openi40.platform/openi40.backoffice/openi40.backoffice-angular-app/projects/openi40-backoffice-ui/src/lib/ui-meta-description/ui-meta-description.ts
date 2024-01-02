import { Injectable, InjectionToken, Type } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Observable, map } from 'rxjs';
export const UI_SEARCH_CONFIG = new InjectionToken<UI>('ui-search-configuration');
export const UI_DETAIL_CONFIG = new InjectionToken<UI>('ui-detail-configuration');
export interface UIControl {
    controlName: string;
    label: string;
    placeholder?: string;
    required?: boolean;
    cssClasses?: string;
    containerCssClasses?: string;
    type: "text" | "lookup" | "dropdown" | "multiselect" | "hidden" | "custom" | "date" | "datetime" | "number"|"boolean";
    values?: any[];
    mappings?: { label: string, identifier?: string };
    populationService?: Type<AbstractUISearchService> | Type<AbstractUIPagedSearchService> | Type<AbstractUIDataLoaderService>;
    customComponent?:any;
};
export interface UIControlRepositoryEntry extends UIControl {
    uniqueControlKey:string;
}

export interface UIControlsRepository{
    controls:UIControlRepositoryEntry[];
};


export interface UIFormGroup {
    name: string;
    controls?: UIControl[];
    formGroups?: UIFormGroup[];
    formArrays?: UIFormGroupArray[];
}
export interface UIFormGroupArray extends UIFormGroup {
    length: number;
};
export interface UIAccessRight {

};
export interface UI {
    uniqueUiKey: string;
    title: string;
    formGroup?: UIFormGroup;
    accessRights?: UIAccessRight[];
}
export class OrderMeta {
    fieldName?: string;
    direction?: "asc" | "desc";
}
export class PageMeta {
    public page: number = 0;
    public size: number = 20;
    public totalElements?: number = 0;
    public order?: OrderMeta[] = [];
}
export class Page<ContainedType> extends PageMeta {
    public data: ContainedType[] = [];
}
export abstract class AbstractUIPagedSearchService {
    public abstract searchPaged(search: any, page: PageMeta): Observable<Page<any>>;
}
export abstract class AbstractUISearchService {
    public abstract search(search: any, order?: OrderMeta[]): Observable<any[]>;
}

export abstract class AbstractUIDataLoaderService {
    public abstract load():Observable<any[]>;
}

export enum OperationStatus {
    SUCCESS, FAIL, WARN
}
export interface UIMsg {

}
export interface OperationResult<DataType> {
    data?: DataType;
    status: OperationStatus;
    msgs?: UIMsg[];
}
export abstract class AbstractUISaveService<DataType> {
    public abstract save?(data: DataType): Observable<OperationResult<DataType>>;
    public abstract save?(data: DataType[]): Observable<OperationResult<DataType[]>>;
}
export abstract class AbstractUIDeleteService<DataType> {
    public abstract delete?(data: DataType): Observable<OperationResult<DataType>>;
    public abstract delete?(data: DataType[]): Observable<OperationResult<DataType[]>>;
}
export abstract class AbstractUIFindByCodeService<DataType> {
    public abstract findByCode(code: string): Observable<OperationResult<DataType>>;
}
export abstract class  AbstractUIFindByCodeServiceAdapter<DataType> extends AbstractUIFindByCodeService<DataType>{
    public  findByCode(code: string): Observable<OperationResult<DataType>>{
        return this.invokeFindByCode(code).pipe(map(returned=>{
            const ors:OperationResult<DataType>={
                status:OperationStatus.SUCCESS,
                data:returned
            };
            return ors;
        }));
    }
    protected  abstract  invokeFindByCode(code:string):Observable<DataType>;
}
export abstract class AbstractUICreateNewService<DataType> {
    public abstract createNew(modelObject:DataType):Observable<OperationResult<DataType>>;
}

export interface UIEditableForm<DataType> extends UI {
    saveService: Type<AbstractUISaveService<DataType>>;
    deleteService?: Type<AbstractUIDeleteService<DataType>>;
}
export interface UIResultColumn {
    field?: string;
    header?: string;
}
export abstract class AbstractGoToDetailService {
    public abstract goToDetail(actualValue: any, configuration: UISearchForm<any, any>, runtimeComponent: any): void;
}
@Injectable({ providedIn: "root" })
export class DefaultGoToDetailService extends AbstractGoToDetailService {
    constructor(private route: Router, private activated: ActivatedRoute) {
        super()
    }
    public override goToDetail(actualValue: any, configuration: UISearchForm<any, any>, runtimeComponent: any): void {
        this.route.navigate([actualValue?.code,"edit"], { relativeTo: this.activated });
        
    }
};
export interface UISearchForm<SearchType, ResultType> extends UI {
    searchService: Type<AbstractUIPagedSearchService> | Type<AbstractUISearchService> | any;
    resultColumns?: UIResultColumn[];
    gotoDetailService?: Type<AbstractGoToDetailService>|any;
}

export interface UIModifiableSearchForm<SearchType, ResultType> extends UISearchForm<SearchType, ResultType>, UIEditableForm<ResultType> {

}

export interface UIDetailForm<DataType> extends UIEditableForm<DataType> {
    findByCodeService: Type<AbstractUIFindByCodeService<DataType>>;
    createNewService?:Type<AbstractUICreateNewService<DataType>>;
}
