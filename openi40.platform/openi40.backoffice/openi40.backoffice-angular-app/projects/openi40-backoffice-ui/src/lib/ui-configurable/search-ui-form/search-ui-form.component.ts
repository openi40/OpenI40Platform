
import { Injector, Component, Inject } from "@angular/core";
import { BaseUIForm } from "../base-ui-form/base-ui-form.component";
import { AbstractGoToDetailService, AbstractUIPagedSearchService, AbstractUISearchService, PageMeta, UIResultColumn, UISearchForm, UI_SEARCH_CONFIG } from "../../ui-meta-description/ui-meta-description";

import { FormGroupConfigurationService } from "../../services/formgroup-configurator.service";
import { ActivatedRoute } from "@angular/router";
@Component({
    selector:"search-ui-form",
    templateUrl:"search-ui-form.component.html",
    styleUrls:[]
})
export class SearchUIFormComponent<SearchType,ResultType> extends BaseUIForm<UISearchForm<SearchType,ResultType>> {
        public results:ResultType[]=[];
        private searchService?:AbstractUIPagedSearchService|AbstractUISearchService;
        public actualPage: PageMeta=new PageMeta();
        private actualFilter?:SearchType;
        public pagedService:boolean=false;
        constructor(injector: Injector,  fgConfigurator: FormGroupConfigurationService,@Inject(UI_SEARCH_CONFIG) config:UISearchForm<SearchType,ResultType>, activatedRouter: ActivatedRoute) {
            super(injector,fgConfigurator,config,activatedRouter);
        }
        public override ngOnInit(): void {
            super.ngOnInit();
            if (this.config?.searchService) {
                const service=this.injector.get(this.config.searchService);
                if (service instanceof AbstractUIPagedSearchService || service instanceof AbstractUISearchService) {
                    this.searchService=service;
                    this.pagedService=(service instanceof AbstractUIPagedSearchService)?true:false;
                }
            }
        }
        public get disabledSearch():boolean {
            return this.frmGroup?.valid===true?false:true;
        }
        public doSearch(){
            const actualFilter=this.frmGroup?.value;
            this.invokeSearch(actualFilter);
        }
        public goToDetail(rowData:ResultType){
            if (this.config?.gotoDetailService) {
                const gotoDetailService:AbstractGoToDetailService=this.injector.get(this.config.gotoDetailService);
                if (gotoDetailService) {
                    gotoDetailService.goToDetail(rowData,this.config,this,this.activatedRouter);
                }
            }
        }
        private invokeSearch(actualFilter?:SearchType){
            this.actualFilter=actualFilter;
            if (this.searchService) {
                if (this.searchService instanceof AbstractUISearchService) {
                    const s:AbstractUISearchService=this.searchService;
                    this.loading=true;
                    s.search(actualFilter,[]).subscribe(result=>{
                        this.results=result;
                        this.loading=false;
                    })
                }
                if (this.searchService instanceof AbstractUIPagedSearchService) {
                    const s:AbstractUIPagedSearchService=this.searchService;
                    this.loading=true;
                    s.searchPaged(actualFilter,this.actualPage).subscribe(result=>{
                        this.results=result?.data;
                        this.actualPage={
                            page:result.page,
                            size:result.size,
                            totalElements:result.totalElements,
                            order:result.order
                        };
                       
                        this.loading=false;
                    });
                }
            }
        }
        public get columnsConfig():UIResultColumn[]{
            if (this.config?.resultColumns) {
                return this.config.resultColumns;
            }else 
                return [];
        }
        public handlePage(ev:{first:number,rows:number}){
            this.loading=true;
            const newPage:number=ev.first/this.actualPage.size;
            const newPageSize:number=ev.rows;
            this.actualPage={
                page:newPage,
                size:newPageSize,
                totalElements:this.actualPage?.totalElements,
                order:this.actualPage.order
            };
            this.invokeSearch(this.actualFilter);

        }
}