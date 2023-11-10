# Oi40DbCoProductItemRepositoryApi

All URIs are relative to *https://localhost:8082/openi40-integration*

Method | HTTP request | Description
------------- | ------------- | -------------
[**deleteByCodeVoid4**](Oi40DbCoProductItemRepositoryApi.md#deleteByCodeVoid4) | **GET** /integration/OI40DBCoProductItem/deleteByCode/{code} | deleteByCode
[**deleteByCodesVoid4**](Oi40DbCoProductItemRepositoryApi.md#deleteByCodesVoid4) | **POST** /integration/OI40DBCoProductItem/deleteByCodes | deleteByCodes
[**doAutocompletePageOI40DBCoProductItem**](Oi40DbCoProductItemRepositoryApi.md#doAutocompletePageOI40DBCoProductItem) | **POST** /integration/OI40DBCoProductItem/doAutocomplete | doAutocomplete
[**doLookupPageOI40DBCoProductItem**](Oi40DbCoProductItemRepositoryApi.md#doLookupPageOI40DBCoProductItem) | **POST** /integration/OI40DBCoProductItem/doLookup | doLookup
[**findAllListOI40DBCoProductItem**](Oi40DbCoProductItemRepositoryApi.md#findAllListOI40DBCoProductItem) | **GET** /integration/OI40DBCoProductItem | findAll
[**findAllPageOI40DBCoProductItem**](Oi40DbCoProductItemRepositoryApi.md#findAllPageOI40DBCoProductItem) | **POST** /integration/OI40DBCoProductItem/findAllPaged | findAll
[**findByAfterIntegrationTsListOI40DBCoProductItem**](Oi40DbCoProductItemRepositoryApi.md#findByAfterIntegrationTsListOI40DBCoProductItem) | **GET** /integration/OI40DBCoProductItem/integratedAfter/{ts} | findByAfterIntegrationTs
[**findByAfterModifiedTimestampListOI40DBCoProductItem**](Oi40DbCoProductItemRepositoryApi.md#findByAfterModifiedTimestampListOI40DBCoProductItem) | **GET** /integration/OI40DBCoProductItem/modifiedAfter/{ts} | findByAfterModifiedTimestamp
[**findByCodeOI40DBCoProductItem**](Oi40DbCoProductItemRepositoryApi.md#findByCodeOI40DBCoProductItem) | **GET** /integration/OI40DBCoProductItem/byCode/{code} | findByCode
[**findByCodesListOI40DBCoProductItem**](Oi40DbCoProductItemRepositoryApi.md#findByCodesListOI40DBCoProductItem) | **POST** /integration/OI40DBCoProductItem/findByCodes | findByCodes
[**findByQbeListOI40DBCoProductItem**](Oi40DbCoProductItemRepositoryApi.md#findByQbeListOI40DBCoProductItem) | **POST** /integration/OI40DBCoProductItem/findByQbe | findByQbe
[**findByQbePagedPageOI40DBCoProductItem**](Oi40DbCoProductItemRepositoryApi.md#findByQbePagedPageOI40DBCoProductItem) | **POST** /integration/OI40DBCoProductItem/findByQbePaged | findByQbePaged
[**updateListOI40DBCoProductItem**](Oi40DbCoProductItemRepositoryApi.md#updateListOI40DBCoProductItem) | **POST** /integration/OI40DBCoProductItem/update | update
[**updateSingleOI40DBCoProductItem**](Oi40DbCoProductItemRepositoryApi.md#updateSingleOI40DBCoProductItem) | **POST** /integration/OI40DBCoProductItem/updateSingle | updateSingle


<a name="deleteByCodeVoid4"></a>
# **deleteByCodeVoid4**
> deleteByCodeVoid4(code)

deleteByCode

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbCoProductItemRepositoryApi;


Oi40DbCoProductItemRepositoryApi apiInstance = new Oi40DbCoProductItemRepositoryApi();
String code = "code_example"; // String | code
try {
    apiInstance.deleteByCodeVoid4(code);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbCoProductItemRepositoryApi#deleteByCodeVoid4");
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

<a name="deleteByCodesVoid4"></a>
# **deleteByCodesVoid4**
> deleteByCodesVoid4(codes)

deleteByCodes

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbCoProductItemRepositoryApi;


Oi40DbCoProductItemRepositoryApi apiInstance = new Oi40DbCoProductItemRepositoryApi();
List<String> codes = Arrays.asList(new List<String>()); // List<String> | codes
try {
    apiInstance.deleteByCodesVoid4(codes);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbCoProductItemRepositoryApi#deleteByCodesVoid4");
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

<a name="doAutocompletePageOI40DBCoProductItem"></a>
# **doAutocompletePageOI40DBCoProductItem**
> PageOI40DBCoProductItem doAutocompletePageOI40DBCoProductItem(autoCompleteData)

doAutocomplete

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbCoProductItemRepositoryApi;


Oi40DbCoProductItemRepositoryApi apiInstance = new Oi40DbCoProductItemRepositoryApi();
AutoCompleteData autoCompleteData = new AutoCompleteData(); // AutoCompleteData | autoCompleteData
try {
    PageOI40DBCoProductItem result = apiInstance.doAutocompletePageOI40DBCoProductItem(autoCompleteData);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbCoProductItemRepositoryApi#doAutocompletePageOI40DBCoProductItem");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **autoCompleteData** | [**AutoCompleteData**](AutoCompleteData.md)| autoCompleteData |

### Return type

[**PageOI40DBCoProductItem**](PageOI40DBCoProductItem.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: */*

<a name="doLookupPageOI40DBCoProductItem"></a>
# **doLookupPageOI40DBCoProductItem**
> PageOI40DBCoProductItem doLookupPageOI40DBCoProductItem(lookup)

doLookup

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbCoProductItemRepositoryApi;


Oi40DbCoProductItemRepositoryApi apiInstance = new Oi40DbCoProductItemRepositoryApi();
LookupData lookup = new LookupData(); // LookupData | lookup
try {
    PageOI40DBCoProductItem result = apiInstance.doLookupPageOI40DBCoProductItem(lookup);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbCoProductItemRepositoryApi#doLookupPageOI40DBCoProductItem");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **lookup** | [**LookupData**](LookupData.md)| lookup |

### Return type

[**PageOI40DBCoProductItem**](PageOI40DBCoProductItem.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: */*

<a name="findAllListOI40DBCoProductItem"></a>
# **findAllListOI40DBCoProductItem**
> List&lt;OI40DBCoProductItem&gt; findAllListOI40DBCoProductItem()

findAll

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbCoProductItemRepositoryApi;


Oi40DbCoProductItemRepositoryApi apiInstance = new Oi40DbCoProductItemRepositoryApi();
try {
    List<OI40DBCoProductItem> result = apiInstance.findAllListOI40DBCoProductItem();
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbCoProductItemRepositoryApi#findAllListOI40DBCoProductItem");
    e.printStackTrace();
}
```

### Parameters
This endpoint does not need any parameter.

### Return type

[**List&lt;OI40DBCoProductItem&gt;**](OI40DBCoProductItem.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="findAllPageOI40DBCoProductItem"></a>
# **findAllPageOI40DBCoProductItem**
> PageOI40DBCoProductItem findAllPageOI40DBCoProductItem(p)

findAll

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbCoProductItemRepositoryApi;


Oi40DbCoProductItemRepositoryApi apiInstance = new Oi40DbCoProductItemRepositoryApi();
PageInfo p = new PageInfo(); // PageInfo | p
try {
    PageOI40DBCoProductItem result = apiInstance.findAllPageOI40DBCoProductItem(p);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbCoProductItemRepositoryApi#findAllPageOI40DBCoProductItem");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **p** | [**PageInfo**](PageInfo.md)| p |

### Return type

[**PageOI40DBCoProductItem**](PageOI40DBCoProductItem.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="findByAfterIntegrationTsListOI40DBCoProductItem"></a>
# **findByAfterIntegrationTsListOI40DBCoProductItem**
> List&lt;OI40DBCoProductItem&gt; findByAfterIntegrationTsListOI40DBCoProductItem(ts)

findByAfterIntegrationTs

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbCoProductItemRepositoryApi;


Oi40DbCoProductItemRepositoryApi apiInstance = new Oi40DbCoProductItemRepositoryApi();
String ts = "ts_example"; // String | ts
try {
    List<OI40DBCoProductItem> result = apiInstance.findByAfterIntegrationTsListOI40DBCoProductItem(ts);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbCoProductItemRepositoryApi#findByAfterIntegrationTsListOI40DBCoProductItem");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **ts** | **String**| ts |

### Return type

[**List&lt;OI40DBCoProductItem&gt;**](OI40DBCoProductItem.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*

<a name="findByAfterModifiedTimestampListOI40DBCoProductItem"></a>
# **findByAfterModifiedTimestampListOI40DBCoProductItem**
> List&lt;OI40DBCoProductItem&gt; findByAfterModifiedTimestampListOI40DBCoProductItem(ts)

findByAfterModifiedTimestamp

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbCoProductItemRepositoryApi;


Oi40DbCoProductItemRepositoryApi apiInstance = new Oi40DbCoProductItemRepositoryApi();
String ts = "ts_example"; // String | ts
try {
    List<OI40DBCoProductItem> result = apiInstance.findByAfterModifiedTimestampListOI40DBCoProductItem(ts);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbCoProductItemRepositoryApi#findByAfterModifiedTimestampListOI40DBCoProductItem");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **ts** | **String**| ts |

### Return type

[**List&lt;OI40DBCoProductItem&gt;**](OI40DBCoProductItem.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*

<a name="findByCodeOI40DBCoProductItem"></a>
# **findByCodeOI40DBCoProductItem**
> OI40DBCoProductItem findByCodeOI40DBCoProductItem(code)

findByCode

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbCoProductItemRepositoryApi;


Oi40DbCoProductItemRepositoryApi apiInstance = new Oi40DbCoProductItemRepositoryApi();
String code = "code_example"; // String | code
try {
    OI40DBCoProductItem result = apiInstance.findByCodeOI40DBCoProductItem(code);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbCoProductItemRepositoryApi#findByCodeOI40DBCoProductItem");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **code** | **String**| code |

### Return type

[**OI40DBCoProductItem**](OI40DBCoProductItem.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="findByCodesListOI40DBCoProductItem"></a>
# **findByCodesListOI40DBCoProductItem**
> List&lt;OI40DBCoProductItem&gt; findByCodesListOI40DBCoProductItem(codes)

findByCodes

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbCoProductItemRepositoryApi;


Oi40DbCoProductItemRepositoryApi apiInstance = new Oi40DbCoProductItemRepositoryApi();
List<String> codes = Arrays.asList(new List<String>()); // List<String> | codes
try {
    List<OI40DBCoProductItem> result = apiInstance.findByCodesListOI40DBCoProductItem(codes);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbCoProductItemRepositoryApi#findByCodesListOI40DBCoProductItem");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **codes** | **List&lt;String&gt;**| codes |

### Return type

[**List&lt;OI40DBCoProductItem&gt;**](OI40DBCoProductItem.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="findByQbeListOI40DBCoProductItem"></a>
# **findByQbeListOI40DBCoProductItem**
> List&lt;OI40DBCoProductItem&gt; findByQbeListOI40DBCoProductItem(qbe)

findByQbe

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbCoProductItemRepositoryApi;


Oi40DbCoProductItemRepositoryApi apiInstance = new Oi40DbCoProductItemRepositoryApi();
OI40DBCoProductItem qbe = new OI40DBCoProductItem(); // OI40DBCoProductItem | qbe
try {
    List<OI40DBCoProductItem> result = apiInstance.findByQbeListOI40DBCoProductItem(qbe);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbCoProductItemRepositoryApi#findByQbeListOI40DBCoProductItem");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **qbe** | [**OI40DBCoProductItem**](OI40DBCoProductItem.md)| qbe |

### Return type

[**List&lt;OI40DBCoProductItem&gt;**](OI40DBCoProductItem.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="findByQbePagedPageOI40DBCoProductItem"></a>
# **findByQbePagedPageOI40DBCoProductItem**
> PageOI40DBCoProductItem findByQbePagedPageOI40DBCoProductItem(qbe)

findByQbePaged

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbCoProductItemRepositoryApi;


Oi40DbCoProductItemRepositoryApi apiInstance = new Oi40DbCoProductItemRepositoryApi();
QbeSupportOI40DBCoProductItem qbe = new QbeSupportOI40DBCoProductItem(); // QbeSupportOI40DBCoProductItem | qbe
try {
    PageOI40DBCoProductItem result = apiInstance.findByQbePagedPageOI40DBCoProductItem(qbe);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbCoProductItemRepositoryApi#findByQbePagedPageOI40DBCoProductItem");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **qbe** | [**QbeSupportOI40DBCoProductItem**](QbeSupportOI40DBCoProductItem.md)| qbe |

### Return type

[**PageOI40DBCoProductItem**](PageOI40DBCoProductItem.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="updateListOI40DBCoProductItem"></a>
# **updateListOI40DBCoProductItem**
> List&lt;OI40DBCoProductItem&gt; updateListOI40DBCoProductItem(data)

update

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbCoProductItemRepositoryApi;


Oi40DbCoProductItemRepositoryApi apiInstance = new Oi40DbCoProductItemRepositoryApi();
List<OI40DBCoProductItem> data = Arrays.asList(new OI40DBCoProductItem()); // List<OI40DBCoProductItem> | data
try {
    List<OI40DBCoProductItem> result = apiInstance.updateListOI40DBCoProductItem(data);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbCoProductItemRepositoryApi#updateListOI40DBCoProductItem");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **data** | [**List&lt;OI40DBCoProductItem&gt;**](OI40DBCoProductItem.md)| data |

### Return type

[**List&lt;OI40DBCoProductItem&gt;**](OI40DBCoProductItem.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="updateSingleOI40DBCoProductItem"></a>
# **updateSingleOI40DBCoProductItem**
> OI40DBCoProductItem updateSingleOI40DBCoProductItem(data)

updateSingle

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbCoProductItemRepositoryApi;


Oi40DbCoProductItemRepositoryApi apiInstance = new Oi40DbCoProductItemRepositoryApi();
OI40DBCoProductItem data = new OI40DBCoProductItem(); // OI40DBCoProductItem | data
try {
    OI40DBCoProductItem result = apiInstance.updateSingleOI40DBCoProductItem(data);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbCoProductItemRepositoryApi#updateSingleOI40DBCoProductItem");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **data** | [**OI40DBCoProductItem**](OI40DBCoProductItem.md)| data |

### Return type

[**OI40DBCoProductItem**](OI40DBCoProductItem.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

