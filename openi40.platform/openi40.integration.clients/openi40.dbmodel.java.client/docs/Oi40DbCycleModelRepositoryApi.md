# Oi40DbCycleModelRepositoryApi

All URIs are relative to *https://localhost:8082/openi40-integration*

Method | HTTP request | Description
------------- | ------------- | -------------
[**deleteByCodeVoid5**](Oi40DbCycleModelRepositoryApi.md#deleteByCodeVoid5) | **GET** /integration/OI40DBCycleModel/deleteByCode/{code} | deleteByCode
[**deleteByCodesVoid5**](Oi40DbCycleModelRepositoryApi.md#deleteByCodesVoid5) | **POST** /integration/OI40DBCycleModel/deleteByCodes | deleteByCodes
[**doAutocompletePageOI40DBCycleModel**](Oi40DbCycleModelRepositoryApi.md#doAutocompletePageOI40DBCycleModel) | **POST** /integration/OI40DBCycleModel/doAutocomplete | doAutocomplete
[**doLookupPageOI40DBCycleModel**](Oi40DbCycleModelRepositoryApi.md#doLookupPageOI40DBCycleModel) | **POST** /integration/OI40DBCycleModel/doLookup | doLookup
[**findAllListOI40DBCycleModel**](Oi40DbCycleModelRepositoryApi.md#findAllListOI40DBCycleModel) | **GET** /integration/OI40DBCycleModel | findAll
[**findAllPageOI40DBCycleModel**](Oi40DbCycleModelRepositoryApi.md#findAllPageOI40DBCycleModel) | **POST** /integration/OI40DBCycleModel/findAllPaged | findAll
[**findByAfterIntegrationTsListOI40DBCycleModel**](Oi40DbCycleModelRepositoryApi.md#findByAfterIntegrationTsListOI40DBCycleModel) | **GET** /integration/OI40DBCycleModel/integratedAfter/{ts} | findByAfterIntegrationTs
[**findByAfterModifiedTimestampListOI40DBCycleModel**](Oi40DbCycleModelRepositoryApi.md#findByAfterModifiedTimestampListOI40DBCycleModel) | **GET** /integration/OI40DBCycleModel/modifiedAfter/{ts} | findByAfterModifiedTimestamp
[**findByCodeOI40DBCycleModel**](Oi40DbCycleModelRepositoryApi.md#findByCodeOI40DBCycleModel) | **GET** /integration/OI40DBCycleModel/byCode/{code} | findByCode
[**findByCodesListOI40DBCycleModel**](Oi40DbCycleModelRepositoryApi.md#findByCodesListOI40DBCycleModel) | **POST** /integration/OI40DBCycleModel/findByCodes | findByCodes
[**findByQbeListOI40DBCycleModel**](Oi40DbCycleModelRepositoryApi.md#findByQbeListOI40DBCycleModel) | **POST** /integration/OI40DBCycleModel/findByQbe | findByQbe
[**findByQbePagedPageOI40DBCycleModel**](Oi40DbCycleModelRepositoryApi.md#findByQbePagedPageOI40DBCycleModel) | **POST** /integration/OI40DBCycleModel/findByQbePaged | findByQbePaged
[**updateListOI40DBCycleModel**](Oi40DbCycleModelRepositoryApi.md#updateListOI40DBCycleModel) | **POST** /integration/OI40DBCycleModel/update | update
[**updateSingleOI40DBCycleModel**](Oi40DbCycleModelRepositoryApi.md#updateSingleOI40DBCycleModel) | **POST** /integration/OI40DBCycleModel/updateSingle | updateSingle


<a name="deleteByCodeVoid5"></a>
# **deleteByCodeVoid5**
> deleteByCodeVoid5(code)

deleteByCode

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbCycleModelRepositoryApi;


Oi40DbCycleModelRepositoryApi apiInstance = new Oi40DbCycleModelRepositoryApi();
String code = "code_example"; // String | code
try {
    apiInstance.deleteByCodeVoid5(code);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbCycleModelRepositoryApi#deleteByCodeVoid5");
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

<a name="deleteByCodesVoid5"></a>
# **deleteByCodesVoid5**
> deleteByCodesVoid5(codes)

deleteByCodes

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbCycleModelRepositoryApi;


Oi40DbCycleModelRepositoryApi apiInstance = new Oi40DbCycleModelRepositoryApi();
List<String> codes = Arrays.asList(new List<String>()); // List<String> | codes
try {
    apiInstance.deleteByCodesVoid5(codes);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbCycleModelRepositoryApi#deleteByCodesVoid5");
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

<a name="doAutocompletePageOI40DBCycleModel"></a>
# **doAutocompletePageOI40DBCycleModel**
> PageOI40DBCycleModel doAutocompletePageOI40DBCycleModel(autoCompleteData)

doAutocomplete

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbCycleModelRepositoryApi;


Oi40DbCycleModelRepositoryApi apiInstance = new Oi40DbCycleModelRepositoryApi();
AutoCompleteData autoCompleteData = new AutoCompleteData(); // AutoCompleteData | autoCompleteData
try {
    PageOI40DBCycleModel result = apiInstance.doAutocompletePageOI40DBCycleModel(autoCompleteData);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbCycleModelRepositoryApi#doAutocompletePageOI40DBCycleModel");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **autoCompleteData** | [**AutoCompleteData**](AutoCompleteData.md)| autoCompleteData |

### Return type

[**PageOI40DBCycleModel**](PageOI40DBCycleModel.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: */*

<a name="doLookupPageOI40DBCycleModel"></a>
# **doLookupPageOI40DBCycleModel**
> PageOI40DBCycleModel doLookupPageOI40DBCycleModel(lookup)

doLookup

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbCycleModelRepositoryApi;


Oi40DbCycleModelRepositoryApi apiInstance = new Oi40DbCycleModelRepositoryApi();
LookupData lookup = new LookupData(); // LookupData | lookup
try {
    PageOI40DBCycleModel result = apiInstance.doLookupPageOI40DBCycleModel(lookup);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbCycleModelRepositoryApi#doLookupPageOI40DBCycleModel");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **lookup** | [**LookupData**](LookupData.md)| lookup |

### Return type

[**PageOI40DBCycleModel**](PageOI40DBCycleModel.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: */*

<a name="findAllListOI40DBCycleModel"></a>
# **findAllListOI40DBCycleModel**
> List&lt;OI40DBCycleModel&gt; findAllListOI40DBCycleModel()

findAll

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbCycleModelRepositoryApi;


Oi40DbCycleModelRepositoryApi apiInstance = new Oi40DbCycleModelRepositoryApi();
try {
    List<OI40DBCycleModel> result = apiInstance.findAllListOI40DBCycleModel();
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbCycleModelRepositoryApi#findAllListOI40DBCycleModel");
    e.printStackTrace();
}
```

### Parameters
This endpoint does not need any parameter.

### Return type

[**List&lt;OI40DBCycleModel&gt;**](OI40DBCycleModel.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="findAllPageOI40DBCycleModel"></a>
# **findAllPageOI40DBCycleModel**
> PageOI40DBCycleModel findAllPageOI40DBCycleModel(p)

findAll

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbCycleModelRepositoryApi;


Oi40DbCycleModelRepositoryApi apiInstance = new Oi40DbCycleModelRepositoryApi();
PageInfo p = new PageInfo(); // PageInfo | p
try {
    PageOI40DBCycleModel result = apiInstance.findAllPageOI40DBCycleModel(p);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbCycleModelRepositoryApi#findAllPageOI40DBCycleModel");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **p** | [**PageInfo**](PageInfo.md)| p |

### Return type

[**PageOI40DBCycleModel**](PageOI40DBCycleModel.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="findByAfterIntegrationTsListOI40DBCycleModel"></a>
# **findByAfterIntegrationTsListOI40DBCycleModel**
> List&lt;OI40DBCycleModel&gt; findByAfterIntegrationTsListOI40DBCycleModel(ts)

findByAfterIntegrationTs

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbCycleModelRepositoryApi;


Oi40DbCycleModelRepositoryApi apiInstance = new Oi40DbCycleModelRepositoryApi();
String ts = "ts_example"; // String | ts
try {
    List<OI40DBCycleModel> result = apiInstance.findByAfterIntegrationTsListOI40DBCycleModel(ts);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbCycleModelRepositoryApi#findByAfterIntegrationTsListOI40DBCycleModel");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **ts** | **String**| ts |

### Return type

[**List&lt;OI40DBCycleModel&gt;**](OI40DBCycleModel.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*

<a name="findByAfterModifiedTimestampListOI40DBCycleModel"></a>
# **findByAfterModifiedTimestampListOI40DBCycleModel**
> List&lt;OI40DBCycleModel&gt; findByAfterModifiedTimestampListOI40DBCycleModel(ts)

findByAfterModifiedTimestamp

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbCycleModelRepositoryApi;


Oi40DbCycleModelRepositoryApi apiInstance = new Oi40DbCycleModelRepositoryApi();
String ts = "ts_example"; // String | ts
try {
    List<OI40DBCycleModel> result = apiInstance.findByAfterModifiedTimestampListOI40DBCycleModel(ts);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbCycleModelRepositoryApi#findByAfterModifiedTimestampListOI40DBCycleModel");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **ts** | **String**| ts |

### Return type

[**List&lt;OI40DBCycleModel&gt;**](OI40DBCycleModel.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*

<a name="findByCodeOI40DBCycleModel"></a>
# **findByCodeOI40DBCycleModel**
> OI40DBCycleModel findByCodeOI40DBCycleModel(code)

findByCode

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbCycleModelRepositoryApi;


Oi40DbCycleModelRepositoryApi apiInstance = new Oi40DbCycleModelRepositoryApi();
String code = "code_example"; // String | code
try {
    OI40DBCycleModel result = apiInstance.findByCodeOI40DBCycleModel(code);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbCycleModelRepositoryApi#findByCodeOI40DBCycleModel");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **code** | **String**| code |

### Return type

[**OI40DBCycleModel**](OI40DBCycleModel.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="findByCodesListOI40DBCycleModel"></a>
# **findByCodesListOI40DBCycleModel**
> List&lt;OI40DBCycleModel&gt; findByCodesListOI40DBCycleModel(codes)

findByCodes

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbCycleModelRepositoryApi;


Oi40DbCycleModelRepositoryApi apiInstance = new Oi40DbCycleModelRepositoryApi();
List<String> codes = Arrays.asList(new List<String>()); // List<String> | codes
try {
    List<OI40DBCycleModel> result = apiInstance.findByCodesListOI40DBCycleModel(codes);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbCycleModelRepositoryApi#findByCodesListOI40DBCycleModel");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **codes** | **List&lt;String&gt;**| codes |

### Return type

[**List&lt;OI40DBCycleModel&gt;**](OI40DBCycleModel.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="findByQbeListOI40DBCycleModel"></a>
# **findByQbeListOI40DBCycleModel**
> List&lt;OI40DBCycleModel&gt; findByQbeListOI40DBCycleModel(qbe)

findByQbe

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbCycleModelRepositoryApi;


Oi40DbCycleModelRepositoryApi apiInstance = new Oi40DbCycleModelRepositoryApi();
OI40DBCycleModel qbe = new OI40DBCycleModel(); // OI40DBCycleModel | qbe
try {
    List<OI40DBCycleModel> result = apiInstance.findByQbeListOI40DBCycleModel(qbe);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbCycleModelRepositoryApi#findByQbeListOI40DBCycleModel");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **qbe** | [**OI40DBCycleModel**](OI40DBCycleModel.md)| qbe |

### Return type

[**List&lt;OI40DBCycleModel&gt;**](OI40DBCycleModel.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="findByQbePagedPageOI40DBCycleModel"></a>
# **findByQbePagedPageOI40DBCycleModel**
> PageOI40DBCycleModel findByQbePagedPageOI40DBCycleModel(qbe)

findByQbePaged

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbCycleModelRepositoryApi;


Oi40DbCycleModelRepositoryApi apiInstance = new Oi40DbCycleModelRepositoryApi();
QbeSupportOI40DBCycleModel qbe = new QbeSupportOI40DBCycleModel(); // QbeSupportOI40DBCycleModel | qbe
try {
    PageOI40DBCycleModel result = apiInstance.findByQbePagedPageOI40DBCycleModel(qbe);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbCycleModelRepositoryApi#findByQbePagedPageOI40DBCycleModel");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **qbe** | [**QbeSupportOI40DBCycleModel**](QbeSupportOI40DBCycleModel.md)| qbe |

### Return type

[**PageOI40DBCycleModel**](PageOI40DBCycleModel.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="updateListOI40DBCycleModel"></a>
# **updateListOI40DBCycleModel**
> List&lt;OI40DBCycleModel&gt; updateListOI40DBCycleModel(data)

update

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbCycleModelRepositoryApi;


Oi40DbCycleModelRepositoryApi apiInstance = new Oi40DbCycleModelRepositoryApi();
List<OI40DBCycleModel> data = Arrays.asList(new OI40DBCycleModel()); // List<OI40DBCycleModel> | data
try {
    List<OI40DBCycleModel> result = apiInstance.updateListOI40DBCycleModel(data);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbCycleModelRepositoryApi#updateListOI40DBCycleModel");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **data** | [**List&lt;OI40DBCycleModel&gt;**](OI40DBCycleModel.md)| data |

### Return type

[**List&lt;OI40DBCycleModel&gt;**](OI40DBCycleModel.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="updateSingleOI40DBCycleModel"></a>
# **updateSingleOI40DBCycleModel**
> OI40DBCycleModel updateSingleOI40DBCycleModel(data)

updateSingle

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbCycleModelRepositoryApi;


Oi40DbCycleModelRepositoryApi apiInstance = new Oi40DbCycleModelRepositoryApi();
OI40DBCycleModel data = new OI40DBCycleModel(); // OI40DBCycleModel | data
try {
    OI40DBCycleModel result = apiInstance.updateSingleOI40DBCycleModel(data);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbCycleModelRepositoryApi#updateSingleOI40DBCycleModel");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **data** | [**OI40DBCycleModel**](OI40DBCycleModel.md)| data |

### Return type

[**OI40DBCycleModel**](OI40DBCycleModel.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

