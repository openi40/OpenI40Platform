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
export * from './apsAlgorithms.service';
import { ApsAlgorithmsService } from './apsAlgorithms.service';
export * from './apsCalendarResource.service';
import { ApsCalendarResourceService } from './apsCalendarResource.service';
export * from './apsCommandResource.service';
import { ApsCommandResourceService } from './apsCommandResource.service';
export * from './apsCycleModelResource.service';
import { ApsCycleModelResourceService } from './apsCycleModelResource.service';
export * from './apsDataList.service';
import { ApsDataListService } from './apsDataList.service';
export * from './apsMaterialAnalisysResource.service';
import { ApsMaterialAnalisysResourceService } from './apsMaterialAnalisysResource.service';
export * from './apsResourcesUsageGraphResource.service';
import { ApsResourcesUsageGraphResourceService } from './apsResourcesUsageGraphResource.service';
export * from './itemLookupResource.service';
import { ItemLookupResourceService } from './itemLookupResource.service';
export * from './plantLookupResource.service';
import { PlantLookupResourceService } from './plantLookupResource.service';
export const APIS = [ApsAlgorithmsService, ApsCalendarResourceService, ApsCommandResourceService, ApsCycleModelResourceService, ApsDataListService, ApsMaterialAnalisysResourceService, ApsResourcesUsageGraphResourceService, ItemLookupResourceService, PlantLookupResourceService];
