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
import { NoopAnimationPlayer } from '@angular/animations';
import { Type } from '@angular/core';
import { BaseGuiItem } from './BaseGuiItem';

export class YBlocksRetrieverMap{
  private map:Map<string,Map<string,BaseGuiItem>>=new Map<string,Map<string,BaseGuiItem>>();
  public   put(t:string,s:string,item:BaseGuiItem):void {
    let innerMap:Map<string,BaseGuiItem>=this.map.get(t);
    if (!innerMap){
      innerMap=new Map();
      this.map.set(t,innerMap);
    }
    innerMap.set(s,item);
  }
  public get(t:string,s:string):BaseGuiItem{
    if ( this.map.has(t) && this.map.get(t).has(s)) return  (this.map.get(t).get(s));
    return null;
  }
  protected sum(map1:Map<string,Map<string,BaseGuiItem>>):YBlocksRetrieverMap{
    let rMap:YBlocksRetrieverMap=new YBlocksRetrieverMap();
    let keysArray=Array.from(map1.keys());
    if (keysArray) {
      keysArray.forEach((key)=>{
        let innerMap=map1.get(key);
        let innerKeysArray=Array.from(innerMap.keys());
        innerKeysArray.forEach((itemKey)=>{
          rMap.put(key,itemKey,innerMap.get(itemKey));
        })
      });
    }
    keysArray=Array.from(this.map.keys());
    if (keysArray) {
      keysArray.forEach((key)=>{
        let innerMap=this.map.get(key);
        let innerKeysArray=Array.from(innerMap.keys());
        innerKeysArray.forEach((itemKey)=>{
          rMap.put(key,itemKey,innerMap.get(itemKey));
        })
      });
    }
    return rMap;
  }
  public clone():YBlocksRetrieverMap{
    let r=new YBlocksRetrieverMap();
    return r.sum(this.map);
  }
  public add(m:YBlocksRetrieverMap):YBlocksRetrieverMap{
    let r=this.clone();
    return r.sum(m.map);
  }
}
