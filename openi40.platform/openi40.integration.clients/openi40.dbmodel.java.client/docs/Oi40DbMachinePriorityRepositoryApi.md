# Oi40DbMachinePriorityRepositoryApi

All URIs are relative to *https://localhost:8082/openi40-integration*

Method | HTTP request | Description
------------- | ------------- | -------------
[**deleteByCodeVoid8**](Oi40DbMachinePriorityRepositoryApi.md#deleteByCodeVoid8) | **GET** /integration/OI40DBMachinePriority/deleteByCode/{code} | deleteByCode
[**deleteByCodesVoid8**](Oi40DbMachinePriorityRepositoryApi.md#deleteByCodesVoid8) | **POST** /integration/OI40DBMachinePriority/deleteByCodes | deleteByCodes
[**doAutocompletePageOI40DBMachinePriority**](Oi40DbMachinePriorityRepositoryApi.md#doAutocompletePageOI40DBMachinePriority) | **POST** /integration/OI40DBMachinePriority/doAutocomplete | doAutocomplete
[**doLookupPageOI40DBMachinePriority**](Oi40DbMachinePriorityRepositoryApi.md#doLookupPageOI40DBMachinePriority) | **POST** /integration/OI40DBMachinePriority/doLookup | doLookup
[**findAllListOI40DBMachinePriority**](Oi40DbMachinePriorityRepositoryApi.md#findAllListOI40DBMachinePriority) | **GET** /integration/OI40DBMachinePriority | findAll
[**findAllPageOI40DBMachinePriority**](Oi40DbMachinePriorityRepositoryApi.md#findAllPageOI40DBMachinePriority) | **POST** /integration/OI40DBMachinePriority/findAllPaged | findAll
[**findByAfterIntegrationTsListOI40DBMachinePriority**](Oi40DbMachinePriorityRepositoryApi.md#findByAfterIntegrationTsListOI40DBMachinePriority) | **GET** /integration/OI40DBMachinePriority/integratedAfter/{ts} | findByAfterIntegrationTs
[**findByAfterModifiedTimestampListOI40DBMachinePriority**](Oi40DbMachinePriorityRepositoryApi.md#findByAfterModifiedTimestampListOI40DBMachinePriority) | **GET** /integration/OI40DBMachinePriority/modifiedAfter/{ts} | findByAfterModifiedTimestamp
[**findByCodeOI40DBMachinePriority**](Oi40DbMachinePriorityRepositoryApi.md#findByCodeOI40DBMachinePriority) | **GET** /integration/OI40DBMachinePriority/byCode/{code} | findByCode
[**findByCodesListOI40DBMachinePriority**](Oi40DbMachinePriorityRepositoryApi.md#findByCodesListOI40DBMachinePriority) | **POST** /integration/OI40DBMachinePriority/findByCodes | findByCodes
[**findByQbeListOI40DBMachinePriority**](Oi40DbMachinePriorityRepositoryApi.md#findByQbeListOI40DBMachinePriority) | **POST** /integration/OI40DBMachinePriority/findByQbe | findByQbe
[**findByQbePagedPageOI40DBMachinePriority**](Oi40DbMachinePriorityRepositoryApi.md#findByQbePagedPageOI40DBMachinePriority) | **POST** /integration/OI40DBMachinePriority/findByQbePaged | findByQbePaged
[**updateListOI40DBMachinePriority**](Oi40DbMachinePriorityRepositoryApi.md#updateListOI40DBMachinePriority) | **POST** /integration/OI40DBMachinePriority/update | update
[**updateSingleOI40DBMachinePriority**](Oi40DbMachinePriorityRepositoryApi.md#updateSingleOI40DBMachinePriority) | **POST** /integration/OI40DBMachinePriority/updateSingle | updateSingle


<a name="deleteByCodeVoid8"></a>
# **deleteByCodeVoid8**
> deleteByCodeVoid8(code)

deleteByCode

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbMachinePriorityRepositoryApi;


Oi40DbMachinePriorityRepositoryApi apiInstance = new Oi40DbMachinePriorityRepositoryApi();
String code = "code_example"; // String | code
try {
    apiInstance.deleteByCodeVoid8(code);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbMachinePriorityRepositoryApi#deleteByCodeVoid8");
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

<a name="deleteByCodesVoid8"></a>
# **deleteByCodesVoid8**
> deleteByCodesVoid8(codes)

deleteByCodes

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbMachinePriorityRepositoryApi;


Oi40DbMachinePriorityRepositoryApi apiInstance = new Oi40DbMachinePriorityRepositoryApi();
List<String> codes = Arrays.asList(new List<String>()); // List<String> | codes
try {
    apiInstance.deleteByCodesVoid8(codes);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbMachinePriorityRepositoryApi#deleteByCodesVoid8");
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

<a name="doAutocompletePageOI40DBMachinePriority"></a>
# **doAutocompletePageOI40DBMachinePriority**
> PageOI40DBMachinePriority doAutocompletePageOI40DBMachinePriority(autoCompleteData)

doAutocomplete

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbMachinePriorityRepositoryApi;


Oi40DbMachinePriorityRepositoryApi apiInstance = new Oi40DbMachinePriorityRepositoryApi();
AutoCompleteData autoCompleteData = new AutoCompleteData(); // AutoCompleteData | autoCompleteData
try {
    PageOI40DBMachinePriority result = apiInstance.doAutocompletePageOI40DBMachinePriority(autoCompleteData);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbMachinePriorityRepositoryApi#doAutocompletePageOI40DBMachinePriority");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **autoCompleteData** | [**AutoCompleteData**](AutoCompleteData.md)| autoCompleteData |

### Return type

[**PageOI40DBMachinePriority**](PageOI40DBMachinePriority.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: */*

<a name="doLookupPageOI40DBMachinePriority"></a>
# **doLookupPageOI40DBMachinePriority**
> PageOI40DBMachinePriority doLookupPageOI40DBMachinePriority(lookup)

doLookup

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbMachinePriorityRepositoryApi;


Oi40DbMachinePriorityRepositoryApi apiInstance = new Oi40DbMachinePriorityRepositoryApi();
LookupData lookup = new LookupData(); // LookupData | lookup
try {
    PageOI40DBMachinePriority result = apiInstance.doLookupPageOI40DBMachinePriority(lookup);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbMachinePriorityRepositoryApi#doLookupPageOI40DBMachinePriority");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **lookup** | [**LookupData**](LookupData.md)| lookup |

### Return type

[**PageOI40DBMachinePriority**](PageOI40DBMachinePriority.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: */*

<a name="findAllListOI40DBMachinePriority"></a>
# **findAllListOI40DBMachinePriority**
> List&lt;OI40DBMachinePriority&gt; findAllListOI40DBMachinePriority()

findAll

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbMachinePriorityRepositoryApi;


Oi40DbMachinePriorityRepositoryApi apiInstance = new Oi40DbMachinePriorityRepositoryApi();
try {
    List<OI40DBMachinePriority> result = apiInstance.findAllListOI40DBMachinePriority();
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbMachinePriorityRepositoryApi#findAllListOI40DBMachinePriority");
    e.printStackTrace();
}
```

### Parameters
This endpoint does not need any parameter.

### Return type

[**List&lt;OI40DBMachinePriority&gt;**](OI40DBMachinePriority.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="findAllPageOI40DBMachinePriority"></a>
# **findAllPageOI40DBMachinePriority**
> PageOI40DBMachinePriority findAllPageOI40DBMachinePriority(p)

findAll

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbMachinePriorityRepositoryApi;


Oi40DbMachinePriorityRepositoryApi apiInstance = new Oi40DbMachinePriorityRepositoryApi();
PageInfo p = new PageInfo(); // PageInfo | p
try {
    PageOI40DBMachinePriority result = apiInstance.findAllPageOI40DBMachinePriority(p);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbMachinePriorityRepositoryApi#findAllPageOI40DBMachinePriority");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **p** | [**PageInfo**](PageInfo.md)| p |

### Return type

[**PageOI40DBMachinePriority**](PageOI40DBMachinePriority.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="findByAfterIntegrationTsListOI40DBMachinePriority"></a>
# **findByAfterIntegrationTsListOI40DBMachinePriority**
> List&lt;OI40DBMachinePriority&gt; findByAfterIntegrationTsListOI40DBMachinePriority(ts)

findByAfterIntegrationTs

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbMachinePriorityRepositoryApi;


Oi40DbMachinePriorityRepositoryApi apiInstance = new Oi40DbMachinePriorityRepositoryApi();
String ts = "ts_example"; // String | ts
try {
    List<OI40DBMachinePriority> result = apiInstance.findByAfterIntegrationTsListOI40DBMachinePriority(ts);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbMachinePriorityRepositoryApi#findByAfterIntegrationTsListOI40DBMachinePriority");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **ts** | **String**| ts |

### Return type

[**List&lt;OI40DBMachinePriority&gt;**](OI40DBMachinePriority.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*

<a name="findByAfterModifiedTimestampListOI40DBMachinePriority"></a>
# **findByAfterModifiedTimestampListOI40DBMachinePriority**
> List&lt;OI40DBMachinePriority&gt; findByAfterModifiedTimestampListOI40DBMachinePriority(ts)

findByAfterModifiedTimestamp

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbMachinePriorityRepositoryApi;


Oi40DbMachinePriorityRepositoryApi apiInstance = new Oi40DbMachinePriorityRepositoryApi();
String ts = "ts_example"; // String | ts
try {
    List<OI40DBMachinePriority> result = apiInstance.findByAfterModifiedTimestampListOI40DBMachinePriority(ts);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbMachinePriorityRepositoryApi#findByAfterModifiedTimestampListOI40DBMachinePriority");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **ts** | **String**| ts |

### Return type

[**List&lt;OI40DBMachinePriority&gt;**](OI40DBMachinePriority.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*

<a name="findByCodeOI40DBMachinePriority"></a>
# **findByCodeOI40DBMachinePriority**
> OI40DBMachinePriority findByCodeOI40DBMachinePriority(code)

findByCode

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbMachinePriorityRepositoryApi;


Oi40DbMachinePriorityRepositoryApi apiInstance = new Oi40DbMachinePriorityRepositoryApi();
String code = "code_example"; // String | code
try {
    OI40DBMachinePriority result = apiInstance.findByCodeOI40DBMachinePriority(code);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbMachinePriorityRepositoryApi#findByCodeOI40DBMachinePriority");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **code** | **String**| code |

### Return type

[**OI40DBMachinePriority**](OI40DBMachinePriority.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="findByCodesListOI40DBMachinePriority"></a>
# **findByCodesListOI40DBMachinePriority**
> List&lt;OI40DBMachinePriority&gt; findByCodesListOI40DBMachinePriority(codes)

findByCodes

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbMachinePriorityRepositoryApi;


Oi40DbMachinePriorityRepositoryApi apiInstance = new Oi40DbMachinePriorityRepositoryApi();
List<String> codes = Arrays.asList(new List<String>()); // List<String> | codes
try {
    List<OI40DBMachinePriority> result = apiInstance.findByCodesListOI40DBMachinePriority(codes);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbMachinePriorityRepositoryApi#findByCodesListOI40DBMachinePriority");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **codes** | **List&lt;String&gt;**| codes |

### Return type

[**List&lt;OI40DBMachinePriority&gt;**](OI40DBMachinePriority.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="findByQbeListOI40DBMachinePriority"></a>
# **findByQbeListOI40DBMachinePriority**
> List&lt;OI40DBMachinePriority&gt; findByQbeListOI40DBMachinePriority(qbe)

findByQbe

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbMachinePriorityRepositoryApi;


Oi40DbMachinePriorityRepositoryApi apiInstance = new Oi40DbMachinePriorityRepositoryApi();
OI40DBMachinePriority qbe = new OI40DBMachinePriority(); // OI40DBMachinePriority | qbe
try {
    List<OI40DBMachinePriority> result = apiInstance.findByQbeListOI40DBMachinePriority(qbe);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbMachinePriorityRepositoryApi#findByQbeListOI40DBMachinePriority");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **qbe** | [**OI40DBMachinePriority**](OI40DBMachinePriority.md)| qbe |

### Return type

[**List&lt;OI40DBMachinePriority&gt;**](OI40DBMachinePriority.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="findByQbePagedPageOI40DBMachinePriority"></a>
# **findByQbePagedPageOI40DBMachinePriority**
> PageOI40DBMachinePriority findByQbePagedPageOI40DBMachinePriority(qbe)

findByQbePaged

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbMachinePriorityRepositoryApi;


Oi40DbMachinePriorityRepositoryApi apiInstance = new Oi40DbMachinePriorityRepositoryApi();
QbeSupportOI40DBMachinePriority qbe = new QbeSupportOI40DBMachinePriority(); // QbeSupportOI40DBMachinePriority | qbe
try {
    PageOI40DBMachinePriority result = apiInstance.findByQbePagedPageOI40DBMachinePriority(qbe);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbMachinePriorityRepositoryApi#findByQbePagedPageOI40DBMachinePriority");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **qbe** | [**QbeSupportOI40DBMachinePriority**](QbeSupportOI40DBMachinePriority.md)| qbe |

### Return type

[**PageOI40DBMachinePriority**](PageOI40DBMachinePriority.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="updateListOI40DBMachinePriority"></a>
# **updateListOI40DBMachinePriority**
> List&lt;OI40DBMachinePriority&gt; updateListOI40DBMachinePriority(data)

update

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbMachinePriorityRepositoryApi;


Oi40DbMachinePriorityRepositoryApi apiInstance = new Oi40DbMachinePriorityRepositoryApi();
List<OI40DBMachinePriority> data = Arrays.asList(new OI40DBMachinePriority()); // List<OI40DBMachinePriority> | data
try {
    List<OI40DBMachinePriority> result = apiInstance.updateListOI40DBMachinePriority(data);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbMachinePriorityRepositoryApi#updateListOI40DBMachinePriority");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **data** | [**List&lt;OI40DBMachinePriority&gt;**](OI40DBMachinePriority.md)| data |

### Return type

[**List&lt;OI40DBMachinePriority&gt;**](OI40DBMachinePriority.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="updateSingleOI40DBMachinePriority"></a>
# **updateSingleOI40DBMachinePriority**
> OI40DBMachinePriority updateSingleOI40DBMachinePriority(data)

updateSingle

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbMachinePriorityRepositoryApi;


Oi40DbMachinePriorityRepositoryApi apiInstance = new Oi40DbMachinePriorityRepositoryApi();
OI40DBMachinePriority data = new OI40DBMachinePriority(); // OI40DBMachinePriority | data
try {
    OI40DBMachinePriority result = apiInstance.updateSingleOI40DBMachinePriority(data);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbMachinePriorityRepositoryApi#updateSingleOI40DBMachinePriority");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **data** | [**OI40DBMachinePriority**](OI40DBMachinePriority.md)| data |

### Return type

[**OI40DBMachinePriority**](OI40DBMachinePriority.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

