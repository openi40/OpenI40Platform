import { CommonModule } from "@angular/common";
import { ModuleWithProviders, NgModule } from "@angular/core";
import { Openi40BackofficeMetaUIModule } from "./openi40-backoffice-meta-ui.module";
import { RouterModule, Routes } from "@angular/router";
import { SearchUIFormComponent } from "./ui-configurable/search-ui-form/search-ui-form.component";
import { DetailUIFormComponent } from "./ui-configurable/detail-ui-form/detail-ui-form.component";
const routes: Routes = [
    {
        pathMatch:"full",
        path: '',
        component: SearchUIFormComponent,
        children: [
            {
                path: ':code/edit',
                component: DetailUIFormComponent
            }, {
                path: 'new',
                component: DetailUIFormComponent
            }

        ]
    },
    {
        path: ':code/edit',
        component: DetailUIFormComponent
    }, {
        path: 'new',
        component: DetailUIFormComponent
    }
];
@NgModule({
    imports: [CommonModule, Openi40BackofficeMetaUIModule, RouterModule.forChild(routes)]

})
export class OpenI40BackofficeMetaUIRoutingModule { 
    
}