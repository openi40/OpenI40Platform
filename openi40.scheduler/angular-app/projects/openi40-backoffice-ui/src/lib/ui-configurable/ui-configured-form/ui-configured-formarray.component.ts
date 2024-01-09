import { Component, Input, OnChanges, OnInit, SimpleChanges } from "@angular/core";
import { FormArray, FormGroup } from "@angular/forms";
import { UIFormGroupArray } from "../ui-meta-description/ui-meta-description";
@Component({
    selector: "ui-configured-formarray",
    templateUrl: "ui-configured-formarray.component.html"
})
export class UIConfiguredFormArray implements OnInit, OnChanges {
    public formgroups: FormGroup[] = [];
    @Input() formArray?: FormArray<any>;
    @Input() config?: UIFormGroupArray;
    @Input() readonly:boolean=false;
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
    }
}