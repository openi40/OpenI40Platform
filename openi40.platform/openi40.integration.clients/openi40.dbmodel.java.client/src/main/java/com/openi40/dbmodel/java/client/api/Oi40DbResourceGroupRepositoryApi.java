package com.openi40.dbmodel.java.client.api;

import com.openi40.dbmodel.java.client.invoker.ApiClient;

import com.openi40.dbmodel.java.client.model.AutoCompleteData;
import com.openi40.dbmodel.java.client.model.LookupData;
import com.openi40.dbmodel.java.client.model.OI40DBResourceGroup;
import com.openi40.dbmodel.java.client.model.PageInfo;
import com.openi40.dbmodel.java.client.model.PageOI40DBResourceGroup;
import com.openi40.dbmodel.java.client.model.QbeSupportOI40DBResourceGroup;

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
@Component("com.openi40.dbmodel.java.client.api.Oi40DbResourceGroupRepositoryApi")
public class Oi40DbResourceGroupRepositoryApi {
    private ApiClient apiClient;

    public Oi40DbResourceGroupRepositoryApi() {
        this(new ApiClient());
    }

    @Autowired
    public Oi40DbResourceGroupRepositoryApi(ApiClient apiClient) {
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
    public void deleteByCodeVoid21(String code) throws RestClientException {
        deleteByCodeVoid21WithHttpInfo(code);
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
    public ResponseEntity<Void> deleteByCodeVoid21WithHttpInfo(String code) throws RestClientException {
        Object postBody = null;
        
        // verify the required parameter 'code' is set
        if (code == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'code' when calling deleteByCodeVoid21");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("code", code);
        String path = UriComponentsBuilder.fromPath("/integration/OI40DBResourceGroup/deleteByCode/{code}").buildAndExpand(uriVariables).toUriString();

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
    public void deleteByCodesVoid21(List<String> codes) throws RestClientException {
        deleteByCodesVoid21WithHttpInfo(codes);
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
    public ResponseEntity<Void> deleteByCodesVoid21WithHttpInfo(List<String> codes) throws RestClientException {
        Object postBody = codes;
        
        // verify the required parameter 'codes' is set
        if (codes == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'codes' when calling deleteByCodesVoid21");
        }
        
        String path = UriComponentsBuilder.fromPath("/integration/OI40DBResourceGroup/deleteByCodes").build().toUriString();

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
     * @return PageOI40DBResourceGroup
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public PageOI40DBResourceGroup doAutocompletePageOI40DBResourceGroup(AutoCompleteData autoCompleteData) throws RestClientException {
        return doAutocompletePageOI40DBResourceGroupWithHttpInfo(autoCompleteData).getBody();
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
     * @return ResponseEntity&lt;PageOI40DBResourceGroup&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<PageOI40DBResourceGroup> doAutocompletePageOI40DBResourceGroupWithHttpInfo(AutoCompleteData autoCompleteData) throws RestClientException {
        Object postBody = autoCompleteData;
        
        // verify the required parameter 'autoCompleteData' is set
        if (autoCompleteData == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'autoCompleteData' when calling doAutocompletePageOI40DBResourceGroup");
        }
        
        String path = UriComponentsBuilder.fromPath("/integration/OI40DBResourceGroup/doAutocomplete").build().toUriString();

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

        ParameterizedTypeReference<PageOI40DBResourceGroup> returnType = new ParameterizedTypeReference<PageOI40DBResourceGroup>() {};
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
     * @return PageOI40DBResourceGroup
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public PageOI40DBResourceGroup doLookupPageOI40DBResourceGroup(LookupData lookup) throws RestClientException {
        return doLookupPageOI40DBResourceGroupWithHttpInfo(lookup).getBody();
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
     * @return ResponseEntity&lt;PageOI40DBResourceGroup&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<PageOI40DBResourceGroup> doLookupPageOI40DBResourceGroupWithHttpInfo(LookupData lookup) throws RestClientException {
        Object postBody = lookup;
        
        // verify the required parameter 'lookup' is set
        if (lookup == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'lookup' when calling doLookupPageOI40DBResourceGroup");
        }
        
        String path = UriComponentsBuilder.fromPath("/integration/OI40DBResourceGroup/doLookup").build().toUriString();

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

        ParameterizedTypeReference<PageOI40DBResourceGroup> returnType = new ParameterizedTypeReference<PageOI40DBResourceGroup>() {};
        return apiClient.invokeAPI(path, HttpMethod.POST, queryParams, postBody, headerParams, formParams, accept, contentType, authNames, returnType);
    }
    /**
     * findAll
     * 
     * <p><b>200</b> - OK
     * <p><b>401</b> - Unauthorized
     * <p><b>403</b> - Forbidden
     * <p><b>404</b> - Not Found
     * @return List&lt;OI40DBResourceGroup&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public List<OI40DBResourceGroup> findAllListOI40DBResourceGroup() throws RestClientException {
        return findAllListOI40DBResourceGroupWithHttpInfo().getBody();
    }

    /**
     * findAll
     * 
     * <p><b>200</b> - OK
     * <p><b>401</b> - Unauthorized
     * <p><b>403</b> - Forbidden
     * <p><b>404</b> - Not Found
     * @return ResponseEntity&lt;List&lt;OI40DBResourceGroup&gt;&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<List<OI40DBResourceGroup>> findAllListOI40DBResourceGroupWithHttpInfo() throws RestClientException {
        Object postBody = null;
        
        String path = UriComponentsBuilder.fromPath("/integration/OI40DBResourceGroup").build().toUriString();

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

        ParameterizedTypeReference<List<OI40DBResourceGroup>> returnType = new ParameterizedTypeReference<List<OI40DBResourceGroup>>() {};
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
     * @return PageOI40DBResourceGroup
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public PageOI40DBResourceGroup findAllPageOI40DBResourceGroup(PageInfo p) throws RestClientException {
        return findAllPageOI40DBResourceGroupWithHttpInfo(p).getBody();
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
     * @return ResponseEntity&lt;PageOI40DBResourceGroup&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<PageOI40DBResourceGroup> findAllPageOI40DBResourceGroupWithHttpInfo(PageInfo p) throws RestClientException {
        Object postBody = p;
        
        // verify the required parameter 'p' is set
        if (p == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'p' when calling findAllPageOI40DBResourceGroup");
        }
        
        String path = UriComponentsBuilder.fromPath("/integration/OI40DBResourceGroup/findAllPaged").build().toUriString();

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

        ParameterizedTypeReference<PageOI40DBResourceGroup> returnType = new ParameterizedTypeReference<PageOI40DBResourceGroup>() {};
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
     * @return List&lt;OI40DBResourceGroup&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public List<OI40DBResourceGroup> findByAfterIntegrationTsListOI40DBResourceGroup(String ts) throws RestClientException {
        return findByAfterIntegrationTsListOI40DBResourceGroupWithHttpInfo(ts).getBody();
    }

    /**
     * findByAfterIntegrationTs
     * 
     * <p><b>200</b> - OK
     * <p><b>401</b> - Unauthorized
     * <p><b>403</b> - Forbidden
     * <p><b>404</b> - Not Found
     * @param ts ts (required)
     * @return ResponseEntity&lt;List&lt;OI40DBResourceGroup&gt;&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<List<OI40DBResourceGroup>> findByAfterIntegrationTsListOI40DBResourceGroupWithHttpInfo(String ts) throws RestClientException {
        Object postBody = null;
        
        // verify the required parameter 'ts' is set
        if (ts == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'ts' when calling findByAfterIntegrationTsListOI40DBResourceGroup");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("ts", ts);
        String path = UriComponentsBuilder.fromPath("/integration/OI40DBResourceGroup/integratedAfter/{ts}").buildAndExpand(uriVariables).toUriString();

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

        ParameterizedTypeReference<List<OI40DBResourceGroup>> returnType = new ParameterizedTypeReference<List<OI40DBResourceGroup>>() {};
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
     * @return List&lt;OI40DBResourceGroup&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public List<OI40DBResourceGroup> findByAfterModifiedTimestampListOI40DBResourceGroup(String ts) throws RestClientException {
        return findByAfterModifiedTimestampListOI40DBResourceGroupWithHttpInfo(ts).getBody();
    }

    /**
     * findByAfterModifiedTimestamp
     * 
     * <p><b>200</b> - OK
     * <p><b>401</b> - Unauthorized
     * <p><b>403</b> - Forbidden
     * <p><b>404</b> - Not Found
     * @param ts ts (required)
     * @return ResponseEntity&lt;List&lt;OI40DBResourceGroup&gt;&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<List<OI40DBResourceGroup>> findByAfterModifiedTimestampListOI40DBResourceGroupWithHttpInfo(String ts) throws RestClientException {
        Object postBody = null;
        
        // verify the required parameter 'ts' is set
        if (ts == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'ts' when calling findByAfterModifiedTimestampListOI40DBResourceGroup");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("ts", ts);
        String path = UriComponentsBuilder.fromPath("/integration/OI40DBResourceGroup/modifiedAfter/{ts}").buildAndExpand(uriVariables).toUriString();

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

        ParameterizedTypeReference<List<OI40DBResourceGroup>> returnType = new ParameterizedTypeReference<List<OI40DBResourceGroup>>() {};
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
     * @return OI40DBResourceGroup
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public OI40DBResourceGroup findByCodeOI40DBResourceGroup(String code) throws RestClientException {
        return findByCodeOI40DBResourceGroupWithHttpInfo(code).getBody();
    }

    /**
     * findByCode
     * 
     * <p><b>200</b> - OK
     * <p><b>401</b> - Unauthorized
     * <p><b>403</b> - Forbidden
     * <p><b>404</b> - Not Found
     * @param code code (required)
     * @return ResponseEntity&lt;OI40DBResourceGroup&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<OI40DBResourceGroup> findByCodeOI40DBResourceGroupWithHttpInfo(String code) throws RestClientException {
        Object postBody = null;
        
        // verify the required parameter 'code' is set
        if (code == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'code' when calling findByCodeOI40DBResourceGroup");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("code", code);
        String path = UriComponentsBuilder.fromPath("/integration/OI40DBResourceGroup/byCode/{code}").buildAndExpand(uriVariables).toUriString();

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

        ParameterizedTypeReference<OI40DBResourceGroup> returnType = new ParameterizedTypeReference<OI40DBResourceGroup>() {};
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
     * @return List&lt;OI40DBResourceGroup&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public List<OI40DBResourceGroup> findByCodesListOI40DBResourceGroup(List<String> codes) throws RestClientException {
        return findByCodesListOI40DBResourceGroupWithHttpInfo(codes).getBody();
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
     * @return ResponseEntity&lt;List&lt;OI40DBResourceGroup&gt;&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<List<OI40DBResourceGroup>> findByCodesListOI40DBResourceGroupWithHttpInfo(List<String> codes) throws RestClientException {
        Object postBody = codes;
        
        // verify the required parameter 'codes' is set
        if (codes == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'codes' when calling findByCodesListOI40DBResourceGroup");
        }
        
        String path = UriComponentsBuilder.fromPath("/integration/OI40DBResourceGroup/findByCodes").build().toUriString();

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

        ParameterizedTypeReference<List<OI40DBResourceGroup>> returnType = new ParameterizedTypeReference<List<OI40DBResourceGroup>>() {};
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
     * @return List&lt;OI40DBResourceGroup&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public List<OI40DBResourceGroup> findByQbeListOI40DBResourceGroup(OI40DBResourceGroup qbe) throws RestClientException {
        return findByQbeListOI40DBResourceGroupWithHttpInfo(qbe).getBody();
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
     * @return ResponseEntity&lt;List&lt;OI40DBResourceGroup&gt;&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<List<OI40DBResourceGroup>> findByQbeListOI40DBResourceGroupWithHttpInfo(OI40DBResourceGroup qbe) throws RestClientException {
        Object postBody = qbe;
        
        // verify the required parameter 'qbe' is set
        if (qbe == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'qbe' when calling findByQbeListOI40DBResourceGroup");
        }
        
        String path = UriComponentsBuilder.fromPath("/integration/OI40DBResourceGroup/findByQbe").build().toUriString();

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

        ParameterizedTypeReference<List<OI40DBResourceGroup>> returnType = new ParameterizedTypeReference<List<OI40DBResourceGroup>>() {};
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
     * @return PageOI40DBResourceGroup
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public PageOI40DBResourceGroup findByQbePagedPageOI40DBResourceGroup(QbeSupportOI40DBResourceGroup qbe) throws RestClientException {
        return findByQbePagedPageOI40DBResourceGroupWithHttpInfo(qbe).getBody();
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
     * @return ResponseEntity&lt;PageOI40DBResourceGroup&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<PageOI40DBResourceGroup> findByQbePagedPageOI40DBResourceGroupWithHttpInfo(QbeSupportOI40DBResourceGroup qbe) throws RestClientException {
        Object postBody = qbe;
        
        // verify the required parameter 'qbe' is set
        if (qbe == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'qbe' when calling findByQbePagedPageOI40DBResourceGroup");
        }
        
        String path = UriComponentsBuilder.fromPath("/integration/OI40DBResourceGroup/findByQbePaged").build().toUriString();

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

        ParameterizedTypeReference<PageOI40DBResourceGroup> returnType = new ParameterizedTypeReference<PageOI40DBResourceGroup>() {};
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
     * @return List&lt;OI40DBResourceGroup&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public List<OI40DBResourceGroup> updateListOI40DBResourceGroup(List<OI40DBResourceGroup> data) throws RestClientException {
        return updateListOI40DBResourceGroupWithHttpInfo(data).getBody();
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
     * @return ResponseEntity&lt;List&lt;OI40DBResourceGroup&gt;&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<List<OI40DBResourceGroup>> updateListOI40DBResourceGroupWithHttpInfo(List<OI40DBResourceGroup> data) throws RestClientException {
        Object postBody = data;
        
        // verify the required parameter 'data' is set
        if (data == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'data' when calling updateListOI40DBResourceGroup");
        }
        
        String path = UriComponentsBuilder.fromPath("/integration/OI40DBResourceGroup/update").build().toUriString();

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

        ParameterizedTypeReference<List<OI40DBResourceGroup>> returnType = new ParameterizedTypeReference<List<OI40DBResourceGroup>>() {};
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
     * @return OI40DBResourceGroup
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public OI40DBResourceGroup updateSingleOI40DBResourceGroup(OI40DBResourceGroup data) throws RestClientException {
        return updateSingleOI40DBResourceGroupWithHttpInfo(data).getBody();
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
     * @return ResponseEntity&lt;OI40DBResourceGroup&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<OI40DBResourceGroup> updateSingleOI40DBResourceGroupWithHttpInfo(OI40DBResourceGroup data) throws RestClientException {
        Object postBody = data;
        
        // verify the required parameter 'data' is set
        if (data == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'data' when calling updateSingleOI40DBResourceGroup");
        }
        
        String path = UriComponentsBuilder.fromPath("/integration/OI40DBResourceGroup/updateSingle").build().toUriString();

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

        ParameterizedTypeReference<OI40DBResourceGroup> returnType = new ParameterizedTypeReference<OI40DBResourceGroup>() {};
        return apiClient.invokeAPI(path, HttpMethod.POST, queryParams, postBody, headerParams, formParams, accept, contentType, authNames, returnType);
    }
}
