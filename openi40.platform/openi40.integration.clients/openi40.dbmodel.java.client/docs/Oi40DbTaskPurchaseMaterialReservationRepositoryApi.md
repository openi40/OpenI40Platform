# Oi40DbTaskPurchaseMaterialReservationRepositoryApi

All URIs are relative to *https://localhost:8083/openi40-backoffice*

Method | HTTP request | Description
------------- | ------------- | -------------
[**deleteByCodeVoid29**](Oi40DbTaskPurchaseMaterialReservationRepositoryApi.md#deleteByCodeVoid29) | **GET** /integration/OI40DBTaskPurchaseMaterialReservation/deleteByCode/{code} | deleteByCode
[**deleteByCodesVoid29**](Oi40DbTaskPurchaseMaterialReservationRepositoryApi.md#deleteByCodesVoid29) | **POST** /integration/OI40DBTaskPurchaseMaterialReservation/deleteByCodes | deleteByCodes
[**doAutocompletePageOI40DBTaskPurchaseMaterialReservation**](Oi40DbTaskPurchaseMaterialReservationRepositoryApi.md#doAutocompletePageOI40DBTaskPurchaseMaterialReservation) | **POST** /integration/OI40DBTaskPurchaseMaterialReservation/doAutocomplete | doAutocomplete
[**doLookupPageOI40DBTaskPurchaseMaterialReservation**](Oi40DbTaskPurchaseMaterialReservationRepositoryApi.md#doLookupPageOI40DBTaskPurchaseMaterialReservation) | **POST** /integration/OI40DBTaskPurchaseMaterialReservation/doLookup | doLookup
[**findAllListOI40DBTaskPurchaseMaterialReservation**](Oi40DbTaskPurchaseMaterialReservationRepositoryApi.md#findAllListOI40DBTaskPurchaseMaterialReservation) | **GET** /integration/OI40DBTaskPurchaseMaterialReservation | findAll
[**findAllPageOI40DBTaskPurchaseMaterialReservation**](Oi40DbTaskPurchaseMaterialReservationRepositoryApi.md#findAllPageOI40DBTaskPurchaseMaterialReservation) | **POST** /integration/OI40DBTaskPurchaseMaterialReservation/findAllPaged | findAll
[**findByAfterIntegrationTsListOI40DBTaskPurchaseMaterialReservation**](Oi40DbTaskPurchaseMaterialReservationRepositoryApi.md#findByAfterIntegrationTsListOI40DBTaskPurchaseMaterialReservation) | **GET** /integration/OI40DBTaskPurchaseMaterialReservation/integratedAfter/{ts} | findByAfterIntegrationTs
[**findByAfterModifiedTimestampListOI40DBTaskPurchaseMaterialReservation**](Oi40DbTaskPurchaseMaterialReservationRepositoryApi.md#findByAfterModifiedTimestampListOI40DBTaskPurchaseMaterialReservation) | **GET** /integration/OI40DBTaskPurchaseMaterialReservation/modifiedAfter/{ts} | findByAfterModifiedTimestamp
[**findByCodeOI40DBTaskPurchaseMaterialReservation**](Oi40DbTaskPurchaseMaterialReservationRepositoryApi.md#findByCodeOI40DBTaskPurchaseMaterialReservation) | **GET** /integration/OI40DBTaskPurchaseMaterialReservation/byCode/{code} | findByCode
[**findByCodesListOI40DBTaskPurchaseMaterialReservation**](Oi40DbTaskPurchaseMaterialReservationRepositoryApi.md#findByCodesListOI40DBTaskPurchaseMaterialReservation) | **POST** /integration/OI40DBTaskPurchaseMaterialReservation/findByCodes | findByCodes
[**findByQbeListOI40DBTaskPurchaseMaterialReservation**](Oi40DbTaskPurchaseMaterialReservationRepositoryApi.md#findByQbeListOI40DBTaskPurchaseMaterialReservation) | **POST** /integration/OI40DBTaskPurchaseMaterialReservation/findByQbe | findByQbe
[**findByQbePagedPageOI40DBTaskPurchaseMaterialReservation**](Oi40DbTaskPurchaseMaterialReservationRepositoryApi.md#findByQbePagedPageOI40DBTaskPurchaseMaterialReservation) | **POST** /integration/OI40DBTaskPurchaseMaterialReservation/findByQbePaged | findByQbePaged
[**updateListOI40DBTaskPurchaseMaterialReservation**](Oi40DbTaskPurchaseMaterialReservationRepositoryApi.md#updateListOI40DBTaskPurchaseMaterialReservation) | **POST** /integration/OI40DBTaskPurchaseMaterialReservation/update | update
[**updateSingleOI40DBTaskPurchaseMaterialReservation**](Oi40DbTaskPurchaseMaterialReservationRepositoryApi.md#updateSingleOI40DBTaskPurchaseMaterialReservation) | **POST** /integration/OI40DBTaskPurchaseMaterialReservation/updateSingle | updateSingle


<a name="deleteByCodeVoid29"></a>
# **deleteByCodeVoid29**
> deleteByCodeVoid29(code)

deleteByCode

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbTaskPurchaseMaterialReservationRepositoryApi;


Oi40DbTaskPurchaseMaterialReservationRepositoryApi apiInstance = new Oi40DbTaskPurchaseMaterialReservationRepositoryApi();
String code = "code_example"; // String | code
try {
    apiInstance.deleteByCodeVoid29(code);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbTaskPurchaseMaterialReservationRepositoryApi#deleteByCodeVoid29");
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

<a name="deleteByCodesVoid29"></a>
# **deleteByCodesVoid29**
> deleteByCodesVoid29(codes)

deleteByCodes

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbTaskPurchaseMaterialReservationRepositoryApi;


Oi40DbTaskPurchaseMaterialReservationRepositoryApi apiInstance = new Oi40DbTaskPurchaseMaterialReservationRepositoryApi();
List<String> codes = Arrays.asList(new List<String>()); // List<String> | codes
try {
    apiInstance.deleteByCodesVoid29(codes);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbTaskPurchaseMaterialReservationRepositoryApi#deleteByCodesVoid29");
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

<a name="doAutocompletePageOI40DBTaskPurchaseMaterialReservation"></a>
# **doAutocompletePageOI40DBTaskPurchaseMaterialReservation**
> PageOI40DBTaskPurchaseMaterialReservation doAutocompletePageOI40DBTaskPurchaseMaterialReservation(autoCompleteData)

doAutocomplete

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbTaskPurchaseMaterialReservationRepositoryApi;


Oi40DbTaskPurchaseMaterialReservationRepositoryApi apiInstance = new Oi40DbTaskPurchaseMaterialReservationRepositoryApi();
AutoCompleteData autoCompleteData = new AutoCompleteData(); // AutoCompleteData | autoCompleteData
try {
    PageOI40DBTaskPurchaseMaterialReservation result = apiInstance.doAutocompletePageOI40DBTaskPurchaseMaterialReservation(autoCompleteData);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbTaskPurchaseMaterialReservationRepositoryApi#doAutocompletePageOI40DBTaskPurchaseMaterialReservation");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **autoCompleteData** | [**AutoCompleteData**](AutoCompleteData.md)| autoCompleteData |

### Return type

[**PageOI40DBTaskPurchaseMaterialReservation**](PageOI40DBTaskPurchaseMaterialReservation.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: */*

<a name="doLookupPageOI40DBTaskPurchaseMaterialReservation"></a>
# **doLookupPageOI40DBTaskPurchaseMaterialReservation**
> PageOI40DBTaskPurchaseMaterialReservation doLookupPageOI40DBTaskPurchaseMaterialReservation(lookup)

doLookup

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbTaskPurchaseMaterialReservationRepositoryApi;


Oi40DbTaskPurchaseMaterialReservationRepositoryApi apiInstance = new Oi40DbTaskPurchaseMaterialReservationRepositoryApi();
LookupData lookup = new LookupData(); // LookupData | lookup
try {
    PageOI40DBTaskPurchaseMaterialReservation result = apiInstance.doLookupPageOI40DBTaskPurchaseMaterialReservation(lookup);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbTaskPurchaseMaterialReservationRepositoryApi#doLookupPageOI40DBTaskPurchaseMaterialReservation");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **lookup** | [**LookupData**](LookupData.md)| lookup |

### Return type

[**PageOI40DBTaskPurchaseMaterialReservation**](PageOI40DBTaskPurchaseMaterialReservation.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: */*

<a name="findAllListOI40DBTaskPurchaseMaterialReservation"></a>
# **findAllListOI40DBTaskPurchaseMaterialReservation**
> List&lt;OI40DBTaskPurchaseMaterialReservation&gt; findAllListOI40DBTaskPurchaseMaterialReservation()

findAll

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbTaskPurchaseMaterialReservationRepositoryApi;


Oi40DbTaskPurchaseMaterialReservationRepositoryApi apiInstance = new Oi40DbTaskPurchaseMaterialReservationRepositoryApi();
try {
    List<OI40DBTaskPurchaseMaterialReservation> result = apiInstance.findAllListOI40DBTaskPurchaseMaterialReservation();
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbTaskPurchaseMaterialReservationRepositoryApi#findAllListOI40DBTaskPurchaseMaterialReservation");
    e.printStackTrace();
}
```

### Parameters
This endpoint does not need any parameter.

### Return type

[**List&lt;OI40DBTaskPurchaseMaterialReservation&gt;**](OI40DBTaskPurchaseMaterialReservation.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="findAllPageOI40DBTaskPurchaseMaterialReservation"></a>
# **findAllPageOI40DBTaskPurchaseMaterialReservation**
> PageOI40DBTaskPurchaseMaterialReservation findAllPageOI40DBTaskPurchaseMaterialReservation(p)

findAll

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbTaskPurchaseMaterialReservationRepositoryApi;


Oi40DbTaskPurchaseMaterialReservationRepositoryApi apiInstance = new Oi40DbTaskPurchaseMaterialReservationRepositoryApi();
PageInfo p = new PageInfo(); // PageInfo | p
try {
    PageOI40DBTaskPurchaseMaterialReservation result = apiInstance.findAllPageOI40DBTaskPurchaseMaterialReservation(p);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbTaskPurchaseMaterialReservationRepositoryApi#findAllPageOI40DBTaskPurchaseMaterialReservation");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **p** | [**PageInfo**](PageInfo.md)| p |

### Return type

[**PageOI40DBTaskPurchaseMaterialReservation**](PageOI40DBTaskPurchaseMaterialReservation.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="findByAfterIntegrationTsListOI40DBTaskPurchaseMaterialReservation"></a>
# **findByAfterIntegrationTsListOI40DBTaskPurchaseMaterialReservation**
> List&lt;OI40DBTaskPurchaseMaterialReservation&gt; findByAfterIntegrationTsListOI40DBTaskPurchaseMaterialReservation(ts)

findByAfterIntegrationTs

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbTaskPurchaseMaterialReservationRepositoryApi;


Oi40DbTaskPurchaseMaterialReservationRepositoryApi apiInstance = new Oi40DbTaskPurchaseMaterialReservationRepositoryApi();
String ts = "ts_example"; // String | ts
try {
    List<OI40DBTaskPurchaseMaterialReservation> result = apiInstance.findByAfterIntegrationTsListOI40DBTaskPurchaseMaterialReservation(ts);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbTaskPurchaseMaterialReservationRepositoryApi#findByAfterIntegrationTsListOI40DBTaskPurchaseMaterialReservation");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **ts** | **String**| ts |

### Return type

[**List&lt;OI40DBTaskPurchaseMaterialReservation&gt;**](OI40DBTaskPurchaseMaterialReservation.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*

<a name="findByAfterModifiedTimestampListOI40DBTaskPurchaseMaterialReservation"></a>
# **findByAfterModifiedTimestampListOI40DBTaskPurchaseMaterialReservation**
> List&lt;OI40DBTaskPurchaseMaterialReservation&gt; findByAfterModifiedTimestampListOI40DBTaskPurchaseMaterialReservation(ts)

findByAfterModifiedTimestamp

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbTaskPurchaseMaterialReservationRepositoryApi;


Oi40DbTaskPurchaseMaterialReservationRepositoryApi apiInstance = new Oi40DbTaskPurchaseMaterialReservationRepositoryApi();
String ts = "ts_example"; // String | ts
try {
    List<OI40DBTaskPurchaseMaterialReservation> result = apiInstance.findByAfterModifiedTimestampListOI40DBTaskPurchaseMaterialReservation(ts);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbTaskPurchaseMaterialReservationRepositoryApi#findByAfterModifiedTimestampListOI40DBTaskPurchaseMaterialReservation");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **ts** | **String**| ts |

### Return type

[**List&lt;OI40DBTaskPurchaseMaterialReservation&gt;**](OI40DBTaskPurchaseMaterialReservation.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*

<a name="findByCodeOI40DBTaskPurchaseMaterialReservation"></a>
# **findByCodeOI40DBTaskPurchaseMaterialReservation**
> OI40DBTaskPurchaseMaterialReservation findByCodeOI40DBTaskPurchaseMaterialReservation(code)

findByCode

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbTaskPurchaseMaterialReservationRepositoryApi;


Oi40DbTaskPurchaseMaterialReservationRepositoryApi apiInstance = new Oi40DbTaskPurchaseMaterialReservationRepositoryApi();
String code = "code_example"; // String | code
try {
    OI40DBTaskPurchaseMaterialReservation result = apiInstance.findByCodeOI40DBTaskPurchaseMaterialReservation(code);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbTaskPurchaseMaterialReservationRepositoryApi#findByCodeOI40DBTaskPurchaseMaterialReservation");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **code** | **String**| code |

### Return type

[**OI40DBTaskPurchaseMaterialReservation**](OI40DBTaskPurchaseMaterialReservation.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="findByCodesListOI40DBTaskPurchaseMaterialReservation"></a>
# **findByCodesListOI40DBTaskPurchaseMaterialReservation**
> List&lt;OI40DBTaskPurchaseMaterialReservation&gt; findByCodesListOI40DBTaskPurchaseMaterialReservation(codes)

findByCodes

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbTaskPurchaseMaterialReservationRepositoryApi;


Oi40DbTaskPurchaseMaterialReservationRepositoryApi apiInstance = new Oi40DbTaskPurchaseMaterialReservationRepositoryApi();
List<String> codes = Arrays.asList(new List<String>()); // List<String> | codes
try {
    List<OI40DBTaskPurchaseMaterialReservation> result = apiInstance.findByCodesListOI40DBTaskPurchaseMaterialReservation(codes);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbTaskPurchaseMaterialReservationRepositoryApi#findByCodesListOI40DBTaskPurchaseMaterialReservation");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **codes** | **List&lt;String&gt;**| codes |

### Return type

[**List&lt;OI40DBTaskPurchaseMaterialReservation&gt;**](OI40DBTaskPurchaseMaterialReservation.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="findByQbeListOI40DBTaskPurchaseMaterialReservation"></a>
# **findByQbeListOI40DBTaskPurchaseMaterialReservation**
> List&lt;OI40DBTaskPurchaseMaterialReservation&gt; findByQbeListOI40DBTaskPurchaseMaterialReservation(qbe)

findByQbe

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbTaskPurchaseMaterialReservationRepositoryApi;


Oi40DbTaskPurchaseMaterialReservationRepositoryApi apiInstance = new Oi40DbTaskPurchaseMaterialReservationRepositoryApi();
OI40DBTaskPurchaseMaterialReservation qbe = new OI40DBTaskPurchaseMaterialReservation(); // OI40DBTaskPurchaseMaterialReservation | qbe
try {
    List<OI40DBTaskPurchaseMaterialReservation> result = apiInstance.findByQbeListOI40DBTaskPurchaseMaterialReservation(qbe);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbTaskPurchaseMaterialReservationRepositoryApi#findByQbeListOI40DBTaskPurchaseMaterialReservation");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **qbe** | [**OI40DBTaskPurchaseMaterialReservation**](OI40DBTaskPurchaseMaterialReservation.md)| qbe |

### Return type

[**List&lt;OI40DBTaskPurchaseMaterialReservation&gt;**](OI40DBTaskPurchaseMaterialReservation.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="findByQbePagedPageOI40DBTaskPurchaseMaterialReservation"></a>
# **findByQbePagedPageOI40DBTaskPurchaseMaterialReservation**
> PageOI40DBTaskPurchaseMaterialReservation findByQbePagedPageOI40DBTaskPurchaseMaterialReservation(qbe)

findByQbePaged

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbTaskPurchaseMaterialReservationRepositoryApi;


Oi40DbTaskPurchaseMaterialReservationRepositoryApi apiInstance = new Oi40DbTaskPurchaseMaterialReservationRepositoryApi();
QbeSupportOI40DBTaskPurchaseMaterialReservation qbe = new QbeSupportOI40DBTaskPurchaseMaterialReservation(); // QbeSupportOI40DBTaskPurchaseMaterialReservation | qbe
try {
    PageOI40DBTaskPurchaseMaterialReservation result = apiInstance.findByQbePagedPageOI40DBTaskPurchaseMaterialReservation(qbe);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbTaskPurchaseMaterialReservationRepositoryApi#findByQbePagedPageOI40DBTaskPurchaseMaterialReservation");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **qbe** | [**QbeSupportOI40DBTaskPurchaseMaterialReservation**](QbeSupportOI40DBTaskPurchaseMaterialReservation.md)| qbe |

### Return type

[**PageOI40DBTaskPurchaseMaterialReservation**](PageOI40DBTaskPurchaseMaterialReservation.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="updateListOI40DBTaskPurchaseMaterialReservation"></a>
# **updateListOI40DBTaskPurchaseMaterialReservation**
> List&lt;OI40DBTaskPurchaseMaterialReservation&gt; updateListOI40DBTaskPurchaseMaterialReservation(data)

update

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbTaskPurchaseMaterialReservationRepositoryApi;


Oi40DbTaskPurchaseMaterialReservationRepositoryApi apiInstance = new Oi40DbTaskPurchaseMaterialReservationRepositoryApi();
List<OI40DBTaskPurchaseMaterialReservation> data = Arrays.asList(new OI40DBTaskPurchaseMaterialReservation()); // List<OI40DBTaskPurchaseMaterialReservation> | data
try {
    List<OI40DBTaskPurchaseMaterialReservation> result = apiInstance.updateListOI40DBTaskPurchaseMaterialReservation(data);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbTaskPurchaseMaterialReservationRepositoryApi#updateListOI40DBTaskPurchaseMaterialReservation");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **data** | [**List&lt;OI40DBTaskPurchaseMaterialReservation&gt;**](OI40DBTaskPurchaseMaterialReservation.md)| data |

### Return type

[**List&lt;OI40DBTaskPurchaseMaterialReservation&gt;**](OI40DBTaskPurchaseMaterialReservation.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="updateSingleOI40DBTaskPurchaseMaterialReservation"></a>
# **updateSingleOI40DBTaskPurchaseMaterialReservation**
> OI40DBTaskPurchaseMaterialReservation updateSingleOI40DBTaskPurchaseMaterialReservation(data)

updateSingle

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbTaskPurchaseMaterialReservationRepositoryApi;


Oi40DbTaskPurchaseMaterialReservationRepositoryApi apiInstance = new Oi40DbTaskPurchaseMaterialReservationRepositoryApi();
OI40DBTaskPurchaseMaterialReservation data = new OI40DBTaskPurchaseMaterialReservation(); // OI40DBTaskPurchaseMaterialReservation | data
try {
    OI40DBTaskPurchaseMaterialReservation result = apiInstance.updateSingleOI40DBTaskPurchaseMaterialReservation(data);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbTaskPurchaseMaterialReservationRepositoryApi#updateSingleOI40DBTaskPurchaseMaterialReservation");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **data** | [**OI40DBTaskPurchaseMaterialReservation**](OI40DBTaskPurchaseMaterialReservation.md)| data |

### Return type

[**OI40DBTaskPurchaseMaterialReservation**](OI40DBTaskPurchaseMaterialReservation.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

