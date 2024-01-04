import { Component, Inject, Injector, OnChanges, OnInit, SimpleChanges } from "@angular/core";
import { ActivatedRoute } from "@angular/router";
import { DetailUIFormComponent, FormGroupConfigurationService, UIDetailForm, UI_DETAIL_CONFIG } from "@openi40/backoffice-ui";
import { GenericOrder } from "./generic-order";
@Component({
    templateUrl:"order.component.html",
    selector:"openi40-order-editing",
    providers:[]
})
export class OrderComponent extends DetailUIFormComponent<GenericOrder>{
    constructor(injector: Injector, fgConfigurator: FormGroupConfigurationService, @Inject(UI_DETAIL_CONFIG) config: UIDetailForm<GenericOrder>, activatedRouter: ActivatedRoute){
        super(injector,fgConfigurator,config,activatedRouter);
    }
    override ngOnInit(): void {
        super.ngOnInit();
    }
    
}