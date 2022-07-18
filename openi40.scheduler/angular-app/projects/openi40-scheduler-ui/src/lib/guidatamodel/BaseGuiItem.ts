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
import { PointCoords } from './PointCoords';

export abstract class BaseGuiItem {
  protected _svgElement: Element = null;
  protected _x: number = 0;
  protected _y: number = 0;
  protected _width: number = 0;
  protected _height: number = 0;
  protected _xParentOffset: number = 0;
  protected _yParentOffset: number = 0;
  protected _additionalCSSClass:string=null;
  private _visible:boolean=true;
  public get visible(): boolean{
    return this._visible
  };
  public set visible(b:boolean) {
    this._visible=b;
  }
  private _opened:boolean=true;
  public get opened():boolean {
    return this._opened;
  }
  public set opened(b:boolean) {
    this._opened=b;
  }
  public constructor (public graphicConfig:Openi40GraphicConfiguration){

  }
  public get additionalCSSClass():string {
    return this._additionalCSSClass;
  }
  public get x(): number {
    return Math.round(this._x);
  }
  public set x(v:number) {
    this._x=v;
  }
  public get y(): number {
    return Math.round(this._y);
  }
  public set y(v:number) {
    this._y=v;
  }
  public get width(): number {
    return Math.round(this._width);
  }
  public get height(): number {
    return Math.round(this._height);
  }
  public get xParentOffset(): number {
    return this._xParentOffset;
  }
  public get yParentOffset(): number {
    return this._yParentOffset;
  }
  public get nodeNumber(): number{
    return -1;
  }
  public get depth():number{
    return -1;
  }
  public clickListener:EventListenerOrEventListenerObject=null;
  public abstract get id(): string;
  public abstract get code(): string;
  public abstract get description(): string;
  public abstract get objectType(): string;
  public get visibleLabel() {
    return this.description?this.description:this.code;
  }
  public get svgElement(): Element {
    return this._svgElement;
  }
  public set svgElement(val: Element) {
    this._svgElement = val;
  }
  public recalculateCoordinates():void{

  }
  public toString():string {
    return (this.constructor.name+"{"  + "code="+this.code +" id="+this.id+ " x="+this.x.toString()+" y="+this.y.toString()+" width="+this.width.toString()+" height="+this.height.toString()+"}");

  }
  public get leftTop():PointCoords{
    return {x:this.x,y:this.y};
  }
  public get rightTop():PointCoords{
    return {x:this.x+this.width,y:this.y};
  }
  public get leftBottom():PointCoords{
    return {x:this.x,y:this.y+this.height};
  }
  public get rightBottom():PointCoords{
    return {x:this.x+this.width,y:this.y+this.height};
  }
}

