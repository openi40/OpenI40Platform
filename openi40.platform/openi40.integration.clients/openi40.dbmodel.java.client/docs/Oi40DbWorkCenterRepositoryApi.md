# Oi40DbWorkCenterRepositoryApi

All URIs are relative to *https://localhost:8083/openi40-backoffice*

Method | HTTP request | Description
------------- | ------------- | -------------
[**deleteByCodeVoid39**](Oi40DbWorkCenterRepositoryApi.md#deleteByCodeVoid39) | **GET** /integration/OI40DBWorkCenter/deleteByCode/{code} | deleteByCode
[**deleteByCodesVoid39**](Oi40DbWorkCenterRepositoryApi.md#deleteByCodesVoid39) | **POST** /integration/OI40DBWorkCenter/deleteByCodes | deleteByCodes
[**doAutocompletePageOI40DBWorkCenter**](Oi40DbWorkCenterRepositoryApi.md#doAutocompletePageOI40DBWorkCenter) | **POST** /integration/OI40DBWorkCenter/doAutocomplete | doAutocomplete
[**doLookupPageOI40DBWorkCenter**](Oi40DbWorkCenterRepositoryApi.md#doLookupPageOI40DBWorkCenter) | **POST** /integration/OI40DBWorkCenter/doLookup | doLookup
[**findAllListOI40DBWorkCenter**](Oi40DbWorkCenterRepositoryApi.md#findAllListOI40DBWorkCenter) | **GET** /integration/OI40DBWorkCenter | findAll
[**findAllPageOI40DBWorkCenter**](Oi40DbWorkCenterRepositoryApi.md#findAllPageOI40DBWorkCenter) | **POST** /integration/OI40DBWorkCenter/findAllPaged | findAll
[**findByAfterIntegrationTsListOI40DBWorkCenter**](Oi40DbWorkCenterRepositoryApi.md#findByAfterIntegrationTsListOI40DBWorkCenter) | **GET** /integration/OI40DBWorkCenter/integratedAfter/{ts} | findByAfterIntegrationTs
[**findByAfterModifiedTimestampListOI40DBWorkCenter**](Oi40DbWorkCenterRepositoryApi.md#findByAfterModifiedTimestampListOI40DBWorkCenter) | **GET** /integration/OI40DBWorkCenter/modifiedAfter/{ts} | findByAfterModifiedTimestamp
[**findByCodeOI40DBWorkCenter**](Oi40DbWorkCenterRepositoryApi.md#findByCodeOI40DBWorkCenter) | **GET** /integration/OI40DBWorkCenter/byCode/{code} | findByCode
[**findByCodesListOI40DBWorkCenter**](Oi40DbWorkCenterRepositoryApi.md#findByCodesListOI40DBWorkCenter) | **POST** /integration/OI40DBWorkCenter/findByCodes | findByCodes
[**findByQbeListOI40DBWorkCenter**](Oi40DbWorkCenterRepositoryApi.md#findByQbeListOI40DBWorkCenter) | **POST** /integration/OI40DBWorkCenter/findByQbe | findByQbe
[**findByQbePagedPageOI40DBWorkCenter**](Oi40DbWorkCenterRepositoryApi.md#findByQbePagedPageOI40DBWorkCenter) | **POST** /integration/OI40DBWorkCenter/findByQbePaged | findByQbePaged
[**updateListOI40DBWorkCenter**](Oi40DbWorkCenterRepositoryApi.md#updateListOI40DBWorkCenter) | **POST** /integration/OI40DBWorkCenter/update | update
[**updateSingleOI40DBWorkCenter**](Oi40DbWorkCenterRepositoryApi.md#updateSingleOI40DBWorkCenter) | **POST** /integration/OI40DBWorkCenter/updateSingle | updateSingle


<a name="deleteByCodeVoid39"></a>
# **deleteByCodeVoid39**
> deleteByCodeVoid39(code)

deleteByCode

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbWorkCenterRepositoryApi;


Oi40DbWorkCenterRepositoryApi apiInstance = new Oi40DbWorkCenterRepositoryApi();
String code = "code_example"; // String | code
try {
    apiInstance.deleteByCodeVoid39(code);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbWorkCenterRepositoryApi#deleteByCodeVoid39");
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

<a name="deleteByCodesVoid39"></a>
# **deleteByCodesVoid39**
> deleteByCodesVoid39(codes)

deleteByCodes

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbWorkCenterRepositoryApi;


Oi40DbWorkCenterRepositoryApi apiInstance = new Oi40DbWorkCenterRepositoryApi();
List<String> codes = Arrays.asList(new List<String>()); // List<String> | codes
try {
    apiInstance.deleteByCodesVoid39(codes);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbWorkCenterRepositoryApi#deleteByCodesVoid39");
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

<a name="doAutocompletePageOI40DBWorkCenter"></a>
# **doAutocompletePageOI40DBWorkCenter**
> PageOI40DBWorkCenter doAutocompletePageOI40DBWorkCenter(autoCompleteData)

doAutocomplete

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbWorkCenterRepositoryApi;


Oi40DbWorkCenterRepositoryApi apiInstance = new Oi40DbWorkCenterRepositoryApi();
AutoCompleteData autoCompleteData = new AutoCompleteData(); // AutoCompleteData | autoCompleteData
try {
    PageOI40DBWorkCenter result = apiInstance.doAutocompletePageOI40DBWorkCenter(autoCompleteData);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbWorkCenterRepositoryApi#doAutocompletePageOI40DBWorkCenter");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **autoCompleteData** | [**AutoCompleteData**](AutoCompleteData.md)| autoCompleteData |

### Return type

[**PageOI40DBWorkCenter**](PageOI40DBWorkCenter.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: */*

<a name="doLookupPageOI40DBWorkCenter"></a>
# **doLookupPageOI40DBWorkCenter**
> PageOI40DBWorkCenter doLookupPageOI40DBWorkCenter(lookup)

doLookup

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbWorkCenterRepositoryApi;


Oi40DbWorkCenterRepositoryApi apiInstance = new Oi40DbWorkCenterRepositoryApi();
LookupData lookup = new LookupData(); // LookupData | lookup
try {
    PageOI40DBWorkCenter result = apiInstance.doLookupPageOI40DBWorkCenter(lookup);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbWorkCenterRepositoryApi#doLookupPageOI40DBWorkCenter");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **lookup** | [**LookupData**](LookupData.md)| lookup |

### Return type

[**PageOI40DBWorkCenter**](PageOI40DBWorkCenter.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: */*

<a name="findAllListOI40DBWorkCenter"></a>
# **findAllListOI40DBWorkCenter**
> List&lt;OI40DBWorkCenter&gt; findAllListOI40DBWorkCenter()

findAll

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbWorkCenterRepositoryApi;


Oi40DbWorkCenterRepositoryApi apiInstance = new Oi40DbWorkCenterRepositoryApi();
try {
    List<OI40DBWorkCenter> result = apiInstance.findAllListOI40DBWorkCenter();
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbWorkCenterRepositoryApi#findAllListOI40DBWorkCenter");
    e.printStackTrace();
}
```

### Parameters
This endpoint does not need any parameter.

### Return type

[**List&lt;OI40DBWorkCenter&gt;**](OI40DBWorkCenter.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="findAllPageOI40DBWorkCenter"></a>
# **findAllPageOI40DBWorkCenter**
> PageOI40DBWorkCenter findAllPageOI40DBWorkCenter(p)

findAll

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbWorkCenterRepositoryApi;


Oi40DbWorkCenterRepositoryApi apiInstance = new Oi40DbWorkCenterRepositoryApi();
PageInfo p = new PageInfo(); // PageInfo | p
try {
    PageOI40DBWorkCenter result = apiInstance.findAllPageOI40DBWorkCenter(p);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbWorkCenterRepositoryApi#findAllPageOI40DBWorkCenter");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **p** | [**PageInfo**](PageInfo.md)| p |

### Return type

[**PageOI40DBWorkCenter**](PageOI40DBWorkCenter.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="findByAfterIntegrationTsListOI40DBWorkCenter"></a>
# **findByAfterIntegrationTsListOI40DBWorkCenter**
> List&lt;OI40DBWorkCenter&gt; findByAfterIntegrationTsListOI40DBWorkCenter(ts)

findByAfterIntegrationTs

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbWorkCenterRepositoryApi;


Oi40DbWorkCenterRepositoryApi apiInstance = new Oi40DbWorkCenterRepositoryApi();
String ts = "ts_example"; // String | ts
try {
    List<OI40DBWorkCenter> result = apiInstance.findByAfterIntegrationTsListOI40DBWorkCenter(ts);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbWorkCenterRepositoryApi#findByAfterIntegrationTsListOI40DBWorkCenter");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **ts** | **String**| ts |

### Return type

[**List&lt;OI40DBWorkCenter&gt;**](OI40DBWorkCenter.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*

<a name="findByAfterModifiedTimestampListOI40DBWorkCenter"></a>
# **findByAfterModifiedTimestampListOI40DBWorkCenter**
> List&lt;OI40DBWorkCenter&gt; findByAfterModifiedTimestampListOI40DBWorkCenter(ts)

findByAfterModifiedTimestamp

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbWorkCenterRepositoryApi;


Oi40DbWorkCenterRepositoryApi apiInstance = new Oi40DbWorkCenterRepositoryApi();
String ts = "ts_example"; // String | ts
try {
    List<OI40DBWorkCenter> result = apiInstance.findByAfterModifiedTimestampListOI40DBWorkCenter(ts);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbWorkCenterRepositoryApi#findByAfterModifiedTimestampListOI40DBWorkCenter");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **ts** | **String**| ts |

### Return type

[**List&lt;OI40DBWorkCenter&gt;**](OI40DBWorkCenter.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*

<a name="findByCodeOI40DBWorkCenter"></a>
# **findByCodeOI40DBWorkCenter**
> OI40DBWorkCenter findByCodeOI40DBWorkCenter(code)

findByCode

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbWorkCenterRepositoryApi;


Oi40DbWorkCenterRepositoryApi apiInstance = new Oi40DbWorkCenterRepositoryApi();
String code = "code_example"; // String | code
try {
    OI40DBWorkCenter result = apiInstance.findByCodeOI40DBWorkCenter(code);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbWorkCenterRepositoryApi#findByCodeOI40DBWorkCenter");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **code** | **String**| code |

### Return type

[**OI40DBWorkCenter**](OI40DBWorkCenter.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="findByCodesListOI40DBWorkCenter"></a>
# **findByCodesListOI40DBWorkCenter**
> List&lt;OI40DBWorkCenter&gt; findByCodesListOI40DBWorkCenter(codes)

findByCodes

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbWorkCenterRepositoryApi;


Oi40DbWorkCenterRepositoryApi apiInstance = new Oi40DbWorkCenterRepositoryApi();
List<String> codes = Arrays.asList(new List<String>()); // List<String> | codes
try {
    List<OI40DBWorkCenter> result = apiInstance.findByCodesListOI40DBWorkCenter(codes);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbWorkCenterRepositoryApi#findByCodesListOI40DBWorkCenter");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **codes** | **List&lt;String&gt;**| codes |

### Return type

[**List&lt;OI40DBWorkCenter&gt;**](OI40DBWorkCenter.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="findByQbeListOI40DBWorkCenter"></a>
# **findByQbeListOI40DBWorkCenter**
> List&lt;OI40DBWorkCenter&gt; findByQbeListOI40DBWorkCenter(qbe)

findByQbe

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbWorkCenterRepositoryApi;


Oi40DbWorkCenterRepositoryApi apiInstance = new Oi40DbWorkCenterRepositoryApi();
OI40DBWorkCenter qbe = new OI40DBWorkCenter(); // OI40DBWorkCenter | qbe
try {
    List<OI40DBWorkCenter> result = apiInstance.findByQbeListOI40DBWorkCenter(qbe);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbWorkCenterRepositoryApi#findByQbeListOI40DBWorkCenter");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **qbe** | [**OI40DBWorkCenter**](OI40DBWorkCenter.md)| qbe |

### Return type

[**List&lt;OI40DBWorkCenter&gt;**](OI40DBWorkCenter.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="findByQbePagedPageOI40DBWorkCenter"></a>
# **findByQbePagedPageOI40DBWorkCenter**
> PageOI40DBWorkCenter findByQbePagedPageOI40DBWorkCenter(qbe)

findByQbePaged

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbWorkCenterRepositoryApi;


Oi40DbWorkCenterRepositoryApi apiInstance = new Oi40DbWorkCenterRepositoryApi();
QbeSupportOI40DBWorkCenter qbe = new QbeSupportOI40DBWorkCenter(); // QbeSupportOI40DBWorkCenter | qbe
try {
    PageOI40DBWorkCenter result = apiInstance.findByQbePagedPageOI40DBWorkCenter(qbe);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbWorkCenterRepositoryApi#findByQbePagedPageOI40DBWorkCenter");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **qbe** | [**QbeSupportOI40DBWorkCenter**](QbeSupportOI40DBWorkCenter.md)| qbe |

### Return type

[**PageOI40DBWorkCenter**](PageOI40DBWorkCenter.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="updateListOI40DBWorkCenter"></a>
# **updateListOI40DBWorkCenter**
> List&lt;OI40DBWorkCenter&gt; updateListOI40DBWorkCenter(data)

update

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbWorkCenterRepositoryApi;


Oi40DbWorkCenterRepositoryApi apiInstance = new Oi40DbWorkCenterRepositoryApi();
List<OI40DBWorkCenter> data = Arrays.asList(new OI40DBWorkCenter()); // List<OI40DBWorkCenter> | data
try {
    List<OI40DBWorkCenter> result = apiInstance.updateListOI40DBWorkCenter(data);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbWorkCenterRepositoryApi#updateListOI40DBWorkCenter");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **data** | [**List&lt;OI40DBWorkCenter&gt;**](OI40DBWorkCenter.md)| data |

### Return type

[**List&lt;OI40DBWorkCenter&gt;**](OI40DBWorkCenter.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="updateSingleOI40DBWorkCenter"></a>
# **updateSingleOI40DBWorkCenter**
> OI40DBWorkCenter updateSingleOI40DBWorkCenter(data)

updateSingle

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbWorkCenterRepositoryApi;


Oi40DbWorkCenterRepositoryApi apiInstance = new Oi40DbWorkCenterRepositoryApi();
OI40DBWorkCenter data = new OI40DBWorkCenter(); // OI40DBWorkCenter | data
try {
    OI40DBWorkCenter result = apiInstance.updateSingleOI40DBWorkCenter(data);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbWorkCenterRepositoryApi#updateSingleOI40DBWorkCenter");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **data** | [**OI40DBWorkCenter**](OI40DBWorkCenter.md)| data |

### Return type

[**OI40DBWorkCenter**](OI40DBWorkCenter.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

