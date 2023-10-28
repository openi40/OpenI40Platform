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
import { OI40DBMachineEquipmentSpecification } from '../model/oI40DBMachineEquipmentSpecification';
import { PageInfo } from '../model/pageInfo';
import { PageOI40DBMachineEquipmentSpecification } from '../model/pageOI40DBMachineEquipmentSpecification';
import { QbeSupportOI40DBMachineEquipmentSpecification } from '../model/qbeSupportOI40DBMachineEquipmentSpecification';

import { BASE_PATH, COLLECTION_FORMATS }                     from '../variables';
import { Configuration }                                     from '../configuration';


@Injectable()
export class Oi40DbMachineEquipmentSpecificationRepositoryService {

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
    public deleteByCodeVoid7(code: string, observe?: 'body', reportProgress?: boolean): Observable<any>;
    public deleteByCodeVoid7(code: string, observe?: 'response', reportProgress?: boolean): Observable<HttpResponse<any>>;
    public deleteByCodeVoid7(code: string, observe?: 'events', reportProgress?: boolean): Observable<HttpEvent<any>>;
    public deleteByCodeVoid7(code: string, observe: any = 'body', reportProgress: boolean = false ): Observable<any> {

        if (code === null || code === undefined) {
            throw new Error('Required parameter code was null or undefined when calling deleteByCodeVoid7.');
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

        return this.httpClient.get<any>(`${this.basePath}/integration/OI40DBMachineEquipmentSpecification/deleteByCode/${encodeURIComponent(String(code))}`,
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
    public deleteByCodesVoid7(codes: Array<string>, observe?: 'body', reportProgress?: boolean): Observable<any>;
    public deleteByCodesVoid7(codes: Array<string>, observe?: 'response', reportProgress?: boolean): Observable<HttpResponse<any>>;
    public deleteByCodesVoid7(codes: Array<string>, observe?: 'events', reportProgress?: boolean): Observable<HttpEvent<any>>;
    public deleteByCodesVoid7(codes: Array<string>, observe: any = 'body', reportProgress: boolean = false ): Observable<any> {

        if (codes === null || codes === undefined) {
            throw new Error('Required parameter codes was null or undefined when calling deleteByCodesVoid7.');
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

        return this.httpClient.post<any>(`${this.basePath}/integration/OI40DBMachineEquipmentSpecification/deleteByCodes`,
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
    public doAutocompletePageOI40DBMachineEquipmentSpecification(autoCompleteData: AutoCompleteData, observe?: 'body', reportProgress?: boolean): Observable<PageOI40DBMachineEquipmentSpecification>;
    public doAutocompletePageOI40DBMachineEquipmentSpecification(autoCompleteData: AutoCompleteData, observe?: 'response', reportProgress?: boolean): Observable<HttpResponse<PageOI40DBMachineEquipmentSpecification>>;
    public doAutocompletePageOI40DBMachineEquipmentSpecification(autoCompleteData: AutoCompleteData, observe?: 'events', reportProgress?: boolean): Observable<HttpEvent<PageOI40DBMachineEquipmentSpecification>>;
    public doAutocompletePageOI40DBMachineEquipmentSpecification(autoCompleteData: AutoCompleteData, observe: any = 'body', reportProgress: boolean = false ): Observable<any> {

        if (autoCompleteData === null || autoCompleteData === undefined) {
            throw new Error('Required parameter autoCompleteData was null or undefined when calling doAutocompletePageOI40DBMachineEquipmentSpecification.');
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

        return this.httpClient.post<PageOI40DBMachineEquipmentSpecification>(`${this.basePath}/integration/OI40DBMachineEquipmentSpecification/doAutocomplete`,
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
    public doLookupPageOI40DBMachineEquipmentSpecification(lookup: LookupData, observe?: 'body', reportProgress?: boolean): Observable<PageOI40DBMachineEquipmentSpecification>;
    public doLookupPageOI40DBMachineEquipmentSpecification(lookup: LookupData, observe?: 'response', reportProgress?: boolean): Observable<HttpResponse<PageOI40DBMachineEquipmentSpecification>>;
    public doLookupPageOI40DBMachineEquipmentSpecification(lookup: LookupData, observe?: 'events', reportProgress?: boolean): Observable<HttpEvent<PageOI40DBMachineEquipmentSpecification>>;
    public doLookupPageOI40DBMachineEquipmentSpecification(lookup: LookupData, observe: any = 'body', reportProgress: boolean = false ): Observable<any> {

        if (lookup === null || lookup === undefined) {
            throw new Error('Required parameter lookup was null or undefined when calling doLookupPageOI40DBMachineEquipmentSpecification.');
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

        return this.httpClient.post<PageOI40DBMachineEquipmentSpecification>(`${this.basePath}/integration/OI40DBMachineEquipmentSpecification/doLookup`,
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
    public findAllListOI40DBMachineEquipmentSpecification(observe?: 'body', reportProgress?: boolean): Observable<Array<OI40DBMachineEquipmentSpecification>>;
    public findAllListOI40DBMachineEquipmentSpecification(observe?: 'response', reportProgress?: boolean): Observable<HttpResponse<Array<OI40DBMachineEquipmentSpecification>>>;
    public findAllListOI40DBMachineEquipmentSpecification(observe?: 'events', reportProgress?: boolean): Observable<HttpEvent<Array<OI40DBMachineEquipmentSpecification>>>;
    public findAllListOI40DBMachineEquipmentSpecification(observe: any = 'body', reportProgress: boolean = false ): Observable<any> {

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

        return this.httpClient.get<Array<OI40DBMachineEquipmentSpecification>>(`${this.basePath}/integration/OI40DBMachineEquipmentSpecification`,
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
    public findAllPageOI40DBMachineEquipmentSpecification(p: PageInfo, observe?: 'body', reportProgress?: boolean): Observable<PageOI40DBMachineEquipmentSpecification>;
    public findAllPageOI40DBMachineEquipmentSpecification(p: PageInfo, observe?: 'response', reportProgress?: boolean): Observable<HttpResponse<PageOI40DBMachineEquipmentSpecification>>;
    public findAllPageOI40DBMachineEquipmentSpecification(p: PageInfo, observe?: 'events', reportProgress?: boolean): Observable<HttpEvent<PageOI40DBMachineEquipmentSpecification>>;
    public findAllPageOI40DBMachineEquipmentSpecification(p: PageInfo, observe: any = 'body', reportProgress: boolean = false ): Observable<any> {

        if (p === null || p === undefined) {
            throw new Error('Required parameter p was null or undefined when calling findAllPageOI40DBMachineEquipmentSpecification.');
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

        return this.httpClient.post<PageOI40DBMachineEquipmentSpecification>(`${this.basePath}/integration/OI40DBMachineEquipmentSpecification/findAllPaged`,
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
    public findByAfterIntegrationTsListOI40DBMachineEquipmentSpecification(ts: string, observe?: 'body', reportProgress?: boolean): Observable<Array<OI40DBMachineEquipmentSpecification>>;
    public findByAfterIntegrationTsListOI40DBMachineEquipmentSpecification(ts: string, observe?: 'response', reportProgress?: boolean): Observable<HttpResponse<Array<OI40DBMachineEquipmentSpecification>>>;
    public findByAfterIntegrationTsListOI40DBMachineEquipmentSpecification(ts: string, observe?: 'events', reportProgress?: boolean): Observable<HttpEvent<Array<OI40DBMachineEquipmentSpecification>>>;
    public findByAfterIntegrationTsListOI40DBMachineEquipmentSpecification(ts: string, observe: any = 'body', reportProgress: boolean = false ): Observable<any> {

        if (ts === null || ts === undefined) {
            throw new Error('Required parameter ts was null or undefined when calling findByAfterIntegrationTsListOI40DBMachineEquipmentSpecification.');
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

        return this.httpClient.get<Array<OI40DBMachineEquipmentSpecification>>(`${this.basePath}/integration/OI40DBMachineEquipmentSpecification/integratedAfter/${encodeURIComponent(String(ts))}`,
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
    public findByAfterModifiedTimestampListOI40DBMachineEquipmentSpecification(ts: string, observe?: 'body', reportProgress?: boolean): Observable<Array<OI40DBMachineEquipmentSpecification>>;
    public findByAfterModifiedTimestampListOI40DBMachineEquipmentSpecification(ts: string, observe?: 'response', reportProgress?: boolean): Observable<HttpResponse<Array<OI40DBMachineEquipmentSpecification>>>;
    public findByAfterModifiedTimestampListOI40DBMachineEquipmentSpecification(ts: string, observe?: 'events', reportProgress?: boolean): Observable<HttpEvent<Array<OI40DBMachineEquipmentSpecification>>>;
    public findByAfterModifiedTimestampListOI40DBMachineEquipmentSpecification(ts: string, observe: any = 'body', reportProgress: boolean = false ): Observable<any> {

        if (ts === null || ts === undefined) {
            throw new Error('Required parameter ts was null or undefined when calling findByAfterModifiedTimestampListOI40DBMachineEquipmentSpecification.');
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

        return this.httpClient.get<Array<OI40DBMachineEquipmentSpecification>>(`${this.basePath}/integration/OI40DBMachineEquipmentSpecification/modifiedAfter/${encodeURIComponent(String(ts))}`,
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
    public findByCodeOI40DBMachineEquipmentSpecification(code: string, observe?: 'body', reportProgress?: boolean): Observable<OI40DBMachineEquipmentSpecification>;
    public findByCodeOI40DBMachineEquipmentSpecification(code: string, observe?: 'response', reportProgress?: boolean): Observable<HttpResponse<OI40DBMachineEquipmentSpecification>>;
    public findByCodeOI40DBMachineEquipmentSpecification(code: string, observe?: 'events', reportProgress?: boolean): Observable<HttpEvent<OI40DBMachineEquipmentSpecification>>;
    public findByCodeOI40DBMachineEquipmentSpecification(code: string, observe: any = 'body', reportProgress: boolean = false ): Observable<any> {

        if (code === null || code === undefined) {
            throw new Error('Required parameter code was null or undefined when calling findByCodeOI40DBMachineEquipmentSpecification.');
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

        return this.httpClient.get<OI40DBMachineEquipmentSpecification>(`${this.basePath}/integration/OI40DBMachineEquipmentSpecification/byCode/${encodeURIComponent(String(code))}`,
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
    public findByCodesListOI40DBMachineEquipmentSpecification(codes: Array<string>, observe?: 'body', reportProgress?: boolean): Observable<Array<OI40DBMachineEquipmentSpecification>>;
    public findByCodesListOI40DBMachineEquipmentSpecification(codes: Array<string>, observe?: 'response', reportProgress?: boolean): Observable<HttpResponse<Array<OI40DBMachineEquipmentSpecification>>>;
    public findByCodesListOI40DBMachineEquipmentSpecification(codes: Array<string>, observe?: 'events', reportProgress?: boolean): Observable<HttpEvent<Array<OI40DBMachineEquipmentSpecification>>>;
    public findByCodesListOI40DBMachineEquipmentSpecification(codes: Array<string>, observe: any = 'body', reportProgress: boolean = false ): Observable<any> {

        if (codes === null || codes === undefined) {
            throw new Error('Required parameter codes was null or undefined when calling findByCodesListOI40DBMachineEquipmentSpecification.');
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

        return this.httpClient.post<Array<OI40DBMachineEquipmentSpecification>>(`${this.basePath}/integration/OI40DBMachineEquipmentSpecification/findByCodes`,
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
    public findByQbeListOI40DBMachineEquipmentSpecification(qbe: OI40DBMachineEquipmentSpecification, observe?: 'body', reportProgress?: boolean): Observable<Array<OI40DBMachineEquipmentSpecification>>;
    public findByQbeListOI40DBMachineEquipmentSpecification(qbe: OI40DBMachineEquipmentSpecification, observe?: 'response', reportProgress?: boolean): Observable<HttpResponse<Array<OI40DBMachineEquipmentSpecification>>>;
    public findByQbeListOI40DBMachineEquipmentSpecification(qbe: OI40DBMachineEquipmentSpecification, observe?: 'events', reportProgress?: boolean): Observable<HttpEvent<Array<OI40DBMachineEquipmentSpecification>>>;
    public findByQbeListOI40DBMachineEquipmentSpecification(qbe: OI40DBMachineEquipmentSpecification, observe: any = 'body', reportProgress: boolean = false ): Observable<any> {

        if (qbe === null || qbe === undefined) {
            throw new Error('Required parameter qbe was null or undefined when calling findByQbeListOI40DBMachineEquipmentSpecification.');
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

        return this.httpClient.post<Array<OI40DBMachineEquipmentSpecification>>(`${this.basePath}/integration/OI40DBMachineEquipmentSpecification/findByQbe`,
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
    public findByQbePagedPageOI40DBMachineEquipmentSpecification(qbe: QbeSupportOI40DBMachineEquipmentSpecification, observe?: 'body', reportProgress?: boolean): Observable<PageOI40DBMachineEquipmentSpecification>;
    public findByQbePagedPageOI40DBMachineEquipmentSpecification(qbe: QbeSupportOI40DBMachineEquipmentSpecification, observe?: 'response', reportProgress?: boolean): Observable<HttpResponse<PageOI40DBMachineEquipmentSpecification>>;
    public findByQbePagedPageOI40DBMachineEquipmentSpecification(qbe: QbeSupportOI40DBMachineEquipmentSpecification, observe?: 'events', reportProgress?: boolean): Observable<HttpEvent<PageOI40DBMachineEquipmentSpecification>>;
    public findByQbePagedPageOI40DBMachineEquipmentSpecification(qbe: QbeSupportOI40DBMachineEquipmentSpecification, observe: any = 'body', reportProgress: boolean = false ): Observable<any> {

        if (qbe === null || qbe === undefined) {
            throw new Error('Required parameter qbe was null or undefined when calling findByQbePagedPageOI40DBMachineEquipmentSpecification.');
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

        return this.httpClient.post<PageOI40DBMachineEquipmentSpecification>(`${this.basePath}/integration/OI40DBMachineEquipmentSpecification/findByQbePaged`,
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
    public updateListOI40DBMachineEquipmentSpecification(data: Array<OI40DBMachineEquipmentSpecification>, observe?: 'body', reportProgress?: boolean): Observable<Array<OI40DBMachineEquipmentSpecification>>;
    public updateListOI40DBMachineEquipmentSpecification(data: Array<OI40DBMachineEquipmentSpecification>, observe?: 'response', reportProgress?: boolean): Observable<HttpResponse<Array<OI40DBMachineEquipmentSpecification>>>;
    public updateListOI40DBMachineEquipmentSpecification(data: Array<OI40DBMachineEquipmentSpecification>, observe?: 'events', reportProgress?: boolean): Observable<HttpEvent<Array<OI40DBMachineEquipmentSpecification>>>;
    public updateListOI40DBMachineEquipmentSpecification(data: Array<OI40DBMachineEquipmentSpecification>, observe: any = 'body', reportProgress: boolean = false ): Observable<any> {

        if (data === null || data === undefined) {
            throw new Error('Required parameter data was null or undefined when calling updateListOI40DBMachineEquipmentSpecification.');
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

        return this.httpClient.post<Array<OI40DBMachineEquipmentSpecification>>(`${this.basePath}/integration/OI40DBMachineEquipmentSpecification/update`,
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
    public updateSingleOI40DBMachineEquipmentSpecification(data: OI40DBMachineEquipmentSpecification, observe?: 'body', reportProgress?: boolean): Observable<OI40DBMachineEquipmentSpecification>;
    public updateSingleOI40DBMachineEquipmentSpecification(data: OI40DBMachineEquipmentSpecification, observe?: 'response', reportProgress?: boolean): Observable<HttpResponse<OI40DBMachineEquipmentSpecification>>;
    public updateSingleOI40DBMachineEquipmentSpecification(data: OI40DBMachineEquipmentSpecification, observe?: 'events', reportProgress?: boolean): Observable<HttpEvent<OI40DBMachineEquipmentSpecification>>;
    public updateSingleOI40DBMachineEquipmentSpecification(data: OI40DBMachineEquipmentSpecification, observe: any = 'body', reportProgress: boolean = false ): Observable<any> {

        if (data === null || data === undefined) {
            throw new Error('Required parameter data was null or undefined when calling updateSingleOI40DBMachineEquipmentSpecification.');
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

        return this.httpClient.post<OI40DBMachineEquipmentSpecification>(`${this.basePath}/integration/OI40DBMachineEquipmentSpecification/updateSingle`,
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