# Oi40DbProductiveCompanyProductSettingRepositoryApi

All URIs are relative to *https://localhost:8082/openi40-integration*

Method | HTTP request | Description
------------- | ------------- | -------------
[**deleteByCodeVoid17**](Oi40DbProductiveCompanyProductSettingRepositoryApi.md#deleteByCodeVoid17) | **GET** /integration/OI40DBProductiveCompanyProductSetting/deleteByCode/{code} | deleteByCode
[**deleteByCodesVoid17**](Oi40DbProductiveCompanyProductSettingRepositoryApi.md#deleteByCodesVoid17) | **POST** /integration/OI40DBProductiveCompanyProductSetting/deleteByCodes | deleteByCodes
[**doAutocompletePageOI40DBProductiveCompanyProductSetting**](Oi40DbProductiveCompanyProductSettingRepositoryApi.md#doAutocompletePageOI40DBProductiveCompanyProductSetting) | **POST** /integration/OI40DBProductiveCompanyProductSetting/doAutocomplete | doAutocomplete
[**doLookupPageOI40DBProductiveCompanyProductSetting**](Oi40DbProductiveCompanyProductSettingRepositoryApi.md#doLookupPageOI40DBProductiveCompanyProductSetting) | **POST** /integration/OI40DBProductiveCompanyProductSetting/doLookup | doLookup
[**findAllListOI40DBProductiveCompanyProductSetting**](Oi40DbProductiveCompanyProductSettingRepositoryApi.md#findAllListOI40DBProductiveCompanyProductSetting) | **GET** /integration/OI40DBProductiveCompanyProductSetting | findAll
[**findAllPageOI40DBProductiveCompanyProductSetting**](Oi40DbProductiveCompanyProductSettingRepositoryApi.md#findAllPageOI40DBProductiveCompanyProductSetting) | **POST** /integration/OI40DBProductiveCompanyProductSetting/findAllPaged | findAll
[**findByAfterIntegrationTsListOI40DBProductiveCompanyProductSetting**](Oi40DbProductiveCompanyProductSettingRepositoryApi.md#findByAfterIntegrationTsListOI40DBProductiveCompanyProductSetting) | **GET** /integration/OI40DBProductiveCompanyProductSetting/integratedAfter/{ts} | findByAfterIntegrationTs
[**findByAfterModifiedTimestampListOI40DBProductiveCompanyProductSetting**](Oi40DbProductiveCompanyProductSettingRepositoryApi.md#findByAfterModifiedTimestampListOI40DBProductiveCompanyProductSetting) | **GET** /integration/OI40DBProductiveCompanyProductSetting/modifiedAfter/{ts} | findByAfterModifiedTimestamp
[**findByCodeOI40DBProductiveCompanyProductSetting**](Oi40DbProductiveCompanyProductSettingRepositoryApi.md#findByCodeOI40DBProductiveCompanyProductSetting) | **GET** /integration/OI40DBProductiveCompanyProductSetting/byCode/{code} | findByCode
[**findByCodesListOI40DBProductiveCompanyProductSetting**](Oi40DbProductiveCompanyProductSettingRepositoryApi.md#findByCodesListOI40DBProductiveCompanyProductSetting) | **POST** /integration/OI40DBProductiveCompanyProductSetting/findByCodes | findByCodes
[**findByQbeListOI40DBProductiveCompanyProductSetting**](Oi40DbProductiveCompanyProductSettingRepositoryApi.md#findByQbeListOI40DBProductiveCompanyProductSetting) | **POST** /integration/OI40DBProductiveCompanyProductSetting/findByQbe | findByQbe
[**findByQbePagedPageOI40DBProductiveCompanyProductSetting**](Oi40DbProductiveCompanyProductSettingRepositoryApi.md#findByQbePagedPageOI40DBProductiveCompanyProductSetting) | **POST** /integration/OI40DBProductiveCompanyProductSetting/findByQbePaged | findByQbePaged
[**updateListOI40DBProductiveCompanyProductSetting**](Oi40DbProductiveCompanyProductSettingRepositoryApi.md#updateListOI40DBProductiveCompanyProductSetting) | **POST** /integration/OI40DBProductiveCompanyProductSetting/update | update
[**updateSingleOI40DBProductiveCompanyProductSetting**](Oi40DbProductiveCompanyProductSettingRepositoryApi.md#updateSingleOI40DBProductiveCompanyProductSetting) | **POST** /integration/OI40DBProductiveCompanyProductSetting/updateSingle | updateSingle


<a name="deleteByCodeVoid17"></a>
# **deleteByCodeVoid17**
> deleteByCodeVoid17(code)

deleteByCode

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbProductiveCompanyProductSettingRepositoryApi;


Oi40DbProductiveCompanyProductSettingRepositoryApi apiInstance = new Oi40DbProductiveCompanyProductSettingRepositoryApi();
String code = "code_example"; // String | code
try {
    apiInstance.deleteByCodeVoid17(code);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbProductiveCompanyProductSettingRepositoryApi#deleteByCodeVoid17");
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

<a name="deleteByCodesVoid17"></a>
# **deleteByCodesVoid17**
> deleteByCodesVoid17(codes)

deleteByCodes

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbProductiveCompanyProductSettingRepositoryApi;


Oi40DbProductiveCompanyProductSettingRepositoryApi apiInstance = new Oi40DbProductiveCompanyProductSettingRepositoryApi();
List<String> codes = Arrays.asList(new List<String>()); // List<String> | codes
try {
    apiInstance.deleteByCodesVoid17(codes);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbProductiveCompanyProductSettingRepositoryApi#deleteByCodesVoid17");
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

<a name="doAutocompletePageOI40DBProductiveCompanyProductSetting"></a>
# **doAutocompletePageOI40DBProductiveCompanyProductSetting**
> PageOI40DBProductiveCompanyProductSetting doAutocompletePageOI40DBProductiveCompanyProductSetting(autoCompleteData)

doAutocomplete

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbProductiveCompanyProductSettingRepositoryApi;


Oi40DbProductiveCompanyProductSettingRepositoryApi apiInstance = new Oi40DbProductiveCompanyProductSettingRepositoryApi();
AutoCompleteData autoCompleteData = new AutoCompleteData(); // AutoCompleteData | autoCompleteData
try {
    PageOI40DBProductiveCompanyProductSetting result = apiInstance.doAutocompletePageOI40DBProductiveCompanyProductSetting(autoCompleteData);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbProductiveCompanyProductSettingRepositoryApi#doAutocompletePageOI40DBProductiveCompanyProductSetting");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **autoCompleteData** | [**AutoCompleteData**](AutoCompleteData.md)| autoCompleteData |

### Return type

[**PageOI40DBProductiveCompanyProductSetting**](PageOI40DBProductiveCompanyProductSetting.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: */*

<a name="doLookupPageOI40DBProductiveCompanyProductSetting"></a>
# **doLookupPageOI40DBProductiveCompanyProductSetting**
> PageOI40DBProductiveCompanyProductSetting doLookupPageOI40DBProductiveCompanyProductSetting(lookup)

doLookup

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbProductiveCompanyProductSettingRepositoryApi;


Oi40DbProductiveCompanyProductSettingRepositoryApi apiInstance = new Oi40DbProductiveCompanyProductSettingRepositoryApi();
LookupData lookup = new LookupData(); // LookupData | lookup
try {
    PageOI40DBProductiveCompanyProductSetting result = apiInstance.doLookupPageOI40DBProductiveCompanyProductSetting(lookup);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbProductiveCompanyProductSettingRepositoryApi#doLookupPageOI40DBProductiveCompanyProductSetting");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **lookup** | [**LookupData**](LookupData.md)| lookup |

### Return type

[**PageOI40DBProductiveCompanyProductSetting**](PageOI40DBProductiveCompanyProductSetting.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: */*

<a name="findAllListOI40DBProductiveCompanyProductSetting"></a>
# **findAllListOI40DBProductiveCompanyProductSetting**
> List&lt;OI40DBProductiveCompanyProductSetting&gt; findAllListOI40DBProductiveCompanyProductSetting()

findAll

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbProductiveCompanyProductSettingRepositoryApi;


Oi40DbProductiveCompanyProductSettingRepositoryApi apiInstance = new Oi40DbProductiveCompanyProductSettingRepositoryApi();
try {
    List<OI40DBProductiveCompanyProductSetting> result = apiInstance.findAllListOI40DBProductiveCompanyProductSetting();
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbProductiveCompanyProductSettingRepositoryApi#findAllListOI40DBProductiveCompanyProductSetting");
    e.printStackTrace();
}
```

### Parameters
This endpoint does not need any parameter.

### Return type

[**List&lt;OI40DBProductiveCompanyProductSetting&gt;**](OI40DBProductiveCompanyProductSetting.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="findAllPageOI40DBProductiveCompanyProductSetting"></a>
# **findAllPageOI40DBProductiveCompanyProductSetting**
> PageOI40DBProductiveCompanyProductSetting findAllPageOI40DBProductiveCompanyProductSetting(p)

findAll

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbProductiveCompanyProductSettingRepositoryApi;


Oi40DbProductiveCompanyProductSettingRepositoryApi apiInstance = new Oi40DbProductiveCompanyProductSettingRepositoryApi();
PageInfo p = new PageInfo(); // PageInfo | p
try {
    PageOI40DBProductiveCompanyProductSetting result = apiInstance.findAllPageOI40DBProductiveCompanyProductSetting(p);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbProductiveCompanyProductSettingRepositoryApi#findAllPageOI40DBProductiveCompanyProductSetting");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **p** | [**PageInfo**](PageInfo.md)| p |

### Return type

[**PageOI40DBProductiveCompanyProductSetting**](PageOI40DBProductiveCompanyProductSetting.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="findByAfterIntegrationTsListOI40DBProductiveCompanyProductSetting"></a>
# **findByAfterIntegrationTsListOI40DBProductiveCompanyProductSetting**
> List&lt;OI40DBProductiveCompanyProductSetting&gt; findByAfterIntegrationTsListOI40DBProductiveCompanyProductSetting(ts)

findByAfterIntegrationTs

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbProductiveCompanyProductSettingRepositoryApi;


Oi40DbProductiveCompanyProductSettingRepositoryApi apiInstance = new Oi40DbProductiveCompanyProductSettingRepositoryApi();
String ts = "ts_example"; // String | ts
try {
    List<OI40DBProductiveCompanyProductSetting> result = apiInstance.findByAfterIntegrationTsListOI40DBProductiveCompanyProductSetting(ts);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbProductiveCompanyProductSettingRepositoryApi#findByAfterIntegrationTsListOI40DBProductiveCompanyProductSetting");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **ts** | **String**| ts |

### Return type

[**List&lt;OI40DBProductiveCompanyProductSetting&gt;**](OI40DBProductiveCompanyProductSetting.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*

<a name="findByAfterModifiedTimestampListOI40DBProductiveCompanyProductSetting"></a>
# **findByAfterModifiedTimestampListOI40DBProductiveCompanyProductSetting**
> List&lt;OI40DBProductiveCompanyProductSetting&gt; findByAfterModifiedTimestampListOI40DBProductiveCompanyProductSetting(ts)

findByAfterModifiedTimestamp

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbProductiveCompanyProductSettingRepositoryApi;


Oi40DbProductiveCompanyProductSettingRepositoryApi apiInstance = new Oi40DbProductiveCompanyProductSettingRepositoryApi();
String ts = "ts_example"; // String | ts
try {
    List<OI40DBProductiveCompanyProductSetting> result = apiInstance.findByAfterModifiedTimestampListOI40DBProductiveCompanyProductSetting(ts);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbProductiveCompanyProductSettingRepositoryApi#findByAfterModifiedTimestampListOI40DBProductiveCompanyProductSetting");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **ts** | **String**| ts |

### Return type

[**List&lt;OI40DBProductiveCompanyProductSetting&gt;**](OI40DBProductiveCompanyProductSetting.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*

<a name="findByCodeOI40DBProductiveCompanyProductSetting"></a>
# **findByCodeOI40DBProductiveCompanyProductSetting**
> OI40DBProductiveCompanyProductSetting findByCodeOI40DBProductiveCompanyProductSetting(code)

findByCode

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbProductiveCompanyProductSettingRepositoryApi;


Oi40DbProductiveCompanyProductSettingRepositoryApi apiInstance = new Oi40DbProductiveCompanyProductSettingRepositoryApi();
String code = "code_example"; // String | code
try {
    OI40DBProductiveCompanyProductSetting result = apiInstance.findByCodeOI40DBProductiveCompanyProductSetting(code);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbProductiveCompanyProductSettingRepositoryApi#findByCodeOI40DBProductiveCompanyProductSetting");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **code** | **String**| code |

### Return type

[**OI40DBProductiveCompanyProductSetting**](OI40DBProductiveCompanyProductSetting.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="findByCodesListOI40DBProductiveCompanyProductSetting"></a>
# **findByCodesListOI40DBProductiveCompanyProductSetting**
> List&lt;OI40DBProductiveCompanyProductSetting&gt; findByCodesListOI40DBProductiveCompanyProductSetting(codes)

findByCodes

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbProductiveCompanyProductSettingRepositoryApi;


Oi40DbProductiveCompanyProductSettingRepositoryApi apiInstance = new Oi40DbProductiveCompanyProductSettingRepositoryApi();
List<String> codes = Arrays.asList(new List<String>()); // List<String> | codes
try {
    List<OI40DBProductiveCompanyProductSetting> result = apiInstance.findByCodesListOI40DBProductiveCompanyProductSetting(codes);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbProductiveCompanyProductSettingRepositoryApi#findByCodesListOI40DBProductiveCompanyProductSetting");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **codes** | **List&lt;String&gt;**| codes |

### Return type

[**List&lt;OI40DBProductiveCompanyProductSetting&gt;**](OI40DBProductiveCompanyProductSetting.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="findByQbeListOI40DBProductiveCompanyProductSetting"></a>
# **findByQbeListOI40DBProductiveCompanyProductSetting**
> List&lt;OI40DBProductiveCompanyProductSetting&gt; findByQbeListOI40DBProductiveCompanyProductSetting(qbe)

findByQbe

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbProductiveCompanyProductSettingRepositoryApi;


Oi40DbProductiveCompanyProductSettingRepositoryApi apiInstance = new Oi40DbProductiveCompanyProductSettingRepositoryApi();
OI40DBProductiveCompanyProductSetting qbe = new OI40DBProductiveCompanyProductSetting(); // OI40DBProductiveCompanyProductSetting | qbe
try {
    List<OI40DBProductiveCompanyProductSetting> result = apiInstance.findByQbeListOI40DBProductiveCompanyProductSetting(qbe);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbProductiveCompanyProductSettingRepositoryApi#findByQbeListOI40DBProductiveCompanyProductSetting");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **qbe** | [**OI40DBProductiveCompanyProductSetting**](OI40DBProductiveCompanyProductSetting.md)| qbe |

### Return type

[**List&lt;OI40DBProductiveCompanyProductSetting&gt;**](OI40DBProductiveCompanyProductSetting.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="findByQbePagedPageOI40DBProductiveCompanyProductSetting"></a>
# **findByQbePagedPageOI40DBProductiveCompanyProductSetting**
> PageOI40DBProductiveCompanyProductSetting findByQbePagedPageOI40DBProductiveCompanyProductSetting(qbe)

findByQbePaged

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbProductiveCompanyProductSettingRepositoryApi;


Oi40DbProductiveCompanyProductSettingRepositoryApi apiInstance = new Oi40DbProductiveCompanyProductSettingRepositoryApi();
QbeSupportOI40DBProductiveCompanyProductSetting qbe = new QbeSupportOI40DBProductiveCompanyProductSetting(); // QbeSupportOI40DBProductiveCompanyProductSetting | qbe
try {
    PageOI40DBProductiveCompanyProductSetting result = apiInstance.findByQbePagedPageOI40DBProductiveCompanyProductSetting(qbe);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbProductiveCompanyProductSettingRepositoryApi#findByQbePagedPageOI40DBProductiveCompanyProductSetting");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **qbe** | [**QbeSupportOI40DBProductiveCompanyProductSetting**](QbeSupportOI40DBProductiveCompanyProductSetting.md)| qbe |

### Return type

[**PageOI40DBProductiveCompanyProductSetting**](PageOI40DBProductiveCompanyProductSetting.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="updateListOI40DBProductiveCompanyProductSetting"></a>
# **updateListOI40DBProductiveCompanyProductSetting**
> List&lt;OI40DBProductiveCompanyProductSetting&gt; updateListOI40DBProductiveCompanyProductSetting(data)

update

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbProductiveCompanyProductSettingRepositoryApi;


Oi40DbProductiveCompanyProductSettingRepositoryApi apiInstance = new Oi40DbProductiveCompanyProductSettingRepositoryApi();
List<OI40DBProductiveCompanyProductSetting> data = Arrays.asList(new OI40DBProductiveCompanyProductSetting()); // List<OI40DBProductiveCompanyProductSetting> | data
try {
    List<OI40DBProductiveCompanyProductSetting> result = apiInstance.updateListOI40DBProductiveCompanyProductSetting(data);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbProductiveCompanyProductSettingRepositoryApi#updateListOI40DBProductiveCompanyProductSetting");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **data** | [**List&lt;OI40DBProductiveCompanyProductSetting&gt;**](OI40DBProductiveCompanyProductSetting.md)| data |

### Return type

[**List&lt;OI40DBProductiveCompanyProductSetting&gt;**](OI40DBProductiveCompanyProductSetting.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="updateSingleOI40DBProductiveCompanyProductSetting"></a>
# **updateSingleOI40DBProductiveCompanyProductSetting**
> OI40DBProductiveCompanyProductSetting updateSingleOI40DBProductiveCompanyProductSetting(data)

updateSingle

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbProductiveCompanyProductSettingRepositoryApi;


Oi40DbProductiveCompanyProductSettingRepositoryApi apiInstance = new Oi40DbProductiveCompanyProductSettingRepositoryApi();
OI40DBProductiveCompanyProductSetting data = new OI40DBProductiveCompanyProductSetting(); // OI40DBProductiveCompanyProductSetting | data
try {
    OI40DBProductiveCompanyProductSetting result = apiInstance.updateSingleOI40DBProductiveCompanyProductSetting(data);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbProductiveCompanyProductSettingRepositoryApi#updateSingleOI40DBProductiveCompanyProductSetting");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **data** | [**OI40DBProductiveCompanyProductSetting**](OI40DBProductiveCompanyProductSetting.md)| data |

### Return type

[**OI40DBProductiveCompanyProductSetting**](OI40DBProductiveCompanyProductSetting.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

