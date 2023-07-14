
import { OnInit, Injector, Component } from "@angular/core";
import { BaseUIForm } from "../base-ui-form/base-ui-form.component";
import { UIResultColumn, UISearchForm } from "../../ui-meta-description/ui-meta-description";
@Component({
    selector:"search-ui-form",
    templateUrl:"search-ui-form.component.html",
    styleUrls:[]
})
export class SearchUIFormComponent<SearchType,ResultType> extends BaseUIForm<UISearchForm<SearchType,ResultType>> {
        public results:ResultType[]=[];
        public override ngOnInit(): void {
            super.ngOnInit();

        }
        public get columnsConfig():UIResultColumn[]{
            if (this.config?.resultColumns) {
                return this.config.resultColumns;
            }else 
                return [];
        }
}