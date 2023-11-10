# Oi40DbTimesheetMetaInfoRepositoryApi

All URIs are relative to *https://localhost:8082/openi40-integration*

Method | HTTP request | Description
------------- | ------------- | -------------
[**deleteByCodeVoid35**](Oi40DbTimesheetMetaInfoRepositoryApi.md#deleteByCodeVoid35) | **GET** /integration/OI40DBTimesheetMetaInfo/deleteByCode/{code} | deleteByCode
[**deleteByCodesVoid35**](Oi40DbTimesheetMetaInfoRepositoryApi.md#deleteByCodesVoid35) | **POST** /integration/OI40DBTimesheetMetaInfo/deleteByCodes | deleteByCodes
[**doAutocompletePageOI40DBTimesheetMetaInfo**](Oi40DbTimesheetMetaInfoRepositoryApi.md#doAutocompletePageOI40DBTimesheetMetaInfo) | **POST** /integration/OI40DBTimesheetMetaInfo/doAutocomplete | doAutocomplete
[**doLookupPageOI40DBTimesheetMetaInfo**](Oi40DbTimesheetMetaInfoRepositoryApi.md#doLookupPageOI40DBTimesheetMetaInfo) | **POST** /integration/OI40DBTimesheetMetaInfo/doLookup | doLookup
[**findAllListOI40DBTimesheetMetaInfo**](Oi40DbTimesheetMetaInfoRepositoryApi.md#findAllListOI40DBTimesheetMetaInfo) | **GET** /integration/OI40DBTimesheetMetaInfo | findAll
[**findAllPageOI40DBTimesheetMetaInfo**](Oi40DbTimesheetMetaInfoRepositoryApi.md#findAllPageOI40DBTimesheetMetaInfo) | **POST** /integration/OI40DBTimesheetMetaInfo/findAllPaged | findAll
[**findByAfterIntegrationTsListOI40DBTimesheetMetaInfo**](Oi40DbTimesheetMetaInfoRepositoryApi.md#findByAfterIntegrationTsListOI40DBTimesheetMetaInfo) | **GET** /integration/OI40DBTimesheetMetaInfo/integratedAfter/{ts} | findByAfterIntegrationTs
[**findByAfterModifiedTimestampListOI40DBTimesheetMetaInfo**](Oi40DbTimesheetMetaInfoRepositoryApi.md#findByAfterModifiedTimestampListOI40DBTimesheetMetaInfo) | **GET** /integration/OI40DBTimesheetMetaInfo/modifiedAfter/{ts} | findByAfterModifiedTimestamp
[**findByCodeOI40DBTimesheetMetaInfo**](Oi40DbTimesheetMetaInfoRepositoryApi.md#findByCodeOI40DBTimesheetMetaInfo) | **GET** /integration/OI40DBTimesheetMetaInfo/byCode/{code} | findByCode
[**findByCodesListOI40DBTimesheetMetaInfo**](Oi40DbTimesheetMetaInfoRepositoryApi.md#findByCodesListOI40DBTimesheetMetaInfo) | **POST** /integration/OI40DBTimesheetMetaInfo/findByCodes | findByCodes
[**findByQbeListOI40DBTimesheetMetaInfo**](Oi40DbTimesheetMetaInfoRepositoryApi.md#findByQbeListOI40DBTimesheetMetaInfo) | **POST** /integration/OI40DBTimesheetMetaInfo/findByQbe | findByQbe
[**findByQbePagedPageOI40DBTimesheetMetaInfo**](Oi40DbTimesheetMetaInfoRepositoryApi.md#findByQbePagedPageOI40DBTimesheetMetaInfo) | **POST** /integration/OI40DBTimesheetMetaInfo/findByQbePaged | findByQbePaged
[**updateListOI40DBTimesheetMetaInfo**](Oi40DbTimesheetMetaInfoRepositoryApi.md#updateListOI40DBTimesheetMetaInfo) | **POST** /integration/OI40DBTimesheetMetaInfo/update | update
[**updateSingleOI40DBTimesheetMetaInfo**](Oi40DbTimesheetMetaInfoRepositoryApi.md#updateSingleOI40DBTimesheetMetaInfo) | **POST** /integration/OI40DBTimesheetMetaInfo/updateSingle | updateSingle


<a name="deleteByCodeVoid35"></a>
# **deleteByCodeVoid35**
> deleteByCodeVoid35(code)

deleteByCode

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbTimesheetMetaInfoRepositoryApi;


Oi40DbTimesheetMetaInfoRepositoryApi apiInstance = new Oi40DbTimesheetMetaInfoRepositoryApi();
String code = "code_example"; // String | code
try {
    apiInstance.deleteByCodeVoid35(code);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbTimesheetMetaInfoRepositoryApi#deleteByCodeVoid35");
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

<a name="deleteByCodesVoid35"></a>
# **deleteByCodesVoid35**
> deleteByCodesVoid35(codes)

deleteByCodes

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbTimesheetMetaInfoRepositoryApi;


Oi40DbTimesheetMetaInfoRepositoryApi apiInstance = new Oi40DbTimesheetMetaInfoRepositoryApi();
List<String> codes = Arrays.asList(new List<String>()); // List<String> | codes
try {
    apiInstance.deleteByCodesVoid35(codes);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbTimesheetMetaInfoRepositoryApi#deleteByCodesVoid35");
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

<a name="doAutocompletePageOI40DBTimesheetMetaInfo"></a>
# **doAutocompletePageOI40DBTimesheetMetaInfo**
> PageOI40DBTimesheetMetaInfo doAutocompletePageOI40DBTimesheetMetaInfo(autoCompleteData)

doAutocomplete

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbTimesheetMetaInfoRepositoryApi;


Oi40DbTimesheetMetaInfoRepositoryApi apiInstance = new Oi40DbTimesheetMetaInfoRepositoryApi();
AutoCompleteData autoCompleteData = new AutoCompleteData(); // AutoCompleteData | autoCompleteData
try {
    PageOI40DBTimesheetMetaInfo result = apiInstance.doAutocompletePageOI40DBTimesheetMetaInfo(autoCompleteData);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbTimesheetMetaInfoRepositoryApi#doAutocompletePageOI40DBTimesheetMetaInfo");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **autoCompleteData** | [**AutoCompleteData**](AutoCompleteData.md)| autoCompleteData |

### Return type

[**PageOI40DBTimesheetMetaInfo**](PageOI40DBTimesheetMetaInfo.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: */*

<a name="doLookupPageOI40DBTimesheetMetaInfo"></a>
# **doLookupPageOI40DBTimesheetMetaInfo**
> PageOI40DBTimesheetMetaInfo doLookupPageOI40DBTimesheetMetaInfo(lookup)

doLookup

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbTimesheetMetaInfoRepositoryApi;


Oi40DbTimesheetMetaInfoRepositoryApi apiInstance = new Oi40DbTimesheetMetaInfoRepositoryApi();
LookupData lookup = new LookupData(); // LookupData | lookup
try {
    PageOI40DBTimesheetMetaInfo result = apiInstance.doLookupPageOI40DBTimesheetMetaInfo(lookup);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbTimesheetMetaInfoRepositoryApi#doLookupPageOI40DBTimesheetMetaInfo");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **lookup** | [**LookupData**](LookupData.md)| lookup |

### Return type

[**PageOI40DBTimesheetMetaInfo**](PageOI40DBTimesheetMetaInfo.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: */*

<a name="findAllListOI40DBTimesheetMetaInfo"></a>
# **findAllListOI40DBTimesheetMetaInfo**
> List&lt;OI40DBTimesheetMetaInfo&gt; findAllListOI40DBTimesheetMetaInfo()

findAll

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbTimesheetMetaInfoRepositoryApi;


Oi40DbTimesheetMetaInfoRepositoryApi apiInstance = new Oi40DbTimesheetMetaInfoRepositoryApi();
try {
    List<OI40DBTimesheetMetaInfo> result = apiInstance.findAllListOI40DBTimesheetMetaInfo();
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbTimesheetMetaInfoRepositoryApi#findAllListOI40DBTimesheetMetaInfo");
    e.printStackTrace();
}
```

### Parameters
This endpoint does not need any parameter.

### Return type

[**List&lt;OI40DBTimesheetMetaInfo&gt;**](OI40DBTimesheetMetaInfo.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="findAllPageOI40DBTimesheetMetaInfo"></a>
# **findAllPageOI40DBTimesheetMetaInfo**
> PageOI40DBTimesheetMetaInfo findAllPageOI40DBTimesheetMetaInfo(p)

findAll

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbTimesheetMetaInfoRepositoryApi;


Oi40DbTimesheetMetaInfoRepositoryApi apiInstance = new Oi40DbTimesheetMetaInfoRepositoryApi();
PageInfo p = new PageInfo(); // PageInfo | p
try {
    PageOI40DBTimesheetMetaInfo result = apiInstance.findAllPageOI40DBTimesheetMetaInfo(p);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbTimesheetMetaInfoRepositoryApi#findAllPageOI40DBTimesheetMetaInfo");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **p** | [**PageInfo**](PageInfo.md)| p |

### Return type

[**PageOI40DBTimesheetMetaInfo**](PageOI40DBTimesheetMetaInfo.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="findByAfterIntegrationTsListOI40DBTimesheetMetaInfo"></a>
# **findByAfterIntegrationTsListOI40DBTimesheetMetaInfo**
> List&lt;OI40DBTimesheetMetaInfo&gt; findByAfterIntegrationTsListOI40DBTimesheetMetaInfo(ts)

findByAfterIntegrationTs

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbTimesheetMetaInfoRepositoryApi;


Oi40DbTimesheetMetaInfoRepositoryApi apiInstance = new Oi40DbTimesheetMetaInfoRepositoryApi();
String ts = "ts_example"; // String | ts
try {
    List<OI40DBTimesheetMetaInfo> result = apiInstance.findByAfterIntegrationTsListOI40DBTimesheetMetaInfo(ts);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbTimesheetMetaInfoRepositoryApi#findByAfterIntegrationTsListOI40DBTimesheetMetaInfo");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **ts** | **String**| ts |

### Return type

[**List&lt;OI40DBTimesheetMetaInfo&gt;**](OI40DBTimesheetMetaInfo.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*

<a name="findByAfterModifiedTimestampListOI40DBTimesheetMetaInfo"></a>
# **findByAfterModifiedTimestampListOI40DBTimesheetMetaInfo**
> List&lt;OI40DBTimesheetMetaInfo&gt; findByAfterModifiedTimestampListOI40DBTimesheetMetaInfo(ts)

findByAfterModifiedTimestamp

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbTimesheetMetaInfoRepositoryApi;


Oi40DbTimesheetMetaInfoRepositoryApi apiInstance = new Oi40DbTimesheetMetaInfoRepositoryApi();
String ts = "ts_example"; // String | ts
try {
    List<OI40DBTimesheetMetaInfo> result = apiInstance.findByAfterModifiedTimestampListOI40DBTimesheetMetaInfo(ts);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbTimesheetMetaInfoRepositoryApi#findByAfterModifiedTimestampListOI40DBTimesheetMetaInfo");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **ts** | **String**| ts |

### Return type

[**List&lt;OI40DBTimesheetMetaInfo&gt;**](OI40DBTimesheetMetaInfo.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*

<a name="findByCodeOI40DBTimesheetMetaInfo"></a>
# **findByCodeOI40DBTimesheetMetaInfo**
> OI40DBTimesheetMetaInfo findByCodeOI40DBTimesheetMetaInfo(code)

findByCode

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbTimesheetMetaInfoRepositoryApi;


Oi40DbTimesheetMetaInfoRepositoryApi apiInstance = new Oi40DbTimesheetMetaInfoRepositoryApi();
String code = "code_example"; // String | code
try {
    OI40DBTimesheetMetaInfo result = apiInstance.findByCodeOI40DBTimesheetMetaInfo(code);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbTimesheetMetaInfoRepositoryApi#findByCodeOI40DBTimesheetMetaInfo");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **code** | **String**| code |

### Return type

[**OI40DBTimesheetMetaInfo**](OI40DBTimesheetMetaInfo.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="findByCodesListOI40DBTimesheetMetaInfo"></a>
# **findByCodesListOI40DBTimesheetMetaInfo**
> List&lt;OI40DBTimesheetMetaInfo&gt; findByCodesListOI40DBTimesheetMetaInfo(codes)

findByCodes

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbTimesheetMetaInfoRepositoryApi;


Oi40DbTimesheetMetaInfoRepositoryApi apiInstance = new Oi40DbTimesheetMetaInfoRepositoryApi();
List<String> codes = Arrays.asList(new List<String>()); // List<String> | codes
try {
    List<OI40DBTimesheetMetaInfo> result = apiInstance.findByCodesListOI40DBTimesheetMetaInfo(codes);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbTimesheetMetaInfoRepositoryApi#findByCodesListOI40DBTimesheetMetaInfo");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **codes** | **List&lt;String&gt;**| codes |

### Return type

[**List&lt;OI40DBTimesheetMetaInfo&gt;**](OI40DBTimesheetMetaInfo.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="findByQbeListOI40DBTimesheetMetaInfo"></a>
# **findByQbeListOI40DBTimesheetMetaInfo**
> List&lt;OI40DBTimesheetMetaInfo&gt; findByQbeListOI40DBTimesheetMetaInfo(qbe)

findByQbe

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbTimesheetMetaInfoRepositoryApi;


Oi40DbTimesheetMetaInfoRepositoryApi apiInstance = new Oi40DbTimesheetMetaInfoRepositoryApi();
OI40DBTimesheetMetaInfo qbe = new OI40DBTimesheetMetaInfo(); // OI40DBTimesheetMetaInfo | qbe
try {
    List<OI40DBTimesheetMetaInfo> result = apiInstance.findByQbeListOI40DBTimesheetMetaInfo(qbe);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbTimesheetMetaInfoRepositoryApi#findByQbeListOI40DBTimesheetMetaInfo");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **qbe** | [**OI40DBTimesheetMetaInfo**](OI40DBTimesheetMetaInfo.md)| qbe |

### Return type

[**List&lt;OI40DBTimesheetMetaInfo&gt;**](OI40DBTimesheetMetaInfo.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="findByQbePagedPageOI40DBTimesheetMetaInfo"></a>
# **findByQbePagedPageOI40DBTimesheetMetaInfo**
> PageOI40DBTimesheetMetaInfo findByQbePagedPageOI40DBTimesheetMetaInfo(qbe)

findByQbePaged

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbTimesheetMetaInfoRepositoryApi;


Oi40DbTimesheetMetaInfoRepositoryApi apiInstance = new Oi40DbTimesheetMetaInfoRepositoryApi();
QbeSupportOI40DBTimesheetMetaInfo qbe = new QbeSupportOI40DBTimesheetMetaInfo(); // QbeSupportOI40DBTimesheetMetaInfo | qbe
try {
    PageOI40DBTimesheetMetaInfo result = apiInstance.findByQbePagedPageOI40DBTimesheetMetaInfo(qbe);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbTimesheetMetaInfoRepositoryApi#findByQbePagedPageOI40DBTimesheetMetaInfo");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **qbe** | [**QbeSupportOI40DBTimesheetMetaInfo**](QbeSupportOI40DBTimesheetMetaInfo.md)| qbe |

### Return type

[**PageOI40DBTimesheetMetaInfo**](PageOI40DBTimesheetMetaInfo.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="updateListOI40DBTimesheetMetaInfo"></a>
# **updateListOI40DBTimesheetMetaInfo**
> List&lt;OI40DBTimesheetMetaInfo&gt; updateListOI40DBTimesheetMetaInfo(data)

update

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbTimesheetMetaInfoRepositoryApi;


Oi40DbTimesheetMetaInfoRepositoryApi apiInstance = new Oi40DbTimesheetMetaInfoRepositoryApi();
List<OI40DBTimesheetMetaInfo> data = Arrays.asList(new OI40DBTimesheetMetaInfo()); // List<OI40DBTimesheetMetaInfo> | data
try {
    List<OI40DBTimesheetMetaInfo> result = apiInstance.updateListOI40DBTimesheetMetaInfo(data);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbTimesheetMetaInfoRepositoryApi#updateListOI40DBTimesheetMetaInfo");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **data** | [**List&lt;OI40DBTimesheetMetaInfo&gt;**](OI40DBTimesheetMetaInfo.md)| data |

### Return type

[**List&lt;OI40DBTimesheetMetaInfo&gt;**](OI40DBTimesheetMetaInfo.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="updateSingleOI40DBTimesheetMetaInfo"></a>
# **updateSingleOI40DBTimesheetMetaInfo**
> OI40DBTimesheetMetaInfo updateSingleOI40DBTimesheetMetaInfo(data)

updateSingle

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbTimesheetMetaInfoRepositoryApi;


Oi40DbTimesheetMetaInfoRepositoryApi apiInstance = new Oi40DbTimesheetMetaInfoRepositoryApi();
OI40DBTimesheetMetaInfo data = new OI40DBTimesheetMetaInfo(); // OI40DBTimesheetMetaInfo | data
try {
    OI40DBTimesheetMetaInfo result = apiInstance.updateSingleOI40DBTimesheetMetaInfo(data);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbTimesheetMetaInfoRepositoryApi#updateSingleOI40DBTimesheetMetaInfo");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **data** | [**OI40DBTimesheetMetaInfo**](OI40DBTimesheetMetaInfo.md)| data |

### Return type

[**OI40DBTimesheetMetaInfo**](OI40DBTimesheetMetaInfo.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

