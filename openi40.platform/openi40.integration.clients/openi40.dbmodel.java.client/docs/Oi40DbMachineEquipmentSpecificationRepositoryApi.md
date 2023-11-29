# Oi40DbMachineEquipmentSpecificationRepositoryApi

All URIs are relative to *https://localhost:8083/openi40-backoffice*

Method | HTTP request | Description
------------- | ------------- | -------------
[**deleteByCodeVoid7**](Oi40DbMachineEquipmentSpecificationRepositoryApi.md#deleteByCodeVoid7) | **GET** /integration/OI40DBMachineEquipmentSpecification/deleteByCode/{code} | deleteByCode
[**deleteByCodesVoid7**](Oi40DbMachineEquipmentSpecificationRepositoryApi.md#deleteByCodesVoid7) | **POST** /integration/OI40DBMachineEquipmentSpecification/deleteByCodes | deleteByCodes
[**doAutocompletePageOI40DBMachineEquipmentSpecification**](Oi40DbMachineEquipmentSpecificationRepositoryApi.md#doAutocompletePageOI40DBMachineEquipmentSpecification) | **POST** /integration/OI40DBMachineEquipmentSpecification/doAutocomplete | doAutocomplete
[**doLookupPageOI40DBMachineEquipmentSpecification**](Oi40DbMachineEquipmentSpecificationRepositoryApi.md#doLookupPageOI40DBMachineEquipmentSpecification) | **POST** /integration/OI40DBMachineEquipmentSpecification/doLookup | doLookup
[**findAllListOI40DBMachineEquipmentSpecification**](Oi40DbMachineEquipmentSpecificationRepositoryApi.md#findAllListOI40DBMachineEquipmentSpecification) | **GET** /integration/OI40DBMachineEquipmentSpecification | findAll
[**findAllPageOI40DBMachineEquipmentSpecification**](Oi40DbMachineEquipmentSpecificationRepositoryApi.md#findAllPageOI40DBMachineEquipmentSpecification) | **POST** /integration/OI40DBMachineEquipmentSpecification/findAllPaged | findAll
[**findByAfterIntegrationTsListOI40DBMachineEquipmentSpecification**](Oi40DbMachineEquipmentSpecificationRepositoryApi.md#findByAfterIntegrationTsListOI40DBMachineEquipmentSpecification) | **GET** /integration/OI40DBMachineEquipmentSpecification/integratedAfter/{ts} | findByAfterIntegrationTs
[**findByAfterModifiedTimestampListOI40DBMachineEquipmentSpecification**](Oi40DbMachineEquipmentSpecificationRepositoryApi.md#findByAfterModifiedTimestampListOI40DBMachineEquipmentSpecification) | **GET** /integration/OI40DBMachineEquipmentSpecification/modifiedAfter/{ts} | findByAfterModifiedTimestamp
[**findByCodeOI40DBMachineEquipmentSpecification**](Oi40DbMachineEquipmentSpecificationRepositoryApi.md#findByCodeOI40DBMachineEquipmentSpecification) | **GET** /integration/OI40DBMachineEquipmentSpecification/byCode/{code} | findByCode
[**findByCodesListOI40DBMachineEquipmentSpecification**](Oi40DbMachineEquipmentSpecificationRepositoryApi.md#findByCodesListOI40DBMachineEquipmentSpecification) | **POST** /integration/OI40DBMachineEquipmentSpecification/findByCodes | findByCodes
[**findByQbeListOI40DBMachineEquipmentSpecification**](Oi40DbMachineEquipmentSpecificationRepositoryApi.md#findByQbeListOI40DBMachineEquipmentSpecification) | **POST** /integration/OI40DBMachineEquipmentSpecification/findByQbe | findByQbe
[**findByQbePagedPageOI40DBMachineEquipmentSpecification**](Oi40DbMachineEquipmentSpecificationRepositoryApi.md#findByQbePagedPageOI40DBMachineEquipmentSpecification) | **POST** /integration/OI40DBMachineEquipmentSpecification/findByQbePaged | findByQbePaged
[**updateListOI40DBMachineEquipmentSpecification**](Oi40DbMachineEquipmentSpecificationRepositoryApi.md#updateListOI40DBMachineEquipmentSpecification) | **POST** /integration/OI40DBMachineEquipmentSpecification/update | update
[**updateSingleOI40DBMachineEquipmentSpecification**](Oi40DbMachineEquipmentSpecificationRepositoryApi.md#updateSingleOI40DBMachineEquipmentSpecification) | **POST** /integration/OI40DBMachineEquipmentSpecification/updateSingle | updateSingle


<a name="deleteByCodeVoid7"></a>
# **deleteByCodeVoid7**
> deleteByCodeVoid7(code)

deleteByCode

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbMachineEquipmentSpecificationRepositoryApi;


Oi40DbMachineEquipmentSpecificationRepositoryApi apiInstance = new Oi40DbMachineEquipmentSpecificationRepositoryApi();
String code = "code_example"; // String | code
try {
    apiInstance.deleteByCodeVoid7(code);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbMachineEquipmentSpecificationRepositoryApi#deleteByCodeVoid7");
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

<a name="deleteByCodesVoid7"></a>
# **deleteByCodesVoid7**
> deleteByCodesVoid7(codes)

deleteByCodes

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbMachineEquipmentSpecificationRepositoryApi;


Oi40DbMachineEquipmentSpecificationRepositoryApi apiInstance = new Oi40DbMachineEquipmentSpecificationRepositoryApi();
List<String> codes = Arrays.asList(new List<String>()); // List<String> | codes
try {
    apiInstance.deleteByCodesVoid7(codes);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbMachineEquipmentSpecificationRepositoryApi#deleteByCodesVoid7");
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

<a name="doAutocompletePageOI40DBMachineEquipmentSpecification"></a>
# **doAutocompletePageOI40DBMachineEquipmentSpecification**
> PageOI40DBMachineEquipmentSpecification doAutocompletePageOI40DBMachineEquipmentSpecification(autoCompleteData)

doAutocomplete

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbMachineEquipmentSpecificationRepositoryApi;


Oi40DbMachineEquipmentSpecificationRepositoryApi apiInstance = new Oi40DbMachineEquipmentSpecificationRepositoryApi();
AutoCompleteData autoCompleteData = new AutoCompleteData(); // AutoCompleteData | autoCompleteData
try {
    PageOI40DBMachineEquipmentSpecification result = apiInstance.doAutocompletePageOI40DBMachineEquipmentSpecification(autoCompleteData);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbMachineEquipmentSpecificationRepositoryApi#doAutocompletePageOI40DBMachineEquipmentSpecification");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **autoCompleteData** | [**AutoCompleteData**](AutoCompleteData.md)| autoCompleteData |

### Return type

[**PageOI40DBMachineEquipmentSpecification**](PageOI40DBMachineEquipmentSpecification.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: */*

<a name="doLookupPageOI40DBMachineEquipmentSpecification"></a>
# **doLookupPageOI40DBMachineEquipmentSpecification**
> PageOI40DBMachineEquipmentSpecification doLookupPageOI40DBMachineEquipmentSpecification(lookup)

doLookup

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbMachineEquipmentSpecificationRepositoryApi;


Oi40DbMachineEquipmentSpecificationRepositoryApi apiInstance = new Oi40DbMachineEquipmentSpecificationRepositoryApi();
LookupData lookup = new LookupData(); // LookupData | lookup
try {
    PageOI40DBMachineEquipmentSpecification result = apiInstance.doLookupPageOI40DBMachineEquipmentSpecification(lookup);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbMachineEquipmentSpecificationRepositoryApi#doLookupPageOI40DBMachineEquipmentSpecification");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **lookup** | [**LookupData**](LookupData.md)| lookup |

### Return type

[**PageOI40DBMachineEquipmentSpecification**](PageOI40DBMachineEquipmentSpecification.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: */*

<a name="findAllListOI40DBMachineEquipmentSpecification"></a>
# **findAllListOI40DBMachineEquipmentSpecification**
> List&lt;OI40DBMachineEquipmentSpecification&gt; findAllListOI40DBMachineEquipmentSpecification()

findAll

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbMachineEquipmentSpecificationRepositoryApi;


Oi40DbMachineEquipmentSpecificationRepositoryApi apiInstance = new Oi40DbMachineEquipmentSpecificationRepositoryApi();
try {
    List<OI40DBMachineEquipmentSpecification> result = apiInstance.findAllListOI40DBMachineEquipmentSpecification();
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbMachineEquipmentSpecificationRepositoryApi#findAllListOI40DBMachineEquipmentSpecification");
    e.printStackTrace();
}
```

### Parameters
This endpoint does not need any parameter.

### Return type

[**List&lt;OI40DBMachineEquipmentSpecification&gt;**](OI40DBMachineEquipmentSpecification.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="findAllPageOI40DBMachineEquipmentSpecification"></a>
# **findAllPageOI40DBMachineEquipmentSpecification**
> PageOI40DBMachineEquipmentSpecification findAllPageOI40DBMachineEquipmentSpecification(p)

findAll

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbMachineEquipmentSpecificationRepositoryApi;


Oi40DbMachineEquipmentSpecificationRepositoryApi apiInstance = new Oi40DbMachineEquipmentSpecificationRepositoryApi();
PageInfo p = new PageInfo(); // PageInfo | p
try {
    PageOI40DBMachineEquipmentSpecification result = apiInstance.findAllPageOI40DBMachineEquipmentSpecification(p);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbMachineEquipmentSpecificationRepositoryApi#findAllPageOI40DBMachineEquipmentSpecification");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **p** | [**PageInfo**](PageInfo.md)| p |

### Return type

[**PageOI40DBMachineEquipmentSpecification**](PageOI40DBMachineEquipmentSpecification.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="findByAfterIntegrationTsListOI40DBMachineEquipmentSpecification"></a>
# **findByAfterIntegrationTsListOI40DBMachineEquipmentSpecification**
> List&lt;OI40DBMachineEquipmentSpecification&gt; findByAfterIntegrationTsListOI40DBMachineEquipmentSpecification(ts)

findByAfterIntegrationTs

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbMachineEquipmentSpecificationRepositoryApi;


Oi40DbMachineEquipmentSpecificationRepositoryApi apiInstance = new Oi40DbMachineEquipmentSpecificationRepositoryApi();
String ts = "ts_example"; // String | ts
try {
    List<OI40DBMachineEquipmentSpecification> result = apiInstance.findByAfterIntegrationTsListOI40DBMachineEquipmentSpecification(ts);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbMachineEquipmentSpecificationRepositoryApi#findByAfterIntegrationTsListOI40DBMachineEquipmentSpecification");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **ts** | **String**| ts |

### Return type

[**List&lt;OI40DBMachineEquipmentSpecification&gt;**](OI40DBMachineEquipmentSpecification.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*

<a name="findByAfterModifiedTimestampListOI40DBMachineEquipmentSpecification"></a>
# **findByAfterModifiedTimestampListOI40DBMachineEquipmentSpecification**
> List&lt;OI40DBMachineEquipmentSpecification&gt; findByAfterModifiedTimestampListOI40DBMachineEquipmentSpecification(ts)

findByAfterModifiedTimestamp

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbMachineEquipmentSpecificationRepositoryApi;


Oi40DbMachineEquipmentSpecificationRepositoryApi apiInstance = new Oi40DbMachineEquipmentSpecificationRepositoryApi();
String ts = "ts_example"; // String | ts
try {
    List<OI40DBMachineEquipmentSpecification> result = apiInstance.findByAfterModifiedTimestampListOI40DBMachineEquipmentSpecification(ts);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbMachineEquipmentSpecificationRepositoryApi#findByAfterModifiedTimestampListOI40DBMachineEquipmentSpecification");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **ts** | **String**| ts |

### Return type

[**List&lt;OI40DBMachineEquipmentSpecification&gt;**](OI40DBMachineEquipmentSpecification.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*

<a name="findByCodeOI40DBMachineEquipmentSpecification"></a>
# **findByCodeOI40DBMachineEquipmentSpecification**
> OI40DBMachineEquipmentSpecification findByCodeOI40DBMachineEquipmentSpecification(code)

findByCode

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbMachineEquipmentSpecificationRepositoryApi;


Oi40DbMachineEquipmentSpecificationRepositoryApi apiInstance = new Oi40DbMachineEquipmentSpecificationRepositoryApi();
String code = "code_example"; // String | code
try {
    OI40DBMachineEquipmentSpecification result = apiInstance.findByCodeOI40DBMachineEquipmentSpecification(code);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbMachineEquipmentSpecificationRepositoryApi#findByCodeOI40DBMachineEquipmentSpecification");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **code** | **String**| code |

### Return type

[**OI40DBMachineEquipmentSpecification**](OI40DBMachineEquipmentSpecification.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="findByCodesListOI40DBMachineEquipmentSpecification"></a>
# **findByCodesListOI40DBMachineEquipmentSpecification**
> List&lt;OI40DBMachineEquipmentSpecification&gt; findByCodesListOI40DBMachineEquipmentSpecification(codes)

findByCodes

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbMachineEquipmentSpecificationRepositoryApi;


Oi40DbMachineEquipmentSpecificationRepositoryApi apiInstance = new Oi40DbMachineEquipmentSpecificationRepositoryApi();
List<String> codes = Arrays.asList(new List<String>()); // List<String> | codes
try {
    List<OI40DBMachineEquipmentSpecification> result = apiInstance.findByCodesListOI40DBMachineEquipmentSpecification(codes);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbMachineEquipmentSpecificationRepositoryApi#findByCodesListOI40DBMachineEquipmentSpecification");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **codes** | **List&lt;String&gt;**| codes |

### Return type

[**List&lt;OI40DBMachineEquipmentSpecification&gt;**](OI40DBMachineEquipmentSpecification.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="findByQbeListOI40DBMachineEquipmentSpecification"></a>
# **findByQbeListOI40DBMachineEquipmentSpecification**
> List&lt;OI40DBMachineEquipmentSpecification&gt; findByQbeListOI40DBMachineEquipmentSpecification(qbe)

findByQbe

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbMachineEquipmentSpecificationRepositoryApi;


Oi40DbMachineEquipmentSpecificationRepositoryApi apiInstance = new Oi40DbMachineEquipmentSpecificationRepositoryApi();
OI40DBMachineEquipmentSpecification qbe = new OI40DBMachineEquipmentSpecification(); // OI40DBMachineEquipmentSpecification | qbe
try {
    List<OI40DBMachineEquipmentSpecification> result = apiInstance.findByQbeListOI40DBMachineEquipmentSpecification(qbe);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbMachineEquipmentSpecificationRepositoryApi#findByQbeListOI40DBMachineEquipmentSpecification");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **qbe** | [**OI40DBMachineEquipmentSpecification**](OI40DBMachineEquipmentSpecification.md)| qbe |

### Return type

[**List&lt;OI40DBMachineEquipmentSpecification&gt;**](OI40DBMachineEquipmentSpecification.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="findByQbePagedPageOI40DBMachineEquipmentSpecification"></a>
# **findByQbePagedPageOI40DBMachineEquipmentSpecification**
> PageOI40DBMachineEquipmentSpecification findByQbePagedPageOI40DBMachineEquipmentSpecification(qbe)

findByQbePaged

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbMachineEquipmentSpecificationRepositoryApi;


Oi40DbMachineEquipmentSpecificationRepositoryApi apiInstance = new Oi40DbMachineEquipmentSpecificationRepositoryApi();
QbeSupportOI40DBMachineEquipmentSpecification qbe = new QbeSupportOI40DBMachineEquipmentSpecification(); // QbeSupportOI40DBMachineEquipmentSpecification | qbe
try {
    PageOI40DBMachineEquipmentSpecification result = apiInstance.findByQbePagedPageOI40DBMachineEquipmentSpecification(qbe);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbMachineEquipmentSpecificationRepositoryApi#findByQbePagedPageOI40DBMachineEquipmentSpecification");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **qbe** | [**QbeSupportOI40DBMachineEquipmentSpecification**](QbeSupportOI40DBMachineEquipmentSpecification.md)| qbe |

### Return type

[**PageOI40DBMachineEquipmentSpecification**](PageOI40DBMachineEquipmentSpecification.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="updateListOI40DBMachineEquipmentSpecification"></a>
# **updateListOI40DBMachineEquipmentSpecification**
> List&lt;OI40DBMachineEquipmentSpecification&gt; updateListOI40DBMachineEquipmentSpecification(data)

update

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbMachineEquipmentSpecificationRepositoryApi;


Oi40DbMachineEquipmentSpecificationRepositoryApi apiInstance = new Oi40DbMachineEquipmentSpecificationRepositoryApi();
List<OI40DBMachineEquipmentSpecification> data = Arrays.asList(new OI40DBMachineEquipmentSpecification()); // List<OI40DBMachineEquipmentSpecification> | data
try {
    List<OI40DBMachineEquipmentSpecification> result = apiInstance.updateListOI40DBMachineEquipmentSpecification(data);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbMachineEquipmentSpecificationRepositoryApi#updateListOI40DBMachineEquipmentSpecification");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **data** | [**List&lt;OI40DBMachineEquipmentSpecification&gt;**](OI40DBMachineEquipmentSpecification.md)| data |

### Return type

[**List&lt;OI40DBMachineEquipmentSpecification&gt;**](OI40DBMachineEquipmentSpecification.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="updateSingleOI40DBMachineEquipmentSpecification"></a>
# **updateSingleOI40DBMachineEquipmentSpecification**
> OI40DBMachineEquipmentSpecification updateSingleOI40DBMachineEquipmentSpecification(data)

updateSingle

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbMachineEquipmentSpecificationRepositoryApi;


Oi40DbMachineEquipmentSpecificationRepositoryApi apiInstance = new Oi40DbMachineEquipmentSpecificationRepositoryApi();
OI40DBMachineEquipmentSpecification data = new OI40DBMachineEquipmentSpecification(); // OI40DBMachineEquipmentSpecification | data
try {
    OI40DBMachineEquipmentSpecification result = apiInstance.updateSingleOI40DBMachineEquipmentSpecification(data);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbMachineEquipmentSpecificationRepositoryApi#updateSingleOI40DBMachineEquipmentSpecification");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **data** | [**OI40DBMachineEquipmentSpecification**](OI40DBMachineEquipmentSpecification.md)| data |

### Return type

[**OI40DBMachineEquipmentSpecification**](OI40DBMachineEquipmentSpecification.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

