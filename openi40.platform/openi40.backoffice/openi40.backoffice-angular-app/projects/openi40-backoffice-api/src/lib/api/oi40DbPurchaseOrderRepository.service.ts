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
/* tslint:disable:no-unused-variable member-ordering */

import { Inject, Injectable, Optional }                      from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams,
         HttpResponse, HttpEvent }                           from '@angular/common/http';
import { CustomHttpUrlEncodingCodec }                        from '../encoder';

import { Observable }                                        from 'rxjs';

import { LookupData } from '../model/lookupData';
import { OI40DBPurchaseOrder } from '../model/oI40DBPurchaseOrder';
import { PageInfo } from '../model/pageInfo';
import { PageOI40DBPurchaseOrder } from '../model/pageOI40DBPurchaseOrder';
import { QbeSupportOI40DBPurchaseOrder } from '../model/qbeSupportOI40DBPurchaseOrder';

import { BASE_PATH, COLLECTION_FORMATS }                     from '../variables';
import { Configuration }                                     from '../configuration';


@Injectable()
export class Oi40DbPurchaseOrderRepositoryService {

    protected basePath = 'https://localhost:8083/openi40-backoffice';
    public defaultHeaders = new HttpHeaders();
    public configuration = new Configuration();

    constructor(protected httpClient: HttpClient, @Optional()@Inject(BASE_PATH) basePath: string, @Optional() configuration: Configuration) {
        if (basePath) {
            this.basePath = basePath;
        }
        if (configuration) {
            this.configuration = configuration;
            this.basePath = basePath || configuration.basePath || this.basePath;
        }
    }

    /**
     * @param consumes string[] mime-types
     * @return true: consumes contains 'multipart/form-data', false: otherwise
     */
    private canConsumeForm(consumes: string[]): boolean {
        const form = 'multipart/form-data';
        for (const consume of consumes) {
            if (form === consume) {
                return true;
            }
        }
        return false;
    }


    /**
     * deleteByCode
     * 
     * @param code code
     * @param observe set whether or not to return the data Observable as the body, response or events. defaults to returning the body.
     * @param reportProgress flag to report request and response progress.
     */
    public deleteByCodeVoid20(code: string, observe?: 'body', reportProgress?: boolean): Observable<any>;
    public deleteByCodeVoid20(code: string, observe?: 'response', reportProgress?: boolean): Observable<HttpResponse<any>>;
    public deleteByCodeVoid20(code: string, observe?: 'events', reportProgress?: boolean): Observable<HttpEvent<any>>;
    public deleteByCodeVoid20(code: string, observe: any = 'body', reportProgress: boolean = false ): Observable<any> {

        if (code === null || code === undefined) {
            throw new Error('Required parameter code was null or undefined when calling deleteByCodeVoid20.');
        }

        let headers = this.defaultHeaders;

        // to determine the Accept header
        let httpHeaderAccepts: string[] = [
            '*/*'
        ];
        const httpHeaderAcceptSelected: string | undefined = this.configuration.selectHeaderAccept(httpHeaderAccepts);
        if (httpHeaderAcceptSelected != undefined) {
            headers = headers.set('Accept', httpHeaderAcceptSelected);
        }

        // to determine the Content-Type header
        const consumes: string[] = [
        ];

        return this.httpClient.get<any>(`${this.basePath}/integration/OI40DBPurchaseOrder/deleteByCode/${encodeURIComponent(String(code))}`,
            {
                withCredentials: this.configuration.withCredentials,
                headers: headers,
                observe: observe,
                reportProgress: reportProgress
            }
        );
    }

