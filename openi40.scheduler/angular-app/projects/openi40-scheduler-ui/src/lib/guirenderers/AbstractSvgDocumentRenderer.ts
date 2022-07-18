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
import { Renderer2 } from "@angular/core";
import { BaseGuiItem } from "../guidatamodel/BaseGuiItem";
import { AbstractSvgComponentRenderer } from "./AbstractSvgComponentRenderer";
import { ISvgPatternsProvider } from './ISvgPatternsProvider'
export abstract class AbstractSvgDocumentRenderer<GuiItemType extends BaseGuiItem> extends AbstractSvgComponentRenderer<GuiItemType>{
  constructor(renderer2: Renderer2, boundGuiItem: GuiItemType, private svgPatternsProvider: ISvgPatternsProvider) {
    super(renderer2, boundGuiItem);
  }
  protected assignAttributesAndChilds(rootElement: Element, jsonObject) {
    let attributes = Object.keys(jsonObject);
    if (attributes) {
      attributes.forEach((attribute: string) => {
        let value = jsonObject[attribute];
        if (value) {
          if (Array.isArray(value)) {
            let _items: any[] = Array.from(value);
            if (_items) {
              _items.forEach(item => {
                let _thisElement: Element = this.createElement(attribute);
                this.assignAttributesAndChilds(_thisElement, item);
                this.renderer2.appendChild(rootElement, _thisElement);
              });
            }
          } else
            if (typeof value === "object") {
              let _thisElement: Element = this.createElement(attribute);
              this.assignAttributesAndChilds(_thisElement, value);
              this.renderer2.appendChild(rootElement, _thisElement);
            } else {
              this.setAttribute(rootElement, attribute, value);
            }
        }
      });
    }
  }
  protected objectToElements(jsonObject): Element[] {
    let _out: Element[] = [];
    let attributes = Object.keys(jsonObject);
    if (attributes) {
      attributes.forEach((attribute) => {
        let value = jsonObject[attribute];
        if (value) {
          if (Array.isArray(value)) {
            let _items: any[] = Array.from(value);
            if (_items) {
              _items.forEach(item => {
                let _thisElement: Element = this.createElement(attribute);
                this.assignAttributesAndChilds(_thisElement, item);
                _out.push(_thisElement);
              });
            }
          } else
            if (typeof value === "object") {
              let _thisElement: Element = this.createElement(attribute);
              this.assignAttributesAndChilds(_thisElement, value);
              _out.push(_thisElement);
            }
        }
      });
    }
    return _out;
  }
  protected getPatternsLibrary(): Element[] {
    let jsonPatterns = this.svgPatternsProvider.getPatterns();
    return jsonPatterns ? this.objectToElements(jsonPatterns) : [];
  }
  protected createSvgDocument(guiItem: BaseGuiItem): Element {
    let _svg = this.createBoundElement("svg", guiItem);
    let _defs = this.createElement("defs");
    this.renderer2.appendChild(_svg, _defs);

    let _patterns = this.getPatternsLibrary();
    if (_patterns) {
      _patterns.forEach(pattern => {
        this.renderer2.setAttribute(pattern,"stroke","black");
        this.renderer2.setAttribute(pattern,"stroke-width","0.2px");
        this.renderer2.appendChild(_defs, pattern);
      });
    }
    return _svg;
  }
}
