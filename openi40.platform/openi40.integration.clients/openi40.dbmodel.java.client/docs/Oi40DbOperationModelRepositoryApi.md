# Oi40DbOperationModelRepositoryApi

All URIs are relative to *https://localhost:8082/openi40-integration*

Method | HTTP request | Description
------------- | ------------- | -------------
[**deleteByCodeVoid11**](Oi40DbOperationModelRepositoryApi.md#deleteByCodeVoid11) | **GET** /integration/OI40DBOperationModel/deleteByCode/{code} | deleteByCode
[**deleteByCodesVoid11**](Oi40DbOperationModelRepositoryApi.md#deleteByCodesVoid11) | **POST** /integration/OI40DBOperationModel/deleteByCodes | deleteByCodes
[**doAutocompletePageOI40DBOperationModel**](Oi40DbOperationModelRepositoryApi.md#doAutocompletePageOI40DBOperationModel) | **POST** /integration/OI40DBOperationModel/doAutocomplete | doAutocomplete
[**doLookupPageOI40DBOperationModel**](Oi40DbOperationModelRepositoryApi.md#doLookupPageOI40DBOperationModel) | **POST** /integration/OI40DBOperationModel/doLookup | doLookup
[**findAllListOI40DBOperationModel**](Oi40DbOperationModelRepositoryApi.md#findAllListOI40DBOperationModel) | **GET** /integration/OI40DBOperationModel | findAll
[**findAllPageOI40DBOperationModel**](Oi40DbOperationModelRepositoryApi.md#findAllPageOI40DBOperationModel) | **POST** /integration/OI40DBOperationModel/findAllPaged | findAll
[**findByAfterIntegrationTsListOI40DBOperationModel**](Oi40DbOperationModelRepositoryApi.md#findByAfterIntegrationTsListOI40DBOperationModel) | **GET** /integration/OI40DBOperationModel/integratedAfter/{ts} | findByAfterIntegrationTs
[**findByAfterModifiedTimestampListOI40DBOperationModel**](Oi40DbOperationModelRepositoryApi.md#findByAfterModifiedTimestampListOI40DBOperationModel) | **GET** /integration/OI40DBOperationModel/modifiedAfter/{ts} | findByAfterModifiedTimestamp
[**findByCodeOI40DBOperationModel**](Oi40DbOperationModelRepositoryApi.md#findByCodeOI40DBOperationModel) | **GET** /integration/OI40DBOperationModel/byCode/{code} | findByCode
[**findByCodesListOI40DBOperationModel**](Oi40DbOperationModelRepositoryApi.md#findByCodesListOI40DBOperationModel) | **POST** /integration/OI40DBOperationModel/findByCodes | findByCodes
[**findByQbeListOI40DBOperationModel**](Oi40DbOperationModelRepositoryApi.md#findByQbeListOI40DBOperationModel) | **POST** /integration/OI40DBOperationModel/findByQbe | findByQbe
[**findByQbePagedPageOI40DBOperationModel**](Oi40DbOperationModelRepositoryApi.md#findByQbePagedPageOI40DBOperationModel) | **POST** /integration/OI40DBOperationModel/findByQbePaged | findByQbePaged
[**updateListOI40DBOperationModel**](Oi40DbOperationModelRepositoryApi.md#updateListOI40DBOperationModel) | **POST** /integration/OI40DBOperationModel/update | update
[**updateSingleOI40DBOperationModel**](Oi40DbOperationModelRepositoryApi.md#updateSingleOI40DBOperationModel) | **POST** /integration/OI40DBOperationModel/updateSingle | updateSingle


<a name="deleteByCodeVoid11"></a>
# **deleteByCodeVoid11**
> deleteByCodeVoid11(code)

deleteByCode

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbOperationModelRepositoryApi;


Oi40DbOperationModelRepositoryApi apiInstance = new Oi40DbOperationModelRepositoryApi();
String code = "code_example"; // String | code
try {
    apiInstance.deleteByCodeVoid11(code);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbOperationModelRepositoryApi#deleteByCodeVoid11");
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

<a name="deleteByCodesVoid11"></a>
# **deleteByCodesVoid11**
> deleteByCodesVoid11(codes)

deleteByCodes

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbOperationModelRepositoryApi;


Oi40DbOperationModelRepositoryApi apiInstance = new Oi40DbOperationModelRepositoryApi();
List<String> codes = Arrays.asList(new List<String>()); // List<String> | codes
try {
    apiInstance.deleteByCodesVoid11(codes);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbOperationModelRepositoryApi#deleteByCodesVoid11");
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

<a name="doAutocompletePageOI40DBOperationModel"></a>
# **doAutocompletePageOI40DBOperationModel**
> PageOI40DBOperationModel doAutocompletePageOI40DBOperationModel(autoCompleteData)

doAutocomplete

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbOperationModelRepositoryApi;


Oi40DbOperationModelRepositoryApi apiInstance = new Oi40DbOperationModelRepositoryApi();
AutoCompleteData autoCompleteData = new AutoCompleteData(); // AutoCompleteData | autoCompleteData
try {
    PageOI40DBOperationModel result = apiInstance.doAutocompletePageOI40DBOperationModel(autoCompleteData);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbOperationModelRepositoryApi#doAutocompletePageOI40DBOperationModel");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **autoCompleteData** | [**AutoCompleteData**](AutoCompleteData.md)| autoCompleteData |

### Return type

[**PageOI40DBOperationModel**](PageOI40DBOperationModel.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: */*

<a name="doLookupPageOI40DBOperationModel"></a>
# **doLookupPageOI40DBOperationModel**
> PageOI40DBOperationModel doLookupPageOI40DBOperationModel(lookup)

doLookup

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbOperationModelRepositoryApi;


Oi40DbOperationModelRepositoryApi apiInstance = new Oi40DbOperationModelRepositoryApi();
LookupData lookup = new LookupData(); // LookupData | lookup
try {
    PageOI40DBOperationModel result = apiInstance.doLookupPageOI40DBOperationModel(lookup);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbOperationModelRepositoryApi#doLookupPageOI40DBOperationModel");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **lookup** | [**LookupData**](LookupData.md)| lookup |

### Return type

[**PageOI40DBOperationModel**](PageOI40DBOperationModel.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: */*

<a name="findAllListOI40DBOperationModel"></a>
# **findAllListOI40DBOperationModel**
> List&lt;OI40DBOperationModel&gt; findAllListOI40DBOperationModel()

findAll

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbOperationModelRepositoryApi;


Oi40DbOperationModelRepositoryApi apiInstance = new Oi40DbOperationModelRepositoryApi();
try {
    List<OI40DBOperationModel> result = apiInstance.findAllListOI40DBOperationModel();
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbOperationModelRepositoryApi#findAllListOI40DBOperationModel");
    e.printStackTrace();
}
```

### Parameters
This endpoint does not need any parameter.

### Return type

[**List&lt;OI40DBOperationModel&gt;**](OI40DBOperationModel.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="findAllPageOI40DBOperationModel"></a>
# **findAllPageOI40DBOperationModel**
> PageOI40DBOperationModel findAllPageOI40DBOperationModel(p)

findAll

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbOperationModelRepositoryApi;


Oi40DbOperationModelRepositoryApi apiInstance = new Oi40DbOperationModelRepositoryApi();
PageInfo p = new PageInfo(); // PageInfo | p
try {
    PageOI40DBOperationModel result = apiInstance.findAllPageOI40DBOperationModel(p);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbOperationModelRepositoryApi#findAllPageOI40DBOperationModel");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **p** | [**PageInfo**](PageInfo.md)| p |

### Return type

[**PageOI40DBOperationModel**](PageOI40DBOperationModel.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="findByAfterIntegrationTsListOI40DBOperationModel"></a>
# **findByAfterIntegrationTsListOI40DBOperationModel**
> List&lt;OI40DBOperationModel&gt; findByAfterIntegrationTsListOI40DBOperationModel(ts)

findByAfterIntegrationTs

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbOperationModelRepositoryApi;


Oi40DbOperationModelRepositoryApi apiInstance = new Oi40DbOperationModelRepositoryApi();
String ts = "ts_example"; // String | ts
try {
    List<OI40DBOperationModel> result = apiInstance.findByAfterIntegrationTsListOI40DBOperationModel(ts);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbOperationModelRepositoryApi#findByAfterIntegrationTsListOI40DBOperationModel");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **ts** | **String**| ts |

### Return type

[**List&lt;OI40DBOperationModel&gt;**](OI40DBOperationModel.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*

<a name="findByAfterModifiedTimestampListOI40DBOperationModel"></a>
# **findByAfterModifiedTimestampListOI40DBOperationModel**
> List&lt;OI40DBOperationModel&gt; findByAfterModifiedTimestampListOI40DBOperationModel(ts)

findByAfterModifiedTimestamp

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbOperationModelRepositoryApi;


Oi40DbOperationModelRepositoryApi apiInstance = new Oi40DbOperationModelRepositoryApi();
String ts = "ts_example"; // String | ts
try {
    List<OI40DBOperationModel> result = apiInstance.findByAfterModifiedTimestampListOI40DBOperationModel(ts);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbOperationModelRepositoryApi#findByAfterModifiedTimestampListOI40DBOperationModel");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **ts** | **String**| ts |

### Return type

[**List&lt;OI40DBOperationModel&gt;**](OI40DBOperationModel.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*

<a name="findByCodeOI40DBOperationModel"></a>
# **findByCodeOI40DBOperationModel**
> OI40DBOperationModel findByCodeOI40DBOperationModel(code)

findByCode

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbOperationModelRepositoryApi;


Oi40DbOperationModelRepositoryApi apiInstance = new Oi40DbOperationModelRepositoryApi();
String code = "code_example"; // String | code
try {
    OI40DBOperationModel result = apiInstance.findByCodeOI40DBOperationModel(code);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbOperationModelRepositoryApi#findByCodeOI40DBOperationModel");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **code** | **String**| code |

### Return type

[**OI40DBOperationModel**](OI40DBOperationModel.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="findByCodesListOI40DBOperationModel"></a>
# **findByCodesListOI40DBOperationModel**
> List&lt;OI40DBOperationModel&gt; findByCodesListOI40DBOperationModel(codes)

findByCodes

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbOperationModelRepositoryApi;


Oi40DbOperationModelRepositoryApi apiInstance = new Oi40DbOperationModelRepositoryApi();
List<String> codes = Arrays.asList(new List<String>()); // List<String> | codes
try {
    List<OI40DBOperationModel> result = apiInstance.findByCodesListOI40DBOperationModel(codes);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbOperationModelRepositoryApi#findByCodesListOI40DBOperationModel");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **codes** | **List&lt;String&gt;**| codes |

### Return type

[**List&lt;OI40DBOperationModel&gt;**](OI40DBOperationModel.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="findByQbeListOI40DBOperationModel"></a>
# **findByQbeListOI40DBOperationModel**
> List&lt;OI40DBOperationModel&gt; findByQbeListOI40DBOperationModel(qbe)

findByQbe

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbOperationModelRepositoryApi;


Oi40DbOperationModelRepositoryApi apiInstance = new Oi40DbOperationModelRepositoryApi();
OI40DBOperationModel qbe = new OI40DBOperationModel(); // OI40DBOperationModel | qbe
try {
    List<OI40DBOperationModel> result = apiInstance.findByQbeListOI40DBOperationModel(qbe);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbOperationModelRepositoryApi#findByQbeListOI40DBOperationModel");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **qbe** | [**OI40DBOperationModel**](OI40DBOperationModel.md)| qbe |

### Return type

[**List&lt;OI40DBOperationModel&gt;**](OI40DBOperationModel.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="findByQbePagedPageOI40DBOperationModel"></a>
# **findByQbePagedPageOI40DBOperationModel**
> PageOI40DBOperationModel findByQbePagedPageOI40DBOperationModel(qbe)

findByQbePaged

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbOperationModelRepositoryApi;


Oi40DbOperationModelRepositoryApi apiInstance = new Oi40DbOperationModelRepositoryApi();
QbeSupportOI40DBOperationModel qbe = new QbeSupportOI40DBOperationModel(); // QbeSupportOI40DBOperationModel | qbe
try {
    PageOI40DBOperationModel result = apiInstance.findByQbePagedPageOI40DBOperationModel(qbe);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbOperationModelRepositoryApi#findByQbePagedPageOI40DBOperationModel");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **qbe** | [**QbeSupportOI40DBOperationModel**](QbeSupportOI40DBOperationModel.md)| qbe |

### Return type

[**PageOI40DBOperationModel**](PageOI40DBOperationModel.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="updateListOI40DBOperationModel"></a>
# **updateListOI40DBOperationModel**
> List&lt;OI40DBOperationModel&gt; updateListOI40DBOperationModel(data)

update

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbOperationModelRepositoryApi;


Oi40DbOperationModelRepositoryApi apiInstance = new Oi40DbOperationModelRepositoryApi();
List<OI40DBOperationModel> data = Arrays.asList(new OI40DBOperationModel()); // List<OI40DBOperationModel> | data
try {
    List<OI40DBOperationModel> result = apiInstance.updateListOI40DBOperationModel(data);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbOperationModelRepositoryApi#updateListOI40DBOperationModel");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **data** | [**List&lt;OI40DBOperationModel&gt;**](OI40DBOperationModel.md)| data |

### Return type

[**List&lt;OI40DBOperationModel&gt;**](OI40DBOperationModel.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="updateSingleOI40DBOperationModel"></a>
# **updateSingleOI40DBOperationModel**
> OI40DBOperationModel updateSingleOI40DBOperationModel(data)

updateSingle

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbOperationModelRepositoryApi;


Oi40DbOperationModelRepositoryApi apiInstance = new Oi40DbOperationModelRepositoryApi();
OI40DBOperationModel data = new OI40DBOperationModel(); // OI40DBOperationModel | data
try {
    OI40DBOperationModel result = apiInstance.updateSingleOI40DBOperationModel(data);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbOperationModelRepositoryApi#updateSingleOI40DBOperationModel");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **data** | [**OI40DBOperationModel**](OI40DBOperationModel.md)| data |

### Return type

[**OI40DBOperationModel**](OI40DBOperationModel.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

