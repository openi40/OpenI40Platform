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
import {NgModule} from '@angular/core'
import { CommonModule } from '@angular/common';
import {DropdownModule} from 'primeng/dropdown';
import {OrderListModule} from 'primeng/orderlist';
import {PickListModule} from 'primeng/picklist';
import {AccordionModule} from 'primeng/accordion';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { ButtonModule } from 'primeng/button';
import { DialogModule } from 'primeng/dialog';
import { PanelModule } from 'primeng/panel';
import { SelectButtonModule } from 'primeng/selectbutton';
import { TabViewModule } from 'primeng/tabview';
import { CardModule, } from 'primeng/card';
import { FieldsetModule } from 'primeng/fieldset';
import {Openi40SchedulingSettingsWizardComponent} from './openi40-scheduling-settings-wizard.component'

@NgModule ({
  declarations:[Openi40SchedulingSettingsWizardComponent],
  imports:[CommonModule,FormsModule,ReactiveFormsModule, DropdownModule,OrderListModule,TabViewModule,SelectButtonModule,AccordionModule,ButtonModule,PickListModule,DialogModule,CardModule,FieldsetModule],
  exports:[Openi40SchedulingSettingsWizardComponent]
})
export class Openi40SchedulingsSettingsWizardModule {

}
