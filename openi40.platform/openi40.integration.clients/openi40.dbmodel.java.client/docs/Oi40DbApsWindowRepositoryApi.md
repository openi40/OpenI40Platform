# Oi40DbApsWindowRepositoryApi

All URIs are relative to *https://localhost:8082/openi40-integration*

Method | HTTP request | Description
------------- | ------------- | -------------
[**deleteByCodeVoid1**](Oi40DbApsWindowRepositoryApi.md#deleteByCodeVoid1) | **GET** /integration/OI40DBApsWindow/deleteByCode/{code} | deleteByCode
[**deleteByCodesVoid1**](Oi40DbApsWindowRepositoryApi.md#deleteByCodesVoid1) | **POST** /integration/OI40DBApsWindow/deleteByCodes | deleteByCodes
[**doAutocompletePageOI40DBApsWindow**](Oi40DbApsWindowRepositoryApi.md#doAutocompletePageOI40DBApsWindow) | **POST** /integration/OI40DBApsWindow/doAutocomplete | doAutocomplete
[**doLookupPageOI40DBApsWindow**](Oi40DbApsWindowRepositoryApi.md#doLookupPageOI40DBApsWindow) | **POST** /integration/OI40DBApsWindow/doLookup | doLookup
[**findAllListOI40DBApsWindow**](Oi40DbApsWindowRepositoryApi.md#findAllListOI40DBApsWindow) | **GET** /integration/OI40DBApsWindow | findAll
[**findAllPageOI40DBApsWindow**](Oi40DbApsWindowRepositoryApi.md#findAllPageOI40DBApsWindow) | **POST** /integration/OI40DBApsWindow/findAllPaged | findAll
[**findByAfterIntegrationTsListOI40DBApsWindow**](Oi40DbApsWindowRepositoryApi.md#findByAfterIntegrationTsListOI40DBApsWindow) | **GET** /integration/OI40DBApsWindow/integratedAfter/{ts} | findByAfterIntegrationTs
[**findByAfterModifiedTimestampListOI40DBApsWindow**](Oi40DbApsWindowRepositoryApi.md#findByAfterModifiedTimestampListOI40DBApsWindow) | **GET** /integration/OI40DBApsWindow/modifiedAfter/{ts} | findByAfterModifiedTimestamp
[**findByCodeOI40DBApsWindow**](Oi40DbApsWindowRepositoryApi.md#findByCodeOI40DBApsWindow) | **GET** /integration/OI40DBApsWindow/byCode/{code} | findByCode
[**findByCodesListOI40DBApsWindow**](Oi40DbApsWindowRepositoryApi.md#findByCodesListOI40DBApsWindow) | **POST** /integration/OI40DBApsWindow/findByCodes | findByCodes
[**findByQbeListOI40DBApsWindow**](Oi40DbApsWindowRepositoryApi.md#findByQbeListOI40DBApsWindow) | **POST** /integration/OI40DBApsWindow/findByQbe | findByQbe
[**findByQbePagedPageOI40DBApsWindow**](Oi40DbApsWindowRepositoryApi.md#findByQbePagedPageOI40DBApsWindow) | **POST** /integration/OI40DBApsWindow/findByQbePaged | findByQbePaged
[**updateListOI40DBApsWindow**](Oi40DbApsWindowRepositoryApi.md#updateListOI40DBApsWindow) | **POST** /integration/OI40DBApsWindow/update | update
[**updateSingleOI40DBApsWindow**](Oi40DbApsWindowRepositoryApi.md#updateSingleOI40DBApsWindow) | **POST** /integration/OI40DBApsWindow/updateSingle | updateSingle


<a name="deleteByCodeVoid1"></a>
# **deleteByCodeVoid1**
> deleteByCodeVoid1(code)

deleteByCode

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbApsWindowRepositoryApi;


Oi40DbApsWindowRepositoryApi apiInstance = new Oi40DbApsWindowRepositoryApi();
String code = "code_example"; // String | code
try {
    apiInstance.deleteByCodeVoid1(code);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbApsWindowRepositoryApi#deleteByCodeVoid1");
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

<a name="deleteByCodesVoid1"></a>
# **deleteByCodesVoid1**
> deleteByCodesVoid1(codes)

deleteByCodes

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbApsWindowRepositoryApi;


Oi40DbApsWindowRepositoryApi apiInstance = new Oi40DbApsWindowRepositoryApi();
List<String> codes = Arrays.asList(new List<String>()); // List<String> | codes
try {
    apiInstance.deleteByCodesVoid1(codes);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbApsWindowRepositoryApi#deleteByCodesVoid1");
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

<a name="doAutocompletePageOI40DBApsWindow"></a>
# **doAutocompletePageOI40DBApsWindow**
> PageOI40DBApsWindow doAutocompletePageOI40DBApsWindow(autoCompleteData)

doAutocomplete

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbApsWindowRepositoryApi;


Oi40DbApsWindowRepositoryApi apiInstance = new Oi40DbApsWindowRepositoryApi();
AutoCompleteData autoCompleteData = new AutoCompleteData(); // AutoCompleteData | autoCompleteData
try {
    PageOI40DBApsWindow result = apiInstance.doAutocompletePageOI40DBApsWindow(autoCompleteData);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbApsWindowRepositoryApi#doAutocompletePageOI40DBApsWindow");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **autoCompleteData** | [**AutoCompleteData**](AutoCompleteData.md)| autoCompleteData |

### Return type

[**PageOI40DBApsWindow**](PageOI40DBApsWindow.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: */*

<a name="doLookupPageOI40DBApsWindow"></a>
# **doLookupPageOI40DBApsWindow**
> PageOI40DBApsWindow doLookupPageOI40DBApsWindow(lookup)

doLookup

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbApsWindowRepositoryApi;


Oi40DbApsWindowRepositoryApi apiInstance = new Oi40DbApsWindowRepositoryApi();
LookupData lookup = new LookupData(); // LookupData | lookup
try {
    PageOI40DBApsWindow result = apiInstance.doLookupPageOI40DBApsWindow(lookup);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbApsWindowRepositoryApi#doLookupPageOI40DBApsWindow");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **lookup** | [**LookupData**](LookupData.md)| lookup |

### Return type

[**PageOI40DBApsWindow**](PageOI40DBApsWindow.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: */*

<a name="findAllListOI40DBApsWindow"></a>
# **findAllListOI40DBApsWindow**
> List&lt;OI40DBApsWindow&gt; findAllListOI40DBApsWindow()

findAll

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbApsWindowRepositoryApi;


Oi40DbApsWindowRepositoryApi apiInstance = new Oi40DbApsWindowRepositoryApi();
try {
    List<OI40DBApsWindow> result = apiInstance.findAllListOI40DBApsWindow();
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbApsWindowRepositoryApi#findAllListOI40DBApsWindow");
    e.printStackTrace();
}
```

### Parameters
This endpoint does not need any parameter.

### Return type

[**List&lt;OI40DBApsWindow&gt;**](OI40DBApsWindow.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="findAllPageOI40DBApsWindow"></a>
# **findAllPageOI40DBApsWindow**
> PageOI40DBApsWindow findAllPageOI40DBApsWindow(p)

findAll

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbApsWindowRepositoryApi;


Oi40DbApsWindowRepositoryApi apiInstance = new Oi40DbApsWindowRepositoryApi();
PageInfo p = new PageInfo(); // PageInfo | p
try {
    PageOI40DBApsWindow result = apiInstance.findAllPageOI40DBApsWindow(p);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbApsWindowRepositoryApi#findAllPageOI40DBApsWindow");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **p** | [**PageInfo**](PageInfo.md)| p |

### Return type

[**PageOI40DBApsWindow**](PageOI40DBApsWindow.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="findByAfterIntegrationTsListOI40DBApsWindow"></a>
# **findByAfterIntegrationTsListOI40DBApsWindow**
> List&lt;OI40DBApsWindow&gt; findByAfterIntegrationTsListOI40DBApsWindow(ts)

findByAfterIntegrationTs

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbApsWindowRepositoryApi;


Oi40DbApsWindowRepositoryApi apiInstance = new Oi40DbApsWindowRepositoryApi();
String ts = "ts_example"; // String | ts
try {
    List<OI40DBApsWindow> result = apiInstance.findByAfterIntegrationTsListOI40DBApsWindow(ts);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbApsWindowRepositoryApi#findByAfterIntegrationTsListOI40DBApsWindow");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **ts** | **String**| ts |

### Return type

[**List&lt;OI40DBApsWindow&gt;**](OI40DBApsWindow.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*

<a name="findByAfterModifiedTimestampListOI40DBApsWindow"></a>
# **findByAfterModifiedTimestampListOI40DBApsWindow**
> List&lt;OI40DBApsWindow&gt; findByAfterModifiedTimestampListOI40DBApsWindow(ts)

findByAfterModifiedTimestamp

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbApsWindowRepositoryApi;


Oi40DbApsWindowRepositoryApi apiInstance = new Oi40DbApsWindowRepositoryApi();
String ts = "ts_example"; // String | ts
try {
    List<OI40DBApsWindow> result = apiInstance.findByAfterModifiedTimestampListOI40DBApsWindow(ts);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbApsWindowRepositoryApi#findByAfterModifiedTimestampListOI40DBApsWindow");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **ts** | **String**| ts |

### Return type

[**List&lt;OI40DBApsWindow&gt;**](OI40DBApsWindow.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*

<a name="findByCodeOI40DBApsWindow"></a>
# **findByCodeOI40DBApsWindow**
> OI40DBApsWindow findByCodeOI40DBApsWindow(code)

findByCode

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbApsWindowRepositoryApi;


Oi40DbApsWindowRepositoryApi apiInstance = new Oi40DbApsWindowRepositoryApi();
String code = "code_example"; // String | code
try {
    OI40DBApsWindow result = apiInstance.findByCodeOI40DBApsWindow(code);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbApsWindowRepositoryApi#findByCodeOI40DBApsWindow");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **code** | **String**| code |

### Return type

[**OI40DBApsWindow**](OI40DBApsWindow.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="findByCodesListOI40DBApsWindow"></a>
# **findByCodesListOI40DBApsWindow**
> List&lt;OI40DBApsWindow&gt; findByCodesListOI40DBApsWindow(codes)

findByCodes

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbApsWindowRepositoryApi;


Oi40DbApsWindowRepositoryApi apiInstance = new Oi40DbApsWindowRepositoryApi();
List<String> codes = Arrays.asList(new List<String>()); // List<String> | codes
try {
    List<OI40DBApsWindow> result = apiInstance.findByCodesListOI40DBApsWindow(codes);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbApsWindowRepositoryApi#findByCodesListOI40DBApsWindow");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **codes** | **List&lt;String&gt;**| codes |

### Return type

[**List&lt;OI40DBApsWindow&gt;**](OI40DBApsWindow.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="findByQbeListOI40DBApsWindow"></a>
# **findByQbeListOI40DBApsWindow**
> List&lt;OI40DBApsWindow&gt; findByQbeListOI40DBApsWindow(qbe)

findByQbe

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbApsWindowRepositoryApi;


Oi40DbApsWindowRepositoryApi apiInstance = new Oi40DbApsWindowRepositoryApi();
OI40DBApsWindow qbe = new OI40DBApsWindow(); // OI40DBApsWindow | qbe
try {
    List<OI40DBApsWindow> result = apiInstance.findByQbeListOI40DBApsWindow(qbe);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbApsWindowRepositoryApi#findByQbeListOI40DBApsWindow");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **qbe** | [**OI40DBApsWindow**](OI40DBApsWindow.md)| qbe |

### Return type

[**List&lt;OI40DBApsWindow&gt;**](OI40DBApsWindow.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="findByQbePagedPageOI40DBApsWindow"></a>
# **findByQbePagedPageOI40DBApsWindow**
> PageOI40DBApsWindow findByQbePagedPageOI40DBApsWindow(qbe)

findByQbePaged

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbApsWindowRepositoryApi;


Oi40DbApsWindowRepositoryApi apiInstance = new Oi40DbApsWindowRepositoryApi();
QbeSupportOI40DBApsWindow qbe = new QbeSupportOI40DBApsWindow(); // QbeSupportOI40DBApsWindow | qbe
try {
    PageOI40DBApsWindow result = apiInstance.findByQbePagedPageOI40DBApsWindow(qbe);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbApsWindowRepositoryApi#findByQbePagedPageOI40DBApsWindow");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **qbe** | [**QbeSupportOI40DBApsWindow**](QbeSupportOI40DBApsWindow.md)| qbe |

### Return type

[**PageOI40DBApsWindow**](PageOI40DBApsWindow.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="updateListOI40DBApsWindow"></a>
# **updateListOI40DBApsWindow**
> List&lt;OI40DBApsWindow&gt; updateListOI40DBApsWindow(data)

update

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbApsWindowRepositoryApi;


Oi40DbApsWindowRepositoryApi apiInstance = new Oi40DbApsWindowRepositoryApi();
List<OI40DBApsWindow> data = Arrays.asList(new OI40DBApsWindow()); // List<OI40DBApsWindow> | data
try {
    List<OI40DBApsWindow> result = apiInstance.updateListOI40DBApsWindow(data);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbApsWindowRepositoryApi#updateListOI40DBApsWindow");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **data** | [**List&lt;OI40DBApsWindow&gt;**](OI40DBApsWindow.md)| data |

### Return type

[**List&lt;OI40DBApsWindow&gt;**](OI40DBApsWindow.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="updateSingleOI40DBApsWindow"></a>
# **updateSingleOI40DBApsWindow**
> OI40DBApsWindow updateSingleOI40DBApsWindow(data)

updateSingle

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbApsWindowRepositoryApi;


Oi40DbApsWindowRepositoryApi apiInstance = new Oi40DbApsWindowRepositoryApi();
OI40DBApsWindow data = new OI40DBApsWindow(); // OI40DBApsWindow | data
try {
    OI40DBApsWindow result = apiInstance.updateSingleOI40DBApsWindow(data);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbApsWindowRepositoryApi#updateSingleOI40DBApsWindow");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **data** | [**OI40DBApsWindow**](OI40DBApsWindow.md)| data |

### Return type

[**OI40DBApsWindow**](OI40DBApsWindow.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

