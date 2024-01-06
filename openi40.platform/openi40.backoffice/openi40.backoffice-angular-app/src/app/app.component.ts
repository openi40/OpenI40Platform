import { Component, OnInit } from '@angular/core';
import { MegaMenuItem } from 'primeng/api';
import { PrimeNGConfig } from 'primeng/api';
@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  constructor(private primengConfig: PrimeNGConfig) {

  }
  ngOnInit(): void {
    this.primengConfig.ripple = true;
  }
  items: MegaMenuItem[] = [{
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
  title = 'backoffice-app';
}
