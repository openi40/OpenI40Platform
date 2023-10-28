import { NgModule } from '@angular/core';
import { OI40CoProductItemAutocompleteSearchService, OI40PlantAutocompleteSearchService, OI40ProductAutocompleteSearchService, OI40ProductiveCompanyAutocompleteSearchService, OI40WarehouseAutocompleteSearchService } from './autocomplete-search.service';



@NgModule({
  declarations: [
   
  ],
  imports: [
  ],
  exports: [
    
  ],
  providers:[
    OI40ProductAutocompleteSearchService,
    OI40CoProductItemAutocompleteSearchService,
    OI40ProductiveCompanyAutocompleteSearchService,
    OI40PlantAutocompleteSearchService,
    OI40WarehouseAutocompleteSearchService]
})
export class Openi40BackofficeServicesModule { }