    /**
     * deleteByCodes
     * 
     * @param codes codes
     * @param observe set whether or not to return the data Observable as the body, response or events. defaults to returning the body.
     * @param reportProgress flag to report request and response progress.
     */
    public deleteByCodesVoid20(codes: Array<string>, observe?: 'body', reportProgress?: boolean): Observable<any>;
    public deleteByCodesVoid20(codes: Array<string>, observe?: 'response', reportProgress?: boolean): Observable<HttpResponse<any>>;
    public deleteByCodesVoid20(codes: Array<string>, observe?: 'events', reportProgress?: boolean): Observable<HttpEvent<any>>;
    public deleteByCodesVoid20(codes: Array<string>, observe: any = 'body', reportProgress: boolean = false ): Observable<any> {

        if (codes === null || codes === undefined) {
            throw new Error('Required parameter codes was null or undefined when calling deleteByCodesVoid20.');
        }

        let headers = this.defaultHeaders;

        // to determine the Accept header
        let httpHeaderAccepts: string[] = [
            '*/*'
        ];
        const httpHeaderAcceptSelected: string | undefined = this.configuration.selectHeaderAccept(httpHeaderAccepts);
        if (httpHeaderAcceptSelected != undefined) {
            headers = headers.set('Accept', httpHeaderAcceptSelected);
        }

        // to determine the Content-Type header
        const consumes: string[] = [
            'application/json'
        ];
        const httpContentTypeSelected: string | undefined = this.configuration.selectHeaderContentType(consumes);
        if (httpContentTypeSelected != undefined) {
            headers = headers.set('Content-Type', httpContentTypeSelected);
        }

        return this.httpClient.post<any>(`${this.basePath}/integration/OI40DBPurchaseOrder/deleteByCodes`,
            codes,
            {
                withCredentials: this.configuration.withCredentials,
                headers: headers,
                observe: observe,
                reportProgress: reportProgress
            }
        );
    }

    /**
     * doLookup
     * 
     * @param lookup lookup
     * @param observe set whether or not to return the data Observable as the body, response or events. defaults to returning the body.
     * @param reportProgress flag to report request and response progress.
     */
    public doLookupPageOI40DBPurchaseOrder(lookup: LookupData, observe?: 'body', reportProgress?: boolean): Observable<PageOI40DBPurchaseOrder>;
    public doLookupPageOI40DBPurchaseOrder(lookup: LookupData, observe?: 'response', reportProgress?: boolean): Observable<HttpResponse<PageOI40DBPurchaseOrder>>;
    public doLookupPageOI40DBPurchaseOrder(lookup: LookupData, observe?: 'events', reportProgress?: boolean): Observable<HttpEvent<PageOI40DBPurchaseOrder>>;
    public doLookupPageOI40DBPurchaseOrder(lookup: LookupData, observe: any = 'body', reportProgress: boolean = false ): Observable<any> {

        if (lookup === null || lookup === undefined) {
            throw new Error('Required parameter lookup was null or undefined when calling doLookupPageOI40DBPurchaseOrder.');
        }

        let headers = this.defaultHeaders;

        // to determine the Accept header
        let httpHeaderAccepts: string[] = [
            '*/*'
        ];
        const httpHeaderAcceptSelected: string | undefined = this.configuration.selectHeaderAccept(httpHeaderAccepts);
        if (httpHeaderAcceptSelected != undefined) {
            headers = headers.set('Accept', httpHeaderAcceptSelected);
        }

        // to determine the Content-Type header
        const consumes: string[] = [
            'application/json'
        ];
        const httpContentTypeSelected: string | undefined = this.configuration.selectHeaderContentType(consumes);
        if (httpContentTypeSelected != undefined) {
            headers = headers.set('Content-Type', httpContentTypeSelected);
        }

        return this.httpClient.post<PageOI40DBPurchaseOrder>(`${this.basePath}/integration/OI40DBPurchaseOrder/doLookup`,
            lookup,
            {
                withCredentials: this.configuration.withCredentials,
                headers: headers,
                observe: observe,
                reportProgress: reportProgress
            }
        );
    }

