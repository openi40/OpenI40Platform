import { OI40DBPurchaseOrder, OI40DBPurchaseOrderLine, OI40DBSalesOrder, OI40DBSalesOrderLine, Oi40DbPurchaseOrderLineRepositoryService, Oi40DbPurchaseOrderRepositoryService, Oi40DbSalesOrderLineRepositoryService, Oi40DbSalesOrderRepositoryService, Pageable, Sort } from "@openi40/backoffice-api";
import { Observable, forkJoin, map } from "rxjs";
import { AbstractBasePagedSearch } from "../reusable/abstract-paged-search-service";
import { Injectable } from "@angular/core";
import { AbstractUIFindByCodeService, AbstractUISaveService, OperationResult, OperationStatus } from "@openi40/backoffice-ui";
import { GenericOrder } from "./generic-order";

@Injectable({ providedIn: "root" })
export class SalesOrdersSearch  extends AbstractBasePagedSearch<OI40DBSalesOrder>  {
   
    public constructor(private salesOrderService:Oi40DbSalesOrderRepositoryService){
        super();
    }
    protected override invokeRemote(searchArgument: { qbe: any; page: { page: number; pageSize: number; }; }): Observable<{ content?: OI40DBSalesOrder[] | undefined; empty?: boolean | undefined; first?: boolean | undefined; last?: boolean | undefined; number?: number | undefined; numberOfElements?: number | undefined; pageable?: Pageable | undefined; size?: number | undefined; sort?: Sort | undefined; totalElements?: number | undefined; totalPages?: number | undefined; }> {
      return  this.salesOrderService.findByQbePagedPageOI40DBSalesOrder(searchArgument);
    }
    
};
@Injectable({ providedIn: "root" })
export class PurchaseOrdersSearch  extends AbstractBasePagedSearch<OI40DBPurchaseOrder>  {
   
    public constructor(private purchaseOrderService:Oi40DbPurchaseOrderRepositoryService){
        super();
    }
    protected override invokeRemote(searchArgument: { qbe: any; page: { page: number; pageSize: number; }; }): Observable<{ content?: OI40DBSalesOrder[] | undefined; empty?: boolean | undefined; first?: boolean | undefined; last?: boolean | undefined; number?: number | undefined; numberOfElements?: number | undefined; pageable?: Pageable | undefined; size?: number | undefined; sort?: Sort | undefined; totalElements?: number | undefined; totalPages?: number | undefined; }> {
      return  this.purchaseOrderService.findByQbePagedPageOI40DBPurchaseOrder(searchArgument);
    }
    
};
@Injectable({providedIn:"root"})
export class FindSalesOrderService extends AbstractUIFindByCodeService<GenericOrder> {
    constructor (
        private salesOrderService:Oi40DbSalesOrderRepositoryService,
        private salesOrderLineService:Oi40DbSalesOrderLineRepositoryService) {
        super();    
    }
    public override findByCode(code: string): Observable<OperationResult<GenericOrder>> {
        const findOrderByCode:Observable<OI40DBSalesOrder>=this.salesOrderService.findByCodeOI40DBSalesOrder(code);
        const findOrderRowsByOrderCode:Observable<OI40DBSalesOrderLine[]>=this.salesOrderLineService.findByQbeListOI40DBSalesOrderLine({orderCode:code});
        const SERVICES=[findOrderByCode,findOrderRowsByOrderCode];
        return forkJoin(SERVICES).pipe(map(returned=> {
            const order:GenericOrder= returned[0] as OI40DBSalesOrder;
            order.rows= returned[1] as OI40DBSalesOrderLine[];
            return {
                data: order,
                status:OperationStatus.SUCCESS
            };
        }))
    }
}
@Injectable({providedIn:"root"}) 
export class SaveSalesOrderService extends AbstractUISaveService<GenericOrder> {
    constructor (
        private salesOrderService:Oi40DbSalesOrderRepositoryService,
        private salesOrderLineService:Oi40DbSalesOrderLineRepositoryService) {
        super();    
    }
    public override save(data: GenericOrder): Observable<OperationResult<GenericOrder>>{
        const rows=data.rows;
        data.rows=undefined;
        const saveOrder:Observable<OI40DBSalesOrder>=this.salesOrderService.updateSingleOI40DBSalesOrder(data);
        const saveOrderRows:Observable<OI40DBSalesOrderLine[]>=this.salesOrderLineService.updateListOI40DBSalesOrderLine(rows as OI40DBSalesOrderLine[]);
        const SERVICES=[saveOrder,saveOrderRows];
        return forkJoin(SERVICES).pipe(map(returned=> {
            const order:GenericOrder= returned[0] as OI40DBSalesOrder;
            order.rows= returned[1] as OI40DBSalesOrderLine[];
            return {
                data: order,
                status:OperationStatus.SUCCESS
            };
        }))
    }
    
    
};
@Injectable({providedIn:"root"})
export class FindPurchaseOrderService extends AbstractUIFindByCodeService<GenericOrder> {
    constructor (
        private salesOrderService:Oi40DbPurchaseOrderRepositoryService,
        private salesOrderLineService:Oi40DbPurchaseOrderLineRepositoryService) {
        super();    
    }
    public override findByCode(code: string): Observable<OperationResult<GenericOrder>> {
        const findOrderByCode:Observable<OI40DBPurchaseOrder>=this.salesOrderService.findByCodeOI40DBPurchaseOrder(code);
        const findOrderRowsByOrderCode:Observable<OI40DBPurchaseOrderLine[]>=this.salesOrderLineService.findByQbeListOI40DBPurchaseOrderLine({orderCode:code});
        const SERVICES=[findOrderByCode,findOrderRowsByOrderCode];
        return forkJoin(SERVICES).pipe(map(returned=> {
            const order:GenericOrder= returned[0] as OI40DBSalesOrder;
            order.rows= returned[1] as OI40DBSalesOrderLine[];
            return {
                data: order,
                status:OperationStatus.SUCCESS
            };
        }))
    }
}
@Injectable({providedIn:"root"}) 
export class SavePurchaseOrderService extends AbstractUISaveService<GenericOrder> {
    constructor (
      private salesOrderService:Oi40DbPurchaseOrderRepositoryService,
      private salesOrderLineService:Oi40DbPurchaseOrderLineRepositoryService) {
        super();    
    }
    public override save(data: GenericOrder): Observable<OperationResult<GenericOrder>>{
        const rows=data.rows;
        data.rows=undefined;
        const saveOrder:Observable<OI40DBPurchaseOrder>=this.salesOrderService.updateSingleOI40DBPurchaseOrder(data);
        const saveOrderRows:Observable<OI40DBPurchaseOrderLine[]>=this.salesOrderLineService.updateListOI40DBPurchaseOrderLine(rows as OI40DBPurchaseOrderLine[]);
        const SERVICES=[saveOrder,saveOrderRows];
        return forkJoin(SERVICES).pipe(map(returned=> {
            const order:GenericOrder= returned[0] as OI40DBSalesOrder;
            order.rows= returned[1] as OI40DBSalesOrderLine[];
            return {
                data: order,
                status:OperationStatus.SUCCESS
            };
        }))
    }
    
    
};