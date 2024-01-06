import { Injector, Component, Inject, Input } from "@angular/core";
import { BaseUIForm } from "../base-ui-form/base-ui-form.component";
import { AbstractGoToDetailService, AbstractUICreateNewService, AbstractUIDeleteService, AbstractUIFindByCodeService, AbstractUIPagedSearchService, AbstractUISaveService, AbstractUISearchService, DEFAULT_FIELD_TRANSLATORS, OperationResult, OperationStatus, PageMeta, UIDetailForm, UIEditableForm, UIMsg, UIResultColumn, UISearchForm, UI_DETAIL_CONFIG } from "../ui-meta-description/ui-meta-description";

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
    @Input() code: string = '';
    @Input() checkModifyMode:(data?:ResultType)=>boolean=(data?:any)=>{
        const ret:boolean=data?.code?true:false;
        return ret;
    }
    protected findByCodeService?: AbstractUIFindByCodeService<ResultType>;
    protected saveService?: AbstractUISaveService<ResultType>;
    protected deleteService?: AbstractUIDeleteService<ResultType>;
    protected createNewService?: AbstractUICreateNewService<ResultType>;
    public editExistingRecord: boolean = false;
    public isCanSave: boolean = false;
    public isCanDelete: boolean = false;
    public isCanCreateNew: boolean = false;
    public modifyMode:boolean=false;
    public get saveDisabled(): boolean {
        return (this.isCanSave && this?.frmGroup && this?.frmGroup.valid) ? false : true;
    }
    constructor(injector: Injector, fgConfigurator: FormGroupConfigurationService, @Inject(UI_DETAIL_CONFIG) config: UIDetailForm<ResultType>, activatedRouter: ActivatedRoute) {
        super(injector, fgConfigurator, config, activatedRouter);
        if (this.config.findByCodeService) {
            this.findByCodeService = this.injector.get(this.config.findByCodeService);
        }
        if (this.config.saveService) {
            this.saveService = this.injector.get(this.config.saveService);
            this.isCanSave = this.saveService ? true : false;
        }
        if (this.config.deleteService) {
            this.deleteService = this.injector.get(this.config.deleteService);
            this.isCanDelete = this.deleteService ? true : false;
        }
        if (this.config.createNewService) {
            this.createNewService = this.injector.get(this.config.createNewService);
            this.isCanCreateNew = this.createNewService ? true : false;
        }
    }
    public override ngOnInit(): void {
        super.ngOnInit();
        let searchedByCode: boolean = false;
        if (this.activatedRouter.snapshot.params['code']) {
            this.code = decodeURIComponent(this.activatedRouter.snapshot.params['code']);
            if (this.code && this.code.trim().length > 0) {
                searchedByCode = false;
                this.doSearchByCode();
            }
        }
        if (!searchedByCode && this.createNewService) {
            const sampleObject = this.activatedRouter.snapshot.data['sampleObject'];
            this.execute(sampleObject, this.setModel, this.createNewService.createNew).subscribe({
                next:
                    results => {
                        this.showUserMessages(results?.status, results?.msgs);
                    },
                error: error => {
                    this.loading = false;
                    console.error(error);
                }

            });
        }
    }
    private execute<ParamType>(m: ParamType, consumer: (m: ResultType) => void, fnctn?: (m: ParamType) => Observable<OperationResult<ResultType>> | undefined): Observable<{ status?: OperationStatus, msgs?: UIMsg[] }> {
        if (fnctn) {
            this.loading = true;
            const r = fnctn(m);
            if (r) {
                return r.pipe(map(result => {
                    this.loading = false;
                    if (result?.data && ((!result?.status) || result?.status !== OperationStatus.FAIL)) {
                        consumer(result.data);
                    };
                    return { status: result?.status, msgs: result?.msgs };
                }));
            }
            else return of({});
        } else {
            return of({});
        }
    }
    private setModel(m: ResultType) {
        this.model = m;
        this.frmGroup.patchValue(this.model ? this.model : {});
        this.modifyMode=this.checkModifyMode(this.model);
    }
    private transduceFields(v?:any):any {
        const ov:any={};
        if (v) {
            if (this.config?.formGroup?.controls) {
                this.config.formGroup.controls.forEach(ctrl=>{
                    if (v[ctrl.controlName] && ctrl.customOutputTranslator) {
                        ov[ctrl.controlName]=ctrl.customOutputTranslator(v[ctrl.controlName]);
                    }else if (ctrl.type){
                        ov[ctrl.controlName]=DEFAULT_FIELD_TRANSLATORS[ctrl.type](v[ctrl.controlName]);
                    }
                });
            }
        }
        return ov;
    }
    public doSave(): void {
        console.log("Running doSave()")

        if (this.saveService !== undefined) {
            const actualModel=this.frmGroup.value;
            this.model = this.transduceFields(actualModel);
            if (this.model && this.saveService) {
                const invokeSaveService = (m: ResultType) => {
                    if (this.saveService && this.saveService.save) {
                        this.editExistingRecord = true;
                        return this.saveService.save(m);
                    } else return undefined;
                };
                this.execute<ResultType>(this.model, (m) => { this.setModel(m) }, invokeSaveService).subscribe(results => {
                    this.showUserMessages(results?.status, results?.msgs);
                });
            }
        }
    }

    public doDelete(): void {
        console.log("Running doDelete()")
        if (this.deleteService) {
            this.model = this.frmGroup.value;
            if (this.model) {
                this.execute<ResultType>(this.model, (m: ResultType) => { }, this.deleteService?.delete).subscribe(results => {
                    this.showUserMessages(results?.status, results?.msgs);
                });

            }
        }
    }
    public doSearchByCode(): void {
        console.log("Running doSearchByCode()")
        this.loading = true;
        this.model = {} as ResultType;
        this.execute<string>(this.code, (m) => { this.setModel(m) }, (m) => {
            return this.findByCodeService?.findByCode(m);
        }).subscribe(
            {
                next: results => {
                    this.editExistingRecord = true;
                    this.showUserMessages(results?.status, results?.msgs);
                    
                    
                }, error: error => { 
                    this.loading=false;
                    console.error(error);
                }
            });

    }
    private showUserMessages(status?: OperationStatus, msgs?: UIMsg[]) {

    }
}