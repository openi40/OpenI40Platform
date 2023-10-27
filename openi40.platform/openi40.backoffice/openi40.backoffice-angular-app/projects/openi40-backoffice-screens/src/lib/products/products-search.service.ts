import { Injectable } from "@angular/core";
import { OI40DBProduct, Oi40DbProductRepositoryService, PageOI40DBProduct, QbeSupportOI40DBProduct } from "@openi40/backoffice-api";
import { AbstractUIPagedSearchService, Page, PageMeta } from "projects/openi40-backoffice-ui/src/public-api";
import { Observable, of, map } from "rxjs";

@Injectable({ providedIn: "root" })
export class ProductsSearch extends AbstractUIPagedSearchService {
  constructor(private apiProducts: Oi40DbProductRepositoryService) {
    super();
  }
  public override searchPaged(search: any, page: PageMeta): Observable<Page<any>> {
    const qbe: QbeSupportOI40DBProduct = {
      qbe:search,
      page: {
        page:page?.page?page.page:0,
        pageSize:page?.size?page.size:20
      }
    };
    

    return this.apiProducts.findByQbePagedPageOI40DBProduct(qbe).pipe(map((returned:PageOI40DBProduct) => {
      const npage: Page<any> = new Page<any>();
      npage.data = returned?.content?returned?.content:[];
      npage.page=page?.page?page.page:0;
      npage.size=page?.size?page.size:20;
      npage.totalElements=returned?.totalElements?returned?.totalElements:0
      return (npage);
    }))

  }
}
