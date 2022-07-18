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
import { TreeNode } from 'primeng/api';
import { ApsDataDto } from 'projects/openi40-scheduler-api/src/lib';
import { Openi40GraphicConfiguration } from 'projects/openi40-scheduler-ui/src/lib/screenconstants';
import {Openi40ResourcesUseGraphService} from './openi40-resources-use-graph-service'

@Component({
  selector: 'openi40-resources-use-graph',
  templateUrl: './openi40-resources-use-graph.component.html',
  styleUrls: ['./openi40-resources-use-graph.component.css']
})
export class Openi40ResourcesUseGraphComponent implements OnInit,OnChanges {
  @Input("apsData") apsData:ApsDataDto=null;
  @Input("graphicConfiguration")  graphicConfiguration:Openi40GraphicConfiguration=null;
  public root:TreeNode[]=[];
  constructor(protected resourceUseGraphService:Openi40ResourcesUseGraphService) {

  }
  ngOnChanges(changes: SimpleChanges): void {
    this.refresh();
  }
  ngOnInit(): void {
    this.refresh();
  }

  protected refresh():void {
      if (this.apsData){
      this.resourceUseGraphService.getGraphRoot(this.apsData.dataSourceName,this.apsData.dataSetName,this.apsData.dataSetVariant,this.graphicConfiguration).subscribe((node)=>{
        this.root=node;
      })
    }
  }
  public lazyLoad(event):void {
    this.refresh();
  }
}
