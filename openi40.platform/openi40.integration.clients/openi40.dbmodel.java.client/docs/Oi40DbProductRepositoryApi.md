# Oi40DbProductRepositoryApi

All URIs are relative to *https://localhost:8083/openi40-backoffice*

Method | HTTP request | Description
------------- | ------------- | -------------
[**deleteByCodeVoid16**](Oi40DbProductRepositoryApi.md#deleteByCodeVoid16) | **GET** /integration/OI40DBProduct/deleteByCode/{code} | deleteByCode
[**deleteByCodesVoid16**](Oi40DbProductRepositoryApi.md#deleteByCodesVoid16) | **POST** /integration/OI40DBProduct/deleteByCodes | deleteByCodes
[**doAutocompletePageOI40DBProduct**](Oi40DbProductRepositoryApi.md#doAutocompletePageOI40DBProduct) | **POST** /integration/OI40DBProduct/doAutocomplete | doAutocomplete
[**doLookupPageOI40DBProduct**](Oi40DbProductRepositoryApi.md#doLookupPageOI40DBProduct) | **POST** /integration/OI40DBProduct/doLookup | doLookup
[**findAllListOI40DBProduct**](Oi40DbProductRepositoryApi.md#findAllListOI40DBProduct) | **GET** /integration/OI40DBProduct | findAll
[**findAllPageOI40DBProduct**](Oi40DbProductRepositoryApi.md#findAllPageOI40DBProduct) | **POST** /integration/OI40DBProduct/findAllPaged | findAll
[**findByAfterIntegrationTsListOI40DBProduct**](Oi40DbProductRepositoryApi.md#findByAfterIntegrationTsListOI40DBProduct) | **GET** /integration/OI40DBProduct/integratedAfter/{ts} | findByAfterIntegrationTs
[**findByAfterModifiedTimestampListOI40DBProduct**](Oi40DbProductRepositoryApi.md#findByAfterModifiedTimestampListOI40DBProduct) | **GET** /integration/OI40DBProduct/modifiedAfter/{ts} | findByAfterModifiedTimestamp
[**findByCodeOI40DBProduct**](Oi40DbProductRepositoryApi.md#findByCodeOI40DBProduct) | **GET** /integration/OI40DBProduct/byCode/{code} | findByCode
[**findByCodesListOI40DBProduct**](Oi40DbProductRepositoryApi.md#findByCodesListOI40DBProduct) | **POST** /integration/OI40DBProduct/findByCodes | findByCodes
[**findByQbeListOI40DBProduct**](Oi40DbProductRepositoryApi.md#findByQbeListOI40DBProduct) | **POST** /integration/OI40DBProduct/findByQbe | findByQbe
[**findByQbePagedPageOI40DBProduct**](Oi40DbProductRepositoryApi.md#findByQbePagedPageOI40DBProduct) | **POST** /integration/OI40DBProduct/findByQbePaged | findByQbePaged
[**updateListOI40DBProduct**](Oi40DbProductRepositoryApi.md#updateListOI40DBProduct) | **POST** /integration/OI40DBProduct/update | update
[**updateSingleOI40DBProduct**](Oi40DbProductRepositoryApi.md#updateSingleOI40DBProduct) | **POST** /integration/OI40DBProduct/updateSingle | updateSingle


<a name="deleteByCodeVoid16"></a>
# **deleteByCodeVoid16**
> deleteByCodeVoid16(code)

deleteByCode

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbProductRepositoryApi;


Oi40DbProductRepositoryApi apiInstance = new Oi40DbProductRepositoryApi();
String code = "code_example"; // String | code
try {
    apiInstance.deleteByCodeVoid16(code);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbProductRepositoryApi#deleteByCodeVoid16");
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

<a name="deleteByCodesVoid16"></a>
# **deleteByCodesVoid16**
> deleteByCodesVoid16(codes)

deleteByCodes

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbProductRepositoryApi;


Oi40DbProductRepositoryApi apiInstance = new Oi40DbProductRepositoryApi();
List<String> codes = Arrays.asList(new List<String>()); // List<String> | codes
try {
    apiInstance.deleteByCodesVoid16(codes);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbProductRepositoryApi#deleteByCodesVoid16");
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

<a name="doAutocompletePageOI40DBProduct"></a>
# **doAutocompletePageOI40DBProduct**
> PageOI40DBProduct doAutocompletePageOI40DBProduct(autoCompleteData)

doAutocomplete

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbProductRepositoryApi;


Oi40DbProductRepositoryApi apiInstance = new Oi40DbProductRepositoryApi();
AutoCompleteData autoCompleteData = new AutoCompleteData(); // AutoCompleteData | autoCompleteData
try {
    PageOI40DBProduct result = apiInstance.doAutocompletePageOI40DBProduct(autoCompleteData);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbProductRepositoryApi#doAutocompletePageOI40DBProduct");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **autoCompleteData** | [**AutoCompleteData**](AutoCompleteData.md)| autoCompleteData |

### Return type

[**PageOI40DBProduct**](PageOI40DBProduct.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: */*

<a name="doLookupPageOI40DBProduct"></a>
# **doLookupPageOI40DBProduct**
> PageOI40DBProduct doLookupPageOI40DBProduct(lookup)

doLookup

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbProductRepositoryApi;


Oi40DbProductRepositoryApi apiInstance = new Oi40DbProductRepositoryApi();
LookupData lookup = new LookupData(); // LookupData | lookup
try {
    PageOI40DBProduct result = apiInstance.doLookupPageOI40DBProduct(lookup);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbProductRepositoryApi#doLookupPageOI40DBProduct");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **lookup** | [**LookupData**](LookupData.md)| lookup |

### Return type

[**PageOI40DBProduct**](PageOI40DBProduct.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: */*

<a name="findAllListOI40DBProduct"></a>
# **findAllListOI40DBProduct**
> List&lt;OI40DBProduct&gt; findAllListOI40DBProduct()

findAll

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbProductRepositoryApi;


Oi40DbProductRepositoryApi apiInstance = new Oi40DbProductRepositoryApi();
try {
    List<OI40DBProduct> result = apiInstance.findAllListOI40DBProduct();
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbProductRepositoryApi#findAllListOI40DBProduct");
    e.printStackTrace();
}
```

### Parameters
This endpoint does not need any parameter.

### Return type

[**List&lt;OI40DBProduct&gt;**](OI40DBProduct.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="findAllPageOI40DBProduct"></a>
# **findAllPageOI40DBProduct**
> PageOI40DBProduct findAllPageOI40DBProduct(p)

findAll

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbProductRepositoryApi;


Oi40DbProductRepositoryApi apiInstance = new Oi40DbProductRepositoryApi();
PageInfo p = new PageInfo(); // PageInfo | p
try {
    PageOI40DBProduct result = apiInstance.findAllPageOI40DBProduct(p);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbProductRepositoryApi#findAllPageOI40DBProduct");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **p** | [**PageInfo**](PageInfo.md)| p |

### Return type

[**PageOI40DBProduct**](PageOI40DBProduct.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="findByAfterIntegrationTsListOI40DBProduct"></a>
# **findByAfterIntegrationTsListOI40DBProduct**
> List&lt;OI40DBProduct&gt; findByAfterIntegrationTsListOI40DBProduct(ts)

findByAfterIntegrationTs

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbProductRepositoryApi;


Oi40DbProductRepositoryApi apiInstance = new Oi40DbProductRepositoryApi();
String ts = "ts_example"; // String | ts
try {
    List<OI40DBProduct> result = apiInstance.findByAfterIntegrationTsListOI40DBProduct(ts);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbProductRepositoryApi#findByAfterIntegrationTsListOI40DBProduct");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **ts** | **String**| ts |

### Return type

[**List&lt;OI40DBProduct&gt;**](OI40DBProduct.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*

<a name="findByAfterModifiedTimestampListOI40DBProduct"></a>
# **findByAfterModifiedTimestampListOI40DBProduct**
> List&lt;OI40DBProduct&gt; findByAfterModifiedTimestampListOI40DBProduct(ts)

findByAfterModifiedTimestamp

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbProductRepositoryApi;


Oi40DbProductRepositoryApi apiInstance = new Oi40DbProductRepositoryApi();
String ts = "ts_example"; // String | ts
try {
    List<OI40DBProduct> result = apiInstance.findByAfterModifiedTimestampListOI40DBProduct(ts);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbProductRepositoryApi#findByAfterModifiedTimestampListOI40DBProduct");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **ts** | **String**| ts |

### Return type

[**List&lt;OI40DBProduct&gt;**](OI40DBProduct.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*

<a name="findByCodeOI40DBProduct"></a>
# **findByCodeOI40DBProduct**
> OI40DBProduct findByCodeOI40DBProduct(code)

findByCode

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbProductRepositoryApi;


Oi40DbProductRepositoryApi apiInstance = new Oi40DbProductRepositoryApi();
String code = "code_example"; // String | code
try {
    OI40DBProduct result = apiInstance.findByCodeOI40DBProduct(code);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbProductRepositoryApi#findByCodeOI40DBProduct");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **code** | **String**| code |

### Return type

[**OI40DBProduct**](OI40DBProduct.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="findByCodesListOI40DBProduct"></a>
# **findByCodesListOI40DBProduct**
> List&lt;OI40DBProduct&gt; findByCodesListOI40DBProduct(codes)

findByCodes

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbProductRepositoryApi;


Oi40DbProductRepositoryApi apiInstance = new Oi40DbProductRepositoryApi();
List<String> codes = Arrays.asList(new List<String>()); // List<String> | codes
try {
    List<OI40DBProduct> result = apiInstance.findByCodesListOI40DBProduct(codes);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbProductRepositoryApi#findByCodesListOI40DBProduct");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **codes** | **List&lt;String&gt;**| codes |

### Return type

[**List&lt;OI40DBProduct&gt;**](OI40DBProduct.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="findByQbeListOI40DBProduct"></a>
# **findByQbeListOI40DBProduct**
> List&lt;OI40DBProduct&gt; findByQbeListOI40DBProduct(qbe)

findByQbe

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbProductRepositoryApi;


Oi40DbProductRepositoryApi apiInstance = new Oi40DbProductRepositoryApi();
OI40DBProduct qbe = new OI40DBProduct(); // OI40DBProduct | qbe
try {
    List<OI40DBProduct> result = apiInstance.findByQbeListOI40DBProduct(qbe);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbProductRepositoryApi#findByQbeListOI40DBProduct");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **qbe** | [**OI40DBProduct**](OI40DBProduct.md)| qbe |

### Return type

[**List&lt;OI40DBProduct&gt;**](OI40DBProduct.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="findByQbePagedPageOI40DBProduct"></a>
# **findByQbePagedPageOI40DBProduct**
> PageOI40DBProduct findByQbePagedPageOI40DBProduct(qbe)

findByQbePaged

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbProductRepositoryApi;


Oi40DbProductRepositoryApi apiInstance = new Oi40DbProductRepositoryApi();
QbeSupportOI40DBProduct qbe = new QbeSupportOI40DBProduct(); // QbeSupportOI40DBProduct | qbe
try {
    PageOI40DBProduct result = apiInstance.findByQbePagedPageOI40DBProduct(qbe);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbProductRepositoryApi#findByQbePagedPageOI40DBProduct");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **qbe** | [**QbeSupportOI40DBProduct**](QbeSupportOI40DBProduct.md)| qbe |

### Return type

[**PageOI40DBProduct**](PageOI40DBProduct.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="updateListOI40DBProduct"></a>
# **updateListOI40DBProduct**
> List&lt;OI40DBProduct&gt; updateListOI40DBProduct(data)

update

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbProductRepositoryApi;


Oi40DbProductRepositoryApi apiInstance = new Oi40DbProductRepositoryApi();
List<OI40DBProduct> data = Arrays.asList(new OI40DBProduct()); // List<OI40DBProduct> | data
try {
    List<OI40DBProduct> result = apiInstance.updateListOI40DBProduct(data);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbProductRepositoryApi#updateListOI40DBProduct");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **data** | [**List&lt;OI40DBProduct&gt;**](OI40DBProduct.md)| data |

### Return type

[**List&lt;OI40DBProduct&gt;**](OI40DBProduct.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="updateSingleOI40DBProduct"></a>
# **updateSingleOI40DBProduct**
> OI40DBProduct updateSingleOI40DBProduct(data)

updateSingle

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbProductRepositoryApi;


Oi40DbProductRepositoryApi apiInstance = new Oi40DbProductRepositoryApi();
OI40DBProduct data = new OI40DBProduct(); // OI40DBProduct | data
try {
    OI40DBProduct result = apiInstance.updateSingleOI40DBProduct(data);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbProductRepositoryApi#updateSingleOI40DBProduct");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **data** | [**OI40DBProduct**](OI40DBProduct.md)| data |

### Return type

[**OI40DBProduct**](OI40DBProduct.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

