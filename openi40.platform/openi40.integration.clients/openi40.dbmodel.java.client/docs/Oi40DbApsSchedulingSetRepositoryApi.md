# Oi40DbApsSchedulingSetRepositoryApi

All URIs are relative to *https://localhost:8083/openi40-backoffice*

Method | HTTP request | Description
------------- | ------------- | -------------
[**deleteByCodeVoid**](Oi40DbApsSchedulingSetRepositoryApi.md#deleteByCodeVoid) | **GET** /integration/OI40DBApsSchedulingSet/deleteByCode/{code} | deleteByCode
[**deleteByCodesVoid**](Oi40DbApsSchedulingSetRepositoryApi.md#deleteByCodesVoid) | **POST** /integration/OI40DBApsSchedulingSet/deleteByCodes | deleteByCodes
[**doAutocompletePageOI40DBApsSchedulingSet**](Oi40DbApsSchedulingSetRepositoryApi.md#doAutocompletePageOI40DBApsSchedulingSet) | **POST** /integration/OI40DBApsSchedulingSet/doAutocomplete | doAutocomplete
[**doLookupPageOI40DBApsSchedulingSet**](Oi40DbApsSchedulingSetRepositoryApi.md#doLookupPageOI40DBApsSchedulingSet) | **POST** /integration/OI40DBApsSchedulingSet/doLookup | doLookup
[**findAllListOI40DBApsSchedulingSet**](Oi40DbApsSchedulingSetRepositoryApi.md#findAllListOI40DBApsSchedulingSet) | **GET** /integration/OI40DBApsSchedulingSet | findAll
[**findAllPageOI40DBApsSchedulingSet**](Oi40DbApsSchedulingSetRepositoryApi.md#findAllPageOI40DBApsSchedulingSet) | **POST** /integration/OI40DBApsSchedulingSet/findAllPaged | findAll
[**findByAfterIntegrationTsListOI40DBApsSchedulingSet**](Oi40DbApsSchedulingSetRepositoryApi.md#findByAfterIntegrationTsListOI40DBApsSchedulingSet) | **GET** /integration/OI40DBApsSchedulingSet/integratedAfter/{ts} | findByAfterIntegrationTs
[**findByAfterModifiedTimestampListOI40DBApsSchedulingSet**](Oi40DbApsSchedulingSetRepositoryApi.md#findByAfterModifiedTimestampListOI40DBApsSchedulingSet) | **GET** /integration/OI40DBApsSchedulingSet/modifiedAfter/{ts} | findByAfterModifiedTimestamp
[**findByCodeOI40DBApsSchedulingSet**](Oi40DbApsSchedulingSetRepositoryApi.md#findByCodeOI40DBApsSchedulingSet) | **GET** /integration/OI40DBApsSchedulingSet/byCode/{code} | findByCode
[**findByCodesListOI40DBApsSchedulingSet**](Oi40DbApsSchedulingSetRepositoryApi.md#findByCodesListOI40DBApsSchedulingSet) | **POST** /integration/OI40DBApsSchedulingSet/findByCodes | findByCodes
[**findByQbeListOI40DBApsSchedulingSet**](Oi40DbApsSchedulingSetRepositoryApi.md#findByQbeListOI40DBApsSchedulingSet) | **POST** /integration/OI40DBApsSchedulingSet/findByQbe | findByQbe
[**findByQbePagedPageOI40DBApsSchedulingSet**](Oi40DbApsSchedulingSetRepositoryApi.md#findByQbePagedPageOI40DBApsSchedulingSet) | **POST** /integration/OI40DBApsSchedulingSet/findByQbePaged | findByQbePaged
[**updateListOI40DBApsSchedulingSet**](Oi40DbApsSchedulingSetRepositoryApi.md#updateListOI40DBApsSchedulingSet) | **POST** /integration/OI40DBApsSchedulingSet/update | update
[**updateSingleOI40DBApsSchedulingSet**](Oi40DbApsSchedulingSetRepositoryApi.md#updateSingleOI40DBApsSchedulingSet) | **POST** /integration/OI40DBApsSchedulingSet/updateSingle | updateSingle


<a name="deleteByCodeVoid"></a>
# **deleteByCodeVoid**
> deleteByCodeVoid(code)

deleteByCode

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbApsSchedulingSetRepositoryApi;


Oi40DbApsSchedulingSetRepositoryApi apiInstance = new Oi40DbApsSchedulingSetRepositoryApi();
String code = "code_example"; // String | code
try {
    apiInstance.deleteByCodeVoid(code);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbApsSchedulingSetRepositoryApi#deleteByCodeVoid");
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

<a name="deleteByCodesVoid"></a>
# **deleteByCodesVoid**
> deleteByCodesVoid(codes)

deleteByCodes

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbApsSchedulingSetRepositoryApi;


Oi40DbApsSchedulingSetRepositoryApi apiInstance = new Oi40DbApsSchedulingSetRepositoryApi();
List<String> codes = Arrays.asList(new List<String>()); // List<String> | codes
try {
    apiInstance.deleteByCodesVoid(codes);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbApsSchedulingSetRepositoryApi#deleteByCodesVoid");
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

<a name="doAutocompletePageOI40DBApsSchedulingSet"></a>
# **doAutocompletePageOI40DBApsSchedulingSet**
> PageOI40DBApsSchedulingSet doAutocompletePageOI40DBApsSchedulingSet(autoCompleteData)

doAutocomplete

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbApsSchedulingSetRepositoryApi;


Oi40DbApsSchedulingSetRepositoryApi apiInstance = new Oi40DbApsSchedulingSetRepositoryApi();
AutoCompleteData autoCompleteData = new AutoCompleteData(); // AutoCompleteData | autoCompleteData
try {
    PageOI40DBApsSchedulingSet result = apiInstance.doAutocompletePageOI40DBApsSchedulingSet(autoCompleteData);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbApsSchedulingSetRepositoryApi#doAutocompletePageOI40DBApsSchedulingSet");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **autoCompleteData** | [**AutoCompleteData**](AutoCompleteData.md)| autoCompleteData |

### Return type

[**PageOI40DBApsSchedulingSet**](PageOI40DBApsSchedulingSet.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: */*

<a name="doLookupPageOI40DBApsSchedulingSet"></a>
# **doLookupPageOI40DBApsSchedulingSet**
> PageOI40DBApsSchedulingSet doLookupPageOI40DBApsSchedulingSet(lookup)

doLookup

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbApsSchedulingSetRepositoryApi;


Oi40DbApsSchedulingSetRepositoryApi apiInstance = new Oi40DbApsSchedulingSetRepositoryApi();
LookupData lookup = new LookupData(); // LookupData | lookup
try {
    PageOI40DBApsSchedulingSet result = apiInstance.doLookupPageOI40DBApsSchedulingSet(lookup);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbApsSchedulingSetRepositoryApi#doLookupPageOI40DBApsSchedulingSet");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **lookup** | [**LookupData**](LookupData.md)| lookup |

### Return type

[**PageOI40DBApsSchedulingSet**](PageOI40DBApsSchedulingSet.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: */*

<a name="findAllListOI40DBApsSchedulingSet"></a>
# **findAllListOI40DBApsSchedulingSet**
> List&lt;OI40DBApsSchedulingSet&gt; findAllListOI40DBApsSchedulingSet()

findAll

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbApsSchedulingSetRepositoryApi;


Oi40DbApsSchedulingSetRepositoryApi apiInstance = new Oi40DbApsSchedulingSetRepositoryApi();
try {
    List<OI40DBApsSchedulingSet> result = apiInstance.findAllListOI40DBApsSchedulingSet();
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbApsSchedulingSetRepositoryApi#findAllListOI40DBApsSchedulingSet");
    e.printStackTrace();
}
```

### Parameters
This endpoint does not need any parameter.

### Return type

[**List&lt;OI40DBApsSchedulingSet&gt;**](OI40DBApsSchedulingSet.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="findAllPageOI40DBApsSchedulingSet"></a>
# **findAllPageOI40DBApsSchedulingSet**
> PageOI40DBApsSchedulingSet findAllPageOI40DBApsSchedulingSet(p)

findAll

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbApsSchedulingSetRepositoryApi;


Oi40DbApsSchedulingSetRepositoryApi apiInstance = new Oi40DbApsSchedulingSetRepositoryApi();
PageInfo p = new PageInfo(); // PageInfo | p
try {
    PageOI40DBApsSchedulingSet result = apiInstance.findAllPageOI40DBApsSchedulingSet(p);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbApsSchedulingSetRepositoryApi#findAllPageOI40DBApsSchedulingSet");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **p** | [**PageInfo**](PageInfo.md)| p |

### Return type

[**PageOI40DBApsSchedulingSet**](PageOI40DBApsSchedulingSet.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="findByAfterIntegrationTsListOI40DBApsSchedulingSet"></a>
# **findByAfterIntegrationTsListOI40DBApsSchedulingSet**
> List&lt;OI40DBApsSchedulingSet&gt; findByAfterIntegrationTsListOI40DBApsSchedulingSet(ts)

findByAfterIntegrationTs

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbApsSchedulingSetRepositoryApi;


Oi40DbApsSchedulingSetRepositoryApi apiInstance = new Oi40DbApsSchedulingSetRepositoryApi();
String ts = "ts_example"; // String | ts
try {
    List<OI40DBApsSchedulingSet> result = apiInstance.findByAfterIntegrationTsListOI40DBApsSchedulingSet(ts);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbApsSchedulingSetRepositoryApi#findByAfterIntegrationTsListOI40DBApsSchedulingSet");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **ts** | **String**| ts |

### Return type

[**List&lt;OI40DBApsSchedulingSet&gt;**](OI40DBApsSchedulingSet.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*

<a name="findByAfterModifiedTimestampListOI40DBApsSchedulingSet"></a>
# **findByAfterModifiedTimestampListOI40DBApsSchedulingSet**
> List&lt;OI40DBApsSchedulingSet&gt; findByAfterModifiedTimestampListOI40DBApsSchedulingSet(ts)

findByAfterModifiedTimestamp

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbApsSchedulingSetRepositoryApi;


Oi40DbApsSchedulingSetRepositoryApi apiInstance = new Oi40DbApsSchedulingSetRepositoryApi();
String ts = "ts_example"; // String | ts
try {
    List<OI40DBApsSchedulingSet> result = apiInstance.findByAfterModifiedTimestampListOI40DBApsSchedulingSet(ts);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbApsSchedulingSetRepositoryApi#findByAfterModifiedTimestampListOI40DBApsSchedulingSet");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **ts** | **String**| ts |

### Return type

[**List&lt;OI40DBApsSchedulingSet&gt;**](OI40DBApsSchedulingSet.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*

<a name="findByCodeOI40DBApsSchedulingSet"></a>
# **findByCodeOI40DBApsSchedulingSet**
> OI40DBApsSchedulingSet findByCodeOI40DBApsSchedulingSet(code)

findByCode

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbApsSchedulingSetRepositoryApi;


Oi40DbApsSchedulingSetRepositoryApi apiInstance = new Oi40DbApsSchedulingSetRepositoryApi();
String code = "code_example"; // String | code
try {
    OI40DBApsSchedulingSet result = apiInstance.findByCodeOI40DBApsSchedulingSet(code);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbApsSchedulingSetRepositoryApi#findByCodeOI40DBApsSchedulingSet");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **code** | **String**| code |

### Return type

[**OI40DBApsSchedulingSet**](OI40DBApsSchedulingSet.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="findByCodesListOI40DBApsSchedulingSet"></a>
# **findByCodesListOI40DBApsSchedulingSet**
> List&lt;OI40DBApsSchedulingSet&gt; findByCodesListOI40DBApsSchedulingSet(codes)

findByCodes

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbApsSchedulingSetRepositoryApi;


Oi40DbApsSchedulingSetRepositoryApi apiInstance = new Oi40DbApsSchedulingSetRepositoryApi();
List<String> codes = Arrays.asList(new List<String>()); // List<String> | codes
try {
    List<OI40DBApsSchedulingSet> result = apiInstance.findByCodesListOI40DBApsSchedulingSet(codes);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbApsSchedulingSetRepositoryApi#findByCodesListOI40DBApsSchedulingSet");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **codes** | **List&lt;String&gt;**| codes |

### Return type

[**List&lt;OI40DBApsSchedulingSet&gt;**](OI40DBApsSchedulingSet.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="findByQbeListOI40DBApsSchedulingSet"></a>
# **findByQbeListOI40DBApsSchedulingSet**
> List&lt;OI40DBApsSchedulingSet&gt; findByQbeListOI40DBApsSchedulingSet(qbe)

findByQbe

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbApsSchedulingSetRepositoryApi;


Oi40DbApsSchedulingSetRepositoryApi apiInstance = new Oi40DbApsSchedulingSetRepositoryApi();
OI40DBApsSchedulingSet qbe = new OI40DBApsSchedulingSet(); // OI40DBApsSchedulingSet | qbe
try {
    List<OI40DBApsSchedulingSet> result = apiInstance.findByQbeListOI40DBApsSchedulingSet(qbe);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbApsSchedulingSetRepositoryApi#findByQbeListOI40DBApsSchedulingSet");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **qbe** | [**OI40DBApsSchedulingSet**](OI40DBApsSchedulingSet.md)| qbe |

### Return type

[**List&lt;OI40DBApsSchedulingSet&gt;**](OI40DBApsSchedulingSet.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="findByQbePagedPageOI40DBApsSchedulingSet"></a>
# **findByQbePagedPageOI40DBApsSchedulingSet**
> PageOI40DBApsSchedulingSet findByQbePagedPageOI40DBApsSchedulingSet(qbe)

findByQbePaged

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbApsSchedulingSetRepositoryApi;


Oi40DbApsSchedulingSetRepositoryApi apiInstance = new Oi40DbApsSchedulingSetRepositoryApi();
QbeSupportOI40DBApsSchedulingSet qbe = new QbeSupportOI40DBApsSchedulingSet(); // QbeSupportOI40DBApsSchedulingSet | qbe
try {
    PageOI40DBApsSchedulingSet result = apiInstance.findByQbePagedPageOI40DBApsSchedulingSet(qbe);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbApsSchedulingSetRepositoryApi#findByQbePagedPageOI40DBApsSchedulingSet");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **qbe** | [**QbeSupportOI40DBApsSchedulingSet**](QbeSupportOI40DBApsSchedulingSet.md)| qbe |

### Return type

[**PageOI40DBApsSchedulingSet**](PageOI40DBApsSchedulingSet.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="updateListOI40DBApsSchedulingSet"></a>
# **updateListOI40DBApsSchedulingSet**
> List&lt;OI40DBApsSchedulingSet&gt; updateListOI40DBApsSchedulingSet(data)

update

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbApsSchedulingSetRepositoryApi;


Oi40DbApsSchedulingSetRepositoryApi apiInstance = new Oi40DbApsSchedulingSetRepositoryApi();
List<OI40DBApsSchedulingSet> data = Arrays.asList(new OI40DBApsSchedulingSet()); // List<OI40DBApsSchedulingSet> | data
try {
    List<OI40DBApsSchedulingSet> result = apiInstance.updateListOI40DBApsSchedulingSet(data);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbApsSchedulingSetRepositoryApi#updateListOI40DBApsSchedulingSet");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **data** | [**List&lt;OI40DBApsSchedulingSet&gt;**](OI40DBApsSchedulingSet.md)| data |

### Return type

[**List&lt;OI40DBApsSchedulingSet&gt;**](OI40DBApsSchedulingSet.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="updateSingleOI40DBApsSchedulingSet"></a>
# **updateSingleOI40DBApsSchedulingSet**
> OI40DBApsSchedulingSet updateSingleOI40DBApsSchedulingSet(data)

updateSingle

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbApsSchedulingSetRepositoryApi;


Oi40DbApsSchedulingSetRepositoryApi apiInstance = new Oi40DbApsSchedulingSetRepositoryApi();
OI40DBApsSchedulingSet data = new OI40DBApsSchedulingSet(); // OI40DBApsSchedulingSet | data
try {
    OI40DBApsSchedulingSet result = apiInstance.updateSingleOI40DBApsSchedulingSet(data);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbApsSchedulingSetRepositoryApi#updateSingleOI40DBApsSchedulingSet");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **data** | [**OI40DBApsSchedulingSet**](OI40DBApsSchedulingSet.md)| data |

### Return type

[**OI40DBApsSchedulingSet**](OI40DBApsSchedulingSet.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

