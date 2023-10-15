import { NgModule, ModuleWithProviders, SkipSelf, Optional } from '@angular/core';
import { Configuration } from './configuration';
import { HttpClient } from '@angular/common/http';


import { Oi40DbApsSchedulingSetRepositoryService } from './api/oi40DbApsSchedulingSetRepository.service';
import { Oi40DbApsWindowRepositoryService } from './api/oi40DbApsWindowRepository.service';
import { Oi40DbBomItemModelRepositoryService } from './api/oi40DbBomItemModelRepository.service';
import { Oi40DbChangeOverMatrixItemRepositoryService } from './api/oi40DbChangeOverMatrixItemRepository.service';
import { Oi40DbCoProductItemRepositoryService } from './api/oi40DbCoProductItemRepository.service';
import { Oi40DbCycleModelRepositoryService } from './api/oi40DbCycleModelRepository.service';
import { Oi40DbDepartmentRepositoryService } from './api/oi40DbDepartmentRepository.service';
import { Oi40DbMachineEquipmentSpecificationRepositoryService } from './api/oi40DbMachineEquipmentSpecificationRepository.service';
import { Oi40DbMachinePriorityRepositoryService } from './api/oi40DbMachinePriorityRepository.service';
import { Oi40DbMachineRepositoryService } from './api/oi40DbMachineRepository.service';
import { Oi40DbOperationEquipmentSpecificationRepositoryService } from './api/oi40DbOperationEquipmentSpecificationRepository.service';
import { Oi40DbOperationModelRepositoryService } from './api/oi40DbOperationModelRepository.service';
import { Oi40DbPartnerRepositoryService } from './api/oi40DbPartnerRepository.service';
import { Oi40DbPeggingRepositoryService } from './api/oi40DbPeggingRepository.service';
import { Oi40DbPlantProductSettingRepositoryService } from './api/oi40DbPlantProductSettingRepository.service';
import { Oi40DbPlantRepositoryService } from './api/oi40DbPlantRepository.service';
import { Oi40DbProductRepositoryService } from './api/oi40DbProductRepository.service';
import { Oi40DbProductiveCompanyProductSettingRepositoryService } from './api/oi40DbProductiveCompanyProductSettingRepository.service';
import { Oi40DbProductiveCompanyRepositoryService } from './api/oi40DbProductiveCompanyRepository.service';
import { Oi40DbPurchaseOrderLineRepositoryService } from './api/oi40DbPurchaseOrderLineRepository.service';
import { Oi40DbPurchaseOrderRepositoryService } from './api/oi40DbPurchaseOrderRepository.service';
import { Oi40DbResourceGroupRepositoryService } from './api/oi40DbResourceGroupRepository.service';
import { Oi40DbSalesOrderLineRepositoryService } from './api/oi40DbSalesOrderLineRepository.service';
import { Oi40DbSalesOrderRepositoryService } from './api/oi40DbSalesOrderRepository.service';
import { Oi40DbScheduledWorkOrderRepositoryService } from './api/oi40DbScheduledWorkOrderRepository.service';
import { Oi40DbSecondaryResourceRepositoryService } from './api/oi40DbSecondaryResourceRepository.service';
import { Oi40DbSecondaryResourceUseSpecificationRepositoryService } from './api/oi40DbSecondaryResourceUseSpecificationRepository.service';
import { Oi40DbStockSupplyRepositoryService } from './api/oi40DbStockSupplyRepository.service';
import { Oi40DbTaskProductionMaterialReservationRepositoryService } from './api/oi40DbTaskProductionMaterialReservationRepository.service';
import { Oi40DbTaskPurchaseMaterialReservationRepositoryService } from './api/oi40DbTaskPurchaseMaterialReservationRepository.service';
import { Oi40DbTaskRelationRepositoryService } from './api/oi40DbTaskRelationRepository.service';
import { Oi40DbTaskRepositoryService } from './api/oi40DbTaskRepository.service';
import { Oi40DbTaskResourceReservationRepositoryService } from './api/oi40DbTaskResourceReservationRepository.service';
import { Oi40DbTaskStockMaterialReservationRepositoryService } from './api/oi40DbTaskStockMaterialReservationRepository.service';
import { Oi40DbTimesheetMetaInfoExceptionRuleRepositoryService } from './api/oi40DbTimesheetMetaInfoExceptionRuleRepository.service';
import { Oi40DbTimesheetMetaInfoRepositoryService } from './api/oi40DbTimesheetMetaInfoRepository.service';
import { Oi40DbTimesheetMetaInfoWorkingTimeRuleRepositoryService } from './api/oi40DbTimesheetMetaInfoWorkingTimeRuleRepository.service';
import { Oi40DbWarehouseProductSettingRepositoryService } from './api/oi40DbWarehouseProductSettingRepository.service';
import { Oi40DbWarehouseRepositoryService } from './api/oi40DbWarehouseRepository.service';
import { Oi40DbWorkCenterRepositoryService } from './api/oi40DbWorkCenterRepository.service';
import { Oi40DbWorkOrderRepositoryService } from './api/oi40DbWorkOrderRepository.service';

@NgModule({
  imports:      [],
  declarations: [],
  exports:      [],
  providers: [
    Oi40DbApsSchedulingSetRepositoryService,
    Oi40DbApsWindowRepositoryService,
    Oi40DbBomItemModelRepositoryService,
    Oi40DbChangeOverMatrixItemRepositoryService,
    Oi40DbCoProductItemRepositoryService,
    Oi40DbCycleModelRepositoryService,
    Oi40DbDepartmentRepositoryService,
    Oi40DbMachineEquipmentSpecificationRepositoryService,
    Oi40DbMachinePriorityRepositoryService,
    Oi40DbMachineRepositoryService,
    Oi40DbOperationEquipmentSpecificationRepositoryService,
    Oi40DbOperationModelRepositoryService,
    Oi40DbPartnerRepositoryService,
    Oi40DbPeggingRepositoryService,
    Oi40DbPlantProductSettingRepositoryService,
    Oi40DbPlantRepositoryService,
    Oi40DbProductRepositoryService,
    Oi40DbProductiveCompanyProductSettingRepositoryService,
    Oi40DbProductiveCompanyRepositoryService,
    Oi40DbPurchaseOrderLineRepositoryService,
    Oi40DbPurchaseOrderRepositoryService,
    Oi40DbResourceGroupRepositoryService,
    Oi40DbSalesOrderLineRepositoryService,
    Oi40DbSalesOrderRepositoryService,
    Oi40DbScheduledWorkOrderRepositoryService,
    Oi40DbSecondaryResourceRepositoryService,
    Oi40DbSecondaryResourceUseSpecificationRepositoryService,
    Oi40DbStockSupplyRepositoryService,
    Oi40DbTaskProductionMaterialReservationRepositoryService,
    Oi40DbTaskPurchaseMaterialReservationRepositoryService,
    Oi40DbTaskRelationRepositoryService,
    Oi40DbTaskRepositoryService,
    Oi40DbTaskResourceReservationRepositoryService,
    Oi40DbTaskStockMaterialReservationRepositoryService,
    Oi40DbTimesheetMetaInfoExceptionRuleRepositoryService,
    Oi40DbTimesheetMetaInfoRepositoryService,
    Oi40DbTimesheetMetaInfoWorkingTimeRuleRepositoryService,
    Oi40DbWarehouseProductSettingRepositoryService,
    Oi40DbWarehouseRepositoryService,
    Oi40DbWorkCenterRepositoryService,
    Oi40DbWorkOrderRepositoryService ]
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