    /**
     * findAll
     * 
     * @param observe set whether or not to return the data Observable as the body, response or events. defaults to returning the body.
     * @param reportProgress flag to report request and response progress.
     */
    public findAllListOI40DBPurchaseOrder(observe?: 'body', reportProgress?: boolean): Observable<Array<OI40DBPurchaseOrder>>;
    public findAllListOI40DBPurchaseOrder(observe?: 'response', reportProgress?: boolean): Observable<HttpResponse<Array<OI40DBPurchaseOrder>>>;
    public findAllListOI40DBPurchaseOrder(observe?: 'events', reportProgress?: boolean): Observable<HttpEvent<Array<OI40DBPurchaseOrder>>>;
    public findAllListOI40DBPurchaseOrder(observe: any = 'body', reportProgress: boolean = false ): Observable<any> {

        let headers = this.defaultHeaders;

        // to determine the Accept header
        let httpHeaderAccepts: string[] = [
            'application/json'
        ];
        const httpHeaderAcceptSelected: string | undefined = this.configuration.selectHeaderAccept(httpHeaderAccepts);
        if (httpHeaderAcceptSelected != undefined) {
            headers = headers.set('Accept', httpHeaderAcceptSelected);
        }

        // to determine the Content-Type header
        const consumes: string[] = [
        ];

        return this.httpClient.get<Array<OI40DBPurchaseOrder>>(`${this.basePath}/integration/OI40DBPurchaseOrder`,
            {
                withCredentials: this.configuration.withCredentials,
                headers: headers,
                observe: observe,
                reportProgress: reportProgress
            }
        );
    }

    /**
     * findAll
     * 
     * @param p p
     * @param observe set whether or not to return the data Observable as the body, response or events. defaults to returning the body.
     * @param reportProgress flag to report request and response progress.
     */
    public findAllPageOI40DBPurchaseOrder(p: PageInfo, observe?: 'body', reportProgress?: boolean): Observable<PageOI40DBPurchaseOrder>;
    public findAllPageOI40DBPurchaseOrder(p: PageInfo, observe?: 'response', reportProgress?: boolean): Observable<HttpResponse<PageOI40DBPurchaseOrder>>;
    public findAllPageOI40DBPurchaseOrder(p: PageInfo, observe?: 'events', reportProgress?: boolean): Observable<HttpEvent<PageOI40DBPurchaseOrder>>;
    public findAllPageOI40DBPurchaseOrder(p: PageInfo, observe: any = 'body', reportProgress: boolean = false ): Observable<any> {

        if (p === null || p === undefined) {
            throw new Error('Required parameter p was null or undefined when calling findAllPageOI40DBPurchaseOrder.');
        }

        let headers = this.defaultHeaders;

        // to determine the Accept header
        let httpHeaderAccepts: string[] = [
            'application/json'
        ];
        const httpHeaderAcceptSelected: string | undefined = this.configuration.selectHeaderAccept(httpHeaderAccepts);
        if (httpHeaderAcceptSelected != undefined) {
            headers = headers.set('Accept', httpHeaderAcceptSelected);
        }

        // to determine the Content-Type header
        const consumes: string[] = [
            'application/json'
        ];
        const httpContentTypeSelected: string | undefined = this.configuration.selectHeaderContentType(consumes);
        if (httpContentTypeSelected != undefined) {
            headers = headers.set('Content-Type', httpContentTypeSelected);
        }

        return this.httpClient.post<PageOI40DBPurchaseOrder>(`${this.basePath}/integration/OI40DBPurchaseOrder/findAllPaged`,
            p,
            {
                withCredentials: this.configuration.withCredentials,
                headers: headers,
                observe: observe,
                reportProgress: reportProgress
            }
        );
    }

