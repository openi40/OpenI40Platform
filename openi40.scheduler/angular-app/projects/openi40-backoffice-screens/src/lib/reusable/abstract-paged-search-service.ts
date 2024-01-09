import { Oi40DbProductRepositoryService, PageOI40DBProduct, Pageable, QbeSupportOI40DBProduct, Sort } from "@openi40/backoffice-api";
import { AbstractUIPagedSearchService, Page, PageMeta } from "@openi40/backoffice-ui";
import { Observable, map } from "rxjs";

export abstract class AbstractBasePagedSearch<DetailModelType> extends AbstractUIPagedSearchService {

    protected abstract invokeRemote(searchArgument: { qbe: any, page: { page: number, pageSize: number } }): Observable<{
        content?: Array<DetailModelType>,
        empty?: boolean,
        first?: boolean,
        last?: boolean,
        number?: number,
        numberOfElements?: number,
        pageable?: Pageable,
        size?: number,
        sort?: Sort,
        totalElements?: number,
        totalPages?: number
    }>;
    public override searchPaged(search: any, page: PageMeta): Observable<Page<any>> {
        const qbe: any = {
            qbe: search,
            page: {
                page: page?.page ? page.page : 0,
                pageSize: page?.size ? page.size : 20
            }
        };


        return this.invokeRemote(qbe).pipe(map((returned) => {
            const npage: Page<any> = new Page<any>();
            npage.data = returned?.content ? returned?.content : [];
            npage.page = page?.page ? page.page : 0;
            npage.size = page?.size ? page.size : 20;
            npage.totalElements = returned?.totalElements ? returned?.totalElements : 0
            return (npage);
        }))

    }
}