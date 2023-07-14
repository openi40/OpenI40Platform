import { Component } from '@angular/core';
import { MegaMenuItem } from 'primeng/api';
@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  items: MegaMenuItem[] = [{
    label: 'production configuration',
    icon: 'pi pi-fw pi-cog',
    items: [
      [{ label: "products", automationId: true, items: [{ label: "products", automationId: true, url: "products-search" }] },
      { label: "cycle/operations", automationId: true, items: [{ label: "cycles", automationId: true, url: "cycles-search" },{ label: "operations", automationId: true, url: "operations-search" }] }]
    ]
  }];
  title = 'backoffice-app';
}
