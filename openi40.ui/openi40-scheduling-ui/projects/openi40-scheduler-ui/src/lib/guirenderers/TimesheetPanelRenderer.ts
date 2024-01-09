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
import { AbstractSvgComponentRenderer } from "./AbstractSvgComponentRenderer";
import { TimesheetPanelGuiItem } from '../guidatamodel/TimesheetPanelGuiItem';
import { AbstractSvgDocumentRenderer } from "./AbstractSvgDocumentRenderer";
import { BaseGuiItem } from "../guidatamodel/BaseGuiItem";
class ElementGuiItemCouple {
  treeItem: BaseGuiItem = null;
  rect: Element = null;
}
export class TimesheetPanelRenderer extends AbstractSvgDocumentRenderer<TimesheetPanelGuiItem>{
  verticalSlots: number = 0;
  private horizontalRects: ElementGuiItemCouple[] = [];
  public create(): Element {
    let timesheetRootComponent = this.createSvgDocument(this.boundGuiItem);
    let timeCells = this.boundGuiItem.createTimeCells();
    var length = timeCells.length;
    for (var i = 0; i < length; i++) {
      var g = this.createElement("g");
      let tc = timeCells[i];
      var _line = this.createElement("line");
      var timeSlot = tc.data;
      var height = this.boundGuiItem.graphicConfig.dimensions.cellHeight;
      var cellWidth = this.boundGuiItem.graphicConfig.dimensions.timeCellWidth;
      if (tc.data.startDate && tc.data.endDate && tc.data.startTime && tc.data.endTime) {
        //Area with time start and time end display with round corners
        var backgroundRect = this.createElement("rect");
        this.setAttribute(backgroundRect, "x", "" + (timeSlot.xLeft + 5));
        this.setAttribute(backgroundRect, "y", "0");
        this.setAttribute(backgroundRect, "height", "" + (this.boundGuiItem.graphicConfig.dimensions.timesheetHeaderHeight + height - 10));
        this.setAttribute(backgroundRect, "width", "" + (cellWidth - 10));
        this.setAttribute(backgroundRect, "class", "timeRect");
        this.setAttribute(backgroundRect, "rx", "10");
        this.renderer2.appendChild(g, backgroundRect);
        var startTimeLabel = this.createElement("text");
        var endTimeLabel = this.createElement("text");
        var startDateWidth: number = this.boundGuiItem.graphicConfig.dimensions.characterWidth * tc.data.startDate.length;
        if (tc.data.startDate !== tc.data.endDate) {
          var startDateLabel = this.createElement("text");

          var endDateLabel = this.createElement("text");


          //Writing start date
          startDateLabel.innerHTML = tc.data.startDate;
          this.setAttribute(startDateLabel, "x", "" + (timeSlot.xLeft + 10));
          this.setAttribute(startDateLabel, "y", height);
          this.setAttribute(startDateLabel, "width", "" + (startDateWidth));
          this.setAttribute(startDateLabel, "class", "startDate");
          this.setAttribute(startDateLabel, "height", height);
          this.renderer2.appendChild(g, startDateLabel);
          endDateLabel.innerHTML = tc.data.endDate;
          //Writing end date
          var endDateWidth: number = this.boundGuiItem.graphicConfig.dimensions.characterWidth * tc.data.endDate.length;
          this.setAttribute(endDateLabel, "x", "" + (timeSlot.xLeft + cellWidth - endDateWidth));
          this.setAttribute(endDateLabel, "y", height);
          this.setAttribute(endDateLabel, "width", "" + (endDateWidth));
          this.setAttribute(endDateLabel, "class", "endDate");
          this.setAttribute(endDateLabel, "height", height);
          this.renderer2.appendChild(g, endDateLabel);
        } else {
          var uniqueDateLabel=this.createElement("text");
          uniqueDateLabel.innerHTML=tc.data.startDate;
          this.setAttribute(uniqueDateLabel, "x", "" + (timeSlot.xLeft + (cellWidth-startDateWidth)/2+10));
          this.setAttribute(uniqueDateLabel, "y", height);
          this.setAttribute(uniqueDateLabel, "width", "" + (startDateWidth));
          this.setAttribute(uniqueDateLabel, "class", "startDate");
          this.setAttribute(uniqueDateLabel, "height", height);
          this.renderer2.appendChild(g, uniqueDateLabel);
        }
        //Writing start time
        startTimeLabel.innerHTML = tc.data.startTime;
        var startTimeWidth: number = tc.data.startTime.length * this.boundGuiItem.graphicConfig.dimensions.characterWidth;
        this.setAttribute(startTimeLabel, "x", "" + (timeSlot.xLeft + 10));
        this.setAttribute(startTimeLabel, "y", height + height);
        this.setAttribute(startTimeLabel, "width", "" + (startTimeWidth));
        this.setAttribute(startTimeLabel, "class", "startTime");
        this.setAttribute(startTimeLabel, "height", height);
        this.renderer2.appendChild(g, startTimeLabel);
        //Writing end time
        var endTimeWidth: number = tc.data.endTime.length * this.boundGuiItem.graphicConfig.dimensions.characterWidth;
        endTimeLabel.innerHTML = tc.data.endTime;
        this.setAttribute(endTimeLabel, "x", "" + (timeSlot.xLeft + cellWidth - endTimeWidth - 2));
        this.setAttribute(endTimeLabel, "y", height + height);
        this.setAttribute(endTimeLabel, "width", "" + (endTimeWidth));
        this.setAttribute(endTimeLabel, "height", height);
        this.setAttribute(endTimeLabel, "class", "endTime");
        this.renderer2.appendChild(g, endTimeLabel);
      } else {
        var _label = this.createElement("text");
        var labelWidth = timeSlot.description.length * this.boundGuiItem.graphicConfig.dimensions.characterWidth;
        this.setAttribute(_label, "x", "" + (timeSlot.xLeft + ((this.boundGuiItem.graphicConfig.dimensions.timeCellWidth - labelWidth) / 2+10)));
        this.setAttribute(_label, "y", height);
        this.setAttribute(_label, "height", "" + height);
        this.setAttribute(_label, "width", "" + labelWidth);
        this.setAttribute(_label, "id", "timeSlot_" + i);
        this.setAttribute(_label, "class", "timeCell " + this.boundGuiItem.graphicConfig.configurationName);
        this.setAttribute(_line, "stroke-width", "1px");
        this.renderer2.appendChild(g, _label);
        _label.innerHTML = timeSlot.description;
        //this.setAttribute(_label, "title", timeSlot.description);
      }




      this.renderer2.appendChild(timesheetRootComponent, g);


      this.setAttribute(_line, "x1", "" + timeSlot.xLeft);
      this.setAttribute(_line, "y1", "0");
      this.setAttribute(_line, "x2", "" + timeSlot.xLeft);
      this.setAttribute(_line, "y2", this.boundGuiItem.height);
      this.setAttribute(_line, "class", "verticalLine");
      this.setAttribute(_line, "stroke-width", "1px");

      this.renderer2.appendChild(timesheetRootComponent, _line);
    }
    for (var i = 0; i < this.boundGuiItem.verticalSlices; i++) {

      if (i > 0 && (i % 2) === 0) {

        let rect = this.createElement("rect");
        let info: ElementGuiItemCouple = new ElementGuiItemCouple();
        info.treeItem = this.boundGuiItem.treeItemsNodes[i];
        info.rect = rect;
        this.setAttribute(rect, "x", "0");
        this.setAttribute(rect, "y", info.treeItem.y);
        this.setAttribute(rect, "width", "" + this.boundGuiItem.width);
        this.setAttribute(rect, "height", this.boundGuiItem.graphicConfig.dimensions.cellHeight);
        this.setAttribute(rect, "class", "horizontalRect");
        this.setAttribute(rect, "fill", this.boundGuiItem.graphicConfig.horizontalRectColor);
        this.setAttribute(rect, "stroke-width", "1px");
        this.renderer2.appendChild(timesheetRootComponent, rect);

        this.horizontalRects.push(info);
      }

    }
    return timesheetRootComponent;
  }
  public update(): void {
    if (this.horizontalRects) {
      this.horizontalRects.forEach((rectInfo) => {
        this.setAttribute(rectInfo.rect, "visibility", rectInfo.treeItem.visible === true ? "visible" : "hidden");
        this.setAttribute(rectInfo.rect, "x", "0");
        this.setAttribute(rectInfo.rect, "y", rectInfo.treeItem.y);
        this.setAttribute(rectInfo.rect, "width", "" + this.boundGuiItem.width);
        this.setAttribute(rectInfo.rect, "height", this.boundGuiItem.graphicConfig.dimensions.cellHeight);
      });
    }
  }
}
