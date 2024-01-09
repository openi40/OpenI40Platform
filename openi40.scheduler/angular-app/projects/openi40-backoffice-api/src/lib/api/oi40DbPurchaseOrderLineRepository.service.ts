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

import { AutoCompleteData } from '../model/autoCompleteData';
import { LookupData } from '../model/lookupData';
import { OI40DBPurchaseOrderLine } from '../model/oI40DBPurchaseOrderLine';
import { PageInfo } from '../model/pageInfo';
import { PageOI40DBPurchaseOrderLine } from '../model/pageOI40DBPurchaseOrderLine';
import { QbeSupportOI40DBPurchaseOrderLine } from '../model/qbeSupportOI40DBPurchaseOrderLine';

import { BASE_PATH, COLLECTION_FORMATS }                     from '../variables';
import { Configuration }                                     from '../configuration';


@Injectable()
export class Oi40DbPurchaseOrderLineRepositoryService {

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
    public deleteByCodeVoid19(code: string, observe?: 'body', reportProgress?: boolean): Observable<any>;
    public deleteByCodeVoid19(code: string, observe?: 'response', reportProgress?: boolean): Observable<HttpResponse<any>>;
    public deleteByCodeVoid19(code: string, observe?: 'events', reportProgress?: boolean): Observable<HttpEvent<any>>;
    public deleteByCodeVoid19(code: string, observe: any = 'body', reportProgress: boolean = false ): Observable<any> {

        if (code === null || code === undefined) {
            throw new Error('Required parameter code was null or undefined when calling deleteByCodeVoid19.');
        }

        let queryParameters = new HttpParams({encoder: new CustomHttpUrlEncodingCodec()});
        if (code !== undefined && code !== null) {
            queryParameters = queryParameters.set('code', <any>code);
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

        return this.httpClient.get<any>(`${this.basePath}/integration/OI40DBPurchaseOrderLine/deleteByCode`,
            {
                params: queryParameters,
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
    public deleteByCodesVoid19(codes: Array<string>, observe?: 'body', reportProgress?: boolean): Observable<any>;
    public deleteByCodesVoid19(codes: Array<string>, observe?: 'response', reportProgress?: boolean): Observable<HttpResponse<any>>;
    public deleteByCodesVoid19(codes: Array<string>, observe?: 'events', reportProgress?: boolean): Observable<HttpEvent<any>>;
    public deleteByCodesVoid19(codes: Array<string>, observe: any = 'body', reportProgress: boolean = false ): Observable<any> {

        if (codes === null || codes === undefined) {
            throw new Error('Required parameter codes was null or undefined when calling deleteByCodesVoid19.');
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

        return this.httpClient.post<any>(`${this.basePath}/integration/OI40DBPurchaseOrderLine/deleteByCodes`,
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
     * doAutocomplete
     * 
     * @param autoCompleteData autoCompleteData
     * @param observe set whether or not to return the data Observable as the body, response or events. defaults to returning the body.
     * @param reportProgress flag to report request and response progress.
     */
    public doAutocompletePageOI40DBPurchaseOrderLine(autoCompleteData: AutoCompleteData, observe?: 'body', reportProgress?: boolean): Observable<PageOI40DBPurchaseOrderLine>;
    public doAutocompletePageOI40DBPurchaseOrderLine(autoCompleteData: AutoCompleteData, observe?: 'response', reportProgress?: boolean): Observable<HttpResponse<PageOI40DBPurchaseOrderLine>>;
    public doAutocompletePageOI40DBPurchaseOrderLine(autoCompleteData: AutoCompleteData, observe?: 'events', reportProgress?: boolean): Observable<HttpEvent<PageOI40DBPurchaseOrderLine>>;
    public doAutocompletePageOI40DBPurchaseOrderLine(autoCompleteData: AutoCompleteData, observe: any = 'body', reportProgress: boolean = false ): Observable<any> {

        if (autoCompleteData === null || autoCompleteData === undefined) {
            throw new Error('Required parameter autoCompleteData was null or undefined when calling doAutocompletePageOI40DBPurchaseOrderLine.');
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

        return this.httpClient.post<PageOI40DBPurchaseOrderLine>(`${this.basePath}/integration/OI40DBPurchaseOrderLine/doAutocomplete`,
            autoCompleteData,
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
    public doLookupPageOI40DBPurchaseOrderLine(lookup: LookupData, observe?: 'body', reportProgress?: boolean): Observable<PageOI40DBPurchaseOrderLine>;
    public doLookupPageOI40DBPurchaseOrderLine(lookup: LookupData, observe?: 'response', reportProgress?: boolean): Observable<HttpResponse<PageOI40DBPurchaseOrderLine>>;
    public doLookupPageOI40DBPurchaseOrderLine(lookup: LookupData, observe?: 'events', reportProgress?: boolean): Observable<HttpEvent<PageOI40DBPurchaseOrderLine>>;
    public doLookupPageOI40DBPurchaseOrderLine(lookup: LookupData, observe: any = 'body', reportProgress: boolean = false ): Observable<any> {

        if (lookup === null || lookup === undefined) {
            throw new Error('Required parameter lookup was null or undefined when calling doLookupPageOI40DBPurchaseOrderLine.');
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

        return this.httpClient.post<PageOI40DBPurchaseOrderLine>(`${this.basePath}/integration/OI40DBPurchaseOrderLine/doLookup`,
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
    public findAllListOI40DBPurchaseOrderLine(observe?: 'body', reportProgress?: boolean): Observable<Array<OI40DBPurchaseOrderLine>>;
    public findAllListOI40DBPurchaseOrderLine(observe?: 'response', reportProgress?: boolean): Observable<HttpResponse<Array<OI40DBPurchaseOrderLine>>>;
    public findAllListOI40DBPurchaseOrderLine(observe?: 'events', reportProgress?: boolean): Observable<HttpEvent<Array<OI40DBPurchaseOrderLine>>>;
    public findAllListOI40DBPurchaseOrderLine(observe: any = 'body', reportProgress: boolean = false ): Observable<any> {

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

        return this.httpClient.get<Array<OI40DBPurchaseOrderLine>>(`${this.basePath}/integration/OI40DBPurchaseOrderLine`,
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
    public findAllPageOI40DBPurchaseOrderLine(p: PageInfo, observe?: 'body', reportProgress?: boolean): Observable<PageOI40DBPurchaseOrderLine>;
    public findAllPageOI40DBPurchaseOrderLine(p: PageInfo, observe?: 'response', reportProgress?: boolean): Observable<HttpResponse<PageOI40DBPurchaseOrderLine>>;
    public findAllPageOI40DBPurchaseOrderLine(p: PageInfo, observe?: 'events', reportProgress?: boolean): Observable<HttpEvent<PageOI40DBPurchaseOrderLine>>;
    public findAllPageOI40DBPurchaseOrderLine(p: PageInfo, observe: any = 'body', reportProgress: boolean = false ): Observable<any> {

        if (p === null || p === undefined) {
            throw new Error('Required parameter p was null or undefined when calling findAllPageOI40DBPurchaseOrderLine.');
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

        return this.httpClient.post<PageOI40DBPurchaseOrderLine>(`${this.basePath}/integration/OI40DBPurchaseOrderLine/findAllPaged`,
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
    public findByAfterIntegrationTsListOI40DBPurchaseOrderLine(ts: string, observe?: 'body', reportProgress?: boolean): Observable<Array<OI40DBPurchaseOrderLine>>;
    public findByAfterIntegrationTsListOI40DBPurchaseOrderLine(ts: string, observe?: 'response', reportProgress?: boolean): Observable<HttpResponse<Array<OI40DBPurchaseOrderLine>>>;
    public findByAfterIntegrationTsListOI40DBPurchaseOrderLine(ts: string, observe?: 'events', reportProgress?: boolean): Observable<HttpEvent<Array<OI40DBPurchaseOrderLine>>>;
    public findByAfterIntegrationTsListOI40DBPurchaseOrderLine(ts: string, observe: any = 'body', reportProgress: boolean = false ): Observable<any> {

        if (ts === null || ts === undefined) {
            throw new Error('Required parameter ts was null or undefined when calling findByAfterIntegrationTsListOI40DBPurchaseOrderLine.');
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

        return this.httpClient.get<Array<OI40DBPurchaseOrderLine>>(`${this.basePath}/integration/OI40DBPurchaseOrderLine/integratedAfter/${encodeURIComponent(String(ts))}`,
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
    public findByAfterModifiedTimestampListOI40DBPurchaseOrderLine(ts: string, observe?: 'body', reportProgress?: boolean): Observable<Array<OI40DBPurchaseOrderLine>>;
    public findByAfterModifiedTimestampListOI40DBPurchaseOrderLine(ts: string, observe?: 'response', reportProgress?: boolean): Observable<HttpResponse<Array<OI40DBPurchaseOrderLine>>>;
    public findByAfterModifiedTimestampListOI40DBPurchaseOrderLine(ts: string, observe?: 'events', reportProgress?: boolean): Observable<HttpEvent<Array<OI40DBPurchaseOrderLine>>>;
    public findByAfterModifiedTimestampListOI40DBPurchaseOrderLine(ts: string, observe: any = 'body', reportProgress: boolean = false ): Observable<any> {

        if (ts === null || ts === undefined) {
            throw new Error('Required parameter ts was null or undefined when calling findByAfterModifiedTimestampListOI40DBPurchaseOrderLine.');
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

        return this.httpClient.get<Array<OI40DBPurchaseOrderLine>>(`${this.basePath}/integration/OI40DBPurchaseOrderLine/modifiedAfter/${encodeURIComponent(String(ts))}`,
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
    public findByCodeOI40DBPurchaseOrderLine(code: string, observe?: 'body', reportProgress?: boolean): Observable<OI40DBPurchaseOrderLine>;
    public findByCodeOI40DBPurchaseOrderLine(code: string, observe?: 'response', reportProgress?: boolean): Observable<HttpResponse<OI40DBPurchaseOrderLine>>;
    public findByCodeOI40DBPurchaseOrderLine(code: string, observe?: 'events', reportProgress?: boolean): Observable<HttpEvent<OI40DBPurchaseOrderLine>>;
    public findByCodeOI40DBPurchaseOrderLine(code: string, observe: any = 'body', reportProgress: boolean = false ): Observable<any> {

        if (code === null || code === undefined) {
            throw new Error('Required parameter code was null or undefined when calling findByCodeOI40DBPurchaseOrderLine.');
        }

        let queryParameters = new HttpParams({encoder: new CustomHttpUrlEncodingCodec()});
        if (code !== undefined && code !== null) {
            queryParameters = queryParameters.set('code', <any>code);
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

        return this.httpClient.get<OI40DBPurchaseOrderLine>(`${this.basePath}/integration/OI40DBPurchaseOrderLine/byCode`,
            {
                params: queryParameters,
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
    public findByCodesListOI40DBPurchaseOrderLine(codes: Array<string>, observe?: 'body', reportProgress?: boolean): Observable<Array<OI40DBPurchaseOrderLine>>;
    public findByCodesListOI40DBPurchaseOrderLine(codes: Array<string>, observe?: 'response', reportProgress?: boolean): Observable<HttpResponse<Array<OI40DBPurchaseOrderLine>>>;
    public findByCodesListOI40DBPurchaseOrderLine(codes: Array<string>, observe?: 'events', reportProgress?: boolean): Observable<HttpEvent<Array<OI40DBPurchaseOrderLine>>>;
    public findByCodesListOI40DBPurchaseOrderLine(codes: Array<string>, observe: any = 'body', reportProgress: boolean = false ): Observable<any> {

        if (codes === null || codes === undefined) {
            throw new Error('Required parameter codes was null or undefined when calling findByCodesListOI40DBPurchaseOrderLine.');
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

        return this.httpClient.post<Array<OI40DBPurchaseOrderLine>>(`${this.basePath}/integration/OI40DBPurchaseOrderLine/findByCodes`,
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
    public findByQbeListOI40DBPurchaseOrderLine(qbe: OI40DBPurchaseOrderLine, observe?: 'body', reportProgress?: boolean): Observable<Array<OI40DBPurchaseOrderLine>>;
    public findByQbeListOI40DBPurchaseOrderLine(qbe: OI40DBPurchaseOrderLine, observe?: 'response', reportProgress?: boolean): Observable<HttpResponse<Array<OI40DBPurchaseOrderLine>>>;
    public findByQbeListOI40DBPurchaseOrderLine(qbe: OI40DBPurchaseOrderLine, observe?: 'events', reportProgress?: boolean): Observable<HttpEvent<Array<OI40DBPurchaseOrderLine>>>;
    public findByQbeListOI40DBPurchaseOrderLine(qbe: OI40DBPurchaseOrderLine, observe: any = 'body', reportProgress: boolean = false ): Observable<any> {

        if (qbe === null || qbe === undefined) {
            throw new Error('Required parameter qbe was null or undefined when calling findByQbeListOI40DBPurchaseOrderLine.');
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

        return this.httpClient.post<Array<OI40DBPurchaseOrderLine>>(`${this.basePath}/integration/OI40DBPurchaseOrderLine/findByQbe`,
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
    public findByQbePagedPageOI40DBPurchaseOrderLine(qbe: QbeSupportOI40DBPurchaseOrderLine, observe?: 'body', reportProgress?: boolean): Observable<PageOI40DBPurchaseOrderLine>;
    public findByQbePagedPageOI40DBPurchaseOrderLine(qbe: QbeSupportOI40DBPurchaseOrderLine, observe?: 'response', reportProgress?: boolean): Observable<HttpResponse<PageOI40DBPurchaseOrderLine>>;
    public findByQbePagedPageOI40DBPurchaseOrderLine(qbe: QbeSupportOI40DBPurchaseOrderLine, observe?: 'events', reportProgress?: boolean): Observable<HttpEvent<PageOI40DBPurchaseOrderLine>>;
    public findByQbePagedPageOI40DBPurchaseOrderLine(qbe: QbeSupportOI40DBPurchaseOrderLine, observe: any = 'body', reportProgress: boolean = false ): Observable<any> {

        if (qbe === null || qbe === undefined) {
            throw new Error('Required parameter qbe was null or undefined when calling findByQbePagedPageOI40DBPurchaseOrderLine.');
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

        return this.httpClient.post<PageOI40DBPurchaseOrderLine>(`${this.basePath}/integration/OI40DBPurchaseOrderLine/findByQbePaged`,
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
    public updateListOI40DBPurchaseOrderLine(data: Array<OI40DBPurchaseOrderLine>, observe?: 'body', reportProgress?: boolean): Observable<Array<OI40DBPurchaseOrderLine>>;
    public updateListOI40DBPurchaseOrderLine(data: Array<OI40DBPurchaseOrderLine>, observe?: 'response', reportProgress?: boolean): Observable<HttpResponse<Array<OI40DBPurchaseOrderLine>>>;
    public updateListOI40DBPurchaseOrderLine(data: Array<OI40DBPurchaseOrderLine>, observe?: 'events', reportProgress?: boolean): Observable<HttpEvent<Array<OI40DBPurchaseOrderLine>>>;
    public updateListOI40DBPurchaseOrderLine(data: Array<OI40DBPurchaseOrderLine>, observe: any = 'body', reportProgress: boolean = false ): Observable<any> {

        if (data === null || data === undefined) {
            throw new Error('Required parameter data was null or undefined when calling updateListOI40DBPurchaseOrderLine.');
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

        return this.httpClient.post<Array<OI40DBPurchaseOrderLine>>(`${this.basePath}/integration/OI40DBPurchaseOrderLine/update`,
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
    public updateSingleOI40DBPurchaseOrderLine(data: OI40DBPurchaseOrderLine, observe?: 'body', reportProgress?: boolean): Observable<OI40DBPurchaseOrderLine>;
    public updateSingleOI40DBPurchaseOrderLine(data: OI40DBPurchaseOrderLine, observe?: 'response', reportProgress?: boolean): Observable<HttpResponse<OI40DBPurchaseOrderLine>>;
    public updateSingleOI40DBPurchaseOrderLine(data: OI40DBPurchaseOrderLine, observe?: 'events', reportProgress?: boolean): Observable<HttpEvent<OI40DBPurchaseOrderLine>>;
    public updateSingleOI40DBPurchaseOrderLine(data: OI40DBPurchaseOrderLine, observe: any = 'body', reportProgress: boolean = false ): Observable<any> {

        if (data === null || data === undefined) {
            throw new Error('Required parameter data was null or undefined when calling updateSingleOI40DBPurchaseOrderLine.');
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

        return this.httpClient.post<OI40DBPurchaseOrderLine>(`${this.basePath}/integration/OI40DBPurchaseOrderLine/updateSingle`,
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