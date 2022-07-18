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
import { ChangeDetectorRef, Component, EventEmitter, Input, OnInit } from '@angular/core';
import { ApsDataDto, PlantDto, ProductiveCompanyDto, TaskDto, WorkOrderDto } from 'projects/openi40-scheduler-api/src/lib';
export class TaskDataItem {
    public data:TaskDto=null;
    public plant:PlantDto=null;
    public company:ProductiveCompanyDto=null;
    public workOrder:WorkOrderDto=null;

    public get errorNr():number{
      let _errors=0;
      if (this.data){
        if (this.data.messages){
          this.data.messages.forEach((msg)=>{
            if (msg.messageCategory=='ERROR'){
              _errors++;
            }
          })
        }
      }
      return _errors;
    }
    public get warningsNr():number{
      let _errors=0;
      if (this.data){
        if (this.data.messages){
          this.data.messages.forEach((msg)=>{
            if (msg.messageCategory=='WARNING'){
              _errors++;
            }
          })
        }
      }
      return _errors;
    }
    public get id():string
    {return this.data.id;}
    public get code():string
    {return this.data.code;}
    public get workOrderCode():string
    {return this.data.workOrderCode;}
    public get description():string {
      return this.data.description;
    }
    public get sequenceCode():string {
      return this.data.sequenceCode;
    }

    public get scheduledMachineCode():string {
      return this.data.scheduledMachineCode;
    }
    public get successfullyScheduled():boolean{
      return this.data.successfullyScheduled;
    }
    public get workCenterCode():string
    {return this.data.workCenterCode;}
    public get productiveCompanyCode():string {
      return this.company.code;
    }
    public get setupStartDateTime():Date {
      return this.data.setup?this.data.setup.startDateTime:null;
    }
    public get setupEndDateTime():Date {
      return this.data.setup?this.data.setup.endDateTime:null;
    }
    public get workStartDateTime():Date {
      return this.data.work?this.data.work.startDateTime:null;
    }
    public get workEndDateTime():Date {
      return this.data.work?this.data.work.endDateTime:null;
    }

}
@Component({
  selector: 'openi40-tasks-datatable',
  templateUrl: './openi40-tasks-datatable.component.html',
  styleUrls: ['./openi40-tasks-datatable.component.css']
})

export class Openi40TasksDatatableComponent implements OnInit {
  protected _apsData:ApsDataDto=null;
  @Input("apsData") set apsData(d:ApsDataDto){
    this._apsData=d;
    this.loadData(d);
  }
  get apsData():ApsDataDto{
    return this._apsData;
  }
  @Input("taskClick")  taskClick:EventEmitter<{data?:TaskDto}>=new EventEmitter();
  loading: boolean = true;
  constructor(private changeDetector: ChangeDetectorRef) { }
  public selectedTasks:TaskDataItem[]=[];
  protected _data:TaskDataItem[]=[];
  cols: any[];
  exportColumns: any[];
  public get data():TaskDataItem[] {
    return this._data;
  }
  ngOnInit(): void {
    this.loading=false;
    this.cols = [
      { field: 'code', header: 'Code' },
      { field: 'description', header: 'Description' },
      { field: 'workOrderCode', header: 'Work order' },
      { field: 'sequenceCode', header: 'Sequence code' },
      { field: 'workCenterCode', header: 'Work center' },
      { field: 'scheduledMachineCode', header: 'Machine' },
      { field: 'setupStartDateTime', header: 'Setup start time' },
      { field: 'setupEndDateTime', header: 'Setup end time' },
      { field: 'workStartDateTime', header: 'Working start time' },
      { field: 'workEndDateTime', header: 'Working end time' }

    ];
    this.exportColumns = this.cols.map(col => ({title: col.header, dataKey: col.field}));
  }
  /*exportPdf() {
    import("jspdf").then(jsPDF => {
        import("jspdf-autotable").then(x => {
            const doc = new jsPDF.default(0,0);
            doc.autoTable(this.exportColumns, this._data);
            doc.save('products.pdf');
        })
    })
}*/

exportExcel() {
    import("xlsx").then(xlsx => {
        const worksheet = xlsx.utils.json_to_sheet(this._data);
        const workbook = { Sheets: { 'data': worksheet }, SheetNames: ['data'] };
        const excelBuffer: any = xlsx.write(workbook, { bookType: 'xlsx', type: 'array' });
        this.saveAsExcelFile(excelBuffer, "products");
    });
}

saveAsExcelFile(buffer: any, fileName: string): void {
    import("file-saver").then(FileSaver => {
        let EXCEL_TYPE = 'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=UTF-8';
        let EXCEL_EXTENSION = '.xlsx';
        const data: Blob = new Blob([buffer], {
            type: EXCEL_TYPE
        });
        FileSaver.saveAs(data, fileName + '_export_' + new Date().getTime() + EXCEL_EXTENSION);
    });
}
   loadData(d:ApsDataDto):void {
     this._data=[];
    if (d.productiveCompanies){
      d.productiveCompanies.forEach((pc)=>{
        if (pc.plants){
          pc.plants.forEach((plant)=>{
            if (plant.workOrders){
              plant.workOrders.forEach((wo)=>{
                if (wo.tasks){
                  wo.tasks.forEach((task)=>{
                    let item:TaskDataItem=new TaskDataItem();
                    item.data=task;
                    item.company=pc;
                    item.plant=plant;
                    item.workOrder=wo;
                    this._data.push(item);
                  })
                }
              })
            }
          })
        }
      })
    }
  }
  selectedItem:TaskDataItem=null;
  public onClicked(item) {
    this.selectedItem=item;
    this.changeDetector.markForCheck();
  }
}
