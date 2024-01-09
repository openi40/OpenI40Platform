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


export interface OI40DBTaskRelation { 
    alignmentType?: string;
    attributesMap?: any;
    bomItemCode?: string;
    code?: string;
    consumerTaskCode?: string;
    consumptionBatchQty?: number;
    consumptionTransferType?: string;
    description?: string;
    integrationTs?: Date;
    modifiedTimestamp?: Date;
    offsetMillisecs?: number;
    peggingCode?: string;
    peggingEdge?: boolean;
    removed?: boolean;
    supplierTaskCode?: string;
    timeRangeType?: string;
}