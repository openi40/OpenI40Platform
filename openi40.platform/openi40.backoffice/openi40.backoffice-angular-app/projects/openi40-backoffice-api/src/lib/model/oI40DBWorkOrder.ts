/**
 * Openi40
 * The open source industy 4.0 production scheduler & MES platform
 *
 * OpenAPI spec version: 1.0.0
 * 
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 */


export interface OI40DBWorkOrder { 
    attributesMap?: any;
    code?: string;
    color?: string;
    cycleCode?: string;
    deliveryDate?: Date;
    description?: string;
    endExecutionDate?: Date;
    idx?: number;
    integrationTs?: Date;
    maxProductionDateConstraint?: Date;
    minProductionDateConstraint?: Date;
    modifiedTimestamp?: Date;
    plantCode?: string;
    productCode?: string;
    removed?: boolean;
    rootSalesOrderWorkOrder?: boolean;
    salesOrderLineCode?: string;
    startExecutionDate?: Date;
    totalQty?: number;
}
