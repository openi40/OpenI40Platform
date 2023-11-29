# Oi40DbPlantProductSettingRepositoryApi

All URIs are relative to *https://localhost:8083/openi40-backoffice*

Method | HTTP request | Description
------------- | ------------- | -------------
[**deleteByCodeVoid14**](Oi40DbPlantProductSettingRepositoryApi.md#deleteByCodeVoid14) | **GET** /integration/OI40DBPlantProductSetting/deleteByCode/{code} | deleteByCode
[**deleteByCodesVoid14**](Oi40DbPlantProductSettingRepositoryApi.md#deleteByCodesVoid14) | **POST** /integration/OI40DBPlantProductSetting/deleteByCodes | deleteByCodes
[**doAutocompletePageOI40DBPlantProductSetting**](Oi40DbPlantProductSettingRepositoryApi.md#doAutocompletePageOI40DBPlantProductSetting) | **POST** /integration/OI40DBPlantProductSetting/doAutocomplete | doAutocomplete
[**doLookupPageOI40DBPlantProductSetting**](Oi40DbPlantProductSettingRepositoryApi.md#doLookupPageOI40DBPlantProductSetting) | **POST** /integration/OI40DBPlantProductSetting/doLookup | doLookup
[**findAllListOI40DBPlantProductSetting**](Oi40DbPlantProductSettingRepositoryApi.md#findAllListOI40DBPlantProductSetting) | **GET** /integration/OI40DBPlantProductSetting | findAll
[**findAllPageOI40DBPlantProductSetting**](Oi40DbPlantProductSettingRepositoryApi.md#findAllPageOI40DBPlantProductSetting) | **POST** /integration/OI40DBPlantProductSetting/findAllPaged | findAll
[**findByAfterIntegrationTsListOI40DBPlantProductSetting**](Oi40DbPlantProductSettingRepositoryApi.md#findByAfterIntegrationTsListOI40DBPlantProductSetting) | **GET** /integration/OI40DBPlantProductSetting/integratedAfter/{ts} | findByAfterIntegrationTs
[**findByAfterModifiedTimestampListOI40DBPlantProductSetting**](Oi40DbPlantProductSettingRepositoryApi.md#findByAfterModifiedTimestampListOI40DBPlantProductSetting) | **GET** /integration/OI40DBPlantProductSetting/modifiedAfter/{ts} | findByAfterModifiedTimestamp
[**findByCodeOI40DBPlantProductSetting**](Oi40DbPlantProductSettingRepositoryApi.md#findByCodeOI40DBPlantProductSetting) | **GET** /integration/OI40DBPlantProductSetting/byCode/{code} | findByCode
[**findByCodesListOI40DBPlantProductSetting**](Oi40DbPlantProductSettingRepositoryApi.md#findByCodesListOI40DBPlantProductSetting) | **POST** /integration/OI40DBPlantProductSetting/findByCodes | findByCodes
[**findByQbeListOI40DBPlantProductSetting**](Oi40DbPlantProductSettingRepositoryApi.md#findByQbeListOI40DBPlantProductSetting) | **POST** /integration/OI40DBPlantProductSetting/findByQbe | findByQbe
[**findByQbePagedPageOI40DBPlantProductSetting**](Oi40DbPlantProductSettingRepositoryApi.md#findByQbePagedPageOI40DBPlantProductSetting) | **POST** /integration/OI40DBPlantProductSetting/findByQbePaged | findByQbePaged
[**updateListOI40DBPlantProductSetting**](Oi40DbPlantProductSettingRepositoryApi.md#updateListOI40DBPlantProductSetting) | **POST** /integration/OI40DBPlantProductSetting/update | update
[**updateSingleOI40DBPlantProductSetting**](Oi40DbPlantProductSettingRepositoryApi.md#updateSingleOI40DBPlantProductSetting) | **POST** /integration/OI40DBPlantProductSetting/updateSingle | updateSingle


<a name="deleteByCodeVoid14"></a>
# **deleteByCodeVoid14**
> deleteByCodeVoid14(code)

deleteByCode

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbPlantProductSettingRepositoryApi;


Oi40DbPlantProductSettingRepositoryApi apiInstance = new Oi40DbPlantProductSettingRepositoryApi();
String code = "code_example"; // String | code
try {
    apiInstance.deleteByCodeVoid14(code);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbPlantProductSettingRepositoryApi#deleteByCodeVoid14");
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

<a name="deleteByCodesVoid14"></a>
# **deleteByCodesVoid14**
> deleteByCodesVoid14(codes)

deleteByCodes

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbPlantProductSettingRepositoryApi;


Oi40DbPlantProductSettingRepositoryApi apiInstance = new Oi40DbPlantProductSettingRepositoryApi();
List<String> codes = Arrays.asList(new List<String>()); // List<String> | codes
try {
    apiInstance.deleteByCodesVoid14(codes);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbPlantProductSettingRepositoryApi#deleteByCodesVoid14");
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

<a name="doAutocompletePageOI40DBPlantProductSetting"></a>
# **doAutocompletePageOI40DBPlantProductSetting**
> PageOI40DBPlantProductSetting doAutocompletePageOI40DBPlantProductSetting(autoCompleteData)

doAutocomplete

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbPlantProductSettingRepositoryApi;


Oi40DbPlantProductSettingRepositoryApi apiInstance = new Oi40DbPlantProductSettingRepositoryApi();
AutoCompleteData autoCompleteData = new AutoCompleteData(); // AutoCompleteData | autoCompleteData
try {
    PageOI40DBPlantProductSetting result = apiInstance.doAutocompletePageOI40DBPlantProductSetting(autoCompleteData);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbPlantProductSettingRepositoryApi#doAutocompletePageOI40DBPlantProductSetting");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **autoCompleteData** | [**AutoCompleteData**](AutoCompleteData.md)| autoCompleteData |

### Return type

[**PageOI40DBPlantProductSetting**](PageOI40DBPlantProductSetting.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: */*

<a name="doLookupPageOI40DBPlantProductSetting"></a>
# **doLookupPageOI40DBPlantProductSetting**
> PageOI40DBPlantProductSetting doLookupPageOI40DBPlantProductSetting(lookup)

doLookup

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbPlantProductSettingRepositoryApi;


Oi40DbPlantProductSettingRepositoryApi apiInstance = new Oi40DbPlantProductSettingRepositoryApi();
LookupData lookup = new LookupData(); // LookupData | lookup
try {
    PageOI40DBPlantProductSetting result = apiInstance.doLookupPageOI40DBPlantProductSetting(lookup);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbPlantProductSettingRepositoryApi#doLookupPageOI40DBPlantProductSetting");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **lookup** | [**LookupData**](LookupData.md)| lookup |

### Return type

[**PageOI40DBPlantProductSetting**](PageOI40DBPlantProductSetting.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: */*

<a name="findAllListOI40DBPlantProductSetting"></a>
# **findAllListOI40DBPlantProductSetting**
> List&lt;OI40DBPlantProductSetting&gt; findAllListOI40DBPlantProductSetting()

findAll

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbPlantProductSettingRepositoryApi;


Oi40DbPlantProductSettingRepositoryApi apiInstance = new Oi40DbPlantProductSettingRepositoryApi();
try {
    List<OI40DBPlantProductSetting> result = apiInstance.findAllListOI40DBPlantProductSetting();
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbPlantProductSettingRepositoryApi#findAllListOI40DBPlantProductSetting");
    e.printStackTrace();
}
```

### Parameters
This endpoint does not need any parameter.

### Return type

[**List&lt;OI40DBPlantProductSetting&gt;**](OI40DBPlantProductSetting.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="findAllPageOI40DBPlantProductSetting"></a>
# **findAllPageOI40DBPlantProductSetting**
> PageOI40DBPlantProductSetting findAllPageOI40DBPlantProductSetting(p)

findAll

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbPlantProductSettingRepositoryApi;


Oi40DbPlantProductSettingRepositoryApi apiInstance = new Oi40DbPlantProductSettingRepositoryApi();
PageInfo p = new PageInfo(); // PageInfo | p
try {
    PageOI40DBPlantProductSetting result = apiInstance.findAllPageOI40DBPlantProductSetting(p);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbPlantProductSettingRepositoryApi#findAllPageOI40DBPlantProductSetting");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **p** | [**PageInfo**](PageInfo.md)| p |

### Return type

[**PageOI40DBPlantProductSetting**](PageOI40DBPlantProductSetting.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="findByAfterIntegrationTsListOI40DBPlantProductSetting"></a>
# **findByAfterIntegrationTsListOI40DBPlantProductSetting**
> List&lt;OI40DBPlantProductSetting&gt; findByAfterIntegrationTsListOI40DBPlantProductSetting(ts)

findByAfterIntegrationTs

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbPlantProductSettingRepositoryApi;


Oi40DbPlantProductSettingRepositoryApi apiInstance = new Oi40DbPlantProductSettingRepositoryApi();
String ts = "ts_example"; // String | ts
try {
    List<OI40DBPlantProductSetting> result = apiInstance.findByAfterIntegrationTsListOI40DBPlantProductSetting(ts);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbPlantProductSettingRepositoryApi#findByAfterIntegrationTsListOI40DBPlantProductSetting");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **ts** | **String**| ts |

### Return type

[**List&lt;OI40DBPlantProductSetting&gt;**](OI40DBPlantProductSetting.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*

<a name="findByAfterModifiedTimestampListOI40DBPlantProductSetting"></a>
# **findByAfterModifiedTimestampListOI40DBPlantProductSetting**
> List&lt;OI40DBPlantProductSetting&gt; findByAfterModifiedTimestampListOI40DBPlantProductSetting(ts)

findByAfterModifiedTimestamp

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbPlantProductSettingRepositoryApi;


Oi40DbPlantProductSettingRepositoryApi apiInstance = new Oi40DbPlantProductSettingRepositoryApi();
String ts = "ts_example"; // String | ts
try {
    List<OI40DBPlantProductSetting> result = apiInstance.findByAfterModifiedTimestampListOI40DBPlantProductSetting(ts);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbPlantProductSettingRepositoryApi#findByAfterModifiedTimestampListOI40DBPlantProductSetting");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **ts** | **String**| ts |

### Return type

[**List&lt;OI40DBPlantProductSetting&gt;**](OI40DBPlantProductSetting.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*

<a name="findByCodeOI40DBPlantProductSetting"></a>
# **findByCodeOI40DBPlantProductSetting**
> OI40DBPlantProductSetting findByCodeOI40DBPlantProductSetting(code)

findByCode

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbPlantProductSettingRepositoryApi;


Oi40DbPlantProductSettingRepositoryApi apiInstance = new Oi40DbPlantProductSettingRepositoryApi();
String code = "code_example"; // String | code
try {
    OI40DBPlantProductSetting result = apiInstance.findByCodeOI40DBPlantProductSetting(code);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbPlantProductSettingRepositoryApi#findByCodeOI40DBPlantProductSetting");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **code** | **String**| code |

### Return type

[**OI40DBPlantProductSetting**](OI40DBPlantProductSetting.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="findByCodesListOI40DBPlantProductSetting"></a>
# **findByCodesListOI40DBPlantProductSetting**
> List&lt;OI40DBPlantProductSetting&gt; findByCodesListOI40DBPlantProductSetting(codes)

findByCodes

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbPlantProductSettingRepositoryApi;


Oi40DbPlantProductSettingRepositoryApi apiInstance = new Oi40DbPlantProductSettingRepositoryApi();
List<String> codes = Arrays.asList(new List<String>()); // List<String> | codes
try {
    List<OI40DBPlantProductSetting> result = apiInstance.findByCodesListOI40DBPlantProductSetting(codes);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbPlantProductSettingRepositoryApi#findByCodesListOI40DBPlantProductSetting");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **codes** | **List&lt;String&gt;**| codes |

### Return type

[**List&lt;OI40DBPlantProductSetting&gt;**](OI40DBPlantProductSetting.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="findByQbeListOI40DBPlantProductSetting"></a>
# **findByQbeListOI40DBPlantProductSetting**
> List&lt;OI40DBPlantProductSetting&gt; findByQbeListOI40DBPlantProductSetting(qbe)

findByQbe

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbPlantProductSettingRepositoryApi;


Oi40DbPlantProductSettingRepositoryApi apiInstance = new Oi40DbPlantProductSettingRepositoryApi();
OI40DBPlantProductSetting qbe = new OI40DBPlantProductSetting(); // OI40DBPlantProductSetting | qbe
try {
    List<OI40DBPlantProductSetting> result = apiInstance.findByQbeListOI40DBPlantProductSetting(qbe);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbPlantProductSettingRepositoryApi#findByQbeListOI40DBPlantProductSetting");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **qbe** | [**OI40DBPlantProductSetting**](OI40DBPlantProductSetting.md)| qbe |

### Return type

[**List&lt;OI40DBPlantProductSetting&gt;**](OI40DBPlantProductSetting.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="findByQbePagedPageOI40DBPlantProductSetting"></a>
# **findByQbePagedPageOI40DBPlantProductSetting**
> PageOI40DBPlantProductSetting findByQbePagedPageOI40DBPlantProductSetting(qbe)

findByQbePaged

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbPlantProductSettingRepositoryApi;


Oi40DbPlantProductSettingRepositoryApi apiInstance = new Oi40DbPlantProductSettingRepositoryApi();
QbeSupportOI40DBPlantProductSetting qbe = new QbeSupportOI40DBPlantProductSetting(); // QbeSupportOI40DBPlantProductSetting | qbe
try {
    PageOI40DBPlantProductSetting result = apiInstance.findByQbePagedPageOI40DBPlantProductSetting(qbe);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbPlantProductSettingRepositoryApi#findByQbePagedPageOI40DBPlantProductSetting");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **qbe** | [**QbeSupportOI40DBPlantProductSetting**](QbeSupportOI40DBPlantProductSetting.md)| qbe |

### Return type

[**PageOI40DBPlantProductSetting**](PageOI40DBPlantProductSetting.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="updateListOI40DBPlantProductSetting"></a>
# **updateListOI40DBPlantProductSetting**
> List&lt;OI40DBPlantProductSetting&gt; updateListOI40DBPlantProductSetting(data)

update

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbPlantProductSettingRepositoryApi;


Oi40DbPlantProductSettingRepositoryApi apiInstance = new Oi40DbPlantProductSettingRepositoryApi();
List<OI40DBPlantProductSetting> data = Arrays.asList(new OI40DBPlantProductSetting()); // List<OI40DBPlantProductSetting> | data
try {
    List<OI40DBPlantProductSetting> result = apiInstance.updateListOI40DBPlantProductSetting(data);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbPlantProductSettingRepositoryApi#updateListOI40DBPlantProductSetting");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **data** | [**List&lt;OI40DBPlantProductSetting&gt;**](OI40DBPlantProductSetting.md)| data |

### Return type

[**List&lt;OI40DBPlantProductSetting&gt;**](OI40DBPlantProductSetting.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="updateSingleOI40DBPlantProductSetting"></a>
# **updateSingleOI40DBPlantProductSetting**
> OI40DBPlantProductSetting updateSingleOI40DBPlantProductSetting(data)

updateSingle

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbPlantProductSettingRepositoryApi;


Oi40DbPlantProductSettingRepositoryApi apiInstance = new Oi40DbPlantProductSettingRepositoryApi();
OI40DBPlantProductSetting data = new OI40DBPlantProductSetting(); // OI40DBPlantProductSetting | data
try {
    OI40DBPlantProductSetting result = apiInstance.updateSingleOI40DBPlantProductSetting(data);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbPlantProductSettingRepositoryApi#updateSingleOI40DBPlantProductSetting");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **data** | [**OI40DBPlantProductSetting**](OI40DBPlantProductSetting.md)| data |

### Return type

[**OI40DBPlantProductSetting**](OI40DBPlantProductSetting.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

