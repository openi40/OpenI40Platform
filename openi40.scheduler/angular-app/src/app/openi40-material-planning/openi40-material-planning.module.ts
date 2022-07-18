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
import { NgModule } from '@angular/core';
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
import { TagModule } from 'primeng/tag';
import { Openi40MaterialPlanningComponent } from '../openi40-material-planning/openi40-material-planning.component';
import {Openi40MaterialProductPlanningModule} from '../openi40-material-product-planning/openi40-material-product-planning.module'
@NgModule({
  imports:[CommonModule,FormsModule,ReactiveFormsModule, DropdownModule,OrderListModule,TabViewModule,SelectButtonModule,AccordionModule,ButtonModule,PickListModule,DialogModule,CardModule,TagModule,FieldsetModule,PanelModule,Openi40MaterialProductPlanningModule],
  declarations:[Openi40MaterialPlanningComponent],
  exports:[Openi40MaterialPlanningComponent]
})
export class Openi40MaterialPlanningModule {

}
