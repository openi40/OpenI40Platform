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
import { Component, OnInit } from '@angular/core';
import {ApsDataListService,DataSetEntry} from 'projects/openi40-scheduler-api/src/lib/';
import { Router } from '@angular/router';
@Component({
  selector: 'openi40-homepage',
  templateUrl: './openi40-homepage.component.html',
  styleUrls: ['./openi40-homepage.component.css']
})
export class Openi40HomepageComponent implements OnInit {
  public dataSetEntries: DataSetEntry[];

  constructor(protected apsDataListService:ApsDataListService,protected router:Router) { }

  ngOnInit(): void {
    this.apsDataListService.getListDataSetEntry().subscribe((entries)=>{this.dataSetEntries=entries;})
  }
  onChoosedDataSource(dataSourceName:string,dataSetName:string,dataSetVariant:string):void {
    this.router.navigateByUrl('/openi40-dataset-homepage?dataSourceName='+dataSourceName+'&dataSetName='+dataSetName+"&dataSetVariant="+dataSetVariant);
  }
}
