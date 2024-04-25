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
import { Component } from '@angular/core';
import { IconDefinition, IconService } from '@ant-design/icons-angular';
import { ThunderboltOutline } from '@ant-design/icons-angular/icons'
@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'Openi40 open source industry 4.0';
  constructor(private _iconService: IconService) {
    // Import all. NOT RECOMMENDED. â?Œ
    // const antDesignIcons = AllIcons as {
      // [key: string]: IconDefinition;
    // };
    // this._iconService.addIcon(...Object.keys(antDesignIcons).map(key => antDesignIcons[key]));
    // Import what you need! âœ”ï¸?
    this._iconService.addIcon(...[ ThunderboltOutline ]);
    this._iconService.twoToneColor = { primaryColor: '#1890ff' };
  }
}
