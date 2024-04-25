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
import { Component, Input, OnInit } from '@angular/core';
import { SimulatedScenario } from '../openi40-material-simulator.service';

@Component({
  selector: 'openi40-material-scenario-serie',
  templateUrl: './openi40-material-scenario-serie.component.html',
  styleUrls: ['./openi40-material-scenario-serie.component.css']
})
export class Openi40MaterialScenarioSerieComponent implements OnInit {
  @Input("scenario") scenario:SimulatedScenario=null;
  data:any={
      labels:[],
      datasets:[]
  };
  dailyWarehouse:any={
    labels:[],
    datasets:[]
  };
  options:any = {
    title: {
        display: false,
        text: 'My Title',
        fontSize: 16
    },
    legend: {
        position: 'bottom'
    }
};
  public toggledFields:Map<string,boolean>=new Map();
  constructor() { }
  regenerateGraphic():void{
    this.data = {
      labels: [],
      datasets: [
        /*
          {
              label: 'First Dataset',
              data: [65, 59, 80, 81, 56, 55, 40]
          },
          {
              label: 'Second Dataset',
              data: [28, 48, 40, 19, 86, 27, 90]
          }*/
      ]
    };
    if (this.scenario) {
      let initialStock:boolean=this.getEnabled("initialStock");
      let finalStock:boolean=this.getEnabled("finalStock");
      let securityStock:boolean=this.getEnabled("securityStock");
      let purchaseSupplies:boolean=this.getEnabled("purchaseSupplies");
      let productionConsumption:boolean=this.getEnabled("productionConsumption");
      let productionSupplies:boolean=this.getEnabled("productionSupplies");
      let sales:boolean=this.getEnabled("sales");

      let initialStockValue=0;
      let _initialStockArray:number[]=[];
      let _securityStockArray:number[]=[];
      let _purchaseSuppliesArray:number[]=[];
      let _productionConsumptionArray:number[]=[];
      let _productionSuppliesArray:number[]=[];
      let _salesArray:number[]=[];
      let _finalStockArray:number[]=[];
      this.scenario.simulationItems.forEach((simulationItem)=>{
        this.data.labels.push(simulationItem.week.description);
        _initialStockArray.push(simulationItem.initialStock);
        _securityStockArray.push(simulationItem.securityStock);
        _productionConsumptionArray.push(simulationItem.productionConsumption);
        _productionSuppliesArray.push(simulationItem.productionQty);
        _purchaseSuppliesArray.push(simulationItem.purchaseQty);
        _finalStockArray.push(simulationItem.finalStock);
        _salesArray.push(simulationItem.salesQty);

      });
      if (initialStock===true){
        this.data.datasets.push({label:"initial inventory",data:_initialStockArray,fill: true,
        borderColor: '#4bc0c0',backgroundColor: '#ebb734'
        });
      }
      if (purchaseSupplies===true) {
        this.data.datasets.push({label: "purchases",data: _purchaseSuppliesArray,fill: true,
        borderColor: '#4bc0c0',backgroundColor: '#a5eb34'});
      }
      if (productionConsumption===true) {
        this.data.datasets.push({label: "consumption for production",data: _productionConsumptionArray,fill: true,
        borderColor: '#4bc0c0',backgroundColor: '#34ebc9'});
      }
      if (productionSupplies===true) {
        this.data.datasets.push({label: "production",data: _productionSuppliesArray,fill: true,
        borderColor: '#4bc0c0',backgroundColor: '#34a8eb'});
      }
      if (securityStock===true) {
        this.data.datasets.push({label: "security stock",data: _securityStockArray,fill: true,
        borderColor: '#4bc0c0',backgroundColor: '#c934eb'});
      }
      if (sales===true) {
        this.data.datasets.push({label: "sales",data: _salesArray,fill: true,
        borderColor: '#4bc0c0',backgroundColor: '#eb3477'});
      }
      if (finalStock===true){
        this.data.datasets.push({label:"final inventory",data:_finalStockArray,fill: true,
        borderColor: '#4bc0c0',backgroundColor: '#eb6234'});
      }
    }
    let _labels:string[]=[];
    let _inventoryEntries:number[]=[];
    this.scenario.dailyWarehouseGraph.inventoryDays.forEach((day)=>{
        _labels.push(day.time.toString());
        _inventoryEntries.push(day.inventoryQty);
    });
    this.dailyWarehouse={
      labels:_labels,
      datasets:[{label: "inventory",data: _inventoryEntries,fill: true,
      borderColor: '#4bc0c0',backgroundColor: '#a5eb34'}]
    };
  }
  ngOnInit(): void {
      this.regenerateGraphic();
  }
  public selectData(_evt){

  }
  public toggleField(name:string) {
    this.toggledFields.set(name,! (!this.toggledFields.has(name) ||  this.toggledFields.get(name)===true));
  }
  private getEnabled(name:string):boolean {
    return (!this.toggledFields.has(name)) || this.toggledFields.get(name)===true;
  }

}
