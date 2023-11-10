# Oi40DbSecondaryResourceUseSpecificationRepositoryApi

All URIs are relative to *https://localhost:8083/openi40-backoffice*

Method | HTTP request | Description
------------- | ------------- | -------------
[**deleteByCodeVoid26**](Oi40DbSecondaryResourceUseSpecificationRepositoryApi.md#deleteByCodeVoid26) | **GET** /integration/OI40DBSecondaryResourceUseSpecification/deleteByCode/{code} | deleteByCode
[**deleteByCodesVoid26**](Oi40DbSecondaryResourceUseSpecificationRepositoryApi.md#deleteByCodesVoid26) | **POST** /integration/OI40DBSecondaryResourceUseSpecification/deleteByCodes | deleteByCodes
[**doAutocompletePageOI40DBSecondaryResourceUseSpecification**](Oi40DbSecondaryResourceUseSpecificationRepositoryApi.md#doAutocompletePageOI40DBSecondaryResourceUseSpecification) | **POST** /integration/OI40DBSecondaryResourceUseSpecification/doAutocomplete | doAutocomplete
[**doLookupPageOI40DBSecondaryResourceUseSpecification**](Oi40DbSecondaryResourceUseSpecificationRepositoryApi.md#doLookupPageOI40DBSecondaryResourceUseSpecification) | **POST** /integration/OI40DBSecondaryResourceUseSpecification/doLookup | doLookup
[**findAllListOI40DBSecondaryResourceUseSpecification**](Oi40DbSecondaryResourceUseSpecificationRepositoryApi.md#findAllListOI40DBSecondaryResourceUseSpecification) | **GET** /integration/OI40DBSecondaryResourceUseSpecification | findAll
[**findAllPageOI40DBSecondaryResourceUseSpecification**](Oi40DbSecondaryResourceUseSpecificationRepositoryApi.md#findAllPageOI40DBSecondaryResourceUseSpecification) | **POST** /integration/OI40DBSecondaryResourceUseSpecification/findAllPaged | findAll
[**findByAfterIntegrationTsListOI40DBSecondaryResourceUseSpecification**](Oi40DbSecondaryResourceUseSpecificationRepositoryApi.md#findByAfterIntegrationTsListOI40DBSecondaryResourceUseSpecification) | **GET** /integration/OI40DBSecondaryResourceUseSpecification/integratedAfter/{ts} | findByAfterIntegrationTs
[**findByAfterModifiedTimestampListOI40DBSecondaryResourceUseSpecification**](Oi40DbSecondaryResourceUseSpecificationRepositoryApi.md#findByAfterModifiedTimestampListOI40DBSecondaryResourceUseSpecification) | **GET** /integration/OI40DBSecondaryResourceUseSpecification/modifiedAfter/{ts} | findByAfterModifiedTimestamp
[**findByCodeOI40DBSecondaryResourceUseSpecification**](Oi40DbSecondaryResourceUseSpecificationRepositoryApi.md#findByCodeOI40DBSecondaryResourceUseSpecification) | **GET** /integration/OI40DBSecondaryResourceUseSpecification/byCode/{code} | findByCode
[**findByCodesListOI40DBSecondaryResourceUseSpecification**](Oi40DbSecondaryResourceUseSpecificationRepositoryApi.md#findByCodesListOI40DBSecondaryResourceUseSpecification) | **POST** /integration/OI40DBSecondaryResourceUseSpecification/findByCodes | findByCodes
[**findByQbeListOI40DBSecondaryResourceUseSpecification**](Oi40DbSecondaryResourceUseSpecificationRepositoryApi.md#findByQbeListOI40DBSecondaryResourceUseSpecification) | **POST** /integration/OI40DBSecondaryResourceUseSpecification/findByQbe | findByQbe
[**findByQbePagedPageOI40DBSecondaryResourceUseSpecification**](Oi40DbSecondaryResourceUseSpecificationRepositoryApi.md#findByQbePagedPageOI40DBSecondaryResourceUseSpecification) | **POST** /integration/OI40DBSecondaryResourceUseSpecification/findByQbePaged | findByQbePaged
[**updateListOI40DBSecondaryResourceUseSpecification**](Oi40DbSecondaryResourceUseSpecificationRepositoryApi.md#updateListOI40DBSecondaryResourceUseSpecification) | **POST** /integration/OI40DBSecondaryResourceUseSpecification/update | update
[**updateSingleOI40DBSecondaryResourceUseSpecification**](Oi40DbSecondaryResourceUseSpecificationRepositoryApi.md#updateSingleOI40DBSecondaryResourceUseSpecification) | **POST** /integration/OI40DBSecondaryResourceUseSpecification/updateSingle | updateSingle


<a name="deleteByCodeVoid26"></a>
# **deleteByCodeVoid26**
> deleteByCodeVoid26(code)

deleteByCode

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbSecondaryResourceUseSpecificationRepositoryApi;


Oi40DbSecondaryResourceUseSpecificationRepositoryApi apiInstance = new Oi40DbSecondaryResourceUseSpecificationRepositoryApi();
String code = "code_example"; // String | code
try {
    apiInstance.deleteByCodeVoid26(code);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbSecondaryResourceUseSpecificationRepositoryApi#deleteByCodeVoid26");
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

<a name="deleteByCodesVoid26"></a>
# **deleteByCodesVoid26**
> deleteByCodesVoid26(codes)

deleteByCodes

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbSecondaryResourceUseSpecificationRepositoryApi;


Oi40DbSecondaryResourceUseSpecificationRepositoryApi apiInstance = new Oi40DbSecondaryResourceUseSpecificationRepositoryApi();
List<String> codes = Arrays.asList(new List<String>()); // List<String> | codes
try {
    apiInstance.deleteByCodesVoid26(codes);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbSecondaryResourceUseSpecificationRepositoryApi#deleteByCodesVoid26");
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

<a name="doAutocompletePageOI40DBSecondaryResourceUseSpecification"></a>
# **doAutocompletePageOI40DBSecondaryResourceUseSpecification**
> PageOI40DBSecondaryResourceUseSpecification doAutocompletePageOI40DBSecondaryResourceUseSpecification(autoCompleteData)

doAutocomplete

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbSecondaryResourceUseSpecificationRepositoryApi;


Oi40DbSecondaryResourceUseSpecificationRepositoryApi apiInstance = new Oi40DbSecondaryResourceUseSpecificationRepositoryApi();
AutoCompleteData autoCompleteData = new AutoCompleteData(); // AutoCompleteData | autoCompleteData
try {
    PageOI40DBSecondaryResourceUseSpecification result = apiInstance.doAutocompletePageOI40DBSecondaryResourceUseSpecification(autoCompleteData);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbSecondaryResourceUseSpecificationRepositoryApi#doAutocompletePageOI40DBSecondaryResourceUseSpecification");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **autoCompleteData** | [**AutoCompleteData**](AutoCompleteData.md)| autoCompleteData |

### Return type

[**PageOI40DBSecondaryResourceUseSpecification**](PageOI40DBSecondaryResourceUseSpecification.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: */*

<a name="doLookupPageOI40DBSecondaryResourceUseSpecification"></a>
# **doLookupPageOI40DBSecondaryResourceUseSpecification**
> PageOI40DBSecondaryResourceUseSpecification doLookupPageOI40DBSecondaryResourceUseSpecification(lookup)

doLookup

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbSecondaryResourceUseSpecificationRepositoryApi;


Oi40DbSecondaryResourceUseSpecificationRepositoryApi apiInstance = new Oi40DbSecondaryResourceUseSpecificationRepositoryApi();
LookupData lookup = new LookupData(); // LookupData | lookup
try {
    PageOI40DBSecondaryResourceUseSpecification result = apiInstance.doLookupPageOI40DBSecondaryResourceUseSpecification(lookup);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbSecondaryResourceUseSpecificationRepositoryApi#doLookupPageOI40DBSecondaryResourceUseSpecification");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **lookup** | [**LookupData**](LookupData.md)| lookup |

### Return type

[**PageOI40DBSecondaryResourceUseSpecification**](PageOI40DBSecondaryResourceUseSpecification.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: */*

<a name="findAllListOI40DBSecondaryResourceUseSpecification"></a>
# **findAllListOI40DBSecondaryResourceUseSpecification**
> List&lt;OI40DBSecondaryResourceUseSpecification&gt; findAllListOI40DBSecondaryResourceUseSpecification()

findAll

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbSecondaryResourceUseSpecificationRepositoryApi;


Oi40DbSecondaryResourceUseSpecificationRepositoryApi apiInstance = new Oi40DbSecondaryResourceUseSpecificationRepositoryApi();
try {
    List<OI40DBSecondaryResourceUseSpecification> result = apiInstance.findAllListOI40DBSecondaryResourceUseSpecification();
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbSecondaryResourceUseSpecificationRepositoryApi#findAllListOI40DBSecondaryResourceUseSpecification");
    e.printStackTrace();
}
```

### Parameters
This endpoint does not need any parameter.

### Return type

[**List&lt;OI40DBSecondaryResourceUseSpecification&gt;**](OI40DBSecondaryResourceUseSpecification.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="findAllPageOI40DBSecondaryResourceUseSpecification"></a>
# **findAllPageOI40DBSecondaryResourceUseSpecification**
> PageOI40DBSecondaryResourceUseSpecification findAllPageOI40DBSecondaryResourceUseSpecification(p)

findAll

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbSecondaryResourceUseSpecificationRepositoryApi;


Oi40DbSecondaryResourceUseSpecificationRepositoryApi apiInstance = new Oi40DbSecondaryResourceUseSpecificationRepositoryApi();
PageInfo p = new PageInfo(); // PageInfo | p
try {
    PageOI40DBSecondaryResourceUseSpecification result = apiInstance.findAllPageOI40DBSecondaryResourceUseSpecification(p);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbSecondaryResourceUseSpecificationRepositoryApi#findAllPageOI40DBSecondaryResourceUseSpecification");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **p** | [**PageInfo**](PageInfo.md)| p |

### Return type

[**PageOI40DBSecondaryResourceUseSpecification**](PageOI40DBSecondaryResourceUseSpecification.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="findByAfterIntegrationTsListOI40DBSecondaryResourceUseSpecification"></a>
# **findByAfterIntegrationTsListOI40DBSecondaryResourceUseSpecification**
> List&lt;OI40DBSecondaryResourceUseSpecification&gt; findByAfterIntegrationTsListOI40DBSecondaryResourceUseSpecification(ts)

findByAfterIntegrationTs

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbSecondaryResourceUseSpecificationRepositoryApi;


Oi40DbSecondaryResourceUseSpecificationRepositoryApi apiInstance = new Oi40DbSecondaryResourceUseSpecificationRepositoryApi();
String ts = "ts_example"; // String | ts
try {
    List<OI40DBSecondaryResourceUseSpecification> result = apiInstance.findByAfterIntegrationTsListOI40DBSecondaryResourceUseSpecification(ts);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbSecondaryResourceUseSpecificationRepositoryApi#findByAfterIntegrationTsListOI40DBSecondaryResourceUseSpecification");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **ts** | **String**| ts |

### Return type

[**List&lt;OI40DBSecondaryResourceUseSpecification&gt;**](OI40DBSecondaryResourceUseSpecification.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*

<a name="findByAfterModifiedTimestampListOI40DBSecondaryResourceUseSpecification"></a>
# **findByAfterModifiedTimestampListOI40DBSecondaryResourceUseSpecification**
> List&lt;OI40DBSecondaryResourceUseSpecification&gt; findByAfterModifiedTimestampListOI40DBSecondaryResourceUseSpecification(ts)

findByAfterModifiedTimestamp

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbSecondaryResourceUseSpecificationRepositoryApi;


Oi40DbSecondaryResourceUseSpecificationRepositoryApi apiInstance = new Oi40DbSecondaryResourceUseSpecificationRepositoryApi();
String ts = "ts_example"; // String | ts
try {
    List<OI40DBSecondaryResourceUseSpecification> result = apiInstance.findByAfterModifiedTimestampListOI40DBSecondaryResourceUseSpecification(ts);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbSecondaryResourceUseSpecificationRepositoryApi#findByAfterModifiedTimestampListOI40DBSecondaryResourceUseSpecification");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **ts** | **String**| ts |

### Return type

[**List&lt;OI40DBSecondaryResourceUseSpecification&gt;**](OI40DBSecondaryResourceUseSpecification.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*

<a name="findByCodeOI40DBSecondaryResourceUseSpecification"></a>
# **findByCodeOI40DBSecondaryResourceUseSpecification**
> OI40DBSecondaryResourceUseSpecification findByCodeOI40DBSecondaryResourceUseSpecification(code)

findByCode

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbSecondaryResourceUseSpecificationRepositoryApi;


Oi40DbSecondaryResourceUseSpecificationRepositoryApi apiInstance = new Oi40DbSecondaryResourceUseSpecificationRepositoryApi();
String code = "code_example"; // String | code
try {
    OI40DBSecondaryResourceUseSpecification result = apiInstance.findByCodeOI40DBSecondaryResourceUseSpecification(code);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbSecondaryResourceUseSpecificationRepositoryApi#findByCodeOI40DBSecondaryResourceUseSpecification");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **code** | **String**| code |

### Return type

[**OI40DBSecondaryResourceUseSpecification**](OI40DBSecondaryResourceUseSpecification.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="findByCodesListOI40DBSecondaryResourceUseSpecification"></a>
# **findByCodesListOI40DBSecondaryResourceUseSpecification**
> List&lt;OI40DBSecondaryResourceUseSpecification&gt; findByCodesListOI40DBSecondaryResourceUseSpecification(codes)

findByCodes

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbSecondaryResourceUseSpecificationRepositoryApi;


Oi40DbSecondaryResourceUseSpecificationRepositoryApi apiInstance = new Oi40DbSecondaryResourceUseSpecificationRepositoryApi();
List<String> codes = Arrays.asList(new List<String>()); // List<String> | codes
try {
    List<OI40DBSecondaryResourceUseSpecification> result = apiInstance.findByCodesListOI40DBSecondaryResourceUseSpecification(codes);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbSecondaryResourceUseSpecificationRepositoryApi#findByCodesListOI40DBSecondaryResourceUseSpecification");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **codes** | **List&lt;String&gt;**| codes |

### Return type

[**List&lt;OI40DBSecondaryResourceUseSpecification&gt;**](OI40DBSecondaryResourceUseSpecification.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="findByQbeListOI40DBSecondaryResourceUseSpecification"></a>
# **findByQbeListOI40DBSecondaryResourceUseSpecification**
> List&lt;OI40DBSecondaryResourceUseSpecification&gt; findByQbeListOI40DBSecondaryResourceUseSpecification(qbe)

findByQbe

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbSecondaryResourceUseSpecificationRepositoryApi;


Oi40DbSecondaryResourceUseSpecificationRepositoryApi apiInstance = new Oi40DbSecondaryResourceUseSpecificationRepositoryApi();
OI40DBSecondaryResourceUseSpecification qbe = new OI40DBSecondaryResourceUseSpecification(); // OI40DBSecondaryResourceUseSpecification | qbe
try {
    List<OI40DBSecondaryResourceUseSpecification> result = apiInstance.findByQbeListOI40DBSecondaryResourceUseSpecification(qbe);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbSecondaryResourceUseSpecificationRepositoryApi#findByQbeListOI40DBSecondaryResourceUseSpecification");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **qbe** | [**OI40DBSecondaryResourceUseSpecification**](OI40DBSecondaryResourceUseSpecification.md)| qbe |

### Return type

[**List&lt;OI40DBSecondaryResourceUseSpecification&gt;**](OI40DBSecondaryResourceUseSpecification.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="findByQbePagedPageOI40DBSecondaryResourceUseSpecification"></a>
# **findByQbePagedPageOI40DBSecondaryResourceUseSpecification**
> PageOI40DBSecondaryResourceUseSpecification findByQbePagedPageOI40DBSecondaryResourceUseSpecification(qbe)

findByQbePaged

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbSecondaryResourceUseSpecificationRepositoryApi;


Oi40DbSecondaryResourceUseSpecificationRepositoryApi apiInstance = new Oi40DbSecondaryResourceUseSpecificationRepositoryApi();
QbeSupportOI40DBSecondaryResourceUseSpecification qbe = new QbeSupportOI40DBSecondaryResourceUseSpecification(); // QbeSupportOI40DBSecondaryResourceUseSpecification | qbe
try {
    PageOI40DBSecondaryResourceUseSpecification result = apiInstance.findByQbePagedPageOI40DBSecondaryResourceUseSpecification(qbe);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbSecondaryResourceUseSpecificationRepositoryApi#findByQbePagedPageOI40DBSecondaryResourceUseSpecification");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **qbe** | [**QbeSupportOI40DBSecondaryResourceUseSpecification**](QbeSupportOI40DBSecondaryResourceUseSpecification.md)| qbe |

### Return type

[**PageOI40DBSecondaryResourceUseSpecification**](PageOI40DBSecondaryResourceUseSpecification.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="updateListOI40DBSecondaryResourceUseSpecification"></a>
# **updateListOI40DBSecondaryResourceUseSpecification**
> List&lt;OI40DBSecondaryResourceUseSpecification&gt; updateListOI40DBSecondaryResourceUseSpecification(data)

update

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbSecondaryResourceUseSpecificationRepositoryApi;


Oi40DbSecondaryResourceUseSpecificationRepositoryApi apiInstance = new Oi40DbSecondaryResourceUseSpecificationRepositoryApi();
List<OI40DBSecondaryResourceUseSpecification> data = Arrays.asList(new OI40DBSecondaryResourceUseSpecification()); // List<OI40DBSecondaryResourceUseSpecification> | data
try {
    List<OI40DBSecondaryResourceUseSpecification> result = apiInstance.updateListOI40DBSecondaryResourceUseSpecification(data);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbSecondaryResourceUseSpecificationRepositoryApi#updateListOI40DBSecondaryResourceUseSpecification");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **data** | [**List&lt;OI40DBSecondaryResourceUseSpecification&gt;**](OI40DBSecondaryResourceUseSpecification.md)| data |

### Return type

[**List&lt;OI40DBSecondaryResourceUseSpecification&gt;**](OI40DBSecondaryResourceUseSpecification.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="updateSingleOI40DBSecondaryResourceUseSpecification"></a>
# **updateSingleOI40DBSecondaryResourceUseSpecification**
> OI40DBSecondaryResourceUseSpecification updateSingleOI40DBSecondaryResourceUseSpecification(data)

updateSingle

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbSecondaryResourceUseSpecificationRepositoryApi;


Oi40DbSecondaryResourceUseSpecificationRepositoryApi apiInstance = new Oi40DbSecondaryResourceUseSpecificationRepositoryApi();
OI40DBSecondaryResourceUseSpecification data = new OI40DBSecondaryResourceUseSpecification(); // OI40DBSecondaryResourceUseSpecification | data
try {
    OI40DBSecondaryResourceUseSpecification result = apiInstance.updateSingleOI40DBSecondaryResourceUseSpecification(data);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbSecondaryResourceUseSpecificationRepositoryApi#updateSingleOI40DBSecondaryResourceUseSpecification");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **data** | [**OI40DBSecondaryResourceUseSpecification**](OI40DBSecondaryResourceUseSpecification.md)| data |

### Return type

[**OI40DBSecondaryResourceUseSpecification**](OI40DBSecondaryResourceUseSpecification.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

