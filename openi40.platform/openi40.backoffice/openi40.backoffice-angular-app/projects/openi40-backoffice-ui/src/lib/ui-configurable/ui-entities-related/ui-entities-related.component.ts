import { Component, Input, OnChanges, OnInit, SimpleChanges } from "@angular/core";
import { UIRelatedEntities, UISearchParams } from "../ui-meta-description/ui-meta-description";
import { MenuItem, MenuItemCommandEvent } from "primeng/api";
import { ActivatedRoute, Router } from "@angular/router";

@Component(
    {
        selector:"ui-entities-related",
        templateUrl:"ui-entities-related.component.html"
    }
)
export class UIEntitesRelatedComponent implements OnInit,OnChanges{
    @Input() entitiesReferences:UIRelatedEntities[]=[];
    @Input() currentObject?:any;
    public disabled:boolean=true;
    public modalOpened:boolean=false;
    public contextualMenu:MenuItem[]=[];
    constructor(private router:Router,private activatedRoute:ActivatedRoute) {

    }
    ngOnInit(): void {
        
    }
    ngOnChanges(changes: SimpleChanges): void {
        if (changes["entitiesReferences"]) {
            this.disabled= (this.entitiesReferences) ?false:true;
            let items:MenuItem[]=[];
            if (this.entitiesReferences && this.currentObject) {
                this.entitiesReferences.forEach((entry,index)=>{
                    const menuEntry:MenuItem={
                        automationId:(index+1),
                        title:entry.description,
                        label:entry.description,
                        command:(event:MenuItemCommandEvent)=>{
                            const actualAttribute:string=entry.actualAttribute?entry.actualAttribute:"code";
                            const startingAttributeValue=this.currentObject[actualAttribute];
                            const searchParams:UISearchParams={
                                lockSearchControl:true,
                                searchQbe:{}
                            };
                            searchParams.searchQbe[entry.targetAttribute]=startingAttributeValue;
                            if (entry.openModal===true && entry.outlet) {
                                const outlet:string=entry.outlet;
                                this.modalOpened=true;
                                const attrs:any={};
                                attrs[outlet]=entry.relativeTargetUri;
                                const routingInfos=[{outlets:attrs}];                                
                                this.router.navigate(routingInfos,{
                                    relativeTo:this.activatedRoute,
                                    state:{
                                        forceSearch:searchParams
                                    }
                                });
                            }else {
                                this.modalOpened=false;
                                this.router.navigate([entry.relativeTargetUri],{
                                    relativeTo:this.activatedRoute,
                                    state:{
                                        forceSearch:searchParams
                                    }
                                });
                            }
                            
                            
                        }
                    };
                });
            }
            this.contextualMenu=items;
        }
    }

}