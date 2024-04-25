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
export const baseScheduleUrl = "ApsCommand";

 const DEFAULT_HOUR_RESOLUTION_WIDTH = 150;
 const DEFAULT_DAY_RESOLUTION_WIDTH = 90;
export const ganttColumn1Margin = 200;
export class GraphicDimensions{
    labelsVerticalOffset:number=0;
    treeParentOffset:number=20;
    cellWidth:number = 200;
    cellHeight:number = 25;
    characterWidth:number=8;
    timeCellWidth:number=DEFAULT_DAY_RESOLUTION_WIDTH;
    timesheetHeaderHeight:number=40;
}

export class Openi40GraphicConfiguration{
    configurationName:string;
    dimensions:GraphicDimensions=new GraphicDimensions();
    horizontalRectColor: string;
}
export class DimensionChoice{
  choiceCode:string=null;
  label:string=null;
  description:string=null;
  configurations:Openi40GraphicConfiguration[]=null;
}
//212,241,248
export const DefaultHourResolutionConfig:Openi40GraphicConfiguration={configurationName:"HOUR",horizontalRectColor:"#D4F1F8",dimensions:{cellHeight:25,cellWidth:30,timeCellWidth:DEFAULT_HOUR_RESOLUTION_WIDTH,characterWidth:9,treeParentOffset:0,labelsVerticalOffset:-6,timesheetHeaderHeight:40}};
export const DefaultTurnResolutionConfig:Openi40GraphicConfiguration={configurationName:"TURN",horizontalRectColor:"#D4F1F8",dimensions:{cellHeight:25,cellWidth:30,timeCellWidth:DEFAULT_HOUR_RESOLUTION_WIDTH,characterWidth:9,treeParentOffset:0,labelsVerticalOffset:-6,timesheetHeaderHeight:40}};
export const DefaultDayResolutionConfig:Openi40GraphicConfiguration={configurationName:"DAY",horizontalRectColor:"#D4F1F8",dimensions:{cellHeight:25,cellWidth:30,timeCellWidth:DEFAULT_DAY_RESOLUTION_WIDTH,characterWidth:9,treeParentOffset:0,labelsVerticalOffset:-6,timesheetHeaderHeight:40}};
export const RESOLUTION_CONFIGURATIONS:Openi40GraphicConfiguration[]=[DefaultDayResolutionConfig,DefaultTurnResolutionConfig,DefaultHourResolutionConfig];

export const TinyHourResolutionConfig:Openi40GraphicConfiguration={configurationName:"HOUR",horizontalRectColor:"#D4F1F8",dimensions:{cellHeight:20,cellWidth:30,timeCellWidth:160,characterWidth:9,treeParentOffset:0,labelsVerticalOffset:-4,timesheetHeaderHeight:40}};
export const TinyTurnResolutionConfig:Openi40GraphicConfiguration={configurationName:"TURN",horizontalRectColor:"#D4F1F8",dimensions:{cellHeight:20,cellWidth:30,timeCellWidth:160,characterWidth:9,treeParentOffset:0,labelsVerticalOffset:-4,timesheetHeaderHeight:40}};
export const TinyDayResolutionConfig:Openi40GraphicConfiguration={configurationName:"DAY",horizontalRectColor:"#D4F1F8",dimensions:{cellHeight:20,cellWidth:30,timeCellWidth:80,characterWidth:9,treeParentOffset:0,labelsVerticalOffset:-4,timesheetHeaderHeight:40}};
export const TINY_RESOLUTION_CONFIGURATIONS:Openi40GraphicConfiguration[]=[TinyDayResolutionConfig,TinyTurnResolutionConfig,TinyHourResolutionConfig];

export const HugeHourResolutionConfig:Openi40GraphicConfiguration={configurationName:"HOUR",horizontalRectColor:"#D4F1F8",dimensions:{cellHeight:30,cellWidth:30,timeCellWidth:200,characterWidth:9,treeParentOffset:0,labelsVerticalOffset:-8,timesheetHeaderHeight:40}};
export const HugeTurnResolutionConfig:Openi40GraphicConfiguration={configurationName:"TURN",horizontalRectColor:"#D4F1F8",dimensions:{cellHeight:30,cellWidth:30,timeCellWidth:200,characterWidth:9,treeParentOffset:0,labelsVerticalOffset:-8,timesheetHeaderHeight:40}};
export const HugeDayResolutionConfig:Openi40GraphicConfiguration={configurationName:"DAY",horizontalRectColor:"#D4F1F8",dimensions:{cellHeight:30,cellWidth:30,timeCellWidth:100,characterWidth:9,treeParentOffset:0,labelsVerticalOffset:-8,timesheetHeaderHeight:40}};
export const HUGE_RESOLUTION_CONFIGURATIONS:Openi40GraphicConfiguration[]=[HugeDayResolutionConfig,HugeTurnResolutionConfig,HugeHourResolutionConfig];


export const DIMENSION_CHOICES:DimensionChoice[]=[
  {
    choiceCode:"TINY",
    configurations: TINY_RESOLUTION_CONFIGURATIONS,
    label:"Tiny",
    description:"Fits more objects in the screen"
  },
    {
      choiceCode:"MEDIUM",
      configurations: RESOLUTION_CONFIGURATIONS,
      label:"Medium",
      description:"Medium resolution, high visibility for all screens"
    },

    {
      choiceCode:"BIG",
      configurations: HUGE_RESOLUTION_CONFIGURATIONS,
      label:"Big",
      description:"Huge resolution and big screens version"
    }

];
