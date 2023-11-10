# Oi40DbSalesOrderLineRepositoryApi

All URIs are relative to *https://localhost:8082/openi40-integration*

Method | HTTP request | Description
------------- | ------------- | -------------
[**deleteByCodeVoid22**](Oi40DbSalesOrderLineRepositoryApi.md#deleteByCodeVoid22) | **GET** /integration/OI40DBSalesOrderLine/deleteByCode/{code} | deleteByCode
[**deleteByCodesVoid22**](Oi40DbSalesOrderLineRepositoryApi.md#deleteByCodesVoid22) | **POST** /integration/OI40DBSalesOrderLine/deleteByCodes | deleteByCodes
[**doAutocompletePageOI40DBSalesOrderLine**](Oi40DbSalesOrderLineRepositoryApi.md#doAutocompletePageOI40DBSalesOrderLine) | **POST** /integration/OI40DBSalesOrderLine/doAutocomplete | doAutocomplete
[**doLookupPageOI40DBSalesOrderLine**](Oi40DbSalesOrderLineRepositoryApi.md#doLookupPageOI40DBSalesOrderLine) | **POST** /integration/OI40DBSalesOrderLine/doLookup | doLookup
[**findAllListOI40DBSalesOrderLine**](Oi40DbSalesOrderLineRepositoryApi.md#findAllListOI40DBSalesOrderLine) | **GET** /integration/OI40DBSalesOrderLine | findAll
[**findAllPageOI40DBSalesOrderLine**](Oi40DbSalesOrderLineRepositoryApi.md#findAllPageOI40DBSalesOrderLine) | **POST** /integration/OI40DBSalesOrderLine/findAllPaged | findAll
[**findByAfterIntegrationTsListOI40DBSalesOrderLine**](Oi40DbSalesOrderLineRepositoryApi.md#findByAfterIntegrationTsListOI40DBSalesOrderLine) | **GET** /integration/OI40DBSalesOrderLine/integratedAfter/{ts} | findByAfterIntegrationTs
[**findByAfterModifiedTimestampListOI40DBSalesOrderLine**](Oi40DbSalesOrderLineRepositoryApi.md#findByAfterModifiedTimestampListOI40DBSalesOrderLine) | **GET** /integration/OI40DBSalesOrderLine/modifiedAfter/{ts} | findByAfterModifiedTimestamp
[**findByCodeOI40DBSalesOrderLine**](Oi40DbSalesOrderLineRepositoryApi.md#findByCodeOI40DBSalesOrderLine) | **GET** /integration/OI40DBSalesOrderLine/byCode/{code} | findByCode
[**findByCodesListOI40DBSalesOrderLine**](Oi40DbSalesOrderLineRepositoryApi.md#findByCodesListOI40DBSalesOrderLine) | **POST** /integration/OI40DBSalesOrderLine/findByCodes | findByCodes
[**findByQbeListOI40DBSalesOrderLine**](Oi40DbSalesOrderLineRepositoryApi.md#findByQbeListOI40DBSalesOrderLine) | **POST** /integration/OI40DBSalesOrderLine/findByQbe | findByQbe
[**findByQbePagedPageOI40DBSalesOrderLine**](Oi40DbSalesOrderLineRepositoryApi.md#findByQbePagedPageOI40DBSalesOrderLine) | **POST** /integration/OI40DBSalesOrderLine/findByQbePaged | findByQbePaged
[**updateListOI40DBSalesOrderLine**](Oi40DbSalesOrderLineRepositoryApi.md#updateListOI40DBSalesOrderLine) | **POST** /integration/OI40DBSalesOrderLine/update | update
[**updateSingleOI40DBSalesOrderLine**](Oi40DbSalesOrderLineRepositoryApi.md#updateSingleOI40DBSalesOrderLine) | **POST** /integration/OI40DBSalesOrderLine/updateSingle | updateSingle


<a name="deleteByCodeVoid22"></a>
# **deleteByCodeVoid22**
> deleteByCodeVoid22(code)

deleteByCode

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbSalesOrderLineRepositoryApi;


Oi40DbSalesOrderLineRepositoryApi apiInstance = new Oi40DbSalesOrderLineRepositoryApi();
String code = "code_example"; // String | code
try {
    apiInstance.deleteByCodeVoid22(code);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbSalesOrderLineRepositoryApi#deleteByCodeVoid22");
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

<a name="deleteByCodesVoid22"></a>
# **deleteByCodesVoid22**
> deleteByCodesVoid22(codes)

deleteByCodes

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbSalesOrderLineRepositoryApi;


Oi40DbSalesOrderLineRepositoryApi apiInstance = new Oi40DbSalesOrderLineRepositoryApi();
List<String> codes = Arrays.asList(new List<String>()); // List<String> | codes
try {
    apiInstance.deleteByCodesVoid22(codes);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbSalesOrderLineRepositoryApi#deleteByCodesVoid22");
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

<a name="doAutocompletePageOI40DBSalesOrderLine"></a>
# **doAutocompletePageOI40DBSalesOrderLine**
> PageOI40DBSalesOrderLine doAutocompletePageOI40DBSalesOrderLine(autoCompleteData)

doAutocomplete

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbSalesOrderLineRepositoryApi;


Oi40DbSalesOrderLineRepositoryApi apiInstance = new Oi40DbSalesOrderLineRepositoryApi();
AutoCompleteData autoCompleteData = new AutoCompleteData(); // AutoCompleteData | autoCompleteData
try {
    PageOI40DBSalesOrderLine result = apiInstance.doAutocompletePageOI40DBSalesOrderLine(autoCompleteData);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbSalesOrderLineRepositoryApi#doAutocompletePageOI40DBSalesOrderLine");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **autoCompleteData** | [**AutoCompleteData**](AutoCompleteData.md)| autoCompleteData |

### Return type

[**PageOI40DBSalesOrderLine**](PageOI40DBSalesOrderLine.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: */*

<a name="doLookupPageOI40DBSalesOrderLine"></a>
# **doLookupPageOI40DBSalesOrderLine**
> PageOI40DBSalesOrderLine doLookupPageOI40DBSalesOrderLine(lookup)

doLookup

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbSalesOrderLineRepositoryApi;


Oi40DbSalesOrderLineRepositoryApi apiInstance = new Oi40DbSalesOrderLineRepositoryApi();
LookupData lookup = new LookupData(); // LookupData | lookup
try {
    PageOI40DBSalesOrderLine result = apiInstance.doLookupPageOI40DBSalesOrderLine(lookup);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbSalesOrderLineRepositoryApi#doLookupPageOI40DBSalesOrderLine");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **lookup** | [**LookupData**](LookupData.md)| lookup |

### Return type

[**PageOI40DBSalesOrderLine**](PageOI40DBSalesOrderLine.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: */*

<a name="findAllListOI40DBSalesOrderLine"></a>
# **findAllListOI40DBSalesOrderLine**
> List&lt;OI40DBSalesOrderLine&gt; findAllListOI40DBSalesOrderLine()

findAll

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbSalesOrderLineRepositoryApi;


Oi40DbSalesOrderLineRepositoryApi apiInstance = new Oi40DbSalesOrderLineRepositoryApi();
try {
    List<OI40DBSalesOrderLine> result = apiInstance.findAllListOI40DBSalesOrderLine();
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbSalesOrderLineRepositoryApi#findAllListOI40DBSalesOrderLine");
    e.printStackTrace();
}
```

### Parameters
This endpoint does not need any parameter.

### Return type

[**List&lt;OI40DBSalesOrderLine&gt;**](OI40DBSalesOrderLine.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="findAllPageOI40DBSalesOrderLine"></a>
# **findAllPageOI40DBSalesOrderLine**
> PageOI40DBSalesOrderLine findAllPageOI40DBSalesOrderLine(p)

findAll

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbSalesOrderLineRepositoryApi;


Oi40DbSalesOrderLineRepositoryApi apiInstance = new Oi40DbSalesOrderLineRepositoryApi();
PageInfo p = new PageInfo(); // PageInfo | p
try {
    PageOI40DBSalesOrderLine result = apiInstance.findAllPageOI40DBSalesOrderLine(p);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbSalesOrderLineRepositoryApi#findAllPageOI40DBSalesOrderLine");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **p** | [**PageInfo**](PageInfo.md)| p |

### Return type

[**PageOI40DBSalesOrderLine**](PageOI40DBSalesOrderLine.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="findByAfterIntegrationTsListOI40DBSalesOrderLine"></a>
# **findByAfterIntegrationTsListOI40DBSalesOrderLine**
> List&lt;OI40DBSalesOrderLine&gt; findByAfterIntegrationTsListOI40DBSalesOrderLine(ts)

findByAfterIntegrationTs

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbSalesOrderLineRepositoryApi;


Oi40DbSalesOrderLineRepositoryApi apiInstance = new Oi40DbSalesOrderLineRepositoryApi();
String ts = "ts_example"; // String | ts
try {
    List<OI40DBSalesOrderLine> result = apiInstance.findByAfterIntegrationTsListOI40DBSalesOrderLine(ts);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbSalesOrderLineRepositoryApi#findByAfterIntegrationTsListOI40DBSalesOrderLine");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **ts** | **String**| ts |

### Return type

[**List&lt;OI40DBSalesOrderLine&gt;**](OI40DBSalesOrderLine.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*

<a name="findByAfterModifiedTimestampListOI40DBSalesOrderLine"></a>
# **findByAfterModifiedTimestampListOI40DBSalesOrderLine**
> List&lt;OI40DBSalesOrderLine&gt; findByAfterModifiedTimestampListOI40DBSalesOrderLine(ts)

findByAfterModifiedTimestamp

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbSalesOrderLineRepositoryApi;


Oi40DbSalesOrderLineRepositoryApi apiInstance = new Oi40DbSalesOrderLineRepositoryApi();
String ts = "ts_example"; // String | ts
try {
    List<OI40DBSalesOrderLine> result = apiInstance.findByAfterModifiedTimestampListOI40DBSalesOrderLine(ts);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbSalesOrderLineRepositoryApi#findByAfterModifiedTimestampListOI40DBSalesOrderLine");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **ts** | **String**| ts |

### Return type

[**List&lt;OI40DBSalesOrderLine&gt;**](OI40DBSalesOrderLine.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*

<a name="findByCodeOI40DBSalesOrderLine"></a>
# **findByCodeOI40DBSalesOrderLine**
> OI40DBSalesOrderLine findByCodeOI40DBSalesOrderLine(code)

findByCode

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbSalesOrderLineRepositoryApi;


Oi40DbSalesOrderLineRepositoryApi apiInstance = new Oi40DbSalesOrderLineRepositoryApi();
String code = "code_example"; // String | code
try {
    OI40DBSalesOrderLine result = apiInstance.findByCodeOI40DBSalesOrderLine(code);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbSalesOrderLineRepositoryApi#findByCodeOI40DBSalesOrderLine");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **code** | **String**| code |

### Return type

[**OI40DBSalesOrderLine**](OI40DBSalesOrderLine.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="findByCodesListOI40DBSalesOrderLine"></a>
# **findByCodesListOI40DBSalesOrderLine**
> List&lt;OI40DBSalesOrderLine&gt; findByCodesListOI40DBSalesOrderLine(codes)

findByCodes

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbSalesOrderLineRepositoryApi;


Oi40DbSalesOrderLineRepositoryApi apiInstance = new Oi40DbSalesOrderLineRepositoryApi();
List<String> codes = Arrays.asList(new List<String>()); // List<String> | codes
try {
    List<OI40DBSalesOrderLine> result = apiInstance.findByCodesListOI40DBSalesOrderLine(codes);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbSalesOrderLineRepositoryApi#findByCodesListOI40DBSalesOrderLine");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **codes** | **List&lt;String&gt;**| codes |

### Return type

[**List&lt;OI40DBSalesOrderLine&gt;**](OI40DBSalesOrderLine.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="findByQbeListOI40DBSalesOrderLine"></a>
# **findByQbeListOI40DBSalesOrderLine**
> List&lt;OI40DBSalesOrderLine&gt; findByQbeListOI40DBSalesOrderLine(qbe)

findByQbe

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbSalesOrderLineRepositoryApi;


Oi40DbSalesOrderLineRepositoryApi apiInstance = new Oi40DbSalesOrderLineRepositoryApi();
OI40DBSalesOrderLine qbe = new OI40DBSalesOrderLine(); // OI40DBSalesOrderLine | qbe
try {
    List<OI40DBSalesOrderLine> result = apiInstance.findByQbeListOI40DBSalesOrderLine(qbe);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbSalesOrderLineRepositoryApi#findByQbeListOI40DBSalesOrderLine");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **qbe** | [**OI40DBSalesOrderLine**](OI40DBSalesOrderLine.md)| qbe |

### Return type

[**List&lt;OI40DBSalesOrderLine&gt;**](OI40DBSalesOrderLine.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="findByQbePagedPageOI40DBSalesOrderLine"></a>
# **findByQbePagedPageOI40DBSalesOrderLine**
> PageOI40DBSalesOrderLine findByQbePagedPageOI40DBSalesOrderLine(qbe)

findByQbePaged

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbSalesOrderLineRepositoryApi;


Oi40DbSalesOrderLineRepositoryApi apiInstance = new Oi40DbSalesOrderLineRepositoryApi();
QbeSupportOI40DBSalesOrderLine qbe = new QbeSupportOI40DBSalesOrderLine(); // QbeSupportOI40DBSalesOrderLine | qbe
try {
    PageOI40DBSalesOrderLine result = apiInstance.findByQbePagedPageOI40DBSalesOrderLine(qbe);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbSalesOrderLineRepositoryApi#findByQbePagedPageOI40DBSalesOrderLine");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **qbe** | [**QbeSupportOI40DBSalesOrderLine**](QbeSupportOI40DBSalesOrderLine.md)| qbe |

### Return type

[**PageOI40DBSalesOrderLine**](PageOI40DBSalesOrderLine.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="updateListOI40DBSalesOrderLine"></a>
# **updateListOI40DBSalesOrderLine**
> List&lt;OI40DBSalesOrderLine&gt; updateListOI40DBSalesOrderLine(data)

update

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbSalesOrderLineRepositoryApi;


Oi40DbSalesOrderLineRepositoryApi apiInstance = new Oi40DbSalesOrderLineRepositoryApi();
List<OI40DBSalesOrderLine> data = Arrays.asList(new OI40DBSalesOrderLine()); // List<OI40DBSalesOrderLine> | data
try {
    List<OI40DBSalesOrderLine> result = apiInstance.updateListOI40DBSalesOrderLine(data);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbSalesOrderLineRepositoryApi#updateListOI40DBSalesOrderLine");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **data** | [**List&lt;OI40DBSalesOrderLine&gt;**](OI40DBSalesOrderLine.md)| data |

### Return type

[**List&lt;OI40DBSalesOrderLine&gt;**](OI40DBSalesOrderLine.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="updateSingleOI40DBSalesOrderLine"></a>
# **updateSingleOI40DBSalesOrderLine**
> OI40DBSalesOrderLine updateSingleOI40DBSalesOrderLine(data)

updateSingle

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbSalesOrderLineRepositoryApi;


Oi40DbSalesOrderLineRepositoryApi apiInstance = new Oi40DbSalesOrderLineRepositoryApi();
OI40DBSalesOrderLine data = new OI40DBSalesOrderLine(); // OI40DBSalesOrderLine | data
try {
    OI40DBSalesOrderLine result = apiInstance.updateSingleOI40DBSalesOrderLine(data);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbSalesOrderLineRepositoryApi#updateSingleOI40DBSalesOrderLine");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **data** | [**OI40DBSalesOrderLine**](OI40DBSalesOrderLine.md)| data |

### Return type

[**OI40DBSalesOrderLine**](OI40DBSalesOrderLine.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

