import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import { FormGroupConfigurationService } from './services/formgroup-configurator.service';
import { UIConfigurationRepositoryService } from './services/ui-configurations-repository.service';
import { UIConfigurableControlComponent } from '../public-api';
import { SearchUIFormComponent } from './ui-configurable/search-ui-form/search-ui-form.component';
import { TableModule } from 'primeng/table';
@NgModule ({
    imports:[CommonModule,FormsModule,ReactiveFormsModule,TableModule],
    declarations:[UIConfigurableControlComponent,SearchUIFormComponent],
    exports:[UIConfigurableControlComponent,SearchUIFormComponent],
    providers:[FormGroupConfigurationService,UIConfigurationRepositoryService]
})
export class Openi40BackofficeMetaUIModule {}