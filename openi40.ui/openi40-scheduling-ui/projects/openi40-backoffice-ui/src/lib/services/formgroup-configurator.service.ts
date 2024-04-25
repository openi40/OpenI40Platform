import {Injectable} from '@angular/core';
import {FormArray, FormControl, FormGroup} from '@angular/forms';
import { DEFAULT_FIELD_TRANSLATORS, UIControl, UIFormGroup, UIFormGroupArray } from '../ui-configurable/ui-meta-description/ui-meta-description';
import { FormatWidth } from '@angular/common';
@Injectable({providedIn:"root"})
export class FormGroupConfigurationService {
    public static CFG_DATA="UI_CFG_DATA";
    
    public createFormGroup(fgMeta:UIFormGroup):FormGroup{
        const cfg:any={};
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
        
        for(let i:number=0;i<fa.length;i++) {
            fgs.push(this.createFormGroup(fa));
        }
        const fao:FormArray=new FormArray(fgs);
        (fao as any)[FormGroupConfigurationService.CFG_DATA]=fa;
        return fao;
    }
    public setValue(rootValue:any,formGroup:FormGroup,options?: { onlySelf?: boolean | undefined; emitEvent?: boolean | undefined;} ) {        
        const configuration:UIFormGroup=(formGroup as any)[FormGroupConfigurationService.CFG_DATA];
        if (!configuration) throw Error("Passed formGroup does not have metaInfos");
        formGroup.patchValue(rootValue,options);
        if (configuration.formArrays) {
            configuration.formArrays.forEach(fa=>{
                let arrayValue:any[]=rootValue[fa.name];
                let formGroupArray:FormArray= formGroup.controls[fa.name] as FormArray;
                if (!arrayValue) arrayValue=[];
                if (!Array.isArray(arrayValue)) {
                    arrayValue=[arrayValue];
                }
                this.setArrayValue(arrayValue,formGroupArray,options);
            });
        }
    }
    public setArrayValue(rootValue:any[],formGroupArray:FormArray,options?: { onlySelf?: boolean | undefined; emitEvent?: boolean | undefined;}) {
        const configuration:UIFormGroupArray=(formGroupArray as any)[FormGroupConfigurationService.CFG_DATA];
        if (!configuration) throw Error("Passed formArray does not have metaInfos");
        configuration.length=rootValue.length;
        let assignedFormGroupArray:FormArray=formGroupArray;
        if (formGroupArray.parent) {
            const parent=(formGroupArray.parent as FormGroup);
            parent.removeControl(configuration.name);
            assignedFormGroupArray=this.createFormArray(configuration);
            parent.addControl(configuration.name,assignedFormGroupArray);
        }else {
            for(let i:number=0;formGroupArray.length>0;i++) {
                formGroupArray.removeAt(0);
            }
            
            for(let i:number=0;i<configuration.length;i++) {
                const fg=this.createFormGroup(configuration);
                formGroupArray.push(fg,options);               
            }
        }
        //assignedFormGroupArray.patchValue(rootValue,options);
        for(let i:number=0;i<rootValue.length;i++){
            const entry=rootValue[i];
            this.setValue(entry,assignedFormGroupArray.at(i) as FormGroup);
        }
    }
    public getTransducedValue(formGroup:FormGroup):any {
        const ov:any={};
        const config:UIFormGroup=(formGroup as any)[FormGroupConfigurationService.CFG_DATA];
        const v=formGroup.value;
        if (v) {
            if (config?.controls) {
                config.controls.forEach(ctrl=>{
                    if (v[ctrl.controlName] && ctrl.customOutputTranslator) {
                        ov[ctrl.controlName]=ctrl.customOutputTranslator(v[ctrl.controlName]);
                    }else if (ctrl.type){
                        ov[ctrl.controlName]=DEFAULT_FIELD_TRANSLATORS[ctrl.type](v[ctrl.controlName]);
                    }
                });
            }
            if (config?.formArrays) {
                config.formArrays.forEach(fa=>{
                    const a:any[]=[];
                    const farray:FormArray=formGroup.controls[fa.name] as FormArray;
                    if (farray) {
                        for(let i:number=0;i<farray.length;i++) {
                            a.push(this.getTransducedValue(farray.at(i) as FormGroup));
                        }
                    }
                    ov[fa.name]=a;
                });
            }
            if (config?.formGroups) {
                config.formGroups.forEach(fg=>{
                    const fGroup:FormGroup= formGroup.controls[fg.name] as FormGroup;
                    ov[fg.name]=this.getTransducedValue(fGroup);
                });
            }
        }
        return ov;
    }
    
}