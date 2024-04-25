import { NgModule } from '@angular/core';
import { OI40CoProductItemAutocompleteSearchService, OI40DepartmentAutocompleteSearchService, OI40PartnerAutocompleteSearchService, OI40PlantAutocompleteSearchService, OI40ProductAutocompleteSearchService, OI40ProductiveCompanyAutocompleteSearchService, OI40TimesheetMetaInfoAutocompleteSearchService, OI40WarehouseAutocompleteSearchService } from './autocomplete-search.service';



@NgModule({
  declarations: [
   
  ],
  imports: [
  ],
  exports: [
    
  ],
  providers:[
    OI40PartnerAutocompleteSearchService,
    OI40ProductAutocompleteSearchService,
    OI40CoProductItemAutocompleteSearchService,
    OI40ProductiveCompanyAutocompleteSearchService,
    OI40PlantAutocompleteSearchService,
    OI40DepartmentAutocompleteSearchService,
    OI40WarehouseAutocompleteSearchService,
    OI40TimesheetMetaInfoAutocompleteSearchService]
})
export class Openi40BackofficeServicesModule { }
