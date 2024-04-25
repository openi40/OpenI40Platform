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
import { Injectable } from "@angular/core";
import { TreeNode } from 'primeng/api';
import { ApsResourcesUsageGraphResourceService, TimesheetDto, GraphicContextItemDtoUsageDataItemDto, GraphicContextNodeDtoUsageDataItemDto, ResourcesUsageGraphDto, ResourceUsageDto, UsageDataItemDto, UsageTimeSegmentDto } from 'projects/openi40-scheduler-api/src/lib';
import { TimeSegment, TimesheetSearchStructure } from 'projects/openi40-scheduler-ui/src/lib';
import { Openi40GraphicConfiguration } from 'projects/openi40-scheduler-ui/src/lib/screenconstants';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators'
class TimeDeltaPoint {
  ts: number = 0;
  increment: number = 0;
}
class UsageSegment extends TimeSegment {
  constructor(segment: UsageTimeSegmentDto) {
    super();
    this.usageQty = segment.usageQty;
    this.utcEndDateTime = segment.utcEndDateTime;
    this.utcStartDateTime = segment.utcStartDateTime;
    this.reservedTime = segment.reservedTime;
    this.resourceCode = segment.resourceCode;
  }
  reservedTime?: number;
  resourceCode?: string;
  usageQty?: number;
}
class ResourceUsageIndex {
  resourceUsageDto: ResourceUsageDto = null;
  index: number = 0;
  usageSlots: UsageSegment[] = [];
}
@Injectable()
export class Openi40ResourcesUseGraphService {
  constructor(protected apsResourcesUsageGraphicService: ApsResourcesUsageGraphResourceService) {

  }
  getGraphRoot(dataSourceName:string,dataSetId: string, dataSetVersionId: string, graphicConfig: Openi40GraphicConfiguration): Observable<TreeNode[]> {
    return this.apsResourcesUsageGraphicService.getResourcesUsageGraphDto(dataSetId,dataSourceName, dataSetVersionId).pipe(map((item) => { return this.adaptGraphData(item, graphicConfig) }));
  }
  protected adaptGraphData(graphDto: ResourcesUsageGraphDto, graphicConfig: Openi40GraphicConfiguration): TreeNode[] {
    let treeNode: TreeNode = { label: "Resources" };

    let timeSearchStructure: TimesheetSearchStructure = TimesheetSearchStructure.build(graphDto.timesheet, graphicConfig);
    let timeLabels: string[] = [];
    timeSearchStructure.timeSlotsArray.forEach((slot) => {
      timeLabels.push(slot.description);
    })
    this.appendChilds(treeNode, graphDto.roots, timeLabels, timeSearchStructure);
    return treeNode.children;
  }
  private generateTimeLabels(calendar: TimesheetDto, graphicConfig: Openi40GraphicConfiguration): string[] {
    let labels: string[] = [];
    calendar.years.forEach((year) => {
      year.quarters.forEach((quarter) => {
        quarter.months.forEach((month) => {

          month.days.forEach((day) => {
            if (graphicConfig.configurationName === "DAY") {
              labels.push(day.description);
            }
            day.turns.forEach((turn) => {
              if (graphicConfig.configurationName === "TURN") {
                labels.push(turn.description);
              }
              turn.hours.forEach((hour) => {
                if (graphicConfig.configurationName === "HOUR") {
                  labels.push(hour.description);
                }

              })
            })
          })
        })
      })
    })
    return labels;
  }
  private appendChilds(treeNode: TreeNode, roots: GraphicContextNodeDtoUsageDataItemDto[], timeLabels: string[], timeSearchStructure: TimesheetSearchStructure): void {
    if (!treeNode.children) treeNode.children = [];
    if (roots) {
      roots.forEach((item) => {
        treeNode.children.push(this.createChild(item, treeNode, timeLabels, timeSearchStructure));
      })
    }
  }
  private createChild(item: GraphicContextNodeDtoUsageDataItemDto, root: TreeNode, timeLabels: string[], timeSearchStructure: TimesheetSearchStructure): TreeNode {
    let node: TreeNode = { label: item.description, children: [], parent: root, expanded: true, data: { label: item.description, expanded: true } };
    if (item.nodes) {
      this.appendChilds(node, item.nodes, timeLabels, timeSearchStructure);
    }
    if (item.leafs) {
      this.appendLeafs(node, item.leafs, timeLabels, timeSearchStructure);
    }

    let otherChilds = this.transformData(item, timeLabels, timeSearchStructure);
    if (otherChilds) otherChilds.forEach((x) => {
      node.children.push(x);
    });
    return node;
  }
  private appendLeafs(treeNode: TreeNode, leafs: GraphicContextItemDtoUsageDataItemDto[], timeLabels: string[], timeSearchStructure: TimesheetSearchStructure) {
    if (!treeNode.children) treeNode.children = [];
    if (leafs) {
      leafs.forEach((item) => {
        treeNode.children.push(this.createLeaf(item, treeNode, timeLabels, timeSearchStructure));
      })
    }
  }
  private createLeaf(item: GraphicContextItemDtoUsageDataItemDto, treeNode: TreeNode, timeLabels: string[], timeSearchStructure: TimesheetSearchStructure): TreeNode {
    let node: TreeNode = { label: item.description, children: [], parent: treeNode, expanded: true, data: { label: item.description, expanded: true } };
    node.children = this.transformData(item, timeLabels, timeSearchStructure);

    return node;
  }
  private transformData(item: { id?: string, code?: string, description?: string, graphicContent?: UsageDataItemDto }, timeLabels: string[], timeSearchStructure: TimesheetSearchStructure): TreeNode[] {
    let tNodes: TreeNode[] = [];
    if (item.graphicContent) {
      if (item.graphicContent.workcenters) {
        let workCenters: TreeNode = null;
        let secondaryResourcesGroups: TreeNode = null;
        item.graphicContent.workcenters.forEach(workCenter => {
          if (!workCenters) {
            workCenters = { label: "Workcenters", expanded: true, data: { label: "Workcenters" }, children: [] };
            tNodes.push(workCenters);
          }
          workCenters.children.push({ label: workCenter.description, data: this.transformGroupData(workCenter, timeLabels, timeSearchStructure) });
        })

        if (item.graphicContent.secondaryResourceGroups) {
          item.graphicContent.secondaryResourceGroups.forEach(group => {
            if (!secondaryResourcesGroups) {
              secondaryResourcesGroups = { label: "Secondary resources groups", expanded: true, data: { label: "Secondary resources groups" }, children: [] };
              tNodes.push(secondaryResourcesGroups);
            }
            secondaryResourcesGroups.children.push({ label: group.description, data: this.transformGroupData(group, timeLabels, timeSearchStructure) });
          })
        }
      }
    }
    return tNodes;
  }