    /**
     * findByAfterIntegrationTs
     * 
     * @param ts ts
     * @param observe set whether or not to return the data Observable as the body, response or events. defaults to returning the body.
     * @param reportProgress flag to report request and response progress.
     */
    public findByAfterIntegrationTsListOI40DBPurchaseOrder(ts: string, observe?: 'body', reportProgress?: boolean): Observable<Array<OI40DBPurchaseOrder>>;
    public findByAfterIntegrationTsListOI40DBPurchaseOrder(ts: string, observe?: 'response', reportProgress?: boolean): Observable<HttpResponse<Array<OI40DBPurchaseOrder>>>;
    public findByAfterIntegrationTsListOI40DBPurchaseOrder(ts: string, observe?: 'events', reportProgress?: boolean): Observable<HttpEvent<Array<OI40DBPurchaseOrder>>>;
    public findByAfterIntegrationTsListOI40DBPurchaseOrder(ts: string, observe: any = 'body', reportProgress: boolean = false ): Observable<any> {

        if (ts === null || ts === undefined) {
            throw new Error('Required parameter ts was null or undefined when calling findByAfterIntegrationTsListOI40DBPurchaseOrder.');
        }

        let headers = this.defaultHeaders;

        // to determine the Accept header
        let httpHeaderAccepts: string[] = [
            '*/*'
        ];
        const httpHeaderAcceptSelected: string | undefined = this.configuration.selectHeaderAccept(httpHeaderAccepts);
        if (httpHeaderAcceptSelected != undefined) {
            headers = headers.set('Accept', httpHeaderAcceptSelected);
        }

        // to determine the Content-Type header
        const consumes: string[] = [
        ];

        return this.httpClient.get<Array<OI40DBPurchaseOrder>>(`${this.basePath}/integration/OI40DBPurchaseOrder/integratedAfter/${encodeURIComponent(String(ts))}`,
            {
                withCredentials: this.configuration.withCredentials,
                headers: headers,
                observe: observe,
                reportProgress: reportProgress
            }
        );
    }

    /**
     * findByAfterModifiedTimestamp
     * 
     * @param ts ts
     * @param observe set whether or not to return the data Observable as the body, response or events. defaults to returning the body.
     * @param reportProgress flag to report request and response progress.
     */
    public findByAfterModifiedTimestampListOI40DBPurchaseOrder(ts: string, observe?: 'body', reportProgress?: boolean): Observable<Array<OI40DBPurchaseOrder>>;
    public findByAfterModifiedTimestampListOI40DBPurchaseOrder(ts: string, observe?: 'response', reportProgress?: boolean): Observable<HttpResponse<Array<OI40DBPurchaseOrder>>>;
    public findByAfterModifiedTimestampListOI40DBPurchaseOrder(ts: string, observe?: 'events', reportProgress?: boolean): Observable<HttpEvent<Array<OI40DBPurchaseOrder>>>;
    public findByAfterModifiedTimestampListOI40DBPurchaseOrder(ts: string, observe: any = 'body', reportProgress: boolean = false ): Observable<any> {

        if (ts === null || ts === undefined) {
            throw new Error('Required parameter ts was null or undefined when calling findByAfterModifiedTimestampListOI40DBPurchaseOrder.');
        }

        let headers = this.defaultHeaders;

        // to determine the Accept header
        let httpHeaderAccepts: string[] = [
            '*/*'
        ];
        const httpHeaderAcceptSelected: string | undefined = this.configuration.selectHeaderAccept(httpHeaderAccepts);
        if (httpHeaderAcceptSelected != undefined) {
            headers = headers.set('Accept', httpHeaderAcceptSelected);
        }

        // to determine the Content-Type header
        const consumes: string[] = [
        ];

        return this.httpClient.get<Array<OI40DBPurchaseOrder>>(`${this.basePath}/integration/OI40DBPurchaseOrder/modifiedAfter/${encodeURIComponent(String(ts))}`,
            {
                withCredentials: this.configuration.withCredentials,
                headers: headers,
                observe: observe,
                reportProgress: reportProgress
            }
        );
    }

