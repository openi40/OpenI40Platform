/**
 * 
 * This code is part of the OpenI40 open source advanced production scheduler
 * platform suite, have look to its licencing options.
 * Web site: http://openi40.org/  
 * Github: https://github.com/openi40/OpenI40Platform
 * We hope you enjoy implementing new amazing projects with it.
 * @author architectures@openi40.org
 *
 */
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


export interface SecondaryResourceUseSpecification { 
    afterStartMinutes?: number;
    beforeStopMinutes?: number;
    code?: string;
    description?: string;
    id?: string;
    maxQty?: number;
    minQty?: number;
    qty?: number;
    secondaryResourceGroupCode?: string;
    useType?: SecondaryResourceUseSpecification.UseTypeEnum;
    usedTime?: SecondaryResourceUseSpecification.UsedTimeEnum;
}
export namespace SecondaryResourceUseSpecification {
    export type UseTypeEnum = 'CONSTANT' | 'PROPORTIONAL';
    export const UseTypeEnum = {
        CONSTANT: 'CONSTANT' as UseTypeEnum,
        PROPORTIONAL: 'PROPORTIONAL' as UseTypeEnum
    };
    export type UsedTimeEnum = 'SETUP' | 'WORK' | 'SETUP_WORK';
    export const UsedTimeEnum = {
        SETUP: 'SETUP' as UsedTimeEnum,
        WORK: 'WORK' as UsedTimeEnum,
        SETUPWORK: 'SETUP_WORK' as UsedTimeEnum
    };
}
