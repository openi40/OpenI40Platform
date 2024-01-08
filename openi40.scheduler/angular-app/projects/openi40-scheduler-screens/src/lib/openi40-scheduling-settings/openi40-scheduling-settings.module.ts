/**
 *
 * This code is part of the OpenI40 open source advanced production scheduler
 * platform suite, have look to its licencing options.
 * Web site: http://openi40.org/
 * Github: https://github.com/openi40/OpenI40Platform
 * We hope you enjoy implementing new amazing projects with it.
 * @author architectures@openi40.org
 *
 */
import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { ButtonModule } from 'primeng/button';
import { DialogModule } from 'primeng/dialog';
import { PanelModule } from 'primeng/panel';
import { BrowserModule } from "@angular/platform-browser";
import { BrowserAnimationsModule } from "@angular/platform-browser/animations";

import { TabViewModule } from 'primeng/tabview';
import {Openi40SchedulingSettingsComponent} from './openi40-scheduling-settings.component'
import {DropdownModule} from 'primeng/dropdown';
import {OrderListModule} from 'primeng/orderlist';
import {PickListModule} from 'primeng/picklist';
import {FormsModule, ReactiveFormsModule} from "@angular/forms"
import {SelectButtonModule} from 'primeng/selectbutton';
import {AccordionModule} from 'primeng/accordion';
import { CardModule } from 'primeng/card';
import { TagModule } from 'primeng/tag';
import {FieldsetModule} from 'primeng/fieldset';
import {Openi40SchedulingSettingsPageComponent} from './openi40-scheduling-settings-page.component'
import { Openi40SchedulingsSettingsWizardModule } from '../openi40-scheduling-settings-wizard/openi40-scheduling-settings-wizard.module';
@NgModule({
    imports:[CommonModule,FormsModule,ReactiveFormsModule, DropdownModule,OrderListModule,TabViewModule,SelectButtonModule,AccordionModule,ButtonModule,PickListModule,DialogModule,CardModule,TagModule,FieldsetModule,Openi40SchedulingsSettingsWizardModule],
    declarations:[Openi40SchedulingSettingsComponent,Openi40SchedulingSettingsPageComponent],
    exports:[Openi40SchedulingSettingsComponent,Openi40SchedulingSettingsPageComponent]
})
export class Openi40SchedulingSettingsModule{

}
