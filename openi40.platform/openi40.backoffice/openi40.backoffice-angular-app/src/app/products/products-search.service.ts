import { Injectable } from "@angular/core";
import { OI40DBProduct, Oi40DbProductRepositoryService } from "@openi40/backoffice-api";
import { AbstractUIPagedSearchService, Page, PageMeta } from "projects/openi40-backoffice-ui/src/public-api";
import { Observable, of, map } from "rxjs";

@Injectable({ providedIn: "root" })
export class ProductsSearch extends AbstractUIPagedSearchService {
  constructor(private apiProducts: Oi40DbProductRepositoryService) {
    super();
  }
  public override searchPaged(search: any, page: PageMeta): Observable<Page<any>> {
    const qbe: OI40DBProduct = {

    };
    const offset: number = 0;
    const pageNumber: number = 0;
    const pageSize: number = 0;
    const paged: boolean = true;

    return this.apiProducts.findByQbePagedPage16(qbe, offset, pageNumber, pageSize, paged).pipe(map(returned => {
      const npage: Page<any> = new Page<any>();
      npage.data = returned?.content?returned?.content:[];
      npage.page=returned?.number?returned?.number:0;
      npage.size=returned?.totalElements?returned?.totalElements:0;
      return (npage);
    }))

  }
}
