import { UI,UIFormGroup, UIFormGroupArray } from "../../public-api";
import {OnInit, Inject, Injectable, InjectionToken, Injector} from "@angular/core";
import {FormArray, FormControl, FormGroup} from '@angular/forms';
export const UI_CONFIG = new InjectionToken<UI>('ui-configuration');
export class BaseUIForm<FormConfig extends UI> implements OnInit {
    public config:FormConfig;
    public frmGroup:FormGroup;
    private thisGuiConfigurationKey:string;
    constructor(protected injector:Injector,@Inject(UI_CONFIG) private configs: UI[]) {

    }
    private retrieveConfig():UI|null|undefined{
        const cfg=this.configs?this.configs.find(ui=>ui.uniqueUiKey===this.thisGuiConfigurationKey):null;
        return cfg;
    }
    public ngOnInit() {
        this.config=this.retrieveConfig() as FormConfig;
        if (this.config && this.config.formGroup) {
            this.frmGroup=this.createFormGroup(this.config.formGroup);
        }
    }
    private createFormGroup(fgMeta:UIFormGroup):FormGroup{
        const cfg:any={string:FormControl};
        if (fgMeta.controls) {
            fgMeta.controls.forEach(entry=>{
                cfg[entry.controlName]=new FormControl();
            });
        }
        const out:FormGroup=new FormGroup(cfg);
        if (fgMeta.formGroups) {
            fgMeta.formGroups.forEach(entry=>{
                out.addControl(entry.name,this.createFormGroup(entry));
            });
        }
        if (fgMeta.formArrays) {
            fgMeta.formArrays.forEach(entry=>{
                out.addControl(entry.name,this.createFormArray(entry));
            });
        }
        return out;
    }
    private createFormArray(fa:UIFormGroupArray):FormArray{
        const fgs:FormGroup[]=[];
        for(let i=0;i<fa.length;i++) {
            fgs.push(this.createFormGroup(fa));
        }
        const fao:FormArray=new FormArray(fgs);
        return fao;
    }
}