import { AbstractControl, ControlValueAccessor, FormControl, FormGroup, NG_VALUE_ACCESSOR } from "@angular/forms";
import { AbstractUIDataLoaderService, AbstractUIPagedSearchService, AbstractUISearchService, Page, PageMeta, UIControl } from "../ui-meta-description/ui-meta-description"
import { Input, Component, forwardRef, OnInit, OnChanges, SimpleChanges, Injector } from '@angular/core';
const NULLCONTROL:UIControl={
    controlName:"",
    type:"hidden",
    label:"",
    placeholder:""
};
interface IEvent {
    originalEvent?: {
        isTrusted: boolean
    },
    query?: string
};
@Component({
    selector: "ui-configurable-component",
    templateUrl: "ui-configurable-control.component.html",
    styleUrls: []
})
export class UIConfigurableControlComponent implements OnInit, OnChanges {
    @Input() public configuration: UIControl=NULLCONTROL;
    @Input() public formGroup?:FormGroup;
    public disabled: boolean = false;
    @Input() public readonly: boolean = false;
    public loading: boolean = false;
    public choices: any[] = [];
    public currentPage: PageMeta = { page: 0, size:30, totalElements: 0 };
    populationService?: AbstractUISearchService | AbstractUIPagedSearchService | AbstractUIDataLoaderService;
    public get hasPopulationService(): boolean {
        return this.populationService ? true : false;
    }
    public get hasUnpagedSearchService(): boolean {
        return (this.populationService && this.populationService instanceof AbstractUISearchService) ? true : false;
    }
    public get hasPagedSearchService(): boolean {
        return (this.populationService && this.populationService instanceof AbstractUIPagedSearchService) ? true : false;
    }
    public get hasDataLoaderService(): boolean {
        return (this.populationService && this.populationService instanceof AbstractUIDataLoaderService) ? true : false;
    }
    public get noSearchService():boolean {
        return this.populationService?false:true;
    }
    public get optionLabel(): string {
        return this.configuration?.mappings?.label ? this.configuration?.mappings?.label : '';
    }
    constructor(private injector: Injector) {

    }
    private alertUser(msg: string) {

    }

    ngOnInit(): void {

    }
    public completeUnpagedSearch(filter:IEvent) {
        if (filter.query !== undefined)
        this.doAutocomplete(filter?.query);
    }
    public completePagedSearch(filter:IEvent) {
        if (filter.query  !== undefined)
        this.doAutocomplete(filter?.query);
    }
    private doAutocomplete(query?: any) {
        if (this.hasUnpagedSearchService) {
            const unpagedService: AbstractUISearchService = this.populationService as AbstractUISearchService;
            this.loading = true;
            unpagedService.search(query).subscribe({
                next: (value: any[]) => {
                    this.choices = value;
                }, error: (error) => {
                    this.alertUser("Error loading data from server");
                    console.log("Error loading data from server", error);
                }, complete: () => {
                    this.loading = false;
                }
            }
            );
        } else if (this.hasPagedSearchService) {
            const unpagedService: AbstractUIPagedSearchService = this.populationService as AbstractUIPagedSearchService;
            this.loading = true;
            unpagedService.searchPaged(query,this.currentPage).subscribe({
                next: (value: Page<any[]>) => {
                    this.choices = value.data;
                    this.currentPage.page=value?.page;
                    this.currentPage.size=value?.size;
                    this.currentPage.totalElements=value?.totalElements;
                }, error: (error) => {
                    this.alertUser("Error loading data from server");
                    console.log("Error loading data from server", error);
                }, complete: () => {
                    this.loading = false;
                }
            }
            );
        }
    }
    ngOnChanges(changes: SimpleChanges): void {
        if (changes["configuration"] && this.configuration) {
            if (this.configuration.values) {
                this.choices = this.configuration.values;
            }
            if (this.configuration.populationService) {
                this.populationService = this.injector.get(this.configuration.populationService);
                if (this.hasDataLoaderService) {
                    this.loading = true;
                    const dataLoader: AbstractUIDataLoaderService = this.populationService as AbstractUIDataLoaderService;
                    dataLoader.load().subscribe({
                        next: (value: any[]) => {
                            this.choices = value ? value : [];
                        },
                        error: (error) => {
                            this.alertUser("Error loading data from server");
                            console.log("Error loading data from server", error);
                        },
                        complete: () => {
                            this.loading = false;
                        }
                    });
                }
            }
        }
    }

}