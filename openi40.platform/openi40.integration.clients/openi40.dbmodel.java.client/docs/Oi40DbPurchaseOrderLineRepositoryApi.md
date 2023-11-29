# Oi40DbPurchaseOrderLineRepositoryApi

All URIs are relative to *https://localhost:8083/openi40-backoffice*

Method | HTTP request | Description
------------- | ------------- | -------------
[**deleteByCodeVoid19**](Oi40DbPurchaseOrderLineRepositoryApi.md#deleteByCodeVoid19) | **GET** /integration/OI40DBPurchaseOrderLine/deleteByCode/{code} | deleteByCode
[**deleteByCodesVoid19**](Oi40DbPurchaseOrderLineRepositoryApi.md#deleteByCodesVoid19) | **POST** /integration/OI40DBPurchaseOrderLine/deleteByCodes | deleteByCodes
[**doAutocompletePageOI40DBPurchaseOrderLine**](Oi40DbPurchaseOrderLineRepositoryApi.md#doAutocompletePageOI40DBPurchaseOrderLine) | **POST** /integration/OI40DBPurchaseOrderLine/doAutocomplete | doAutocomplete
[**doLookupPageOI40DBPurchaseOrderLine**](Oi40DbPurchaseOrderLineRepositoryApi.md#doLookupPageOI40DBPurchaseOrderLine) | **POST** /integration/OI40DBPurchaseOrderLine/doLookup | doLookup
[**findAllListOI40DBPurchaseOrderLine**](Oi40DbPurchaseOrderLineRepositoryApi.md#findAllListOI40DBPurchaseOrderLine) | **GET** /integration/OI40DBPurchaseOrderLine | findAll
[**findAllPageOI40DBPurchaseOrderLine**](Oi40DbPurchaseOrderLineRepositoryApi.md#findAllPageOI40DBPurchaseOrderLine) | **POST** /integration/OI40DBPurchaseOrderLine/findAllPaged | findAll
[**findByAfterIntegrationTsListOI40DBPurchaseOrderLine**](Oi40DbPurchaseOrderLineRepositoryApi.md#findByAfterIntegrationTsListOI40DBPurchaseOrderLine) | **GET** /integration/OI40DBPurchaseOrderLine/integratedAfter/{ts} | findByAfterIntegrationTs
[**findByAfterModifiedTimestampListOI40DBPurchaseOrderLine**](Oi40DbPurchaseOrderLineRepositoryApi.md#findByAfterModifiedTimestampListOI40DBPurchaseOrderLine) | **GET** /integration/OI40DBPurchaseOrderLine/modifiedAfter/{ts} | findByAfterModifiedTimestamp
[**findByCodeOI40DBPurchaseOrderLine**](Oi40DbPurchaseOrderLineRepositoryApi.md#findByCodeOI40DBPurchaseOrderLine) | **GET** /integration/OI40DBPurchaseOrderLine/byCode/{code} | findByCode
[**findByCodesListOI40DBPurchaseOrderLine**](Oi40DbPurchaseOrderLineRepositoryApi.md#findByCodesListOI40DBPurchaseOrderLine) | **POST** /integration/OI40DBPurchaseOrderLine/findByCodes | findByCodes
[**findByQbeListOI40DBPurchaseOrderLine**](Oi40DbPurchaseOrderLineRepositoryApi.md#findByQbeListOI40DBPurchaseOrderLine) | **POST** /integration/OI40DBPurchaseOrderLine/findByQbe | findByQbe
[**findByQbePagedPageOI40DBPurchaseOrderLine**](Oi40DbPurchaseOrderLineRepositoryApi.md#findByQbePagedPageOI40DBPurchaseOrderLine) | **POST** /integration/OI40DBPurchaseOrderLine/findByQbePaged | findByQbePaged
[**updateListOI40DBPurchaseOrderLine**](Oi40DbPurchaseOrderLineRepositoryApi.md#updateListOI40DBPurchaseOrderLine) | **POST** /integration/OI40DBPurchaseOrderLine/update | update
[**updateSingleOI40DBPurchaseOrderLine**](Oi40DbPurchaseOrderLineRepositoryApi.md#updateSingleOI40DBPurchaseOrderLine) | **POST** /integration/OI40DBPurchaseOrderLine/updateSingle | updateSingle


<a name="deleteByCodeVoid19"></a>
# **deleteByCodeVoid19**
> deleteByCodeVoid19(code)

deleteByCode

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbPurchaseOrderLineRepositoryApi;


Oi40DbPurchaseOrderLineRepositoryApi apiInstance = new Oi40DbPurchaseOrderLineRepositoryApi();
String code = "code_example"; // String | code
try {
    apiInstance.deleteByCodeVoid19(code);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbPurchaseOrderLineRepositoryApi#deleteByCodeVoid19");
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

<a name="deleteByCodesVoid19"></a>
# **deleteByCodesVoid19**
> deleteByCodesVoid19(codes)

deleteByCodes

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbPurchaseOrderLineRepositoryApi;


Oi40DbPurchaseOrderLineRepositoryApi apiInstance = new Oi40DbPurchaseOrderLineRepositoryApi();
List<String> codes = Arrays.asList(new List<String>()); // List<String> | codes
try {
    apiInstance.deleteByCodesVoid19(codes);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbPurchaseOrderLineRepositoryApi#deleteByCodesVoid19");
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

<a name="doAutocompletePageOI40DBPurchaseOrderLine"></a>
# **doAutocompletePageOI40DBPurchaseOrderLine**
> PageOI40DBPurchaseOrderLine doAutocompletePageOI40DBPurchaseOrderLine(autoCompleteData)

doAutocomplete

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbPurchaseOrderLineRepositoryApi;


Oi40DbPurchaseOrderLineRepositoryApi apiInstance = new Oi40DbPurchaseOrderLineRepositoryApi();
AutoCompleteData autoCompleteData = new AutoCompleteData(); // AutoCompleteData | autoCompleteData
try {
    PageOI40DBPurchaseOrderLine result = apiInstance.doAutocompletePageOI40DBPurchaseOrderLine(autoCompleteData);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbPurchaseOrderLineRepositoryApi#doAutocompletePageOI40DBPurchaseOrderLine");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **autoCompleteData** | [**AutoCompleteData**](AutoCompleteData.md)| autoCompleteData |

### Return type

[**PageOI40DBPurchaseOrderLine**](PageOI40DBPurchaseOrderLine.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: */*

<a name="doLookupPageOI40DBPurchaseOrderLine"></a>
# **doLookupPageOI40DBPurchaseOrderLine**
> PageOI40DBPurchaseOrderLine doLookupPageOI40DBPurchaseOrderLine(lookup)

doLookup

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbPurchaseOrderLineRepositoryApi;


Oi40DbPurchaseOrderLineRepositoryApi apiInstance = new Oi40DbPurchaseOrderLineRepositoryApi();
LookupData lookup = new LookupData(); // LookupData | lookup
try {
    PageOI40DBPurchaseOrderLine result = apiInstance.doLookupPageOI40DBPurchaseOrderLine(lookup);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbPurchaseOrderLineRepositoryApi#doLookupPageOI40DBPurchaseOrderLine");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **lookup** | [**LookupData**](LookupData.md)| lookup |

### Return type

[**PageOI40DBPurchaseOrderLine**](PageOI40DBPurchaseOrderLine.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: */*

<a name="findAllListOI40DBPurchaseOrderLine"></a>
# **findAllListOI40DBPurchaseOrderLine**
> List&lt;OI40DBPurchaseOrderLine&gt; findAllListOI40DBPurchaseOrderLine()

findAll

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbPurchaseOrderLineRepositoryApi;


Oi40DbPurchaseOrderLineRepositoryApi apiInstance = new Oi40DbPurchaseOrderLineRepositoryApi();
try {
    List<OI40DBPurchaseOrderLine> result = apiInstance.findAllListOI40DBPurchaseOrderLine();
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbPurchaseOrderLineRepositoryApi#findAllListOI40DBPurchaseOrderLine");
    e.printStackTrace();
}
```

### Parameters
This endpoint does not need any parameter.

### Return type

[**List&lt;OI40DBPurchaseOrderLine&gt;**](OI40DBPurchaseOrderLine.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="findAllPageOI40DBPurchaseOrderLine"></a>
# **findAllPageOI40DBPurchaseOrderLine**
> PageOI40DBPurchaseOrderLine findAllPageOI40DBPurchaseOrderLine(p)

findAll

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbPurchaseOrderLineRepositoryApi;


Oi40DbPurchaseOrderLineRepositoryApi apiInstance = new Oi40DbPurchaseOrderLineRepositoryApi();
PageInfo p = new PageInfo(); // PageInfo | p
try {
    PageOI40DBPurchaseOrderLine result = apiInstance.findAllPageOI40DBPurchaseOrderLine(p);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbPurchaseOrderLineRepositoryApi#findAllPageOI40DBPurchaseOrderLine");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **p** | [**PageInfo**](PageInfo.md)| p |

### Return type

[**PageOI40DBPurchaseOrderLine**](PageOI40DBPurchaseOrderLine.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="findByAfterIntegrationTsListOI40DBPurchaseOrderLine"></a>
# **findByAfterIntegrationTsListOI40DBPurchaseOrderLine**
> List&lt;OI40DBPurchaseOrderLine&gt; findByAfterIntegrationTsListOI40DBPurchaseOrderLine(ts)

findByAfterIntegrationTs

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbPurchaseOrderLineRepositoryApi;


Oi40DbPurchaseOrderLineRepositoryApi apiInstance = new Oi40DbPurchaseOrderLineRepositoryApi();
String ts = "ts_example"; // String | ts
try {
    List<OI40DBPurchaseOrderLine> result = apiInstance.findByAfterIntegrationTsListOI40DBPurchaseOrderLine(ts);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbPurchaseOrderLineRepositoryApi#findByAfterIntegrationTsListOI40DBPurchaseOrderLine");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **ts** | **String**| ts |

### Return type

[**List&lt;OI40DBPurchaseOrderLine&gt;**](OI40DBPurchaseOrderLine.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*

<a name="findByAfterModifiedTimestampListOI40DBPurchaseOrderLine"></a>
# **findByAfterModifiedTimestampListOI40DBPurchaseOrderLine**
> List&lt;OI40DBPurchaseOrderLine&gt; findByAfterModifiedTimestampListOI40DBPurchaseOrderLine(ts)

findByAfterModifiedTimestamp

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbPurchaseOrderLineRepositoryApi;


Oi40DbPurchaseOrderLineRepositoryApi apiInstance = new Oi40DbPurchaseOrderLineRepositoryApi();
String ts = "ts_example"; // String | ts
try {
    List<OI40DBPurchaseOrderLine> result = apiInstance.findByAfterModifiedTimestampListOI40DBPurchaseOrderLine(ts);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbPurchaseOrderLineRepositoryApi#findByAfterModifiedTimestampListOI40DBPurchaseOrderLine");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **ts** | **String**| ts |

### Return type

[**List&lt;OI40DBPurchaseOrderLine&gt;**](OI40DBPurchaseOrderLine.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*

<a name="findByCodeOI40DBPurchaseOrderLine"></a>
# **findByCodeOI40DBPurchaseOrderLine**
> OI40DBPurchaseOrderLine findByCodeOI40DBPurchaseOrderLine(code)

findByCode

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbPurchaseOrderLineRepositoryApi;


Oi40DbPurchaseOrderLineRepositoryApi apiInstance = new Oi40DbPurchaseOrderLineRepositoryApi();
String code = "code_example"; // String | code
try {
    OI40DBPurchaseOrderLine result = apiInstance.findByCodeOI40DBPurchaseOrderLine(code);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbPurchaseOrderLineRepositoryApi#findByCodeOI40DBPurchaseOrderLine");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **code** | **String**| code |

### Return type

[**OI40DBPurchaseOrderLine**](OI40DBPurchaseOrderLine.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="findByCodesListOI40DBPurchaseOrderLine"></a>
# **findByCodesListOI40DBPurchaseOrderLine**
> List&lt;OI40DBPurchaseOrderLine&gt; findByCodesListOI40DBPurchaseOrderLine(codes)

findByCodes

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbPurchaseOrderLineRepositoryApi;


Oi40DbPurchaseOrderLineRepositoryApi apiInstance = new Oi40DbPurchaseOrderLineRepositoryApi();
List<String> codes = Arrays.asList(new List<String>()); // List<String> | codes
try {
    List<OI40DBPurchaseOrderLine> result = apiInstance.findByCodesListOI40DBPurchaseOrderLine(codes);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbPurchaseOrderLineRepositoryApi#findByCodesListOI40DBPurchaseOrderLine");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **codes** | **List&lt;String&gt;**| codes |

### Return type

[**List&lt;OI40DBPurchaseOrderLine&gt;**](OI40DBPurchaseOrderLine.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="findByQbeListOI40DBPurchaseOrderLine"></a>
# **findByQbeListOI40DBPurchaseOrderLine**
> List&lt;OI40DBPurchaseOrderLine&gt; findByQbeListOI40DBPurchaseOrderLine(qbe)

findByQbe

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbPurchaseOrderLineRepositoryApi;


Oi40DbPurchaseOrderLineRepositoryApi apiInstance = new Oi40DbPurchaseOrderLineRepositoryApi();
OI40DBPurchaseOrderLine qbe = new OI40DBPurchaseOrderLine(); // OI40DBPurchaseOrderLine | qbe
try {
    List<OI40DBPurchaseOrderLine> result = apiInstance.findByQbeListOI40DBPurchaseOrderLine(qbe);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbPurchaseOrderLineRepositoryApi#findByQbeListOI40DBPurchaseOrderLine");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **qbe** | [**OI40DBPurchaseOrderLine**](OI40DBPurchaseOrderLine.md)| qbe |

### Return type

[**List&lt;OI40DBPurchaseOrderLine&gt;**](OI40DBPurchaseOrderLine.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="findByQbePagedPageOI40DBPurchaseOrderLine"></a>
# **findByQbePagedPageOI40DBPurchaseOrderLine**
> PageOI40DBPurchaseOrderLine findByQbePagedPageOI40DBPurchaseOrderLine(qbe)

findByQbePaged

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbPurchaseOrderLineRepositoryApi;


Oi40DbPurchaseOrderLineRepositoryApi apiInstance = new Oi40DbPurchaseOrderLineRepositoryApi();
QbeSupportOI40DBPurchaseOrderLine qbe = new QbeSupportOI40DBPurchaseOrderLine(); // QbeSupportOI40DBPurchaseOrderLine | qbe
try {
    PageOI40DBPurchaseOrderLine result = apiInstance.findByQbePagedPageOI40DBPurchaseOrderLine(qbe);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbPurchaseOrderLineRepositoryApi#findByQbePagedPageOI40DBPurchaseOrderLine");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **qbe** | [**QbeSupportOI40DBPurchaseOrderLine**](QbeSupportOI40DBPurchaseOrderLine.md)| qbe |

### Return type

[**PageOI40DBPurchaseOrderLine**](PageOI40DBPurchaseOrderLine.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="updateListOI40DBPurchaseOrderLine"></a>
# **updateListOI40DBPurchaseOrderLine**
> List&lt;OI40DBPurchaseOrderLine&gt; updateListOI40DBPurchaseOrderLine(data)

update

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbPurchaseOrderLineRepositoryApi;


Oi40DbPurchaseOrderLineRepositoryApi apiInstance = new Oi40DbPurchaseOrderLineRepositoryApi();
List<OI40DBPurchaseOrderLine> data = Arrays.asList(new OI40DBPurchaseOrderLine()); // List<OI40DBPurchaseOrderLine> | data
try {
    List<OI40DBPurchaseOrderLine> result = apiInstance.updateListOI40DBPurchaseOrderLine(data);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbPurchaseOrderLineRepositoryApi#updateListOI40DBPurchaseOrderLine");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **data** | [**List&lt;OI40DBPurchaseOrderLine&gt;**](OI40DBPurchaseOrderLine.md)| data |

### Return type

[**List&lt;OI40DBPurchaseOrderLine&gt;**](OI40DBPurchaseOrderLine.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="updateSingleOI40DBPurchaseOrderLine"></a>
# **updateSingleOI40DBPurchaseOrderLine**
> OI40DBPurchaseOrderLine updateSingleOI40DBPurchaseOrderLine(data)

updateSingle

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbPurchaseOrderLineRepositoryApi;


Oi40DbPurchaseOrderLineRepositoryApi apiInstance = new Oi40DbPurchaseOrderLineRepositoryApi();
OI40DBPurchaseOrderLine data = new OI40DBPurchaseOrderLine(); // OI40DBPurchaseOrderLine | data
try {
    OI40DBPurchaseOrderLine result = apiInstance.updateSingleOI40DBPurchaseOrderLine(data);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbPurchaseOrderLineRepositoryApi#updateSingleOI40DBPurchaseOrderLine");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **data** | [**OI40DBPurchaseOrderLine**](OI40DBPurchaseOrderLine.md)| data |

### Return type

[**OI40DBPurchaseOrderLine**](OI40DBPurchaseOrderLine.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

