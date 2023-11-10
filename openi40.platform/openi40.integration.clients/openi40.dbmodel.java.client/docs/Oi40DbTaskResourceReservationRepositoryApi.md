# Oi40DbTaskResourceReservationRepositoryApi

All URIs are relative to *https://localhost:8082/openi40-integration*

Method | HTTP request | Description
------------- | ------------- | -------------
[**deleteByCodeVoid32**](Oi40DbTaskResourceReservationRepositoryApi.md#deleteByCodeVoid32) | **GET** /integration/OI40DBTaskResourceReservation/deleteByCode/{code} | deleteByCode
[**deleteByCodesVoid32**](Oi40DbTaskResourceReservationRepositoryApi.md#deleteByCodesVoid32) | **POST** /integration/OI40DBTaskResourceReservation/deleteByCodes | deleteByCodes
[**doAutocompletePageOI40DBTaskResourceReservation**](Oi40DbTaskResourceReservationRepositoryApi.md#doAutocompletePageOI40DBTaskResourceReservation) | **POST** /integration/OI40DBTaskResourceReservation/doAutocomplete | doAutocomplete
[**doLookupPageOI40DBTaskResourceReservation**](Oi40DbTaskResourceReservationRepositoryApi.md#doLookupPageOI40DBTaskResourceReservation) | **POST** /integration/OI40DBTaskResourceReservation/doLookup | doLookup
[**findAllListOI40DBTaskResourceReservation**](Oi40DbTaskResourceReservationRepositoryApi.md#findAllListOI40DBTaskResourceReservation) | **GET** /integration/OI40DBTaskResourceReservation | findAll
[**findAllPageOI40DBTaskResourceReservation**](Oi40DbTaskResourceReservationRepositoryApi.md#findAllPageOI40DBTaskResourceReservation) | **POST** /integration/OI40DBTaskResourceReservation/findAllPaged | findAll
[**findByAfterIntegrationTsListOI40DBTaskResourceReservation**](Oi40DbTaskResourceReservationRepositoryApi.md#findByAfterIntegrationTsListOI40DBTaskResourceReservation) | **GET** /integration/OI40DBTaskResourceReservation/integratedAfter/{ts} | findByAfterIntegrationTs
[**findByAfterModifiedTimestampListOI40DBTaskResourceReservation**](Oi40DbTaskResourceReservationRepositoryApi.md#findByAfterModifiedTimestampListOI40DBTaskResourceReservation) | **GET** /integration/OI40DBTaskResourceReservation/modifiedAfter/{ts} | findByAfterModifiedTimestamp
[**findByCodeOI40DBTaskResourceReservation**](Oi40DbTaskResourceReservationRepositoryApi.md#findByCodeOI40DBTaskResourceReservation) | **GET** /integration/OI40DBTaskResourceReservation/byCode/{code} | findByCode
[**findByCodesListOI40DBTaskResourceReservation**](Oi40DbTaskResourceReservationRepositoryApi.md#findByCodesListOI40DBTaskResourceReservation) | **POST** /integration/OI40DBTaskResourceReservation/findByCodes | findByCodes
[**findByQbeListOI40DBTaskResourceReservation**](Oi40DbTaskResourceReservationRepositoryApi.md#findByQbeListOI40DBTaskResourceReservation) | **POST** /integration/OI40DBTaskResourceReservation/findByQbe | findByQbe
[**findByQbePagedPageOI40DBTaskResourceReservation**](Oi40DbTaskResourceReservationRepositoryApi.md#findByQbePagedPageOI40DBTaskResourceReservation) | **POST** /integration/OI40DBTaskResourceReservation/findByQbePaged | findByQbePaged
[**updateListOI40DBTaskResourceReservation**](Oi40DbTaskResourceReservationRepositoryApi.md#updateListOI40DBTaskResourceReservation) | **POST** /integration/OI40DBTaskResourceReservation/update | update
[**updateSingleOI40DBTaskResourceReservation**](Oi40DbTaskResourceReservationRepositoryApi.md#updateSingleOI40DBTaskResourceReservation) | **POST** /integration/OI40DBTaskResourceReservation/updateSingle | updateSingle


<a name="deleteByCodeVoid32"></a>
# **deleteByCodeVoid32**
> deleteByCodeVoid32(code)

deleteByCode

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbTaskResourceReservationRepositoryApi;


Oi40DbTaskResourceReservationRepositoryApi apiInstance = new Oi40DbTaskResourceReservationRepositoryApi();
String code = "code_example"; // String | code
try {
    apiInstance.deleteByCodeVoid32(code);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbTaskResourceReservationRepositoryApi#deleteByCodeVoid32");
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

<a name="deleteByCodesVoid32"></a>
# **deleteByCodesVoid32**
> deleteByCodesVoid32(codes)

deleteByCodes

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbTaskResourceReservationRepositoryApi;


Oi40DbTaskResourceReservationRepositoryApi apiInstance = new Oi40DbTaskResourceReservationRepositoryApi();
List<String> codes = Arrays.asList(new List<String>()); // List<String> | codes
try {
    apiInstance.deleteByCodesVoid32(codes);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbTaskResourceReservationRepositoryApi#deleteByCodesVoid32");
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

<a name="doAutocompletePageOI40DBTaskResourceReservation"></a>
# **doAutocompletePageOI40DBTaskResourceReservation**
> PageOI40DBTaskResourceReservation doAutocompletePageOI40DBTaskResourceReservation(autoCompleteData)

doAutocomplete

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbTaskResourceReservationRepositoryApi;


Oi40DbTaskResourceReservationRepositoryApi apiInstance = new Oi40DbTaskResourceReservationRepositoryApi();
AutoCompleteData autoCompleteData = new AutoCompleteData(); // AutoCompleteData | autoCompleteData
try {
    PageOI40DBTaskResourceReservation result = apiInstance.doAutocompletePageOI40DBTaskResourceReservation(autoCompleteData);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbTaskResourceReservationRepositoryApi#doAutocompletePageOI40DBTaskResourceReservation");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **autoCompleteData** | [**AutoCompleteData**](AutoCompleteData.md)| autoCompleteData |

### Return type

[**PageOI40DBTaskResourceReservation**](PageOI40DBTaskResourceReservation.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: */*

<a name="doLookupPageOI40DBTaskResourceReservation"></a>
# **doLookupPageOI40DBTaskResourceReservation**
> PageOI40DBTaskResourceReservation doLookupPageOI40DBTaskResourceReservation(lookup)

doLookup

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbTaskResourceReservationRepositoryApi;


Oi40DbTaskResourceReservationRepositoryApi apiInstance = new Oi40DbTaskResourceReservationRepositoryApi();
LookupData lookup = new LookupData(); // LookupData | lookup
try {
    PageOI40DBTaskResourceReservation result = apiInstance.doLookupPageOI40DBTaskResourceReservation(lookup);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbTaskResourceReservationRepositoryApi#doLookupPageOI40DBTaskResourceReservation");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **lookup** | [**LookupData**](LookupData.md)| lookup |

### Return type

[**PageOI40DBTaskResourceReservation**](PageOI40DBTaskResourceReservation.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: */*

<a name="findAllListOI40DBTaskResourceReservation"></a>
# **findAllListOI40DBTaskResourceReservation**
> List&lt;OI40DBTaskResourceReservation&gt; findAllListOI40DBTaskResourceReservation()

findAll

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbTaskResourceReservationRepositoryApi;


Oi40DbTaskResourceReservationRepositoryApi apiInstance = new Oi40DbTaskResourceReservationRepositoryApi();
try {
    List<OI40DBTaskResourceReservation> result = apiInstance.findAllListOI40DBTaskResourceReservation();
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbTaskResourceReservationRepositoryApi#findAllListOI40DBTaskResourceReservation");
    e.printStackTrace();
}
```

### Parameters
This endpoint does not need any parameter.

### Return type

[**List&lt;OI40DBTaskResourceReservation&gt;**](OI40DBTaskResourceReservation.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="findAllPageOI40DBTaskResourceReservation"></a>
# **findAllPageOI40DBTaskResourceReservation**
> PageOI40DBTaskResourceReservation findAllPageOI40DBTaskResourceReservation(p)

findAll

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbTaskResourceReservationRepositoryApi;


Oi40DbTaskResourceReservationRepositoryApi apiInstance = new Oi40DbTaskResourceReservationRepositoryApi();
PageInfo p = new PageInfo(); // PageInfo | p
try {
    PageOI40DBTaskResourceReservation result = apiInstance.findAllPageOI40DBTaskResourceReservation(p);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbTaskResourceReservationRepositoryApi#findAllPageOI40DBTaskResourceReservation");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **p** | [**PageInfo**](PageInfo.md)| p |

### Return type

[**PageOI40DBTaskResourceReservation**](PageOI40DBTaskResourceReservation.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="findByAfterIntegrationTsListOI40DBTaskResourceReservation"></a>
# **findByAfterIntegrationTsListOI40DBTaskResourceReservation**
> List&lt;OI40DBTaskResourceReservation&gt; findByAfterIntegrationTsListOI40DBTaskResourceReservation(ts)

findByAfterIntegrationTs

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbTaskResourceReservationRepositoryApi;


Oi40DbTaskResourceReservationRepositoryApi apiInstance = new Oi40DbTaskResourceReservationRepositoryApi();
String ts = "ts_example"; // String | ts
try {
    List<OI40DBTaskResourceReservation> result = apiInstance.findByAfterIntegrationTsListOI40DBTaskResourceReservation(ts);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbTaskResourceReservationRepositoryApi#findByAfterIntegrationTsListOI40DBTaskResourceReservation");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **ts** | **String**| ts |

### Return type

[**List&lt;OI40DBTaskResourceReservation&gt;**](OI40DBTaskResourceReservation.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*

<a name="findByAfterModifiedTimestampListOI40DBTaskResourceReservation"></a>
# **findByAfterModifiedTimestampListOI40DBTaskResourceReservation**
> List&lt;OI40DBTaskResourceReservation&gt; findByAfterModifiedTimestampListOI40DBTaskResourceReservation(ts)

findByAfterModifiedTimestamp

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbTaskResourceReservationRepositoryApi;


Oi40DbTaskResourceReservationRepositoryApi apiInstance = new Oi40DbTaskResourceReservationRepositoryApi();
String ts = "ts_example"; // String | ts
try {
    List<OI40DBTaskResourceReservation> result = apiInstance.findByAfterModifiedTimestampListOI40DBTaskResourceReservation(ts);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbTaskResourceReservationRepositoryApi#findByAfterModifiedTimestampListOI40DBTaskResourceReservation");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **ts** | **String**| ts |

### Return type

[**List&lt;OI40DBTaskResourceReservation&gt;**](OI40DBTaskResourceReservation.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*

<a name="findByCodeOI40DBTaskResourceReservation"></a>
# **findByCodeOI40DBTaskResourceReservation**
> OI40DBTaskResourceReservation findByCodeOI40DBTaskResourceReservation(code)

findByCode

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbTaskResourceReservationRepositoryApi;


Oi40DbTaskResourceReservationRepositoryApi apiInstance = new Oi40DbTaskResourceReservationRepositoryApi();
String code = "code_example"; // String | code
try {
    OI40DBTaskResourceReservation result = apiInstance.findByCodeOI40DBTaskResourceReservation(code);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbTaskResourceReservationRepositoryApi#findByCodeOI40DBTaskResourceReservation");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **code** | **String**| code |

### Return type

[**OI40DBTaskResourceReservation**](OI40DBTaskResourceReservation.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="findByCodesListOI40DBTaskResourceReservation"></a>
# **findByCodesListOI40DBTaskResourceReservation**
> List&lt;OI40DBTaskResourceReservation&gt; findByCodesListOI40DBTaskResourceReservation(codes)

findByCodes

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbTaskResourceReservationRepositoryApi;


Oi40DbTaskResourceReservationRepositoryApi apiInstance = new Oi40DbTaskResourceReservationRepositoryApi();
List<String> codes = Arrays.asList(new List<String>()); // List<String> | codes
try {
    List<OI40DBTaskResourceReservation> result = apiInstance.findByCodesListOI40DBTaskResourceReservation(codes);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbTaskResourceReservationRepositoryApi#findByCodesListOI40DBTaskResourceReservation");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **codes** | **List&lt;String&gt;**| codes |

### Return type

[**List&lt;OI40DBTaskResourceReservation&gt;**](OI40DBTaskResourceReservation.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="findByQbeListOI40DBTaskResourceReservation"></a>
# **findByQbeListOI40DBTaskResourceReservation**
> List&lt;OI40DBTaskResourceReservation&gt; findByQbeListOI40DBTaskResourceReservation(qbe)

findByQbe

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbTaskResourceReservationRepositoryApi;


Oi40DbTaskResourceReservationRepositoryApi apiInstance = new Oi40DbTaskResourceReservationRepositoryApi();
OI40DBTaskResourceReservation qbe = new OI40DBTaskResourceReservation(); // OI40DBTaskResourceReservation | qbe
try {
    List<OI40DBTaskResourceReservation> result = apiInstance.findByQbeListOI40DBTaskResourceReservation(qbe);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbTaskResourceReservationRepositoryApi#findByQbeListOI40DBTaskResourceReservation");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **qbe** | [**OI40DBTaskResourceReservation**](OI40DBTaskResourceReservation.md)| qbe |

### Return type

[**List&lt;OI40DBTaskResourceReservation&gt;**](OI40DBTaskResourceReservation.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="findByQbePagedPageOI40DBTaskResourceReservation"></a>
# **findByQbePagedPageOI40DBTaskResourceReservation**
> PageOI40DBTaskResourceReservation findByQbePagedPageOI40DBTaskResourceReservation(qbe)

findByQbePaged

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbTaskResourceReservationRepositoryApi;


Oi40DbTaskResourceReservationRepositoryApi apiInstance = new Oi40DbTaskResourceReservationRepositoryApi();
QbeSupportOI40DBTaskResourceReservation qbe = new QbeSupportOI40DBTaskResourceReservation(); // QbeSupportOI40DBTaskResourceReservation | qbe
try {
    PageOI40DBTaskResourceReservation result = apiInstance.findByQbePagedPageOI40DBTaskResourceReservation(qbe);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbTaskResourceReservationRepositoryApi#findByQbePagedPageOI40DBTaskResourceReservation");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **qbe** | [**QbeSupportOI40DBTaskResourceReservation**](QbeSupportOI40DBTaskResourceReservation.md)| qbe |

### Return type

[**PageOI40DBTaskResourceReservation**](PageOI40DBTaskResourceReservation.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="updateListOI40DBTaskResourceReservation"></a>
# **updateListOI40DBTaskResourceReservation**
> List&lt;OI40DBTaskResourceReservation&gt; updateListOI40DBTaskResourceReservation(data)

update

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbTaskResourceReservationRepositoryApi;


Oi40DbTaskResourceReservationRepositoryApi apiInstance = new Oi40DbTaskResourceReservationRepositoryApi();
List<OI40DBTaskResourceReservation> data = Arrays.asList(new OI40DBTaskResourceReservation()); // List<OI40DBTaskResourceReservation> | data
try {
    List<OI40DBTaskResourceReservation> result = apiInstance.updateListOI40DBTaskResourceReservation(data);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbTaskResourceReservationRepositoryApi#updateListOI40DBTaskResourceReservation");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **data** | [**List&lt;OI40DBTaskResourceReservation&gt;**](OI40DBTaskResourceReservation.md)| data |

### Return type

[**List&lt;OI40DBTaskResourceReservation&gt;**](OI40DBTaskResourceReservation.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="updateSingleOI40DBTaskResourceReservation"></a>
# **updateSingleOI40DBTaskResourceReservation**
> OI40DBTaskResourceReservation updateSingleOI40DBTaskResourceReservation(data)

updateSingle

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbTaskResourceReservationRepositoryApi;


Oi40DbTaskResourceReservationRepositoryApi apiInstance = new Oi40DbTaskResourceReservationRepositoryApi();
OI40DBTaskResourceReservation data = new OI40DBTaskResourceReservation(); // OI40DBTaskResourceReservation | data
try {
    OI40DBTaskResourceReservation result = apiInstance.updateSingleOI40DBTaskResourceReservation(data);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbTaskResourceReservationRepositoryApi#updateSingleOI40DBTaskResourceReservation");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **data** | [**OI40DBTaskResourceReservation**](OI40DBTaskResourceReservation.md)| data |

### Return type

[**OI40DBTaskResourceReservation**](OI40DBTaskResourceReservation.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

