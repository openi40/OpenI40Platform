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
import { NgModule, ModuleWithProviders, SkipSelf, Optional } from '@angular/core';
import { Configuration } from './configuration';
import { HttpClient } from '@angular/common/http';


import { ApsAlgorithmsService } from './api/apsAlgorithms.service';
import { ApsCalendarResourceService } from './api/apsCalendarResource.service';
import { ApsCommandResourceService } from './api/apsCommandResource.service';
import { ApsCycleModelResourceService } from './api/apsCycleModelResource.service';
import { ApsDataListService } from './api/apsDataList.service';
import { ApsMaterialAnalisysResourceService } from './api/apsMaterialAnalisysResource.service';
import { ApsResourcesUsageGraphResourceService } from './api/apsResourcesUsageGraphResource.service';
import { ItemLookupResourceService } from './api/itemLookupResource.service';
import { PlantLookupResourceService } from './api/plantLookupResource.service';

@NgModule({
  imports:      [],
  declarations: [],
  exports:      [],
  providers: [
    ApsAlgorithmsService,
    ApsCalendarResourceService,
    ApsCommandResourceService,
    ApsCycleModelResourceService,
    ApsDataListService,
    ApsMaterialAnalisysResourceService,
    ApsResourcesUsageGraphResourceService,
    ItemLookupResourceService,
    PlantLookupResourceService ]
})
export class ApiModule {
    public static forRoot(configurationFactory: () => Configuration): ModuleWithProviders<ApiModule> {
        return {
            ngModule: ApiModule,
            providers: [ { provide: Configuration, useFactory: configurationFactory } ]
        };
    }

    constructor( @Optional() @SkipSelf() parentModule: ApiModule,
                 @Optional() http: HttpClient) {
        if (parentModule) {
            throw new Error('ApiModule is already loaded. Import in your base AppModule only.');
        }
        if (!http) {
            throw new Error('You need to import the HttpClientModule in your AppModule! \n' +
            'See also https://github.com/angular/angular/issues/20575');
        }
    }
}
