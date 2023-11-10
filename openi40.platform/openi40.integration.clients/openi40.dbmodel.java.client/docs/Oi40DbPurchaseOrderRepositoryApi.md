# Oi40DbPurchaseOrderRepositoryApi

All URIs are relative to *https://localhost:8082/openi40-integration*

Method | HTTP request | Description
------------- | ------------- | -------------
[**deleteByCodeVoid20**](Oi40DbPurchaseOrderRepositoryApi.md#deleteByCodeVoid20) | **GET** /integration/OI40DBPurchaseOrder/deleteByCode/{code} | deleteByCode
[**deleteByCodesVoid20**](Oi40DbPurchaseOrderRepositoryApi.md#deleteByCodesVoid20) | **POST** /integration/OI40DBPurchaseOrder/deleteByCodes | deleteByCodes
[**doAutocompletePageOI40DBPurchaseOrder**](Oi40DbPurchaseOrderRepositoryApi.md#doAutocompletePageOI40DBPurchaseOrder) | **POST** /integration/OI40DBPurchaseOrder/doAutocomplete | doAutocomplete
[**doLookupPageOI40DBPurchaseOrder**](Oi40DbPurchaseOrderRepositoryApi.md#doLookupPageOI40DBPurchaseOrder) | **POST** /integration/OI40DBPurchaseOrder/doLookup | doLookup
[**findAllListOI40DBPurchaseOrder**](Oi40DbPurchaseOrderRepositoryApi.md#findAllListOI40DBPurchaseOrder) | **GET** /integration/OI40DBPurchaseOrder | findAll
[**findAllPageOI40DBPurchaseOrder**](Oi40DbPurchaseOrderRepositoryApi.md#findAllPageOI40DBPurchaseOrder) | **POST** /integration/OI40DBPurchaseOrder/findAllPaged | findAll
[**findByAfterIntegrationTsListOI40DBPurchaseOrder**](Oi40DbPurchaseOrderRepositoryApi.md#findByAfterIntegrationTsListOI40DBPurchaseOrder) | **GET** /integration/OI40DBPurchaseOrder/integratedAfter/{ts} | findByAfterIntegrationTs
[**findByAfterModifiedTimestampListOI40DBPurchaseOrder**](Oi40DbPurchaseOrderRepositoryApi.md#findByAfterModifiedTimestampListOI40DBPurchaseOrder) | **GET** /integration/OI40DBPurchaseOrder/modifiedAfter/{ts} | findByAfterModifiedTimestamp
[**findByCodeOI40DBPurchaseOrder**](Oi40DbPurchaseOrderRepositoryApi.md#findByCodeOI40DBPurchaseOrder) | **GET** /integration/OI40DBPurchaseOrder/byCode/{code} | findByCode
[**findByCodesListOI40DBPurchaseOrder**](Oi40DbPurchaseOrderRepositoryApi.md#findByCodesListOI40DBPurchaseOrder) | **POST** /integration/OI40DBPurchaseOrder/findByCodes | findByCodes
[**findByQbeListOI40DBPurchaseOrder**](Oi40DbPurchaseOrderRepositoryApi.md#findByQbeListOI40DBPurchaseOrder) | **POST** /integration/OI40DBPurchaseOrder/findByQbe | findByQbe
[**findByQbePagedPageOI40DBPurchaseOrder**](Oi40DbPurchaseOrderRepositoryApi.md#findByQbePagedPageOI40DBPurchaseOrder) | **POST** /integration/OI40DBPurchaseOrder/findByQbePaged | findByQbePaged
[**updateListOI40DBPurchaseOrder**](Oi40DbPurchaseOrderRepositoryApi.md#updateListOI40DBPurchaseOrder) | **POST** /integration/OI40DBPurchaseOrder/update | update
[**updateSingleOI40DBPurchaseOrder**](Oi40DbPurchaseOrderRepositoryApi.md#updateSingleOI40DBPurchaseOrder) | **POST** /integration/OI40DBPurchaseOrder/updateSingle | updateSingle


<a name="deleteByCodeVoid20"></a>
# **deleteByCodeVoid20**
> deleteByCodeVoid20(code)

deleteByCode

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbPurchaseOrderRepositoryApi;


Oi40DbPurchaseOrderRepositoryApi apiInstance = new Oi40DbPurchaseOrderRepositoryApi();
String code = "code_example"; // String | code
try {
    apiInstance.deleteByCodeVoid20(code);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbPurchaseOrderRepositoryApi#deleteByCodeVoid20");
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

<a name="deleteByCodesVoid20"></a>
# **deleteByCodesVoid20**
> deleteByCodesVoid20(codes)

deleteByCodes

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbPurchaseOrderRepositoryApi;


Oi40DbPurchaseOrderRepositoryApi apiInstance = new Oi40DbPurchaseOrderRepositoryApi();
List<String> codes = Arrays.asList(new List<String>()); // List<String> | codes
try {
    apiInstance.deleteByCodesVoid20(codes);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbPurchaseOrderRepositoryApi#deleteByCodesVoid20");
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

<a name="doAutocompletePageOI40DBPurchaseOrder"></a>
# **doAutocompletePageOI40DBPurchaseOrder**
> PageOI40DBPurchaseOrder doAutocompletePageOI40DBPurchaseOrder(autoCompleteData)

doAutocomplete

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbPurchaseOrderRepositoryApi;


Oi40DbPurchaseOrderRepositoryApi apiInstance = new Oi40DbPurchaseOrderRepositoryApi();
AutoCompleteData autoCompleteData = new AutoCompleteData(); // AutoCompleteData | autoCompleteData
try {
    PageOI40DBPurchaseOrder result = apiInstance.doAutocompletePageOI40DBPurchaseOrder(autoCompleteData);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbPurchaseOrderRepositoryApi#doAutocompletePageOI40DBPurchaseOrder");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **autoCompleteData** | [**AutoCompleteData**](AutoCompleteData.md)| autoCompleteData |

### Return type

[**PageOI40DBPurchaseOrder**](PageOI40DBPurchaseOrder.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: */*

<a name="doLookupPageOI40DBPurchaseOrder"></a>
# **doLookupPageOI40DBPurchaseOrder**
> PageOI40DBPurchaseOrder doLookupPageOI40DBPurchaseOrder(lookup)

doLookup

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbPurchaseOrderRepositoryApi;


Oi40DbPurchaseOrderRepositoryApi apiInstance = new Oi40DbPurchaseOrderRepositoryApi();
LookupData lookup = new LookupData(); // LookupData | lookup
try {
    PageOI40DBPurchaseOrder result = apiInstance.doLookupPageOI40DBPurchaseOrder(lookup);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbPurchaseOrderRepositoryApi#doLookupPageOI40DBPurchaseOrder");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **lookup** | [**LookupData**](LookupData.md)| lookup |

### Return type

[**PageOI40DBPurchaseOrder**](PageOI40DBPurchaseOrder.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: */*

<a name="findAllListOI40DBPurchaseOrder"></a>
# **findAllListOI40DBPurchaseOrder**
> List&lt;OI40DBPurchaseOrder&gt; findAllListOI40DBPurchaseOrder()

findAll

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbPurchaseOrderRepositoryApi;


Oi40DbPurchaseOrderRepositoryApi apiInstance = new Oi40DbPurchaseOrderRepositoryApi();
try {
    List<OI40DBPurchaseOrder> result = apiInstance.findAllListOI40DBPurchaseOrder();
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbPurchaseOrderRepositoryApi#findAllListOI40DBPurchaseOrder");
    e.printStackTrace();
}
```

### Parameters
This endpoint does not need any parameter.

### Return type

[**List&lt;OI40DBPurchaseOrder&gt;**](OI40DBPurchaseOrder.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="findAllPageOI40DBPurchaseOrder"></a>
# **findAllPageOI40DBPurchaseOrder**
> PageOI40DBPurchaseOrder findAllPageOI40DBPurchaseOrder(p)

findAll

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbPurchaseOrderRepositoryApi;


Oi40DbPurchaseOrderRepositoryApi apiInstance = new Oi40DbPurchaseOrderRepositoryApi();
PageInfo p = new PageInfo(); // PageInfo | p
try {
    PageOI40DBPurchaseOrder result = apiInstance.findAllPageOI40DBPurchaseOrder(p);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbPurchaseOrderRepositoryApi#findAllPageOI40DBPurchaseOrder");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **p** | [**PageInfo**](PageInfo.md)| p |

### Return type

[**PageOI40DBPurchaseOrder**](PageOI40DBPurchaseOrder.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="findByAfterIntegrationTsListOI40DBPurchaseOrder"></a>
# **findByAfterIntegrationTsListOI40DBPurchaseOrder**
> List&lt;OI40DBPurchaseOrder&gt; findByAfterIntegrationTsListOI40DBPurchaseOrder(ts)

findByAfterIntegrationTs

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbPurchaseOrderRepositoryApi;


Oi40DbPurchaseOrderRepositoryApi apiInstance = new Oi40DbPurchaseOrderRepositoryApi();
String ts = "ts_example"; // String | ts
try {
    List<OI40DBPurchaseOrder> result = apiInstance.findByAfterIntegrationTsListOI40DBPurchaseOrder(ts);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbPurchaseOrderRepositoryApi#findByAfterIntegrationTsListOI40DBPurchaseOrder");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **ts** | **String**| ts |

### Return type

[**List&lt;OI40DBPurchaseOrder&gt;**](OI40DBPurchaseOrder.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*

<a name="findByAfterModifiedTimestampListOI40DBPurchaseOrder"></a>
# **findByAfterModifiedTimestampListOI40DBPurchaseOrder**
> List&lt;OI40DBPurchaseOrder&gt; findByAfterModifiedTimestampListOI40DBPurchaseOrder(ts)

findByAfterModifiedTimestamp

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbPurchaseOrderRepositoryApi;


Oi40DbPurchaseOrderRepositoryApi apiInstance = new Oi40DbPurchaseOrderRepositoryApi();
String ts = "ts_example"; // String | ts
try {
    List<OI40DBPurchaseOrder> result = apiInstance.findByAfterModifiedTimestampListOI40DBPurchaseOrder(ts);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbPurchaseOrderRepositoryApi#findByAfterModifiedTimestampListOI40DBPurchaseOrder");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **ts** | **String**| ts |

### Return type

[**List&lt;OI40DBPurchaseOrder&gt;**](OI40DBPurchaseOrder.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*

<a name="findByCodeOI40DBPurchaseOrder"></a>
# **findByCodeOI40DBPurchaseOrder**
> OI40DBPurchaseOrder findByCodeOI40DBPurchaseOrder(code)

findByCode

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbPurchaseOrderRepositoryApi;


Oi40DbPurchaseOrderRepositoryApi apiInstance = new Oi40DbPurchaseOrderRepositoryApi();
String code = "code_example"; // String | code
try {
    OI40DBPurchaseOrder result = apiInstance.findByCodeOI40DBPurchaseOrder(code);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbPurchaseOrderRepositoryApi#findByCodeOI40DBPurchaseOrder");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **code** | **String**| code |

### Return type

[**OI40DBPurchaseOrder**](OI40DBPurchaseOrder.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="findByCodesListOI40DBPurchaseOrder"></a>
# **findByCodesListOI40DBPurchaseOrder**
> List&lt;OI40DBPurchaseOrder&gt; findByCodesListOI40DBPurchaseOrder(codes)

findByCodes

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbPurchaseOrderRepositoryApi;


Oi40DbPurchaseOrderRepositoryApi apiInstance = new Oi40DbPurchaseOrderRepositoryApi();
List<String> codes = Arrays.asList(new List<String>()); // List<String> | codes
try {
    List<OI40DBPurchaseOrder> result = apiInstance.findByCodesListOI40DBPurchaseOrder(codes);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbPurchaseOrderRepositoryApi#findByCodesListOI40DBPurchaseOrder");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **codes** | **List&lt;String&gt;**| codes |

### Return type

[**List&lt;OI40DBPurchaseOrder&gt;**](OI40DBPurchaseOrder.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="findByQbeListOI40DBPurchaseOrder"></a>
# **findByQbeListOI40DBPurchaseOrder**
> List&lt;OI40DBPurchaseOrder&gt; findByQbeListOI40DBPurchaseOrder(qbe)

findByQbe

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbPurchaseOrderRepositoryApi;


Oi40DbPurchaseOrderRepositoryApi apiInstance = new Oi40DbPurchaseOrderRepositoryApi();
OI40DBPurchaseOrder qbe = new OI40DBPurchaseOrder(); // OI40DBPurchaseOrder | qbe
try {
    List<OI40DBPurchaseOrder> result = apiInstance.findByQbeListOI40DBPurchaseOrder(qbe);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbPurchaseOrderRepositoryApi#findByQbeListOI40DBPurchaseOrder");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **qbe** | [**OI40DBPurchaseOrder**](OI40DBPurchaseOrder.md)| qbe |

### Return type

[**List&lt;OI40DBPurchaseOrder&gt;**](OI40DBPurchaseOrder.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="findByQbePagedPageOI40DBPurchaseOrder"></a>
# **findByQbePagedPageOI40DBPurchaseOrder**
> PageOI40DBPurchaseOrder findByQbePagedPageOI40DBPurchaseOrder(qbe)

findByQbePaged

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbPurchaseOrderRepositoryApi;


Oi40DbPurchaseOrderRepositoryApi apiInstance = new Oi40DbPurchaseOrderRepositoryApi();
QbeSupportOI40DBPurchaseOrder qbe = new QbeSupportOI40DBPurchaseOrder(); // QbeSupportOI40DBPurchaseOrder | qbe
try {
    PageOI40DBPurchaseOrder result = apiInstance.findByQbePagedPageOI40DBPurchaseOrder(qbe);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbPurchaseOrderRepositoryApi#findByQbePagedPageOI40DBPurchaseOrder");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **qbe** | [**QbeSupportOI40DBPurchaseOrder**](QbeSupportOI40DBPurchaseOrder.md)| qbe |

### Return type

[**PageOI40DBPurchaseOrder**](PageOI40DBPurchaseOrder.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="updateListOI40DBPurchaseOrder"></a>
# **updateListOI40DBPurchaseOrder**
> List&lt;OI40DBPurchaseOrder&gt; updateListOI40DBPurchaseOrder(data)

update

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbPurchaseOrderRepositoryApi;


Oi40DbPurchaseOrderRepositoryApi apiInstance = new Oi40DbPurchaseOrderRepositoryApi();
List<OI40DBPurchaseOrder> data = Arrays.asList(new OI40DBPurchaseOrder()); // List<OI40DBPurchaseOrder> | data
try {
    List<OI40DBPurchaseOrder> result = apiInstance.updateListOI40DBPurchaseOrder(data);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbPurchaseOrderRepositoryApi#updateListOI40DBPurchaseOrder");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **data** | [**List&lt;OI40DBPurchaseOrder&gt;**](OI40DBPurchaseOrder.md)| data |

### Return type

[**List&lt;OI40DBPurchaseOrder&gt;**](OI40DBPurchaseOrder.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="updateSingleOI40DBPurchaseOrder"></a>
# **updateSingleOI40DBPurchaseOrder**
> OI40DBPurchaseOrder updateSingleOI40DBPurchaseOrder(data)

updateSingle

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbPurchaseOrderRepositoryApi;


Oi40DbPurchaseOrderRepositoryApi apiInstance = new Oi40DbPurchaseOrderRepositoryApi();
OI40DBPurchaseOrder data = new OI40DBPurchaseOrder(); // OI40DBPurchaseOrder | data
try {
    OI40DBPurchaseOrder result = apiInstance.updateSingleOI40DBPurchaseOrder(data);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbPurchaseOrderRepositoryApi#updateSingleOI40DBPurchaseOrder");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **data** | [**OI40DBPurchaseOrder**](OI40DBPurchaseOrder.md)| data |

### Return type

[**OI40DBPurchaseOrder**](OI40DBPurchaseOrder.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

