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
import { PointCoords } from './PointCoords';

export abstract class EdgeConnectorGuiItem<BoundType,FromType extends BaseGuiItem,ToType extends BaseGuiItem> extends DataBoundGuiItem<BoundType> {
  public from:FromType=null;
  public to:ToType=null;
  public constructor(protected dataItem:BoundType,graphicConfig:Openi40GraphicConfiguration,from:FromType,to:ToType) {
    super(dataItem,graphicConfig);
    this.from=from;
    this.to=to;
  }
  recalculateCoordinates():void {
    super.recalculateCoordinates();
    let visible:boolean=true;
    if (this.from) {
      if (this.from.visible===false) visible=false;
    }
    if (this.to) {
      if (this.to.visible===false) visible=false;
    }
    this.visible=visible;
  }
  public abstract calculateConnectorPoints():PointCoords[];
}
