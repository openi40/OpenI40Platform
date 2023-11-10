# Oi40DbPartnerRepositoryApi

All URIs are relative to *https://localhost:8082/openi40-integration*

Method | HTTP request | Description
------------- | ------------- | -------------
[**deleteByCodeVoid12**](Oi40DbPartnerRepositoryApi.md#deleteByCodeVoid12) | **GET** /integration/OI40DBPartner/deleteByCode/{code} | deleteByCode
[**deleteByCodesVoid12**](Oi40DbPartnerRepositoryApi.md#deleteByCodesVoid12) | **POST** /integration/OI40DBPartner/deleteByCodes | deleteByCodes
[**doAutocompletePageOI40DBPartner**](Oi40DbPartnerRepositoryApi.md#doAutocompletePageOI40DBPartner) | **POST** /integration/OI40DBPartner/doAutocomplete | doAutocomplete
[**doLookupPageOI40DBPartner**](Oi40DbPartnerRepositoryApi.md#doLookupPageOI40DBPartner) | **POST** /integration/OI40DBPartner/doLookup | doLookup
[**findAllListOI40DBPartner**](Oi40DbPartnerRepositoryApi.md#findAllListOI40DBPartner) | **GET** /integration/OI40DBPartner | findAll
[**findAllPageOI40DBPartner**](Oi40DbPartnerRepositoryApi.md#findAllPageOI40DBPartner) | **POST** /integration/OI40DBPartner/findAllPaged | findAll
[**findByAfterIntegrationTsListOI40DBPartner**](Oi40DbPartnerRepositoryApi.md#findByAfterIntegrationTsListOI40DBPartner) | **GET** /integration/OI40DBPartner/integratedAfter/{ts} | findByAfterIntegrationTs
[**findByAfterModifiedTimestampListOI40DBPartner**](Oi40DbPartnerRepositoryApi.md#findByAfterModifiedTimestampListOI40DBPartner) | **GET** /integration/OI40DBPartner/modifiedAfter/{ts} | findByAfterModifiedTimestamp
[**findByCodeOI40DBPartner**](Oi40DbPartnerRepositoryApi.md#findByCodeOI40DBPartner) | **GET** /integration/OI40DBPartner/byCode/{code} | findByCode
[**findByCodesListOI40DBPartner**](Oi40DbPartnerRepositoryApi.md#findByCodesListOI40DBPartner) | **POST** /integration/OI40DBPartner/findByCodes | findByCodes
[**findByQbeListOI40DBPartner**](Oi40DbPartnerRepositoryApi.md#findByQbeListOI40DBPartner) | **POST** /integration/OI40DBPartner/findByQbe | findByQbe
[**findByQbePagedPageOI40DBPartner**](Oi40DbPartnerRepositoryApi.md#findByQbePagedPageOI40DBPartner) | **POST** /integration/OI40DBPartner/findByQbePaged | findByQbePaged
[**updateListOI40DBPartner**](Oi40DbPartnerRepositoryApi.md#updateListOI40DBPartner) | **POST** /integration/OI40DBPartner/update | update
[**updateSingleOI40DBPartner**](Oi40DbPartnerRepositoryApi.md#updateSingleOI40DBPartner) | **POST** /integration/OI40DBPartner/updateSingle | updateSingle


<a name="deleteByCodeVoid12"></a>
# **deleteByCodeVoid12**
> deleteByCodeVoid12(code)

deleteByCode

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbPartnerRepositoryApi;


Oi40DbPartnerRepositoryApi apiInstance = new Oi40DbPartnerRepositoryApi();
String code = "code_example"; // String | code
try {
    apiInstance.deleteByCodeVoid12(code);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbPartnerRepositoryApi#deleteByCodeVoid12");
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

<a name="deleteByCodesVoid12"></a>
# **deleteByCodesVoid12**
> deleteByCodesVoid12(codes)

deleteByCodes

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbPartnerRepositoryApi;


Oi40DbPartnerRepositoryApi apiInstance = new Oi40DbPartnerRepositoryApi();
List<String> codes = Arrays.asList(new List<String>()); // List<String> | codes
try {
    apiInstance.deleteByCodesVoid12(codes);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbPartnerRepositoryApi#deleteByCodesVoid12");
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

<a name="doAutocompletePageOI40DBPartner"></a>
# **doAutocompletePageOI40DBPartner**
> PageOI40DBPartner doAutocompletePageOI40DBPartner(autoCompleteData)

doAutocomplete

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbPartnerRepositoryApi;


Oi40DbPartnerRepositoryApi apiInstance = new Oi40DbPartnerRepositoryApi();
AutoCompleteData autoCompleteData = new AutoCompleteData(); // AutoCompleteData | autoCompleteData
try {
    PageOI40DBPartner result = apiInstance.doAutocompletePageOI40DBPartner(autoCompleteData);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbPartnerRepositoryApi#doAutocompletePageOI40DBPartner");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **autoCompleteData** | [**AutoCompleteData**](AutoCompleteData.md)| autoCompleteData |

### Return type

[**PageOI40DBPartner**](PageOI40DBPartner.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: */*

<a name="doLookupPageOI40DBPartner"></a>
# **doLookupPageOI40DBPartner**
> PageOI40DBPartner doLookupPageOI40DBPartner(lookup)

doLookup

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbPartnerRepositoryApi;


Oi40DbPartnerRepositoryApi apiInstance = new Oi40DbPartnerRepositoryApi();
LookupData lookup = new LookupData(); // LookupData | lookup
try {
    PageOI40DBPartner result = apiInstance.doLookupPageOI40DBPartner(lookup);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbPartnerRepositoryApi#doLookupPageOI40DBPartner");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **lookup** | [**LookupData**](LookupData.md)| lookup |

### Return type

[**PageOI40DBPartner**](PageOI40DBPartner.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: */*

<a name="findAllListOI40DBPartner"></a>
# **findAllListOI40DBPartner**
> List&lt;OI40DBPartner&gt; findAllListOI40DBPartner()

findAll

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbPartnerRepositoryApi;


Oi40DbPartnerRepositoryApi apiInstance = new Oi40DbPartnerRepositoryApi();
try {
    List<OI40DBPartner> result = apiInstance.findAllListOI40DBPartner();
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbPartnerRepositoryApi#findAllListOI40DBPartner");
    e.printStackTrace();
}
```

### Parameters
This endpoint does not need any parameter.

### Return type

[**List&lt;OI40DBPartner&gt;**](OI40DBPartner.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="findAllPageOI40DBPartner"></a>
# **findAllPageOI40DBPartner**
> PageOI40DBPartner findAllPageOI40DBPartner(p)

findAll

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbPartnerRepositoryApi;


Oi40DbPartnerRepositoryApi apiInstance = new Oi40DbPartnerRepositoryApi();
PageInfo p = new PageInfo(); // PageInfo | p
try {
    PageOI40DBPartner result = apiInstance.findAllPageOI40DBPartner(p);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbPartnerRepositoryApi#findAllPageOI40DBPartner");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **p** | [**PageInfo**](PageInfo.md)| p |

### Return type

[**PageOI40DBPartner**](PageOI40DBPartner.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="findByAfterIntegrationTsListOI40DBPartner"></a>
# **findByAfterIntegrationTsListOI40DBPartner**
> List&lt;OI40DBPartner&gt; findByAfterIntegrationTsListOI40DBPartner(ts)

findByAfterIntegrationTs

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbPartnerRepositoryApi;


Oi40DbPartnerRepositoryApi apiInstance = new Oi40DbPartnerRepositoryApi();
String ts = "ts_example"; // String | ts
try {
    List<OI40DBPartner> result = apiInstance.findByAfterIntegrationTsListOI40DBPartner(ts);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbPartnerRepositoryApi#findByAfterIntegrationTsListOI40DBPartner");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **ts** | **String**| ts |

### Return type

[**List&lt;OI40DBPartner&gt;**](OI40DBPartner.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*

<a name="findByAfterModifiedTimestampListOI40DBPartner"></a>
# **findByAfterModifiedTimestampListOI40DBPartner**
> List&lt;OI40DBPartner&gt; findByAfterModifiedTimestampListOI40DBPartner(ts)

findByAfterModifiedTimestamp

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbPartnerRepositoryApi;


Oi40DbPartnerRepositoryApi apiInstance = new Oi40DbPartnerRepositoryApi();
String ts = "ts_example"; // String | ts
try {
    List<OI40DBPartner> result = apiInstance.findByAfterModifiedTimestampListOI40DBPartner(ts);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbPartnerRepositoryApi#findByAfterModifiedTimestampListOI40DBPartner");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **ts** | **String**| ts |

### Return type

[**List&lt;OI40DBPartner&gt;**](OI40DBPartner.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*

<a name="findByCodeOI40DBPartner"></a>
# **findByCodeOI40DBPartner**
> OI40DBPartner findByCodeOI40DBPartner(code)

findByCode

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbPartnerRepositoryApi;


Oi40DbPartnerRepositoryApi apiInstance = new Oi40DbPartnerRepositoryApi();
String code = "code_example"; // String | code
try {
    OI40DBPartner result = apiInstance.findByCodeOI40DBPartner(code);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbPartnerRepositoryApi#findByCodeOI40DBPartner");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **code** | **String**| code |

### Return type

[**OI40DBPartner**](OI40DBPartner.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="findByCodesListOI40DBPartner"></a>
# **findByCodesListOI40DBPartner**
> List&lt;OI40DBPartner&gt; findByCodesListOI40DBPartner(codes)

findByCodes

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbPartnerRepositoryApi;


Oi40DbPartnerRepositoryApi apiInstance = new Oi40DbPartnerRepositoryApi();
List<String> codes = Arrays.asList(new List<String>()); // List<String> | codes
try {
    List<OI40DBPartner> result = apiInstance.findByCodesListOI40DBPartner(codes);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbPartnerRepositoryApi#findByCodesListOI40DBPartner");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **codes** | **List&lt;String&gt;**| codes |

### Return type

[**List&lt;OI40DBPartner&gt;**](OI40DBPartner.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="findByQbeListOI40DBPartner"></a>
# **findByQbeListOI40DBPartner**
> List&lt;OI40DBPartner&gt; findByQbeListOI40DBPartner(qbe)

findByQbe

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbPartnerRepositoryApi;


Oi40DbPartnerRepositoryApi apiInstance = new Oi40DbPartnerRepositoryApi();
OI40DBPartner qbe = new OI40DBPartner(); // OI40DBPartner | qbe
try {
    List<OI40DBPartner> result = apiInstance.findByQbeListOI40DBPartner(qbe);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbPartnerRepositoryApi#findByQbeListOI40DBPartner");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **qbe** | [**OI40DBPartner**](OI40DBPartner.md)| qbe |

### Return type

[**List&lt;OI40DBPartner&gt;**](OI40DBPartner.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="findByQbePagedPageOI40DBPartner"></a>
# **findByQbePagedPageOI40DBPartner**
> PageOI40DBPartner findByQbePagedPageOI40DBPartner(qbe)

findByQbePaged

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbPartnerRepositoryApi;


Oi40DbPartnerRepositoryApi apiInstance = new Oi40DbPartnerRepositoryApi();
QbeSupportOI40DBPartner qbe = new QbeSupportOI40DBPartner(); // QbeSupportOI40DBPartner | qbe
try {
    PageOI40DBPartner result = apiInstance.findByQbePagedPageOI40DBPartner(qbe);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbPartnerRepositoryApi#findByQbePagedPageOI40DBPartner");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **qbe** | [**QbeSupportOI40DBPartner**](QbeSupportOI40DBPartner.md)| qbe |

### Return type

[**PageOI40DBPartner**](PageOI40DBPartner.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="updateListOI40DBPartner"></a>
# **updateListOI40DBPartner**
> List&lt;OI40DBPartner&gt; updateListOI40DBPartner(data)

update

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbPartnerRepositoryApi;


Oi40DbPartnerRepositoryApi apiInstance = new Oi40DbPartnerRepositoryApi();
List<OI40DBPartner> data = Arrays.asList(new OI40DBPartner()); // List<OI40DBPartner> | data
try {
    List<OI40DBPartner> result = apiInstance.updateListOI40DBPartner(data);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbPartnerRepositoryApi#updateListOI40DBPartner");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **data** | [**List&lt;OI40DBPartner&gt;**](OI40DBPartner.md)| data |

### Return type

[**List&lt;OI40DBPartner&gt;**](OI40DBPartner.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="updateSingleOI40DBPartner"></a>
# **updateSingleOI40DBPartner**
> OI40DBPartner updateSingleOI40DBPartner(data)

updateSingle

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbPartnerRepositoryApi;


Oi40DbPartnerRepositoryApi apiInstance = new Oi40DbPartnerRepositoryApi();
OI40DBPartner data = new OI40DBPartner(); // OI40DBPartner | data
try {
    OI40DBPartner result = apiInstance.updateSingleOI40DBPartner(data);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbPartnerRepositoryApi#updateSingleOI40DBPartner");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **data** | [**OI40DBPartner**](OI40DBPartner.md)| data |

### Return type

[**OI40DBPartner**](OI40DBPartner.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

