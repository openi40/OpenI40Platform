# Oi40DbSecondaryResourceRepositoryApi

All URIs are relative to *https://localhost:8082/openi40-integration*

Method | HTTP request | Description
------------- | ------------- | -------------
[**deleteByCodeVoid25**](Oi40DbSecondaryResourceRepositoryApi.md#deleteByCodeVoid25) | **GET** /integration/OI40DBSecondaryResource/deleteByCode/{code} | deleteByCode
[**deleteByCodesVoid25**](Oi40DbSecondaryResourceRepositoryApi.md#deleteByCodesVoid25) | **POST** /integration/OI40DBSecondaryResource/deleteByCodes | deleteByCodes
[**doAutocompletePageOI40DBSecondaryResource**](Oi40DbSecondaryResourceRepositoryApi.md#doAutocompletePageOI40DBSecondaryResource) | **POST** /integration/OI40DBSecondaryResource/doAutocomplete | doAutocomplete
[**doLookupPageOI40DBSecondaryResource**](Oi40DbSecondaryResourceRepositoryApi.md#doLookupPageOI40DBSecondaryResource) | **POST** /integration/OI40DBSecondaryResource/doLookup | doLookup
[**findAllListOI40DBSecondaryResource**](Oi40DbSecondaryResourceRepositoryApi.md#findAllListOI40DBSecondaryResource) | **GET** /integration/OI40DBSecondaryResource | findAll
[**findAllPageOI40DBSecondaryResource**](Oi40DbSecondaryResourceRepositoryApi.md#findAllPageOI40DBSecondaryResource) | **POST** /integration/OI40DBSecondaryResource/findAllPaged | findAll
[**findByAfterIntegrationTsListOI40DBSecondaryResource**](Oi40DbSecondaryResourceRepositoryApi.md#findByAfterIntegrationTsListOI40DBSecondaryResource) | **GET** /integration/OI40DBSecondaryResource/integratedAfter/{ts} | findByAfterIntegrationTs
[**findByAfterModifiedTimestampListOI40DBSecondaryResource**](Oi40DbSecondaryResourceRepositoryApi.md#findByAfterModifiedTimestampListOI40DBSecondaryResource) | **GET** /integration/OI40DBSecondaryResource/modifiedAfter/{ts} | findByAfterModifiedTimestamp
[**findByCodeOI40DBSecondaryResource**](Oi40DbSecondaryResourceRepositoryApi.md#findByCodeOI40DBSecondaryResource) | **GET** /integration/OI40DBSecondaryResource/byCode/{code} | findByCode
[**findByCodesListOI40DBSecondaryResource**](Oi40DbSecondaryResourceRepositoryApi.md#findByCodesListOI40DBSecondaryResource) | **POST** /integration/OI40DBSecondaryResource/findByCodes | findByCodes
[**findByQbeListOI40DBSecondaryResource**](Oi40DbSecondaryResourceRepositoryApi.md#findByQbeListOI40DBSecondaryResource) | **POST** /integration/OI40DBSecondaryResource/findByQbe | findByQbe
[**findByQbePagedPageOI40DBSecondaryResource**](Oi40DbSecondaryResourceRepositoryApi.md#findByQbePagedPageOI40DBSecondaryResource) | **POST** /integration/OI40DBSecondaryResource/findByQbePaged | findByQbePaged
[**updateListOI40DBSecondaryResource**](Oi40DbSecondaryResourceRepositoryApi.md#updateListOI40DBSecondaryResource) | **POST** /integration/OI40DBSecondaryResource/update | update
[**updateSingleOI40DBSecondaryResource**](Oi40DbSecondaryResourceRepositoryApi.md#updateSingleOI40DBSecondaryResource) | **POST** /integration/OI40DBSecondaryResource/updateSingle | updateSingle


<a name="deleteByCodeVoid25"></a>
# **deleteByCodeVoid25**
> deleteByCodeVoid25(code)

deleteByCode

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbSecondaryResourceRepositoryApi;


Oi40DbSecondaryResourceRepositoryApi apiInstance = new Oi40DbSecondaryResourceRepositoryApi();
String code = "code_example"; // String | code
try {
    apiInstance.deleteByCodeVoid25(code);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbSecondaryResourceRepositoryApi#deleteByCodeVoid25");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **code** | **String**| code |

### Return type

null (empty response body)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*

<a name="deleteByCodesVoid25"></a>
# **deleteByCodesVoid25**
> deleteByCodesVoid25(codes)

deleteByCodes

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbSecondaryResourceRepositoryApi;


Oi40DbSecondaryResourceRepositoryApi apiInstance = new Oi40DbSecondaryResourceRepositoryApi();
List<String> codes = Arrays.asList(new List<String>()); // List<String> | codes
try {
    apiInstance.deleteByCodesVoid25(codes);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbSecondaryResourceRepositoryApi#deleteByCodesVoid25");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **codes** | **List&lt;String&gt;**| codes |

### Return type

null (empty response body)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: */*

<a name="doAutocompletePageOI40DBSecondaryResource"></a>
# **doAutocompletePageOI40DBSecondaryResource**
> PageOI40DBSecondaryResource doAutocompletePageOI40DBSecondaryResource(autoCompleteData)

doAutocomplete

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbSecondaryResourceRepositoryApi;


Oi40DbSecondaryResourceRepositoryApi apiInstance = new Oi40DbSecondaryResourceRepositoryApi();
AutoCompleteData autoCompleteData = new AutoCompleteData(); // AutoCompleteData | autoCompleteData
try {
    PageOI40DBSecondaryResource result = apiInstance.doAutocompletePageOI40DBSecondaryResource(autoCompleteData);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbSecondaryResourceRepositoryApi#doAutocompletePageOI40DBSecondaryResource");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **autoCompleteData** | [**AutoCompleteData**](AutoCompleteData.md)| autoCompleteData |

### Return type

[**PageOI40DBSecondaryResource**](PageOI40DBSecondaryResource.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: */*

<a name="doLookupPageOI40DBSecondaryResource"></a>
# **doLookupPageOI40DBSecondaryResource**
> PageOI40DBSecondaryResource doLookupPageOI40DBSecondaryResource(lookup)

doLookup

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbSecondaryResourceRepositoryApi;


Oi40DbSecondaryResourceRepositoryApi apiInstance = new Oi40DbSecondaryResourceRepositoryApi();
LookupData lookup = new LookupData(); // LookupData | lookup
try {
    PageOI40DBSecondaryResource result = apiInstance.doLookupPageOI40DBSecondaryResource(lookup);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbSecondaryResourceRepositoryApi#doLookupPageOI40DBSecondaryResource");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **lookup** | [**LookupData**](LookupData.md)| lookup |

### Return type

[**PageOI40DBSecondaryResource**](PageOI40DBSecondaryResource.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: */*

<a name="findAllListOI40DBSecondaryResource"></a>
# **findAllListOI40DBSecondaryResource**
> List&lt;OI40DBSecondaryResource&gt; findAllListOI40DBSecondaryResource()

findAll

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbSecondaryResourceRepositoryApi;


Oi40DbSecondaryResourceRepositoryApi apiInstance = new Oi40DbSecondaryResourceRepositoryApi();
try {
    List<OI40DBSecondaryResource> result = apiInstance.findAllListOI40DBSecondaryResource();
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbSecondaryResourceRepositoryApi#findAllListOI40DBSecondaryResource");
    e.printStackTrace();
}
```

### Parameters
This endpoint does not need any parameter.

### Return type

[**List&lt;OI40DBSecondaryResource&gt;**](OI40DBSecondaryResource.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="findAllPageOI40DBSecondaryResource"></a>
# **findAllPageOI40DBSecondaryResource**
> PageOI40DBSecondaryResource findAllPageOI40DBSecondaryResource(p)

findAll

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbSecondaryResourceRepositoryApi;


Oi40DbSecondaryResourceRepositoryApi apiInstance = new Oi40DbSecondaryResourceRepositoryApi();
PageInfo p = new PageInfo(); // PageInfo | p
try {
    PageOI40DBSecondaryResource result = apiInstance.findAllPageOI40DBSecondaryResource(p);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbSecondaryResourceRepositoryApi#findAllPageOI40DBSecondaryResource");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **p** | [**PageInfo**](PageInfo.md)| p |

### Return type

[**PageOI40DBSecondaryResource**](PageOI40DBSecondaryResource.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="findByAfterIntegrationTsListOI40DBSecondaryResource"></a>
# **findByAfterIntegrationTsListOI40DBSecondaryResource**
> List&lt;OI40DBSecondaryResource&gt; findByAfterIntegrationTsListOI40DBSecondaryResource(ts)

findByAfterIntegrationTs

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbSecondaryResourceRepositoryApi;


Oi40DbSecondaryResourceRepositoryApi apiInstance = new Oi40DbSecondaryResourceRepositoryApi();
String ts = "ts_example"; // String | ts
try {
    List<OI40DBSecondaryResource> result = apiInstance.findByAfterIntegrationTsListOI40DBSecondaryResource(ts);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbSecondaryResourceRepositoryApi#findByAfterIntegrationTsListOI40DBSecondaryResource");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **ts** | **String**| ts |

### Return type

[**List&lt;OI40DBSecondaryResource&gt;**](OI40DBSecondaryResource.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*

<a name="findByAfterModifiedTimestampListOI40DBSecondaryResource"></a>
# **findByAfterModifiedTimestampListOI40DBSecondaryResource**
> List&lt;OI40DBSecondaryResource&gt; findByAfterModifiedTimestampListOI40DBSecondaryResource(ts)

findByAfterModifiedTimestamp

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbSecondaryResourceRepositoryApi;


Oi40DbSecondaryResourceRepositoryApi apiInstance = new Oi40DbSecondaryResourceRepositoryApi();
String ts = "ts_example"; // String | ts
try {
    List<OI40DBSecondaryResource> result = apiInstance.findByAfterModifiedTimestampListOI40DBSecondaryResource(ts);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbSecondaryResourceRepositoryApi#findByAfterModifiedTimestampListOI40DBSecondaryResource");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **ts** | **String**| ts |

### Return type

[**List&lt;OI40DBSecondaryResource&gt;**](OI40DBSecondaryResource.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*

<a name="findByCodeOI40DBSecondaryResource"></a>
# **findByCodeOI40DBSecondaryResource**
> OI40DBSecondaryResource findByCodeOI40DBSecondaryResource(code)

findByCode

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbSecondaryResourceRepositoryApi;


Oi40DbSecondaryResourceRepositoryApi apiInstance = new Oi40DbSecondaryResourceRepositoryApi();
String code = "code_example"; // String | code
try {
    OI40DBSecondaryResource result = apiInstance.findByCodeOI40DBSecondaryResource(code);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbSecondaryResourceRepositoryApi#findByCodeOI40DBSecondaryResource");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **code** | **String**| code |

### Return type

[**OI40DBSecondaryResource**](OI40DBSecondaryResource.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="findByCodesListOI40DBSecondaryResource"></a>
# **findByCodesListOI40DBSecondaryResource**
> List&lt;OI40DBSecondaryResource&gt; findByCodesListOI40DBSecondaryResource(codes)

findByCodes

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbSecondaryResourceRepositoryApi;


Oi40DbSecondaryResourceRepositoryApi apiInstance = new Oi40DbSecondaryResourceRepositoryApi();
List<String> codes = Arrays.asList(new List<String>()); // List<String> | codes
try {
    List<OI40DBSecondaryResource> result = apiInstance.findByCodesListOI40DBSecondaryResource(codes);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbSecondaryResourceRepositoryApi#findByCodesListOI40DBSecondaryResource");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **codes** | **List&lt;String&gt;**| codes |

### Return type

[**List&lt;OI40DBSecondaryResource&gt;**](OI40DBSecondaryResource.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="findByQbeListOI40DBSecondaryResource"></a>
# **findByQbeListOI40DBSecondaryResource**
> List&lt;OI40DBSecondaryResource&gt; findByQbeListOI40DBSecondaryResource(qbe)

findByQbe

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbSecondaryResourceRepositoryApi;


Oi40DbSecondaryResourceRepositoryApi apiInstance = new Oi40DbSecondaryResourceRepositoryApi();
OI40DBSecondaryResource qbe = new OI40DBSecondaryResource(); // OI40DBSecondaryResource | qbe
try {
    List<OI40DBSecondaryResource> result = apiInstance.findByQbeListOI40DBSecondaryResource(qbe);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbSecondaryResourceRepositoryApi#findByQbeListOI40DBSecondaryResource");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **qbe** | [**OI40DBSecondaryResource**](OI40DBSecondaryResource.md)| qbe |

### Return type

[**List&lt;OI40DBSecondaryResource&gt;**](OI40DBSecondaryResource.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="findByQbePagedPageOI40DBSecondaryResource"></a>
# **findByQbePagedPageOI40DBSecondaryResource**
> PageOI40DBSecondaryResource findByQbePagedPageOI40DBSecondaryResource(qbe)

findByQbePaged

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbSecondaryResourceRepositoryApi;


Oi40DbSecondaryResourceRepositoryApi apiInstance = new Oi40DbSecondaryResourceRepositoryApi();
QbeSupportOI40DBSecondaryResource qbe = new QbeSupportOI40DBSecondaryResource(); // QbeSupportOI40DBSecondaryResource | qbe
try {
    PageOI40DBSecondaryResource result = apiInstance.findByQbePagedPageOI40DBSecondaryResource(qbe);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbSecondaryResourceRepositoryApi#findByQbePagedPageOI40DBSecondaryResource");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **qbe** | [**QbeSupportOI40DBSecondaryResource**](QbeSupportOI40DBSecondaryResource.md)| qbe |

### Return type

[**PageOI40DBSecondaryResource**](PageOI40DBSecondaryResource.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="updateListOI40DBSecondaryResource"></a>
# **updateListOI40DBSecondaryResource**
> List&lt;OI40DBSecondaryResource&gt; updateListOI40DBSecondaryResource(data)

update

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbSecondaryResourceRepositoryApi;


Oi40DbSecondaryResourceRepositoryApi apiInstance = new Oi40DbSecondaryResourceRepositoryApi();
List<OI40DBSecondaryResource> data = Arrays.asList(new OI40DBSecondaryResource()); // List<OI40DBSecondaryResource> | data
try {
    List<OI40DBSecondaryResource> result = apiInstance.updateListOI40DBSecondaryResource(data);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbSecondaryResourceRepositoryApi#updateListOI40DBSecondaryResource");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **data** | [**List&lt;OI40DBSecondaryResource&gt;**](OI40DBSecondaryResource.md)| data |

### Return type

[**List&lt;OI40DBSecondaryResource&gt;**](OI40DBSecondaryResource.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="updateSingleOI40DBSecondaryResource"></a>
# **updateSingleOI40DBSecondaryResource**
> OI40DBSecondaryResource updateSingleOI40DBSecondaryResource(data)

updateSingle

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbSecondaryResourceRepositoryApi;


Oi40DbSecondaryResourceRepositoryApi apiInstance = new Oi40DbSecondaryResourceRepositoryApi();
OI40DBSecondaryResource data = new OI40DBSecondaryResource(); // OI40DBSecondaryResource | data
try {
    OI40DBSecondaryResource result = apiInstance.updateSingleOI40DBSecondaryResource(data);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbSecondaryResourceRepositoryApi#updateSingleOI40DBSecondaryResource");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **data** | [**OI40DBSecondaryResource**](OI40DBSecondaryResource.md)| data |

### Return type

[**OI40DBSecondaryResource**](OI40DBSecondaryResource.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

