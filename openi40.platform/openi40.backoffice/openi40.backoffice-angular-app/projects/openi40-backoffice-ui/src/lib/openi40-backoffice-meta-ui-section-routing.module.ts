import { CommonModule } from "@angular/common";
import { EnvironmentProviders, ModuleWithProviders, NgModule, Provider, Type } from "@angular/core";
import { RouterModule, Routes } from "@angular/router";
import { AbstractUICreateNewService, AbstractUIDeleteService, AbstractUIFindByCodeService, AbstractUIPagedSearchService, AbstractUISaveService, AbstractUISearchService, DefaultGoToDetailService, UIControl, UIDetailForm, UIResultColumn, UISearchForm, UI_DETAIL_CONFIG, UI_SEARCH_CONFIG } from "./ui-configurable/ui-meta-description/ui-meta-description";

@NgModule({
    imports: [CommonModule]
})
export class OpenI40BackofficeMetaUISectionRoutingModule {
    static getConfiguredBackendSection(mainSectionUriFragment: string, searchTitle: string,additionalSearchControls: UIControl[],additionalResultColumns: UIResultColumn[] , searchService: Type<AbstractUIPagedSearchService> | Type<AbstractUISearchService>, detailTitle: string,additionalEditControls: UIControl[], findByCodeService: Type<AbstractUIFindByCodeService<any>>,
        createNewService: Type<AbstractUICreateNewService<any>>, saveService: Type<AbstractUISaveService<any>>, deleteService: Type<AbstractUIDeleteService<any>>,additionalServicesProviders?:(Provider | EnvironmentProviders)[]): ModuleWithProviders<RouterModule> {
        const searchControls: UIControl[] = [{
            controlName: "code",
            label: "code",
            type: "text",
            required: false,
            containerCssClasses: "col-4"
        },
        {
            controlName: "description",
            label: "description",
            type: "text",
            required: false,
            containerCssClasses: "col-8"
        },...additionalSearchControls];
        const editControls: UIControl[] = [{
            controlName: "code",
            label: "code",
            type: "text",
            required: true,
            containerCssClasses: "col-4"
        },
        {
            controlName: "description",
            label: "description",
            type: "text",
            required: true,
            containerCssClasses: "col-8"
        },...additionalEditControls];
        const resultColumns: UIResultColumn[] = [{ field: "code", header: "code" }, { field: "description", header: "description" },...additionalResultColumns];
        const UI_SEARCH: UISearchForm<any, any> = {
            title: searchTitle,
            uniqueUiKey: mainSectionUriFragment + "-search",
            searchService: searchService,
            gotoDetailService: DefaultGoToDetailService,
            accessRights: [],
            resultColumns: resultColumns,
            formGroup: {
                name: mainSectionUriFragment+"_search_formgroup",
                controls: searchControls
            }
        };
        const UI_DETAIL: UIDetailForm<any> = {
            title: detailTitle,
            findByCodeService: findByCodeService,
            saveService: saveService,
            createNewService: createNewService,
            deleteService: deleteService,
            uniqueUiKey: mainSectionUriFragment + "-detail",
            formGroup: {
                name: mainSectionUriFragment+"_edit_formgroup",
                controls: editControls
            }
        }
        const moduleRoutes: Routes = [{
           
            path: mainSectionUriFragment,
            loadChildren: () => import("./openi40-backoffice-meta-ui-routing.module").then(m => m.OpenI40BackofficeMetaUIRoutingModule),
            providers: [{
                provide: UI_SEARCH_CONFIG,
                useValue: UI_SEARCH, multi: false
            }, {
                provide: UI_DETAIL_CONFIG,
                useValue: UI_DETAIL,
                multi: false
            },
                searchService, findByCodeService,
                createNewService, saveService, deleteService
            ]
        }];
        if (additionalServicesProviders && moduleRoutes[0].providers) {
            moduleRoutes[0].providers.push(additionalServicesProviders);
        }
        return RouterModule.forRoot(moduleRoutes);
    }
}