    /**
     * findByCode
     * 
     * @param code code
     * @param observe set whether or not to return the data Observable as the body, response or events. defaults to returning the body.
     * @param reportProgress flag to report request and response progress.
     */
    public findByCodeOI40DBPurchaseOrder(code: string, observe?: 'body', reportProgress?: boolean): Observable<OI40DBPurchaseOrder>;
    public findByCodeOI40DBPurchaseOrder(code: string, observe?: 'response', reportProgress?: boolean): Observable<HttpResponse<OI40DBPurchaseOrder>>;
    public findByCodeOI40DBPurchaseOrder(code: string, observe?: 'events', reportProgress?: boolean): Observable<HttpEvent<OI40DBPurchaseOrder>>;
    public findByCodeOI40DBPurchaseOrder(code: string, observe: any = 'body', reportProgress: boolean = false ): Observable<any> {

        if (code === null || code === undefined) {
            throw new Error('Required parameter code was null or undefined when calling findByCodeOI40DBPurchaseOrder.');
        }

        let headers = this.defaultHeaders;

        // to determine the Accept header
        let httpHeaderAccepts: string[] = [
            'application/json'
        ];
        const httpHeaderAcceptSelected: string | undefined = this.configuration.selectHeaderAccept(httpHeaderAccepts);
        if (httpHeaderAcceptSelected != undefined) {
            headers = headers.set('Accept', httpHeaderAcceptSelected);
        }

        // to determine the Content-Type header
        const consumes: string[] = [
        ];

        return this.httpClient.get<OI40DBPurchaseOrder>(`${this.basePath}/integration/OI40DBPurchaseOrder/byCode/${encodeURIComponent(String(code))}`,
            {
                withCredentials: this.configuration.withCredentials,
                headers: headers,
                observe: observe,
                reportProgress: reportProgress
            }
        );
    }

    /**
     * findByCodes
     * 
     * @param codes codes
     * @param observe set whether or not to return the data Observable as the body, response or events. defaults to returning the body.
     * @param reportProgress flag to report request and response progress.
     */
    public findByCodesListOI40DBPurchaseOrder(codes: Array<string>, observe?: 'body', reportProgress?: boolean): Observable<Array<OI40DBPurchaseOrder>>;
    public findByCodesListOI40DBPurchaseOrder(codes: Array<string>, observe?: 'response', reportProgress?: boolean): Observable<HttpResponse<Array<OI40DBPurchaseOrder>>>;
    public findByCodesListOI40DBPurchaseOrder(codes: Array<string>, observe?: 'events', reportProgress?: boolean): Observable<HttpEvent<Array<OI40DBPurchaseOrder>>>;
    public findByCodesListOI40DBPurchaseOrder(codes: Array<string>, observe: any = 'body', reportProgress: boolean = false ): Observable<any> {

        if (codes === null || codes === undefined) {
            throw new Error('Required parameter codes was null or undefined when calling findByCodesListOI40DBPurchaseOrder.');
        }

        let headers = this.defaultHeaders;

        // to determine the Accept header
        let httpHeaderAccepts: string[] = [
            'application/json'
        ];
        const httpHeaderAcceptSelected: string | undefined = this.configuration.selectHeaderAccept(httpHeaderAccepts);
        if (httpHeaderAcceptSelected != undefined) {
            headers = headers.set('Accept', httpHeaderAcceptSelected);
        }

        // to determine the Content-Type header
        const consumes: string[] = [
            'application/json'
        ];
        const httpContentTypeSelected: string | undefined = this.configuration.selectHeaderContentType(consumes);
        if (httpContentTypeSelected != undefined) {
            headers = headers.set('Content-Type', httpContentTypeSelected);
        }

        return this.httpClient.post<Array<OI40DBPurchaseOrder>>(`${this.basePath}/integration/OI40DBPurchaseOrder/findByCodes`,
            codes,
            {
                withCredentials: this.configuration.withCredentials,
                headers: headers,
                observe: observe,
                reportProgress: reportProgress
            }
        );
    }

