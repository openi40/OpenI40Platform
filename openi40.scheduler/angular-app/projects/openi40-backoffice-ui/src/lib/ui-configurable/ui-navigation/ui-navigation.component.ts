import { Component, Inject } from "@angular/core";
import { MegaMenu } from "primeng/megamenu";
import { UI_MENU } from "../ui-meta-description/ui-meta-description";
import { MegaMenuItem } from "primeng/api";

@Component({
    selector:"ui-navigation",
    templateUrl:"ui-navigation.component.html"
})
export class UINavigationComponent {
    constructor(@Inject(UI_MENU) public items:MegaMenuItem[]) {

    }
}