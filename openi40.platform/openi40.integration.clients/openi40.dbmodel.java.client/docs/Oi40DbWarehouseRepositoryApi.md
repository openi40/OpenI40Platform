# Oi40DbWarehouseRepositoryApi

All URIs are relative to *https://localhost:8082/openi40-integration*

Method | HTTP request | Description
------------- | ------------- | -------------
[**deleteByCodeVoid38**](Oi40DbWarehouseRepositoryApi.md#deleteByCodeVoid38) | **GET** /integration/OI40DBWarehouse/deleteByCode/{code} | deleteByCode
[**deleteByCodesVoid38**](Oi40DbWarehouseRepositoryApi.md#deleteByCodesVoid38) | **POST** /integration/OI40DBWarehouse/deleteByCodes | deleteByCodes
[**doAutocompletePageOI40DBWarehouse**](Oi40DbWarehouseRepositoryApi.md#doAutocompletePageOI40DBWarehouse) | **POST** /integration/OI40DBWarehouse/doAutocomplete | doAutocomplete
[**doLookupPageOI40DBWarehouse**](Oi40DbWarehouseRepositoryApi.md#doLookupPageOI40DBWarehouse) | **POST** /integration/OI40DBWarehouse/doLookup | doLookup
[**findAllListOI40DBWarehouse**](Oi40DbWarehouseRepositoryApi.md#findAllListOI40DBWarehouse) | **GET** /integration/OI40DBWarehouse | findAll
[**findAllPageOI40DBWarehouse**](Oi40DbWarehouseRepositoryApi.md#findAllPageOI40DBWarehouse) | **POST** /integration/OI40DBWarehouse/findAllPaged | findAll
[**findByAfterIntegrationTsListOI40DBWarehouse**](Oi40DbWarehouseRepositoryApi.md#findByAfterIntegrationTsListOI40DBWarehouse) | **GET** /integration/OI40DBWarehouse/integratedAfter/{ts} | findByAfterIntegrationTs
[**findByAfterModifiedTimestampListOI40DBWarehouse**](Oi40DbWarehouseRepositoryApi.md#findByAfterModifiedTimestampListOI40DBWarehouse) | **GET** /integration/OI40DBWarehouse/modifiedAfter/{ts} | findByAfterModifiedTimestamp
[**findByCodeOI40DBWarehouse**](Oi40DbWarehouseRepositoryApi.md#findByCodeOI40DBWarehouse) | **GET** /integration/OI40DBWarehouse/byCode/{code} | findByCode
[**findByCodesListOI40DBWarehouse**](Oi40DbWarehouseRepositoryApi.md#findByCodesListOI40DBWarehouse) | **POST** /integration/OI40DBWarehouse/findByCodes | findByCodes
[**findByQbeListOI40DBWarehouse**](Oi40DbWarehouseRepositoryApi.md#findByQbeListOI40DBWarehouse) | **POST** /integration/OI40DBWarehouse/findByQbe | findByQbe
[**findByQbePagedPageOI40DBWarehouse**](Oi40DbWarehouseRepositoryApi.md#findByQbePagedPageOI40DBWarehouse) | **POST** /integration/OI40DBWarehouse/findByQbePaged | findByQbePaged
[**updateListOI40DBWarehouse**](Oi40DbWarehouseRepositoryApi.md#updateListOI40DBWarehouse) | **POST** /integration/OI40DBWarehouse/update | update
[**updateSingleOI40DBWarehouse**](Oi40DbWarehouseRepositoryApi.md#updateSingleOI40DBWarehouse) | **POST** /integration/OI40DBWarehouse/updateSingle | updateSingle


<a name="deleteByCodeVoid38"></a>
# **deleteByCodeVoid38**
> deleteByCodeVoid38(code)

deleteByCode

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbWarehouseRepositoryApi;


Oi40DbWarehouseRepositoryApi apiInstance = new Oi40DbWarehouseRepositoryApi();
String code = "code_example"; // String | code
try {
    apiInstance.deleteByCodeVoid38(code);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbWarehouseRepositoryApi#deleteByCodeVoid38");
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

<a name="deleteByCodesVoid38"></a>
# **deleteByCodesVoid38**
> deleteByCodesVoid38(codes)

deleteByCodes

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbWarehouseRepositoryApi;


Oi40DbWarehouseRepositoryApi apiInstance = new Oi40DbWarehouseRepositoryApi();
List<String> codes = Arrays.asList(new List<String>()); // List<String> | codes
try {
    apiInstance.deleteByCodesVoid38(codes);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbWarehouseRepositoryApi#deleteByCodesVoid38");
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

<a name="doAutocompletePageOI40DBWarehouse"></a>
# **doAutocompletePageOI40DBWarehouse**
> PageOI40DBWarehouse doAutocompletePageOI40DBWarehouse(autoCompleteData)

doAutocomplete

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbWarehouseRepositoryApi;


Oi40DbWarehouseRepositoryApi apiInstance = new Oi40DbWarehouseRepositoryApi();
AutoCompleteData autoCompleteData = new AutoCompleteData(); // AutoCompleteData | autoCompleteData
try {
    PageOI40DBWarehouse result = apiInstance.doAutocompletePageOI40DBWarehouse(autoCompleteData);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbWarehouseRepositoryApi#doAutocompletePageOI40DBWarehouse");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **autoCompleteData** | [**AutoCompleteData**](AutoCompleteData.md)| autoCompleteData |

### Return type

[**PageOI40DBWarehouse**](PageOI40DBWarehouse.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: */*

<a name="doLookupPageOI40DBWarehouse"></a>
# **doLookupPageOI40DBWarehouse**
> PageOI40DBWarehouse doLookupPageOI40DBWarehouse(lookup)

doLookup

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbWarehouseRepositoryApi;


Oi40DbWarehouseRepositoryApi apiInstance = new Oi40DbWarehouseRepositoryApi();
LookupData lookup = new LookupData(); // LookupData | lookup
try {
    PageOI40DBWarehouse result = apiInstance.doLookupPageOI40DBWarehouse(lookup);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbWarehouseRepositoryApi#doLookupPageOI40DBWarehouse");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **lookup** | [**LookupData**](LookupData.md)| lookup |

### Return type

[**PageOI40DBWarehouse**](PageOI40DBWarehouse.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: */*

<a name="findAllListOI40DBWarehouse"></a>
# **findAllListOI40DBWarehouse**
> List&lt;OI40DBWarehouse&gt; findAllListOI40DBWarehouse()

findAll

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbWarehouseRepositoryApi;


Oi40DbWarehouseRepositoryApi apiInstance = new Oi40DbWarehouseRepositoryApi();
try {
    List<OI40DBWarehouse> result = apiInstance.findAllListOI40DBWarehouse();
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbWarehouseRepositoryApi#findAllListOI40DBWarehouse");
    e.printStackTrace();
}
```

### Parameters
This endpoint does not need any parameter.

### Return type

[**List&lt;OI40DBWarehouse&gt;**](OI40DBWarehouse.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="findAllPageOI40DBWarehouse"></a>
# **findAllPageOI40DBWarehouse**
> PageOI40DBWarehouse findAllPageOI40DBWarehouse(p)

findAll

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbWarehouseRepositoryApi;


Oi40DbWarehouseRepositoryApi apiInstance = new Oi40DbWarehouseRepositoryApi();
PageInfo p = new PageInfo(); // PageInfo | p
try {
    PageOI40DBWarehouse result = apiInstance.findAllPageOI40DBWarehouse(p);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbWarehouseRepositoryApi#findAllPageOI40DBWarehouse");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **p** | [**PageInfo**](PageInfo.md)| p |

### Return type

[**PageOI40DBWarehouse**](PageOI40DBWarehouse.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="findByAfterIntegrationTsListOI40DBWarehouse"></a>
# **findByAfterIntegrationTsListOI40DBWarehouse**
> List&lt;OI40DBWarehouse&gt; findByAfterIntegrationTsListOI40DBWarehouse(ts)

findByAfterIntegrationTs

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbWarehouseRepositoryApi;


Oi40DbWarehouseRepositoryApi apiInstance = new Oi40DbWarehouseRepositoryApi();
String ts = "ts_example"; // String | ts
try {
    List<OI40DBWarehouse> result = apiInstance.findByAfterIntegrationTsListOI40DBWarehouse(ts);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbWarehouseRepositoryApi#findByAfterIntegrationTsListOI40DBWarehouse");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **ts** | **String**| ts |

### Return type

[**List&lt;OI40DBWarehouse&gt;**](OI40DBWarehouse.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*

<a name="findByAfterModifiedTimestampListOI40DBWarehouse"></a>
# **findByAfterModifiedTimestampListOI40DBWarehouse**
> List&lt;OI40DBWarehouse&gt; findByAfterModifiedTimestampListOI40DBWarehouse(ts)

findByAfterModifiedTimestamp

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbWarehouseRepositoryApi;


Oi40DbWarehouseRepositoryApi apiInstance = new Oi40DbWarehouseRepositoryApi();
String ts = "ts_example"; // String | ts
try {
    List<OI40DBWarehouse> result = apiInstance.findByAfterModifiedTimestampListOI40DBWarehouse(ts);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbWarehouseRepositoryApi#findByAfterModifiedTimestampListOI40DBWarehouse");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **ts** | **String**| ts |

### Return type

[**List&lt;OI40DBWarehouse&gt;**](OI40DBWarehouse.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*

<a name="findByCodeOI40DBWarehouse"></a>
# **findByCodeOI40DBWarehouse**
> OI40DBWarehouse findByCodeOI40DBWarehouse(code)

findByCode

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbWarehouseRepositoryApi;


Oi40DbWarehouseRepositoryApi apiInstance = new Oi40DbWarehouseRepositoryApi();
String code = "code_example"; // String | code
try {
    OI40DBWarehouse result = apiInstance.findByCodeOI40DBWarehouse(code);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbWarehouseRepositoryApi#findByCodeOI40DBWarehouse");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **code** | **String**| code |

### Return type

[**OI40DBWarehouse**](OI40DBWarehouse.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="findByCodesListOI40DBWarehouse"></a>
# **findByCodesListOI40DBWarehouse**
> List&lt;OI40DBWarehouse&gt; findByCodesListOI40DBWarehouse(codes)

findByCodes

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbWarehouseRepositoryApi;


Oi40DbWarehouseRepositoryApi apiInstance = new Oi40DbWarehouseRepositoryApi();
List<String> codes = Arrays.asList(new List<String>()); // List<String> | codes
try {
    List<OI40DBWarehouse> result = apiInstance.findByCodesListOI40DBWarehouse(codes);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbWarehouseRepositoryApi#findByCodesListOI40DBWarehouse");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **codes** | **List&lt;String&gt;**| codes |

### Return type

[**List&lt;OI40DBWarehouse&gt;**](OI40DBWarehouse.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="findByQbeListOI40DBWarehouse"></a>
# **findByQbeListOI40DBWarehouse**
> List&lt;OI40DBWarehouse&gt; findByQbeListOI40DBWarehouse(qbe)

findByQbe

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbWarehouseRepositoryApi;


Oi40DbWarehouseRepositoryApi apiInstance = new Oi40DbWarehouseRepositoryApi();
OI40DBWarehouse qbe = new OI40DBWarehouse(); // OI40DBWarehouse | qbe
try {
    List<OI40DBWarehouse> result = apiInstance.findByQbeListOI40DBWarehouse(qbe);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbWarehouseRepositoryApi#findByQbeListOI40DBWarehouse");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **qbe** | [**OI40DBWarehouse**](OI40DBWarehouse.md)| qbe |

### Return type

[**List&lt;OI40DBWarehouse&gt;**](OI40DBWarehouse.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="findByQbePagedPageOI40DBWarehouse"></a>
# **findByQbePagedPageOI40DBWarehouse**
> PageOI40DBWarehouse findByQbePagedPageOI40DBWarehouse(qbe)

findByQbePaged

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbWarehouseRepositoryApi;


Oi40DbWarehouseRepositoryApi apiInstance = new Oi40DbWarehouseRepositoryApi();
QbeSupportOI40DBWarehouse qbe = new QbeSupportOI40DBWarehouse(); // QbeSupportOI40DBWarehouse | qbe
try {
    PageOI40DBWarehouse result = apiInstance.findByQbePagedPageOI40DBWarehouse(qbe);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbWarehouseRepositoryApi#findByQbePagedPageOI40DBWarehouse");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **qbe** | [**QbeSupportOI40DBWarehouse**](QbeSupportOI40DBWarehouse.md)| qbe |

### Return type

[**PageOI40DBWarehouse**](PageOI40DBWarehouse.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="updateListOI40DBWarehouse"></a>
# **updateListOI40DBWarehouse**
> List&lt;OI40DBWarehouse&gt; updateListOI40DBWarehouse(data)

update

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbWarehouseRepositoryApi;


Oi40DbWarehouseRepositoryApi apiInstance = new Oi40DbWarehouseRepositoryApi();
List<OI40DBWarehouse> data = Arrays.asList(new OI40DBWarehouse()); // List<OI40DBWarehouse> | data
try {
    List<OI40DBWarehouse> result = apiInstance.updateListOI40DBWarehouse(data);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbWarehouseRepositoryApi#updateListOI40DBWarehouse");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **data** | [**List&lt;OI40DBWarehouse&gt;**](OI40DBWarehouse.md)| data |

### Return type

[**List&lt;OI40DBWarehouse&gt;**](OI40DBWarehouse.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="updateSingleOI40DBWarehouse"></a>
# **updateSingleOI40DBWarehouse**
> OI40DBWarehouse updateSingleOI40DBWarehouse(data)

updateSingle

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbWarehouseRepositoryApi;


Oi40DbWarehouseRepositoryApi apiInstance = new Oi40DbWarehouseRepositoryApi();
OI40DBWarehouse data = new OI40DBWarehouse(); // OI40DBWarehouse | data
try {
    OI40DBWarehouse result = apiInstance.updateSingleOI40DBWarehouse(data);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbWarehouseRepositoryApi#updateSingleOI40DBWarehouse");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **data** | [**OI40DBWarehouse**](OI40DBWarehouse.md)| data |

### Return type

[**OI40DBWarehouse**](OI40DBWarehouse.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

