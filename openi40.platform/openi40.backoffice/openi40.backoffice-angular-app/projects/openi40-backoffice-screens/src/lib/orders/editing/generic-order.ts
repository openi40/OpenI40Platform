export interface GenericOrderRow {
    askedDeliveryDate?: Date;
    attributesMap?: any;
    code?: string;
    color?: string;
    completedQty?: number;
    customPriority?: number;
    departmentCode?: string;
    description?: string;
    integrationTs?: Date;
    lineStatus?: string;
    maxProductionDateConstraint?: Date;
    minProductionDateConstraint?: Date;
    modifiedTimestamp?: Date;
    orderCode?: string;
    orderType?: string;
    plannedDeliveryDate?: Date;
    plantCode?: string;
    productCode?: string;
    removed?: boolean;
    residualQty?: number;
    totalQty?: number;
    warehouseCode?: string;
}
export interface GenericOrder {
    askedDeliveryDate?: Date;
    attributesMap?: any;
    code?: string;
    customPriority?: number;
    departmentCode?: string;
    description?: string;
    integrationTs?: Date;
    modifiedTimestamp?: Date;
    orderStatus?: string;
    orderType?: string;
    partner?: string;
    plannedDeliveryDate?: Date;
    plantCode?: string;
    removed?: boolean;
    rows?:GenericOrderRow[];
}
