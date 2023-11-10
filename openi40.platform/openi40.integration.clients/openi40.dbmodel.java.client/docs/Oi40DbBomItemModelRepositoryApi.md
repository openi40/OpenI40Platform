# Oi40DbBomItemModelRepositoryApi

All URIs are relative to *https://localhost:8082/openi40-integration*

Method | HTTP request | Description
------------- | ------------- | -------------
[**deleteByCodeVoid2**](Oi40DbBomItemModelRepositoryApi.md#deleteByCodeVoid2) | **GET** /integration/OI40DBBomItemModel/deleteByCode/{code} | deleteByCode
[**deleteByCodesVoid2**](Oi40DbBomItemModelRepositoryApi.md#deleteByCodesVoid2) | **POST** /integration/OI40DBBomItemModel/deleteByCodes | deleteByCodes
[**doAutocompletePageOI40DBBomItemModel**](Oi40DbBomItemModelRepositoryApi.md#doAutocompletePageOI40DBBomItemModel) | **POST** /integration/OI40DBBomItemModel/doAutocomplete | doAutocomplete
[**doLookupPageOI40DBBomItemModel**](Oi40DbBomItemModelRepositoryApi.md#doLookupPageOI40DBBomItemModel) | **POST** /integration/OI40DBBomItemModel/doLookup | doLookup
[**findAllListOI40DBBomItemModel**](Oi40DbBomItemModelRepositoryApi.md#findAllListOI40DBBomItemModel) | **GET** /integration/OI40DBBomItemModel | findAll
[**findAllPageOI40DBBomItemModel**](Oi40DbBomItemModelRepositoryApi.md#findAllPageOI40DBBomItemModel) | **POST** /integration/OI40DBBomItemModel/findAllPaged | findAll
[**findByAfterIntegrationTsListOI40DBBomItemModel**](Oi40DbBomItemModelRepositoryApi.md#findByAfterIntegrationTsListOI40DBBomItemModel) | **GET** /integration/OI40DBBomItemModel/integratedAfter/{ts} | findByAfterIntegrationTs
[**findByAfterModifiedTimestampListOI40DBBomItemModel**](Oi40DbBomItemModelRepositoryApi.md#findByAfterModifiedTimestampListOI40DBBomItemModel) | **GET** /integration/OI40DBBomItemModel/modifiedAfter/{ts} | findByAfterModifiedTimestamp
[**findByCodeOI40DBBomItemModel**](Oi40DbBomItemModelRepositoryApi.md#findByCodeOI40DBBomItemModel) | **GET** /integration/OI40DBBomItemModel/byCode/{code} | findByCode
[**findByCodesListOI40DBBomItemModel**](Oi40DbBomItemModelRepositoryApi.md#findByCodesListOI40DBBomItemModel) | **POST** /integration/OI40DBBomItemModel/findByCodes | findByCodes
[**findByQbeListOI40DBBomItemModel**](Oi40DbBomItemModelRepositoryApi.md#findByQbeListOI40DBBomItemModel) | **POST** /integration/OI40DBBomItemModel/findByQbe | findByQbe
[**findByQbePagedPageOI40DBBomItemModel**](Oi40DbBomItemModelRepositoryApi.md#findByQbePagedPageOI40DBBomItemModel) | **POST** /integration/OI40DBBomItemModel/findByQbePaged | findByQbePaged
[**updateListOI40DBBomItemModel**](Oi40DbBomItemModelRepositoryApi.md#updateListOI40DBBomItemModel) | **POST** /integration/OI40DBBomItemModel/update | update
[**updateSingleOI40DBBomItemModel**](Oi40DbBomItemModelRepositoryApi.md#updateSingleOI40DBBomItemModel) | **POST** /integration/OI40DBBomItemModel/updateSingle | updateSingle


<a name="deleteByCodeVoid2"></a>
# **deleteByCodeVoid2**
> deleteByCodeVoid2(code)

deleteByCode

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbBomItemModelRepositoryApi;


Oi40DbBomItemModelRepositoryApi apiInstance = new Oi40DbBomItemModelRepositoryApi();
String code = "code_example"; // String | code
try {
    apiInstance.deleteByCodeVoid2(code);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbBomItemModelRepositoryApi#deleteByCodeVoid2");
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

<a name="deleteByCodesVoid2"></a>
# **deleteByCodesVoid2**
> deleteByCodesVoid2(codes)

deleteByCodes

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbBomItemModelRepositoryApi;


Oi40DbBomItemModelRepositoryApi apiInstance = new Oi40DbBomItemModelRepositoryApi();
List<String> codes = Arrays.asList(new List<String>()); // List<String> | codes
try {
    apiInstance.deleteByCodesVoid2(codes);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbBomItemModelRepositoryApi#deleteByCodesVoid2");
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

<a name="doAutocompletePageOI40DBBomItemModel"></a>
# **doAutocompletePageOI40DBBomItemModel**
> PageOI40DBBomItemModel doAutocompletePageOI40DBBomItemModel(autoCompleteData)

doAutocomplete

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbBomItemModelRepositoryApi;


Oi40DbBomItemModelRepositoryApi apiInstance = new Oi40DbBomItemModelRepositoryApi();
AutoCompleteData autoCompleteData = new AutoCompleteData(); // AutoCompleteData | autoCompleteData
try {
    PageOI40DBBomItemModel result = apiInstance.doAutocompletePageOI40DBBomItemModel(autoCompleteData);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbBomItemModelRepositoryApi#doAutocompletePageOI40DBBomItemModel");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **autoCompleteData** | [**AutoCompleteData**](AutoCompleteData.md)| autoCompleteData |

### Return type

[**PageOI40DBBomItemModel**](PageOI40DBBomItemModel.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: */*

<a name="doLookupPageOI40DBBomItemModel"></a>
# **doLookupPageOI40DBBomItemModel**
> PageOI40DBBomItemModel doLookupPageOI40DBBomItemModel(lookup)

doLookup

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbBomItemModelRepositoryApi;


Oi40DbBomItemModelRepositoryApi apiInstance = new Oi40DbBomItemModelRepositoryApi();
LookupData lookup = new LookupData(); // LookupData | lookup
try {
    PageOI40DBBomItemModel result = apiInstance.doLookupPageOI40DBBomItemModel(lookup);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbBomItemModelRepositoryApi#doLookupPageOI40DBBomItemModel");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **lookup** | [**LookupData**](LookupData.md)| lookup |

### Return type

[**PageOI40DBBomItemModel**](PageOI40DBBomItemModel.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: */*

<a name="findAllListOI40DBBomItemModel"></a>
# **findAllListOI40DBBomItemModel**
> List&lt;OI40DBBomItemModel&gt; findAllListOI40DBBomItemModel()

findAll

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbBomItemModelRepositoryApi;


Oi40DbBomItemModelRepositoryApi apiInstance = new Oi40DbBomItemModelRepositoryApi();
try {
    List<OI40DBBomItemModel> result = apiInstance.findAllListOI40DBBomItemModel();
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbBomItemModelRepositoryApi#findAllListOI40DBBomItemModel");
    e.printStackTrace();
}
```

### Parameters
This endpoint does not need any parameter.

### Return type

[**List&lt;OI40DBBomItemModel&gt;**](OI40DBBomItemModel.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="findAllPageOI40DBBomItemModel"></a>
# **findAllPageOI40DBBomItemModel**
> PageOI40DBBomItemModel findAllPageOI40DBBomItemModel(p)

findAll

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbBomItemModelRepositoryApi;


Oi40DbBomItemModelRepositoryApi apiInstance = new Oi40DbBomItemModelRepositoryApi();
PageInfo p = new PageInfo(); // PageInfo | p
try {
    PageOI40DBBomItemModel result = apiInstance.findAllPageOI40DBBomItemModel(p);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbBomItemModelRepositoryApi#findAllPageOI40DBBomItemModel");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **p** | [**PageInfo**](PageInfo.md)| p |

### Return type

[**PageOI40DBBomItemModel**](PageOI40DBBomItemModel.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="findByAfterIntegrationTsListOI40DBBomItemModel"></a>
# **findByAfterIntegrationTsListOI40DBBomItemModel**
> List&lt;OI40DBBomItemModel&gt; findByAfterIntegrationTsListOI40DBBomItemModel(ts)

findByAfterIntegrationTs

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbBomItemModelRepositoryApi;


Oi40DbBomItemModelRepositoryApi apiInstance = new Oi40DbBomItemModelRepositoryApi();
String ts = "ts_example"; // String | ts
try {
    List<OI40DBBomItemModel> result = apiInstance.findByAfterIntegrationTsListOI40DBBomItemModel(ts);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbBomItemModelRepositoryApi#findByAfterIntegrationTsListOI40DBBomItemModel");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **ts** | **String**| ts |

### Return type

[**List&lt;OI40DBBomItemModel&gt;**](OI40DBBomItemModel.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*

<a name="findByAfterModifiedTimestampListOI40DBBomItemModel"></a>
# **findByAfterModifiedTimestampListOI40DBBomItemModel**
> List&lt;OI40DBBomItemModel&gt; findByAfterModifiedTimestampListOI40DBBomItemModel(ts)

findByAfterModifiedTimestamp

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbBomItemModelRepositoryApi;


Oi40DbBomItemModelRepositoryApi apiInstance = new Oi40DbBomItemModelRepositoryApi();
String ts = "ts_example"; // String | ts
try {
    List<OI40DBBomItemModel> result = apiInstance.findByAfterModifiedTimestampListOI40DBBomItemModel(ts);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbBomItemModelRepositoryApi#findByAfterModifiedTimestampListOI40DBBomItemModel");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **ts** | **String**| ts |

### Return type

[**List&lt;OI40DBBomItemModel&gt;**](OI40DBBomItemModel.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*

<a name="findByCodeOI40DBBomItemModel"></a>
# **findByCodeOI40DBBomItemModel**
> OI40DBBomItemModel findByCodeOI40DBBomItemModel(code)

findByCode

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbBomItemModelRepositoryApi;


Oi40DbBomItemModelRepositoryApi apiInstance = new Oi40DbBomItemModelRepositoryApi();
String code = "code_example"; // String | code
try {
    OI40DBBomItemModel result = apiInstance.findByCodeOI40DBBomItemModel(code);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbBomItemModelRepositoryApi#findByCodeOI40DBBomItemModel");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **code** | **String**| code |

### Return type

[**OI40DBBomItemModel**](OI40DBBomItemModel.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="findByCodesListOI40DBBomItemModel"></a>
# **findByCodesListOI40DBBomItemModel**
> List&lt;OI40DBBomItemModel&gt; findByCodesListOI40DBBomItemModel(codes)

findByCodes

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbBomItemModelRepositoryApi;


Oi40DbBomItemModelRepositoryApi apiInstance = new Oi40DbBomItemModelRepositoryApi();
List<String> codes = Arrays.asList(new List<String>()); // List<String> | codes
try {
    List<OI40DBBomItemModel> result = apiInstance.findByCodesListOI40DBBomItemModel(codes);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbBomItemModelRepositoryApi#findByCodesListOI40DBBomItemModel");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **codes** | **List&lt;String&gt;**| codes |

### Return type

[**List&lt;OI40DBBomItemModel&gt;**](OI40DBBomItemModel.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="findByQbeListOI40DBBomItemModel"></a>
# **findByQbeListOI40DBBomItemModel**
> List&lt;OI40DBBomItemModel&gt; findByQbeListOI40DBBomItemModel(qbe)

findByQbe

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbBomItemModelRepositoryApi;


Oi40DbBomItemModelRepositoryApi apiInstance = new Oi40DbBomItemModelRepositoryApi();
OI40DBBomItemModel qbe = new OI40DBBomItemModel(); // OI40DBBomItemModel | qbe
try {
    List<OI40DBBomItemModel> result = apiInstance.findByQbeListOI40DBBomItemModel(qbe);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbBomItemModelRepositoryApi#findByQbeListOI40DBBomItemModel");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **qbe** | [**OI40DBBomItemModel**](OI40DBBomItemModel.md)| qbe |

### Return type

[**List&lt;OI40DBBomItemModel&gt;**](OI40DBBomItemModel.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="findByQbePagedPageOI40DBBomItemModel"></a>
# **findByQbePagedPageOI40DBBomItemModel**
> PageOI40DBBomItemModel findByQbePagedPageOI40DBBomItemModel(qbe)

findByQbePaged

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbBomItemModelRepositoryApi;


Oi40DbBomItemModelRepositoryApi apiInstance = new Oi40DbBomItemModelRepositoryApi();
QbeSupportOI40DBBomItemModel qbe = new QbeSupportOI40DBBomItemModel(); // QbeSupportOI40DBBomItemModel | qbe
try {
    PageOI40DBBomItemModel result = apiInstance.findByQbePagedPageOI40DBBomItemModel(qbe);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbBomItemModelRepositoryApi#findByQbePagedPageOI40DBBomItemModel");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **qbe** | [**QbeSupportOI40DBBomItemModel**](QbeSupportOI40DBBomItemModel.md)| qbe |

### Return type

[**PageOI40DBBomItemModel**](PageOI40DBBomItemModel.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="updateListOI40DBBomItemModel"></a>
# **updateListOI40DBBomItemModel**
> List&lt;OI40DBBomItemModel&gt; updateListOI40DBBomItemModel(data)

update

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbBomItemModelRepositoryApi;


Oi40DbBomItemModelRepositoryApi apiInstance = new Oi40DbBomItemModelRepositoryApi();
List<OI40DBBomItemModel> data = Arrays.asList(new OI40DBBomItemModel()); // List<OI40DBBomItemModel> | data
try {
    List<OI40DBBomItemModel> result = apiInstance.updateListOI40DBBomItemModel(data);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbBomItemModelRepositoryApi#updateListOI40DBBomItemModel");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **data** | [**List&lt;OI40DBBomItemModel&gt;**](OI40DBBomItemModel.md)| data |

### Return type

[**List&lt;OI40DBBomItemModel&gt;**](OI40DBBomItemModel.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="updateSingleOI40DBBomItemModel"></a>
# **updateSingleOI40DBBomItemModel**
> OI40DBBomItemModel updateSingleOI40DBBomItemModel(data)

updateSingle

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbBomItemModelRepositoryApi;


Oi40DbBomItemModelRepositoryApi apiInstance = new Oi40DbBomItemModelRepositoryApi();
OI40DBBomItemModel data = new OI40DBBomItemModel(); // OI40DBBomItemModel | data
try {
    OI40DBBomItemModel result = apiInstance.updateSingleOI40DBBomItemModel(data);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbBomItemModelRepositoryApi#updateSingleOI40DBBomItemModel");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **data** | [**OI40DBBomItemModel**](OI40DBBomItemModel.md)| data |

### Return type

[**OI40DBBomItemModel**](OI40DBBomItemModel.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

