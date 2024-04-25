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
import { GuiController } from './GuiController';
import { TreeItemGuiItem } from '../guidatamodel/TreeItemGuiItem';
import { TreeItemRenderer } from '../guirenderers/TreeItemRenderer';
export class TreeItemController<BaseDataType,
  TreeItemType extends TreeItemGuiItem<BaseDataType>,
  TreeItemRendererType extends TreeItemRenderer<TreeItemType>>
  extends GuiController<TreeItemType, TreeItemRendererType> {
    protected initializeGuiEventListeners():void {
        this._renderer.anchor.addEventListener("click",this);

    }
}
