import { UI, UI_SEARCH_CONFIG } from "../../ui-meta-description/ui-meta-description";
import { OnInit, Injector, Component, Inject, Directive } from "@angular/core";
import { FormGroup, UntypedFormGroup } from '@angular/forms';
import { FormGroupConfigurationService } from "../../services/formgroup-configurator.service";

import { ActivatedRoute } from "@angular/router";
@Directive()
export  class BaseUIForm<FormConfig extends UI> implements OnInit {
    public loading:boolean=false;
    public frmGroup: UntypedFormGroup=new UntypedFormGroup({});    
    constructor(protected injector: Injector,protected fgConfigurator: FormGroupConfigurationService,public config :FormConfig,protected activatedRouter:ActivatedRoute) {
        if (this.config && this.config.formGroup) {
            this.frmGroup = this.fgConfigurator.createFormGroup(this.config.formGroup);
        }
    }

    public ngOnInit() {      
           
        
    }

}