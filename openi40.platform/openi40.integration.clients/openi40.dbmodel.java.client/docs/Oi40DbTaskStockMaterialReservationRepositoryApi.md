# Oi40DbTaskStockMaterialReservationRepositoryApi

All URIs are relative to *https://localhost:8083/openi40-backoffice*

Method | HTTP request | Description
------------- | ------------- | -------------
[**deleteByCodeVoid33**](Oi40DbTaskStockMaterialReservationRepositoryApi.md#deleteByCodeVoid33) | **GET** /integration/OI40DBTaskStockMaterialReservation/deleteByCode/{code} | deleteByCode
[**deleteByCodesVoid33**](Oi40DbTaskStockMaterialReservationRepositoryApi.md#deleteByCodesVoid33) | **POST** /integration/OI40DBTaskStockMaterialReservation/deleteByCodes | deleteByCodes
[**doAutocompletePageOI40DBTaskStockMaterialReservation**](Oi40DbTaskStockMaterialReservationRepositoryApi.md#doAutocompletePageOI40DBTaskStockMaterialReservation) | **POST** /integration/OI40DBTaskStockMaterialReservation/doAutocomplete | doAutocomplete
[**doLookupPageOI40DBTaskStockMaterialReservation**](Oi40DbTaskStockMaterialReservationRepositoryApi.md#doLookupPageOI40DBTaskStockMaterialReservation) | **POST** /integration/OI40DBTaskStockMaterialReservation/doLookup | doLookup
[**findAllListOI40DBTaskStockMaterialReservation**](Oi40DbTaskStockMaterialReservationRepositoryApi.md#findAllListOI40DBTaskStockMaterialReservation) | **GET** /integration/OI40DBTaskStockMaterialReservation | findAll
[**findAllPageOI40DBTaskStockMaterialReservation**](Oi40DbTaskStockMaterialReservationRepositoryApi.md#findAllPageOI40DBTaskStockMaterialReservation) | **POST** /integration/OI40DBTaskStockMaterialReservation/findAllPaged | findAll
[**findByAfterIntegrationTsListOI40DBTaskStockMaterialReservation**](Oi40DbTaskStockMaterialReservationRepositoryApi.md#findByAfterIntegrationTsListOI40DBTaskStockMaterialReservation) | **GET** /integration/OI40DBTaskStockMaterialReservation/integratedAfter/{ts} | findByAfterIntegrationTs
[**findByAfterModifiedTimestampListOI40DBTaskStockMaterialReservation**](Oi40DbTaskStockMaterialReservationRepositoryApi.md#findByAfterModifiedTimestampListOI40DBTaskStockMaterialReservation) | **GET** /integration/OI40DBTaskStockMaterialReservation/modifiedAfter/{ts} | findByAfterModifiedTimestamp
[**findByCodeOI40DBTaskStockMaterialReservation**](Oi40DbTaskStockMaterialReservationRepositoryApi.md#findByCodeOI40DBTaskStockMaterialReservation) | **GET** /integration/OI40DBTaskStockMaterialReservation/byCode/{code} | findByCode
[**findByCodesListOI40DBTaskStockMaterialReservation**](Oi40DbTaskStockMaterialReservationRepositoryApi.md#findByCodesListOI40DBTaskStockMaterialReservation) | **POST** /integration/OI40DBTaskStockMaterialReservation/findByCodes | findByCodes
[**findByQbeListOI40DBTaskStockMaterialReservation**](Oi40DbTaskStockMaterialReservationRepositoryApi.md#findByQbeListOI40DBTaskStockMaterialReservation) | **POST** /integration/OI40DBTaskStockMaterialReservation/findByQbe | findByQbe
[**findByQbePagedPageOI40DBTaskStockMaterialReservation**](Oi40DbTaskStockMaterialReservationRepositoryApi.md#findByQbePagedPageOI40DBTaskStockMaterialReservation) | **POST** /integration/OI40DBTaskStockMaterialReservation/findByQbePaged | findByQbePaged
[**updateListOI40DBTaskStockMaterialReservation**](Oi40DbTaskStockMaterialReservationRepositoryApi.md#updateListOI40DBTaskStockMaterialReservation) | **POST** /integration/OI40DBTaskStockMaterialReservation/update | update
[**updateSingleOI40DBTaskStockMaterialReservation**](Oi40DbTaskStockMaterialReservationRepositoryApi.md#updateSingleOI40DBTaskStockMaterialReservation) | **POST** /integration/OI40DBTaskStockMaterialReservation/updateSingle | updateSingle


<a name="deleteByCodeVoid33"></a>
# **deleteByCodeVoid33**
> deleteByCodeVoid33(code)

deleteByCode

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbTaskStockMaterialReservationRepositoryApi;


Oi40DbTaskStockMaterialReservationRepositoryApi apiInstance = new Oi40DbTaskStockMaterialReservationRepositoryApi();
String code = "code_example"; // String | code
try {
    apiInstance.deleteByCodeVoid33(code);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbTaskStockMaterialReservationRepositoryApi#deleteByCodeVoid33");
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

<a name="deleteByCodesVoid33"></a>
# **deleteByCodesVoid33**
> deleteByCodesVoid33(codes)

deleteByCodes

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbTaskStockMaterialReservationRepositoryApi;


Oi40DbTaskStockMaterialReservationRepositoryApi apiInstance = new Oi40DbTaskStockMaterialReservationRepositoryApi();
List<String> codes = Arrays.asList(new List<String>()); // List<String> | codes
try {
    apiInstance.deleteByCodesVoid33(codes);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbTaskStockMaterialReservationRepositoryApi#deleteByCodesVoid33");
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

<a name="doAutocompletePageOI40DBTaskStockMaterialReservation"></a>
# **doAutocompletePageOI40DBTaskStockMaterialReservation**
> PageOI40DBTaskStockMaterialReservation doAutocompletePageOI40DBTaskStockMaterialReservation(autoCompleteData)

doAutocomplete

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbTaskStockMaterialReservationRepositoryApi;


Oi40DbTaskStockMaterialReservationRepositoryApi apiInstance = new Oi40DbTaskStockMaterialReservationRepositoryApi();
AutoCompleteData autoCompleteData = new AutoCompleteData(); // AutoCompleteData | autoCompleteData
try {
    PageOI40DBTaskStockMaterialReservation result = apiInstance.doAutocompletePageOI40DBTaskStockMaterialReservation(autoCompleteData);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbTaskStockMaterialReservationRepositoryApi#doAutocompletePageOI40DBTaskStockMaterialReservation");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **autoCompleteData** | [**AutoCompleteData**](AutoCompleteData.md)| autoCompleteData |

### Return type

[**PageOI40DBTaskStockMaterialReservation**](PageOI40DBTaskStockMaterialReservation.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: */*

<a name="doLookupPageOI40DBTaskStockMaterialReservation"></a>
# **doLookupPageOI40DBTaskStockMaterialReservation**
> PageOI40DBTaskStockMaterialReservation doLookupPageOI40DBTaskStockMaterialReservation(lookup)

doLookup

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbTaskStockMaterialReservationRepositoryApi;


Oi40DbTaskStockMaterialReservationRepositoryApi apiInstance = new Oi40DbTaskStockMaterialReservationRepositoryApi();
LookupData lookup = new LookupData(); // LookupData | lookup
try {
    PageOI40DBTaskStockMaterialReservation result = apiInstance.doLookupPageOI40DBTaskStockMaterialReservation(lookup);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbTaskStockMaterialReservationRepositoryApi#doLookupPageOI40DBTaskStockMaterialReservation");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **lookup** | [**LookupData**](LookupData.md)| lookup |

### Return type

[**PageOI40DBTaskStockMaterialReservation**](PageOI40DBTaskStockMaterialReservation.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: */*

<a name="findAllListOI40DBTaskStockMaterialReservation"></a>
# **findAllListOI40DBTaskStockMaterialReservation**
> List&lt;OI40DBTaskStockMaterialReservation&gt; findAllListOI40DBTaskStockMaterialReservation()

findAll

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbTaskStockMaterialReservationRepositoryApi;


Oi40DbTaskStockMaterialReservationRepositoryApi apiInstance = new Oi40DbTaskStockMaterialReservationRepositoryApi();
try {
    List<OI40DBTaskStockMaterialReservation> result = apiInstance.findAllListOI40DBTaskStockMaterialReservation();
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbTaskStockMaterialReservationRepositoryApi#findAllListOI40DBTaskStockMaterialReservation");
    e.printStackTrace();
}
```

### Parameters
This endpoint does not need any parameter.

### Return type

[**List&lt;OI40DBTaskStockMaterialReservation&gt;**](OI40DBTaskStockMaterialReservation.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="findAllPageOI40DBTaskStockMaterialReservation"></a>
# **findAllPageOI40DBTaskStockMaterialReservation**
> PageOI40DBTaskStockMaterialReservation findAllPageOI40DBTaskStockMaterialReservation(p)

findAll

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbTaskStockMaterialReservationRepositoryApi;


Oi40DbTaskStockMaterialReservationRepositoryApi apiInstance = new Oi40DbTaskStockMaterialReservationRepositoryApi();
PageInfo p = new PageInfo(); // PageInfo | p
try {
    PageOI40DBTaskStockMaterialReservation result = apiInstance.findAllPageOI40DBTaskStockMaterialReservation(p);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbTaskStockMaterialReservationRepositoryApi#findAllPageOI40DBTaskStockMaterialReservation");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **p** | [**PageInfo**](PageInfo.md)| p |

### Return type

[**PageOI40DBTaskStockMaterialReservation**](PageOI40DBTaskStockMaterialReservation.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="findByAfterIntegrationTsListOI40DBTaskStockMaterialReservation"></a>
# **findByAfterIntegrationTsListOI40DBTaskStockMaterialReservation**
> List&lt;OI40DBTaskStockMaterialReservation&gt; findByAfterIntegrationTsListOI40DBTaskStockMaterialReservation(ts)

findByAfterIntegrationTs

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbTaskStockMaterialReservationRepositoryApi;


Oi40DbTaskStockMaterialReservationRepositoryApi apiInstance = new Oi40DbTaskStockMaterialReservationRepositoryApi();
String ts = "ts_example"; // String | ts
try {
    List<OI40DBTaskStockMaterialReservation> result = apiInstance.findByAfterIntegrationTsListOI40DBTaskStockMaterialReservation(ts);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbTaskStockMaterialReservationRepositoryApi#findByAfterIntegrationTsListOI40DBTaskStockMaterialReservation");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **ts** | **String**| ts |

### Return type

[**List&lt;OI40DBTaskStockMaterialReservation&gt;**](OI40DBTaskStockMaterialReservation.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*

<a name="findByAfterModifiedTimestampListOI40DBTaskStockMaterialReservation"></a>
# **findByAfterModifiedTimestampListOI40DBTaskStockMaterialReservation**
> List&lt;OI40DBTaskStockMaterialReservation&gt; findByAfterModifiedTimestampListOI40DBTaskStockMaterialReservation(ts)

findByAfterModifiedTimestamp

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbTaskStockMaterialReservationRepositoryApi;


Oi40DbTaskStockMaterialReservationRepositoryApi apiInstance = new Oi40DbTaskStockMaterialReservationRepositoryApi();
String ts = "ts_example"; // String | ts
try {
    List<OI40DBTaskStockMaterialReservation> result = apiInstance.findByAfterModifiedTimestampListOI40DBTaskStockMaterialReservation(ts);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbTaskStockMaterialReservationRepositoryApi#findByAfterModifiedTimestampListOI40DBTaskStockMaterialReservation");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **ts** | **String**| ts |

### Return type

[**List&lt;OI40DBTaskStockMaterialReservation&gt;**](OI40DBTaskStockMaterialReservation.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*

<a name="findByCodeOI40DBTaskStockMaterialReservation"></a>
# **findByCodeOI40DBTaskStockMaterialReservation**
> OI40DBTaskStockMaterialReservation findByCodeOI40DBTaskStockMaterialReservation(code)

findByCode

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbTaskStockMaterialReservationRepositoryApi;


Oi40DbTaskStockMaterialReservationRepositoryApi apiInstance = new Oi40DbTaskStockMaterialReservationRepositoryApi();
String code = "code_example"; // String | code
try {
    OI40DBTaskStockMaterialReservation result = apiInstance.findByCodeOI40DBTaskStockMaterialReservation(code);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbTaskStockMaterialReservationRepositoryApi#findByCodeOI40DBTaskStockMaterialReservation");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **code** | **String**| code |

### Return type

[**OI40DBTaskStockMaterialReservation**](OI40DBTaskStockMaterialReservation.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="findByCodesListOI40DBTaskStockMaterialReservation"></a>
# **findByCodesListOI40DBTaskStockMaterialReservation**
> List&lt;OI40DBTaskStockMaterialReservation&gt; findByCodesListOI40DBTaskStockMaterialReservation(codes)

findByCodes

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbTaskStockMaterialReservationRepositoryApi;


Oi40DbTaskStockMaterialReservationRepositoryApi apiInstance = new Oi40DbTaskStockMaterialReservationRepositoryApi();
List<String> codes = Arrays.asList(new List<String>()); // List<String> | codes
try {
    List<OI40DBTaskStockMaterialReservation> result = apiInstance.findByCodesListOI40DBTaskStockMaterialReservation(codes);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbTaskStockMaterialReservationRepositoryApi#findByCodesListOI40DBTaskStockMaterialReservation");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **codes** | **List&lt;String&gt;**| codes |

### Return type

[**List&lt;OI40DBTaskStockMaterialReservation&gt;**](OI40DBTaskStockMaterialReservation.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="findByQbeListOI40DBTaskStockMaterialReservation"></a>
# **findByQbeListOI40DBTaskStockMaterialReservation**
> List&lt;OI40DBTaskStockMaterialReservation&gt; findByQbeListOI40DBTaskStockMaterialReservation(qbe)

findByQbe

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbTaskStockMaterialReservationRepositoryApi;


Oi40DbTaskStockMaterialReservationRepositoryApi apiInstance = new Oi40DbTaskStockMaterialReservationRepositoryApi();
OI40DBTaskStockMaterialReservation qbe = new OI40DBTaskStockMaterialReservation(); // OI40DBTaskStockMaterialReservation | qbe
try {
    List<OI40DBTaskStockMaterialReservation> result = apiInstance.findByQbeListOI40DBTaskStockMaterialReservation(qbe);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbTaskStockMaterialReservationRepositoryApi#findByQbeListOI40DBTaskStockMaterialReservation");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **qbe** | [**OI40DBTaskStockMaterialReservation**](OI40DBTaskStockMaterialReservation.md)| qbe |

### Return type

[**List&lt;OI40DBTaskStockMaterialReservation&gt;**](OI40DBTaskStockMaterialReservation.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="findByQbePagedPageOI40DBTaskStockMaterialReservation"></a>
# **findByQbePagedPageOI40DBTaskStockMaterialReservation**
> PageOI40DBTaskStockMaterialReservation findByQbePagedPageOI40DBTaskStockMaterialReservation(qbe)

findByQbePaged

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbTaskStockMaterialReservationRepositoryApi;


Oi40DbTaskStockMaterialReservationRepositoryApi apiInstance = new Oi40DbTaskStockMaterialReservationRepositoryApi();
QbeSupportOI40DBTaskStockMaterialReservation qbe = new QbeSupportOI40DBTaskStockMaterialReservation(); // QbeSupportOI40DBTaskStockMaterialReservation | qbe
try {
    PageOI40DBTaskStockMaterialReservation result = apiInstance.findByQbePagedPageOI40DBTaskStockMaterialReservation(qbe);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbTaskStockMaterialReservationRepositoryApi#findByQbePagedPageOI40DBTaskStockMaterialReservation");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **qbe** | [**QbeSupportOI40DBTaskStockMaterialReservation**](QbeSupportOI40DBTaskStockMaterialReservation.md)| qbe |

### Return type

[**PageOI40DBTaskStockMaterialReservation**](PageOI40DBTaskStockMaterialReservation.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="updateListOI40DBTaskStockMaterialReservation"></a>
# **updateListOI40DBTaskStockMaterialReservation**
> List&lt;OI40DBTaskStockMaterialReservation&gt; updateListOI40DBTaskStockMaterialReservation(data)

update

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbTaskStockMaterialReservationRepositoryApi;


Oi40DbTaskStockMaterialReservationRepositoryApi apiInstance = new Oi40DbTaskStockMaterialReservationRepositoryApi();
List<OI40DBTaskStockMaterialReservation> data = Arrays.asList(new OI40DBTaskStockMaterialReservation()); // List<OI40DBTaskStockMaterialReservation> | data
try {
    List<OI40DBTaskStockMaterialReservation> result = apiInstance.updateListOI40DBTaskStockMaterialReservation(data);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbTaskStockMaterialReservationRepositoryApi#updateListOI40DBTaskStockMaterialReservation");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **data** | [**List&lt;OI40DBTaskStockMaterialReservation&gt;**](OI40DBTaskStockMaterialReservation.md)| data |

### Return type

[**List&lt;OI40DBTaskStockMaterialReservation&gt;**](OI40DBTaskStockMaterialReservation.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="updateSingleOI40DBTaskStockMaterialReservation"></a>
# **updateSingleOI40DBTaskStockMaterialReservation**
> OI40DBTaskStockMaterialReservation updateSingleOI40DBTaskStockMaterialReservation(data)

updateSingle

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbTaskStockMaterialReservationRepositoryApi;


Oi40DbTaskStockMaterialReservationRepositoryApi apiInstance = new Oi40DbTaskStockMaterialReservationRepositoryApi();
OI40DBTaskStockMaterialReservation data = new OI40DBTaskStockMaterialReservation(); // OI40DBTaskStockMaterialReservation | data
try {
    OI40DBTaskStockMaterialReservation result = apiInstance.updateSingleOI40DBTaskStockMaterialReservation(data);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbTaskStockMaterialReservationRepositoryApi#updateSingleOI40DBTaskStockMaterialReservation");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **data** | [**OI40DBTaskStockMaterialReservation**](OI40DBTaskStockMaterialReservation.md)| data |

### Return type

[**OI40DBTaskStockMaterialReservation**](OI40DBTaskStockMaterialReservation.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

