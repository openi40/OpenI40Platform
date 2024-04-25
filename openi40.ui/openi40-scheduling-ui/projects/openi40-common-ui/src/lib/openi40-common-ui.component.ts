import { Component, OnInit } from '@angular/core';
import { Openi40CommonUiService, UILoadedConfig } from './openi40-common-ui.service';
import { Router } from '@angular/router';

@Component({
  selector: 'openi40-common-ui',
  templateUrl: "openi40-common-ui.component.html",

})
export class Openi40CommonUiComponent implements OnInit {
  public loadedConfig:UILoadedConfig;
  public loading:boolean=false;
  constructor(private openi40CommonService: Openi40CommonUiService,private router:Router) {

  }
  ngOnInit(): void {
    this.loading=true;
    this.openi40CommonService.getUIConfiguration().subscribe(
      {
        next: (cfg) => {
          this.loadedConfig=cfg;
          this.loading=false;
          if (this.loadedConfig?.backofficeUI===true && this.loadedConfig?.schedulerUI===true) {
            //Remain here
          }else if (this.loadedConfig?.backofficeUI===true) {
            this.router.navigate(["/openi40-backoffice-home"]);
          } else if (this.loadedConfig?.schedulerUI===true) {
            this.router.navigate(["/openi40-scheduler-home"]);
          }
        }, error: (err) => {
          this.loading=false;
        }
      }
    );
  }

}
