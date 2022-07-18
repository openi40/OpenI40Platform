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
import { AbstractSvgComponentRenderer } from './AbstractSvgComponentRenderer';
import { TaskGuiItem } from '../guidatamodel/TaskGuiItem';


export class TaskRenderer extends AbstractSvgComponentRenderer<TaskGuiItem>{
    public workElement:Element=null;
    public setupElement:Element=null;
    public label:Element=null;
    public g:Element=null;
    public gWork:Element=null;
    public gSetup:Element=null;
    public anchor:Element=null;
    public workTitle:Element=null;
    public setupTitle:Element=null;
    public create(): Element {
        this.g=this.createBoundElement("g",this.boundGuiItem);
        this.gSetup=this.createElement("g");
        this.gWork=this.createElement("g");
        this.workTitle=this.createElement("title");
        this.workTitle.innerHTML="Work phase "+ this.boundGuiItem.longLabel;
        this.setupTitle=this.createElement("title");
        this.setupTitle.innerHTML="Setup phase "+this.boundGuiItem.longLabel;
        this.boundGuiItem.svgElement=this.g;
        this.anchor=this.createElement("a");
        if (this.boundGuiItem.clickListener)
          this.anchor.addEventListener("click",this.boundGuiItem.clickListener);
        this.setupElement=this.createElement("rect");
        this.workElement=this.createElement("rect");
        this.setAttribute(this.setupElement,"id","TaskSetup"+this.boundGuiItem.id);
        this.setAttribute(this.workElement,"id","TaskWork"+this.boundGuiItem.id);
        var phaseCodes = this.boundGuiItem.data.workOrderCode + "/" + this.boundGuiItem.data.sequenceCode;
        this.setAttribute(this.anchor,"title","Task "+phaseCodes);
        this.setAttribute(this.anchor,"href","javascript:void(0);");
        this.renderer2.appendChild(this.gSetup,this.setupElement);
        this.renderer2.appendChild(this.gWork,this.workElement);
				this.renderer2.appendChild(this.anchor,this.gSetup);
        this.renderer2.appendChild(this.anchor,this.gWork);
        this.renderer2.appendChild(this.g,this.anchor);
        this.renderer2.appendChild(this.gWork,this.workTitle);
        this.renderer2.appendChild(this.gSetup,this.setupTitle);
        if (this.isCanRenderLabel) {
          this.label=this.createLabel();
          this.renderer2.appendChild(this.anchor,this.label);
        }
        this.update();
        return this.g;
    }
    public update(): void {
      var phaseCodes = this.boundGuiItem.data.workOrderCode + "/" + this.boundGuiItem.data.sequenceCode;

       this.adaptElement(this.g,this.boundGuiItem);
       this.setAttribute(this.setupElement,"x", this.boundGuiItem.xStartSetupLeft);
				this.setAttribute(this.setupElement,"y", this.boundGuiItem.y+2);
				this.setAttribute(this.setupElement,"width", (this.boundGuiItem.xStopSetupRight - this.boundGuiItem.xStartSetupLeft));
				this.setAttribute(this.setupElement,"height", this.boundGuiItem.graphicConfig.dimensions.cellHeight - 4);
				this.setAttribute(this.setupElement,"class", "setupStyle");
        //this.setAttribute(this.setupElement,"style", "fill: url(#setuppattern);fill-opacity: 0.5;background-color:"+this.boundGuiItem?.data?.color);
				this.setAttribute(this.setupElement,"title", "Setup " + phaseCodes);
        this.setAttribute(this.setupElement,"rx","4");
				this.setAttribute(this.workElement,"x", this.boundGuiItem.xStartWorkLeft);
				this.setAttribute(this.workElement,"y", this.boundGuiItem.y + 2);
        this.setAttribute(this.workElement,"rx", "4");
				this.setAttribute(this.workElement,"width",	(this.boundGuiItem.xStopWorkRight - this.boundGuiItem.xStartWorkLeft));
				this.setAttribute(this.workElement,"height", this.boundGuiItem.graphicConfig.dimensions.cellHeight - 4);
				this.setAttribute(this.workElement,"class", "workStyle");
        this.setAttribute(this.workElement,"title", "Work " + phaseCodes);
        if (this.isCanRenderLabel && this.label) {
          this.updateLabel(this.label);
      }
        if (this.boundGuiItem?.data?.color) {
          this.setAttribute(this.setupElement,"fill", this.boundGuiItem?.data?.color);
          this.setAttribute(this.workElement,"fill",this.boundGuiItem?.data?.color);
        }else {
          this.renderer2.removeAttribute(this.setupElement,"fill");
          this.renderer2.removeAttribute(this.workElement,"fill");
        }
        //this.workTitle.innerHTML="code: "+this.boundGuiItem.data.code+" description: "+this.boundGuiItem.data.description;
    }
    public drop(rootElement:Element):void{
      this.renderer2.removeChild(rootElement,this.g);
      this.setupElement=null;
      this.workElement=null;
      this.anchor=null;
      this.g=null;
    }
}
