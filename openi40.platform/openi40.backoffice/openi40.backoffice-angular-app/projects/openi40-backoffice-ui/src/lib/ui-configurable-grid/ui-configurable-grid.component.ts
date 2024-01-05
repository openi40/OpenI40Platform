import { Component, EventEmitter, Input, Output } from "@angular/core";
import { AbstractGoToDetailService, PageMeta, UIResultColumn, UISearchForm } from "../ui-meta-description/ui-meta-description";
import { ActivatedRoute } from "@angular/router";

@Component({
    templateUrl: "ui-configurable-grid.component.html",
    selector: "ui-configurable-grid"
})
export class UIConfigurableGridComponent {
    @Input() public actualPage: PageMeta = new PageMeta();
    @Output() actualPageChange:EventEmitter<PageMeta>=new EventEmitter();
    @Input() public columnsConfig: UIResultColumn[] = [];
    @Input() public data: any[] = [];
    @Input() public gotoDetailService?: AbstractGoToDetailService;
    @Input() public searchFormConfig: UISearchForm<any, any> = { title: "", searchService: null, uniqueUiKey: "" };
    @Input() public paged: boolean = false;
    @Input() public externalPagingManagement:boolean=false;
    constructor(private activatedRoute: ActivatedRoute) { }
    goToDetail(rowData?: any) {
        this.gotoDetailService?.goToDetail(rowData, this.searchFormConfig, this, this.activatedRoute);
    }
    public handlePage(ev: { first: number, rows: number }) {

        const newPage: number = ev.first / this.actualPage.size;
        const newPageSize: number = ev.rows;
        this.actualPage = {
            page: newPage,
            size: newPageSize,
            totalElements: this.actualPage?.totalElements,
            order: this.actualPage.order
        };
        this.actualPageChange.emit(this.actualPage);
    }
}