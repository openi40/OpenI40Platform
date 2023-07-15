import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import { FormGroupConfigurationService } from './services/formgroup-configurator.service';
import { UIConfigurationRepositoryService } from './services/ui-configurations-repository.service';
import { DefaultGoToDetailService, UIConfigurableControlComponent } from '../public-api';
import { SearchUIFormComponent } from './ui-configurable/search-ui-form/search-ui-form.component';
import { TableModule } from 'primeng/table';
import { RouterModule } from '@angular/router';
import { InputTextModule } from 'primeng/inputtext';
import { DropdownModule } from 'primeng/dropdown';
import { ButtonModule } from 'primeng/button';
@NgModule ({
    imports:[CommonModule,FormsModule,ReactiveFormsModule,TableModule,RouterModule,InputTextModule,DropdownModule,ButtonModule],
    declarations:[UIConfigurableControlComponent,SearchUIFormComponent],
    exports:[UIConfigurableControlComponent,SearchUIFormComponent],
    providers:[FormGroupConfigurationService,UIConfigurationRepositoryService,DefaultGoToDetailService]
})
export class Openi40BackofficeMetaUIModule {}