# Oi40DbDepartmentRepositoryApi

All URIs are relative to *https://localhost:8082/openi40-integration*

Method | HTTP request | Description
------------- | ------------- | -------------
[**deleteByCodeVoid6**](Oi40DbDepartmentRepositoryApi.md#deleteByCodeVoid6) | **GET** /integration/OI40DBDepartment/deleteByCode/{code} | deleteByCode
[**deleteByCodesVoid6**](Oi40DbDepartmentRepositoryApi.md#deleteByCodesVoid6) | **POST** /integration/OI40DBDepartment/deleteByCodes | deleteByCodes
[**doAutocompletePageOI40DBDepartment**](Oi40DbDepartmentRepositoryApi.md#doAutocompletePageOI40DBDepartment) | **POST** /integration/OI40DBDepartment/doAutocomplete | doAutocomplete
[**doLookupPageOI40DBDepartment**](Oi40DbDepartmentRepositoryApi.md#doLookupPageOI40DBDepartment) | **POST** /integration/OI40DBDepartment/doLookup | doLookup
[**findAllListOI40DBDepartment**](Oi40DbDepartmentRepositoryApi.md#findAllListOI40DBDepartment) | **GET** /integration/OI40DBDepartment | findAll
[**findAllPageOI40DBDepartment**](Oi40DbDepartmentRepositoryApi.md#findAllPageOI40DBDepartment) | **POST** /integration/OI40DBDepartment/findAllPaged | findAll
[**findByAfterIntegrationTsListOI40DBDepartment**](Oi40DbDepartmentRepositoryApi.md#findByAfterIntegrationTsListOI40DBDepartment) | **GET** /integration/OI40DBDepartment/integratedAfter/{ts} | findByAfterIntegrationTs
[**findByAfterModifiedTimestampListOI40DBDepartment**](Oi40DbDepartmentRepositoryApi.md#findByAfterModifiedTimestampListOI40DBDepartment) | **GET** /integration/OI40DBDepartment/modifiedAfter/{ts} | findByAfterModifiedTimestamp
[**findByCodeOI40DBDepartment**](Oi40DbDepartmentRepositoryApi.md#findByCodeOI40DBDepartment) | **GET** /integration/OI40DBDepartment/byCode/{code} | findByCode
[**findByCodesListOI40DBDepartment**](Oi40DbDepartmentRepositoryApi.md#findByCodesListOI40DBDepartment) | **POST** /integration/OI40DBDepartment/findByCodes | findByCodes
[**findByQbeListOI40DBDepartment**](Oi40DbDepartmentRepositoryApi.md#findByQbeListOI40DBDepartment) | **POST** /integration/OI40DBDepartment/findByQbe | findByQbe
[**findByQbePagedPageOI40DBDepartment**](Oi40DbDepartmentRepositoryApi.md#findByQbePagedPageOI40DBDepartment) | **POST** /integration/OI40DBDepartment/findByQbePaged | findByQbePaged
[**updateListOI40DBDepartment**](Oi40DbDepartmentRepositoryApi.md#updateListOI40DBDepartment) | **POST** /integration/OI40DBDepartment/update | update
[**updateSingleOI40DBDepartment**](Oi40DbDepartmentRepositoryApi.md#updateSingleOI40DBDepartment) | **POST** /integration/OI40DBDepartment/updateSingle | updateSingle


<a name="deleteByCodeVoid6"></a>
# **deleteByCodeVoid6**
> deleteByCodeVoid6(code)

deleteByCode

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbDepartmentRepositoryApi;


Oi40DbDepartmentRepositoryApi apiInstance = new Oi40DbDepartmentRepositoryApi();
String code = "code_example"; // String | code
try {
    apiInstance.deleteByCodeVoid6(code);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbDepartmentRepositoryApi#deleteByCodeVoid6");
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

<a name="deleteByCodesVoid6"></a>
# **deleteByCodesVoid6**
> deleteByCodesVoid6(codes)

deleteByCodes

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbDepartmentRepositoryApi;


Oi40DbDepartmentRepositoryApi apiInstance = new Oi40DbDepartmentRepositoryApi();
List<String> codes = Arrays.asList(new List<String>()); // List<String> | codes
try {
    apiInstance.deleteByCodesVoid6(codes);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbDepartmentRepositoryApi#deleteByCodesVoid6");
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

<a name="doAutocompletePageOI40DBDepartment"></a>
# **doAutocompletePageOI40DBDepartment**
> PageOI40DBDepartment doAutocompletePageOI40DBDepartment(autoCompleteData)

doAutocomplete

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbDepartmentRepositoryApi;


Oi40DbDepartmentRepositoryApi apiInstance = new Oi40DbDepartmentRepositoryApi();
AutoCompleteData autoCompleteData = new AutoCompleteData(); // AutoCompleteData | autoCompleteData
try {
    PageOI40DBDepartment result = apiInstance.doAutocompletePageOI40DBDepartment(autoCompleteData);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbDepartmentRepositoryApi#doAutocompletePageOI40DBDepartment");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **autoCompleteData** | [**AutoCompleteData**](AutoCompleteData.md)| autoCompleteData |

### Return type

[**PageOI40DBDepartment**](PageOI40DBDepartment.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: */*

<a name="doLookupPageOI40DBDepartment"></a>
# **doLookupPageOI40DBDepartment**
> PageOI40DBDepartment doLookupPageOI40DBDepartment(lookup)

doLookup

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbDepartmentRepositoryApi;


Oi40DbDepartmentRepositoryApi apiInstance = new Oi40DbDepartmentRepositoryApi();
LookupData lookup = new LookupData(); // LookupData | lookup
try {
    PageOI40DBDepartment result = apiInstance.doLookupPageOI40DBDepartment(lookup);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbDepartmentRepositoryApi#doLookupPageOI40DBDepartment");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **lookup** | [**LookupData**](LookupData.md)| lookup |

### Return type

[**PageOI40DBDepartment**](PageOI40DBDepartment.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: */*

<a name="findAllListOI40DBDepartment"></a>
# **findAllListOI40DBDepartment**
> List&lt;OI40DBDepartment&gt; findAllListOI40DBDepartment()

findAll

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbDepartmentRepositoryApi;


Oi40DbDepartmentRepositoryApi apiInstance = new Oi40DbDepartmentRepositoryApi();
try {
    List<OI40DBDepartment> result = apiInstance.findAllListOI40DBDepartment();
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbDepartmentRepositoryApi#findAllListOI40DBDepartment");
    e.printStackTrace();
}
```

### Parameters
This endpoint does not need any parameter.

### Return type

[**List&lt;OI40DBDepartment&gt;**](OI40DBDepartment.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="findAllPageOI40DBDepartment"></a>
# **findAllPageOI40DBDepartment**
> PageOI40DBDepartment findAllPageOI40DBDepartment(p)

findAll

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbDepartmentRepositoryApi;


Oi40DbDepartmentRepositoryApi apiInstance = new Oi40DbDepartmentRepositoryApi();
PageInfo p = new PageInfo(); // PageInfo | p
try {
    PageOI40DBDepartment result = apiInstance.findAllPageOI40DBDepartment(p);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbDepartmentRepositoryApi#findAllPageOI40DBDepartment");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **p** | [**PageInfo**](PageInfo.md)| p |

### Return type

[**PageOI40DBDepartment**](PageOI40DBDepartment.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="findByAfterIntegrationTsListOI40DBDepartment"></a>
# **findByAfterIntegrationTsListOI40DBDepartment**
> List&lt;OI40DBDepartment&gt; findByAfterIntegrationTsListOI40DBDepartment(ts)

findByAfterIntegrationTs

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbDepartmentRepositoryApi;


Oi40DbDepartmentRepositoryApi apiInstance = new Oi40DbDepartmentRepositoryApi();
String ts = "ts_example"; // String | ts
try {
    List<OI40DBDepartment> result = apiInstance.findByAfterIntegrationTsListOI40DBDepartment(ts);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbDepartmentRepositoryApi#findByAfterIntegrationTsListOI40DBDepartment");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **ts** | **String**| ts |

### Return type

[**List&lt;OI40DBDepartment&gt;**](OI40DBDepartment.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*

<a name="findByAfterModifiedTimestampListOI40DBDepartment"></a>
# **findByAfterModifiedTimestampListOI40DBDepartment**
> List&lt;OI40DBDepartment&gt; findByAfterModifiedTimestampListOI40DBDepartment(ts)

findByAfterModifiedTimestamp

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbDepartmentRepositoryApi;


Oi40DbDepartmentRepositoryApi apiInstance = new Oi40DbDepartmentRepositoryApi();
String ts = "ts_example"; // String | ts
try {
    List<OI40DBDepartment> result = apiInstance.findByAfterModifiedTimestampListOI40DBDepartment(ts);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbDepartmentRepositoryApi#findByAfterModifiedTimestampListOI40DBDepartment");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **ts** | **String**| ts |

### Return type

[**List&lt;OI40DBDepartment&gt;**](OI40DBDepartment.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*

<a name="findByCodeOI40DBDepartment"></a>
# **findByCodeOI40DBDepartment**
> OI40DBDepartment findByCodeOI40DBDepartment(code)

findByCode

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbDepartmentRepositoryApi;


Oi40DbDepartmentRepositoryApi apiInstance = new Oi40DbDepartmentRepositoryApi();
String code = "code_example"; // String | code
try {
    OI40DBDepartment result = apiInstance.findByCodeOI40DBDepartment(code);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbDepartmentRepositoryApi#findByCodeOI40DBDepartment");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **code** | **String**| code |

### Return type

[**OI40DBDepartment**](OI40DBDepartment.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="findByCodesListOI40DBDepartment"></a>
# **findByCodesListOI40DBDepartment**
> List&lt;OI40DBDepartment&gt; findByCodesListOI40DBDepartment(codes)

findByCodes

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbDepartmentRepositoryApi;


Oi40DbDepartmentRepositoryApi apiInstance = new Oi40DbDepartmentRepositoryApi();
List<String> codes = Arrays.asList(new List<String>()); // List<String> | codes
try {
    List<OI40DBDepartment> result = apiInstance.findByCodesListOI40DBDepartment(codes);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbDepartmentRepositoryApi#findByCodesListOI40DBDepartment");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **codes** | **List&lt;String&gt;**| codes |

### Return type

[**List&lt;OI40DBDepartment&gt;**](OI40DBDepartment.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="findByQbeListOI40DBDepartment"></a>
# **findByQbeListOI40DBDepartment**
> List&lt;OI40DBDepartment&gt; findByQbeListOI40DBDepartment(qbe)

findByQbe

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbDepartmentRepositoryApi;


Oi40DbDepartmentRepositoryApi apiInstance = new Oi40DbDepartmentRepositoryApi();
OI40DBDepartment qbe = new OI40DBDepartment(); // OI40DBDepartment | qbe
try {
    List<OI40DBDepartment> result = apiInstance.findByQbeListOI40DBDepartment(qbe);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbDepartmentRepositoryApi#findByQbeListOI40DBDepartment");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **qbe** | [**OI40DBDepartment**](OI40DBDepartment.md)| qbe |

### Return type

[**List&lt;OI40DBDepartment&gt;**](OI40DBDepartment.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="findByQbePagedPageOI40DBDepartment"></a>
# **findByQbePagedPageOI40DBDepartment**
> PageOI40DBDepartment findByQbePagedPageOI40DBDepartment(qbe)

findByQbePaged

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbDepartmentRepositoryApi;


Oi40DbDepartmentRepositoryApi apiInstance = new Oi40DbDepartmentRepositoryApi();
QbeSupportOI40DBDepartment qbe = new QbeSupportOI40DBDepartment(); // QbeSupportOI40DBDepartment | qbe
try {
    PageOI40DBDepartment result = apiInstance.findByQbePagedPageOI40DBDepartment(qbe);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbDepartmentRepositoryApi#findByQbePagedPageOI40DBDepartment");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **qbe** | [**QbeSupportOI40DBDepartment**](QbeSupportOI40DBDepartment.md)| qbe |

### Return type

[**PageOI40DBDepartment**](PageOI40DBDepartment.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="updateListOI40DBDepartment"></a>
# **updateListOI40DBDepartment**
> List&lt;OI40DBDepartment&gt; updateListOI40DBDepartment(data)

update

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbDepartmentRepositoryApi;


Oi40DbDepartmentRepositoryApi apiInstance = new Oi40DbDepartmentRepositoryApi();
List<OI40DBDepartment> data = Arrays.asList(new OI40DBDepartment()); // List<OI40DBDepartment> | data
try {
    List<OI40DBDepartment> result = apiInstance.updateListOI40DBDepartment(data);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbDepartmentRepositoryApi#updateListOI40DBDepartment");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **data** | [**List&lt;OI40DBDepartment&gt;**](OI40DBDepartment.md)| data |

### Return type

[**List&lt;OI40DBDepartment&gt;**](OI40DBDepartment.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="updateSingleOI40DBDepartment"></a>
# **updateSingleOI40DBDepartment**
> OI40DBDepartment updateSingleOI40DBDepartment(data)

updateSingle

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbDepartmentRepositoryApi;


Oi40DbDepartmentRepositoryApi apiInstance = new Oi40DbDepartmentRepositoryApi();
OI40DBDepartment data = new OI40DBDepartment(); // OI40DBDepartment | data
try {
    OI40DBDepartment result = apiInstance.updateSingleOI40DBDepartment(data);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbDepartmentRepositoryApi#updateSingleOI40DBDepartment");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **data** | [**OI40DBDepartment**](OI40DBDepartment.md)| data |

### Return type

[**OI40DBDepartment**](OI40DBDepartment.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

