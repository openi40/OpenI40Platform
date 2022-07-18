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
import { ApsDataDto, ProductDto } from 'projects/openi40-scheduler-api/src/lib';
import { Openi40GraphicConfiguration } from 'projects/openi40-scheduler-ui/src/lib/screenconstants';
import TreeMap from 'ts-treemap';
class ProductItem {
  opened?:boolean=false;
  code?: string;
  description?: string;
  id?: string;
}
@Component({
  selector: 'openi40-material-planning',
  templateUrl: './openi40-material-planning.component.html',
  styleUrls: ['./openi40-material-planning.component.css']
})
export class Openi40MaterialPlanningComponent implements OnInit {

  constructor() { }
  @Input("apsData") apsData:ApsDataDto=null;
  @Input("graphicConfiguration")  graphicConfiguration:Openi40GraphicConfiguration=null;
  public products:ProductItem[]=[];
  ngOnInit(): void {
    this.products=[];
    let _treeMap:TreeMap<string,ProductDto>=new TreeMap<string,ProductDto>();
    if (this.apsData.products){
      this.apsData.products.forEach((prod)=>{
        _treeMap.set(prod.code,prod);
      });
    }
    _treeMap.forEach((value,key)=>{
      this.products.push(value);
    })
  }

}
