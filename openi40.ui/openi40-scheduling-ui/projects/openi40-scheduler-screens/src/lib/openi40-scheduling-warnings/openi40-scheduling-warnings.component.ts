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
import { Component, Input, OnChanges, OnInit, SimpleChanges } from '@angular/core';
import { ApsDataDto, ApsMessageDto, TaskDto, WorkOrderDto } from 'projects/openi40-scheduler-api/src/lib';
export class MessageItem{
  task:TaskDto=null;
  order:WorkOrderDto=null;
  message:ApsMessageDto=null;
  public get taskCode() {return this.task.code;}
  public get workOrderCode() {return this.task.workOrderCode;}
  public get sequenceCode() {return this.task.sequenceCode;}
  public get messageCode() {return this.message.messageCode;}
  public get messageCategory() {return this.message.messageCategory;}
  public get messageDescription() {return this.message.messageDescription;}
}
@Component({
  selector: 'openi40-scheduling-warnings',
  templateUrl: './openi40-scheduling-warnings.component.html',
  styleUrls: ['./openi40-scheduling-warnings.component.css']
})
export class Openi40SchedulingWarningsComponent implements OnInit,OnChanges {

  constructor() { }
  @Input("messages") public  messages:MessageItem[]=[];
  @Input("apsData") apsData:ApsDataDto=null;
  private refreshMessages():void {
    this.messages=[];
    this.apsData.productiveCompanies.forEach((pc)=>{
      pc.plants.forEach((plant)=>{
        plant.workOrders.forEach((wo)=>{
            wo.tasks.forEach((task)=>{
                task.messages.forEach((message)=>{
                    let msg:MessageItem=new MessageItem();
                    msg.message=message;
                    msg.task=task;
                    msg.order=wo;
                    this.messages.push(msg);

                });
            });
        });
      });
    });
  }
  ngOnChanges(changes: SimpleChanges): void {
    if (this.apsData) this.refreshMessages();
  }

  ngOnInit(): void {
    if (this.apsData) this.refreshMessages();
  }

}
