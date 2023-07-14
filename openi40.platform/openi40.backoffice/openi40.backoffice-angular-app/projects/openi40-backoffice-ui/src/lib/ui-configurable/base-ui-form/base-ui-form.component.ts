import { UI } from "../../ui-meta-description/ui-meta-description";
import { OnInit, Injector, Component } from "@angular/core";
import { FormGroup } from '@angular/forms';
import { FormGroupConfigurationService } from "../../services/formgroup-configurator.service";
import { UIConfigurationRepositoryService } from "../../services/ui-configurations-repository.service";
import { ActivatedRoute } from "@angular/router";
@Component({
    selector:"base-form",
    template:""
})
export  class BaseUIForm<FormConfig extends UI> implements OnInit {
    public config?: FormConfig;
    public frmGroup: FormGroup=new FormGroup({});
    private thisGuiConfigurationKey: string | null = null;
    constructor(protected injector: Injector, private configurationRepositoryService: UIConfigurationRepositoryService, private fgConfigurator: FormGroupConfigurationService,private activatedRouter:ActivatedRoute) {
        const guiConfigurationKey=this.activatedRouter.snapshot?.data["guiConfigurationKey"];
        this.thisGuiConfigurationKey=guiConfigurationKey;
    }

    public ngOnInit() {
        if (this.thisGuiConfigurationKey) {
            this.config = this.configurationRepositoryService.getConfig(this.thisGuiConfigurationKey) as FormConfig;
            if (this.config && this.config.formGroup) {
                this.frmGroup = this.fgConfigurator.createFormGroup(this.config.formGroup);
            }
        }
    }

}