    /**
     * findByQbe
     * 
     * @param qbe qbe
     * @param observe set whether or not to return the data Observable as the body, response or events. defaults to returning the body.
     * @param reportProgress flag to report request and response progress.
     */
    public findByQbeListOI40DBPurchaseOrder(qbe: OI40DBPurchaseOrder, observe?: 'body', reportProgress?: boolean): Observable<Array<OI40DBPurchaseOrder>>;
    public findByQbeListOI40DBPurchaseOrder(qbe: OI40DBPurchaseOrder, observe?: 'response', reportProgress?: boolean): Observable<HttpResponse<Array<OI40DBPurchaseOrder>>>;
    public findByQbeListOI40DBPurchaseOrder(qbe: OI40DBPurchaseOrder, observe?: 'events', reportProgress?: boolean): Observable<HttpEvent<Array<OI40DBPurchaseOrder>>>;
    public findByQbeListOI40DBPurchaseOrder(qbe: OI40DBPurchaseOrder, observe: any = 'body', reportProgress: boolean = false ): Observable<any> {

        if (qbe === null || qbe === undefined) {
            throw new Error('Required parameter qbe was null or undefined when calling findByQbeListOI40DBPurchaseOrder.');
        }

        let headers = this.defaultHeaders;

        // to determine the Accept header
        let httpHeaderAccepts: string[] = [
            'application/json'
        ];
        const httpHeaderAcceptSelected: string | undefined = this.configuration.selectHeaderAccept(httpHeaderAccepts);
        if (httpHeaderAcceptSelected != undefined) {
            headers = headers.set('Accept', httpHeaderAcceptSelected);
        }

        // to determine the Content-Type header
        const consumes: string[] = [
            'application/json'
        ];
        const httpContentTypeSelected: string | undefined = this.configuration.selectHeaderContentType(consumes);
        if (httpContentTypeSelected != undefined) {
            headers = headers.set('Content-Type', httpContentTypeSelected);
        }

        return this.httpClient.post<Array<OI40DBPurchaseOrder>>(`${this.basePath}/integration/OI40DBPurchaseOrder/findByQbe`,
            qbe,
            {
                withCredentials: this.configuration.withCredentials,
                headers: headers,
                observe: observe,
                reportProgress: reportProgress
            }
        );
    }

    /**
     * findByQbePaged
     * 
     * @param qbe qbe
     * @param observe set whether or not to return the data Observable as the body, response or events. defaults to returning the body.
     * @param reportProgress flag to report request and response progress.
     */
    public findByQbePagedPageOI40DBPurchaseOrder(qbe: QbeSupportOI40DBPurchaseOrder, observe?: 'body', reportProgress?: boolean): Observable<PageOI40DBPurchaseOrder>;
    public findByQbePagedPageOI40DBPurchaseOrder(qbe: QbeSupportOI40DBPurchaseOrder, observe?: 'response', reportProgress?: boolean): Observable<HttpResponse<PageOI40DBPurchaseOrder>>;
    public findByQbePagedPageOI40DBPurchaseOrder(qbe: QbeSupportOI40DBPurchaseOrder, observe?: 'events', reportProgress?: boolean): Observable<HttpEvent<PageOI40DBPurchaseOrder>>;
    public findByQbePagedPageOI40DBPurchaseOrder(qbe: QbeSupportOI40DBPurchaseOrder, observe: any = 'body', reportProgress: boolean = false ): Observable<any> {

        if (qbe === null || qbe === undefined) {
            throw new Error('Required parameter qbe was null or undefined when calling findByQbePagedPageOI40DBPurchaseOrder.');
        }

        let headers = this.defaultHeaders;

        // to determine the Accept header
        let httpHeaderAccepts: string[] = [
            'application/json'
        ];
        const httpHeaderAcceptSelected: string | undefined = this.configuration.selectHeaderAccept(httpHeaderAccepts);
        if (httpHeaderAcceptSelected != undefined) {
            headers = headers.set('Accept', httpHeaderAcceptSelected);
        }

        // to determine the Content-Type header
        const consumes: string[] = [
            'application/json'
        ];
        const httpContentTypeSelected: string | undefined = this.configuration.selectHeaderContentType(consumes);
        if (httpContentTypeSelected != undefined) {
            headers = headers.set('Content-Type', httpContentTypeSelected);
        }

        return this.httpClient.post<PageOI40DBPurchaseOrder>(`${this.basePath}/integration/OI40DBPurchaseOrder/findByQbePaged`,
            qbe,
            {
                withCredentials: this.configuration.withCredentials,
                headers: headers,
                observe: observe,
                reportProgress: reportProgress
            }
        );
    }

