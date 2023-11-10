# Oi40DbTaskRepositoryApi

All URIs are relative to *https://localhost:8082/openi40-integration*

Method | HTTP request | Description
------------- | ------------- | -------------
[**deleteByCodeVoid31**](Oi40DbTaskRepositoryApi.md#deleteByCodeVoid31) | **GET** /integration/OI40DBTask/deleteByCode/{code} | deleteByCode
[**deleteByCodesVoid31**](Oi40DbTaskRepositoryApi.md#deleteByCodesVoid31) | **POST** /integration/OI40DBTask/deleteByCodes | deleteByCodes
[**doAutocompletePageOI40DBTask**](Oi40DbTaskRepositoryApi.md#doAutocompletePageOI40DBTask) | **POST** /integration/OI40DBTask/doAutocomplete | doAutocomplete
[**doLookupPageOI40DBTask**](Oi40DbTaskRepositoryApi.md#doLookupPageOI40DBTask) | **POST** /integration/OI40DBTask/doLookup | doLookup
[**findAllListOI40DBTask**](Oi40DbTaskRepositoryApi.md#findAllListOI40DBTask) | **GET** /integration/OI40DBTask | findAll
[**findAllPageOI40DBTask**](Oi40DbTaskRepositoryApi.md#findAllPageOI40DBTask) | **POST** /integration/OI40DBTask/findAllPaged | findAll
[**findByAfterIntegrationTsListOI40DBTask**](Oi40DbTaskRepositoryApi.md#findByAfterIntegrationTsListOI40DBTask) | **GET** /integration/OI40DBTask/integratedAfter/{ts} | findByAfterIntegrationTs
[**findByAfterModifiedTimestampListOI40DBTask**](Oi40DbTaskRepositoryApi.md#findByAfterModifiedTimestampListOI40DBTask) | **GET** /integration/OI40DBTask/modifiedAfter/{ts} | findByAfterModifiedTimestamp
[**findByCodeOI40DBTask**](Oi40DbTaskRepositoryApi.md#findByCodeOI40DBTask) | **GET** /integration/OI40DBTask/byCode/{code} | findByCode
[**findByCodesListOI40DBTask**](Oi40DbTaskRepositoryApi.md#findByCodesListOI40DBTask) | **POST** /integration/OI40DBTask/findByCodes | findByCodes
[**findByQbeListOI40DBTask**](Oi40DbTaskRepositoryApi.md#findByQbeListOI40DBTask) | **POST** /integration/OI40DBTask/findByQbe | findByQbe
[**findByQbePagedPageOI40DBTask**](Oi40DbTaskRepositoryApi.md#findByQbePagedPageOI40DBTask) | **POST** /integration/OI40DBTask/findByQbePaged | findByQbePaged
[**updateListOI40DBTask**](Oi40DbTaskRepositoryApi.md#updateListOI40DBTask) | **POST** /integration/OI40DBTask/update | update
[**updateSingleOI40DBTask**](Oi40DbTaskRepositoryApi.md#updateSingleOI40DBTask) | **POST** /integration/OI40DBTask/updateSingle | updateSingle


<a name="deleteByCodeVoid31"></a>
# **deleteByCodeVoid31**
> deleteByCodeVoid31(code)

deleteByCode

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbTaskRepositoryApi;


Oi40DbTaskRepositoryApi apiInstance = new Oi40DbTaskRepositoryApi();
String code = "code_example"; // String | code
try {
    apiInstance.deleteByCodeVoid31(code);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbTaskRepositoryApi#deleteByCodeVoid31");
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

<a name="deleteByCodesVoid31"></a>
# **deleteByCodesVoid31**
> deleteByCodesVoid31(codes)

deleteByCodes

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbTaskRepositoryApi;


Oi40DbTaskRepositoryApi apiInstance = new Oi40DbTaskRepositoryApi();
List<String> codes = Arrays.asList(new List<String>()); // List<String> | codes
try {
    apiInstance.deleteByCodesVoid31(codes);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbTaskRepositoryApi#deleteByCodesVoid31");
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

<a name="doAutocompletePageOI40DBTask"></a>
# **doAutocompletePageOI40DBTask**
> PageOI40DBTask doAutocompletePageOI40DBTask(autoCompleteData)

doAutocomplete

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbTaskRepositoryApi;


Oi40DbTaskRepositoryApi apiInstance = new Oi40DbTaskRepositoryApi();
AutoCompleteData autoCompleteData = new AutoCompleteData(); // AutoCompleteData | autoCompleteData
try {
    PageOI40DBTask result = apiInstance.doAutocompletePageOI40DBTask(autoCompleteData);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbTaskRepositoryApi#doAutocompletePageOI40DBTask");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **autoCompleteData** | [**AutoCompleteData**](AutoCompleteData.md)| autoCompleteData |

### Return type

[**PageOI40DBTask**](PageOI40DBTask.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: */*

<a name="doLookupPageOI40DBTask"></a>
# **doLookupPageOI40DBTask**
> PageOI40DBTask doLookupPageOI40DBTask(lookup)

doLookup

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbTaskRepositoryApi;


Oi40DbTaskRepositoryApi apiInstance = new Oi40DbTaskRepositoryApi();
LookupData lookup = new LookupData(); // LookupData | lookup
try {
    PageOI40DBTask result = apiInstance.doLookupPageOI40DBTask(lookup);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbTaskRepositoryApi#doLookupPageOI40DBTask");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **lookup** | [**LookupData**](LookupData.md)| lookup |

### Return type

[**PageOI40DBTask**](PageOI40DBTask.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: */*

<a name="findAllListOI40DBTask"></a>
# **findAllListOI40DBTask**
> List&lt;OI40DBTask&gt; findAllListOI40DBTask()

findAll

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbTaskRepositoryApi;


Oi40DbTaskRepositoryApi apiInstance = new Oi40DbTaskRepositoryApi();
try {
    List<OI40DBTask> result = apiInstance.findAllListOI40DBTask();
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbTaskRepositoryApi#findAllListOI40DBTask");
    e.printStackTrace();
}
```

### Parameters
This endpoint does not need any parameter.

### Return type

[**List&lt;OI40DBTask&gt;**](OI40DBTask.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="findAllPageOI40DBTask"></a>
# **findAllPageOI40DBTask**
> PageOI40DBTask findAllPageOI40DBTask(p)

findAll

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbTaskRepositoryApi;


Oi40DbTaskRepositoryApi apiInstance = new Oi40DbTaskRepositoryApi();
PageInfo p = new PageInfo(); // PageInfo | p
try {
    PageOI40DBTask result = apiInstance.findAllPageOI40DBTask(p);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbTaskRepositoryApi#findAllPageOI40DBTask");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **p** | [**PageInfo**](PageInfo.md)| p |

### Return type

[**PageOI40DBTask**](PageOI40DBTask.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="findByAfterIntegrationTsListOI40DBTask"></a>
# **findByAfterIntegrationTsListOI40DBTask**
> List&lt;OI40DBTask&gt; findByAfterIntegrationTsListOI40DBTask(ts)

findByAfterIntegrationTs

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbTaskRepositoryApi;


Oi40DbTaskRepositoryApi apiInstance = new Oi40DbTaskRepositoryApi();
String ts = "ts_example"; // String | ts
try {
    List<OI40DBTask> result = apiInstance.findByAfterIntegrationTsListOI40DBTask(ts);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbTaskRepositoryApi#findByAfterIntegrationTsListOI40DBTask");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **ts** | **String**| ts |

### Return type

[**List&lt;OI40DBTask&gt;**](OI40DBTask.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*

<a name="findByAfterModifiedTimestampListOI40DBTask"></a>
# **findByAfterModifiedTimestampListOI40DBTask**
> List&lt;OI40DBTask&gt; findByAfterModifiedTimestampListOI40DBTask(ts)

findByAfterModifiedTimestamp

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbTaskRepositoryApi;


Oi40DbTaskRepositoryApi apiInstance = new Oi40DbTaskRepositoryApi();
String ts = "ts_example"; // String | ts
try {
    List<OI40DBTask> result = apiInstance.findByAfterModifiedTimestampListOI40DBTask(ts);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbTaskRepositoryApi#findByAfterModifiedTimestampListOI40DBTask");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **ts** | **String**| ts |

### Return type

[**List&lt;OI40DBTask&gt;**](OI40DBTask.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*

<a name="findByCodeOI40DBTask"></a>
# **findByCodeOI40DBTask**
> OI40DBTask findByCodeOI40DBTask(code)

findByCode

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbTaskRepositoryApi;


Oi40DbTaskRepositoryApi apiInstance = new Oi40DbTaskRepositoryApi();
String code = "code_example"; // String | code
try {
    OI40DBTask result = apiInstance.findByCodeOI40DBTask(code);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbTaskRepositoryApi#findByCodeOI40DBTask");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **code** | **String**| code |

### Return type

[**OI40DBTask**](OI40DBTask.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="findByCodesListOI40DBTask"></a>
# **findByCodesListOI40DBTask**
> List&lt;OI40DBTask&gt; findByCodesListOI40DBTask(codes)

findByCodes

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbTaskRepositoryApi;


Oi40DbTaskRepositoryApi apiInstance = new Oi40DbTaskRepositoryApi();
List<String> codes = Arrays.asList(new List<String>()); // List<String> | codes
try {
    List<OI40DBTask> result = apiInstance.findByCodesListOI40DBTask(codes);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbTaskRepositoryApi#findByCodesListOI40DBTask");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **codes** | **List&lt;String&gt;**| codes |

### Return type

[**List&lt;OI40DBTask&gt;**](OI40DBTask.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="findByQbeListOI40DBTask"></a>
# **findByQbeListOI40DBTask**
> List&lt;OI40DBTask&gt; findByQbeListOI40DBTask(qbe)

findByQbe

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbTaskRepositoryApi;


Oi40DbTaskRepositoryApi apiInstance = new Oi40DbTaskRepositoryApi();
OI40DBTask qbe = new OI40DBTask(); // OI40DBTask | qbe
try {
    List<OI40DBTask> result = apiInstance.findByQbeListOI40DBTask(qbe);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbTaskRepositoryApi#findByQbeListOI40DBTask");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **qbe** | [**OI40DBTask**](OI40DBTask.md)| qbe |

### Return type

[**List&lt;OI40DBTask&gt;**](OI40DBTask.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="findByQbePagedPageOI40DBTask"></a>
# **findByQbePagedPageOI40DBTask**
> PageOI40DBTask findByQbePagedPageOI40DBTask(qbe)

findByQbePaged

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbTaskRepositoryApi;


Oi40DbTaskRepositoryApi apiInstance = new Oi40DbTaskRepositoryApi();
QbeSupportOI40DBTask qbe = new QbeSupportOI40DBTask(); // QbeSupportOI40DBTask | qbe
try {
    PageOI40DBTask result = apiInstance.findByQbePagedPageOI40DBTask(qbe);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbTaskRepositoryApi#findByQbePagedPageOI40DBTask");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **qbe** | [**QbeSupportOI40DBTask**](QbeSupportOI40DBTask.md)| qbe |

### Return type

[**PageOI40DBTask**](PageOI40DBTask.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="updateListOI40DBTask"></a>
# **updateListOI40DBTask**
> List&lt;OI40DBTask&gt; updateListOI40DBTask(data)

update

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbTaskRepositoryApi;


Oi40DbTaskRepositoryApi apiInstance = new Oi40DbTaskRepositoryApi();
List<OI40DBTask> data = Arrays.asList(new OI40DBTask()); // List<OI40DBTask> | data
try {
    List<OI40DBTask> result = apiInstance.updateListOI40DBTask(data);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbTaskRepositoryApi#updateListOI40DBTask");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **data** | [**List&lt;OI40DBTask&gt;**](OI40DBTask.md)| data |

### Return type

[**List&lt;OI40DBTask&gt;**](OI40DBTask.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="updateSingleOI40DBTask"></a>
# **updateSingleOI40DBTask**
> OI40DBTask updateSingleOI40DBTask(data)

updateSingle

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbTaskRepositoryApi;


Oi40DbTaskRepositoryApi apiInstance = new Oi40DbTaskRepositoryApi();
OI40DBTask data = new OI40DBTask(); // OI40DBTask | data
try {
    OI40DBTask result = apiInstance.updateSingleOI40DBTask(data);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbTaskRepositoryApi#updateSingleOI40DBTask");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **data** | [**OI40DBTask**](OI40DBTask.md)| data |

### Return type

[**OI40DBTask**](OI40DBTask.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

