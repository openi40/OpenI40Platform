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
import { Component, Input, OnChanges, OnInit, SimpleChanges } from '@angular/core';
import { ApsDataDto, ApsMaterialAnalisysResourceService, MaterialPlanningDto, ProductDto } from 'projects/openi40-scheduler-api/src/lib';

@Component({
  selector: 'openi40-material-product-planning',
  templateUrl: './openi40-material-product-planning.component.html',
  styleUrls: ['./openi40-material-product-planning.component.css']
})
export class Openi40MaterialProductPlanningComponent implements OnInit,OnChanges {
  @Input("apsData") apsData:ApsDataDto=null;
  @Input("product") product:ProductDto=null;
  constructor(private apsMaterialService:ApsMaterialAnalisysResourceService) {

  }

  ngOnChanges(changes: SimpleChanges): void {
    //this.reloadData();
  }
  materialPlanningData:MaterialPlanningDto=null;
  ngOnInit(): void {
    this.reloadData();
  }
  private reloadData():void {
    if (this.apsData && this.product){
    this.apsMaterialService.getMaterialPlanningMaterialPlanningDto(this.apsData.dataSetName,this.apsData.dataSourceName, this.product.code, this.apsData.dataSetVariant).subscribe((materialPlanningDto:MaterialPlanningDto)=>{
      this.materialPlanningData=materialPlanningDto;
    });
  }
  }

}
