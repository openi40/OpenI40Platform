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
import { HourDto } from './hourDto';


export interface TurnDto { 
    code?: string;
    date?: number;
    description?: string;
    endDateTime?: Date;
    endShortDate?: string;
    endShortDateTime?: string;
    endShortTime?: string;
    hours?: Array<HourDto>;
    id?: string;
    startDateTime?: Date;
    startShortDate?: string;
    startShortDateTime?: string;
    startShortTime?: string;
    utcEndDateTime?: number;
    utcStartDateTime?: number;
    week?: number;
}
