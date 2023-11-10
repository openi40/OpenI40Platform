# Oi40DbPeggingRepositoryApi

All URIs are relative to *https://localhost:8082/openi40-integration*

Method | HTTP request | Description
------------- | ------------- | -------------
[**deleteByCodeVoid13**](Oi40DbPeggingRepositoryApi.md#deleteByCodeVoid13) | **GET** /integration/OI40DBPegging/deleteByCode/{code} | deleteByCode
[**deleteByCodesVoid13**](Oi40DbPeggingRepositoryApi.md#deleteByCodesVoid13) | **POST** /integration/OI40DBPegging/deleteByCodes | deleteByCodes
[**doAutocompletePageOI40DBPegging**](Oi40DbPeggingRepositoryApi.md#doAutocompletePageOI40DBPegging) | **POST** /integration/OI40DBPegging/doAutocomplete | doAutocomplete
[**doLookupPageOI40DBPegging**](Oi40DbPeggingRepositoryApi.md#doLookupPageOI40DBPegging) | **POST** /integration/OI40DBPegging/doLookup | doLookup
[**findAllListOI40DBPegging**](Oi40DbPeggingRepositoryApi.md#findAllListOI40DBPegging) | **GET** /integration/OI40DBPegging | findAll
[**findAllPageOI40DBPegging**](Oi40DbPeggingRepositoryApi.md#findAllPageOI40DBPegging) | **POST** /integration/OI40DBPegging/findAllPaged | findAll
[**findByAfterIntegrationTsListOI40DBPegging**](Oi40DbPeggingRepositoryApi.md#findByAfterIntegrationTsListOI40DBPegging) | **GET** /integration/OI40DBPegging/integratedAfter/{ts} | findByAfterIntegrationTs
[**findByAfterModifiedTimestampListOI40DBPegging**](Oi40DbPeggingRepositoryApi.md#findByAfterModifiedTimestampListOI40DBPegging) | **GET** /integration/OI40DBPegging/modifiedAfter/{ts} | findByAfterModifiedTimestamp
[**findByCodeOI40DBPegging**](Oi40DbPeggingRepositoryApi.md#findByCodeOI40DBPegging) | **GET** /integration/OI40DBPegging/byCode/{code} | findByCode
[**findByCodesListOI40DBPegging**](Oi40DbPeggingRepositoryApi.md#findByCodesListOI40DBPegging) | **POST** /integration/OI40DBPegging/findByCodes | findByCodes
[**findByQbeListOI40DBPegging**](Oi40DbPeggingRepositoryApi.md#findByQbeListOI40DBPegging) | **POST** /integration/OI40DBPegging/findByQbe | findByQbe
[**findByQbePagedPageOI40DBPegging**](Oi40DbPeggingRepositoryApi.md#findByQbePagedPageOI40DBPegging) | **POST** /integration/OI40DBPegging/findByQbePaged | findByQbePaged
[**updateListOI40DBPegging**](Oi40DbPeggingRepositoryApi.md#updateListOI40DBPegging) | **POST** /integration/OI40DBPegging/update | update
[**updateSingleOI40DBPegging**](Oi40DbPeggingRepositoryApi.md#updateSingleOI40DBPegging) | **POST** /integration/OI40DBPegging/updateSingle | updateSingle


<a name="deleteByCodeVoid13"></a>
# **deleteByCodeVoid13**
> deleteByCodeVoid13(code)

deleteByCode

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbPeggingRepositoryApi;


Oi40DbPeggingRepositoryApi apiInstance = new Oi40DbPeggingRepositoryApi();
String code = "code_example"; // String | code
try {
    apiInstance.deleteByCodeVoid13(code);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbPeggingRepositoryApi#deleteByCodeVoid13");
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

<a name="deleteByCodesVoid13"></a>
# **deleteByCodesVoid13**
> deleteByCodesVoid13(codes)

deleteByCodes

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbPeggingRepositoryApi;


Oi40DbPeggingRepositoryApi apiInstance = new Oi40DbPeggingRepositoryApi();
List<String> codes = Arrays.asList(new List<String>()); // List<String> | codes
try {
    apiInstance.deleteByCodesVoid13(codes);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbPeggingRepositoryApi#deleteByCodesVoid13");
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

<a name="doAutocompletePageOI40DBPegging"></a>
# **doAutocompletePageOI40DBPegging**
> PageOI40DBPegging doAutocompletePageOI40DBPegging(autoCompleteData)

doAutocomplete

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbPeggingRepositoryApi;


Oi40DbPeggingRepositoryApi apiInstance = new Oi40DbPeggingRepositoryApi();
AutoCompleteData autoCompleteData = new AutoCompleteData(); // AutoCompleteData | autoCompleteData
try {
    PageOI40DBPegging result = apiInstance.doAutocompletePageOI40DBPegging(autoCompleteData);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbPeggingRepositoryApi#doAutocompletePageOI40DBPegging");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **autoCompleteData** | [**AutoCompleteData**](AutoCompleteData.md)| autoCompleteData |

### Return type

[**PageOI40DBPegging**](PageOI40DBPegging.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: */*

<a name="doLookupPageOI40DBPegging"></a>
# **doLookupPageOI40DBPegging**
> PageOI40DBPegging doLookupPageOI40DBPegging(lookup)

doLookup

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbPeggingRepositoryApi;


Oi40DbPeggingRepositoryApi apiInstance = new Oi40DbPeggingRepositoryApi();
LookupData lookup = new LookupData(); // LookupData | lookup
try {
    PageOI40DBPegging result = apiInstance.doLookupPageOI40DBPegging(lookup);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbPeggingRepositoryApi#doLookupPageOI40DBPegging");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **lookup** | [**LookupData**](LookupData.md)| lookup |

### Return type

[**PageOI40DBPegging**](PageOI40DBPegging.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: */*

<a name="findAllListOI40DBPegging"></a>
# **findAllListOI40DBPegging**
> List&lt;OI40DBPegging&gt; findAllListOI40DBPegging()

findAll

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbPeggingRepositoryApi;


Oi40DbPeggingRepositoryApi apiInstance = new Oi40DbPeggingRepositoryApi();
try {
    List<OI40DBPegging> result = apiInstance.findAllListOI40DBPegging();
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbPeggingRepositoryApi#findAllListOI40DBPegging");
    e.printStackTrace();
}
```

### Parameters
This endpoint does not need any parameter.

### Return type

[**List&lt;OI40DBPegging&gt;**](OI40DBPegging.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="findAllPageOI40DBPegging"></a>
# **findAllPageOI40DBPegging**
> PageOI40DBPegging findAllPageOI40DBPegging(p)

findAll

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbPeggingRepositoryApi;


Oi40DbPeggingRepositoryApi apiInstance = new Oi40DbPeggingRepositoryApi();
PageInfo p = new PageInfo(); // PageInfo | p
try {
    PageOI40DBPegging result = apiInstance.findAllPageOI40DBPegging(p);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbPeggingRepositoryApi#findAllPageOI40DBPegging");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **p** | [**PageInfo**](PageInfo.md)| p |

### Return type

[**PageOI40DBPegging**](PageOI40DBPegging.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="findByAfterIntegrationTsListOI40DBPegging"></a>
# **findByAfterIntegrationTsListOI40DBPegging**
> List&lt;OI40DBPegging&gt; findByAfterIntegrationTsListOI40DBPegging(ts)

findByAfterIntegrationTs

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbPeggingRepositoryApi;


Oi40DbPeggingRepositoryApi apiInstance = new Oi40DbPeggingRepositoryApi();
String ts = "ts_example"; // String | ts
try {
    List<OI40DBPegging> result = apiInstance.findByAfterIntegrationTsListOI40DBPegging(ts);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbPeggingRepositoryApi#findByAfterIntegrationTsListOI40DBPegging");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **ts** | **String**| ts |

### Return type

[**List&lt;OI40DBPegging&gt;**](OI40DBPegging.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*

<a name="findByAfterModifiedTimestampListOI40DBPegging"></a>
# **findByAfterModifiedTimestampListOI40DBPegging**
> List&lt;OI40DBPegging&gt; findByAfterModifiedTimestampListOI40DBPegging(ts)

findByAfterModifiedTimestamp

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbPeggingRepositoryApi;


Oi40DbPeggingRepositoryApi apiInstance = new Oi40DbPeggingRepositoryApi();
String ts = "ts_example"; // String | ts
try {
    List<OI40DBPegging> result = apiInstance.findByAfterModifiedTimestampListOI40DBPegging(ts);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbPeggingRepositoryApi#findByAfterModifiedTimestampListOI40DBPegging");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **ts** | **String**| ts |

### Return type

[**List&lt;OI40DBPegging&gt;**](OI40DBPegging.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*

<a name="findByCodeOI40DBPegging"></a>
# **findByCodeOI40DBPegging**
> OI40DBPegging findByCodeOI40DBPegging(code)

findByCode

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbPeggingRepositoryApi;


Oi40DbPeggingRepositoryApi apiInstance = new Oi40DbPeggingRepositoryApi();
String code = "code_example"; // String | code
try {
    OI40DBPegging result = apiInstance.findByCodeOI40DBPegging(code);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbPeggingRepositoryApi#findByCodeOI40DBPegging");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **code** | **String**| code |

### Return type

[**OI40DBPegging**](OI40DBPegging.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="findByCodesListOI40DBPegging"></a>
# **findByCodesListOI40DBPegging**
> List&lt;OI40DBPegging&gt; findByCodesListOI40DBPegging(codes)

findByCodes

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbPeggingRepositoryApi;


Oi40DbPeggingRepositoryApi apiInstance = new Oi40DbPeggingRepositoryApi();
List<String> codes = Arrays.asList(new List<String>()); // List<String> | codes
try {
    List<OI40DBPegging> result = apiInstance.findByCodesListOI40DBPegging(codes);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbPeggingRepositoryApi#findByCodesListOI40DBPegging");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **codes** | **List&lt;String&gt;**| codes |

### Return type

[**List&lt;OI40DBPegging&gt;**](OI40DBPegging.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="findByQbeListOI40DBPegging"></a>
# **findByQbeListOI40DBPegging**
> List&lt;OI40DBPegging&gt; findByQbeListOI40DBPegging(qbe)

findByQbe

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbPeggingRepositoryApi;


Oi40DbPeggingRepositoryApi apiInstance = new Oi40DbPeggingRepositoryApi();
OI40DBPegging qbe = new OI40DBPegging(); // OI40DBPegging | qbe
try {
    List<OI40DBPegging> result = apiInstance.findByQbeListOI40DBPegging(qbe);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbPeggingRepositoryApi#findByQbeListOI40DBPegging");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **qbe** | [**OI40DBPegging**](OI40DBPegging.md)| qbe |

### Return type

[**List&lt;OI40DBPegging&gt;**](OI40DBPegging.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="findByQbePagedPageOI40DBPegging"></a>
# **findByQbePagedPageOI40DBPegging**
> PageOI40DBPegging findByQbePagedPageOI40DBPegging(qbe)

findByQbePaged

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbPeggingRepositoryApi;


Oi40DbPeggingRepositoryApi apiInstance = new Oi40DbPeggingRepositoryApi();
QbeSupportOI40DBPegging qbe = new QbeSupportOI40DBPegging(); // QbeSupportOI40DBPegging | qbe
try {
    PageOI40DBPegging result = apiInstance.findByQbePagedPageOI40DBPegging(qbe);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbPeggingRepositoryApi#findByQbePagedPageOI40DBPegging");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **qbe** | [**QbeSupportOI40DBPegging**](QbeSupportOI40DBPegging.md)| qbe |

### Return type

[**PageOI40DBPegging**](PageOI40DBPegging.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="updateListOI40DBPegging"></a>
# **updateListOI40DBPegging**
> List&lt;OI40DBPegging&gt; updateListOI40DBPegging(data)

update

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbPeggingRepositoryApi;


Oi40DbPeggingRepositoryApi apiInstance = new Oi40DbPeggingRepositoryApi();
List<OI40DBPegging> data = Arrays.asList(new OI40DBPegging()); // List<OI40DBPegging> | data
try {
    List<OI40DBPegging> result = apiInstance.updateListOI40DBPegging(data);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbPeggingRepositoryApi#updateListOI40DBPegging");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **data** | [**List&lt;OI40DBPegging&gt;**](OI40DBPegging.md)| data |

### Return type

[**List&lt;OI40DBPegging&gt;**](OI40DBPegging.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="updateSingleOI40DBPegging"></a>
# **updateSingleOI40DBPegging**
> OI40DBPegging updateSingleOI40DBPegging(data)

updateSingle

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbPeggingRepositoryApi;


Oi40DbPeggingRepositoryApi apiInstance = new Oi40DbPeggingRepositoryApi();
OI40DBPegging data = new OI40DBPegging(); // OI40DBPegging | data
try {
    OI40DBPegging result = apiInstance.updateSingleOI40DBPegging(data);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbPeggingRepositoryApi#updateSingleOI40DBPegging");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **data** | [**OI40DBPegging**](OI40DBPegging.md)| data |

### Return type

[**OI40DBPegging**](OI40DBPegging.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

