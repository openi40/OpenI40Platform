package com.openi40.dbmodel.java.client.api;

import com.openi40.dbmodel.java.client.invoker.ApiClient;

import com.openi40.dbmodel.java.client.model.AutoCompleteData;
import com.openi40.dbmodel.java.client.model.LookupData;
import com.openi40.dbmodel.java.client.model.OI40DBTimesheetMetaInfoWorkingTimeRule;
import com.openi40.dbmodel.java.client.model.PageInfo;
import com.openi40.dbmodel.java.client.model.PageOI40DBTimesheetMetaInfoWorkingTimeRule;
import com.openi40.dbmodel.java.client.model.QbeSupportOI40DBTimesheetMetaInfoWorkingTimeRule;

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
@Component("com.openi40.dbmodel.java.client.api.Oi40DbTimesheetMetaInfoWorkingTimeRuleRepositoryApi")
public class Oi40DbTimesheetMetaInfoWorkingTimeRuleRepositoryApi {
    private ApiClient apiClient;

    public Oi40DbTimesheetMetaInfoWorkingTimeRuleRepositoryApi() {
        this(new ApiClient());
    }

    @Autowired
    public Oi40DbTimesheetMetaInfoWorkingTimeRuleRepositoryApi(ApiClient apiClient) {
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
    public void deleteByCodeVoid36(String code) throws RestClientException {
        deleteByCodeVoid36WithHttpInfo(code);
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
    public ResponseEntity<Void> deleteByCodeVoid36WithHttpInfo(String code) throws RestClientException {
        Object postBody = null;
        
        // verify the required parameter 'code' is set
        if (code == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'code' when calling deleteByCodeVoid36");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("code", code);
        String path = UriComponentsBuilder.fromPath("/integration/OI40DBTimesheetMetaInfoWorkingTimeRule/deleteByCode/{code}").buildAndExpand(uriVariables).toUriString();

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
    public void deleteByCodesVoid36(List<String> codes) throws RestClientException {
        deleteByCodesVoid36WithHttpInfo(codes);
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
    public ResponseEntity<Void> deleteByCodesVoid36WithHttpInfo(List<String> codes) throws RestClientException {
        Object postBody = codes;
        
        // verify the required parameter 'codes' is set
        if (codes == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'codes' when calling deleteByCodesVoid36");
        }
        
        String path = UriComponentsBuilder.fromPath("/integration/OI40DBTimesheetMetaInfoWorkingTimeRule/deleteByCodes").build().toUriString();

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
     * @return PageOI40DBTimesheetMetaInfoWorkingTimeRule
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public PageOI40DBTimesheetMetaInfoWorkingTimeRule doAutocompletePageOI40DBTimesheetMetaInfoWorkingTimeRule(AutoCompleteData autoCompleteData) throws RestClientException {
        return doAutocompletePageOI40DBTimesheetMetaInfoWorkingTimeRuleWithHttpInfo(autoCompleteData).getBody();
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
     * @return ResponseEntity&lt;PageOI40DBTimesheetMetaInfoWorkingTimeRule&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<PageOI40DBTimesheetMetaInfoWorkingTimeRule> doAutocompletePageOI40DBTimesheetMetaInfoWorkingTimeRuleWithHttpInfo(AutoCompleteData autoCompleteData) throws RestClientException {
        Object postBody = autoCompleteData;
        
        // verify the required parameter 'autoCompleteData' is set
        if (autoCompleteData == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'autoCompleteData' when calling doAutocompletePageOI40DBTimesheetMetaInfoWorkingTimeRule");
        }
        
        String path = UriComponentsBuilder.fromPath("/integration/OI40DBTimesheetMetaInfoWorkingTimeRule/doAutocomplete").build().toUriString();

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

        ParameterizedTypeReference<PageOI40DBTimesheetMetaInfoWorkingTimeRule> returnType = new ParameterizedTypeReference<PageOI40DBTimesheetMetaInfoWorkingTimeRule>() {};
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
     * @return PageOI40DBTimesheetMetaInfoWorkingTimeRule
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public PageOI40DBTimesheetMetaInfoWorkingTimeRule doLookupPageOI40DBTimesheetMetaInfoWorkingTimeRule(LookupData lookup) throws RestClientException {
        return doLookupPageOI40DBTimesheetMetaInfoWorkingTimeRuleWithHttpInfo(lookup).getBody();
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
     * @return ResponseEntity&lt;PageOI40DBTimesheetMetaInfoWorkingTimeRule&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<PageOI40DBTimesheetMetaInfoWorkingTimeRule> doLookupPageOI40DBTimesheetMetaInfoWorkingTimeRuleWithHttpInfo(LookupData lookup) throws RestClientException {
        Object postBody = lookup;
        
        // verify the required parameter 'lookup' is set
        if (lookup == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'lookup' when calling doLookupPageOI40DBTimesheetMetaInfoWorkingTimeRule");
        }
        
        String path = UriComponentsBuilder.fromPath("/integration/OI40DBTimesheetMetaInfoWorkingTimeRule/doLookup").build().toUriString();

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

        ParameterizedTypeReference<PageOI40DBTimesheetMetaInfoWorkingTimeRule> returnType = new ParameterizedTypeReference<PageOI40DBTimesheetMetaInfoWorkingTimeRule>() {};
        return apiClient.invokeAPI(path, HttpMethod.POST, queryParams, postBody, headerParams, formParams, accept, contentType, authNames, returnType);
    }
    /**
     * findAll
     * 
     * <p><b>200</b> - OK
     * <p><b>401</b> - Unauthorized
     * <p><b>403</b> - Forbidden
     * <p><b>404</b> - Not Found
     * @return List&lt;OI40DBTimesheetMetaInfoWorkingTimeRule&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public List<OI40DBTimesheetMetaInfoWorkingTimeRule> findAllListOI40DBTimesheetMetaInfoWorkingTimeRule() throws RestClientException {
        return findAllListOI40DBTimesheetMetaInfoWorkingTimeRuleWithHttpInfo().getBody();
    }

    /**
     * findAll
     * 
     * <p><b>200</b> - OK
     * <p><b>401</b> - Unauthorized
     * <p><b>403</b> - Forbidden
     * <p><b>404</b> - Not Found
     * @return ResponseEntity&lt;List&lt;OI40DBTimesheetMetaInfoWorkingTimeRule&gt;&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<List<OI40DBTimesheetMetaInfoWorkingTimeRule>> findAllListOI40DBTimesheetMetaInfoWorkingTimeRuleWithHttpInfo() throws RestClientException {
        Object postBody = null;
        
        String path = UriComponentsBuilder.fromPath("/integration/OI40DBTimesheetMetaInfoWorkingTimeRule").build().toUriString();

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

        ParameterizedTypeReference<List<OI40DBTimesheetMetaInfoWorkingTimeRule>> returnType = new ParameterizedTypeReference<List<OI40DBTimesheetMetaInfoWorkingTimeRule>>() {};
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
     * @return PageOI40DBTimesheetMetaInfoWorkingTimeRule
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public PageOI40DBTimesheetMetaInfoWorkingTimeRule findAllPageOI40DBTimesheetMetaInfoWorkingTimeRule(PageInfo p) throws RestClientException {
        return findAllPageOI40DBTimesheetMetaInfoWorkingTimeRuleWithHttpInfo(p).getBody();
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
     * @return ResponseEntity&lt;PageOI40DBTimesheetMetaInfoWorkingTimeRule&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<PageOI40DBTimesheetMetaInfoWorkingTimeRule> findAllPageOI40DBTimesheetMetaInfoWorkingTimeRuleWithHttpInfo(PageInfo p) throws RestClientException {
        Object postBody = p;
        
        // verify the required parameter 'p' is set
        if (p == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'p' when calling findAllPageOI40DBTimesheetMetaInfoWorkingTimeRule");
        }
        
        String path = UriComponentsBuilder.fromPath("/integration/OI40DBTimesheetMetaInfoWorkingTimeRule/findAllPaged").build().toUriString();

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

        ParameterizedTypeReference<PageOI40DBTimesheetMetaInfoWorkingTimeRule> returnType = new ParameterizedTypeReference<PageOI40DBTimesheetMetaInfoWorkingTimeRule>() {};
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
     * @return List&lt;OI40DBTimesheetMetaInfoWorkingTimeRule&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public List<OI40DBTimesheetMetaInfoWorkingTimeRule> findByAfterIntegrationTsListOI40DBTimesheetMetaInfoWorkingTimeRule(String ts) throws RestClientException {
        return findByAfterIntegrationTsListOI40DBTimesheetMetaInfoWorkingTimeRuleWithHttpInfo(ts).getBody();
    }

    /**
     * findByAfterIntegrationTs
     * 
     * <p><b>200</b> - OK
     * <p><b>401</b> - Unauthorized
     * <p><b>403</b> - Forbidden
     * <p><b>404</b> - Not Found
     * @param ts ts (required)
     * @return ResponseEntity&lt;List&lt;OI40DBTimesheetMetaInfoWorkingTimeRule&gt;&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<List<OI40DBTimesheetMetaInfoWorkingTimeRule>> findByAfterIntegrationTsListOI40DBTimesheetMetaInfoWorkingTimeRuleWithHttpInfo(String ts) throws RestClientException {
        Object postBody = null;
        
        // verify the required parameter 'ts' is set
        if (ts == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'ts' when calling findByAfterIntegrationTsListOI40DBTimesheetMetaInfoWorkingTimeRule");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("ts", ts);
        String path = UriComponentsBuilder.fromPath("/integration/OI40DBTimesheetMetaInfoWorkingTimeRule/integratedAfter/{ts}").buildAndExpand(uriVariables).toUriString();

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

        ParameterizedTypeReference<List<OI40DBTimesheetMetaInfoWorkingTimeRule>> returnType = new ParameterizedTypeReference<List<OI40DBTimesheetMetaInfoWorkingTimeRule>>() {};
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
     * @return List&lt;OI40DBTimesheetMetaInfoWorkingTimeRule&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public List<OI40DBTimesheetMetaInfoWorkingTimeRule> findByAfterModifiedTimestampListOI40DBTimesheetMetaInfoWorkingTimeRule(String ts) throws RestClientException {
        return findByAfterModifiedTimestampListOI40DBTimesheetMetaInfoWorkingTimeRuleWithHttpInfo(ts).getBody();
    }

    /**
     * findByAfterModifiedTimestamp
     * 
     * <p><b>200</b> - OK
     * <p><b>401</b> - Unauthorized
     * <p><b>403</b> - Forbidden
     * <p><b>404</b> - Not Found
     * @param ts ts (required)
     * @return ResponseEntity&lt;List&lt;OI40DBTimesheetMetaInfoWorkingTimeRule&gt;&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<List<OI40DBTimesheetMetaInfoWorkingTimeRule>> findByAfterModifiedTimestampListOI40DBTimesheetMetaInfoWorkingTimeRuleWithHttpInfo(String ts) throws RestClientException {
        Object postBody = null;
        
        // verify the required parameter 'ts' is set
        if (ts == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'ts' when calling findByAfterModifiedTimestampListOI40DBTimesheetMetaInfoWorkingTimeRule");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("ts", ts);
        String path = UriComponentsBuilder.fromPath("/integration/OI40DBTimesheetMetaInfoWorkingTimeRule/modifiedAfter/{ts}").buildAndExpand(uriVariables).toUriString();

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

        ParameterizedTypeReference<List<OI40DBTimesheetMetaInfoWorkingTimeRule>> returnType = new ParameterizedTypeReference<List<OI40DBTimesheetMetaInfoWorkingTimeRule>>() {};
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
     * @return OI40DBTimesheetMetaInfoWorkingTimeRule
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public OI40DBTimesheetMetaInfoWorkingTimeRule findByCodeOI40DBTimesheetMetaInfoWorkingTimeRule(String code) throws RestClientException {
        return findByCodeOI40DBTimesheetMetaInfoWorkingTimeRuleWithHttpInfo(code).getBody();
    }

    /**
     * findByCode
     * 
     * <p><b>200</b> - OK
     * <p><b>401</b> - Unauthorized
     * <p><b>403</b> - Forbidden
     * <p><b>404</b> - Not Found
     * @param code code (required)
     * @return ResponseEntity&lt;OI40DBTimesheetMetaInfoWorkingTimeRule&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<OI40DBTimesheetMetaInfoWorkingTimeRule> findByCodeOI40DBTimesheetMetaInfoWorkingTimeRuleWithHttpInfo(String code) throws RestClientException {
        Object postBody = null;
        
        // verify the required parameter 'code' is set
        if (code == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'code' when calling findByCodeOI40DBTimesheetMetaInfoWorkingTimeRule");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("code", code);
        String path = UriComponentsBuilder.fromPath("/integration/OI40DBTimesheetMetaInfoWorkingTimeRule/byCode/{code}").buildAndExpand(uriVariables).toUriString();

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

        ParameterizedTypeReference<OI40DBTimesheetMetaInfoWorkingTimeRule> returnType = new ParameterizedTypeReference<OI40DBTimesheetMetaInfoWorkingTimeRule>() {};
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
     * @return List&lt;OI40DBTimesheetMetaInfoWorkingTimeRule&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public List<OI40DBTimesheetMetaInfoWorkingTimeRule> findByCodesListOI40DBTimesheetMetaInfoWorkingTimeRule(List<String> codes) throws RestClientException {
        return findByCodesListOI40DBTimesheetMetaInfoWorkingTimeRuleWithHttpInfo(codes).getBody();
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
     * @return ResponseEntity&lt;List&lt;OI40DBTimesheetMetaInfoWorkingTimeRule&gt;&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<List<OI40DBTimesheetMetaInfoWorkingTimeRule>> findByCodesListOI40DBTimesheetMetaInfoWorkingTimeRuleWithHttpInfo(List<String> codes) throws RestClientException {
        Object postBody = codes;
        
        // verify the required parameter 'codes' is set
        if (codes == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'codes' when calling findByCodesListOI40DBTimesheetMetaInfoWorkingTimeRule");
        }
        
        String path = UriComponentsBuilder.fromPath("/integration/OI40DBTimesheetMetaInfoWorkingTimeRule/findByCodes").build().toUriString();

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

        ParameterizedTypeReference<List<OI40DBTimesheetMetaInfoWorkingTimeRule>> returnType = new ParameterizedTypeReference<List<OI40DBTimesheetMetaInfoWorkingTimeRule>>() {};
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
     * @return List&lt;OI40DBTimesheetMetaInfoWorkingTimeRule&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public List<OI40DBTimesheetMetaInfoWorkingTimeRule> findByQbeListOI40DBTimesheetMetaInfoWorkingTimeRule(OI40DBTimesheetMetaInfoWorkingTimeRule qbe) throws RestClientException {
        return findByQbeListOI40DBTimesheetMetaInfoWorkingTimeRuleWithHttpInfo(qbe).getBody();
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
     * @return ResponseEntity&lt;List&lt;OI40DBTimesheetMetaInfoWorkingTimeRule&gt;&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<List<OI40DBTimesheetMetaInfoWorkingTimeRule>> findByQbeListOI40DBTimesheetMetaInfoWorkingTimeRuleWithHttpInfo(OI40DBTimesheetMetaInfoWorkingTimeRule qbe) throws RestClientException {
        Object postBody = qbe;
        
        // verify the required parameter 'qbe' is set
        if (qbe == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'qbe' when calling findByQbeListOI40DBTimesheetMetaInfoWorkingTimeRule");
        }
        
        String path = UriComponentsBuilder.fromPath("/integration/OI40DBTimesheetMetaInfoWorkingTimeRule/findByQbe").build().toUriString();

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

        ParameterizedTypeReference<List<OI40DBTimesheetMetaInfoWorkingTimeRule>> returnType = new ParameterizedTypeReference<List<OI40DBTimesheetMetaInfoWorkingTimeRule>>() {};
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
     * @return PageOI40DBTimesheetMetaInfoWorkingTimeRule
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public PageOI40DBTimesheetMetaInfoWorkingTimeRule findByQbePagedPageOI40DBTimesheetMetaInfoWorkingTimeRule(QbeSupportOI40DBTimesheetMetaInfoWorkingTimeRule qbe) throws RestClientException {
        return findByQbePagedPageOI40DBTimesheetMetaInfoWorkingTimeRuleWithHttpInfo(qbe).getBody();
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
     * @return ResponseEntity&lt;PageOI40DBTimesheetMetaInfoWorkingTimeRule&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<PageOI40DBTimesheetMetaInfoWorkingTimeRule> findByQbePagedPageOI40DBTimesheetMetaInfoWorkingTimeRuleWithHttpInfo(QbeSupportOI40DBTimesheetMetaInfoWorkingTimeRule qbe) throws RestClientException {
        Object postBody = qbe;
        
        // verify the required parameter 'qbe' is set
        if (qbe == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'qbe' when calling findByQbePagedPageOI40DBTimesheetMetaInfoWorkingTimeRule");
        }
        
        String path = UriComponentsBuilder.fromPath("/integration/OI40DBTimesheetMetaInfoWorkingTimeRule/findByQbePaged").build().toUriString();

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

        ParameterizedTypeReference<PageOI40DBTimesheetMetaInfoWorkingTimeRule> returnType = new ParameterizedTypeReference<PageOI40DBTimesheetMetaInfoWorkingTimeRule>() {};
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
     * @return List&lt;OI40DBTimesheetMetaInfoWorkingTimeRule&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public List<OI40DBTimesheetMetaInfoWorkingTimeRule> updateListOI40DBTimesheetMetaInfoWorkingTimeRule(List<OI40DBTimesheetMetaInfoWorkingTimeRule> data) throws RestClientException {
        return updateListOI40DBTimesheetMetaInfoWorkingTimeRuleWithHttpInfo(data).getBody();
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
     * @return ResponseEntity&lt;List&lt;OI40DBTimesheetMetaInfoWorkingTimeRule&gt;&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<List<OI40DBTimesheetMetaInfoWorkingTimeRule>> updateListOI40DBTimesheetMetaInfoWorkingTimeRuleWithHttpInfo(List<OI40DBTimesheetMetaInfoWorkingTimeRule> data) throws RestClientException {
        Object postBody = data;
        
        // verify the required parameter 'data' is set
        if (data == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'data' when calling updateListOI40DBTimesheetMetaInfoWorkingTimeRule");
        }
        
        String path = UriComponentsBuilder.fromPath("/integration/OI40DBTimesheetMetaInfoWorkingTimeRule/update").build().toUriString();

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

        ParameterizedTypeReference<List<OI40DBTimesheetMetaInfoWorkingTimeRule>> returnType = new ParameterizedTypeReference<List<OI40DBTimesheetMetaInfoWorkingTimeRule>>() {};
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
     * @return OI40DBTimesheetMetaInfoWorkingTimeRule
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public OI40DBTimesheetMetaInfoWorkingTimeRule updateSingleOI40DBTimesheetMetaInfoWorkingTimeRule(OI40DBTimesheetMetaInfoWorkingTimeRule data) throws RestClientException {
        return updateSingleOI40DBTimesheetMetaInfoWorkingTimeRuleWithHttpInfo(data).getBody();
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
     * @return ResponseEntity&lt;OI40DBTimesheetMetaInfoWorkingTimeRule&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<OI40DBTimesheetMetaInfoWorkingTimeRule> updateSingleOI40DBTimesheetMetaInfoWorkingTimeRuleWithHttpInfo(OI40DBTimesheetMetaInfoWorkingTimeRule data) throws RestClientException {
        Object postBody = data;
        
        // verify the required parameter 'data' is set
        if (data == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'data' when calling updateSingleOI40DBTimesheetMetaInfoWorkingTimeRule");
        }
        
        String path = UriComponentsBuilder.fromPath("/integration/OI40DBTimesheetMetaInfoWorkingTimeRule/updateSingle").build().toUriString();

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

        ParameterizedTypeReference<OI40DBTimesheetMetaInfoWorkingTimeRule> returnType = new ParameterizedTypeReference<OI40DBTimesheetMetaInfoWorkingTimeRule>() {};
        return apiClient.invokeAPI(path, HttpMethod.POST, queryParams, postBody, headerParams, formParams, accept, contentType, authNames, returnType);
    }
}
