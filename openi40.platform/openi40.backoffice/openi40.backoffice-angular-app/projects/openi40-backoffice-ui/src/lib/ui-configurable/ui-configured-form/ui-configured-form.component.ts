import { CommonModule } from "@angular/common";
import { Component, Input, OnChanges, OnInit, SimpleChanges } from "@angular/core";
import { FormGroup, FormsModule, ReactiveFormsModule } from "@angular/forms";
import { UI } from "../../ui-meta-description/ui-meta-description";

@Component({selector:"ui-configured-form",templateUrl:"ui-configured-form.component.html"})
export class UIConfiguredFormComponent implements OnInit,OnChanges{
    @Input() config?:UI;
    @Input() formGroup?:FormGroup;
    ngOnInit(): void {
        
    }
    ngOnChanges(changes: SimpleChanges): void {
        
    }
    
}