import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
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
import { BreadcrumbModule } from 'primeng/breadcrumb';
import { MegaMenuModule } from 'primeng/megamenu';
import { FieldsetModule } from 'primeng/fieldset';
import { DetailUIFormComponent } from './ui-configurable/detail-ui-form/detail-ui-form.component';
import { UIConfiguredFormComponent } from './ui-configurable/ui-configured-form/ui-configured-form.component';
import { BlockUIModule } from 'primeng/blockui';
import { CalendarModule } from 'primeng/calendar';
import { UIConfigurableGridComponent } from './ui-configurable/ui-configurable-grid/ui-configurable-grid.component';
import { InputMaskModule } from 'primeng/inputmask';
import { UIConfiguredFormArray } from './ui-configurable/ui-configured-form/ui-configured-formarray.component';
import { UIConfigurableEditableGridComponent } from './ui-configurable/ui-configurable-grid/ui-configurable-editable-grid.component';
import { AccordionModule } from 'primeng/accordion';
import { UIEntitesRelatedComponent } from './ui-configurable/ui-entities-related/ui-entities-related.component';
import { ContextMenuModule } from 'primeng/contextmenu';
import { UINavigationComponent } from './ui-configurable/ui-navigation/ui-navigation.component';
@NgModule({
    imports: [CommonModule,  FormsModule, ReactiveFormsModule, TableModule, RouterModule, InputTextModule, DropdownModule, ButtonModule, AutoCompleteModule, CheckboxModule, CalendarModule, BlockUIModule, InputMaskModule,AccordionModule,ContextMenuModule,BreadcrumbModule,MegaMenuModule,FieldsetModule],
    declarations: [UIConfigurableControlComponent, SearchUIFormComponent, DetailUIFormComponent, UIConfiguredFormComponent, UIConfigurableGridComponent,UIConfigurableEditableGridComponent,UIConfiguredFormArray,UIEntitesRelatedComponent,UINavigationComponent],
    exports: [UIConfigurableControlComponent, SearchUIFormComponent, DetailUIFormComponent, UIConfiguredFormComponent, UIConfigurableGridComponent,UIConfigurableEditableGridComponent,UIConfiguredFormArray,UIEntitesRelatedComponent,UINavigationComponent],
    providers: [FormGroupConfigurationService, DefaultGoToDetailService]
})
export class Openi40BackofficeMetaUIModule { }
