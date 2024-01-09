import { UI, UI_SEARCH_CONFIG } from "../ui-meta-description/ui-meta-description";
import { OnInit, Injector, Component, Inject, Directive, Input, TemplateRef } from "@angular/core";
import { FormGroup, UntypedFormGroup } from '@angular/forms';
import { FormGroupConfigurationService } from "../../services/formgroup-configurator.service";

import { ActivatedRoute } from "@angular/router";
@Directive()
export  class BaseUIForm<FormConfig extends UI> implements OnInit {
    public loading:boolean=false;
    @Input() formTemplate?: TemplateRef<any>=undefined;
    public frmGroup: UntypedFormGroup=new UntypedFormGroup({});    
    constructor(protected injector: Injector,protected advancedFormGroupSupport: FormGroupConfigurationService,public config :FormConfig,protected activatedRouter:ActivatedRoute) {
        if (this.config && this.config.formGroup) {
            this.frmGroup = this.advancedFormGroupSupport.createFormGroup(this.config.formGroup);
        }
    }

    public ngOnInit() {      
           
        
    }

}