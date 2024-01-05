import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import { FormGroupConfigurationService } from './services/formgroup-configurator.service';

import { DefaultGoToDetailService, UIConfigurableControlComponent } from '../public-api';
import { SearchUIFormComponent } from './ui-configurable/search-ui-form/search-ui-form.component';
import { TableModule } from 'primeng/table';
import { RouterModule } from '@angular/router';
import { InputTextModule } from 'primeng/inputtext';
import { DropdownModule } from 'primeng/dropdown';
import { ButtonModule } from 'primeng/button';
import { CheckboxModule } from 'primeng/checkbox';
import { AutoCompleteModule } from 'primeng/autocomplete';
import { DetailUIFormComponent } from './ui-configurable/detail-ui-form/detail-ui-form.component';
import { UIConfiguredFormComponent } from './ui-configurable/ui-configured-form/ui-configured-form.component';
import { BlockUIModule } from 'primeng/blockui';
import { CalendarModule } from 'primeng/calendar';
import { UIConfigurableGridComponent } from './ui-configurable-grid/ui-configurable-grid.component';
@NgModule ({
    imports:[CommonModule,FormsModule,ReactiveFormsModule,TableModule,RouterModule,InputTextModule,DropdownModule,ButtonModule,AutoCompleteModule,CheckboxModule,CalendarModule,BlockUIModule],
    declarations:[UIConfigurableControlComponent,SearchUIFormComponent,DetailUIFormComponent,UIConfiguredFormComponent,UIConfigurableGridComponent],
    exports:[UIConfigurableControlComponent,SearchUIFormComponent,DetailUIFormComponent,UIConfiguredFormComponent,UIConfigurableGridComponent],
    providers:[FormGroupConfigurationService,DefaultGoToDetailService]
})
export class Openi40BackofficeMetaUIModule {}