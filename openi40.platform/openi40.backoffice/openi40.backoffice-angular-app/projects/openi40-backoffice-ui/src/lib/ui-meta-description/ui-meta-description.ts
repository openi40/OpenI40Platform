import {Observable} from 'rxjs';
export interface UIControl {
    controlName:string;
    label:string;
    required?:boolean;
    type:"input"|"lookup"|"combo"|"hidden"|"custom";
    populationService?:AbstractUISearchService<any,any>|AbstractUIPagedSearchService<any,any>;
}

export interface UIFormGroup{
    name:string;
    controls?:UIControl[];
    formGroups?:UIFormGroup[];
    formArrays?:UIFormGroupArray[];
}
export interface UIFormGroupArray extends UIFormGroup{
        length:number;
};
export interface UIAccessRight{

};
export interface UI {
        uniqueUiKey:string;
        title:string;
        formGroup?:UIFormGroup;
        accessRights?:UIAccessRight[];
}
export class OrderMeta {
    fieldName?:string;
    direction?:"asc"|"desc";
}
export class PageMeta {
    public page:number=0;
    public size:number=0;
    public totalElements?:number=0;
    public order?:OrderMeta[]=[];
}
export class Page<ContainedType> extends PageMeta{
    public data:ContainedType[]=[];
}
export abstract class AbstractUIPagedSearchService<SearchType,ResultType> {
    public abstract search(search:SearchType,page:PageMeta):Observable<Page<ResultType>>;
}
export abstract class AbstractUISearchService<SearchType,ResultType> {
    public abstract search(search:SearchType,order?:OrderMeta[]):Observable<ResultType[]>;
}
export enum SaveStatus{
    SUCCESS,FAIL,WARN
}
export interface UIMsg {

}
export interface OperationResult<DataType> {
    data?:DataType|DataType[];
    status:SaveStatus;
    msgs?:UIMsg[];
}
export abstract class AbstractUISaveService<DataType> {
    public abstract save?(data:DataType):Observable<OperationResult<DataType>>;
    public abstract save?(data:DataType[]):Observable<OperationResult<DataType[]>>;
}
export abstract class AbstractUIDeleteService<DataType> {
    public abstract delete?(data:DataType):Observable<OperationResult<DataType>>;
    public abstract delete?(data:DataType[]):Observable<OperationResult<DataType[]>>;
}
export abstract class AbstractUIFindByIdService<DataType> {
    public abstract findBy(id:number):Observable<OperationResult<DataType>>;    
}
export interface UIEditableForm<DataType> extends UI{
    saveService:AbstractUISaveService<DataType>;
    deleteService?:AbstractUIDeleteService<DataType>;
}

export interface UISearchForm<SearchType,ResultType> extends UI{
        pagedSearch:boolean;
        searchService:AbstractUIPagedSearchService<SearchType,ResultType>|AbstractUISearchService<SearchType,ResultType>;
}

export interface UIModifiableSearchForm<SearchType,ResultType> extends UISearchForm<SearchType,ResultType>,UIEditableForm<ResultType>{

}

export interface UIDetailForm<DataType> extends UIEditableForm<DataType>{
        findByIdService:AbstractUIFindByIdService<DataType>;
}
