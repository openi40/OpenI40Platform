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
import { OI40DBChangeOverMatrixItem } from '../model/oI40DBChangeOverMatrixItem';
import { PageInfo } from '../model/pageInfo';
import { PageOI40DBChangeOverMatrixItem } from '../model/pageOI40DBChangeOverMatrixItem';
import { QbeSupportOI40DBChangeOverMatrixItem } from '../model/qbeSupportOI40DBChangeOverMatrixItem';

import { BASE_PATH, COLLECTION_FORMATS }                     from '../variables';
import { Configuration }                                     from '../configuration';


@Injectable()
export class Oi40DbChangeOverMatrixItemRepositoryService {

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
    public deleteByCodeVoid3(code: string, observe?: 'body', reportProgress?: boolean): Observable<any>;
    public deleteByCodeVoid3(code: string, observe?: 'response', reportProgress?: boolean): Observable<HttpResponse<any>>;
    public deleteByCodeVoid3(code: string, observe?: 'events', reportProgress?: boolean): Observable<HttpEvent<any>>;
    public deleteByCodeVoid3(code: string, observe: any = 'body', reportProgress: boolean = false ): Observable<any> {

        if (code === null || code === undefined) {
            throw new Error('Required parameter code was null or undefined when calling deleteByCodeVoid3.');
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

        return this.httpClient.get<any>(`${this.basePath}/integration/OI40DBChangeOverMatrixItem/deleteByCode/${encodeURIComponent(String(code))}`,
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
    public deleteByCodesVoid3(codes: Array<string>, observe?: 'body', reportProgress?: boolean): Observable<any>;
    public deleteByCodesVoid3(codes: Array<string>, observe?: 'response', reportProgress?: boolean): Observable<HttpResponse<any>>;
    public deleteByCodesVoid3(codes: Array<string>, observe?: 'events', reportProgress?: boolean): Observable<HttpEvent<any>>;
    public deleteByCodesVoid3(codes: Array<string>, observe: any = 'body', reportProgress: boolean = false ): Observable<any> {

        if (codes === null || codes === undefined) {
            throw new Error('Required parameter codes was null or undefined when calling deleteByCodesVoid3.');
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

        return this.httpClient.post<any>(`${this.basePath}/integration/OI40DBChangeOverMatrixItem/deleteByCodes`,
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
    public doLookupPageOI40DBChangeOverMatrixItem(lookup: LookupData, observe?: 'body', reportProgress?: boolean): Observable<PageOI40DBChangeOverMatrixItem>;
    public doLookupPageOI40DBChangeOverMatrixItem(lookup: LookupData, observe?: 'response', reportProgress?: boolean): Observable<HttpResponse<PageOI40DBChangeOverMatrixItem>>;
    public doLookupPageOI40DBChangeOverMatrixItem(lookup: LookupData, observe?: 'events', reportProgress?: boolean): Observable<HttpEvent<PageOI40DBChangeOverMatrixItem>>;
    public doLookupPageOI40DBChangeOverMatrixItem(lookup: LookupData, observe: any = 'body', reportProgress: boolean = false ): Observable<any> {

        if (lookup === null || lookup === undefined) {
            throw new Error('Required parameter lookup was null or undefined when calling doLookupPageOI40DBChangeOverMatrixItem.');
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

        return this.httpClient.post<PageOI40DBChangeOverMatrixItem>(`${this.basePath}/integration/OI40DBChangeOverMatrixItem/doLookup`,
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
    public findAllListOI40DBChangeOverMatrixItem(observe?: 'body', reportProgress?: boolean): Observable<Array<OI40DBChangeOverMatrixItem>>;
    public findAllListOI40DBChangeOverMatrixItem(observe?: 'response', reportProgress?: boolean): Observable<HttpResponse<Array<OI40DBChangeOverMatrixItem>>>;
    public findAllListOI40DBChangeOverMatrixItem(observe?: 'events', reportProgress?: boolean): Observable<HttpEvent<Array<OI40DBChangeOverMatrixItem>>>;
    public findAllListOI40DBChangeOverMatrixItem(observe: any = 'body', reportProgress: boolean = false ): Observable<any> {

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

        return this.httpClient.get<Array<OI40DBChangeOverMatrixItem>>(`${this.basePath}/integration/OI40DBChangeOverMatrixItem`,
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
    public findAllPageOI40DBChangeOverMatrixItem(p: PageInfo, observe?: 'body', reportProgress?: boolean): Observable<PageOI40DBChangeOverMatrixItem>;
    public findAllPageOI40DBChangeOverMatrixItem(p: PageInfo, observe?: 'response', reportProgress?: boolean): Observable<HttpResponse<PageOI40DBChangeOverMatrixItem>>;
    public findAllPageOI40DBChangeOverMatrixItem(p: PageInfo, observe?: 'events', reportProgress?: boolean): Observable<HttpEvent<PageOI40DBChangeOverMatrixItem>>;
    public findAllPageOI40DBChangeOverMatrixItem(p: PageInfo, observe: any = 'body', reportProgress: boolean = false ): Observable<any> {

        if (p === null || p === undefined) {
            throw new Error('Required parameter p was null or undefined when calling findAllPageOI40DBChangeOverMatrixItem.');
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

        return this.httpClient.post<PageOI40DBChangeOverMatrixItem>(`${this.basePath}/integration/OI40DBChangeOverMatrixItem/findAllPaged`,
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
    public findByAfterIntegrationTsListOI40DBChangeOverMatrixItem(ts: string, observe?: 'body', reportProgress?: boolean): Observable<Array<OI40DBChangeOverMatrixItem>>;
    public findByAfterIntegrationTsListOI40DBChangeOverMatrixItem(ts: string, observe?: 'response', reportProgress?: boolean): Observable<HttpResponse<Array<OI40DBChangeOverMatrixItem>>>;
    public findByAfterIntegrationTsListOI40DBChangeOverMatrixItem(ts: string, observe?: 'events', reportProgress?: boolean): Observable<HttpEvent<Array<OI40DBChangeOverMatrixItem>>>;
    public findByAfterIntegrationTsListOI40DBChangeOverMatrixItem(ts: string, observe: any = 'body', reportProgress: boolean = false ): Observable<any> {

        if (ts === null || ts === undefined) {
            throw new Error('Required parameter ts was null or undefined when calling findByAfterIntegrationTsListOI40DBChangeOverMatrixItem.');
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

        return this.httpClient.get<Array<OI40DBChangeOverMatrixItem>>(`${this.basePath}/integration/OI40DBChangeOverMatrixItem/integratedAfter/${encodeURIComponent(String(ts))}`,
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
    public findByAfterModifiedTimestampListOI40DBChangeOverMatrixItem(ts: string, observe?: 'body', reportProgress?: boolean): Observable<Array<OI40DBChangeOverMatrixItem>>;
    public findByAfterModifiedTimestampListOI40DBChangeOverMatrixItem(ts: string, observe?: 'response', reportProgress?: boolean): Observable<HttpResponse<Array<OI40DBChangeOverMatrixItem>>>;
    public findByAfterModifiedTimestampListOI40DBChangeOverMatrixItem(ts: string, observe?: 'events', reportProgress?: boolean): Observable<HttpEvent<Array<OI40DBChangeOverMatrixItem>>>;
    public findByAfterModifiedTimestampListOI40DBChangeOverMatrixItem(ts: string, observe: any = 'body', reportProgress: boolean = false ): Observable<any> {

        if (ts === null || ts === undefined) {
            throw new Error('Required parameter ts was null or undefined when calling findByAfterModifiedTimestampListOI40DBChangeOverMatrixItem.');
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

        return this.httpClient.get<Array<OI40DBChangeOverMatrixItem>>(`${this.basePath}/integration/OI40DBChangeOverMatrixItem/modifiedAfter/${encodeURIComponent(String(ts))}`,
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
    public findByCodeOI40DBChangeOverMatrixItem(code: string, observe?: 'body', reportProgress?: boolean): Observable<OI40DBChangeOverMatrixItem>;
    public findByCodeOI40DBChangeOverMatrixItem(code: string, observe?: 'response', reportProgress?: boolean): Observable<HttpResponse<OI40DBChangeOverMatrixItem>>;
    public findByCodeOI40DBChangeOverMatrixItem(code: string, observe?: 'events', reportProgress?: boolean): Observable<HttpEvent<OI40DBChangeOverMatrixItem>>;
    public findByCodeOI40DBChangeOverMatrixItem(code: string, observe: any = 'body', reportProgress: boolean = false ): Observable<any> {

        if (code === null || code === undefined) {
            throw new Error('Required parameter code was null or undefined when calling findByCodeOI40DBChangeOverMatrixItem.');
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

        return this.httpClient.get<OI40DBChangeOverMatrixItem>(`${this.basePath}/integration/OI40DBChangeOverMatrixItem/byCode/${encodeURIComponent(String(code))}`,
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
    public findByCodesListOI40DBChangeOverMatrixItem(codes: Array<string>, observe?: 'body', reportProgress?: boolean): Observable<Array<OI40DBChangeOverMatrixItem>>;
    public findByCodesListOI40DBChangeOverMatrixItem(codes: Array<string>, observe?: 'response', reportProgress?: boolean): Observable<HttpResponse<Array<OI40DBChangeOverMatrixItem>>>;
    public findByCodesListOI40DBChangeOverMatrixItem(codes: Array<string>, observe?: 'events', reportProgress?: boolean): Observable<HttpEvent<Array<OI40DBChangeOverMatrixItem>>>;
    public findByCodesListOI40DBChangeOverMatrixItem(codes: Array<string>, observe: any = 'body', reportProgress: boolean = false ): Observable<any> {

        if (codes === null || codes === undefined) {
            throw new Error('Required parameter codes was null or undefined when calling findByCodesListOI40DBChangeOverMatrixItem.');
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

        return this.httpClient.post<Array<OI40DBChangeOverMatrixItem>>(`${this.basePath}/integration/OI40DBChangeOverMatrixItem/findByCodes`,
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
    public findByQbeListOI40DBChangeOverMatrixItem(qbe: OI40DBChangeOverMatrixItem, observe?: 'body', reportProgress?: boolean): Observable<Array<OI40DBChangeOverMatrixItem>>;
    public findByQbeListOI40DBChangeOverMatrixItem(qbe: OI40DBChangeOverMatrixItem, observe?: 'response', reportProgress?: boolean): Observable<HttpResponse<Array<OI40DBChangeOverMatrixItem>>>;
    public findByQbeListOI40DBChangeOverMatrixItem(qbe: OI40DBChangeOverMatrixItem, observe?: 'events', reportProgress?: boolean): Observable<HttpEvent<Array<OI40DBChangeOverMatrixItem>>>;
    public findByQbeListOI40DBChangeOverMatrixItem(qbe: OI40DBChangeOverMatrixItem, observe: any = 'body', reportProgress: boolean = false ): Observable<any> {

        if (qbe === null || qbe === undefined) {
            throw new Error('Required parameter qbe was null or undefined when calling findByQbeListOI40DBChangeOverMatrixItem.');
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

        return this.httpClient.post<Array<OI40DBChangeOverMatrixItem>>(`${this.basePath}/integration/OI40DBChangeOverMatrixItem/findByQbe`,
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
    public findByQbePagedPageOI40DBChangeOverMatrixItem(qbe: QbeSupportOI40DBChangeOverMatrixItem, observe?: 'body', reportProgress?: boolean): Observable<PageOI40DBChangeOverMatrixItem>;
    public findByQbePagedPageOI40DBChangeOverMatrixItem(qbe: QbeSupportOI40DBChangeOverMatrixItem, observe?: 'response', reportProgress?: boolean): Observable<HttpResponse<PageOI40DBChangeOverMatrixItem>>;
    public findByQbePagedPageOI40DBChangeOverMatrixItem(qbe: QbeSupportOI40DBChangeOverMatrixItem, observe?: 'events', reportProgress?: boolean): Observable<HttpEvent<PageOI40DBChangeOverMatrixItem>>;
    public findByQbePagedPageOI40DBChangeOverMatrixItem(qbe: QbeSupportOI40DBChangeOverMatrixItem, observe: any = 'body', reportProgress: boolean = false ): Observable<any> {

        if (qbe === null || qbe === undefined) {
            throw new Error('Required parameter qbe was null or undefined when calling findByQbePagedPageOI40DBChangeOverMatrixItem.');
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

        return this.httpClient.post<PageOI40DBChangeOverMatrixItem>(`${this.basePath}/integration/OI40DBChangeOverMatrixItem/findByQbePaged`,
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
    public updateListOI40DBChangeOverMatrixItem(data: Array<OI40DBChangeOverMatrixItem>, observe?: 'body', reportProgress?: boolean): Observable<Array<OI40DBChangeOverMatrixItem>>;
    public updateListOI40DBChangeOverMatrixItem(data: Array<OI40DBChangeOverMatrixItem>, observe?: 'response', reportProgress?: boolean): Observable<HttpResponse<Array<OI40DBChangeOverMatrixItem>>>;
    public updateListOI40DBChangeOverMatrixItem(data: Array<OI40DBChangeOverMatrixItem>, observe?: 'events', reportProgress?: boolean): Observable<HttpEvent<Array<OI40DBChangeOverMatrixItem>>>;
    public updateListOI40DBChangeOverMatrixItem(data: Array<OI40DBChangeOverMatrixItem>, observe: any = 'body', reportProgress: boolean = false ): Observable<any> {

        if (data === null || data === undefined) {
            throw new Error('Required parameter data was null or undefined when calling updateListOI40DBChangeOverMatrixItem.');
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

        return this.httpClient.post<Array<OI40DBChangeOverMatrixItem>>(`${this.basePath}/integration/OI40DBChangeOverMatrixItem/update`,
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
    public updateSingleOI40DBChangeOverMatrixItem(data: OI40DBChangeOverMatrixItem, observe?: 'body', reportProgress?: boolean): Observable<OI40DBChangeOverMatrixItem>;
    public updateSingleOI40DBChangeOverMatrixItem(data: OI40DBChangeOverMatrixItem, observe?: 'response', reportProgress?: boolean): Observable<HttpResponse<OI40DBChangeOverMatrixItem>>;
    public updateSingleOI40DBChangeOverMatrixItem(data: OI40DBChangeOverMatrixItem, observe?: 'events', reportProgress?: boolean): Observable<HttpEvent<OI40DBChangeOverMatrixItem>>;
    public updateSingleOI40DBChangeOverMatrixItem(data: OI40DBChangeOverMatrixItem, observe: any = 'body', reportProgress: boolean = false ): Observable<any> {

        if (data === null || data === undefined) {
            throw new Error('Required parameter data was null or undefined when calling updateSingleOI40DBChangeOverMatrixItem.');
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

        return this.httpClient.post<OI40DBChangeOverMatrixItem>(`${this.basePath}/integration/OI40DBChangeOverMatrixItem/updateSingle`,
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
