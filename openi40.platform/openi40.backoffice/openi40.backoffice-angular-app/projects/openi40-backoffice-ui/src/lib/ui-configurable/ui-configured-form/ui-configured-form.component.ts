import { CommonModule } from "@angular/common";
import { Component, Input, OnChanges, OnInit, SimpleChanges, TemplateRef } from "@angular/core";
import { FormGroup,FormArray, FormsModule, ReactiveFormsModule } from "@angular/forms";
import { UI, UIFormGroup, UIFormGroupArray } from "../../ui-meta-description/ui-meta-description";

@Component({selector:"ui-configured-form",templateUrl:"ui-configured-form.component.html"})
export class UIConfiguredFormComponent implements OnInit,OnChanges{
    @Input() config?:UIFormGroup;
    @Input() formGroup?:FormGroup;
    @Input() formTemplate?: TemplateRef<any>=undefined;
    ngOnInit(): void {
        
    }
    ngOnChanges(changes: SimpleChanges): void {
        
    }
    getSubFormGroup(cfg:UIFormGroup):FormGroup {
        return this.formGroup?.get(cfg.name) as FormGroup;
    }
    getSubFormArray(cfg:UIFormGroupArray):FormArray {
        return this.formGroup?.get(cfg.name) as FormArray;
    }
}