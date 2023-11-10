# Oi40DbPlantRepositoryApi

All URIs are relative to *https://localhost:8082/openi40-integration*

Method | HTTP request | Description
------------- | ------------- | -------------
[**deleteByCodeVoid15**](Oi40DbPlantRepositoryApi.md#deleteByCodeVoid15) | **GET** /integration/OI40DBPlant/deleteByCode/{code} | deleteByCode
[**deleteByCodesVoid15**](Oi40DbPlantRepositoryApi.md#deleteByCodesVoid15) | **POST** /integration/OI40DBPlant/deleteByCodes | deleteByCodes
[**doAutocompletePageOI40DBPlant**](Oi40DbPlantRepositoryApi.md#doAutocompletePageOI40DBPlant) | **POST** /integration/OI40DBPlant/doAutocomplete | doAutocomplete
[**doLookupPageOI40DBPlant**](Oi40DbPlantRepositoryApi.md#doLookupPageOI40DBPlant) | **POST** /integration/OI40DBPlant/doLookup | doLookup
[**findAllListOI40DBPlant**](Oi40DbPlantRepositoryApi.md#findAllListOI40DBPlant) | **GET** /integration/OI40DBPlant | findAll
[**findAllPageOI40DBPlant**](Oi40DbPlantRepositoryApi.md#findAllPageOI40DBPlant) | **POST** /integration/OI40DBPlant/findAllPaged | findAll
[**findByAfterIntegrationTsListOI40DBPlant**](Oi40DbPlantRepositoryApi.md#findByAfterIntegrationTsListOI40DBPlant) | **GET** /integration/OI40DBPlant/integratedAfter/{ts} | findByAfterIntegrationTs
[**findByAfterModifiedTimestampListOI40DBPlant**](Oi40DbPlantRepositoryApi.md#findByAfterModifiedTimestampListOI40DBPlant) | **GET** /integration/OI40DBPlant/modifiedAfter/{ts} | findByAfterModifiedTimestamp
[**findByCodeOI40DBPlant**](Oi40DbPlantRepositoryApi.md#findByCodeOI40DBPlant) | **GET** /integration/OI40DBPlant/byCode/{code} | findByCode
[**findByCodesListOI40DBPlant**](Oi40DbPlantRepositoryApi.md#findByCodesListOI40DBPlant) | **POST** /integration/OI40DBPlant/findByCodes | findByCodes
[**findByQbeListOI40DBPlant**](Oi40DbPlantRepositoryApi.md#findByQbeListOI40DBPlant) | **POST** /integration/OI40DBPlant/findByQbe | findByQbe
[**findByQbePagedPageOI40DBPlant**](Oi40DbPlantRepositoryApi.md#findByQbePagedPageOI40DBPlant) | **POST** /integration/OI40DBPlant/findByQbePaged | findByQbePaged
[**updateListOI40DBPlant**](Oi40DbPlantRepositoryApi.md#updateListOI40DBPlant) | **POST** /integration/OI40DBPlant/update | update
[**updateSingleOI40DBPlant**](Oi40DbPlantRepositoryApi.md#updateSingleOI40DBPlant) | **POST** /integration/OI40DBPlant/updateSingle | updateSingle


<a name="deleteByCodeVoid15"></a>
# **deleteByCodeVoid15**
> deleteByCodeVoid15(code)

deleteByCode

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbPlantRepositoryApi;


Oi40DbPlantRepositoryApi apiInstance = new Oi40DbPlantRepositoryApi();
String code = "code_example"; // String | code
try {
    apiInstance.deleteByCodeVoid15(code);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbPlantRepositoryApi#deleteByCodeVoid15");
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

<a name="deleteByCodesVoid15"></a>
# **deleteByCodesVoid15**
> deleteByCodesVoid15(codes)

deleteByCodes

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbPlantRepositoryApi;


Oi40DbPlantRepositoryApi apiInstance = new Oi40DbPlantRepositoryApi();
List<String> codes = Arrays.asList(new List<String>()); // List<String> | codes
try {
    apiInstance.deleteByCodesVoid15(codes);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbPlantRepositoryApi#deleteByCodesVoid15");
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

<a name="doAutocompletePageOI40DBPlant"></a>
# **doAutocompletePageOI40DBPlant**
> PageOI40DBPlant doAutocompletePageOI40DBPlant(autoCompleteData)

doAutocomplete

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbPlantRepositoryApi;


Oi40DbPlantRepositoryApi apiInstance = new Oi40DbPlantRepositoryApi();
AutoCompleteData autoCompleteData = new AutoCompleteData(); // AutoCompleteData | autoCompleteData
try {
    PageOI40DBPlant result = apiInstance.doAutocompletePageOI40DBPlant(autoCompleteData);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbPlantRepositoryApi#doAutocompletePageOI40DBPlant");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **autoCompleteData** | [**AutoCompleteData**](AutoCompleteData.md)| autoCompleteData |

### Return type

[**PageOI40DBPlant**](PageOI40DBPlant.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: */*

<a name="doLookupPageOI40DBPlant"></a>
# **doLookupPageOI40DBPlant**
> PageOI40DBPlant doLookupPageOI40DBPlant(lookup)

doLookup

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbPlantRepositoryApi;


Oi40DbPlantRepositoryApi apiInstance = new Oi40DbPlantRepositoryApi();
LookupData lookup = new LookupData(); // LookupData | lookup
try {
    PageOI40DBPlant result = apiInstance.doLookupPageOI40DBPlant(lookup);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbPlantRepositoryApi#doLookupPageOI40DBPlant");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **lookup** | [**LookupData**](LookupData.md)| lookup |

### Return type

[**PageOI40DBPlant**](PageOI40DBPlant.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: */*

<a name="findAllListOI40DBPlant"></a>
# **findAllListOI40DBPlant**
> List&lt;OI40DBPlant&gt; findAllListOI40DBPlant()

findAll

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbPlantRepositoryApi;


Oi40DbPlantRepositoryApi apiInstance = new Oi40DbPlantRepositoryApi();
try {
    List<OI40DBPlant> result = apiInstance.findAllListOI40DBPlant();
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbPlantRepositoryApi#findAllListOI40DBPlant");
    e.printStackTrace();
}
```

### Parameters
This endpoint does not need any parameter.

### Return type

[**List&lt;OI40DBPlant&gt;**](OI40DBPlant.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="findAllPageOI40DBPlant"></a>
# **findAllPageOI40DBPlant**
> PageOI40DBPlant findAllPageOI40DBPlant(p)

findAll

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbPlantRepositoryApi;


Oi40DbPlantRepositoryApi apiInstance = new Oi40DbPlantRepositoryApi();
PageInfo p = new PageInfo(); // PageInfo | p
try {
    PageOI40DBPlant result = apiInstance.findAllPageOI40DBPlant(p);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbPlantRepositoryApi#findAllPageOI40DBPlant");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **p** | [**PageInfo**](PageInfo.md)| p |

### Return type

[**PageOI40DBPlant**](PageOI40DBPlant.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="findByAfterIntegrationTsListOI40DBPlant"></a>
# **findByAfterIntegrationTsListOI40DBPlant**
> List&lt;OI40DBPlant&gt; findByAfterIntegrationTsListOI40DBPlant(ts)

findByAfterIntegrationTs

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbPlantRepositoryApi;


Oi40DbPlantRepositoryApi apiInstance = new Oi40DbPlantRepositoryApi();
String ts = "ts_example"; // String | ts
try {
    List<OI40DBPlant> result = apiInstance.findByAfterIntegrationTsListOI40DBPlant(ts);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbPlantRepositoryApi#findByAfterIntegrationTsListOI40DBPlant");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **ts** | **String**| ts |

### Return type

[**List&lt;OI40DBPlant&gt;**](OI40DBPlant.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*

<a name="findByAfterModifiedTimestampListOI40DBPlant"></a>
# **findByAfterModifiedTimestampListOI40DBPlant**
> List&lt;OI40DBPlant&gt; findByAfterModifiedTimestampListOI40DBPlant(ts)

findByAfterModifiedTimestamp

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbPlantRepositoryApi;


Oi40DbPlantRepositoryApi apiInstance = new Oi40DbPlantRepositoryApi();
String ts = "ts_example"; // String | ts
try {
    List<OI40DBPlant> result = apiInstance.findByAfterModifiedTimestampListOI40DBPlant(ts);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbPlantRepositoryApi#findByAfterModifiedTimestampListOI40DBPlant");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **ts** | **String**| ts |

### Return type

[**List&lt;OI40DBPlant&gt;**](OI40DBPlant.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*

<a name="findByCodeOI40DBPlant"></a>
# **findByCodeOI40DBPlant**
> OI40DBPlant findByCodeOI40DBPlant(code)

findByCode

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbPlantRepositoryApi;


Oi40DbPlantRepositoryApi apiInstance = new Oi40DbPlantRepositoryApi();
String code = "code_example"; // String | code
try {
    OI40DBPlant result = apiInstance.findByCodeOI40DBPlant(code);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbPlantRepositoryApi#findByCodeOI40DBPlant");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **code** | **String**| code |

### Return type

[**OI40DBPlant**](OI40DBPlant.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="findByCodesListOI40DBPlant"></a>
# **findByCodesListOI40DBPlant**
> List&lt;OI40DBPlant&gt; findByCodesListOI40DBPlant(codes)

findByCodes

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbPlantRepositoryApi;


Oi40DbPlantRepositoryApi apiInstance = new Oi40DbPlantRepositoryApi();
List<String> codes = Arrays.asList(new List<String>()); // List<String> | codes
try {
    List<OI40DBPlant> result = apiInstance.findByCodesListOI40DBPlant(codes);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbPlantRepositoryApi#findByCodesListOI40DBPlant");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **codes** | **List&lt;String&gt;**| codes |

### Return type

[**List&lt;OI40DBPlant&gt;**](OI40DBPlant.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="findByQbeListOI40DBPlant"></a>
# **findByQbeListOI40DBPlant**
> List&lt;OI40DBPlant&gt; findByQbeListOI40DBPlant(qbe)

findByQbe

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbPlantRepositoryApi;


Oi40DbPlantRepositoryApi apiInstance = new Oi40DbPlantRepositoryApi();
OI40DBPlant qbe = new OI40DBPlant(); // OI40DBPlant | qbe
try {
    List<OI40DBPlant> result = apiInstance.findByQbeListOI40DBPlant(qbe);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbPlantRepositoryApi#findByQbeListOI40DBPlant");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **qbe** | [**OI40DBPlant**](OI40DBPlant.md)| qbe |

### Return type

[**List&lt;OI40DBPlant&gt;**](OI40DBPlant.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="findByQbePagedPageOI40DBPlant"></a>
# **findByQbePagedPageOI40DBPlant**
> PageOI40DBPlant findByQbePagedPageOI40DBPlant(qbe)

findByQbePaged

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbPlantRepositoryApi;


Oi40DbPlantRepositoryApi apiInstance = new Oi40DbPlantRepositoryApi();
QbeSupportOI40DBPlant qbe = new QbeSupportOI40DBPlant(); // QbeSupportOI40DBPlant | qbe
try {
    PageOI40DBPlant result = apiInstance.findByQbePagedPageOI40DBPlant(qbe);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbPlantRepositoryApi#findByQbePagedPageOI40DBPlant");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **qbe** | [**QbeSupportOI40DBPlant**](QbeSupportOI40DBPlant.md)| qbe |

### Return type

[**PageOI40DBPlant**](PageOI40DBPlant.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="updateListOI40DBPlant"></a>
# **updateListOI40DBPlant**
> List&lt;OI40DBPlant&gt; updateListOI40DBPlant(data)

update

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbPlantRepositoryApi;


Oi40DbPlantRepositoryApi apiInstance = new Oi40DbPlantRepositoryApi();
List<OI40DBPlant> data = Arrays.asList(new OI40DBPlant()); // List<OI40DBPlant> | data
try {
    List<OI40DBPlant> result = apiInstance.updateListOI40DBPlant(data);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbPlantRepositoryApi#updateListOI40DBPlant");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **data** | [**List&lt;OI40DBPlant&gt;**](OI40DBPlant.md)| data |

### Return type

[**List&lt;OI40DBPlant&gt;**](OI40DBPlant.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="updateSingleOI40DBPlant"></a>
# **updateSingleOI40DBPlant**
> OI40DBPlant updateSingleOI40DBPlant(data)

updateSingle

### Example
```java
// Import classes:
//import com.openi40.dbmodel.java.client.invoker.ApiException;
//import com.openi40.dbmodel.java.client.api.Oi40DbPlantRepositoryApi;


Oi40DbPlantRepositoryApi apiInstance = new Oi40DbPlantRepositoryApi();
OI40DBPlant data = new OI40DBPlant(); // OI40DBPlant | data
try {
    OI40DBPlant result = apiInstance.updateSingleOI40DBPlant(data);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling Oi40DbPlantRepositoryApi#updateSingleOI40DBPlant");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **data** | [**OI40DBPlant**](OI40DBPlant.md)| data |

### Return type

[**OI40DBPlant**](OI40DBPlant.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

