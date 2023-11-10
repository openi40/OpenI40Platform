package com.openi40.dbmodel.java.client.api;

import com.openi40.dbmodel.java.client.invoker.ApiClient;

import com.openi40.dbmodel.java.client.model.AutoCompleteData;
import com.openi40.dbmodel.java.client.model.LookupData;
import com.openi40.dbmodel.java.client.model.OI40DBTaskStockMaterialReservation;
import com.openi40.dbmodel.java.client.model.PageInfo;
import com.openi40.dbmodel.java.client.model.PageOI40DBTaskStockMaterialReservation;
import com.openi40.dbmodel.java.client.model.QbeSupportOI40DBTaskStockMaterialReservation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaClientCodegen", date = "2023-11-10T15:05:07.942+01:00")
@Component("com.openi40.dbmodel.java.client.api.Oi40DbTaskStockMaterialReservationRepositoryApi")
public class Oi40DbTaskStockMaterialReservationRepositoryApi {
    private ApiClient apiClient;

    public Oi40DbTaskStockMaterialReservationRepositoryApi() {
        this(new ApiClient());
    }

    @Autowired
    public Oi40DbTaskStockMaterialReservationRepositoryApi(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    public ApiClient getApiClient() {
        return apiClient;
    }

    public void setApiClient(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    /**
     * deleteByCode
     * 
     * <p><b>200</b> - OK
     * <p><b>401</b> - Unauthorized
     * <p><b>403</b> - Forbidden
     * <p><b>404</b> - Not Found
     * @param code code (required)
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public void deleteByCodeVoid33(String code) throws RestClientException {
        deleteByCodeVoid33WithHttpInfo(code);
    }

    /**
     * deleteByCode
     * 
     * <p><b>200</b> - OK
     * <p><b>401</b> - Unauthorized
     * <p><b>403</b> - Forbidden
     * <p><b>404</b> - Not Found
     * @param code code (required)
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Void> deleteByCodeVoid33WithHttpInfo(String code) throws RestClientException {
        Object postBody = null;
        
        // verify the required parameter 'code' is set
        if (code == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'code' when calling deleteByCodeVoid33");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("code", code);
        String path = UriComponentsBuilder.fromPath("/integration/OI40DBTaskStockMaterialReservation/deleteByCode/{code}").buildAndExpand(uriVariables).toUriString();

        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, Object> formParams = new LinkedMultiValueMap<String, Object>();

        final String[] accepts = { 
            "*/*"
        };
        final List<MediaType> accept = apiClient.selectHeaderAccept(accepts);
        final String[] contentTypes = { };
        final MediaType contentType = apiClient.selectHeaderContentType(contentTypes);

        String[] authNames = new String[] {  };

        ParameterizedTypeReference<Void> returnType = new ParameterizedTypeReference<Void>() {};
        return apiClient.invokeAPI(path, HttpMethod.GET, queryParams, postBody, headerParams, formParams, accept, contentType, authNames, returnType);
    }
    /**
     * deleteByCodes
     * 
     * <p><b>200</b> - OK
     * <p><b>201</b> - Created
     * <p><b>401</b> - Unauthorized
     * <p><b>403</b> - Forbidden
     * <p><b>404</b> - Not Found
     * @param codes codes (required)
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public void deleteByCodesVoid33(List<String> codes) throws RestClientException {
        deleteByCodesVoid33WithHttpInfo(codes);
    }

    /**
     * deleteByCodes
     * 
     * <p><b>200</b> - OK
     * <p><b>201</b> - Created
     * <p><b>401</b> - Unauthorized
     * <p><b>403</b> - Forbidden
     * <p><b>404</b> - Not Found
     * @param codes codes (required)
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Void> deleteByCodesVoid33WithHttpInfo(List<String> codes) throws RestClientException {
        Object postBody = codes;
        
        // verify the required parameter 'codes' is set
        if (codes == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'codes' when calling deleteByCodesVoid33");
        }
        
        String path = UriComponentsBuilder.fromPath("/integration/OI40DBTaskStockMaterialReservation/deleteByCodes").build().toUriString();

        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, Object> formParams = new LinkedMultiValueMap<String, Object>();

        final String[] accepts = { 
            "*/*"
        };
        final List<MediaType> accept = apiClient.selectHeaderAccept(accepts);
        final String[] contentTypes = { 
            "application/json"
        };
        final MediaType contentType = apiClient.selectHeaderContentType(contentTypes);

        String[] authNames = new String[] {  };

        ParameterizedTypeReference<Void> returnType = new ParameterizedTypeReference<Void>() {};
        return apiClient.invokeAPI(path, HttpMethod.POST, queryParams, postBody, headerParams, formParams, accept, contentType, authNames, returnType);
    }
    /**
     * doAutocomplete
     * 
     * <p><b>200</b> - OK
     * <p><b>201</b> - Created
     * <p><b>401</b> - Unauthorized
     * <p><b>403</b> - Forbidden
     * <p><b>404</b> - Not Found
     * @param autoCompleteData autoCompleteData (required)
     * @return PageOI40DBTaskStockMaterialReservation
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public PageOI40DBTaskStockMaterialReservation doAutocompletePageOI40DBTaskStockMaterialReservation(AutoCompleteData autoCompleteData) throws RestClientException {
        return doAutocompletePageOI40DBTaskStockMaterialReservationWithHttpInfo(autoCompleteData).getBody();
    }

    /**
     * doAutocomplete
     * 
     * <p><b>200</b> - OK
     * <p><b>201</b> - Created
     * <p><b>401</b> - Unauthorized
     * <p><b>403</b> - Forbidden
     * <p><b>404</b> - Not Found
     * @param autoCompleteData autoCompleteData (required)
     * @return ResponseEntity&lt;PageOI40DBTaskStockMaterialReservation&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<PageOI40DBTaskStockMaterialReservation> doAutocompletePageOI40DBTaskStockMaterialReservationWithHttpInfo(AutoCompleteData autoCompleteData) throws RestClientException {
        Object postBody = autoCompleteData;
        
        // verify the required parameter 'autoCompleteData' is set
        if (autoCompleteData == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'autoCompleteData' when calling doAutocompletePageOI40DBTaskStockMaterialReservation");
        }
        
        String path = UriComponentsBuilder.fromPath("/integration/OI40DBTaskStockMaterialReservation/doAutocomplete").build().toUriString();

        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, Object> formParams = new LinkedMultiValueMap<String, Object>();

        final String[] accepts = { 
            "*/*"
        };
        final List<MediaType> accept = apiClient.selectHeaderAccept(accepts);
        final String[] contentTypes = { 
            "application/json"
        };
        final MediaType contentType = apiClient.selectHeaderContentType(contentTypes);

        String[] authNames = new String[] {  };

        ParameterizedTypeReference<PageOI40DBTaskStockMaterialReservation> returnType = new ParameterizedTypeReference<PageOI40DBTaskStockMaterialReservation>() {};
        return apiClient.invokeAPI(path, HttpMethod.POST, queryParams, postBody, headerParams, formParams, accept, contentType, authNames, returnType);
    }
    /**
     * doLookup
     * 
     * <p><b>200</b> - OK
     * <p><b>201</b> - Created
     * <p><b>401</b> - Unauthorized
     * <p><b>403</b> - Forbidden
     * <p><b>404</b> - Not Found
     * @param lookup lookup (required)
     * @return PageOI40DBTaskStockMaterialReservation
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public PageOI40DBTaskStockMaterialReservation doLookupPageOI40DBTaskStockMaterialReservation(LookupData lookup) throws RestClientException {
        return doLookupPageOI40DBTaskStockMaterialReservationWithHttpInfo(lookup).getBody();
    }

    /**
     * doLookup
     * 
     * <p><b>200</b> - OK
     * <p><b>201</b> - Created
     * <p><b>401</b> - Unauthorized
     * <p><b>403</b> - Forbidden
     * <p><b>404</b> - Not Found
     * @param lookup lookup (required)
     * @return ResponseEntity&lt;PageOI40DBTaskStockMaterialReservation&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<PageOI40DBTaskStockMaterialReservation> doLookupPageOI40DBTaskStockMaterialReservationWithHttpInfo(LookupData lookup) throws RestClientException {
        Object postBody = lookup;
        
        // verify the required parameter 'lookup' is set
        if (lookup == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'lookup' when calling doLookupPageOI40DBTaskStockMaterialReservation");
        }
        
        String path = UriComponentsBuilder.fromPath("/integration/OI40DBTaskStockMaterialReservation/doLookup").build().toUriString();

        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, Object> formParams = new LinkedMultiValueMap<String, Object>();

        final String[] accepts = { 
            "*/*"
        };
        final List<MediaType> accept = apiClient.selectHeaderAccept(accepts);
        final String[] contentTypes = { 
            "application/json"
        };
        final MediaType contentType = apiClient.selectHeaderContentType(contentTypes);

        String[] authNames = new String[] {  };

        ParameterizedTypeReference<PageOI40DBTaskStockMaterialReservation> returnType = new ParameterizedTypeReference<PageOI40DBTaskStockMaterialReservation>() {};
        return apiClient.invokeAPI(path, HttpMethod.POST, queryParams, postBody, headerParams, formParams, accept, contentType, authNames, returnType);
    }
    /**
     * findAll
     * 
     * <p><b>200</b> - OK
     * <p><b>401</b> - Unauthorized
     * <p><b>403</b> - Forbidden
     * <p><b>404</b> - Not Found
     * @return List&lt;OI40DBTaskStockMaterialReservation&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public List<OI40DBTaskStockMaterialReservation> findAllListOI40DBTaskStockMaterialReservation() throws RestClientException {
        return findAllListOI40DBTaskStockMaterialReservationWithHttpInfo().getBody();
    }

    /**
     * findAll
     * 
     * <p><b>200</b> - OK
     * <p><b>401</b> - Unauthorized
     * <p><b>403</b> - Forbidden
     * <p><b>404</b> - Not Found
     * @return ResponseEntity&lt;List&lt;OI40DBTaskStockMaterialReservation&gt;&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<List<OI40DBTaskStockMaterialReservation>> findAllListOI40DBTaskStockMaterialReservationWithHttpInfo() throws RestClientException {
        Object postBody = null;
        
        String path = UriComponentsBuilder.fromPath("/integration/OI40DBTaskStockMaterialReservation").build().toUriString();

        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, Object> formParams = new LinkedMultiValueMap<String, Object>();

        final String[] accepts = { 
            "application/json"
        };
        final List<MediaType> accept = apiClient.selectHeaderAccept(accepts);
        final String[] contentTypes = { };
        final MediaType contentType = apiClient.selectHeaderContentType(contentTypes);

        String[] authNames = new String[] {  };

        ParameterizedTypeReference<List<OI40DBTaskStockMaterialReservation>> returnType = new ParameterizedTypeReference<List<OI40DBTaskStockMaterialReservation>>() {};
        return apiClient.invokeAPI(path, HttpMethod.GET, queryParams, postBody, headerParams, formParams, accept, contentType, authNames, returnType);
    }
    /**
     * findAll
     * 
     * <p><b>200</b> - OK
     * <p><b>201</b> - Created
     * <p><b>401</b> - Unauthorized
     * <p><b>403</b> - Forbidden
     * <p><b>404</b> - Not Found
     * @param p p (required)
     * @return PageOI40DBTaskStockMaterialReservation
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public PageOI40DBTaskStockMaterialReservation findAllPageOI40DBTaskStockMaterialReservation(PageInfo p) throws RestClientException {
        return findAllPageOI40DBTaskStockMaterialReservationWithHttpInfo(p).getBody();
    }

    /**
     * findAll
     * 
     * <p><b>200</b> - OK
     * <p><b>201</b> - Created
     * <p><b>401</b> - Unauthorized
     * <p><b>403</b> - Forbidden
     * <p><b>404</b> - Not Found
     * @param p p (required)
     * @return ResponseEntity&lt;PageOI40DBTaskStockMaterialReservation&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<PageOI40DBTaskStockMaterialReservation> findAllPageOI40DBTaskStockMaterialReservationWithHttpInfo(PageInfo p) throws RestClientException {
        Object postBody = p;
        
        // verify the required parameter 'p' is set
        if (p == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'p' when calling findAllPageOI40DBTaskStockMaterialReservation");
        }
        
        String path = UriComponentsBuilder.fromPath("/integration/OI40DBTaskStockMaterialReservation/findAllPaged").build().toUriString();

        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, Object> formParams = new LinkedMultiValueMap<String, Object>();

        final String[] accepts = { 
            "application/json"
        };
        final List<MediaType> accept = apiClient.selectHeaderAccept(accepts);
        final String[] contentTypes = { 
            "application/json"
        };
        final MediaType contentType = apiClient.selectHeaderContentType(contentTypes);

        String[] authNames = new String[] {  };

        ParameterizedTypeReference<PageOI40DBTaskStockMaterialReservation> returnType = new ParameterizedTypeReference<PageOI40DBTaskStockMaterialReservation>() {};
        return apiClient.invokeAPI(path, HttpMethod.POST, queryParams, postBody, headerParams, formParams, accept, contentType, authNames, returnType);
    }
    /**
     * findByAfterIntegrationTs
     * 
     * <p><b>200</b> - OK
     * <p><b>401</b> - Unauthorized
     * <p><b>403</b> - Forbidden
     * <p><b>404</b> - Not Found
     * @param ts ts (required)
     * @return List&lt;OI40DBTaskStockMaterialReservation&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public List<OI40DBTaskStockMaterialReservation> findByAfterIntegrationTsListOI40DBTaskStockMaterialReservation(String ts) throws RestClientException {
        return findByAfterIntegrationTsListOI40DBTaskStockMaterialReservationWithHttpInfo(ts).getBody();
    }

    /**
     * findByAfterIntegrationTs
     * 
     * <p><b>200</b> - OK
     * <p><b>401</b> - Unauthorized
     * <p><b>403</b> - Forbidden
     * <p><b>404</b> - Not Found
     * @param ts ts (required)
     * @return ResponseEntity&lt;List&lt;OI40DBTaskStockMaterialReservation&gt;&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<List<OI40DBTaskStockMaterialReservation>> findByAfterIntegrationTsListOI40DBTaskStockMaterialReservationWithHttpInfo(String ts) throws RestClientException {
        Object postBody = null;
        
        // verify the required parameter 'ts' is set
        if (ts == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'ts' when calling findByAfterIntegrationTsListOI40DBTaskStockMaterialReservation");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("ts", ts);
        String path = UriComponentsBuilder.fromPath("/integration/OI40DBTaskStockMaterialReservation/integratedAfter/{ts}").buildAndExpand(uriVariables).toUriString();

        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, Object> formParams = new LinkedMultiValueMap<String, Object>();

        final String[] accepts = { 
            "*/*"
        };
        final List<MediaType> accept = apiClient.selectHeaderAccept(accepts);
        final String[] contentTypes = { };
        final MediaType contentType = apiClient.selectHeaderContentType(contentTypes);

        String[] authNames = new String[] {  };

        ParameterizedTypeReference<List<OI40DBTaskStockMaterialReservation>> returnType = new ParameterizedTypeReference<List<OI40DBTaskStockMaterialReservation>>() {};
        return apiClient.invokeAPI(path, HttpMethod.GET, queryParams, postBody, headerParams, formParams, accept, contentType, authNames, returnType);
    }
    /**
     * findByAfterModifiedTimestamp
     * 
     * <p><b>200</b> - OK
     * <p><b>401</b> - Unauthorized
     * <p><b>403</b> - Forbidden
     * <p><b>404</b> - Not Found
     * @param ts ts (required)
     * @return List&lt;OI40DBTaskStockMaterialReservation&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public List<OI40DBTaskStockMaterialReservation> findByAfterModifiedTimestampListOI40DBTaskStockMaterialReservation(String ts) throws RestClientException {
        return findByAfterModifiedTimestampListOI40DBTaskStockMaterialReservationWithHttpInfo(ts).getBody();
    }

    /**
     * findByAfterModifiedTimestamp
     * 
     * <p><b>200</b> - OK
     * <p><b>401</b> - Unauthorized
     * <p><b>403</b> - Forbidden
     * <p><b>404</b> - Not Found
     * @param ts ts (required)
     * @return ResponseEntity&lt;List&lt;OI40DBTaskStockMaterialReservation&gt;&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<List<OI40DBTaskStockMaterialReservation>> findByAfterModifiedTimestampListOI40DBTaskStockMaterialReservationWithHttpInfo(String ts) throws RestClientException {
        Object postBody = null;
        
        // verify the required parameter 'ts' is set
        if (ts == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'ts' when calling findByAfterModifiedTimestampListOI40DBTaskStockMaterialReservation");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("ts", ts);
        String path = UriComponentsBuilder.fromPath("/integration/OI40DBTaskStockMaterialReservation/modifiedAfter/{ts}").buildAndExpand(uriVariables).toUriString();

        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, Object> formParams = new LinkedMultiValueMap<String, Object>();

        final String[] accepts = { 
            "*/*"
        };
        final List<MediaType> accept = apiClient.selectHeaderAccept(accepts);
        final String[] contentTypes = { };
        final MediaType contentType = apiClient.selectHeaderContentType(contentTypes);

        String[] authNames = new String[] {  };

        ParameterizedTypeReference<List<OI40DBTaskStockMaterialReservation>> returnType = new ParameterizedTypeReference<List<OI40DBTaskStockMaterialReservation>>() {};
        return apiClient.invokeAPI(path, HttpMethod.GET, queryParams, postBody, headerParams, formParams, accept, contentType, authNames, returnType);
    }
    /**
     * findByCode
     * 
     * <p><b>200</b> - OK
     * <p><b>401</b> - Unauthorized
     * <p><b>403</b> - Forbidden
     * <p><b>404</b> - Not Found
     * @param code code (required)
     * @return OI40DBTaskStockMaterialReservation
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public OI40DBTaskStockMaterialReservation findByCodeOI40DBTaskStockMaterialReservation(String code) throws RestClientException {
        return findByCodeOI40DBTaskStockMaterialReservationWithHttpInfo(code).getBody();
    }

    /**
     * findByCode
     * 
     * <p><b>200</b> - OK
     * <p><b>401</b> - Unauthorized
     * <p><b>403</b> - Forbidden
     * <p><b>404</b> - Not Found
     * @param code code (required)
     * @return ResponseEntity&lt;OI40DBTaskStockMaterialReservation&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<OI40DBTaskStockMaterialReservation> findByCodeOI40DBTaskStockMaterialReservationWithHttpInfo(String code) throws RestClientException {
        Object postBody = null;
        
        // verify the required parameter 'code' is set
        if (code == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'code' when calling findByCodeOI40DBTaskStockMaterialReservation");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("code", code);
        String path = UriComponentsBuilder.fromPath("/integration/OI40DBTaskStockMaterialReservation/byCode/{code}").buildAndExpand(uriVariables).toUriString();

        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, Object> formParams = new LinkedMultiValueMap<String, Object>();

        final String[] accepts = { 
            "application/json"
        };
        final List<MediaType> accept = apiClient.selectHeaderAccept(accepts);
        final String[] contentTypes = { };
        final MediaType contentType = apiClient.selectHeaderContentType(contentTypes);

        String[] authNames = new String[] {  };

        ParameterizedTypeReference<OI40DBTaskStockMaterialReservation> returnType = new ParameterizedTypeReference<OI40DBTaskStockMaterialReservation>() {};
        return apiClient.invokeAPI(path, HttpMethod.GET, queryParams, postBody, headerParams, formParams, accept, contentType, authNames, returnType);
    }
    /**
     * findByCodes
     * 
     * <p><b>200</b> - OK
     * <p><b>201</b> - Created
     * <p><b>401</b> - Unauthorized
     * <p><b>403</b> - Forbidden
     * <p><b>404</b> - Not Found
     * @param codes codes (required)
     * @return List&lt;OI40DBTaskStockMaterialReservation&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public List<OI40DBTaskStockMaterialReservation> findByCodesListOI40DBTaskStockMaterialReservation(List<String> codes) throws RestClientException {
        return findByCodesListOI40DBTaskStockMaterialReservationWithHttpInfo(codes).getBody();
    }

    /**
     * findByCodes
     * 
     * <p><b>200</b> - OK
     * <p><b>201</b> - Created
     * <p><b>401</b> - Unauthorized
     * <p><b>403</b> - Forbidden
     * <p><b>404</b> - Not Found
     * @param codes codes (required)
     * @return ResponseEntity&lt;List&lt;OI40DBTaskStockMaterialReservation&gt;&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<List<OI40DBTaskStockMaterialReservation>> findByCodesListOI40DBTaskStockMaterialReservationWithHttpInfo(List<String> codes) throws RestClientException {
        Object postBody = codes;
        
        // verify the required parameter 'codes' is set
        if (codes == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'codes' when calling findByCodesListOI40DBTaskStockMaterialReservation");
        }
        
        String path = UriComponentsBuilder.fromPath("/integration/OI40DBTaskStockMaterialReservation/findByCodes").build().toUriString();

        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, Object> formParams = new LinkedMultiValueMap<String, Object>();

        final String[] accepts = { 
            "application/json"
        };
        final List<MediaType> accept = apiClient.selectHeaderAccept(accepts);
        final String[] contentTypes = { 
            "application/json"
        };
        final MediaType contentType = apiClient.selectHeaderContentType(contentTypes);

        String[] authNames = new String[] {  };

        ParameterizedTypeReference<List<OI40DBTaskStockMaterialReservation>> returnType = new ParameterizedTypeReference<List<OI40DBTaskStockMaterialReservation>>() {};
        return apiClient.invokeAPI(path, HttpMethod.POST, queryParams, postBody, headerParams, formParams, accept, contentType, authNames, returnType);
    }
    /**
     * findByQbe
     * 
     * <p><b>200</b> - OK
     * <p><b>201</b> - Created
     * <p><b>401</b> - Unauthorized
     * <p><b>403</b> - Forbidden
     * <p><b>404</b> - Not Found
     * @param qbe qbe (required)
     * @return List&lt;OI40DBTaskStockMaterialReservation&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public List<OI40DBTaskStockMaterialReservation> findByQbeListOI40DBTaskStockMaterialReservation(OI40DBTaskStockMaterialReservation qbe) throws RestClientException {
        return findByQbeListOI40DBTaskStockMaterialReservationWithHttpInfo(qbe).getBody();
    }

    /**
     * findByQbe
     * 
     * <p><b>200</b> - OK
     * <p><b>201</b> - Created
     * <p><b>401</b> - Unauthorized
     * <p><b>403</b> - Forbidden
     * <p><b>404</b> - Not Found
     * @param qbe qbe (required)
     * @return ResponseEntity&lt;List&lt;OI40DBTaskStockMaterialReservation&gt;&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<List<OI40DBTaskStockMaterialReservation>> findByQbeListOI40DBTaskStockMaterialReservationWithHttpInfo(OI40DBTaskStockMaterialReservation qbe) throws RestClientException {
        Object postBody = qbe;
        
        // verify the required parameter 'qbe' is set
        if (qbe == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'qbe' when calling findByQbeListOI40DBTaskStockMaterialReservation");
        }
        
        String path = UriComponentsBuilder.fromPath("/integration/OI40DBTaskStockMaterialReservation/findByQbe").build().toUriString();

        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, Object> formParams = new LinkedMultiValueMap<String, Object>();

        final String[] accepts = { 
            "application/json"
        };
        final List<MediaType> accept = apiClient.selectHeaderAccept(accepts);
        final String[] contentTypes = { 
            "application/json"
        };
        final MediaType contentType = apiClient.selectHeaderContentType(contentTypes);

        String[] authNames = new String[] {  };

        ParameterizedTypeReference<List<OI40DBTaskStockMaterialReservation>> returnType = new ParameterizedTypeReference<List<OI40DBTaskStockMaterialReservation>>() {};
        return apiClient.invokeAPI(path, HttpMethod.POST, queryParams, postBody, headerParams, formParams, accept, contentType, authNames, returnType);
    }
    /**
     * findByQbePaged
     * 
     * <p><b>200</b> - OK
     * <p><b>201</b> - Created
     * <p><b>401</b> - Unauthorized
     * <p><b>403</b> - Forbidden
     * <p><b>404</b> - Not Found
     * @param qbe qbe (required)
     * @return PageOI40DBTaskStockMaterialReservation
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public PageOI40DBTaskStockMaterialReservation findByQbePagedPageOI40DBTaskStockMaterialReservation(QbeSupportOI40DBTaskStockMaterialReservation qbe) throws RestClientException {
        return findByQbePagedPageOI40DBTaskStockMaterialReservationWithHttpInfo(qbe).getBody();
    }

    /**
     * findByQbePaged
     * 
     * <p><b>200</b> - OK
     * <p><b>201</b> - Created
     * <p><b>401</b> - Unauthorized
     * <p><b>403</b> - Forbidden
     * <p><b>404</b> - Not Found
     * @param qbe qbe (required)
     * @return ResponseEntity&lt;PageOI40DBTaskStockMaterialReservation&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<PageOI40DBTaskStockMaterialReservation> findByQbePagedPageOI40DBTaskStockMaterialReservationWithHttpInfo(QbeSupportOI40DBTaskStockMaterialReservation qbe) throws RestClientException {
        Object postBody = qbe;
        
        // verify the required parameter 'qbe' is set
        if (qbe == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'qbe' when calling findByQbePagedPageOI40DBTaskStockMaterialReservation");
        }
        
        String path = UriComponentsBuilder.fromPath("/integration/OI40DBTaskStockMaterialReservation/findByQbePaged").build().toUriString();

        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, Object> formParams = new LinkedMultiValueMap<String, Object>();

        final String[] accepts = { 
            "application/json"
        };
        final List<MediaType> accept = apiClient.selectHeaderAccept(accepts);
        final String[] contentTypes = { 
            "application/json"
        };
        final MediaType contentType = apiClient.selectHeaderContentType(contentTypes);

        String[] authNames = new String[] {  };

        ParameterizedTypeReference<PageOI40DBTaskStockMaterialReservation> returnType = new ParameterizedTypeReference<PageOI40DBTaskStockMaterialReservation>() {};
        return apiClient.invokeAPI(path, HttpMethod.POST, queryParams, postBody, headerParams, formParams, accept, contentType, authNames, returnType);
    }
    /**
     * update
     * 
     * <p><b>200</b> - OK
     * <p><b>201</b> - Created
     * <p><b>401</b> - Unauthorized
     * <p><b>403</b> - Forbidden
     * <p><b>404</b> - Not Found
     * @param data data (required)
     * @return List&lt;OI40DBTaskStockMaterialReservation&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public List<OI40DBTaskStockMaterialReservation> updateListOI40DBTaskStockMaterialReservation(List<OI40DBTaskStockMaterialReservation> data) throws RestClientException {
        return updateListOI40DBTaskStockMaterialReservationWithHttpInfo(data).getBody();
    }

    /**
     * update
     * 
     * <p><b>200</b> - OK
     * <p><b>201</b> - Created
     * <p><b>401</b> - Unauthorized
     * <p><b>403</b> - Forbidden
     * <p><b>404</b> - Not Found
     * @param data data (required)
     * @return ResponseEntity&lt;List&lt;OI40DBTaskStockMaterialReservation&gt;&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<List<OI40DBTaskStockMaterialReservation>> updateListOI40DBTaskStockMaterialReservationWithHttpInfo(List<OI40DBTaskStockMaterialReservation> data) throws RestClientException {
        Object postBody = data;
        
        // verify the required parameter 'data' is set
        if (data == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'data' when calling updateListOI40DBTaskStockMaterialReservation");
        }
        
        String path = UriComponentsBuilder.fromPath("/integration/OI40DBTaskStockMaterialReservation/update").build().toUriString();

        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, Object> formParams = new LinkedMultiValueMap<String, Object>();

        final String[] accepts = { 
            "application/json"
        };
        final List<MediaType> accept = apiClient.selectHeaderAccept(accepts);
        final String[] contentTypes = { 
            "application/json"
        };
        final MediaType contentType = apiClient.selectHeaderContentType(contentTypes);

        String[] authNames = new String[] {  };

        ParameterizedTypeReference<List<OI40DBTaskStockMaterialReservation>> returnType = new ParameterizedTypeReference<List<OI40DBTaskStockMaterialReservation>>() {};
        return apiClient.invokeAPI(path, HttpMethod.POST, queryParams, postBody, headerParams, formParams, accept, contentType, authNames, returnType);
    }
    /**
     * updateSingle
     * 
     * <p><b>200</b> - OK
     * <p><b>201</b> - Created
     * <p><b>401</b> - Unauthorized
     * <p><b>403</b> - Forbidden
     * <p><b>404</b> - Not Found
     * @param data data (required)
     * @return OI40DBTaskStockMaterialReservation
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public OI40DBTaskStockMaterialReservation updateSingleOI40DBTaskStockMaterialReservation(OI40DBTaskStockMaterialReservation data) throws RestClientException {
        return updateSingleOI40DBTaskStockMaterialReservationWithHttpInfo(data).getBody();
    }

    /**
     * updateSingle
     * 
     * <p><b>200</b> - OK
     * <p><b>201</b> - Created
     * <p><b>401</b> - Unauthorized
     * <p><b>403</b> - Forbidden
     * <p><b>404</b> - Not Found
     * @param data data (required)
     * @return ResponseEntity&lt;OI40DBTaskStockMaterialReservation&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<OI40DBTaskStockMaterialReservation> updateSingleOI40DBTaskStockMaterialReservationWithHttpInfo(OI40DBTaskStockMaterialReservation data) throws RestClientException {
        Object postBody = data;
        
        // verify the required parameter 'data' is set
        if (data == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'data' when calling updateSingleOI40DBTaskStockMaterialReservation");
        }
        
        String path = UriComponentsBuilder.fromPath("/integration/OI40DBTaskStockMaterialReservation/updateSingle").build().toUriString();

        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, Object> formParams = new LinkedMultiValueMap<String, Object>();

        final String[] accepts = { 
            "application/json"
        };
        final List<MediaType> accept = apiClient.selectHeaderAccept(accepts);
        final String[] contentTypes = { 
            "application/json"
        };
        final MediaType contentType = apiClient.selectHeaderContentType(contentTypes);

        String[] authNames = new String[] {  };

        ParameterizedTypeReference<OI40DBTaskStockMaterialReservation> returnType = new ParameterizedTypeReference<OI40DBTaskStockMaterialReservation>() {};
        return apiClient.invokeAPI(path, HttpMethod.POST, queryParams, postBody, headerParams, formParams, accept, contentType, authNames, returnType);
    }
}
