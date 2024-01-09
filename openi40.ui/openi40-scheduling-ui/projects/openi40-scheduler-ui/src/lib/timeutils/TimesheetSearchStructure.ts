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
import { TimesheetDto } from 'projects/openi40-scheduler-api/src/lib';
import TreeMap from 'ts-treemap';
import { Openi40GraphicConfiguration } from '../screenconstants';
import { TimeSegment } from './TimeSegment';
import { TimesheetSearchStructureItem } from './TimesheetSearchStructureItem';
const treeMultipliers = new Array<number>();
treeMultipliers.push(100000000000);
treeMultipliers.push(1000000000);
treeMultipliers.push(10000000);
treeMultipliers.push(100000);
treeMultipliers.push(1000);
export class TimesheetSearchStructure {
  private constructor(protected xSlotWidth: number) {

  }
  public static build(dataItem: TimesheetDto, graphicConfig: Openi40GraphicConfiguration): TimesheetSearchStructure {
    let currentSearchStructure: TimesheetSearchStructure = null;

    if (dataItem) {
      let slotCounter: number = 0;
      currentSearchStructure = new TimesheetSearchStructure(graphicConfig.dimensions.timeCellWidth);
      if (dataItem.years) {
        dataItem.years.forEach(year => {
          if (year.quarters) {
            year.quarters.forEach(quarter => {
              if (quarter.months) {
                quarter.months.forEach(month => {
                  if (month.days) {
                    month.days.forEach(day => {

                      let currentEntry: TimesheetSearchStructureItem = null;
                      if (graphicConfig.configurationName === "DAY") {
                        currentEntry = currentSearchStructure.addTimeSegment(day);
                        currentEntry.xLeft = slotCounter * graphicConfig.dimensions.timeCellWidth;
                        currentEntry.xWidth = graphicConfig.dimensions.timeCellWidth;
                        slotCounter++;
                      }
                      if (day.turns) {
                        day.turns.forEach(turn => {

                          if (graphicConfig.configurationName === "TURN") {
                            currentEntry = currentSearchStructure.addTimeSegment(turn);
                            currentEntry.startDate = turn.startShortDate;
                            currentEntry.endDate = turn.endShortDate;
                            currentEntry.startTime = turn.startShortTime;
                            currentEntry.endTime = turn.endShortTime;
                            currentEntry.xLeft = slotCounter * graphicConfig.dimensions.timeCellWidth;
                            currentEntry.xWidth = graphicConfig.dimensions.timeCellWidth;
                            slotCounter++;
                          }
                          if (turn.hours) {
                            turn.hours.forEach(hour => {

                              if (graphicConfig.configurationName === "HOUR") {
                                currentEntry = currentSearchStructure.addTimeSegment(hour);
                                currentEntry.startDate = hour.startShortDate;
                                currentEntry.endDate = hour.endShortDate;
                                currentEntry.startTime = hour.startShortTime;
                                currentEntry.endTime = hour.endShortTime;
                                currentEntry.xLeft = slotCounter * graphicConfig.dimensions.timeCellWidth;
                                currentEntry.xWidth = graphicConfig.dimensions.timeCellWidth;
                                slotCounter++;
                              }
                              currentEntry.effectiveAvailableTime += (hour.utcEndDateTime - hour.utcStartDateTime) / (60.0 * 1000.0);
                            });
                          }
                        });
                      }

                    });
                  }
                });
              }
            });
          }
        });
      }
    }
    return currentSearchStructure;
  }
  public searchTree: TimesheetSearchStructureItem = new TimesheetSearchStructureItem();

