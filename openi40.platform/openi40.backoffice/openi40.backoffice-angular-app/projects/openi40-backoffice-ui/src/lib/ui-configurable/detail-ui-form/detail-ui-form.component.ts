import { Injector, Component, Inject, Input } from "@angular/core";
import { BaseUIForm } from "../base-ui-form/base-ui-form.component";
import { AbstractGoToDetailService, AbstractUICreateNewService, AbstractUIDeleteService, AbstractUIFindByCodeService, AbstractUIPagedSearchService, AbstractUISaveService, AbstractUISearchService, OperationResult, OperationStatus, PageMeta, UIDetailForm, UIEditableForm, UIMsg, UIResultColumn, UISearchForm, UI_DETAIL_CONFIG } from "../../ui-meta-description/ui-meta-description";

import { FormGroupConfigurationService } from "../../services/formgroup-configurator.service";
import { ActivatedRoute } from "@angular/router";
import { Observable, map, of } from "rxjs";
@Component({
    selector: "detail-ui-form",
    templateUrl: "detail-ui-form.component.html",
    styleUrls: []
})
export class DetailUIFormComponent<ResultType> extends BaseUIForm<UIDetailForm<ResultType>> {
    public model?: ResultType;

    @Input() code: string='' ;
    protected findByCodeService?: AbstractUIFindByCodeService<ResultType>;
    protected saveService?: AbstractUISaveService<ResultType>;
    protected deleteService?: AbstractUIDeleteService<ResultType>;
    protected createNewService?: AbstractUICreateNewService<ResultType>;
    public isCanSave:boolean=false;
    public isCanDelete:boolean=false;
    public isCanCreateNew:boolean=false;
    constructor(injector: Injector, fgConfigurator: FormGroupConfigurationService, @Inject(UI_DETAIL_CONFIG) config: UIDetailForm<ResultType>, activatedRouter: ActivatedRoute) {
        super(injector, fgConfigurator, config, activatedRouter);
        if (this.config.findByCodeService) {
            this.findByCodeService = this.injector.get(this.config.findByCodeService);
        }
        if (this.config.saveService) {
            this.saveService = this.injector.get(this.config.saveService);
            this.isCanSave=this.saveService?true:false;
        }
        if (this.config.deleteService) {
            this.deleteService = this.injector.get(this.config.deleteService);
            this.isCanDelete=this.deleteService?true:false;
        }
        if (this.config.createNewService) {
            this.createNewService=this.injector.get(this.config.createNewService);
            this.isCanCreateNew=this.createNewService?true:false;
        }
    }
    public override ngOnInit(): void {
        super.ngOnInit();
        let searchedByCode:boolean=false;
        if (this.activatedRouter.snapshot.params['code'] ) {
            this.code=this.activatedRouter.snapshot.params['code'];
            if (this.code && this.code.trim().length>0) {
                searchedByCode=false;
                this.doSearchByCode();
            }
        }
        if (!searchedByCode && this.createNewService) {
            const sampleObject=this.activatedRouter.snapshot.data['sampleObject'];
            this.execute(sampleObject,this.setModel,this.createNewService.createNew).subscribe(results=>{
                this.showUserMessages(results?.status,results?.msgs);
            });
        }
    }
    private  execute<ParamType>(m: ParamType, consumer: (m: ResultType) => void, fnctn?: (m: ParamType) => Observable<OperationResult<ResultType>>): Observable<{ status?: OperationStatus,msgs?: UIMsg[]}> {
        if (fnctn) {
            this.loading = true;
            return fnctn(m).pipe(map(result=>{
                this.loading=false; 
                if (result?.data && ((!result?.status) || result?.status!== OperationStatus.FAIL)) {consumer(result.data);}; 
                return {status:result?.status , msgs:result?.msgs} }));
        }else {
            return of({});
        }
    }
    private setModel(m:ResultType) {
        this.model=m;
        this.frmGroup.patchValue(this.model ? this.model : {});
    }
    public doSave(): void {


        if (this.saveService !== undefined) {
            
            this.model = this.frmGroup.value;
            if (this.model) {
                this.execute<ResultType>(this.model,this.setModel,this.saveService?.save).subscribe(results=>{
                    this.showUserMessages(results?.status,results?.msgs);
                });                
            }
        }
    }

    public doDelete(): void {
        if (this.deleteService){
        this.model = this.frmGroup.value;
        if (this.model) {
            this.execute<ResultType>(this.model,(m:ResultType)=>{},this.deleteService?.delete).subscribe(results=>{
                this.showUserMessages(results?.status,results?.msgs);
            }); 
            
        }
    }
    }
    public doSearchByCode(): void {
        this.loading = true;
        this.model={} as ResultType;
        this.execute<string>(this.code,this.setModel,this.findByCodeService?.findByCode).subscribe(results=>{
            this.showUserMessages(results?.status,results?.msgs);
        });  

    }
    private showUserMessages(status?:OperationStatus,msgs?:UIMsg[]) {

    }
}