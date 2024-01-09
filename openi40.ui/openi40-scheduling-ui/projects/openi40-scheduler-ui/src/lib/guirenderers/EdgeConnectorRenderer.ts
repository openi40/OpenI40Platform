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
import { BaseGuiItem } from '../guidatamodel/BaseGuiItem';
import { EdgeConnectorGuiItem } from '../guidatamodel/EdgeConnectorGuiItem';
import { PointCoords } from '../guidatamodel/PointCoords';
import { AbstractSvgComponentRenderer } from './AbstractSvgComponentRenderer';

export class EdgeConnectorRenderer<BoundType, FromType extends BaseGuiItem, ToType extends BaseGuiItem, EdgeConnectorType extends EdgeConnectorGuiItem<BoundType, FromType, ToType>> extends AbstractSvgComponentRenderer<EdgeConnectorType> {
  arrow: Element = null;
  g:Element=null;
  title:Element=null;
  public create(): Element {
    this.boundGuiItem.recalculateCoordinates();
    //<polyline points="20,20 40,25 60,40 80,120 120,140 200,180"
    //style="fill:none;stroke:black;stroke-width:3" />
    this.g=this.createElement("g");
    this.arrow = this.createElement("polyline");
    this.title=this.createElement("title");
    this.title.innerHTML="from " + this.boundGuiItem.from.visibleLabel+" to "+ this.boundGuiItem.to.visibleLabel;
    //this.setAttribute(this.arrow,"style","fill:none;stroke:blue;stroke-width:1");

    this.renderer2.appendChild(this.g,this.arrow);
    this.renderer2.appendChild(this.arrow,this.title);
    this.update();
    return this.g;
  }
  public get startMarker():string {
    return "startarrow";
  }
  public get endMarker():string {
    return "arrowhead";
  }
  public get startYOffset():number{
    return 0;
  }
  public get endYOffset():number{
    return 0;
  }
  public update(): void {
    this.boundGuiItem.recalculateCoordinates();
    let polys: PointCoords[] = this.boundGuiItem.calculateConnectorPoints();

    let pointsString: string = "";
    if (polys) {
      let validPolys: PointCoords[] = [];
      for (let i = 0; i < polys.length; i++) {
        let point: PointCoords = polys[i];
        if (point && point.x && point.y && !isNaN(point.x) && !isNaN(point.y)) {
          let newPoint:PointCoords={x: point.x,y:point.y};
          if (i==0) {
            newPoint.y+=this.startYOffset;
          }
          if (i==polys.length-1) {
            newPoint.y+=this.endYOffset;
          }
          validPolys.push(newPoint);
        }
      }
      polys = validPolys;
      for (let i = 0; i < polys.length; i++) {
        let yLevel=polys[i].y;
        if (i>0 && i==polys.length-1) {

          if (polys[i-1].x==polys[i].x) {
            let offset=polys[i-1].y>polys[i].y?+3:-3;
            yLevel+=offset;
          }
        }
        pointsString += polys[i].x.toString() + "," + yLevel.toString();
        if (i < polys.length - 1) {
          pointsString += " ";
        }
      }
    }
    this.setAttribute(this.arrow, "class", this.boundGuiItem.constructor.name+(this.boundGuiItem.additionalCSSClass?" "+this.boundGuiItem.additionalCSSClass:""));
    if (pointsString){
      this.setAttribute(this.arrow, "points", pointsString);
    }
    this.setAttribute(this.arrow,"marker-start","url(#"+this.startMarker+")");
    this.setAttribute(this.arrow,"marker-end","url(#"+this.endMarker+")");
  }

}
