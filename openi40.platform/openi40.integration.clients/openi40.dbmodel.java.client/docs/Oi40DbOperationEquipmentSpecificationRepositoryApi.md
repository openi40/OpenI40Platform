# Oi40DbOperationEquipmentSpecificationRepositoryApi

All URIs are relative to *https://localhost:8083/openi40-backoffice*

Method | HTTP request | Description
------------- | ------------- | -------------
[**deleteByCodeVoid10**](Oi40DbOperationEquipmentSpecificationRepositoryApi.md#deleteByCodeVoid10) | **GET** /integration/OI40DBOperationEquipmentSpecification/deleteByCode/{code} | deleteByCode
[**deleteByCodesVoid10**](Oi40DbOperationEquipmentSpecificationRepositoryApi.md#deleteByCodesVoid10) | **POST** /integration/OI40DBOperationEquipmentSpecification/deleteByCodes | deleteByCodes
[**doAutocompletePageOI40DBOperationEquipmentSpecification**](Oi40DbOperationEquipmentSpecificationRepositoryApi.md#doAutocompletePageOI40DBOperationEquipmentSpecification) | **POST** /integration/OI40DBOperationEquipmentSpecification/doAutocomplete | doAutocomplete
[**doLookupPageOI40DBOperationEquipmentSpecification**](Oi40DbOperationEquipmentSpecificationRepositoryApi.md#doLookupPageOI40DBOperationEquipmentSpecification) | **POST** /integration/OI40DBOperationEquipmentSpecification/doLookup | doLookup
[**findAllListOI40DBOperationEquipmentSpecification**](Oi40DbOperationEquipmentSpecificationRepositoryApi.md#findAllListOI40DBOperationEquipmentSpecification) | **GET** /integration/OI40DBOperationEquipmentSpecification | findAll
[**findAllPageOI40DBOperationEquipmentSpecification**](Oi40DbOperationEquipmentSpecificationRepositoryApi.md#findAllPageOI40DBOperationEquipmentSpecification) | **POST** /integration/OI40DBOperationEquipmentSpecification/findAllPaged | findAll
[**findByAfterIntegrationTsListOI40DBOperationEquipmentSpecification**](Oi40DbOperationEquipmentSpecificationRepositoryApi.md#findByAfterIntegrationTsListOI40DBOperationEquipmentSpecification) | **GET** /integration/OI40DBOperationEquipmentSpecification/integratedAfter/{ts} | findByAfterIntegrationTs
[**findByAfterModifiedTimestampListOI40DBOperationEquipmentSpecification**](Oi40DbOperationEquipmentSpecificationRepositoryApi.md#findByAfterModifiedTimestampListOI40DBOperationEquipmentSpecification) | **GET** /integration/OI40DBOperationEquipmentSpecification/modifiedAfter/{ts} | findByAfterModifiedTimestamp
[**findByCodeOI40DBOperationEquipmentSpecification**](Oi40DbOperationEquipmentSpecificationRepositoryApi.md#findByCodeOI40DBOperationEquipmentSpecification) | **GET** /integration/OI40DBOperationEquipmentSpecification/byCode/{code} | findByCode
[**findByCodesListOI40DBOperationEquipmentSpecification**](Oi40DbOperationEquipmentSpecificationRepositoryApi.md#findByCodesListOI40DBOperationEquipmentSpecification) | **POST** /integration/OI40DBOperationEquipmentSpecification/findByCodes | findByCodes
[**findByQbeListOI40DBOperationEquipmentSpecification**](Oi40DbOperationEquipmentSpecificationRepositoryApi.md#findByQbeListOI40DBOperationEquipmentSpecification) | **POST** /integration/OI40DBOperationEquipmentSpecification/findByQbe | findByQbe
[**findByQbePagedPageOI40DBOperationEquipmentSpecification**](Oi40DbOperationEquipmentSpecificationRepositoryApi.md#findByQbePagedPageOI40DBOperationEquipmentSpecification) | **POST** /integration/OI40DBOperationEquipmentSpecification/findByQbePaged | findByQbePaged
[**updateListOI40DBOperationEquipmentSpecification**](Oi40DbOperationEquipmentSpecificationRepositoryApi.md#updateListOI40DBOperationEquipmentSpecification) | **POST** /integration/OI40DBOperationEquipmentSpecification/update | update
[**updateSingleOI40DBOperationEquipmentSpecification**](Oi40DbOperationEquipmentSpecificationRepositoryApi.md#updateSingleOI40DBOperationEquipmentSpecification) | **POST** /integration/OI40DBOperationEquipmentSpecification/updateSingle | updateSingle


<a name="deleteByCodeVoid10"></a>
# **deleteByCodeVoid10**
> deleteByCodeVoid10(code)

deleteByCode

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbOperationEquipmentSpecificationRepositoryApi;


Oi40DbOperationEquipmentSpecificationRepositoryApi apiInstance = new Oi40DbOperationEquipmentSpecificationRepositoryApi();
String code = "code_example"; // String | code
try {
    apiInstance.deleteByCodeVoid10(code);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbOperationEquipmentSpecificationRepositoryApi#deleteByCodeVoid10");
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

<a name="deleteByCodesVoid10"></a>
# **deleteByCodesVoid10**
> deleteByCodesVoid10(codes)

deleteByCodes

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbOperationEquipmentSpecificationRepositoryApi;


Oi40DbOperationEquipmentSpecificationRepositoryApi apiInstance = new Oi40DbOperationEquipmentSpecificationRepositoryApi();
List<String> codes = Arrays.asList(new List<String>()); // List<String> | codes
try {
    apiInstance.deleteByCodesVoid10(codes);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbOperationEquipmentSpecificationRepositoryApi#deleteByCodesVoid10");
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

<a name="doAutocompletePageOI40DBOperationEquipmentSpecification"></a>
# **doAutocompletePageOI40DBOperationEquipmentSpecification**
> PageOI40DBOperationEquipmentSpecification doAutocompletePageOI40DBOperationEquipmentSpecification(autoCompleteData)

doAutocomplete

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbOperationEquipmentSpecificationRepositoryApi;


Oi40DbOperationEquipmentSpecificationRepositoryApi apiInstance = new Oi40DbOperationEquipmentSpecificationRepositoryApi();
AutoCompleteData autoCompleteData = new AutoCompleteData(); // AutoCompleteData | autoCompleteData
try {
    PageOI40DBOperationEquipmentSpecification result = apiInstance.doAutocompletePageOI40DBOperationEquipmentSpecification(autoCompleteData);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbOperationEquipmentSpecificationRepositoryApi#doAutocompletePageOI40DBOperationEquipmentSpecification");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **autoCompleteData** | [**AutoCompleteData**](AutoCompleteData.md)| autoCompleteData |

### Return type

[**PageOI40DBOperationEquipmentSpecification**](PageOI40DBOperationEquipmentSpecification.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: */*

<a name="doLookupPageOI40DBOperationEquipmentSpecification"></a>
# **doLookupPageOI40DBOperationEquipmentSpecification**
> PageOI40DBOperationEquipmentSpecification doLookupPageOI40DBOperationEquipmentSpecification(lookup)

doLookup

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbOperationEquipmentSpecificationRepositoryApi;


Oi40DbOperationEquipmentSpecificationRepositoryApi apiInstance = new Oi40DbOperationEquipmentSpecificationRepositoryApi();
LookupData lookup = new LookupData(); // LookupData | lookup
try {
    PageOI40DBOperationEquipmentSpecification result = apiInstance.doLookupPageOI40DBOperationEquipmentSpecification(lookup);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbOperationEquipmentSpecificationRepositoryApi#doLookupPageOI40DBOperationEquipmentSpecification");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **lookup** | [**LookupData**](LookupData.md)| lookup |

### Return type

[**PageOI40DBOperationEquipmentSpecification**](PageOI40DBOperationEquipmentSpecification.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: */*

<a name="findAllListOI40DBOperationEquipmentSpecification"></a>
# **findAllListOI40DBOperationEquipmentSpecification**
> List&lt;OI40DBOperationEquipmentSpecification&gt; findAllListOI40DBOperationEquipmentSpecification()

findAll

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbOperationEquipmentSpecificationRepositoryApi;


Oi40DbOperationEquipmentSpecificationRepositoryApi apiInstance = new Oi40DbOperationEquipmentSpecificationRepositoryApi();
try {
    List<OI40DBOperationEquipmentSpecification> result = apiInstance.findAllListOI40DBOperationEquipmentSpecification();
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbOperationEquipmentSpecificationRepositoryApi#findAllListOI40DBOperationEquipmentSpecification");
    e.printStackTrace();
}
```

### Parameters
This endpoint does not need any parameter.

### Return type

[**List&lt;OI40DBOperationEquipmentSpecification&gt;**](OI40DBOperationEquipmentSpecification.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="findAllPageOI40DBOperationEquipmentSpecification"></a>
# **findAllPageOI40DBOperationEquipmentSpecification**
> PageOI40DBOperationEquipmentSpecification findAllPageOI40DBOperationEquipmentSpecification(p)

findAll

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbOperationEquipmentSpecificationRepositoryApi;


Oi40DbOperationEquipmentSpecificationRepositoryApi apiInstance = new Oi40DbOperationEquipmentSpecificationRepositoryApi();
PageInfo p = new PageInfo(); // PageInfo | p
try {
    PageOI40DBOperationEquipmentSpecification result = apiInstance.findAllPageOI40DBOperationEquipmentSpecification(p);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbOperationEquipmentSpecificationRepositoryApi#findAllPageOI40DBOperationEquipmentSpecification");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **p** | [**PageInfo**](PageInfo.md)| p |

### Return type

[**PageOI40DBOperationEquipmentSpecification**](PageOI40DBOperationEquipmentSpecification.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="findByAfterIntegrationTsListOI40DBOperationEquipmentSpecification"></a>
# **findByAfterIntegrationTsListOI40DBOperationEquipmentSpecification**
> List&lt;OI40DBOperationEquipmentSpecification&gt; findByAfterIntegrationTsListOI40DBOperationEquipmentSpecification(ts)

findByAfterIntegrationTs

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbOperationEquipmentSpecificationRepositoryApi;


Oi40DbOperationEquipmentSpecificationRepositoryApi apiInstance = new Oi40DbOperationEquipmentSpecificationRepositoryApi();
String ts = "ts_example"; // String | ts
try {
    List<OI40DBOperationEquipmentSpecification> result = apiInstance.findByAfterIntegrationTsListOI40DBOperationEquipmentSpecification(ts);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbOperationEquipmentSpecificationRepositoryApi#findByAfterIntegrationTsListOI40DBOperationEquipmentSpecification");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **ts** | **String**| ts |

### Return type

[**List&lt;OI40DBOperationEquipmentSpecification&gt;**](OI40DBOperationEquipmentSpecification.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*

<a name="findByAfterModifiedTimestampListOI40DBOperationEquipmentSpecification"></a>
# **findByAfterModifiedTimestampListOI40DBOperationEquipmentSpecification**
> List&lt;OI40DBOperationEquipmentSpecification&gt; findByAfterModifiedTimestampListOI40DBOperationEquipmentSpecification(ts)

findByAfterModifiedTimestamp

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbOperationEquipmentSpecificationRepositoryApi;


Oi40DbOperationEquipmentSpecificationRepositoryApi apiInstance = new Oi40DbOperationEquipmentSpecificationRepositoryApi();
String ts = "ts_example"; // String | ts
try {
    List<OI40DBOperationEquipmentSpecification> result = apiInstance.findByAfterModifiedTimestampListOI40DBOperationEquipmentSpecification(ts);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbOperationEquipmentSpecificationRepositoryApi#findByAfterModifiedTimestampListOI40DBOperationEquipmentSpecification");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **ts** | **String**| ts |

### Return type

[**List&lt;OI40DBOperationEquipmentSpecification&gt;**](OI40DBOperationEquipmentSpecification.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*

<a name="findByCodeOI40DBOperationEquipmentSpecification"></a>
# **findByCodeOI40DBOperationEquipmentSpecification**
> OI40DBOperationEquipmentSpecification findByCodeOI40DBOperationEquipmentSpecification(code)

findByCode

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbOperationEquipmentSpecificationRepositoryApi;


Oi40DbOperationEquipmentSpecificationRepositoryApi apiInstance = new Oi40DbOperationEquipmentSpecificationRepositoryApi();
String code = "code_example"; // String | code
try {
    OI40DBOperationEquipmentSpecification result = apiInstance.findByCodeOI40DBOperationEquipmentSpecification(code);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbOperationEquipmentSpecificationRepositoryApi#findByCodeOI40DBOperationEquipmentSpecification");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **code** | **String**| code |

### Return type

[**OI40DBOperationEquipmentSpecification**](OI40DBOperationEquipmentSpecification.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="findByCodesListOI40DBOperationEquipmentSpecification"></a>
# **findByCodesListOI40DBOperationEquipmentSpecification**
> List&lt;OI40DBOperationEquipmentSpecification&gt; findByCodesListOI40DBOperationEquipmentSpecification(codes)

findByCodes

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbOperationEquipmentSpecificationRepositoryApi;


Oi40DbOperationEquipmentSpecificationRepositoryApi apiInstance = new Oi40DbOperationEquipmentSpecificationRepositoryApi();
List<String> codes = Arrays.asList(new List<String>()); // List<String> | codes
try {
    List<OI40DBOperationEquipmentSpecification> result = apiInstance.findByCodesListOI40DBOperationEquipmentSpecification(codes);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbOperationEquipmentSpecificationRepositoryApi#findByCodesListOI40DBOperationEquipmentSpecification");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **codes** | **List&lt;String&gt;**| codes |

### Return type

[**List&lt;OI40DBOperationEquipmentSpecification&gt;**](OI40DBOperationEquipmentSpecification.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="findByQbeListOI40DBOperationEquipmentSpecification"></a>
# **findByQbeListOI40DBOperationEquipmentSpecification**
> List&lt;OI40DBOperationEquipmentSpecification&gt; findByQbeListOI40DBOperationEquipmentSpecification(qbe)

findByQbe

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbOperationEquipmentSpecificationRepositoryApi;


Oi40DbOperationEquipmentSpecificationRepositoryApi apiInstance = new Oi40DbOperationEquipmentSpecificationRepositoryApi();
OI40DBOperationEquipmentSpecification qbe = new OI40DBOperationEquipmentSpecification(); // OI40DBOperationEquipmentSpecification | qbe
try {
    List<OI40DBOperationEquipmentSpecification> result = apiInstance.findByQbeListOI40DBOperationEquipmentSpecification(qbe);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbOperationEquipmentSpecificationRepositoryApi#findByQbeListOI40DBOperationEquipmentSpecification");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **qbe** | [**OI40DBOperationEquipmentSpecification**](OI40DBOperationEquipmentSpecification.md)| qbe |

### Return type

[**List&lt;OI40DBOperationEquipmentSpecification&gt;**](OI40DBOperationEquipmentSpecification.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="findByQbePagedPageOI40DBOperationEquipmentSpecification"></a>
# **findByQbePagedPageOI40DBOperationEquipmentSpecification**
> PageOI40DBOperationEquipmentSpecification findByQbePagedPageOI40DBOperationEquipmentSpecification(qbe)

findByQbePaged

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbOperationEquipmentSpecificationRepositoryApi;


Oi40DbOperationEquipmentSpecificationRepositoryApi apiInstance = new Oi40DbOperationEquipmentSpecificationRepositoryApi();
QbeSupportOI40DBOperationEquipmentSpecification qbe = new QbeSupportOI40DBOperationEquipmentSpecification(); // QbeSupportOI40DBOperationEquipmentSpecification | qbe
try {
    PageOI40DBOperationEquipmentSpecification result = apiInstance.findByQbePagedPageOI40DBOperationEquipmentSpecification(qbe);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbOperationEquipmentSpecificationRepositoryApi#findByQbePagedPageOI40DBOperationEquipmentSpecification");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **qbe** | [**QbeSupportOI40DBOperationEquipmentSpecification**](QbeSupportOI40DBOperationEquipmentSpecification.md)| qbe |

### Return type

[**PageOI40DBOperationEquipmentSpecification**](PageOI40DBOperationEquipmentSpecification.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="updateListOI40DBOperationEquipmentSpecification"></a>
# **updateListOI40DBOperationEquipmentSpecification**
> List&lt;OI40DBOperationEquipmentSpecification&gt; updateListOI40DBOperationEquipmentSpecification(data)

update

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbOperationEquipmentSpecificationRepositoryApi;


Oi40DbOperationEquipmentSpecificationRepositoryApi apiInstance = new Oi40DbOperationEquipmentSpecificationRepositoryApi();
List<OI40DBOperationEquipmentSpecification> data = Arrays.asList(new OI40DBOperationEquipmentSpecification()); // List<OI40DBOperationEquipmentSpecification> | data
try {
    List<OI40DBOperationEquipmentSpecification> result = apiInstance.updateListOI40DBOperationEquipmentSpecification(data);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbOperationEquipmentSpecificationRepositoryApi#updateListOI40DBOperationEquipmentSpecification");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **data** | [**List&lt;OI40DBOperationEquipmentSpecification&gt;**](OI40DBOperationEquipmentSpecification.md)| data |

### Return type

[**List&lt;OI40DBOperationEquipmentSpecification&gt;**](OI40DBOperationEquipmentSpecification.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="updateSingleOI40DBOperationEquipmentSpecification"></a>
# **updateSingleOI40DBOperationEquipmentSpecification**
> OI40DBOperationEquipmentSpecification updateSingleOI40DBOperationEquipmentSpecification(data)

updateSingle

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbOperationEquipmentSpecificationRepositoryApi;


Oi40DbOperationEquipmentSpecificationRepositoryApi apiInstance = new Oi40DbOperationEquipmentSpecificationRepositoryApi();
OI40DBOperationEquipmentSpecification data = new OI40DBOperationEquipmentSpecification(); // OI40DBOperationEquipmentSpecification | data
try {
    OI40DBOperationEquipmentSpecification result = apiInstance.updateSingleOI40DBOperationEquipmentSpecification(data);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbOperationEquipmentSpecificationRepositoryApi#updateSingleOI40DBOperationEquipmentSpecification");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **data** | [**OI40DBOperationEquipmentSpecification**](OI40DBOperationEquipmentSpecification.md)| data |

### Return type

[**OI40DBOperationEquipmentSpecification**](OI40DBOperationEquipmentSpecification.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

