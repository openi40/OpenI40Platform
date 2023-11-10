# Oi40DbChangeOverMatrixItemRepositoryApi

All URIs are relative to *https://localhost:8083/openi40-backoffice*

Method | HTTP request | Description
------------- | ------------- | -------------
[**deleteByCodeVoid3**](Oi40DbChangeOverMatrixItemRepositoryApi.md#deleteByCodeVoid3) | **GET** /integration/OI40DBChangeOverMatrixItem/deleteByCode/{code} | deleteByCode
[**deleteByCodesVoid3**](Oi40DbChangeOverMatrixItemRepositoryApi.md#deleteByCodesVoid3) | **POST** /integration/OI40DBChangeOverMatrixItem/deleteByCodes | deleteByCodes
[**doAutocompletePageOI40DBChangeOverMatrixItem**](Oi40DbChangeOverMatrixItemRepositoryApi.md#doAutocompletePageOI40DBChangeOverMatrixItem) | **POST** /integration/OI40DBChangeOverMatrixItem/doAutocomplete | doAutocomplete
[**doLookupPageOI40DBChangeOverMatrixItem**](Oi40DbChangeOverMatrixItemRepositoryApi.md#doLookupPageOI40DBChangeOverMatrixItem) | **POST** /integration/OI40DBChangeOverMatrixItem/doLookup | doLookup
[**findAllListOI40DBChangeOverMatrixItem**](Oi40DbChangeOverMatrixItemRepositoryApi.md#findAllListOI40DBChangeOverMatrixItem) | **GET** /integration/OI40DBChangeOverMatrixItem | findAll
[**findAllPageOI40DBChangeOverMatrixItem**](Oi40DbChangeOverMatrixItemRepositoryApi.md#findAllPageOI40DBChangeOverMatrixItem) | **POST** /integration/OI40DBChangeOverMatrixItem/findAllPaged | findAll
[**findByAfterIntegrationTsListOI40DBChangeOverMatrixItem**](Oi40DbChangeOverMatrixItemRepositoryApi.md#findByAfterIntegrationTsListOI40DBChangeOverMatrixItem) | **GET** /integration/OI40DBChangeOverMatrixItem/integratedAfter/{ts} | findByAfterIntegrationTs
[**findByAfterModifiedTimestampListOI40DBChangeOverMatrixItem**](Oi40DbChangeOverMatrixItemRepositoryApi.md#findByAfterModifiedTimestampListOI40DBChangeOverMatrixItem) | **GET** /integration/OI40DBChangeOverMatrixItem/modifiedAfter/{ts} | findByAfterModifiedTimestamp
[**findByCodeOI40DBChangeOverMatrixItem**](Oi40DbChangeOverMatrixItemRepositoryApi.md#findByCodeOI40DBChangeOverMatrixItem) | **GET** /integration/OI40DBChangeOverMatrixItem/byCode/{code} | findByCode
[**findByCodesListOI40DBChangeOverMatrixItem**](Oi40DbChangeOverMatrixItemRepositoryApi.md#findByCodesListOI40DBChangeOverMatrixItem) | **POST** /integration/OI40DBChangeOverMatrixItem/findByCodes | findByCodes
[**findByQbeListOI40DBChangeOverMatrixItem**](Oi40DbChangeOverMatrixItemRepositoryApi.md#findByQbeListOI40DBChangeOverMatrixItem) | **POST** /integration/OI40DBChangeOverMatrixItem/findByQbe | findByQbe
[**findByQbePagedPageOI40DBChangeOverMatrixItem**](Oi40DbChangeOverMatrixItemRepositoryApi.md#findByQbePagedPageOI40DBChangeOverMatrixItem) | **POST** /integration/OI40DBChangeOverMatrixItem/findByQbePaged | findByQbePaged
[**updateListOI40DBChangeOverMatrixItem**](Oi40DbChangeOverMatrixItemRepositoryApi.md#updateListOI40DBChangeOverMatrixItem) | **POST** /integration/OI40DBChangeOverMatrixItem/update | update
[**updateSingleOI40DBChangeOverMatrixItem**](Oi40DbChangeOverMatrixItemRepositoryApi.md#updateSingleOI40DBChangeOverMatrixItem) | **POST** /integration/OI40DBChangeOverMatrixItem/updateSingle | updateSingle


<a name="deleteByCodeVoid3"></a>
# **deleteByCodeVoid3**
> deleteByCodeVoid3(code)

deleteByCode

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbChangeOverMatrixItemRepositoryApi;


Oi40DbChangeOverMatrixItemRepositoryApi apiInstance = new Oi40DbChangeOverMatrixItemRepositoryApi();
String code = "code_example"; // String | code
try {
    apiInstance.deleteByCodeVoid3(code);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbChangeOverMatrixItemRepositoryApi#deleteByCodeVoid3");
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

<a name="deleteByCodesVoid3"></a>
# **deleteByCodesVoid3**
> deleteByCodesVoid3(codes)

deleteByCodes

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbChangeOverMatrixItemRepositoryApi;


Oi40DbChangeOverMatrixItemRepositoryApi apiInstance = new Oi40DbChangeOverMatrixItemRepositoryApi();
List<String> codes = Arrays.asList(new List<String>()); // List<String> | codes
try {
    apiInstance.deleteByCodesVoid3(codes);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbChangeOverMatrixItemRepositoryApi#deleteByCodesVoid3");
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

<a name="doAutocompletePageOI40DBChangeOverMatrixItem"></a>
# **doAutocompletePageOI40DBChangeOverMatrixItem**
> PageOI40DBChangeOverMatrixItem doAutocompletePageOI40DBChangeOverMatrixItem(autoCompleteData)

doAutocomplete

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbChangeOverMatrixItemRepositoryApi;


Oi40DbChangeOverMatrixItemRepositoryApi apiInstance = new Oi40DbChangeOverMatrixItemRepositoryApi();
AutoCompleteData autoCompleteData = new AutoCompleteData(); // AutoCompleteData | autoCompleteData
try {
    PageOI40DBChangeOverMatrixItem result = apiInstance.doAutocompletePageOI40DBChangeOverMatrixItem(autoCompleteData);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbChangeOverMatrixItemRepositoryApi#doAutocompletePageOI40DBChangeOverMatrixItem");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **autoCompleteData** | [**AutoCompleteData**](AutoCompleteData.md)| autoCompleteData |

### Return type

[**PageOI40DBChangeOverMatrixItem**](PageOI40DBChangeOverMatrixItem.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: */*

<a name="doLookupPageOI40DBChangeOverMatrixItem"></a>
# **doLookupPageOI40DBChangeOverMatrixItem**
> PageOI40DBChangeOverMatrixItem doLookupPageOI40DBChangeOverMatrixItem(lookup)

doLookup

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbChangeOverMatrixItemRepositoryApi;


Oi40DbChangeOverMatrixItemRepositoryApi apiInstance = new Oi40DbChangeOverMatrixItemRepositoryApi();
LookupData lookup = new LookupData(); // LookupData | lookup
try {
    PageOI40DBChangeOverMatrixItem result = apiInstance.doLookupPageOI40DBChangeOverMatrixItem(lookup);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbChangeOverMatrixItemRepositoryApi#doLookupPageOI40DBChangeOverMatrixItem");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **lookup** | [**LookupData**](LookupData.md)| lookup |

### Return type

[**PageOI40DBChangeOverMatrixItem**](PageOI40DBChangeOverMatrixItem.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: */*

<a name="findAllListOI40DBChangeOverMatrixItem"></a>
# **findAllListOI40DBChangeOverMatrixItem**
> List&lt;OI40DBChangeOverMatrixItem&gt; findAllListOI40DBChangeOverMatrixItem()

findAll

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbChangeOverMatrixItemRepositoryApi;


Oi40DbChangeOverMatrixItemRepositoryApi apiInstance = new Oi40DbChangeOverMatrixItemRepositoryApi();
try {
    List<OI40DBChangeOverMatrixItem> result = apiInstance.findAllListOI40DBChangeOverMatrixItem();
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbChangeOverMatrixItemRepositoryApi#findAllListOI40DBChangeOverMatrixItem");
    e.printStackTrace();
}
```

### Parameters
This endpoint does not need any parameter.

### Return type

[**List&lt;OI40DBChangeOverMatrixItem&gt;**](OI40DBChangeOverMatrixItem.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="findAllPageOI40DBChangeOverMatrixItem"></a>
# **findAllPageOI40DBChangeOverMatrixItem**
> PageOI40DBChangeOverMatrixItem findAllPageOI40DBChangeOverMatrixItem(p)

findAll

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbChangeOverMatrixItemRepositoryApi;


Oi40DbChangeOverMatrixItemRepositoryApi apiInstance = new Oi40DbChangeOverMatrixItemRepositoryApi();
PageInfo p = new PageInfo(); // PageInfo | p
try {
    PageOI40DBChangeOverMatrixItem result = apiInstance.findAllPageOI40DBChangeOverMatrixItem(p);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbChangeOverMatrixItemRepositoryApi#findAllPageOI40DBChangeOverMatrixItem");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **p** | [**PageInfo**](PageInfo.md)| p |

### Return type

[**PageOI40DBChangeOverMatrixItem**](PageOI40DBChangeOverMatrixItem.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="findByAfterIntegrationTsListOI40DBChangeOverMatrixItem"></a>
# **findByAfterIntegrationTsListOI40DBChangeOverMatrixItem**
> List&lt;OI40DBChangeOverMatrixItem&gt; findByAfterIntegrationTsListOI40DBChangeOverMatrixItem(ts)

findByAfterIntegrationTs

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbChangeOverMatrixItemRepositoryApi;


Oi40DbChangeOverMatrixItemRepositoryApi apiInstance = new Oi40DbChangeOverMatrixItemRepositoryApi();
String ts = "ts_example"; // String | ts
try {
    List<OI40DBChangeOverMatrixItem> result = apiInstance.findByAfterIntegrationTsListOI40DBChangeOverMatrixItem(ts);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbChangeOverMatrixItemRepositoryApi#findByAfterIntegrationTsListOI40DBChangeOverMatrixItem");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **ts** | **String**| ts |

### Return type

[**List&lt;OI40DBChangeOverMatrixItem&gt;**](OI40DBChangeOverMatrixItem.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*

<a name="findByAfterModifiedTimestampListOI40DBChangeOverMatrixItem"></a>
# **findByAfterModifiedTimestampListOI40DBChangeOverMatrixItem**
> List&lt;OI40DBChangeOverMatrixItem&gt; findByAfterModifiedTimestampListOI40DBChangeOverMatrixItem(ts)

findByAfterModifiedTimestamp

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbChangeOverMatrixItemRepositoryApi;


Oi40DbChangeOverMatrixItemRepositoryApi apiInstance = new Oi40DbChangeOverMatrixItemRepositoryApi();
String ts = "ts_example"; // String | ts
try {
    List<OI40DBChangeOverMatrixItem> result = apiInstance.findByAfterModifiedTimestampListOI40DBChangeOverMatrixItem(ts);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbChangeOverMatrixItemRepositoryApi#findByAfterModifiedTimestampListOI40DBChangeOverMatrixItem");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **ts** | **String**| ts |

### Return type

[**List&lt;OI40DBChangeOverMatrixItem&gt;**](OI40DBChangeOverMatrixItem.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*

<a name="findByCodeOI40DBChangeOverMatrixItem"></a>
# **findByCodeOI40DBChangeOverMatrixItem**
> OI40DBChangeOverMatrixItem findByCodeOI40DBChangeOverMatrixItem(code)

findByCode

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbChangeOverMatrixItemRepositoryApi;


Oi40DbChangeOverMatrixItemRepositoryApi apiInstance = new Oi40DbChangeOverMatrixItemRepositoryApi();
String code = "code_example"; // String | code
try {
    OI40DBChangeOverMatrixItem result = apiInstance.findByCodeOI40DBChangeOverMatrixItem(code);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbChangeOverMatrixItemRepositoryApi#findByCodeOI40DBChangeOverMatrixItem");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **code** | **String**| code |

### Return type

[**OI40DBChangeOverMatrixItem**](OI40DBChangeOverMatrixItem.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="findByCodesListOI40DBChangeOverMatrixItem"></a>
# **findByCodesListOI40DBChangeOverMatrixItem**
> List&lt;OI40DBChangeOverMatrixItem&gt; findByCodesListOI40DBChangeOverMatrixItem(codes)

findByCodes

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbChangeOverMatrixItemRepositoryApi;


Oi40DbChangeOverMatrixItemRepositoryApi apiInstance = new Oi40DbChangeOverMatrixItemRepositoryApi();
List<String> codes = Arrays.asList(new List<String>()); // List<String> | codes
try {
    List<OI40DBChangeOverMatrixItem> result = apiInstance.findByCodesListOI40DBChangeOverMatrixItem(codes);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbChangeOverMatrixItemRepositoryApi#findByCodesListOI40DBChangeOverMatrixItem");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **codes** | **List&lt;String&gt;**| codes |

### Return type

[**List&lt;OI40DBChangeOverMatrixItem&gt;**](OI40DBChangeOverMatrixItem.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="findByQbeListOI40DBChangeOverMatrixItem"></a>
# **findByQbeListOI40DBChangeOverMatrixItem**
> List&lt;OI40DBChangeOverMatrixItem&gt; findByQbeListOI40DBChangeOverMatrixItem(qbe)

findByQbe

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbChangeOverMatrixItemRepositoryApi;


Oi40DbChangeOverMatrixItemRepositoryApi apiInstance = new Oi40DbChangeOverMatrixItemRepositoryApi();
OI40DBChangeOverMatrixItem qbe = new OI40DBChangeOverMatrixItem(); // OI40DBChangeOverMatrixItem | qbe
try {
    List<OI40DBChangeOverMatrixItem> result = apiInstance.findByQbeListOI40DBChangeOverMatrixItem(qbe);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbChangeOverMatrixItemRepositoryApi#findByQbeListOI40DBChangeOverMatrixItem");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **qbe** | [**OI40DBChangeOverMatrixItem**](OI40DBChangeOverMatrixItem.md)| qbe |

### Return type

[**List&lt;OI40DBChangeOverMatrixItem&gt;**](OI40DBChangeOverMatrixItem.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="findByQbePagedPageOI40DBChangeOverMatrixItem"></a>
# **findByQbePagedPageOI40DBChangeOverMatrixItem**
> PageOI40DBChangeOverMatrixItem findByQbePagedPageOI40DBChangeOverMatrixItem(qbe)

findByQbePaged

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbChangeOverMatrixItemRepositoryApi;


Oi40DbChangeOverMatrixItemRepositoryApi apiInstance = new Oi40DbChangeOverMatrixItemRepositoryApi();
QbeSupportOI40DBChangeOverMatrixItem qbe = new QbeSupportOI40DBChangeOverMatrixItem(); // QbeSupportOI40DBChangeOverMatrixItem | qbe
try {
    PageOI40DBChangeOverMatrixItem result = apiInstance.findByQbePagedPageOI40DBChangeOverMatrixItem(qbe);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbChangeOverMatrixItemRepositoryApi#findByQbePagedPageOI40DBChangeOverMatrixItem");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **qbe** | [**QbeSupportOI40DBChangeOverMatrixItem**](QbeSupportOI40DBChangeOverMatrixItem.md)| qbe |

### Return type

[**PageOI40DBChangeOverMatrixItem**](PageOI40DBChangeOverMatrixItem.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="updateListOI40DBChangeOverMatrixItem"></a>
# **updateListOI40DBChangeOverMatrixItem**
> List&lt;OI40DBChangeOverMatrixItem&gt; updateListOI40DBChangeOverMatrixItem(data)

update

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbChangeOverMatrixItemRepositoryApi;


Oi40DbChangeOverMatrixItemRepositoryApi apiInstance = new Oi40DbChangeOverMatrixItemRepositoryApi();
List<OI40DBChangeOverMatrixItem> data = Arrays.asList(new OI40DBChangeOverMatrixItem()); // List<OI40DBChangeOverMatrixItem> | data
try {
    List<OI40DBChangeOverMatrixItem> result = apiInstance.updateListOI40DBChangeOverMatrixItem(data);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbChangeOverMatrixItemRepositoryApi#updateListOI40DBChangeOverMatrixItem");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **data** | [**List&lt;OI40DBChangeOverMatrixItem&gt;**](OI40DBChangeOverMatrixItem.md)| data |

### Return type

[**List&lt;OI40DBChangeOverMatrixItem&gt;**](OI40DBChangeOverMatrixItem.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="updateSingleOI40DBChangeOverMatrixItem"></a>
# **updateSingleOI40DBChangeOverMatrixItem**
> OI40DBChangeOverMatrixItem updateSingleOI40DBChangeOverMatrixItem(data)

updateSingle

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbChangeOverMatrixItemRepositoryApi;


Oi40DbChangeOverMatrixItemRepositoryApi apiInstance = new Oi40DbChangeOverMatrixItemRepositoryApi();
OI40DBChangeOverMatrixItem data = new OI40DBChangeOverMatrixItem(); // OI40DBChangeOverMatrixItem | data
try {
    OI40DBChangeOverMatrixItem result = apiInstance.updateSingleOI40DBChangeOverMatrixItem(data);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbChangeOverMatrixItemRepositoryApi#updateSingleOI40DBChangeOverMatrixItem");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **data** | [**OI40DBChangeOverMatrixItem**](OI40DBChangeOverMatrixItem.md)| data |

### Return type

[**OI40DBChangeOverMatrixItem**](OI40DBChangeOverMatrixItem.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

