import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import { UIConfigurableControlComponent } from './ui-configurable-control.component';
@NgModule({
    imports:[CommonModule],
    declarations:[UIConfigurableControlComponent],
    exports:[UIConfigurableControlComponent]
})
export class UIConfigurableControlModule {}