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
export class PointCoords {
  public x: number = 0;
  public y: number = 0;
  public ToString?():string {
    return "{x:"+this.x+",y:"+this.y+"}";
  }
}
