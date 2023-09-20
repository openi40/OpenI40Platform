import { Injectable } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Observable } from 'rxjs';
export interface UIControl {
    controlName: string;
    label: string;
    placeholder?: string;
    required?: boolean;
    cssClasses?: string;
    containerCssClasses?: string;
    type: "text" | "lookup" | "dropdown" | "multiselect" | "hidden" | "custom" | "date" | "datetime" | "number";
    values?: any[];
    mappings?: { label: string, identifier?: string };
    populationService?: AbstractUISearchService | AbstractUIPagedSearchService;
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
    public size: number = 0;
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
export enum SaveStatus {
    SUCCESS, FAIL, WARN
}
export interface UIMsg {

}
export interface OperationResult<DataType> {
    data?: DataType | DataType[];
    status: SaveStatus;
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
export abstract class AbstractUIFindByIdService<DataType> {
    public abstract findBy(id: number): Observable<OperationResult<DataType>>;
}
export interface UIEditableForm<DataType> extends UI {
    saveService: AbstractUISaveService<DataType>;
    deleteService?: AbstractUIDeleteService<DataType>;
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
        this.route.navigate([actualValue?.id], { relativeTo: this.activated });
    }
};
export interface UISearchForm<SearchType, ResultType> extends UI {
    searchService: AbstractUIPagedSearchService | AbstractUISearchService | any;
    resultColumns?: UIResultColumn[];
    gotoDetailService?: AbstractGoToDetailService|any;
}

export interface UIModifiableSearchForm<SearchType, ResultType> extends UISearchForm<SearchType, ResultType>, UIEditableForm<ResultType> {

}

export interface UIDetailForm<DataType> extends UIEditableForm<DataType> {
    findByIdService: AbstractUIFindByIdService<DataType>;
}
