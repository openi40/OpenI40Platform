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


export interface OI40DBBomItemModel { 
    attributesMap?: any;
    code?: string;
    consumingBatchQty?: number;
    consumingBatchTransferType?: string;
    description?: string;
    integrationTs?: Date;
    modifiedTimestamp?: Date;
    operationCode?: string;
    removed?: boolean;
    requiredProductCode?: string;
    useCoefficient?: number;
    warehouseCode?: string;
}