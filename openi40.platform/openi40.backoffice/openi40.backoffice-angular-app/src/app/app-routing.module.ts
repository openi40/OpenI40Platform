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
import { RouterModule } from '@angular/router';
import { DepartmentModule } from '@openi40/backoffice-screens-dynaload/department/department.module';
import { MachineModule } from '@openi40/backoffice-screens-dynaload/machine/machine.module';
import { OrdersModule } from '@openi40/backoffice-screens-dynaload/orders/orders.module';
import { PlantModule } from '@openi40/backoffice-screens-dynaload/plant/plant.module';
import { ProductiveCompanyModule } from '@openi40/backoffice-screens-dynaload/productiveCompany/productive-companies.module';
import { ProductModule } from '@openi40/backoffice-screens-dynaload/products/product.module';
import { ResourceGroupModule } from '@openi40/backoffice-screens-dynaload/resource-group/resource-group.module';
import { ResourceModule } from '@openi40/backoffice-screens-dynaload/resource/resource.module';
import { TaskModule } from '@openi40/backoffice-screens-dynaload/task/task.module';
import { WarehouseModule } from '@openi40/backoffice-screens-dynaload/warehouse/warehouse.module';
import { WorkOrderModule } from '@openi40/backoffice-screens-dynaload/work-order/work-order.module';
import { WorkCenterModule } from '@openi40/backoffice-screens-dynaload/workcenters/workcenter.module';



@NgModule({
  imports: [CommonModule,ProductModule,OrdersModule,ProductiveCompanyModule,PlantModule,DepartmentModule,WarehouseModule,WorkCenterModule,MachineModule,ResourceGroupModule,ResourceModule,WorkOrderModule,TaskModule],
  exports: [RouterModule]
})
export class AppRoutingModule { }
