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
import { DataBoundGuiItem } from './DataBoundGuiItem';
import { NodesCounter } from './NodesCounter';
import { YBlocksRetrieverMap } from './YBlocksRetrieverMap';
export abstract class TreeItemGuiItem<BoundData> extends DataBoundGuiItem<BoundData> {
  protected _nodeNumber: number = 0;
  public constructor(dataItem: BoundData,graphicConfig:Openi40GraphicConfiguration, protected _depth, protected _nodesCounter: NodesCounter, public minWidth: number = 200, public minHeight: number = graphicConfig.dimensions.cellHeight) {
    super(dataItem,graphicConfig);
    this._nodeNumber = this._nodesCounter.getActualNodeNumber();
    this._height=minHeight;
    this._xParentOffset=this.graphicConfig.dimensions.treeParentOffset;
    this._width=this.visibleLabel?this.visibleLabel.length*this.graphicConfig.dimensions.characterWidth:10+3*this.graphicConfig.dimensions.cellWidth;
  }
  public get nodeNumber(): number {
    return this._nodeNumber;
  }
  public get depth(): number {
    return this._depth;
  }
  public countNodes():number {
    return 1;
  }
  public get listNodes():BaseGuiItem[] {
    return [this];
  }
  public get mappedControls():YBlocksRetrieverMap{
    let map:YBlocksRetrieverMap=new YBlocksRetrieverMap();
    map.put(this.constructor.name,this.code,this);
    return map;
  }
  public get iconPath():string {
    return null;
  }
}
