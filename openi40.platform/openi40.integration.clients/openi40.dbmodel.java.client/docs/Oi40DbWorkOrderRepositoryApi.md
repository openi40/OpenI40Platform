# Oi40DbWorkOrderRepositoryApi

All URIs are relative to *https://localhost:8083/openi40-backoffice*

Method | HTTP request | Description
------------- | ------------- | -------------
[**deleteByCodeVoid40**](Oi40DbWorkOrderRepositoryApi.md#deleteByCodeVoid40) | **GET** /integration/OI40DBWorkOrder/deleteByCode/{code} | deleteByCode
[**deleteByCodesVoid40**](Oi40DbWorkOrderRepositoryApi.md#deleteByCodesVoid40) | **POST** /integration/OI40DBWorkOrder/deleteByCodes | deleteByCodes
[**doAutocompletePageOI40DBWorkOrder**](Oi40DbWorkOrderRepositoryApi.md#doAutocompletePageOI40DBWorkOrder) | **POST** /integration/OI40DBWorkOrder/doAutocomplete | doAutocomplete
[**doLookupPageOI40DBWorkOrder**](Oi40DbWorkOrderRepositoryApi.md#doLookupPageOI40DBWorkOrder) | **POST** /integration/OI40DBWorkOrder/doLookup | doLookup
[**findAllListOI40DBWorkOrder**](Oi40DbWorkOrderRepositoryApi.md#findAllListOI40DBWorkOrder) | **GET** /integration/OI40DBWorkOrder | findAll
[**findAllPageOI40DBWorkOrder**](Oi40DbWorkOrderRepositoryApi.md#findAllPageOI40DBWorkOrder) | **POST** /integration/OI40DBWorkOrder/findAllPaged | findAll
[**findByAfterIntegrationTsListOI40DBWorkOrder**](Oi40DbWorkOrderRepositoryApi.md#findByAfterIntegrationTsListOI40DBWorkOrder) | **GET** /integration/OI40DBWorkOrder/integratedAfter/{ts} | findByAfterIntegrationTs
[**findByAfterModifiedTimestampListOI40DBWorkOrder**](Oi40DbWorkOrderRepositoryApi.md#findByAfterModifiedTimestampListOI40DBWorkOrder) | **GET** /integration/OI40DBWorkOrder/modifiedAfter/{ts} | findByAfterModifiedTimestamp
[**findByCodeOI40DBWorkOrder**](Oi40DbWorkOrderRepositoryApi.md#findByCodeOI40DBWorkOrder) | **GET** /integration/OI40DBWorkOrder/byCode/{code} | findByCode
[**findByCodesListOI40DBWorkOrder**](Oi40DbWorkOrderRepositoryApi.md#findByCodesListOI40DBWorkOrder) | **POST** /integration/OI40DBWorkOrder/findByCodes | findByCodes
[**findByQbeListOI40DBWorkOrder**](Oi40DbWorkOrderRepositoryApi.md#findByQbeListOI40DBWorkOrder) | **POST** /integration/OI40DBWorkOrder/findByQbe | findByQbe
[**findByQbePagedPageOI40DBWorkOrder**](Oi40DbWorkOrderRepositoryApi.md#findByQbePagedPageOI40DBWorkOrder) | **POST** /integration/OI40DBWorkOrder/findByQbePaged | findByQbePaged
[**updateListOI40DBWorkOrder**](Oi40DbWorkOrderRepositoryApi.md#updateListOI40DBWorkOrder) | **POST** /integration/OI40DBWorkOrder/update | update
[**updateSingleOI40DBWorkOrder**](Oi40DbWorkOrderRepositoryApi.md#updateSingleOI40DBWorkOrder) | **POST** /integration/OI40DBWorkOrder/updateSingle | updateSingle


<a name="deleteByCodeVoid40"></a>
# **deleteByCodeVoid40**
> deleteByCodeVoid40(code)

deleteByCode

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbWorkOrderRepositoryApi;


Oi40DbWorkOrderRepositoryApi apiInstance = new Oi40DbWorkOrderRepositoryApi();
String code = "code_example"; // String | code
try {
    apiInstance.deleteByCodeVoid40(code);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbWorkOrderRepositoryApi#deleteByCodeVoid40");
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

<a name="deleteByCodesVoid40"></a>
# **deleteByCodesVoid40**
> deleteByCodesVoid40(codes)

deleteByCodes

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbWorkOrderRepositoryApi;


Oi40DbWorkOrderRepositoryApi apiInstance = new Oi40DbWorkOrderRepositoryApi();
List<String> codes = Arrays.asList(new List<String>()); // List<String> | codes
try {
    apiInstance.deleteByCodesVoid40(codes);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbWorkOrderRepositoryApi#deleteByCodesVoid40");
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

<a name="doAutocompletePageOI40DBWorkOrder"></a>
# **doAutocompletePageOI40DBWorkOrder**
> PageOI40DBWorkOrder doAutocompletePageOI40DBWorkOrder(autoCompleteData)

doAutocomplete

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbWorkOrderRepositoryApi;


Oi40DbWorkOrderRepositoryApi apiInstance = new Oi40DbWorkOrderRepositoryApi();
AutoCompleteData autoCompleteData = new AutoCompleteData(); // AutoCompleteData | autoCompleteData
try {
    PageOI40DBWorkOrder result = apiInstance.doAutocompletePageOI40DBWorkOrder(autoCompleteData);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbWorkOrderRepositoryApi#doAutocompletePageOI40DBWorkOrder");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **autoCompleteData** | [**AutoCompleteData**](AutoCompleteData.md)| autoCompleteData |

### Return type

[**PageOI40DBWorkOrder**](PageOI40DBWorkOrder.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: */*

<a name="doLookupPageOI40DBWorkOrder"></a>
# **doLookupPageOI40DBWorkOrder**
> PageOI40DBWorkOrder doLookupPageOI40DBWorkOrder(lookup)

doLookup

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbWorkOrderRepositoryApi;


Oi40DbWorkOrderRepositoryApi apiInstance = new Oi40DbWorkOrderRepositoryApi();
LookupData lookup = new LookupData(); // LookupData | lookup
try {
    PageOI40DBWorkOrder result = apiInstance.doLookupPageOI40DBWorkOrder(lookup);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbWorkOrderRepositoryApi#doLookupPageOI40DBWorkOrder");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **lookup** | [**LookupData**](LookupData.md)| lookup |

### Return type

[**PageOI40DBWorkOrder**](PageOI40DBWorkOrder.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: */*

<a name="findAllListOI40DBWorkOrder"></a>
# **findAllListOI40DBWorkOrder**
> List&lt;OI40DBWorkOrder&gt; findAllListOI40DBWorkOrder()

findAll

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbWorkOrderRepositoryApi;


Oi40DbWorkOrderRepositoryApi apiInstance = new Oi40DbWorkOrderRepositoryApi();
try {
    List<OI40DBWorkOrder> result = apiInstance.findAllListOI40DBWorkOrder();
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbWorkOrderRepositoryApi#findAllListOI40DBWorkOrder");
    e.printStackTrace();
}
```

### Parameters
This endpoint does not need any parameter.

### Return type

[**List&lt;OI40DBWorkOrder&gt;**](OI40DBWorkOrder.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="findAllPageOI40DBWorkOrder"></a>
# **findAllPageOI40DBWorkOrder**
> PageOI40DBWorkOrder findAllPageOI40DBWorkOrder(p)

findAll

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbWorkOrderRepositoryApi;


Oi40DbWorkOrderRepositoryApi apiInstance = new Oi40DbWorkOrderRepositoryApi();
PageInfo p = new PageInfo(); // PageInfo | p
try {
    PageOI40DBWorkOrder result = apiInstance.findAllPageOI40DBWorkOrder(p);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbWorkOrderRepositoryApi#findAllPageOI40DBWorkOrder");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **p** | [**PageInfo**](PageInfo.md)| p |

### Return type

[**PageOI40DBWorkOrder**](PageOI40DBWorkOrder.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="findByAfterIntegrationTsListOI40DBWorkOrder"></a>
# **findByAfterIntegrationTsListOI40DBWorkOrder**
> List&lt;OI40DBWorkOrder&gt; findByAfterIntegrationTsListOI40DBWorkOrder(ts)

findByAfterIntegrationTs

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbWorkOrderRepositoryApi;


Oi40DbWorkOrderRepositoryApi apiInstance = new Oi40DbWorkOrderRepositoryApi();
String ts = "ts_example"; // String | ts
try {
    List<OI40DBWorkOrder> result = apiInstance.findByAfterIntegrationTsListOI40DBWorkOrder(ts);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbWorkOrderRepositoryApi#findByAfterIntegrationTsListOI40DBWorkOrder");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **ts** | **String**| ts |

### Return type

[**List&lt;OI40DBWorkOrder&gt;**](OI40DBWorkOrder.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*

<a name="findByAfterModifiedTimestampListOI40DBWorkOrder"></a>
# **findByAfterModifiedTimestampListOI40DBWorkOrder**
> List&lt;OI40DBWorkOrder&gt; findByAfterModifiedTimestampListOI40DBWorkOrder(ts)

findByAfterModifiedTimestamp

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbWorkOrderRepositoryApi;


Oi40DbWorkOrderRepositoryApi apiInstance = new Oi40DbWorkOrderRepositoryApi();
String ts = "ts_example"; // String | ts
try {
    List<OI40DBWorkOrder> result = apiInstance.findByAfterModifiedTimestampListOI40DBWorkOrder(ts);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbWorkOrderRepositoryApi#findByAfterModifiedTimestampListOI40DBWorkOrder");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **ts** | **String**| ts |

### Return type

[**List&lt;OI40DBWorkOrder&gt;**](OI40DBWorkOrder.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*

<a name="findByCodeOI40DBWorkOrder"></a>
# **findByCodeOI40DBWorkOrder**
> OI40DBWorkOrder findByCodeOI40DBWorkOrder(code)

findByCode

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbWorkOrderRepositoryApi;


Oi40DbWorkOrderRepositoryApi apiInstance = new Oi40DbWorkOrderRepositoryApi();
String code = "code_example"; // String | code
try {
    OI40DBWorkOrder result = apiInstance.findByCodeOI40DBWorkOrder(code);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbWorkOrderRepositoryApi#findByCodeOI40DBWorkOrder");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **code** | **String**| code |

### Return type

[**OI40DBWorkOrder**](OI40DBWorkOrder.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="findByCodesListOI40DBWorkOrder"></a>
# **findByCodesListOI40DBWorkOrder**
> List&lt;OI40DBWorkOrder&gt; findByCodesListOI40DBWorkOrder(codes)

findByCodes

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbWorkOrderRepositoryApi;


Oi40DbWorkOrderRepositoryApi apiInstance = new Oi40DbWorkOrderRepositoryApi();
List<String> codes = Arrays.asList(new List<String>()); // List<String> | codes
try {
    List<OI40DBWorkOrder> result = apiInstance.findByCodesListOI40DBWorkOrder(codes);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbWorkOrderRepositoryApi#findByCodesListOI40DBWorkOrder");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **codes** | **List&lt;String&gt;**| codes |

### Return type

[**List&lt;OI40DBWorkOrder&gt;**](OI40DBWorkOrder.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="findByQbeListOI40DBWorkOrder"></a>
# **findByQbeListOI40DBWorkOrder**
> List&lt;OI40DBWorkOrder&gt; findByQbeListOI40DBWorkOrder(qbe)

findByQbe

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbWorkOrderRepositoryApi;


Oi40DbWorkOrderRepositoryApi apiInstance = new Oi40DbWorkOrderRepositoryApi();
OI40DBWorkOrder qbe = new OI40DBWorkOrder(); // OI40DBWorkOrder | qbe
try {
    List<OI40DBWorkOrder> result = apiInstance.findByQbeListOI40DBWorkOrder(qbe);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbWorkOrderRepositoryApi#findByQbeListOI40DBWorkOrder");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **qbe** | [**OI40DBWorkOrder**](OI40DBWorkOrder.md)| qbe |

### Return type

[**List&lt;OI40DBWorkOrder&gt;**](OI40DBWorkOrder.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="findByQbePagedPageOI40DBWorkOrder"></a>
# **findByQbePagedPageOI40DBWorkOrder**
> PageOI40DBWorkOrder findByQbePagedPageOI40DBWorkOrder(qbe)

findByQbePaged

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbWorkOrderRepositoryApi;


Oi40DbWorkOrderRepositoryApi apiInstance = new Oi40DbWorkOrderRepositoryApi();
QbeSupportOI40DBWorkOrder qbe = new QbeSupportOI40DBWorkOrder(); // QbeSupportOI40DBWorkOrder | qbe
try {
    PageOI40DBWorkOrder result = apiInstance.findByQbePagedPageOI40DBWorkOrder(qbe);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbWorkOrderRepositoryApi#findByQbePagedPageOI40DBWorkOrder");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **qbe** | [**QbeSupportOI40DBWorkOrder**](QbeSupportOI40DBWorkOrder.md)| qbe |

### Return type

[**PageOI40DBWorkOrder**](PageOI40DBWorkOrder.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="updateListOI40DBWorkOrder"></a>
# **updateListOI40DBWorkOrder**
> List&lt;OI40DBWorkOrder&gt; updateListOI40DBWorkOrder(data)

update

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbWorkOrderRepositoryApi;


Oi40DbWorkOrderRepositoryApi apiInstance = new Oi40DbWorkOrderRepositoryApi();
List<OI40DBWorkOrder> data = Arrays.asList(new OI40DBWorkOrder()); // List<OI40DBWorkOrder> | data
try {
    List<OI40DBWorkOrder> result = apiInstance.updateListOI40DBWorkOrder(data);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbWorkOrderRepositoryApi#updateListOI40DBWorkOrder");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **data** | [**List&lt;OI40DBWorkOrder&gt;**](OI40DBWorkOrder.md)| data |

### Return type

[**List&lt;OI40DBWorkOrder&gt;**](OI40DBWorkOrder.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="updateSingleOI40DBWorkOrder"></a>
# **updateSingleOI40DBWorkOrder**
> OI40DBWorkOrder updateSingleOI40DBWorkOrder(data)

updateSingle

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbWorkOrderRepositoryApi;


Oi40DbWorkOrderRepositoryApi apiInstance = new Oi40DbWorkOrderRepositoryApi();
OI40DBWorkOrder data = new OI40DBWorkOrder(); // OI40DBWorkOrder | data
try {
    OI40DBWorkOrder result = apiInstance.updateSingleOI40DBWorkOrder(data);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbWorkOrderRepositoryApi#updateSingleOI40DBWorkOrder");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **data** | [**OI40DBWorkOrder**](OI40DBWorkOrder.md)| data |

### Return type

[**OI40DBWorkOrder**](OI40DBWorkOrder.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

