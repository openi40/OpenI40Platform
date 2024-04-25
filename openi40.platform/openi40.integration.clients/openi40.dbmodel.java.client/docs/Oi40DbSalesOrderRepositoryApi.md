# Oi40DbSalesOrderRepositoryApi

All URIs are relative to *https://localhost:8083/openi40-backoffice*

Method | HTTP request | Description
------------- | ------------- | -------------
[**deleteByCodeVoid23**](Oi40DbSalesOrderRepositoryApi.md#deleteByCodeVoid23) | **GET** /integration/OI40DBSalesOrder/deleteByCode/{code} | deleteByCode
[**deleteByCodesVoid23**](Oi40DbSalesOrderRepositoryApi.md#deleteByCodesVoid23) | **POST** /integration/OI40DBSalesOrder/deleteByCodes | deleteByCodes
[**doAutocompletePageOI40DBSalesOrder**](Oi40DbSalesOrderRepositoryApi.md#doAutocompletePageOI40DBSalesOrder) | **POST** /integration/OI40DBSalesOrder/doAutocomplete | doAutocomplete
[**doLookupPageOI40DBSalesOrder**](Oi40DbSalesOrderRepositoryApi.md#doLookupPageOI40DBSalesOrder) | **POST** /integration/OI40DBSalesOrder/doLookup | doLookup
[**findAllListOI40DBSalesOrder**](Oi40DbSalesOrderRepositoryApi.md#findAllListOI40DBSalesOrder) | **GET** /integration/OI40DBSalesOrder | findAll
[**findAllPageOI40DBSalesOrder**](Oi40DbSalesOrderRepositoryApi.md#findAllPageOI40DBSalesOrder) | **POST** /integration/OI40DBSalesOrder/findAllPaged | findAll
[**findByAfterIntegrationTsListOI40DBSalesOrder**](Oi40DbSalesOrderRepositoryApi.md#findByAfterIntegrationTsListOI40DBSalesOrder) | **GET** /integration/OI40DBSalesOrder/integratedAfter/{ts} | findByAfterIntegrationTs
[**findByAfterModifiedTimestampListOI40DBSalesOrder**](Oi40DbSalesOrderRepositoryApi.md#findByAfterModifiedTimestampListOI40DBSalesOrder) | **GET** /integration/OI40DBSalesOrder/modifiedAfter/{ts} | findByAfterModifiedTimestamp
[**findByCodeOI40DBSalesOrder**](Oi40DbSalesOrderRepositoryApi.md#findByCodeOI40DBSalesOrder) | **GET** /integration/OI40DBSalesOrder/byCode/{code} | findByCode
[**findByCodesListOI40DBSalesOrder**](Oi40DbSalesOrderRepositoryApi.md#findByCodesListOI40DBSalesOrder) | **POST** /integration/OI40DBSalesOrder/findByCodes | findByCodes
[**findByQbeListOI40DBSalesOrder**](Oi40DbSalesOrderRepositoryApi.md#findByQbeListOI40DBSalesOrder) | **POST** /integration/OI40DBSalesOrder/findByQbe | findByQbe
[**findByQbePagedPageOI40DBSalesOrder**](Oi40DbSalesOrderRepositoryApi.md#findByQbePagedPageOI40DBSalesOrder) | **POST** /integration/OI40DBSalesOrder/findByQbePaged | findByQbePaged
[**updateListOI40DBSalesOrder**](Oi40DbSalesOrderRepositoryApi.md#updateListOI40DBSalesOrder) | **POST** /integration/OI40DBSalesOrder/update | update
[**updateSingleOI40DBSalesOrder**](Oi40DbSalesOrderRepositoryApi.md#updateSingleOI40DBSalesOrder) | **POST** /integration/OI40DBSalesOrder/updateSingle | updateSingle


<a name="deleteByCodeVoid23"></a>
# **deleteByCodeVoid23**
> deleteByCodeVoid23(code)

deleteByCode

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbSalesOrderRepositoryApi;


Oi40DbSalesOrderRepositoryApi apiInstance = new Oi40DbSalesOrderRepositoryApi();
String code = "code_example"; // String | code
try {
    apiInstance.deleteByCodeVoid23(code);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbSalesOrderRepositoryApi#deleteByCodeVoid23");
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

<a name="deleteByCodesVoid23"></a>
# **deleteByCodesVoid23**
> deleteByCodesVoid23(codes)

deleteByCodes

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbSalesOrderRepositoryApi;


Oi40DbSalesOrderRepositoryApi apiInstance = new Oi40DbSalesOrderRepositoryApi();
List<String> codes = Arrays.asList(new List<String>()); // List<String> | codes
try {
    apiInstance.deleteByCodesVoid23(codes);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbSalesOrderRepositoryApi#deleteByCodesVoid23");
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

<a name="doAutocompletePageOI40DBSalesOrder"></a>
# **doAutocompletePageOI40DBSalesOrder**
> PageOI40DBSalesOrder doAutocompletePageOI40DBSalesOrder(autoCompleteData)

doAutocomplete

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbSalesOrderRepositoryApi;


Oi40DbSalesOrderRepositoryApi apiInstance = new Oi40DbSalesOrderRepositoryApi();
AutoCompleteData autoCompleteData = new AutoCompleteData(); // AutoCompleteData | autoCompleteData
try {
    PageOI40DBSalesOrder result = apiInstance.doAutocompletePageOI40DBSalesOrder(autoCompleteData);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbSalesOrderRepositoryApi#doAutocompletePageOI40DBSalesOrder");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **autoCompleteData** | [**AutoCompleteData**](AutoCompleteData.md)| autoCompleteData |

### Return type

[**PageOI40DBSalesOrder**](PageOI40DBSalesOrder.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: */*

<a name="doLookupPageOI40DBSalesOrder"></a>
# **doLookupPageOI40DBSalesOrder**
> PageOI40DBSalesOrder doLookupPageOI40DBSalesOrder(lookup)

doLookup

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbSalesOrderRepositoryApi;


Oi40DbSalesOrderRepositoryApi apiInstance = new Oi40DbSalesOrderRepositoryApi();
LookupData lookup = new LookupData(); // LookupData | lookup
try {
    PageOI40DBSalesOrder result = apiInstance.doLookupPageOI40DBSalesOrder(lookup);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbSalesOrderRepositoryApi#doLookupPageOI40DBSalesOrder");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **lookup** | [**LookupData**](LookupData.md)| lookup |

### Return type

[**PageOI40DBSalesOrder**](PageOI40DBSalesOrder.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: */*

<a name="findAllListOI40DBSalesOrder"></a>
# **findAllListOI40DBSalesOrder**
> List&lt;OI40DBSalesOrder&gt; findAllListOI40DBSalesOrder()

findAll

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbSalesOrderRepositoryApi;


Oi40DbSalesOrderRepositoryApi apiInstance = new Oi40DbSalesOrderRepositoryApi();
try {
    List<OI40DBSalesOrder> result = apiInstance.findAllListOI40DBSalesOrder();
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbSalesOrderRepositoryApi#findAllListOI40DBSalesOrder");
    e.printStackTrace();
}
```

### Parameters
This endpoint does not need any parameter.

### Return type

[**List&lt;OI40DBSalesOrder&gt;**](OI40DBSalesOrder.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="findAllPageOI40DBSalesOrder"></a>
# **findAllPageOI40DBSalesOrder**
> PageOI40DBSalesOrder findAllPageOI40DBSalesOrder(p)

findAll

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbSalesOrderRepositoryApi;


Oi40DbSalesOrderRepositoryApi apiInstance = new Oi40DbSalesOrderRepositoryApi();
PageInfo p = new PageInfo(); // PageInfo | p
try {
    PageOI40DBSalesOrder result = apiInstance.findAllPageOI40DBSalesOrder(p);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbSalesOrderRepositoryApi#findAllPageOI40DBSalesOrder");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **p** | [**PageInfo**](PageInfo.md)| p |

### Return type

[**PageOI40DBSalesOrder**](PageOI40DBSalesOrder.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="findByAfterIntegrationTsListOI40DBSalesOrder"></a>
# **findByAfterIntegrationTsListOI40DBSalesOrder**
> List&lt;OI40DBSalesOrder&gt; findByAfterIntegrationTsListOI40DBSalesOrder(ts)

findByAfterIntegrationTs

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbSalesOrderRepositoryApi;


Oi40DbSalesOrderRepositoryApi apiInstance = new Oi40DbSalesOrderRepositoryApi();
String ts = "ts_example"; // String | ts
try {
    List<OI40DBSalesOrder> result = apiInstance.findByAfterIntegrationTsListOI40DBSalesOrder(ts);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbSalesOrderRepositoryApi#findByAfterIntegrationTsListOI40DBSalesOrder");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **ts** | **String**| ts |

### Return type

[**List&lt;OI40DBSalesOrder&gt;**](OI40DBSalesOrder.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*

<a name="findByAfterModifiedTimestampListOI40DBSalesOrder"></a>
# **findByAfterModifiedTimestampListOI40DBSalesOrder**
> List&lt;OI40DBSalesOrder&gt; findByAfterModifiedTimestampListOI40DBSalesOrder(ts)

findByAfterModifiedTimestamp

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbSalesOrderRepositoryApi;


Oi40DbSalesOrderRepositoryApi apiInstance = new Oi40DbSalesOrderRepositoryApi();
String ts = "ts_example"; // String | ts
try {
    List<OI40DBSalesOrder> result = apiInstance.findByAfterModifiedTimestampListOI40DBSalesOrder(ts);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbSalesOrderRepositoryApi#findByAfterModifiedTimestampListOI40DBSalesOrder");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **ts** | **String**| ts |

### Return type

[**List&lt;OI40DBSalesOrder&gt;**](OI40DBSalesOrder.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*

<a name="findByCodeOI40DBSalesOrder"></a>
# **findByCodeOI40DBSalesOrder**
> OI40DBSalesOrder findByCodeOI40DBSalesOrder(code)

findByCode

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbSalesOrderRepositoryApi;


Oi40DbSalesOrderRepositoryApi apiInstance = new Oi40DbSalesOrderRepositoryApi();
String code = "code_example"; // String | code
try {
    OI40DBSalesOrder result = apiInstance.findByCodeOI40DBSalesOrder(code);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbSalesOrderRepositoryApi#findByCodeOI40DBSalesOrder");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **code** | **String**| code |

### Return type

[**OI40DBSalesOrder**](OI40DBSalesOrder.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="findByCodesListOI40DBSalesOrder"></a>
# **findByCodesListOI40DBSalesOrder**
> List&lt;OI40DBSalesOrder&gt; findByCodesListOI40DBSalesOrder(codes)

findByCodes

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbSalesOrderRepositoryApi;


Oi40DbSalesOrderRepositoryApi apiInstance = new Oi40DbSalesOrderRepositoryApi();
List<String> codes = Arrays.asList(new List<String>()); // List<String> | codes
try {
    List<OI40DBSalesOrder> result = apiInstance.findByCodesListOI40DBSalesOrder(codes);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbSalesOrderRepositoryApi#findByCodesListOI40DBSalesOrder");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **codes** | **List&lt;String&gt;**| codes |

### Return type

[**List&lt;OI40DBSalesOrder&gt;**](OI40DBSalesOrder.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="findByQbeListOI40DBSalesOrder"></a>
# **findByQbeListOI40DBSalesOrder**
> List&lt;OI40DBSalesOrder&gt; findByQbeListOI40DBSalesOrder(qbe)

findByQbe

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbSalesOrderRepositoryApi;


Oi40DbSalesOrderRepositoryApi apiInstance = new Oi40DbSalesOrderRepositoryApi();
OI40DBSalesOrder qbe = new OI40DBSalesOrder(); // OI40DBSalesOrder | qbe
try {
    List<OI40DBSalesOrder> result = apiInstance.findByQbeListOI40DBSalesOrder(qbe);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbSalesOrderRepositoryApi#findByQbeListOI40DBSalesOrder");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **qbe** | [**OI40DBSalesOrder**](OI40DBSalesOrder.md)| qbe |

### Return type

[**List&lt;OI40DBSalesOrder&gt;**](OI40DBSalesOrder.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="findByQbePagedPageOI40DBSalesOrder"></a>
# **findByQbePagedPageOI40DBSalesOrder**
> PageOI40DBSalesOrder findByQbePagedPageOI40DBSalesOrder(qbe)

findByQbePaged

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbSalesOrderRepositoryApi;


Oi40DbSalesOrderRepositoryApi apiInstance = new Oi40DbSalesOrderRepositoryApi();
QbeSupportOI40DBSalesOrder qbe = new QbeSupportOI40DBSalesOrder(); // QbeSupportOI40DBSalesOrder | qbe
try {
    PageOI40DBSalesOrder result = apiInstance.findByQbePagedPageOI40DBSalesOrder(qbe);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbSalesOrderRepositoryApi#findByQbePagedPageOI40DBSalesOrder");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **qbe** | [**QbeSupportOI40DBSalesOrder**](QbeSupportOI40DBSalesOrder.md)| qbe |

### Return type

[**PageOI40DBSalesOrder**](PageOI40DBSalesOrder.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="updateListOI40DBSalesOrder"></a>
# **updateListOI40DBSalesOrder**
> List&lt;OI40DBSalesOrder&gt; updateListOI40DBSalesOrder(data)

update

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbSalesOrderRepositoryApi;


Oi40DbSalesOrderRepositoryApi apiInstance = new Oi40DbSalesOrderRepositoryApi();
List<OI40DBSalesOrder> data = Arrays.asList(new OI40DBSalesOrder()); // List<OI40DBSalesOrder> | data
try {
    List<OI40DBSalesOrder> result = apiInstance.updateListOI40DBSalesOrder(data);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbSalesOrderRepositoryApi#updateListOI40DBSalesOrder");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **data** | [**List&lt;OI40DBSalesOrder&gt;**](OI40DBSalesOrder.md)| data |

### Return type

[**List&lt;OI40DBSalesOrder&gt;**](OI40DBSalesOrder.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="updateSingleOI40DBSalesOrder"></a>
# **updateSingleOI40DBSalesOrder**
> OI40DBSalesOrder updateSingleOI40DBSalesOrder(data)

updateSingle

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbSalesOrderRepositoryApi;


Oi40DbSalesOrderRepositoryApi apiInstance = new Oi40DbSalesOrderRepositoryApi();
OI40DBSalesOrder data = new OI40DBSalesOrder(); // OI40DBSalesOrder | data
try {
    OI40DBSalesOrder result = apiInstance.updateSingleOI40DBSalesOrder(data);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbSalesOrderRepositoryApi#updateSingleOI40DBSalesOrder");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **data** | [**OI40DBSalesOrder**](OI40DBSalesOrder.md)| data |

### Return type

[**OI40DBSalesOrder**](OI40DBSalesOrder.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