    /**
     * update
     * 
     * @param data data
     * @param observe set whether or not to return the data Observable as the body, response or events. defaults to returning the body.
     * @param reportProgress flag to report request and response progress.
     */
    public updateListOI40DBPurchaseOrder(data: Array<OI40DBPurchaseOrder>, observe?: 'body', reportProgress?: boolean): Observable<Array<OI40DBPurchaseOrder>>;
    public updateListOI40DBPurchaseOrder(data: Array<OI40DBPurchaseOrder>, observe?: 'response', reportProgress?: boolean): Observable<HttpResponse<Array<OI40DBPurchaseOrder>>>;
    public updateListOI40DBPurchaseOrder(data: Array<OI40DBPurchaseOrder>, observe?: 'events', reportProgress?: boolean): Observable<HttpEvent<Array<OI40DBPurchaseOrder>>>;
    public updateListOI40DBPurchaseOrder(data: Array<OI40DBPurchaseOrder>, observe: any = 'body', reportProgress: boolean = false ): Observable<any> {

        if (data === null || data === undefined) {
            throw new Error('Required parameter data was null or undefined when calling updateListOI40DBPurchaseOrder.');
        }

        let headers = this.defaultHeaders;

        // to determine the Accept header
        let httpHeaderAccepts: string[] = [
            'application/json'
        ];
        const httpHeaderAcceptSelected: string | undefined = this.configuration.selectHeaderAccept(httpHeaderAccepts);
        if (httpHeaderAcceptSelected != undefined) {
            headers = headers.set('Accept', httpHeaderAcceptSelected);
        }

        // to determine the Content-Type header
        const consumes: string[] = [
            'application/json'
        ];
        const httpContentTypeSelected: string | undefined = this.configuration.selectHeaderContentType(consumes);
        if (httpContentTypeSelected != undefined) {
            headers = headers.set('Content-Type', httpContentTypeSelected);
        }

        return this.httpClient.post<Array<OI40DBPurchaseOrder>>(`${this.basePath}/integration/OI40DBPurchaseOrder/update`,
            data,
            {
                withCredentials: this.configuration.withCredentials,
                headers: headers,
                observe: observe,
                reportProgress: reportProgress
            }
        );
    }

    /**
     * updateSingle
     * 
     * @param data data
     * @param observe set whether or not to return the data Observable as the body, response or events. defaults to returning the body.
     * @param reportProgress flag to report request and response progress.
     */
    public updateSingleOI40DBPurchaseOrder(data: OI40DBPurchaseOrder, observe?: 'body', reportProgress?: boolean): Observable<OI40DBPurchaseOrder>;
    public updateSingleOI40DBPurchaseOrder(data: OI40DBPurchaseOrder, observe?: 'response', reportProgress?: boolean): Observable<HttpResponse<OI40DBPurchaseOrder>>;
    public updateSingleOI40DBPurchaseOrder(data: OI40DBPurchaseOrder, observe?: 'events', reportProgress?: boolean): Observable<HttpEvent<OI40DBPurchaseOrder>>;
    public updateSingleOI40DBPurchaseOrder(data: OI40DBPurchaseOrder, observe: any = 'body', reportProgress: boolean = false ): Observable<any> {

        if (data === null || data === undefined) {
            throw new Error('Required parameter data was null or undefined when calling updateSingleOI40DBPurchaseOrder.');
        }

        let headers = this.defaultHeaders;

        // to determine the Accept header
        let httpHeaderAccepts: string[] = [
            'application/json'
        ];
        const httpHeaderAcceptSelected: string | undefined = this.configuration.selectHeaderAccept(httpHeaderAccepts);
        if (httpHeaderAcceptSelected != undefined) {
            headers = headers.set('Accept', httpHeaderAcceptSelected);
        }

        // to determine the Content-Type header
        const consumes: string[] = [
            'application/json'
        ];
        const httpContentTypeSelected: string | undefined = this.configuration.selectHeaderContentType(consumes);
        if (httpContentTypeSelected != undefined) {
            headers = headers.set('Content-Type', httpContentTypeSelected);
        }

        return this.httpClient.post<OI40DBPurchaseOrder>(`${this.basePath}/integration/OI40DBPurchaseOrder/updateSingle`,
            data,
            {
                withCredentials: this.configuration.withCredentials,
                headers: headers,
                observe: observe,
                reportProgress: reportProgress
            }
        );
    }

}
