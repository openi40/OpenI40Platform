
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
                    gotoDetailService.goToDetail(rowData,this.config,this);
                }
            }
        }
        private invokeSearch(actualFilter:SearchType){
            if (this.searchService) {
                if (this.searchService instanceof AbstractUISearchService) {
                    const s:AbstractUISearchService=this.searchService;
                    s.search(actualFilter,[]).subscribe(result=>{
                        this.results=result;
                    })
                }
                if (this.searchService instanceof AbstractUIPagedSearchService) {
                    const s:AbstractUIPagedSearchService=this.searchService;
                    s.searchPaged(actualFilter,this.actualPage).subscribe(result=>{
                        this.results=result?.data;
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
            

        }
}