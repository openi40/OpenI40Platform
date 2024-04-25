# Oi40DbStockSupplyRepositoryApi

All URIs are relative to *https://localhost:8083/openi40-backoffice*

Method | HTTP request | Description
------------- | ------------- | -------------
[**deleteByCodeVoid27**](Oi40DbStockSupplyRepositoryApi.md#deleteByCodeVoid27) | **GET** /integration/OI40DBStockSupply/deleteByCode/{code} | deleteByCode
[**deleteByCodesVoid27**](Oi40DbStockSupplyRepositoryApi.md#deleteByCodesVoid27) | **POST** /integration/OI40DBStockSupply/deleteByCodes | deleteByCodes
[**doAutocompletePageOI40DBStockSupply**](Oi40DbStockSupplyRepositoryApi.md#doAutocompletePageOI40DBStockSupply) | **POST** /integration/OI40DBStockSupply/doAutocomplete | doAutocomplete
[**doLookupPageOI40DBStockSupply**](Oi40DbStockSupplyRepositoryApi.md#doLookupPageOI40DBStockSupply) | **POST** /integration/OI40DBStockSupply/doLookup | doLookup
[**findAllListOI40DBStockSupply**](Oi40DbStockSupplyRepositoryApi.md#findAllListOI40DBStockSupply) | **GET** /integration/OI40DBStockSupply | findAll
[**findAllPageOI40DBStockSupply**](Oi40DbStockSupplyRepositoryApi.md#findAllPageOI40DBStockSupply) | **POST** /integration/OI40DBStockSupply/findAllPaged | findAll
[**findByAfterIntegrationTsListOI40DBStockSupply**](Oi40DbStockSupplyRepositoryApi.md#findByAfterIntegrationTsListOI40DBStockSupply) | **GET** /integration/OI40DBStockSupply/integratedAfter/{ts} | findByAfterIntegrationTs
[**findByAfterModifiedTimestampListOI40DBStockSupply**](Oi40DbStockSupplyRepositoryApi.md#findByAfterModifiedTimestampListOI40DBStockSupply) | **GET** /integration/OI40DBStockSupply/modifiedAfter/{ts} | findByAfterModifiedTimestamp
[**findByCodeOI40DBStockSupply**](Oi40DbStockSupplyRepositoryApi.md#findByCodeOI40DBStockSupply) | **GET** /integration/OI40DBStockSupply/byCode/{code} | findByCode
[**findByCodesListOI40DBStockSupply**](Oi40DbStockSupplyRepositoryApi.md#findByCodesListOI40DBStockSupply) | **POST** /integration/OI40DBStockSupply/findByCodes | findByCodes
[**findByQbeListOI40DBStockSupply**](Oi40DbStockSupplyRepositoryApi.md#findByQbeListOI40DBStockSupply) | **POST** /integration/OI40DBStockSupply/findByQbe | findByQbe
[**findByQbePagedPageOI40DBStockSupply**](Oi40DbStockSupplyRepositoryApi.md#findByQbePagedPageOI40DBStockSupply) | **POST** /integration/OI40DBStockSupply/findByQbePaged | findByQbePaged
[**updateListOI40DBStockSupply**](Oi40DbStockSupplyRepositoryApi.md#updateListOI40DBStockSupply) | **POST** /integration/OI40DBStockSupply/update | update
[**updateSingleOI40DBStockSupply**](Oi40DbStockSupplyRepositoryApi.md#updateSingleOI40DBStockSupply) | **POST** /integration/OI40DBStockSupply/updateSingle | updateSingle


<a name="deleteByCodeVoid27"></a>
# **deleteByCodeVoid27**
> deleteByCodeVoid27(code)

deleteByCode

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbStockSupplyRepositoryApi;


Oi40DbStockSupplyRepositoryApi apiInstance = new Oi40DbStockSupplyRepositoryApi();
String code = "code_example"; // String | code
try {
    apiInstance.deleteByCodeVoid27(code);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbStockSupplyRepositoryApi#deleteByCodeVoid27");
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

<a name="deleteByCodesVoid27"></a>
# **deleteByCodesVoid27**
> deleteByCodesVoid27(codes)

deleteByCodes

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbStockSupplyRepositoryApi;


Oi40DbStockSupplyRepositoryApi apiInstance = new Oi40DbStockSupplyRepositoryApi();
List<String> codes = Arrays.asList(new List<String>()); // List<String> | codes
try {
    apiInstance.deleteByCodesVoid27(codes);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbStockSupplyRepositoryApi#deleteByCodesVoid27");
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

<a name="doAutocompletePageOI40DBStockSupply"></a>
# **doAutocompletePageOI40DBStockSupply**
> PageOI40DBStockSupply doAutocompletePageOI40DBStockSupply(autoCompleteData)

doAutocomplete

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbStockSupplyRepositoryApi;


Oi40DbStockSupplyRepositoryApi apiInstance = new Oi40DbStockSupplyRepositoryApi();
AutoCompleteData autoCompleteData = new AutoCompleteData(); // AutoCompleteData | autoCompleteData
try {
    PageOI40DBStockSupply result = apiInstance.doAutocompletePageOI40DBStockSupply(autoCompleteData);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbStockSupplyRepositoryApi#doAutocompletePageOI40DBStockSupply");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **autoCompleteData** | [**AutoCompleteData**](AutoCompleteData.md)| autoCompleteData |

### Return type

[**PageOI40DBStockSupply**](PageOI40DBStockSupply.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: */*

<a name="doLookupPageOI40DBStockSupply"></a>
# **doLookupPageOI40DBStockSupply**
> PageOI40DBStockSupply doLookupPageOI40DBStockSupply(lookup)

doLookup

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbStockSupplyRepositoryApi;


Oi40DbStockSupplyRepositoryApi apiInstance = new Oi40DbStockSupplyRepositoryApi();
LookupData lookup = new LookupData(); // LookupData | lookup
try {
    PageOI40DBStockSupply result = apiInstance.doLookupPageOI40DBStockSupply(lookup);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbStockSupplyRepositoryApi#doLookupPageOI40DBStockSupply");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **lookup** | [**LookupData**](LookupData.md)| lookup |

### Return type

[**PageOI40DBStockSupply**](PageOI40DBStockSupply.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: */*

<a name="findAllListOI40DBStockSupply"></a>
# **findAllListOI40DBStockSupply**
> List&lt;OI40DBStockSupply&gt; findAllListOI40DBStockSupply()

findAll

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbStockSupplyRepositoryApi;


Oi40DbStockSupplyRepositoryApi apiInstance = new Oi40DbStockSupplyRepositoryApi();
try {
    List<OI40DBStockSupply> result = apiInstance.findAllListOI40DBStockSupply();
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbStockSupplyRepositoryApi#findAllListOI40DBStockSupply");
    e.printStackTrace();
}
```

### Parameters
This endpoint does not need any parameter.

### Return type

[**List&lt;OI40DBStockSupply&gt;**](OI40DBStockSupply.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="findAllPageOI40DBStockSupply"></a>
# **findAllPageOI40DBStockSupply**
> PageOI40DBStockSupply findAllPageOI40DBStockSupply(p)

findAll

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbStockSupplyRepositoryApi;


Oi40DbStockSupplyRepositoryApi apiInstance = new Oi40DbStockSupplyRepositoryApi();
PageInfo p = new PageInfo(); // PageInfo | p
try {
    PageOI40DBStockSupply result = apiInstance.findAllPageOI40DBStockSupply(p);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbStockSupplyRepositoryApi#findAllPageOI40DBStockSupply");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **p** | [**PageInfo**](PageInfo.md)| p |

### Return type

[**PageOI40DBStockSupply**](PageOI40DBStockSupply.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="findByAfterIntegrationTsListOI40DBStockSupply"></a>
# **findByAfterIntegrationTsListOI40DBStockSupply**
> List&lt;OI40DBStockSupply&gt; findByAfterIntegrationTsListOI40DBStockSupply(ts)

findByAfterIntegrationTs

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbStockSupplyRepositoryApi;


Oi40DbStockSupplyRepositoryApi apiInstance = new Oi40DbStockSupplyRepositoryApi();
String ts = "ts_example"; // String | ts
try {
    List<OI40DBStockSupply> result = apiInstance.findByAfterIntegrationTsListOI40DBStockSupply(ts);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbStockSupplyRepositoryApi#findByAfterIntegrationTsListOI40DBStockSupply");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **ts** | **String**| ts |

### Return type

[**List&lt;OI40DBStockSupply&gt;**](OI40DBStockSupply.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*

<a name="findByAfterModifiedTimestampListOI40DBStockSupply"></a>
# **findByAfterModifiedTimestampListOI40DBStockSupply**
> List&lt;OI40DBStockSupply&gt; findByAfterModifiedTimestampListOI40DBStockSupply(ts)

findByAfterModifiedTimestamp

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbStockSupplyRepositoryApi;


Oi40DbStockSupplyRepositoryApi apiInstance = new Oi40DbStockSupplyRepositoryApi();
String ts = "ts_example"; // String | ts
try {
    List<OI40DBStockSupply> result = apiInstance.findByAfterModifiedTimestampListOI40DBStockSupply(ts);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbStockSupplyRepositoryApi#findByAfterModifiedTimestampListOI40DBStockSupply");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **ts** | **String**| ts |

### Return type

[**List&lt;OI40DBStockSupply&gt;**](OI40DBStockSupply.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*

<a name="findByCodeOI40DBStockSupply"></a>
# **findByCodeOI40DBStockSupply**
> OI40DBStockSupply findByCodeOI40DBStockSupply(code)

findByCode

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbStockSupplyRepositoryApi;


Oi40DbStockSupplyRepositoryApi apiInstance = new Oi40DbStockSupplyRepositoryApi();
String code = "code_example"; // String | code
try {
    OI40DBStockSupply result = apiInstance.findByCodeOI40DBStockSupply(code);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbStockSupplyRepositoryApi#findByCodeOI40DBStockSupply");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **code** | **String**| code |

### Return type

[**OI40DBStockSupply**](OI40DBStockSupply.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="findByCodesListOI40DBStockSupply"></a>
# **findByCodesListOI40DBStockSupply**
> List&lt;OI40DBStockSupply&gt; findByCodesListOI40DBStockSupply(codes)

findByCodes

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbStockSupplyRepositoryApi;


Oi40DbStockSupplyRepositoryApi apiInstance = new Oi40DbStockSupplyRepositoryApi();
List<String> codes = Arrays.asList(new List<String>()); // List<String> | codes
try {
    List<OI40DBStockSupply> result = apiInstance.findByCodesListOI40DBStockSupply(codes);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbStockSupplyRepositoryApi#findByCodesListOI40DBStockSupply");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **codes** | **List&lt;String&gt;**| codes |

### Return type

[**List&lt;OI40DBStockSupply&gt;**](OI40DBStockSupply.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="findByQbeListOI40DBStockSupply"></a>
# **findByQbeListOI40DBStockSupply**
> List&lt;OI40DBStockSupply&gt; findByQbeListOI40DBStockSupply(qbe)

findByQbe

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbStockSupplyRepositoryApi;


Oi40DbStockSupplyRepositoryApi apiInstance = new Oi40DbStockSupplyRepositoryApi();
OI40DBStockSupply qbe = new OI40DBStockSupply(); // OI40DBStockSupply | qbe
try {
    List<OI40DBStockSupply> result = apiInstance.findByQbeListOI40DBStockSupply(qbe);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbStockSupplyRepositoryApi#findByQbeListOI40DBStockSupply");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **qbe** | [**OI40DBStockSupply**](OI40DBStockSupply.md)| qbe |

### Return type

[**List&lt;OI40DBStockSupply&gt;**](OI40DBStockSupply.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="findByQbePagedPageOI40DBStockSupply"></a>
# **findByQbePagedPageOI40DBStockSupply**
> PageOI40DBStockSupply findByQbePagedPageOI40DBStockSupply(qbe)

findByQbePaged

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbStockSupplyRepositoryApi;


Oi40DbStockSupplyRepositoryApi apiInstance = new Oi40DbStockSupplyRepositoryApi();
QbeSupportOI40DBStockSupply qbe = new QbeSupportOI40DBStockSupply(); // QbeSupportOI40DBStockSupply | qbe
try {
    PageOI40DBStockSupply result = apiInstance.findByQbePagedPageOI40DBStockSupply(qbe);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbStockSupplyRepositoryApi#findByQbePagedPageOI40DBStockSupply");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **qbe** | [**QbeSupportOI40DBStockSupply**](QbeSupportOI40DBStockSupply.md)| qbe |

### Return type

[**PageOI40DBStockSupply**](PageOI40DBStockSupply.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="updateListOI40DBStockSupply"></a>
# **updateListOI40DBStockSupply**
> List&lt;OI40DBStockSupply&gt; updateListOI40DBStockSupply(data)

update

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbStockSupplyRepositoryApi;


Oi40DbStockSupplyRepositoryApi apiInstance = new Oi40DbStockSupplyRepositoryApi();
List<OI40DBStockSupply> data = Arrays.asList(new OI40DBStockSupply()); // List<OI40DBStockSupply> | data
try {
    List<OI40DBStockSupply> result = apiInstance.updateListOI40DBStockSupply(data);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbStockSupplyRepositoryApi#updateListOI40DBStockSupply");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **data** | [**List&lt;OI40DBStockSupply&gt;**](OI40DBStockSupply.md)| data |

### Return type

[**List&lt;OI40DBStockSupply&gt;**](OI40DBStockSupply.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="updateSingleOI40DBStockSupply"></a>
# **updateSingleOI40DBStockSupply**
> OI40DBStockSupply updateSingleOI40DBStockSupply(data)

updateSingle

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbStockSupplyRepositoryApi;


Oi40DbStockSupplyRepositoryApi apiInstance = new Oi40DbStockSupplyRepositoryApi();
OI40DBStockSupply data = new OI40DBStockSupply(); // OI40DBStockSupply | data
try {
    OI40DBStockSupply result = apiInstance.updateSingleOI40DBStockSupply(data);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbStockSupplyRepositoryApi#updateSingleOI40DBStockSupply");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **data** | [**OI40DBStockSupply**](OI40DBStockSupply.md)| data |

### Return type

[**OI40DBStockSupply**](OI40DBStockSupply.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

