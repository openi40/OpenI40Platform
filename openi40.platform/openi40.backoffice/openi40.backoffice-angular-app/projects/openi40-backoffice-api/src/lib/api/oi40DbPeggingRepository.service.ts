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

import { OI40DBPegging } from '../model/oI40DBPegging';
import { PageInfo } from '../model/pageInfo';
import { PageOI40DBPegging } from '../model/pageOI40DBPegging';
import { QbeSupportOI40DBPegging } from '../model/qbeSupportOI40DBPegging';

import { BASE_PATH, COLLECTION_FORMATS }                     from '../variables';
import { Configuration }                                     from '../configuration';


@Injectable()
export class Oi40DbPeggingRepositoryService {

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
    public deleteByCodeVoid13(code: string, observe?: 'body', reportProgress?: boolean): Observable<any>;
    public deleteByCodeVoid13(code: string, observe?: 'response', reportProgress?: boolean): Observable<HttpResponse<any>>;
    public deleteByCodeVoid13(code: string, observe?: 'events', reportProgress?: boolean): Observable<HttpEvent<any>>;
    public deleteByCodeVoid13(code: string, observe: any = 'body', reportProgress: boolean = false ): Observable<any> {

        if (code === null || code === undefined) {
            throw new Error('Required parameter code was null or undefined when calling deleteByCodeVoid13.');
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

        return this.httpClient.get<any>(`${this.basePath}/integration/OI40DBPegging/deleteByCode/${encodeURIComponent(String(code))}`,
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
    public deleteByCodesVoid13(codes: Array<string>, observe?: 'body', reportProgress?: boolean): Observable<any>;
    public deleteByCodesVoid13(codes: Array<string>, observe?: 'response', reportProgress?: boolean): Observable<HttpResponse<any>>;
    public deleteByCodesVoid13(codes: Array<string>, observe?: 'events', reportProgress?: boolean): Observable<HttpEvent<any>>;
    public deleteByCodesVoid13(codes: Array<string>, observe: any = 'body', reportProgress: boolean = false ): Observable<any> {

        if (codes === null || codes === undefined) {
            throw new Error('Required parameter codes was null or undefined when calling deleteByCodesVoid13.');
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

        return this.httpClient.post<any>(`${this.basePath}/integration/OI40DBPegging/deleteByCodes`,
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
     * findAll
     * 
     * @param observe set whether or not to return the data Observable as the body, response or events. defaults to returning the body.
     * @param reportProgress flag to report request and response progress.
     */
    public findAllListOI40DBPegging(observe?: 'body', reportProgress?: boolean): Observable<Array<OI40DBPegging>>;
    public findAllListOI40DBPegging(observe?: 'response', reportProgress?: boolean): Observable<HttpResponse<Array<OI40DBPegging>>>;
    public findAllListOI40DBPegging(observe?: 'events', reportProgress?: boolean): Observable<HttpEvent<Array<OI40DBPegging>>>;
    public findAllListOI40DBPegging(observe: any = 'body', reportProgress: boolean = false ): Observable<any> {

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

        return this.httpClient.get<Array<OI40DBPegging>>(`${this.basePath}/integration/OI40DBPegging`,
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
    public findAllPageOI40DBPegging(p: PageInfo, observe?: 'body', reportProgress?: boolean): Observable<PageOI40DBPegging>;
    public findAllPageOI40DBPegging(p: PageInfo, observe?: 'response', reportProgress?: boolean): Observable<HttpResponse<PageOI40DBPegging>>;
    public findAllPageOI40DBPegging(p: PageInfo, observe?: 'events', reportProgress?: boolean): Observable<HttpEvent<PageOI40DBPegging>>;
    public findAllPageOI40DBPegging(p: PageInfo, observe: any = 'body', reportProgress: boolean = false ): Observable<any> {

        if (p === null || p === undefined) {
            throw new Error('Required parameter p was null or undefined when calling findAllPageOI40DBPegging.');
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

        return this.httpClient.post<PageOI40DBPegging>(`${this.basePath}/integration/OI40DBPegging/findAllPaged`,
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
    public findByAfterIntegrationTsListOI40DBPegging(ts: string, observe?: 'body', reportProgress?: boolean): Observable<Array<OI40DBPegging>>;
    public findByAfterIntegrationTsListOI40DBPegging(ts: string, observe?: 'response', reportProgress?: boolean): Observable<HttpResponse<Array<OI40DBPegging>>>;
    public findByAfterIntegrationTsListOI40DBPegging(ts: string, observe?: 'events', reportProgress?: boolean): Observable<HttpEvent<Array<OI40DBPegging>>>;
    public findByAfterIntegrationTsListOI40DBPegging(ts: string, observe: any = 'body', reportProgress: boolean = false ): Observable<any> {

        if (ts === null || ts === undefined) {
            throw new Error('Required parameter ts was null or undefined when calling findByAfterIntegrationTsListOI40DBPegging.');
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

        return this.httpClient.get<Array<OI40DBPegging>>(`${this.basePath}/integration/OI40DBPegging/integratedAfter/${encodeURIComponent(String(ts))}`,
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
    public findByAfterModifiedTimestampListOI40DBPegging(ts: string, observe?: 'body', reportProgress?: boolean): Observable<Array<OI40DBPegging>>;
    public findByAfterModifiedTimestampListOI40DBPegging(ts: string, observe?: 'response', reportProgress?: boolean): Observable<HttpResponse<Array<OI40DBPegging>>>;
    public findByAfterModifiedTimestampListOI40DBPegging(ts: string, observe?: 'events', reportProgress?: boolean): Observable<HttpEvent<Array<OI40DBPegging>>>;
    public findByAfterModifiedTimestampListOI40DBPegging(ts: string, observe: any = 'body', reportProgress: boolean = false ): Observable<any> {

        if (ts === null || ts === undefined) {
            throw new Error('Required parameter ts was null or undefined when calling findByAfterModifiedTimestampListOI40DBPegging.');
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

        return this.httpClient.get<Array<OI40DBPegging>>(`${this.basePath}/integration/OI40DBPegging/modifiedAfter/${encodeURIComponent(String(ts))}`,
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
    public findByCodeOI40DBPegging(code: string, observe?: 'body', reportProgress?: boolean): Observable<OI40DBPegging>;
    public findByCodeOI40DBPegging(code: string, observe?: 'response', reportProgress?: boolean): Observable<HttpResponse<OI40DBPegging>>;
    public findByCodeOI40DBPegging(code: string, observe?: 'events', reportProgress?: boolean): Observable<HttpEvent<OI40DBPegging>>;
    public findByCodeOI40DBPegging(code: string, observe: any = 'body', reportProgress: boolean = false ): Observable<any> {

        if (code === null || code === undefined) {
            throw new Error('Required parameter code was null or undefined when calling findByCodeOI40DBPegging.');
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

        return this.httpClient.get<OI40DBPegging>(`${this.basePath}/integration/OI40DBPegging/byCode/${encodeURIComponent(String(code))}`,
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
    public findByCodesListOI40DBPegging(codes: Array<string>, observe?: 'body', reportProgress?: boolean): Observable<Array<OI40DBPegging>>;
    public findByCodesListOI40DBPegging(codes: Array<string>, observe?: 'response', reportProgress?: boolean): Observable<HttpResponse<Array<OI40DBPegging>>>;
    public findByCodesListOI40DBPegging(codes: Array<string>, observe?: 'events', reportProgress?: boolean): Observable<HttpEvent<Array<OI40DBPegging>>>;
    public findByCodesListOI40DBPegging(codes: Array<string>, observe: any = 'body', reportProgress: boolean = false ): Observable<any> {

        if (codes === null || codes === undefined) {
            throw new Error('Required parameter codes was null or undefined when calling findByCodesListOI40DBPegging.');
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

        return this.httpClient.post<Array<OI40DBPegging>>(`${this.basePath}/integration/OI40DBPegging/findByCodes`,
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
    public findByQbeListOI40DBPegging(qbe: OI40DBPegging, observe?: 'body', reportProgress?: boolean): Observable<Array<OI40DBPegging>>;
    public findByQbeListOI40DBPegging(qbe: OI40DBPegging, observe?: 'response', reportProgress?: boolean): Observable<HttpResponse<Array<OI40DBPegging>>>;
    public findByQbeListOI40DBPegging(qbe: OI40DBPegging, observe?: 'events', reportProgress?: boolean): Observable<HttpEvent<Array<OI40DBPegging>>>;
    public findByQbeListOI40DBPegging(qbe: OI40DBPegging, observe: any = 'body', reportProgress: boolean = false ): Observable<any> {

        if (qbe === null || qbe === undefined) {
            throw new Error('Required parameter qbe was null or undefined when calling findByQbeListOI40DBPegging.');
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

        return this.httpClient.post<Array<OI40DBPegging>>(`${this.basePath}/integration/OI40DBPegging/findByQbe`,
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
    public findByQbePagedPageOI40DBPegging(qbe: QbeSupportOI40DBPegging, observe?: 'body', reportProgress?: boolean): Observable<PageOI40DBPegging>;
    public findByQbePagedPageOI40DBPegging(qbe: QbeSupportOI40DBPegging, observe?: 'response', reportProgress?: boolean): Observable<HttpResponse<PageOI40DBPegging>>;
    public findByQbePagedPageOI40DBPegging(qbe: QbeSupportOI40DBPegging, observe?: 'events', reportProgress?: boolean): Observable<HttpEvent<PageOI40DBPegging>>;
    public findByQbePagedPageOI40DBPegging(qbe: QbeSupportOI40DBPegging, observe: any = 'body', reportProgress: boolean = false ): Observable<any> {

        if (qbe === null || qbe === undefined) {
            throw new Error('Required parameter qbe was null or undefined when calling findByQbePagedPageOI40DBPegging.');
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

        return this.httpClient.post<PageOI40DBPegging>(`${this.basePath}/integration/OI40DBPegging/findByQbePaged`,
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
    public updateListOI40DBPegging(data: Array<OI40DBPegging>, observe?: 'body', reportProgress?: boolean): Observable<Array<OI40DBPegging>>;
    public updateListOI40DBPegging(data: Array<OI40DBPegging>, observe?: 'response', reportProgress?: boolean): Observable<HttpResponse<Array<OI40DBPegging>>>;
    public updateListOI40DBPegging(data: Array<OI40DBPegging>, observe?: 'events', reportProgress?: boolean): Observable<HttpEvent<Array<OI40DBPegging>>>;
    public updateListOI40DBPegging(data: Array<OI40DBPegging>, observe: any = 'body', reportProgress: boolean = false ): Observable<any> {

        if (data === null || data === undefined) {
            throw new Error('Required parameter data was null or undefined when calling updateListOI40DBPegging.');
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

        return this.httpClient.post<Array<OI40DBPegging>>(`${this.basePath}/integration/OI40DBPegging/update`,
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
    public updateSingleOI40DBPegging(data: OI40DBPegging, observe?: 'body', reportProgress?: boolean): Observable<OI40DBPegging>;
    public updateSingleOI40DBPegging(data: OI40DBPegging, observe?: 'response', reportProgress?: boolean): Observable<HttpResponse<OI40DBPegging>>;
    public updateSingleOI40DBPegging(data: OI40DBPegging, observe?: 'events', reportProgress?: boolean): Observable<HttpEvent<OI40DBPegging>>;
    public updateSingleOI40DBPegging(data: OI40DBPegging, observe: any = 'body', reportProgress: boolean = false ): Observable<any> {

        if (data === null || data === undefined) {
            throw new Error('Required parameter data was null or undefined when calling updateSingleOI40DBPegging.');
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

        return this.httpClient.post<OI40DBPegging>(`${this.basePath}/integration/OI40DBPegging/updateSingle`,
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
