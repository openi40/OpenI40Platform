import { ControlValueAccessor, FormControl, NG_VALUE_ACCESSOR } from "@angular/forms";
import { UIControl } from "../ui-meta-description/ui-meta-description"
import { Input, Component, forwardRef, OnInit, OnChanges, SimpleChanges, Injector } from '@angular/core';
@Component({
    selector: "ui-configurable-component",
    templateUrl: "ui-configurable-control.component.html",
    styleUrls: [],
    providers: [
        {
            provide: NG_VALUE_ACCESSOR,
            useExisting: forwardRef(() => UIConfigurableControlComponent),
            multi: true
        }
    ]
})
export class UIConfigurableControlComponent implements ControlValueAccessor,OnInit,OnChanges{
    public disabled:boolean=false;
    public internalFormControl=new FormControl();
    public choices:any[]=[];
    
    public get optionLabel():string {
        return this.configuration?.mappings?.label?this.configuration?.mappings?.label:'';
    }
    constructor(private injector:Injector){

    }
    @Input() public configuration?: UIControl;
    writeValue(obj: any): void {
        this.internalFormControl.setValue(obj,{onlySelf:true,emitEvent:false});
    }
    onChange:(v:any)=>void=(v:any)=>{};
    registerOnChange(fn: any): void {
        this.onChange=fn;
    }
    onTouch:(v:any)=>void=(v:any)=>{};
    registerOnTouched(fn: any): void {
        this.onTouch=fn;
    }
    setDisabledState?(isDisabled: boolean): void {
        this.disabled=isDisabled;
    }
    ngOnInit(): void {

        this.internalFormControl.valueChanges.subscribe(newValue=>{
            this.onChange(newValue);
        });
    }
    ngOnChanges(changes: SimpleChanges): void {
        if (changes["configuration"] && this.configuration) {
            if (this.configuration.values) {
                this.choices=this.configuration.values;
            }
            if (this.configuration.populationService) {

            }
        }
    }
    
}