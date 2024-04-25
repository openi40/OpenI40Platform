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
import { Openi40GraphicConfiguration } from '../screenconstants';
import { BaseGuiItem } from './BaseGuiItem';
import { NodesCounter } from './NodesCounter';
import { TreeItemGuiItem } from "./TreeItemGuiItem";
import { YBlocksRetrieverMap } from './YBlocksRetrieverMap';
export abstract class TreeNodeGuiItem<BoundData, BoundChildType extends BaseGuiItem> extends TreeItemGuiItem<BoundData> {
  toggleOpened() {
    this.opened = !(this.opened === true);
    this.setChildsVisibility(this.opened);
  }
  private setChildsVisibility(_v: boolean) {
    if (this.fullChildsList) {
      this.fullChildsList.forEach((child) => {
        child.visible = _v;
        if (child["fullChildsList"]) {
          let thisItem: any = child;
          thisItem.setChildsVisibility(_v);
        }
      });
    }
  }
  public mainChilds: BoundChildType[] = null;
  public constructor(dataItem: BoundData, graphicConfig: Openi40GraphicConfiguration, protected _depth, protected _nodesCounter: NodesCounter, public minWidth: number = 250, public minHeight: number = 50) {
    super(dataItem, graphicConfig, _depth, _nodesCounter, minWidth, minHeight);
    this.mainChilds = this.getMainChildGuiItems();
    this._xParentOffset = graphicConfig.dimensions.treeParentOffset;
  }
  protected abstract getMainChildGuiItems(): BoundChildType[];
  protected get fullChildsList(): BaseGuiItem[] {
    return this.mainChilds;
  }
  public get height() {
    let h = this.graphicConfig.dimensions.cellHeight;
    if (this.opened) {
      this.fullChildsList.forEach((ch) => { h += ch.height; });
    }
    return h;
  }
  public get width() {
    if (this.mainChilds) {
      let _w = Math.max(this._width, this.minWidth);
      this.fullChildsList.forEach((c) => {
        _w = Math.max(c.width + c.xParentOffset, _w);
      });
      return _w;
    }
    else {
      if (this._width > 0)
        return this._width;
      else {
        return this.minWidth;
      }
    }
  }
  public countNodes(): number {
    let nr: number = 1;
    if (this.mainChilds) {
      this.fullChildsList.forEach((node) => {
        if (node instanceof TreeItemGuiItem) {
          nr += node.countNodes();
        }
      })
    }
    return nr;
  }
  public recalculateCoordinates(): void {
    super.recalculateCoordinates();
    let yOffset = this.y + this.graphicConfig.dimensions.cellHeight;
    if (this.fullChildsList) this.fullChildsList.forEach((n) => {
      n.x = this.x + n.xParentOffset;
      n.y = yOffset + n.yParentOffset;
      yOffset += n.height;
      n.recalculateCoordinates();
    }
    );
  }
  public get listNodes(): BaseGuiItem[] {
    let _out: BaseGuiItem[] = [this];
    let childs = this.fullChildsList;
    if (childs) {
      childs.forEach(child => {
        if (child["listNodes"]) {
          let anyChild: any[] = child["listNodes"];
          if (anyChild) {
            anyChild.forEach(c => {
              _out.push(c);
            });
          }
        } else {
          _out.push(child);
        }
      });
    }
    return _out;
  }
  public get mappedControls(): YBlocksRetrieverMap {
    let map: YBlocksRetrieverMap = super.mappedControls;
    if (this.fullChildsList) {
      this.fullChildsList.forEach((chld) => {
        if (chld instanceof TreeItemGuiItem) {
          map = map.add(chld.mappedControls);
        }
      });
    }
    return map;
  }
}
