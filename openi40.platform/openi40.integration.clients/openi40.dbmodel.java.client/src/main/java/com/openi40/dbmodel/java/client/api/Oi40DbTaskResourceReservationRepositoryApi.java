package com.openi40.dbmodel.java.client.api;

import com.openi40.dbmodel.java.client.invoker.ApiClient;

import com.openi40.dbmodel.java.client.model.AutoCompleteData;
import com.openi40.dbmodel.java.client.model.LookupData;
import com.openi40.dbmodel.java.client.model.OI40DBTaskResourceReservation;
import com.openi40.dbmodel.java.client.model.PageInfo;
import com.openi40.dbmodel.java.client.model.PageOI40DBTaskResourceReservation;
import com.openi40.dbmodel.java.client.model.QbeSupportOI40DBTaskResourceReservation;

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

@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaClientCodegen", date = "2023-11-09T15:18:53.580+01:00")
@Component("com.openi40.dbmodel.java.client.api.Oi40DbTaskResourceReservationRepositoryApi")
public class Oi40DbTaskResourceReservationRepositoryApi {
    private ApiClient apiClient;

    public Oi40DbTaskResourceReservationRepositoryApi() {
        this(new ApiClient());
    }

    @Autowired
    public Oi40DbTaskResourceReservationRepositoryApi(ApiClient apiClient) {
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
    public void deleteByCodeVoid32(String code) throws RestClientException {
        deleteByCodeVoid32WithHttpInfo(code);
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
    public ResponseEntity<Void> deleteByCodeVoid32WithHttpInfo(String code) throws RestClientException {
        Object postBody = null;
        
        // verify the required parameter 'code' is set
        if (code == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'code' when calling deleteByCodeVoid32");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("code", code);
        String path = UriComponentsBuilder.fromPath("/integration/OI40DBTaskResourceReservation/deleteByCode/{code}").buildAndExpand(uriVariables).toUriString();

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
    public void deleteByCodesVoid32(List<String> codes) throws RestClientException {
        deleteByCodesVoid32WithHttpInfo(codes);
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
    public ResponseEntity<Void> deleteByCodesVoid32WithHttpInfo(List<String> codes) throws RestClientException {
        Object postBody = codes;
        
        // verify the required parameter 'codes' is set
        if (codes == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'codes' when calling deleteByCodesVoid32");
        }
        
        String path = UriComponentsBuilder.fromPath("/integration/OI40DBTaskResourceReservation/deleteByCodes").build().toUriString();

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
     * @return PageOI40DBTaskResourceReservation
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public PageOI40DBTaskResourceReservation doAutocompletePageOI40DBTaskResourceReservation(AutoCompleteData autoCompleteData) throws RestClientException {
        return doAutocompletePageOI40DBTaskResourceReservationWithHttpInfo(autoCompleteData).getBody();
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
     * @return ResponseEntity&lt;PageOI40DBTaskResourceReservation&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<PageOI40DBTaskResourceReservation> doAutocompletePageOI40DBTaskResourceReservationWithHttpInfo(AutoCompleteData autoCompleteData) throws RestClientException {
        Object postBody = autoCompleteData;
        
        // verify the required parameter 'autoCompleteData' is set
        if (autoCompleteData == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'autoCompleteData' when calling doAutocompletePageOI40DBTaskResourceReservation");
        }
        
        String path = UriComponentsBuilder.fromPath("/integration/OI40DBTaskResourceReservation/doAutocomplete").build().toUriString();

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

        ParameterizedTypeReference<PageOI40DBTaskResourceReservation> returnType = new ParameterizedTypeReference<PageOI40DBTaskResourceReservation>() {};
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
     * @return PageOI40DBTaskResourceReservation
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public PageOI40DBTaskResourceReservation doLookupPageOI40DBTaskResourceReservation(LookupData lookup) throws RestClientException {
        return doLookupPageOI40DBTaskResourceReservationWithHttpInfo(lookup).getBody();
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
     * @return ResponseEntity&lt;PageOI40DBTaskResourceReservation&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<PageOI40DBTaskResourceReservation> doLookupPageOI40DBTaskResourceReservationWithHttpInfo(LookupData lookup) throws RestClientException {
        Object postBody = lookup;
        
        // verify the required parameter 'lookup' is set
        if (lookup == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'lookup' when calling doLookupPageOI40DBTaskResourceReservation");
        }
        
        String path = UriComponentsBuilder.fromPath("/integration/OI40DBTaskResourceReservation/doLookup").build().toUriString();

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

        ParameterizedTypeReference<PageOI40DBTaskResourceReservation> returnType = new ParameterizedTypeReference<PageOI40DBTaskResourceReservation>() {};
        return apiClient.invokeAPI(path, HttpMethod.POST, queryParams, postBody, headerParams, formParams, accept, contentType, authNames, returnType);
    }
    /**
     * findAll
     * 
     * <p><b>200</b> - OK
     * <p><b>401</b> - Unauthorized
     * <p><b>403</b> - Forbidden
     * <p><b>404</b> - Not Found
     * @return List&lt;OI40DBTaskResourceReservation&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public List<OI40DBTaskResourceReservation> findAllListOI40DBTaskResourceReservation() throws RestClientException {
        return findAllListOI40DBTaskResourceReservationWithHttpInfo().getBody();
    }

    /**
     * findAll
     * 
     * <p><b>200</b> - OK
     * <p><b>401</b> - Unauthorized
     * <p><b>403</b> - Forbidden
     * <p><b>404</b> - Not Found
     * @return ResponseEntity&lt;List&lt;OI40DBTaskResourceReservation&gt;&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<List<OI40DBTaskResourceReservation>> findAllListOI40DBTaskResourceReservationWithHttpInfo() throws RestClientException {
        Object postBody = null;
        
        String path = UriComponentsBuilder.fromPath("/integration/OI40DBTaskResourceReservation").build().toUriString();

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

        ParameterizedTypeReference<List<OI40DBTaskResourceReservation>> returnType = new ParameterizedTypeReference<List<OI40DBTaskResourceReservation>>() {};
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
     * @return PageOI40DBTaskResourceReservation
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public PageOI40DBTaskResourceReservation findAllPageOI40DBTaskResourceReservation(PageInfo p) throws RestClientException {
        return findAllPageOI40DBTaskResourceReservationWithHttpInfo(p).getBody();
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
     * @return ResponseEntity&lt;PageOI40DBTaskResourceReservation&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<PageOI40DBTaskResourceReservation> findAllPageOI40DBTaskResourceReservationWithHttpInfo(PageInfo p) throws RestClientException {
        Object postBody = p;
        
        // verify the required parameter 'p' is set
        if (p == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'p' when calling findAllPageOI40DBTaskResourceReservation");
        }
        
        String path = UriComponentsBuilder.fromPath("/integration/OI40DBTaskResourceReservation/findAllPaged").build().toUriString();

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

        ParameterizedTypeReference<PageOI40DBTaskResourceReservation> returnType = new ParameterizedTypeReference<PageOI40DBTaskResourceReservation>() {};
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
     * @return List&lt;OI40DBTaskResourceReservation&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public List<OI40DBTaskResourceReservation> findByAfterIntegrationTsListOI40DBTaskResourceReservation(String ts) throws RestClientException {
        return findByAfterIntegrationTsListOI40DBTaskResourceReservationWithHttpInfo(ts).getBody();
    }

    /**
     * findByAfterIntegrationTs
     * 
     * <p><b>200</b> - OK
     * <p><b>401</b> - Unauthorized
     * <p><b>403</b> - Forbidden
     * <p><b>404</b> - Not Found
     * @param ts ts (required)
     * @return ResponseEntity&lt;List&lt;OI40DBTaskResourceReservation&gt;&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<List<OI40DBTaskResourceReservation>> findByAfterIntegrationTsListOI40DBTaskResourceReservationWithHttpInfo(String ts) throws RestClientException {
        Object postBody = null;
        
        // verify the required parameter 'ts' is set
        if (ts == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'ts' when calling findByAfterIntegrationTsListOI40DBTaskResourceReservation");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("ts", ts);
        String path = UriComponentsBuilder.fromPath("/integration/OI40DBTaskResourceReservation/integratedAfter/{ts}").buildAndExpand(uriVariables).toUriString();

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

        ParameterizedTypeReference<List<OI40DBTaskResourceReservation>> returnType = new ParameterizedTypeReference<List<OI40DBTaskResourceReservation>>() {};
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
     * @return List&lt;OI40DBTaskResourceReservation&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public List<OI40DBTaskResourceReservation> findByAfterModifiedTimestampListOI40DBTaskResourceReservation(String ts) throws RestClientException {
        return findByAfterModifiedTimestampListOI40DBTaskResourceReservationWithHttpInfo(ts).getBody();
    }

    /**
     * findByAfterModifiedTimestamp
     * 
     * <p><b>200</b> - OK
     * <p><b>401</b> - Unauthorized
     * <p><b>403</b> - Forbidden
     * <p><b>404</b> - Not Found
     * @param ts ts (required)
     * @return ResponseEntity&lt;List&lt;OI40DBTaskResourceReservation&gt;&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<List<OI40DBTaskResourceReservation>> findByAfterModifiedTimestampListOI40DBTaskResourceReservationWithHttpInfo(String ts) throws RestClientException {
        Object postBody = null;
        
        // verify the required parameter 'ts' is set
        if (ts == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'ts' when calling findByAfterModifiedTimestampListOI40DBTaskResourceReservation");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("ts", ts);
        String path = UriComponentsBuilder.fromPath("/integration/OI40DBTaskResourceReservation/modifiedAfter/{ts}").buildAndExpand(uriVariables).toUriString();

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

        ParameterizedTypeReference<List<OI40DBTaskResourceReservation>> returnType = new ParameterizedTypeReference<List<OI40DBTaskResourceReservation>>() {};
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
     * @return OI40DBTaskResourceReservation
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public OI40DBTaskResourceReservation findByCodeOI40DBTaskResourceReservation(String code) throws RestClientException {
        return findByCodeOI40DBTaskResourceReservationWithHttpInfo(code).getBody();
    }

    /**
     * findByCode
     * 
     * <p><b>200</b> - OK
     * <p><b>401</b> - Unauthorized
     * <p><b>403</b> - Forbidden
     * <p><b>404</b> - Not Found
     * @param code code (required)
     * @return ResponseEntity&lt;OI40DBTaskResourceReservation&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<OI40DBTaskResourceReservation> findByCodeOI40DBTaskResourceReservationWithHttpInfo(String code) throws RestClientException {
        Object postBody = null;
        
        // verify the required parameter 'code' is set
        if (code == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'code' when calling findByCodeOI40DBTaskResourceReservation");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("code", code);
        String path = UriComponentsBuilder.fromPath("/integration/OI40DBTaskResourceReservation/byCode/{code}").buildAndExpand(uriVariables).toUriString();

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

        ParameterizedTypeReference<OI40DBTaskResourceReservation> returnType = new ParameterizedTypeReference<OI40DBTaskResourceReservation>() {};
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
     * @return List&lt;OI40DBTaskResourceReservation&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public List<OI40DBTaskResourceReservation> findByCodesListOI40DBTaskResourceReservation(List<String> codes) throws RestClientException {
        return findByCodesListOI40DBTaskResourceReservationWithHttpInfo(codes).getBody();
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
     * @return ResponseEntity&lt;List&lt;OI40DBTaskResourceReservation&gt;&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<List<OI40DBTaskResourceReservation>> findByCodesListOI40DBTaskResourceReservationWithHttpInfo(List<String> codes) throws RestClientException {
        Object postBody = codes;
        
        // verify the required parameter 'codes' is set
        if (codes == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'codes' when calling findByCodesListOI40DBTaskResourceReservation");
        }
        
        String path = UriComponentsBuilder.fromPath("/integration/OI40DBTaskResourceReservation/findByCodes").build().toUriString();

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

        ParameterizedTypeReference<List<OI40DBTaskResourceReservation>> returnType = new ParameterizedTypeReference<List<OI40DBTaskResourceReservation>>() {};
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
     * @return List&lt;OI40DBTaskResourceReservation&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public List<OI40DBTaskResourceReservation> findByQbeListOI40DBTaskResourceReservation(OI40DBTaskResourceReservation qbe) throws RestClientException {
        return findByQbeListOI40DBTaskResourceReservationWithHttpInfo(qbe).getBody();
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
     * @return ResponseEntity&lt;List&lt;OI40DBTaskResourceReservation&gt;&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<List<OI40DBTaskResourceReservation>> findByQbeListOI40DBTaskResourceReservationWithHttpInfo(OI40DBTaskResourceReservation qbe) throws RestClientException {
        Object postBody = qbe;
        
        // verify the required parameter 'qbe' is set
        if (qbe == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'qbe' when calling findByQbeListOI40DBTaskResourceReservation");
        }
        
        String path = UriComponentsBuilder.fromPath("/integration/OI40DBTaskResourceReservation/findByQbe").build().toUriString();

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

        ParameterizedTypeReference<List<OI40DBTaskResourceReservation>> returnType = new ParameterizedTypeReference<List<OI40DBTaskResourceReservation>>() {};
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
     * @return PageOI40DBTaskResourceReservation
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public PageOI40DBTaskResourceReservation findByQbePagedPageOI40DBTaskResourceReservation(QbeSupportOI40DBTaskResourceReservation qbe) throws RestClientException {
        return findByQbePagedPageOI40DBTaskResourceReservationWithHttpInfo(qbe).getBody();
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
     * @return ResponseEntity&lt;PageOI40DBTaskResourceReservation&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<PageOI40DBTaskResourceReservation> findByQbePagedPageOI40DBTaskResourceReservationWithHttpInfo(QbeSupportOI40DBTaskResourceReservation qbe) throws RestClientException {
        Object postBody = qbe;
        
        // verify the required parameter 'qbe' is set
        if (qbe == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'qbe' when calling findByQbePagedPageOI40DBTaskResourceReservation");
        }
        
        String path = UriComponentsBuilder.fromPath("/integration/OI40DBTaskResourceReservation/findByQbePaged").build().toUriString();

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

        ParameterizedTypeReference<PageOI40DBTaskResourceReservation> returnType = new ParameterizedTypeReference<PageOI40DBTaskResourceReservation>() {};
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
     * @return List&lt;OI40DBTaskResourceReservation&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public List<OI40DBTaskResourceReservation> updateListOI40DBTaskResourceReservation(List<OI40DBTaskResourceReservation> data) throws RestClientException {
        return updateListOI40DBTaskResourceReservationWithHttpInfo(data).getBody();
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
     * @return ResponseEntity&lt;List&lt;OI40DBTaskResourceReservation&gt;&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<List<OI40DBTaskResourceReservation>> updateListOI40DBTaskResourceReservationWithHttpInfo(List<OI40DBTaskResourceReservation> data) throws RestClientException {
        Object postBody = data;
        
        // verify the required parameter 'data' is set
        if (data == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'data' when calling updateListOI40DBTaskResourceReservation");
        }
        
        String path = UriComponentsBuilder.fromPath("/integration/OI40DBTaskResourceReservation/update").build().toUriString();

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

        ParameterizedTypeReference<List<OI40DBTaskResourceReservation>> returnType = new ParameterizedTypeReference<List<OI40DBTaskResourceReservation>>() {};
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
     * @return OI40DBTaskResourceReservation
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public OI40DBTaskResourceReservation updateSingleOI40DBTaskResourceReservation(OI40DBTaskResourceReservation data) throws RestClientException {
        return updateSingleOI40DBTaskResourceReservationWithHttpInfo(data).getBody();
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
     * @return ResponseEntity&lt;OI40DBTaskResourceReservation&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<OI40DBTaskResourceReservation> updateSingleOI40DBTaskResourceReservationWithHttpInfo(OI40DBTaskResourceReservation data) throws RestClientException {
        Object postBody = data;
        
        // verify the required parameter 'data' is set
        if (data == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'data' when calling updateSingleOI40DBTaskResourceReservation");
        }
        
        String path = UriComponentsBuilder.fromPath("/integration/OI40DBTaskResourceReservation/updateSingle").build().toUriString();

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

        ParameterizedTypeReference<OI40DBTaskResourceReservation> returnType = new ParameterizedTypeReference<OI40DBTaskResourceReservation>() {};
        return apiClient.invokeAPI(path, HttpMethod.POST, queryParams, postBody, headerParams, formParams, accept, contentType, authNames, returnType);
    }
}
