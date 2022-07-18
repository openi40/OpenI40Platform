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
import { AbstractSvgComponentRenderer } from '../guirenderers/AbstractSvgComponentRenderer';
import { Renderer2 } from '@angular/core';
import { IControllerCallback } from './IControllerCallback'
import { IGuiController } from './IGuiController';
import { debugOutputAstAsTypeScript } from '@angular/compiler';
export class GuiController<DataType extends BaseGuiItem, RendererType extends AbstractSvgComponentRenderer<DataType>> extends AbstractSvgComponentRenderer<DataType> implements EventListenerObject, IGuiController {

  protected _renderer: RendererType = null;

  public constructor(boundGuiItem: DataType, renderer2: Renderer2, private rendererFactory: (_d: DataType, _r: Renderer2) => RendererType) {
    super(renderer2, boundGuiItem);
    this._renderer = rendererFactory(boundGuiItem, renderer2);
  }
  handleEvent(evt: Event): void {

    this.fireEvent(evt.type, evt);
  }
  public get data(): DataType {
    return this.boundGuiItem;
  }
  public get renderer(): RendererType {
    return this._renderer;
  }
  private callbacks: IControllerCallback[] = [];
  public addControllerCallback(callback: IControllerCallback) {
    this.callbacks.push(callback);
  }
  public fireEvent(eventType: string, _event: Event): void {
    this.callbacks.forEach((cb) => {
      let eTypes: string[] = cb.getTargetEvents();
      if (eTypes) {
        let matches: boolean = false;
        eTypes.forEach((m) => { matches = (m == eventType) || matches });
        if (matches) {
          let objectTypeOk: boolean = false;
          let oTypes: string[] = cb.getTargetObjectTypes();
          if (oTypes) oTypes.forEach((ot) => {
            objectTypeOk = objectTypeOk || ot == this.constructor.name;
          })
          if (objectTypeOk) {
            cb.onEvent(this, _event);
          }
        }

      }
    });
  }
  protected initializeGuiEventListeners(): void {

  }
  protected _rootGeneratedElement: Element = null;
  protected _rootSVGElement:Element=null;
  public show(rootSVGElement: Element): Element {
    this._rootSVGElement=rootSVGElement;

    this.data.recalculateCoordinates();
    this.renderer.setVisibility(this.data.visible);
    if (this.data.visible) {

      if (!this._rootGeneratedElement) {
        this._rootGeneratedElement = this.renderer.create();
        if (this._rootGeneratedElement != null) {
          this.renderer2.appendChild(rootSVGElement, this._rootGeneratedElement);
          this.initializeGuiEventListeners();
        }
      }
      else {
        this.renderer.update();
      }
    }
    return this._rootGeneratedElement;
  }
  public drop(rootSVGElement: Element): void {
    if (this._rootGeneratedElement) {
      this._renderer.drop(rootSVGElement);
    }
  }
  public create(): Element {
    return this._rootGeneratedElement;
  }
  public update(): void {
    this.show(this._rootSVGElement);
  }
}

export class AggregatedController<DataType extends BaseGuiItem, RendererType extends AbstractSvgComponentRenderer<DataType>> extends GuiController<DataType, RendererType>{
  protected childControllers: IGuiController[] = [];
  public show(root: Element): Element {
    let rv = super.show(root);
    if (this.childControllers)
      this.childControllers.forEach((c) => { c.show(root); });
    return rv;
  }
  public drop(root: Element): void {
    let rv = super.drop(root);
    if (this.childControllers)
      this.childControllers.forEach((c) => { c.drop(root); });

  }
}
