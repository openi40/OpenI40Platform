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
import { AutoCompleteModule } from 'primeng/autocomplete';
import { DetailUIFormComponent } from './ui-configurable/detail-ui-form/detail-ui-form.component';
import { UIConfiguredFormComponent } from './ui-configurable/ui-configured-form/ui-configured-form.component';
@NgModule ({
    imports:[CommonModule,FormsModule,ReactiveFormsModule,TableModule,RouterModule,InputTextModule,DropdownModule,ButtonModule,AutoCompleteModule],
    declarations:[UIConfigurableControlComponent,SearchUIFormComponent,DetailUIFormComponent,UIConfiguredFormComponent],
    exports:[UIConfigurableControlComponent,SearchUIFormComponent,DetailUIFormComponent,UIConfiguredFormComponent],
    providers:[FormGroupConfigurationService,DefaultGoToDetailService]
})
export class Openi40BackofficeMetaUIModule {}