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
import { OI40DBTimesheetMetaInfoExceptionRule } from './oI40DBTimesheetMetaInfoExceptionRule';
import { Pageable } from './pageable';
import { Sort } from './sort';


export interface PageOI40DBTimesheetMetaInfoExceptionRule { 
    content?: Array<OI40DBTimesheetMetaInfoExceptionRule>;
    empty?: boolean;
    first?: boolean;
    last?: boolean;
    number?: number;
    numberOfElements?: number;
    pageable?: Pageable;
    size?: number;
    sort?: Sort;
    totalElements?: number;
    totalPages?: number;
}
