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

import { OI40DBPartner } from '../model/oI40DBPartner';
import { PageInfo } from '../model/pageInfo';
import { PageOI40DBPartner } from '../model/pageOI40DBPartner';
import { QbeSupportOI40DBPartner } from '../model/qbeSupportOI40DBPartner';

import { BASE_PATH, COLLECTION_FORMATS }                     from '../variables';
import { Configuration }                                     from '../configuration';


@Injectable()
export class Oi40DbPartnerRepositoryService {

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
    public deleteByCodeVoid12(code: string, observe?: 'body', reportProgress?: boolean): Observable<any>;
    public deleteByCodeVoid12(code: string, observe?: 'response', reportProgress?: boolean): Observable<HttpResponse<any>>;
    public deleteByCodeVoid12(code: string, observe?: 'events', reportProgress?: boolean): Observable<HttpEvent<any>>;
    public deleteByCodeVoid12(code: string, observe: any = 'body', reportProgress: boolean = false ): Observable<any> {

        if (code === null || code === undefined) {
            throw new Error('Required parameter code was null or undefined when calling deleteByCodeVoid12.');
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

        return this.httpClient.get<any>(`${this.basePath}/integration/OI40DBPartner/deleteByCode/${encodeURIComponent(String(code))}`,
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
    public deleteByCodesVoid12(codes: Array<string>, observe?: 'body', reportProgress?: boolean): Observable<any>;
    public deleteByCodesVoid12(codes: Array<string>, observe?: 'response', reportProgress?: boolean): Observable<HttpResponse<any>>;
    public deleteByCodesVoid12(codes: Array<string>, observe?: 'events', reportProgress?: boolean): Observable<HttpEvent<any>>;
    public deleteByCodesVoid12(codes: Array<string>, observe: any = 'body', reportProgress: boolean = false ): Observable<any> {

        if (codes === null || codes === undefined) {
            throw new Error('Required parameter codes was null or undefined when calling deleteByCodesVoid12.');
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

        return this.httpClient.post<any>(`${this.basePath}/integration/OI40DBPartner/deleteByCodes`,
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
    public findAllListOI40DBPartner(observe?: 'body', reportProgress?: boolean): Observable<Array<OI40DBPartner>>;
    public findAllListOI40DBPartner(observe?: 'response', reportProgress?: boolean): Observable<HttpResponse<Array<OI40DBPartner>>>;
    public findAllListOI40DBPartner(observe?: 'events', reportProgress?: boolean): Observable<HttpEvent<Array<OI40DBPartner>>>;
    public findAllListOI40DBPartner(observe: any = 'body', reportProgress: boolean = false ): Observable<any> {

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

        return this.httpClient.get<Array<OI40DBPartner>>(`${this.basePath}/integration/OI40DBPartner`,
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
    public findAllPageOI40DBPartner(p: PageInfo, observe?: 'body', reportProgress?: boolean): Observable<PageOI40DBPartner>;
    public findAllPageOI40DBPartner(p: PageInfo, observe?: 'response', reportProgress?: boolean): Observable<HttpResponse<PageOI40DBPartner>>;
    public findAllPageOI40DBPartner(p: PageInfo, observe?: 'events', reportProgress?: boolean): Observable<HttpEvent<PageOI40DBPartner>>;
    public findAllPageOI40DBPartner(p: PageInfo, observe: any = 'body', reportProgress: boolean = false ): Observable<any> {

        if (p === null || p === undefined) {
            throw new Error('Required parameter p was null or undefined when calling findAllPageOI40DBPartner.');
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

        return this.httpClient.post<PageOI40DBPartner>(`${this.basePath}/integration/OI40DBPartner/findAllPaged`,
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
    public findByAfterIntegrationTsListOI40DBPartner(ts: string, observe?: 'body', reportProgress?: boolean): Observable<Array<OI40DBPartner>>;
    public findByAfterIntegrationTsListOI40DBPartner(ts: string, observe?: 'response', reportProgress?: boolean): Observable<HttpResponse<Array<OI40DBPartner>>>;
    public findByAfterIntegrationTsListOI40DBPartner(ts: string, observe?: 'events', reportProgress?: boolean): Observable<HttpEvent<Array<OI40DBPartner>>>;
    public findByAfterIntegrationTsListOI40DBPartner(ts: string, observe: any = 'body', reportProgress: boolean = false ): Observable<any> {

        if (ts === null || ts === undefined) {
            throw new Error('Required parameter ts was null or undefined when calling findByAfterIntegrationTsListOI40DBPartner.');
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

        return this.httpClient.get<Array<OI40DBPartner>>(`${this.basePath}/integration/OI40DBPartner/integratedAfter/${encodeURIComponent(String(ts))}`,
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
    public findByAfterModifiedTimestampListOI40DBPartner(ts: string, observe?: 'body', reportProgress?: boolean): Observable<Array<OI40DBPartner>>;
    public findByAfterModifiedTimestampListOI40DBPartner(ts: string, observe?: 'response', reportProgress?: boolean): Observable<HttpResponse<Array<OI40DBPartner>>>;
    public findByAfterModifiedTimestampListOI40DBPartner(ts: string, observe?: 'events', reportProgress?: boolean): Observable<HttpEvent<Array<OI40DBPartner>>>;
    public findByAfterModifiedTimestampListOI40DBPartner(ts: string, observe: any = 'body', reportProgress: boolean = false ): Observable<any> {

        if (ts === null || ts === undefined) {
            throw new Error('Required parameter ts was null or undefined when calling findByAfterModifiedTimestampListOI40DBPartner.');
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

        return this.httpClient.get<Array<OI40DBPartner>>(`${this.basePath}/integration/OI40DBPartner/modifiedAfter/${encodeURIComponent(String(ts))}`,
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
    public findByCodeOI40DBPartner(code: string, observe?: 'body', reportProgress?: boolean): Observable<OI40DBPartner>;
    public findByCodeOI40DBPartner(code: string, observe?: 'response', reportProgress?: boolean): Observable<HttpResponse<OI40DBPartner>>;
    public findByCodeOI40DBPartner(code: string, observe?: 'events', reportProgress?: boolean): Observable<HttpEvent<OI40DBPartner>>;
    public findByCodeOI40DBPartner(code: string, observe: any = 'body', reportProgress: boolean = false ): Observable<any> {

        if (code === null || code === undefined) {
            throw new Error('Required parameter code was null or undefined when calling findByCodeOI40DBPartner.');
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

        return this.httpClient.get<OI40DBPartner>(`${this.basePath}/integration/OI40DBPartner/byCode/${encodeURIComponent(String(code))}`,
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
    public findByCodesListOI40DBPartner(codes: Array<string>, observe?: 'body', reportProgress?: boolean): Observable<Array<OI40DBPartner>>;
    public findByCodesListOI40DBPartner(codes: Array<string>, observe?: 'response', reportProgress?: boolean): Observable<HttpResponse<Array<OI40DBPartner>>>;
    public findByCodesListOI40DBPartner(codes: Array<string>, observe?: 'events', reportProgress?: boolean): Observable<HttpEvent<Array<OI40DBPartner>>>;
    public findByCodesListOI40DBPartner(codes: Array<string>, observe: any = 'body', reportProgress: boolean = false ): Observable<any> {

        if (codes === null || codes === undefined) {
            throw new Error('Required parameter codes was null or undefined when calling findByCodesListOI40DBPartner.');
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

        return this.httpClient.post<Array<OI40DBPartner>>(`${this.basePath}/integration/OI40DBPartner/findByCodes`,
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
    public findByQbeListOI40DBPartner(qbe: OI40DBPartner, observe?: 'body', reportProgress?: boolean): Observable<Array<OI40DBPartner>>;
    public findByQbeListOI40DBPartner(qbe: OI40DBPartner, observe?: 'response', reportProgress?: boolean): Observable<HttpResponse<Array<OI40DBPartner>>>;
    public findByQbeListOI40DBPartner(qbe: OI40DBPartner, observe?: 'events', reportProgress?: boolean): Observable<HttpEvent<Array<OI40DBPartner>>>;
    public findByQbeListOI40DBPartner(qbe: OI40DBPartner, observe: any = 'body', reportProgress: boolean = false ): Observable<any> {

        if (qbe === null || qbe === undefined) {
            throw new Error('Required parameter qbe was null or undefined when calling findByQbeListOI40DBPartner.');
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

        return this.httpClient.post<Array<OI40DBPartner>>(`${this.basePath}/integration/OI40DBPartner/findByQbe`,
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
    public findByQbePagedPageOI40DBPartner(qbe: QbeSupportOI40DBPartner, observe?: 'body', reportProgress?: boolean): Observable<PageOI40DBPartner>;
    public findByQbePagedPageOI40DBPartner(qbe: QbeSupportOI40DBPartner, observe?: 'response', reportProgress?: boolean): Observable<HttpResponse<PageOI40DBPartner>>;
    public findByQbePagedPageOI40DBPartner(qbe: QbeSupportOI40DBPartner, observe?: 'events', reportProgress?: boolean): Observable<HttpEvent<PageOI40DBPartner>>;
    public findByQbePagedPageOI40DBPartner(qbe: QbeSupportOI40DBPartner, observe: any = 'body', reportProgress: boolean = false ): Observable<any> {

        if (qbe === null || qbe === undefined) {
            throw new Error('Required parameter qbe was null or undefined when calling findByQbePagedPageOI40DBPartner.');
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

        return this.httpClient.post<PageOI40DBPartner>(`${this.basePath}/integration/OI40DBPartner/findByQbePaged`,
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
    public updateListOI40DBPartner(data: Array<OI40DBPartner>, observe?: 'body', reportProgress?: boolean): Observable<Array<OI40DBPartner>>;
    public updateListOI40DBPartner(data: Array<OI40DBPartner>, observe?: 'response', reportProgress?: boolean): Observable<HttpResponse<Array<OI40DBPartner>>>;
    public updateListOI40DBPartner(data: Array<OI40DBPartner>, observe?: 'events', reportProgress?: boolean): Observable<HttpEvent<Array<OI40DBPartner>>>;
    public updateListOI40DBPartner(data: Array<OI40DBPartner>, observe: any = 'body', reportProgress: boolean = false ): Observable<any> {

        if (data === null || data === undefined) {
            throw new Error('Required parameter data was null or undefined when calling updateListOI40DBPartner.');
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

        return this.httpClient.post<Array<OI40DBPartner>>(`${this.basePath}/integration/OI40DBPartner/update`,
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
    public updateSingleOI40DBPartner(data: OI40DBPartner, observe?: 'body', reportProgress?: boolean): Observable<OI40DBPartner>;
    public updateSingleOI40DBPartner(data: OI40DBPartner, observe?: 'response', reportProgress?: boolean): Observable<HttpResponse<OI40DBPartner>>;
    public updateSingleOI40DBPartner(data: OI40DBPartner, observe?: 'events', reportProgress?: boolean): Observable<HttpEvent<OI40DBPartner>>;
    public updateSingleOI40DBPartner(data: OI40DBPartner, observe: any = 'body', reportProgress: boolean = false ): Observable<any> {

        if (data === null || data === undefined) {
            throw new Error('Required parameter data was null or undefined when calling updateSingleOI40DBPartner.');
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

        return this.httpClient.post<OI40DBPartner>(`${this.basePath}/integration/OI40DBPartner/updateSingle`,
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
