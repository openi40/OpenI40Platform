# Oi40DbTaskProductionMaterialReservationRepositoryApi

All URIs are relative to *https://localhost:8083/openi40-backoffice*

Method | HTTP request | Description
------------- | ------------- | -------------
[**deleteByCodeVoid28**](Oi40DbTaskProductionMaterialReservationRepositoryApi.md#deleteByCodeVoid28) | **GET** /integration/OI40DBTaskProductionMaterialReservation/deleteByCode/{code} | deleteByCode
[**deleteByCodesVoid28**](Oi40DbTaskProductionMaterialReservationRepositoryApi.md#deleteByCodesVoid28) | **POST** /integration/OI40DBTaskProductionMaterialReservation/deleteByCodes | deleteByCodes
[**doAutocompletePageOI40DBTaskProductionMaterialReservation**](Oi40DbTaskProductionMaterialReservationRepositoryApi.md#doAutocompletePageOI40DBTaskProductionMaterialReservation) | **POST** /integration/OI40DBTaskProductionMaterialReservation/doAutocomplete | doAutocomplete
[**doLookupPageOI40DBTaskProductionMaterialReservation**](Oi40DbTaskProductionMaterialReservationRepositoryApi.md#doLookupPageOI40DBTaskProductionMaterialReservation) | **POST** /integration/OI40DBTaskProductionMaterialReservation/doLookup | doLookup
[**findAllListOI40DBTaskProductionMaterialReservation**](Oi40DbTaskProductionMaterialReservationRepositoryApi.md#findAllListOI40DBTaskProductionMaterialReservation) | **GET** /integration/OI40DBTaskProductionMaterialReservation | findAll
[**findAllPageOI40DBTaskProductionMaterialReservation**](Oi40DbTaskProductionMaterialReservationRepositoryApi.md#findAllPageOI40DBTaskProductionMaterialReservation) | **POST** /integration/OI40DBTaskProductionMaterialReservation/findAllPaged | findAll
[**findByAfterIntegrationTsListOI40DBTaskProductionMaterialReservation**](Oi40DbTaskProductionMaterialReservationRepositoryApi.md#findByAfterIntegrationTsListOI40DBTaskProductionMaterialReservation) | **GET** /integration/OI40DBTaskProductionMaterialReservation/integratedAfter/{ts} | findByAfterIntegrationTs
[**findByAfterModifiedTimestampListOI40DBTaskProductionMaterialReservation**](Oi40DbTaskProductionMaterialReservationRepositoryApi.md#findByAfterModifiedTimestampListOI40DBTaskProductionMaterialReservation) | **GET** /integration/OI40DBTaskProductionMaterialReservation/modifiedAfter/{ts} | findByAfterModifiedTimestamp
[**findByCodeOI40DBTaskProductionMaterialReservation**](Oi40DbTaskProductionMaterialReservationRepositoryApi.md#findByCodeOI40DBTaskProductionMaterialReservation) | **GET** /integration/OI40DBTaskProductionMaterialReservation/byCode/{code} | findByCode
[**findByCodesListOI40DBTaskProductionMaterialReservation**](Oi40DbTaskProductionMaterialReservationRepositoryApi.md#findByCodesListOI40DBTaskProductionMaterialReservation) | **POST** /integration/OI40DBTaskProductionMaterialReservation/findByCodes | findByCodes
[**findByQbeListOI40DBTaskProductionMaterialReservation**](Oi40DbTaskProductionMaterialReservationRepositoryApi.md#findByQbeListOI40DBTaskProductionMaterialReservation) | **POST** /integration/OI40DBTaskProductionMaterialReservation/findByQbe | findByQbe
[**findByQbePagedPageOI40DBTaskProductionMaterialReservation**](Oi40DbTaskProductionMaterialReservationRepositoryApi.md#findByQbePagedPageOI40DBTaskProductionMaterialReservation) | **POST** /integration/OI40DBTaskProductionMaterialReservation/findByQbePaged | findByQbePaged
[**updateListOI40DBTaskProductionMaterialReservation**](Oi40DbTaskProductionMaterialReservationRepositoryApi.md#updateListOI40DBTaskProductionMaterialReservation) | **POST** /integration/OI40DBTaskProductionMaterialReservation/update | update
[**updateSingleOI40DBTaskProductionMaterialReservation**](Oi40DbTaskProductionMaterialReservationRepositoryApi.md#updateSingleOI40DBTaskProductionMaterialReservation) | **POST** /integration/OI40DBTaskProductionMaterialReservation/updateSingle | updateSingle


<a name="deleteByCodeVoid28"></a>
# **deleteByCodeVoid28**
> deleteByCodeVoid28(code)

deleteByCode

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbTaskProductionMaterialReservationRepositoryApi;


Oi40DbTaskProductionMaterialReservationRepositoryApi apiInstance = new Oi40DbTaskProductionMaterialReservationRepositoryApi();
String code = "code_example"; // String | code
try {
    apiInstance.deleteByCodeVoid28(code);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbTaskProductionMaterialReservationRepositoryApi#deleteByCodeVoid28");
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

<a name="deleteByCodesVoid28"></a>
# **deleteByCodesVoid28**
> deleteByCodesVoid28(codes)

deleteByCodes

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbTaskProductionMaterialReservationRepositoryApi;


Oi40DbTaskProductionMaterialReservationRepositoryApi apiInstance = new Oi40DbTaskProductionMaterialReservationRepositoryApi();
List<String> codes = Arrays.asList(new List<String>()); // List<String> | codes
try {
    apiInstance.deleteByCodesVoid28(codes);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbTaskProductionMaterialReservationRepositoryApi#deleteByCodesVoid28");
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

<a name="doAutocompletePageOI40DBTaskProductionMaterialReservation"></a>
# **doAutocompletePageOI40DBTaskProductionMaterialReservation**
> PageOI40DBTaskProductionMaterialReservation doAutocompletePageOI40DBTaskProductionMaterialReservation(autoCompleteData)

doAutocomplete

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbTaskProductionMaterialReservationRepositoryApi;


Oi40DbTaskProductionMaterialReservationRepositoryApi apiInstance = new Oi40DbTaskProductionMaterialReservationRepositoryApi();
AutoCompleteData autoCompleteData = new AutoCompleteData(); // AutoCompleteData | autoCompleteData
try {
    PageOI40DBTaskProductionMaterialReservation result = apiInstance.doAutocompletePageOI40DBTaskProductionMaterialReservation(autoCompleteData);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbTaskProductionMaterialReservationRepositoryApi#doAutocompletePageOI40DBTaskProductionMaterialReservation");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **autoCompleteData** | [**AutoCompleteData**](AutoCompleteData.md)| autoCompleteData |

### Return type

[**PageOI40DBTaskProductionMaterialReservation**](PageOI40DBTaskProductionMaterialReservation.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: */*

<a name="doLookupPageOI40DBTaskProductionMaterialReservation"></a>
# **doLookupPageOI40DBTaskProductionMaterialReservation**
> PageOI40DBTaskProductionMaterialReservation doLookupPageOI40DBTaskProductionMaterialReservation(lookup)

doLookup

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbTaskProductionMaterialReservationRepositoryApi;


Oi40DbTaskProductionMaterialReservationRepositoryApi apiInstance = new Oi40DbTaskProductionMaterialReservationRepositoryApi();
LookupData lookup = new LookupData(); // LookupData | lookup
try {
    PageOI40DBTaskProductionMaterialReservation result = apiInstance.doLookupPageOI40DBTaskProductionMaterialReservation(lookup);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbTaskProductionMaterialReservationRepositoryApi#doLookupPageOI40DBTaskProductionMaterialReservation");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **lookup** | [**LookupData**](LookupData.md)| lookup |

### Return type

[**PageOI40DBTaskProductionMaterialReservation**](PageOI40DBTaskProductionMaterialReservation.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: */*

<a name="findAllListOI40DBTaskProductionMaterialReservation"></a>
# **findAllListOI40DBTaskProductionMaterialReservation**
> List&lt;OI40DBTaskProductionMaterialReservation&gt; findAllListOI40DBTaskProductionMaterialReservation()

findAll

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbTaskProductionMaterialReservationRepositoryApi;


Oi40DbTaskProductionMaterialReservationRepositoryApi apiInstance = new Oi40DbTaskProductionMaterialReservationRepositoryApi();
try {
    List<OI40DBTaskProductionMaterialReservation> result = apiInstance.findAllListOI40DBTaskProductionMaterialReservation();
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbTaskProductionMaterialReservationRepositoryApi#findAllListOI40DBTaskProductionMaterialReservation");
    e.printStackTrace();
}
```

### Parameters
This endpoint does not need any parameter.

### Return type

[**List&lt;OI40DBTaskProductionMaterialReservation&gt;**](OI40DBTaskProductionMaterialReservation.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="findAllPageOI40DBTaskProductionMaterialReservation"></a>
# **findAllPageOI40DBTaskProductionMaterialReservation**
> PageOI40DBTaskProductionMaterialReservation findAllPageOI40DBTaskProductionMaterialReservation(p)

findAll

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbTaskProductionMaterialReservationRepositoryApi;


Oi40DbTaskProductionMaterialReservationRepositoryApi apiInstance = new Oi40DbTaskProductionMaterialReservationRepositoryApi();
PageInfo p = new PageInfo(); // PageInfo | p
try {
    PageOI40DBTaskProductionMaterialReservation result = apiInstance.findAllPageOI40DBTaskProductionMaterialReservation(p);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbTaskProductionMaterialReservationRepositoryApi#findAllPageOI40DBTaskProductionMaterialReservation");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **p** | [**PageInfo**](PageInfo.md)| p |

### Return type

[**PageOI40DBTaskProductionMaterialReservation**](PageOI40DBTaskProductionMaterialReservation.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="findByAfterIntegrationTsListOI40DBTaskProductionMaterialReservation"></a>
# **findByAfterIntegrationTsListOI40DBTaskProductionMaterialReservation**
> List&lt;OI40DBTaskProductionMaterialReservation&gt; findByAfterIntegrationTsListOI40DBTaskProductionMaterialReservation(ts)

findByAfterIntegrationTs

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbTaskProductionMaterialReservationRepositoryApi;


Oi40DbTaskProductionMaterialReservationRepositoryApi apiInstance = new Oi40DbTaskProductionMaterialReservationRepositoryApi();
String ts = "ts_example"; // String | ts
try {
    List<OI40DBTaskProductionMaterialReservation> result = apiInstance.findByAfterIntegrationTsListOI40DBTaskProductionMaterialReservation(ts);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbTaskProductionMaterialReservationRepositoryApi#findByAfterIntegrationTsListOI40DBTaskProductionMaterialReservation");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **ts** | **String**| ts |

### Return type

[**List&lt;OI40DBTaskProductionMaterialReservation&gt;**](OI40DBTaskProductionMaterialReservation.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*

<a name="findByAfterModifiedTimestampListOI40DBTaskProductionMaterialReservation"></a>
# **findByAfterModifiedTimestampListOI40DBTaskProductionMaterialReservation**
> List&lt;OI40DBTaskProductionMaterialReservation&gt; findByAfterModifiedTimestampListOI40DBTaskProductionMaterialReservation(ts)

findByAfterModifiedTimestamp

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbTaskProductionMaterialReservationRepositoryApi;


Oi40DbTaskProductionMaterialReservationRepositoryApi apiInstance = new Oi40DbTaskProductionMaterialReservationRepositoryApi();
String ts = "ts_example"; // String | ts
try {
    List<OI40DBTaskProductionMaterialReservation> result = apiInstance.findByAfterModifiedTimestampListOI40DBTaskProductionMaterialReservation(ts);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbTaskProductionMaterialReservationRepositoryApi#findByAfterModifiedTimestampListOI40DBTaskProductionMaterialReservation");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **ts** | **String**| ts |

### Return type

[**List&lt;OI40DBTaskProductionMaterialReservation&gt;**](OI40DBTaskProductionMaterialReservation.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*

<a name="findByCodeOI40DBTaskProductionMaterialReservation"></a>
# **findByCodeOI40DBTaskProductionMaterialReservation**
> OI40DBTaskProductionMaterialReservation findByCodeOI40DBTaskProductionMaterialReservation(code)

findByCode

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbTaskProductionMaterialReservationRepositoryApi;


Oi40DbTaskProductionMaterialReservationRepositoryApi apiInstance = new Oi40DbTaskProductionMaterialReservationRepositoryApi();
String code = "code_example"; // String | code
try {
    OI40DBTaskProductionMaterialReservation result = apiInstance.findByCodeOI40DBTaskProductionMaterialReservation(code);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbTaskProductionMaterialReservationRepositoryApi#findByCodeOI40DBTaskProductionMaterialReservation");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **code** | **String**| code |

### Return type

[**OI40DBTaskProductionMaterialReservation**](OI40DBTaskProductionMaterialReservation.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="findByCodesListOI40DBTaskProductionMaterialReservation"></a>
# **findByCodesListOI40DBTaskProductionMaterialReservation**
> List&lt;OI40DBTaskProductionMaterialReservation&gt; findByCodesListOI40DBTaskProductionMaterialReservation(codes)

findByCodes

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbTaskProductionMaterialReservationRepositoryApi;


Oi40DbTaskProductionMaterialReservationRepositoryApi apiInstance = new Oi40DbTaskProductionMaterialReservationRepositoryApi();
List<String> codes = Arrays.asList(new List<String>()); // List<String> | codes
try {
    List<OI40DBTaskProductionMaterialReservation> result = apiInstance.findByCodesListOI40DBTaskProductionMaterialReservation(codes);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbTaskProductionMaterialReservationRepositoryApi#findByCodesListOI40DBTaskProductionMaterialReservation");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **codes** | **List&lt;String&gt;**| codes |

### Return type

[**List&lt;OI40DBTaskProductionMaterialReservation&gt;**](OI40DBTaskProductionMaterialReservation.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="findByQbeListOI40DBTaskProductionMaterialReservation"></a>
# **findByQbeListOI40DBTaskProductionMaterialReservation**
> List&lt;OI40DBTaskProductionMaterialReservation&gt; findByQbeListOI40DBTaskProductionMaterialReservation(qbe)

findByQbe

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbTaskProductionMaterialReservationRepositoryApi;


Oi40DbTaskProductionMaterialReservationRepositoryApi apiInstance = new Oi40DbTaskProductionMaterialReservationRepositoryApi();
OI40DBTaskProductionMaterialReservation qbe = new OI40DBTaskProductionMaterialReservation(); // OI40DBTaskProductionMaterialReservation | qbe
try {
    List<OI40DBTaskProductionMaterialReservation> result = apiInstance.findByQbeListOI40DBTaskProductionMaterialReservation(qbe);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbTaskProductionMaterialReservationRepositoryApi#findByQbeListOI40DBTaskProductionMaterialReservation");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **qbe** | [**OI40DBTaskProductionMaterialReservation**](OI40DBTaskProductionMaterialReservation.md)| qbe |

### Return type

[**List&lt;OI40DBTaskProductionMaterialReservation&gt;**](OI40DBTaskProductionMaterialReservation.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="findByQbePagedPageOI40DBTaskProductionMaterialReservation"></a>
# **findByQbePagedPageOI40DBTaskProductionMaterialReservation**
> PageOI40DBTaskProductionMaterialReservation findByQbePagedPageOI40DBTaskProductionMaterialReservation(qbe)

findByQbePaged

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbTaskProductionMaterialReservationRepositoryApi;


Oi40DbTaskProductionMaterialReservationRepositoryApi apiInstance = new Oi40DbTaskProductionMaterialReservationRepositoryApi();
QbeSupportOI40DBTaskProductionMaterialReservation qbe = new QbeSupportOI40DBTaskProductionMaterialReservation(); // QbeSupportOI40DBTaskProductionMaterialReservation | qbe
try {
    PageOI40DBTaskProductionMaterialReservation result = apiInstance.findByQbePagedPageOI40DBTaskProductionMaterialReservation(qbe);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbTaskProductionMaterialReservationRepositoryApi#findByQbePagedPageOI40DBTaskProductionMaterialReservation");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **qbe** | [**QbeSupportOI40DBTaskProductionMaterialReservation**](QbeSupportOI40DBTaskProductionMaterialReservation.md)| qbe |

### Return type

[**PageOI40DBTaskProductionMaterialReservation**](PageOI40DBTaskProductionMaterialReservation.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="updateListOI40DBTaskProductionMaterialReservation"></a>
# **updateListOI40DBTaskProductionMaterialReservation**
> List&lt;OI40DBTaskProductionMaterialReservation&gt; updateListOI40DBTaskProductionMaterialReservation(data)

update

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbTaskProductionMaterialReservationRepositoryApi;


Oi40DbTaskProductionMaterialReservationRepositoryApi apiInstance = new Oi40DbTaskProductionMaterialReservationRepositoryApi();
List<OI40DBTaskProductionMaterialReservation> data = Arrays.asList(new OI40DBTaskProductionMaterialReservation()); // List<OI40DBTaskProductionMaterialReservation> | data
try {
    List<OI40DBTaskProductionMaterialReservation> result = apiInstance.updateListOI40DBTaskProductionMaterialReservation(data);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbTaskProductionMaterialReservationRepositoryApi#updateListOI40DBTaskProductionMaterialReservation");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **data** | [**List&lt;OI40DBTaskProductionMaterialReservation&gt;**](OI40DBTaskProductionMaterialReservation.md)| data |

### Return type

[**List&lt;OI40DBTaskProductionMaterialReservation&gt;**](OI40DBTaskProductionMaterialReservation.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="updateSingleOI40DBTaskProductionMaterialReservation"></a>
# **updateSingleOI40DBTaskProductionMaterialReservation**
> OI40DBTaskProductionMaterialReservation updateSingleOI40DBTaskProductionMaterialReservation(data)

updateSingle

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbTaskProductionMaterialReservationRepositoryApi;


Oi40DbTaskProductionMaterialReservationRepositoryApi apiInstance = new Oi40DbTaskProductionMaterialReservationRepositoryApi();
OI40DBTaskProductionMaterialReservation data = new OI40DBTaskProductionMaterialReservation(); // OI40DBTaskProductionMaterialReservation | data
try {
    OI40DBTaskProductionMaterialReservation result = apiInstance.updateSingleOI40DBTaskProductionMaterialReservation(data);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbTaskProductionMaterialReservationRepositoryApi#updateSingleOI40DBTaskProductionMaterialReservation");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **data** | [**OI40DBTaskProductionMaterialReservation**](OI40DBTaskProductionMaterialReservation.md)| data |

### Return type

[**OI40DBTaskProductionMaterialReservation**](OI40DBTaskProductionMaterialReservation.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

