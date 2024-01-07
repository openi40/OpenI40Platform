import { Component, EventEmitter, Input, OnChanges, OnInit, Output, SimpleChanges } from "@angular/core";
import { AbstractGoToDetailService, PageMeta, UIFormGroupArray, UIResultColumn, UISearchForm } from "../ui-meta-description/ui-meta-description";
import { ActivatedRoute } from "@angular/router";
import { FormArray, FormGroup } from "@angular/forms";

@Component({
    templateUrl: "ui-configurable-editable-grid.component.html",
    selector: "ui-configurable-editable-grid"
})
export class UIConfigurableEditableGridComponent implements OnInit, OnChanges {
    public columnsConfig: UIResultColumn[] = [];
    public formgroups: FormGroup[] = [];
    @Input() public formArrayConfig?: UIFormGroupArray;
    @Input() public formArray?: FormArray<any>;
    @Input() public readonly:boolean=false;
    constructor() { }
    ngOnInit(): void {

    }
    ngOnChanges(changes: SimpleChanges): void {
        if (changes["formArray"]) {
            const arr: FormGroup[] = [];
            if (this.formArray) {

                for (let i: number = 0; i < this.formArray.length; i++) {
                    this.formgroups.push(this.formArray.at(i) as FormGroup);
                }

            }
        }
        if (changes["formArrayConfig"] )  {
            const cCfg: UIResultColumn[] = [];
            if (this.formArrayConfig && this.formArrayConfig.controls) {
                this.formArrayConfig.controls.forEach(ctrl=>{
                    cCfg.push({
                        field: ctrl.controlName,
                        header: ctrl.label
                    });
                });
            }
        }
    }
    
}