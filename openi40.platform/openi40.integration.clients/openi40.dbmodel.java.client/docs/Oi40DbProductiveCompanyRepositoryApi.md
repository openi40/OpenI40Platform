# Oi40DbProductiveCompanyRepositoryApi

All URIs are relative to *https://localhost:8082/openi40-integration*

Method | HTTP request | Description
------------- | ------------- | -------------
[**deleteByCodeVoid18**](Oi40DbProductiveCompanyRepositoryApi.md#deleteByCodeVoid18) | **GET** /integration/OI40DBProductiveCompany/deleteByCode/{code} | deleteByCode
[**deleteByCodesVoid18**](Oi40DbProductiveCompanyRepositoryApi.md#deleteByCodesVoid18) | **POST** /integration/OI40DBProductiveCompany/deleteByCodes | deleteByCodes
[**doAutocompletePageOI40DBProductiveCompany**](Oi40DbProductiveCompanyRepositoryApi.md#doAutocompletePageOI40DBProductiveCompany) | **POST** /integration/OI40DBProductiveCompany/doAutocomplete | doAutocomplete
[**doLookupPageOI40DBProductiveCompany**](Oi40DbProductiveCompanyRepositoryApi.md#doLookupPageOI40DBProductiveCompany) | **POST** /integration/OI40DBProductiveCompany/doLookup | doLookup
[**findAllListOI40DBProductiveCompany**](Oi40DbProductiveCompanyRepositoryApi.md#findAllListOI40DBProductiveCompany) | **GET** /integration/OI40DBProductiveCompany | findAll
[**findAllPageOI40DBProductiveCompany**](Oi40DbProductiveCompanyRepositoryApi.md#findAllPageOI40DBProductiveCompany) | **POST** /integration/OI40DBProductiveCompany/findAllPaged | findAll
[**findByAfterIntegrationTsListOI40DBProductiveCompany**](Oi40DbProductiveCompanyRepositoryApi.md#findByAfterIntegrationTsListOI40DBProductiveCompany) | **GET** /integration/OI40DBProductiveCompany/integratedAfter/{ts} | findByAfterIntegrationTs
[**findByAfterModifiedTimestampListOI40DBProductiveCompany**](Oi40DbProductiveCompanyRepositoryApi.md#findByAfterModifiedTimestampListOI40DBProductiveCompany) | **GET** /integration/OI40DBProductiveCompany/modifiedAfter/{ts} | findByAfterModifiedTimestamp
[**findByCodeOI40DBProductiveCompany**](Oi40DbProductiveCompanyRepositoryApi.md#findByCodeOI40DBProductiveCompany) | **GET** /integration/OI40DBProductiveCompany/byCode/{code} | findByCode
[**findByCodesListOI40DBProductiveCompany**](Oi40DbProductiveCompanyRepositoryApi.md#findByCodesListOI40DBProductiveCompany) | **POST** /integration/OI40DBProductiveCompany/findByCodes | findByCodes
[**findByQbeListOI40DBProductiveCompany**](Oi40DbProductiveCompanyRepositoryApi.md#findByQbeListOI40DBProductiveCompany) | **POST** /integration/OI40DBProductiveCompany/findByQbe | findByQbe
[**findByQbePagedPageOI40DBProductiveCompany**](Oi40DbProductiveCompanyRepositoryApi.md#findByQbePagedPageOI40DBProductiveCompany) | **POST** /integration/OI40DBProductiveCompany/findByQbePaged | findByQbePaged
[**updateListOI40DBProductiveCompany**](Oi40DbProductiveCompanyRepositoryApi.md#updateListOI40DBProductiveCompany) | **POST** /integration/OI40DBProductiveCompany/update | update
[**updateSingleOI40DBProductiveCompany**](Oi40DbProductiveCompanyRepositoryApi.md#updateSingleOI40DBProductiveCompany) | **POST** /integration/OI40DBProductiveCompany/updateSingle | updateSingle


<a name="deleteByCodeVoid18"></a>
# **deleteByCodeVoid18**
> deleteByCodeVoid18(code)

deleteByCode

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbProductiveCompanyRepositoryApi;


Oi40DbProductiveCompanyRepositoryApi apiInstance = new Oi40DbProductiveCompanyRepositoryApi();
String code = "code_example"; // String | code
try {
    apiInstance.deleteByCodeVoid18(code);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbProductiveCompanyRepositoryApi#deleteByCodeVoid18");
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

<a name="deleteByCodesVoid18"></a>
# **deleteByCodesVoid18**
> deleteByCodesVoid18(codes)

deleteByCodes

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbProductiveCompanyRepositoryApi;


Oi40DbProductiveCompanyRepositoryApi apiInstance = new Oi40DbProductiveCompanyRepositoryApi();
List<String> codes = Arrays.asList(new List<String>()); // List<String> | codes
try {
    apiInstance.deleteByCodesVoid18(codes);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbProductiveCompanyRepositoryApi#deleteByCodesVoid18");
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

<a name="doAutocompletePageOI40DBProductiveCompany"></a>
# **doAutocompletePageOI40DBProductiveCompany**
> PageOI40DBProductiveCompany doAutocompletePageOI40DBProductiveCompany(autoCompleteData)

doAutocomplete

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbProductiveCompanyRepositoryApi;


Oi40DbProductiveCompanyRepositoryApi apiInstance = new Oi40DbProductiveCompanyRepositoryApi();
AutoCompleteData autoCompleteData = new AutoCompleteData(); // AutoCompleteData | autoCompleteData
try {
    PageOI40DBProductiveCompany result = apiInstance.doAutocompletePageOI40DBProductiveCompany(autoCompleteData);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbProductiveCompanyRepositoryApi#doAutocompletePageOI40DBProductiveCompany");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **autoCompleteData** | [**AutoCompleteData**](AutoCompleteData.md)| autoCompleteData |

### Return type

[**PageOI40DBProductiveCompany**](PageOI40DBProductiveCompany.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: */*

<a name="doLookupPageOI40DBProductiveCompany"></a>
# **doLookupPageOI40DBProductiveCompany**
> PageOI40DBProductiveCompany doLookupPageOI40DBProductiveCompany(lookup)

doLookup

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbProductiveCompanyRepositoryApi;


Oi40DbProductiveCompanyRepositoryApi apiInstance = new Oi40DbProductiveCompanyRepositoryApi();
LookupData lookup = new LookupData(); // LookupData | lookup
try {
    PageOI40DBProductiveCompany result = apiInstance.doLookupPageOI40DBProductiveCompany(lookup);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbProductiveCompanyRepositoryApi#doLookupPageOI40DBProductiveCompany");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **lookup** | [**LookupData**](LookupData.md)| lookup |

### Return type

[**PageOI40DBProductiveCompany**](PageOI40DBProductiveCompany.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: */*

<a name="findAllListOI40DBProductiveCompany"></a>
# **findAllListOI40DBProductiveCompany**
> List&lt;OI40DBProductiveCompany&gt; findAllListOI40DBProductiveCompany()

findAll

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbProductiveCompanyRepositoryApi;


Oi40DbProductiveCompanyRepositoryApi apiInstance = new Oi40DbProductiveCompanyRepositoryApi();
try {
    List<OI40DBProductiveCompany> result = apiInstance.findAllListOI40DBProductiveCompany();
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbProductiveCompanyRepositoryApi#findAllListOI40DBProductiveCompany");
    e.printStackTrace();
}
```

### Parameters
This endpoint does not need any parameter.

### Return type

[**List&lt;OI40DBProductiveCompany&gt;**](OI40DBProductiveCompany.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="findAllPageOI40DBProductiveCompany"></a>
# **findAllPageOI40DBProductiveCompany**
> PageOI40DBProductiveCompany findAllPageOI40DBProductiveCompany(p)

findAll

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbProductiveCompanyRepositoryApi;


Oi40DbProductiveCompanyRepositoryApi apiInstance = new Oi40DbProductiveCompanyRepositoryApi();
PageInfo p = new PageInfo(); // PageInfo | p
try {
    PageOI40DBProductiveCompany result = apiInstance.findAllPageOI40DBProductiveCompany(p);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbProductiveCompanyRepositoryApi#findAllPageOI40DBProductiveCompany");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **p** | [**PageInfo**](PageInfo.md)| p |

### Return type

[**PageOI40DBProductiveCompany**](PageOI40DBProductiveCompany.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="findByAfterIntegrationTsListOI40DBProductiveCompany"></a>
# **findByAfterIntegrationTsListOI40DBProductiveCompany**
> List&lt;OI40DBProductiveCompany&gt; findByAfterIntegrationTsListOI40DBProductiveCompany(ts)

findByAfterIntegrationTs

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbProductiveCompanyRepositoryApi;


Oi40DbProductiveCompanyRepositoryApi apiInstance = new Oi40DbProductiveCompanyRepositoryApi();
String ts = "ts_example"; // String | ts
try {
    List<OI40DBProductiveCompany> result = apiInstance.findByAfterIntegrationTsListOI40DBProductiveCompany(ts);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbProductiveCompanyRepositoryApi#findByAfterIntegrationTsListOI40DBProductiveCompany");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **ts** | **String**| ts |

### Return type

[**List&lt;OI40DBProductiveCompany&gt;**](OI40DBProductiveCompany.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*

<a name="findByAfterModifiedTimestampListOI40DBProductiveCompany"></a>
# **findByAfterModifiedTimestampListOI40DBProductiveCompany**
> List&lt;OI40DBProductiveCompany&gt; findByAfterModifiedTimestampListOI40DBProductiveCompany(ts)

findByAfterModifiedTimestamp

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbProductiveCompanyRepositoryApi;


Oi40DbProductiveCompanyRepositoryApi apiInstance = new Oi40DbProductiveCompanyRepositoryApi();
String ts = "ts_example"; // String | ts
try {
    List<OI40DBProductiveCompany> result = apiInstance.findByAfterModifiedTimestampListOI40DBProductiveCompany(ts);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbProductiveCompanyRepositoryApi#findByAfterModifiedTimestampListOI40DBProductiveCompany");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **ts** | **String**| ts |

### Return type

[**List&lt;OI40DBProductiveCompany&gt;**](OI40DBProductiveCompany.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*

<a name="findByCodeOI40DBProductiveCompany"></a>
# **findByCodeOI40DBProductiveCompany**
> OI40DBProductiveCompany findByCodeOI40DBProductiveCompany(code)

findByCode

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbProductiveCompanyRepositoryApi;


Oi40DbProductiveCompanyRepositoryApi apiInstance = new Oi40DbProductiveCompanyRepositoryApi();
String code = "code_example"; // String | code
try {
    OI40DBProductiveCompany result = apiInstance.findByCodeOI40DBProductiveCompany(code);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbProductiveCompanyRepositoryApi#findByCodeOI40DBProductiveCompany");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **code** | **String**| code |

### Return type

[**OI40DBProductiveCompany**](OI40DBProductiveCompany.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="findByCodesListOI40DBProductiveCompany"></a>
# **findByCodesListOI40DBProductiveCompany**
> List&lt;OI40DBProductiveCompany&gt; findByCodesListOI40DBProductiveCompany(codes)

findByCodes

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbProductiveCompanyRepositoryApi;


Oi40DbProductiveCompanyRepositoryApi apiInstance = new Oi40DbProductiveCompanyRepositoryApi();
List<String> codes = Arrays.asList(new List<String>()); // List<String> | codes
try {
    List<OI40DBProductiveCompany> result = apiInstance.findByCodesListOI40DBProductiveCompany(codes);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbProductiveCompanyRepositoryApi#findByCodesListOI40DBProductiveCompany");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **codes** | **List&lt;String&gt;**| codes |

### Return type

[**List&lt;OI40DBProductiveCompany&gt;**](OI40DBProductiveCompany.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="findByQbeListOI40DBProductiveCompany"></a>
# **findByQbeListOI40DBProductiveCompany**
> List&lt;OI40DBProductiveCompany&gt; findByQbeListOI40DBProductiveCompany(qbe)

findByQbe

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbProductiveCompanyRepositoryApi;


Oi40DbProductiveCompanyRepositoryApi apiInstance = new Oi40DbProductiveCompanyRepositoryApi();
OI40DBProductiveCompany qbe = new OI40DBProductiveCompany(); // OI40DBProductiveCompany | qbe
try {
    List<OI40DBProductiveCompany> result = apiInstance.findByQbeListOI40DBProductiveCompany(qbe);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbProductiveCompanyRepositoryApi#findByQbeListOI40DBProductiveCompany");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **qbe** | [**OI40DBProductiveCompany**](OI40DBProductiveCompany.md)| qbe |

### Return type

[**List&lt;OI40DBProductiveCompany&gt;**](OI40DBProductiveCompany.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="findByQbePagedPageOI40DBProductiveCompany"></a>
# **findByQbePagedPageOI40DBProductiveCompany**
> PageOI40DBProductiveCompany findByQbePagedPageOI40DBProductiveCompany(qbe)

findByQbePaged

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbProductiveCompanyRepositoryApi;


Oi40DbProductiveCompanyRepositoryApi apiInstance = new Oi40DbProductiveCompanyRepositoryApi();
QbeSupportOI40DBProductiveCompany qbe = new QbeSupportOI40DBProductiveCompany(); // QbeSupportOI40DBProductiveCompany | qbe
try {
    PageOI40DBProductiveCompany result = apiInstance.findByQbePagedPageOI40DBProductiveCompany(qbe);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbProductiveCompanyRepositoryApi#findByQbePagedPageOI40DBProductiveCompany");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **qbe** | [**QbeSupportOI40DBProductiveCompany**](QbeSupportOI40DBProductiveCompany.md)| qbe |

### Return type

[**PageOI40DBProductiveCompany**](PageOI40DBProductiveCompany.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="updateListOI40DBProductiveCompany"></a>
# **updateListOI40DBProductiveCompany**
> List&lt;OI40DBProductiveCompany&gt; updateListOI40DBProductiveCompany(data)

update

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbProductiveCompanyRepositoryApi;


Oi40DbProductiveCompanyRepositoryApi apiInstance = new Oi40DbProductiveCompanyRepositoryApi();
List<OI40DBProductiveCompany> data = Arrays.asList(new OI40DBProductiveCompany()); // List<OI40DBProductiveCompany> | data
try {
    List<OI40DBProductiveCompany> result = apiInstance.updateListOI40DBProductiveCompany(data);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbProductiveCompanyRepositoryApi#updateListOI40DBProductiveCompany");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **data** | [**List&lt;OI40DBProductiveCompany&gt;**](OI40DBProductiveCompany.md)| data |

### Return type

[**List&lt;OI40DBProductiveCompany&gt;**](OI40DBProductiveCompany.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="updateSingleOI40DBProductiveCompany"></a>
# **updateSingleOI40DBProductiveCompany**
> OI40DBProductiveCompany updateSingleOI40DBProductiveCompany(data)

updateSingle

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbProductiveCompanyRepositoryApi;


Oi40DbProductiveCompanyRepositoryApi apiInstance = new Oi40DbProductiveCompanyRepositoryApi();
OI40DBProductiveCompany data = new OI40DBProductiveCompany(); // OI40DBProductiveCompany | data
try {
    OI40DBProductiveCompany result = apiInstance.updateSingleOI40DBProductiveCompany(data);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbProductiveCompanyRepositoryApi#updateSingleOI40DBProductiveCompany");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **data** | [**OI40DBProductiveCompany**](OI40DBProductiveCompany.md)| data |

### Return type

[**OI40DBProductiveCompany**](OI40DBProductiveCompany.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

