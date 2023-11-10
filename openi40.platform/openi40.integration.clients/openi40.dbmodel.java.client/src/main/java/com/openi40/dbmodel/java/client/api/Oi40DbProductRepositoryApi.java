package com.openi40.dbmodel.java.client.api;

import com.openi40.dbmodel.java.client.invoker.ApiClient;

import com.openi40.dbmodel.java.client.model.AutoCompleteData;
import com.openi40.dbmodel.java.client.model.LookupData;
import com.openi40.dbmodel.java.client.model.OI40DBProduct;
import com.openi40.dbmodel.java.client.model.PageInfo;
import com.openi40.dbmodel.java.client.model.PageOI40DBProduct;
import com.openi40.dbmodel.java.client.model.QbeSupportOI40DBProduct;

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
@Component("com.openi40.dbmodel.java.client.api.Oi40DbProductRepositoryApi")
public class Oi40DbProductRepositoryApi {
    private ApiClient apiClient;

    public Oi40DbProductRepositoryApi() {
        this(new ApiClient());
    }

    @Autowired
    public Oi40DbProductRepositoryApi(ApiClient apiClient) {
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
    public void deleteByCodeVoid16(String code) throws RestClientException {
        deleteByCodeVoid16WithHttpInfo(code);
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
    public ResponseEntity<Void> deleteByCodeVoid16WithHttpInfo(String code) throws RestClientException {
        Object postBody = null;
        
        // verify the required parameter 'code' is set
        if (code == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'code' when calling deleteByCodeVoid16");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("code", code);
        String path = UriComponentsBuilder.fromPath("/integration/OI40DBProduct/deleteByCode/{code}").buildAndExpand(uriVariables).toUriString();

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
    public void deleteByCodesVoid16(List<String> codes) throws RestClientException {
        deleteByCodesVoid16WithHttpInfo(codes);
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
    public ResponseEntity<Void> deleteByCodesVoid16WithHttpInfo(List<String> codes) throws RestClientException {
        Object postBody = codes;
        
        // verify the required parameter 'codes' is set
        if (codes == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'codes' when calling deleteByCodesVoid16");
        }
        
        String path = UriComponentsBuilder.fromPath("/integration/OI40DBProduct/deleteByCodes").build().toUriString();

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
     * @return PageOI40DBProduct
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public PageOI40DBProduct doAutocompletePageOI40DBProduct(AutoCompleteData autoCompleteData) throws RestClientException {
        return doAutocompletePageOI40DBProductWithHttpInfo(autoCompleteData).getBody();
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
     * @return ResponseEntity&lt;PageOI40DBProduct&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<PageOI40DBProduct> doAutocompletePageOI40DBProductWithHttpInfo(AutoCompleteData autoCompleteData) throws RestClientException {
        Object postBody = autoCompleteData;
        
        // verify the required parameter 'autoCompleteData' is set
        if (autoCompleteData == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'autoCompleteData' when calling doAutocompletePageOI40DBProduct");
        }
        
        String path = UriComponentsBuilder.fromPath("/integration/OI40DBProduct/doAutocomplete").build().toUriString();

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

        ParameterizedTypeReference<PageOI40DBProduct> returnType = new ParameterizedTypeReference<PageOI40DBProduct>() {};
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
     * @return PageOI40DBProduct
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public PageOI40DBProduct doLookupPageOI40DBProduct(LookupData lookup) throws RestClientException {
        return doLookupPageOI40DBProductWithHttpInfo(lookup).getBody();
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
     * @return ResponseEntity&lt;PageOI40DBProduct&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<PageOI40DBProduct> doLookupPageOI40DBProductWithHttpInfo(LookupData lookup) throws RestClientException {
        Object postBody = lookup;
        
        // verify the required parameter 'lookup' is set
        if (lookup == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'lookup' when calling doLookupPageOI40DBProduct");
        }
        
        String path = UriComponentsBuilder.fromPath("/integration/OI40DBProduct/doLookup").build().toUriString();

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

        ParameterizedTypeReference<PageOI40DBProduct> returnType = new ParameterizedTypeReference<PageOI40DBProduct>() {};
        return apiClient.invokeAPI(path, HttpMethod.POST, queryParams, postBody, headerParams, formParams, accept, contentType, authNames, returnType);
    }
    /**
     * findAll
     * 
     * <p><b>200</b> - OK
     * <p><b>401</b> - Unauthorized
     * <p><b>403</b> - Forbidden
     * <p><b>404</b> - Not Found
     * @return List&lt;OI40DBProduct&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public List<OI40DBProduct> findAllListOI40DBProduct() throws RestClientException {
        return findAllListOI40DBProductWithHttpInfo().getBody();
    }

    /**
     * findAll
     * 
     * <p><b>200</b> - OK
     * <p><b>401</b> - Unauthorized
     * <p><b>403</b> - Forbidden
     * <p><b>404</b> - Not Found
     * @return ResponseEntity&lt;List&lt;OI40DBProduct&gt;&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<List<OI40DBProduct>> findAllListOI40DBProductWithHttpInfo() throws RestClientException {
        Object postBody = null;
        
        String path = UriComponentsBuilder.fromPath("/integration/OI40DBProduct").build().toUriString();

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

        ParameterizedTypeReference<List<OI40DBProduct>> returnType = new ParameterizedTypeReference<List<OI40DBProduct>>() {};
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
     * @return PageOI40DBProduct
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public PageOI40DBProduct findAllPageOI40DBProduct(PageInfo p) throws RestClientException {
        return findAllPageOI40DBProductWithHttpInfo(p).getBody();
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
     * @return ResponseEntity&lt;PageOI40DBProduct&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<PageOI40DBProduct> findAllPageOI40DBProductWithHttpInfo(PageInfo p) throws RestClientException {
        Object postBody = p;
        
        // verify the required parameter 'p' is set
        if (p == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'p' when calling findAllPageOI40DBProduct");
        }
        
        String path = UriComponentsBuilder.fromPath("/integration/OI40DBProduct/findAllPaged").build().toUriString();

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

        ParameterizedTypeReference<PageOI40DBProduct> returnType = new ParameterizedTypeReference<PageOI40DBProduct>() {};
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
     * @return List&lt;OI40DBProduct&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public List<OI40DBProduct> findByAfterIntegrationTsListOI40DBProduct(String ts) throws RestClientException {
        return findByAfterIntegrationTsListOI40DBProductWithHttpInfo(ts).getBody();
    }

    /**
     * findByAfterIntegrationTs
     * 
     * <p><b>200</b> - OK
     * <p><b>401</b> - Unauthorized
     * <p><b>403</b> - Forbidden
     * <p><b>404</b> - Not Found
     * @param ts ts (required)
     * @return ResponseEntity&lt;List&lt;OI40DBProduct&gt;&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<List<OI40DBProduct>> findByAfterIntegrationTsListOI40DBProductWithHttpInfo(String ts) throws RestClientException {
        Object postBody = null;
        
        // verify the required parameter 'ts' is set
        if (ts == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'ts' when calling findByAfterIntegrationTsListOI40DBProduct");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("ts", ts);
        String path = UriComponentsBuilder.fromPath("/integration/OI40DBProduct/integratedAfter/{ts}").buildAndExpand(uriVariables).toUriString();

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

        ParameterizedTypeReference<List<OI40DBProduct>> returnType = new ParameterizedTypeReference<List<OI40DBProduct>>() {};
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
     * @return List&lt;OI40DBProduct&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public List<OI40DBProduct> findByAfterModifiedTimestampListOI40DBProduct(String ts) throws RestClientException {
        return findByAfterModifiedTimestampListOI40DBProductWithHttpInfo(ts).getBody();
    }

    /**
     * findByAfterModifiedTimestamp
     * 
     * <p><b>200</b> - OK
     * <p><b>401</b> - Unauthorized
     * <p><b>403</b> - Forbidden
     * <p><b>404</b> - Not Found
     * @param ts ts (required)
     * @return ResponseEntity&lt;List&lt;OI40DBProduct&gt;&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<List<OI40DBProduct>> findByAfterModifiedTimestampListOI40DBProductWithHttpInfo(String ts) throws RestClientException {
        Object postBody = null;
        
        // verify the required parameter 'ts' is set
        if (ts == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'ts' when calling findByAfterModifiedTimestampListOI40DBProduct");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("ts", ts);
        String path = UriComponentsBuilder.fromPath("/integration/OI40DBProduct/modifiedAfter/{ts}").buildAndExpand(uriVariables).toUriString();

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

        ParameterizedTypeReference<List<OI40DBProduct>> returnType = new ParameterizedTypeReference<List<OI40DBProduct>>() {};
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
     * @return OI40DBProduct
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public OI40DBProduct findByCodeOI40DBProduct(String code) throws RestClientException {
        return findByCodeOI40DBProductWithHttpInfo(code).getBody();
    }

    /**
     * findByCode
     * 
     * <p><b>200</b> - OK
     * <p><b>401</b> - Unauthorized
     * <p><b>403</b> - Forbidden
     * <p><b>404</b> - Not Found
     * @param code code (required)
     * @return ResponseEntity&lt;OI40DBProduct&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<OI40DBProduct> findByCodeOI40DBProductWithHttpInfo(String code) throws RestClientException {
        Object postBody = null;
        
        // verify the required parameter 'code' is set
        if (code == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'code' when calling findByCodeOI40DBProduct");
        }
        
        // create path and map variables
        final Map<String, Object> uriVariables = new HashMap<String, Object>();
        uriVariables.put("code", code);
        String path = UriComponentsBuilder.fromPath("/integration/OI40DBProduct/byCode/{code}").buildAndExpand(uriVariables).toUriString();

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

        ParameterizedTypeReference<OI40DBProduct> returnType = new ParameterizedTypeReference<OI40DBProduct>() {};
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
     * @return List&lt;OI40DBProduct&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public List<OI40DBProduct> findByCodesListOI40DBProduct(List<String> codes) throws RestClientException {
        return findByCodesListOI40DBProductWithHttpInfo(codes).getBody();
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
     * @return ResponseEntity&lt;List&lt;OI40DBProduct&gt;&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<List<OI40DBProduct>> findByCodesListOI40DBProductWithHttpInfo(List<String> codes) throws RestClientException {
        Object postBody = codes;
        
        // verify the required parameter 'codes' is set
        if (codes == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'codes' when calling findByCodesListOI40DBProduct");
        }
        
        String path = UriComponentsBuilder.fromPath("/integration/OI40DBProduct/findByCodes").build().toUriString();

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

        ParameterizedTypeReference<List<OI40DBProduct>> returnType = new ParameterizedTypeReference<List<OI40DBProduct>>() {};
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
     * @return List&lt;OI40DBProduct&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public List<OI40DBProduct> findByQbeListOI40DBProduct(OI40DBProduct qbe) throws RestClientException {
        return findByQbeListOI40DBProductWithHttpInfo(qbe).getBody();
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
     * @return ResponseEntity&lt;List&lt;OI40DBProduct&gt;&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<List<OI40DBProduct>> findByQbeListOI40DBProductWithHttpInfo(OI40DBProduct qbe) throws RestClientException {
        Object postBody = qbe;
        
        // verify the required parameter 'qbe' is set
        if (qbe == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'qbe' when calling findByQbeListOI40DBProduct");
        }
        
        String path = UriComponentsBuilder.fromPath("/integration/OI40DBProduct/findByQbe").build().toUriString();

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

        ParameterizedTypeReference<List<OI40DBProduct>> returnType = new ParameterizedTypeReference<List<OI40DBProduct>>() {};
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
     * @return PageOI40DBProduct
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public PageOI40DBProduct findByQbePagedPageOI40DBProduct(QbeSupportOI40DBProduct qbe) throws RestClientException {
        return findByQbePagedPageOI40DBProductWithHttpInfo(qbe).getBody();
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
     * @return ResponseEntity&lt;PageOI40DBProduct&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<PageOI40DBProduct> findByQbePagedPageOI40DBProductWithHttpInfo(QbeSupportOI40DBProduct qbe) throws RestClientException {
        Object postBody = qbe;
        
        // verify the required parameter 'qbe' is set
        if (qbe == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'qbe' when calling findByQbePagedPageOI40DBProduct");
        }
        
        String path = UriComponentsBuilder.fromPath("/integration/OI40DBProduct/findByQbePaged").build().toUriString();

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

        ParameterizedTypeReference<PageOI40DBProduct> returnType = new ParameterizedTypeReference<PageOI40DBProduct>() {};
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
     * @return List&lt;OI40DBProduct&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public List<OI40DBProduct> updateListOI40DBProduct(List<OI40DBProduct> data) throws RestClientException {
        return updateListOI40DBProductWithHttpInfo(data).getBody();
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
     * @return ResponseEntity&lt;List&lt;OI40DBProduct&gt;&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<List<OI40DBProduct>> updateListOI40DBProductWithHttpInfo(List<OI40DBProduct> data) throws RestClientException {
        Object postBody = data;
        
        // verify the required parameter 'data' is set
        if (data == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'data' when calling updateListOI40DBProduct");
        }
        
        String path = UriComponentsBuilder.fromPath("/integration/OI40DBProduct/update").build().toUriString();

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

        ParameterizedTypeReference<List<OI40DBProduct>> returnType = new ParameterizedTypeReference<List<OI40DBProduct>>() {};
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
     * @return OI40DBProduct
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public OI40DBProduct updateSingleOI40DBProduct(OI40DBProduct data) throws RestClientException {
        return updateSingleOI40DBProductWithHttpInfo(data).getBody();
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
     * @return ResponseEntity&lt;OI40DBProduct&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<OI40DBProduct> updateSingleOI40DBProductWithHttpInfo(OI40DBProduct data) throws RestClientException {
        Object postBody = data;
        
        // verify the required parameter 'data' is set
        if (data == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'data' when calling updateSingleOI40DBProduct");
        }
        
        String path = UriComponentsBuilder.fromPath("/integration/OI40DBProduct/updateSingle").build().toUriString();

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

        ParameterizedTypeReference<OI40DBProduct> returnType = new ParameterizedTypeReference<OI40DBProduct>() {};
        return apiClient.invokeAPI(path, HttpMethod.POST, queryParams, postBody, headerParams, formParams, accept, contentType, authNames, returnType);
    }
}
