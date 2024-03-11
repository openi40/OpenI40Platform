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
import { DepartmentModule } from './department/department.module';
import { MachineModule } from './machine/machine.module';
import { OrdersModule } from './orders/orders.module';
import { PlantModule } from './plant/plant.module';
import { ProductiveCompanyModule } from './productiveCompany/productive-companies.module';
import { ProductModule } from './products/product.module';
import { ResourceGroupModule } from './resource-group/resource-group.module';
import { ResourceModule } from './resource/resource.module';
import { TaskModule } from './task/task.module';
import { TimesheetMetaInfoModule } from './timesheet-meta-info/timesheet-meta-info.module';
import { WarehouseModule } from './warehouse/warehouse.module';
import { WorkOrderModule } from './work-order/work-order.module';
import { WorkCenterModule } from './workcenters/workcenter.module';
import { OpenI40BackofficeHomeModule } from "./home/openi40-backoffice-home.module";
import { MegaMenuItem } from 'primeng/api';
import { UI_MENU } from '@openi40/backoffice-ui';
import { BASE_PATH } from '@openi40/backoffice-api';
import { getBaseUrl } from "@openi40/common-ui";
const BACKOFFICE_MENU_ITEMS: MegaMenuItem[] = [
  { label:"Home",icon:"pi pi-home",automationId:true,routerLink:"/"}
  ,{
  label: 'companies organization',
  icon: 'pi pi-fw pi-sitemap',
  items: [
    [
      {
        label: "companies/plants...", automationId: true, items: [
          { label: "companies", automationId: true, url: "productive-companies" },
          { label: "plants", automationId: true, url: "plants" },
          { label: "departments", automationId: true, url: "departments" },
          { label: "warehouses", automationId: true, url: "warehouses" },
          { label: "calendars", automationId: true, url: "timesheet-meta-infos" }
        ]
      }, {
        label: "shop floor", automationId: true, items: [
          { label: "workcenters", automationId: true, url: "workcenters" },
          { label: "machines", automationId: true, url: "machines" },
          { label: "resources group", automationId: true, url: "resource-groups" },
          { label: "resources", automationId: true, url: "resources" }
        ]
      }]
  ]
}, {
  label: 'items & items production cfg',
  icon: 'pi pi-fw pi-cog',
  items: [
    [{ label: "products", automationId: true, items: [{ label: "products", automationId: true, url: "products" }] },
    { label: "cycle/operations", automationId: true, items: [{ label: "cycles", automationId: true, url: "cycles-search" }, { label: "operations", automationId: true, url: "operations-search" }] }]
  ]
}, {
  label: 'sales cycle',
  icon: 'pi pi-fw pi-dollar',
  items: [
    [{
      label: "orders", automationId: true,
      items: [{ label: "sales orders", automationId: true, url: "sales-orders" }]
    }]
  ]
}, {
  label: 'purchase cycle',
  icon: 'pi pi-fw pi-shopping-cart',
  items: [
    [{
      label: "orders", automationId: true,
      items: [{ label: "purchase orders", automationId: true, url: "purchase-orders" }]
    }]
  ]
}, {
  label: 'production',
  icon: 'pi pi-fw pi-wrench',
  items: [
    [{
      label: "work orders/tasks", automationId: true,
      items: [{ label: "work orders", automationId: true, url: "workorders" }, { label: "production tasks", automationId: true, url: "tasks" }]
    }]
  ]
}];

@NgModule({
  imports: [CommonModule, ProductModule, OrdersModule, ProductiveCompanyModule, PlantModule, DepartmentModule, WarehouseModule, WorkCenterModule, MachineModule, ResourceGroupModule, ResourceModule, WorkOrderModule, TaskModule, TimesheetMetaInfoModule, OpenI40BackofficeHomeModule],
  exports: [],
  providers: [{ provide: UI_MENU, useValue: BACKOFFICE_MENU_ITEMS }, { provide: BASE_PATH, useFactory: getBaseUrl }]
})
export class OpenI40BackofficeScreensModule { }
