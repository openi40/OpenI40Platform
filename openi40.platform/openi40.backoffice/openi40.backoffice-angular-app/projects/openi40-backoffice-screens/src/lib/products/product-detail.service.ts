import { Injectable } from "@angular/core";
import { OI40DBProduct, Oi40DbProductRepositoryService } from "@openi40/backoffice-api";
import { AbstractUIFindByCodeService, AbstractUISaveService, OperationResult, OperationStatus } from "projects/openi40-backoffice-ui/src/public-api";
import { Observable, map, of } from "rxjs";

@Injectable({ providedIn: "root" })
export class ProductFindByCodeService extends AbstractUIFindByCodeService<any> {
  constructor(private apiProducts: Oi40DbProductRepositoryService) {
    super();
  }
  public override findByCode(_code: string): Observable<OperationResult<any>> {
    
    return this.apiProducts.findByCodeOI40DBProduct(_code).pipe(map(returned=>{
      const object:OperationResult<OI40DBProduct>={
        data:returned,
        status:OperationStatus.SUCCESS,
        msgs:[]
      };
      return object;
    }));
  }

}
@Injectable({ providedIn: "root" })
export class ProductSaveService extends AbstractUISaveService<any> {
  constructor(private apiProducts: Oi40DbProductRepositoryService) {
    super();
  }
  public override save(data: any): Observable<OperationResult<any>> {
    return this.apiProducts.updateSingleOI40DBProduct(data).pipe(map(data=>{const ors: OperationResult<any> = {
      status: OperationStatus.SUCCESS,
      data: data
    };
    return ors;}));
    
  }


}
