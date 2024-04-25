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
import { Component, EventEmitter, Input, OnChanges, OnInit, Output, SimpleChanges } from '@angular/core';
import { TreeNode } from 'primeng/api';
import { ApsDataDto, TaskDto } from 'projects/openi40-scheduler-api/src/lib';
@Component({
  selector: 'openi40-dependency-graph',
  templateUrl: './openi40-dependency-graph.component.html',
  styleUrls: ['./openi40-dependency-graph.component.css']
})
export class Openi40DependencyGraphComponent implements OnInit, OnChanges {
  @Input("apsData") public apsData: ApsDataDto = null;
  @Input("taskInFilter") public taskInFilter: TaskDto = null;
  trees: TreeNode<TaskDto>[] = [];
  selectedNode: TreeNode<TaskDto> = null;
  @Output() viewTaskDetails:EventEmitter<TaskDto>=new EventEmitter();
  constructor() { }
  ngOnChanges(changes: SimpleChanges): void {
    this.refreshTrees();
  }

  ngOnInit(): void {
    this.refreshTrees();
  }
  private refreshTrees(): void {
    this.trees = [];
    let ordersNode: TreeNode<any> = {};
    ordersNode.expanded = true;
    ordersNode.label = "Orders";
    ordersNode.children = [];

    if (this.apsData) {
      let _treeMap: Map<string, TreeNode<TaskDto>> = new Map();
      let _rootCandidates: Map<string, TreeNode<TaskDto>> = new Map();
      this.apsData.productiveCompanies.forEach((pc) => {
        pc.plants.forEach((plnt) => {
          plnt.workOrders.forEach((wo) => {
            wo.tasks.forEach((task) => {
              if (task.dependencies) {
                let node: TreeNode<TaskDto> = {};
                node.label = task.code + " " + (task.description ? task.description : "");
                node.data = task;
                node.expanded = true;
                node.children = [];
                node.leaf = task.dependencies ? task.dependencies.length == 0 : true;
                _treeMap.set(task.id, node);
                _rootCandidates.set(task.id, node);
              }
            });
          });
        });
      });
      _treeMap.forEach((value: TreeNode<TaskDto>, key: string) => {
        value.data.dependencies.forEach((dependency) => {
          if (_rootCandidates.has(dependency.supplyTaskId)) {
            _rootCandidates.delete(dependency.supplyTaskId);
          }
          let _child = _treeMap.get(dependency.supplyTaskId);
          if (_child) {
            value.children.push(_child);
          }
        });
      });
      _rootCandidates.forEach((value: TreeNode<TaskDto>, key: string) => {
        ordersNode.children.push(value);
      });
      if (this.taskInFilter) {
        this.trees = this.filterByTaskInChain(ordersNode.children, this.taskInFilter);
      }else {
        this.trees.push(ordersNode);
      }
    }


  }
  private filterByTaskInChain(_rootCandidates: TreeNode<TaskDto>[], taskInFilter: TaskDto): TreeNode<TaskDto>[] {
    let outNodes: TreeNode<TaskDto>[] = [];
    if (_rootCandidates){
      _rootCandidates.forEach((candidate)=>{
        let isContained:boolean=this.treeContainsTask(candidate,taskInFilter);
        if (isContained){
          console.log("Returning candidate=>"+candidate.data.code);
          outNodes.push(candidate);
        }
      });
    }
    return outNodes;
  }
  treeContainsTask(candidate: TreeNode<TaskDto>, taskInFilter: TaskDto):boolean {
    console.log("Check task:"+candidate.data.code+" matching with code: "+taskInFilter.code);
    let ret:boolean=false;
    if (candidate.data.code===taskInFilter.code) {
      console.log("Check task:"+candidate.data.code+" returning TRUE");
      this.selectedNode=candidate;
      ret= true;
    }
    if (candidate.children) {
      candidate.children.forEach((child)=>{
        let childOk:boolean=this.treeContainsTask(child,taskInFilter);
        if (childOk) {
          console.log("Check task:"+candidate.data.code+" children:" + child.data.code+" returns TRUE!");
          ret= true;
        }
      });
    }
    return ret;
  }
  public viewDetailsClicked(task:TaskDto):void {
    this.viewTaskDetails.emit(task);
  }
}