  private transformGroupData(toBeDrawn: { id?: string, code?: string, description?: string, resources?: Array<ResourceUsageDto> }, timeLabels: string[], timeSearchStructure: TimesheetSearchStructure): { id?: string, label?: string, code?: string, graphData: { labels: string[], datasets: any[] } } {
    let value = {
      id: toBeDrawn.id,
      label: toBeDrawn.description,
      code: toBeDrawn.code,
      graphData: {
        labels: timeLabels,
        datasets: [
          {
            label: 'Resources use %',
            borderColor: '#4bc0c0',
            backgroundColor:'#4bc0c0',
            data: []
          }]
      }
    };
    //parse all resources and compound point with increase/decrease
    if (toBeDrawn.resources && toBeDrawn.resources.length) {
      let usagesIndex: ResourceUsageIndex[] = [];
      let totalNr = toBeDrawn.resources.length;
      //Create a data structure with timeRanges operators
      //to analyze list of reservations
      toBeDrawn.resources.forEach((resource) => {
        if (resource.usages && resource.usages.length && resource.usages.length > 0) {
          let usageIndex: ResourceUsageIndex = new ResourceUsageIndex();
          usageIndex.resourceUsageDto = resource;
          resource.usages.forEach((uEntry) => {
            usageIndex.usageSlots.push(new UsageSegment(uEntry));
          })
          usagesIndex.push(usageIndex);
        }
      })
      //Now scan each timeSlot in actual calendar entries
      //for each of them go to each resource in the utility data structure
      //and position in the corresponding index analizing its content
      let initialQty = 0.0;
      timeSearchStructure.timeSlotsArray.forEach((timeSlot) => {
        let totalSlotArea = timeSlot.effectiveAvailableTime * toBeDrawn.resources.length;
        let usedArea = 0;
        usagesIndex.forEach((usageIndex) => {
          if (usageIndex.index < usageIndex.usageSlots.length) {

            while (usageIndex.index < usageIndex.usageSlots.length && usageIndex.usageSlots[usageIndex.index].utcEndDateTime < timeSlot.utcStartDateTime) {
              usageIndex.index++;
            }
            let index=usageIndex.index;
            while (index < usageIndex.usageSlots.length &&
              (usageIndex.usageSlots[index].IsOverlapping(timeSlot) ||
                timeSlot.IsOverlapping(usageIndex.usageSlots[index]) ||
                timeSlot.isSegmentInRange(usageIndex.usageSlots[index]) ||
                usageIndex.usageSlots[index].isSegmentInRange(timeSlot))) {
              let goForward: boolean = false;
              let currentRangeArea = 0;
              if (timeSlot.isSegmentInRange(usageIndex.usageSlots[index])) {
                currentRangeArea = usageIndex.usageSlots[index].reservedTime * usageIndex.usageSlots[index].usageQty;
                goForward = true;
              } else if (usageIndex.usageSlots[index].isSegmentInRange(timeSlot)) {
                currentRangeArea = timeSlot.effectiveAvailableTime * usageIndex.usageSlots[index].usageQty;
                goForward = false;
              } else if (timeSlot.isInRange(usageIndex.usageSlots[index].utcStartDateTime)) {
                goForward=true;
                currentRangeArea= ((timeSlot.utcEndDateTime-usageIndex.usageSlots[index].utcStartDateTime)/(60*1000))* usageIndex.usageSlots[index].usageQty;
              } else if (timeSlot.isInRange(usageIndex.usageSlots[index].utcEndDateTime)) {
                goForward=true;
                currentRangeArea= ((usageIndex.usageSlots[index].utcEndDateTime-timeSlot.utcStartDateTime)/(60*1000))* usageIndex.usageSlots[index].usageQty;
              } else
                throw Error("range not managed");
              usedArea+=currentRangeArea;
              if (goForward) {
                usageIndex.index=index;
                index++;
              } else {
                break;
              }
            }
          }

        })
        value.graphData.datasets[0].data.push(Math.round(100.0*usedArea/totalSlotArea));
      })

    }
    //timeLabels.forEach((l) => {
    //  value.graphData.datasets[0].data.push(Math.random() * 100.0);
    //})
    return value;
  }
  private intersects(segment1: { utcStartDateTime?: number, utcEndDateTime?: number }, segment2: { utcStartDateTime?: number, utcEndDateTime?: number }): boolean {
    return false;
  }
}
