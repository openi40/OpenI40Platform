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
import { AbstractSvgComponentRenderer } from '../guirenderers/AbstractSvgComponentRenderer';
import { GuiController } from './GuiController';

export class EdgeConnectorController<BoundType,FromType extends BaseGuiItem,ToType extends BaseGuiItem,EdgeConnectorType extends EdgeConnectorGuiItem<BoundType,FromType,ToType>,EdgeRendererType extends AbstractSvgComponentRenderer<EdgeConnectorType>> extends GuiController<EdgeConnectorType,EdgeRendererType>{

}

