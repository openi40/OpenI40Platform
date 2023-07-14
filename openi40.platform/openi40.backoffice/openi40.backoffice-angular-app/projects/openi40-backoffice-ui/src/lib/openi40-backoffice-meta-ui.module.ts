import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {FormsModule} from '@angular/forms';
import { FormGroupConfigurationService } from './services/formgroup-configurator.service';
@NgModule ({
    imports:[CommonModule,FormsModule],
    providers:[FormGroupConfigurationService]
})
export class Openi40BackofficeMetaUIModule {}