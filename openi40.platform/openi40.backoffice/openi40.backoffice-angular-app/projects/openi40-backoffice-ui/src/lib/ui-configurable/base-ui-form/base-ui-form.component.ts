import { UI } from "../../ui-meta-description/ui-meta-description";
import { OnInit, Inject, InjectionToken, Injector } from "@angular/core";
import { FormGroup } from '@angular/forms';
import { FormGroupConfigurationService } from "../../services/formgroup-configurator.service";
export const UI_CONFIG = new InjectionToken<UI>('ui-configuration');
export abstract class BaseUIForm<FormConfig extends UI> implements OnInit {
    public config?:FormConfig;
    public frmGroup?:FormGroup;
    private thisGuiConfigurationKey?:string;
    constructor(protected injector:Injector,@Inject(UI_CONFIG) private configs: UI[],private fgConfigurator:FormGroupConfigurationService) {

    }
    private retrieveConfig():UI|null|undefined{
        const cfg=this.configs?this.configs.find(ui=>ui.uniqueUiKey===this.thisGuiConfigurationKey):null;
        return cfg;
    }
    public ngOnInit() {
        this.config=this.retrieveConfig() as FormConfig;
        if (this.config && this.config.formGroup) {
            this.frmGroup=this.fgConfigurator.createFormGroup(this.config.formGroup);
        }
    }
    
}