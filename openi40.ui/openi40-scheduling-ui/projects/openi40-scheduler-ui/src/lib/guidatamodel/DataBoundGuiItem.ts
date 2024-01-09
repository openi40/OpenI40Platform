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

export class DataBoundGuiItem<BoundData extends { id?: string; code?: string; description?: string; }> extends BaseGuiItem {


  public constructor(protected dataItem: BoundData,graphicConfig:Openi40GraphicConfiguration) {
    super(graphicConfig);
  }
  public get data(): BoundData {
    return this.dataItem;
  }
  public get id(): string {
    return this.dataItem.id;
  }
  public get code(): string {
    return this.dataItem.code;
  }
  public get description(): string {
    return this.dataItem.description;
  }
  public get objectType(): string {
    return this.data.constructor.name;
  }

}

