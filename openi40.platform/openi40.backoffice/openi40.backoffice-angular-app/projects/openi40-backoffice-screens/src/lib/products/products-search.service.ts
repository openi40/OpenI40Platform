import { Injectable } from "@angular/core";
import { OI40DBProduct, Oi40DbProductRepositoryService, Pageable, Sort } from "@openi40/backoffice-api";
import { Observable } from "rxjs";
import { AbstractBasePagedSearch } from "../reusable/abstract-paged-search-service";

@Injectable({ providedIn: "root" })
export class ProductsSearch extends AbstractBasePagedSearch<OI40DBProduct> {
  constructor(private apiProducts: Oi40DbProductRepositoryService) {
    super();
  }
  protected override invokeRemote(searchArgument: { qbe: any; page: { page: number; pageSize: number; }; }): Observable<{ content?: OI40DBProduct[] | undefined; empty?: boolean | undefined; first?: boolean | undefined; last?: boolean | undefined; number?: number | undefined; numberOfElements?: number | undefined; pageable?: Pageable | undefined; size?: number | undefined; sort?: Sort | undefined; totalElements?: number | undefined; totalPages?: number | undefined; }> {
    return this.apiProducts.findByQbePagedPageOI40DBProduct(searchArgument);
  }
 
  
}
