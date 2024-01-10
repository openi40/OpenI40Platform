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


export interface OI40DBProduct { 
    attributesMap?: any;
    averageMinPurchaseQty?: number;
    canBeProducedByScheduler?: boolean;
    canBePurchasedByScheduler?: boolean;
    class1fam1?: string;
    class1fam2?: string;
    class1fam3?: string;
    class2fam1?: string;
    class2fam2?: string;
    class2fam3?: string;
    code?: string;
    description?: string;
    integrationTs?: Date;
    leadTimeDays?: number;
    modifiedTimestamp?: Date;
    mov2purchCoeff?: number;
    movUnity?: string;
    netWeight?: number;
    purchUnity?: string;
    removed?: boolean;
    reorderQty?: number;
}