  public timeSlotsArray: TimesheetSearchStructureItem[] = [];
  private buildEntry(utcValue: number): TimesheetSearchStructureItem {
    let previusLevelObject: TimesheetSearchStructureItem = this.searchTree;
    previusLevelObject.array = new Array<TimesheetSearchStructureItem>();
    for (var m = 0; treeMultipliers[m]; m++) {
      var dividendo: number = treeMultipliers[m];
      let indice = Math.floor(utcValue / dividendo);
      if (!previusLevelObject.searchMap) previusLevelObject.searchMap = new Map();
      if (!previusLevelObject.searchMap.has(indice)) {
        let item: TimesheetSearchStructureItem = new TimesheetSearchStructureItem();
        item.utcStartDateTime = utcValue;
        item.utcEndDateTime = utcValue;
        item.xWidth = this.xSlotWidth;
        item.xLeft = 0;
        if (m < treeMultipliers.length - 1) {
          item.array = new Array();
        }
        previusLevelObject.searchMap.set(indice, item);
        previusLevelObject.array.push(item);
      }
      previusLevelObject = previusLevelObject.searchMap.get(indice);
      if (utcValue < previusLevelObject.utcStartDateTime) {
        previusLevelObject.utcStartDateTime = utcValue;
      }
      if (utcValue > previusLevelObject.utcEndDateTime) {
        previusLevelObject.utcEndDateTime = utcValue;
      }
    }
    return previusLevelObject;
  }
  public getEntry(utcValue: number): TimesheetSearchStructureItem {
    let previusLevelObject = this.searchTree;
    for (var m = 0; treeMultipliers[m]; m++) {
      let dividendo = treeMultipliers[m];
      let indice = Math.floor(utcValue / dividendo);
      if (m == (treeMultipliers.length - 2)) {
        for (var j = 0; previusLevelObject.array[j]; j++) {
          var thirdLevelArray = previusLevelObject.array[j];
          for (var y = 0; thirdLevelArray.array[y]; y++) {
            var entry = thirdLevelArray.array[y];
            if (entry.startSlot
              && entry.startSlot.utcStartDateTime
              && entry.startSlot.utcEndDateTime
              && (utcValue >= entry.startSlot.utcStartDateTime && utcValue <= entry.startSlot.utcEndDateTime)) {
              return entry.startSlot;
            }
            if (entry.endSlot
              && entry.endSlot.utcStartDateTime
              && entry.endSlot.utcEndDateTime
              && (utcValue >= entry.endSlot.utcStartDateTime && utcValue <= entry.endSlot.utcEndDateTime)) {
              return entry.endSlot;
            }
          }
        }
      }
      else {
        if (!previusLevelObject.searchMap.has(indice)) {
          return this.searchRecursive(previusLevelObject, utcValue);
        }
        else {
          previusLevelObject = previusLevelObject.searchMap.get(indice);
        }
      }
    }

    return previusLevelObject;
  }
  public getStartSegment(utcValue: number): TimesheetSearchStructureItem {
    let _entry: TimesheetSearchStructureItem = this.getEntry(utcValue);
    if (!_entry) {
      let _vals = this.entriesStartRawMap.ceilingEntry(utcValue);
      if (_vals) _entry = _vals[1];
    }
    return _entry;
  }
  public getEndSegment(utcValue: number): TimesheetSearchStructureItem {
    let _entry: TimesheetSearchStructureItem = this.getEntry(utcValue);
    if (!_entry) {
      let _vals = this.entriesStartRawMap.floorEntry(utcValue);
      if (_vals) _entry = _vals[1];
    }
    return _entry;
  }
  private searchRecursive(item: TimesheetSearchStructureItem, utcValue: number): TimesheetSearchStructureItem {
    let retValue: TimesheetSearchStructureItem = null;
    if (item.array) {
      for (var i = 0; !retValue && item.array && i < item.array.length; i++) {
        retValue = this.searchRecursive(item.array[i], utcValue);
      }
    }
    else {
      if (item.startSlot
        && item.startSlot.utcStartDateTime
        && item.startSlot.utcEndDateTime
        && (utcValue >= item.startSlot.utcStartDateTime && utcValue <= item.startSlot.utcEndDateTime)) {
        retValue = item.startSlot;
      }
      if (item.endSlot
        && item.endSlot.utcStartDateTime
        && item.endSlot.utcEndDateTime
        && (utcValue >= item.endSlot.utcStartDateTime && utcValue <= item.endSlot.utcEndDateTime)) {
        retValue = item.endSlot;
      }
    }
    return retValue;
  }
  private entriesStartRawMap: TreeMap<number, TimesheetSearchStructureItem> = new TreeMap<number, TimesheetSearchStructureItem>();
  private entriesEndRawMap: TreeMap<number, TimesheetSearchStructureItem> = new TreeMap<number, TimesheetSearchStructureItem>();
  private addTimeSegment(hour: { utcStartDateTime?: number; utcEndDateTime?: number; id?: string; description?: string, code?: string }): TimesheetSearchStructureItem {
    var utcStartDateTime = hour.utcStartDateTime;
    var utcEndDateTime = hour.utcEndDateTime;
    let _entry = new TimesheetSearchStructureItem();
    _entry.utcStartDateTime = hour.utcStartDateTime;
    _entry.utcEndDateTime = hour.utcEndDateTime;
    _entry.code = hour.code;
    _entry.id = hour.id;
    _entry.description = hour.description;
    this.buildEntry(utcStartDateTime).startSlot = _entry;
    this.buildEntry(utcEndDateTime).endSlot = _entry;
    this.timeSlotsArray.push(_entry);
    this.entriesEndRawMap.set(_entry.utcEndDateTime, _entry);
    this.entriesStartRawMap.set(_entry.utcStartDateTime, _entry);
    return _entry;
  }
  public getXTimeCoord(utcTime: number) {
    let _entry = this.getEntry(utcTime);
    if (!_entry) {
      //Management of "out of calendar" X values
      //let _floorEntry=this.entriesStartRawMap.floorEntry(utcTime);
      //let _ceilingEntry=this.entriesEndRawMap.ceilingEntry(utcTime);
      let _floorEntry = this.entriesEndRawMap.floorEntry(utcTime);
      let _ceilingEntry = this.entriesStartRawMap.ceilingEntry(utcTime);
      if (_floorEntry && _ceilingEntry) {
        //let timeSlot={utcStartDateTime: _floorEntry[1].utcStartDateTime, utcEndDateTime: _ceilingEntry[1].utcEndDateTime, xWidth:((_ceilingEntry[1].xLeft+_ceilingEntry[1].xWidth)-_floorEntry[1].xLeft),xLeft:_floorEntry[1].xLeft};
        //return this.calculateXCoordinata(timeSlot,utcTime);
        return _ceilingEntry[1].xLeft;
      }
    }
    if (_entry) {
      return this.calculateXCoordinata(_entry, utcTime);
    } else return -1;
  }
  public calculateXCoordinata(timeSlot: { utcStartDateTime?: number; utcEndDateTime?: number; xWidth?: number; xLeft?: number }, utcTime: number): number {
    return (timeSlot.xLeft + this.calculateXOffset(timeSlot, utcTime));
  }
  private calculateXOffset(timeSlot: { utcStartDateTime?: number; utcEndDateTime?: number; xWidth?: number }, utcTime: number) {
    let offset = 0;
    let fullWidth = (timeSlot.xWidth);
    let utcWidth = (timeSlot.utcEndDateTime - timeSlot.utcStartDateTime);
    let utcOffset = (utcTime - timeSlot.utcStartDateTime);
    offset = (fullWidth * (utcOffset / utcWidth));
    return offset;
  }
}
