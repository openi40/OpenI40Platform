# Oi40DbScheduledWorkOrderRepositoryApi

All URIs are relative to *https://localhost:8083/openi40-backoffice*

Method | HTTP request | Description
------------- | ------------- | -------------
[**deleteByCodeVoid24**](Oi40DbScheduledWorkOrderRepositoryApi.md#deleteByCodeVoid24) | **GET** /integration/OI40DBScheduledWorkOrder/deleteByCode/{code} | deleteByCode
[**deleteByCodesVoid24**](Oi40DbScheduledWorkOrderRepositoryApi.md#deleteByCodesVoid24) | **POST** /integration/OI40DBScheduledWorkOrder/deleteByCodes | deleteByCodes
[**doAutocompletePageOI40DBScheduledWorkOrder**](Oi40DbScheduledWorkOrderRepositoryApi.md#doAutocompletePageOI40DBScheduledWorkOrder) | **POST** /integration/OI40DBScheduledWorkOrder/doAutocomplete | doAutocomplete
[**doLookupPageOI40DBScheduledWorkOrder**](Oi40DbScheduledWorkOrderRepositoryApi.md#doLookupPageOI40DBScheduledWorkOrder) | **POST** /integration/OI40DBScheduledWorkOrder/doLookup | doLookup
[**findAllListOI40DBScheduledWorkOrder**](Oi40DbScheduledWorkOrderRepositoryApi.md#findAllListOI40DBScheduledWorkOrder) | **GET** /integration/OI40DBScheduledWorkOrder | findAll
[**findAllPageOI40DBScheduledWorkOrder**](Oi40DbScheduledWorkOrderRepositoryApi.md#findAllPageOI40DBScheduledWorkOrder) | **POST** /integration/OI40DBScheduledWorkOrder/findAllPaged | findAll
[**findByAfterIntegrationTsListOI40DBScheduledWorkOrder**](Oi40DbScheduledWorkOrderRepositoryApi.md#findByAfterIntegrationTsListOI40DBScheduledWorkOrder) | **GET** /integration/OI40DBScheduledWorkOrder/integratedAfter/{ts} | findByAfterIntegrationTs
[**findByAfterModifiedTimestampListOI40DBScheduledWorkOrder**](Oi40DbScheduledWorkOrderRepositoryApi.md#findByAfterModifiedTimestampListOI40DBScheduledWorkOrder) | **GET** /integration/OI40DBScheduledWorkOrder/modifiedAfter/{ts} | findByAfterModifiedTimestamp
[**findByCodeOI40DBScheduledWorkOrder**](Oi40DbScheduledWorkOrderRepositoryApi.md#findByCodeOI40DBScheduledWorkOrder) | **GET** /integration/OI40DBScheduledWorkOrder/byCode/{code} | findByCode
[**findByCodesListOI40DBScheduledWorkOrder**](Oi40DbScheduledWorkOrderRepositoryApi.md#findByCodesListOI40DBScheduledWorkOrder) | **POST** /integration/OI40DBScheduledWorkOrder/findByCodes | findByCodes
[**findByQbeListOI40DBScheduledWorkOrder**](Oi40DbScheduledWorkOrderRepositoryApi.md#findByQbeListOI40DBScheduledWorkOrder) | **POST** /integration/OI40DBScheduledWorkOrder/findByQbe | findByQbe
[**findByQbePagedPageOI40DBScheduledWorkOrder**](Oi40DbScheduledWorkOrderRepositoryApi.md#findByQbePagedPageOI40DBScheduledWorkOrder) | **POST** /integration/OI40DBScheduledWorkOrder/findByQbePaged | findByQbePaged
[**updateListOI40DBScheduledWorkOrder**](Oi40DbScheduledWorkOrderRepositoryApi.md#updateListOI40DBScheduledWorkOrder) | **POST** /integration/OI40DBScheduledWorkOrder/update | update
[**updateSingleOI40DBScheduledWorkOrder**](Oi40DbScheduledWorkOrderRepositoryApi.md#updateSingleOI40DBScheduledWorkOrder) | **POST** /integration/OI40DBScheduledWorkOrder/updateSingle | updateSingle


<a name="deleteByCodeVoid24"></a>
# **deleteByCodeVoid24**
> deleteByCodeVoid24(code)

deleteByCode

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbScheduledWorkOrderRepositoryApi;


Oi40DbScheduledWorkOrderRepositoryApi apiInstance = new Oi40DbScheduledWorkOrderRepositoryApi();
String code = "code_example"; // String | code
try {
    apiInstance.deleteByCodeVoid24(code);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbScheduledWorkOrderRepositoryApi#deleteByCodeVoid24");
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

<a name="deleteByCodesVoid24"></a>
# **deleteByCodesVoid24**
> deleteByCodesVoid24(codes)

deleteByCodes

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbScheduledWorkOrderRepositoryApi;


Oi40DbScheduledWorkOrderRepositoryApi apiInstance = new Oi40DbScheduledWorkOrderRepositoryApi();
List<String> codes = Arrays.asList(new List<String>()); // List<String> | codes
try {
    apiInstance.deleteByCodesVoid24(codes);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbScheduledWorkOrderRepositoryApi#deleteByCodesVoid24");
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

<a name="doAutocompletePageOI40DBScheduledWorkOrder"></a>
# **doAutocompletePageOI40DBScheduledWorkOrder**
> PageOI40DBScheduledWorkOrder doAutocompletePageOI40DBScheduledWorkOrder(autoCompleteData)

doAutocomplete

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbScheduledWorkOrderRepositoryApi;


Oi40DbScheduledWorkOrderRepositoryApi apiInstance = new Oi40DbScheduledWorkOrderRepositoryApi();
AutoCompleteData autoCompleteData = new AutoCompleteData(); // AutoCompleteData | autoCompleteData
try {
    PageOI40DBScheduledWorkOrder result = apiInstance.doAutocompletePageOI40DBScheduledWorkOrder(autoCompleteData);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbScheduledWorkOrderRepositoryApi#doAutocompletePageOI40DBScheduledWorkOrder");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **autoCompleteData** | [**AutoCompleteData**](AutoCompleteData.md)| autoCompleteData |

### Return type

[**PageOI40DBScheduledWorkOrder**](PageOI40DBScheduledWorkOrder.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: */*

<a name="doLookupPageOI40DBScheduledWorkOrder"></a>
# **doLookupPageOI40DBScheduledWorkOrder**
> PageOI40DBScheduledWorkOrder doLookupPageOI40DBScheduledWorkOrder(lookup)

doLookup

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbScheduledWorkOrderRepositoryApi;


Oi40DbScheduledWorkOrderRepositoryApi apiInstance = new Oi40DbScheduledWorkOrderRepositoryApi();
LookupData lookup = new LookupData(); // LookupData | lookup
try {
    PageOI40DBScheduledWorkOrder result = apiInstance.doLookupPageOI40DBScheduledWorkOrder(lookup);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbScheduledWorkOrderRepositoryApi#doLookupPageOI40DBScheduledWorkOrder");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **lookup** | [**LookupData**](LookupData.md)| lookup |

### Return type

[**PageOI40DBScheduledWorkOrder**](PageOI40DBScheduledWorkOrder.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: */*

<a name="findAllListOI40DBScheduledWorkOrder"></a>
# **findAllListOI40DBScheduledWorkOrder**
> List&lt;OI40DBScheduledWorkOrder&gt; findAllListOI40DBScheduledWorkOrder()

findAll

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbScheduledWorkOrderRepositoryApi;


Oi40DbScheduledWorkOrderRepositoryApi apiInstance = new Oi40DbScheduledWorkOrderRepositoryApi();
try {
    List<OI40DBScheduledWorkOrder> result = apiInstance.findAllListOI40DBScheduledWorkOrder();
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbScheduledWorkOrderRepositoryApi#findAllListOI40DBScheduledWorkOrder");
    e.printStackTrace();
}
```

### Parameters
This endpoint does not need any parameter.

### Return type

[**List&lt;OI40DBScheduledWorkOrder&gt;**](OI40DBScheduledWorkOrder.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="findAllPageOI40DBScheduledWorkOrder"></a>
# **findAllPageOI40DBScheduledWorkOrder**
> PageOI40DBScheduledWorkOrder findAllPageOI40DBScheduledWorkOrder(p)

findAll

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbScheduledWorkOrderRepositoryApi;


Oi40DbScheduledWorkOrderRepositoryApi apiInstance = new Oi40DbScheduledWorkOrderRepositoryApi();
PageInfo p = new PageInfo(); // PageInfo | p
try {
    PageOI40DBScheduledWorkOrder result = apiInstance.findAllPageOI40DBScheduledWorkOrder(p);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbScheduledWorkOrderRepositoryApi#findAllPageOI40DBScheduledWorkOrder");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **p** | [**PageInfo**](PageInfo.md)| p |

### Return type

[**PageOI40DBScheduledWorkOrder**](PageOI40DBScheduledWorkOrder.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="findByAfterIntegrationTsListOI40DBScheduledWorkOrder"></a>
# **findByAfterIntegrationTsListOI40DBScheduledWorkOrder**
> List&lt;OI40DBScheduledWorkOrder&gt; findByAfterIntegrationTsListOI40DBScheduledWorkOrder(ts)

findByAfterIntegrationTs

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbScheduledWorkOrderRepositoryApi;


Oi40DbScheduledWorkOrderRepositoryApi apiInstance = new Oi40DbScheduledWorkOrderRepositoryApi();
String ts = "ts_example"; // String | ts
try {
    List<OI40DBScheduledWorkOrder> result = apiInstance.findByAfterIntegrationTsListOI40DBScheduledWorkOrder(ts);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbScheduledWorkOrderRepositoryApi#findByAfterIntegrationTsListOI40DBScheduledWorkOrder");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **ts** | **String**| ts |

### Return type

[**List&lt;OI40DBScheduledWorkOrder&gt;**](OI40DBScheduledWorkOrder.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*

<a name="findByAfterModifiedTimestampListOI40DBScheduledWorkOrder"></a>
# **findByAfterModifiedTimestampListOI40DBScheduledWorkOrder**
> List&lt;OI40DBScheduledWorkOrder&gt; findByAfterModifiedTimestampListOI40DBScheduledWorkOrder(ts)

findByAfterModifiedTimestamp

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbScheduledWorkOrderRepositoryApi;


Oi40DbScheduledWorkOrderRepositoryApi apiInstance = new Oi40DbScheduledWorkOrderRepositoryApi();
String ts = "ts_example"; // String | ts
try {
    List<OI40DBScheduledWorkOrder> result = apiInstance.findByAfterModifiedTimestampListOI40DBScheduledWorkOrder(ts);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbScheduledWorkOrderRepositoryApi#findByAfterModifiedTimestampListOI40DBScheduledWorkOrder");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **ts** | **String**| ts |

### Return type

[**List&lt;OI40DBScheduledWorkOrder&gt;**](OI40DBScheduledWorkOrder.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*

<a name="findByCodeOI40DBScheduledWorkOrder"></a>
# **findByCodeOI40DBScheduledWorkOrder**
> OI40DBScheduledWorkOrder findByCodeOI40DBScheduledWorkOrder(code)

findByCode

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbScheduledWorkOrderRepositoryApi;


Oi40DbScheduledWorkOrderRepositoryApi apiInstance = new Oi40DbScheduledWorkOrderRepositoryApi();
String code = "code_example"; // String | code
try {
    OI40DBScheduledWorkOrder result = apiInstance.findByCodeOI40DBScheduledWorkOrder(code);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbScheduledWorkOrderRepositoryApi#findByCodeOI40DBScheduledWorkOrder");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **code** | **String**| code |

### Return type

[**OI40DBScheduledWorkOrder**](OI40DBScheduledWorkOrder.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="findByCodesListOI40DBScheduledWorkOrder"></a>
# **findByCodesListOI40DBScheduledWorkOrder**
> List&lt;OI40DBScheduledWorkOrder&gt; findByCodesListOI40DBScheduledWorkOrder(codes)

findByCodes

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbScheduledWorkOrderRepositoryApi;


Oi40DbScheduledWorkOrderRepositoryApi apiInstance = new Oi40DbScheduledWorkOrderRepositoryApi();
List<String> codes = Arrays.asList(new List<String>()); // List<String> | codes
try {
    List<OI40DBScheduledWorkOrder> result = apiInstance.findByCodesListOI40DBScheduledWorkOrder(codes);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbScheduledWorkOrderRepositoryApi#findByCodesListOI40DBScheduledWorkOrder");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **codes** | **List&lt;String&gt;**| codes |

### Return type

[**List&lt;OI40DBScheduledWorkOrder&gt;**](OI40DBScheduledWorkOrder.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="findByQbeListOI40DBScheduledWorkOrder"></a>
# **findByQbeListOI40DBScheduledWorkOrder**
> List&lt;OI40DBScheduledWorkOrder&gt; findByQbeListOI40DBScheduledWorkOrder(qbe)

findByQbe

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbScheduledWorkOrderRepositoryApi;


Oi40DbScheduledWorkOrderRepositoryApi apiInstance = new Oi40DbScheduledWorkOrderRepositoryApi();
OI40DBScheduledWorkOrder qbe = new OI40DBScheduledWorkOrder(); // OI40DBScheduledWorkOrder | qbe
try {
    List<OI40DBScheduledWorkOrder> result = apiInstance.findByQbeListOI40DBScheduledWorkOrder(qbe);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbScheduledWorkOrderRepositoryApi#findByQbeListOI40DBScheduledWorkOrder");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **qbe** | [**OI40DBScheduledWorkOrder**](OI40DBScheduledWorkOrder.md)| qbe |

### Return type

[**List&lt;OI40DBScheduledWorkOrder&gt;**](OI40DBScheduledWorkOrder.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="findByQbePagedPageOI40DBScheduledWorkOrder"></a>
# **findByQbePagedPageOI40DBScheduledWorkOrder**
> PageOI40DBScheduledWorkOrder findByQbePagedPageOI40DBScheduledWorkOrder(qbe)

findByQbePaged

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbScheduledWorkOrderRepositoryApi;


Oi40DbScheduledWorkOrderRepositoryApi apiInstance = new Oi40DbScheduledWorkOrderRepositoryApi();
QbeSupportOI40DBScheduledWorkOrder qbe = new QbeSupportOI40DBScheduledWorkOrder(); // QbeSupportOI40DBScheduledWorkOrder | qbe
try {
    PageOI40DBScheduledWorkOrder result = apiInstance.findByQbePagedPageOI40DBScheduledWorkOrder(qbe);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbScheduledWorkOrderRepositoryApi#findByQbePagedPageOI40DBScheduledWorkOrder");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **qbe** | [**QbeSupportOI40DBScheduledWorkOrder**](QbeSupportOI40DBScheduledWorkOrder.md)| qbe |

### Return type

[**PageOI40DBScheduledWorkOrder**](PageOI40DBScheduledWorkOrder.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="updateListOI40DBScheduledWorkOrder"></a>
# **updateListOI40DBScheduledWorkOrder**
> List&lt;OI40DBScheduledWorkOrder&gt; updateListOI40DBScheduledWorkOrder(data)

update

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbScheduledWorkOrderRepositoryApi;


Oi40DbScheduledWorkOrderRepositoryApi apiInstance = new Oi40DbScheduledWorkOrderRepositoryApi();
List<OI40DBScheduledWorkOrder> data = Arrays.asList(new OI40DBScheduledWorkOrder()); // List<OI40DBScheduledWorkOrder> | data
try {
    List<OI40DBScheduledWorkOrder> result = apiInstance.updateListOI40DBScheduledWorkOrder(data);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbScheduledWorkOrderRepositoryApi#updateListOI40DBScheduledWorkOrder");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **data** | [**List&lt;OI40DBScheduledWorkOrder&gt;**](OI40DBScheduledWorkOrder.md)| data |

### Return type

[**List&lt;OI40DBScheduledWorkOrder&gt;**](OI40DBScheduledWorkOrder.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="updateSingleOI40DBScheduledWorkOrder"></a>
# **updateSingleOI40DBScheduledWorkOrder**
> OI40DBScheduledWorkOrder updateSingleOI40DBScheduledWorkOrder(data)

updateSingle

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbScheduledWorkOrderRepositoryApi;


Oi40DbScheduledWorkOrderRepositoryApi apiInstance = new Oi40DbScheduledWorkOrderRepositoryApi();
OI40DBScheduledWorkOrder data = new OI40DBScheduledWorkOrder(); // OI40DBScheduledWorkOrder | data
try {
    OI40DBScheduledWorkOrder result = apiInstance.updateSingleOI40DBScheduledWorkOrder(data);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbScheduledWorkOrderRepositoryApi#updateSingleOI40DBScheduledWorkOrder");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **data** | [**OI40DBScheduledWorkOrder**](OI40DBScheduledWorkOrder.md)| data |

### Return type

[**OI40DBScheduledWorkOrder**](OI40DBScheduledWorkOrder.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

