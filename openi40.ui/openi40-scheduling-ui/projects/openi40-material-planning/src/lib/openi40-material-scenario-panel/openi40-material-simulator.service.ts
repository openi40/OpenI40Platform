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
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import {Week,PurchaseSupplyDto,ProductionSupplyDto,StockSupplyDto,SalesOrderLineDto,MaterialPlanningDto, SupplyReservationDto, DailyWarehouseGraph} from '../../../../openi40-scheduler-api/src/lib'

export class MaterialWeeklySimulationEntry {
  public purchaseQty:number=0;
  public week:Week=null;
  public warehouseCode:string=null;
  public initialStock:number=0;
  public finalStock:number=0;
  public securityStock:number=0;
  public productionQty:number=0;
  public salesQty:number=0;
  public productionConsumption:number=0;
  public purchasesQty:number=0;
  public simulatedPurchaseQty:number=0;
  public purchases:PurchaseSupplyDto[]=[];
  public productions:ProductionSupplyDto[]=[];
  public sales:SalesOrderLineDto[]=[];
  public productionConsumptions: SupplyReservationDto[]=[];
  public stockInitialPosition:StockSupplyDto={qtyTotal:0,qtyAvailable:0,qtyReserved:0};

}
export class SimulatedScenario{
  public description:string=null;
  public stockRotation:number=0;
  public avgStock:number=0;
  public avgConsumption:number=0;
  public ivConsumption:number=0;
  public dailyWarehouseGraph:DailyWarehouseGraph=null;
  public simulationItems:MaterialWeeklySimulationEntry[]=[];
}

