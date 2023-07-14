import {Injectable} from '@angular/core';
import {FormArray, FormControl, FormGroup} from '@angular/forms';
import { UIControl, UIFormGroup, UIFormGroupArray } from '../ui-meta-description/ui-meta-description';
@Injectable({providedIn:"root"})
export class FormGroupConfigurationService {
    public static CFG_DATA="UI_CFG_DATA";
    
    public createFormGroup(fgMeta:UIFormGroup):FormGroup{
        const cfg:any={string:FormControl};
        if (fgMeta.controls) {
            fgMeta.controls.forEach(entry=>{
                cfg[entry.controlName]=new FormControl();
                cfg[entry.controlName][FormGroupConfigurationService.CFG_DATA]=entry;
            });
        }
        const out:FormGroup=new FormGroup(cfg);
        (out as any)[FormGroupConfigurationService.CFG_DATA]=fgMeta;
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
    public createFormArray(fa:UIFormGroupArray):FormArray{
        const fgs:FormGroup[]=[];
        (fgs as any)[FormGroupConfigurationService.CFG_DATA]=fa;
        for(let i=0;i<fa.length;i++) {
            fgs.push(this.createFormGroup(fa));
        }
        const fao:FormArray=new FormArray(fgs);
        return fao;
    }
}