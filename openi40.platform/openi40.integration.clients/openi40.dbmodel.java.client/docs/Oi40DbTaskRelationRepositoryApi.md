# Oi40DbTaskRelationRepositoryApi

All URIs are relative to *https://localhost:8082/openi40-integration*

Method | HTTP request | Description
------------- | ------------- | -------------
[**deleteByCodeVoid30**](Oi40DbTaskRelationRepositoryApi.md#deleteByCodeVoid30) | **GET** /integration/OI40DBTaskRelation/deleteByCode/{code} | deleteByCode
[**deleteByCodesVoid30**](Oi40DbTaskRelationRepositoryApi.md#deleteByCodesVoid30) | **POST** /integration/OI40DBTaskRelation/deleteByCodes | deleteByCodes
[**doAutocompletePageOI40DBTaskRelation**](Oi40DbTaskRelationRepositoryApi.md#doAutocompletePageOI40DBTaskRelation) | **POST** /integration/OI40DBTaskRelation/doAutocomplete | doAutocomplete
[**doLookupPageOI40DBTaskRelation**](Oi40DbTaskRelationRepositoryApi.md#doLookupPageOI40DBTaskRelation) | **POST** /integration/OI40DBTaskRelation/doLookup | doLookup
[**findAllListOI40DBTaskRelation**](Oi40DbTaskRelationRepositoryApi.md#findAllListOI40DBTaskRelation) | **GET** /integration/OI40DBTaskRelation | findAll
[**findAllPageOI40DBTaskRelation**](Oi40DbTaskRelationRepositoryApi.md#findAllPageOI40DBTaskRelation) | **POST** /integration/OI40DBTaskRelation/findAllPaged | findAll
[**findByAfterIntegrationTsListOI40DBTaskRelation**](Oi40DbTaskRelationRepositoryApi.md#findByAfterIntegrationTsListOI40DBTaskRelation) | **GET** /integration/OI40DBTaskRelation/integratedAfter/{ts} | findByAfterIntegrationTs
[**findByAfterModifiedTimestampListOI40DBTaskRelation**](Oi40DbTaskRelationRepositoryApi.md#findByAfterModifiedTimestampListOI40DBTaskRelation) | **GET** /integration/OI40DBTaskRelation/modifiedAfter/{ts} | findByAfterModifiedTimestamp
[**findByCodeOI40DBTaskRelation**](Oi40DbTaskRelationRepositoryApi.md#findByCodeOI40DBTaskRelation) | **GET** /integration/OI40DBTaskRelation/byCode/{code} | findByCode
[**findByCodesListOI40DBTaskRelation**](Oi40DbTaskRelationRepositoryApi.md#findByCodesListOI40DBTaskRelation) | **POST** /integration/OI40DBTaskRelation/findByCodes | findByCodes
[**findByQbeListOI40DBTaskRelation**](Oi40DbTaskRelationRepositoryApi.md#findByQbeListOI40DBTaskRelation) | **POST** /integration/OI40DBTaskRelation/findByQbe | findByQbe
[**findByQbePagedPageOI40DBTaskRelation**](Oi40DbTaskRelationRepositoryApi.md#findByQbePagedPageOI40DBTaskRelation) | **POST** /integration/OI40DBTaskRelation/findByQbePaged | findByQbePaged
[**updateListOI40DBTaskRelation**](Oi40DbTaskRelationRepositoryApi.md#updateListOI40DBTaskRelation) | **POST** /integration/OI40DBTaskRelation/update | update
[**updateSingleOI40DBTaskRelation**](Oi40DbTaskRelationRepositoryApi.md#updateSingleOI40DBTaskRelation) | **POST** /integration/OI40DBTaskRelation/updateSingle | updateSingle


<a name="deleteByCodeVoid30"></a>
# **deleteByCodeVoid30**
> deleteByCodeVoid30(code)

deleteByCode

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbTaskRelationRepositoryApi;


Oi40DbTaskRelationRepositoryApi apiInstance = new Oi40DbTaskRelationRepositoryApi();
String code = "code_example"; // String | code
try {
    apiInstance.deleteByCodeVoid30(code);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbTaskRelationRepositoryApi#deleteByCodeVoid30");
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

<a name="deleteByCodesVoid30"></a>
# **deleteByCodesVoid30**
> deleteByCodesVoid30(codes)

deleteByCodes

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbTaskRelationRepositoryApi;


Oi40DbTaskRelationRepositoryApi apiInstance = new Oi40DbTaskRelationRepositoryApi();
List<String> codes = Arrays.asList(new List<String>()); // List<String> | codes
try {
    apiInstance.deleteByCodesVoid30(codes);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbTaskRelationRepositoryApi#deleteByCodesVoid30");
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

<a name="doAutocompletePageOI40DBTaskRelation"></a>
# **doAutocompletePageOI40DBTaskRelation**
> PageOI40DBTaskRelation doAutocompletePageOI40DBTaskRelation(autoCompleteData)

doAutocomplete

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbTaskRelationRepositoryApi;


Oi40DbTaskRelationRepositoryApi apiInstance = new Oi40DbTaskRelationRepositoryApi();
AutoCompleteData autoCompleteData = new AutoCompleteData(); // AutoCompleteData | autoCompleteData
try {
    PageOI40DBTaskRelation result = apiInstance.doAutocompletePageOI40DBTaskRelation(autoCompleteData);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbTaskRelationRepositoryApi#doAutocompletePageOI40DBTaskRelation");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **autoCompleteData** | [**AutoCompleteData**](AutoCompleteData.md)| autoCompleteData |

### Return type

[**PageOI40DBTaskRelation**](PageOI40DBTaskRelation.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: */*

<a name="doLookupPageOI40DBTaskRelation"></a>
# **doLookupPageOI40DBTaskRelation**
> PageOI40DBTaskRelation doLookupPageOI40DBTaskRelation(lookup)

doLookup

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbTaskRelationRepositoryApi;


Oi40DbTaskRelationRepositoryApi apiInstance = new Oi40DbTaskRelationRepositoryApi();
LookupData lookup = new LookupData(); // LookupData | lookup
try {
    PageOI40DBTaskRelation result = apiInstance.doLookupPageOI40DBTaskRelation(lookup);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbTaskRelationRepositoryApi#doLookupPageOI40DBTaskRelation");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **lookup** | [**LookupData**](LookupData.md)| lookup |

### Return type

[**PageOI40DBTaskRelation**](PageOI40DBTaskRelation.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: */*

<a name="findAllListOI40DBTaskRelation"></a>
# **findAllListOI40DBTaskRelation**
> List&lt;OI40DBTaskRelation&gt; findAllListOI40DBTaskRelation()

findAll

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbTaskRelationRepositoryApi;


Oi40DbTaskRelationRepositoryApi apiInstance = new Oi40DbTaskRelationRepositoryApi();
try {
    List<OI40DBTaskRelation> result = apiInstance.findAllListOI40DBTaskRelation();
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbTaskRelationRepositoryApi#findAllListOI40DBTaskRelation");
    e.printStackTrace();
}
```

### Parameters
This endpoint does not need any parameter.

### Return type

[**List&lt;OI40DBTaskRelation&gt;**](OI40DBTaskRelation.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="findAllPageOI40DBTaskRelation"></a>
# **findAllPageOI40DBTaskRelation**
> PageOI40DBTaskRelation findAllPageOI40DBTaskRelation(p)

findAll

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbTaskRelationRepositoryApi;


Oi40DbTaskRelationRepositoryApi apiInstance = new Oi40DbTaskRelationRepositoryApi();
PageInfo p = new PageInfo(); // PageInfo | p
try {
    PageOI40DBTaskRelation result = apiInstance.findAllPageOI40DBTaskRelation(p);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbTaskRelationRepositoryApi#findAllPageOI40DBTaskRelation");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **p** | [**PageInfo**](PageInfo.md)| p |

### Return type

[**PageOI40DBTaskRelation**](PageOI40DBTaskRelation.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="findByAfterIntegrationTsListOI40DBTaskRelation"></a>
# **findByAfterIntegrationTsListOI40DBTaskRelation**
> List&lt;OI40DBTaskRelation&gt; findByAfterIntegrationTsListOI40DBTaskRelation(ts)

findByAfterIntegrationTs

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbTaskRelationRepositoryApi;


Oi40DbTaskRelationRepositoryApi apiInstance = new Oi40DbTaskRelationRepositoryApi();
String ts = "ts_example"; // String | ts
try {
    List<OI40DBTaskRelation> result = apiInstance.findByAfterIntegrationTsListOI40DBTaskRelation(ts);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbTaskRelationRepositoryApi#findByAfterIntegrationTsListOI40DBTaskRelation");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **ts** | **String**| ts |

### Return type

[**List&lt;OI40DBTaskRelation&gt;**](OI40DBTaskRelation.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*

<a name="findByAfterModifiedTimestampListOI40DBTaskRelation"></a>
# **findByAfterModifiedTimestampListOI40DBTaskRelation**
> List&lt;OI40DBTaskRelation&gt; findByAfterModifiedTimestampListOI40DBTaskRelation(ts)

findByAfterModifiedTimestamp

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbTaskRelationRepositoryApi;


Oi40DbTaskRelationRepositoryApi apiInstance = new Oi40DbTaskRelationRepositoryApi();
String ts = "ts_example"; // String | ts
try {
    List<OI40DBTaskRelation> result = apiInstance.findByAfterModifiedTimestampListOI40DBTaskRelation(ts);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbTaskRelationRepositoryApi#findByAfterModifiedTimestampListOI40DBTaskRelation");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **ts** | **String**| ts |

### Return type

[**List&lt;OI40DBTaskRelation&gt;**](OI40DBTaskRelation.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*

<a name="findByCodeOI40DBTaskRelation"></a>
# **findByCodeOI40DBTaskRelation**
> OI40DBTaskRelation findByCodeOI40DBTaskRelation(code)

findByCode

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbTaskRelationRepositoryApi;


Oi40DbTaskRelationRepositoryApi apiInstance = new Oi40DbTaskRelationRepositoryApi();
String code = "code_example"; // String | code
try {
    OI40DBTaskRelation result = apiInstance.findByCodeOI40DBTaskRelation(code);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbTaskRelationRepositoryApi#findByCodeOI40DBTaskRelation");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **code** | **String**| code |

### Return type

[**OI40DBTaskRelation**](OI40DBTaskRelation.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="findByCodesListOI40DBTaskRelation"></a>
# **findByCodesListOI40DBTaskRelation**
> List&lt;OI40DBTaskRelation&gt; findByCodesListOI40DBTaskRelation(codes)

findByCodes

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbTaskRelationRepositoryApi;


Oi40DbTaskRelationRepositoryApi apiInstance = new Oi40DbTaskRelationRepositoryApi();
List<String> codes = Arrays.asList(new List<String>()); // List<String> | codes
try {
    List<OI40DBTaskRelation> result = apiInstance.findByCodesListOI40DBTaskRelation(codes);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbTaskRelationRepositoryApi#findByCodesListOI40DBTaskRelation");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **codes** | **List&lt;String&gt;**| codes |

### Return type

[**List&lt;OI40DBTaskRelation&gt;**](OI40DBTaskRelation.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="findByQbeListOI40DBTaskRelation"></a>
# **findByQbeListOI40DBTaskRelation**
> List&lt;OI40DBTaskRelation&gt; findByQbeListOI40DBTaskRelation(qbe)

findByQbe

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbTaskRelationRepositoryApi;


Oi40DbTaskRelationRepositoryApi apiInstance = new Oi40DbTaskRelationRepositoryApi();
OI40DBTaskRelation qbe = new OI40DBTaskRelation(); // OI40DBTaskRelation | qbe
try {
    List<OI40DBTaskRelation> result = apiInstance.findByQbeListOI40DBTaskRelation(qbe);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbTaskRelationRepositoryApi#findByQbeListOI40DBTaskRelation");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **qbe** | [**OI40DBTaskRelation**](OI40DBTaskRelation.md)| qbe |

### Return type

[**List&lt;OI40DBTaskRelation&gt;**](OI40DBTaskRelation.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="findByQbePagedPageOI40DBTaskRelation"></a>
# **findByQbePagedPageOI40DBTaskRelation**
> PageOI40DBTaskRelation findByQbePagedPageOI40DBTaskRelation(qbe)

findByQbePaged

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbTaskRelationRepositoryApi;


Oi40DbTaskRelationRepositoryApi apiInstance = new Oi40DbTaskRelationRepositoryApi();
QbeSupportOI40DBTaskRelation qbe = new QbeSupportOI40DBTaskRelation(); // QbeSupportOI40DBTaskRelation | qbe
try {
    PageOI40DBTaskRelation result = apiInstance.findByQbePagedPageOI40DBTaskRelation(qbe);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbTaskRelationRepositoryApi#findByQbePagedPageOI40DBTaskRelation");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **qbe** | [**QbeSupportOI40DBTaskRelation**](QbeSupportOI40DBTaskRelation.md)| qbe |

### Return type

[**PageOI40DBTaskRelation**](PageOI40DBTaskRelation.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="updateListOI40DBTaskRelation"></a>
# **updateListOI40DBTaskRelation**
> List&lt;OI40DBTaskRelation&gt; updateListOI40DBTaskRelation(data)

update

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbTaskRelationRepositoryApi;


Oi40DbTaskRelationRepositoryApi apiInstance = new Oi40DbTaskRelationRepositoryApi();
List<OI40DBTaskRelation> data = Arrays.asList(new OI40DBTaskRelation()); // List<OI40DBTaskRelation> | data
try {
    List<OI40DBTaskRelation> result = apiInstance.updateListOI40DBTaskRelation(data);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbTaskRelationRepositoryApi#updateListOI40DBTaskRelation");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **data** | [**List&lt;OI40DBTaskRelation&gt;**](OI40DBTaskRelation.md)| data |

### Return type

[**List&lt;OI40DBTaskRelation&gt;**](OI40DBTaskRelation.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="updateSingleOI40DBTaskRelation"></a>
# **updateSingleOI40DBTaskRelation**
> OI40DBTaskRelation updateSingleOI40DBTaskRelation(data)

updateSingle

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbTaskRelationRepositoryApi;


Oi40DbTaskRelationRepositoryApi apiInstance = new Oi40DbTaskRelationRepositoryApi();
OI40DBTaskRelation data = new OI40DBTaskRelation(); // OI40DBTaskRelation | data
try {
    OI40DBTaskRelation result = apiInstance.updateSingleOI40DBTaskRelation(data);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbTaskRelationRepositoryApi#updateSingleOI40DBTaskRelation");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **data** | [**OI40DBTaskRelation**](OI40DBTaskRelation.md)| data |

### Return type

[**OI40DBTaskRelation**](OI40DBTaskRelation.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