@Injectable()
export class Openi40MaterialSimulatorService {
  loadSimulatedScenarios(materialPlanningData:MaterialPlanningDto):SimulatedScenario[]{
    let _outValues:SimulatedScenario[]=[];
    if (materialPlanningData && materialPlanningData.warehousesAnalisys) {
      materialPlanningData.warehousesAnalisys.forEach((warehouseAnalisys)=>{
        let thisScenario:SimulatedScenario=new SimulatedScenario();
        thisScenario.description="Warehouse "+warehouseAnalisys.warehouseCode;
        let productionMap=this.weeklyMap(warehouseAnalisys.productionSupplies);
        let purchaseMap=this.weeklyMap(warehouseAnalisys.purchaseSupplies);
        let reservationsMap=this.reservationsWeeklyMap(warehouseAnalisys.productionConsumptions);
        let salesOrderLinesMap=this.deliveryWeeklyMap(warehouseAnalisys.salesOrderLines);

        materialPlanningData.analizedWeeks.forEach(week=>{
          let _entry:MaterialWeeklySimulationEntry=new MaterialWeeklySimulationEntry();
          _entry.week=week;
          if (thisScenario.simulationItems.length==0 && warehouseAnalisys.stockSupply) {
            _entry.stockInitialPosition=warehouseAnalisys.stockSupply;
          }
          _entry.productions=this.getOrEmpty(week.period,productionMap);
          _entry.purchases=this.getOrEmpty(week.period,purchaseMap);
          _entry.sales=this.getOrEmpty(week.period,salesOrderLinesMap);
          _entry.productionConsumptions=this.getOrEmpty(week.period,reservationsMap);
          thisScenario.simulationItems.push(_entry);
          thisScenario.dailyWarehouseGraph=warehouseAnalisys.dailyGraph;
        });
        _outValues.push(thisScenario);
        this.calculateFigures(thisScenario);

      });
    }
    return _outValues;
  }
  private calculateFigures(simulatedScenario:SimulatedScenario){
     let cumulativeStock:number=0;
     let cumulativeConsumption:number=0;
      simulatedScenario.simulationItems.forEach((weeklyScenary:MaterialWeeklySimulationEntry,index:number)=>{
        weeklyScenary.salesQty=0;
        if (weeklyScenary.sales){
          weeklyScenary.initialStock=weeklyScenary.stockInitialPosition?weeklyScenary.stockInitialPosition.qtyTotal:0;
          weeklyScenary.sales.forEach((s)=>{
            weeklyScenary.salesQty+=s.completedQty;
          });
        }
        weeklyScenary.productionQty=0;
        if (weeklyScenary.productions) {
          weeklyScenary.productions.forEach((p)=>{
            weeklyScenary.productionQty+=p.qtyTotal;
          });
        }
        weeklyScenary.purchaseQty=0;
        if (weeklyScenary.purchases){
          weeklyScenary.purchases.forEach((p)=>{
            weeklyScenary.purchaseQty+=p.qtyTotal;
          });
        }
        weeklyScenary.salesQty=0;
        if (weeklyScenary.sales) {
          weeklyScenary.sales.forEach((p)=>{
            weeklyScenary.salesQty+=p.totalQty;
          });
        }
        weeklyScenary.productionConsumption=0;
        if (weeklyScenary.productionConsumptions) {
          weeklyScenary.productionConsumptions.forEach((pc)=>{
            weeklyScenary.productionConsumption+=pc.qtyProvided;
          });
        }
        weeklyScenary.simulatedPurchaseQty=0;
        cumulativeConsumption=(weeklyScenary.productionConsumption+weeklyScenary.salesQty);
        weeklyScenary.finalStock=weeklyScenary.initialStock+weeklyScenary.productionQty+weeklyScenary.purchaseQty-(weeklyScenary.productionConsumption+weeklyScenary.salesQty);
        cumulativeStock+=((weeklyScenary.finalStock+weeklyScenary.initialStock)/2);
        if (index< simulatedScenario.simulationItems.length-1) {
          simulatedScenario.simulationItems[index+1].stockInitialPosition={qtyTotal:weeklyScenary.finalStock};
        }
      });
      simulatedScenario.avgStock=simulatedScenario.simulationItems.length>0?cumulativeStock/simulatedScenario.simulationItems.length:0;
      simulatedScenario.stockRotation=simulatedScenario.avgStock>0?cumulativeConsumption/simulatedScenario.avgStock:0;
      simulatedScenario.avgConsumption=simulatedScenario.simulationItems.length>0?cumulativeConsumption/simulatedScenario.simulationItems.length:0

  }
  private  getOrEmpty<T>(week:number,map:Map<number,T[]>):T[]{
      let _out:T[]=map.has(week)?map.get(week):[];
      return _out;
  }
  private  weeklyMap<T extends {availabilityWeek?:number}>(entries:T[] ):Map<number,T[]>{
    let entriesMap:Map<number,T[]>=new Map();
    if (entries){
      entries.forEach((ps)=>{
        if (ps.availabilityWeek) {
          if (!entriesMap.has(ps.availabilityWeek)) {
            entriesMap.set(ps.availabilityWeek,[]);
          }
          entriesMap.get(ps.availabilityWeek).push(ps);
        }
      })
    }
    return entriesMap;
  }
  private  reservationsWeeklyMap<T extends {movementWeek?:number}>(entries:T[] ):Map<number,T[]>{
    let entriesMap:Map<number,T[]>=new Map();
    if (entries){
      entries.forEach((ps)=>{
        if (ps.movementWeek) {
          if (!entriesMap.has(ps.movementWeek)) {
            entriesMap.set(ps.movementWeek,[]);
          }
          entriesMap.get(ps.movementWeek).push(ps);
        }
      })
    }
    return entriesMap;
  }
  private  deliveryWeeklyMap<T extends {deliveryWeek?:number}>(entries:T[] ):Map<number,T[]>{
    let entriesMap:Map<number,T[]>=new Map();
    if (entries){
      entries.forEach((ps)=>{
        if (ps.deliveryWeek) {
          if (!entriesMap.has(ps.deliveryWeek)) {
            entriesMap.set(ps.deliveryWeek,[]);
          }
          entriesMap.get(ps.deliveryWeek).push(ps);
        }
      })
    }
    return entriesMap;
  }
}



