import { UIControl } from "../ui-meta-description/ui-meta-description"
import {Input,Component} from '@angular/core';
@Component({
    selector:"ui-configurable-component",
    templateUrl:"ui-configurable-control.component.html",
    styleUrls:[]
})
export class UIConfigurableControlComponent {
     @Input() configuration?: UIControl;
}