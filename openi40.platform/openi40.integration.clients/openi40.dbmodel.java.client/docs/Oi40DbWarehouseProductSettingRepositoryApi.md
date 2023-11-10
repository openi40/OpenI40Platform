# Oi40DbWarehouseProductSettingRepositoryApi

All URIs are relative to *https://localhost:8082/openi40-integration*

Method | HTTP request | Description
------------- | ------------- | -------------
[**deleteByCodeVoid37**](Oi40DbWarehouseProductSettingRepositoryApi.md#deleteByCodeVoid37) | **GET** /integration/OI40DBWarehouseProductSetting/deleteByCode/{code} | deleteByCode
[**deleteByCodesVoid37**](Oi40DbWarehouseProductSettingRepositoryApi.md#deleteByCodesVoid37) | **POST** /integration/OI40DBWarehouseProductSetting/deleteByCodes | deleteByCodes
[**doAutocompletePageOI40DBWarehouseProductSetting**](Oi40DbWarehouseProductSettingRepositoryApi.md#doAutocompletePageOI40DBWarehouseProductSetting) | **POST** /integration/OI40DBWarehouseProductSetting/doAutocomplete | doAutocomplete
[**doLookupPageOI40DBWarehouseProductSetting**](Oi40DbWarehouseProductSettingRepositoryApi.md#doLookupPageOI40DBWarehouseProductSetting) | **POST** /integration/OI40DBWarehouseProductSetting/doLookup | doLookup
[**findAllListOI40DBWarehouseProductSetting**](Oi40DbWarehouseProductSettingRepositoryApi.md#findAllListOI40DBWarehouseProductSetting) | **GET** /integration/OI40DBWarehouseProductSetting | findAll
[**findAllPageOI40DBWarehouseProductSetting**](Oi40DbWarehouseProductSettingRepositoryApi.md#findAllPageOI40DBWarehouseProductSetting) | **POST** /integration/OI40DBWarehouseProductSetting/findAllPaged | findAll
[**findByAfterIntegrationTsListOI40DBWarehouseProductSetting**](Oi40DbWarehouseProductSettingRepositoryApi.md#findByAfterIntegrationTsListOI40DBWarehouseProductSetting) | **GET** /integration/OI40DBWarehouseProductSetting/integratedAfter/{ts} | findByAfterIntegrationTs
[**findByAfterModifiedTimestampListOI40DBWarehouseProductSetting**](Oi40DbWarehouseProductSettingRepositoryApi.md#findByAfterModifiedTimestampListOI40DBWarehouseProductSetting) | **GET** /integration/OI40DBWarehouseProductSetting/modifiedAfter/{ts} | findByAfterModifiedTimestamp
[**findByCodeOI40DBWarehouseProductSetting**](Oi40DbWarehouseProductSettingRepositoryApi.md#findByCodeOI40DBWarehouseProductSetting) | **GET** /integration/OI40DBWarehouseProductSetting/byCode/{code} | findByCode
[**findByCodesListOI40DBWarehouseProductSetting**](Oi40DbWarehouseProductSettingRepositoryApi.md#findByCodesListOI40DBWarehouseProductSetting) | **POST** /integration/OI40DBWarehouseProductSetting/findByCodes | findByCodes
[**findByQbeListOI40DBWarehouseProductSetting**](Oi40DbWarehouseProductSettingRepositoryApi.md#findByQbeListOI40DBWarehouseProductSetting) | **POST** /integration/OI40DBWarehouseProductSetting/findByQbe | findByQbe
[**findByQbePagedPageOI40DBWarehouseProductSetting**](Oi40DbWarehouseProductSettingRepositoryApi.md#findByQbePagedPageOI40DBWarehouseProductSetting) | **POST** /integration/OI40DBWarehouseProductSetting/findByQbePaged | findByQbePaged
[**updateListOI40DBWarehouseProductSetting**](Oi40DbWarehouseProductSettingRepositoryApi.md#updateListOI40DBWarehouseProductSetting) | **POST** /integration/OI40DBWarehouseProductSetting/update | update
[**updateSingleOI40DBWarehouseProductSetting**](Oi40DbWarehouseProductSettingRepositoryApi.md#updateSingleOI40DBWarehouseProductSetting) | **POST** /integration/OI40DBWarehouseProductSetting/updateSingle | updateSingle


<a name="deleteByCodeVoid37"></a>
# **deleteByCodeVoid37**
> deleteByCodeVoid37(code)

deleteByCode

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbWarehouseProductSettingRepositoryApi;


Oi40DbWarehouseProductSettingRepositoryApi apiInstance = new Oi40DbWarehouseProductSettingRepositoryApi();
String code = "code_example"; // String | code
try {
    apiInstance.deleteByCodeVoid37(code);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbWarehouseProductSettingRepositoryApi#deleteByCodeVoid37");
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

<a name="deleteByCodesVoid37"></a>
# **deleteByCodesVoid37**
> deleteByCodesVoid37(codes)

deleteByCodes

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbWarehouseProductSettingRepositoryApi;


Oi40DbWarehouseProductSettingRepositoryApi apiInstance = new Oi40DbWarehouseProductSettingRepositoryApi();
List<String> codes = Arrays.asList(new List<String>()); // List<String> | codes
try {
    apiInstance.deleteByCodesVoid37(codes);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbWarehouseProductSettingRepositoryApi#deleteByCodesVoid37");
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

<a name="doAutocompletePageOI40DBWarehouseProductSetting"></a>
# **doAutocompletePageOI40DBWarehouseProductSetting**
> PageOI40DBWarehouseProductSetting doAutocompletePageOI40DBWarehouseProductSetting(autoCompleteData)

doAutocomplete

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbWarehouseProductSettingRepositoryApi;


Oi40DbWarehouseProductSettingRepositoryApi apiInstance = new Oi40DbWarehouseProductSettingRepositoryApi();
AutoCompleteData autoCompleteData = new AutoCompleteData(); // AutoCompleteData | autoCompleteData
try {
    PageOI40DBWarehouseProductSetting result = apiInstance.doAutocompletePageOI40DBWarehouseProductSetting(autoCompleteData);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbWarehouseProductSettingRepositoryApi#doAutocompletePageOI40DBWarehouseProductSetting");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **autoCompleteData** | [**AutoCompleteData**](AutoCompleteData.md)| autoCompleteData |

### Return type

[**PageOI40DBWarehouseProductSetting**](PageOI40DBWarehouseProductSetting.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: */*

<a name="doLookupPageOI40DBWarehouseProductSetting"></a>
# **doLookupPageOI40DBWarehouseProductSetting**
> PageOI40DBWarehouseProductSetting doLookupPageOI40DBWarehouseProductSetting(lookup)

doLookup

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbWarehouseProductSettingRepositoryApi;


Oi40DbWarehouseProductSettingRepositoryApi apiInstance = new Oi40DbWarehouseProductSettingRepositoryApi();
LookupData lookup = new LookupData(); // LookupData | lookup
try {
    PageOI40DBWarehouseProductSetting result = apiInstance.doLookupPageOI40DBWarehouseProductSetting(lookup);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbWarehouseProductSettingRepositoryApi#doLookupPageOI40DBWarehouseProductSetting");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **lookup** | [**LookupData**](LookupData.md)| lookup |

### Return type

[**PageOI40DBWarehouseProductSetting**](PageOI40DBWarehouseProductSetting.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: */*

<a name="findAllListOI40DBWarehouseProductSetting"></a>
# **findAllListOI40DBWarehouseProductSetting**
> List&lt;OI40DBWarehouseProductSetting&gt; findAllListOI40DBWarehouseProductSetting()

findAll

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbWarehouseProductSettingRepositoryApi;


Oi40DbWarehouseProductSettingRepositoryApi apiInstance = new Oi40DbWarehouseProductSettingRepositoryApi();
try {
    List<OI40DBWarehouseProductSetting> result = apiInstance.findAllListOI40DBWarehouseProductSetting();
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbWarehouseProductSettingRepositoryApi#findAllListOI40DBWarehouseProductSetting");
    e.printStackTrace();
}
```

### Parameters
This endpoint does not need any parameter.

### Return type

[**List&lt;OI40DBWarehouseProductSetting&gt;**](OI40DBWarehouseProductSetting.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="findAllPageOI40DBWarehouseProductSetting"></a>
# **findAllPageOI40DBWarehouseProductSetting**
> PageOI40DBWarehouseProductSetting findAllPageOI40DBWarehouseProductSetting(p)

findAll

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbWarehouseProductSettingRepositoryApi;


Oi40DbWarehouseProductSettingRepositoryApi apiInstance = new Oi40DbWarehouseProductSettingRepositoryApi();
PageInfo p = new PageInfo(); // PageInfo | p
try {
    PageOI40DBWarehouseProductSetting result = apiInstance.findAllPageOI40DBWarehouseProductSetting(p);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbWarehouseProductSettingRepositoryApi#findAllPageOI40DBWarehouseProductSetting");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **p** | [**PageInfo**](PageInfo.md)| p |

### Return type

[**PageOI40DBWarehouseProductSetting**](PageOI40DBWarehouseProductSetting.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="findByAfterIntegrationTsListOI40DBWarehouseProductSetting"></a>
# **findByAfterIntegrationTsListOI40DBWarehouseProductSetting**
> List&lt;OI40DBWarehouseProductSetting&gt; findByAfterIntegrationTsListOI40DBWarehouseProductSetting(ts)

findByAfterIntegrationTs

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbWarehouseProductSettingRepositoryApi;


Oi40DbWarehouseProductSettingRepositoryApi apiInstance = new Oi40DbWarehouseProductSettingRepositoryApi();
String ts = "ts_example"; // String | ts
try {
    List<OI40DBWarehouseProductSetting> result = apiInstance.findByAfterIntegrationTsListOI40DBWarehouseProductSetting(ts);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbWarehouseProductSettingRepositoryApi#findByAfterIntegrationTsListOI40DBWarehouseProductSetting");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **ts** | **String**| ts |

### Return type

[**List&lt;OI40DBWarehouseProductSetting&gt;**](OI40DBWarehouseProductSetting.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*

<a name="findByAfterModifiedTimestampListOI40DBWarehouseProductSetting"></a>
# **findByAfterModifiedTimestampListOI40DBWarehouseProductSetting**
> List&lt;OI40DBWarehouseProductSetting&gt; findByAfterModifiedTimestampListOI40DBWarehouseProductSetting(ts)

findByAfterModifiedTimestamp

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbWarehouseProductSettingRepositoryApi;


Oi40DbWarehouseProductSettingRepositoryApi apiInstance = new Oi40DbWarehouseProductSettingRepositoryApi();
String ts = "ts_example"; // String | ts
try {
    List<OI40DBWarehouseProductSetting> result = apiInstance.findByAfterModifiedTimestampListOI40DBWarehouseProductSetting(ts);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbWarehouseProductSettingRepositoryApi#findByAfterModifiedTimestampListOI40DBWarehouseProductSetting");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **ts** | **String**| ts |

### Return type

[**List&lt;OI40DBWarehouseProductSetting&gt;**](OI40DBWarehouseProductSetting.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*

<a name="findByCodeOI40DBWarehouseProductSetting"></a>
# **findByCodeOI40DBWarehouseProductSetting**
> OI40DBWarehouseProductSetting findByCodeOI40DBWarehouseProductSetting(code)

findByCode

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbWarehouseProductSettingRepositoryApi;


Oi40DbWarehouseProductSettingRepositoryApi apiInstance = new Oi40DbWarehouseProductSettingRepositoryApi();
String code = "code_example"; // String | code
try {
    OI40DBWarehouseProductSetting result = apiInstance.findByCodeOI40DBWarehouseProductSetting(code);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbWarehouseProductSettingRepositoryApi#findByCodeOI40DBWarehouseProductSetting");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **code** | **String**| code |

### Return type

[**OI40DBWarehouseProductSetting**](OI40DBWarehouseProductSetting.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="findByCodesListOI40DBWarehouseProductSetting"></a>
# **findByCodesListOI40DBWarehouseProductSetting**
> List&lt;OI40DBWarehouseProductSetting&gt; findByCodesListOI40DBWarehouseProductSetting(codes)

findByCodes

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbWarehouseProductSettingRepositoryApi;


Oi40DbWarehouseProductSettingRepositoryApi apiInstance = new Oi40DbWarehouseProductSettingRepositoryApi();
List<String> codes = Arrays.asList(new List<String>()); // List<String> | codes
try {
    List<OI40DBWarehouseProductSetting> result = apiInstance.findByCodesListOI40DBWarehouseProductSetting(codes);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbWarehouseProductSettingRepositoryApi#findByCodesListOI40DBWarehouseProductSetting");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **codes** | **List&lt;String&gt;**| codes |

### Return type

[**List&lt;OI40DBWarehouseProductSetting&gt;**](OI40DBWarehouseProductSetting.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="findByQbeListOI40DBWarehouseProductSetting"></a>
# **findByQbeListOI40DBWarehouseProductSetting**
> List&lt;OI40DBWarehouseProductSetting&gt; findByQbeListOI40DBWarehouseProductSetting(qbe)

findByQbe

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbWarehouseProductSettingRepositoryApi;


Oi40DbWarehouseProductSettingRepositoryApi apiInstance = new Oi40DbWarehouseProductSettingRepositoryApi();
OI40DBWarehouseProductSetting qbe = new OI40DBWarehouseProductSetting(); // OI40DBWarehouseProductSetting | qbe
try {
    List<OI40DBWarehouseProductSetting> result = apiInstance.findByQbeListOI40DBWarehouseProductSetting(qbe);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbWarehouseProductSettingRepositoryApi#findByQbeListOI40DBWarehouseProductSetting");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **qbe** | [**OI40DBWarehouseProductSetting**](OI40DBWarehouseProductSetting.md)| qbe |

### Return type

[**List&lt;OI40DBWarehouseProductSetting&gt;**](OI40DBWarehouseProductSetting.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="findByQbePagedPageOI40DBWarehouseProductSetting"></a>
# **findByQbePagedPageOI40DBWarehouseProductSetting**
> PageOI40DBWarehouseProductSetting findByQbePagedPageOI40DBWarehouseProductSetting(qbe)

findByQbePaged

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbWarehouseProductSettingRepositoryApi;


Oi40DbWarehouseProductSettingRepositoryApi apiInstance = new Oi40DbWarehouseProductSettingRepositoryApi();
QbeSupportOI40DBWarehouseProductSetting qbe = new QbeSupportOI40DBWarehouseProductSetting(); // QbeSupportOI40DBWarehouseProductSetting | qbe
try {
    PageOI40DBWarehouseProductSetting result = apiInstance.findByQbePagedPageOI40DBWarehouseProductSetting(qbe);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbWarehouseProductSettingRepositoryApi#findByQbePagedPageOI40DBWarehouseProductSetting");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **qbe** | [**QbeSupportOI40DBWarehouseProductSetting**](QbeSupportOI40DBWarehouseProductSetting.md)| qbe |

### Return type

[**PageOI40DBWarehouseProductSetting**](PageOI40DBWarehouseProductSetting.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="updateListOI40DBWarehouseProductSetting"></a>
# **updateListOI40DBWarehouseProductSetting**
> List&lt;OI40DBWarehouseProductSetting&gt; updateListOI40DBWarehouseProductSetting(data)

update

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbWarehouseProductSettingRepositoryApi;


Oi40DbWarehouseProductSettingRepositoryApi apiInstance = new Oi40DbWarehouseProductSettingRepositoryApi();
List<OI40DBWarehouseProductSetting> data = Arrays.asList(new OI40DBWarehouseProductSetting()); // List<OI40DBWarehouseProductSetting> | data
try {
    List<OI40DBWarehouseProductSetting> result = apiInstance.updateListOI40DBWarehouseProductSetting(data);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbWarehouseProductSettingRepositoryApi#updateListOI40DBWarehouseProductSetting");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **data** | [**List&lt;OI40DBWarehouseProductSetting&gt;**](OI40DBWarehouseProductSetting.md)| data |

### Return type

[**List&lt;OI40DBWarehouseProductSetting&gt;**](OI40DBWarehouseProductSetting.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="updateSingleOI40DBWarehouseProductSetting"></a>
# **updateSingleOI40DBWarehouseProductSetting**
> OI40DBWarehouseProductSetting updateSingleOI40DBWarehouseProductSetting(data)

updateSingle

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbWarehouseProductSettingRepositoryApi;


Oi40DbWarehouseProductSettingRepositoryApi apiInstance = new Oi40DbWarehouseProductSettingRepositoryApi();
OI40DBWarehouseProductSetting data = new OI40DBWarehouseProductSetting(); // OI40DBWarehouseProductSetting | data
try {
    OI40DBWarehouseProductSetting result = apiInstance.updateSingleOI40DBWarehouseProductSetting(data);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbWarehouseProductSettingRepositoryApi#updateSingleOI40DBWarehouseProductSetting");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **data** | [**OI40DBWarehouseProductSetting**](OI40DBWarehouseProductSetting.md)| data |

### Return type

[**OI40DBWarehouseProductSetting**](OI40DBWarehouseProductSetting.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

