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

import { OI40DBTaskProductionMaterialReservation } from '../model/oI40DBTaskProductionMaterialReservation';
import { PageOI40DBTaskProductionMaterialReservation } from '../model/pageOI40DBTaskProductionMaterialReservation';
import { Pageable } from '../model/pageable';

import { BASE_PATH, COLLECTION_FORMATS }                     from '../variables';
import { Configuration }                                     from '../configuration';


@Injectable()
export class Oi40DbTaskProductionMaterialReservationRepositoryService {

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
    public deleteByCodeVoid28(code: string, observe?: 'body', reportProgress?: boolean): Observable<any>;
    public deleteByCodeVoid28(code: string, observe?: 'response', reportProgress?: boolean): Observable<HttpResponse<any>>;
    public deleteByCodeVoid28(code: string, observe?: 'events', reportProgress?: boolean): Observable<HttpEvent<any>>;
    public deleteByCodeVoid28(code: string, observe: any = 'body', reportProgress: boolean = false ): Observable<any> {

        if (code === null || code === undefined) {
            throw new Error('Required parameter code was null or undefined when calling deleteByCodeVoid28.');
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

        return this.httpClient.get<any>(`${this.basePath}/integration/OI40DBTaskProductionMaterialReservation/deleteByCode/${encodeURIComponent(String(code))}`,
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
    public deleteByCodesVoid28(codes: Array<string>, observe?: 'body', reportProgress?: boolean): Observable<any>;
    public deleteByCodesVoid28(codes: Array<string>, observe?: 'response', reportProgress?: boolean): Observable<HttpResponse<any>>;
    public deleteByCodesVoid28(codes: Array<string>, observe?: 'events', reportProgress?: boolean): Observable<HttpEvent<any>>;
    public deleteByCodesVoid28(codes: Array<string>, observe: any = 'body', reportProgress: boolean = false ): Observable<any> {

        if (codes === null || codes === undefined) {
            throw new Error('Required parameter codes was null or undefined when calling deleteByCodesVoid28.');
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

        return this.httpClient.post<any>(`${this.basePath}/integration/OI40DBTaskProductionMaterialReservation/deleteByCodes`,
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
    public findAllListOI40DBTaskProductionMaterialReservation(observe?: 'body', reportProgress?: boolean): Observable<Array<OI40DBTaskProductionMaterialReservation>>;
    public findAllListOI40DBTaskProductionMaterialReservation(observe?: 'response', reportProgress?: boolean): Observable<HttpResponse<Array<OI40DBTaskProductionMaterialReservation>>>;
    public findAllListOI40DBTaskProductionMaterialReservation(observe?: 'events', reportProgress?: boolean): Observable<HttpEvent<Array<OI40DBTaskProductionMaterialReservation>>>;
    public findAllListOI40DBTaskProductionMaterialReservation(observe: any = 'body', reportProgress: boolean = false ): Observable<any> {

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

        return this.httpClient.get<Array<OI40DBTaskProductionMaterialReservation>>(`${this.basePath}/integration/OI40DBTaskProductionMaterialReservation`,
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
    public findAllPage28(p: Pageable, observe?: 'body', reportProgress?: boolean): Observable<PageOI40DBTaskProductionMaterialReservation>;
    public findAllPage28(p: Pageable, observe?: 'response', reportProgress?: boolean): Observable<HttpResponse<PageOI40DBTaskProductionMaterialReservation>>;
    public findAllPage28(p: Pageable, observe?: 'events', reportProgress?: boolean): Observable<HttpEvent<PageOI40DBTaskProductionMaterialReservation>>;
    public findAllPage28(p: Pageable, observe: any = 'body', reportProgress: boolean = false ): Observable<any> {

        if (p === null || p === undefined) {
            throw new Error('Required parameter p was null or undefined when calling findAllPage28.');
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

        return this.httpClient.post<PageOI40DBTaskProductionMaterialReservation>(`${this.basePath}/integration/OI40DBTaskProductionMaterialReservation/findAllPaged`,
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
    public findByAfterIntegrationTsListOI40DBTaskProductionMaterialReservation(ts: string, observe?: 'body', reportProgress?: boolean): Observable<Array<OI40DBTaskProductionMaterialReservation>>;
    public findByAfterIntegrationTsListOI40DBTaskProductionMaterialReservation(ts: string, observe?: 'response', reportProgress?: boolean): Observable<HttpResponse<Array<OI40DBTaskProductionMaterialReservation>>>;
    public findByAfterIntegrationTsListOI40DBTaskProductionMaterialReservation(ts: string, observe?: 'events', reportProgress?: boolean): Observable<HttpEvent<Array<OI40DBTaskProductionMaterialReservation>>>;
    public findByAfterIntegrationTsListOI40DBTaskProductionMaterialReservation(ts: string, observe: any = 'body', reportProgress: boolean = false ): Observable<any> {

        if (ts === null || ts === undefined) {
            throw new Error('Required parameter ts was null or undefined when calling findByAfterIntegrationTsListOI40DBTaskProductionMaterialReservation.');
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

        return this.httpClient.get<Array<OI40DBTaskProductionMaterialReservation>>(`${this.basePath}/integration/OI40DBTaskProductionMaterialReservation/integratedAfter/${encodeURIComponent(String(ts))}`,
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
    public findByAfterModifiedTimestampListOI40DBTaskProductionMaterialReservation(ts: string, observe?: 'body', reportProgress?: boolean): Observable<Array<OI40DBTaskProductionMaterialReservation>>;
    public findByAfterModifiedTimestampListOI40DBTaskProductionMaterialReservation(ts: string, observe?: 'response', reportProgress?: boolean): Observable<HttpResponse<Array<OI40DBTaskProductionMaterialReservation>>>;
    public findByAfterModifiedTimestampListOI40DBTaskProductionMaterialReservation(ts: string, observe?: 'events', reportProgress?: boolean): Observable<HttpEvent<Array<OI40DBTaskProductionMaterialReservation>>>;
    public findByAfterModifiedTimestampListOI40DBTaskProductionMaterialReservation(ts: string, observe: any = 'body', reportProgress: boolean = false ): Observable<any> {

        if (ts === null || ts === undefined) {
            throw new Error('Required parameter ts was null or undefined when calling findByAfterModifiedTimestampListOI40DBTaskProductionMaterialReservation.');
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

        return this.httpClient.get<Array<OI40DBTaskProductionMaterialReservation>>(`${this.basePath}/integration/OI40DBTaskProductionMaterialReservation/modifiedAfter/${encodeURIComponent(String(ts))}`,
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
    public findByCodeOI40DBTaskProductionMaterialReservation(code: string, observe?: 'body', reportProgress?: boolean): Observable<OI40DBTaskProductionMaterialReservation>;
    public findByCodeOI40DBTaskProductionMaterialReservation(code: string, observe?: 'response', reportProgress?: boolean): Observable<HttpResponse<OI40DBTaskProductionMaterialReservation>>;
    public findByCodeOI40DBTaskProductionMaterialReservation(code: string, observe?: 'events', reportProgress?: boolean): Observable<HttpEvent<OI40DBTaskProductionMaterialReservation>>;
    public findByCodeOI40DBTaskProductionMaterialReservation(code: string, observe: any = 'body', reportProgress: boolean = false ): Observable<any> {

        if (code === null || code === undefined) {
            throw new Error('Required parameter code was null or undefined when calling findByCodeOI40DBTaskProductionMaterialReservation.');
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

        return this.httpClient.get<OI40DBTaskProductionMaterialReservation>(`${this.basePath}/integration/OI40DBTaskProductionMaterialReservation/byCode/${encodeURIComponent(String(code))}`,
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
    public findByCodesListOI40DBTaskProductionMaterialReservation(codes: Array<string>, observe?: 'body', reportProgress?: boolean): Observable<Array<OI40DBTaskProductionMaterialReservation>>;
    public findByCodesListOI40DBTaskProductionMaterialReservation(codes: Array<string>, observe?: 'response', reportProgress?: boolean): Observable<HttpResponse<Array<OI40DBTaskProductionMaterialReservation>>>;
    public findByCodesListOI40DBTaskProductionMaterialReservation(codes: Array<string>, observe?: 'events', reportProgress?: boolean): Observable<HttpEvent<Array<OI40DBTaskProductionMaterialReservation>>>;
    public findByCodesListOI40DBTaskProductionMaterialReservation(codes: Array<string>, observe: any = 'body', reportProgress: boolean = false ): Observable<any> {

        if (codes === null || codes === undefined) {
            throw new Error('Required parameter codes was null or undefined when calling findByCodesListOI40DBTaskProductionMaterialReservation.');
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

        return this.httpClient.post<Array<OI40DBTaskProductionMaterialReservation>>(`${this.basePath}/integration/OI40DBTaskProductionMaterialReservation/findByCodes`,
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
    public findByQbeListOI40DBTaskProductionMaterialReservation(qbe: OI40DBTaskProductionMaterialReservation, observe?: 'body', reportProgress?: boolean): Observable<Array<OI40DBTaskProductionMaterialReservation>>;
    public findByQbeListOI40DBTaskProductionMaterialReservation(qbe: OI40DBTaskProductionMaterialReservation, observe?: 'response', reportProgress?: boolean): Observable<HttpResponse<Array<OI40DBTaskProductionMaterialReservation>>>;
    public findByQbeListOI40DBTaskProductionMaterialReservation(qbe: OI40DBTaskProductionMaterialReservation, observe?: 'events', reportProgress?: boolean): Observable<HttpEvent<Array<OI40DBTaskProductionMaterialReservation>>>;
    public findByQbeListOI40DBTaskProductionMaterialReservation(qbe: OI40DBTaskProductionMaterialReservation, observe: any = 'body', reportProgress: boolean = false ): Observable<any> {

        if (qbe === null || qbe === undefined) {
            throw new Error('Required parameter qbe was null or undefined when calling findByQbeListOI40DBTaskProductionMaterialReservation.');
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

        return this.httpClient.post<Array<OI40DBTaskProductionMaterialReservation>>(`${this.basePath}/integration/OI40DBTaskProductionMaterialReservation/findByQbe`,
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
     * @param offset 
     * @param pageNumber 
     * @param pageSize 
     * @param paged 
     * @param sortSorted 
     * @param sortUnsorted 
     * @param unpaged 
     * @param observe set whether or not to return the data Observable as the body, response or events. defaults to returning the body.
     * @param reportProgress flag to report request and response progress.
     */
    public findByQbePagedPage28(qbe: OI40DBTaskProductionMaterialReservation, offset?: number, pageNumber?: number, pageSize?: number, paged?: boolean, sortSorted?: boolean, sortUnsorted?: boolean, unpaged?: boolean, observe?: 'body', reportProgress?: boolean): Observable<PageOI40DBTaskProductionMaterialReservation>;
    public findByQbePagedPage28(qbe: OI40DBTaskProductionMaterialReservation, offset?: number, pageNumber?: number, pageSize?: number, paged?: boolean, sortSorted?: boolean, sortUnsorted?: boolean, unpaged?: boolean, observe?: 'response', reportProgress?: boolean): Observable<HttpResponse<PageOI40DBTaskProductionMaterialReservation>>;
    public findByQbePagedPage28(qbe: OI40DBTaskProductionMaterialReservation, offset?: number, pageNumber?: number, pageSize?: number, paged?: boolean, sortSorted?: boolean, sortUnsorted?: boolean, unpaged?: boolean, observe?: 'events', reportProgress?: boolean): Observable<HttpEvent<PageOI40DBTaskProductionMaterialReservation>>;
    public findByQbePagedPage28(qbe: OI40DBTaskProductionMaterialReservation, offset?: number, pageNumber?: number, pageSize?: number, paged?: boolean, sortSorted?: boolean, sortUnsorted?: boolean, unpaged?: boolean, observe: any = 'body', reportProgress: boolean = false ): Observable<any> {

        if (qbe === null || qbe === undefined) {
            throw new Error('Required parameter qbe was null or undefined when calling findByQbePagedPage28.');
        }








        let queryParameters = new HttpParams({encoder: new CustomHttpUrlEncodingCodec()});
        if (offset !== undefined && offset !== null) {
            queryParameters = queryParameters.set('offset', <any>offset);
        }
        if (pageNumber !== undefined && pageNumber !== null) {
            queryParameters = queryParameters.set('pageNumber', <any>pageNumber);
        }
        if (pageSize !== undefined && pageSize !== null) {
            queryParameters = queryParameters.set('pageSize', <any>pageSize);
        }
        if (paged !== undefined && paged !== null) {
            queryParameters = queryParameters.set('paged', <any>paged);
        }
        if (sortSorted !== undefined && sortSorted !== null) {
            queryParameters = queryParameters.set('sort.sorted', <any>sortSorted);
        }
        if (sortUnsorted !== undefined && sortUnsorted !== null) {
            queryParameters = queryParameters.set('sort.unsorted', <any>sortUnsorted);
        }
        if (unpaged !== undefined && unpaged !== null) {
            queryParameters = queryParameters.set('unpaged', <any>unpaged);
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

        return this.httpClient.post<PageOI40DBTaskProductionMaterialReservation>(`${this.basePath}/integration/OI40DBTaskProductionMaterialReservation/findByQbePaged`,
            qbe,
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
     * update
     * 
     * @param data data
     * @param observe set whether or not to return the data Observable as the body, response or events. defaults to returning the body.
     * @param reportProgress flag to report request and response progress.
     */
    public updateListOI40DBTaskProductionMaterialReservation(data: Array<OI40DBTaskProductionMaterialReservation>, observe?: 'body', reportProgress?: boolean): Observable<Array<OI40DBTaskProductionMaterialReservation>>;
    public updateListOI40DBTaskProductionMaterialReservation(data: Array<OI40DBTaskProductionMaterialReservation>, observe?: 'response', reportProgress?: boolean): Observable<HttpResponse<Array<OI40DBTaskProductionMaterialReservation>>>;
    public updateListOI40DBTaskProductionMaterialReservation(data: Array<OI40DBTaskProductionMaterialReservation>, observe?: 'events', reportProgress?: boolean): Observable<HttpEvent<Array<OI40DBTaskProductionMaterialReservation>>>;
    public updateListOI40DBTaskProductionMaterialReservation(data: Array<OI40DBTaskProductionMaterialReservation>, observe: any = 'body', reportProgress: boolean = false ): Observable<any> {

        if (data === null || data === undefined) {
            throw new Error('Required parameter data was null or undefined when calling updateListOI40DBTaskProductionMaterialReservation.');
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

        return this.httpClient.post<Array<OI40DBTaskProductionMaterialReservation>>(`${this.basePath}/integration/OI40DBTaskProductionMaterialReservation/update`,
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
