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

    @Input() public formArrayConfig?: UIFormGroupArray;
    @Input() public formArray?: FormArray<any>;
    @Input() public readonly: boolean = false;
    constructor() { }
    ngOnInit(): void {

    }
    public formgroups: FormGroup[] = [];
    ngOnChanges(changes: SimpleChanges): void {
         if (changes["formArray"]) {
              const arr: FormGroup[] = [];
              if (this.formArray) {
  
                  for (let i: number = 0; i < this.formArray.length; i++) {
                      arr.push(this.formArray.at(i) as FormGroup);
                  }
  
              }
              this.formgroups = arr;
          }
        if (changes["formArrayConfig"]) {
            const cCfg: UIResultColumn[] = [];
            if (this.formArrayConfig && this.formArrayConfig.controls) {
                this.formArrayConfig.controls.forEach(ctrl => {
                    cCfg.push({
                        field: ctrl.controlName,
                        header: ctrl.label
                    });
                });
            }
            this.columnsConfig = cCfg;
        }
    }
    

}